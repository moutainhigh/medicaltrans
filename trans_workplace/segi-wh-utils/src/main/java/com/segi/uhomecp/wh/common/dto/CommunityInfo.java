package com.segi.uhomecp.wh.common.dto;

public class CommunityInfo {
	private Integer communityId;
	private String name;
	private String pySname;
	private String address;
	public Integer getCommunityId() {
		return communityId;
	}
	public void setCommunityId(Integer communityId) {
		this.communityId = communityId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPySname() {
		return pySname;
	}
	public void setPySname(String pySname) {
		this.pySname = pySname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
