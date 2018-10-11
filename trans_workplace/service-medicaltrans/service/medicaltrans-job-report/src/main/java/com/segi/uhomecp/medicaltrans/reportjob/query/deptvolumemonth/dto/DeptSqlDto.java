package com.segi.uhomecp.medicaltrans.reportjob.query.deptvolumemonth.dto;

import java.util.List;

public class DeptSqlDto {
	
	//科室idList
	private List<Integer> houseIdList;
	
	//项目idList
	private List<Integer> organIdList;
	
	//开始时间
	private Long startTime;
	
	//结束时间
	private Long endTime;
	
	//任务状态
	private List<Integer> taskTypeList;
	
	//统计截止时间
	private Long countEndTime;
	
	//月份
	private Integer cycleYm;
	
	/** 分表的后缀名 */
	private String tableIndex;

	public String getTableIndex() {
		return tableIndex;
	}

	public void setTableIndex(String tableIndex) {
		this.tableIndex = tableIndex;
	}

	public Integer getCycleYm() {
		return cycleYm;
	}

	public void setCycleYm(Integer cycleYm) {
		this.cycleYm = cycleYm;
	}

	public List<Integer> getHouseIdList() {
		return houseIdList;
	}

	public void setHouseIdList(List<Integer> houseIdList) {
		this.houseIdList = houseIdList;
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

	public List<Integer> getTaskTypeList() {
		return taskTypeList;
	}

	public void setTaskTypeList(List<Integer> taskTypeList) {
		this.taskTypeList = taskTypeList;
	}

	public Long getCountEndTime() {
		return countEndTime;
	}

	public void setCountEndTime(Long countEndTime) {
		this.countEndTime = countEndTime;
	}
}
