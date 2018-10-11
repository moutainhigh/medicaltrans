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
public enum HandlerStateEnums {
	
	/**
	 * 同步
	 */
	RUNNING(1),
	
	COMMPLETED(2),
	
	ERROR(-1);
	
	private int value;
	
	private HandlerStateEnums(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
