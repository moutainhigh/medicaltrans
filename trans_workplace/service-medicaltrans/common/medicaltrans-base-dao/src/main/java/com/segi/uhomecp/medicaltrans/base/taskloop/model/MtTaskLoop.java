package com.segi.uhomecp.medicaltrans.base.taskloop.model;

import java.util.Date;

import com.segi.uhomecp.common.model.AbstractModel;

public class MtTaskLoop extends AbstractModel {
    private Integer taskLoopId;

    private Integer organId;

    private String transTypeParentCode;

    private Integer transTypeId;

    private String taskName;

    private String transTools;

    private String loopType;

    private Integer taskBeginTime;

    private Integer taskEndTime;

    private String loopDays;

    private String loopWeeks;

    private String loopMonths;

    private Integer preGenerateMinute;

    private String status;

    private Date createDate;

    private Integer createBy;

    private Date updateDate;

    private Integer updateBy;

    private String taskCron;

    private String loseRemark;

    private Integer limitMinute;
    
    private Integer groupOrganId;

    private static final long serialVersionUID = 1L;

    public Integer getTaskLoopId() {
        return taskLoopId;
    }

    public void setTaskLoopId(Integer taskLoopId) {
        this.taskLoopId = taskLoopId;
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

    public Integer getTransTypeId() {
        return transTypeId;
    }

    public void setTransTypeId(Integer transTypeId) {
        this.transTypeId = transTypeId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getTransTools() {
        return transTools;
    }

    public void setTransTools(String transTools) {
        this.transTools = transTools == null ? null : transTools.trim();
    }

    public String getLoopType() {
        return loopType;
    }

    public void setLoopType(String loopType) {
        this.loopType = loopType == null ? null : loopType.trim();
    }

    public Integer getTaskBeginTime() {
        return taskBeginTime;
    }

    public void setTaskBeginTime(Integer taskBeginTime) {
        this.taskBeginTime = taskBeginTime;
    }

    public Integer getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(Integer taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public String getLoopDays() {
        return loopDays;
    }

    public void setLoopDays(String loopDays) {
        this.loopDays = loopDays == null ? null : loopDays.trim();
    }

    public String getLoopWeeks() {
        return loopWeeks;
    }

    public void setLoopWeeks(String loopWeeks) {
        this.loopWeeks = loopWeeks == null ? null : loopWeeks.trim();
    }

    public String getLoopMonths() {
        return loopMonths;
    }

    public void setLoopMonths(String loopMonths) {
        this.loopMonths = loopMonths == null ? null : loopMonths.trim();
    }

    public Integer getPreGenerateMinute() {
        return preGenerateMinute;
    }

    public void setPreGenerateMinute(Integer preGenerateMinute) {
        this.preGenerateMinute = preGenerateMinute;
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

    public String getTaskCron() {
        return taskCron;
    }

    public void setTaskCron(String taskCron) {
        this.taskCron = taskCron == null ? null : taskCron.trim();
    }

    public String getLoseRemark() {
        return loseRemark;
    }

    public void setLoseRemark(String loseRemark) {
        this.loseRemark = loseRemark == null ? null : loseRemark.trim();
    }

    public Integer getLimitMinute() {
        return limitMinute;
    }

    public void setLimitMinute(Integer limitMinute) {
        this.limitMinute = limitMinute;
    }

	public Integer getGroupOrganId() {
		return groupOrganId;
	}

	public void setGroupOrganId(Integer groupOrganId) {
		this.groupOrganId = groupOrganId;
	}
	
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", taskLoopId=").append(taskLoopId);
        sb.append(", organId=").append(organId);
        sb.append(", transTypeParentCode=").append(transTypeParentCode);
        sb.append(", transTypeId=").append(transTypeId);
        sb.append(", taskName=").append(taskName);
        sb.append(", transTools=").append(transTools);
        sb.append(", loopType=").append(loopType);
        sb.append(", taskBeginTime=").append(taskBeginTime);
        sb.append(", taskEndTime=").append(taskEndTime);
        sb.append(", loopDays=").append(loopDays);
        sb.append(", loopWeeks=").append(loopWeeks);
        sb.append(", loopMonths=").append(loopMonths);
        sb.append(", preGenerateMinute=").append(preGenerateMinute);
        sb.append(", status=").append(status);
        sb.append(", createDate=").append(createDate);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", taskCron=").append(taskCron);
        sb.append(", loseRemark=").append(loseRemark);
        sb.append(", limitMinute=").append(limitMinute);
        sb.append(", groupOrganId=").append(groupOrganId);
        sb.append("]");
        return sb.toString();
    }
}