package com.segi.uhomecp.medicaltrans.reportjob.query.trans.model;

import java.util.Date;

import com.segi.uhomecp.common.model.AbstractModel;

public class MtTaskExtract extends AbstractModel {
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

    private Short robTaskCount;

    private String dataSource;

    private Date sendTime;

    private Date receiveTime;

    private Date exeBeginTime;

    private Date exeEndTime;

    private Integer exeEndUserId;

    private String status;

    private Long createDate;

    private Integer createBy;

    private Date updateDate;

    private Integer updateBy;

    private Short evaluate;

    private String evaluateContent;

    private Integer version;

    private Integer dispatchUserId;

    private Integer taskLoopId;
    
    private Integer groupOrganId;

    private Byte taskHour;

    private Integer timeConsuming;

    private String isTimeOut;
    
    private Integer respTime;
    
    private Integer taskTime;

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

    public Short getRobTaskCount() {
        return robTaskCount;
    }

    public void setRobTaskCount(Short robTaskCount) {
        this.robTaskCount = robTaskCount;
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

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
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

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent == null ? null : evaluateContent.trim();
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
		this.isTimeOut = isTimeOut;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MtTaskExtract [taskId=");
		builder.append(taskId);
		builder.append(", organId=");
		builder.append(organId);
		builder.append(", transTypeParentCode=");
		builder.append(transTypeParentCode);
		builder.append(", transTypeId=");
		builder.append(transTypeId);
		builder.append(", urgency=");
		builder.append(urgency);
		builder.append(", taskType=");
		builder.append(taskType);
		builder.append(", sourceHouseId=");
		builder.append(sourceHouseId);
		builder.append(", fromHouseId=");
		builder.append(fromHouseId);
		builder.append(", toHouseId=");
		builder.append(toHouseId);
		builder.append(", transTools=");
		builder.append(transTools);
		builder.append(", transPersonCount=");
		builder.append(transPersonCount);
		builder.append(", beginTime=");
		builder.append(beginTime);
		builder.append(", limitMinute=");
		builder.append(limitMinute);
		builder.append(", resType=");
		builder.append(resType);
		builder.append(", robTaskCount=");
		builder.append(robTaskCount);
		builder.append(", dataSource=");
		builder.append(dataSource);
		builder.append(", sendTime=");
		builder.append(sendTime);
		builder.append(", receiveTime=");
		builder.append(receiveTime);
		builder.append(", exeBeginTime=");
		builder.append(exeBeginTime);
		builder.append(", exeEndTime=");
		builder.append(exeEndTime);
		builder.append(", exeEndUserId=");
		builder.append(exeEndUserId);
		builder.append(", status=");
		builder.append(status);
		builder.append(", createDate=");
		builder.append(createDate);
		builder.append(", createBy=");
		builder.append(createBy);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append(", updateBy=");
		builder.append(updateBy);
		builder.append(", evaluate=");
		builder.append(evaluate);
		builder.append(", evaluateContent=");
		builder.append(evaluateContent);
		builder.append(", version=");
		builder.append(version);
		builder.append(", dispatchUserId=");
		builder.append(dispatchUserId);
		builder.append(", taskLoopId=");
		builder.append(taskLoopId);
		builder.append(", groupOrganId=");
		builder.append(groupOrganId);
		builder.append(", taskHour=");
		builder.append(taskHour);
		builder.append(", timeConsuming=");
		builder.append(timeConsuming);
		builder.append(", isTimeOut=");
		builder.append(isTimeOut);
		builder.append(", respTime=");
		builder.append(respTime);
		builder.append(", taskTime=");
		builder.append(taskTime);
		builder.append("]");
		return builder.toString();
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		MtTaskExtract other = (MtTaskExtract) obj;
		if (taskId == null) {
			if (other.taskId != null) {
				return false;
			}
		} else if (!taskId.equals(other.taskId)) {
			return false;
		}
		return true;
	}
}
