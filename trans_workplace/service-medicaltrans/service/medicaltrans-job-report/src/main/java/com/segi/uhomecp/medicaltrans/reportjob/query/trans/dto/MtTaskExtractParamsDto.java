package com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto;

import java.util.Date;
import java.util.List;

public class MtTaskExtractParamsDto {
	
	private Integer organId;
	
	private List<String> statusList;
	
	private Date beginTime;
	
	private Date endTime;
	
	private Long beginTimeLog;
	
	private Long endTimeLog;
	
	private Date updateDate;

	public Integer getOrganId() {
		return organId;
	}

	public void setOrganId(Integer organId) {
		this.organId = organId;
	}

	public List<String> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getBeginTimeLog() {
		return beginTimeLog;
	}

	public void setBeginTimeLog(Long beginTimeLog) {
		this.beginTimeLog = beginTimeLog;
	}

	public Long getEndTimeLog() {
		return endTimeLog;
	}

	public void setEndTimeLog(Long endTimeLog) {
		this.endTimeLog = endTimeLog;
	}
	
}
