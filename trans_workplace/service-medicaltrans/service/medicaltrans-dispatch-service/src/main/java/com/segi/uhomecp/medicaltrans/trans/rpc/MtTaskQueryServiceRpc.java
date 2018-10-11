package com.segi.uhomecp.medicaltrans.trans.rpc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import page.RpcPageIce;
import segi.datacachesvr.queryInfo.GroupUserBrief;
import segi.datacachesvr.queryInfo.Organ;
import segi.datacachesvr.queryInfo.TOrganInfo;
import segi.datacachesvr.queryInfo.UserInfo;
import segi.datacachesvr.queryInfo.UserInfoV2;
import segi.filecommon.CommonFileIce;
import segi.medicaltrans.common.transType.TransTypeInfo;
import segi.medicaltrans.common.userhosp.UserHospCommonIce;
import segi.medicaltrans.location.common.MtLocationInfoIce;
import segi.medicaltrans.mttask.query.FileParams;
import segi.medicaltrans.mttask.query.FixedTaskExeDetailIce;
import segi.medicaltrans.mttask.query.FixedTaskExeDetailReturnIce;
import segi.medicaltrans.mttask.query.FixedTaskExeInfoIce;
import segi.medicaltrans.mttask.query.FixedTaskExePaginatorIceRsp;
import segi.medicaltrans.mttask.query.MtAutonomousTaskDetailIce;
import segi.medicaltrans.mttask.query.MtAutonomousTaskDetailIceRsp;
import segi.medicaltrans.mttask.query.MtAutonomousTaskPageIce;
import segi.medicaltrans.mttask.query.MtAutonomousTaskPaginatorIceRsp;
import segi.medicaltrans.mttask.query.MtDispatchTaskDetailIce;
import segi.medicaltrans.mttask.query.MtDispatchTaskDetailIceRsp;
import segi.medicaltrans.mttask.query.MtDispatchTaskPageIce;
import segi.medicaltrans.mttask.query.MtDispatchTaskPaginatorIceRsp;
import segi.medicaltrans.mttask.query.MtFixedTaskDetailIce;
import segi.medicaltrans.mttask.query.MtFixedTaskDetailIceRsp;
import segi.medicaltrans.mttask.query.MtFixedTaskPageIce;
import segi.medicaltrans.mttask.query.MtFixedTaskPaginatorIceRsp;
import segi.medicaltrans.mttask.query.MtPadCommonPageIce;
import segi.medicaltrans.mttask.query.MtServiceGroupIce;
import segi.medicaltrans.mttask.query.MtTaskDetailIceParam;
import segi.medicaltrans.mttask.query.MtTaskDetailRetIce;
import segi.medicaltrans.mttask.query.MtTaskDetailRetIceRsp;
import segi.medicaltrans.mttask.query.MtTaskHistoryPaginatorIce;
import segi.medicaltrans.mttask.query.MtTaskHistoryRerurnIce;
import segi.medicaltrans.mttask.query.MtTaskPageIceParam;
import segi.medicaltrans.mttask.query.MtTaskPageRerurnIce;
import segi.medicaltrans.mttask.query.MtTaskPaginatorIce;
import segi.medicaltrans.mttask.query.TaskAndEvaluatePaginatorIceRsp;
import segi.medicaltrans.mttask.query.TaskCount;
import segi.medicaltrans.mttask.query.TaskIsTimeOutIceParam;
import segi.medicaltrans.mttask.query.WarnMinute;
import segi.medicaltrans.mttask.query._MtTaskQueryServiceIceDisp;
import segimedicaltrans.base.MtTaskBaseRsp;
import segiwh.common.User;
import Ice.Current;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.constant.MtConstant.FileRefType;
import com.segi.uhomecp.medicaltrans.enums.InvokingFlagEnum;
import com.segi.uhomecp.medicaltrans.enums.TransDispatchTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransStatusEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTaskTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransToolsEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.UrgencyEnum;
import com.segi.uhomecp.medicaltrans.trans.dto.MtTaskPageDto;
import com.segi.uhomecp.medicaltrans.trans.dto.TaskAppQueryDto;
import com.segi.uhomecp.medicaltrans.trans.dto.TaskPadQueryDto;
import com.segi.uhomecp.medicaltrans.trans.dto.TaskWebQueryDto;
import com.segi.uhomecp.medicaltrans.trans.dto.UserDto;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutors;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExt;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskGroupRel;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRoute;
import com.segi.uhomecp.medicaltrans.trans.service.MtCommonTaskService;
import com.segi.uhomecp.medicaltrans.trans.service.MtTaskQueryService;
import com.segi.uhomecp.medicaltrans.trans.utils.InitTaskDataUtils;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.medicaltrans.utils.MtIbatchQueryServiceUtils;
import com.segi.uhomecp.medicaltrans.utils.MtTaskHisQueryUtil;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.utils.DateTimeUtils;
import com.segi.uhomecp.utils.DateUtil;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.enums.SexEnum;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;
import com.segi.uhomecp.wh.common.utils.DataTypeConverUtils;
import com.segi.uhomecp.wh.common.utils.PageUtils;
/**
 * Title: MtTaskManagerAppCommonServiceRpc.java    
 * @Description: 运送管理 app公共接口 rpc
 * @author zhangyang@segimail.com       
 * @created 2018年4月9日 下午4:02:12
 */
@Component
public class MtTaskQueryServiceRpc extends _MtTaskQueryServiceIceDisp{

	/**  描述   (@author: zhangyang@segimail.com) */      
	    
	private static final long serialVersionUID = 7065341426928764645L;

	public static final Logger logger = LoggerFactory.getLogger(MtTaskQueryServiceRpc.class);
	
	@Autowired
	private MtCommonTaskService mtTaskCommonService;
	
	@Autowired
	private MtTaskQueryService mtTaskQueryService;
	
	@Autowired
	private InitTaskDataUtils initTaskDataUtils;

