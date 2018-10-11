package com.segi.uhomecp.medicaltrans.trans.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import segi.datacachesvr.queryInfo.GroupUserBrief;
import segi.datacachesvr.queryInfo.UserInfo;
import segi.medicaltrans.common.transType.TransTypeInfo;
import segi.medicaltrans.location.common.MtLocationInfoIce;

import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.constant.MtSeqContants;
import com.segi.uhomecp.medicaltrans.enums.TransDispatchTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransStatusEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTaskTypeEnum;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutors;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutorsLog;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRoute;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.medicaltrans.utils.MtIbatchQueryServiceUtils;
import com.segi.uhomecp.utils.SeqContants;
import com.segi.uhomecp.wh.common.interfaces.InvocationHandler;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;
import com.segi.uhomecp.wh.common.utils.DataTypeConverUtils;

/**
 * Title: InitTaskDataUtils.java    
 * @Description: 初始化task数据
 * @author zhangyang@segimail.com       
 * @created 2018年8月11日 下午4:30:08
 */
@Component
public class InitTaskDataUtils {
	
	/**
	 * @discription 初始化执行人对象
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月10日 上午10:15:36     
	 * @param taskId
	 * @param userId
	 * @param version
	 * @return
	 */
	public MtTaskExecutors initSaveMtTaskExcutors(MtTask mtTask, Integer userId, 
			Integer organId, Date nowDate, String isExeEndUser, Long endTime) {
		MtTaskExecutors mtTaskExecutors = new MtTaskExecutors();
		mtTaskExecutors.setTaskExeId(SeqContants.getSequnces(MtSeqContants.MT_TASK_EXE_ID_SEQ).intValue());
		mtTaskExecutors.setTaskId(mtTask.getTaskId());
		mtTaskExecutors.setExeUserId(userId);
		mtTaskExecutors.setIsExeEndUser(isExeEndUser);// 是否主责任人 单个人处理都是主责任人
		mtTaskExecutors.setUpdateDate(nowDate);
		mtTaskExecutors.setOrganId(organId);
		mtTaskExecutors.setGroupOrganId(mtTask.getGroupOrganId());
		mtTaskExecutors.setTaskType(mtTask.getTaskType());
		mtTaskExecutors.setTaskStatus(mtTask.getStatus());
		mtTaskExecutors.setBeginTime(mtTask.getBeginTime());
		mtTaskExecutors.setIsTimeOut(MtConstant.NO_TIME_OUT);
		mtTaskExecutors.setEvaluate(MtConstant.EVALUATE_DEFAULT_VALUE);
		mtTaskExecutors.setSendTime(mtTask.getSendTime());
		mtTaskExecutors.setCreateDate(mtTask.getCreateDate());
		mtTaskExecutors.setTransTypeParentCode(mtTask.getTransTypeParentCode());
		mtTaskExecutors.setUrgency(mtTask.getUrgency());
		mtTaskExecutors.setEndTime(endTime);
		return mtTaskExecutors;
	}
	
	/**
	 * @discription 初始化执行人对象
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月6日 下午3:43:22      
	 * @param mtTask
	 * @param exeUserIds
	 * @param versionDefault
	 * @param nowDate
	 * @param exeEndUserId
	 * @param taskStatus
	 * @return     
	 */
	public List<MtTaskExecutors> initMtTaskExcutorsList(MtTask mtTask, String exeUserIds, 
			Date nowDate, Integer exeEndUserId, String taskStatus, Date sendTime, Long endTime) {
		return initMtTaskExcutorsList(mtTask, AppUtils.str2Integer(exeUserIds), 
				nowDate, exeEndUserId, taskStatus, null, MtConstant.NO_TIME_OUT, sendTime, endTime);
	}

