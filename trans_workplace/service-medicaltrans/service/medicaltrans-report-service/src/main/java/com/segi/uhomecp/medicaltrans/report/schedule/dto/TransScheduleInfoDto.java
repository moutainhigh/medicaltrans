package com.segi.uhomecp.medicaltrans.report.schedule.dto;

import java.io.Serializable;

public class TransScheduleInfoDto implements Serializable {
	
	/**  描述   (@author: wangxiong@segimail.com) */      
	private static final long serialVersionUID = 4873720222660704040L;
	// 一级物业Id
	private Integer groupOrganId;
	// 组织机构Id
	private Integer organId;
	// 运送状态
	private String runningStatus;
	
	private Integer userId;
	
	private Integer userOrganId;
	
	private Integer pageLength;
	
	private Integer pageNo;
	
	// 密码
	private String pwd;
	
	// 校正开始时间
	private String beginTime;
	
	// 校正结束时间
	private String endTime;

	public Integer getGroupOrganId() {
		return groupOrganId;
	}

	public void setGroupOrganId(Integer groupOrganId) {
		this.groupOrganId = groupOrganId;
	}

	public Integer getOrganId() {
		return organId;
	}

	public void setOrganId(Integer organId) {
		this.organId = organId;
	}

	public String getRunningStatus() {
		return runningStatus;
	}

	public void setRunningStatus(String runningStatus) {
		this.runningStatus = runningStatus;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserOrganId() {
		return userOrganId;
	}

	public void setUserOrganId(Integer userOrganId) {
		this.userOrganId = userOrganId;
	}

	public Integer getPageLength() {
		return pageLength;
	}

	public void setPageLength(Integer pageLength) {
		this.pageLength = pageLength;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
