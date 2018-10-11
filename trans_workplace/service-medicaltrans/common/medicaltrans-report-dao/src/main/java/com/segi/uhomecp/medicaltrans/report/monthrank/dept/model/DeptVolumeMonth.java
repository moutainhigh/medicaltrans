package com.segi.uhomecp.medicaltrans.report.monthrank.dept.model;

import com.segi.uhomecp.common.model.AbstractModel;

public class DeptVolumeMonth extends AbstractModel {
    private Integer id;

    private Integer organId;

    private Integer groupOrganId;

    private Integer houseId;

    private Integer buildId;

    private Integer sid;

    private Integer floorId;

    private Integer cycleYm;

    private Integer transAmount;

    private Integer dispatchAmount;

    private Integer autonomousAmount;

    private Integer specialAmount;

    private Integer urgentAmount;

    private Integer commonAmount;

    private Long createDate;

    private Long updateDate;

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

    public Integer getGroupOrganId() {
        return groupOrganId;
    }

    public void setGroupOrganId(Integer groupOrganId) {
        this.groupOrganId = groupOrganId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getBuildId() {
        return buildId;
    }

    public void setBuildId(Integer buildId) {
        this.buildId = buildId;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getFloorId() {
        return floorId;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    public Integer getCycleYm() {
        return cycleYm;
    }

    public void setCycleYm(Integer cycleYm) {
        this.cycleYm = cycleYm;
    }

    public Integer getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(Integer transAmount) {
        this.transAmount = transAmount;
    }

    public Integer getDispatchAmount() {
        return dispatchAmount;
    }

    public void setDispatchAmount(Integer dispatchAmount) {
        this.dispatchAmount = dispatchAmount;
    }

    public Integer getAutonomousAmount() {
        return autonomousAmount;
    }

    public void setAutonomousAmount(Integer autonomousAmount) {
        this.autonomousAmount = autonomousAmount;
    }

    public Integer getSpecialAmount() {
        return specialAmount;
    }

    public void setSpecialAmount(Integer specialAmount) {
        this.specialAmount = specialAmount;
    }

    public Integer getUrgentAmount() {
        return urgentAmount;
    }

    public void setUrgentAmount(Integer urgentAmount) {
        this.urgentAmount = urgentAmount;
    }

    public Integer getCommonAmount() {
        return commonAmount;
    }

    public void setCommonAmount(Integer commonAmount) {
        this.commonAmount = commonAmount;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", organId=").append(organId);
        sb.append(", groupOrganId=").append(groupOrganId);
        sb.append(", houseId=").append(houseId);
        sb.append(", buildId=").append(buildId);
        sb.append(", sid=").append(sid);
        sb.append(", floorId=").append(floorId);
        sb.append(", cycleYm=").append(cycleYm);
        sb.append(", transAmount=").append(transAmount);
        sb.append(", dispatchAmount=").append(dispatchAmount);
        sb.append(", autonomousAmount=").append(autonomousAmount);
        sb.append(", specialAmount=").append(specialAmount);
        sb.append(", urgentAmount=").append(urgentAmount);
        sb.append(", commonAmount=").append(commonAmount);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}