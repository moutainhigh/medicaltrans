package com.segi.uhomecp.medicaltrans.report.monthamount.transtype.service;

import com.segi.uhomecp.medicaltrans.report.common.dto.CommonDto;
import com.segi.uhomecp.medicaltrans.report.monthamount.transtype.dto.TransTypeDto;

/**
 * Title: TranstypeStatisticsInfoService.java    
 * @Description: 报表运送类型业务Service
 * @author yangyh@segimail.com       
 * @created 2018年8月14日 下午3:29:08
 */
public interface TranstypeStatisticsInfoService {
	
	/**
	 * @discription 运送类型占比
	 * @author yangyh@segimail.com       
	 * @created 2018年8月14日 下午3:29:45     
	 * @param commonDto
	 * @return transTypeDto
	 */
	public TransTypeDto queryTaskTypeRatio(CommonDto commonDto);
}
