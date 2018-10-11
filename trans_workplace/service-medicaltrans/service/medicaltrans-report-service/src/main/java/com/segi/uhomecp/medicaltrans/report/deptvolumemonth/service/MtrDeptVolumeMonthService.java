package com.segi.uhomecp.medicaltrans.report.deptvolumemonth.service;

import resp.RpcRespIce;

import com.segi.uhomecp.medicaltrans.report.deptvolumemonth.dto.DeptMonthDto;

public interface MtrDeptVolumeMonthService {

	public RpcRespIce updateDeptVolume(DeptMonthDto deptMonthDto);
	
	public RpcRespIce queryTransSchedule(DeptMonthDto deptMonthDto);
}
