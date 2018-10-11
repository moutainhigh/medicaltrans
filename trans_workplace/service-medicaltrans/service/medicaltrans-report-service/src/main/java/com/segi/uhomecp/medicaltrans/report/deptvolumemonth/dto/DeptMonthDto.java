package com.segi.uhomecp.medicaltrans.report.deptvolumemonth.dto;

import java.io.Serializable;
import java.util.Date;

public class DeptMonthDto implements Serializable {

	/**
	 * 2018年9月14日上午11:53:53
	 */
	private static final long serialVersionUID = 48636610842197121L;

	// 项目id
	private String organId;
	
	// 一级物业Id
	private String groupOrganId;
	
	// 修改后年月  201809
	private String cycleYm;
	
	// 修改前年月  201808
	private String beforeCycleYm;
	
	// 修改前科室id
	private String beforeHouseId;
	
	// 修改后科室id
	private String houseId;
	
	// 运送单创建时间
	private String createDate;
	
	// 运送单类型
	private String taskType;
	
	// 修改前紧急程度
	private String beforeUrgency;
	
	// 修改后紧急程度
	private String urgency;
	
	// 修改后预计开始时间 201810020900
	private Date beginDate;
	
	// 修改后预计开始时间 201809280900
	private Date beforeBeginDate;
	
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getBeforeBeginDate() {
		return beforeBeginDate;
	}

	public void setBeforeBeginDate(Date beforeBeginDate) {
		this.beforeBeginDate = beforeBeginDate;
	}
	
	public String getBeforeCycleYm() {
		return beforeCycleYm;
	}
	
	public void setBeforeCycleYm(String beforeCycleYm) {
		this.beforeCycleYm = beforeCycleYm;
	}
	
	public String getOrganId() {
		return organId;
	}
	
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	
	public String getGroupOrganId() {
		return groupOrganId;
	}
	
	public void setGroupOrganId(String groupOrganId) {
		this.groupOrganId = groupOrganId;
	}
	
	public String getCycleYm() {
		return cycleYm;
	}
	
	public void setCycleYm(String cycleYm) {
		this.cycleYm = cycleYm;
	}
	
	public String getBeforeHouseId() {
		return beforeHouseId;
	}
	
	public void setBeforeHouseId(String beforeHouseId) {
		this.beforeHouseId = beforeHouseId;
	}
	
	public String getHouseId() {
		return houseId;
	}
	
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	
	public String getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	public String getTaskType() {
		return taskType;
	}
	
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	
	public String getBeforeUrgency() {
		return beforeUrgency;
	}
	
	public void setBeforeUrgency(String beforeUrgency) {
		this.beforeUrgency = beforeUrgency;
	}
	
	public String getUrgency() {
		return urgency;
	}
	
	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
}
