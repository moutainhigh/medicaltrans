package com.segi.uhomecp.medicaltrans.trans.listen;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import segi.medicaltrans.mttask.track.ItemIce;

import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.constant.MtTrackConstant.TaskTrackOprFlag;
import com.segi.uhomecp.medicaltrans.enums.TaskTrackActionEnum;
import com.segi.uhomecp.medicaltrans.enums.TransDispatchTypeEnum;
import com.segi.uhomecp.medicaltrans.trans.listen.event.TaskTrackEvent;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.medicaltrans.trans.utils.InitTaskDataUtils;
import com.segi.uhomecp.medicaltrans.trans.utils.TrackMessageUtil;
import com.segi.uhomecp.wh.common.utils.DateUtil;

/**
 * Title: TaskTrackEventListener.java    
 * @Description: 运送轨迹监听
 * @author zhangyang@segimail.com       
 * @created 2018年9月30日 下午1:42:19
 */
@Component
public class TaskTrackEventListener implements ApplicationListener<TaskTrackEvent> {
	
	public static final Logger logger = LoggerFactory.getLogger(TaskTrackEventListener.class);
	
	@Autowired
	private InitTaskDataUtils initTaskDataUtils;
	
	@Autowired
	private TrackMessageUtil trackMessageUtil;
	
