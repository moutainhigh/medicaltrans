package com.segi.uhomecp.medicaltrans.trans.listen.event;

import java.util.Date;

import org.springframework.context.ApplicationEvent;

import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;

/**
 * Title: TaskTrackEvent.java    
 * @Description: 轨迹处理事件
 * @author zhangyang@segimail.com       
 * @created 2018年9月30日 下午3:01:57
 */
public class TaskTrackEvent extends ApplicationEvent {
	
	/**  描述   (@author: zhangyang@segimail.com) */      
	    
	private static final long serialVersionUID = 2550197712703793213L;

	private Integer taskId;
	
	private String actionType;
	
	private Integer userId;
	
	private MtTask mtTask;
	
	private Date nowDate;
	
	// 不同客户端创建任务标志/不同客户端完成任务标志
	private String flag;
	
	// 退单、超时原因
	private String reason;
	
	public TaskTrackEvent(Object source, Integer taskId, String actionType, Integer userId, MtTask mtTask, Date nowDate, String flag) {
		super(source);
		this.taskId = taskId;
		this.actionType = actionType;
		this.userId = userId;
		this.mtTask = mtTask;
		this.nowDate = nowDate;
		this.flag = flag;
	}
	
	public TaskTrackEvent(Object source, Integer taskId, String actionType, Integer userId, MtTask mtTask, Date nowDate) {
		super(source);
		this.taskId = taskId;
		this.actionType = actionType;
		this.userId = userId;
		this.mtTask = mtTask;
		this.nowDate = nowDate;
	}

	public TaskTrackEvent(Object source, Integer taskId, String actionType, Integer userId, 
			MtTask mtTask, Date nowDate, String flag, String reason) {
		super(source);
		this.taskId = taskId;
		this.actionType = actionType;
		this.userId = userId;
		this.mtTask = mtTask;
		this.nowDate = nowDate;
		this.flag = flag;
		this.reason = reason;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public MtTask getMtTask() {
		return mtTask;
	}

	public void setMtTask(MtTask mtTask) {
		this.mtTask = mtTask;
	}

	public Date getNowDate() {
		return nowDate;
	}

	public void setNowDate(Date nowDate) {
		this.nowDate = nowDate;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
