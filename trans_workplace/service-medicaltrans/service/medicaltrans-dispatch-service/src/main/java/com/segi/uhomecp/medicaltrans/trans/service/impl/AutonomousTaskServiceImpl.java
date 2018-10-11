package com.segi.uhomecp.medicaltrans.trans.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import segi.medicaltrans.common.transType.TransTypeInfo;

import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.constant.MtMqConstant.TaskMqOeration;
import com.segi.uhomecp.medicaltrans.constant.MtNoticeConstant;
import com.segi.uhomecp.medicaltrans.constant.MtSeqContants;
import com.segi.uhomecp.medicaltrans.constant.MtTrackConstant.TaskTrackOprFlag;
import com.segi.uhomecp.medicaltrans.enums.TaskTrackActionEnum;
import com.segi.uhomecp.medicaltrans.enums.TransDispatchTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransStatusEnum;
import com.segi.uhomecp.medicaltrans.enums.TransUserStatusEnum;
import com.segi.uhomecp.medicaltrans.enums.UrgencyEnum;
import com.segi.uhomecp.medicaltrans.trans.dto.AutonomousTaskDto;
import com.segi.uhomecp.medicaltrans.trans.dto.CommonTaskDto;
import com.segi.uhomecp.medicaltrans.trans.dto.TransportMqDto;
import com.segi.uhomecp.medicaltrans.trans.listen.event.TaskTrackEvent;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutors;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutorsLog;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExt;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRoute;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskExtService;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskService;
import com.segi.uhomecp.medicaltrans.trans.service.AutonomousTaskService;
import com.segi.uhomecp.medicaltrans.trans.service.MtCommonTaskService;
import com.segi.uhomecp.medicaltrans.trans.support.AbstractDispatchTransport;
import com.segi.uhomecp.medicaltrans.trans.utils.InitTaskDataUtils;
import com.segi.uhomecp.medicaltrans.trans.utils.TaskSendNoticeUtils;
import com.segi.uhomecp.medicaltrans.trans.utils.TrackMessageUtil;
import com.segi.uhomecp.medicaltrans.trans.utils.UpdateUserPositUnTaskNumUtils;
import com.segi.uhomecp.medicaltrans.utils.DynamicTableSourceKeyHolder;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.utils.SeqContants;
import com.segi.uhomecp.wh.common.dto.ResultDto;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;
import com.segi.uhomecp.wh.common.utils.DataTypeConverUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;

/**
 * Title: AutonomousTaskServiceImpl.java    
 * @Description: 自主任务处理业务类
 * @author wangxiong@segimail.com       
 * @created 2018年3月26日 下午1:56:48
 */