	/**
	 * @discription 历史任务分页
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月8日 下午6:56:18      
	 * @param mtTaskHistoryPageIceParam
	 * @param __current
	 * @return     
	 */
	@Override
	public MtTaskHistoryPaginatorIce queryMtTaskHistoryPage(
			MtTaskPageIceParam mtTaskPageIceParam,
			Current __current) {
		MtTaskHistoryPaginatorIce rsp = new MtTaskHistoryPaginatorIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new RpcPageIce(mtTaskPageIceParam.getPageLength(), mtTaskPageIceParam.getPageNo(), "0"), 
				new ArrayList<MtTaskHistoryRerurnIce>());
		try {
			MtTaskPageDto pageDto = BeanCopierUtils.copyProperties(mtTaskPageIceParam, MtTaskPageDto.class, true);
			// 获取一级物业Id
			TOrganInfo organ = CommonServiceUtils.getTopOrganByOrganID(pageDto.getOrganId());
			Integer groupOrganId = null;
			if (null != organ) {
				groupOrganId = organ.getOrganId();
			} else {
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("查不到一级物业,查询历史任务失败!");
				return rsp;
			}
			PageList<TaskAppQueryDto> historyList = mtTaskQueryService.queryMtTaskHistoryPage(groupOrganId, pageDto);
			Map<String, TransTypeInfo> transTypeMap = new HashMap<>();
			if (AppUtils.isNotEmpty(historyList)) {
				//1.获取出发地/目的地/科室id Set
				Map<String, MtLocationInfoIce> houseInfoMap = initTaskDataUtils.
						getHouseInfoByTaskListForAppAndPad(historyList, TaskAppQueryDto.class, pageDto.getUserOrganId());
				//3.查询小类信息map
				if (!TransTaskTypeEnum.TASK_TYPE_LOOP.getCode().equals(pageDto.getTaskType())) {
					// 固定任务不查询小类
					transTypeMap = initTaskDataUtils.getTransTypeInfoMap(historyList, TaskAppQueryDto.class);
				}
				//处理数据
				List<MtTaskHistoryRerurnIce> resultList = initMtTaskHistoryData(pageDto, historyList, houseInfoMap, transTypeMap);
				rsp.setPaginator(PageUtils.paginator2RpcPageIce(historyList.getPaginator()));
				rsp.setResultList(resultList);
			}
		} catch (Exception e) {
			logger.error("queryMtTaskHistoryPage", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}

	/**
	 * @discription 处理数据
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月9日 上午11:19:13     
	 * @param mtTaskHistoryPageIceParam
	 * @param historyList
	 * @param houseInfoMap
	 * @param transTypeMap
	 * @return
	 */
	private List<MtTaskHistoryRerurnIce> initMtTaskHistoryData(
			MtTaskPageDto dto, PageList<TaskAppQueryDto> historyList,
			Map<String, MtLocationInfoIce> houseInfoMap, Map<String, TransTypeInfo> transTypeMap) {
		MtLocationInfoIce positInfo = null;
		List<MtTaskHistoryRerurnIce> resultList = new ArrayList<>();
		for (TaskAppQueryDto mtTask : historyList) {
			MtTaskHistoryRerurnIce history = BeanCopierUtils.copyProperties(mtTask, MtTaskHistoryRerurnIce.class, true);
			//出发地
			positInfo = houseInfoMap.get(String.valueOf(mtTask.getFromHouseId()));
			history.setFromHouseName(null != positInfo ? positInfo.getLocationName() : "");
			//目的地
			positInfo = houseInfoMap.get(String.valueOf(mtTask.getToHouseId()));
			history.setToHouseName(null != positInfo ? positInfo.getLocationName() : "");
			//运送工具名称
			history.setTransToolsName(TransToolsEnum.getNameByCode(mtTask.getTransTools()));
			//运送大类名称
			history.setTransTypeParentCodeName(TransTypeEnum.getNameByCode(mtTask.getTransTypeParentCode()));
			//小类名称
			if (!transTypeMap.isEmpty()) {
				TransTypeInfo transTypeIce = transTypeMap.get(String.valueOf(mtTask.getTransTypeId()));
				history.setTransTypeName(null != transTypeIce ? transTypeIce.getTransTypeName() : "");
			}
			history.setStatusName(TransStatusEnum.TRANS_COMPLETED.getName());
			//任务类型是调度任务时
			history.setUrgencyName(UrgencyEnum.getNameByCode(mtTask.getUrgency()));
			history.setExeBeginTime(DateUtil.formatDateToString(
					mtTask.getExeBeginTime(), MtConstant.FORMAT_YYYY_MM_DD_HH_MM));
			history.setExeEndTime(DateUtil.formatDateToString(
					mtTask.getExeEndTime(), MtConstant.FORMAT_YYYY_MM_DD_HH_MM));
			resultList.add(history);
		}
		return resultList;
	}

	/**
	 * @discription 运送管理分页查询
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月8日 上午10:08:49      
	 * @param mtTaskPageIceParam
	 * @param __current
	 * @return     
	 */
	@Override
	public MtTaskPaginatorIce queryMtTaskPage(
			MtTaskPageIceParam mtTaskPageIceParam, Current __current) {
		MtTaskPaginatorIce rsp = new MtTaskPaginatorIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new RpcPageIce(mtTaskPageIceParam.getPageLength(), mtTaskPageIceParam.getPageNo(), "0"), 
				new ArrayList<MtTaskPageRerurnIce>(), new WarnMinute());
		try {
			MtTaskPageDto dto = BeanCopierUtils.copyProperties(mtTaskPageIceParam, MtTaskPageDto.class, true);
			// 获取一级物业Id
			TOrganInfo topOrgan = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
			Integer groupOrganId = null;
			if (null != topOrgan) {
				groupOrganId = topOrgan.getOrganId();
			} else {
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("查不到一级物业,运送任务列表查询失败!");
				return rsp;
			}
			// 处理任务单预警时间
			WarnMinute warnMinute = new WarnMinute();
			warnMinute.setNoSendWarnMinute(String.valueOf(MtCommonServiceUtils.getTaskNoSendWarnMinute()));
			warnMinute.setNoStartWarnMinute(String.valueOf(MtCommonServiceUtils.getTaskNoStartWarnMinute()));
			warnMinute.setNoRobWarnMinute(String.valueOf(MtCommonServiceUtils.getTaskNoRobWarnMinute()));
			warnMinute.setSystemTime(DateUtil.formatDateToString(new Date(), MtConstant.FORMAT_YYYY_MM_DD_HH_MM));
			PageList<TaskWebQueryDto> taskPageList = mtTaskQueryService.queryMtTaskPage(groupOrganId, dto);
			if (AppUtils.isNotEmpty(taskPageList)) {
				// 处理导出数据新增字段信息
				handleExportExtInfo(groupOrganId, mtTaskPageIceParam.isExportFlag(), taskPageList);
				//1.分页查询是同一个项目下的,返回一样的organName
				Organ organ = CommonServiceUtils.getOrganByID(dto.getOrganId());
				String organName = null != organ ? organ.getOrganName() : "";
				//2.通过taskPageList查询运送员List
				List<MtTaskExecutors> taskUserInfoList = mtTaskCommonService.getTaskUserInfoList(groupOrganId, taskPageList);
				//3.获取所有的用户信息map
				Map<Integer, UserInfo> allUserInfoMap = new HashMap<Integer, UserInfo>();
				//4.获取所有的科室信息
				Map<String, MtLocationInfoIce> houseInfoMap = new HashMap<String, MtLocationInfoIce>();
				if (mtTaskPageIceParam.isExportFlag()) {
					// 如果是导出调用优化后的方法
					allUserInfoMap = getExportAllUserInfoMap(taskPageList, TaskWebQueryDto.class, taskUserInfoList);
					// 导出调用 优化后查询科室的方法
					houseInfoMap = getHouseInfoByTaskList(taskPageList, TaskWebQueryDto.class, dto.getOrganId());
				} else {
					allUserInfoMap = getAllUserInfoMap(taskPageList, TaskWebQueryDto.class, taskUserInfoList);
					houseInfoMap = initTaskDataUtils.getHouseInfoByTaskList(taskPageList, 
							TaskWebQueryDto.class, dto.getOrganId());
				}
				// 5.获取任务执行人用户信息map
				Map<Integer, List<User>> userIceMap = getExeUserInfoMap(taskUserInfoList, allUserInfoMap);
//				Map<String, MtLocationInfoIce> houseInfoMap = initTaskDataUtils.getHouseInfoByTaskList(
//						taskPageList, TaskWebQueryDto.class, dto.getOrganId());
				//6.处理运行单分页数据
				List<MtTaskPageRerurnIce> resultList = initTaskPageData(taskPageList,
						houseInfoMap, userIceMap, organName, allUserInfoMap);
				// 设置签收人信息
				setUserHsopInfoList(resultList, taskPageList, mtTaskPageIceParam);
				rsp.setPaginator(PageUtils.paginator2RpcPageIce(taskPageList.getPaginator()));
				rsp.setResultList(resultList);
				rsp.setWarnMinute(warnMinute);
			}
		} catch (Exception e) {
			logger.error("queryMtTaskPage", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}
	
	/**
	 * @Title: getExportAllUserInfoMap 
	 * @Description: 导出查询人员信息 
	 * @author liuyi@segimail.com 
	 * @date 2018年9月27日下午5:38:19
	 */
	private <V> Map<Integer, UserInfo> getExportAllUserInfoMap(List<V> taskList, Class<V> c, List<MtTaskExecutors> exeUserList) {
		Set<Integer> userIdSet = new HashSet<>();
		if (AppUtils.isNotEmpty(taskList)) {
			Set<Integer> createBySet = AppUtils.list2ParamsSet(taskList, c, "createBy");
			userIdSet.addAll(createBySet);
			// 派单人Id集合
			Set<Integer> dispatchUserIdSet = AppUtils.list2ParamsSet(taskList, c, "dispatchUserId");
			userIdSet.addAll(dispatchUserIdSet);
			// 签收人Id集合
			Set<Integer> receiverSet = AppUtils.list2ParamsSet(taskList, c, "receiver");
			userIdSet.addAll(receiverSet);
		}
		if (AppUtils.isNotEmpty(exeUserList)) {
			Set<Integer> exeUserIdSet = AppUtils.list2ParamsSet(exeUserList, (obj) -> obj.getExeUserId());
			userIdSet.addAll(exeUserIdSet);
		}
		return MtTaskHisQueryUtil.getAllUserInfoMap(new ArrayList<Integer>(userIdSet));
	}

	/**
	 * @Title: setUserHsopInfoList   
	 *  设置签收人信息 
	 * @author zhaoqing  
	 * @param resultList
	 * @param pageList
	 * @param dto 
	 */
	private void setUserHsopInfoList(List<MtTaskPageRerurnIce> resultList, 
			PageList<TaskWebQueryDto> pageList, MtTaskPageIceParam mtTaskPageIceParam) {
		if (mtTaskPageIceParam.isExportFlag()) {
			// 获取签收人Id集合
			Set<Integer> userIdSet = AppUtils.list2ParamsSet(
					pageList, TaskWebQueryDto.class, "receiver");
			List<Integer> userIdList = new ArrayList<>(userIdSet);
			// 删除null对象
			userIdList.removeAll(Collections.singleton(null));
			// 设置签收人信息
			handleReceiverInfoList(String.valueOf(mtTaskPageIceParam.getOrganId()), 
					userIdList, resultList);
		}	
	}
	
	/**
	 * @Title: handleReceiverInfoList   
	 *  导出列表数据设置签收人信息 
	 * @author zhaoqing  
	 * @param organId
	 * @param userIdList
	 * @param resultList 
	 */
	private void handleReceiverInfoList(String organId, 
			List<Integer> userIdList, List<MtTaskPageRerurnIce> resultList) {
		if (!AppUtils.isNotEmpty(resultList)) {
			return;
		}
		// 查询签收人科室信息
		List<UserHospCommonIce> userHospList = MtCommonServiceUtils
				.queryHospUserInfoList(organId, userIdList);
		// List集合转Map
		Map<String, UserHospCommonIce> userHospMap = AppUtils.list2Map(
				userHospList, "userId", UserHospCommonIce.class);
		// 查询用户信息
		Map<Integer, UserInfoV2> userInfoMap = MtIbatchQueryServiceUtils
				.queryUserMapByUserIds(userIdList);
		for (MtTaskPageRerurnIce mtTaskPageIce : resultList) {
			String receiverIdStr = mtTaskPageIce.getReceiver();
			Integer receiverId = null;
			if (StringUtils.isNotEmpty(receiverIdStr)) {
				receiverId = Integer.valueOf(receiverIdStr);
			}
			String receiverUserName = "";
			// 签收人信息
			UserInfoV2 userInfo = userInfoMap.get(receiverId);
			if (null != userInfo) {
				receiverUserName = userInfo.getUserName();
			}
			String receiverHouseName = "";
			// 科室信息
			UserHospCommonIce userHospCommonIce = userHospMap.get(receiverIdStr);
			if (null != userHospCommonIce) {
				receiverHouseName = userHospCommonIce.getHouseName();	
			}
			if (StringUtils.isNotEmpty(receiverUserName) 
					&& StringUtils.isNotEmpty(receiverHouseName)) {
				// 签收人名称和科室名称拼接
				receiverUserName = receiverUserName + "(" + receiverHouseName + ")";
			}
			mtTaskPageIce.setReceiverUserName(receiverUserName);
		} 
	}
	
	/**
	 * @Title: handleExportExtInfo   
	 *  处理导出增加的扩展字段信息 
	 * @author zhaoqing  
	 * @param groupOrganId
	 * @param exportFlag 导出标识，true表示为导出查询数据
	 * @param taskPageList
	 */
	private void handleExportExtInfo(Integer groupOrganId, boolean exportFlag, 
			PageList<TaskWebQueryDto> taskPageList) {
		if (!exportFlag) { // 不是导出操作直接返回
			return;
		}
		// 获取任务Id
		List<Integer> taskIdList = AppUtils.list2ParamsList(
				taskPageList, TaskWebQueryDto.class, "taskId");
		List<MtTaskExt> taskExtList = new ArrayList<>();
		if (AppUtils.isNotEmpty(taskIdList)) {
			// 查询任务扩展信息
			taskExtList = mtTaskQueryService.queryTaskExtInfoByTaskIds(groupOrganId, taskIdList);
		}
		// 将扩展信息设置到返回的结果集中
		transferTaskExtToTaskPageDto(taskExtList, taskPageList);
	}
	
	/**
	 * @Title: transferTaskExtToTaskPageDto   
	 *  将扩展信息设置到返回的结果集中
	 * @author zhaoqing  
	 * @param taskExtList
	 * @param pageDtoList
	 */
	private void transferTaskExtToTaskPageDto(
			List<MtTaskExt> taskExtList, List<TaskWebQueryDto> pageDtoList) {
		if (!AppUtils.isNotEmpty(taskExtList) || !AppUtils.isNotEmpty(pageDtoList)) {
			return;
		}
		// list对象转成Map集合
		Map<Integer, MtTaskExt> taskExtMap = AppUtils.list2Map(taskExtList, "taskId", MtTaskExt.class);
		for (TaskWebQueryDto taskDto : pageDtoList) {
			MtTaskExt mtTaskExt = taskExtMap.get(taskDto.getTaskId());
			if (null != mtTaskExt) {
				taskDto.setTaskContent(mtTaskExt.getTaskContent());
				taskDto.setReceiver(mtTaskExt.getReceiver());
				taskDto.setReceiveTime(mtTaskExt.getReceiveTime());
				taskDto.setEvaluateContent(mtTaskExt.getEvaluateContent());
			}
		}
	}

	/**
	 * @discription 获取执行人用户信息,返回taskId为key,List<User>为value的map
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月26日 上午10:39:55     
	 * @param taskUserInfoList
	 * @param userInfoMap
	 * @return
	 */
	private Map<Integer, List<User>> getExeUserInfoMap(
			List<MtTaskExecutors> taskUserInfoList,
			Map<Integer, UserInfo> userInfoMap) {
		UserInfo userInfo = null;
		UserDto userDto = null;
		List<UserDto> userList = new ArrayList<>(); 
		for (MtTaskExecutors exeUserInfo : taskUserInfoList) {
			Integer userId = exeUserInfo.getExeUserId();
			userDto = new UserDto();
			userInfo = userInfoMap.get(userId);
			userDto.setTaskId(exeUserInfo.getTaskId());
			userDto.setUserId(String.valueOf(userId));
			userDto.setUserName(null != userInfo ? userInfo.getUserName() : "");
			userDto.setUserNo(null != userInfo ? userInfo.getJobNumber() : "");
			userDto.setTel(null != userInfo ? userInfo.getTel() : "");
			userList.add(userDto);
		}
		return AppUtils.listGroup2Map(userList, UserDto.class, "taskId", User.class);
	}
	
	/**
	 * @discription 获取所有的user信息,返回userId为key,userInfo为value的map
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月26日 上午10:40:53     
	 * @param taskList
	 * @param exeUserList
	 * @return
	 */
	public <V> Map<Integer, UserInfo> getAllUserInfoMap(List<V> taskList, Class<V> c, List<MtTaskExecutors> exeUserList){
		Set<Integer> userIdSet = new HashSet<>();
		if (AppUtils.isNotEmpty(taskList)) {
			Set<Integer> createBySet = AppUtils.list2ParamsSet(taskList, c, "createBy");
			userIdSet.addAll(createBySet);
			// 派单人Id集合
			Set<Integer> dispatchUserIdSet = AppUtils.list2ParamsSet(taskList, c, "dispatchUserId");
			userIdSet.addAll(dispatchUserIdSet);
			// 签收人Id集合
			Set<Integer> receiverSet = AppUtils.list2ParamsSet(taskList, c, "receiver");
			userIdSet.addAll(receiverSet);
		}
		if (AppUtils.isNotEmpty(exeUserList)) {
			Set<Integer> exeUserIdSet = AppUtils.list2ParamsSet(exeUserList, (obj) -> obj.getExeUserId());
			userIdSet.addAll(exeUserIdSet);
		}
		return initTaskDataUtils.getUserInfoMap(userIdSet);
	}
	
	/**
	 * @discription 查询下单人和主责任人用户信息
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月12日 下午2:16:21     
	 * @param taskList
	 * @param c
	 * @return
	 */
	public <V> Map<Integer, UserInfo> getUserInfoMap(List<V> taskList, Class<V> c){
		Set<Integer> userIdSet = new HashSet<>();
		if (AppUtils.isNotEmpty(taskList)) {
			Set<Integer> createBySet = AppUtils.list2ParamsSet(taskList, c, "createBy");
			userIdSet.addAll(createBySet);
			Set<Integer> exeEndUserSet = AppUtils.list2ParamsSet(taskList, c, "exeEndUserId");
			userIdSet.addAll(exeEndUserSet);
		}
		return initTaskDataUtils.getUserInfoMap(userIdSet);
	}
	
	/**
	 * @discription 详情查询人员信息
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月11日 下午4:12:35     
	 * @param createBy
	 * @param exeUserList
	 * @param receiver
	 * @return
	 */
	public Map<Integer, UserInfo> getUserInfoMapForDetail(Integer createBy, 
			List<MtTaskExecutors> exeUserList, String receiver, Integer dispatchUserId){
		Set<Integer> userIdSet = new HashSet<>();
		if (null != createBy && createBy.intValue() > 0) {
			// 下单人
			userIdSet.add(createBy);
		}
		if (StringUtils.isNotBlank(receiver)) {
			// 签收人
			userIdSet.add(Integer.valueOf(receiver));
		}
		if (null != dispatchUserId && dispatchUserId.intValue() > 0) {
			// 派单人
			userIdSet.add(dispatchUserId);
		}
		if (AppUtils.isNotEmpty(exeUserList)) {
			// 执行人
			Set<Integer> exeUserIdSet = AppUtils.list2ParamsSet(exeUserList, (obj) -> obj.getExeUserId());
			userIdSet.addAll(exeUserIdSet);
		}
		return initTaskDataUtils.getUserInfoMap(userIdSet);
	}
	
	public Map<Integer, UserInfo> getAllUserInfoMapForAppAndPad(List<MtTaskExecutors> exeUserList){
		Set<Integer> exeUserIdSet = AppUtils.list2ParamsSet(exeUserList, (obj) -> obj.getExeUserId());
		return initTaskDataUtils.getUserInfoMap(exeUserIdSet);
	}

	/**
	 * @discription 处理查询出的数据
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月8日 下午4:40:00     
	 * @param taskPageList
	 * @param houseInfoMap
	 * @param exeUserIdMap
	 * @param organName
	 * @param userInfoMap
	 * @return
	 */
	private List<MtTaskPageRerurnIce> initTaskPageData(PageList<TaskWebQueryDto> taskPageList,
			Map<String, MtLocationInfoIce> houseInfoMap,
			Map<Integer,List<User>> userIceMap, String organName,
			Map<Integer, UserInfo> allUserInfoMap) {
		MtTaskPageRerurnIce returnIce = null;
		MtLocationInfoIce positInfo = null;
		UserInfo userInfo = null;
		List<MtTaskPageRerurnIce> resultList = new ArrayList<>();
		List<User> userList = new ArrayList<>();
		for (TaskWebQueryDto task : taskPageList) {
			returnIce = BeanCopierUtils.copyProperties(task, MtTaskPageRerurnIce.class, true);
			// 组织名称
			returnIce.setOrganName(organName);
			//下单人
			userInfo = allUserInfoMap.get(task.getCreateBy());
			returnIce.setCreateByName(null != userInfo ? userInfo.getUserName() : "");
			// 派单人
			userInfo = allUserInfoMap.get(task.getDispatchUserId());
			returnIce.setDispatchUserName(null != userInfo ? userInfo.getUserName() : "");
//			// 签收人
//			userInfo = allUserInfoMap.get(task.getReceiver());
//			returnIce.setReceiverUserName(null != userInfo ? userInfo.getUserName() : "");
			// 任务来源
			positInfo = houseInfoMap.get(String.valueOf(task.getSourceHouseId()));
			returnIce.setSourceHouseName(null != positInfo ? positInfo.getLocationName() : "");
			// 运送类型名称
			returnIce.setTransTypeParentCodeName(TransTypeEnum.getNameByCode(task.getTransTypeParentCode()));
			// 紧急程度名称
			returnIce.setUrgencyName(UrgencyEnum.getNameByCode(task.getUrgency()));
			// 出发地
			positInfo = houseInfoMap.get(String.valueOf(task.getFromHouseId()));
			returnIce.setFromHouseName(null != positInfo ? positInfo.getLocationName() : "");
			// 目的地
			positInfo = houseInfoMap.get(String.valueOf(task.getToHouseId()));
			returnIce.setToHouseName(null != positInfo ? positInfo.getLocationName() : "");
			// 执行人
			if (!(TransStatusEnum.TRANS_BACK.getCode().equals(task.getStatus())
	        		|| TransStatusEnum.TRANS_CANCEL.getCode().equals(task.getStatus()))) {
				userList = userIceMap.get(task.getTaskId());
				returnIce.setUserList(AppUtils.isNotEmpty(userList) ? userList : new ArrayList<User>());
			}
			// 响应类型
			returnIce.setResTypeName(TransDispatchTypeEnum.getNameByCode(task.getResType()));
			// 任务类型
			returnIce.setTaskTypeName(TransTaskTypeEnum.getNameByCode(task.getTaskType()));
			// 任务状态
			returnIce.setStatusName(TransStatusEnum.getNameByCode(task.getStatus()));
			// 下单时间
			String createDate = DataTypeConverUtils.paresLongToString(
					Long.valueOf(returnIce.getCreateDate()), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
			returnIce.setCreateDate(createDate);
			// 预计开始时间
			returnIce.setBeginTime(DataTypeConverUtils.paresNumberToString(task.getBeginTime(), 
					DateUtil.FORMAT_YYYY_MM_DD_HH_MM, MtConstant.FORMAT_YYYY_MM_DD_HH_MM));
			// 预计结束时间
			returnIce.setEndTime(DataTypeConverUtils.paresNumberToString(task.getEndTime(), 
					DateUtil.FORMAT_YYYY_MM_DD_HH_MM, MtConstant.FORMAT_YYYY_MM_DD_HH_MM));
			// 设置评价信息
			setEvaluate(task.getEvaluate(), returnIce);
			resultList.add(returnIce);
		}
		return resultList;
	}
	
	/**
	 * @Title: setEvaluate   
	 *  设置评价值信息 
	 * @author zhaoqing  
	 * @param evaluate
	 * @param returnIce 
	 */
	public static void setEvaluate(Short evaluate, MtTaskPageRerurnIce returnIce) {
		if (evaluate == null || MtConstant.EVALUATE_DEFAULT_VALUE == evaluate) {
			// 评价值为默认值时，评价结论设置为空
			returnIce.setEvaluate("");
		} else {
			returnIce.setEvaluate(evaluate + MtConstant.EVALUATE_NOTE);
		}
	}

	/**
	 * 
	 * 类描述: 查看调度任务
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年4月20日 下午4:02:18
	 */
	@Override
	public MtDispatchTaskDetailIceRsp queryMtDispatchTaskDetailApp(int organId, int taskId,
			String userId, Current __current) {
		MtDispatchTaskDetailIceRsp result = new MtDispatchTaskDetailIceRsp();
		result.code = RpcError.SUCCESS.getCode();
		result.message = RpcError.SUCCESS.getMessage();
		try {
			// 获取一级物业Id
			TOrganInfo topOrgan = CommonServiceUtils.getTopOrganByOrganID(organId);
			Integer groupOrganId = null;
			if (null != topOrgan) {
				groupOrganId = topOrgan.getOrganId();
			} else {
				result.setCode(RpcError.FAIL.getCode());
				result.setMessage("查不到一级物业,查询调度任务详情失败!");
				return result;
			}
			MtTask task = mtTaskCommonService.queryMtTaskByTaskId(groupOrganId, taskId);
			if (null == task) {
				result.setCode(RpcError.FAIL.getCode());
				result.setMessage("调度任务不存在");
				return result;
			}
			MtTaskExt mtTaskExt = this.mtTaskCommonService.queryMtTaskExtInfo(groupOrganId, taskId);
	        MtDispatchTaskDetailIce detailIce = BeanCopierUtils.copyProperties(task, MtDispatchTaskDetailIce.class, true);
	        if (null != mtTaskExt) {
				BeanCopierUtils.copyProperties(mtTaskExt, detailIce, true);
			}
	        this.queryTaskInfo(detailIce, task);
	        // 添加性别
	        detailIce.setPatientGenderName(SexEnum.getNameByCode(detailIce.getPatientGender()));
	        // 查询运送员
	        List<MtTaskExecutors> exeUserList = this.mtTaskCommonService.queryMtTaskExcutorsByTaskId(groupOrganId, taskId, null);
	        // list排序
	        Collections.sort(exeUserList, (s1,s2) -> 
        		(StringUtils.isNotBlank(s2.getIsExeEndUser()) ? s2.getIsExeEndUser() : MtConstant.DEFAULT_PERSON_LIABLE).compareTo(
        				StringUtils.isNotBlank(s1.getIsExeEndUser()) ? s1.getIsExeEndUser() : MtConstant.DEFAULT_PERSON_LIABLE));
//	        Map<Integer, UserInfo> userInfoMap = getAllUserInfoMapForAppAndPad(exeUserList);
	        Map<Integer, UserInfo> userInfoMap = getUserInfoMapForDetail(null, exeUserList, detailIce.getReceiver(), null);
	        // 查询路线   完成情况
	        MtTaskRoute taskRoute = mtTaskQueryService.queryMtTaskRouteByTaskId(groupOrganId, task.getTaskId());
	        detailIce.setRouteId(taskRoute.getRouteId() == null ? "" : String.valueOf(taskRoute.getRouteId()));
	        detailIce.setFinishContent(taskRoute.getFinishContent() == null ? "" : taskRoute.getFinishContent());
	        List<User> userList = new ArrayList<User>();
	        User user = null;
	        UserInfo userInfo = null;
	        for (MtTaskExecutors exeUser : exeUserList) {
	        	user = new User();
	        	user.setUserId(String.valueOf(exeUser.getExeUserId()));
	        	userInfo = userInfoMap.get(exeUser.getExeUserId());
	        	user.setUserName(null != userInfo ? userInfo.getUserName() : "");
	        	user.setUserNo(null != userInfo ? userInfo.getJobNumber() : "");
	        	userList.add(user);
	        	if (StringUtils.isNotBlank(userId) && Integer.valueOf(userId).intValue() > 0 
	        			&& Integer.valueOf(userId).intValue() == exeUser.getExeUserId()
	        			&& TransStatusEnum.TRANS_ROBBING.getCode().equals(task.getStatus())) {
					//说明抢单了
	        		detailIce.setStatus(TransStatusEnum.TRANS_ROB_COMPLETED.getCode());
	        		detailIce.setStatusName(TransStatusEnum.TRANS_ROB_COMPLETED.getName());
				}
			}
	        // 处理签收人
	        if (TransStatusEnum.TRANS_COMPLETED.getCode().equals(task.getStatus()) 
	        		&& StringUtils.isNotBlank(detailIce.getReceiver())) {
	        	userInfo = userInfoMap.get(Integer.valueOf(detailIce.getReceiver()));
	        	detailIce.setReceiverUserName(null != userInfo ? userInfo.getUserName() : "");
	        	// 查询用户所在科室
	        	UserHospCommonIce userHouseInfo = MtCommonServiceUtils.
	        			queryHospUserInfo(String.valueOf(organId), detailIce.getReceiver());
	        	detailIce.setReceiverHouseName(null != userHouseInfo ? userHouseInfo.getHouseName() : "");
			}
	        // 设置紧急程度名称
	        detailIce.setUrgencyName(UrgencyEnum.getNameByCode(detailIce.getUrgency()));
	        detailIce.setUserList(userList);
	        result.setMtDispatchTaskDetailIce(detailIce);
		} catch (Exception e) {
			logger.error("queryMtDispatchTaskDetail", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("查询调度任务失败");
		 }
		return result;
	}

	/**
	 * 
	 * 类描述: 查看固定任务
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年4月20日 下午4:02:46
	 */
	@Override
	public MtFixedTaskDetailIceRsp queryFixedTaskDetailApp(int organId, int taskId,
			Current __current) {
		MtFixedTaskDetailIceRsp result = new MtFixedTaskDetailIceRsp();
		result.code = RpcError.SUCCESS.getCode();
		result.message = RpcError.SUCCESS.getMessage();
		try {
			// 获取一级物业Id
			TOrganInfo topOrgan = CommonServiceUtils.getTopOrganByOrganID(organId);
			Integer groupOrganId = null;
			if (null != topOrgan) {
				groupOrganId = topOrgan.getOrganId();
			} else {
				result.setCode(RpcError.FAIL.getCode());
				result.setMessage("查不到一级物业,查询固定任务详情失败!");
				return result;
			}
			MtTask task = mtTaskCommonService.queryMtTaskByTaskId(groupOrganId, taskId);
			if (null == task) {
				result.setCode(RpcError.FAIL.getCode());
				result.setMessage("固定任务不存在");
				return result;
			}
			MtTaskExt mtTaskExt = this.mtTaskCommonService.queryMtTaskExtInfo(groupOrganId, taskId);
			MtFixedTaskDetailIce detailIce = BeanCopierUtils.copyProperties(task, MtFixedTaskDetailIce.class, true);
	        if (null != mtTaskExt) {
				BeanCopierUtils.copyProperties(mtTaskExt, detailIce, true);
			}
	        // 添加性别
	        detailIce.setPatientGenderName(SexEnum.getNameByCode(detailIce.getPatientGender()));
	        this.queryTaskInfo(detailIce, task);
	        result.setMtFixedTaskDetailIce(detailIce);
		} catch (Exception e) {
			logger.error("queryFixedTaskDetail", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("查询固定任务失败");
		}
		return result;
	}

	/**
	 * 
	 * 类描述: 查看自主任务
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年4月20日 下午4:03:24
	 */
	@Override
	public MtAutonomousTaskDetailIceRsp queryAutonomousTaskDetailApp(int organId, int taskId,
			Current __current) {
		MtAutonomousTaskDetailIceRsp result = new MtAutonomousTaskDetailIceRsp();
		result.code = RpcError.SUCCESS.getCode();
		result.message = RpcError.SUCCESS.getMessage();
		try {
			// 获取一级物业Id
			TOrganInfo topOrgan = CommonServiceUtils.getTopOrganByOrganID(organId);
			Integer groupOrganId = null;
			UserInfo userInfo = null;
			if (null != topOrgan) {
				groupOrganId = topOrgan.getOrganId();
			} else {
				result.setCode(RpcError.FAIL.getCode());
				result.setMessage("查不到一级物业,查询自主任务详情失败!");
				return result;
			}
			MtTask task = mtTaskCommonService.queryMtTaskByTaskId(groupOrganId, taskId);
			if (null == task) {
				result.setCode(RpcError.FAIL.getCode());
				result.setMessage("自主任务不存在");
				return result;
			}
			MtTaskExt mtTaskExt = this.mtTaskCommonService.queryMtTaskExtInfo(groupOrganId, taskId);
			MtAutonomousTaskDetailIce detailIce = BeanCopierUtils.copyProperties(task, MtAutonomousTaskDetailIce.class, true);
	        if (null != mtTaskExt) {
				BeanCopierUtils.copyProperties(mtTaskExt, detailIce, true);
			}
	        // 查询签收人
	        Map<Integer, UserInfo> userInfoMap = getUserInfoMapForDetail(null, null, detailIce.getReceiver(), null);
	        // 处理签收人
	        if (TransStatusEnum.TRANS_COMPLETED.getCode().equals(task.getStatus()) 
	        		&& StringUtils.isNotBlank(detailIce.getReceiver())) {
	        	userInfo = userInfoMap.get(Integer.valueOf(detailIce.getReceiver()));
	        	detailIce.setReceiverUserName(null != userInfo ? userInfo.getUserName() : "");
	        	// 查询用户所在科室
	        	UserHospCommonIce userHouseInfo = MtCommonServiceUtils.
	        			queryHospUserInfo(String.valueOf(organId), detailIce.getReceiver());
	        	detailIce.setReceiverHouseName(null != userHouseInfo ? userHouseInfo.getHouseName() : "");
			}
	        // 添加性别
	        detailIce.setPatientGenderName(SexEnum.getNameByCode(detailIce.getPatientGender()));
	        this.queryTaskInfo(detailIce,  task);
	        // 查询路线
	        MtTaskRoute taskRoute = mtTaskQueryService.queryMtTaskRouteByTaskId(groupOrganId, task.getTaskId());
	        detailIce.setRouteId(taskRoute.getRouteId() == null ? "" : String.valueOf(taskRoute.getRouteId()));
	        detailIce.setFinishContent(taskRoute.getFinishContent() == null ? "" : taskRoute.getFinishContent());
	        result.setMtAutonomousTaskDetailIce(detailIce);
		} catch (Exception e) {
			logger.error("queryAutonomousTaskDetail", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("查询自主任务失败");
		}
		return result;
	}

	/**
	 * 
	 * 类描述: 查询任务基础信息
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年4月21日 下午4:29:34
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 */
	public void queryTaskInfo(MtTaskBaseRsp taskBase, MtTask task) {
		// 查询组织名称
		Organ organ = CommonServiceUtils.getOrganByID(task.getOrganId());
		taskBase.setOrganName(organ == null ? null : organ.getOrganName());
		taskBase.setTransTypeParentCodeName(TransTypeEnum.getNameByCode(task.getTransTypeParentCode()));
		// 查询运输类型名称
		if (null != task.getTransTypeId() && task.getTransTypeId().intValue() >0) {
			TransTypeInfo typeInfo = MtCommonServiceUtils
					.getTransTypeInfoByTransTypeId(task.getTransTypeId());
			taskBase.setTransTypeName(typeInfo != null ? typeInfo.getTransTypeName(): null);
			if (TransTaskTypeEnum.TASK_TYPE_SELF.getCode().equals(task.getTaskType())) {
				// 自主任务
				task.setLimitMinute(typeInfo != null ? Integer.valueOf(typeInfo.getStandardMinite()): null);
			}
		}
		// 查询科室名称
		Map<String, MtLocationInfoIce> houseMap = initTaskDataUtils.getHouseInfoByTask(task);
		if (null != houseMap && houseMap.size() > 0) {
			MtLocationInfoIce fromHouse = houseMap.get(String.valueOf(task.getFromHouseId()));
			if (fromHouse != null ) {
				taskBase.setFromHouseName(StringUtils.isBlank(fromHouse.getLocationName()) ? "" : fromHouse.getLocationName());
				taskBase.setFromHouseLocateType(StringUtils.isBlank(fromHouse.getLocateType()) ? "" : fromHouse.getLocateType());
				taskBase.setFromHouseMac(StringUtils.isBlank(fromHouse.getMac()) ? "" : fromHouse.getMac());
			}
			MtLocationInfoIce toHouse = houseMap.get(String.valueOf(task.getToHouseId()));
			if (toHouse != null) {
				taskBase.setToHouseName(StringUtils.isBlank(toHouse.getLocationName()) ? "" : toHouse.getLocationName());
				taskBase.setToHouseLocateType(StringUtils.isBlank(toHouse.getLocateType()) ? "" : toHouse.getLocateType());
				taskBase.setToHouseMac(StringUtils.isBlank(toHouse.getMac()) ? "" : toHouse.getMac());
			}
			MtLocationInfoIce sourceHouse = houseMap.get(String.valueOf(task.getSourceHouseId()));
			if (toHouse != null) {
				taskBase.setSourceHouseName(StringUtils.isBlank(sourceHouse.getLocationName()) ? "" : sourceHouse.getLocationName());
			}
		}
		// 处理计划开始运送时间格式
		if (task.getBeginTime() != null) {
			taskBase.setBeginTime(DateUtil.formatDateToString(new Date(DataTypeConverUtils
		    			.parseLongToDate(task.getBeginTime()*100).getTime()), DateTimeUtils.FORMAT_YYYY_MM_DD_HH_MM));
		}
		// 计算结束时间
		if (task.getEndTime() != null) {
			taskBase.setEndTime(DataTypeConverUtils.paresNumberToString(task.getEndTime(), 
					DateUtil.FORMAT_YYYY_MM_DD_HH_MM, MtConstant.FORMAT_YYYY_MM_DD_HH_MM));
		}
		// 运送工具名称
		taskBase.setTransToolsName(task.getTransTools() == null ? null
				: TransToolsEnum.getNameByCode(task.getTransTools()));
		// 查询状态
		String nameByCode = TransStatusEnum.getNameByCode(task.getStatus());
		taskBase.setStatusName(nameByCode);
	}
	
	/**
	 * 
	 * 类描述: 查询基础信息详情信息
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年4月27日 下午2:29:47
	 */
	public void queryTaskDetailInfo(MtTaskDetailRetIce taskDetailRetIce, MtTask task) {
		// 查询组织名称
		Organ organ = CommonServiceUtils.getOrganByID(task.getOrganId());
		taskDetailRetIce.setOrganName(organ == null ? null : organ.getOrganName());
		// 运送状态
		taskDetailRetIce.setStatusName(TransStatusEnum.getNameByCode(task.getStatus()));
		// 处理大类名称
		taskDetailRetIce.setTransTypeParentCodeName(TransTypeEnum.getNameByCode(task.getTransTypeParentCode()));
        // 查询运输类型名称
        if (null != task.getTransTypeId() && task.getTransTypeId().intValue() > 0) {
        	TransTypeInfo typeInfo = MtCommonServiceUtils.getTransTypeInfoByTransTypeId(task.getTransTypeId());
        	taskDetailRetIce.setTransTypeName(typeInfo != null ? typeInfo.getTransTypeName() : null);
        	if (TransTaskTypeEnum.TASK_TYPE_SELF.getCode().equals(task.getTaskType())) {
        		taskDetailRetIce.setLimitMinute(typeInfo != null ? typeInfo.getStandardMinite() : null);
        		task.setLimitMinute(typeInfo != null ? Integer.valueOf(typeInfo.getStandardMinite()) : null);
			}
		}
        // 查询科室名称
        Map<String, MtLocationInfoIce> houseMap = initTaskDataUtils.getHouseInfoByTask(task);
        if (null != houseMap && houseMap.size() > 0) {
        	MtLocationInfoIce fromHouse = houseMap.get(String.valueOf(task.getFromHouseId()));
        	taskDetailRetIce.setFromHouseName(fromHouse == null ? "" : fromHouse.getLocationName());
        	MtLocationInfoIce toHouse = houseMap.get(String.valueOf(task.getToHouseId()));
        	taskDetailRetIce.setToHouseName(toHouse == null ? "" : toHouse.getLocationName());
        	MtLocationInfoIce sourceHouse = houseMap.get(String.valueOf(task.getSourceHouseId()));
        	taskDetailRetIce.setSourceHouseName(sourceHouse == null ? "" : sourceHouse.getLocationName());
		}
        // 处理计划开始运送时间格式
        if (task.getBeginTime() != null) {
        	taskDetailRetIce.setBeginTime(DateUtil.formatDateToString(new Date(DataTypeConverUtils
        			.parseLongToDate(task.getBeginTime()*100).getTime()), DateTimeUtils.FORMAT_YYYY_MM_DD_HH_MM));
		}
        // 计算结束时间
        if (task.getLimitMinute() != null && task.getBeginTime() != null) {
        	taskDetailRetIce.setEndTime(DateTimeUtils.convertDateStr(new Date(DataTypeConverUtils
        			.parseLongToDate(task.getBeginTime()*100).getTime() 
        			+ task.getLimitMinute()*60*1000), DateTimeUtils.FORMAT_YYYY_MM_DD_HH_MM));
		}
    	// 运送工具名称
        taskDetailRetIce.setTransToolsName(task.getTransTools() == null ? 
        		null : TransToolsEnum.getNameByCode(task.getTransTools()));
        // 查询运送紧急程度
        taskDetailRetIce.setUrgencyName(task.getUrgency() == null ?
        		null : UrgencyEnum.getNameByCode(task.getUrgency()));
        // 查询任务类型
        taskDetailRetIce.setTaskTypeName(task.getTaskId() == null ?
        		null : TransTaskTypeEnum.getNameByCode(String.valueOf(task.getTaskType())));
        // 设置相应类型  
        taskDetailRetIce.setResTypeName(task.getResType() == null ?
        		null : TransDispatchTypeEnum.getNameByCode(String.valueOf(task.getResType())));
	}
	
	/**
	 * @discription 固定任务执行信息分页查询
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月2日 上午11:19:08      
	 * @param mtTaskPageIceParam
	 * @param __current
	 * @return     
	 */
	@Override
	public FixedTaskExePaginatorIceRsp queryFixedTaskExeInfoPage(
			MtTaskPageIceParam mtTaskPageIceParam, Current __current) {
		FixedTaskExePaginatorIceRsp rsp = new FixedTaskExePaginatorIceRsp(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new RpcPageIce(), new ArrayList<FixedTaskExeInfoIce>());
		try {
			MtTaskPageDto dto = BeanCopierUtils.copyProperties(mtTaskPageIceParam, MtTaskPageDto.class, true);
			// 获取一级物业Id
			TOrganInfo topOrgan = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
			Integer groupOrganId = null;
			if (null != topOrgan) {
				groupOrganId = topOrgan.getOrganId();
			} else {
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("查不到一级物业,查询固定任务执行信息列表失败!");
				return rsp;
			}
			PageList<MtTaskRoute> taskRouteList = mtTaskQueryService.queryFixedTaskExeInfoPage(groupOrganId, dto);
			if (AppUtils.isNotEmpty(taskRouteList)) {
				//查询科室信息
				Map<String, MtLocationInfoIce> houseInfoMap = getHouseInfoMap(dto, taskRouteList);
				//处理执行信息数据
				List<FixedTaskExeInfoIce> exeInfoList = initExeInfoData(taskRouteList, houseInfoMap);
				rsp.setResultList(exeInfoList);
				rsp.setPaginator(PageUtils.paginator2RpcPageIce(taskRouteList.getPaginator()));
			}
		} catch (Exception e) {
			logger.error("queryFixedTaskExeInfoPage", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}
	
	/**
	 * @discription 处理固定任务执行信息数据
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月2日 上午11:56:41     
	 * @param taskRouteList
	 * @param houseInfoMap
	 * @return
	 */
	private List<FixedTaskExeInfoIce> initExeInfoData(PageList<MtTaskRoute> taskRouteList,
			Map<String, MtLocationInfoIce> houseInfoMap) {
		List<FixedTaskExeInfoIce> exeInfoList = new ArrayList<>();
		FixedTaskExeInfoIce exeInfo = null;
		MtLocationInfoIce positInfo = null;
		int i = 0;
		for (MtTaskRoute mtTaskRoute : taskRouteList) {
			exeInfo = BeanCopierUtils.copyProperties(mtTaskRoute, FixedTaskExeInfoIce.class, true);
			if (Constant.STATUS_CD_NORMAL.equals(mtTaskRoute.getStatus())) {
				//已打卡,设置更新时间为完成时间
				String finishTime = DateUtil.formatDateToString(
						mtTaskRoute.getUpdateDate(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
				exeInfo.setFinishTime(finishTime);
				String beginTime = null;
				if (taskRouteList.get(0) == mtTaskRoute) {
					beginTime = finishTime;
					i = 1;
				} else {
					beginTime = DateUtil.formatDateToString(taskRouteList.get(i-1).getUpdateDate(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
					i++;
				}
				exeInfo.setBeginTime(beginTime);
			}
			positInfo = houseInfoMap.get(String.valueOf(mtTaskRoute.getHouseId()));
			exeInfo.setHouseName(null != positInfo ? positInfo.getLocationName() : "");
			// 定位方式
			exeInfo.setLocateType(null != positInfo ? positInfo.getLocateType() : "");
			exeInfo.setMac(null != positInfo ? positInfo.getMac() : ""); // mac地址
			exeInfo.setIsAutograph(mtTaskRoute.getIsAutograph());
			exeInfoList.add(exeInfo);
		}
		return exeInfoList;
	}

	/**
	 * @discription 查询固定任务路线house信息
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月2日 上午11:57:07     
	 * @param dto
	 * @param taskRouteList
	 * @return
	 */
	private Map<String, MtLocationInfoIce> getHouseInfoMap(MtTaskPageDto dto,
			PageList<MtTaskRoute> taskRouteList) {
		Set<Integer> houseIdSet = AppUtils.list2ParamsSet(taskRouteList, (obj) -> obj.getHouseId());
		//set转list
		List<Integer> houseIdList = new ArrayList<>(houseIdSet);
		List<MtLocationInfoIce> locationInfoList = MtCommonServiceUtils.getLocationInfoList(dto.getOrganId(), houseIdList);
		if (AppUtils.isNotEmpty(locationInfoList)) {
			return AppUtils.list2Map(locationInfoList, (obj) -> obj.getLocationId());
		}
		return new HashMap<>();
	}

	/**
	 * 
	 * 类描述: web运送任务详情
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年4月27日 上午10:46:02
	 */
	@Override
	public MtTaskDetailRetIceRsp queryMtTaskDetail(
			MtTaskDetailIceParam mtTaskDetailIceParam, Current __current) {
		MtTaskDetailRetIceRsp result = new MtTaskDetailRetIceRsp();
		result.code = RpcError.SUCCESS.getCode();
		result.message = RpcError.SUCCESS.getMessage();
		try {
			// 获取一级物业Id
			TOrganInfo topOrgan = CommonServiceUtils.getTopOrganByOrganID(Integer.valueOf(mtTaskDetailIceParam.getOrganId()));
			Integer groupOrganId = null;
			if (null != topOrgan) {
				groupOrganId = topOrgan.getOrganId();
			} else {
				result.setCode(RpcError.FAIL.getCode());
				result.setMessage("查不到一级物业,查询任务详情失败!");
				return result;
			}
			Integer taskId = Integer.valueOf(mtTaskDetailIceParam.getTaskId());
			MtTask task = mtTaskCommonService.queryMtTaskByTaskId(groupOrganId, taskId);
			if (null == task) {
				result.setCode(RpcError.FAIL.getCode());
				result.setMessage("任务不存在");
				return result;
			}
			MtTaskExt mtTaskExt = this.mtTaskCommonService.queryMtTaskExtInfo(groupOrganId, taskId);
			MtTaskDetailRetIce detailIce = BeanCopierUtils.copyProperties(task, MtTaskDetailRetIce.class, true);
	        if (null != mtTaskExt) {
				BeanCopierUtils.copyProperties(mtTaskExt, detailIce, true);
			}
	        // 一级物业名称
	        detailIce.setGroupOrganName(topOrgan.getOrganName());
	        this.queryTaskDetailInfo(detailIce, task);
	        // 添加性别
	        detailIce.setPatientGenderName(SexEnum.getNameByCode(detailIce.getPatientGender()));
	        // 查询创建者
	        List<MtTask> mtTaskList = new ArrayList<MtTask>();
	        mtTaskList.add(task);
	        // 查询运送员
	        List<MtTaskExecutors> exeUserList = this.mtTaskCommonService.queryMtTaskExcutorsByTaskId(groupOrganId, taskId, null);
	        // list排序
	        Collections.sort(exeUserList, (s1,s2) -> 
	        	(StringUtils.isNotBlank(s2.getIsExeEndUser()) ? s2.getIsExeEndUser() : MtConstant.DEFAULT_PERSON_LIABLE).compareTo(
	        		StringUtils.isNotBlank(s1.getIsExeEndUser()) ? s1.getIsExeEndUser() : MtConstant.DEFAULT_PERSON_LIABLE));
	        // 查询下单人/签收人/执行人/派单人信息
	        Map<Integer, UserInfo> userInfoMap = getUserInfoMapForDetail(task.getCreateBy(), 
	        		exeUserList, detailIce.getReceiver(), task.getDispatchUserId());
	        List<User> userList = new ArrayList<User>();
	        User user = null;
	        UserInfo userInfo = null;
	        if (!(TransStatusEnum.TRANS_CANCEL.getCode().equals(task.getStatus()) 
	        		|| TransStatusEnum.TRANS_BACK.getCode().equals(task.getStatus()))) {
	        	// 状态不为取消/退单时,处理执行人信息
	        	for (MtTaskExecutors exeUser : exeUserList) {
		        	user = new User();
		        	user.setUserId(String.valueOf(exeUser.getExeUserId()));
		        	userInfo = userInfoMap.get(exeUser.getExeUserId());
		        	user.setUserName(null != userInfo ? userInfo.getUserName() : "");
		        	user.setUserNo(null != userInfo ? userInfo.getJobNumber() : "");
		        	user.setTel(null != userInfo ? userInfo.getTel() : "");
		        	userList.add(user);
				}
			}
	        // 设置创建者
	        detailIce.setCreateByName(userInfoMap.get(task.getCreateBy()) == null ? 
	        		null : userInfoMap.get(task.getCreateBy()).getUserName());
	        // 处理签收人
	        if (TransStatusEnum.TRANS_COMPLETED.getCode().equals(task.getStatus()) 
	        		&& StringUtils.isNotBlank(detailIce.getReceiver())) {
	        	userInfo = userInfoMap.get(Integer.valueOf(detailIce.getReceiver()));
	        	detailIce.setReceiverUserName(null != userInfo ? userInfo.getUserName() : "");
	        	// 查询用户所在科室
	        	UserHospCommonIce userHouseInfo = MtCommonServiceUtils.
	        			queryHospUserInfo(mtTaskDetailIceParam.getOrganId(), detailIce.getReceiver());
	        	detailIce.setReceiverHouseName(null != userHouseInfo ? userHouseInfo.getHouseName() : "");
			}
	        if (null != task.getTimeConsuming()) {
        		// 计算任务耗时 四舍五入
        		double timeConsuming = Math.round((double) task.getTimeConsuming() / 60);
        		detailIce.setTimeConsuming(String.valueOf((int)timeConsuming));
			}
	        // 处理派单人
	        if (null != task.getDispatchUserId()) {
	        	userInfo = userInfoMap.get(task.getDispatchUserId());
	        	detailIce.setDispatchUserName(null != userInfo ? userInfo.getUserName() : "");
	        	detailIce.setDispatchUserNo(null != userInfo ? userInfo.getJobNumber() : "");
			}
	        // 创建时间格式
	        if (StringUtils.isNotEmpty(detailIce.getCreateDate())) {
	        	String createDate = DataTypeConverUtils.paresLongToString(
	        			Long.valueOf(detailIce.getCreateDate()), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
	        	detailIce.setCreateDate(createDate);
			}
	        detailIce.setUserList(userList);
	        // 查询路线
	        MtTaskRoute taskRoute = mtTaskQueryService.queryMtTaskRouteByTaskId(groupOrganId, task.getTaskId());
	        detailIce.setRouteId(taskRoute.getRouteId() == null ? "" : String.valueOf(taskRoute.getRouteId()));
	        detailIce.setFinishContent(taskRoute.getFinishContent() == null ? "" : taskRoute.getFinishContent());
	        // 查询服务处信息
	        if (TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode().equals(task.getTaskType())
	        		&& TransDispatchTypeEnum.TRANS_DISPATCH_TYPE_02.getCode().equals(task.getResType())) {
	        	// 调度任务，并且响应类型为抢单
	        	List<MtServiceGroupIce> groupInfoList = new ArrayList<>();
		        MtServiceGroupIce group = null;
		        // 通过taskId查询服务处
		        List<MtTaskGroupRel> groupList = mtTaskCommonService.queryServiceByTaskId(groupOrganId, taskId);
		        
		        if (AppUtils.isNotEmpty(groupList)) {
		        	//通过groupIdList 查询服务处信息
		        	List<Integer> groupIdList = AppUtils.list2ParamsList(groupList, (obj) -> obj.getGroupId());
		        	GroupUserBrief[] groups = MtIbatchQueryServiceUtils.queryUserListByGroupIds(AppUtils.listToString(groupIdList, ','));
		        	Set<Integer> groupIdSet = AppUtils.list2ParamsSet(Arrays.asList(groups), (obj) -> obj.getGroupId());
		        	Map<Integer, GroupUserBrief> groupMap = AppUtils.list2Map(Arrays.asList(groups), (obj) -> obj.getGroupId());
		 	        if (groups.length > 0) {
		 	        	for (Integer groupId : new ArrayList<>(groupIdSet)) {
							GroupUserBrief groupUserBrief = groupMap.get(groupId);
							if (null != groupUserBrief) {
								group = BeanCopierUtils.copyProperties(groupUserBrief, MtServiceGroupIce.class, true);
								groupInfoList.add(group);
							}
						}
		 	        	detailIce.setServiceGroupList(groupInfoList);
					}
				}
			}
	        result.setMtTaskDetailRetIce(detailIce);
		} catch (Exception e) {
			logger.error("queryMtDispatchTaskDetail", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("查询调度任务失败");
		 }
		return result;
	}

	/**
	 * 
	 * 类描述: web运送任务详情(固定任务)
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年4月27日 上午10:46:11
	 */
	@Override
	public MtTaskDetailRetIceRsp queryMtTaskFixedDetail(
			MtTaskDetailIceParam mtTaskDetailIceParam, Current __current) {
		MtTaskDetailRetIceRsp result = new MtTaskDetailRetIceRsp();
		result.code = RpcError.SUCCESS.getCode();
		result.message = RpcError.SUCCESS.getMessage();
		try {
			// 获取一级物业Id
			TOrganInfo topOrgan = CommonServiceUtils.getTopOrganByOrganID(Integer.valueOf(mtTaskDetailIceParam.getOrganId()));
			Integer groupOrganId = null;
			if (null != topOrgan) {
				groupOrganId = topOrgan.getOrganId();
			} else {
				result.setCode(RpcError.FAIL.getCode());
				result.setMessage("查不到一级物业,查询固定任务详情失败!");
				return result;
			}
			MtTask task = mtTaskCommonService.queryMtTaskByTaskId(groupOrganId, Integer.valueOf(mtTaskDetailIceParam.getTaskId()));
			if (null == task) {
				result.setCode(RpcError.FAIL.getCode());
				result.setMessage("固定任务不存在");
				return result;
			}
			MtTaskExt mtTaskExt = this.mtTaskCommonService.queryMtTaskExtInfo(groupOrganId, Integer.valueOf(mtTaskDetailIceParam.getTaskId()));
			MtTaskDetailRetIce detailIce = BeanCopierUtils.copyProperties(task, MtTaskDetailRetIce.class, true);
	        if (null != mtTaskExt) {
				BeanCopierUtils.copyProperties(mtTaskExt, detailIce, true);
			}
	        detailIce.setGroupOrganName(topOrgan.getOrganName());
	        this.queryTaskDetailInfo(detailIce, task);
	        // 添加性别
	        detailIce.setPatientGenderName(SexEnum.getNameByCode(detailIce.getPatientGender()));
	        // 查询创建者
	        List<MtTask> mtTaskList = new ArrayList<MtTask>();
	        mtTaskList.add(task);
	        // 查询运送员
	        List<MtTaskExecutors> exeUserList = this.mtTaskCommonService.queryMtTaskExcutorsByTaskId(groupOrganId, task.getTaskId(), null);
	        Map<Integer, UserInfo> userInfoMap = getAllUserInfoMap(mtTaskList, MtTask.class, exeUserList);
	        List<User> userList = new ArrayList<User>();
	        User user = null;
	        UserInfo userInfo = null;
	        for (MtTaskExecutors exeUser : exeUserList) {
	        	user = new User();
	        	user.setUserId(String.valueOf(exeUser.getExeUserId()));
	        	userInfo = userInfoMap.get(exeUser.getExeUserId());
	        	user.setUserName(null != userInfo ? userInfo.getUserName() : "");
	        	user.setUserNo(null != userInfo ? userInfo.getJobNumber() : "");
	        	user.setTel(null != userInfo ? userInfo.getTel() : "");
	        	userList.add(user);
			}
	        //  设置创建者
	        detailIce.setCreateByName(userInfoMap.get(task.getCreateBy()) == null ? 
	        		null : userInfoMap.get(task.getCreateBy()).getUserName());
	        // 创建时间格式
	        if (StringUtils.isNotEmpty(detailIce.getCreateDate())) {
	        	String createDate = DataTypeConverUtils.paresLongToString(
	        			Long.valueOf(detailIce.getCreateDate()), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
	        	detailIce.setCreateDate(createDate);
			}
	        if (null != task.getTimeConsuming()) {
        		// 计算任务耗时 四舍五入
        		double timeConsuming = Math.round((double) task.getTimeConsuming() / 60);
        		detailIce.setTimeConsuming(String.valueOf((int)timeConsuming));
			}
		    // 处理派单人
		    if (null != task.getDispatchUserId()) {
		    	userInfo = userInfoMap.get(task.getDispatchUserId());
		    	detailIce.setDispatchUserName(null != userInfo ? userInfo.getUserName() : "");
		    	detailIce.setDispatchUserNo(null != userInfo ? userInfo.getJobNumber() : "");
			}
		    detailIce.setUserList(userList);
	        result.setMtTaskDetailRetIce(detailIce);
		} catch (Exception e) {
			logger.error("queryMtDispatchTaskDetail", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("查询固定任务失败");
		 }
		return result;
	}
	
	private TaskCount queryAllTaskCount(int groupOrganId, Integer organId, Integer userId, Boolean isRob) {
		TaskCount taskCount = new TaskCount();
		List<Integer> allTaskCount = mtTaskQueryService.queryAllTaskCount(groupOrganId, organId, userId);
		taskCount.setDispatchTaskCount(String.valueOf(allTaskCount.get(0)));
		taskCount.setFixedTaskCount(String.valueOf(allTaskCount.get(1)));
		taskCount.setAutonomousTaskCount(String.valueOf(allTaskCount.get(2)));
		if (isRob) {
			// 查抢单缓存
			MtTaskPageDto dto = new MtTaskPageDto();
			dto.setOrganId(organId);
			dto.setUserId(userId);
			List<MtTask> robTask = mtTaskQueryService.queryDispatchTaskPageByRob(groupOrganId, dto);
			taskCount.setRobTaskCount(String.valueOf(robTask.size()));
		}
		return taskCount;
	}

	/**
	 * @discription 固定任务分页查询(活动中的任务)
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月8日 下午3:16:20      
	 * @param mtTaskPageIceParam
	 * @param __current
	 * @return     
	 */
	@Override
	public MtFixedTaskPaginatorIceRsp queryFixedTaskPage(
			MtTaskPageIceParam mtTaskPageIceParam, Current __current) {
		MtFixedTaskPaginatorIceRsp rsp = new MtFixedTaskPaginatorIceRsp(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new RpcPageIce(mtTaskPageIceParam.getPageLength(), mtTaskPageIceParam.getPageNo(), "0"), 
				new TaskCount(), new ArrayList<MtFixedTaskPageIce>());
		try {
			MtTaskPageDto dto = BeanCopierUtils.copyProperties(mtTaskPageIceParam, MtTaskPageDto.class, true);
			// 获取一级物业Id
			TOrganInfo topOrgan = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
			Integer groupOrganId = null;
			if (null != topOrgan) {
				groupOrganId = topOrgan.getOrganId();
			} else {
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("查不到一级物业,固定任务列表查询失败!");
				return rsp;
			}
			dto.setTaskType(TransTaskTypeEnum.TASK_TYPE_LOOP.getCode());
			List<TaskAppQueryDto> taskPageList = mtTaskQueryService.queryFixedTaskPage(groupOrganId, dto);
			// 查询所有任务条数
			TaskCount taskCount = queryAllTaskCount(groupOrganId, dto.getOrganId(), dto.getUserId(), true);
			if (AppUtils.isNotEmpty(taskPageList)) {
				//1.分页查询是同一个项目下的,返回一样的organName
				Organ organ = CommonServiceUtils.getOrganByID(dto.getOrganId());
				String organName = null != organ ? organ.getOrganName() : "";
				//2.查询科室信息
				Map<String, MtLocationInfoIce> houseInfoMap = initTaskDataUtils.
						getHouseInfoByTaskListForAppAndPad(taskPageList, TaskAppQueryDto.class, dto.getOrganId());
				//3.处理数据
				List<MtFixedTaskPageIce> fixedTaskList = initFixedTaskPageData(taskPageList, organName, houseInfoMap);
				rsp.setResultList(fixedTaskList);
				Paginator page = new Paginator(dto.getPageNo(), dto.getPageLength(), 
						Integer.valueOf(taskCount.getFixedTaskCount()));
				rsp.setPaginator(PageUtils.paginator2RpcPageIce(page));
			}
			rsp.setTaskCount(taskCount);
		} catch (Exception e) {
			logger.error("queryFixedTaskPage", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("查询固定任务分页失败");
		}
		return rsp;
	}
	
	/**
	 * @discription 处理固定任务分页数据
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月8日 下午4:07:23     
	 * @param taskPageList
	 * @param organName
	 * @param houseInfoMap
	 * @param transTypeInfoMap
	 * @return
	 */
	private List<MtFixedTaskPageIce> initFixedTaskPageData(List<TaskAppQueryDto> taskPageList,
			String organName, Map<String, MtLocationInfoIce> houseInfoMap) {
		MtFixedTaskPageIce fixedTask = null;
		MtLocationInfoIce positInfo = null;
		List<MtFixedTaskPageIce> fixedTaskList = new ArrayList<>();
		for (TaskAppQueryDto mtTask : taskPageList) {
			fixedTask = BeanCopierUtils.copyProperties(mtTask, MtFixedTaskPageIce.class, true);
			//组织名称
			fixedTask.setOrganName(organName);
			//任务状态
			fixedTask.setStatusName(TransStatusEnum.getNameByCode(mtTask.getStatus()));
			//出发地
			positInfo = houseInfoMap.get(String.valueOf(mtTask.getFromHouseId()));
			fixedTask.setFromHouseName(null != positInfo ? positInfo.getLocationName() : "");
			//目的地
			positInfo = houseInfoMap.get(String.valueOf(mtTask.getToHouseId()));
			fixedTask.setToHouseName(null != positInfo ? positInfo.getLocationName() : "");
			//运送工具
			fixedTask.setTransToolsName(TransToolsEnum.getNameByCode(mtTask.getTransTools()));
			//运送大类
			fixedTask.setTransTypeParentCodeName(TransTypeEnum.getNameByCode(mtTask.getTransTypeParentCode()));
			//开始结束时间
			fixedTask.setBeginTime(DataTypeConverUtils.paresNumberToString(
					mtTask.getBeginTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM, "MM-dd HH:mm"));
			fixedTask.setEndTime(DataTypeConverUtils.paresNumberToString(
					mtTask.getEndTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM, "HH:mm"));
			if (TransStatusEnum.TRANS_RUNNING.getCode().equals(mtTask.getStatus()) ) {
				long endDate = DateUtil.parseStringToDate(String.valueOf(mtTask.getEndTime() + "00"), 
						DateUtil.FORMAT_YYYYMMDDHHMMSS).getTime();
				// 进行中状态返回剩余时间
				long currentTime = new Date().getTime();
				long remainTime = (endDate - currentTime) / 60 / 1000 ;
				fixedTask.setRemainTime(String.valueOf(remainTime));
			}
			fixedTaskList.add(fixedTask);
		}
		return fixedTaskList;
	}

	/**
	 * 
	 * 类描述: 自主任务分页(app)
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月2日 上午11:26:25
	 */
	@Override
	public MtAutonomousTaskPaginatorIceRsp queryAutonomousTaskPage(
			MtTaskPageIceParam mtTaskPageIceParam, Current __current) {
		MtAutonomousTaskPaginatorIceRsp rsp = new MtAutonomousTaskPaginatorIceRsp(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new RpcPageIce(mtTaskPageIceParam.getPageLength(), mtTaskPageIceParam.getPageNo(), "0"), 
				new TaskCount(), new ArrayList<MtAutonomousTaskPageIce>());
		try {
			MtTaskPageDto dto = BeanCopierUtils.copyProperties(mtTaskPageIceParam, MtTaskPageDto.class, true);
			// 获取一级物业Id
			TOrganInfo topOrgan = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
			Integer groupOrganId = null;
			if (null != topOrgan) {
				groupOrganId = topOrgan.getOrganId();
			} else {
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("查不到一级物业,自主任务列表查询失败!");
				return rsp;
			}
			dto.setTaskType(TransTaskTypeEnum.TASK_TYPE_SELF.getCode());
			List<TaskAppQueryDto> taskPageList = mtTaskQueryService.queryAutonomousTransTaskPage(groupOrganId, dto);
			// 查询所有任务条数
			TaskCount taskCount = queryAllTaskCount(groupOrganId, dto.getOrganId(), dto.getUserId(), true);
			if (AppUtils.isNotEmpty(taskPageList)) {
				//1.分页查询是同一个项目下的,返回一样的organName
				Organ organ = CommonServiceUtils.getOrganByID(dto.getOrganId());
				String organName = null != organ ? organ.getOrganName() : "";
				//2.查询科室信息
				Map<String, MtLocationInfoIce> houseInfoMap = initTaskDataUtils.
						getHouseInfoByTaskListForAppAndPad(taskPageList, TaskAppQueryDto.class, dto.getOrganId());
				//3.查询小类信息
				Map<String, TransTypeInfo> transTypeInfoMap = initTaskDataUtils.getTransTypeInfoMap(taskPageList, TaskAppQueryDto.class);
				//处理返回数据
				List<MtAutonomousTaskPageIce> autonomousTaskList = initAutonomousTaskPageData(taskPageList, organName, houseInfoMap,
						transTypeInfoMap);
				rsp.setResultList(autonomousTaskList);
				Paginator page = new Paginator(dto.getPageNo(), dto.getPageLength(), 
						Integer.valueOf(taskCount.getAutonomousTaskCount()));
				rsp.setPaginator(PageUtils.paginator2RpcPageIce(page));
			}
			rsp.setTaskCount(taskCount);
		} catch (Exception e) {
			logger.error("queryAutonomousTaskPage", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}

	/**
	 * 
	 * 类描述: 自主任务(活动中的任务)分页查询
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月2日 下午3:31:23
	 */
	private List<MtAutonomousTaskPageIce> initAutonomousTaskPageData(
			List<TaskAppQueryDto> taskPageList, String organName,
			Map<String, MtLocationInfoIce> houseInfoMap,
			Map<String, TransTypeInfo> transTypeInfoMap) {
		ArrayList<MtAutonomousTaskPageIce> autonomousTaskList = new ArrayList<MtAutonomousTaskPageIce>();
		MtLocationInfoIce positInfo = null;
		MtAutonomousTaskPageIce autonomousTask = null;
		for (TaskAppQueryDto mtTask : taskPageList) {
			autonomousTask = BeanCopierUtils.copyProperties(mtTask, MtAutonomousTaskPageIce.class, true);
			//组织名称
			autonomousTask.setOrganName(organName);
			//出发地
			positInfo = houseInfoMap.get(String.valueOf(mtTask.getFromHouseId()));
			autonomousTask.setFromHouseName(null != positInfo ? positInfo.getLocationName() : "");
			//目的地
			positInfo = houseInfoMap.get(String.valueOf(mtTask.getToHouseId()));
			autonomousTask.setToHouseName(null != positInfo ? positInfo.getLocationName() : "");
			//运送工具
			autonomousTask.setTransToolsName(TransToolsEnum.getNameByCode(mtTask.getTransTools()));
			//运送大类
			autonomousTask.setTransTypeParentCodeName(TransTypeEnum.getNameByCode(mtTask.getTransTypeParentCode()));
			//运送小类
			TransTypeInfo transTypeInfo = transTypeInfoMap.get(String.valueOf(mtTask.getTransTypeId()));
			autonomousTask.setTransTypeName(null != transTypeInfo ? transTypeInfo.getTransTypeName() : "");
			// 计算剩余时间
			long endDate = DateUtil.parseStringToDate(String.valueOf(mtTask.getEndTime() + "00"), 
					DateUtil.FORMAT_YYYYMMDDHHMMSS).getTime();
			long currentTime = new Date().getTime();
			long remainTime = (endDate - currentTime) / 60 / 1000;
			autonomousTask.setRemainTime(String.valueOf(remainTime));
			autonomousTaskList.add(autonomousTask);
		}
		return autonomousTaskList;
	}

	/**
	 * @discription 抢单或抢单完成的调度任务分页查询(活动中的任务)
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月27日 下午3:16:01      
	 * @param mtTaskPageIceParam
	 * @param __current
	 * @return     
	 */
	@Override
	public MtDispatchTaskPaginatorIceRsp queryDispatchTaskPageByRob(
			MtTaskPageIceParam mtTaskPageIceParam, Current __current) {
		RpcPageIce rspPage = new RpcPageIce();
		MtDispatchTaskPaginatorIceRsp rsp = new MtDispatchTaskPaginatorIceRsp(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				rspPage, new TaskCount(), new ArrayList<MtDispatchTaskPageIce>());
		try {
			MtTaskPageDto dto = BeanCopierUtils.copyProperties(mtTaskPageIceParam, MtTaskPageDto.class, true);
			// 获取一级物业Id
			TOrganInfo topOrgan = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
			Integer groupOrganId = null;
			if (null != topOrgan) {
				groupOrganId = topOrgan.getOrganId();
			} else {
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("查不到一级物业,抢单任务列表查询失败!");
				return rsp;
			}
			Paginator page = new Paginator(dto.getPageNo(), dto.getPageLength(), 0);
			List<MtDispatchTaskPageIce> resultList = new ArrayList<>();
			List<MtTask> taskPageList = mtTaskQueryService.queryDispatchTaskPageByRob(groupOrganId, dto);
			// 查询所有任务条数
			TaskCount taskCount = queryAllTaskCount(groupOrganId, dto.getOrganId(), dto.getUserId(), false);
			taskCount.setRobTaskCount(String.valueOf(taskPageList.size()));
			List<MtTask> toPageList = new ArrayList<>();
			if (AppUtils.isNotEmpty(taskPageList)) {
				//3.查询当前用户抢单完成的执行人信息
				Map<Integer, MtTaskExecutors> exeInfoMap = getCurUserExecutors(groupOrganId, dto, taskPageList);
				//4.分页查询是同一个项目下的,返回一样的organName
				Organ organ = CommonServiceUtils.getOrganByID(dto.getOrganId());
				String organName = null != organ ? organ.getOrganName() : "";
				//5.查询科室信息
				Map<String, MtLocationInfoIce> houseInfoMap = initTaskDataUtils.
						getHouseInfoByTaskListForAppAndPad(taskPageList, MtTask.class, dto.getOrganId());
				//6.查询小类信息
				Map<String, TransTypeInfo> transTypeInfoMap = initTaskDataUtils.getTransTypeInfoMap(taskPageList, MtTask.class);
				//7.处理数据,将执行人为该用户的任务单状态改为抢单(做展示用,数据库不改)
				for (MtTask mtTask : taskPageList) {
					if (!exeInfoMap.isEmpty() && null != exeInfoMap.get(mtTask.getTaskId())) {
						//当前用户抢单完成
						mtTask.setStatus(TransStatusEnum.TRANS_ROB_COMPLETED.getCode());
					}
				}
				//处理当前分页对象
				page = new Paginator(dto.getPageNo(), dto.getPageLength(), taskPageList.size());
				if (page.getPage() == dto.getPageNo().intValue()) {
					//8.list排序和分页
					sortTaskList(taskPageList);			
					toPageList = listToPaginator(taskPageList, page);
				}
				if (AppUtils.isNotEmpty(toPageList)) {
					// 处理数据
					resultList = initPageData(toPageList, resultList, organName,
							houseInfoMap, transTypeInfoMap);
				}
				rsp.setResultList(resultList);
				rspPage.setPageLength(String.valueOf(dto.getPageLength()));
				rspPage.setPageNo(String.valueOf(dto.getPageNo()));
				rspPage.setTotalCount(String.valueOf(resultList.size()));
				rsp.setPaginator(rspPage);
			}
			rsp.setTaskCount(taskCount);
		} catch (Exception e) {
			logger.error("queryDispatchTaskPageByRob", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		} 
		return rsp;
	}
	
	/**
	 * @discription taskList根据状态和预约时间排序
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月22日 上午11:01:18     
	 * @param resultList
	 */
	private void sortTaskList(List<MtTask> resultList) {
		Collections.sort(resultList, new Comparator<MtTask>() {
			@Override
			public int compare(MtTask o1, MtTask o2) {
				if (StringUtils.isNotBlank(o2.getStatus()) && StringUtils.isNotBlank(o1.getStatus())
						&& null != o1.getBeginTime() && o1.getBeginTime().longValue() > 0
						&& null != o2.getBeginTime() && o2.getBeginTime().longValue() > 0) {
					if (o2.getStatus().compareTo(o1.getStatus()) > 0) {
						return 1;
					} else if (o2.getStatus().compareTo(o1.getStatus()) == 0) {
						if (o2.getUrgency().compareTo(o1.getUrgency()) > 0) {
							return 1;
						} else if (o2.getUrgency().compareTo(o1.getUrgency()) == 0) {
							if (o2.getBeginTime().intValue() < o1.getBeginTime().intValue()) {
								return 1;
							} else if (o2.getBeginTime().intValue() == o1.getBeginTime().intValue()) {
								return 0;
							} else {
								return -1;
							}
						} else {
							return -1;
						}
					} else {
						return -1;
					}
				}
				return 0;
			}
		});
	}

	/**
	 * @discription 查询当前用户抢单完成的执行人信息
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月22日 上午11:00:18     
	 * @param dto
	 * @param mtTaskList
	 * @return
	 */
	private Map<Integer, MtTaskExecutors> getCurUserExecutors(
			int groupOrganId, MtTaskPageDto dto, List<MtTask> mtTaskList) {
		List<MtTaskExecutors> exeUserList = mtTaskCommonService.getTaskUserInfoByExeUserId(
				groupOrganId, mtTaskList, dto.getUserId());
		return AppUtils.list2Map(exeUserList, (obj) -> obj.getTaskId());
	}
	
	/**
	 * @discription list分页
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月21日 下午6:04:23     
	 * @param list
	 * @return
	 */
	public <V> List<V> listToPaginator(List<V> list, Paginator paginator){  
		return list.subList(paginator.getStartRow() - 1, paginator.getEndRow());
    }  

	/**
	 * @discription 处理调度任务抢单数据
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月22日 上午10:38:51     
	 * @param mtTaskList
	 * @param resultList
	 * @param exeInfoMap
	 * @param organName
	 * @param houseInfoMap
	 * @param transTypeInfoMap
	 * @return
	 */
	private List<MtDispatchTaskPageIce> initPageData(List<MtTask> mtTaskList,
			List<MtDispatchTaskPageIce> resultList,String organName,
			Map<String, MtLocationInfoIce> houseInfoMap,
			Map<String, TransTypeInfo> transTypeInfoMap) {
		MtDispatchTaskPageIce pageIce = null;
		MtLocationInfoIce locationInfo = null;
		for (MtTask mtTask : mtTaskList) {
			pageIce = BeanCopierUtils.copyProperties(mtTask, MtDispatchTaskPageIce.class, true);
			//组织名称
			pageIce.setOrganName(organName);
			//紧急程度
			pageIce.setUrgencyName(UrgencyEnum.getNameByCode(mtTask.getUrgency()));
			//任务状态
			pageIce.setStatusName(TransStatusEnum.getNameByCode(mtTask.getStatus()));
			//出发地
			locationInfo = houseInfoMap.get(String.valueOf(mtTask.getFromHouseId()));
			pageIce.setFromHouseName(null != locationInfo ? locationInfo.getLocationName() : "");
			//目的地
			locationInfo = houseInfoMap.get(String.valueOf(mtTask.getToHouseId()));
			pageIce.setToHouseName(null != locationInfo ? locationInfo.getLocationName() : "");
			//运送工具
			pageIce.setTransToolsName(TransToolsEnum.getNameByCode(mtTask.getTransTools()));
			//运送大类
			pageIce.setTransTypeParentCodeName(TransTypeEnum.getNameByCode(mtTask.getTransTypeParentCode()));
			//运送小类
			TransTypeInfo transTypeInfo = transTypeInfoMap.get(String.valueOf(mtTask.getTransTypeId()));
			pageIce.setTransTypeName(null != transTypeInfo ? transTypeInfo.getTransTypeName() : "");
			//开始时间
			pageIce.setBeginTime(DataTypeConverUtils.paresLongToString(mtTask.getBeginTime() * 100, MtConstant.FORMAT_YYYY_MM_DD_HH_MM));
			if (null != mtTask.getLimitMinute() && mtTask.getLimitMinute().intValue() != 0) {
				// 预计结束时间
				long limitMinute = (long) mtTask.getLimitMinute() * 60 * 1000;
				pageIce.setEndTime(DateUtil.formatDateToString(new Date(DataTypeConverUtils
		    			.parseLongToDate(mtTask.getBeginTime() * 100).getTime() + limitMinute), 
		    			MtConstant.FORMAT_YYYY_MM_DD_HH_MM));
			}
			resultList.add(pageIce);
		}
		return resultList;
	}

	/**
	 * @discription 处理调度任务分页(活动中的任务)数据
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月27日 下午4:48:45     
	 * @param taskPageList
	 * @param organName
	 * @param houseInfoMap
	 * @param transTypeInfoMap
	 * @return
	 */
	private List<MtDispatchTaskPageIce> initDispatchTaskPageData(PageList<TaskAppQueryDto> taskPageList,
			String organName, Map<String, MtLocationInfoIce> houseInfoMap,
			Map<String, TransTypeInfo> transTypeInfoMap) {
		MtDispatchTaskPageIce dispatchTask = null;
		MtLocationInfoIce positInfo = null;
		List<MtDispatchTaskPageIce> dispatchTaskList = new ArrayList<>();
		for (TaskAppQueryDto mtTask : taskPageList) {
			dispatchTask = BeanCopierUtils.copyProperties(mtTask, MtDispatchTaskPageIce.class, true);
			// 组织名称
			dispatchTask.setOrganName(organName);
			// 紧急程度
			dispatchTask.setUrgencyName(UrgencyEnum.getNameByCode(mtTask.getUrgency()));
			// 任务状态
			dispatchTask.setStatusName(TransStatusEnum.getNameByCode(mtTask.getStatus()));
			// 出发地
			positInfo = houseInfoMap.get(String.valueOf(mtTask.getFromHouseId()));
			dispatchTask.setFromHouseName(null != positInfo ? positInfo.getLocationName() : "");
			// 目的地
			positInfo = houseInfoMap.get(String.valueOf(mtTask.getToHouseId()));
			dispatchTask.setToHouseName(null != positInfo ? positInfo.getLocationName() : "");
			// 运送工具
			dispatchTask.setTransToolsName(TransToolsEnum.getNameByCode(mtTask.getTransTools()));
			// 运送大类
			dispatchTask.setTransTypeParentCodeName(TransTypeEnum.getNameByCode(mtTask.getTransTypeParentCode()));
			// 运送小类
			TransTypeInfo transTypeInfo = transTypeInfoMap.get(String.valueOf(mtTask.getTransTypeId()));
			dispatchTask.setTransTypeName(null != transTypeInfo ? transTypeInfo.getTransTypeName() : "");
			// 预计开始时间
			dispatchTask.setBeginTime(DataTypeConverUtils.paresLongToString(mtTask.getBeginTime() * 100, MtConstant.FORMAT_YYYY_MM_DD_HH_MM));
			// 预计结束时间
			dispatchTask.setEndTime(DataTypeConverUtils.paresLongToString(mtTask.getEndTime() * 100, MtConstant.FORMAT_YYYY_MM_DD_HH_MM));
			if (TransStatusEnum.TRANS_RUNNING.getCode().equals(mtTask.getStatus()) ) {
				long endDate = DateUtil.parseStringToDate(String.valueOf(mtTask.getEndTime() + "00"), 
						DateUtil.FORMAT_YYYYMMDDHHMMSS).getTime();
				// 进行中状态返回剩余时间
				long currentTime = new Date().getTime();
				long remainTime = (endDate - currentTime) / 60 / 1000;
				dispatchTask.setRemainTime(String.valueOf(remainTime));
			}
			dispatchTaskList.add(dispatchTask);
		}
		return dispatchTaskList;
	}
	
	/**
	 * @discription 固定任务某个点执行信息详情
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月9日 下午7:05:57      
	 * @param routeId
	 * @param __current
	 * @return     
	 */
	@Override
	public FixedTaskExeDetailReturnIce queryFixedTaskExeDetail(int routeId,
			int organId, Current __current) {
		FixedTaskExeDetailReturnIce rsp = new FixedTaskExeDetailReturnIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new FixedTaskExeDetailIce());
		try {
			// 查询一级物业
			TOrganInfo topOrgan = CommonServiceUtils.getTopOrganByOrganID(organId);
			Integer groupOrganId = null;
			if (null != topOrgan) {
				groupOrganId = topOrgan.getOrganId();
			} else {
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("查不到一级物业,查询固定任务某个点执行信息详情失败!");
				return rsp;
			}
			MtTaskRoute mtTaskRoute = this.mtTaskQueryService.queryMtTaskRoute(groupOrganId, routeId);
			if (null == mtTaskRoute) {
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("没有该路线信息");
				return rsp;
			}
			FixedTaskExeDetailIce detail = BeanCopierUtils.copyProperties(mtTaskRoute, FixedTaskExeDetailIce.class, true);
			List<FileParams> autographFileList = new ArrayList<>();
			List<FileParams> finishFileList = new ArrayList<>();
			//签过名才会有详情
			CommonFileIce[] autographFile = CommonServiceUtils.getCommonFileByRefType(
					FileRefType.MT_TASK_AUTOGRAPH_FILE, String.valueOf(routeId));
			if (null != autographFile && autographFile.length > 0) {
				List<CommonFileIce> asList = Arrays.asList(autographFile);
				autographFileList = BeanCopierUtils.copyList2List(asList, FileParams.class, true);
				detail.setAutographFileList(autographFileList);
			}
			if (MtConstant.CLOCK_STATUS_1.equals(mtTaskRoute.getStatus())) {
				// 已打卡,查询完成图片
				CommonFileIce[] finishFile = CommonServiceUtils.getCommonFileByRefType(
						FileRefType.MT_TASK_FINISH_FILE, String.valueOf(routeId));
				if (null != finishFile && finishFile.length > 0) {
					List<CommonFileIce> asList = Arrays.asList(finishFile);
					finishFileList = BeanCopierUtils.copyList2List(asList, FileParams.class, true);
					detail.setFinishFileList(finishFileList);
				}
				// 完成时间
				detail.setFinishTime(DateUtil.formatDateToString(mtTaskRoute.getUpdateDate(), MtConstant.FORMAT_YYYY_MM_DD_HH_MM));
			}
			//查询科室名称
			List<Integer> locationIdList = new ArrayList<>();
			locationIdList.add(mtTaskRoute.getHouseId());
			List<MtLocationInfoIce> locationInfoList = MtCommonServiceUtils.getLocationInfoList(organId, locationIdList);
			detail.setHouseName(AppUtils.isNotEmpty(locationInfoList) ? locationInfoList.get(0).getLocationName() : "");
			rsp.setDetail(detail);
		} catch (Exception e) {
			logger.error("queryFixedTaskExeDetail", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}

	/**
	 * @discription 查询跟踪任务分页(pad端)
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月10日 下午2:09:22      
	 * @param mtTaskPageIceParam
	 * @param __current
	 * @return     
	 */
	@Override
	public TaskAndEvaluatePaginatorIceRsp queryMtTaskPageForPad(
			MtTaskPageIceParam mtTaskPageIceParam, Current __current) {
		TaskAndEvaluatePaginatorIceRsp rsp = new TaskAndEvaluatePaginatorIceRsp(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new RpcPageIce(mtTaskPageIceParam.getPageLength(), mtTaskPageIceParam.getPageNo(), "0"), 
				new ArrayList<MtPadCommonPageIce>());
		try {
			MtTaskPageDto dto = BeanCopierUtils.copyProperties(mtTaskPageIceParam, MtTaskPageDto.class, true);
			// 获取一级物业Id
			TOrganInfo topOrgan = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
			Integer groupOrganId = null;
			if (null != topOrgan) {
				groupOrganId = topOrgan.getOrganId();
			} else {
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("查不到一级物业,查询任务列表失败!");
				return rsp;
			}
			PageList<TaskPadQueryDto> taskPageList = mtTaskQueryService.queryMtTaskPageForPad(groupOrganId, dto);
			if (AppUtils.isNotEmpty(taskPageList)) {
				// 1.分页查询是同一个项目下的,返回一样的organName
				Organ organ = CommonServiceUtils.getOrganByID(dto.getOrganId());
				String organName = null != organ ? organ.getOrganName() : "";
				// 2.查询科室信息 
				Map<String, MtLocationInfoIce> houseInfoMap = new HashMap<String, MtLocationInfoIce>();
				if (mtTaskPageIceParam.isExportFlag()) {
					// 导出调用 优化后查询科室的方法
					houseInfoMap = getHouseInfoByTaskList(taskPageList, TaskPadQueryDto.class, dto.getOrganId());
				} else {
					houseInfoMap = initTaskDataUtils.getHouseInfoByTaskList(taskPageList, 
							TaskPadQueryDto.class, dto.getOrganId());
				}
				// 3.查询小类信息
				Map<String, TransTypeInfo> transTypeInfoMap = initTaskDataUtils.getTransTypeInfoMap(taskPageList, TaskPadQueryDto.class);
				// 4.通过taskPageList查询运送员List
//				List<MtTaskExecutors> taskUserInfoList = mtTaskCommonService.getTaskUserInfoList(groupOrganId, taskPageList);
				// 5.获取所有的用户信息map
				// 调用 优化后查询人员的方法
				Map<Integer, UserInfo>allUserInfoMap = getUserInfoMap(taskPageList, TaskPadQueryDto.class);
				// 6.获取任务执行人用户信息map
//				Map<Integer, List<User>> userIceMap = getExeUserInfoMap(taskUserInfoList, allUserInfoMap);
				// 7.获取大类是病人护送的任务Id集合，并查询扩展表
				Map<Integer, MtTaskExt> extInfoMap = null;
				if (InvokingFlagEnum.INVOKING_FROM_PAD.getCode().equals(dto.getInvokingFlag())) {
					// pad端列表查询患者信息
					extInfoMap = queryTransType04ExtInfo(groupOrganId, taskPageList);
				}
				// 8.处理数据
				List<MtPadCommonPageIce> resultList = initMtTaskAndEvaluatePageData(taskPageList, organName,
						houseInfoMap, transTypeInfoMap, dto, allUserInfoMap, extInfoMap);
				rsp.setResultList(resultList);
				rsp.setPaginator(PageUtils.paginator2RpcPageIce(taskPageList.getPaginator()));
				
			}
		} catch (Exception e) {
			logger.error("queryMtTaskPageForPad", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}

	/**
	 * @Title: getUserInfoMap 
	 * @Description: 一次性查询全部人员信息 
	 * @author liuyi@segimail.com 
	 * @date 2018年9月27日下午12:12:57
	 */
	private Map<Integer, UserInfo> getUserInfoMap(PageList<TaskPadQueryDto> taskList,
			Class<TaskPadQueryDto> c) {
		Set<Integer> userIdSet = new HashSet<>();
		if (AppUtils.isNotEmpty(taskList)) {
			Set<Integer> createBySet = AppUtils.list2ParamsSet(taskList, c, "createBy");
			userIdSet.addAll(createBySet);
			Set<Integer> exeEndUserSet = AppUtils.list2ParamsSet(taskList, c, "exeEndUserId");
			userIdSet.addAll(exeEndUserSet);
		}
		return MtTaskHisQueryUtil.getAllUserInfoMap(new ArrayList<Integer>(userIdSet));
	}

	/**
	 * @Title: getHouseInfoByTaskList 
	 * @Description: 查询科室信息 
	 * @author liuyi@segimail.com 
	 * @date 2018年9月26日下午6:06:42
	 */
	private <V>Map<String, MtLocationInfoIce> getHouseInfoByTaskList(
			PageList<V> taskPageList, Class<V> c, Integer organId) {
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
			List<MtLocationInfoIce> locationInfoList = MtCommonServiceUtils.queryLocationInfoListByOrganId(organId, houseIdList);
			if (AppUtils.isNotEmpty(locationInfoList)) {
				return AppUtils.list2Map(locationInfoList, (obj) -> obj.getLocationId());
			}
		}
		return new HashMap<>();
	}

	/**
	 * @discription 查询大类为病人护送04的扩展表信息
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月11日 上午9:33:52     
	 * @param taskPageList
	 */
	private Map<Integer, MtTaskExt> queryTransType04ExtInfo(int groupOrganId, PageList<TaskPadQueryDto> taskPageList) {
		Map<Integer, MtTaskExt> extInfoMap = new HashMap<>();
		List<Integer> taskIdList = new ArrayList<>();
		for (TaskPadQueryDto task : taskPageList) {
			if (TransTypeEnum.TRANS_TYPE_04.getCode().equals(task.getTransTypeParentCode())) {
				taskIdList.add(task.getTaskId());
			}
		}
		if (AppUtils.isNotEmpty(taskIdList)) {
			List<MtTaskExt> extList = mtTaskQueryService.queryTaskExtInfoByTaskIds(groupOrganId, taskIdList);
			return AppUtils.list2Map(extList, (obj) -> obj.getTaskId());
		}
		return extInfoMap;
	}

	/**
	 * @discription 处理分页数据
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月10日 下午4:53:25     
	 * @param taskPageList
	 * @param organName
	 * @param houseInfoMap
	 * @param transTypeInfoMap
	 * @param userIceMap
	 * @return
	 */
	private List<MtPadCommonPageIce> initMtTaskAndEvaluatePageData(PageList<TaskPadQueryDto> taskPageList,
			String organName, Map<String, MtLocationInfoIce> houseInfoMap,
			Map<String, TransTypeInfo> transTypeInfoMap,
			MtTaskPageDto dto, Map<Integer, UserInfo> allUserInfoMap,
			Map<Integer, MtTaskExt> extInfoMap) {
		MtPadCommonPageIce pageIce = null;
		MtLocationInfoIce positInfo = null;
		UserInfo userInfo = null;
		List<User> userList = null;
		User user = null;
		List<MtPadCommonPageIce> resultList = new ArrayList<>();
		for (TaskPadQueryDto mtTask : taskPageList) {
			pageIce = BeanCopierUtils.copyProperties(mtTask, MtPadCommonPageIce.class, true);
			// 组织名称
			pageIce.setOrganName(organName);
			// 任务状态
			pageIce.setStatusName(TransStatusEnum.getNameByCode(mtTask.getStatus()));
			if (TransStatusEnum.TRANS_BACK.getCode().equals(mtTask.getStatus()) 
					|| TransStatusEnum.TRANS_ROBBING.getCode().equals(mtTask.getStatus())) {
				// 退单/抢单中状态在pad端列表展示为未开始
				pageIce.setStatus(TransStatusEnum.TRANS_NOT_START.getCode());
				pageIce.setStatusName(TransStatusEnum.TRANS_NOT_START.getName());
			}
			// 出发地
			positInfo = houseInfoMap.get(String.valueOf(mtTask.getFromHouseId()));
			pageIce.setFromHouseName(null != positInfo ? positInfo.getLocationName() : "");
			// 目的地
			positInfo = houseInfoMap.get(String.valueOf(mtTask.getToHouseId()));
			pageIce.setToHouseName(null != positInfo ? positInfo.getLocationName() : "");
			// 任务来源地
			positInfo = houseInfoMap.get(String.valueOf(mtTask.getSourceHouseId()));
			pageIce.setSourceHouseName(null != positInfo ? positInfo.getLocationName() : "");
			// 运送工具
			pageIce.setTransToolsName(TransToolsEnum.getNameByCode(mtTask.getTransTools()));
			// 运送大类
			pageIce.setTransTypeParentCodeName(TransTypeEnum.getNameByCode(mtTask.getTransTypeParentCode()));
			// 运送小类
			TransTypeInfo transTypeInfo = transTypeInfoMap.get(String.valueOf(mtTask.getTransTypeId()));
			pageIce.setTransTypeName(null != transTypeInfo ? transTypeInfo.getTransTypeName() : "");
			// 执行人
//			userList = userIceMap.get(mtTask.getTaskId());
//			pageIce.setUserList(AppUtils.isNotEmpty(userList) ? userList : new ArrayList<User>());
			// 主责任人
			if (null != mtTask.getExeEndUserId() && mtTask.getExeEndUserId().intValue() > 0) {
				user = new User();
				userList = new ArrayList<>();
				userInfo = allUserInfoMap.get(mtTask.getExeEndUserId());
				user.setUserId(null != userInfo ? String.valueOf(userInfo.getUserId()) : "");
				user.setUserName(null != userInfo ? userInfo.getUserName() : "");
				user.setUserNo(null != userInfo ? userInfo.getJobNumber() : "");
				user.setTel(null != userInfo ? userInfo.getTel() : "");
				userList.add(user);
				pageIce.setUserList(userList);
			}
			if (TransStatusEnum.TRANS_BACK.getCode().equals(mtTask.getStatus()) 
					|| TransStatusEnum.TRANS_CANCEL.getCode().equals(mtTask.getStatus())) {
				pageIce.setUserList(new ArrayList<>());
			}
			// 下单人
			userInfo = allUserInfoMap.get(mtTask.getCreateBy());
			pageIce.setCreateByName(null != userInfo ? userInfo.getUserName() : "");
			// 下单时间
			pageIce.setCreateDate(DateUtil.formatDateToStringYYMMDDHHmmss(mtTask.getCreateDate()));
			// 紧急程度
			pageIce.setUrgencyName(UrgencyEnum.getNameByCode(mtTask.getUrgency()));
			// 处理预约时间
			pageIce.setBeginTime(DataTypeConverUtils.paresLongToString(
					Long.valueOf(pageIce.getBeginTime()) * 100, MtConstant.FORMAT_YYYY_MM_DD_HH_MM));
			// 处理患者姓名和床号
			if (AppUtils.isNotEmpty(extInfoMap)) {
				MtTaskExt mtTaskExt = extInfoMap.get(mtTask.getTaskId());
				pageIce.setPatientName(null != mtTaskExt ? mtTaskExt.getPatientName() : "");
				pageIce.setBedNo(null != mtTaskExt ? mtTaskExt.getBedNo() : "");
			}
			resultList.add(pageIce);
		}
		logger.debug("result:" + resultList);
		return resultList;
	}
   
	/**
	 * @discription 查询已经指定给用户的调度任务分页查询(活动中的任务)
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月14日 上午9:40:40      
	 * @param __current
	 * @return     
	 */
	@Override
	public MtDispatchTaskPaginatorIceRsp queryDispatchTaskPageByAssign(
			MtTaskPageIceParam mtTaskPageIceParam, Current __current) {
		RpcPageIce page = new RpcPageIce(mtTaskPageIceParam.getPageLength(), 
				mtTaskPageIceParam.getPageNo(), "0");
		MtDispatchTaskPaginatorIceRsp rsp = new MtDispatchTaskPaginatorIceRsp(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				page, new TaskCount(), new ArrayList<MtDispatchTaskPageIce>());
		try {
			MtTaskPageDto dto = BeanCopierUtils.copyProperties(mtTaskPageIceParam, MtTaskPageDto.class, true);
			// 获取一级物业Id
			TOrganInfo topOrgan = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
			Integer groupOrganId = null;
			if (null != topOrgan) {
				groupOrganId = topOrgan.getOrganId();
			} else {
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("查不到一级物业,调度任务列表查询失败!");
				return rsp;
			}
			dto.setTaskType(TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode());
			// 验证是否筛选
			boolean filter = false;
			if (StringUtils.isNotBlank(dto.getStatus()) || StringUtils.isNotBlank(dto.getUrgency())
					|| StringUtils.isNotBlank(dto.getTransTypeParentCode())) {
				filter = true;
			}
			PageList<TaskAppQueryDto> taskPageList = mtTaskQueryService.queryDispatchTaskPageByAssign(groupOrganId, dto, filter);
			// 查询所有任务条数
			TaskCount taskCount = queryAllTaskCount(groupOrganId, dto.getOrganId(), dto.getUserId(), true);
			// 调度任务有筛选条件 把调度任务未完成数覆盖掉
			if (filter) {
				taskCount.setDispatchTaskCount(String.valueOf(taskPageList.getPaginator().getTotalCount()));
				page.setTotalCount(String.valueOf(taskPageList.getPaginator().getTotalCount()));
			} else {
				page.setTotalCount(taskCount.getDispatchTaskCount());
			}
			if (AppUtils.isNotEmpty(taskPageList)) {
				//1.分页查询是同一个项目下的,返回一样的organName
				Organ organ = CommonServiceUtils.getOrganByID(dto.getOrganId());
				String organName = null != organ ? organ.getOrganName() : "";
				//2.查询科室信息
				Map<String, MtLocationInfoIce> houseInfoMap = initTaskDataUtils.
						getHouseInfoByTaskListForAppAndPad(taskPageList, TaskAppQueryDto.class, dto.getOrganId());
				//3.查询小类信息
				Map<String, TransTypeInfo> transTypeInfoMap = initTaskDataUtils.getTransTypeInfoMap(taskPageList, TaskAppQueryDto.class);
				//4.处理数据
				List<MtDispatchTaskPageIce> dispatchTaskList = initDispatchTaskPageData(taskPageList, organName, 
						houseInfoMap, transTypeInfoMap);
				rsp.setResultList(dispatchTaskList);
			}
			rsp.setPaginator(page);
			rsp.setTaskCount(taskCount); 
		} catch (Exception e) {
			logger.error("queryDispatchTaskPageByAssign", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp; 
	}

	/**
	 * @discription 查询评价信息分页(Pad端)
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月21日 上午11:07:24      
	 * @param mtTaskPageIceParam
	 * @param __current
	 * @return     
	 */
	@Override
	public TaskAndEvaluatePaginatorIceRsp queryEvaluatePageForPad(
			MtTaskPageIceParam mtTaskPageIceParam, Current __current) {
		TaskAndEvaluatePaginatorIceRsp rsp = new TaskAndEvaluatePaginatorIceRsp(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new RpcPageIce(mtTaskPageIceParam.getPageLength(), mtTaskPageIceParam.getPageNo(), "0"), 
				new ArrayList<MtPadCommonPageIce>());
		try {
			MtTaskPageDto dto = BeanCopierUtils.copyProperties(mtTaskPageIceParam, MtTaskPageDto.class, true);
			// 获取一级物业Id
			TOrganInfo topOrgan = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
			Integer groupOrganId = null;
			if (null != topOrgan) {
				groupOrganId = topOrgan.getOrganId();
			} else {
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("查不到一级物业,查询评价列表失败!");
				return rsp;
			}
			if (null == dto.getUserHouseId() || dto.getUserHouseId().intValue() <= 0) {
				return rsp;
			}
			PageList<TaskPadQueryDto> taskPageList = mtTaskQueryService.queryTaskEvaluatePageForPad(groupOrganId, dto);
			if (AppUtils.isNotEmpty(taskPageList)) {
				// 1.分页查询是同一个项目下的,返回一样的organName
				Organ organ = CommonServiceUtils.getOrganByID(dto.getOrganId());
				String organName = null != organ ? organ.getOrganName() : "";
				// 2.查询科室信息
				Map<String, MtLocationInfoIce> houseInfoMap = new HashMap<String, MtLocationInfoIce>();
				if (mtTaskPageIceParam.isExportFlag()) {
					houseInfoMap = getHouseInfoByTaskList(taskPageList, TaskPadQueryDto.class, dto.getOrganId());
				} else {
					houseInfoMap = initTaskDataUtils.getHouseInfoByTaskList(taskPageList, 
							TaskPadQueryDto.class, dto.getOrganId());
				}
				// 3.查询小类信息
				Map<String, TransTypeInfo> transTypeInfoMap = initTaskDataUtils.getTransTypeInfoMap(taskPageList, TaskPadQueryDto.class);
				// 4.通过taskPageList查询运送员List
//				List<MtTaskExecutors> taskUserInfoList = mtTaskCommonService.getTaskUserInfoList(groupOrganId, taskPageList);
				// 5.获取所有的用户信息map
				// 调用 优化后查询人员的方法
				Map<Integer, UserInfo>allUserInfoMap = getUserInfoMap(taskPageList, TaskPadQueryDto.class);
				// 6.获取任务执行人用户信息map
//				Map<Integer, List<User>> userIceMap = getExeUserInfoMap(taskUserInfoList, allUserInfoMap);
				// 7.获取大类是病人护送的任务Id集合，并查询扩展表
				Map<Integer, MtTaskExt> extInfoMap = queryTransType04ExtInfo(groupOrganId, taskPageList);
				// 8.处理数据
				List<MtPadCommonPageIce> resultList = initMtTaskAndEvaluatePageData(taskPageList, organName,
						houseInfoMap, transTypeInfoMap, dto, allUserInfoMap, extInfoMap);
				rsp.setResultList(resultList);
				rsp.setPaginator(PageUtils.paginator2RpcPageIce(taskPageList.getPaginator()));
			}
		} catch (Exception e) {
			logger.error("queryEvaluatePageForPad", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}

	/**
	 * @discription 查询任务是否超时
	 * @author zhangyang@segimail.com       
	 * @created 2018年10月9日 下午5:03:00      
	 * @param organId
	 * @param taskId
	 * @param __current
	 * @return     
	 */
	@Override
	public TaskIsTimeOutIceParam queryTaskIsTimeOut(int organId, int taskId,
			Current __current) {
		TaskIsTimeOutIceParam result = new TaskIsTimeOutIceParam(RpcError.SUCCESS.getCode(), 
				RpcError.SUCCESS.getMessage(), String.valueOf(taskId), MtConstant.NO_TIME_OUT);
		try {
			// 获取一级物业Id
			TOrganInfo topOrgan = CommonServiceUtils.getTopOrganByOrganID(organId);
			Integer groupOrganId = null;
			if (null != topOrgan) {
				groupOrganId = topOrgan.getOrganId();
			} else {
				result.setCode(RpcError.FAIL.getCode());
				result.setMessage("查不到一级物业,查询自主任务详情失败!");
				return result;
			}
			MtTask task = mtTaskCommonService.queryMtTaskByTaskId(groupOrganId, taskId);
			if (null == task) {
				result.setCode(RpcError.FAIL.getCode());
				result.setMessage("任务不存在");
				return result;
			}
			long endTime = DateUtil.parseStringToDate(String.valueOf(task.getEndTime() + "00"), 
					DateUtil.FORMAT_YYYYMMDDHHMMSS).getTime();
			String isTimeOut = endTime - new Date().getTime() >= 0 ? MtConstant.NO_TIME_OUT : MtConstant.TIME_OUT;
			result.setIsTimeOut(isTimeOut);
		} catch (Exception e) {
			logger.error("queryTaskIsTimeOut", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage(RpcError.FAIL.getMessage());
		}
		return result;
	} 
	
}