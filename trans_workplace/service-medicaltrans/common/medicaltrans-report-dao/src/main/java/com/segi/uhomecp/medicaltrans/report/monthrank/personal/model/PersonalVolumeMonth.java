package com.segi.uhomecp.medicaltrans.report.monthrank.personal.model;

import com.segi.uhomecp.common.model.AbstractModel;

public class PersonalVolumeMonth  extends AbstractModel {
    private Integer id;

    private Integer groupOrganId;

    private Integer organId;

    private Integer userId;

    private Integer sergroupId;

    private Integer cycleYm;

    private Integer transAmount;

    private Integer dispatchAmount;

    private Integer dispatchSatisfactionAmount;

    private Integer dispatchTimelyAmount;

    private Integer autonomousAmount;

    private Integer autonomousSatisfactionAmount;

    private Integer autonomousTimelyAmount;

    private Integer fixedAmount;

    private Integer fixedSatisfactionAmount;

    private Integer fixedTimelyAmount;

    private Long createDate;

    private Long updateDate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupOrganId() {
        return groupOrganId;
    }

    public void setGroupOrganId(Integer groupOrganId) {
        this.groupOrganId = groupOrganId;
    }

    public Integer getOrganId() {
        return organId;
    }

    public void setOrganId(Integer organId) {
        this.organId = organId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSergroupId() {
        return sergroupId;
    }

    public void setSergroupId(Integer sergroupId) {
        this.sergroupId = sergroupId;
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

    public Integer getDispatchSatisfactionAmount() {
        return dispatchSatisfactionAmount;
    }

    public void setDispatchSatisfactionAmount(Integer dispatchSatisfactionAmount) {
        this.dispatchSatisfactionAmount = dispatchSatisfactionAmount;
    }

    public Integer getDispatchTimelyAmount() {
        return dispatchTimelyAmount;
    }

    public void setDispatchTimelyAmount(Integer dispatchTimelyAmount) {
        this.dispatchTimelyAmount = dispatchTimelyAmount;
    }

    public Integer getAutonomousAmount() {
        return autonomousAmount;
    }

    public void setAutonomousAmount(Integer autonomousAmount) {
        this.autonomousAmount = autonomousAmount;
    }

    public Integer getAutonomousSatisfactionAmount() {
        return autonomousSatisfactionAmount;
    }

    public void setAutonomousSatisfactionAmount(Integer autonomousSatisfactionAmount) {
        this.autonomousSatisfactionAmount = autonomousSatisfactionAmount;
    }

    public Integer getAutonomousTimelyAmount() {
        return autonomousTimelyAmount;
    }

    public void setAutonomousTimelyAmount(Integer autonomousTimelyAmount) {
        this.autonomousTimelyAmount = autonomousTimelyAmount;
    }

    public Integer getFixedAmount() {
        return fixedAmount;
    }

    public void setFixedAmount(Integer fixedAmount) {
        this.fixedAmount = fixedAmount;
    }

    public Integer getFixedSatisfactionAmount() {
        return fixedSatisfactionAmount;
    }

    public void setFixedSatisfactionAmount(Integer fixedSatisfactionAmount) {
        this.fixedSatisfactionAmount = fixedSatisfactionAmount;
    }

    public Integer getFixedTimelyAmount() {
        return fixedTimelyAmount;
    }

    public void setFixedTimelyAmount(Integer fixedTimelyAmount) {
        this.fixedTimelyAmount = fixedTimelyAmount;
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
        sb.append(", groupOrganId=").append(groupOrganId);
        sb.append(", organId=").append(organId);
        sb.append(", userId=").append(userId);
        sb.append(", sergroupId=").append(sergroupId);
        sb.append(", cycleYm=").append(cycleYm);
        sb.append(", transAmount=").append(transAmount);
        sb.append(", dispatchAmount=").append(dispatchAmount);
        sb.append(", dispatchSatisfactionAmount=").append(dispatchSatisfactionAmount);
        sb.append(", dispatchTimelyAmount=").append(dispatchTimelyAmount);
        sb.append(", autonomousAmount=").append(autonomousAmount);
        sb.append(", autonomousSatisfactionAmount=").append(autonomousSatisfactionAmount);
        sb.append(", autonomousTimelyAmount=").append(autonomousTimelyAmount);
        sb.append(", fixedAmount=").append(fixedAmount);
        sb.append(", fixedSatisfactionAmount=").append(fixedSatisfactionAmount);
        sb.append(", fixedTimelyAmount=").append(fixedTimelyAmount);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}