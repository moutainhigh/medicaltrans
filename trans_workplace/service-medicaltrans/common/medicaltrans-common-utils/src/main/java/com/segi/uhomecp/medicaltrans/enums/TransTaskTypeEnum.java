package com.segi.uhomecp.medicaltrans.enums;

/**
 * Title: TransTaskTypeEnum.java    
 * @Description: 任务类型枚举
 * @author zhangyang@segimail.com       
 * @created 2018年4月8日 下午3:19:47
 */
public enum TransTaskTypeEnum {
	TASK_TYPE_GRAD("0", "抢单任务"),// 这个只做提醒不做其他用途
	TASK_TYPE_DISPATCH("1", "调度任务"),
	TASK_TYPE_SELF("2", "自主任务"),
	TASK_TYPE_LOOP("3", "固定任务");
	
	private String code;
	
	private String name;
	
	private TransTaskTypeEnum(String code, String name) {
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
		for (TransTaskTypeEnum tmp : TransTaskTypeEnum.values()) {
			if (tmp.getCode().equals(code)) {
				return tmp.getName();
			}
		}
		return "";
	}
}
