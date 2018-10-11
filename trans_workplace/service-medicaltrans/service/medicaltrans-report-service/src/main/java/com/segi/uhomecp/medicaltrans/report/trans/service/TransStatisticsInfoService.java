package com.segi.uhomecp.medicaltrans.report.trans.service;

import java.util.List;

import com.segi.uhomecp.medicaltrans.report.trans.dto.TransStatisticsDto;

/**
 * Title: TransStatisticsInfoService.java    
 * @Description: 宽表业务方法
 * @author yangyh@segimail.com       
 * @created 2018年9月18日 上午11:51:59
 */
public interface TransStatisticsInfoService {

	/**
	 * @discription 运送时间统计
	 * @author yangyh@segimail.com       
	 * @created 2018年9月18日 下午3:21:01     
	 * @param organId
	 * @return
	 */
	public List<TransStatisticsDto> queryTransportTime(Integer organId);

	/**
	 * @discription 任务结构占比
	 * @author yangyh@segimail.com       
	 * @created 2018年9月18日 下午3:21:01     
	 * @param organId
	 * @return
	 */
	public List<TransStatisticsDto> queryTaskStructureRatio(Integer organId);

	/**
	 * @discription 运送方式占比
	 * @author yangyh@segimail.com       
	 * @created 2018年9月18日 下午3:21:01     
	 * @param organId
	 * @return
	 */
	public List<TransStatisticsDto> queryTransModeRatio(Integer organId);
}
