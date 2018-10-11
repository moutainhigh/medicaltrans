package com.segi.uhomecp.medicaltrans.base.posit.bean;

import com.segi.uhomecp.common.model.AbstractModel;

/**
 * 人员最新位置
 * @author Jimmy
 * 2018-3-22
 */
public class MtCurUserPosit extends AbstractModel {
	private static final long serialVersionUID = 5865591901111397120L;
	
	/**物业集团Id*/
	private Integer groupOrganId;
	
	/**组织机构Id*/
	private Integer organId;
	
	/**用户Id*/
	private Integer userId;
	
	/**用户名称*/
	private String userName;
	
	/**用户工号*/
	private String userWorkNo;
	
	/**所在科室*/
	private Integer locationId;
	
	/**楼栋id */
	private Integer buildId;

	/**楼层id*/
	private Integer floorId;
	
	/**单元id*/
	private Integer sid;

	/**状态*/
	private String status;
	
	/**未完成运送单*/
	private Short unTaskNum;
	
	private Short runTaskNum;
	
	/**最后更新时间*/
	private String lastUpdateTime;
	
	public Short getUnTaskNum() {
		return unTaskNum;
	}

	public void setUnTaskNum(Short unTaskNum) {
		this.unTaskNum = unTaskNum;
	}


	public Integer getGroupOrganId() {
		return groupOrganId;
	}

	public void setGroupOrganId(Integer groupOrganId) {
		this.groupOrganId = groupOrganId;
	}

	public Integer getOrganId() {
		return organId;
	}

	public void setOrganId(Integer organId) {
		this.organId = organId;
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

	public String getUserWorkNo() {
		return userWorkNo;
	}

	public void setUserWorkNo(String userWorkNo) {
		this.userWorkNo = userWorkNo;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getBuildId() {
		return buildId;
	}

	public void setBuildId(Integer buildId) {
		this.buildId = buildId;
	}

	public Integer getFloorId() {
		return floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Short getRunTaskNum() {
		return runTaskNum;
	}

	public void setRunTaskNum(Short runTaskNum) {
		this.runTaskNum = runTaskNum;
	}

	@Override
	public String toString() {
		return "MtCurUserPosit [groupOrganId=" + groupOrganId + ", organId=" + organId + ", userId=" + userId
				+ ", userName=" + userName + ", userWorkNo=" + userWorkNo + ", locationId=" + locationId + ", buildId="
				+ buildId + ", floorId=" + floorId + ", sid=" + sid + ", status=" + status + ", unTaskNum=" + unTaskNum
				+ ", runTaskNum=" + runTaskNum + ", lastUpdateTime=" + lastUpdateTime + "]";
	}
	

}
