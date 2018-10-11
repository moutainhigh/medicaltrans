package com.segi.uhomecp.medicaltrans.dto;

public class ReportCommonDto {
	private Integer organId;
	private Integer cycleY;
	private Integer cycleYmBeg;
	private Integer cycleYmEnd;
	private String amountName;
	private Long amount;
	public Integer getOrganId() {
		return organId;
	}
	public void setOrganId(Integer organId) {
		this.organId = organId;
	}
	public Integer getCycleY() {
		return cycleY;
	}
	public void setCycleY(Integer cycleY) {
		this.cycleY = cycleY;
	}
	public Integer getCycleYmBeg() {
		return cycleYmBeg;
	}
	public void setCycleYmBeg(Integer cycleYmBeg) {
		this.cycleYmBeg = cycleYmBeg;
	}
	public Integer getCycleYmEnd() {
		return cycleYmEnd;
	}
	public void setCycleYmEnd(Integer cycleYmEnd) {
		this.cycleYmEnd = cycleYmEnd;
	}
	public String getAmountName() {
		return amountName;
	}
	public void setAmountName(String amountName) {
		this.amountName = amountName;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
}
