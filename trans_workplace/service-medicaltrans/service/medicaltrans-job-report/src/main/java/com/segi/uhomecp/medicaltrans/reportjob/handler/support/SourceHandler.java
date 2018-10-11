package com.segi.uhomecp.medicaltrans.reportjob.handler.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.report.monthamount.source.model.SourceStatistics;
import com.segi.uhomecp.medicaltrans.reportjob.handler.AbstractHandler;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.AddJudgeParamsDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.model.MtTaskExtract;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.utils.JudgeMtTaskExtractUtils;
import com.segi.uhomecp.medicaltrans.reportjob.util.ReportUtils;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;
import com.xxl.job.core.log.XxlJobLogger;

@Component
public class SourceHandler extends AbstractHandler<List<MtTaskExtract>> {
	
	private static final Logger LOG = LoggerFactory.getLogger(SourceHandler.class);
	
	@Override
	public void doInvoke(List<MtTaskExtract> list) {
		if (!AppUtils.isNotEmpty(list)) {
			return;
		}
		XxlJobLogger.log("==============>开始增量统计运送来源运输总量表信息...");
		Map<String, SourceStatistics> sourceMap = getGroupList(list);
		this.getStatisticsMap().putIfAbsent(this.getHandlerName(),
				new ArrayList<SourceStatistics>(sourceMap.values()));
		XxlJobLogger.log("==============>结束增量统计运送来源运输总量表信息");
	}

	/**
	 * @discription 循环遍历将业务数据分组成运送来源list
	 * @author yangyh@segimail.com       
	 * @created 2018年9月13日 下午2:49:46     
	 * @param list
	 * @return
	 */
	private Map<String, SourceStatistics> getGroupList(List<MtTaskExtract> list) {
		Map<String, SourceStatistics> sourceMap = new HashMap<String, SourceStatistics>();
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
			// 出发地
			Integer code = mtTask.getFromHouseId();
			if (null == code) {
				XxlJobLogger.log("==============>出发地为空！id:{" + taskId + "}");
				LOG.error("出发地为空！id:{}", taskId);
				continue;
			}
			// 将运送来源对象装到map里
			getSource(cycleYm, code, sourceMap, mtTask);
		}
		return sourceMap;
	}
	
	/**
	 * @discription 根据cycleYm(年月)+code(运送来源)将对应的业务数据转成报表对象装好组Map<cycleYm(年月)+code(运送来源),SourceStatistics>
	 * @author yangyh@segimail.com       
	 * @created 2018年9月13日 下午2:49:46     
	 * @param cycleYm
	 * @param code
	 * @param SourceMap
	 * @param mtTask
	 */
	private void getSource(Integer cycleYm, Integer code,
			Map<String, SourceStatistics> sourceMap, MtTaskExtract mtTask) {
		// key:cycleYm(年月)+code(运送来源)
		String key = cycleYm.toString() + code.toString();
		SourceStatistics source = sourceMap.get(key);
		if (null == source) {
			// 创建一个全新的运送来源对象
			source = new SourceStatistics();
			source.setGroupOrganId(this.getGroupOrganId());
			source.setOrganId(this.getOrganId());
			source.setCycleYm(cycleYm);
			source.setFromHouseId(code);
			source.setTransAmount(0L);
			sourceMap.put(key, source);
		}
		source.setTransAmount(source.getTransAmount() + 1);
	}
	
	@Override
	public String getHandlerName() {
		return MtConstant.TRANS_SOURCE_HANDLER;
	}
	
	@Override
	public String getDataKey() {
		return ReportUtils.TASK_LIST;
	}

}
