package com.segi.uhomecp.medicaltrans.dto;

import segi.medicaltrans.mthistask.query.ExeUser;

public class ExeUserDto extends ExeUser {
     
	private static final long serialVersionUID = 1L;
	
	private Integer taskId;

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	
	

}
