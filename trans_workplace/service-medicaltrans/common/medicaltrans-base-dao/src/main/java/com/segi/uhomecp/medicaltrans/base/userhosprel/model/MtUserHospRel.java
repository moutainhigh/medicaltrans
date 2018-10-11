package com.segi.uhomecp.medicaltrans.base.userhosprel.model;

import java.util.Date;

import com.segi.uhomecp.common.model.AbstractModel;

public class MtUserHospRel extends AbstractModel {
    private Integer userHospRelId;

    private Integer userId;

    private Integer organId;

    private Integer houseId;

    private Date updateDate;

    private Integer updateBy;

    private static final long serialVersionUID = 1L;

    public Integer getUserHospRelId() {
        return userHospRelId;
    }

    public void setUserHospRelId(Integer userHospRelId) {
        this.userHospRelId = userHospRelId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrganId() {
        return organId;
    }

    public void setOrganId(Integer organId) {
        this.organId = organId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userHospRelId=").append(userHospRelId);
        sb.append(", userId=").append(userId);
        sb.append(", organId=").append(organId);
        sb.append(", houseId=").append(houseId);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append("]");
        return sb.toString();
    }
}