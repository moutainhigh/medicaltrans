package com.segi.uhomecp.medicaltrans.base.common.model;

import java.util.Date;

import com.segi.uhomecp.common.model.AbstractModel;

public class MtImpBatchMsg extends AbstractModel {
    private Integer batchId;

    private Integer groupOrganId;

    private Integer organId;

    private String impType;

    private String fileName;

    private String statusCd;

    private Date exeDate;

    private Integer importCount;

    private String impRemark;

    private Date updateDate;

    private Integer updateBy;

    private Integer createBy;

    private Date createDate;

    private String attr1;

    private String attr2;

    private String attr3;

    private String attr4;

    private String attr5;

    private static final long serialVersionUID = 1L;

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
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

    public String getImpType() {
        return impType;
    }

    public void setImpType(String impType) {
        this.impType = impType == null ? null : impType.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
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

    public Integer getImportCount() {
        return importCount;
    }

    public void setImportCount(Integer importCount) {
        this.importCount = importCount;
    }

    public String getImpRemark() {
        return impRemark;
    }

    public void setImpRemark(String impRemark) {
        this.impRemark = impRemark == null ? null : impRemark.trim();
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

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1 == null ? null : attr1.trim();
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2 == null ? null : attr2.trim();
    }

    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3 == null ? null : attr3.trim();
    }

    public String getAttr4() {
        return attr4;
    }

    public void setAttr4(String attr4) {
        this.attr4 = attr4 == null ? null : attr4.trim();
    }

    public String getAttr5() {
        return attr5;
    }

    public void setAttr5(String attr5) {
        this.attr5 = attr5 == null ? null : attr5.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", batchId=").append(batchId);
        sb.append(", groupOrganId=").append(groupOrganId);
        sb.append(", organId=").append(organId);
        sb.append(", impType=").append(impType);
        sb.append(", fileName=").append(fileName);
        sb.append(", statusCd=").append(statusCd);
        sb.append(", exeDate=").append(exeDate);
        sb.append(", importCount=").append(importCount);
        sb.append(", impRemark=").append(impRemark);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", createBy=").append(createBy);
        sb.append(", createDate=").append(createDate);
        sb.append(", attr1=").append(attr1);
        sb.append(", attr2=").append(attr2);
        sb.append(", attr3=").append(attr3);
        sb.append(", attr4=").append(attr4);
        sb.append(", attr5=").append(attr5);
        sb.append("]");
        return sb.toString();
    }
}