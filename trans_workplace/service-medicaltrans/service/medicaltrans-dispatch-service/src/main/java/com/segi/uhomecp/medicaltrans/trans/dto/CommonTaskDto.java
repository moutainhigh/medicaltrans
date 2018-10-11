package com.segi.uhomecp.medicaltrans.trans.dto;

import java.io.Serializable;
import java.util.Date;

public class CommonTaskDto implements Serializable {

	/** 描述 (@author: wangxiong@segimail.com) */

	private static final long serialVersionUID = 3379119767066731091L;

	private Integer taskId;
	private Integer houseId;
	private String mac;
	private String finishFileIds;
	private String taskUserIds;
	private String finishContent;

	private String dataSource; 		// 数据源
	private Integer userId;    		// 操作人Id
	private Integer userOrganId;    // 操作人组织Id
	
	private Integer organId;        // 组织Id
	
	private Date updateDate;        // 更新时间
	
	private Integer routeId;        // 路线Id
	
	private Short evaluate;			// 评价
	
	private String evaluateContent; // 评价内容
	
	private String applyStatus;           // 申请状态 1：是 申请   0：是拒绝
	
	private String autographFileIds;     // 签名附件
	
	private String backTaskReason;   // 退单原因
	
	private String timeOutReason;    // 超时原因
	
	public String getAutographFileIds() {
		return autographFileIds;
	}

	public void setAutographFileIds(String autographFileIds) {
		this.autographFileIds = autographFileIds;
	}

	public Short getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Short evaluate) {
		this.evaluate = evaluate;
	}

	public String getEvaluateContent() {
		return evaluateContent;
	}

	public void setEvaluateContent(String evaluateContent) {
		this.evaluateContent = evaluateContent;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getOrganId() {
		return organId;
	}

	public void setOrganId(Integer organId) {
		this.organId = organId;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getHouseId() {
		return houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getFinishFileIds() {
		return finishFileIds;
	}

	public void setFinishFileIds(String finishFileIds) {
		this.finishFileIds = finishFileIds;
	}

	public String getTaskUserIds() {
		return taskUserIds;
	}

	public void setTaskUserIds(String taskUserIds) {
		this.taskUserIds = taskUserIds;
	}

	public String getFinishContent() {
		return finishContent;
	}

	public void setFinishContent(String finishContent) {
		this.finishContent = finishContent;
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

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	public String getBackTaskReason() {
		return backTaskReason;
	}

	public void setBackTaskReason(String backTaskReason) {
		this.backTaskReason = backTaskReason;
	}

	public String getTimeOutReason() {
		return timeOutReason;
	}

	public void setTimeOutReason(String timeOutReason) {
		this.timeOutReason = timeOutReason;
	}
	
}
