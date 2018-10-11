package com.segi.uhomecp.medicaltrans.trans.mttask.model;

import java.util.Date;

import com.segi.uhomecp.common.model.AbstractModel;

public class MtTaskExt extends AbstractModel {
    private Integer taskExtId;

    private Integer taskId;

    private String patientName;

    private String patientGender;

    private String bedNo;

    private String medicalRecNo;

    private String taskContent;

    private String sendContent;

    private Integer groupOrganId;

    private String evaluateContent;

    private Integer receiver;

    private Date receiveTime;

    private static final long serialVersionUID = 1L;

    public Integer getTaskExtId() {
        return taskExtId;
    }

    public void setTaskExtId(Integer taskExtId) {
        this.taskExtId = taskExtId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender == null ? null : patientGender.trim();
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo == null ? null : bedNo.trim();
    }

    public String getMedicalRecNo() {
        return medicalRecNo;
    }

    public void setMedicalRecNo(String medicalRecNo) {
        this.medicalRecNo = medicalRecNo == null ? null : medicalRecNo.trim();
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent == null ? null : taskContent.trim();
    }

    public String getSendContent() {
        return sendContent;
    }

    public void setSendContent(String sendContent) {
        this.sendContent = sendContent == null ? null : sendContent.trim();
    }

    public Integer getGroupOrganId() {
        return groupOrganId;
    }

    public void setGroupOrganId(Integer groupOrganId) {
        this.groupOrganId = groupOrganId;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent == null ? null : evaluateContent.trim();
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", taskExtId=").append(taskExtId);
        sb.append(", taskId=").append(taskId);
        sb.append(", patientName=").append(patientName);
        sb.append(", patientGender=").append(patientGender);
        sb.append(", bedNo=").append(bedNo);
        sb.append(", medicalRecNo=").append(medicalRecNo);
        sb.append(", taskContent=").append(taskContent);
        sb.append(", sendContent=").append(sendContent);
        sb.append(", groupOrganId=").append(groupOrganId);
        sb.append(", evaluateContent=").append(evaluateContent);
        sb.append(", receiver=").append(receiver);
        sb.append(", receiveTime=").append(receiveTime);
        sb.append("]");
        return sb.toString();
    }
}