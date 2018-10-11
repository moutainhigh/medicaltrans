package com.segi.uhomecp.wh.common.appmsg.model;

import java.io.Serializable;

public class StationMessage implements Serializable{

	private String contentType;	//是	Integer	类容类型定义:1为文本，2为图片，3为语音，4为视频 5 借条
	
	private String subMsg;	//否	string	引用内容
	
	private String label; //	否	string	引用标题
	
	private String businessType	;//是	Integer	消息类型 1:邻居;2:私信;3:系统
	
	private String type	;//是	Integer	业务类型，接入者需要在common项目里的PushType加上枚举定义
	
	private String objectId;	//是	Integer	业主务键，没有传-1
	
	private String sendUserId; //	是	string	发送人 id，没有传-1
	
	private String sender;//	否	string	发送者昵称
	
	private String turnTip; //	否	string	跳转标题
	
	private String turnType;//	否	string	跳转类型

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getSubMsg() {
		return subMsg;
	}

	public void setSubMsg(String subMsg) {
		this.subMsg = subMsg;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getTurnTip() {
		return turnTip;
	}

	public void setTurnTip(String turnTip) {
		this.turnTip = turnTip;
	}

	public String getTurnType() {
		return turnType;
	}

	public void setTurnType(String turnType) {
		this.turnType = turnType;
	}
}
