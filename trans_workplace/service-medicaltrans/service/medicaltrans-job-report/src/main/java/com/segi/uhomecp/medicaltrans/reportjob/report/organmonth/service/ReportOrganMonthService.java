package com.segi.uhomecp.medicaltrans.reportjob.report.organmonth.service;

import java.util.List;
import java.util.Map;

import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.OrganMonthStatisticsDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.ReportJobTimeDto;
import com.segi.uhomecp.medicaltrans.reportjob.report.organmonth.dto.AmountMonthUserDto;
/**
 * 报表排程Service
 *     
 * 包: com.segi.uhomecp.medicaltrans.reportjob.base.service 
 * 类名称: ReportScheduleService.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 下午5:46:26
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
public interface ReportOrganMonthService {
	
	/**
	 * 先删除需要更新的项目
	 * @param reportJobTimeDto
	 */
	public void deleteOrganMonthList(ReportJobTimeDto reportJobTimeDto);
	
	/**
	 * 按单个对象dto（项目、月份）更新报表库
	 * @param organMonthStatisticsDto
	 */
	public void updateOrganMonthList(OrganMonthStatisticsDto organMonthStatisticsDto, Integer cycleYm);
	
	/**
	 * 提供给人员报表更新每月人数的接口
	 */
	public void updateOrganMonthUser(List<AmountMonthUserDto> amountMonthUserDtoList);
	
	/**
	 * 增量job更新报表库接口
	 */
	public void updateOrganMonthByMapAdd(Map<Integer, OrganMonthStatisticsDto> organMonthDtoMap);
	
	/**
	 * 批量更新删除老数据时，先查出原数据的运输人数字段
	 * @param reportJobTimeDto
	 * @return
	 */
	public Map<Integer, Integer> queryTrantUserNum(ReportJobTimeDto reportJobTimeDto);
}
