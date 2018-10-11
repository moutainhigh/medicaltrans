package com.segi.uhomecp.medicaltrans.enums;

/**
 * Title: TaskTrackActionCodeEnum.java    
 * @Description: 任务轨迹actionTypeCode枚举
 * @author zhangyang@segimail.com       
 * @created 2018年10月9日 下午4:21:20
 */
public enum TaskTrackActionCodeEnum {

	CREATE_TASK("CREATE_TASK", "1"),
	DISPATCH_TASK("DISPATCH_TASK", "2"),
	AGAIN_DISPATCH_TASK("AGAIN_DISPATCH_TASK", "3"),
	CANCEL_TASK("CANCEL_TASK", "4"),
	BEGIN_TASK("BEGIN_TASK", "5"),
	FINISH_TASK("FINISH_TASK", "6"),
	EVALUATE_TASK("EVALUATE_TASK", "7"),
	BACK_TASK("BACK_TASK", "8"),
	UNUSUAL_CLOSE_TASK("UNUSUAL_CLOSE_TASK", "9");
	
	private String actionType;
	
	private String actionCode;
	
	private TaskTrackActionCodeEnum(String actionType, String actionCode) {
		this.actionType = actionType;
		this.actionCode = actionCode;
	}

	public String getActionType() {
		return actionType;
	}

	public String getActionCode() {
		return actionCode;
	}
	
	public static String getCodeByAction(String actionType) {
		for (TaskTrackActionCodeEnum tmp : TaskTrackActionCodeEnum.values()) {
			if (tmp.getActionType().equals(actionType)) {
				return tmp.getActionCode();
			}
		}
		return "";
	}
}
