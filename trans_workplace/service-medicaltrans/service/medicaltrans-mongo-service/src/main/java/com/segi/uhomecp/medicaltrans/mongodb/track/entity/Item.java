package com.segi.uhomecp.medicaltrans.mongodb.track.entity;


/**
 * 运输单实例
 *     
 * 包: com.segi.uhomecp.medicaltrans.mongodb.trail.entity 
 * 类名称: Item.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 下午6:46:30
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
public class Item {
    
    private String userId; // 处理人用户ID
    
    private String userName; // 处理人名字
    
    private String action; // 动作
    
    private String message; // 跟踪描述
    
    private String createDate; // 创建时间
    
    private String specialType; // 特殊类型（退单、超时）

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getSpecialType() {
		return specialType;
	}

	public void setSpecialType(String specialType) {
		this.specialType = specialType;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Item [userId=" + userId + ", userName=" + userName
				+ ", action=" + action + ", message=" + message
				+ ", createDate=" + createDate + ", specialType=" + specialType
				+ "]";
	}

}
