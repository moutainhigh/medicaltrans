package com.segi.uhomecp.medicaltrans.base.userposit.model;

import java.util.Date;

import com.segi.uhomecp.common.model.AbstractModel;

public class MtUserPosit extends AbstractModel {
    private Integer userPositId;

    private Integer organId;

    private Integer buildId;

    private Integer floorId;

    private Integer userId;

    private Integer houseId;

    private String status;

    private Short unTaskNum;

    private Date updateDate;

    private Integer updateBy;

    private Short runTaskNum;

    private static final long serialVersionUID = 1L;

    public Integer getUserPositId() {
        return userPositId;
    }

    public void setUserPositId(Integer userPositId) {
        this.userPositId = userPositId;
    }

    public Integer getOrganId() {
        return organId;
    }

    public void setOrganId(Integer organId) {
        this.organId = organId;
    }

    public Integer getBuildId() {
        return buildId;
    }

    public void setBuildId(Integer buildId) {
        this.buildId = buildId;
    }

    public Integer getFloorId() {
        return floorId;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Short getUnTaskNum() {
        return unTaskNum;
    }

    public void setUnTaskNum(Short unTaskNum) {
        this.unTaskNum = unTaskNum;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Short getRunTaskNum() {
        return runTaskNum;
    }

    public void setRunTaskNum(Short runTaskNum) {
        this.runTaskNum = runTaskNum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userPositId=").append(userPositId);
        sb.append(", organId=").append(organId);
        sb.append(", buildId=").append(buildId);
        sb.append(", floorId=").append(floorId);
        sb.append(", userId=").append(userId);
        sb.append(", houseId=").append(houseId);
        sb.append(", status=").append(status);
        sb.append(", unTaskNum=").append(unTaskNum);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", runTaskNum=").append(runTaskNum);
        sb.append("]");
        return sb.toString();
    }
}