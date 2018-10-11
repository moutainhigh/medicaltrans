package com.segi.uhomecp.medicaltrans.trans.mttask.model;

import java.util.Date;

import com.segi.uhomecp.common.model.AbstractModel;

public class MtTaskExecutors extends AbstractModel {
    private Integer taskExeId;

    private Integer taskId;

    private Integer groupOrganId;

    private Integer exeUserId;

    private Date updateDate;

    private String isExeEndUser;

    private Integer organId;

    private String taskStatus;

    private Short evaluate;

    private String taskType;

    private String isTimeOut;

    private Long beginTime;

    private Date exeBeginTime;

    private Date exeEndTime;

    private Date sendTime;

    private Long createDate;

    private String urgency;

    private String transTypeParentCode;

    private Long endTime;

    private static final long serialVersionUID = 1L;

    public Integer getTaskExeId() {
        return taskExeId;
    }

    public void setTaskExeId(Integer taskExeId) {
        this.taskExeId = taskExeId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getGroupOrganId() {
        return groupOrganId;
    }

    public void setGroupOrganId(Integer groupOrganId) {
        this.groupOrganId = groupOrganId;
    }

    public Integer getExeUserId() {
        return exeUserId;
    }

    public void setExeUserId(Integer exeUserId) {
        this.exeUserId = exeUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getIsExeEndUser() {
        return isExeEndUser;
    }

    public void setIsExeEndUser(String isExeEndUser) {
        this.isExeEndUser = isExeEndUser == null ? null : isExeEndUser.trim();
    }

    public Integer getOrganId() {
        return organId;
    }

    public void setOrganId(Integer organId) {
        this.organId = organId;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus == null ? null : taskStatus.trim();
    }

    public Short getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Short evaluate) {
        this.evaluate = evaluate;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType == null ? null : taskType.trim();
    }

    public String getIsTimeOut() {
        return isTimeOut;
    }

    public void setIsTimeOut(String isTimeOut) {
        this.isTimeOut = isTimeOut == null ? null : isTimeOut.trim();
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
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

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency == null ? null : urgency.trim();
    }

    public String getTransTypeParentCode() {
        return transTypeParentCode;
    }

    public void setTransTypeParentCode(String transTypeParentCode) {
        this.transTypeParentCode = transTypeParentCode == null ? null : transTypeParentCode.trim();
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", taskExeId=").append(taskExeId);
        sb.append(", taskId=").append(taskId);
        sb.append(", groupOrganId=").append(groupOrganId);
        sb.append(", exeUserId=").append(exeUserId);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", isExeEndUser=").append(isExeEndUser);
        sb.append(", organId=").append(organId);
        sb.append(", taskStatus=").append(taskStatus);
        sb.append(", evaluate=").append(evaluate);
        sb.append(", taskType=").append(taskType);
        sb.append(", isTimeOut=").append(isTimeOut);
        sb.append(", beginTime=").append(beginTime);
        sb.append(", exeBeginTime=").append(exeBeginTime);
        sb.append(", exeEndTime=").append(exeEndTime);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", createDate=").append(createDate);
        sb.append(", urgency=").append(urgency);
        sb.append(", transTypeParentCode=").append(transTypeParentCode);
        sb.append(", endTime=").append(endTime);
        sb.append("]");
        return sb.toString();
    }
}