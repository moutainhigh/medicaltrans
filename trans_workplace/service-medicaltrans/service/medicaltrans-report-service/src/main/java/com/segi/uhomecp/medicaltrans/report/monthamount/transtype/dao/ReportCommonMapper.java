package com.segi.uhomecp.medicaltrans.report.monthamount.transtype.dao;

import java.util.List;

import com.segi.uhomecp.medicaltrans.report.common.dto.CommonDto;
import com.segi.uhomecp.medicaltrans.report.monthamount.transtype.model.TranstypeStatistics;
import com.segi.uhomecp.medicaltrans.report.organmonth.dto.TaskDateSourceDto;
import com.segi.uhomecp.medicaltrans.report.organmonth.dto.TaskTypeDto;

/**
 * Title: ReportCommonMapper.java    
 * @Description: 报表饼状图查询SQL
 * @author yangyh@segimail.com       
 * @created 2018年8月14日 上午11:37:38
 */
public interface ReportCommonMapper {
	/**
	 * @discription 运送类型
	 * @author yangyh@segimail.com       
	 * @created 2018年8月14日 上午11:58:26     
	 * @param commonDto
	 * @return list
	 */
	List<TranstypeStatistics> selectTransTypeByOrganIdAndCycleYm(CommonDto commonDto);
	
	/**
	 * @discription 任务类型
	 * @author yangyh@segimail.com       
	 * @created 2018年8月14日 上午11:58:26     
	 * @param commonDto
	 * @return list
	 */
	TaskTypeDto selectTaskTypeByOrganIdAndCycleYm(CommonDto commonDto);

	/**
	 * @discription 运输来源
	 * @author yangyh@segimail.com       
	 * @created 2018年8月14日 上午11:58:26     
	 * @param commonDto
	 * @return list
	 */
	TaskDateSourceDto selectTaskDateSourceByOrganIdAndCycleYm(CommonDto commonDto);
}
