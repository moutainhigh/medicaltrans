package com.segi.uhomecp.medicaltrans.baseinfo.userhosp.dto;

import java.util.Date;


public class MtUserHospParamDto {

	public Integer userId;
	
	public Integer updateBy;
	
	 private Date updateDate;

	public java.util.List<MtUserHospInfoDto> organList;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public java.util.List<MtUserHospInfoDto> getOrganList() {
		return organList;
	}

	public void setOrganList(java.util.List<MtUserHospInfoDto> organList) {
		this.organList = organList;
	}

	@Override
	public String toString() {
		return "MtUserHospParamDto [userId=" + userId + ", updateBy="
				+ updateBy + ", updateDate=" + updateDate + ", organList="
				+ organList + "]";
	}
	
}
