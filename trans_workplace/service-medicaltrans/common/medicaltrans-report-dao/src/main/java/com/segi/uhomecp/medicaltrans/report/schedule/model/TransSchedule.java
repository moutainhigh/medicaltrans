package com.segi.uhomecp.medicaltrans.report.schedule.model;

import java.util.Date;

import com.segi.uhomecp.common.model.AbstractModel;

public class TransSchedule extends AbstractModel {
    private Integer organId;

    private Date startDate;

    private Date paramDate;

    private String status;

    private String runningStatus;

    private Date createDate;

    private Integer groupOrganId;

    private Date updateDate;

    private Date excDate;

    private Date lastExcDate;

    private Date excEndDate;

    private static final long serialVersionUID = 1L;

    public Integer getOrganId() {
        return organId;
    }

    public void setOrganId(Integer organId) {
        this.organId = organId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getParamDate() {
        return paramDate;
    }

    public void setParamDate(Date paramDate) {
        this.paramDate = paramDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRunningStatus() {
        return runningStatus;
    }

    public void setRunningStatus(String runningStatus) {
        this.runningStatus = runningStatus == null ? null : runningStatus.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getGroupOrganId() {
        return groupOrganId;
    }

    public void setGroupOrganId(Integer groupOrganId) {
        this.groupOrganId = groupOrganId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getExcDate() {
        return excDate;
    }

    public void setExcDate(Date excDate) {
        this.excDate = excDate;
    }

    public Date getLastExcDate() {
        return lastExcDate;
    }

    public void setLastExcDate(Date lastExcDate) {
        this.lastExcDate = lastExcDate;
    }

    public Date getExcEndDate() {
        return excEndDate;
    }

    public void setExcEndDate(Date excEndDate) {
        this.excEndDate = excEndDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", organId=").append(organId);
        sb.append(", startDate=").append(startDate);
        sb.append(", paramDate=").append(paramDate);
        sb.append(", status=").append(status);
        sb.append(", runningStatus=").append(runningStatus);
        sb.append(", createDate=").append(createDate);
        sb.append(", groupOrganId=").append(groupOrganId);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", excDate=").append(excDate);
        sb.append(", lastExcDate=").append(lastExcDate);
        sb.append(", excEndDate=").append(excEndDate);
        sb.append("]");
        return sb.toString();
    }
}