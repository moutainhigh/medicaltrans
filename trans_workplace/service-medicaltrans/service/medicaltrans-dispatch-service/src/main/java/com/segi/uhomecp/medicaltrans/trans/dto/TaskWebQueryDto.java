package com.segi.uhomecp.medicaltrans.trans.dto;

import java.io.Serializable;
import java.util.Date;

public class TaskWebQueryDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Integer taskId;
	
	private Integer organId;
	
	private String transTypeParentCode;
	
	private String urgency;
	
	private String taskType;
	
	private Integer sourceHouseId;
	
	private Integer fromHouseId;
	
	private Integer toHouseId;
	
	private Long beginTime;

    private Integer limitMinute;
    
    private String resType;
    
    private String dataSource;

    private Date sendTime;
    
    private Date exeBeginTime;
    
    private Date exeEndTime;

    private String status;
    
    private Long createDate;

    private Integer createBy;
    
    /** 导出数据新增字段 */
    /** 运送内容描述*/
	private String taskContent;				 
	
	/** 签收人 */
	private Integer receiver;
	
	/** 签收人名称 */
	private Integer receiverName;
	
	/** 签收时间 */
	private Date receiveTime;	             	
	
	/** 评价结论 */
	private Short evaluate;
	
	/** 评价信息 */
	private String evaluateContent;
	
	/** 派单人 */
	private Integer dispatchUserId;
	
	/** 派单人名称 */
	private String dispatchUserName;
	
	/** 预计结束时间 */
	private Long endTime;

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
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

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public Integer getSourceHouseId() {
		return sourceHouseId;
	}

	public void setSourceHouseId(Integer sourceHouseId) {
		this.sourceHouseId = sourceHouseId;
	}

	public Integer getFromHouseId() {
		return fromHouseId;
	}

	public void setFromHouseId(Integer fromHouseId) {
		this.fromHouseId = fromHouseId;
	}

	public Integer getToHouseId() {
		return toHouseId;
	}

	public void setToHouseId(Integer toHouseId) {
		this.toHouseId = toHouseId;
	}

	public Long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}

	public Integer getLimitMinute() {
		return limitMinute;
	}

	public void setLimitMinute(Integer limitMinute) {
		this.limitMinute = limitMinute;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getExeBeginTime() {
		return exeBeginTime;
	}

	public void setExeBeginTime(Date exeBeginTime) {
		this.exeBeginTime = exeBeginTime;
	}

	public Date getExeEndTime() {
		return exeEndTime;
	}

	public void setExeEndTime(Date exeEndTime) {
		this.exeEndTime = exeEndTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

	public Integer getReceiver() {
		return receiver;
	}

	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}

	public Integer getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(Integer receiverName) {
		this.receiverName = receiverName;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
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

	public Integer getDispatchUserId() {
		return dispatchUserId;
	}

	public void setDispatchUserId(Integer dispatchUserId) {
		this.dispatchUserId = dispatchUserId;
	}

	public String getDispatchUserName() {
		return dispatchUserName;
	}

	public void setDispatchUserName(String dispatchUserName) {
		this.dispatchUserName = dispatchUserName;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "TaskWebQueryDto [taskId=" + taskId + ", organId=" + organId
				+ ", transTypeParentCode=" + transTypeParentCode + ", urgency="
				+ urgency + ", taskType=" + taskType + ", sourceHouseId="
				+ sourceHouseId + ", fromHouseId=" + fromHouseId
				+ ", toHouseId=" + toHouseId + ", beginTime=" + beginTime
				+ ", limitMinute=" + limitMinute + ", resType=" + resType
				+ ", dataSource=" + dataSource + ", sendTime=" + sendTime
				+ ", exeBeginTime=" + exeBeginTime + ", exeEndTime="
				+ exeEndTime + ", status=" + status + ", createDate="
				+ createDate + ", createBy=" + createBy + "]";
	}
     
}
