package com.segi.uhomecp.medicaltrans.trans.dto;

import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;

public class AutonomousTaskDto extends MtTask{

	/**  描述   (@author: wangxiong@segimail.com) */      
	private static final long serialVersionUID = 9062176373375399200L;
	
	private Integer userId;
	
	private Integer userOrganId;

	private Integer houseId;//目的地科室
	
	private String patientName;

	private String patientGender;
	
	private String bedNo;

	private String medicalRecNo;
	
	private String taskContent;
	
	private Integer userHouseId;
	
	private String fileIds;
	
	public String getFileIds() {
		return fileIds;
	}

	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}

	public Integer getUserHouseId() {
		return userHouseId;
	}

	public void setUserHouseId(Integer userHouseId) {
		this.userHouseId = userHouseId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public Integer getHouseId() {
		return houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	@Override
	public String toString() {
		return "AutonomousTaskDto [userId=" + userId + ", userOrganId="
				+ userOrganId + ", houseId=" + houseId + ", patientName="
				+ patientName + ", patientGender=" + patientGender + ", bedNo="
				+ bedNo + ", medicalRecNo=" + medicalRecNo + ", taskContent="
				+ taskContent + ", userHouseId=" + userHouseId + ", fileIds="
				+ fileIds + "]";
	}
}
