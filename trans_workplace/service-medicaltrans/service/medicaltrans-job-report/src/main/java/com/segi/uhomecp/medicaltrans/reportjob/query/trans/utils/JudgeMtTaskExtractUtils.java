package com.segi.uhomecp.medicaltrans.reportjob.query.trans.utils;

import java.util.Date;

import com.segi.uhomecp.medicaltrans.enums.TransStatusEnum;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.AddJudgeParamsDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.model.MtTaskExtract;
import com.segi.uhomecp.wh.common.utils.DataTypeConverUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;
import com.xxl.job.core.log.XxlJobLogger;

public class JudgeMtTaskExtractUtils {
	
	/**
	 * 过滤增量job的list，ture为需要的，false不需要
	 * 只取完成状态，当状态为已完成，已评价，实际完成时间小于上次执行时间，过滤掉
	 */
	public static boolean judgeMtTaskExtract(MtTaskExtract mtTaskExtract, AddJudgeParamsDto addJudgeParamsDto) {
		if(null == mtTaskExtract || null == mtTaskExtract.getStatus()||null == mtTaskExtract.getUpdateDate()||null == mtTaskExtract.getBeginTime() || null == addJudgeParamsDto || null == addJudgeParamsDto.getParamDate()){
			return false;
		}
		// 只取完成状态
		if (!TransStatusEnum.TRANS_COMPLETED.getCode().equals(mtTaskExtract.getStatus())) {
			return false;
		}
		// 当状态为已完成，已评价，实际完成时间小于上次执行时间，过滤掉
		if (mtTaskExtract.getEvaluate() != null && mtTaskExtract.getEvaluate() != 0
			&& mtTaskExtract.getExeEndTime()!=null	&& mtTaskExtract.getExeEndTime().compareTo(addJudgeParamsDto.getParamDate()) < 0) {
			XxlJobLogger.log("==============>当数据状态为已完成，已评价，实际完成时间小于上次执行时间，过滤掉,id:{" + mtTaskExtract.getTaskId() + "}");
			return false;
		}
		//获取当月的00：00分
		Long monthFirstTime = Long.valueOf(DateUtil.getFirstTimeOfNewMonth());
		//如果该工单属于上个月,实际完成时间要小于（等于）上次全量执行时间，过滤掉
		if(mtTaskExtract.getBeginTime().compareTo(monthFirstTime) < 0 && mtTaskExtract.getExeEndTime()!=null
				&& addJudgeParamsDto.getLastExcTime()!=null && mtTaskExtract.getExeEndTime().compareTo(addJudgeParamsDto.getLastExcTime()) <= 0){
			XxlJobLogger.log("==============>如果该工单属于上个月,实际完成时间要小于（等于）上次全量执行时间，过滤掉,id:{" + mtTaskExtract.getTaskId() + "}");
			return false;
		}
		//如果该单属于本月，更新时间小于（等于）当月执全量行时间,且开始时间小于（等于）当月截止时间，过滤掉
		if(mtTaskExtract.getBeginTime().compareTo(monthFirstTime) > 0 &&  
				mtTaskExtract.getUpdateDate().compareTo(addJudgeParamsDto.getExcTime()) <= 0
				&& mtTaskExtract.getBeginTime().compareTo(DataTypeConverUtils.formatDateToLongYYYYMMDDHHMM(addJudgeParamsDto.getExcEndTime())) <= 0){
			XxlJobLogger.log("==============>如果该单属于本月，更新时间小于（等于）当月执全量行时间,且开始时间小于（等于）当月截止时间，过滤掉,id:{" + mtTaskExtract.getTaskId() + "}");
			return false;
		}
		return true;
	}
}
