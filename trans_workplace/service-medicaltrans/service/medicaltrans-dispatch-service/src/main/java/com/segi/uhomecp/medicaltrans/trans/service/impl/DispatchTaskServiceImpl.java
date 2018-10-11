package com.segi.uhomecp.medicaltrans.trans.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import segi.datacachesvr.queryInfo.GroupUserBrief;
import segi.medicaltrans.location.common.MtLocationInfoIce;

import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.constant.MtMqConstant.TaskMqOeration;
import com.segi.uhomecp.medicaltrans.constant.MtNoticeConstant;
import com.segi.uhomecp.medicaltrans.constant.MtSeqContants;
import com.segi.uhomecp.medicaltrans.constant.MtTrackConstant.TaskTrackOprFlag;
import com.segi.uhomecp.medicaltrans.enums.TaskTrackActionEnum;
import com.segi.uhomecp.medicaltrans.enums.TransDispatchTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransStatusEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTaskTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransUserStatusEnum;
import com.segi.uhomecp.medicaltrans.trans.cache.MtTaskGrabRedisCache;
import com.segi.uhomecp.medicaltrans.trans.dto.CommonTaskDto;
import com.segi.uhomecp.medicaltrans.trans.dto.DispatchTaskDto;
import com.segi.uhomecp.medicaltrans.trans.dto.TransportMqDto;
import com.segi.uhomecp.medicaltrans.trans.listen.event.TaskTrackEvent;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutors;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutorsLog;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExt;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskGroupRel;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRoute;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRouteCriteria;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskExtService;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskRouteService;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskService;
import com.segi.uhomecp.medicaltrans.trans.service.DispatchTaskService;
import com.segi.uhomecp.medicaltrans.trans.service.MtCommonTaskService;
import com.segi.uhomecp.medicaltrans.trans.support.AbstractDispatchTransport;
import com.segi.uhomecp.medicaltrans.trans.utils.InitTaskDataUtils;
import com.segi.uhomecp.medicaltrans.trans.utils.TaskSendNoticeUtils;
import com.segi.uhomecp.medicaltrans.trans.utils.TrackMessageUtil;
import com.segi.uhomecp.medicaltrans.trans.utils.UpdateUserPositUnTaskNumUtils;
import com.segi.uhomecp.medicaltrans.utils.DynamicTableSourceKeyHolder;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.medicaltrans.utils.MtIbatchQueryServiceUtils;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.utils.SeqContants;
import com.segi.uhomecp.wh.common.dto.ResultDto;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;
import com.segi.uhomecp.wh.common.utils.DataTypeConverUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;

/**
 * 
 * 调度任务处理业务类
 * @param <E>
 *
 */
