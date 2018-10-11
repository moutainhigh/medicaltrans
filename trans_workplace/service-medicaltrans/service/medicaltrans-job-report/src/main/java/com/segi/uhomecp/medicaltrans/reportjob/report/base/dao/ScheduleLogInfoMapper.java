package com.segi.uhomecp.medicaltrans.reportjob.report.base.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.segi.uhomecp.medicaltrans.report.schedule.model.ScheduleLog;

public interface ScheduleLogInfoMapper {

	public void insertBatchLog(@Param("list")List<ScheduleLog> logs);
}
