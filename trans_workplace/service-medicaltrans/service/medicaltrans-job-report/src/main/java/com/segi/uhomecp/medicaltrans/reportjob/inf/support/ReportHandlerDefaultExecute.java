package com.segi.uhomecp.medicaltrans.reportjob.inf.support;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.report.schedule.model.ExecuteHandler;
import com.segi.uhomecp.medicaltrans.report.schedule.model.ScheduleLog;
import com.segi.uhomecp.medicaltrans.reportjob.enums.HandlerOpEnums;
import com.segi.uhomecp.medicaltrans.reportjob.enums.HandlerStateEnums;
import com.segi.uhomecp.medicaltrans.reportjob.enums.ScheduleStatusEnums;
import com.segi.uhomecp.medicaltrans.reportjob.handler.HandlerCommunication;
import com.segi.uhomecp.medicaltrans.reportjob.handler.HandlerThreadFactory;
import com.segi.uhomecp.medicaltrans.reportjob.inf.AbstractDefaultHandler;
import com.segi.uhomecp.medicaltrans.reportjob.inf.ReportHandler;
import com.segi.uhomecp.medicaltrans.reportjob.inf.bean.ExtractData;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.model.MtTaskExtract;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.service.TransService;
import com.segi.uhomecp.medicaltrans.reportjob.report.base.ReportHandlerService;
import com.segi.uhomecp.medicaltrans.reportjob.report.base.ReportScheduleService;
import com.segi.uhomecp.medicaltrans.reportjob.util.ReportUtils;
import com.segi.uhomecp.medicaltrans.taskgroup.runner.WirterRunner;
import com.segi.uhomecp.utils.SpringContextUtils;

