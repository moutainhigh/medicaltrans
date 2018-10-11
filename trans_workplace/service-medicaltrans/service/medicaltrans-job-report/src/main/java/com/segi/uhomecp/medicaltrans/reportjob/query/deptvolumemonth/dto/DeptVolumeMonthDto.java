package com.segi.uhomecp.medicaltrans.reportjob.query.deptvolumemonth.dto;

import com.segi.uhomecp.medicaltrans.report.monthrank.dept.model.DeptVolumeMonth;

public class DeptVolumeMonthDto extends DeptVolumeMonth {

	private String taskType;
	
	private String urgency;
	
	private Integer num;
	
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	/**
	 * 2018年7月26日下午12:00:22
	 */
	private static final long serialVersionUID = -3282256162623615161L;

	 // 是否有业务数据标识
    private boolean haveBusDataFlag;

	public boolean isHaveBusDataFlag() {
		return haveBusDataFlag;
	}

	public void setHaveBusDataFlag(boolean haveBusDataFlag) {
		this.haveBusDataFlag = haveBusDataFlag;
	}
    
}
