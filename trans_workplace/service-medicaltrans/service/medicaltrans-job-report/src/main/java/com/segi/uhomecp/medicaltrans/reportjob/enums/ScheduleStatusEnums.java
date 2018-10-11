package com.segi.uhomecp.medicaltrans.reportjob.enums;
/**
 * 运行状态
 *     
 * 包: com.segi.uhomecp.medicaltrans.reportjob.enums 
 * 类名称: ScheduleStatusEnums.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 下午5:49:09
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
public enum ScheduleStatusEnums {
	/**
	 * 运行
	 */
	RUNNING(0),
	/**
	 * 异常
	 */
	ERROR(2),
	/**
	 * 完成
	 */
	COMPLETE(1);
	
	private int value;
	
	private ScheduleStatusEnums(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
