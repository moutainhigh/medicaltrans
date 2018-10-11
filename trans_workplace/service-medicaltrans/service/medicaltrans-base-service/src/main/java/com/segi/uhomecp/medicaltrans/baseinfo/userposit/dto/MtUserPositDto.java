package com.segi.uhomecp.medicaltrans.baseinfo.userposit.dto;

import com.segi.uhomecp.medicaltrans.base.userposit.model.MtUserPosit;

public class MtUserPositDto extends MtUserPosit{

	/**
	 * 类描述: 
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月3日 下午8:47:40   
	 */
	private static final long serialVersionUID = 3385227636466953845L;
	
	public String mac;
	
	public String userName;
	
	public String userWorkNo;

	public String sid;
	
	private Short runTaskNum;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public int locationId;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserWorkNo() {
		return userWorkNo;
	}

	public void setUserWorkNo(String userWorkNo) {
		this.userWorkNo = userWorkNo;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Short getRunTaskNum() {
		return runTaskNum;
	}

	public void setRunTaskNum(Short runTaskNum) {
		this.runTaskNum = runTaskNum;
	}
	
}
