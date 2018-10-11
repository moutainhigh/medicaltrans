package com.segi.uhomecp.medicaltrans.report.schedule.model;

import java.io.Serializable;
import java.util.Date;

public class ScheduleLog implements Serializable {
    private Integer id;

    private Integer organId;

    private Date startDate;

    private Date endDate;

    private String runningStatus;

    private Integer taskCount;

    private Integer exeTime;

    private String errorMessage;

    private Date createDate;

    private Date excTime;

    private Date lastExcDate;

    private Date excEndDate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRunningStatus() {
        return runningStatus;
    }

    public void setRunningStatus(String runningStatus) {
        this.runningStatus = runningStatus == null ? null : runningStatus.trim();
    }

    public Integer getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }

    public Integer getExeTime() {
        return exeTime;
    }

    public void setExeTime(Integer exeTime) {
        this.exeTime = exeTime;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage == null ? null : errorMessage.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExcTime() {
        return excTime;
    }

    public void setExcTime(Date excTime) {
        this.excTime = excTime;
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
        sb.append(", id=").append(id);
        sb.append(", organId=").append(organId);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", runningStatus=").append(runningStatus);
        sb.append(", taskCount=").append(taskCount);
        sb.append(", exeTime=").append(exeTime);
        sb.append(", errorMessage=").append(errorMessage);
        sb.append(", createDate=").append(createDate);
        sb.append(", excTime=").append(excTime);
        sb.append(", lastExcDate=").append(lastExcDate);
        sb.append(", excEndDate=").append(excEndDate);
        sb.append("]");
        return sb.toString();
    }
}