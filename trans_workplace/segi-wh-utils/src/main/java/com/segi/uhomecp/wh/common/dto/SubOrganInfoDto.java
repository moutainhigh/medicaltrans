package com.segi.uhomecp.wh.common.dto;

import java.util.LinkedList;
import java.util.List;

import segi.datacachesvr.queryInfo.UserOrganInfo;

/**
 * 组织机构信息DTO
 * @author kinas
 * 2017-12-11
 */
public class SubOrganInfoDto implements java.io.Serializable {

	private static final long serialVersionUID = -1230106823342306822L;

	private UserOrganInfo organ;	//组织机构
	
	private List<UserOrganInfo> communityList = new LinkedList<UserOrganInfo>();	//项目
	
	private List<UserOrganInfo> deptList = new LinkedList<UserOrganInfo>();		//部门

	public UserOrganInfo getOrgan() {
		return organ;
	}

	public void setOrgan(UserOrganInfo organ) {
		this.organ = organ;
	}

	public List<UserOrganInfo> getCommunityList() {
		return communityList;
	}

	public void setCommunityList(List<UserOrganInfo> communityList) {
		this.communityList = communityList;
	}

	public List<UserOrganInfo> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<UserOrganInfo> deptList) {
		this.deptList = deptList;
	}
	
}
