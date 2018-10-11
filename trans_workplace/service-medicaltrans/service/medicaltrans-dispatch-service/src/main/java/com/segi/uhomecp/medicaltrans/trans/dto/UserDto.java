package com.segi.uhomecp.medicaltrans.trans.dto;

import segiwh.common.User;

public class UserDto extends User{

	/**  描述   (@author: zhangyang@segimail.com) */      
	private static final long serialVersionUID = 1L;
	
	private Integer taskId;

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	
	

}
