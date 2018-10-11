package com.segi.uhomecp.medicaltrans.trans.dto;

import java.io.Serializable;
import java.util.List;

public class MtTaskPageDto implements Serializable {

	/** 描述 (@author: wangxiong@segimail.com) */
	private static final long serialVersionUID = 8321588611052300340L;
	private Integer organId;
	private String transTypeParentCode;
	private Integer taskId;
	private String urgency;
	private Long beginTime;
	private Long endTime;
	private String status;
	private String taskType;
	private String mtTaskStatus;
	private String exeUserStatus;
	private Integer pageNo;
	private Integer pageLength;

	private Integer userOrganId;
	private Integer userId;
	
	//用户所在科室Id
	private Integer userHouseId;
	
	private List<String> taskStatusList;
	
	//评价状态
	private String evaluateStatus;
	
	//评价标志
	private Boolean evaluateFlag;
	
	// 不同客户端调用接口标志
	private String invokingFlag;
	
	// app/pad版本号
	private String version;
	
	public Integer getUserHouseId() {
		return userHouseId;
	}

	public void setUserHouseId(Integer userHouseId) {
		this.userHouseId = userHouseId;
	}

	public Boolean getEvaluateFlag() {
		return evaluateFlag;
	}

	public void setEvaluateFlag(Boolean evaluateFlag) {
		this.evaluateFlag = evaluateFlag;
	}

	public String getEvaluateStatus() {
		return evaluateStatus;
	}

	public void setEvaluateStatus(String evaluateStatus) {
		this.evaluateStatus = evaluateStatus;
	}

	public List<String> getTaskStatusList() {
		return taskStatusList;
	}

	public void setTaskStatusList(List<String> taskStatusList) {
		this.taskStatusList = taskStatusList;
	}

	public Integer getOrganId() {
		return organId;
	}

	public void setOrganId(Integer organId) {
		this.organId = organId;
	}

	public String getTransTypeParentCode() {
		return transTypeParentCode;
	}

	public void setTransTypeParentCode(String transTypeParentCode) {
		this.transTypeParentCode = transTypeParentCode;
	}

	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}

	public Long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageLength() {
		return pageLength;
	}

	public void setPageLength(Integer pageLength) {
		this.pageLength = pageLength;
	}

	public Integer getUserOrganId() {
		return userOrganId;
	}

	public void setUserOrganId(Integer userOrganId) {
		this.userOrganId = userOrganId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getMtTaskStatus() {
		return mtTaskStatus;
	}

	public void setMtTaskStatus(String mtTaskStatus) {
		this.mtTaskStatus = mtTaskStatus;
	}

	public String getExeUserStatus() {
		return exeUserStatus;
	}

	public void setExeUserStatus(String exeUserStatus) {
		this.exeUserStatus = exeUserStatus;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getInvokingFlag() {
		return invokingFlag;
	}

	public void setInvokingFlag(String invokingFlag) {
		this.invokingFlag = invokingFlag;
	}
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "MtTaskPageDto [organId=" + organId + ", transTypeParentCode="
				+ transTypeParentCode + ", taskId=" + taskId + ", urgency="
				+ urgency + ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", status=" + status + ", taskType=" + taskType
				+ ", mtTaskStatus=" + mtTaskStatus + ", exeUserStatus="
				+ exeUserStatus + ", pageNo=" + pageNo + ", pageLength="
				+ pageLength + ", userOrganId=" + userOrganId + ", userId="
				+ userId + ", userHouseId=" + userHouseId + ", taskStatusList="
				+ taskStatusList + ", evaluateStatus=" + evaluateStatus
				+ ", evaluateFlag=" + evaluateFlag + ", invokingFlag="
				+ invokingFlag + ", version=" + version + "]";
	}

}
