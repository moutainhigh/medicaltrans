package com.segi.uhomecp.medicaltrans.base.dbsourcerule.model;

import java.util.Date;

import com.segi.uhomecp.common.model.AbstractModel;

public class DbSourceRule extends AbstractModel {
    private Integer idxId;

    private Integer groupOrganId;

    private String dbIdx;

    private String tableIdx;

    private String status;

    private Date createDate;

    private Date updateDate;

    private static final long serialVersionUID = 1L;

    public Integer getIdxId() {
        return idxId;
    }

    public void setIdxId(Integer idxId) {
        this.idxId = idxId;
    }

    public Integer getGroupOrganId() {
        return groupOrganId;
    }

    public void setGroupOrganId(Integer groupOrganId) {
        this.groupOrganId = groupOrganId;
    }

    public String getDbIdx() {
        return dbIdx;
    }

    public void setDbIdx(String dbIdx) {
        this.dbIdx = dbIdx == null ? null : dbIdx.trim();
    }

    public String getTableIdx() {
        return tableIdx;
    }

    public void setTableIdx(String tableIdx) {
        this.tableIdx = tableIdx == null ? null : tableIdx.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", idxId=").append(idxId);
        sb.append(", groupOrganId=").append(groupOrganId);
        sb.append(", dbIdx=").append(dbIdx);
        sb.append(", tableIdx=").append(tableIdx);
        sb.append(", status=").append(status);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}