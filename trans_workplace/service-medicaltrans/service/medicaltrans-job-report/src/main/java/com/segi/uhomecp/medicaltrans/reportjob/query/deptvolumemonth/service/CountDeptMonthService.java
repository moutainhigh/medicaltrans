package com.segi.uhomecp.medicaltrans.reportjob.query.deptvolumemonth.service;

import java.util.List;

import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocation;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.model.DeptVolumeMonth;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.ReportJobTimeDto;

/**
 * 
 * CountDeptMonthService.java
 * @Description: 科室运送量
 * @author liuyi@segimail.com 
 * @created 2018年8月11日下午2:17:19
 */
public interface CountDeptMonthService {

	/**
	 * 
	 * @Title: queryDeptVolumeMonthInfo 
	 * @Description:  查询科室运送量 
	 * @author liuyi@segimail.com 
	 * @date 2018年7月30日下午3:33:18
	 */
	public ResultInfo queryDeptVolumeMonthInfo(Integer groupOrganId, List<MtBuildLocation> buildList, ReportJobTimeDto reportJobTimeDto);

	/**
	 * @Title: queryCountDeptInfo 
	 * @Description: 统计科室信息  
	 * @author liuyi@segimail.com 
	 * @date 2018年8月14日下午7:19:38
	 */
	public List<DeptVolumeMonth> queryCountDeptInfo(Integer groupOrganId, List<MtBuildLocation> buildList, ReportJobTimeDto reportJobTimeDto);
}
