package com.segi.uhomecp.medicaltrans.enums;

public enum TransNoticeEnum {
	
	MT_TASK_ACCEPT_NOTICE("700201","任务接收通知"),
	MT_TASK_TIMEOUT_NOTICE("700202","任务即将超时通知"),
	MT_TASK_CANCEL_NOTICE("700203","运送单取消提醒"),
	MT_TASK_REDISPATCH_NOTICE("700204","运送单重新派单提醒"),
	MT_TASK_BACK_NOTICE("700205","运送退单提醒"),
	MT_TASK_OVERTIME_NOT_STARTED("700207","超时未开始"),
	MT_TASK_OVERTIME_NOTFULL("700208","抢单任务超时未满员"),
	MT_TASK_EVALUATE_NOTICE("700209","运送任务签收评价提醒"),
	MT_TASK_IS_ARRIVING_NOTICE("700209","运送任务即将到达提醒");
	
	private String code;
	
	private String name;
	
	private TransNoticeEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
}
