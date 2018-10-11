package com.segi.uhomecp.medicaltrans.enums;

public enum TransStatusEnum {

	TRANS_NON_DISPATCH("1", "未派单"),
	TRANS_ROBBING("2", "抢单中"),
	TRANS_RUNNING("3", "进行中"),
	TRANS_NOT_START("4", "未开始"),
	TRANS_COMPLETED("5", "已完成"),
	TRANS_CANCEL("6", "已取消"),
	TRANS_BACK("7", "退单"),
	TRANS_UNUSUAL_CLOSE("9", "异常关闭"),
	TRANS_ROB_COMPLETED("8", "抢单成功,等待其他队友");
	
	private String code;
	
	private String name;
	
	private TransStatusEnum(String code, String name) {
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
		for (TransStatusEnum tmp : TransStatusEnum.values()) {
			if (tmp.getCode().equals(code)) {
				return tmp.getName();
			}
		}
		return "";
	}
}