	@Override
	public void onApplicationEvent(TaskTrackEvent event) {
		MtTask mtTask = event.getMtTask();
		Integer userId = event.getUserId();
		Integer taskId = event.getTaskId();
		String actionType = event.getActionType();
		String flag = event.getFlag();
		String userName = null;
		String nowDate = DateUtil.formatDateToStringYYMMDDHHmmss(event.getNowDate());
		String reason = event.getReason();
		if (!TaskTrackActionEnum.UNUSUAL_CLOSE_TASK.getActionType().equals(actionType)) {
			// 异常关闭轨迹 不用查询userName
			userName = initTaskDataUtils.getCurUserName(userId);
		} else {
			userName = MtConstant.SYSTEM_NAME;
		}
		if (StringUtils.isBlank(userName)) {
			return ;
		}
		
		if (TaskTrackActionEnum.CREATE_TASK.getActionType().equals(actionType)) {
			// 创建任务 通过flag判断是哪个客户端创建的
			List<ItemIce> itemIceList = new ArrayList<>();
			ItemIce createTrack = trackMessageUtil.initTaskTrackMessage(TaskTrackActionEnum.CREATE_TASK.getActionType(), 
					String.valueOf(userId), userName, nowDate, new Object[] {userName});
			itemIceList.add(createTrack);
			if (TaskTrackOprFlag.WEB_CERATE_FLAG.equals(flag)) {
				ItemIce dispatchTrack = trackMessageUtil.initTaskTrackMessage(TaskTrackActionEnum.DISPATCH_TASK.getActionType(), 
						String.valueOf(userId), userName, nowDate, new Object[] {
						userName, TransDispatchTypeEnum.getNameByCode(mtTask.getResType())});
				itemIceList.add(dispatchTrack);
			}
			if (TaskTrackOprFlag.CREATE_AUTO_FLAG.equals(flag)) {
				ItemIce dispatchTrack = trackMessageUtil.initTaskTrackMessage(TaskTrackActionEnum.DISPATCH_TASK.getActionType(), 
						String.valueOf(userId), userName, nowDate, new Object[] {
						userName, TransDispatchTypeEnum.getNameByCode(mtTask.getResType())});
				ItemIce beginTrack = trackMessageUtil.initTaskTrackMessage(TaskTrackActionEnum.BEGIN_TASK.getActionType(), 
						String.valueOf(userId), userName, nowDate, new Object[] {userName});
				itemIceList.add(dispatchTrack);
				itemIceList.add(beginTrack);
			}
			trackMessageUtil.saveTrackForCreateTask(String.valueOf(taskId), 
					mtTask.getOrganId(), mtTask.getBeginTime(), itemIceList);
		}
		
		if (TaskTrackActionEnum.DISPATCH_TASK.getActionType().equals(actionType)) {
			// 派单
			ItemIce dispatchTrack = trackMessageUtil.initTaskTrackMessage(actionType, 
					String.valueOf(userId), userName, nowDate, new Object[] {userName, 
					TransDispatchTypeEnum.getNameByCode(mtTask.getResType())});
			trackMessageUtil.saveTaskTrackMessage(taskId, dispatchTrack);
		}
		
		if (TaskTrackActionEnum.AGAIN_DISPATCH_TASK.getActionType().equals(actionType)) {
			// 重新派单
			ItemIce againDispatchTrack = trackMessageUtil.initTaskTrackMessage(actionType, 
					String.valueOf(userId), userName, nowDate, new Object[] {userName, 
					TransDispatchTypeEnum.getNameByCode(mtTask.getResType())});
			trackMessageUtil.saveTaskTrackMessage(taskId, againDispatchTrack);
		}
		
		if (TaskTrackActionEnum.CANCEL_TASK.getActionType().equals(actionType)) {
			// 取消
			ItemIce cancelTrack = trackMessageUtil.initTaskTrackMessage(actionType, 
					String.valueOf(userId), userName, nowDate, new Object[] {userName});
			trackMessageUtil.saveTaskTrackMessage(taskId, cancelTrack);
		}
		
		if (TaskTrackActionEnum.BEGIN_TASK.getActionType().equals(actionType)) {
			// 开始
			ItemIce beginTrack = trackMessageUtil.initTaskTrackMessage(actionType, 
					String.valueOf(userId), userName, nowDate, new Object[]{userName});
			trackMessageUtil.saveTaskTrackMessage(taskId, beginTrack);
		}
		
		if (TaskTrackActionEnum.EVALUATE_TASK.getActionType().equals(actionType)) {
			// 签收评价
			ItemIce evaluateTrack = trackMessageUtil.initTaskTrackMessage(actionType, 
					String.valueOf(userId), userName, nowDate, new Object[] {userName, mtTask.getEvaluate()});
			trackMessageUtil.saveTaskTrackMessage(taskId, evaluateTrack);
		}
		
		if (TaskTrackActionEnum.BACK_TASK.getActionType().equals(actionType)) {
			// 退单
			ItemIce backTrack = trackMessageUtil.initTaskTrackMessage(actionType, 
					String.valueOf(userId), userName, nowDate, MtConstant.BACK_TASK_SPECIAL_TYPE, 
					new Object[]{userName, reason});
			trackMessageUtil.saveTaskTrackMessage(taskId, backTrack);
		}
		
		if (TaskTrackActionEnum.FINISH_TASK.getActionType().equals(actionType)) {
			// 完成任务
			if (TaskTrackOprFlag.APP_FINISH_FLAG.equals(flag)) {
				String specialType = null;
				Object[] objArr = new Object[3];
				if (MtConstant.TIME_OUT.equals(mtTask.getIsTimeOut())) {
					// 超时了
					objArr[0] = userName;
					objArr[1] = MtConstant.TRACK_TIME_OUT;
					objArr[2] = reason;
					specialType = MtConstant.TIME_OUT_SPECIAL_TYPE;
				} else {
					// 没超时
					objArr[0] = userName;
					objArr[1] = "";
					objArr[2] = "";
				}
				ItemIce finishTrack = trackMessageUtil.initTaskTrackMessage(actionType, 
						String.valueOf(userId), userName, nowDate, specialType, objArr);
				trackMessageUtil.saveTaskTrackMessage(taskId, finishTrack);
			} else {
				ItemIce finishTrack = trackMessageUtil.initTaskTrackMessage(actionType, 
						String.valueOf(userId), userName, nowDate, new Object[]{userName, "", ""});
				trackMessageUtil.saveTaskTrackMessage(taskId, finishTrack);
			}
		} 
		
		if (TaskTrackActionEnum.UNUSUAL_CLOSE_TASK.getActionType().equals(actionType)) {
			// 异常关闭
			ItemIce unusualCloseTask = trackMessageUtil.initTaskTrackMessage(TaskTrackActionEnum.
					UNUSUAL_CLOSE_TASK.getActionType(), "0", userName, nowDate, new Object[] {userName});
			trackMessageUtil.saveTaskTrackMessage(taskId, unusualCloseTask);
		}
	}

}
