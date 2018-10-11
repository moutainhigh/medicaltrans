package com.segi.uhomecp.medicaltrans.report.monthamount.transway.model;

import com.segi.uhomecp.common.model.AbstractModel;

public class TranswayStatistics extends AbstractModel {
    private Integer id;

    private Integer groupOrganId;

    private Integer organId;

    private Integer cycleYm;

    private String transWayCode;

    private Long transAmount;

    private Long transMinite;

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

    public Integer getCycleYm() {
        return cycleYm;
    }

    public void setCycleYm(Integer cycleYm) {
        this.cycleYm = cycleYm;
    }

    public String getTransWayCode() {
        return transWayCode;
    }

    public void setTransWayCode(String transWayCode) {
        this.transWayCode = transWayCode == null ? null : transWayCode.trim();
    }

    public Long getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(Long transAmount) {
        this.transAmount = transAmount;
    }

    public Long getTransMinite() {
        return transMinite;
    }

    public void setTransMinite(Long transMinite) {
        this.transMinite = transMinite;
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
        sb.append(", cycleYm=").append(cycleYm);
        sb.append(", transWayCode=").append(transWayCode);
        sb.append(", transAmount=").append(transAmount);
        sb.append(", transMinite=").append(transMinite);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}