package com.segi.uhomecp.medicaltrans.reportjob.monthjob.inf;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.report.schedule.model.ScheduleLog;
import com.segi.uhomecp.medicaltrans.report.schedule.model.TransSchedule;
import com.segi.uhomecp.medicaltrans.reportjob.enums.ScheduleStatusEnums;
import com.segi.uhomecp.medicaltrans.reportjob.monthjob.dto.MonthReportExecuteDto;
import com.segi.uhomecp.medicaltrans.reportjob.monthjob.dto.MonthReportExecuteInitialDto;
import com.segi.uhomecp.medicaltrans.reportjob.monthjob.handler.JobFullMonthHandler;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.ReportJobTimeDto;
import com.segi.uhomecp.medicaltrans.reportjob.report.base.ReportScheduleService;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.utils.SpringContextUtils;
import com.segi.uhomecp.wh.common.dto.ResultDto;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.xxl.job.core.log.XxlJobLogger;

public abstract class AbsFullMonthDefaultExecute implements JobFullMonthExecute {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbsFullMonthDefaultExecute.class);

	@Autowired
	private ReportScheduleService reportScheduleService;
	
	/**
	 * @discription 数据初始化
	 * @author wangxiong@segimail.com
	 * @created 2018年8月15日 上午9:40:40
	 * @param groupOrganId
	 * @param processOrganIds
	 * @param repairDates 执行月份
	 * @return
	 */
	public abstract MonthReportExecuteInitialDto init(Integer groupOrganId, List<Integer> processOrganIds, List<String> repairDates);
	
	/**
	 * @discription 执行配置程序
	 * @author wangxiong@segimail.com
	 * @created 2018年8月14日 下午11:55:44
	 * @param organIds
	 * @param executeList
	 */
	public void executionProcessing(List<Integer> organIds, MonthReportExecuteInitialDto initiaDto) {
		ResultDto<String, String, Integer> rst = null;
		Set<Integer> errorSet = new HashSet<>();
		long beginTime = System.currentTimeMillis(); 
		List<MonthReportExecuteDto> executeList = initiaDto.getExecuteList();
		ReportJobTimeDto dto = null;
		Date nowDate = new Date();
		Map<Integer ,String> errMap = new HashMap<>();
		reportScheduleService.updateScheduleByOrganIds(organIds, ScheduleStatusEnums.RUNNING, null, null, null);
		for (MonthReportExecuteDto executeDto : executeList) {
			if (executeDto == null) {
				continue;
			}
			XxlJobLogger.log("executionProcessing========================》executeDto:{}" + executeDto.toString());
			try {
				dto = executeDto.getParams();
				dto.setExcDate(nowDate);
				JobFullMonthHandler exec = (JobFullMonthHandler) SpringContextUtils.getBean(executeDto.getExecuteObj());
				rst = exec.invoke(executeDto.getParams());
				if (rst.getIsSucc()) {
					LOGGER.debug("=====>executeDto{}====》返回数据{}", FastjsonUtils.toJsonString(executeDto), rst.getMessage());
				}
				if (AppUtils.isNotEmpty(rst.getList())) {
					errorSet.addAll(rst.getList());
					updateOrganErrMsg(errMap, rst.getList(), rst.getMessage());
				}
				XxlJobLogger.log("executionProcessing========================》执行结束executeDto{}" + executeDto.toString());
			} catch (Exception e) {
				XxlJobLogger.log("executionProcessing========================》executeDto{}出错" + executeDto.toString());
				XxlJobLogger.log(e);
				LOGGER.error("executionProcessing========================》executeDto{}出错" + executeDto.toString(), e);
				// 如果有捕获异常的话就全部错误
				errorSet.addAll(executeDto.getParams().getOrganIdList());
				updateOrganErrMsg(errMap, executeDto.getParams().getOrganIdList(), e.getMessage());
			}
		}
		List<Integer> errorList = new ArrayList<>(errorSet);
		// 记录日志
		recordExcLog(initiaDto, errMap, organIds, errorList, nowDate);
		// 记录日志
		XxlJobLogger.log(new StringBuffer("executionProcessing==============>耗时{}")
				.append(System.currentTimeMillis() - beginTime).append("]毫秒").append(",,执行网点如下：").append(organIds)
				.append("执行错误网点：").append(errorSet).toString());
		// 更新排程表数据
		updateSchedule(initiaDto, organIds, errorList, nowDate);
	}
	
	/**
	 * @discription 记录执行日志
	 * @author wangxiong@segimail.com       
	 * @created 2018年9月29日 下午2:38:18     
	 * @param initiaDto
	 * @param errMap
	 * @param organIds
	 * @param errorList
	 * @param excDate
	 */
	private void recordExcLog(MonthReportExecuteInitialDto initiaDto, Map<Integer, String> errMap,
			List<Integer> organIds, List<Integer> errorList, Date excDate) {
		try {
			Date now = new Date();
			@SuppressWarnings("unchecked")
			List<Integer> successList = ListUtils.subtract(organIds, errorList);
			List<ScheduleLog> logList = new ArrayList<>();
			ScheduleLog log = null;
			boolean updateFlag = initiaDto.isUpdateFlag();       // 是否更新当前月的
			boolean lastUpdateFlag = initiaDto.isLastUpdateFlag();// 是否更新上个月的
			// 记录成功日志
			for (Integer organId : successList) {
				log = new ScheduleLog();
				log.setCreateDate(now);
				log.setEndDate(now);
				log.setOrganId(organId);
				log.setStartDate(excDate);
				log.setRunningStatus(String.valueOf(ScheduleStatusEnums.COMPLETE.getValue()));
				log.setLastExcDate(lastUpdateFlag ? excDate : null);
				log.setExcEndDate(updateFlag ? initiaDto.getExcEndDate() : null);
				log.setExcTime(updateFlag ? excDate: null);
				logList.add(log);
			}
			// 记录错误日志
			for (Map.Entry<Integer, String> entry : errMap.entrySet()) {
				log = new ScheduleLog();
				log.setCreateDate(now);
				log.setEndDate(now);
				log.setErrorMessage(entry.getValue());
				log.setOrganId(entry.getKey());
				log.setStartDate(excDate);
				log.setRunningStatus(String.valueOf(ScheduleStatusEnums.ERROR.getValue()));
				logList.add(log);
			}
			reportScheduleService.insertBatchLog(logList);
		} catch (Exception e) {
			LOGGER.error("recordExcLog========================》出错" , e);
		}
		
	}

	/**
	 * @discription 更新Organs 错误信息
	 * @author wangxiong@segimail.com       
	 * @created 2018年9月13日 下午6:35:38     
	 * @param errMap
	 * @param list
	 * @param message
	 */
	private void updateOrganErrMsg(Map<Integer, String> errMap, List<Integer> list, String msg) {
		String errMsg = null;
		for (Integer organId : list) {
			errMsg = errMap.get(organId);
//			if(StringUtils.isNotBlank(errMsg)) {
//				errMap.put(organId, msg);
//				continue;
//			}
			errMap.put(organId, errMsg + "{}" + msg);
		}
	}

	/**
	 * @discription 更新排程表信息
	 * @author wangxiong@segimail.com
	 * @created 2018年8月15日 上午9:46:15
	 * @param organIds
	 * @param errorList
	 */
	private void updateSchedule(MonthReportExecuteInitialDto initiaDto,
			List<Integer> organIds, List<Integer> errorSet, Date excDate) {
		List<Integer> errorList = new ArrayList<>(errorSet);
		@SuppressWarnings("unchecked")
		List<Integer> successList = ListUtils.subtract(organIds, errorList);
		boolean updateFlag = initiaDto.isUpdateFlag();       // 是否更新当前月的
		boolean lastUpdateFlag = initiaDto.isLastUpdateFlag();// 是否更新上个月的
		if (updateFlag || lastUpdateFlag) {// 这里需要改造成 判断是否有当前月截止时间和 上个月截止时间
			// 成功更新
			if (AppUtils.isNotEmpty(successList)) {
				XxlJobLogger.log("updateSchedule========================》successList{}"+FastjsonUtils.toJsonString(successList));
				reportScheduleService.updateScheduleByOrganIds(successList, ScheduleStatusEnums.COMPLETE,
						updateFlag ? excDate: null,
						lastUpdateFlag ? excDate : null,
						updateFlag ? initiaDto.getExcEndDate() : null);
				XxlJobLogger.log("updateSchedule========================》successList");
			}
			// 失败更新
			if (AppUtils.isNotEmpty(errorList)) {
				XxlJobLogger.log("updateSchedule========================》errorList{}"+FastjsonUtils.toJsonString(errorList));
				reportScheduleService.updateScheduleByOrganIds(errorList, ScheduleStatusEnums.ERROR, null, null, null);
				XxlJobLogger.log("updateSchedule========================》errorList");
			}
		}
	}

	@Override
	public void execute(List<Integer> arg0) {
		LOGGER.info("增量获取数据开始调度...");
		Map<Integer, Set<Integer>> groupOrganMap = getUpdateOrganIdList(arg0);
		LOGGER.info("获取项目清单：{}", groupOrganMap);
		Integer groupOrganId = null;
		List<Integer> organIdList = null;
		Paginator page = null;
		List<Integer> processOrganIds = null;
		for (Map.Entry<Integer, Set<Integer>> entry : groupOrganMap.entrySet()) {
			groupOrganId = entry.getKey();
			organIdList = new ArrayList<>(entry.getValue());
			// 判断是否有必要分批
			if (MtConstant.MONTH_UPDATE_LIMIT < organIdList.size()) {
				page = new Paginator(1, MtConstant.MONTH_UPDATE_LIMIT, organIdList.size());
				for (int i = 0; i < page.getTotalPages(); i++) {
					page = new Paginator(i + 1, MtConstant.MONTH_UPDATE_LIMIT, organIdList.size());
					processOrganIds = organIdList.subList(page.getStartRow() - 1, page.getEndRow());
					MonthReportExecuteInitialDto initiaDto = init(groupOrganId, processOrganIds, null);
					executionProcessing(processOrganIds, initiaDto);
				}
			} else {
				MonthReportExecuteInitialDto initiaDto = init(groupOrganId, new ArrayList<>(entry.getValue()), null);
				executionProcessing(organIdList, initiaDto);
			}
		}
	}

	@Override
	public void execute(Integer organId, List<String> months) {
		LOGGER.info("增量获取数据开始调度...");
		List<Integer> organIds = new ArrayList<Integer>();
		organIds.add(organId);
		Map<Integer, Set<Integer>> groupOrganMap = getUpdateOrganIdList(organIds);
		LOGGER.info("获取项目清单：{}", groupOrganMap);
		if (!AppUtils.isNotEmpty(groupOrganMap))	{
			// 该数据在排程表无数据，直接返回
			return ;
		}
		Iterator<Integer> groupIte = groupOrganMap.keySet().iterator();
		if (groupIte.hasNext()) {
			// 该数据在排程表有数据，直接返回
			Integer groupOrganId = groupIte.next();
			MonthReportExecuteInitialDto initiaDto = init(groupOrganId, organIds, months);
			executionProcessing(organIds, initiaDto);
		}
	}
	
	/**
	 * @discription 获取排程表的更新的项目集合
	 * @author wangxiong@segimail.com
	 * @created 2018年8月2日 下午8:24:23 organId 组织机构Id
	 * @return
	 */
	public Map<Integer, Set<Integer>> getUpdateOrganIdList(List<Integer> organIds) {
		// 查询可以更新的表的数据
		LOGGER.info("===========>MtPerVolMonthReportHandle: arg0:{}", organIds);
		XxlJobLogger.log("===========>MtPerVolMonthReportHandle: arg0:" + organIds);
		List<TransSchedule> transList = reportScheduleService.getAllTransSchedule(organIds);
		Map<Integer, Set<Integer>> groupOrganMap = getGroupOrganMapBytransList(transList);
		LOGGER.debug("===========>获取使用医疗运输的项目ID: groupOrganMap:{}", groupOrganMap);
		XxlJobLogger
				.log("===========>成功获取使用医疗运输的项目ID: groupOrganMap:{" + FastjsonUtils.toJsonString(groupOrganMap) + "}");
		return groupOrganMap;
	}

	/**
	 * @discription 根据排程表获取 以groupOrganId为key，下属的organIds（List）
	 * @author wangxiong@segimail.com
	 * @created 2018年8月3日 上午11:06:11
	 * @param transList
	 * @return
	 */
	private Map<Integer, Set<Integer>> getGroupOrganMapBytransList(List<TransSchedule> transList) {
		Map<Integer, Set<Integer>> rstMap = new HashMap<>();
		if (AppUtils.isNotEmpty(transList)) {
			Integer groupOrganId = null;
			Set<Integer> organIds = null;
			for (TransSchedule transSchedule : transList) {
				groupOrganId = transSchedule.getGroupOrganId();
				organIds = rstMap.get(groupOrganId);
				if (organIds == null) {
					// 数据返回为 null 说明里面没有数据
					organIds = new HashSet<>();
					organIds.add(transSchedule.getOrganId());
					rstMap.put(groupOrganId, organIds);
				} else {
					organIds.add(transSchedule.getOrganId());
				}
			}
		}
		return rstMap;
	}

}
