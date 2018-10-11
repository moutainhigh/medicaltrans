package com.segi.uhomecp.medicaltrans.report.deptvolumemonth.dao;

import org.apache.ibatis.annotations.Param;

import com.segi.uhomecp.medicaltrans.report.monthrank.dept.model.DeptVolumeMonth;

public interface DeptMonthMapper {

	public int updateDeptMonth(@Param("dept")DeptVolumeMonth deptMonth);
}
