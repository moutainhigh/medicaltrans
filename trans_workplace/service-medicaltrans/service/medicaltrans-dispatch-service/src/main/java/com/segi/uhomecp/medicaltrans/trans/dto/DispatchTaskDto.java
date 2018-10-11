package com.segi.uhomecp.medicaltrans.trans.dto;

import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;

public class DispatchTaskDto extends MtTask{

	/**  描述   (@author: wangxiong@segimail.com) */      
	private static final long serialVersionUID = -4483939171840158107L;

	private Integer userId;
	
	private Integer userOrganId;
	
	private Integer userHouseId;
	
	private String exeUserIds;
	
	private String serviceGroupIds;
	
    private String patientName;

    private String patientGender;

    private String bedNo;

    private String medicalRecNo;

    private String taskContent;

    private String sendContent;

    private String finishContent;
    
    //是否预约
    private String isReservedFlag;
    
    //附件
    private String fileIds;
    
    private String delFileIds;
    
    private String voiceIds;
    
    private String delVoiceIds;
    
    private Boolean flag;
    
	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public String getFileIds() {
		return fileIds;
	}

	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}

	public String getDelFileIds() {
		return delFileIds;
	}

	public void setDelFileIds(String delFileIds) {
		this.delFileIds = delFileIds;
	}

	public String getVoiceIds() {
		return voiceIds;
	}

	public void setVoiceIds(String voiceIds) {
		this.voiceIds = voiceIds;
	}

	public String getDelVoiceIds() {
		return delVoiceIds;
	}

	public void setDelVoiceIds(String delVoiceIds) {
		this.delVoiceIds = delVoiceIds;
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

	public Integer getUserHouseId() {
		return userHouseId;
	}

	public void setUserHouseId(Integer userHouseId) {
		this.userHouseId = userHouseId;
	}

	public String getExeUserIds() {
		return exeUserIds;
	}

	public void setExeUserIds(String exeUserIds) {
		this.exeUserIds = exeUserIds;
	}

	public String getServiceGroupIds() {
		return serviceGroupIds;
	}

	public void setServiceGroupIds(String serviceGroupIds) {
		this.serviceGroupIds = serviceGroupIds;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	public String getBedNo() {
		return bedNo;
	}

	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	public String getMedicalRecNo() {
		return medicalRecNo;
	}

	public void setMedicalRecNo(String medicalRecNo) {
		this.medicalRecNo = medicalRecNo;
	}

	public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

	public String getSendContent() {
		return sendContent;
	}

	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}

	public String getFinishContent() {
		return finishContent;
	}

	public void setFinishContent(String finishContent) {
		this.finishContent = finishContent;
	}

	public String getIsReservedFlag() {
		return isReservedFlag;
	}

	public void setIsReservedFlag(String isReservedFlag) {
		this.isReservedFlag = isReservedFlag;
	}
	
}
