package com.segi.uhomecp.medicaltrans.enums;

public enum TransDispatchTypeEnum {

	TRANS_DISPATCH_TYPE_01("1", "指定"),
	TRANS_DISPATCH_TYPE_02("2", "抢单");
	
	private String code;
	
	private String name;
	
	private TransDispatchTypeEnum(String code, String name) {
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
		for (TransDispatchTypeEnum tmp : TransDispatchTypeEnum.values()) {
			if (tmp.getCode().equals(code)) {
				return tmp.getName();
			}
		}
		return "";
	}
}
