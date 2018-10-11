package com.segi.uhomecp.medicaltrans.trans.mttask.model;

import java.util.Date;

import com.segi.uhomecp.common.model.AbstractModel;

public class MtTaskRoute extends AbstractModel {
	private Integer routeId;

    private Integer taskId;

    private Integer houseId;

    private String status;

    private Short sortNo;

    private Date updateDate;

    private String finishContent;

    private String isAutograph;

    private Integer groupOrganId;

    private static final long serialVersionUID = 1L;

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Short getSortNo() {
        return sortNo;
    }

    public void setSortNo(Short sortNo) {
        this.sortNo = sortNo;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getFinishContent() {
        return finishContent;
    }

    public void setFinishContent(String finishContent) {
        this.finishContent = finishContent == null ? null : finishContent.trim();
    }

    public String getIsAutograph() {
        return isAutograph;
    }

    public void setIsAutograph(String isAutograph) {
        this.isAutograph = isAutograph == null ? null : isAutograph.trim();
    }

    public Integer getGroupOrganId() {
        return groupOrganId;
    }

    public void setGroupOrganId(Integer groupOrganId) {
        this.groupOrganId = groupOrganId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", routeId=").append(routeId);
        sb.append(", taskId=").append(taskId);
        sb.append(", houseId=").append(houseId);
        sb.append(", status=").append(status);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", finishContent=").append(finishContent);
        sb.append(", isAutograph=").append(isAutograph);
        sb.append(", groupOrganId=").append(groupOrganId);
        sb.append("]");
        return sb.toString();
    }
}