package com.segi.uhomecp.medicaltrans.enums;

/**
 * 
 * Title: TransToolsEnum.java    
 * @Description: 運送工具
 * @author wangxiong@segimail.com       
 * @created 2018年3月26日 下午7:42:17
 */
public enum TransToolsEnum {

	TRANS_TOOLS_WALK("1", "步行"),
	TRANS_TOOLS_PUSHING_BED("2", "推床"),
	TRANS_TOOLS_FLAT_CAR("3", "平车"),
	TRANS_TOOLS_WHEELCHAIR("4","轮椅");
	
	private String code;
	
	private String name;
	
	private TransToolsEnum(String code, String name) {
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
		for (TransToolsEnum tmp : TransToolsEnum.values()) {
			if (tmp.getCode().equals(code)) {
				return tmp.getName();
			}
		}
		return "";
	}
}
