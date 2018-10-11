package com.segi.uhomecp.medicaltrans.report.organmonth.dto;

public class TaskTypeDto {
	private String dispatchTask;
	private Long dispatchTaskAmount;
	private String autonomousTask;
	private Long autonomousTaskAmount;
	private String fixedTask;
	private Long fixedTaskAmount;
	public String getDispatchTask() {
		return dispatchTask;
	}
	public void setDispatchTask(String dispatchTask) {
		this.dispatchTask = dispatchTask;
	}
	public Long getDispatchTaskAmount() {
		return dispatchTaskAmount;
	}
	public void setDispatchTaskAmount(Long dispatchTaskAmount) {
		this.dispatchTaskAmount = dispatchTaskAmount;
	}
	public String getAutonomousTask() {
		return autonomousTask;
	}
	public void setAutonomousTask(String autonomousTask) {
		this.autonomousTask = autonomousTask;
	}
	public Long getAutonomousTaskAmount() {
		return autonomousTaskAmount;
	}
	public void setAutonomousTaskAmount(Long autonomousTaskAmount) {
		this.autonomousTaskAmount = autonomousTaskAmount;
	}
	public String getFixedTask() {
		return fixedTask;
	}
	public void setFixedTask(String fixedTask) {
		this.fixedTask = fixedTask;
	}
	public Long getFixedTaskAmount() {
		return fixedTaskAmount;
	}
	public void setFixedTaskAmount(Long fixedTaskAmount) {
		this.fixedTaskAmount = fixedTaskAmount;
	}
}