@Service
public class AutonomousTaskServiceImpl
	extends AbstractDispatchTransport<MtTask>
	implements AutonomousTaskService, ApplicationContextAware {
	
	public static final Logger logger = LoggerFactory.getLogger(AutonomousTaskServiceImpl.class);
	
	@Autowired
	private MtTaskService mtTaskService;
	
	@Autowired
	private MtTaskExtService mtTaskExtService;
	
	@Autowired
	private MtCommonTaskService mtCommonTaskService;
	
	@Autowired
	private InitTaskDataUtils initTaskDataUtils;
	
	@Autowired
	private UpdateUserPositUnTaskNumUtils updateUserPositUnTaskNumUtils;
	
	@Autowired
	private TaskSendNoticeUtils taskSendNoticeUtils;
	
	@Autowired
	private TrackMessageUtil trackMessageUtil;
	
	private static ApplicationContext applicationContext;
	
	@Autowired
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext  = applicationContext;
	}
	
	/**
	 * @discription 创建任务
	 * @author wangxiong@segimail.com       
	 * @created 2018年3月28日 下午2:18:52      
	 * @param dto
	 * @return     
	 */
	@Override
	public ResultDto<String, String, Integer> createTask(int groupOrganId, AutonomousTaskDto dto) {
		ResultDto<String,String, Integer> rst = new ResultDto<>(true, "");
		Date nowDate = new Date();
		Integer userId = dto.getUserId();
		Integer taskId = SeqContants.getSequnces(MtSeqContants.MT_TASK_ID_SEQ).intValue();
		MtTask mtTask= BeanCopierUtils.copyProperties(dto, MtTask.class, true);
		mtTask.setBeginTime(DataTypeConverUtils.formatDateToLongYYYYMMDDHHMM(nowDate));
		TransTypeInfo transTypeInfo = MtCommonServiceUtils.getTransTypeInfoByTransTypeId(dto.getTransTypeId());
		Long endTime = null;
		if (null != transTypeInfo) {
			mtTask.setLimitMinute(Integer.valueOf(transTypeInfo.getStandardMinite()));
			// 处理预计完成时间
			endTime = Long.valueOf(DateUtil.formatDateToString(DateUtil.addDateMinute(
					DataTypeConverUtils.parseLongToDate(mtTask.getBeginTime() * 100), 
					mtTask.getLimitMinute()), DateUtil.FORMAT_YYYY_MM_DD_HH_MM));
			mtTask.setEndTime(endTime);
		}
		mtTask.setStatus(TransStatusEnum.TRANS_RUNNING.getCode());
		mtTask.setResType(TransDispatchTypeEnum.TRANS_DISPATCH_TYPE_01.getCode());
		mtTask.setExeBeginTime(nowDate);
		mtTask.setExeEndUserId(userId);
		mtTask.setUrgency(UrgencyEnum.URGENCY_COMMONLY.getCode());
		mtTask.setSendTime(nowDate);
		mtTask.setGroupOrganId(groupOrganId);
		// 处理任务所属时刻
		mtTask.setTaskHour((byte)Integer.parseInt(String.valueOf(mtTask.getBeginTime()).substring(8, 10)));
		// 任务所属年月日
		mtTask.setTaskTime(Integer.valueOf(String.valueOf(mtTask.getBeginTime()).substring(0, 8)));
		// 派单人
		mtTask.setDispatchUserId(userId);
		this.initTaskDataUtils.initSaveMtTask(mtTask, dto.getSourceHouseId(),
				userId, taskId, nowDate);
		mtTaskService.insert(mtTask);
		/*扩展信息保存*/
		MtTaskExt mtTaskExt= BeanCopierUtils.copyProperties(dto, MtTaskExt.class, true);
		mtTaskExt.setTaskExtId(SeqContants.getSequnces(MtSeqContants.MT_TASK_EXT_ID_SEQ).intValue());
		mtTaskExt.setTaskId(taskId);
		mtTaskExt.setGroupOrganId(groupOrganId);
		this.mtTaskExtService.insert(mtTaskExt);
		/* 运送管理运送人信息保存 */
		List<MtTaskExecutors> excutorsList = this.initTaskDataUtils.initMtTaskExcutorsList(mtTask, AppUtils.str2Integer(
				String.valueOf(userId)), nowDate, userId, mtTask.getStatus(), nowDate, null, nowDate, endTime);
		String tableIndex = DynamicTableSourceKeyHolder.getDataSourceKey();
		this.mtCommonTaskService.saveBatchMtTaskExcutors(groupOrganId, excutorsList, tableIndex);
		/* 运送路线信息保存 */
		this.mtCommonTaskService.saveBatchMtTaskRoute(groupOrganId, initMtTaskRouteList(dto, taskId, nowDate, groupOrganId), tableIndex);
		/* 更新人员位置信息表 对人员未完成数量进行加 1 */
		this.updateUserPositUnTaskNumUtils.updateUserPositUnTaskNum(dto.getOrganId(), userId, 
				(short)1, (short)1, mtTask.getBeginTime(), String.valueOf(dto.getFromHouseId()));
		// 处理附件
		if (StringUtils.isNotBlank(dto.getFileIds())) {
			CommonServiceUtils.updateRefIdByFileIds(dto.getFileIds(), String.valueOf(taskId));
		}
		// 给任务单目的地科室人员发送任务即将到达提醒
		List<String> userIds = MtCommonServiceUtils.queryUserIdsByHouseId(mtTask.getToHouseId());
		if (AppUtils.isNotEmpty(userIds)) {
			logger.debug("需要发送提醒的人有：" + userIds.toString());
			taskSendNoticeUtils.sendTaskNotice(userIds, String.valueOf(taskId), 
					MtNoticeConstant.MT_TASK_IS_ARRIVING_NOTICE, null, new Object[]{});
		}
		// 处理 轨迹信息
		TaskTrackEvent trackEvent = new TaskTrackEvent(this, taskId, TaskTrackActionEnum.
				CREATE_TASK.getActionType(), userId, mtTask, nowDate, TaskTrackOprFlag.CREATE_AUTO_FLAG);
		applicationContext.publishEvent(trackEvent);
		// 给mq发送消息
		List<MtTask> taskList = new ArrayList<>();
		taskList.add(mtTask);
		this.create(taskList, String.valueOf(userId), nowDate);
		rst.setObj(taskId);
		return rst;
	}

	/**
	 * @discription 初始化
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月4日 上午11:42:55     
	 * @param dto
	 * @param version
	 * @param taskId2 
	 * @return
	 */
	public List<MtTaskRoute> initMtTaskRouteList(AutonomousTaskDto dto, 
			Integer taskId, Date nowDate, Integer groupOrganId) {
		List<MtTaskRoute> routeList = new ArrayList<>();
		MtTaskRoute mtTaskRoute = new MtTaskRoute();
		mtTaskRoute.setHouseId(dto.getToHouseId());
		mtTaskRoute.setStatus(MtConstant.CLOCK_STATUS_0);
		mtTaskRoute.setIsAutograph(MtConstant.IS_AUTOGRAPH_DEFAULT);
		mtTaskRoute.setGroupOrganId(groupOrganId);
		routeList.add(mtTaskRoute);
		return this.initTaskDataUtils.initMtTaskRouteList(routeList, taskId, nowDate);
	}

	/**
	 * @discription 取消任务
	 * @author wangxiong@segimail.com       
	 * @created 2018年3月28日 下午7:36:51      
	 * @param t
	 * @return     
	 */
	@Override
	public ResultDto<String, String, Integer> handlerCancelTask(int groupOrganId, MtTask mtTask,
			Integer userId) {
		ResultDto<String, String, Integer> rst = new ResultDto<>(true,"");
		Integer taskId = mtTask.getTaskId();
		Date nowDate = new Date();
		// MtTask 表设置取消状态
		MtTask updateMtTask = new MtTask();
		updateMtTask.setStatus(TransStatusEnum.TRANS_CANCEL.getCode());
		updateMtTask.setUpdateBy(userId);
		updateMtTask.setUpdateDate(nowDate);
		List<String> statusList = new ArrayList<>();
		statusList.add(TransStatusEnum.TRANS_RUNNING.getCode());
		int cnt = this.mtCommonTaskService.updateMtTaskStatus(groupOrganId, updateMtTask, statusList, taskId);
		if(cnt <= 0 ) {
			rst.setIsSucc(false);
			rst.setMessage("该运送单已被其他人操作无法进行取消！");
			return rst;
		}
		// 删除执行人表中数据
		this.mtCommonTaskService.deleteTaskExecutorsByTaskId(groupOrganId, taskId);
		// 将取消的执行人新增到执行人log表
		List<MtTaskExecutorsLog> exeUserLog = this.initTaskDataUtils.initExecutorsLog(mtTask, 
				String.valueOf(mtTask.getExeEndUserId()), TransUserStatusEnum.TRANS_USER_STATUS_00.getCode(), nowDate);
		String tableIndex = DynamicTableSourceKeyHolder.getDataSourceKey();
		this.mtCommonTaskService.saveBatchMtTaskExcutorsLog(groupOrganId, exeUserLog, tableIndex);
		/* 更新人员位置信息表 对人员未完成数量进行加-1  */
		this.updateUserPositUnTaskNumUtils.updateUserPositUnTaskNum(mtTask.getOrganId(), mtTask.getExeEndUserId(),
				(short)-1, (short)-1, mtTask.getBeginTime(), null);	
		// 处理 轨迹信息
		TaskTrackEvent trackEvent = new TaskTrackEvent(this, taskId, TaskTrackActionEnum.
				CANCEL_TASK.getActionType(), userId, mtTask, nowDate);
		applicationContext.publishEvent(trackEvent);
		// 给mq发送消息
		this.cancel(mtTask, nowDate);
		rst.setObj(taskId);
		return rst;
	}

	/**
	 * @discription 完成任务
	 * @author wangxiong@segimail.com       
	 * @created 2018年3月28日 下午8:03:13      
	 * @param t
	 * @return     
	 */
	@Override
	public ResultDto<String, String, String> handlerFinishTask(int groupOrganId, MtTask mtTask,
			CommonTaskDto dto) {
		ResultDto<String, String, String> rst = new ResultDto<>(true, "");
		Integer userId = dto.getUserId();
		Integer taskId = dto.getTaskId();
		Date nowDate = new Date();
		long currentTime = nowDate.getTime();
		// 计算耗时 返回秒
		Long taskConsume = (currentTime - mtTask.getExeBeginTime().getTime()) / 1000;
		// 预计开始时间
		long beginTime = DateUtil.parseStringToDate(String.valueOf(mtTask.getBeginTime() + "00"), 
				DateUtil.FORMAT_YYYYMMDDHHMMSS).getTime();
		String isTimeOut = MtConstant.NO_TIME_OUT;
		if (null != mtTask.getLimitMinute() && mtTask.getLimitMinute().intValue() != 0) {
			// 预计结束时间
			long limitMinute = (long) mtTask.getLimitMinute() * 60 * 1000;
			isTimeOut = beginTime + limitMinute - currentTime >= 0 ? MtConstant.NO_TIME_OUT : MtConstant.TIME_OUT;
		}
		if (MtConstant.TIME_OUT.equals(isTimeOut) && StringUtils.isBlank(dto.getTimeOutReason())) {
			// 超时了 没填写超时原因
			rst.setIsSucc(false);
			rst.setMessage("该任务已超时，请填写超时原因！");
			return rst;
		}
		// 修改 EVT_MT_TASK
		MtTask updateMtTask = new MtTask();
		updateMtTask.setExeEndTime(nowDate);
		updateMtTask.setStatus(TransStatusEnum.TRANS_COMPLETED.getCode());
		updateMtTask.setUpdateBy(userId);
		updateMtTask.setUpdateDate(nowDate);
		updateMtTask.setTimeConsuming(taskConsume.intValue());
		updateMtTask.setIsTimeOut(isTimeOut);
		int cnt = this.mtCommonTaskService.updateMtTaskStatus(groupOrganId, updateMtTask, 
				TransStatusEnum.TRANS_RUNNING.getCode(), taskId);
		if (cnt == 0) {
			rst.setIsSucc(false);
			rst.setMessage("该运送单已被其他人操作无法完成任务！");
			return rst;
		}
		// 修改路线是否打卡 
		this.mtCommonTaskService.updateTaskRoute(groupOrganId, mtTask, dto.getFinishContent(), taskId, nowDate);
		// 修改执行人状态
		this.mtCommonTaskService.updateTaskExcutors(groupOrganId, taskId, String.valueOf(mtTask.getExeEndUserId()), null, 
				TransStatusEnum.TRANS_COMPLETED.getCode(), isTimeOut, null, nowDate);
		/* 更新人员位置信息表 对人员未完成数量进行加- 1  */
		this.updateUserPositUnTaskNumUtils.updateUserPositUnTaskNum(mtTask.getOrganId(), userId, (short)-1, 
				(short)-1, mtTask.getBeginTime(), String.valueOf(mtTask.getToHouseId()));
		// 处理附件
		if (StringUtils.isNotBlank(dto.getFinishFileIds())) {
			CommonServiceUtils.updateRefIdByFileIds(dto.getFinishFileIds(), String.valueOf(dto.getRouteId()));
		}
		// 更新个人完成数
		String exeBeginTime = DateUtil.formatDateToString(mtTask.getExeBeginTime(), DateUtil.FORMAT_YYYYMMDDHHMMSS);
		MtCommonServiceUtils.savePersonalVolumeIncrease(mtTask.getOrganId(), String.valueOf(userId), 1, exeBeginTime, mtTask.getTaskType());
		// 给下单人和任务目的地科室人员发送签收评价提醒
		List<String> userIds = initTaskDataUtils.getEvaluateNoticeUserIds(
				mtTask.getToHouseId(), String.valueOf(mtTask.getCreateBy()));
		if (AppUtils.isNotEmpty(userIds)) {
			logger.debug("需要发送提醒的人有：" + userIds.toString());
			taskSendNoticeUtils.sendTaskNotice(userIds, String.valueOf(taskId), 
					MtNoticeConstant.MT_TASK_EVALUATE_NOTICE, null, new Object[]{});
		}
		rst.setObj(String.valueOf(taskId));
		// 处理 轨迹信息
		TaskTrackEvent trackEvent = new TaskTrackEvent(this, taskId, TaskTrackActionEnum.FINISH_TASK.getActionType(), 
				userId, updateMtTask, nowDate, TaskTrackOprFlag.APP_FINISH_FLAG, dto.getTimeOutReason());
		applicationContext.publishEvent(trackEvent);
		// 推送mq
		this.take(updateMtTask, mtTask, String.valueOf(userId), nowDate);
		return rst;
	}

	@Override
	public List<TransportMqDto> createHandler(List<MtTask> t, String exeUserIds, Date nowDate) {
		List<TransportMqDto> mqList = new ArrayList<>();
		TransportMqDto mqDto = null;
		if (AppUtils.isNotEmpty(t)) {
			for (MtTask mtTask : t) {
				mqDto = BeanCopierUtils.copyProperties(mtTask, TransportMqDto.class, true, "HH:mm");
				mqDto.setOperation(TaskMqOeration.CREATE_TASK_MQ);
				mqDto.setTimeStamp(DateUtil.formatDateToStringYYMMDDHHmmss(nowDate));
				mqList.add(mqDto);
			}
		}
		return mqList;
	}

	@Override
	public TransportMqDto takeHandler(MtTask updateTask, MtTask beforeTask, String exeUserIds, Date nowDate) {
		TransportMqDto mqDto = BeanCopierUtils.copyProperties(updateTask, TransportMqDto.class, true, "HH:mm");
		mqDto.setOperation(TaskMqOeration.UPDATE_TASK_MQ);
		mqDto.setTaskId(String.valueOf(beforeTask.getTaskId()));
		mqDto.setTaskType(beforeTask.getTaskType());
		mqDto.setBeforeStatus(beforeTask.getStatus());
		mqDto.setGroupOrganId(String.valueOf(beforeTask.getGroupOrganId()));
		mqDto.setOrganId(String.valueOf(beforeTask.getOrganId()));
		mqDto.setTimeStamp(DateUtil.formatDateToStringYYMMDDHHmmss(nowDate));
		if (null == mqDto.getBeginTime()) {
			// 存在beginTime不为空的情况(pad端编辑任务)
			mqDto.setBeginTime(String.valueOf(beforeTask.getBeginTime()));
		}
		if (StringUtils.isNotBlank(exeUserIds)) {
			mqDto.setExeUserIds(exeUserIds);
		}
		return mqDto;
	}

	@Override
	public TransportMqDto cancelHandler(MtTask mtTask, Date nowDate) {
		TransportMqDto mqDto = new TransportMqDto();
		mqDto.setOperation(TaskMqOeration.CANCEL_TASK_MQ);
		mqDto.setGroupOrganId(String.valueOf(mtTask.getGroupOrganId()));
		mqDto.setOrganId(String.valueOf(mtTask.getOrganId()));
		mqDto.setTaskId(String.valueOf(mtTask.getTaskId()));
		mqDto.setTaskType(mtTask.getTaskType());
		mqDto.setSourceHouseId(String.valueOf(mtTask.getSourceHouseId()));
		mqDto.setBeforeStatus(mtTask.getStatus());
		mqDto.setUrgency(mtTask.getUrgency());
		mqDto.setBeginTime(String.valueOf(mtTask.getBeginTime()));
		mqDto.setTimeStamp(DateUtil.formatDateToStringYYMMDDHHmmss(nowDate));
		return mqDto;
	}
	
	@Override
	public TransportMqDto grabHandler(MtTask v, String exeEndUserId, Date nowDate) {
		// 自主任务没有抢单
		return null;
	}

	/**
	 * @Title: handlerFinishWebTask 
	 * @Description:  完成任务Web 
	 * @author liuyi@segimail.com 
	 * @date 2018年9月12日上午11:25:59
	 */
	@Override
	public ResultDto<String, String, String> handlerFinishWebTask(
			Integer groupOrganId, MtTask mtTask, CommonTaskDto dto) {
		ResultDto<String, String, String> rst = new ResultDto<>(true, "");
		Integer userId = dto.getUserId();
		Integer taskId = dto.getTaskId();
		Date nowDate = new Date();
		long currentTime = nowDate.getTime();
		// 计算耗时 返回秒
		Long taskConsume = (currentTime - mtTask.getExeBeginTime().getTime()) / 1000;
		// 预计结束时间
		long endTime = DateUtil.parseStringToDate(String.valueOf(mtTask.getEndTime() + "00"), 
				DateUtil.FORMAT_YYYYMMDDHHMMSS).getTime();
		// 是否超时
		String isTimeOut = endTime - currentTime >= 0 ? MtConstant.NO_TIME_OUT : MtConstant.TIME_OUT;
		// 修改 EVT_MT_TASK
		MtTask updateMtTask = new MtTask();
		updateMtTask.setExeEndTime(nowDate);
		updateMtTask.setStatus(TransStatusEnum.TRANS_COMPLETED.getCode());
		updateMtTask.setUpdateBy(userId);
		updateMtTask.setUpdateDate(nowDate);
		updateMtTask.setTimeConsuming(taskConsume.intValue());
		updateMtTask.setIsTimeOut(isTimeOut);
		int cnt = this.mtCommonTaskService.updateMtTaskStatus(groupOrganId, updateMtTask, 
				TransStatusEnum.TRANS_RUNNING.getCode(), taskId);
		if (cnt == 0) {
			rst.setIsSucc(false);
			rst.setMessage("该运送单已被其他人操作无法完成任务！");
			return rst;
		}
		// 修改路线是否打卡 
		this.mtCommonTaskService.updateTaskRoute(groupOrganId, mtTask, dto.getFinishContent(), taskId, nowDate);
		// 修改执行人状态
		this.mtCommonTaskService.updateTaskExcutors(groupOrganId, taskId, String.valueOf(mtTask.getExeEndUserId()), null, 
				TransStatusEnum.TRANS_COMPLETED.getCode(), isTimeOut, null, nowDate);
		/* 更新人员位置信息表 对人员未完成数量进行加- 1  */
		this.updateUserPositUnTaskNumUtils.updateUserPositUnTaskNum(mtTask.getOrganId(), mtTask.getExeEndUserId(), (short)-1, 
				(short)-1, mtTask.getBeginTime(), String.valueOf(mtTask.getToHouseId()));
		// 处理附件
//		if (StringUtils.isNotBlank(dto.getFinishFileIds())) {
//			CommonServiceUtils.updateRefIdByFileIds(dto.getFinishFileIds(), String.valueOf(dto.getRouteId()));
//		}
		// 更新个人完成数
		String exeBeginTime = DateUtil.formatDateToString(mtTask.getExeBeginTime(), DateUtil.FORMAT_YYYYMMDDHHMMSS);
		MtCommonServiceUtils.savePersonalVolumeIncrease(mtTask.getOrganId(), String.valueOf(mtTask.getExeEndUserId()), 1, exeBeginTime, mtTask.getTaskType());
		// 给下单人和任务目的地科室人员发送签收评价提醒
		List<String> userIds = initTaskDataUtils.getEvaluateNoticeUserIds(
				mtTask.getToHouseId(), String.valueOf(mtTask.getCreateBy()));
		if (AppUtils.isNotEmpty(userIds)) {
			logger.debug("需要发送提醒的人有：" + userIds.toString());
			taskSendNoticeUtils.sendTaskNotice(userIds, String.valueOf(taskId), 
					MtNoticeConstant.MT_TASK_EVALUATE_NOTICE, null, new Object[]{});
		}
		rst.setObj(String.valueOf(taskId));
		// 处理 轨迹信息
		TaskTrackEvent trackEvent = new TaskTrackEvent(this, taskId, TaskTrackActionEnum.
				FINISH_TASK.getActionType(), userId, updateMtTask, nowDate);
		applicationContext.publishEvent(trackEvent);
		// 推送mq
		this.take(updateMtTask, mtTask, String.valueOf(mtTask.getExeEndUserId()), nowDate);
		return rst;
	}

	@Override
	public TransportMqDto editSourceHouseHandler(MtTask v, MtTask beforeTask,
			Date nowDate) {
		return null;
	}
}
