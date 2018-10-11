package com.segi.uhomecp.medicaltrans.reportjob.query.deptvolumemonth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.segi.uhomecp.medicaltrans.report.monthrank.dept.model.DeptVolumeMonth;
import com.segi.uhomecp.medicaltrans.reportjob.query.deptvolumemonth.dto.DeptSqlDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.deptvolumemonth.dto.DeptVolumeMonthDto;

/**
 * 
 * CountDeptMonthMapper.java
 * @Description:全量统计科室运送量  
 * @author liuyi@segimail.com 
 * @created 2018年8月11日下午2:49:36
 */
public interface CountDeptMonthMapper {

	/**
	 * 
	 * @Title: getDeptVolumeMonth 
	 * @Description: 统计科室运送量  
	 * @author liuyi@segimail.com 
	 * @date 2018年7月26日下午7:41:14
	 */
	public List<DeptVolumeMonth> getDeptVolumeMonth(@Param("dto")DeptSqlDto deptSqlDto);
	
	/**
	 * @Title: getDeptMonthTaskType 
	 * @Description: 统计科室 任务类型数量
	 * @author liuyi@segimail.com 
	 * @date 2018年8月23日上午9:58:20
	 */
	public List<DeptVolumeMonthDto> getDeptMonthTaskType(@Param("dto")DeptSqlDto deptSqlDto);
	
	/**
	 * @Title: getDeptMonthUrgency 
	 * @Description:  统计科室紧急程度
	 * @author liuyi@segimail.com 
	 * @date 2018年8月23日上午9:59:18
	 */
	public List<DeptVolumeMonthDto> getDeptMonthUrgency(@Param("dto")DeptSqlDto deptSqlDto);
}
