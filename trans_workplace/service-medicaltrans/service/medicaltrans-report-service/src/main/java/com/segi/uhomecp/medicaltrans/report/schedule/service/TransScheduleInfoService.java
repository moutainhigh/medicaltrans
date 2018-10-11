package com.segi.uhomecp.medicaltrans.report.schedule.service;

import java.util.Date;
import java.util.List;

import com.segi.uhomecp.medicaltrans.report.schedule.model.TransSchedule;
  
/** 
 * Title: TransScheduleInfoService.java    
 * @Description: 描述
 * @author yangyh@segimail.com       
 * @created 2018年5月15日 下午3:42:59    
 */
public interface TransScheduleInfoService {

	/**
	 * @discription 获取当月更新的项目
	 * @author yangyh@segimail.com       
	 * @created 2018年5月15日 下午3:47:54     
	 * @return
	 */
	public List<Integer> getCurrentMonthOrgan(Date monthFirstDay, Date monthLastDay);
	
	/**
	 * @Title: queryTransSchedule 
	 * @Description: 通过organId查询排程表 
	 * @author liuyi@segimail.com 
	 * @date 2018年10月10日上午10:59:38
	 */
	public TransSchedule queryTransSchedule(Integer organId);
}
