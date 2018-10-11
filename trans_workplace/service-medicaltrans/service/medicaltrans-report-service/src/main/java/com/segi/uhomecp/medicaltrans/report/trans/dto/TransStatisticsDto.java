package com.segi.uhomecp.medicaltrans.report.trans.dto;

import java.io.Serializable;

public class TransStatisticsDto implements Serializable {
	
	/**  描述   (@author: yangyh@segimail.com) */      
	private static final long serialVersionUID = 292260716767267010L;
	
	private String timeName;
	private String time;
	private String amountName;
	private String amount;
	public String getTimeName() {
		return timeName;
	}
	public void setTimeName(String timeName) {
		this.timeName = timeName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAmountName() {
		return amountName;
	}
	public void setAmountName(String amountName) {
		this.amountName = amountName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
}