	public List<MtTaskExecutors> initMtTaskExcutorsList(MtTask mtTask,
			List<Integer> exeUserIds, Date nowDate,Integer exeEndUserId, 
			String taskStatus, Date exeBeginTime, String isTimeOut, Date sendTime, Long endTime) {
		MtTaskExecutors mtTaskExecutors = null;
		List<MtTaskExecutors> list = new ArrayList<>();
		for (Integer exeUserId : exeUserIds) {
			mtTaskExecutors = new MtTaskExecutors();
			mtTaskExecutors.setTaskExeId(SeqContants.getSequnces(MtSeqContants.MT_TASK_EXE_ID_SEQ).intValue());
			mtTaskExecutors.setTaskId(mtTask.getTaskId());
			mtTaskExecutors.setExeUserId(exeUserId);
			mtTaskExecutors.setUpdateDate(nowDate);
			mtTaskExecutors.setOrganId(mtTask.getOrganId());
			mtTaskExecutors.setGroupOrganId(mtTask.getGroupOrganId());
			mtTaskExecutors.setTaskType(mtTask.getTaskType());
			mtTaskExecutors.setTaskStatus(taskStatus);
			mtTaskExecutors.setBeginTime(mtTask.getBeginTime());
			mtTaskExecutors.setEvaluate(MtConstant.EVALUATE_DEFAULT_VALUE);
			mtTaskExecutors.setExeBeginTime(exeBeginTime);
			mtTaskExecutors.setIsTimeOut(isTimeOut);
			mtTaskExecutors.setIsExeEndUser(exeUserId.intValue() == exeEndUserId.intValue() ? 
					MtConstant.IS_EXE_END_USER : MtConstant.DEFAULT_PERSON_LIABLE);
			// 重新派单 会更新派单时间
			mtTaskExecutors.setSendTime(sendTime);
			mtTaskExecutors.setCreateDate(mtTask.getCreateDate());
			mtTaskExecutors.setTransTypeParentCode(mtTask.getTransTypeParentCode());
			mtTaskExecutors.setUrgency(mtTask.getUrgency());
			mtTaskExecutors.setEndTime(null != endTime ? endTime : mtTask.getEndTime());
			list.add(mtTaskExecutors);
		}
		return list;
	}
	
	/**
	 * @discription  默认人数为1
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月10日 下午2:50:43      
	 * @param mtTask
	 * @param sourceHouseId
	 * @param userId
	 * @param taskId
	 * @param nowDate     
	 */
	public void initSaveMtTask(MtTask mtTask,Integer sourceHouseId,
			Integer userId, Integer taskId, Date nowDate) {
		initSaveMtTask(mtTask, sourceHouseId, userId, taskId, 
				nowDate, MtConstant.TRANS_PERSON_COUNT_DEFAULT);
	}
	
	public void initSaveMtTask(MtTask mtTask,Integer sourceHouseId,
			Integer userId, Integer taskId, Date nowDate, short transPersonCont) {
		mtTask.setTaskId(taskId);
		mtTask.setCreateBy(userId);
		mtTask.setCreateDate(DataTypeConverUtils.formatDateToLongYYYYMMDDHHMMSS(nowDate));
		mtTask.setUpdateBy(userId);
		mtTask.setUpdateDate(nowDate);
		mtTask.setSourceHouseId(sourceHouseId);
		mtTask.setVersion(MtConstant.VERSION_DEFAULT);
		if (null != mtTask.getExeEndUserId()) {
			mtTask.setExeEndUserId(mtTask.getExeEndUserId());
		}
		mtTask.setTransPersonCount(transPersonCont);
		//设置默认的评价值 0
		mtTask.setEvaluate(MtConstant.EVALUATE_DEFAULT_VALUE);
		mtTask.setRespTime(MtConstant.DEFAULT_TIME);
		mtTask.setIsTimeOut(MtConstant.NO_TIME_OUT);
		mtTask.setTimeConsuming(MtConstant.DEFAULT_TIME);
		if (StringUtils.isNotBlank(mtTask.getResType()) 
				&& TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode().equals(mtTask.getTaskType())) {
			mtTask.setStatus(TransDispatchTypeEnum.TRANS_DISPATCH_TYPE_01.getCode().equals(mtTask.getResType()) ?
					TransStatusEnum.TRANS_NOT_START.getCode() : TransStatusEnum.TRANS_ROBBING.getCode());
			// web端运送管理发起任务是直接派单的,需要处理派单时间
			mtTask.setSendTime(nowDate);
		}
	}
	
	public List<MtTaskRoute> initMtTaskRouteList(List<MtTaskRoute> routeList, Integer taskId, Date nowDate) {
		for(int i = 0 ; i < routeList.size(); i++) {
			MtTaskRoute mtTaskRoute = routeList.get(i);
			mtTaskRoute.setTaskId(taskId);
			mtTaskRoute.setUpdateDate(nowDate);
			mtTaskRoute.setRouteId(SeqContants.getSequnces(MtSeqContants.MT_TASK_ROUTE_ID_SEQ).intValue());
			mtTaskRoute.setSortNo((short)i);
		}
		return routeList;
	}
	
