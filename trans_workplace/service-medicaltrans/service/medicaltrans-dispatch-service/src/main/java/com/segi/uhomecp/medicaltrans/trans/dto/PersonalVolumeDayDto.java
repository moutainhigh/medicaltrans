package com.segi.uhomecp.medicaltrans.trans.dto;

import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutors;

/**
 * @ClassName:  PersonalVolumeDayDto   
 * @Description:个人运送量日排名Dto   
 * @author: zhaoqing
 * @date:   2018年9月17日 上午11:56:58
 */
public class PersonalVolumeDayDto extends MtTaskExecutors {
   
	private static final long serialVersionUID = 1266288207897824128L;
	
	/** 排名名次 */
	private Integer rank;	
	
	/** 组织名称 */
	private String organName;
	
	/** 运送员Id */
	private Integer userId;
	
	/** 运送员名称 */
	private String userName;
	
	/** 个人运送量 */
	private Integer transVolume;
	
	/** 运送员员工号 */
	private String userNo;
	
	/** 员工IDS */
	private String userIds;
	
	/** 服务组Id */
	private Integer sergroupId;
	
	/** 员工所属的服务组名称 */
	private String sergroupName;
	
	/** 服务组Id */
	private String sergroupIds;
	
	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getTransVolume() {
		return transVolume;
	}

	public void setTransVolume(Integer transVolume) {
		this.transVolume = transVolume;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public Integer getSergroupId() {
		return sergroupId;
	}

	public void setSergroupId(Integer sergroupId) {
		this.sergroupId = sergroupId;
	}

	public String getSergroupName() {
		return sergroupName;
	}

	public void setSergroupName(String sergroupName) {
		this.sergroupName = sergroupName;
	}

	public String getSergroupIds() {
		return sergroupIds;
	}

	public void setSergroupIds(String sergroupIds) {
		this.sergroupIds = sergroupIds;
	}

}
