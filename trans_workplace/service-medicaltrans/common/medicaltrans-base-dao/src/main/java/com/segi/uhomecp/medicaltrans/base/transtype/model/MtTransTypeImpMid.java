package com.segi.uhomecp.medicaltrans.base.transtype.model;

import java.util.Date;

import com.segi.uhomecp.common.model.AbstractModel;

public class MtTransTypeImpMid extends AbstractModel {
    private Integer itemId;

    private Integer batchId;

    private String transTypeParentCode;

    private String transTypeCode;

    private String transTypeName;

    private Integer standardMinite;

    private Integer warnMinite;

    private Integer timeOut;

    private String remark;

    private String oprGuide;

    private String statusCd;

    private Date exeDate;

    private Integer excelRow;

    private static final long serialVersionUID = 1L;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
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

    public Integer getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getOprGuide() {
        return oprGuide;
    }

    public void setOprGuide(String oprGuide) {
        this.oprGuide = oprGuide == null ? null : oprGuide.trim();
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd == null ? null : statusCd.trim();
    }

    public Date getExeDate() {
        return exeDate;
    }

    public void setExeDate(Date exeDate) {
        this.exeDate = exeDate;
    }

    public Integer getExcelRow() {
        return excelRow;
    }

    public void setExcelRow(Integer excelRow) {
        this.excelRow = excelRow;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", itemId=").append(itemId);
        sb.append(", batchId=").append(batchId);
        sb.append(", transTypeParentCode=").append(transTypeParentCode);
        sb.append(", transTypeCode=").append(transTypeCode);
        sb.append(", transTypeName=").append(transTypeName);
        sb.append(", standardMinite=").append(standardMinite);
        sb.append(", warnMinite=").append(warnMinite);
        sb.append(", timeOut=").append(timeOut);
        sb.append(", remark=").append(remark);
        sb.append(", oprGuide=").append(oprGuide);
        sb.append(", statusCd=").append(statusCd);
        sb.append(", exeDate=").append(exeDate);
        sb.append(", excelRow=").append(excelRow);
        sb.append("]");
        return sb.toString();
    }
}