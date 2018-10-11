package com.segi.uhomecp.medicaltrans.report.trans.dao;

import com.segi.uhomecp.medicaltrans.dto.TransStatisticsCommonDto;
import com.segi.uhomecp.medicaltrans.report.common.dto.CommonDto;

/**
 * Title: TransStatisticsMapper.java    
 * @Description: 大屏运送时间统计和饼状图查询SQL
 * @author yangyh@segimail.com       
 * @created 2018年9月26日 下午4:43:17
 */
public interface TransStatisticsMapper {
	/**
	 * @discription 运送时间统计
	 * @author yangyh@segimail.com       
	 * @created 2018年9月26日 下午4:52:15     
	 * @param commonDto
	 * @return
	 */
	TransStatisticsCommonDto queryTransportTime(CommonDto commonDto);
	
	/**
	 * @discription 任务结构占比
	 * @author yangyh@segimail.com       
	 * @created 2018年9月26日 下午4:52:37     
	 * @param commonDto
	 * @return
	 */
	TransStatisticsCommonDto queryTaskStructureRatio(CommonDto commonDto);

	/**
	 * @discription 出发地占比
	 * @author yangyh@segimail.com       
	 * @created 2018年9月26日 下午4:52:43     
	 * @param commonDto
	 * @return
	 */
	TransStatisticsCommonDto queryTransModeRatio(CommonDto commonDto);
}
