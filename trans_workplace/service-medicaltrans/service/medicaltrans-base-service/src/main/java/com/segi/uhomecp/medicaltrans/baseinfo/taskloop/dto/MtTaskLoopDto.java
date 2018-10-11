package com.segi.uhomecp.medicaltrans.baseinfo.taskloop.dto;

import java.util.List;

import segiwh.common.User;

import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoop;

public class MtTaskLoopDto extends MtTaskLoop {

	/** 描述 (@author: yangyh@segimail.com) */
	private static final long serialVersionUID = -8652675284247310364L;

	private String organName;

	private String transTypeParentName;

	private String transTypeName;

	private String transToolsName;

	private String houseIds;

	private String houseNames;
	
	private List<HouseDto> houseList;
	
	private String statusName;

	private String userIds;

	private String userNames;

	private List<User> userList;

	private Integer pageNo;

	private Integer pageLength;

	private String taskBeginTimeStr;

	private String taskEndTimeStr;

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public String getHouseNames() {
		return houseNames;
	}

	public void setHouseNames(String houseNames) {
		this.houseNames = houseNames;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getHouseIds() {
		return houseIds;
	}

	public void setHouseIds(String houseIds) {
		this.houseIds = houseIds;
	}

	public String getTransToolsName() {
		return transToolsName;
	}

	public void setTransToolsName(String transToolsName) {
		this.transToolsName = transToolsName;
	}

	public String getTransTypeName() {
		return transTypeName;
	}

	public void setTransTypeName(String transTypeName) {
		this.transTypeName = transTypeName;
	}

	public String getTransTypeParentName() {
		return transTypeParentName;
	}

	public void setTransTypeParentName(String transTypeParentName) {
		this.transTypeParentName = transTypeParentName;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageLength() {
		return pageLength;
	}

	public void setPageLength(Integer pageLength) {
		this.pageLength = pageLength;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public String getTaskBeginTimeStr() {
		return taskBeginTimeStr;
	}

	public void setTaskBeginTimeStr(String taskBeginTimeStr) {
		this.taskBeginTimeStr = taskBeginTimeStr;
	}

	public String getTaskEndTimeStr() {
		return taskEndTimeStr;
	}

	public void setTaskEndTimeStr(String taskEndTimeStr) {
		this.taskEndTimeStr = taskEndTimeStr;
	}

	public List<HouseDto> getHouseList() {
		return houseList;
	}

	public void setHouseList(List<HouseDto> houseList) {
		this.houseList = houseList;
	}
}
