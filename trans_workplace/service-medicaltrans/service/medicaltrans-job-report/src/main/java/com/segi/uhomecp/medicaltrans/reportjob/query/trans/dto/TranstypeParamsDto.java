package com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto;

import java.io.Serializable;
import java.util.Date;

public class TranstypeParamsDto implements Serializable {
	
	/**  描述   (@author: yangyh@segimail.com) */      
	private static final long serialVersionUID = -9065451980604421096L;

	private Integer groupOrganId;
	private String organIdListStr;
	private Long startTime;
	private Long endTime;
	private String status;
	private Date excDate;

	public Integer getGroupOrganId() {
		return groupOrganId;
	}

	public void setGroupOrganId(Integer groupOrganId) {
		this.groupOrganId = groupOrganId;
	}
	
	public String getOrganIdListStr() {
		return organIdListStr;
	}
	
	public void setOrganIdListStr(String organIdListStr) {
		this.organIdListStr = organIdListStr;
	}
	
	public Long getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	
	public Long getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getExcDate() {
		return excDate;
	}

	public void setExcDate(Date excDate) {
		this.excDate = excDate;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", groupOrganId=").append(groupOrganId);
        sb.append(", organIdListStr=").append(organIdListStr);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", status=").append(status);
        sb.append(", excDate=").append(excDate);
        sb.append("]");
        return sb.toString();
	}
}
