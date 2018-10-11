package com.segi.uhomecp.medicaltrans.enums;

/**
 * 
 * Title: UrgencyEnum.java    
 * @Description: 紧急程度
 * @author wangxiong@segimail.com       
 * @created 2018年3月26日 下午7:38:39
 */
public enum UrgencyEnum {
	
	URGENCY_COMMONLY("1", "一般"),
	URGENCY_URGENT("2", "紧急"),
	URGENCY_EXTRAURGENT("3", "特急");
	
	private String code;
	
	private String name;
	
	private UrgencyEnum(String code, String name) {
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
		for (UrgencyEnum tmp : UrgencyEnum.values()) {
			if (tmp.getCode().equals(code)) {
				return tmp.getName();
			}
		}
		return "";
	}
}
