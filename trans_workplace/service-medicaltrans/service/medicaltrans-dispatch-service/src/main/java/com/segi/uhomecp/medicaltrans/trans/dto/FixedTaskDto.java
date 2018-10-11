package com.segi.uhomecp.medicaltrans.trans.dto;

import java.util.List;

import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;

public class FixedTaskDto extends MtTask{

	
	/**  描述   (@author: wangxiong@segimail.com) */      
	private static final long serialVersionUID = -5075996217834313880L;

	private Integer userId;
	
	private Integer userOrganId;
	
	private Integer houseId; //完成的目的地
	
	private List<Integer> transactors;
	
	private List<Integer> routeList;
	
	private Integer exeUserId;//执行人
	
	private String sendContent;

	public List<Integer> getTransactors() {
		return transactors;
	}

	public void setTransactors(List<Integer> transactors) {
		this.transactors = transactors;
	}
	
	public List<Integer> getRouteList() {
		return routeList;
	}

	public void setRouteList(List<Integer> routeList) {
		this.routeList = routeList;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserOrganId() {
		return userOrganId;
	}

	public void setUserOrganId(Integer userOrganId) {
		this.userOrganId = userOrganId;
	}

	public Integer getHouseId() {
		return houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	public Integer getExeUserId() {
		return exeUserId;
	}

	public void setExeUserId(Integer exeUserId) {
		this.exeUserId = exeUserId;
	}

	public String getSendContent() {
		return sendContent;
	}

	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}
	
}