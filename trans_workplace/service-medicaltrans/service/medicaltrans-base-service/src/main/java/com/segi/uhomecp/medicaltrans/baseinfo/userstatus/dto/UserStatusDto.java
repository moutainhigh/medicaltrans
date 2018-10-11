package com.segi.uhomecp.medicaltrans.baseinfo.userstatus.dto;

import com.segi.uhomecp.common.model.AbstractModel;

/**
 * 
 * Title: UserStatusDto.java    
 * @Description: 人员状态dto
 * @author dengdong@segimail.com       
 * @created 2018年5月9日 下午2:23:04
 */
public class UserStatusDto extends AbstractModel{
	
	/**  描述   (@author: dengdong@segimail.com) */      
	    
	private static final long serialVersionUID = 1L;

	private String organId;
	
	private String userId;
	
    private String status;
    
    private Long paramTime;//打卡时间
    
    private Long updateTime;//更新时间

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getParamTime() {
		return paramTime;
	}

	public void setParamTime(Long paramTime) {
		this.paramTime = paramTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserStatusDto [organId=");
		builder.append(organId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", status=");
		builder.append(status);
		builder.append(", paramTime=");
		builder.append(paramTime);
		builder.append(", updateTime=");
		builder.append(updateTime);
		builder.append("]");
		return builder.toString();
	}
}