package com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto;

import java.util.Date;
import java.util.List;

public class OrganMonthEntryParamDto {
	
	private String status;
	
	private String disTask;
	
	private String selfTask;
	
	private String loopTask;
	
	private String ios;
	
	private String padIos;
	
	private String android;
	
	private String padandroid;
	
	private String web;
	
	private String wechat;
	
	private Integer groupOrganId;
	
	//项目idList
	private List<Integer> organIdList;;

	private Long startTime;
	
	private Long endTime;
	
	private String tableIndex;
	
	private Date excDate;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDisTask() {
		return disTask;
	}

	public void setDisTask(String disTask) {
		this.disTask = disTask;
	}

	public String getSelfTask() {
		return selfTask;
	}

	public void setSelfTask(String selfTask) {
		this.selfTask = selfTask;
	}

	public String getLoopTask() {
		return loopTask;
	}

	public void setLoopTask(String loopTask) {
		this.loopTask = loopTask;
	}

	public String getIos() {
		return ios;
	}

	public void setIos(String ios) {
		this.ios = ios;
	}

	public String getPadIos() {
		return padIos;
	}

	public void setPadIos(String padIos) {
		this.padIos = padIos;
	}

	public String getAndroid() {
		return android;
	}

	public void setAndroid(String android) {
		this.android = android;
	}

	public String getPadandroid() {
		return padandroid;
	}

	public void setPadandroid(String padandroid) {
		this.padandroid = padandroid;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public Integer getGroupOrganId() {
		return groupOrganId;
	}

	public void setGroupOrganId(Integer groupOrganId) {
		this.groupOrganId = groupOrganId;
	}

	public List<Integer> getOrganIdList() {
		return organIdList;
	}

	public void setOrganIdList(List<Integer> organIdList) {
		this.organIdList = organIdList;
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
	
	public String getTableIndex() {
		return tableIndex;
	}

	public void setTableIndex(String tableIndex) {
		this.tableIndex = tableIndex;
	}
	
	public Date getExcDate() {
		return excDate;
	}

	public void setExcDate(Date excDate) {
		this.excDate = excDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrganMonthEntryParamDto [status=");
		builder.append(status);
		builder.append(", disTask=");
		builder.append(disTask);
		builder.append(", selfTask=");
		builder.append(selfTask);
		builder.append(", loopTask=");
		builder.append(loopTask);
		builder.append(", ios=");
		builder.append(ios);
		builder.append(", padIos=");
		builder.append(padIos);
		builder.append(", android=");
		builder.append(android);
		builder.append(", padandroid=");
		builder.append(padandroid);
		builder.append(", web=");
		builder.append(web);
		builder.append(", wechat=");
		builder.append(wechat);
		builder.append(", groupOrganId=");
		builder.append(groupOrganId);
		builder.append(", organIdList=");
		builder.append(organIdList);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", tableIndex=");
		builder.append(tableIndex);
		builder.append("]");
		return builder.toString();
	}

	
	
	
	
	
}
