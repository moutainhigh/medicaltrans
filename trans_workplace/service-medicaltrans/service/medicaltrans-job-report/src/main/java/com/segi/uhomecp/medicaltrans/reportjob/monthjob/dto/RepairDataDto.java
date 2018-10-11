package com.segi.uhomecp.medicaltrans.reportjob.monthjob.dto;

import java.io.Serializable;

public class RepairDataDto implements Serializable {
	
	/**  描述   (@author: wangxiong@segimail.com) */      
	private static final long serialVersionUID = 4873720222660704040L;
	
	// 一级物业Id
	private Integer groupOrganId;
	
	// 组织机构Id
	private Integer organId;
	
	// 密码
	private String pwd;
	
	// 校正开始时间
	private String beginTime;
	
	// 校正结束时间
	private String endTime;
	
	// 登录用户userId
	private Integer userId;
	
	// 登录用户organId
	private Integer userOrganId;

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
	
}
