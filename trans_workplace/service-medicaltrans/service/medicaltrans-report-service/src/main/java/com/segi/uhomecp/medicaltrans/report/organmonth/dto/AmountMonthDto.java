package com.segi.uhomecp.medicaltrans.report.organmonth.dto;

import java.io.Serializable;

/**
 * 每月运送量趋势分析对象
 * @author Administrator
 *
 */
public class AmountMonthDto implements Serializable {
	
	private static final long serialVersionUID = -4933388668403795158L;

	private String cycleYm;
	
	//运送量
	private String transAmount;
	
	//及时率
	private String timelyRatio;
	
	//满意率
	private String satisfactionRatio;
	
	//运送员运送量
	private String transAmountAverage;
	
	//及时任务平均响应时间
	private String avgRespTime;

	public String getCycleYm() {
		return cycleYm;
	}

	public void setCycleYm(String cycleYm) {
		this.cycleYm = cycleYm;
	}

	public String getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(String transAmount) {
		this.transAmount = transAmount;
	}

	public String getTimelyRatio() {
		return timelyRatio;
	}

	public void setTimelyRatio(String timelyRatio) {
		this.timelyRatio = timelyRatio;
	}

	public String getSatisfactionRatio() {
		return satisfactionRatio;
	}

	public void setSatisfactionRatio(String satisfactionRatio) {
		this.satisfactionRatio = satisfactionRatio;
	}

	public String getTransAmountAverage() {
		return transAmountAverage;
	}

	public void setTransAmountAverage(String transAmountAverage) {
		this.transAmountAverage = transAmountAverage;
	}

	public String getAvgRespTime() {
		return avgRespTime;
	}

	public void setAvgRespTime(String avgRespTime) {
		this.avgRespTime = avgRespTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AmountMonthDto [cycleYm=");
		builder.append(cycleYm);
		builder.append(", transAmount=");
		builder.append(transAmount);
		builder.append(", timelyRatio=");
		builder.append(timelyRatio);
		builder.append(", satisfactionRatio=");
		builder.append(satisfactionRatio);
		builder.append(", transAmountAverage=");
		builder.append(transAmountAverage);
		builder.append(", avgRespTime=");
		builder.append(avgRespTime);
		builder.append("]");
		return builder.toString();
	}
		
}
