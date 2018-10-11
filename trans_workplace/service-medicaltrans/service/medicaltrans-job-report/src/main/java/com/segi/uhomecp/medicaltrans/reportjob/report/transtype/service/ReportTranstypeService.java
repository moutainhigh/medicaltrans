package com.segi.uhomecp.medicaltrans.reportjob.report.transtype.service;

import java.util.List;

import com.segi.uhomecp.medicaltrans.report.monthamount.transtype.model.TranstypeStatistics;

/**
 * Title: ReportTranstypeService.java    
 * @Description: 运送类型运输总量报表业务方法
 * @author yangyh@segimail.com       
 * @created 2018年8月13日 下午12:16:03
 */
public interface ReportTranstypeService {

	/**
	 * @discription 每月或每周的更新job
	 * @author yangyh@segimail.com       
	 * @created 2018年7月27日 下午12:02:47     
	 * @param transtypeList
	 */
	public void updateTranstypeMonth(List<TranstypeStatistics> list, Integer organId, Integer cycleYm);
	
	/**
	 * @discription 每日的更新job
	 * @author yangyh@segimail.com       
	 * @created 2018年7月27日 下午12:02:47     
	 * @param resultList
	 */
	public void updateTranstypeDay(List<TranstypeStatistics> resultList);
	
	/**
	 * @discription 根据部门idList和月份删除
	 * @author yangyh@segimail.com       
	 * @created 2018年8月13日 下午12:18:07     
	 * @param organIdList
	 * @param cycleYm
	 */
	public void deleteByOrganListAndCycleYm(List<Integer> organIdList, Integer cycleYm);
}
