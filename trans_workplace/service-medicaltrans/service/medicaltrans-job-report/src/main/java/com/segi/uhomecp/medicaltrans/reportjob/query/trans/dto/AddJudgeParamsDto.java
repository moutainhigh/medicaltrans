package com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto;


import java.util.Date;

public class AddJudgeParamsDto {
	
	private Date paramDate;
	
	private Date excTime;
	
	private Date excEndTime;
	
	private Date lastExcTime;

	public Date getParamDate() {
		return paramDate;
	}

	public void setParamDate(Date paramDate) {
		this.paramDate = paramDate;
	}

	public Date getExcTime() {
		return excTime;
	}

	public void setExcTime(Date excTime) {
		this.excTime = excTime;
	}

	public Date getExcEndTime() {
		return excEndTime;
	}

	public void setExcEndTime(Date excEndTime) {
		this.excEndTime = excEndTime;
	}

	public Date getLastExcTime() {
		return lastExcTime;
	}

	public void setLastExcTime(Date lastExcTime) {
		this.lastExcTime = lastExcTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddJudgeParamsDto [paramDate=");
		builder.append(paramDate);
		builder.append(", excTime=");
		builder.append(excTime);
		builder.append(", excEndTime=");
		builder.append(excEndTime);
		builder.append(", lastExcTime=");
		builder.append(lastExcTime);
		builder.append("]");
		return builder.toString();
	}

}
