package com.segi.uhomecp.medicaltrans.reportjob.report.deptvolumemonth.service;

import java.util.List;
import java.util.Map;

import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocation;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.model.DeptVolumeMonth;
import com.segi.uhomecp.wh.common.dto.ResultDto;

/**
 * 
 * ReportDeptVolumeMonthService.java
 * @Description: 科室信息
 * @author liuyi@segimail.com 
 * @created 2018年8月11日下午2:57:31
 */
public interface ReportDeptVolumeMonthService {

	/**
	 * @Title: delDeptVolumeMonthInfo 
	 * @Description: 删除科室运送量 
	 * @author liuyi@segimail.com 
	 * @date 2018年7月30日下午3:42:28
	 */
	public void deleteDeptVolumeMonthInfo(List<Integer> organIdList, String cycleYm);
	
	/**
	 * @Title: deleteDeptInfoByHouseIdList 
	 * @Description: 根据科室id修改科室统计信息 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月7日下午12:02:30
	 */
	public void updateDeptCount(DeptVolumeMonth deptMonth);

	/**
	 * @Title: saveDeptVolumeMonthInfo 
	 * @Description: 保存科室运送量   
	 * @author liuyi@segimail.com 
	 * @date 2018年7月30日下午3:34:42
	 */
	public void saveDeptVolumeMonthInfo(List<DeptVolumeMonth> deptList);
	
	/**
	 * @Title: saveDeptVolumeMonthInfo 
	 * @Description: 保存科室运送量   
	 * @author liuyi@segimail.com 
	 * @date 2018年8月13日上午11:36:12
	 */
	public void saveDeptVolumeMonthInfo(DeptVolumeMonth dept);

	/**
	 * @Title: queryDeptVolumeMonthList 
	 * @Description: 查询科室统计信息 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月6日下午4:49:26
	 */
	public List<DeptVolumeMonth> queryDeptVolumeMonthList(List<Integer> houseIdList, String cycleYm);
	
	/**
	 * @Title: countDeptInitInfo 
	 * @Description: 计算科室报表信息剔除掉报表里纯在的对象  
	 * @author liuyi@segimail.com 
	 * @date 2018年8月16日上午11:20:22
	 */
	public List<MtBuildLocation> countDeptInitInfo(List<MtBuildLocation> buildList, String exeYearMonth);

	/**
	 * @Title: sqveDeptInfo 
	 * @Description: 先删除在保存科室信息 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月30日下午4:39:35
	 */
	public void saveDeptInfo(Integer organId, String cycleYm, ResultDto<String, String, Integer> resultDto,
			Map<Integer, List<DeptVolumeMonth>> deptMap);
}
