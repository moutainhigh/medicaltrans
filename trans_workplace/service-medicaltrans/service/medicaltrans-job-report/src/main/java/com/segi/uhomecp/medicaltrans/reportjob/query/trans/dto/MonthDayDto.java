package com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto;

import java.util.List;

public class MonthDayDto {
	
	private Integer groupOrganId;
	
	private Integer organId;
	
	private String status;
	
	private List<Integer> yearMonthDayList;
	
	private String tableIndex;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Integer> getYearMonthDayList() {
		return yearMonthDayList;
	}

	public void setYearMonthDayList(List<Integer> yearMonthDayList) {
		this.yearMonthDayList = yearMonthDayList;
	}

	public String getTableIndex() {
		return tableIndex;
	}

	public void setTableIndex(String tableIndex) {
		this.tableIndex = tableIndex;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MonthDayDto [groupOrganId=");
		builder.append(groupOrganId);
		builder.append(", organId=");
		builder.append(organId);
		builder.append(", status=");
		builder.append(status);
		builder.append(", yearMonthDayList=");
		builder.append(yearMonthDayList);
		builder.append(", tableIndex=");
		builder.append(tableIndex);
		builder.append("]");
		return builder.toString();
	}


}
