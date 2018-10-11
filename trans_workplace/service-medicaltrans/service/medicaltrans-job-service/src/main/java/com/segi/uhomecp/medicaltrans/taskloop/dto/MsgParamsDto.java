package com.segi.uhomecp.medicaltrans.taskloop.dto;

import java.io.Serializable;

public class MsgParamsDto implements Serializable {
	private static final long serialVersionUID = -5904765717304268642L;
	private String organId;
	private String transTypeParentCode;
	private String transTypeId;
	private String urgency;
	private String sourceHouseId;
	private String fromHouseId;
	private String toHouseId;
	private String transTools;
	private String transPersonCount;
	private String isReservedFlag;
	private String beginTime;
	private String limitMinute;
	private String taskContent;
	private String patientName;
	private String bedNo;
	private String patientGender;
	private String medicalRecNo;
	private String sendContent;
	private Integer[] routeList;
	private Integer[] transactors;
	private Integer createBy;
	private Integer taskLoopId;
	private Integer groupOrganId;

	public MsgParamsDto(String organId, String transTypeParentCode, String transTypeId, String urgency,
			String sourceHouseId, String fromHouseId, String toHouseId, String transTools, String transPersonCount,
			String isReservedFlag, String beginTime, String limitMinute, String taskContent, String patientName,
			String bedNo, String patientGender, String medicalRecNo, String sendContent, Integer[] routeList,
			Integer[] transactors, Integer createBy, Integer taskLoopId, Integer groupOrganId) {
		super();
		this.organId = organId;
		this.transTypeParentCode = transTypeParentCode;
		this.transTypeId = transTypeId;
		this.urgency = urgency;
		this.sourceHouseId = sourceHouseId;
		this.fromHouseId = fromHouseId;
		this.toHouseId = toHouseId;
		this.transTools = transTools;
		this.transPersonCount = transPersonCount;
		this.isReservedFlag = isReservedFlag;
		this.beginTime = beginTime;
		this.limitMinute = limitMinute;
		this.taskContent = taskContent;
		this.patientName = patientName;
		this.bedNo = bedNo;
		this.patientGender = patientGender;
		this.medicalRecNo = medicalRecNo;
		this.sendContent = sendContent;
		this.routeList = routeList;
		this.transactors = transactors;
		this.createBy = createBy;
		this.taskLoopId = taskLoopId;
		this.groupOrganId = groupOrganId;
	}

	public MsgParamsDto() {
		super();
	}

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
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

	public String getUrgency() {
		return urgency;
	}

	public void setUrgency(String urgency) {
		this.urgency = urgency;
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

	public String getTransTools() {
		return transTools;
	}

	public void setTransTools(String transTools) {
		this.transTools = transTools;
	}

	public String getTransPersonCount() {
		return transPersonCount;
	}

	public void setTransPersonCount(String transPersonCount) {
		this.transPersonCount = transPersonCount;
	}

	public String getIsReservedFlag() {
		return isReservedFlag;
	}

	public void setIsReservedFlag(String isReservedFlag) {
		this.isReservedFlag = isReservedFlag;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getLimitMinute() {
		return limitMinute;
	}

	public void setLimitMinute(String limitMinute) {
		this.limitMinute = limitMinute;
	}

	public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getBedNo() {
		return bedNo;
	}

	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	public String getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	public String getMedicalRecNo() {
		return medicalRecNo;
	}

	public void setMedicalRecNo(String medicalRecNo) {
		this.medicalRecNo = medicalRecNo;
	}

	public String getSendContent() {
		return sendContent;
	}

	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}

	public Integer[] getRouteList() {
		return routeList;
	}

	public void setRouteList(Integer[] routeList) {
		this.routeList = routeList;
	}

	public Integer[] getTransactors() {
		return transactors;
	}

	public void setTransactors(Integer[] transactors) {
		this.transactors = transactors;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Integer getTaskLoopId() {
		return taskLoopId;
	}

	public void setTaskLoopId(Integer taskLoopId) {
		this.taskLoopId = taskLoopId;
	}

	public Integer getGroupOrganId() {
		return groupOrganId;
	}

	public void setGroupOrganId(Integer groupOrganId) {
		this.groupOrganId = groupOrganId;
	}
}
