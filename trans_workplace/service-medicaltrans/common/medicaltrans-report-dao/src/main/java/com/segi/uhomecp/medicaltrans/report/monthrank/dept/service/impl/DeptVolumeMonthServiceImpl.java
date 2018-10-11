package com.segi.uhomecp.medicaltrans.report.monthrank.dept.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.dao.DeptVolumeMonthMapper;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.model.DeptVolumeMonth;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.model.DeptVolumeMonthCriteria;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.service.DeptVolumeMonthService;

@Service
public class DeptVolumeMonthServiceImpl extends
		GenericServiceImpl<DeptVolumeMonth, DeptVolumeMonthCriteria, Integer>
		implements DeptVolumeMonthService {

	/**
	 * 2018年7月24日上午9:25:09
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private DeptVolumeMonthMapper deptVolumeMonthMapper;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(deptVolumeMonthMapper);
		
	}

}
