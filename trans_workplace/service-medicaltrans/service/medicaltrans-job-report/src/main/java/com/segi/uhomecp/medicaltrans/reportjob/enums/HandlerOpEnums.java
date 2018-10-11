package com.segi.uhomecp.medicaltrans.reportjob.enums;

/**
 * 执行器处理方式
 *     
 * 包: com.segi.uhomecp.medicaltrans.reportjob.enums 
 * 类名称: HandlerOpEnums.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 上午10:01:46
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
public enum HandlerOpEnums {
	
	/**
	 * 同步
	 */
	SYNC("0", "同步"),
	
	ASYNC("1", "异步");
	
	private String value;
	
	private String name;
	
	private HandlerOpEnums(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 根据值获取名称
	 * @param value
	 * @return
	 */
	public static String getNameByValue(String value) {
		for (HandlerOpEnums tmp : HandlerOpEnums.values()) {
			if (tmp.getValue().equals(value)) {
				return tmp.getName();
			}
		}
		
		return "";
	}
}
