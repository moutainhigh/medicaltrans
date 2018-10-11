package com.segi.uhomecp.medicaltrans.reportjob.handler.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.report.monthamount.transway.model.TranswayStatistics;
import com.segi.uhomecp.medicaltrans.reportjob.handler.AbstractHandler;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.AddJudgeParamsDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.model.MtTaskExtract;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.utils.JudgeMtTaskExtractUtils;
import com.segi.uhomecp.medicaltrans.reportjob.util.ReportUtils;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;
import com.xxl.job.core.log.XxlJobLogger;

@Component
public class TranswayHandler extends AbstractHandler<List<MtTaskExtract>> {
	
	private static final Logger LOG = LoggerFactory.getLogger(TranswayHandler.class);
	
	@Override
	public void doInvoke(List<MtTaskExtract> list) {
		if (!AppUtils.isNotEmpty(list)) {
			return;
		}
		XxlJobLogger.log("==============>开始增量统计运送方式运输总量表信息...");
		Map<String, TranswayStatistics> transwayMap = getGroupList(list);
		this.getStatisticsMap().putIfAbsent(this.getHandlerName(),
				new ArrayList<TranswayStatistics>(transwayMap.values()));
		XxlJobLogger.log("==============>结束增量统计运送方式运输总量表信息");
	}

	/**
	 * @discription 循环遍历将业务数据分组成运送方式list
	 * @author yangyh@segimail.com       
	 * @created 2018年9月13日 下午2:49:46     
	 * @param list
	 * @return
	 */
	private Map<String, TranswayStatistics> getGroupList(List<MtTaskExtract> list) {
		Map<String, TranswayStatistics> transwayMap = new HashMap<String, TranswayStatistics>();
		AddJudgeParamsDto addJudgeParamsDto = new AddJudgeParamsDto();
		//取数开始时间
		Date paramDate = this.getStartTime();
		addJudgeParamsDto.setParamDate(paramDate);
		//本月执行时间
		Date excTime = this.getExcTime();
		addJudgeParamsDto.setExcTime(excTime);
		//本月截止时间
		Date excEndTime = this.getExcEndTime();
		addJudgeParamsDto.setExcEndTime(excEndTime);
		//上月执行时间
		Date lastExcTime = this.getLastExcTime();
		addJudgeParamsDto.setLastExcTime(lastExcTime);
		// 循环遍历
		for (MtTaskExtract mtTask : list) {
			// 1、只取完成状态，2、当状态为已完成，已评价，实际完成时间小于上次执行时间，过滤掉
			if (!JudgeMtTaskExtractUtils.judgeMtTaskExtract(mtTask, addJudgeParamsDto)) {
				continue;
			}
			// 主键
			Integer taskId = mtTask.getTaskId();
			// 开始时间
			String beginTime = String.valueOf(mtTask.getBeginTime());
			String yearMonthOfDate = DateUtil.getYearMonthOfDate(beginTime);
			if (null == yearMonthOfDate) {
				XxlJobLogger.log("==============>开始时间格式错误！id:{" + taskId + "},beginTime:{" + beginTime + "}");
				LOG.error("开始时间格式错误！id:{},beginTime:{}", taskId, beginTime);
				continue;
			}
			// 年月
			Integer cycleYm = Integer.valueOf(yearMonthOfDate);
			// 运送工具
			String code = mtTask.getTransTools();
			if (StringUtils.isBlank(code)) {
				XxlJobLogger.log("==============>运送工具为空！id:{" + taskId + "}");
				LOG.error("运送工具为空！id:{}", taskId);
				continue;
			}
			// 将运送方式对象装到map里
			getTransway(cycleYm, code, transwayMap, mtTask);
		}
		return transwayMap;
	}
	
	/**
	 * @discription 根据cycleYm(年月)+code(运送方式)将对应的业务数据转成报表对象装好组Map<cycleYm(年月)+code(运送方式),TranswayStatistics>
	 * @author yangyh@segimail.com       
	 * @created 2018年9月13日 下午2:49:46     
	 * @param cycleYm
	 * @param code
	 * @param transwayMap
	 * @param mtTask
	 */
	private void getTransway(Integer cycleYm, String code,
			Map<String, TranswayStatistics> transwayMap, MtTaskExtract mtTask) {
		// key:cycleYm(年月)+code(运送方式)
		String key = cycleYm + code;
		TranswayStatistics transway = transwayMap.get(key);
		if (null == transway) {
			// 创建一个全新的运送方式对象
			transway = new TranswayStatistics();
			transway.setGroupOrganId(this.getGroupOrganId());
			transway.setOrganId(this.getOrganId());
			transway.setCycleYm(cycleYm);
			transway.setTransWayCode(code);
			transway.setTransAmount(0L);
			transway.setTransMinite(0L);
			transwayMap.put(key, transway);
		}
		Integer timeConsuming = null == mtTask.getTimeConsuming() ? 0 : mtTask.getTimeConsuming();
		transway.setTransAmount(transway.getTransAmount() + 1);
		transway.setTransMinite(transway.getTransMinite() + timeConsuming);
	}
	
	@Override
	public String getHandlerName() {
		return MtConstant.TRANS_TYPE_HANDLER;
	}
	
	@Override
	public String getDataKey() {
		return ReportUtils.TASK_LIST;
	}

}
