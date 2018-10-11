package com.segi.uhomecp.medicaltrans.enums;

public enum InvokingFlagEnum {

	INVOKING_FROM_PAD("1", "pad端"),
	INVOKING_FROM_APP("2", "app端"),
	INVOKING_FROM_WEB("3", "web端"),
	INVOKING_FROM_WECHAT("4", "微信公众号");
	
	private String code;
	
	private String name;
	
	private InvokingFlagEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public static String getNameByCode(String code) {
		for (InvokingFlagEnum tmp : InvokingFlagEnum.values()) {
			if (tmp.getCode().equals(code)) {
				return tmp.getName();
			}
		}
		return "";
	}
}
