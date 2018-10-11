package com.segi.uhomecp.medicaltrans.enums;

public enum PartDataBaseEnum {
	
	DISPATCH_SHARD_00("sharding_0","数据库0");
//	,DISPATCH_SHARD_01("sharding_1","数据库1");
	
	private String code;
	
	private String name;
	
	private PartDataBaseEnum(String code, String name) {
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
		for (PartDataBaseEnum tmp : PartDataBaseEnum.values()) {
			if (tmp.getCode().equals(code)) {
				return tmp.getName();
			}
		}
		return "";
	}
}
