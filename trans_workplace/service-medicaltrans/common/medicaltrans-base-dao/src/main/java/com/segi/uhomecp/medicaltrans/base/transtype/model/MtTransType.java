package com.segi.uhomecp.medicaltrans.base.transtype.model;

import java.util.Date;

import com.segi.uhomecp.common.model.AbstractModel;

public class MtTransType extends AbstractModel {
    private Integer transTypeId;

    private Integer groupOrganId;

    private Integer organId;

    private String transTypeParentCode;

    private String transTypeCode;

    private String transTypeName;

    private Integer standardMinite;

    private Integer warnMinite;

    private String status;

    private String remark;

    private Date createDate;

    private Integer createBy;

    private Date updateDate;

    private Integer updateBy;

    private Integer timeOut;

    private String oprGuide;

    private static final long serialVersionUID = 1L;

    public Integer getTransTypeId() {
        return transTypeId;
    }

    public void setTransTypeId(Integer transTypeId) {
        this.transTypeId = transTypeId;
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

    public String getTransTypeParentCode() {
        return transTypeParentCode;
    }

    public void setTransTypeParentCode(String transTypeParentCode) {
        this.transTypeParentCode = transTypeParentCode == null ? null : transTypeParentCode.trim();
    }

    public String getTransTypeCode() {
        return transTypeCode;
    }

    public void setTransTypeCode(String transTypeCode) {
        this.transTypeCode = transTypeCode == null ? null : transTypeCode.trim();
    }

    public String getTransTypeName() {
        return transTypeName;
    }

    public void setTransTypeName(String transTypeName) {
        this.transTypeName = transTypeName == null ? null : transTypeName.trim();
    }

    public Integer getStandardMinite() {
        return standardMinite;
    }

    public void setStandardMinite(Integer standardMinite) {
        this.standardMinite = standardMinite;
    }

    public Integer getWarnMinite() {
        return warnMinite;
    }

    public void setWarnMinite(Integer warnMinite) {
        this.warnMinite = warnMinite;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
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

    public Integer getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }

    public String getOprGuide() {
        return oprGuide;
    }

    public void setOprGuide(String oprGuide) {
        this.oprGuide = oprGuide == null ? null : oprGuide;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", transTypeId=").append(transTypeId);
        sb.append(", groupOrganId=").append(groupOrganId);
        sb.append(", organId=").append(organId);
        sb.append(", transTypeParentCode=").append(transTypeParentCode);
        sb.append(", transTypeCode=").append(transTypeCode);
        sb.append(", transTypeName=").append(transTypeName);
        sb.append(", standardMinite=").append(standardMinite);
        sb.append(", warnMinite=").append(warnMinite);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createDate=").append(createDate);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", timeOut=").append(timeOut);
        sb.append(", oprGuide=").append(oprGuide);
        sb.append("]");
        return sb.toString();
    }
}