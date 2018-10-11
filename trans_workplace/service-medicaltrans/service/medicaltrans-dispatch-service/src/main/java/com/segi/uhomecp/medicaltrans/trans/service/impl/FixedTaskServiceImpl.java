package com.segi.uhomecp.medicaltrans.trans.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import segi.medicaltrans.location.common.MtLocationInfoIce;

import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.constant.MtMqConstant.TaskMqOeration;
import com.segi.uhomecp.medicaltrans.constant.MtNoticeConstant;
import com.segi.uhomecp.medicaltrans.constant.MtSeqContants;
import com.segi.uhomecp.medicaltrans.enums.TransDispatchTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransStatusEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTaskTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransUserStatusEnum;
import com.segi.uhomecp.medicaltrans.enums.UrgencyEnum;
import com.segi.uhomecp.medicaltrans.trans.dto.CommonTaskDto;
import com.segi.uhomecp.medicaltrans.trans.dto.FixedTaskDto;
import com.segi.uhomecp.medicaltrans.trans.dto.TransportMqDto;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutors;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutorsLog;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExt;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRoute;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRouteCriteria;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskRouteService;
import com.segi.uhomecp.medicaltrans.trans.service.FixedTaskService;
import com.segi.uhomecp.medicaltrans.trans.service.MtCommonTaskService;
import com.segi.uhomecp.medicaltrans.trans.support.AbstractDispatchTransport;
import com.segi.uhomecp.medicaltrans.trans.utils.InitTaskDataUtils;
import com.segi.uhomecp.medicaltrans.trans.utils.TaskSendNoticeUtils;
import com.segi.uhomecp.medicaltrans.trans.utils.UpdateUserPositUnTaskNumUtils;
import com.segi.uhomecp.medicaltrans.utils.DynamicTableSourceKeyHolder;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.utils.DateTimeUtils;
import com.segi.uhomecp.utils.SeqContants;
import com.segi.uhomecp.wh.common.appmsg.AppMessageUtil;
import com.segi.uhomecp.wh.common.dto.ResultDto;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;
import com.segi.uhomecp.wh.common.utils.DataTypeConverUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;

/**
 * Title: FixedTaskServiceImpl.java    
 * @Description: 描述 固定化任务管理业务处理
 * @author wangxiong@segimail.com       
 * @created 2018年4月3日 下午8:01:18
 */
