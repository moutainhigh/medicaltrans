package com.segi.uhomecp.medicaltrans.enums;

/**
 * 
 * Title: TransTypeEnum.java    
 * @Description: 运送类型
 * @author wangxiong@segimail.com       
 * @created 2018年3月26日 下午7:54:02
 */
public enum TransTypeEnum {
	
	TRANS_TYPE_01("01", "药品运送"),
	TRANS_TYPE_02("02", "标本运送"),
	TRANS_TYPE_03("03", "血制品运送"),
	TRANS_TYPE_04("04", "病人护送"),
	TRANS_TYPE_05("05", "物品运送"),
	TRANS_TYPE_06("06", "文书运送"),
	TRANS_TYPE_07("07", "其他");
	
	private String code;
	
	private String name;
	
	private TransTypeEnum(String code, String name) {
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
		for (TransTypeEnum tmp : TransTypeEnum.values()) {
			if (tmp.getCode().equals(code)) {
				return tmp.getName();
			}
		}
		return "";
	}
	
	public static String getCodeByName(String name) {
		for (TransTypeEnum tmp : TransTypeEnum.values()) {
			if (tmp.getName().equals(name)) {
				return tmp.getCode();
			}
		}
		return "";
	}
}