@Service
public class DispatchTaskServiceImpl<E> 
	extends AbstractDispatchTransport<MtTask>
	implements DispatchTaskService, ApplicationContextAware {

	public static final Logger logger = LoggerFactory.getLogger(DispatchTaskService.class);
	
	@Autowired
	private MtTaskService mtTaskService;
	
	@Autowired
	private MtTaskExtService mtTaskExtService;
	
	@Autowired
	private MtTaskRouteService mtTaskRouteService;
	
	@Autowired
	private MtCommonTaskService mtCommonTaskService;
	
	@Autowired
	private MtTaskGrabRedisCache mtTaskGrabRedisCache;
	
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
	 * @created 2018年4月9日 下午7:46:52      
	 * @param t
	 * @return     
	 */
	@Override
	public ResultDto<String, String,Integer> createTask(int groupOrganId, DispatchTaskDto dto) {
		ResultDto<String,String, Integer> rst = new ResultDto<>(true, "");
		Date nowDate = new Date();
		Integer userId = dto.getUserId();
		Integer taskId = SeqContants.getSequnces(MtSeqContants.MT_TASK_ID_SEQ).intValue();
		MtTask mtTask= BeanCopierUtils.copyProperties(dto, MtTask.class, true);
		// 计划开始时间
		Date beginTime = null;
		this.initTaskDataUtils.initSaveMtTask(mtTask, dto.getSourceHouseId(),
				userId, taskId, nowDate, dto.getTransPersonCount());
		beginTime = initBeginTimeAndTaskHour(dto, nowDate, mtTask);
		mtTask.setGroupOrganId(groupOrganId);
		// 处理任务所属时刻
		mtTask.setTaskHour((byte)Integer.parseInt(String.valueOf(mtTask.getBeginTime()).substring(8, 10)));
		// 任务所属年月日
		mtTask.setTaskTime(Integer.valueOf(String.valueOf(mtTask.getBeginTime()).substring(0, 8)));
		// 设置当前用户为派单人
		mtTask.setDispatchUserId(userId);
		// 处理预计完成时间
		Long endTime = Long.valueOf(DateUtil.formatDateToString(DateUtil.addDateMinute(
				DataTypeConverUtils.parseLongToDate(mtTask.getBeginTime() * 100), 
				mtTask.getLimitMinute()), DateUtil.FORMAT_YYYY_MM_DD_HH_MM));
		mtTask.setEndTime(endTime);
		// 业务表insert
		mtTaskService.insert(mtTask);
		
		/*扩展信息保存*/
		MtTaskExt mtTaskExt= BeanCopierUtils.copyProperties(dto, MtTaskExt.class, true);
		mtTaskExt.setTaskExtId(SeqContants.getSequnces(MtSeqContants.MT_TASK_EXT_ID_SEQ).intValue());
		mtTaskExt.setTaskId(taskId);
		mtTaskExt.setGroupOrganId(groupOrganId);
		this.mtTaskExtService.insert(mtTaskExt);
		String tableIndex = DynamicTableSourceKeyHolder.getDataSourceKey();
		/* 运送路线信息保存 */
		this.mtCommonTaskService.saveBatchMtTaskRoute(groupOrganId, initMtTaskRouteList(dto, taskId, nowDate, groupOrganId), tableIndex);
		// 任务接收提醒所需数据
		Map<String, MtLocationInfoIce> houseInfoMap = initTaskDataUtils.getHouseInfoByTask(mtTask);
		// 出发地
		String fromHouseName = !houseInfoMap.isEmpty() && null != houseInfoMap.get(String.valueOf(mtTask.getFromHouseId()))
				? houseInfoMap.get(String.valueOf(mtTask.getFromHouseId())).getLocationName() : "";
		// 目的地	
		String toHouseName = !houseInfoMap.isEmpty() && null != houseInfoMap.get(String.valueOf(mtTask.getToHouseId()))
				? houseInfoMap.get(String.valueOf(mtTask.getToHouseId())).getLocationName() : "";
		//开始结束时间
		String noticeBeginTime = DataTypeConverUtils.paresNumberToString(mtTask.getBeginTime(), 
				DateUtil.FORMAT_YYYY_MM_DD_HH_MM, "HH:mm");
		// 预计结束时间
		String noticeEndTime = DataTypeConverUtils.paresNumberToString(mtTask.getEndTime(), 
				DateUtil.FORMAT_YYYY_MM_DD_HH_MM, "HH:mm");
		// 大类名称
		String transTypeName = TransTypeEnum.getNameByCode(mtTask.getTransTypeParentCode());
		String resTypeName = "";
		/* 运送管理运送人信息保存 */
		if (TransDispatchTypeEnum.TRANS_DISPATCH_TYPE_01.getCode().equals(dto.getResType())) {
			//指定,新增执行人
			List<MtTaskExecutors> excutorsList = initTaskDataUtils.initMtTaskExcutorsList(mtTask, dto.getExeUserIds(), 
					nowDate, dto.getExeEndUserId(), mtTask.getStatus(), mtTask.getSendTime(), endTime);
			this.mtCommonTaskService.saveBatchMtTaskExcutors(groupOrganId, excutorsList, tableIndex);
			/* 更新人员位置信息表 对人员未完成数量进行加 1  */
			List<Integer> exeUserIdList = AppUtils.str2Integer(dto.getExeUserIds());
			this.updateUserPositUnTaskNumUtils.updateUserPositUnTaskNum(dto.getOrganId(),
					exeUserIdList, (short)1, (short)0, mtTask.getBeginTime(), null);
			//当为指定的类型时，创建任务给主责人发送任务即将超时通知
			this.taskSendNoticeUtils.sendTaskTimeOutNotice(mtTask, String.valueOf(mtTask.getExeEndUserId()), null, 
					TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode(), mtTask.getLimitMinute());
			// 发送任务接收提醒
			this.taskSendNoticeUtils.sendTaskNotice(AppUtils.listToString(exeUserIdList, ','), 
					String.valueOf(taskId), MtNoticeConstant.MT_TASK_ACCEPT_NOTICE, TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode(), new Object[]{
					resTypeName, transTypeName, fromHouseName, toHouseName, noticeBeginTime, noticeEndTime});
		}
		if (TransDispatchTypeEnum.TRANS_DISPATCH_TYPE_02.getCode().equals(dto.getResType())) {
			//抢单,新增服务处
			List<Integer> serviceGroupIds = AppUtils.str2Integer(dto.getServiceGroupIds());
			if(!AppUtils.isNotEmpty(serviceGroupIds)) {
				throw new RuntimeException("服务组信息为空，无法创建抢单任务");
			}
			this.mtCommonTaskService.saveBatchMtTaskGroup(groupOrganId, initTaskGroupListToSave(
					taskId, serviceGroupIds, userId, nowDate, groupOrganId), tableIndex);
			//抢单服务需要新增到redis缓存中
			this.mtTaskGrabRedisCache.zaddGradTaskDep(serviceGroupIds, mtTask);
			//抢单延时发送消息（未满员时）
			this.taskSendNoticeUtils.sendOvertimeNotFull(dto.getUserId(), nowDate, taskId, TransTaskTypeEnum.TASK_TYPE_GRAD.getCode());
			
			resTypeName = "（抢单）";
			// 服务处所有人员发送任务接收提醒
			List<String> userIdList = initTaskDataUtils.getUserListByGroupIds(dto.getServiceGroupIds());
			if (AppUtils.isNotEmpty(userIdList)) {
				// 获取这些服务组下班人员list //TODO
				List<String> leaveUserList = new ArrayList<>();
				userIdList.removeAll(leaveUserList);
				taskSendNoticeUtils.sendTaskNotice(userIdList, String.valueOf(taskId), 
						MtNoticeConstant.MT_TASK_ACCEPT_NOTICE, TransTaskTypeEnum.TASK_TYPE_GRAD.getCode(), new Object[]{resTypeName, transTypeName, fromHouseName, toHouseName,
						noticeBeginTime, noticeEndTime});
			}
		}
		// 未开始需要
		if(TransStatusEnum.TRANS_NOT_START.getCode().equals(mtTask.getStatus())) {
			// 延迟发送消息
			this.taskSendNoticeUtils.sendOvertimeNotStarted(userId, beginTime, nowDate, taskId, TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode());
		}
		// 处理附件
		if (StringUtils.isNotBlank(dto.getFileIds())) {
			CommonServiceUtils.updateRefIdByFileIds(dto.getFileIds(), String.valueOf(taskId));
		}
		// 处理 轨迹信息
		TaskTrackEvent trackEvent = new TaskTrackEvent(this, taskId, TaskTrackActionEnum.
				CREATE_TASK.getActionType(), userId, mtTask, nowDate, TaskTrackOprFlag.WEB_CERATE_FLAG);
		applicationContext.publishEvent(trackEvent);
		// 给mq发送消息
		List<MtTask> taskList = new ArrayList<>();
		taskList.add(mtTask);
		this.create(taskList, dto.getExeUserIds(), nowDate);
		rst.setObj(taskId);
		return rst;
	}

	private Date initBeginTimeAndTaskHour(DispatchTaskDto dto, Date nowDate,
			MtTask mtTask) {
		Date beginTime;
		if (MtConstant.IS_RESERVED_FLAG.equals(dto.getIsReservedFlag())) {
			//没预约开始时间
			mtTask.setBeginTime(DataTypeConverUtils.formatDateToLongYYYYMMDDHHMM(nowDate));
			beginTime = nowDate;
		} else {
			beginTime = DateUtil.parseStringToDateYYYYMMDDHHMMSS(
					String.valueOf(dto.getBeginTime() * 100));
			// 预约了开始时间
			if (beginTime.getTime() < nowDate.getTime()) {
				// 预约时间小于当前时间,设置当前时间为预计开始时间(出现情况,新建任务单时一直没点提交)
				mtTask.setBeginTime(DataTypeConverUtils.formatDateToLongYYYYMMDDHHMM(nowDate));
				beginTime = nowDate;
			} else {
				mtTask.setBeginTime(dto.getBeginTime());
			}
		}
		return beginTime;
	}

	/**
	 * @discription 取消任务
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月9日 下午7:47:16      
	 * @param t
	 * @return     
	 */
	@Override
	public ResultDto<String, String, Integer> handlerCancelTask(int groupOrganId, MtTask mtTask,
			Integer userId, Integer userOrganId) {
		ResultDto<String, String, Integer> rst = new ResultDto<>(true,"");
		Integer taskId = mtTask.getTaskId();
		Date nowDate = new Date();
		// MtTask 表设置取消状态
		MtTask updateMtTask = new MtTask();
		updateMtTask.setStatus(TransStatusEnum.TRANS_CANCEL.getCode());
		updateMtTask.setUpdateBy(userId);
		updateMtTask.setUpdateDate(nowDate);
		List<String> statusList = new ArrayList<>();
		statusList.add(TransStatusEnum.TRANS_NON_DISPATCH.getCode());
		statusList.add(TransStatusEnum.TRANS_NOT_START.getCode());
		statusList.add(TransStatusEnum.TRANS_ROBBING.getCode());
		statusList.add(TransStatusEnum.TRANS_BACK.getCode());
		int cnt = this.mtCommonTaskService.updateMtTaskStatus(groupOrganId, updateMtTask, statusList, taskId);
		if(cnt <= 0 ) {
			rst.setIsSucc(false);
			rst.setMessage("该运送单已被其他人操作无法进行取消！");
			return rst;
		}
		List<MtTaskExecutors> userList = mtCommonTaskService.queryMtTaskExcutorsByTaskId(groupOrganId, taskId, null);
		List<Integer> userIds = AppUtils.list2ParamsList(userList, MtTaskExecutors.class, "exeUserId");
		String tableIndex = DynamicTableSourceKeyHolder.getDataSourceKey();
		if (AppUtils.isNotEmpty(userIds)) {
			// 删除执行人表信息
			this.mtCommonTaskService.deleteTaskExecutorsByTaskId(groupOrganId, taskId);
			// 将取消的执行人添加带log表
			List<MtTaskExecutorsLog> exeUserLogList = this.initTaskDataUtils.initExecutorsLog(mtTask, userIds, 
					TransUserStatusEnum.TRANS_USER_STATUS_00.getCode(), nowDate);
			this.mtCommonTaskService.saveBatchMtTaskExcutorsLog(groupOrganId, exeUserLogList, tableIndex);
		}
		if (TransDispatchTypeEnum.TRANS_DISPATCH_TYPE_02.getCode().equals(mtTask.getResType()) 
				&& TransStatusEnum.TRANS_ROBBING.getCode().equals(mtTask.getStatus())) {
			List<MtTaskGroupRel> mtTaskGroupList = this.mtCommonTaskService.queryServiceByTaskId(groupOrganId, taskId);
			List<Integer> groupIds = AppUtils.list2ParamsList(mtTaskGroupList, MtTaskGroupRel.class, "groupId");
			// 之前任务单为抢单时,删除服务处
			this.mtCommonTaskService.deleteMtTaskGroupByTaskId(groupOrganId, taskId);
			// 删除缓存中的数据
			this.mtTaskGrabRedisCache.hdelGradTaskDep(groupIds, taskId);
			//删除抢单未满员提醒
			this.taskSendNoticeUtils.delOvertimeNotFull(taskId);
		} 
		if (AppUtils.isNotEmpty(userList)) {
			List<String> exeUserList = AppUtils.list2ParamsList(userList, MtTaskExecutors.class, "exeUserId");
			//查询已指定或已抢单的人员信息,并将其未完成任务数-1
			this.updateUserPositUnTaskNumUtils.updateUserPositUnTaskNum(userList, mtTask, null);
			// 执行人发送取消提醒
			taskSendNoticeUtils.sendTaskNotice(AppUtils.listToString(exeUserList, ','), String.valueOf(mtTask.getTaskId()), 
					MtNoticeConstant.MT_TASK_CANCEL_NOTICE,TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode());
		}
		//删除任务超时提醒
		this.taskSendNoticeUtils.delTaskTimeOutNotice(taskId);
		this.taskSendNoticeUtils.delOverTimeNotStarted(taskId);
		// 处理 轨迹信息
		TaskTrackEvent trackEvent = new TaskTrackEvent(this, taskId, TaskTrackActionEnum.
				CANCEL_TASK.getActionType(), userId, mtTask, nowDate);
		applicationContext.publishEvent(trackEvent);
		// 给mq发送消息
		this.cancel(mtTask, nowDate);
		rst.setObj(taskId);
		return rst;
	}

	@Override
	public ResultDto<String, String, String> handlerFinishTask(int groupOrganId, MtTask mtTask,
			CommonTaskDto dto) {
		ResultDto<String, String, String> rst = new ResultDto<>(true, "");
		Integer organId = mtTask.getOrganId();
		Integer userId = dto.getUserId();
		Integer taskId = dto.getTaskId();
		String taskUserIds = dto.getTaskUserIds();
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
		/** 完成后更新执行人 **/
		Map<String, List<Integer>> map = updateMtTaskExcutors(groupOrganId, mtTask, taskUserIds, nowDate, isTimeOut);
		List<Integer> updateTaskNum = map.get("updateTaskNum");
		this.mtCommonTaskService.updateTaskExcutors(groupOrganId, taskId, AppUtils.str2Integer(taskUserIds), null, 
				TransStatusEnum.TRANS_COMPLETED.getCode(), isTimeOut, null, nowDate);
		/* 更新人员位置信息表 对人员未完成数量进行加- 1  */
		this.updateUserPositUnTaskNumUtils.updateUserPositUnTaskNum(organId, updateTaskNum, (short)-1, 
				(short)-1, mtTask.getBeginTime(), String.valueOf(mtTask.getToHouseId()));
		/* 更新人员最新位置 */
		List<Integer> updatePosition = map.get("updatePosition");
		if (AppUtils.isNotEmpty(updatePosition)) {
			MtCommonServiceUtils.updateUserNewLocation(organId, updatePosition, mtTask.getToHouseId());
		}
		// 更新个人完成数
		String exeBeginTime = DateUtil.formatDateToString(mtTask.getExeBeginTime(), DateUtil.FORMAT_YYYYMMDDHHMMSS);
		MtCommonServiceUtils.savePersonalVolumeIncrease(organId, taskUserIds, 1, exeBeginTime, mtTask.getTaskType());
		//处理附件
		if (StringUtils.isNotBlank(dto.getFinishFileIds())) {
			CommonServiceUtils.updateRefIdByFileIds(dto.getFinishFileIds(), String.valueOf(dto.getRouteId()));
		}
		//完成时删除任务超时提醒
		this.taskSendNoticeUtils.delTaskTimeOutNotice(taskId);
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
		this.take(updateMtTask, mtTask, taskUserIds, nowDate);
		return rst;
	}

	/**
	 * @discription 更新医疗任务完成执行人更新
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月3日 下午2:39:35     
	 * @param mtTask
	 * @param dto
	 * @param taskId
	 * @param nowDate
	 */
	private Map<String, List<Integer>> updateMtTaskExcutors(int groupOrganId, MtTask mtTask, String taskUserIds,
			 Date nowDate, String isTimeOut) {
		Map<String, List<Integer>> map = new HashMap<>();
		Set<Integer> rst = new HashSet<>();
		List<MtTaskExecutors> excutorsList = this.mtCommonTaskService.queryMtTaskExcutorsByTaskId(groupOrganId, mtTask.getTaskId(), null);
		List<Integer> delExcutorsList = new ArrayList<>();
		List<Integer> taskUserList = AppUtils.str2Integer(taskUserIds);
		for (MtTaskExecutors excutors : excutorsList) {
			Integer exeUserId = excutors.getExeUserId();
			if(!taskUserList.contains(exeUserId)) {
				delExcutorsList.add(exeUserId);
			}
			// 删除数据库已经存在的
			taskUserList.remove(exeUserId);
			rst.add(exeUserId);
		}
		String tableIndex = DynamicTableSourceKeyHolder.getDataSourceKey();
		if (AppUtils.isNotEmpty(taskUserList)) {
			List<MtTaskExecutors> list = initTaskDataUtils.initMtTaskExcutorsList(mtTask, taskUserList, nowDate, mtTask.getExeEndUserId(), 
					TransStatusEnum.TRANS_COMPLETED.getCode(), mtTask.getExeBeginTime(), isTimeOut, mtTask.getSendTime(), mtTask.getEndTime());
			this.mtCommonTaskService.saveBatchMtTaskExcutors(groupOrganId, list, tableIndex);
		}
		if (AppUtils.isNotEmpty(delExcutorsList)) {
			// 执行人表删掉
			this.mtCommonTaskService.deleteTaskExecutorsByTaskId(groupOrganId, mtTask.getTaskId(), delExcutorsList);
			List<MtTaskExecutorsLog> exeUserLogList = this.initTaskDataUtils.initExecutorsLog(
					mtTask, delExcutorsList, TransUserStatusEnum.TRANS_USER_STATUS_00.getCode(), nowDate);
			this.mtCommonTaskService.saveBatchMtTaskExcutorsLog(groupOrganId, exeUserLogList, tableIndex);
		}
		// 要更新任务完成数的list
		map.put("updateTaskNum", new ArrayList<Integer>(rst));
		// 完成任务添加的人员要定位的list
		map.put("updatePosition", taskUserList);
		return map;
	} 

	/**
	 * @discription 调度任务开始
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月4日 下午5:29:08      
	 * @param dto
	 * @param mtTask
	 * @return     
	 */
	@Override
	public ResultDto<String, String, String> handlerStartTask(int groupOrganId, CommonTaskDto dto, MtTask mtTask) {
		ResultDto<String, String, String> rst = new ResultDto<>(true, "");
		Integer organId = mtTask.getOrganId();
		Integer taskId = mtTask.getTaskId();
		Integer userId = dto.getUserId();
		MtTask updateMtTask = new MtTask();
		Date nowDate = new Date();
		long currentTime = nowDate.getTime();
		// 预计开始时间
		long beginTime = DateUtil.parseStringToDate(String.valueOf(mtTask.getBeginTime() + "00"), 
				DateUtil.FORMAT_YYYYMMDDHHMMSS).getTime();
		// 响应时间 返回秒
		Integer respTime = 0;
		if (currentTime - beginTime > 0) {
			Long resp = (currentTime - beginTime) / 1000;
			respTime = resp.intValue();
		}
		updateMtTask.setExeBeginTime(nowDate);
		updateMtTask.setUpdateBy(userId);
		updateMtTask.setUpdateDate(nowDate);
		updateMtTask.setStatus(TransStatusEnum.TRANS_RUNNING.getCode());
		updateMtTask.setRespTime(respTime);
		int cnt = this.mtCommonTaskService.updateMtTaskStatus(groupOrganId, updateMtTask,
				TransStatusEnum.TRANS_NOT_START.getCode(), taskId);
		if (cnt == 0) {
			rst.setIsSucc(false);
			rst.setMessage("该运送单已被其他人操作无法开始任务！");
			return rst;
		}
		List<MtTaskExecutors> list = this.mtCommonTaskService.queryMtTaskExcutorsByTaskId(groupOrganId, taskId, null);
		List<Integer> userIds = AppUtils.list2ParamsList(list, MtTaskExecutors.class, "exeUserId");
		this.mtCommonTaskService.updateTaskExcutors(groupOrganId, taskId, userIds, null, 
				TransStatusEnum.TRANS_RUNNING.getCode(), null, nowDate, null);
		if (TransDispatchTypeEnum.TRANS_DISPATCH_TYPE_02.getCode().equals(mtTask.getResType())) {
			// 响应类型是抢单,开始时删除服务处表数据
			this.mtCommonTaskService.deleteMtTaskGroupByTaskId(groupOrganId, mtTask.getTaskId());
		}
		/* 更新人员位置信息表 进行中任务+1  */
		this.updateUserPositUnTaskNumUtils.updateUserPositUnTaskNum(organId, userIds, (short)0, (short)1, 
				mtTask.getBeginTime(), String.valueOf(mtTask.getFromHouseId()));
		
		this.taskSendNoticeUtils.delOverTimeNotStarted(taskId);
		//当调度任务开始的时候给除责任人的全体执行人发送任务即将超时的提醒
		if (list.size() > 1) {
			this.taskSendNoticeUtils.sendTaskTimeOutNotice(mtTask, null, list, 
					TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode(), mtTask.getLimitMinute());
		}
		// 给任务单目的地科室人员发送任务即将到达提醒
		List<String> noticeUserIds = MtCommonServiceUtils.queryUserIdsByHouseId(mtTask.getToHouseId());
		if (AppUtils.isNotEmpty(noticeUserIds)) {
			logger.debug("需要发送提醒的人有：" + noticeUserIds.toString());
			taskSendNoticeUtils.sendTaskNotice(noticeUserIds, String.valueOf(taskId), 
					MtNoticeConstant.MT_TASK_IS_ARRIVING_NOTICE, null, new Object[]{});
		}
		rst.setObj(String.valueOf(mtTask.getTaskId()));
		// 处理 轨迹信息
		TaskTrackEvent trackEvent = new TaskTrackEvent(this, taskId, 
				TaskTrackActionEnum.BEGIN_TASK.getActionType(), userId, mtTask, nowDate);
		applicationContext.publishEvent(trackEvent);
		// 推送mq
		this.take(updateMtTask, mtTask, AppUtils.listToString(userIds, ','), nowDate);
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
				mqDto.setCreateDate(DateUtil.formatDateToString(nowDate, "HH:mm"));
				if (StringUtils.isNotBlank(exeUserIds)) {
					mqDto.setExeUserIds(exeUserIds);
				}
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
	public TransportMqDto grabHandler(MtTask mtTask, String exeEndUserId, Date nowDate) {
		TransportMqDto mqDto = new TransportMqDto();
		mqDto.setOperation(TaskMqOeration.GRAB_TASK_MQ);
		mqDto.setGroupOrganId(String.valueOf(mtTask.getGroupOrganId()));
		mqDto.setOrganId(String.valueOf(mtTask.getOrganId()));
		mqDto.setTaskId(String.valueOf(mtTask.getTaskId()));
		mqDto.setBeginTime(String.valueOf(mtTask.getBeginTime()));
		mqDto.setTimeStamp(DateUtil.formatDateToStringYYMMDDHHmmss(nowDate));
		mqDto.setExeEndUserId(exeEndUserId);
		return mqDto;
	}
	
	/**
	 * @discription 创建Pad任务单
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月18日 下午6:40:14      
	 * @param dto
	 * @return     
	 */
	@Override
	public ResultDto<String, String, Integer> createPadTask(
			int groupOrganId, DispatchTaskDto dto) {
		ResultDto<String,String, Integer> rst = new ResultDto<>(true, "");
		Date nowDate = new Date();
		Integer userId = dto.getUserId();
		Integer taskId = SeqContants.getSequnces(MtSeqContants.MT_TASK_ID_SEQ).intValue();
		MtTask mtTask= BeanCopierUtils.copyProperties(dto, MtTask.class, true);
		// 验证任务来源
		Integer sourceHouseId = mtTask.getSourceHouseId();
		if (null == sourceHouseId || sourceHouseId.intValue() <= 0) {
			sourceHouseId = null != dto.getUserHouseId() && dto.getUserHouseId().intValue() > 0
					? dto.getUserHouseId() : dto.getFromHouseId();
		}
		this.initTaskDataUtils.initSaveMtTask(mtTask, sourceHouseId,
				userId, taskId, nowDate, dto.getTransPersonCount());
		mtTask.setStatus(TransStatusEnum.TRANS_NON_DISPATCH.getCode());
		if (MtConstant.IS_RESERVED_FLAG.equals(dto.getIsReservedFlag())) {
			//没预约开始时间
			mtTask.setBeginTime(DataTypeConverUtils.formatDateToLongYYYYMMDDHHMM(nowDate));
		} else {
			// 预约了开始时间
			if (DateUtil.parseStringToDateYYYYMMDDHHMMSS(
					String.valueOf(dto.getBeginTime() * 100)).getTime() < nowDate.getTime()) {
				// 预约时间小于当前时间,设置当前时间为预计开始时间(出现情况,新建任务单时一直没点提交)
				mtTask.setBeginTime(DataTypeConverUtils.formatDateToLongYYYYMMDDHHMM(nowDate));
			} else {
				mtTask.setBeginTime(dto.getBeginTime());
			}
		}
		// 处理任务所属时刻
		mtTask.setTaskHour((byte)Integer.parseInt(String.valueOf(mtTask.getBeginTime()).substring(8, 10)));
		// 任务所属年月日
		mtTask.setTaskTime(Integer.valueOf(String.valueOf(mtTask.getBeginTime()).substring(0, 8)));
		mtTask.setGroupOrganId(groupOrganId);
		// 处理预计完成时间
		Long endTime = Long.valueOf(DateUtil.formatDateToString(DateUtil.addDateMinute(
				DataTypeConverUtils.parseLongToDate(mtTask.getBeginTime() * 100), 
				mtTask.getLimitMinute()), DateUtil.FORMAT_YYYY_MM_DD_HH_MM));
		mtTask.setEndTime(endTime);
		mtTaskService.insert(mtTask);
		
		/*扩展信息保存*/
		MtTaskExt mtTaskExt= BeanCopierUtils.copyProperties(dto, MtTaskExt.class, true);
		mtTaskExt.setTaskExtId(SeqContants.getSequnces(MtSeqContants.MT_TASK_EXT_ID_SEQ).intValue());
		mtTaskExt.setTaskId(taskId);
		mtTaskExt.setGroupOrganId(groupOrganId);
		this.mtTaskExtService.insert(mtTaskExt);
		
		String tableIndex = DynamicTableSourceKeyHolder.getDataSourceKey();
		/* 运送路线信息保存 */
		this.mtCommonTaskService.saveBatchMtTaskRoute(groupOrganId, initMtTaskRouteList(
				dto, taskId, nowDate, groupOrganId), tableIndex);
		//处理附件
		if (StringUtils.isNotBlank(dto.getFileIds())) {
			CommonServiceUtils.updateRefIdByFileIds(dto.getFileIds(), String.valueOf(taskId));
		}
		if (StringUtils.isNotBlank(dto.getVoiceIds())) {
			CommonServiceUtils.updateRefIdByFileIds(dto.getVoiceIds(), String.valueOf(taskId));
		}
		// 处理 轨迹信息
		TaskTrackEvent trackEvent = new TaskTrackEvent(this, taskId, 
				TaskTrackActionEnum.CREATE_TASK.getActionType(), userId, mtTask, nowDate);
		applicationContext.publishEvent(trackEvent);
		// 给mq发送消息
		List<MtTask> taskList = new ArrayList<>();
		taskList.add(mtTask);
		this.create(taskList, null, nowDate);
		rst.setObj(taskId);
		return rst;
	}

	private List<MtTaskRoute> initMtTaskRouteList(DispatchTaskDto dto,
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
	 * @discription 调度任务派单
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月25日 上午10:37:31      
	 * @param dto
	 * @param organId
	 * @return     
	 */
	@Override
	public ResultDto<String, String, Integer> handlerDispatchTask(
			int groupOrganId, DispatchTaskDto dto, MtTask beforeMtTask) {
		ResultDto<String,String, Integer> rst = new ResultDto<>(true, "");
		// 需要发送消息的用户集合
		List<String> userList = new ArrayList<String>();
		// 验证
		Date nowDate = new Date();
		Integer userId = dto.getUserId();
		List<Integer> exeUserIdList = AppUtils.str2Integer(dto.getExeUserIds());
		userList = AppUtils.str2List(dto.getExeUserIds());
		Integer taskId = dto.getTaskId();
		Long endTime = null;
		if (beforeMtTask.getLimitMinute().intValue() != dto.getLimitMinute().intValue()) {
			// 修改了时限，重新算预计结束时间
			endTime = Long.valueOf(DateUtil.formatDateToString(DateUtil.addDateMinute(
					DataTypeConverUtils.parseLongToDate(beforeMtTask.getBeginTime() * 100), 
					dto.getLimitMinute()), DateUtil.FORMAT_YYYY_MM_DD_HH_MM));
		}
		String tableIndex = DynamicTableSourceKeyHolder.getDataSourceKey();
		MtTask updateMtTask= new MtTask();
		updateMtTask.setUpdateBy(userId);
		updateMtTask.setUpdateDate(nowDate);
		updateMtTask.setResType(dto.getResType());
		updateMtTask.setVersion(MtConstant.VERSION_DEFAULT);
		updateMtTask.setSendTime(nowDate);
		updateMtTask.setDispatchUserId(userId);
		updateMtTask.setEndTime(endTime);
		// 修改时限
		updateMtTask.setLimitMinute(dto.getLimitMinute());
		// 修改运送人数
		updateMtTask.setTransPersonCount(dto.getTransPersonCount());
		String taskType = null;
		//处理任务单扩展表
		if (StringUtils.isNotBlank(dto.getSendContent())) {
			MtTaskExt ext = new MtTaskExt();
			ext.setSendContent(dto.getSendContent());
			this.mtCommonTaskService.updateMtTaskExtContent(groupOrganId, ext, taskId);
		}
		if(TransDispatchTypeEnum.TRANS_DISPATCH_TYPE_01.getCode()
				.equals(dto.getResType())) {
			//指定
			taskType = TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode();
			updateMtTask.setExeEndUserId(dto.getExeEndUserId());
			updateMtTask.setStatus(TransStatusEnum.TRANS_NOT_START.getCode());
			int cnt = mtCommonTaskService.updateMtTaskStatus(groupOrganId, updateMtTask,
					TransStatusEnum.TRANS_NON_DISPATCH.getCode(), taskId);
			if (cnt <= 0) {
				rst.setIsSucc(false);
				rst.setMessage("该运送单已被其他人操作无法派单");
				return rst;
			}
			//批量新增运送人员信息
			List<MtTaskExecutors> excutorsList = initTaskDataUtils.initMtTaskExcutorsList(beforeMtTask, dto.getExeUserIds(), 
					nowDate, dto.getExeEndUserId(), updateMtTask.getStatus(), nowDate, endTime);
			this.mtCommonTaskService.saveBatchMtTaskExcutors(groupOrganId, excutorsList, tableIndex);
			/* 更新人员位置信息表 对人员未完成数量进行加 1  */
			this.updateUserPositUnTaskNumUtils.updateUserPositUnTaskNum(beforeMtTask.getOrganId(), exeUserIdList, 
					(short)1,(short) 0, beforeMtTask.getBeginTime(), null);
			//派单指定类型给主责人发送任务超时提醒
			this.taskSendNoticeUtils.sendTaskTimeOutNotice(beforeMtTask, String.valueOf(
					updateMtTask.getExeEndUserId()), null, taskType, dto.getLimitMinute());
		}
		if (TransDispatchTypeEnum.TRANS_DISPATCH_TYPE_02.getCode()
				.equals(dto.getResType())) {
			taskType = TransTaskTypeEnum.TASK_TYPE_GRAD.getCode();
			//抢单
			updateMtTask.setStatus(TransStatusEnum.TRANS_ROBBING.getCode());
			int cnt = mtCommonTaskService.updateMtTaskStatus(groupOrganId, updateMtTask,
					TransStatusEnum.TRANS_NON_DISPATCH.getCode(), taskId);
			if (cnt <= 0) {
				rst.setIsSucc(false);
				rst.setMessage("该运送单已被其他人操作无法派单");
				return rst;
			}
			//处理服务处
			List<Integer> serviceGroupIds = AppUtils.str2Integer(dto.getServiceGroupIds());
			this.mtCommonTaskService.saveBatchMtTaskGroup(groupOrganId, initTaskGroupListToSave(
					taskId, serviceGroupIds, userId, nowDate, beforeMtTask.getGroupOrganId()), tableIndex);
			// 抢单服务需要新增到redis缓存中 处理要存的抢单缓存数据
			this.mtTaskGrabRedisCache.zaddGradTaskDep(serviceGroupIds, initRobDataToSaveRedis(beforeMtTask, updateMtTask));
			// 通过服务处id 查询服务处下人员
			userList = initTaskDataUtils.getUserListByGroupIds(dto.getServiceGroupIds());
			//抢单延时发送消息（未满员时）
			this.taskSendNoticeUtils.sendOvertimeNotFull(updateMtTask.getDispatchUserId(), nowDate, taskId, taskType);
		}
		rst.setObj(taskId);
		// 运送任务派单的时候通知运送员 liuyi
		taskSendNoticeUtils.dispatchSendAppMessage(userList, beforeMtTask, taskType, null != endTime ? endTime : beforeMtTask.getEndTime());
		// end
		// 未开始需要
		if(TransStatusEnum.TRANS_NOT_START.getCode().equals(updateMtTask.getStatus())) {
			// 延迟发送消息
			this.taskSendNoticeUtils.sendOvertimeNotStarted(userId, beforeMtTask.getBeginTime() , nowDate, taskId, taskType);
		}
		// 处理 轨迹信息
		TaskTrackEvent trackEvent = new TaskTrackEvent(this, taskId, 
				TaskTrackActionEnum.DISPATCH_TASK.getActionType(), userId, updateMtTask, nowDate);
		applicationContext.publishEvent(trackEvent);
		// 推送mq
		this.take(updateMtTask, beforeMtTask, dto.getExeUserIds(), nowDate);
		return rst;
	}
	
	private MtTask initRobDataToSaveRedis(MtTask beforeMtTask, MtTask mtUpdateTask) {
		beforeMtTask.setSendTime(mtUpdateTask.getSendTime());
		beforeMtTask.setUpdateBy(mtUpdateTask.getUpdateBy());
		beforeMtTask.setUpdateDate(mtUpdateTask.getUpdateDate());
		beforeMtTask.setResType(mtUpdateTask.getResType());
		beforeMtTask.setVersion(mtUpdateTask.getVersion());
		beforeMtTask.setStatus(TransStatusEnum.TRANS_ROBBING.getCode());
		beforeMtTask.setLimitMinute(mtUpdateTask.getLimitMinute());
		beforeMtTask.setTransPersonCount(mtUpdateTask.getTransPersonCount());
		if (null != mtUpdateTask.getEndTime()) {
			beforeMtTask.setEndTime(mtUpdateTask.getEndTime());
		}
		return beforeMtTask;
	}

	/**
	 * @discription 退单
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月10日 下午2:24:08      
	 * @param t
	 * @return     
	 */
	@Override
	public ResultDto<String, String, MtTask> handlerBackTask(int groupOrganId, MtTask task,
			CommonTaskDto dto) {
		ResultDto<String, String, MtTask> rst = new ResultDto<>(true,"");
		Integer taskId = task.getTaskId();
		Integer userId = dto.getUserId();
		Date nowDate = new Date();
		if(TransStatusEnum.TRANS_ROBBING.getCode().equals(task.getStatus())) {
			// 主负责人
			Integer exeEndUserId = task.getExeEndUserId();
			MtTask updateMtTask = new MtTask();
			boolean isExeEndUser = false;
			if(exeEndUserId != null && exeEndUserId.intValue() == userId.intValue()) {
				updateMtTask.setExeEndUserId(0);
				isExeEndUser = true;
			}
			updateMtTask.setUpdateBy(userId);
			updateMtTask.setUpdateDate(nowDate);
			int cnt = this.mtCommonTaskService.updateMtTaskStatus(groupOrganId, updateMtTask, 
					TransStatusEnum.TRANS_ROBBING.getCode(), task.getTaskId());
			if(cnt <= 0) {
				// 当前运送单已被修改
				rst.setIsSucc(false);
				rst.setMessage("运送单状态已被修改！");
			} else {
				// 执行人表中删掉退单人员信息
				mtCommonTaskService.deleteTaskExecutorsByTaskId(groupOrganId, taskId, String.valueOf(userId));
				/* 更新人员位置信息表 对人员未完成数量进行加 1  */
				this.updateUserPositUnTaskNumUtils.updateUserPositUnTaskNum(task.getOrganId(), userId, 
						(short)-1,(short) 0, task.getBeginTime(), null);
				if(isExeEndUser) {
					//当主责人退单时，删除任务超时提醒,事务一致性
					this.taskSendNoticeUtils.delTaskTimeOutNotice(taskId);
				}
			}
		}
		if(TransStatusEnum.TRANS_NOT_START.getCode().equals(task.getStatus())) {
			// 未开始退单是公用的方法
			rst = this.mtCommonTaskService.updateBackTaskNotStartStatus(groupOrganId, task, userId, 
					nowDate, TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode());
			if (null != rst.getObj() && null != rst.getObj().getStatus() 
					&& TransStatusEnum.TRANS_BACK.getCode().equals(rst.getObj().getStatus())) {
				// 未开始状态责任人退单推送mq
				this.take(rst.getObj(), task, null, nowDate);
			}
			// 处理 轨迹信息
			TaskTrackEvent trackEvent = new TaskTrackEvent(this, taskId, TaskTrackActionEnum.
					BACK_TASK.getActionType(), userId, task, nowDate, null, dto.getBackTaskReason());
			applicationContext.publishEvent(trackEvent);
		}
		rst.setVal(String.valueOf(taskId));
		// 发送消息
		return rst;
	}

	/**
	 * @discription 抢单
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月9日 下午8:45:57      
	 * @param t
	 * @return     
	 */
	@Override
	public ResultDto<String, Integer, Boolean> handlerGrabTask(
			int groupOrganId, MtTask mtTask, CommonTaskDto t) {
		ResultDto<String, Integer, Boolean> rst = new ResultDto<>(true,"");
		//最后1个人抢单是否设置责任人
		Integer taskId = t.getTaskId();
		Integer userId = t.getUserId();
		Date nowDate= new Date();
		ResultDto<String, String, MtTask> statusRst = null;
		List<MtTaskExecutors> executorsList = this.mtCommonTaskService.
				queryMtTaskExcutorsByTaskId(groupOrganId, taskId, "UPDATE_DATE asc");
		List<Integer> exeUserIdList = AppUtils.list2ParamsList(executorsList, (obj) -> obj.getExeUserId());
		int gradCnt = executorsList.size();
		if(mtTask.getTransPersonCount().intValue() - gradCnt <= 0 ) {
			rst.setIsSucc(false);
			rst.setMessage("抢单失败,运送单已经满员了!");
			return rst;
		}
		if (exeUserIdList.contains(userId)) {
			rst.setIsSucc(false);
			rst.setMessage("你已抢过单了!");
			return rst;
		}
		
		List<MtTaskExecutors> mtTaskExcutorsList = new ArrayList<>();
		Integer endExeUserId = getEndExeUserId(executorsList);
		rst.setVal(endExeUserId);//责任人UserId
		rst.setObj(endExeUserId == null ? false: true);//是否设置责任人
		String exeUserId = null;
		statusRst = handlerStatus(groupOrganId, mtTask, rst, gradCnt, userId, nowDate, executorsList);
		if (StringUtils.isNotBlank(statusRst.getVal()) && MtConstant.IS_EXE_END_USER.equals(statusRst.getVal())) {
			rst.setObj(true);
		}
		if (null != statusRst.getObj()) {
			mtTask.setStatus(statusRst.getObj().getStatus());
			if (null != statusRst.getObj().getExeEndUserId()) {
				exeUserId = String.valueOf(statusRst.getObj().getExeEndUserId());
			}
		}
		// 增加一条数据在执行人中
		MtTaskExecutors mtTaskExcutors = this.initTaskDataUtils.initSaveMtTaskExcutors(mtTask,
				userId, mtTask.getOrganId(), nowDate, statusRst.getVal(), mtTask.getEndTime());
		mtTaskExcutorsList.add(mtTaskExcutors);
		String tableIndex = DynamicTableSourceKeyHolder.getDataSourceKey();
		this.mtCommonTaskService.saveBatchMtTaskExcutors(groupOrganId, mtTaskExcutorsList, tableIndex);
		List<Integer> delUserId = new ArrayList<Integer>();
		if (mtTask.getTransPersonCount().intValue() - gradCnt == 1) {
			if (!rst.getObj()) {
				// 没设置责任人,将第一个抢单人设为 2
				mtCommonTaskService.updateTaskExcutors(groupOrganId, mtTask.getTaskId(), String.valueOf(executorsList.get(0).getExeUserId()), 
						MtConstant.SETTING_PERSON_LIABLE, mtTask.getStatus());
				delUserId.add(executorsList.get(0).getExeUserId());
			}
			exeUserIdList.removeAll(delUserId);
			if (AppUtils.isNotEmpty(exeUserIdList)) {
				// 修改任务状态为未开始
				mtCommonTaskService.updateTaskExcutors(groupOrganId, taskId, exeUserIdList, null, mtTask.getStatus(), null, null, null);
			}
			// 删除抢单缓存
			List<MtTaskGroupRel> mtTaskGroupList = this.mtCommonTaskService.queryServiceByTaskId(groupOrganId, mtTask.getTaskId());
			List<Integer> delGroupIds = AppUtils.list2ParamsList(mtTaskGroupList, MtTaskGroupRel.class, "groupId");
			if (AppUtils.isNotEmpty(delGroupIds)) {
				this.mtTaskGrabRedisCache.hdelGradTaskDep(delGroupIds, mtTask.getTaskId());
				//start by zhangyang  先不删除服务处表数据,供重新派单回填
				//this.mtCommonTaskService.deleteMtTaskGroupByTaskId(mtTask.getTaskId());
				// end
			}				
			//当抢单满员时删除未满员提醒的发送
			this.taskSendNoticeUtils.delOvertimeNotFull(mtTask.getTaskId());
			// 给主责任人发送任务超时预警提醒
			if (StringUtils.isNotBlank(exeUserId)) {
				this.taskSendNoticeUtils.sendTaskTimeOutNotice(mtTask, exeUserId, null, 
						TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode(), mtTask.getLimitMinute());
			}
			// 给派单人发送超时未开始提醒
			this.taskSendNoticeUtils.sendOvertimeNotStarted(mtTask.getDispatchUserId(), mtTask.getBeginTime(), 
					nowDate, mtTask.getTaskId(), TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode());
			// 给mq发送消息
			this.grab(mtTask, exeUserId, nowDate); 
		}
		/* 更新人员位置信息表 对人员未完成数量进行加 1  */
		this.updateUserPositUnTaskNumUtils.updateUserPositUnTaskNum(mtTask.getOrganId(), userId, (short)1, 
				(short)0, mtTask.getBeginTime(), null);
		return rst;
	}

	private ResultDto<String, String, MtTask> handlerStatus(int groupOrganId, MtTask mtTask, ResultDto<String, Integer, Boolean> rst,  
			int gradCnt, int userId, Date nowDate, List<MtTaskExecutors> executorsList) {
		ResultDto<String, String, MtTask> statusRst = new ResultDto<>(true, "");
		if (rst.getObj()) {
			statusRst.setVal(MtConstant.DEFAULT_PERSON_LIABLE);
		}
		if (!rst.getObj() && mtTask.getTransPersonCount().intValue() - gradCnt > 1) {
			// 不是最后1个抢单的
			statusRst.setVal(MtConstant.DEFAULT_PERSON_LIABLE);
		}
		if (mtTask.getTransPersonCount().intValue() - gradCnt == 1) {
			MtTask updateMtTask = new MtTask();
			if (!rst.getObj()) {
				// 没设置责任人
				if (mtTask.getTransPersonCount().intValue() == 1) {
					// 只有一个人的任务
					updateMtTask.setExeEndUserId(userId);
					statusRst.setVal(MtConstant.IS_EXE_END_USER);
				} else {
					// 是最后1个抢单的
					updateMtTask.setExeEndUserId(executorsList.get(0).getExeUserId());
					statusRst.setVal(MtConstant.DEFAULT_PERSON_LIABLE);
				}
			}
			updateMtTask.setUpdateBy(userId);
			updateMtTask.setUpdateDate(nowDate);
			updateMtTask.setStatus(TransStatusEnum.TRANS_NOT_START.getCode());
			this.mtCommonTaskService.updateMtTaskStatus(groupOrganId, updateMtTask, 
					TransStatusEnum.TRANS_ROBBING.getCode(), mtTask.getTaskId());
			statusRst.setObj(updateMtTask);
		}
		return statusRst;
	}
	
	/**
	 * @discription 获取主责任人
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月16日 下午7:32:54     
	 * @param executorsList
	 */
	private Integer getEndExeUserId(List<MtTaskExecutors> executorsList) {
		if (AppUtils.isNotEmpty(executorsList)) {
			for (MtTaskExecutors mtTaskExecutors : executorsList) {
				if(MtConstant.IS_EXE_END_USER.equals(mtTaskExecutors.getIsExeEndUser())) {
					return mtTaskExecutors.getExeUserId();
				}
			}
		}
		return null;
	}
	
	/**
	 * @discription 验证UserId是否在执行人列表中
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月16日 下午7:32:54     
	 * @param executorsList
	 */
	private ResultDto<String, String, Integer> verifyExeUserListByUserId(List<MtTaskExecutors> executorsList,
			Integer userId) {
		ResultDto<String, String, Integer> rst = new ResultDto<>();
		for (MtTaskExecutors mtTaskExecutors : executorsList) {
			if(MtConstant.IS_EXE_END_USER.equals(mtTaskExecutors.getIsExeEndUser())) {
				rst.setObj(mtTaskExecutors.getExeUserId());
			}
			if(mtTaskExecutors.getExeUserId() != null &&
					mtTaskExecutors.getExeUserId().intValue() == userId.intValue()) {
				// 当前用户抢单了
				rst.setIsSucc(true);
			}
		}
		return rst;
	}

	/**
	 * @discription 设置责任人
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月9日 下午8:12:35      
	 * @param t
	 * @return     
	 */
	@Override
	public ResultDto<String, String, MtTask> handlerSettingResponsiblePerson(int groupOrganId, CommonTaskDto dto , MtTask t) {
		ResultDto<String, String, MtTask> rst = new ResultDto<>(true,"");
		rst.setObj(t);
		Integer taskId = t.getTaskId();
		Integer userId = dto.getUserId();
		String applyStatus = dto.getApplyStatus();
		Date nowDate = new Date();
		List<MtTaskExecutors> executorsList = this.mtCommonTaskService.
				queryMtTaskExcutorsByTaskId(groupOrganId, taskId, "IS_EXE_END_USER desc, UPDATE_DATE desc");
		int personCnt = t.getTransPersonCount();
		int gradCnt = executorsList.size();
		ResultDto<String, String, Integer> verifyRst = verifyExeUserListByUserId(executorsList, userId);
		
		if(!verifyRst.getIsSucc()) {
			rst.setIsSucc(false);
			rst.setMessage("当前用户未抢到单，不可以设置责任人");
			return rst;
		}
		if(verifyRst.getObj() != null && verifyRst.getObj().intValue() != 0) {
			// 说明已经配置主责任人
			rst.setIsSucc(false);
			rst.setMessage("已设置了主责任人！");
			return rst;
		}
		String exeEndUserId = null;
		if (personCnt - gradCnt >= 1) {
			// 还没抢单完
			if(MtConstant.APPLY_STATUS_1.equals(applyStatus)) {
				// 同意担任主责任人
				rst = setApplyPerson(groupOrganId, taskId, userId, userId, TransStatusEnum.TRANS_ROBBING.getCode(), t.getVersion());
				exeEndUserId = String.valueOf(rst.getObj().getExeEndUserId());
			}
		}
		
		if (personCnt - gradCnt == 0 && TransDispatchTypeEnum.TRANS_DISPATCH_TYPE_02.getCode().equals(t.getResType())) {
			if(MtConstant.APPLY_STATUS_1.equals(applyStatus)) {
				// 同意担任主责任人
				rst = setApplyPerson(groupOrganId, taskId, userId, userId, TransStatusEnum.TRANS_NOT_START.getCode(), t.getVersion());
				// 将第一个抢单人设为0(非主责任人)
				if (userId.intValue() != executorsList.get(0).getExeUserId().intValue()
						&& MtConstant.SETTING_PERSON_LIABLE.equals(executorsList.get(0).getIsExeEndUser())) {
					mtCommonTaskService.updateTaskExcutors(groupOrganId, taskId, String.valueOf(executorsList.get(0).
							getExeUserId()), MtConstant.DEFAULT_PERSON_LIABLE, null);
				}
				exeEndUserId = String.valueOf(userId);
			}
		}
		// 删除之前的任务超时预警提醒(有的话)
		this.taskSendNoticeUtils.delTaskTimeOutNotice(t.getTaskId());
		// 给主责任人发送任务超时预警提醒
		this.taskSendNoticeUtils.sendTaskTimeOutNotice(t, exeEndUserId, null, 
				TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode(), t.getLimitMinute());
		// 给mq发送消息
		this.grab(t, exeEndUserId, nowDate);
		return rst;
	}

	/**
	 * @discription 设置申请人
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月18日 下午7:55:12     
	 * @param rst
	 * @param taskId
	 * @param userId
	 * @return
	 */
	private ResultDto<String, String, MtTask> setApplyPerson(Integer groupOrganId, Integer taskId, Integer optUserId, Integer userId, String status, Integer version) {
		ResultDto<String, String, MtTask> rst = new ResultDto<>(true,"");
		MtTask mtTaskUpdate = new MtTask();
		mtTaskUpdate.setExeEndUserId(userId);
		mtTaskUpdate.setUpdateBy(optUserId);
		mtTaskUpdate.setUpdateDate(new Date());// 责任人条件
		int cnt = mtCommonTaskService.updateMtTaskStatus(groupOrganId, mtTaskUpdate, status, taskId);
		if (cnt <= 0) {
			rst.setIsSucc(false);
			rst.setMessage("该运送单已被其他人操作无法设置主责任人");
			return rst;
		}
		// 修改执行人表 是否责任人(不修改任务状态)
		mtCommonTaskService.updateTaskExcutors(groupOrganId, taskId, String.valueOf(userId), MtConstant.IS_EXE_END_USER, null);
		rst.setObj(mtTaskUpdate);
		rst.setMessage(RpcError.SUCCESS.getMessage());
		return rst;
	}
	
	/**
	 * @discription 编辑任务单
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月26日 下午3:17:59      
	 * @param dto
	 * @param mtTask     
	 */
	@Override
	public ResultDto<String, String, String> handlerEditTask(int groupOrganId, DispatchTaskDto dto, MtTask mtTask) {
		ResultDto<String, String, String> rst = new ResultDto<>(true,"");
		MtTask updateMtTask = BeanCopierUtils.copyProperties(dto, MtTask.class, true);
		Integer taskId = mtTask.getTaskId();
		Date nowDate = new Date();
		updateMtTask.setUpdateBy(dto.getUserId());
		updateMtTask.setUpdateDate(nowDate);
		if (MtConstant.IS_RESERVED_FLAG.equals(dto.getIsReservedFlag())) {
			//没预约开始时间
			updateMtTask.setBeginTime(DataTypeConverUtils.formatDateToLongYYYYMMDDHHMM(nowDate));
		} else {
			// 预约了开始时间
			if (DateUtil.parseStringToDateYYYYMMDDHHMMSS(
					String.valueOf(dto.getBeginTime() * 100)).getTime() < new Date().getTime()) {
				// 预约时间小于当前时间,设置当前时间为预计开始时间(出现情况,新建任务单时一直没点提交)
				updateMtTask.setBeginTime(DataTypeConverUtils.formatDateToLongYYYYMMDDHHMM(nowDate));
			} else {
				updateMtTask.setBeginTime(dto.getBeginTime());
			}
		}
		if (mtTask.getLimitMinute().intValue() != updateMtTask.getLimitMinute().intValue()) {
			// 如果时限修改了 处理预计完成时间
			Long endTime = Long.valueOf(DateUtil.formatDateToString(DateUtil.addDateMinute(
					DataTypeConverUtils.parseLongToDate(updateMtTask.getBeginTime() * 100), 
					updateMtTask.getLimitMinute()), DateUtil.FORMAT_YYYY_MM_DD_HH_MM));
			updateMtTask.setEndTime(endTime);
		}
		// 验证任务来源
		Integer sourceHouseId = updateMtTask.getSourceHouseId();
		if (null == sourceHouseId || sourceHouseId.intValue() <= 0) {
			sourceHouseId = null != dto.getUserHouseId() && dto.getUserHouseId().intValue() > 0
					? dto.getUserHouseId() : dto.getFromHouseId();
		}
		updateMtTask.setSourceHouseId(sourceHouseId);
		// 处理任务所属时刻
		updateMtTask.setTaskHour((byte)Integer.parseInt(String.valueOf(updateMtTask.getBeginTime()).substring(8, 10)));
		// 任务所属年月日
		updateMtTask.setTaskTime(Integer.valueOf(String.valueOf(mtTask.getBeginTime()).substring(0, 8)));
		
		int updateNum = this.mtCommonTaskService.updateMtTaskStatus(groupOrganId, updateMtTask,
				TransStatusEnum.TRANS_NON_DISPATCH.getCode(), mtTask.getTaskId());
		if(updateNum <= 0) {
			rst.setIsSucc(false);
			rst.setMessage("该运送单已被其他人操作无法编辑");
			return rst;
		}
		//更新扩展表
		MtTaskExt mtTaskExt = BeanCopierUtils.copyProperties(dto, MtTaskExt.class, true);
		this.mtCommonTaskService.updateMtTaskExt(groupOrganId, mtTaskExt);
		
		// 修改目的地路线表
		MtTaskRoute route = new MtTaskRoute();
		route.setHouseId(updateMtTask.getToHouseId());
		route.setUpdateDate(nowDate);
		MtTaskRouteCriteria example = new MtTaskRouteCriteria();
		MtTaskRouteCriteria.Criteria criteria = example.createCriteria();
		criteria.andTaskIdEqualTo(taskId);
		mtTaskRouteService.updateByExampleSelective(route, example);
		//处理附件
		if (StringUtils.isNotBlank(dto.getFileIds())) {
			CommonServiceUtils.updateRefIdByFileIds(dto.getFileIds(), String.valueOf(taskId));
		}
		if (StringUtils.isNotBlank(dto.getDelFileIds())) {
			CommonServiceUtils.updateRefIdByFileIds(dto.getDelFileIds(), "");
		}
		if (StringUtils.isNotBlank(dto.getVoiceIds())) {
			CommonServiceUtils.updateRefIdByFileIds(dto.getVoiceIds(), String.valueOf(taskId));
		}
		if (StringUtils.isNotBlank(dto.getDelVoiceIds())) {
			CommonServiceUtils.updateRefIdByFileIds(dto.getDelVoiceIds(), "");
		}
		rst.setObj(String.valueOf(mtTask.getTaskId()));
		if (updateMtTask.getBeginTime().intValue() != mtTask.getBeginTime().intValue()) {
			// 修改了beginTime 处理轨迹
			trackMessageUtil.updateTrackForEditTask(String.valueOf(taskId), mtTask.getOrganId(), updateMtTask.getBeginTime());
		}
		this.take(updateMtTask, mtTask, null, nowDate);
		if (updateMtTask.getSourceHouseId().intValue() != mtTask.getSourceHouseId().intValue()
				|| !updateMtTask.getUrgency().equals(mtTask.getUrgency())
				|| updateMtTask.getBeginTime().intValue() != mtTask.getBeginTime().intValue()) {
			// 修改了任务来源或者紧急程度或者预约时间  推送mq
			this.editSourceHouce(updateMtTask, mtTask, nowDate);
		}
		return rst;
	}
	
	/**
	 * @discription 调度任务重新派单
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月5日 下午2:15:19      
	 * @param mtTask
	 * @param t
	 * @return     
	 */
	@Override
	public ResultDto<String, String, Integer> handlerAgainDispatchTask(int groupOrganId, MtTask beforeMtTask,
			DispatchTaskDto dto) {
		ResultDto<String,String, Integer> rst = new ResultDto<>(true, "");
		//再次派单在之前的版本号+1
		Integer afterVersion = beforeMtTask.getVersion().intValue() + MtConstant.VERSION_ADD_DEFAULT;
		Date nowDate = new Date();
		Integer userId = dto.getUserId();
		List<Integer> exeUserIdList = AppUtils.str2Integer(dto.getExeUserIds());
		Integer taskId = dto.getTaskId();
		Long endTime = null;
		if (beforeMtTask.getLimitMinute().intValue() != dto.getLimitMinute().intValue()) {
			// 修改了时限，重新算预计结束时间
			endTime = Long.valueOf(DateUtil.formatDateToString(DateUtil.addDateMinute(
					DataTypeConverUtils.parseLongToDate(beforeMtTask.getBeginTime() * 100), 
					dto.getLimitMinute()), DateUtil.FORMAT_YYYY_MM_DD_HH_MM));
		}
		MtTask mtTaskUpdate = new MtTask();
		mtTaskUpdate.setUpdateBy(userId);
		mtTaskUpdate.setUpdateDate(nowDate);
		mtTaskUpdate.setResType(dto.getResType());
		mtTaskUpdate.setVersion(afterVersion);
		// 处理派单时间
		mtTaskUpdate.setSendTime(nowDate);
		// 设置派单人
		mtTaskUpdate.setDispatchUserId(userId);
		mtTaskUpdate.setEndTime(endTime);
		// 修改时限
		mtTaskUpdate.setLimitMinute(dto.getLimitMinute());
		// 修改运送人数
		mtTaskUpdate.setTransPersonCount(dto.getTransPersonCount());
		String tableIndex = DynamicTableSourceKeyHolder.getDataSourceKey();
		// 修改扩展表信息
		if (StringUtils.isNotBlank(dto.getSendContent())) {
			MtTaskExt ext = new MtTaskExt();
			ext.setSendContent(dto.getSendContent());
			this.mtCommonTaskService.updateMtTaskExtContent(groupOrganId, ext, taskId);
		}
		// 获取已抢单或指定执行人信息
		List<MtTaskExecutors> taskUserInfoList = mtCommonTaskService.queryMtTaskExcutorsByTaskId(groupOrganId, taskId, null);
		List<Integer> beforeExeUser = AppUtils.list2ParamsList(taskUserInfoList, (obj) -> obj.getExeUserId());
		if (AppUtils.isNotEmpty(beforeExeUser)) {
			// 重新派单前的执行人 添加到执行人log表中
			List<MtTaskExecutorsLog> exeUserLog = this.initTaskDataUtils.initExecutorsLog(beforeMtTask, beforeExeUser, 
					TransUserStatusEnum.TRANS_USER_STATUS_00.getCode(), nowDate);
			this.mtCommonTaskService.saveBatchMtTaskExcutorsLog(groupOrganId, exeUserLog, tableIndex);
		}
		
		Map<String, MtLocationInfoIce> houseInfoMap = initTaskDataUtils.getHouseInfoByTask(beforeMtTask);
		// 出发地
		String fromHouseName = !houseInfoMap.isEmpty() && null != houseInfoMap.get(String.valueOf(beforeMtTask.getFromHouseId()))
				? houseInfoMap.get(String.valueOf(beforeMtTask.getFromHouseId())).getLocationName() : "";
		// 目的地	
		String toHouseName = !houseInfoMap.isEmpty() && null != houseInfoMap.get(String.valueOf(beforeMtTask.getToHouseId()))
				? houseInfoMap.get(String.valueOf(beforeMtTask.getToHouseId())).getLocationName() : "";
		//开始结束时间
		String noticeBeginTime = DataTypeConverUtils.paresNumberToString(beforeMtTask.getBeginTime(), 
				DateUtil.FORMAT_YYYY_MM_DD_HH_MM, "HH:mm");
		// 预计结束时间
		Long end = null != endTime ? endTime : beforeMtTask.getEndTime();
		String noticeEndTime = DataTypeConverUtils.paresNumberToString(end, 
				DateUtil.FORMAT_YYYY_MM_DD_HH_MM, "HH:mm");
		// 大类名称
		String transTypeName = TransTypeEnum.getNameByCode(beforeMtTask.getTransTypeParentCode());
		
		List<Integer> delUserList = new ArrayList<>();
		String resTypeName = "";
		
		if(TransDispatchTypeEnum.TRANS_DISPATCH_TYPE_01.getCode()
				.equals(dto.getResType())) {
			// 重新派单为指定
			mtTaskUpdate.setExeEndUserId(dto.getExeEndUserId());
			mtTaskUpdate.setStatus(TransStatusEnum.TRANS_NOT_START.getCode());
			List<String> statusList = new ArrayList<>();
			statusList.add(TransStatusEnum.TRANS_NOT_START.getCode());
			statusList.add(TransStatusEnum.TRANS_ROBBING.getCode());
			statusList.add(TransStatusEnum.TRANS_BACK.getCode());
			int cnt = mtCommonTaskService.updateMtTaskStatus(groupOrganId, mtTaskUpdate, statusList, taskId);
			if (cnt <= 0) {
				rst.setIsSucc(false);
				rst.setMessage("该运送单已被其他人操作无法重新派单");
				return rst;
			}
			
			for (Integer exeUserId : beforeExeUser) {
				if (!exeUserIdList.contains(exeUserId)) {
					delUserList.add(exeUserId); 
				} else {
					exeUserIdList.remove(exeUserId);
				}
			}
			
			// 执行人表 之前的删掉
			this.mtCommonTaskService.deleteTaskExecutorsByTaskId(groupOrganId, taskId);
			List<MtTaskExecutors> excutorsList = initTaskDataUtils.initMtTaskExcutorsList(beforeMtTask, dto.getExeUserIds(), nowDate, 
					dto.getExeEndUserId(), mtTaskUpdate.getStatus(), nowDate, endTime);
			//批量新增运送人员信息
			this.mtCommonTaskService.saveBatchMtTaskExcutors(groupOrganId, excutorsList, tableIndex);
			if (TransDispatchTypeEnum.TRANS_DISPATCH_TYPE_02.getCode().equals(beforeMtTask.getResType())) {
				//如果之前响应类型为抢单,现在改为指定,删除缓存里服务处抢单信息
				List<MtTaskGroupRel> mtTaskGroupList = this.mtCommonTaskService.queryServiceByTaskId(groupOrganId, taskId);
				List<Integer> delGroupIds = AppUtils.list2ParamsList(mtTaskGroupList, MtTaskGroupRel.class, "groupId");
				if (AppUtils.isNotEmpty(delGroupIds)) {
					this.mtCommonTaskService.deleteMtTaskGroupByTaskId(groupOrganId, taskId);
					this.mtTaskGrabRedisCache.hdelGradTaskDep(delGroupIds, taskId);
				}
				if (AppUtils.isNotEmpty(beforeExeUser)) {
					// 发送取消提醒
					taskSendNoticeUtils.sendTaskNotice(AppUtils.listToString(beforeExeUser, ','),  
							String.valueOf(taskId), MtNoticeConstant.MT_TASK_REDISPATCH_NOTICE, 
							TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode(), new Object[]{});
				}
			}
			// delUserList 发送取消提醒
			if (AppUtils.isNotEmpty(delUserList) && TransDispatchTypeEnum.
					TRANS_DISPATCH_TYPE_01.getCode().equals(beforeMtTask.getResType())) {
				taskSendNoticeUtils.sendTaskNotice(AppUtils.listToString(delUserList, ','), String.valueOf(taskId), 
						MtNoticeConstant.MT_TASK_REDISPATCH_NOTICE, TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode(),
						new Object[]{});
			}
			// exeUserIdList 发送任务接收提醒
			if (AppUtils.isNotEmpty(exeUserIdList)) {
				taskSendNoticeUtils.sendTaskNotice(AppUtils.listToString(exeUserIdList, ','), String.valueOf(taskId), 
					MtNoticeConstant.MT_TASK_ACCEPT_NOTICE, TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode(),
					new Object[]{resTypeName, transTypeName, fromHouseName, 
					toHouseName, noticeBeginTime, noticeEndTime});
			}
			//查询之前的执行人信息,把未完成任务数-1
			this.updateUserPositUnTaskNumUtils.updateUserPositUnTaskNum(taskUserInfoList, beforeMtTask, null);
			/* 更新人员位置信息表 对人员未完成数量进行加 1  */
			this.updateUserPositUnTaskNumUtils.updateUserPositUnTaskNum(beforeMtTask.getOrganId(), AppUtils.str2Integer(dto.getExeUserIds()),
					(short)1, (short)0, beforeMtTask.getBeginTime(), null);
			//指定类型时删除任务超时提醒，并重新给主责人派单
			this.taskSendNoticeUtils.delTaskTimeOutNotice(taskId);
			this.taskSendNoticeUtils.sendTaskTimeOutNotice(beforeMtTask, String.valueOf(mtTaskUpdate.getExeEndUserId()), null,
					TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode(), dto.getLimitMinute());
			// 删除之前的超时未开始提醒(如果有的话)
			this.taskSendNoticeUtils.delOverTimeNotStarted(taskId);
			// 给派单人发送超时未开始提醒
			this.taskSendNoticeUtils.sendOvertimeNotStarted(userId, beforeMtTask.getBeginTime(), nowDate,
					taskId, TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode());
		}
		if (TransDispatchTypeEnum.TRANS_DISPATCH_TYPE_02.getCode()
				.equals(dto.getResType())) {
			// 重新派单为抢单,重置主责任人
			mtTaskUpdate.setExeEndUserId(0);
			mtTaskUpdate.setStatus(TransStatusEnum.TRANS_ROBBING.getCode());
			List<String> statusList = new ArrayList<>();
			statusList.add(TransStatusEnum.TRANS_NOT_START.getCode());
			statusList.add(TransStatusEnum.TRANS_ROBBING.getCode());
			statusList.add(TransStatusEnum.TRANS_BACK.getCode());
			int cnt = mtCommonTaskService.updateMtTaskStatus(groupOrganId, mtTaskUpdate, statusList,taskId);
			if (cnt <= 0) {
				rst.setIsSucc(false);
				rst.setMessage("该运送单已被其他人操作无法重新派单");
				return rst;
			}
			// 删除当前执行人表记录
			this.mtCommonTaskService.deleteTaskExecutorsByTaskId(groupOrganId, taskId);
			// 查询任务单服务处关系表是否有记录
			List<MtTaskGroupRel> mtTaskGroupList = this.mtCommonTaskService.queryServiceByTaskId(groupOrganId, taskId);
			List<Integer> delGroupIds = AppUtils.list2ParamsList(mtTaskGroupList, MtTaskGroupRel.class, "groupId");
			List<String> groupIdsAfter = AppUtils.str2List(dto.getServiceGroupIds());
			if (AppUtils.isNotEmpty(delGroupIds)) {
				//处理服务处,先删除之前的然后新增
				this.mtCommonTaskService.deleteMtTaskGroupByTaskId(groupOrganId, taskId);
				for (Integer groupId : delGroupIds) {
					// 之前有的服务组不发送任务接收提醒
					if (groupIdsAfter.contains(String.valueOf(groupId))) {
						groupIdsAfter.remove(String.valueOf(groupId));
					}
				}
			}
			// 新增抢单任务到缓存中
			List<Integer> addGroupIds = AppUtils.str2Integer(dto.getServiceGroupIds());
			this.mtCommonTaskService.saveBatchMtTaskGroup(groupOrganId, initTaskGroupListToSave(
					taskId, addGroupIds, userId, nowDate, beforeMtTask.getGroupOrganId()), tableIndex);
			if (AppUtils.isNotEmpty(delGroupIds)) {
				// 事务一致性
				this.mtTaskGrabRedisCache.hdelGradTaskDep(delGroupIds, taskId);
			}
			this.mtTaskGrabRedisCache.zaddGradTaskDep(addGroupIds, initRobDataToSaveRedis(beforeMtTask, mtTaskUpdate));
			//查询执行人表中上次响应类型为指定或抢单完成执行人信息,修改未完成任务数-1
			this.updateUserPositUnTaskNumUtils.updateUserPositUnTaskNum(taskUserInfoList, beforeMtTask, null);
			if (AppUtils.isNotEmpty(beforeExeUser)) {
				// 之前执行人发送取消提醒
				taskSendNoticeUtils.sendTaskNotice(AppUtils.listToString(beforeExeUser, ','), String.valueOf(taskId), 
						MtNoticeConstant.MT_TASK_REDISPATCH_NOTICE, TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode(), new Object[]{});
			}
			// 服务处所有人员发送抢单提醒
			if (AppUtils.isNotEmpty(groupIdsAfter)) {
				resTypeName = "（抢单）";
				GroupUserBrief[] userList = MtIbatchQueryServiceUtils.queryUserListByGroupIds(AppUtils.listToString(groupIdsAfter, ','));
				if (userList != null && userList.length > 0) {
					List<String> userIdList = AppUtils.list2ParamsList(Arrays.asList(userList), GroupUserBrief.class, "userId");
					taskSendNoticeUtils.sendTaskNotice(userIdList, String.valueOf(taskId), 
							MtNoticeConstant.MT_TASK_ACCEPT_NOTICE, TransTaskTypeEnum.TASK_TYPE_GRAD.getCode(),
							new Object[]{resTypeName, transTypeName, fromHouseName, toHouseName,
							noticeBeginTime, noticeEndTime});
				}
			}
			//重新派单先删除以前的提醒，重新发提醒
			this.taskSendNoticeUtils.delOvertimeNotFull(taskId);
			this.taskSendNoticeUtils.sendOvertimeNotFull(mtTaskUpdate.getDispatchUserId(), nowDate, taskId, TransTaskTypeEnum.TASK_TYPE_GRAD.getCode());
			//抢单类型直接删除任务超时提醒
			this.taskSendNoticeUtils.delTaskTimeOutNotice(taskId);
		}
		// 处理 轨迹信息
		TaskTrackEvent trackEvent = new TaskTrackEvent(this, taskId, TaskTrackActionEnum.
				AGAIN_DISPATCH_TASK.getActionType(), userId, mtTaskUpdate, nowDate);
		applicationContext.publishEvent(trackEvent);
		rst.setObj(taskId);
		// 重新派单推送mq
		this.take(mtTaskUpdate, beforeMtTask, dto.getExeUserIds(), nowDate);
		return rst;
	}
	
	/**
	 * @discription 初始化多个服务处对象
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月7日 下午3:11:19     
	 * @param taskId
	 * @param groupIdList
	 * @param userId
	 * @param nowDate
	 * @return
	 */
	private List<MtTaskGroupRel> initTaskGroupListToSave(Integer taskId, List<Integer> groupIdList, 
			Integer userId, Date nowDate, Integer groupOrganId) {
		List<MtTaskGroupRel> taskGroupList = new ArrayList<>();
		if (AppUtils.isNotEmpty(groupIdList)) {
			MtTaskGroupRel group = null;
			for (Integer groupId : groupIdList) {
				//指定了多个服务处
				group = new MtTaskGroupRel();
				group.setTaskGroupId(SeqContants.getSequnces(MtSeqContants.MT_TASK_GROUP_REL_ID_SEQ).intValue());
				group.setTaskId(taskId);
				group.setGroupId(groupId);
				group.setUpdateDate(nowDate);
				group.setUpdateBy(userId);
				group.setGroupOrganId(groupOrganId);
				taskGroupList.add(group);
			}
		}
		return taskGroupList;
	}

	/**
	 * @Title: handlerFinishWebTask 
	 * @Description: 完成任务web 
	 * @author liuyi@segimail.com 
	 * @date 2018年9月12日上午9:33:17
	 */
	@Override
	public ResultDto<String, String, String> handlerFinishWebTask(
			Integer groupOrganId, MtTask mtTask, CommonTaskDto dto) {
		ResultDto<String, String, String> rst = new ResultDto<>(true, "");
		Integer organId = mtTask.getOrganId();
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
		/** 完成后更新执行人 **/
		List<MtTaskExecutors> excutorsList = this.mtCommonTaskService.queryMtTaskExcutorsByTaskId(groupOrganId, mtTask.getTaskId(), null);
		List<Integer> taskUserIds = AppUtils.list2ParamsList(excutorsList, (obj) -> obj.getExeUserId());
		
		this.mtCommonTaskService.updateTaskExcutors(groupOrganId, taskId, taskUserIds, null, 
				TransStatusEnum.TRANS_COMPLETED.getCode(), isTimeOut, null, nowDate);
		/* 更新人员位置信息表 对人员未完成数量进行加- 1  */
		this.updateUserPositUnTaskNumUtils.updateUserPositUnTaskNum(organId, taskUserIds, (short)-1, 
				(short)-1, mtTask.getBeginTime(), String.valueOf(mtTask.getToHouseId()));
		// 更新个人完成数
		String exeBeginTime = DateUtil.formatDateToString(mtTask.getExeBeginTime(), DateUtil.FORMAT_YYYYMMDDHHMMSS);
		MtCommonServiceUtils.savePersonalVolumeIncrease(organId, AppUtils.listToString(taskUserIds, ','), 1, exeBeginTime, mtTask.getTaskType());
		//完成时删除任务超时提醒
		this.taskSendNoticeUtils.delTaskTimeOutNotice(taskId);
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
				FINISH_TASK.getActionType(), userId, mtTask, nowDate);
		applicationContext.publishEvent(trackEvent);
		// 推送mq
		this.take(updateMtTask, mtTask, AppUtils.listToString(excutorsList, ','), nowDate);
		return rst;
	}

	@Override
	public TransportMqDto editSourceHouseHandler(MtTask updateTask, MtTask beforeTask, Date nowDate) {
		TransportMqDto mqDto = BeanCopierUtils.copyProperties(updateTask, TransportMqDto.class, true, "HH:mm");
		mqDto.setOperation(TaskMqOeration.EDIT_SOURCE_HOUSE_MQ);
		mqDto.setTaskId(String.valueOf(beforeTask.getTaskId()));
		mqDto.setTaskType(beforeTask.getTaskType());
		mqDto.setBeforeStatus(beforeTask.getStatus());
		mqDto.setGroupOrganId(String.valueOf(beforeTask.getGroupOrganId()));
		mqDto.setOrganId(String.valueOf(beforeTask.getOrganId()));
		mqDto.setTimeStamp(DateUtil.formatDateToStringYYMMDDHHmmss(nowDate));
		// 修改前的任务来源科室
		mqDto.setBeforeSourceHouse(String.valueOf(beforeTask.getSourceHouseId()));
		// 修改前的任务紧急程度
		mqDto.setBeforeUrgency(beforeTask.getUrgency());
		// 修改前的预约时间
		mqDto.setBeforeBeginTime(String.valueOf(beforeTask.getBeginTime()));
		return mqDto;
	}

}