	/**
	 * @discription 通过用户id Set 获取用户信息map
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月10日 上午11:21:05      
	 * @param userIdSet
	 * @return     
	 */
	public Map<Integer, UserInfo> getUserInfoMap(Set<Integer> userIdSet) {
		Map<Integer, UserInfo> userInfoMap = new HashMap<>();
		if (AppUtils.isNotEmpty(userIdSet)) {
			//set转list
			List<Integer> userIdList = new ArrayList<>(userIdSet);
			userInfoMap = CommonServiceUtils.getUserInfoMap(userIdList, new InvocationHandler<Integer, Integer>() {
				@Override
				public Integer invoke(Integer obj) {
					return obj;
				}
			});
			return userInfoMap;
		}
		return userInfoMap;
	}
	
	/**
	 * @discription 获取当前用户的姓名
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月26日 上午11:54:48     
	 * @param userId
	 * @return
	 */
	public String getCurUserName(Integer userId) {
		if (null == userId) {
			return null;
		}
		UserInfo userInfo = CommonServiceUtils.getCurrentUserInfoByID(userId);
		if (null != userInfo && StringUtils.isNotBlank(userInfo.getUserName())) {
			return userInfo.getUserName();
		}
		return null;
	}
	
	/**
	 * @discription 根据taskList查询所有的科室信息,返回map
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月3日 上午10:30:41      
	 * @param taskPageList
	 * @param organId
	 * @return     
	 */
	public <V> Map<String, MtLocationInfoIce> getHouseInfoByTaskList(
			List<V> taskPageList, Class<V> c, Integer organId) {
		Set<Integer> houseIdSet = new HashSet<>();
		Set<Integer> fromHouseIdSet = AppUtils.list2ParamsSet(taskPageList, c, "fromHouseId");
		Set<Integer> toHouseIdSet = AppUtils.list2ParamsSet(taskPageList, c, "toHouseId");
		Set<Integer> sourceHouseIdSet = AppUtils.list2ParamsSet(taskPageList, c, "sourceHouseId");
		
		houseIdSet.addAll(fromHouseIdSet);
		houseIdSet.addAll(toHouseIdSet);
		houseIdSet.addAll(sourceHouseIdSet);
		if (AppUtils.isNotEmpty(houseIdSet)) {
			//查询house信息,并返回Map
			List<Integer> houseIdList = new ArrayList<Integer>(houseIdSet);
			List<MtLocationInfoIce> locationInfoList = MtCommonServiceUtils.getLocationInfoList(organId, houseIdList);
			if (AppUtils.isNotEmpty(locationInfoList)) {
				return AppUtils.list2Map(locationInfoList, (obj) -> obj.getLocationId());
			}
		}
		return new HashMap<>();
	}
	
	/**
	 * @discription 根据task查询科室信息,返回map
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月3日 上午10:31:24      
	 * @param mtTask
	 * @param organId
	 * @return     
	 */
	public Map<String, MtLocationInfoIce> getHouseInfoByTask(MtTask mtTask) {
		if (null != mtTask) {
			Set<Integer> houseIdSet = new HashSet<>();
			if (null != mtTask.getSourceHouseId()) {
				houseIdSet.add(mtTask.getSourceHouseId());
			}
			if (null != mtTask.getFromHouseId()) {
				houseIdSet.add(mtTask.getFromHouseId());
			}
			if (null != mtTask.getToHouseId()) {
				houseIdSet.add(mtTask.getToHouseId());
			}
			List<Integer> houseIdList = new ArrayList<Integer>(houseIdSet);
			List<MtLocationInfoIce> locationInfoList = MtCommonServiceUtils.getLocationInfoList(mtTask.getOrganId(), houseIdList);
			if (AppUtils.isNotEmpty(locationInfoList)) {
				return AppUtils.list2Map(locationInfoList, (obj) -> obj.getLocationId());
			}
		}
		return new HashMap<>();
	}
	
	public <V> Map<String, TransTypeInfo> getTransTypeInfoMap(
			List<V> mtTaskList, Class<V> c) {
		Map<String, TransTypeInfo> transTypeMap = new HashMap<>();
		Set<Integer> transTypeIdSet = AppUtils.list2ParamsSet(mtTaskList, c, "transTypeId");
		List<Integer> transTypeIdList = new ArrayList<Integer>(transTypeIdSet);
		if (!AppUtils.isNotEmpty(transTypeIdList)) {
			return transTypeMap;
		}
		List<TransTypeInfo> transTypeInfoList = MtCommonServiceUtils.getTransTypeInfoByTransTypeIdList(transTypeIdList);
		if (AppUtils.isNotEmpty(transTypeInfoList)) {
			transTypeMap = AppUtils.list2Map(transTypeInfoList, (obj) -> obj.getTransTypeId());
		}
		return transTypeMap;
	}

