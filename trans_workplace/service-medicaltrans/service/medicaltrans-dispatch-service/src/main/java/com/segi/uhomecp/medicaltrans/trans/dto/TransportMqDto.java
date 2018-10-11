package com.segi.uhomecp.medicaltrans.trans.dto;

import java.io.Serializable;

public class TransportMqDto implements Serializable{

	/**  描述   (@author: wangxiong@segimail.com) */      
	private static final long serialVersionUID = 3545538335921858679L;
	
	private String operation; //":"create/update/cancel",	
	private String groupOrganId;
	private String organId ;//"organId"://"项目ID",
	private String taskId;//"运单ID",
	private String taskType;//"任务类型",
	private String status;//":"运输状态",
	private String beforeStatus;//":"之前运输状态",
	private String sourceHouseId;// 任务来源地
	private String fromHouseId;//":"开始位置",
	private String toHouseId;//":"到达位置",
	private String transTypeParentCode;//":"运输大类",
	private String transTypeId;//运送小类 
	private String beginTime; // 预约时间
	private String createDate;//":"下单时间",
	private String sendTime;//":"派单时间",
	private String exeBeginTime;//":"开始时间",
	private String exeEndTime;//":"完成时间",
	private String urgency;//"紧急程度",
	private String exeEndUserId;//主责任人Id
	private String timeStamp;//当前时间戳
	private String exeUserIds;//执行人userIds,用逗号隔开
	private String beforeSourceHouse;// 之前的任务来源地
	private String beforeUrgency;// 之前紧急程度
	private String beforeBeginTime; // 之前预约时间
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getGroupOrganId() {
		return groupOrganId;
	}
	public void setGroupOrganId(String groupOrganId) {
		this.groupOrganId = groupOrganId;
	}
	public String getOrganId() {
		return organId;
	}
	public void setOrganId(String organId) {
		this.organId = organId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBeforeStatus() {
		return beforeStatus;
	}
	public void setBeforeStatus(String beforeStatus) {
		this.beforeStatus = beforeStatus;
	}
	public String getSourceHouseId() {
		return sourceHouseId;
	}
	public void setSourceHouseId(String sourceHouseId) {
		this.sourceHouseId = sourceHouseId;
	}
	public String getFromHouseId() {
		return fromHouseId;
	}
	public void setFromHouseId(String fromHouseId) {
		this.fromHouseId = fromHouseId;
	}
	public String getToHouseId() {
		return toHouseId;
	}
	public void setToHouseId(String toHouseId) {
		this.toHouseId = toHouseId;
	}
	public String getTransTypeParentCode() {
		return transTypeParentCode;
	}
	public void setTransTypeParentCode(String transTypeParentCode) {
		this.transTypeParentCode = transTypeParentCode;
	}
	public String getTransTypeId() {
		return transTypeId;
	}
	public void setTransTypeId(String transTypeId) {
		this.transTypeId = transTypeId;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getExeBeginTime() {
		return exeBeginTime;
	}
	public void setExeBeginTime(String exeBeginTime) {
		this.exeBeginTime = exeBeginTime;
	}
	public String getExeEndTime() {
		return exeEndTime;
	}
	public void setExeEndTime(String exeEndTime) {
		this.exeEndTime = exeEndTime;
	}
	public String getUrgency() {
		return urgency;
	}
	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	public String getExeEndUserId() {
		return exeEndUserId;
	}
	public void setExeEndUserId(String exeEndUserId) {
		this.exeEndUserId = exeEndUserId;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getExeUserIds() {
		return exeUserIds;
	}
	public void setExeUserIds(String exeUserIds) {
		this.exeUserIds = exeUserIds;
	}
	
	public String getBeforeSourceHouse() {
		return beforeSourceHouse;
	}
	
	public void setBeforeSourceHouse(String beforeSourceHouse) {
		this.beforeSourceHouse = beforeSourceHouse;
	}
	
	public String getBeforeUrgency() {
		return beforeUrgency;
	}
	public void setBeforeUrgency(String beforeUrgency) {
		this.beforeUrgency = beforeUrgency;
	}
	
	public String getBeforeBeginTime() {
		return beforeBeginTime;
	}
	public void setBeforeBeginTime(String beforeBeginTime) {
		this.beforeBeginTime = beforeBeginTime;
	}
	
	@Override
	public String toString() {
		return "TransportMqDto [operation=" + operation + ", groupOrganId="
				+ groupOrganId + ", organId=" + organId + ", taskId=" + taskId
				+ ", taskType=" + taskType + ", status=" + status
				+ ", beforeStatus=" + beforeStatus + ", sourceHouseId="
				+ sourceHouseId + ", fromHouseId=" + fromHouseId
				+ ", toHouseId=" + toHouseId + ", transTypeParentCode="
				+ transTypeParentCode + ", transTypeId=" + transTypeId
				+ ", beginTime=" + beginTime + ", createDate=" + createDate
				+ ", sendTime=" + sendTime + ", exeBeginTime=" + exeBeginTime
				+ ", exeEndTime=" + exeEndTime + ", urgency=" + urgency
				+ ", exeEndUserId=" + exeEndUserId + ", timeStamp=" + timeStamp
				+ ", exeUserIds=" + exeUserIds + ", beforeSourceHouse="
				+ beforeSourceHouse + ", beforeUrgency=" + beforeUrgency
				+ ", beforeBeginTime=" + beforeBeginTime + "]";
	}
	
}
