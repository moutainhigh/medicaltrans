package com.segi.uhomecp.medicaltrans.report.organmonth.service;

import java.util.List;

import com.segi.uhomecp.medicaltrans.report.common.dto.CommonDto;
import com.segi.uhomecp.medicaltrans.report.organmonth.dto.AmountMonthDto;
import com.segi.uhomecp.medicaltrans.report.organmonth.dto.TaskDateSourceDto;
import com.segi.uhomecp.medicaltrans.report.organmonth.dto.TaskTypeDto;
import com.segi.uhomecp.medicaltrans.report.organmonth.dto.TransProfileDto;

/**
 * 查询报表接口
 * @author Administrator
 *
 */
public interface OrganMonthAmountService {
	
	/**
	 * 运送报表的运送概况
	 * @param organId
	 * @param cycleY
	 * @return
	 */
	public TransProfileDto getTransProfile(String organId, String cycleY);
	
	/**
	 * 报表每月运输趋势分析
	 */
	public List<AmountMonthDto> queryAmountMonth(String organId, String cycleY);
	
	/**
	 * 及时率/满意率趋势分析
	 * @param organId
	 * @param cycleY
	 * @return
	 */
	public List<AmountMonthDto> queryTimelyAmountMonth(String organId, String cycleY);
	
	/**
	 * 运送员每月平均运送量趋势分析
	 * @param organId
	 * @param cycleY
	 * @return
	 */
	public List<AmountMonthDto> queryUserAmountMonth(String organId, String cycleY);
	
	/**
	 * 即时任务响应时间趋势分析
	 * @param organId
	 * @param cycleY
	 * @return
	 */
	public List<AmountMonthDto> queryHisRespTime(String organId, String cycleY);
	
	/**
	 * @discription 运送报表的任务类型占比
	 * @author yangyh@segimail.com       
	 * @created 2018年8月2日 上午11:16:51     
	 * @param commonDto
	 * @return
	 */
	public TaskTypeDto queryTaskTypeRatio(CommonDto commonDto);

	/**
	 * @discription 运送报表的任务发起来源占比
	 * @author yangyh@segimail.com       
	 * @created 2018年8月2日 上午11:16:51     
	 * @param commonDto
	 * @return
	 */
	public TaskDateSourceDto queryTaskDateSourceRatio(CommonDto commonDto);
}
