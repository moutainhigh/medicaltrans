package com.segi.uhomecp.medicaltrans.base.hospuser.model;

import java.util.Date;

import com.segi.uhomecp.common.model.AbstractModel;

public class MtHospUser extends AbstractModel {
    private Integer mtUserId;

    private String tel;

    private String loginPwd;

    private String wechatNo;

    private Date updateDate;

    private Integer updateBy;

    private static final long serialVersionUID = 1L;

    public Integer getMtUserId() {
        return mtUserId;
    }

    public void setMtUserId(Integer mtUserId) {
        this.mtUserId = mtUserId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd == null ? null : loginPwd.trim();
    }

    public String getWechatNo() {
        return wechatNo;
    }

    public void setWechatNo(String wechatNo) {
        this.wechatNo = wechatNo == null ? null : wechatNo.trim();
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
        sb.append(", mtUserId=").append(mtUserId);
        sb.append(", tel=").append(tel);
        sb.append(", loginPwd=").append(loginPwd);
        sb.append(", wechatNo=").append(wechatNo);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append("]");
        return sb.toString();
    }
}