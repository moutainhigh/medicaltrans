package com.segi.uhomecp.medicaltrans.enums;

/**
 * Title: TaskTrackActionEnum.java    
 * @Description: 任务轨迹actionType枚举
 * @author zhangyang@segimail.com       
 * @created 2018年9月26日 上午9:42:23
 */
public enum TaskTrackActionEnum {

	CREATE_TASK("CREATE_TASK", "提单"),
	DISPATCH_TASK("DISPATCH_TASK", "派单"),
	AGAIN_DISPATCH_TASK("AGAIN_DISPATCH_TASK", "重新派单"),
	CANCEL_TASK("CANCEL_TASK", "取消"),
	BEGIN_TASK("BEGIN_TASK", "开始"),
	FINISH_TASK("FINISH_TASK", "完成"),
	EVALUATE_TASK("EVALUATE_TASK", "评价"),
	BACK_TASK("BACK_TASK", "退单"),
	UNUSUAL_CLOSE_TASK("UNUSUAL_CLOSE_TASK", "异常关闭");
	
	private String actionType;
	
	private String actionName;
	
	private TaskTrackActionEnum(String actionType, String actionName) {
		this.actionType = actionType;
		this.actionName = actionName;
	}

	public String getActionType() {
		return actionType;
	}

	public String getActionName() {
		return actionName;
	}

	public static String getNameByAction(String actionType) {
		for (TaskTrackActionEnum tmp : TaskTrackActionEnum.values()) {
			if (tmp.getActionType().equals(actionType)) {
				return tmp.getActionName();
			}
		}
		return "";
	}
	
}
