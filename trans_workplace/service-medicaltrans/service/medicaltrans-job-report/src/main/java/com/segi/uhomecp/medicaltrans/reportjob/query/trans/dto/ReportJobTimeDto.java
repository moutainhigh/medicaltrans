package com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.segi.uhomecp.common.model.AbstractModel;

public class ReportJobTimeDto extends AbstractModel {

	
	/**  描述   (@author: dengdong@segimail.com) */      
	    
	private static final long serialVersionUID = -2895795852824987663L;
	//年月
	private String exeYearMonth;
	
	//开始时间
	private Long startTime;
	
	//结束时间
	private Long endTime;
	
	//月份结束时间
	private Long monthEndTime;
	
	//一级物业id
	private Integer groupOrganId;
	
	//项目idList
	private List<Integer> organIdList;
	
	private Date excDate;
	
	//项目对应月是否有工单的天数（key是项目id，value是001001..）
	private Map<Integer, String> organWorkMap;
	
	public String getExeYearMonth() {
		return exeYearMonth;
	}

	public void setExeYearMonth(String exeYearMonth) {
		this.exeYearMonth = exeYearMonth;
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

	public Long getMonthEndTime() {
		return monthEndTime;
	}

	public void setMonthEndTime(Long monthEndTime) {
		this.monthEndTime = monthEndTime;
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

	public Map<Integer, String> getOrganWorkMap() {
		return organWorkMap;
	}

	public void setOrganWorkMap(Map<Integer, String> organWorkMap) {
		this.organWorkMap = organWorkMap;
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
		builder.append("ReportJobTimeDto [exeYearMonth=");
		builder.append(exeYearMonth);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", monthEndTime=");
		builder.append(monthEndTime);
		builder.append(", excDate=");
		builder.append(excDate);
		builder.append(", groupOrganId=");
		builder.append(groupOrganId);
		builder.append(", organIdList=");
		builder.append(organIdList);
		builder.append(", organWorkMap=");
		builder.append(organWorkMap);
		builder.append("]");
		return builder.toString();
	}

}
