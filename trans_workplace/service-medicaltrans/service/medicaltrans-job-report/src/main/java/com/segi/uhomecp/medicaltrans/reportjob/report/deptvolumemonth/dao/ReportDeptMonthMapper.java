package com.segi.uhomecp.medicaltrans.reportjob.report.deptvolumemonth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.segi.uhomecp.medicaltrans.report.monthrank.dept.model.DeptVolumeMonth;
import com.segi.uhomecp.medicaltrans.reportjob.query.deptvolumemonth.dto.DeptSqlDto;

/**
 * 
 * ReportDeptMonthMapper.java
 * @Description: 科室信息sql
 * @author liuyi@segimail.com 
 * @created 2018年8月11日下午2:56:54
 */
public interface ReportDeptMonthMapper {

	/**
	 * @Title: saveDeptMonthIbatch 
	 * @Description: 保存科室信息 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月11日下午2:56:38
	 */
	public void saveDeptMonthIbatch(@Param("list")List<DeptVolumeMonth> list);
	
	/**
	 * @Title: updateDeptCount 
	 * @Description: 更新科室统计数量 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月11日下午5:37:17
	 */
	public int updateDeptCount(@Param("dept")DeptVolumeMonth deptMonth);
	
	/**
	 * @Title: queryHouseId 
	 * @Description: 查询当月科室是否已被统计 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月16日上午10:52:25
	 */
	public List<Integer> queryHouseId(@Param("dto")DeptSqlDto deptSqlDto);
}
