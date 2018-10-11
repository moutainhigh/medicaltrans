package com.segi.uhomecp.medicaltrans.reportjob.report.transway.service;

import java.util.List;

import com.segi.uhomecp.medicaltrans.report.monthamount.transway.model.TranswayStatistics;

/**
 * Title: ReportTranswayService.java    
 * @Description: 运送方式运输总量报表业务方法
 * @author yangyh@segimail.com       
 * @created 2018年9月12日 下午4:54:43
 */
public interface ReportTranswayService {

	/**
	 * @discription 每月或每周的更新job
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:54:43     
	 * @param TranswayList
	 */
	public void updateTranswayMonth(List<TranswayStatistics> list, Integer organId, Integer cycleYm);
	
	/**
	 * @discription 每日的更新job
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:54:43     
	 * @param resultList
	 */
	public void updateTranswayDay(List<TranswayStatistics> resultList);
	
	/**
	 * @discription 根据部门idList和月份删除
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:54:43     
	 * @param organIdList
	 * @param cycleYm
	 */
	public void deleteByOrganListAndCycleYm(List<Integer> organIdList, Integer cycleYm);
}
