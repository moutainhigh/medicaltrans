package com.segi.uhomecp.wh.common.appmsg.model;

public class AppMessage {
	
	private String code; 
	
	private String type;
	
	private String title;
	
	private String message;
	

	public AppMessage() {
		super();
	}
	
	public AppMessage(String code, String type, String title, String message) {
		super();
		this.code = code;
		this.type = type;
		this.title = title;
		this.message = message;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
