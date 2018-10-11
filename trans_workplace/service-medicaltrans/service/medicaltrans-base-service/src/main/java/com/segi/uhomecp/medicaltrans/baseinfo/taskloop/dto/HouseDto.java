package com.segi.uhomecp.medicaltrans.baseinfo.taskloop.dto;

import java.io.Serializable;

public class HouseDto implements Serializable {

	private static final long serialVersionUID = 1169501312030347796L;
	
	public String houseId;
	public String houseName;
	public String status;
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
