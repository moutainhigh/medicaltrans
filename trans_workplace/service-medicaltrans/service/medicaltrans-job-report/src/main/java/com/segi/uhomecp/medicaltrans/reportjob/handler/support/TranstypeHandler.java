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
import com.segi.uhomecp.medicaltrans.report.monthamount.transtype.model.TranstypeStatistics;
import com.segi.uhomecp.medicaltrans.reportjob.handler.AbstractHandler;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.AddJudgeParamsDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.model.MtTaskExtract;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.utils.JudgeMtTaskExtractUtils;
import com.segi.uhomecp.medicaltrans.reportjob.util.ReportUtils;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;
import com.xxl.job.core.log.XxlJobLogger;

@Component
public class TranstypeHandler extends AbstractHandler<List<MtTaskExtract>> {
	
	private static final Logger LOG = LoggerFactory.getLogger(TranstypeHandler.class);
	
	@Override
	public void doInvoke(List<MtTaskExtract> list) {
		if (!AppUtils.isNotEmpty(list)) {
			return;
		}
		XxlJobLogger.log("==============>开始增量统计运送类型运输总量表信息...");
		Map<String, TranstypeStatistics> transtypeMap = getGroupList(list);
		this.getStatisticsMap().putIfAbsent(this.getHandlerName(),
				new ArrayList<TranstypeStatistics>(transtypeMap.values()));
		XxlJobLogger.log("==============>结束增量统计运送类型运输总量表信息");
	}

	/**
	 * @discription 循环遍历将业务数据分组成运送类型list
	 * @author yangyh@segimail.com       
	 * @created 2018年8月14日 上午11:55:07     
	 * @param list
	 * @return
	 */
	private Map<String, TranstypeStatistics> getGroupList(List<MtTaskExtract> list) {
		Map<String, TranstypeStatistics> transtypeMap = new HashMap<String, TranstypeStatistics>();
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
			// 运送大类
			String code = mtTask.getTransTypeParentCode();
			if (StringUtils.isBlank(code)) {
				XxlJobLogger.log("==============>运送大类为空！id:{" + taskId + "}");
				LOG.error("运送大类为空！id:{}", taskId);
				continue;
			}
			// 将运送类型对象装到map里
			getTranstype(cycleYm, code, transtypeMap, mtTask);
		}
		return transtypeMap;
	}
	
	/**
	 * @discription 根据cycleYm(年月)+code(运送类型)将对应的业务数据转成报表对象装好组Map<cycleYm(年月)+code(运送类型)
	 * @author yangyh@segimail.com       
	 * @created 2018年8月13日 下午12:10:58     
	 * @param cycleYm
	 * @param code
	 * @param transtypeMap
	 * @param mtTask
	 */
	private void getTranstype(Integer cycleYm, String code,
			Map<String, TranstypeStatistics> transtypeMap, MtTaskExtract mtTask) {
		// key:cycleYm(年月)+code(运送类型)
		String key = cycleYm + code;
		TranstypeStatistics transtype = transtypeMap.get(key);
		if (null == transtype) {
			// 创建一个全新的运送类型对象
			transtype = new TranstypeStatistics();
			transtype.setGroupOrganId(this.getGroupOrganId());
			transtype.setOrganId(this.getOrganId());
			transtype.setCycleYm(cycleYm);
			transtype.setTransTypeParentCode(code);
			transtype.setTransAmount(0L);
			transtype.setTransMinite(0L);
			transtypeMap.put(key, transtype);
		}
		Integer timeConsuming = null == mtTask.getTimeConsuming() ? 0 : mtTask.getTimeConsuming();
		transtype.setTransAmount(transtype.getTransAmount() + 1);
		transtype.setTransMinite(transtype.getTransMinite() + timeConsuming);
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
