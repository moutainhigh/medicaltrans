package com.segi.uhomecp.medicaltrans.base.hospuser.model;

import java.util.Date;

import com.segi.uhomecp.common.model.AbstractModel;

public class MtHospUserRel extends AbstractModel {
    private Integer mtUserRelId;

    private Integer mtUserId;

    private Integer organId;

    private Integer houseId;

    private String mtUserName;

    private String status;

    private String remark;

    private Date createDate;

    private Integer createBy;

    private Date updateDate;

    private Integer updateBy;

    private String tel;

    private static final long serialVersionUID = 1L;

    public Integer getMtUserRelId() {
        return mtUserRelId;
    }

    public void setMtUserRelId(Integer mtUserRelId) {
        this.mtUserRelId = mtUserRelId;
    }

    public Integer getMtUserId() {
        return mtUserId;
    }

    public void setMtUserId(Integer mtUserId) {
        this.mtUserId = mtUserId;
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

    public String getMtUserName() {
        return mtUserName;
    }

    public void setMtUserName(String mtUserName) {
        this.mtUserName = mtUserName == null ? null : mtUserName.trim();
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mtUserRelId=").append(mtUserRelId);
        sb.append(", mtUserId=").append(mtUserId);
        sb.append(", organId=").append(organId);
        sb.append(", houseId=").append(houseId);
        sb.append(", mtUserName=").append(mtUserName);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createDate=").append(createDate);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", tel=").append(tel);
        sb.append("]");
        return sb.toString();
    }
}