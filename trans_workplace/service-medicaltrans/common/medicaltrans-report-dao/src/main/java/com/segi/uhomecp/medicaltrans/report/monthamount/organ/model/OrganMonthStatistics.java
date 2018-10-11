package com.segi.uhomecp.medicaltrans.report.monthamount.organ.model;

import com.segi.uhomecp.common.model.AbstractModel;

public class OrganMonthStatistics extends AbstractModel {
    private Integer id;

    private Integer groupOrganId;

    private Integer organId;

    private Integer cycleYm;

    private Integer transAmount;

    private Integer transUserAmount;

    private Integer dispatchAmount;

    private Integer autonomousAmount;

    private Integer fixedAmount;

    private Long transInstantTime;

    private Long transTime;

    private Integer timelyAmount;

    private Integer satisfactionAmount;

    private Long createDate;

    private Long updateDate;

    private Integer webDatasource;

    private Integer wechatDatasource;

    private Integer padDatasource;

    private Integer appDatasource;

    private String istaskday;

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

    public Integer getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(Integer transAmount) {
        this.transAmount = transAmount;
    }

    public Integer getTransUserAmount() {
        return transUserAmount;
    }

    public void setTransUserAmount(Integer transUserAmount) {
        this.transUserAmount = transUserAmount;
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

    public Integer getFixedAmount() {
        return fixedAmount;
    }

    public void setFixedAmount(Integer fixedAmount) {
        this.fixedAmount = fixedAmount;
    }

    public Long getTransInstantTime() {
        return transInstantTime;
    }

    public void setTransInstantTime(Long transInstantTime) {
        this.transInstantTime = transInstantTime;
    }

    public Long getTransTime() {
        return transTime;
    }

    public void setTransTime(Long transTime) {
        this.transTime = transTime;
    }

    public Integer getTimelyAmount() {
        return timelyAmount;
    }

    public void setTimelyAmount(Integer timelyAmount) {
        this.timelyAmount = timelyAmount;
    }

    public Integer getSatisfactionAmount() {
        return satisfactionAmount;
    }

    public void setSatisfactionAmount(Integer satisfactionAmount) {
        this.satisfactionAmount = satisfactionAmount;
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

    public Integer getWebDatasource() {
        return webDatasource;
    }

    public void setWebDatasource(Integer webDatasource) {
        this.webDatasource = webDatasource;
    }

    public Integer getWechatDatasource() {
        return wechatDatasource;
    }

    public void setWechatDatasource(Integer wechatDatasource) {
        this.wechatDatasource = wechatDatasource;
    }

    public Integer getPadDatasource() {
        return padDatasource;
    }

    public void setPadDatasource(Integer padDatasource) {
        this.padDatasource = padDatasource;
    }

    public Integer getAppDatasource() {
        return appDatasource;
    }

    public void setAppDatasource(Integer appDatasource) {
        this.appDatasource = appDatasource;
    }

    public String getIstaskday() {
        return istaskday;
    }

    public void setIstaskday(String istaskday) {
        this.istaskday = istaskday == null ? null : istaskday.trim();
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
        sb.append(", transAmount=").append(transAmount);
        sb.append(", transUserAmount=").append(transUserAmount);
        sb.append(", dispatchAmount=").append(dispatchAmount);
        sb.append(", autonomousAmount=").append(autonomousAmount);
        sb.append(", fixedAmount=").append(fixedAmount);
        sb.append(", transInstantTime=").append(transInstantTime);
        sb.append(", transTime=").append(transTime);
        sb.append(", timelyAmount=").append(timelyAmount);
        sb.append(", satisfactionAmount=").append(satisfactionAmount);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", webDatasource=").append(webDatasource);
        sb.append(", wechatDatasource=").append(wechatDatasource);
        sb.append(", padDatasource=").append(padDatasource);
        sb.append(", appDatasource=").append(appDatasource);
        sb.append(", istaskday=").append(istaskday);
        sb.append("]");
        return sb.toString();
    }
}