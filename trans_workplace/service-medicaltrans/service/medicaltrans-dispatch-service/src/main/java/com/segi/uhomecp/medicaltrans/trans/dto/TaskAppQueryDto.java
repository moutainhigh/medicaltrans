package com.segi.uhomecp.medicaltrans.trans.dto;

import java.io.Serializable;
import java.util.Date;

public class TaskAppQueryDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer taskId;
	
	private Integer organId;
	
	private Long beginTime;

    private Integer limitMinute;
    
    private String transTypeParentCode;
    
    private Integer transTypeId;
    
    private String transTools;

    private String urgency;

    private Integer fromHouseId;
    
    private Integer toHouseId;
    
    private String status;
    
    private Date exeBeginTime;
    
    private Date exeEndTime;
    
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTransTypeId() {
		return transTypeId;
	}

	public void setTransTypeId(Integer transTypeId) {
		this.transTypeId = transTypeId;
	}

	public String getTransTools() {
		return transTools;
	}

	public void setTransTools(String transTools) {
		this.transTools = transTools;
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
	
	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "TaskAppQueryDto [taskId=" + taskId + ", organId=" + organId
				+ ", beginTime=" + beginTime + ", limitMinute=" + limitMinute
				+ ", transTypeParentCode=" + transTypeParentCode
				+ ", transTypeId=" + transTypeId + ", transTools=" + transTools
				+ ", urgency=" + urgency + ", fromHouseId=" + fromHouseId
				+ ", toHouseId=" + toHouseId + ", status=" + status
				+ ", exeBeginTime=" + exeBeginTime + ", exeEndTime="
				+ exeEndTime + ", endTime=" + endTime + "]";
	}

}
