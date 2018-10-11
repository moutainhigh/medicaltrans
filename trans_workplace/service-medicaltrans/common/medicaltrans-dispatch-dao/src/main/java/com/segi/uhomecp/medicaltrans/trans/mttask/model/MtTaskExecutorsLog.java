package com.segi.uhomecp.medicaltrans.trans.mttask.model;

import java.util.Date;

import com.segi.uhomecp.common.model.AbstractModel;

public class MtTaskExecutorsLog extends AbstractModel {
    private Integer taskExeId;

    private Integer groupOrganId;

    private Integer taskId;

    private Integer exeUserId;

    private Integer version;

    private String status;

    private Date updateDate;

    private String isExeEndUser;

    private Integer organId;

    private String taskType;

    private static final long serialVersionUID = 1L;

    public Integer getTaskExeId() {
        return taskExeId;
    }

    public void setTaskExeId(Integer taskExeId) {
        this.taskExeId = taskExeId;
    }

    public Integer getGroupOrganId() {
        return groupOrganId;
    }

    public void setGroupOrganId(Integer groupOrganId) {
        this.groupOrganId = groupOrganId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getExeUserId() {
        return exeUserId;
    }

    public void setExeUserId(Integer exeUserId) {
        this.exeUserId = exeUserId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType == null ? null : taskType.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", taskExeId=").append(taskExeId);
        sb.append(", groupOrganId=").append(groupOrganId);
        sb.append(", taskId=").append(taskId);
        sb.append(", exeUserId=").append(exeUserId);
        sb.append(", version=").append(version);
        sb.append(", status=").append(status);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", isExeEndUser=").append(isExeEndUser);
        sb.append(", organId=").append(organId);
        sb.append(", taskType=").append(taskType);
        sb.append("]");
        return sb.toString();
    }
}