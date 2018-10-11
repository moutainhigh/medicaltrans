package com.segi.uhomecp.medicaltrans.trans.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import resp.RpcRespIce;

import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.constant.MtNoticeConstant;
import com.segi.uhomecp.medicaltrans.enums.TaskTrackActionEnum;
import com.segi.uhomecp.medicaltrans.enums.TransDispatchTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransStatusEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTaskTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransUserStatusEnum;
import com.segi.uhomecp.medicaltrans.trans.cache.MtTaskGrabRedisCache;
import com.segi.uhomecp.medicaltrans.trans.dao.MtTaskCommonMapper;
import com.segi.uhomecp.medicaltrans.trans.dao.MtTaskQueryMapper;
import com.segi.uhomecp.medicaltrans.trans.dto.CommonTaskDto;
import com.segi.uhomecp.medicaltrans.trans.dto.VerifyParamDto;
import com.segi.uhomecp.medicaltrans.trans.listen.event.TaskTrackEvent;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskCriteria;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutors;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutorsCriteria;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutorsLog;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExt;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExtCriteria;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskGroupRel;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskGroupRelCriteria;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRoute;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRouteCriteria;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskExecutorsService;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskExtService;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskGroupRelService;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskRouteService;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskService;
import com.segi.uhomecp.medicaltrans.trans.service.MtCommonTaskService;
import com.segi.uhomecp.medicaltrans.trans.utils.InitTaskDataUtils;
import com.segi.uhomecp.medicaltrans.trans.utils.TaskSendNoticeUtils;
import com.segi.uhomecp.medicaltrans.trans.utils.UpdateUserPositUnTaskNumUtils;
import com.segi.uhomecp.medicaltrans.utils.DynamicTableSourceKeyHolder;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.dto.ResultDto;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;
import com.xxl.job.core.log.XxlJobLogger;

@Service
public class MtCommonTaskServiceImpl implements MtCommonTaskService, ApplicationContextAware {
	
	public static final Logger logger = LoggerFactory.getLogger(MtCommonTaskServiceImpl.class);

	@Autowired
	private MtTaskExecutorsService mtTaskExecutorsService;
	
	@Autowired
	private MtTaskExtService mtTaskExtService;
	
	@Autowired
	private MtTaskRouteService mtTaskRouteService;
	
	@Autowired
	private MtTaskCommonMapper mtTaskCommonMapper;
	
	@Autowired
	public MtTaskService mtTaskService;
	
	@Autowired
	private MtTaskQueryMapper mtTaskQueryMapper;
	
	@Autowired
	private MtTaskGroupRelService mtTaskGroupRelService;
	
	@Autowired
	private InitTaskDataUtils initTaskDataUtils;
	
	@Autowired
	private UpdateUserPositUnTaskNumUtils updateUserPositUnTaskNumUtils;
	
	@Autowired
	private TaskSendNoticeUtils taskSendNoticeUtils;
	
	@Autowired
	private MtTaskGrabRedisCache mtTaskGrabRedisCache;
	
	private static ApplicationContext applicationContext;
	
