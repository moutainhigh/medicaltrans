package com.segi.uhomecp.medicaltrans.enums;

public enum PartTableEnum {

	DISPATCH_TABLE_01("1","表1"),
	
	DISPATCH_TABLE_02("2","表2"),
	
	DISPATCH_TABLE_03("3","表3"),
	
	DISPATCH_TABLE_04("4","表4"),
	
	DISPATCH_TABLE_05("5","表5"),
	
	DISPATCH_TABLE_06("6","表6"),
	
	DISPATCH_TABLE_07("7","表7"),
	
	DISPATCH_TABLE_08("8","表8"),
	
	DISPATCH_TABLE_09("9","表9"),
	
	DISPATCH_TABLE_10("10","表10"),
	
	DISPATCH_TABLE_11("11","表11"),
	
	DISPATCH_TABLE_12("12","表12"),
	
	DISPATCH_TABLE_13("13","表13"),
	
	DISPATCH_TABLE_14("14","表14"),
	
	DISPATCH_TABLE_15("15","表15"),
	
	DISPATCH_TABLE_16("16","表16");
	
	
	private String code;
	
	private String name;
	
	private PartTableEnum(String code, String name) {
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
		for (PartTableEnum tmp : PartTableEnum.values()) {
			if (tmp.getCode().equals(code)) {
				return tmp.getName();
			}
		}
		return "";
	}
}
