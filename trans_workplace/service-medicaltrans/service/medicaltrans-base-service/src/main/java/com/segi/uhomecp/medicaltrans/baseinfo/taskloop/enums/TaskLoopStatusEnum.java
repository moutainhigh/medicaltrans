/**
 * 
 */
package com.segi.uhomecp.medicaltrans.baseinfo.taskloop.enums;

/** 
 * Title: TaskLoopStatusEnum.java    
 * @Description: 循环任务状态
 * @author yangyh@segimail.com       
 * @created 2018年3月30日 下午6:51:32    
 */
public enum TaskLoopStatusEnum {

    STATUS_DEL("0", "已删除"),
	
    STATUS_CD_NORMAL("1","启用"),
	
    STATUS_CD_STOP("2", "停用");

	private String status;
	private String statusName;

	private TaskLoopStatusEnum(String status, String statusName) {
		this.status = status;
		this.statusName = statusName;
	}

	public String getStatus() {
		return this.status;
	}
	
	public String getStatusName() {
		return this.statusName;
	}

	public static String getNamefromStatus(String status) {
		for (TaskLoopStatusEnum tmp : TaskLoopStatusEnum.values()) {
			if (tmp.getStatus().equals(status)) {
				return tmp.getStatusName();
			}
		}
		return null;
	}
}
