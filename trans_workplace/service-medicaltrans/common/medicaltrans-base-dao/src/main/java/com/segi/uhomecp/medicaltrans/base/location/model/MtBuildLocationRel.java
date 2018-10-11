package com.segi.uhomecp.medicaltrans.base.location.model;

import java.io.Serializable;
import java.util.Date;

import com.segi.uhomecp.common.model.AbstractModel;

public class MtBuildLocationRel extends AbstractModel {
    private Integer locationId;

    private Integer locationRelId;

    private Integer organId;

    private Integer houseId;

    private Date updateDate;

    private Integer updateuserId;

    private static final long serialVersionUID = 1L;

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getLocationRelId() {
        return locationRelId;
    }

    public void setLocationRelId(Integer locationRelId) {
        this.locationRelId = locationRelId;
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

    public Integer getUpdateuserId() {
        return updateuserId;
    }

    public void setUpdateuserId(Integer updateuserId) {
        this.updateuserId = updateuserId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", locationId=").append(locationId);
        sb.append(", locationRelId=").append(locationRelId);
        sb.append(", organId=").append(organId);
        sb.append(", houseId=").append(houseId);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateuserId=").append(updateuserId);
        sb.append("]");
        return sb.toString();
    }
}