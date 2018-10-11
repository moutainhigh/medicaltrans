package com.segi.uhomecp.medicaltrans.trans.dto;

import java.io.Serializable;
import java.util.Date;

public class TaskPadQueryDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Integer taskId;
	
	private Integer organId;
	
	private String transTypeParentCode;
	
	private Integer transTypeId;
	
	private Integer fromHouseId;
	
	private Integer toHouseId;
	
	private String transTools;
	
	private Long beginTime;

    private Date exeEndTime;

    private String status;
    
    private String evaluate;
    
    private Integer sourceHouseId;
    
    private Integer createBy;
    
    private Date createDate;
    
    private String urgency;
    
    private Integer exeEndUserId;

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

	public Integer getTransTypeId() {
		return transTypeId;
	}

	public void setTransTypeId(Integer transTypeId) {
		this.transTypeId = transTypeId;
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

	public String getTransTools() {
		return transTools;
	}

	public void setTransTools(String transTools) {
		this.transTools = transTools;
	}

	public Long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
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

	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	
	public Integer getSourceHouseId() {
		return sourceHouseId;
	}

	public void setSourceHouseId(Integer sourceHouseId) {
		this.sourceHouseId = sourceHouseId;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
	}
	
	public Integer getExeEndUserId() {
		return exeEndUserId;
	}

	public void setExeEndUserId(Integer exeEndUserId) {
		this.exeEndUserId = exeEndUserId;
	}

	@Override
	public String toString() {
		return "TaskPadQueryDto [taskId=" + taskId + ", organId=" + organId
				+ ", transTypeParentCode=" + transTypeParentCode
				+ ", transTypeId=" + transTypeId + ", fromHouseId="
				+ fromHouseId + ", toHouseId=" + toHouseId + ", transTools="
				+ transTools + ", beginTime=" + beginTime + ", exeEndTime="
				+ exeEndTime + ", status=" + status + ", evaluate=" + evaluate
				+ ", sourceHouseId=" + sourceHouseId + ", createBy=" + createBy
				+ ", createDate=" + createDate + ", urgency=" + urgency
				+ ", exeEndUserId=" + exeEndUserId + "]";
	}

}
