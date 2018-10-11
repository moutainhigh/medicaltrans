package com.segi.uhomecp.medicaltrans.base.taskloop.model;

import com.segi.uhomecp.common.model.AbstractModel;

public class MtTaskLoopRoute extends AbstractModel {
    private Integer routeId;

    private Integer taskLoopId;

    private Integer houseId;

    private Short sortNo;

    private static final long serialVersionUID = 1L;

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getTaskLoopId() {
        return taskLoopId;
    }

    public void setTaskLoopId(Integer taskLoopId) {
        this.taskLoopId = taskLoopId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Short getSortNo() {
        return sortNo;
    }

    public void setSortNo(Short sortNo) {
        this.sortNo = sortNo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", routeId=").append(routeId);
        sb.append(", taskLoopId=").append(taskLoopId);
        sb.append(", houseId=").append(houseId);
        sb.append(", sortNo=").append(sortNo);
        sb.append("]");
        return sb.toString();
    }
}