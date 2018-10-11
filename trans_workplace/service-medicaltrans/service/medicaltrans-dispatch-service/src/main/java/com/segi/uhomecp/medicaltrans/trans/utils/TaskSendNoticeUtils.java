package com.segi.uhomecp.medicaltrans.trans.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import segi.medicaltrans.common.transType.TransTypeInfo;
import segi.medicaltrans.location.common.MtLocationInfoIce;

import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.constant.MtNoticeConstant;
import com.segi.uhomecp.medicaltrans.enums.TransDispatchTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransNoticeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTaskTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTypeEnum;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutors;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.wh.common.appmsg.AppMessageUtil;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.DataTypeConverUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;

@Component
public class TaskSendNoticeUtils {
	
	public static final Logger logger = LoggerFactory.getLogger(TaskSendNoticeUtils.class);
	
	@Autowired
	private AppMessageUtil appMessageUtil;
	
	/**
	 * 延时发送抢单未满员提醒
	 * @param userId
	 * @param nowDate
	 * @param taskId
	 */
	public void sendOvertimeNotFull(Integer userId, Date nowDate, Integer taskId, String taskType){
		try{
			List<String> userList = new ArrayList<>();
			userList.add(String.valueOf(userId));
			String businessKey = new StringBuffer(String.valueOf(taskId)).append("_")
					.append(TransNoticeEnum.MT_TASK_OVERTIME_NOTFULL.getCode()).toString();
			appMessageUtil.scheduleSendAppMessage(userList, taskId, businessKey,
					DateUtil.addDateMinute(nowDate, MtCommonServiceUtils.getTaskNoRobWarnMinute()).getTime()
					, MtNoticeConstant.MT_TASK_OVERTIME_NOT_FULL, taskType);
		}catch (Exception e) {
			logger.error("sendOvertimeNotFull",e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 当抢单已满员，删除延时提醒
	 * @param taskId
	 */
	public void delOvertimeNotFull(Integer taskId){
		try{
			String serviceId = new StringBuffer(String.valueOf(taskId)).append("_")
					.append(TransNoticeEnum.MT_TASK_OVERTIME_NOTFULL.getCode()).toString();
			appMessageUtil.delAppMessage(String.valueOf(serviceId));
		}catch (Exception e) {
			logger.error("delOvertimeNotFull",e);
			e.printStackTrace();
		}
	}
		
	/**
	 * 任务即将超时通知
	 */
	public void sendTaskTimeOutNotice(MtTask mtTask, String userId, List<MtTaskExecutors> list, String taskType, Integer limitMinute){
		try{
			logger.debug("===============sendTaskTimeOutNotice{}{}{}----------->Start"+ DateUtil.nowDateToStringYYMMDDHHmmss());
			if(mtTask==null){
				logger.error("任务为空");
				return;
			}
			//1、获取执行人list
			List<String> userList = new ArrayList<String>();
			if (StringUtils.isNotBlank(userId)) {
				userList.add(userId);
			}
			
			if(AppUtils.isNotEmpty(list)){
				for(MtTaskExecutors mtTaskExecutors:list){
					if(mtTaskExecutors!=null && mtTaskExecutors.getExeUserId()!=null 
							&& (!mtTaskExecutors.getExeUserId().equals(mtTask.getExeEndUserId()))){
						userList.add(String.valueOf(mtTaskExecutors.getExeUserId()));
					}
				}
			}
			if(!AppUtils.isNotEmpty(userList)){
				logger.error("发送人为空");
				return;
			}
			//2、消息提醒的key
			String businessKey = new StringBuffer(String.valueOf(mtTask.getTaskId())).append("_")
					.append(TransNoticeEnum.MT_TASK_TIMEOUT_NOTICE.getCode()).toString();
			
			Date startDate = DataTypeConverUtils.parseLongToDate(mtTask.getBeginTime() * 100);
			Date end = DateUtil.addDateMinute(startDate, null != limitMinute ? limitMinute : mtTask.getLimitMinute());
			
			Integer warnMinite = MtConstant.WARN_MINUTE;
			if (TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode().equals(mtTask.getTaskType())) {
				// 调度任务才有小类,获取该运输类型的预警时间
				List<Integer> transTypeIdList = new ArrayList<Integer>();
				transTypeIdList.add(mtTask.getTransTypeId());
				List<TransTypeInfo> TransTypeInfoList = MtCommonServiceUtils.getTransTypeInfoByTransTypeIdList(transTypeIdList);
				if(AppUtils.isNotEmpty(TransTypeInfoList) && TransTypeInfoList.get(0).getWarnMinite()!=null){
					warnMinite = Integer.valueOf(TransTypeInfoList.get(0).getWarnMinite());
				}
			}
			//3、获取预警发送时间
			Date sendDate = DateUtil.addDateMinute(end,warnMinite*(-1));
			if(sendDate==null){
				logger.error("预警发送时间为空");
				return;
			}
			logger.debug("===============scheduleSendAppMessage{}{}{}----------->Start"+ DateUtil.nowDateToStringYYMMDDHHmmss());

			//4、发送
			appMessageUtil.scheduleSendAppMessage(userList, mtTask.getTaskId(), 
					businessKey,sendDate.getTime(), MtNoticeConstant.MT_TASK_TIMEOUT_NOTICE, taskType);
			logger.debug("===============scheduleSendAppMessage{}{}{}----------->End"+ DateUtil.nowDateToStringYYMMDDHHmmss());
		}catch (Exception e) {
			logger.error("sendTaskTimeOutNotice",e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除即将超时通知
	 * @param taskId
	 */
	public void delTaskTimeOutNotice(Integer taskId){
		try{
			String serviceId = new StringBuffer(String.valueOf(taskId)).append("_")
					.append(TransNoticeEnum.MT_TASK_TIMEOUT_NOTICE.getCode()).toString();
			appMessageUtil.delAppMessage(String.valueOf(serviceId));
		}catch (Exception e) {
			logger.error("delOvertimeNotFull",e);
			e.printStackTrace();
		}
	}

	/**
	 * @discription 发送即时提醒
	 * @author zhangyang@segimail.com       
	 * @created 2018年6月11日 上午11:18:27     
	 * @param userIds
	 * @param taskId
	 * @param bussinessType
	 * @param argArray
	 */
	public void sendTaskNotice(String userIds, String taskId, String bussinessType, String taskType, Object... argArray) {
		try {
			appMessageUtil.syncSendAppMessage(userIds, taskId, bussinessType, taskType, argArray);
		} catch (Exception e) {
			logger.error("发送提醒系统错误", e);
			e.printStackTrace();
		}
	}

	/**
	 * @discription 发送即时提醒
	 * @author zhangyang@segimail.com       
	 * @created 2018年6月13日 下午3:00:01      
	 * @param userIdList
	 * @param taskId
	 * @param bussinessType
	 * @param argArray     
	 */
	public void sendTaskNotice(List<String> userIdList, String taskId,
			String bussinessType, String taskType, Object... argArray) {
		try {
			appMessageUtil.syncSendAppMessage(userIdList, taskId, bussinessType, taskType, argArray);
		} catch (Exception e) {
			logger.error("发送提醒系统错误", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @discription 延迟发送超时未开始提醒
	 * @author wangxiong@segimail.com       
	 * @created 2018年6月7日 下午3:12:31      
	 * @param userId
	 * @param nowDate
	 * @param taskId     
	 */
	public void sendOvertimeNotStarted(Integer userId,Date beginTimeDate, Date nowDate, Integer taskId, String taskType) {
		try {
			List<String> userList = new ArrayList<>();
			userList.add(String.valueOf(userId));
			Date sendDate = beginTimeDate.after(nowDate) ? beginTimeDate : nowDate;
			appMessageUtil.scheduleSendAppMessage(userList, taskId,
					new StringBuffer(String.valueOf(taskId)).append("_").append(TransNoticeEnum.MT_TASK_OVERTIME_NOT_STARTED.getCode()).toString(), 
					DateUtil.addDateMinute(sendDate, MtCommonServiceUtils.getTaskNoStartWarnMinute()).getTime()
					, MtNoticeConstant.MT_TASK_OVERTIME_NOT_STARTED, taskType);
		}catch (Exception e) {
			logger.error("sendOvertimeNotStarted",e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @discription  延迟发送超时未开始提醒
	 * @author wangxiong@segimail.com       
	 * @created 2018年6月7日 下午8:15:52     
	 * @param userId
	 * @param nowDate
	 * @param taskId
	 */
	public void sendOvertimeNotStarted(Integer userId, Long beginTime, Date nowDate, Integer taskId, String taskType) {
		Date beginTimeDate = DateUtil.parseStringToDateYYYYMMDDHHMMSS(
				String.valueOf(beginTime * 100));
		sendOvertimeNotStarted(userId, beginTimeDate, nowDate, taskId, taskType);
	}
	
	/**
	 * @discription 删除超时未开始的短消息
	 * @author wangxiong@segimail.com       
	 * @created 2018年6月11日 下午3:18:10     
	 * @param taskId
	 */
	public void delOverTimeNotStarted(Integer taskId) {
		try {
			appMessageUtil.delAppMessage(new StringBuffer(String.valueOf(taskId))
						.append("_").append(TransNoticeEnum.MT_TASK_OVERTIME_NOT_STARTED.getCode()).toString());
		} catch (Exception e) {
			logger.error("delOverTimeNotStarted",e);
			e.printStackTrace();
		}
	}
	
	// 调度任务派单提醒
	public void dispatchSendAppMessage(List<String> userList,
			MtTask beforeMtTask, String dataType, Long endTime) {
		try {
			String resTypeName = "";
			if (TransDispatchTypeEnum.TRANS_DISPATCH_TYPE_02.getCode()
					.equals(beforeMtTask.getResType())) {
				resTypeName = "（抢单）";
			}
			List<Integer> locationIdList = new ArrayList<Integer>();
			locationIdList.add(beforeMtTask.getFromHouseId());
			locationIdList.add(beforeMtTask.getToHouseId());
			List<MtLocationInfoIce> locationInfoList = MtCommonServiceUtils.getLocationInfoList(beforeMtTask.getOrganId(), locationIdList);
			Map<String, MtLocationInfoIce> locationInfoMap = AppUtils.list2Map(locationInfoList, "locationId", MtLocationInfoIce.class);
			String fromHouseName = locationInfoMap.get(String.valueOf(beforeMtTask.getFromHouseId())) != null 
					&& StringUtils.isNotBlank(locationInfoMap.get(String.valueOf(beforeMtTask.getFromHouseId())).getLocationName()) 
					? locationInfoMap.get(String.valueOf(beforeMtTask.getFromHouseId())).getLocationName() : "";
			String toHouseIdName = locationInfoMap.get(String.valueOf(beforeMtTask.getToHouseId())) != null 
					&& StringUtils.isNotBlank(locationInfoMap.get(String.valueOf(beforeMtTask.getToHouseId())).getLocationName()) 
					? locationInfoMap.get(String.valueOf(beforeMtTask.getToHouseId())).getLocationName() : "";
			String beginTime = DateUtil.formatDateToString(new Date(DataTypeConverUtils
	    			.parseLongToDate(beforeMtTask.getBeginTime()*100).getTime()), MtConstant.FORMAT_HH_MM);
			String noticeEndTime = DataTypeConverUtils.paresNumberToString(endTime, 
					DateUtil.FORMAT_YYYY_MM_DD_HH_MM, MtConstant.FORMAT_HH_MM);
			appMessageUtil.syncSendAppMessage(userList, String.valueOf(beforeMtTask.getTaskId()), MtNoticeConstant.MT_TASK_ACCEPT_NOTICE, dataType
					, new Object[] {resTypeName, TransTypeEnum.getNameByCode(beforeMtTask.getTransTypeParentCode()), fromHouseName, toHouseIdName, beginTime, noticeEndTime});
		} catch (Exception e) {
			logger.error("调度任务派单提醒消息发送失败", e);
		}
	}

}
