package com.segi.uhomecp.medicaltrans.report.organmonth.dto;

import java.io.Serializable;

/**
 * 运送概况对象
 * @author Administrator
 *
 */
public class TransProfileDto implements Serializable {
	
	private static final long serialVersionUID = -4933388668403795158L;

	private String transUserAmountAverage;
	
	private String transAmountTotal;
	
	private String transAmountAverage;
	
	private String instantTaskTimeAverage;
	
	private String timelyRatio;
	
	private String satisfactionRatio;

	public String getTransUserAmountAverage() {
		return transUserAmountAverage;
	}

	public void setTransUserAmountAverage(String transUserAmountAverage) {
		this.transUserAmountAverage = transUserAmountAverage;
	}

	public String getTransAmountTotal() {
		return transAmountTotal;
	}

	public void setTransAmountTotal(String transAmountTotal) {
		this.transAmountTotal = transAmountTotal;
	}

	public String getTransAmountAverage() {
		return transAmountAverage;
	}

	public void setTransAmountAverage(String transAmountAverage) {
		this.transAmountAverage = transAmountAverage;
	}

	public String getInstantTaskTimeAverage() {
		return instantTaskTimeAverage;
	}

	public void setInstantTaskTimeAverage(String instantTaskTimeAverage) {
		this.instantTaskTimeAverage = instantTaskTimeAverage;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TransProfileDto [transUserAmountAverage=");
		builder.append(transUserAmountAverage);
		builder.append(", transAmountTotal=");
		builder.append(transAmountTotal);
		builder.append(", transAmountAverage=");
		builder.append(transAmountAverage);
		builder.append(", instantTaskTimeAverage=");
		builder.append(instantTaskTimeAverage);
		builder.append(", timelyRatio=");
		builder.append(timelyRatio);
		builder.append(", satisfactionRatio=");
		builder.append(satisfactionRatio);
		builder.append("]");
		return builder.toString();
	}
	
	
}
