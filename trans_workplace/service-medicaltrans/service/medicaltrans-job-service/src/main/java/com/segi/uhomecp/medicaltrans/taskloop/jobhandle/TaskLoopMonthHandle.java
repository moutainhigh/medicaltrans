package com.segi.uhomecp.medicaltrans.taskloop.jobhandle;


import java.util.List;

import org.springframework.stereotype.Component;

import segi.medicaltrans.common.taskloop.MtTaskLoopIce;
import segi.medicaltrans.common.taskloop.MtTaskLoopIceListReturn;

import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.xxl.job.core.handler.annotation.JobHandler;
/**
 * 每天23：00执行扫描月日类型的循环任务并触发生成
 * @author Administrator
 *
 */
@JobHandler(value = "TaskLoopMonthJob")
@Component
public class TaskLoopMonthHandle extends TaskLoopHandle {
	
	@Override
	public List<MtTaskLoopIce> getMtTaskLoopList(Integer organId) {
		// 提供一个查询接口
		MtTaskLoopIceListReturn mtTaskLoopListByMonth = MtCommonServiceUtils.getMtTaskLoopListByMonth(organId);
		return mtTaskLoopListByMonth.resultList;
	}
}
