package com.segi.uhomecp.medicaltrans.report.schedule.model;

import java.io.Serializable;
import java.util.Date;

public class ExecuteHandler implements Serializable {
    private Integer handlerId;

    private String handlerName;

    private String syncFlag;

    private Integer exeOrder;

    private String runningStatus;

    private Date createDate;

    private Date updateDate;

    private static final long serialVersionUID = 1L;

    public Integer getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Integer handlerId) {
        this.handlerId = handlerId;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName == null ? null : handlerName.trim();
    }

    public String getSyncFlag() {
        return syncFlag;
    }

    public void setSyncFlag(String syncFlag) {
        this.syncFlag = syncFlag == null ? null : syncFlag.trim();
    }

    public Integer getExeOrder() {
        return exeOrder;
    }

    public void setExeOrder(Integer exeOrder) {
        this.exeOrder = exeOrder;
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", handlerId=").append(handlerId);
        sb.append(", handlerName=").append(handlerName);
        sb.append(", syncFlag=").append(syncFlag);
        sb.append(", exeOrder=").append(exeOrder);
        sb.append(", runningStatus=").append(runningStatus);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}