	public <V> Map<String, MtLocationInfoIce> getHouseInfoByTaskListForAppAndPad(
			List<V> taskPageList, Class<V> c, Integer organId) {
		Set<Integer> houseIdSet = new HashSet<>();
		Set<Integer> toHouseIdSet = AppUtils.list2ParamsSet(taskPageList, c, "toHouseId");
		Set<Integer> fromHouseIdSet = AppUtils.list2ParamsSet(taskPageList, c, "fromHouseId");
		
		houseIdSet.addAll(toHouseIdSet);
		houseIdSet.addAll(fromHouseIdSet);
		if (AppUtils.isNotEmpty(houseIdSet)) {
			//查询house信息,并返回Map
			List<Integer> houseIdList = new ArrayList<Integer>(houseIdSet);
			List<MtLocationInfoIce> locationInfoList = MtCommonServiceUtils.getLocationInfoList(organId, houseIdList);
			if (AppUtils.isNotEmpty(locationInfoList)) {
				return AppUtils.list2Map(locationInfoList, (obj) -> obj.getLocationId());
			}
		}
		return new HashMap<>();
	}
	
	/**
	 * @discription 初始化执行人log数据
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月8日 下午3:22:08      
	 * @param mtTask
	 * @param exeUserIds
	 * @param status
	 * @param nowDate
	 * @return     
	 */
	public List<MtTaskExecutorsLog> initExecutorsLog(MtTask mtTask,
			List<Integer> exeUserIds, String status, Date nowDate) {
		List<MtTaskExecutorsLog> exeUserLogList = new ArrayList<>();
		MtTaskExecutorsLog exeUserLog = null;
		for (Integer exeUserId : exeUserIds) {
			exeUserLog = new MtTaskExecutorsLog();
			exeUserLog.setTaskExeId(SeqContants.getSequnces(MtSeqContants.MT_TASK_EXE_LOG_ID_SEQ).intValue());
			exeUserLog.setOrganId(mtTask.getOrganId());
			exeUserLog.setGroupOrganId(mtTask.getGroupOrganId());
			exeUserLog.setTaskId(mtTask.getTaskId());
			exeUserLog.setVersion(mtTask.getVersion());
			exeUserLog.setExeUserId(exeUserId);
			exeUserLog.setTaskType(mtTask.getTaskType());
			exeUserLog.setUpdateDate(nowDate);
			exeUserLog.setStatus(status);
			if (null != mtTask.getExeEndUserId() && mtTask.getExeEndUserId().intValue() == exeUserId.intValue()) {
				exeUserLog.setIsExeEndUser(MtConstant.IS_EXE_END_USER);
			} else {
				exeUserLog.setIsExeEndUser(MtConstant.DEFAULT_PERSON_LIABLE);
			}
			exeUserLogList.add(exeUserLog);
		}
		return exeUserLogList;
	}

	/* 初始化执行人log数据 */
	public List<MtTaskExecutorsLog> initExecutorsLog(MtTask mtTask,
			String exeUserIds, String status, Date nowDate) {
		return initExecutorsLog(mtTask, AppUtils.str2Integer(exeUserIds), status, nowDate);
	}
	
	/**
	 * @discription 通过groupIDs获取userList
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月13日 下午4:59:28     
	 * @param groupIds
	 * @return
	 */
	public List<String> getUserListByGroupIds(String groupIds) {
		// 通过服务处id 查询服务处下人员
		GroupUserBrief[] userListByGroupIds = MtIbatchQueryServiceUtils.queryUserListByGroupIds(groupIds);
		if (userListByGroupIds != null && userListByGroupIds.length > 0) {
			return AppUtils.list2ParamsList(Arrays.asList(userListByGroupIds), GroupUserBrief.class, "userId");
		}		
		return new ArrayList<String>();
	}
	
	/**
	 * @discription 获取需要发送签收评价提醒的人员
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月17日 下午5:23:21     
	 * @param houseId
	 * @param createBy
	 * @return
	 */
	public List<String> getEvaluateNoticeUserIds(Integer houseId, String createBy) {
		List<String> userIds = MtCommonServiceUtils.queryUserIdsByHouseId(houseId);
		if (AppUtils.isNotEmpty(userIds)) {
			if (!userIds.contains(createBy)) {
				userIds.add(createBy);
			}
		} else {
			userIds.add(createBy);
		}
		return userIds;
	}
	
}
