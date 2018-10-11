package com.segi.uhomecp.medicaltrans.reportjob.report.organmonth.dto;

import java.io.Serializable;

/**
 * 每月运送量趋势分析对象
 * @author Administrator
 *
 */
public class AmountMonthUserDto implements Serializable {
	
	private static final long serialVersionUID = -4933388668403795158L;

	//一级物业id
	private Integer groupOrganId;
	
	//项目id
	private Integer organId;
	
	//执行年月
	private Integer exeYearMonth;
	
	//人数
	private Integer userAmount;

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

	public Integer getExeYearMonth() {
		return exeYearMonth;
	}

	public void setExeYearMonth(Integer exeYearMonth) {
		this.exeYearMonth = exeYearMonth;
	}

	public Integer getUserAmount() {
		return userAmount;
	}

	public void setUserAmount(Integer userAmount) {
		this.userAmount = userAmount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AmountMonthUserDto [groupOrganId=");
		builder.append(groupOrganId);
		builder.append(", organId=");
		builder.append(organId);
		builder.append(", exeYearMonth=");
		builder.append(exeYearMonth);
		builder.append(", userAmount=");
		builder.append(userAmount);
		builder.append("]");
		return builder.toString();
	}

	
	
}