@Service
public class FixedTaskServiceImpl 
	extends AbstractDispatchTransport<MtTask>
	implements FixedTaskService {

	@Autowired
	private MtTaskRouteService mtTaskRouteService;
	
	@Autowired
	private MtCommonTaskService mtCommonTaskService;
	
	@Autowired
	private AppMessageUtil appMessageUtil;
	
	@Autowired
	private InitTaskDataUtils initTaskDataUtils;
	
	@Autowired
	private UpdateUserPositUnTaskNumUtils updateUserPositUnTaskNumUtils;
	
	@Autowired
	private TaskSendNoticeUtils taskSendNoticeUtils;
	
	private Logger logger = LoggerFactory.getLogger(FixedTaskServiceImpl.class);
	
	/**
	 * 创建任务
	 */
	@Override
	public ResultDto<String, String, Integer> createTask(int groupOrganId, FixedTaskDto dto) {
		ResultDto<String,String, Integer> rst = new ResultDto<>(true, "");
		List<Integer> routeList = dto.getRouteList();
		List<Integer> transactors = dto.getTransactors();
		Integer taskLoopId = dto.getTaskLoopId();
		MtTask mtTask = null;
		MtTaskExt mtTaskExt = null;
		MtTaskRoute mtTaskRoute = null;
		Date nowDate = new Date();
		List<MtTask> mtTaskList = new ArrayList<>();
		List<Integer> userList = new ArrayList<>();
		List<MtTaskExt> mtTaskExtList = new ArrayList<>();
		List<MtTaskExecutors> mtTaskExcutorsList = new ArrayList<>();
		List<MtTaskRoute> mtTaskRouteList = new ArrayList<>();
		
		// 处理任务所属时刻
		byte taskHour = (byte)Integer.parseInt(String.valueOf(dto.getBeginTime()).substring(8, 10));
		// 任务所属年月日
		Integer taskTime = Integer.valueOf(String.valueOf(dto.getBeginTime()).substring(0, 8));
		// 处理预计完成时间
		Long endTime = Long.valueOf(DateUtil.formatDateToString(DateUtil.addDateMinute(
				DataTypeConverUtils.parseLongToDate(dto.getBeginTime() * 100), 
				dto.getLimitMinute()), DateUtil.FORMAT_YYYY_MM_DD_HH_MM));
		for (Integer transactor : transactors) {
			// 遍历人员
			Integer taskId = SeqContants.getSequnces(MtSeqContants.MT_TASK_ID_SEQ).intValue();
			mtTask = BeanCopierUtils.copyProperties(dto, MtTask.class, true);
			mtTask.setTaskId(taskId);
			mtTask.setCreateDate(DataTypeConverUtils.formatDateToLongYYYYMMDDHHMMSS(nowDate));
			mtTask.setUpdateDate(nowDate);
			mtTask.setCreateBy(dto.getCreateBy());
			mtTask.setUpdateBy(dto.getCreateBy());
			mtTask.setStatus(TransStatusEnum.TRANS_NOT_START.getCode());
			mtTask.setSourceHouseId(dto.getFromHouseId());
			mtTask.setTaskType(TransTaskTypeEnum.TASK_TYPE_LOOP.getCode());
			mtTask.setVersion(MtConstant.VERSION_DEFAULT);
			mtTask.setTransPersonCount(MtConstant.TRANS_PERSON_COUNT_DEFAULT);
			mtTask.setResType(TransDispatchTypeEnum.TRANS_DISPATCH_TYPE_01.getCode());
			mtTask.setSendTime(nowDate);
			mtTask.setExeEndUserId(transactor);
			mtTask.setTaskLoopId(taskLoopId);
			mtTask.setDispatchUserId(dto.getCreateBy());  //派单人
			mtTask.setUrgency(UrgencyEnum.URGENCY_COMMONLY.getCode());
			mtTask.setGroupOrganId(groupOrganId);
			mtTask.setTaskHour(taskHour);
			mtTask.setRespTime(MtConstant.DEFAULT_TIME);
			mtTask.setTimeConsuming(MtConstant.DEFAULT_TIME);
			mtTask.setIsTimeOut(MtConstant.NO_TIME_OUT);
			mtTask.setTaskTime(taskTime);
			mtTask.setEvaluate(MtConstant.EVALUATE_DEFAULT_VALUE);
			mtTask.setEndTime(endTime);
			mtTaskList.add(mtTask);
			
			mtTaskExt= BeanCopierUtils.copyProperties(dto, MtTaskExt.class, true);
			mtTaskExt.setTaskId(taskId);
			mtTaskExt.setTaskExtId(SeqContants.getSequnces(MtSeqContants.MT_TASK_EXT_ID_SEQ).intValue());
			mtTaskExt.setGroupOrganId(groupOrganId);
			mtTaskExtList.add(mtTaskExt);
			// 增加执行人
			mtTaskExcutorsList.add(this.initTaskDataUtils.initSaveMtTaskExcutors(
					mtTask, transactor, dto.getOrganId(), nowDate, MtConstant.IS_EXE_END_USER, endTime));
			int sortNo = 0;
			for (Integer houseId : routeList) {
				//路由点
				mtTaskRoute = new MtTaskRoute();
				mtTaskRoute.setTaskId(taskId);
				mtTaskRoute.setRouteId(SeqContants.getSequnces(MtSeqContants.MT_TASK_ROUTE_ID_SEQ).intValue());
				mtTaskRoute.setHouseId(houseId);
				mtTaskRoute.setSortNo((short)sortNo);
				mtTaskRoute.setStatus(MtConstant.CLOCK_STATUS_0);
				mtTaskRoute.setIsAutograph(MtConstant.IS_AUTOGRAPH_DEFAULT);
				mtTaskRoute.setGroupOrganId(groupOrganId);
				mtTaskRoute.setUpdateDate(nowDate);
				mtTaskRouteList.add(mtTaskRoute);
				sortNo ++;
			}
			userList.add(transactor);
		}
		String tableIndex = DynamicTableSourceKeyHolder.getDataSourceKey();
		this.mtCommonTaskService.saveBatchMtTask(groupOrganId, mtTaskList, tableIndex);
		this.mtCommonTaskService.saveBatchMtTaskExt(groupOrganId, mtTaskExtList, tableIndex);
		this.mtCommonTaskService.saveBatchMtTaskExcutors(groupOrganId, mtTaskExcutorsList, tableIndex);
		this.mtCommonTaskService.saveBatchMtTaskRoute(groupOrganId, mtTaskRouteList, tableIndex);
		/* 更新人员位置信息表 对人员未完成数量进行加 1  */
		this.updateUserPositUnTaskNumUtils.updateUserPositUnTaskNum(dto.getOrganId(), userList,
				(short)1, (short)0, dto.getBeginTime(), null);	
		// 发送提醒  liuyi
		this.fixedSendAppMessage(mtTaskList, dto, TransTaskTypeEnum.TASK_TYPE_LOOP.getCode(), nowDate);
		// end
		// 给mq发送消息 大屏开发时放开
		this.create(mtTaskList, null, nowDate);
		return rst;
	}
	
	/**
	 * @discription 运送任务完成业务处理
	 * @author wangxiong@segimail.com 
	 * @created 2018年4月9日 上午11:23:24      
	 * @param dto
	 * @return     
	 */
	@Override
	public ResultDto<String, String, String> handlerFinishTask(int groupOrganId, MtTask mtTask, CommonTaskDto dto) {
		ResultDto<String, String, String> rst = new ResultDto<>(true, "");
		Integer userId = dto.getUserId();
		Integer taskId = dto.getTaskId();
		Integer organId = mtTask.getOrganId();
		Date nowDate = new Date();
		MtTask updateMtTask = null;
		// 获取未完成的 ROUTE 列表
		MtTaskRouteCriteria routeExample = new MtTaskRouteCriteria();
		MtTaskRouteCriteria.Criteria routeCriteria = routeExample.createCriteria();
		routeCriteria.andTaskIdEqualTo(taskId);
		routeCriteria.andStatusEqualTo(MtConstant.CLOCK_STATUS_0);
		routeExample.setOrderByClause(MtConstant.SORT_NO_ASC);
		List<MtTaskRoute> mtTaskRoutelist = this.mtTaskRouteService.selectByExample(routeExample);
		Map<String, MtLocationInfoIce> houseMap = null;
		if(AppUtils.isNotEmpty(mtTaskRoutelist)) {
			Set<Integer> houseIdSet = AppUtils.list2ParamsSet(mtTaskRoutelist, (obj) -> obj.getHouseId());
			List<MtLocationInfoIce> locationInfoList = MtCommonServiceUtils.getLocationInfoList(mtTask.getOrganId(), new ArrayList<>(houseIdSet));
			houseMap = AppUtils.list2Map(locationInfoList, (obj) -> obj.getLocationId());
			MtTaskRoute mtTaskRoute = mtTaskRoutelist.get(0);
			MtLocationInfoIce houseInfoByMac = null;
			if (StringUtils.isNotBlank(dto.getMac())) {
				houseInfoByMac = MtCommonServiceUtils.getLocationInfoByMac(dto.getOrganId(), dto.getMac());
				if (null == houseInfoByMac) {
					rst.setIsSucc(false);
					rst.setMessage("医院没有该科室信息");
				}
			}
				
			if (rst.getIsSucc() && mtTaskRoute.getHouseId() != null 
					&& (null != houseInfoByMac && houseInfoByMac.getLocationId() != null 
					&& mtTaskRoute.getHouseId().intValue() == Integer.valueOf(houseInfoByMac.getLocationId()).intValue()
					|| dto.getHouseId() != null && dto.getHouseId().intValue() == mtTaskRoute.getHouseId().intValue())) {
				// 修改路线表
				routeExample = new MtTaskRouteCriteria();
				routeCriteria = routeExample.createCriteria();
				routeCriteria.andRouteIdEqualTo(mtTaskRoute.getRouteId());
				MtTaskRoute taskRoute = new MtTaskRoute();
				taskRoute.setUpdateDate(nowDate);
				taskRoute.setStatus(MtConstant.CLOCK_STATUS_1);
				taskRoute.setFinishContent(dto.getFinishContent());
				this.mtTaskRouteService.updateByExampleSelective(taskRoute, routeExample);
				
				if(mtTaskRoutelist.size() == 1) {
					// 由于验证的时候会把剩余的路径查询出来，如果等于代表正在处理是最后一条
					long currentTime = nowDate.getTime();
					// 计算耗时返回秒
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
					// 修改 EVT_MT_TASK
					updateMtTask = new MtTask();
					updateMtTask.setExeEndTime(nowDate);
					updateMtTask.setStatus(TransStatusEnum.TRANS_COMPLETED.getCode());
					updateMtTask.setUpdateBy(userId);
					updateMtTask.setUpdateDate(nowDate);
					updateMtTask.setTimeConsuming(taskConsume.intValue());
					updateMtTask.setIsTimeOut(isTimeOut);
					int cnt = this.mtCommonTaskService.updateMtTaskStatus(groupOrganId, updateMtTask, 
							TransStatusEnum.TRANS_RUNNING.getCode(), taskId);
					if (cnt <= 0) {
						rst.setIsSucc(false);
						rst.setMessage("该运送单已被其他人操作无法完成任务！");
						return rst;
					}
					this.mtCommonTaskService.updateTaskExcutors(groupOrganId, taskId, String.valueOf(mtTask.getExeEndUserId()), null, 
							TransStatusEnum.TRANS_COMPLETED.getCode(), isTimeOut, null, nowDate);
					/* 更新人员位置信息表 对人员未完成数量进行加- 1  */
					this.updateUserPositUnTaskNumUtils.updateUserPositUnTaskNum(organId, userId,
							(short)-1, (short)-1, mtTask.getBeginTime(), String.valueOf(mtTaskRoute.getHouseId()));	
					// 更新个人完成数
					String exeBeginTime = DateUtil.formatDateToString(mtTask.getExeBeginTime(), DateUtil.FORMAT_YYYYMMDDHHMMSS);
					MtCommonServiceUtils.savePersonalVolumeIncrease(organId, String.valueOf(userId), 1, exeBeginTime, mtTask.getTaskType());
					//最后一次完成删除任务超时提醒
					this.taskSendNoticeUtils.delTaskTimeOutNotice(taskId);
				}
				// 处理附件
				if (StringUtils.isNotBlank(dto.getFinishFileIds())) {
					CommonServiceUtils.updateRefIdByFileIds(dto.getFinishFileIds(), String.valueOf(mtTaskRoute.getRouteId()));
				}
				rst.setObj(String.valueOf(taskId));
			}else {
				rst.setIsSucc(false);
				//需要翻译成中文
				rst.setMessage("当前需完成的位置点[" + houseMap.get(String.valueOf(mtTaskRoute.getHouseId())).getLocationName() + "]");
			}
		}else {
			rst.setIsSucc(false);
			//需要翻译成中文
			rst.setMessage("所有任务点都已完成!");
		}
		if (mtTaskRoutelist.size() == 1 && null != updateMtTask) {
			// 推送mq
			this.take(updateMtTask, mtTask, String.valueOf(userId), nowDate);
		}
		return rst;
	}
	
	/**
	 * @discription 重新派单
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月9日 上午11:26:39      
	 * @param t
	 * @return     
	 */
	@Override
	public ResultDto<String, String, Integer> handlerAgainTask(int groupOrganId, MtTask beforeMtTask, FixedTaskDto dto) {
		ResultDto<String, String, Integer> rst = new ResultDto<>(true, "");
		Integer userId = dto.getUserId();
		Integer taskId = dto.getTaskId();
		Integer exeUserId = dto.getExeUserId();
		Integer exeEndUserId = dto.getExeEndUserId();
		Date nowDate = new Date();
		int version = beforeMtTask.getVersion().intValue() + MtConstant.VERSION_ADD_DEFAULT;
		
		// 扩展表更新 更新派单备注信息
		if (StringUtils.isNotBlank(dto.getSendContent())) {
			MtTaskExt mtTaskExt = new MtTaskExt();
			mtTaskExt.setSendContent(dto.getSendContent());
			this.mtCommonTaskService.updateMtTaskExtContent(groupOrganId, mtTaskExt, taskId);
		}
		
		MtTask mtTaskUpdate = new MtTask();
		mtTaskUpdate.setUpdateDate(nowDate);
		mtTaskUpdate.setUpdateBy(userId);
		mtTaskUpdate.setVersion(version);
		mtTaskUpdate.setExeEndUserId(exeEndUserId);
		mtTaskUpdate.setStatus(TransStatusEnum.TRANS_NOT_START.getCode());
		mtTaskUpdate.setSendTime(nowDate);
		mtTaskUpdate.setDispatchUserId(userId);
		List<String> statusList = new ArrayList<>();
		statusList.add(TransStatusEnum.TRANS_BACK.getCode());
		statusList.add(TransStatusEnum.TRANS_CANCEL.getCode());
		statusList.add(TransStatusEnum.TRANS_NOT_START.getCode());
		int cnt = this.mtCommonTaskService.updateMtTaskStatus(groupOrganId, mtTaskUpdate,
				statusList,taskId);
		if (cnt <= 0) {
			rst.setIsSucc(false);
			rst.setMessage("该运送单已被其他人操作无法重新派单");
			return rst;
		}
		// 删除之前的执行人
		mtCommonTaskService.deleteTaskExecutorsByTaskId(groupOrganId, taskId);
		// 处理新增运送人员信息
		List<MtTaskExecutors> excutorsList = this.initTaskDataUtils.initMtTaskExcutorsList(beforeMtTask, String.valueOf(exeUserId), 
				nowDate, exeEndUserId, mtTaskUpdate.getStatus(), nowDate, beforeMtTask.getEndTime());
		String tableIndex = DynamicTableSourceKeyHolder.getDataSourceKey();
		this.mtCommonTaskService.saveBatchMtTaskExcutors(groupOrganId, excutorsList, tableIndex);
		// 重新派单前的执行人保存到执行人log表中
		List<MtTaskExecutorsLog> exeUserLog = this.initTaskDataUtils.initExecutorsLog(beforeMtTask, String.valueOf(
				beforeMtTask.getExeEndUserId()), TransUserStatusEnum.TRANS_USER_STATUS_00.getCode(), nowDate);
		this.mtCommonTaskService.saveBatchMtTaskExcutorsLog(groupOrganId, exeUserLog, tableIndex);
		/* 更新人员位置信息表 对人员未完成数量进行加- 1  */
		List<Integer> addNumUserList = new ArrayList<>();
		addNumUserList.add(exeUserId);
		List<Integer> subNumUserList = new ArrayList<>();
		subNumUserList.add(beforeMtTask.getExeEndUserId());
		this.updateUserPositUnTaskNumUtils.updateBatchUserPositUnTaskNum(beforeMtTask.getOrganId(), 
				addNumUserList,subNumUserList, beforeMtTask.getBeginTime(), null);	
		rst.setObj(dto.getTaskId());
		
		//重新派单需要删掉以前的任务超时提醒并重新发送
		this.taskSendNoticeUtils.delTaskTimeOutNotice(taskId);
		this.taskSendNoticeUtils.sendTaskTimeOutNotice(beforeMtTask, String.valueOf(mtTaskUpdate.getExeEndUserId()), null,
				TransTaskTypeEnum.TASK_TYPE_LOOP.getCode(), beforeMtTask.getLimitMinute());
		
		// 删除之前超时未开始提醒,并重新给执行人派发超时未开始提醒
		this.taskSendNoticeUtils.delOverTimeNotStarted(taskId);
		this.taskSendNoticeUtils.sendOvertimeNotStarted(mtTaskUpdate.getExeEndUserId(), beforeMtTask.getBeginTime(),
				nowDate, taskId,  TransTaskTypeEnum.TASK_TYPE_LOOP.getCode());
		
		// 派单前执行人发送取消提醒
		taskSendNoticeUtils.sendTaskNotice(String.valueOf(beforeMtTask.getExeEndUserId()), String.valueOf(taskId), 
				MtNoticeConstant.MT_TASK_REDISPATCH_NOTICE, TransTaskTypeEnum.TASK_TYPE_LOOP.getCode(), new Object[]{});
		
		// 重新派单后执行人发送接收任务提醒
		Map<String, MtLocationInfoIce> houseInfoMap = initTaskDataUtils.getHouseInfoByTask(beforeMtTask);
		// 出发地
		String fromHouseId = String.valueOf(beforeMtTask.getFromHouseId());
		String toHouseId = String.valueOf(beforeMtTask.getToHouseId());
		String fromHouseName = !houseInfoMap.isEmpty() && null != houseInfoMap.get(fromHouseId)
				? houseInfoMap.get(fromHouseId).getLocationName() : "";
		// 目的地	
		String toHouseName = !houseInfoMap.isEmpty() && null != houseInfoMap.get(toHouseId)
				? houseInfoMap.get(toHouseId).getLocationName() : "";
		//开始结束时间
		String beginTime = DataTypeConverUtils.paresNumberToString(beforeMtTask.getBeginTime(), 
				DateUtil.FORMAT_YYYY_MM_DD_HH_MM, "HH:mm");
		// 预计结束时间
		String endTime = null;
		if (null != beforeMtTask.getLimitMinute() && beforeMtTask.getLimitMinute().intValue() > 0) {
			endTime = DateUtil.formatDateToString(new Date(DataTypeConverUtils
	    			.parseLongToDate(beforeMtTask.getBeginTime() * 100).getTime() + 
	    			(long) beforeMtTask.getLimitMinute() * 60 * 1000), "HH:mm");
		}
		// 大类名称
		String transTypeName = TransTypeEnum.getNameByCode(beforeMtTask.getTransTypeParentCode());
		
		taskSendNoticeUtils.sendTaskNotice(String.valueOf(exeUserId), String.valueOf(taskId), 
			MtNoticeConstant.MT_TASK_ACCEPT_NOTICE, TransTaskTypeEnum.TASK_TYPE_LOOP.getCode(), new Object[]{"", 
			transTypeName, fromHouseName, toHouseName, beginTime, endTime});
		return rst;
	}
		
	/**
	 * @discription 退单
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月9日 上午11:26:39      
	 * @param t
	 * @return     
	 */
	@Override
	public ResultDto<String, String, MtTask> handlerBackTask(int groupOrganId, MtTask task,
			CommonTaskDto dto){
		// 调用公共方法退单
		return this.mtCommonTaskService.updateBackTaskNotStartStatus(groupOrganId, task, dto.getUserId(), 
				new Date(), TransTaskTypeEnum.TASK_TYPE_LOOP.getCode());
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
		// 固定任务没有抢单
		return null;
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
			Integer userId, Integer userOrganId) {
		ResultDto<String, String, Integer> rst = new ResultDto<>(true,"");
		// 获取同步锁  MT_TASK_10001_MT_TASK_ID 
		Integer taskId = mtTask.getTaskId();
		Date nowDate = new Date();
		// MtTask 表设置取消状态
		MtTask updateMtTask = new MtTask();
		updateMtTask.setStatus(TransStatusEnum.TRANS_CANCEL.getCode());
		updateMtTask.setUpdateBy(userId);
		updateMtTask.setUpdateDate(nowDate);
		int cnt = this.mtCommonTaskService.updateMtTaskStatus(groupOrganId, updateMtTask,
				TransStatusEnum.TRANS_NOT_START.getCode(), taskId);
		if(cnt <= 0 ) {
			rst.setIsSucc(false);
			rst.setMessage("该运送单已完成无法取消！");
			return rst;
		}
		// 固定任务单个人 删除执行人表
		this.mtCommonTaskService.deleteTaskExecutorsByTaskId(groupOrganId, taskId);
		// 将取消的执行人新增到执行人log表
		List<MtTaskExecutorsLog> exeUserLog = this.initTaskDataUtils.initExecutorsLog(mtTask, 
				String.valueOf(mtTask.getExeEndUserId()), TransUserStatusEnum.TRANS_USER_STATUS_00.getCode(), nowDate);
		String tableIndex = DynamicTableSourceKeyHolder.getDataSourceKey();
		this.mtCommonTaskService.saveBatchMtTaskExcutorsLog(groupOrganId, exeUserLog, tableIndex);
		/* 更新人员位置信息表 对人员未完成数量进行加-1  */
		this.updateUserPositUnTaskNumUtils.updateUserPositUnTaskNum(mtTask.getOrganId(), mtTask.getExeEndUserId(),
				(short)-1,(short) 0, mtTask.getBeginTime(), null);	
		this.taskSendNoticeUtils.delTaskTimeOutNotice(taskId);
		// 删除超时未开始提醒
		this.taskSendNoticeUtils.delOverTimeNotStarted(taskId);
		if (null != mtTask.getExeEndUserId() && mtTask.getExeEndUserId().intValue() > 0) {
			// 发送取消提醒
			taskSendNoticeUtils.sendTaskNotice(String.valueOf(mtTask.getExeEndUserId()), String.valueOf(mtTask.getTaskId()), 
					MtNoticeConstant.MT_TASK_CANCEL_NOTICE,TransTaskTypeEnum.TASK_TYPE_LOOP.getCode());
		}
		// 给mq发送消息
		this.cancel(mtTask, nowDate);
		rst.setObj(taskId);
		return rst;
	}

	// 生成固定任务消息提醒运送员
	private void fixedSendAppMessage(List<MtTask> mtTaskList, FixedTaskDto dto, String taskType, Date nowDate) {
		List<Integer> locationIdList = new ArrayList<Integer>();
		locationIdList.add(dto.getFromHouseId());
		locationIdList.add(dto.getToHouseId());
		List<MtLocationInfoIce> locationInfoList = MtCommonServiceUtils.getLocationInfoList(dto.getOrganId(), locationIdList);
		Map<String, MtLocationInfoIce> locationInfoMap = AppUtils.list2Map(locationInfoList, "locationId", MtLocationInfoIce.class);
		String fromHouseName = locationInfoMap.get(String.valueOf(dto.getFromHouseId())) != null 
				&& StringUtils.isNotBlank(locationInfoMap.get(String.valueOf(dto.getFromHouseId())).getLocationName()) 
				? locationInfoMap.get(String.valueOf(dto.getFromHouseId())).getLocationName() : "";
		String toHouseIdName = locationInfoMap.get(String.valueOf(dto.getToHouseId())) != null 
				&& StringUtils.isNotBlank(locationInfoMap.get(String.valueOf(dto.getToHouseId())).getLocationName()) 
				? locationInfoMap.get(String.valueOf(dto.getToHouseId())).getLocationName() : "";
		String beginTime = DateUtil.formatDateToString(new Date(DataTypeConverUtils
    			.parseLongToDate(dto.getBeginTime()*100).getTime()), MtConstant.FORMAT_HH_MM);
		String endTime = DateTimeUtils.convertDateStr(DateUtils.addMinutes(
				DataTypeConverUtils.parseLongToDate(dto.getBeginTime()*100), dto.getLimitMinute()),
				MtConstant.FORMAT_HH_MM);
		for (MtTask task : mtTaskList) {
			try {
				appMessageUtil.syncSendAppMessage(String.valueOf(task.getExeEndUserId()), String.valueOf(task.getTaskId()), 
						MtNoticeConstant.MT_TASK_ACCEPT_NOTICE, taskType, new Object[] {"", 
					    TransTypeEnum.getNameByCode(dto.getTransTypeParentCode()), fromHouseName, 
						toHouseIdName, beginTime, endTime});
				//创建时给责任发送任务超时提醒
				this.taskSendNoticeUtils.sendTaskTimeOutNotice(task, String.valueOf(task.getExeEndUserId()), null, 
						TransTaskTypeEnum.TASK_TYPE_LOOP.getCode(), task.getLimitMinute());
				// 给派单人发送超时未开始提醒
				this.taskSendNoticeUtils.sendOvertimeNotStarted(dto.getCreateBy(), dto.getBeginTime(),
						nowDate, task.getTaskId(), TransTaskTypeEnum.TASK_TYPE_LOOP.getCode());
			} catch (Exception e) {
				logger.error("生成固定任务消息提醒运送员", e);
			}
		}
	}

	@Override
	public TransportMqDto editSourceHouseHandler(MtTask v, MtTask beforeTask,
			Date nowDate) {
		return null;
	}
}
