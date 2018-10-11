package com.segi.uhomecp.medicaltrans.enums;

/**
 * @ClassName:  IsTimeOutEnum   
 * @Description:是否超时   
 * @author: zhaoqing
 * @date:   2018年8月7日 下午4:53:37
 */
public enum IsTimeOutEnum {
	
	IS_TIME_OUT_NO("0", "否"),
	IS_TIME_OUT_TES("1", "是");
	
	private String code;
	
	private String name;
	
	private IsTimeOutEnum(String code, String name) {
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
		for (IsTimeOutEnum tmp : IsTimeOutEnum.values()) {
			if (tmp.getCode().equals(code)) {
				return tmp.getName();
			}
		}
		return "";
	}
}