	@Autowired
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext  = applicationContext;
	}
	
	/**
	 * @discription 批量保存执行人信息
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月18日 下午3:37:30      
	 * @param taskExcutorsList     
	 */
	@Override
	public void saveBatchMtTaskExcutors(int groupOrganId, List<MtTaskExecutors> taskExcutorsList, String tableIndex) {
		this.mtTaskCommonMapper.saveBatchMtTaskExcutors(taskExcutorsList, tableIndex);
	}

	/**
	 * @discription 批量保存路由信息
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月18日 下午3:37:45      
	 * @param taskRouteList     
	 */
	@Override
	public void saveBatchMtTaskRoute(int groupOrganId, List<MtTaskRoute> taskRouteList, String tableIndex) {
		this.mtTaskCommonMapper.saveBatchMtTaskRoute(taskRouteList, tableIndex);	
	}

	/**
	 * @discription 批量保存任务扩展信息
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月18日 下午3:36:54      
	 * @param taskExtList     
	 */
	@Override
	public void saveBatchMtTaskExt(int groupOrganId, List<MtTaskExt> taskExtList, String tableIndex) {
		this.mtTaskCommonMapper.saveBatchMtTaskExt(taskExtList, tableIndex);
	}

	/**
	 * @discription 更新MtTaskExcutors 人员的退单状态
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月10日 上午10:51:38      
	 * @param userId
	 * @param taskId
	 * @param nowDate     
	 */
	public ResultDto<String, String, MtTask> updateBackTaskNotStartStatus(int groupOrganId, MtTask task, 
			Integer userId, Date nowDate, String taskType) {
		ResultDto<String, String, MtTask> rst = new ResultDto<>(true, "");
		// 主负责人
		boolean isEndExeUser = false; 
		Integer exeEndUserId = task.getExeEndUserId();
		Integer taskId = task.getTaskId();
		MtTask updateMtTask = new MtTask();
		updateMtTask.setUpdateBy(userId);
		updateMtTask.setUpdateDate(nowDate);
		if(exeEndUserId != null && exeEndUserId.intValue() == userId.intValue()) {
			updateMtTask.setExeEndUserId(0);
			updateMtTask.setStatus(TransStatusEnum.TRANS_BACK.getCode());
			isEndExeUser = true;
		}
		int cnt = this.updateMtTaskStatus(groupOrganId, updateMtTask, TransStatusEnum.TRANS_NOT_START.getCode(), taskId);
		if(cnt <= 0) {
			// 当前运送单已被修改
			rst.setIsSucc(false);
			rst.setMessage("运送单状态已被修改！");
		}
		String tableIndex = DynamicTableSourceKeyHolder.getDataSourceKey();
		if(rst.getIsSucc() && isEndExeUser) {
			// 查询已经存在的执行人
			List<MtTaskExecutors> excutorsList = this.queryMtTaskExcutorsByTaskId(groupOrganId, taskId, null);
			List<Integer> exeUserList = AppUtils.list2ParamsList(excutorsList, (obj) -> obj.getExeUserId());
			// 责任人退单 删掉执行人表记录
			this.deleteTaskExecutorsByTaskId(groupOrganId, taskId);
			// 将退单的执行人新增到执行人log表
			List<MtTaskExecutorsLog> exeUserLog = initTaskDataUtils.initExecutorsLog(task, 
					exeUserList, TransUserStatusEnum.TRANS_USER_STATUS_02.getCode(), nowDate);
			this.saveBatchMtTaskExcutorsLog(groupOrganId, exeUserLog, tableIndex);
			// 主责任人退单 修改未完成人数信息
			updateUserPositUnTaskNumUtils.updateUserPositUnTaskNum(excutorsList, task, null);
			//删除任务超时提醒
			taskSendNoticeUtils.delTaskTimeOutNotice(taskId);
			// 发送退单消息给派单人
			taskSendNoticeUtils.sendTaskNotice(String.valueOf(task.getDispatchUserId()), String.valueOf(taskId), 
					MtNoticeConstant.MT_TASK_BACK_NOTICE, taskType, new Object[]{});
			// 删除超时未开始提醒
			taskSendNoticeUtils.delOverTimeNotStarted(taskId);
		}
		if(rst.getIsSucc() && !isEndExeUser) {
			// 执行人表删除当前退单人的记录
			this.deleteTaskExecutorsByTaskId(groupOrganId, taskId, String.valueOf(userId));
			// 将退单的执行人新增到执行人log表
			List<MtTaskExecutorsLog> exeUserLog = initTaskDataUtils.initExecutorsLog(task, 
					String.valueOf(userId), TransUserStatusEnum.TRANS_USER_STATUS_02.getCode(), nowDate);
			this.saveBatchMtTaskExcutorsLog(groupOrganId, exeUserLog, tableIndex);
			// 非主责任人退单 修改未完成人数信息
			updateUserPositUnTaskNumUtils.updateUserPositUnTaskNum(task.getOrganId(), userId, (short)-1, (short)0, task.getBeginTime(), null);
		}
		rst.setObj(updateMtTask);
		rst.setVal(String.valueOf(taskId));
		return rst;
	}
	
	/**
	 * @discription 通过taskList获取任务执行人
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月10日 上午11:17:05      
	 * @param taskInfoMap
	 * @return     
	 */
	@Override
	public <V> List<MtTaskExecutors> getTaskUserInfoList(int groupOrganId, List<V> taskList) {
		return mtTaskQueryMapper.getTaskUserInfo(taskList);
	}
	
	/**
	 * 
	 * 类描述: 根据taskId查询扩展信息
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年4月21日 上午9:51:57
	 */
	public MtTaskExt queryMtTaskExtInfo(int groupOrganId, Integer taskId) {
		// 查询扩展信息表
		MtTaskExtCriteria example = new MtTaskExtCriteria();
		MtTaskExtCriteria.Criteria criteria = example.createCriteria();
		criteria.andTaskIdEqualTo(taskId);
		List<MtTaskExt> extList = mtTaskExtService.selectByExample(example);
		if (AppUtils.isNotEmpty(extList)) {
			return extList.get(0);
		}
		return null;
	}
	
	/**
	 * @discription 根据Id 查找运送单
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月25日 下午5:33:45      
	 * @param taskId
	 * @return     
	 */
	@Override
	public VerifyParamDto queryMtTaskById(int groupOrganId, Integer taskId) {
		VerifyParamDto verifyRst = new VerifyParamDto();
		verifyRst.setIsSucc(true);
		MtTask mtTask = this.mtTaskService.selectByPrimaryKey(taskId);
		if(mtTask == null) {
			// 抢单已结束
			verifyRst.setIsSucc(false);
			verifyRst.setMessage("任务单【"+ taskId +"】不存在");
		}else {
			verifyRst.setMtTask(mtTask);
		}
		return verifyRst;
	}

	/**
	 * @discription 评价
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月26日 下午3:47:51      
	 * @param dto
	 * @return     
	 */
	@Override
	public ResultDto<String, String, String> handlerEvaluate(int groupOrganId, CommonTaskDto dto, MtTask mtTask) {
		ResultDto<String, String, String> rst = new ResultDto<>(true,"");
		Date nowDate = new Date();
		MtTask updateMtTask = new MtTask();
		Integer userId = dto.getUserId();
		Integer taskId = mtTask.getTaskId();
		updateMtTask.setUpdateBy(userId);
		updateMtTask.setEvaluate(dto.getEvaluate());
		updateMtTask.setUpdateDate(nowDate);
		
		int updateNum = this.updateMtTaskStatus(groupOrganId, updateMtTask, TransStatusEnum.TRANS_COMPLETED.getCode(), taskId);
		if(updateNum <= 0) {
			rst.setIsSucc(false);
			rst.setMessage("任务单状态不可编辑");
		} else {
			// 更新执行人表
			this.updateTaskExcutorsByEvaluate(groupOrganId, taskId, dto.getEvaluate(), nowDate);
			// 更新扩展表
			MtTaskExt ext = new MtTaskExt();
			if (StringUtils.isNotBlank(dto.getEvaluateContent())) {
				ext.setEvaluateContent(dto.getEvaluateContent());
			}
			// 签收人
			ext.setReceiver(userId);
			// 签收时间
			ext.setReceiveTime(nowDate);
			this.updateMtTaskExtContent(groupOrganId, ext, taskId);
			rst.setObj(String.valueOf(taskId));
		}
		// 处理 轨迹信息
		TaskTrackEvent trackEvent = new TaskTrackEvent(this, taskId, TaskTrackActionEnum.
				EVALUATE_TASK.getActionType(), userId, updateMtTask, nowDate);
		applicationContext.publishEvent(trackEvent);
		return rst;
	}
	
	/**
	 * @discription 在此输入一句话描述作用
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月27日 下午2:10:55      
	 * @param taskId
	 * @param afterStatus
	 * @param beforeList
	 * @return     
	 */
	@Override
	public int updateMtTaskStatus(int groupOrganId, MtTask mtTask, List<String> beforeList, Integer taskId) {
		MtTaskCriteria example = new MtTaskCriteria();
		MtTaskCriteria.Criteria criteria = example.createCriteria();
		criteria.andTaskIdEqualTo(taskId);
		criteria.andStatusIn(beforeList);
		return this.mtTaskService.updateByExampleSelective(mtTask, example);
	}
	
	/**
	 * @discription 在此输入一句话描述作用
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月27日 下午2:10:55      
	 * @param taskId
	 * @param afterStatus
	 * @param beforeList
	 * @return     
	 */
	@Override
	public int updateMtTaskStatus(int groupOrganId, MtTask mtTask, String beforeStatus, Integer taskId) {
		List<String> beforeList = new ArrayList<>();
		beforeList.add(beforeStatus);
		return this.updateMtTaskStatus(groupOrganId, mtTask, beforeList, taskId);
	}
	
	/**
	 * 
	 * 类描述: 根据taskId查询运送任务人员信息
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年4月21日 上午11:13:16
	 */
	@Override
	public List<MtTaskExecutors> queryMtTaskExcutorsByTaskId(int groupOrganId, Integer taskId, String sort) {
		MtTaskExecutorsCriteria example = new MtTaskExecutorsCriteria();
		MtTaskExecutorsCriteria.Criteria criteria = example.createCriteria();
		criteria.andTaskIdEqualTo(taskId);
		example.setOrderByClause(sort);
		return mtTaskExecutorsService.selectByExample(example);
	}

	/**
	 * @discription 批量新增服务处信息
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月7日 下午3:21:50      
	 * @param taskGroupList     
	 */
	@Override
	public void saveBatchMtTaskGroup(int groupOrganId, List<MtTaskGroupRel> taskGroupList, String tableIndex) {
		if (AppUtils.isNotEmpty(taskGroupList)) {
			mtTaskCommonMapper.saveBatchMtTaskGroup(taskGroupList, tableIndex);
		}
	}

	/**
	 * @discription 通过taskId删除服务处信息
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月7日 下午3:41:19      
	 * @param taskId     
	 */
	@Override
	public void deleteMtTaskGroupByTaskId(int groupOrganId, Integer taskId) {
		MtTaskGroupRelCriteria example = new MtTaskGroupRelCriteria();
		MtTaskGroupRelCriteria.Criteria criteria = example.createCriteria();
		criteria.andTaskIdEqualTo(taskId);
		mtTaskGroupRelService.deleteByExample(example);
	}
	
	/**
	 * @discription 同时修改执行人/执行人log表
	 * @author zhangyang@segimail.com       
	 * @created 2018年7月31日 下午6:47:02      
	 * @param taskId
	 * @param exeUserId
	 * @param isExeEndUser
	 * @param taskStatus
	 * @param version     
	 */
	@Override
	public void updateTaskExcutors(int groupOrganId, Integer taskId, String exeUserId,
			String isExeEndUser, String taskStatus) {
		this.updateTaskExcutors(groupOrganId, taskId, exeUserId, isExeEndUser, taskStatus, null, null, null);
	}
	
	/* 同时修改执行人/执行人log表 */
	@Override
	public void updateTaskExcutors(int groupOrganId, Integer taskId, String exeUserId, String isExeEndUser, String taskStatus, 
			String isTimeOut, Date exeBeginTime, Date exeEndTime) {
		this.updateTaskExcutors(groupOrganId, taskId, AppUtils.str2Integer(exeUserId), 
				isExeEndUser, taskStatus, isTimeOut, exeBeginTime, exeEndTime);
	}

	/* 同时修改执行人/执行人log表 */
	@Override
	public void updateTaskExcutors(int groupOrganId, Integer taskId, List<Integer> exeUserId, String isExeEndUser, String taskStatus, 
			String isTimeOut, Date exeBeginTime, Date exeEndTime) {
		Date nowDate = new Date();
		// 修改执行人表
		MtTaskExecutorsCriteria example = new MtTaskExecutorsCriteria();
		MtTaskExecutorsCriteria.Criteria criteria = example.createCriteria();
		criteria.andTaskIdEqualTo(taskId);
		criteria.andExeUserIdIn(exeUserId);
		MtTaskExecutors taskExecutors = new MtTaskExecutors();
		taskExecutors.setIsExeEndUser(isExeEndUser);
		taskExecutors.setUpdateDate(nowDate);
		taskExecutors.setTaskStatus(taskStatus);
		taskExecutors.setIsTimeOut(isTimeOut);
		taskExecutors.setExeBeginTime(exeBeginTime);
		taskExecutors.setExeEndTime(exeEndTime);
		mtTaskExecutorsService.updateByExampleSelective(taskExecutors, example);
	} 
	
	/**
	 * @discription 查询运送任务执行人,根据userId
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月14日 下午5:51:10      
	 * @param taskList
	 * @param status
	 * @param exeUserId
	 * @return     
	 */
	@Override
	public List<MtTaskExecutors> getTaskUserInfoByExeUserId(
			int groupOrganId, List<MtTask> taskList, Integer exeUserId) {
		return mtTaskQueryMapper.getTaskUserInfoByExeUserId(taskList, exeUserId);
	}
	  
	/**
	 * @discription 
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月16日 下午3:25:37      
	 * @param taskId
	 * @return     
	 */
	@Override
	public List<MtTaskGroupRel> queryServiceByTaskId(int groupOrganId, Integer taskId) {
		MtTaskGroupRelCriteria example = new MtTaskGroupRelCriteria();
		MtTaskGroupRelCriteria.Criteria criteria = example.createCriteria();
		criteria.andTaskIdEqualTo(taskId);
		return this.mtTaskGroupRelService.selectByExample(example);
	}
	
	/**
	 * @discription 修改扩展表信息
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月18日 下午6:54:45     
	 * @param dto
	 * @param taskId
	 */
	public void updateMtTaskExt(int groupOrganId, MtTaskExt ext) {
		//修改扩展表
		MtTaskExt taskExt = new MtTaskExt();
		taskExt.setSendContent(ext.getSendContent());
		taskExt.setTaskContent(ext.getTaskContent());
		taskExt.setPatientGender(ext.getPatientGender());
		taskExt.setMedicalRecNo(ext.getMedicalRecNo());
		taskExt.setBedNo(ext.getBedNo());
		taskExt.setPatientName(ext.getPatientName());
		
		MtTaskExtCriteria example = new MtTaskExtCriteria();
		MtTaskExtCriteria.Criteria criteria = example.createCriteria();
		criteria.andTaskIdEqualTo(ext.getTaskId());
		this.mtTaskExtService.updateByExampleSelective(taskExt, example);
	}

	/**
	 * @discription 签名
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月28日 上午9:51:01      
	 * @param dto     
	 */
	@Override
	public String handlerAutograph(int groupOrganId, CommonTaskDto dto) {
		// 处理附件
		RpcRespIce rsp = CommonServiceUtils.updateRefIdByFileIds(
				dto.getAutographFileIds(), String.valueOf(dto.getRouteId()));
		if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
			Date nowDate = new Date();
			// 修改路线表该条记录为已签名
			MtTaskRoute route = new MtTaskRoute();
			route.setRouteId(dto.getRouteId());
			route.setIsAutograph(MtConstant.IS_AUTOGRAPH);
			route.setUpdateDate(nowDate);
			mtTaskRouteService.updateByPrimaryKeySelective(route);
			return String.valueOf(dto.getRouteId());
		}
		return null;
	}

	/**
	 * @discription 批量保存任务信息
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月18日 下午3:36:38      
	 * @param taskList     
	 */
	@Override
	public void saveBatchMtTask(int groupOrganId, List<MtTask> taskList, String tableIndex) {
		this.mtTaskCommonMapper.saveBatchMtTask(taskList, tableIndex);
	}
	
	/**
	 * @discription 查询执行人信息,并修改未完成任务数
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月8日 下午5:37:20      
	 * @param mtTask
	 * @param userId
	 * @param nowDate     
	 */
	public void updateUserPositUnTaskNum(int groupOrganId, MtTask mtTask, String locationId) {
		List<MtTaskExecutors> executorsList = this.queryMtTaskExcutorsByTaskId(groupOrganId, mtTask.getTaskId(), null);
		Set<Integer> userList = AppUtils.list2ParamsSet(executorsList, (obj) -> obj.getExeUserId());
		if (AppUtils.isNotEmpty(userList)) {
			/* 更新人员位置信息表 对人员未完成数量进行加- 1  */
			Date executeDate = DateUtil.parseStringToDateYYYYMMDDHHMMSS(
					String.valueOf(mtTask.getBeginTime() * 100));
			updateUserPositUnTaskNumUtils.updateUserPositUnTaskNum(mtTask.getOrganId(), 
					new ArrayList<Integer>(userList), (short)-1, (short)0, executeDate, locationId);
		}
	}
	
	/**
	 * @discription 验证是否可以签名
	 * @author zhangyang@segimail.com       
	 * @created 2018年6月29日 下午4:14:16      
	 * @param routeId
	 * @return     
	 */
	@Override
	public RpcRespIce checkCanAutograph(int groupOrganId, Integer routeId) {
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(),
				RpcError.SUCCESS.getMessage(), "");
		MtTaskRoute route = mtTaskRouteService.selectByPrimaryKey(routeId);
		if (null != route && null != route.getTaskId() && route.getTaskId().intValue() > 0) {
			MtTask task = mtTaskService.selectByPrimaryKey(route.getTaskId());
			if (null != task && (TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode().equals(task.getTaskType())
					|| TransTaskTypeEnum.TASK_TYPE_SELF.getCode().equals(task.getTaskType()))
					&& !TransStatusEnum.TRANS_RUNNING.getCode().equals(task.getStatus())) {
				// 调度任务/自主任务 进行中状态才可以签名
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("任务已完成,不可签名!");
			}
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode()) && null != task 
					&& TransTaskTypeEnum.TASK_TYPE_LOOP.getCode().equals(task.getTaskType()) 
					&& !(TransStatusEnum.TRANS_RUNNING.getCode().equals(task.getStatus()) 
					|| TransStatusEnum.TRANS_NOT_START.getCode().equals(task.getStatus()))) {
				// 固定任务未开始和进行中状态可以签名
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("任务已完成,不可签名!");
			}
		}
		return rsp;
	}

	/**
	 * @discription 批量保存执行人log信息
	 * @author zhangyang@segimail.com       
	 * @created 2018年7月30日 上午11:13:19      
	 * @param taskExcutorsList     
	 */
	@Override
	public void saveBatchMtTaskExcutorsLog(int groupOrganId, 
			List<MtTaskExecutorsLog> taskExcutorsList, String tableIndex) {
		this.mtTaskCommonMapper.saveBatchMtTaskExcutorsLog(taskExcutorsList, tableIndex);
	}

	/**
	 * @discription 通过taskId和exeUserId删除执行人
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月6日 下午3:44:25      
	 * @param taskId
	 * @param userId     
	 */
	@Override
	public void deleteTaskExecutorsByTaskId(int groupOrganId, Integer taskId, String userId) {
		List<Integer> userIdList = new ArrayList<>();
		if (StringUtils.isNotBlank(userId)) {
			userIdList = AppUtils.str2Integer(userId);
		}
		this.deleteTaskExecutorsByTaskId(groupOrganId, taskId, userIdList);
	}

	/* 通过taskId删除执行人 */
	@Override
	public void deleteTaskExecutorsByTaskId(int groupOrganId, Integer taskId) {
		this.deleteTaskExecutorsByTaskId(groupOrganId, taskId, "");
	}
	
	@Override
	public void deleteTaskExecutorsByTaskId(int groupOrganId, Integer taskId,
			List<Integer> userIdList) {
		if (taskId == null) {
			return;
		}
		MtTaskExecutorsCriteria example = new MtTaskExecutorsCriteria();
		MtTaskExecutorsCriteria.Criteria criteria = example.createCriteria();
		criteria.andTaskIdEqualTo(taskId);
		if (AppUtils.isNotEmpty(userIdList)) {
			criteria.andExeUserIdIn(userIdList);
		}
		mtTaskExecutorsService.deleteByExample(example);
	}
	
	/**
	 * @discription 修改扩展表taskContent/sendContent/evaluateContent
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月6日 下午3:47:02      
	 * @param ext
	 * @param taskId     
	 */
	@Override
	public void updateMtTaskExtContent(int groupOrganId, MtTaskExt ext, Integer taskId) {
		//修改扩展表
		MtTaskExtCriteria example = new MtTaskExtCriteria();
		MtTaskExtCriteria.Criteria criteria = example.createCriteria();
		criteria.andTaskIdEqualTo(taskId);
		this.mtTaskExtService.updateByExampleSelective(ext, example);
	}

	/**
	 * @discription 修改执行人评价
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月6日 下午3:48:24      
	 * @param taskId
	 * @param version
	 * @param evaluate
	 * @param nowDate     
	 */
	@Override
	public void updateTaskExcutorsByEvaluate(int groupOrganId, Integer taskId, Short evaluate, Date nowDate) {
		// 更新执行人表
		MtTaskExecutorsCriteria example = new MtTaskExecutorsCriteria();
		MtTaskExecutorsCriteria.Criteria criteria = example.createCriteria();
		criteria.andTaskIdEqualTo(taskId);
		MtTaskExecutors taskExecutors = new MtTaskExecutors();
		taskExecutors.setUpdateDate(nowDate);
		taskExecutors.setEvaluate(evaluate);
		mtTaskExecutorsService.updateByExampleSelective(taskExecutors, example);
	}

	/**
	 * @discription 完成任务时修改路线表
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月6日 下午3:48:54      
	 * @param mtTask
	 * @param finishContent
	 * @param taskId
	 * @param nowDate     
	 */
	@Override
	public void updateTaskRoute(int groupOrganId, MtTask mtTask, String finishContent,
			Integer taskId, Date nowDate) {
		MtTaskRouteCriteria routeExample = new MtTaskRouteCriteria();
		MtTaskRouteCriteria.Criteria routeCriteria = routeExample.createCriteria();
		routeCriteria.andHouseIdEqualTo(mtTask.getToHouseId());
		routeCriteria.andTaskIdEqualTo(taskId);
		MtTaskRoute taskRoute = new MtTaskRoute();
		taskRoute.setUpdateDate(nowDate);
		taskRoute.setStatus(MtConstant.CLOCK_STATUS_1);
		taskRoute.setFinishContent(finishContent);
		this.mtTaskRouteService.updateByExampleSelective(taskRoute, routeExample);
	}

	/**
	 * @discription 更新任务单状态异常关闭
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月8日 下午5:25:41      
	 * @param organIdList
	 * @param limitDate
	 * @param nowDate
	 * @param statusList     
	 */
	private Integer updateTaskUnusalClose(List<Integer> organIdList, long limitDate, Date nowDate,
			List<String> statusList) {
		MtTaskCriteria example = new MtTaskCriteria();
		MtTaskCriteria.Criteria criteria = example.createCriteria();
		criteria.andOrganIdIn(organIdList);
		criteria.andBeginTimeLessThan(limitDate);
		criteria.andStatusIn(statusList);
		
		MtTask task = new MtTask();
		task.setStatus(TransStatusEnum.TRANS_UNUSUAL_CLOSE.getCode());
		task.setUpdateDate(nowDate);
		return this.mtTaskService.updateByExampleSelective(task, example);
	}

	/**
	 * @discription 更新任务单执行人状态异常关闭
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月8日 下午5:26:16      
	 * @param organIdList
	 * @param limitDate
	 * @param nowDate
	 * @param statusList     
	 */
	private void updateTaskExeUserUnusalClose(List<Integer> organIdList, long limitDate, Date nowDate,
			List<String> statusList) {
		MtTaskExecutorsCriteria example = new MtTaskExecutorsCriteria();
		MtTaskExecutorsCriteria.Criteria criteria = example.createCriteria();
		criteria.andOrganIdIn(organIdList);
		criteria.andBeginTimeLessThan(limitDate);
		criteria.andTaskStatusIn(statusList);
		
		MtTaskExecutors taskExecutors = new MtTaskExecutors();
		taskExecutors.setTaskStatus(TransStatusEnum.TRANS_UNUSUAL_CLOSE.getCode());
		taskExecutors.setUpdateDate(nowDate);
		this.mtTaskExecutorsService.updateByExampleSelective(taskExecutors, example);
	}

	/**
	 * @discription 通过taskId查询任务单
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月14日 下午4:33:24      
	 * @param groupOrganId
	 * @param taskId
	 * @return     
	 */
	@Override
	public MtTask queryMtTaskByTaskId(int groupOrganId, int taskId) {
		return mtTaskService.selectByPrimaryKey(taskId);
	}

	/**
	 * @discription 查询今天异常关闭的响应类型为抢单的任务单
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月31日 上午9:12:57      
	 * @param groupOrganId
	 * @param organIdList
	 * @return     
	 */
	private List<Integer> queryUnusalCloseTask(List<Integer> organIdList) {
		MtTaskCriteria example = new MtTaskCriteria();
		MtTaskCriteria.Criteria criteria = example.createCriteria();
		criteria.andOrganIdIn(organIdList);
		// 更新时间大于今天0点0分0秒
		criteria.andUpdateDateGreaterThan(DateUtil.getCurDayHHmmss(0, 0, 0));
		// 异常关闭
		criteria.andStatusEqualTo(TransStatusEnum.TRANS_UNUSUAL_CLOSE.getCode());
		return mtTaskQueryMapper.queryTaskIdPage(example, null);
	}

	/**
	 * @discription 查询异常关闭任务单的服务处关系表数据
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月31日 上午9:13:24      
	 * @param taskIdList
	 * @return     
	 */
	private Map<Integer, List<MtTaskGroupRel>> queryGroupMapByTaskList(List<Integer> taskIdList) {
		Map<Integer, List<MtTaskGroupRel>> groupMap = new HashMap<>();
		MtTaskGroupRelCriteria example = new MtTaskGroupRelCriteria();
		MtTaskGroupRelCriteria.Criteria criteria = example.createCriteria();
		criteria.andTaskIdIn(taskIdList);
		List<MtTaskGroupRel> groupList = mtTaskGroupRelService.selectByExample(example);
		if (AppUtils.isNotEmpty(groupList)) {
			groupMap = AppUtils.listGroup2Map(groupList, MtTaskGroupRel.class, "groupId", MtTaskGroupRel.class);
		}
		return groupMap;
	}

	/**
	 * @discription 删除异常关闭的抢单缓存和服务处关系表
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月31日 上午9:14:01      
	 * @param groupId
	 * @param taskIdList     
	 */
	private void deleteGrabTaskRedisAndTable(int groupId, List<Integer> taskIdList) {
		// 删除已经异常关闭的服务处关系表
		MtTaskGroupRelCriteria example = new MtTaskGroupRelCriteria();
		MtTaskGroupRelCriteria.Criteria criteria = example.createCriteria();
		criteria.andGroupIdEqualTo(groupId);
		criteria.andTaskIdIn(taskIdList);
		mtTaskGroupRelService.deleteByExample(example);
		// 删除已经异常关闭的抢单缓存
		for (Integer taskId : taskIdList) {
			mtTaskGrabRedisCache.hdelGradTaskDep(groupId, taskId);
		}
		XxlJobLogger.log("删除了任务Id为:" + taskIdList + "的异常关闭的抢单任务!");
	}

	/**
	 * @discription 运送任务预约时间7天没完成异常关闭(除取消/完成状态)
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月31日 上午10:01:48      
	 * @param groupOrganId
	 * @param organIdList
	 * @param limitDate
	 * @param nowDate
	 * @param statusList
	 * @param rsp
	 * @return     
	 */
	@Override
	public RpcRespIce handlerTaskUnusalClose(int groupOrganId,
			List<Integer> organIdList, long limitDate, Date nowDate,
			List<String> statusList, RpcRespIce rsp) {
		// 修改任务单表状态为异常关闭
		Integer unusalCloseTaskCount = this.updateTaskUnusalClose(organIdList, limitDate, nowDate, statusList);
		XxlJobLogger.log("异常关闭的任务单有" + unusalCloseTaskCount + "条");
		// 修改任务单执行人表状态为异常关闭
		this.updateTaskExeUserUnusalClose(organIdList, limitDate, nowDate, statusList);
		// 查询今天异常关闭的任务单
		List<Integer> taskIdList = this.queryUnusalCloseTask(organIdList);
		if (!AppUtils.isNotEmpty(taskIdList)) {
			return rsp;
		}
		// 处理异常关闭轨迹
		for (Integer taskId : taskIdList) {
			TaskTrackEvent trackEvent = new TaskTrackEvent(this, taskId, TaskTrackActionEnum.
					UNUSUAL_CLOSE_TASK.getActionType(), 0, null, nowDate);
			applicationContext.publishEvent(trackEvent);
		}
		// 通过taskIdList查询服务处关系表
		Map<Integer, List<MtTaskGroupRel>> groupMap = this.queryGroupMapByTaskList(taskIdList);
		if (!AppUtils.isNotEmpty(groupMap)) {
			return rsp;
		}
		Integer groupId = null;
		List<MtTaskGroupRel> groupList = new ArrayList<>();
		List<Integer> taskIds = new ArrayList<>();
		for (Entry<Integer, List<MtTaskGroupRel>> entry : groupMap.entrySet()) {
			groupId = entry.getKey();
			groupList = entry.getValue();
			if (!AppUtils.isNotEmpty(groupList)) {
				continue;
			} else {
				taskIds = AppUtils.list2ParamsList(groupList, (obj) -> obj.getTaskId());
			}
			// 删除抢单缓存和服务处关系表
			this.deleteGrabTaskRedisAndTable(groupId, taskIds);
		}
		return rsp;
	}

	/**
     * @Title: checkCanPhotograph 
     * @Description: 验证是否能够拍照 
     * @author liuyi@segimail.com 
     * @date 2018年9月12日下午4:03:25
     */
	@Override
	public RpcRespIce checkCanPhotograph(Integer groupOrganId, Integer taskId) {
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(),
				RpcError.SUCCESS.getMessage(), "");
		MtTask task = mtTaskService.selectByPrimaryKey(taskId);
		if (null != task) {
			if (TransTaskTypeEnum.TASK_TYPE_LOOP.getCode().equals(task.getTaskType())) {
				// 调度任务和自主任务,才可进行拍照
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("调度任务和自主任务,才可进行拍照!");
				return rsp;
			}
			if (!TransStatusEnum.TRANS_RUNNING.getCode().equals(task.getStatus())) {
				// 进行中状态才可以拍照
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("进行中状态才可以拍照!");
			}
		}
		return rsp;
	}
	
}