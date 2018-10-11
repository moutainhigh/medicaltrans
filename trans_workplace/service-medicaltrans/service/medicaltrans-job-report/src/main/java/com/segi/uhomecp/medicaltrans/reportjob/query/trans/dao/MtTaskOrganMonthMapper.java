package com.segi.uhomecp.medicaltrans.reportjob.query.trans.dao;

import java.util.List;


import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.MonthDayDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.OrganMonthEntryParamDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.OrganMonthStatisticsDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.OrganTimeStatisticsDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.model.MtTaskExtract;

/**
 * 取项目月运送量mapper
 * @author Administrator
 *
 */
public interface MtTaskOrganMonthMapper {
	
	/**
	 * 根据项目和开始结束时间查询项目运输表数据  
	 * @param organMonthEntryParamDto
	 * @return
	 */
	public List<OrganMonthStatisticsDto> getOrganMonthList(OrganMonthEntryParamDto organMonthEntryParamDto);
	
	/**
	 * 计算每天是否有运单
	 * @param monthDayDto
	 * @return
	 */
	public List<MtTaskExtract> getIsTaskDay(MonthDayDto monthDayDto);
	
	/**
	 *  查出各个时段运送量总数据
	 */
	public List<OrganTimeStatisticsDto> getOrganTimeList(OrganMonthEntryParamDto organMonthEntryParamDto);
	
}
