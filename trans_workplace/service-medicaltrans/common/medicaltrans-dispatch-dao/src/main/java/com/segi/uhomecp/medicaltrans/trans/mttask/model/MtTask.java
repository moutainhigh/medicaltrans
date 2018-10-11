package com.segi.uhomecp.medicaltrans.trans.mttask.model;

import java.util.Date;

import com.segi.uhomecp.common.model.AbstractModel;

public class MtTask extends AbstractModel {
    private Integer taskId;

    private Integer organId;

    private String transTypeParentCode;

    private Integer transTypeId;

    private String urgency;

    private String taskType;

    private Integer sourceHouseId;

    private Integer fromHouseId;

    private Integer toHouseId;

    private String transTools;

    private Short transPersonCount;

    private Long beginTime;

    private Integer limitMinute;

    private String resType;

    private String dataSource;

    private Date sendTime;

    private Date exeBeginTime;

    private Date exeEndTime;

    private Integer exeEndUserId;

    private String status;

    private Long createDate;

    private Integer createBy;

    private Date updateDate;

    private Integer updateBy;

    private Short evaluate;

    private Integer version;

    private Integer dispatchUserId;

    private Integer taskLoopId;

    private Integer groupOrganId;

    private Byte taskHour;

    private Integer timeConsuming;

    private String isTimeOut;

    private Integer respTime;

    private Integer taskTime;

    private Long endTime;

    private static final long serialVersionUID = 1L;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
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

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency == null ? null : urgency.trim();
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType == null ? null : taskType.trim();
    }

    public Integer getSourceHouseId() {
        return sourceHouseId;
    }

    public void setSourceHouseId(Integer sourceHouseId) {
        this.sourceHouseId = sourceHouseId;
    }

    public Integer getFromHouseId() {
        return fromHouseId;
    }

    public void setFromHouseId(Integer fromHouseId) {
        this.fromHouseId = fromHouseId;
    }

    public Integer getToHouseId() {
        return toHouseId;
    }

    public void setToHouseId(Integer toHouseId) {
        this.toHouseId = toHouseId;
    }

    public String getTransTools() {
        return transTools;
    }

    public void setTransTools(String transTools) {
        this.transTools = transTools == null ? null : transTools.trim();
    }

    public Short getTransPersonCount() {
        return transPersonCount;
    }

    public void setTransPersonCount(Short transPersonCount) {
        this.transPersonCount = transPersonCount;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getLimitMinute() {
        return limitMinute;
    }

    public void setLimitMinute(Integer limitMinute) {
        this.limitMinute = limitMinute;
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType == null ? null : resType.trim();
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource == null ? null : dataSource.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getExeBeginTime() {
        return exeBeginTime;
    }

    public void setExeBeginTime(Date exeBeginTime) {
        this.exeBeginTime = exeBeginTime;
    }

    public Date getExeEndTime() {
        return exeEndTime;
    }

    public void setExeEndTime(Date exeEndTime) {
        this.exeEndTime = exeEndTime;
    }

    public Integer getExeEndUserId() {
        return exeEndUserId;
    }

    public void setExeEndUserId(Integer exeEndUserId) {
        this.exeEndUserId = exeEndUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
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

    public Short getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Short evaluate) {
        this.evaluate = evaluate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDispatchUserId() {
        return dispatchUserId;
    }

    public void setDispatchUserId(Integer dispatchUserId) {
        this.dispatchUserId = dispatchUserId;
    }

    public Integer getTaskLoopId() {
        return taskLoopId;
    }

    public void setTaskLoopId(Integer taskLoopId) {
        this.taskLoopId = taskLoopId;
    }

    public Integer getGroupOrganId() {
        return groupOrganId;
    }

    public void setGroupOrganId(Integer groupOrganId) {
        this.groupOrganId = groupOrganId;
    }

    public Byte getTaskHour() {
        return taskHour;
    }

    public void setTaskHour(Byte taskHour) {
        this.taskHour = taskHour;
    }

    public Integer getTimeConsuming() {
        return timeConsuming;
    }

    public void setTimeConsuming(Integer timeConsuming) {
        this.timeConsuming = timeConsuming;
    }

    public String getIsTimeOut() {
        return isTimeOut;
    }

    public void setIsTimeOut(String isTimeOut) {
        this.isTimeOut = isTimeOut == null ? null : isTimeOut.trim();
    }

    public Integer getRespTime() {
        return respTime;
    }

    public void setRespTime(Integer respTime) {
        this.respTime = respTime;
    }

    public Integer getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Integer taskTime) {
        this.taskTime = taskTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", taskId=").append(taskId);
        sb.append(", organId=").append(organId);
        sb.append(", transTypeParentCode=").append(transTypeParentCode);
        sb.append(", transTypeId=").append(transTypeId);
        sb.append(", urgency=").append(urgency);
        sb.append(", taskType=").append(taskType);
        sb.append(", sourceHouseId=").append(sourceHouseId);
        sb.append(", fromHouseId=").append(fromHouseId);
        sb.append(", toHouseId=").append(toHouseId);
        sb.append(", transTools=").append(transTools);
        sb.append(", transPersonCount=").append(transPersonCount);
        sb.append(", beginTime=").append(beginTime);
        sb.append(", limitMinute=").append(limitMinute);
        sb.append(", resType=").append(resType);
        sb.append(", dataSource=").append(dataSource);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", exeBeginTime=").append(exeBeginTime);
        sb.append(", exeEndTime=").append(exeEndTime);
        sb.append(", exeEndUserId=").append(exeEndUserId);
        sb.append(", status=").append(status);
        sb.append(", createDate=").append(createDate);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", evaluate=").append(evaluate);
        sb.append(", version=").append(version);
        sb.append(", dispatchUserId=").append(dispatchUserId);
        sb.append(", taskLoopId=").append(taskLoopId);
        sb.append(", groupOrganId=").append(groupOrganId);
        sb.append(", taskHour=").append(taskHour);
        sb.append(", timeConsuming=").append(timeConsuming);
        sb.append(", isTimeOut=").append(isTimeOut);
        sb.append(", respTime=").append(respTime);
        sb.append(", taskTime=").append(taskTime);
        sb.append(", endTime=").append(endTime);
        sb.append("]");
        return sb.toString();
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MtTask other = (MtTask) obj;
		if (taskId == null) {
			if (other.taskId != null)
				return false;
		} else if (!taskId.equals(other.taskId))
			return false;
		return true;
	}
    
}