@Component(value = "reportHandlerExecute")
@Scope("prototype")
@Lazy(false)
public class ReportHandlerDefaultExecute extends AbstractDefaultHandler<ExtractData<MtTaskExtract>> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportHandlerDefaultExecute.class);
	
	private static final int WAIT_MIN = 300;
	
	private static final int MILLISECOND = 1000;
	
	private static final int CUTLEN = 2000;
	
	@Autowired
	private ReportHandlerService reportHandlerService;
	
	@Autowired
	private ReportScheduleService reportScheduleService;
	
	@Autowired
	private TransService transService;
	
	private ConcurrentHashMap<String, HandlerCommunication> communicationMap;
	
	private ConcurrentHashMap<String, Object> countMap;
	
	@PostConstruct
	@Override
	public synchronized void init() {
		if (this.getHandlerInfos().isEmpty()) {
			LOGGER.info("=====>初始化执行器配置信息");
			this.setHandlerInfos(reportHandlerService.getHandlerList());
			LOGGER.info("=====>初始化执行器配置信息:{}", this.getHandlerInfos());
		}
		countMap = new ConcurrentHashMap<String, Object>();
		communicationMap = new ConcurrentHashMap<String, HandlerCommunication>();
		for (ExecuteHandler exh : this.getHandlerInfos()) {
			communicationMap.put(exh.getHandlerName(), new HandlerCommunication(exh.getHandlerName()));
		}
	}
	
	/**
	 * 获取异步handler数量
	 * @param handlers handlers
	 * @return
	 */
	private int getCountDownLatch(List<ExecuteHandler> handlers) {
		int count = 0;
		for (ExecuteHandler exh : handlers) {
			if (HandlerOpEnums.ASYNC.getValue().equals(exh.getSyncFlag())) {
				count++;
			}
		}
		
		return count;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute(ExtractData<MtTaskExtract> data) {
		long start = System.currentTimeMillis();
		this.start(data.getOrganId(), data.getEndTime());
		int count = getCountDownLatch(this.getHandlerInfos());
		final CountDownLatch countDown = new CountDownLatch(count);
		ExecutorService organPool = initExecutor(count);
		
		List<MtTaskExtract> taskList = data.getDataList().get(ReportUtils.TASK_LIST);
		try {
			for (ExecuteHandler exh : this.getHandlerInfos()) {
				LOGGER.info("=====>执行处理器,执行器名称:{},执行方式:{},执行顺序:{}", exh.getHandlerName(), HandlerOpEnums.getNameByValue(exh.getSyncFlag()), exh.getExeOrder());
				
				if (HandlerOpEnums.ASYNC.getValue().equals(exh.getSyncFlag())) {
					organPool.execute(new Runnable() {
						@Override
						public void run() {
							invoke(exh.getHandlerName(), data, countDown);
						}
					});
				} else {
					invoke(exh.getHandlerName(), data, null);
				}
			}
			
			if (count > 0) {
				//等待超时
				countDown.await(WAIT_MIN, TimeUnit.SECONDS);
				organPool.shutdown();
			}
		} catch (Exception e) {
			LOGGER.error("report execute error.", e);
			e.printStackTrace();
		}
		LOGGER.info("All is finished.");
		//写报表库
		String errorMsg = this.writer();
		//排程结束
		this.end(data, taskList.size(), System.currentTimeMillis() - start, errorMsg);
	}
	
	/**
	 * 排程开始
	 * @param organId organId
	 * @param endDate endDate
	 */
	private void start(Integer organId, Date endDate) {
		reportScheduleService.updateScheduleByOrganId(organId, ScheduleStatusEnums.RUNNING, endDate);
	}
	
	/**
	 * 排程完成
	 * @param organId organId
	 * @param start start
	 * @param end end
	 * @param excTime excTime
	 * @param lastExcTime lastExcTime
	 * @param excEndTime excEndTime
	 * @param count count
	 * @param exeTime exeTime
	 * @param errorMsg errorMsg
	 */
	private void end(ExtractData<MtTaskExtract> data, int count, long exeTime, String errorMsg) {
		ScheduleStatusEnums status = StringUtils.isNotEmpty(errorMsg) ? ScheduleStatusEnums.ERROR : ScheduleStatusEnums.COMPLETE;
		
		Integer organId = data.getOrganId();
		Date end = data.getEndTime();
		ScheduleLog log = new ScheduleLog();
		log.setOrganId(organId);
		log.setTaskCount(count);
		log.setStartDate(data.getStartTime());
		log.setEndDate(end);
		log.setExcTime(data.getExcTime());
		log.setExcEndDate(data.getExcEndTime());
		log.setLastExcDate(data.getLastExcTime());
		log.setRunningStatus(String.valueOf(status.getValue()));
		log.setExeTime(Integer.valueOf(((int) exeTime / MILLISECOND)));
		log.setCreateDate(new Date());
		log.setErrorMessage(StringUtils.isNotEmpty(errorMsg) ? errorMsg : "completed");
		
		reportScheduleService.completed(organId, status, end, log);
	}
	
	/**
	 * handler初始化
	 * @param handlerName handlerName
	 * @param data data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ReportHandler<List<MtTaskExtract>> initHandler(String handlerName, ExtractData<MtTaskExtract> data) {
		ReportHandler<List<MtTaskExtract>> service = (ReportHandler<List<MtTaskExtract>>) SpringContextUtils.getBean(handlerName);
		service.setCommunication(communicationMap.get(handlerName));
		service.setStatisticsMap(countMap);
		service.setOrganId(data.getOrganId());
		service.setGroupOrganId(data.getGroupOrganId());
		service.setStartTime(data.getStartTime());
		service.setEndTime(data.getEndTime());
		service.setExcTime(data.getExcTime());
		service.setLastExcTime(data.getLastExcTime());
		service.setExcEndTime(data.getExcEndTime());
		return service;
	}
	
	/**
	 * 写数据
	 */
	private String writer() {
		boolean errorFlag = false;
		StringBuffer buf = new StringBuffer();
		try {
			for (Map.Entry<String, HandlerCommunication> entry : communicationMap.entrySet()) {
				HandlerCommunication communication = entry.getValue();
				if (HandlerStateEnums.ERROR.equals(communication.getState())) {
					LOGGER.info("{} handler error.{}", communication.getName(), communication.getThrowable().getMessage());
					errorFlag = true;
					buf.append(communication.getName() + ":" + communication.getThrowable().getMessage() + ",");
				} else {
					LOGGER.info("{} handler completed.", communication.getName());
				}
			}
			
			if (!errorFlag) {
//				if (LOGGER.isDebugEnabled()) {
//					for (Map.Entry<String, Object> entry : countMap.entrySet()) {
//						LOGGER.debug("key : {}, value : {}", entry.getKey(), entry.getValue());
//					}
//				}

				WirterRunner<?> wirter = SpringContextUtils.getBean(WirterRunner.class);
				wirter.setCountMap(countMap);
				wirter.run();
			}
		} catch (Exception e) {
			LOGGER.error("wirter data error", e);
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));
			buf.append(sw);
		}
		return StringUtils.substring(buf.toString(), 0, CUTLEN);
	}
	/**
	 * 初始化线程池
	 * @param count
	 * @return
	 */
	private ExecutorService initExecutor(int count) {
		ExecutorService organPool = null;
		if (count > 0) {
			organPool = Executors.newFixedThreadPool(count, new HandlerThreadFactory());
		}
		
		return organPool;
	}
	/**
	 * hander执行方法
	 * @param handlerName
	 * @param data
	 * @param countDown
	 */
	private void invoke(String handlerName, ExtractData<MtTaskExtract> data, final CountDownLatch countDown) {
		try {
			ReportHandler<List<MtTaskExtract>> service = this.initHandler(handlerName, data);
			LOGGER.info("==============>获取handler处理器:{}", service.getClass().getName());
			LOGGER.info("==============>开始执行handler处理器................");	
			List<MtTaskExtract> taskList = data.getDataList().get(service.getDataKey());
			service.invoke(taskList);
			LOGGER.info("==============>结束执行handler处理器................");	
			if (countDown != null) {
				countDown.countDown();
			}
		} catch (Exception e) {
			LOGGER.error(handlerName + " error.", e);
			communicationMap.get(handlerName).setState(HandlerStateEnums.ERROR);
			communicationMap.get(handlerName).setThrowable(e);
			if (countDown != null) {
				countDown.countDown();
			}
		}
	}

}
