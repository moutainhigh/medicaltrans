package com.segi.uhomecp.medicaltrans.reportjob.query.trans.service;

import java.util.Date;
import java.util.List;

import com.segi.uhomecp.medicaltrans.report.monthamount.source.model.SourceStatistics;
import com.segi.uhomecp.medicaltrans.report.monthamount.transtype.model.TranstypeStatistics;
import com.segi.uhomecp.medicaltrans.report.monthamount.transway.model.TranswayStatistics;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.OrganMonthStatisticsDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.OrganTimeStatisticsDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.ReportJobTimeDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.model.MtTaskExtract;

/**
 * 
 *     
 * 包: com.segi.uhomecp.medicaltrans.reportjob.query.trans.service 
 * 类名称: TransService.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 下午4:00:18
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
public interface TransService {
	/**
	 * 根据项目ID获取运单数据集
	 * @param organId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<MtTaskExtract> getTaskList(Integer groupOrangId, Integer organId, Date beginTime, Date endTime);
	
	/**
	 * 通过创建时间获取运单数据集
	 * @param organId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public List<MtTaskExtract> getTaskListByCreate(Integer groupOrangId, Integer organId, Date beginTime, Date endTime);
	
	/**
	 * 根据项目id 获取项目月运送量
	 * @param organIdList
	 * @param startTime
	 * @param endTime
	 */
	public List<OrganMonthStatisticsDto> getOrganMonthList(int groupOrganId, ReportJobTimeDto reportJobTimeDto);
	
	/**
	 *	通过项目、年月获取入参（注意：当月是开始时间到前一天23:59分，如果是当月1号，开始时间和结束时间一致）
	 */
	public ReportJobTimeDto getJobEnterParam(Integer groupOrganId, List<Integer> organIdList, String exeYearMonth);
	
	/**
	 * @discription 根据条件对象去业务数据中取运送类型list
	 * @author yangyh@segimail.com       
	 * @created 2018年8月2日 上午10:52:35     
	* @param reportJobTimeDto
	 * @return
	 */
	public List<TranstypeStatistics> getTranstypeList(int groupOrganId, ReportJobTimeDto reportJobTimeDto);

	/**
	 * @discription 根据条件对象去业务数据中取运送方式list
	 * @author yangyh@segimail.com       
	 * @created 2018年9月13日 上午10:15:16     
	 * @param reportJobTimeDto
	 * @return
	 */
	public List<TranswayStatistics> getTranswayList(int groupOrganId, ReportJobTimeDto reportJobTimeDto);

	/**
	 * @discription 根据条件对象去业务数据中取运送来源list
	 * @author yangyh@segimail.com       
	 * @created 2018年9月13日 上午10:15:16     
	 * @param reportJobTimeDto
	 * @return
	 */
	public List<SourceStatistics> getSourceList(int groupOrganId, ReportJobTimeDto reportJobTimeDto);
	
	/**
	 * 查出各个时段运送量总数据
	 * @param groupOrganId
	 * @param reportJobTimeDto
	 * @return
	 */
	public List<OrganTimeStatisticsDto> getOrganTimeList(int groupOrganId, ReportJobTimeDto reportJobTimeDto);
}
