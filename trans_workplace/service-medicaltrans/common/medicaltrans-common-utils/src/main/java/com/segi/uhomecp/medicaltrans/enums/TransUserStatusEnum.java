package com.segi.uhomecp.medicaltrans.enums;

public enum TransUserStatusEnum {

	TRANS_USER_STATUS_00("0", "删除"),//0:删除（管理员删除）
	TRANS_USER_STATUS_01("1", "正常"),
	TRANS_USER_STATUS_02("2", "退单"),
	TRANS_USER_STATUS_05("5", "完成");
	
	private String code;
	
	private String name;
	
	private TransUserStatusEnum(String code, String name) {
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
		for (TransUserStatusEnum tmp : TransUserStatusEnum.values()) {
			if (tmp.getCode().equals(code)) {
				return tmp.getName();
			}
		}
		return "";
	}
}
