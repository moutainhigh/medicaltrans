package com.segi.uhomecp.medicaltrans.baseinfo.userhosp.dto;

import java.util.List;

/**
 * 描述: 创建人: liuyi@sige.com 创建时间: 2018年5月10日 下午5:18:46
 */
public class MtUserHospInfoDto {

	public Integer userHospRelId;
	public Integer organId;
	public String organName;
	public Integer houseId;
	public String houseName;
	public Integer userId;
	public String userName;
	public List<Integer> userIdList;

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

	public Integer getUserHospRelId() {
		return userHospRelId;
	}

	public void setUserHospRelId(Integer userHospRelId) {
		this.userHospRelId = userHospRelId;
	}

	public Integer getOrganId() {
		return organId;
	}

	public void setOrganId(Integer organId) {
		this.organId = organId;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public Integer getHouseId() {
		return houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public List<Integer> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<Integer> userIdList) {
		this.userIdList = userIdList;
	}

	@Override
	public String toString() {
		return "MtUserHospInfoDto [userHospRelId=" + userHospRelId
				+ ", organId=" + organId + ", organName=" + organName
				+ ", houseId=" + houseId + ", houseName=" + houseName
				+ ", userId=" + userId + ", userName=" + userName + "]";
	}
}
