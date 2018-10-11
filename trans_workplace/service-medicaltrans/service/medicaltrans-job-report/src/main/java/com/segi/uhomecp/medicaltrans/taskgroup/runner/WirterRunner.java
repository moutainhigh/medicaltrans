package com.segi.uhomecp.medicaltrans.taskgroup.runner;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.report.monthamount.source.model.SourceStatistics;
import com.segi.uhomecp.medicaltrans.report.monthamount.transtype.model.TranstypeStatistics;
import com.segi.uhomecp.medicaltrans.report.monthamount.transway.model.TranswayStatistics;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.model.DeptVolumeMonth;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.OrganMonthStatisticsDto;
import com.segi.uhomecp.medicaltrans.reportjob.report.deptvolumemonth.utils.DeptCommonUtils;
import com.segi.uhomecp.medicaltrans.reportjob.report.organmonth.service.ReportOrganMonthService;
import com.segi.uhomecp.medicaltrans.reportjob.report.source.service.ReportSourceService;
import com.segi.uhomecp.medicaltrans.reportjob.report.transtype.service.ReportTranstypeService;
import com.segi.uhomecp.medicaltrans.reportjob.report.transway.service.ReportTranswayService;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * 写数据runner
 *     
 * 包: com.segi.uhomecp.medicaltrans.taskgroup.runner 
 * 类名称: WirterRunner.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 上午11:48:02
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 * @param <E>
 *
 */
@Component
public class WirterRunner<E> extends AbstractRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WirterRunner.class);
	
	private ConcurrentHashMap<String, Object> countMap;
	
	@Autowired
	private ReportTranstypeService reportTranstypeService;
	
	@Autowired
	private DeptCommonUtils deptCommonUtils;
	
	@Autowired
	private ReportOrganMonthService reportOrganMonthService;
	
	@Autowired
	private ReportTranswayService reportTranswayService;
	
	@Autowired
	private ReportSourceService reportSourceService;
	
	public ConcurrentHashMap<String, Object> getCountMap() {
		return countMap;
	}

	public void setCountMap(ConcurrentHashMap<String, Object> countMap) {
		this.countMap = countMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() throws RuntimeException {
		LOGGER.info("update data start...");
		// 修改运送类型运输总量表
		XxlJobLogger.log("==============>开始增量更新运送类型运输总量表信息...");
		List<TranstypeStatistics> resultList = (List<TranstypeStatistics>) getCountMap().get(MtConstant.TRANS_TYPE_HANDLER);
		reportTranstypeService.updateTranstypeDay(resultList);
		XxlJobLogger.log("==============>结束增量更新运送类型运输总量表信息...");

		// 修改科室运送量报表库
		Map<String, Map<Integer, DeptVolumeMonth>> yearMonthMap = (Map<String, Map<Integer, DeptVolumeMonth>>) getCountMap()
		.get(MtConstant.INCR_DEPT_MONTH_HANDLER);
		XxlJobLogger.log("==============>开始增量更新科室运输总量表信息...---------------------------");
		XxlJobLogger.log("==============>开始增量更新科室运输总量表信息...---------" +  FastjsonUtils.toJsonString(yearMonthMap));
		deptCommonUtils.updateDeptMonthInfo(yearMonthMap);
		
		//项目月运送量
		Map<Integer, OrganMonthStatisticsDto> organMonthMap = (Map<Integer, OrganMonthStatisticsDto>) getCountMap().get(MtConstant.ORGAN_MONTH_HANDLER);
		reportOrganMonthService.updateOrganMonthByMapAdd(organMonthMap);
		
		// 修改运送方式运输总量表
//		XxlJobLogger.log("==============>开始增量更新运送方式运输总量表信息...");
//		List<TranswayStatistics> transwayList = (List<TranswayStatistics>) getCountMap().get(MtConstant.TRANS_WAY_HANDLER);
//		reportTranswayService.updateTranswayDay(transwayList);
//		XxlJobLogger.log("==============>结束增量更新运送方式运输总量表信息...");
		
		// 修改运送来源运输总量表
//		XxlJobLogger.log("==============>开始增量更新运送来源运输总量表信息...");
//		List<SourceStatistics> sourceList = (List<SourceStatistics>) getCountMap().get(MtConstant.TRANS_SOURCE_HANDLER);
//		reportSourceService.updateSourceDay(sourceList);
//		XxlJobLogger.log("==============>结束增量更新运送来源运输总量表信息...");
	}
}
