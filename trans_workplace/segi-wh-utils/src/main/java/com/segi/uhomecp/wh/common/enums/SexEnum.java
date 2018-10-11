package com.segi.uhomecp.wh.common.enums;

public enum SexEnum {
	MALE("2", "男"),
	
	FEMALE("1", "女");
	
	private String code;
		
	private String name;
	
	private SexEnum(String code, String name) {
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
		if(SexEnum.MALE.getCode().equals(code)) {
			return SexEnum.MALE.getName();
		}
		if(SexEnum.FEMALE.getCode().equals(code)) {
			return SexEnum.FEMALE.getName();
		}
		return null;
	}
}
