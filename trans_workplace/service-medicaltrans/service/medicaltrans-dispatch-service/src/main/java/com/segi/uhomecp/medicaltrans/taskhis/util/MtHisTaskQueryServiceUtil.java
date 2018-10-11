package com.segi.uhomecp.medicaltrans.taskhis.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import segi.datacachesvr.queryInfo.GroupUserBrief;
import segi.datacachesvr.queryInfo.Organ;
import segi.datacachesvr.queryInfo.UserInfo;
import segi.filecommon.CommonFileIce;
import segi.medicaltrans.common.transType.TransTypeInfo;
import segi.medicaltrans.location.common.MtLocationInfoIce;
import segi.medicaltrans.mthistask.query.ExeUser;
import segi.medicaltrans.mthistask.query.FileParams;
import segi.medicaltrans.mthistask.query.FixedTaskExeDetailIce;
import segi.medicaltrans.mthistask.query.FixedTaskHisExeInfoIce;
import segi.medicaltrans.mthistask.query.MtServiceGroupIce;
import segi.medicaltrans.mthistask.query.MtTaskDetailRetIce;
import segi.medicaltrans.mthistask.query.MtTaskPageIce;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.constant.MtConstant.FileRefType;
import com.segi.uhomecp.medicaltrans.dto.ExeUserDto;
import com.segi.uhomecp.medicaltrans.enums.IsTimeOutEnum;
import com.segi.uhomecp.medicaltrans.enums.TransDispatchTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransStatusEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTaskTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransToolsEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.UrgencyEnum;
import com.segi.uhomecp.medicaltrans.taskhis.dto.MtHisTaskPageDto;
import com.segi.uhomecp.medicaltrans.taskhis.service.MtHisTaskQueryService;
import com.segi.uhomecp.medicaltrans.trans.dto.MtTaskPageDto;
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
import com.segi.uhomecp.utils.DateUtil;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.enums.SexEnum;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;

/**
 * @ClassName:  MtHisTaskQueryServiceUtil   
 * @Description:运送记录查询接口业务处理类  
 * @author: zhaoqing
 * @date:   2018年8月7日 上午10:54:33
 */
@Component(value = "mtHisTaskQueryServiceUtil")
public class MtHisTaskQueryServiceUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MtHisTaskQueryServiceUtil.class);
	
	@Autowired
	private MtHisTaskQueryService mtHisTaskQueryService;
	
	@Autowired
	private MtCommonTaskService mtCommonTaskService;
	
	@Autowired
	private InitTaskDataUtils initTaskDataUtils;
	
	@Autowired
	private MtTaskQueryService mtTaskQueryService;
	
	/**
	 * @Title: queryMtHisTaskPage   
	 *  运送记录分页查询 
	 * @author zhaoqing  
	 * @param dto
	 * @return 
	 */
	public ResultInfo queryMtHisTaskPage(MtHisTaskPageDto dto) {
		ResultInfo result = new ResultInfo();
		result.setIsSucc(true);
		try {
			// 初始化分页对象
			initPageInfo(dto);
			long beginTimeTop = System.currentTimeMillis();
			long beginTime = System.currentTimeMillis();
			// 分页查询数据
			PageList<MtHisTaskPageDto> pageList = mtHisTaskQueryService
					.queryMtHisTaskPage(dto.getGroupOrganId(), dto);
			if (!AppUtils.isNotEmpty(pageList)) {
				return result;
			}
			LOGGER.debug("=============>共查询任务表数据[" + pageList.size() + "]条, 耗时[" 
					+ (System.currentTimeMillis() - beginTime) + "]毫秒");
			// 任务ID集合
			List<Integer> taskIdList = AppUtils.list2ParamsList(pageList, MtHisTaskPageDto.class, "taskId");
			String taskIds = null;
			if (AppUtils.isNotEmpty(taskIdList)) {
				taskIds = AppUtils.listToString(taskIdList, CharUtils.toChar(Constant.SPLIT_COMMA));
			}
			// 处理导出数据扩展字段信息
			handleExportExtInfo(dto, taskIdList, pageList);
			// 通过taskIdList查询运送员List
			List<MtTaskExecutors> taskUserInfoList = new ArrayList<>();
			if (StringUtils.isNotEmpty(taskIds)) {
				beginTime = System.currentTimeMillis();
				dto.setTaskIds(taskIds);
				taskUserInfoList = mtHisTaskQueryService.getHisTaskUserInfo(dto.getGroupOrganId(), dto);
				int taskUsrCount = taskUserInfoList == null ? 0 : taskUserInfoList.size();
				LOGGER.debug("=============>共查询执行人表数据[" +  taskUsrCount + "]条, 耗时[" 
				+ (System.currentTimeMillis() - beginTime) + "]毫秒");
			}
			// 查询所属组织
			Organ organ = CommonServiceUtils.getOrganByID(dto.getOrganId());
			// 获取所有的用户信息map
			Map<Integer, UserInfo> allUserInfoMap = getAllUserInfoMap(
					pageList, MtHisTaskPageDto.class, taskUserInfoList);
			// 获取任务执行人用户信息map
			Map<Integer, List<ExeUser>> userIceMap = getExeUserInfoMap(taskUserInfoList, allUserInfoMap);
			// 获取所有的科室信息
			Map<String, MtLocationInfoIce> houseInfoMap = MtTaskHisQueryUtil.getHouseInfoByTaskList(
					pageList, MtHisTaskPageDto.class, dto.getOrganId());
			// 处理运行单分页数据
			List<MtTaskPageIce> resultList = initHisTaskPageData(result,
					pageList, houseInfoMap, userIceMap, organ, allUserInfoMap);
			if (!result.getIsSucc() || !AppUtils.isNotEmpty(resultList)) {
				return result;
			}
			// 设置签收人信息
			setUserHsopInfoList(resultList, pageList, dto);
			result.setObject1(pageList);
			result.setObjList(resultList);
			LOGGER.debug("=============>共查询并处理数据[" +  pageList.size() + "]条, 耗时[" 
					+ (System.currentTimeMillis() - beginTimeTop) + "]毫秒");
		} catch (Exception e) {
			LOGGER.error("queryMtHisTaskPage", e);
			result.setIsSucc(false);
			result.setMessage("运送记录分页查询失败！");
		}
		return result;
	}
	
	/**
	 * @Title: setUserHsopInfoList   
	 *  设置签收人信息 
	 * @author zhaoqing  
	 * @param resultList
	 * @param pageList
	 * @param dto 
	 */
	private void setUserHsopInfoList(List<MtTaskPageIce> resultList, 
			PageList<MtHisTaskPageDto> pageList, MtHisTaskPageDto dto) {
		if (dto.isExportFlag()) {
			// 获取签收人Id集合
			Set<Integer> userIdSet = AppUtils.list2ParamsSet(
					pageList, MtHisTaskPageDto.class, "receiver");
			List<Integer> userIdList = new ArrayList<>(userIdSet);
			// 删除null对象
			userIdList.removeAll(Collections.singleton(null));
			MtTaskHisQueryUtil.handleReceiverInfoList(
					String.valueOf(dto.getOrganId()), userIdList, resultList);
		}	
	}
	
	/**
	 * @Title: handleExportExtInfo   
	 *  处理导出数据扩展字段信息 
	 * @author zhaoqing  
	 * @param dto
	 * @param taskIdList
	 * @param pageList
	 */
	private void handleExportExtInfo(MtHisTaskPageDto dto, 
			List<Integer> taskIdList, PageList<MtHisTaskPageDto> pageList) {
		if (dto.isExportFlag()) {
			long beginTime = System.currentTimeMillis();
			// 根据任务Id查询任务扩展信息
			List<MtTaskExt> taskExtList = mtHisTaskQueryService
					.queryMtHisTaskExtByTaskIds(dto.getGroupOrganId(), taskIdList);
			if (!AppUtils.isNotEmpty(taskExtList)) {
				return;
			}
			// 设置扩展信息到任务分页对象中
			transferTaskExtToTaskPageList(taskExtList, pageList);
			LOGGER.debug("=============>共查询任务扩展表数据[" + taskExtList.size() 
					+ "]条, 耗时[" + (System.currentTimeMillis() - beginTime) + "]毫秒");
		}	
	}
	
	/**
	 * @Title: transferTaskExtToTaskPageList   
	 *  设置扩展信息到任务分页对象中
	 * @author zhaoqing  
	 * @param taskExtList
	 * @param pageList      
	 * void      
	 * @throws
	 */
	private void transferTaskExtToTaskPageList(List<MtTaskExt> taskExtList, 
			PageList<MtHisTaskPageDto> pageList) {
		// list对象转成Map集合
		Map<Integer, MtTaskExt> taskExtMap = AppUtils.list2Map(taskExtList, "taskId", MtTaskExt.class);
		for (MtHisTaskPageDto taskDto : pageList) {
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
	 * @Title: initHisTaskPageData   
	 *  处理运行单分页数据 
	 * @author zhaoqing  
	 * @param taskPageList
	 * @param houseInfoMap
	 * @param userIceMap
	 * @param organ
	 * @param allUserInfoMap
	 * @return 
	 */
	private List<MtTaskPageIce> initHisTaskPageData(ResultInfo result, 
			PageList<MtHisTaskPageDto> taskPageList,
			Map<String, MtLocationInfoIce> houseInfoMap,
			Map<Integer, List<ExeUser>> userIceMap, Organ organ, 
			Map<Integer, UserInfo> allUserInfoMap) {
		MtTaskPageIce returnIce = null;
		List<MtTaskPageIce> resultList = new ArrayList<>();
		List<ExeUser> userList = new ArrayList<>();
		try {
			long beginTime = System.currentTimeMillis();
			for (MtHisTaskPageDto task : taskPageList) {
				returnIce = BeanCopierUtils.copyProperties(task, MtTaskPageIce.class, true);
				// 组织名称
				returnIce.setOrganName(null != organ ? organ.getOrganName() : "");
				// 下单人
				returnIce.setCreateByName(MtTaskHisQueryUtil
						.getUserName(task.getCreateBy(), allUserInfoMap));
				// 派单人
				returnIce.setDispatchUserName(MtTaskHisQueryUtil
						.getUserName(task.getDispatchUserId(), allUserInfoMap));
				// 运送类型名称
				returnIce.setTransTypeParentCodeName(
						TransTypeEnum.getNameByCode(task.getTransTypeParentCode()));
				// 紧急程度名称
				returnIce.setUrgencyName(UrgencyEnum.getNameByCode(task.getUrgency()));
				// 设置位置信息
				MtTaskHisQueryUtil.setPositionInfo(houseInfoMap, returnIce, 
						task.getSourceHouseId(), task.getFromHouseId(), task.getToHouseId());
				// 执行人
				setExeUserInfo(task, returnIce, userList, userIceMap);
				// 响应类型
				returnIce.setResTypeName(TransDispatchTypeEnum.getNameByCode(task.getResType()));
				// 任务类型
				returnIce.setTaskTypeName(TransTaskTypeEnum.getNameByCode(task.getTaskType()));
				// 任务状态
				returnIce.setStatusName(TransStatusEnum.getNameByCode(task.getStatus()));
				// 是否超时
				returnIce.setIsTimeOutName(IsTimeOutEnum.getNameByCode(task.getIsTimeOut()));
				// 设置时间信息
				MtTaskHisQueryUtil.setPageDateTimes(returnIce, 
						task.getCreateDate(), task.getBeginTime(), task.getEndTime());
				// 设置评价信息
				MtTaskHisQueryUtil.setEvaluate(task.getEvaluate(), returnIce);
				resultList.add(returnIce);
			}
			LOGGER.debug("=============>共处理运送单数据[" + taskPageList.size() 
					+ "]条, 耗时[" + (System.currentTimeMillis() - beginTime) + "]毫秒");
		} catch (Exception e) {
			LOGGER.error("initHisTaskPageData", e);
			result.setIsSucc(false);
			result.setMessage("运送记录分页数据处理失败！");
		}
		return resultList;
	}
	
	/**
	 * @Title: setExeUserInfo   
	 *  设置执行人信息
	 * @author zhaoqing  
	 * @param task
	 * @param returnIce
	 * @param userList
	 * @param userIceMap     
	 */
	private void setExeUserInfo(MtHisTaskPageDto task, MtTaskPageIce returnIce, 
			List<ExeUser> userList, Map<Integer, List<ExeUser>> userIceMap) {
		if (!(TransStatusEnum.TRANS_BACK.getCode().equals(task.getStatus())
	    		|| TransStatusEnum.TRANS_CANCEL.getCode().equals(task.getStatus()))) {
			userList = userIceMap.get(task.getTaskId());
			if (AppUtils.isNotEmpty(userList)) {
				returnIce.setUserList(userList);
			}
		}
	}

	/**
	 * @Title: getExeUserInfoMap   
	 *  获取任务执行人用户信息map
	 * @author zhaoqing  
	 * @param taskUserInfoList
	 * @param userInfoMap
	 * @return
	 */
	private Map<Integer, List<ExeUser>> getExeUserInfoMap(
			List<MtTaskExecutors> taskUserInfoList, Map<Integer, UserInfo> userInfoMap) {	
		Map<Integer, List<ExeUser>> map = new HashMap<>();
		if (!AppUtils.isNotEmpty(taskUserInfoList)) {
			return map;
		}
		List<ExeUserDto> userList = new ArrayList<>(); 	
		for (MtTaskExecutors exeUserInfo : taskUserInfoList) {
			// 获取执行人信息
			ExeUserDto userDto = MtTaskHisQueryUtil.initExeUserDto(
					exeUserInfo.getExeUserId(), exeUserInfo.getTaskId(), userInfoMap);
			userList.add(userDto);
		}
		return AppUtils.listGroup2Map(userList, ExeUserDto.class, "taskId", ExeUser.class);
	} 
	
	/**
	 * @Title: getAllUserInfoMap   
	 *  获取所有的用户信息map
	 * @author zhaoqing  
	 * @param userIdSet
	 * @return
	 */
	private <V> Map<Integer, UserInfo> getAllUserInfoMap(List<V> taskList, 
			Class<V> c, List<MtTaskExecutors> exeUserList) {
		Set<Integer> exeUserIdSet = new HashSet<>();
		if (AppUtils.isNotEmpty(exeUserList)) {
			exeUserIdSet = AppUtils.list2ParamsSet(exeUserList, (obj) -> obj.getExeUserId());
		}
		return MtTaskHisQueryUtil.getAllUserInfoMap(taskList, c, exeUserIdSet);
	}
	
	/**
	 * @Title: initPageInfo   
	 *  初始化分页对象 
	 * @author zhaoqing  
	 * @param dto
	 */
	private void initPageInfo(MtHisTaskPageDto dto) {
		int pageNo = Constant.INIT_PAGE_NO + 1;
		if (null != dto.getPageNo() && dto.getPageNo().intValue() != 0) {
			//先对页码初始化,然后进行判断并赋值
			pageNo = dto.getPageNo();
		}
		int pageLength = Constant.DEFAULT_PAGE_LENGTH;
		if (null != dto.getPageLength() && dto.getPageLength().intValue() != 0) { 
			//先对每页记录数初始化,然后进行判断并赋值
			pageLength = dto.getPageLength();
		}
		// 初始偏移量
		dto.setStartIndex((pageNo - 1) * pageLength);
		dto.setPageNo(pageNo);
		dto.setPageLength(pageLength);
	}
	
	/**
	 * @discription 固定任务执行信息分页查询
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月2日 下午12:00:57      
	 * @param dto
	 * @return     
	 */
	public ResultInfo queryMtHisFixedTaskExeInfoPage(MtHisTaskPageDto dto) {
		ResultInfo result = new ResultInfo();
		result.setIsSucc(true);
		try {
			// 初始化分页对象
			initPageInfo(dto);
			// 分页查询执行线路数据
			MtTaskPageDto mtTaskPageDto = new MtTaskPageDto();
			mtTaskPageDto.setPageLength(dto.getPageLength());
			mtTaskPageDto.setPageNo(dto.getPageNo());
			mtTaskPageDto.setTaskId(dto.getTaskId());
			PageList<MtTaskRoute> pageList = mtTaskQueryService
					.queryFixedTaskExeInfoPage(dto.getGroupOrganId(), mtTaskPageDto);
			if (!AppUtils.isNotEmpty(pageList)) {
				return result;
			}
			// 查询科室信息
			Map<String, MtLocationInfoIce> houseInfoMap = getHouseInfoMap(dto, pageList);
			// 处理执行信息数据
			List<FixedTaskHisExeInfoIce> exeInfoList = initExeInfoData(result, pageList, houseInfoMap);
			if (!result.getIsSucc() || !AppUtils.isNotEmpty(exeInfoList)) {
				return result;
			}
			result.setObject1(pageList);
			result.setObjList(exeInfoList);
		} catch (Exception e) {
			LOGGER.error("queryMtHisFixedTaskExeInfoPage", e);
			result.setIsSucc(false);
			result.setMessage("固定任务执行信息分页查询失败");
		}
		return result;
	}
	
	/**
	 * @Title: initExeInfoData   
	 *  处理固定任务执行信息数据 
	 * @author zhaoqing  
	 * @param result
	 * @param taskRouteList
	 * @param houseInfoMap
	 * @return
	 */
	private List<FixedTaskHisExeInfoIce> initExeInfoData(ResultInfo result, 
			PageList<MtTaskRoute> taskRouteList, 
			Map<String, MtLocationInfoIce> houseInfoMap) {
		List<FixedTaskHisExeInfoIce> exeInfoList = new ArrayList<>();
		try {
			for (MtTaskRoute mtTaskRoute : taskRouteList) {
				FixedTaskHisExeInfoIce exeInfo = BeanCopierUtils.copyProperties(
						mtTaskRoute, FixedTaskHisExeInfoIce.class, true);
				if (Constant.STATUS_CD_NORMAL.equals(mtTaskRoute.getStatus())) {
					// 已打卡,设置更新时间为完成时间
					String finishTime = DateUtil.formatDateToString(
							mtTaskRoute.getUpdateDate(), MtConstant.FORMAT_YYYY_MM_DD_HH_MM);
					exeInfo.setFinishTime(finishTime);
				}
				MtLocationInfoIce positInfo = houseInfoMap.get(
						String.valueOf(mtTaskRoute.getHouseId()));
				if (null != positInfo) {
					exeInfo.setHouseName(positInfo.getLocationName());
					// 定位方式
					exeInfo.setLocateType(positInfo.getLocateType());
					// mac地址
					exeInfo.setMac(positInfo.getMac());
				} 
				exeInfo.setIsAutograph(mtTaskRoute.getIsAutograph());
				exeInfoList.add(exeInfo);
			}
		} catch (Exception e) {
			LOGGER.error("initExeInfoData", e);
			result.setIsSucc(false);
			result.setMessage("处理固定任务执行信息数据失败");
		}
		return exeInfoList;
	}

	/**
	 * @Title: getHouseInfoMap   
	 *  查询固定任务路线house信息 
	 * @author zhaoqing  
	 * @param dto
	 * @param taskRouteList
	 * @return
	 */
	private Map<String, MtLocationInfoIce> getHouseInfoMap(
			MtHisTaskPageDto dto, PageList<MtTaskRoute> taskRouteList) {
		// 获取houseId和Set集合
		Set<Integer> houseIdSet = AppUtils.list2ParamsSet(
				taskRouteList, MtTaskRoute.class, "houseId");
		// set转list
		List<Integer> houseIdList = new ArrayList<>(houseIdSet);
		// 获取位置信息
		List<MtLocationInfoIce> locationInfoList = MtCommonServiceUtils
				.getLocationInfoList(dto.getOrganId(), houseIdList);
		if (AppUtils.isNotEmpty(locationInfoList)) {
			return AppUtils.list2Map(locationInfoList, "locationId", MtLocationInfoIce.class);
		}
		return new HashMap<>();
	}
	

	/**
	 * <p>Title: queryMtHisTaskDetail</p>   
	 * <p>Description: 运送任务详情(自主任务/调度任务)</p> 
	 * <p>zhaoqing</p>
	 * @param dto
	 * @return   
	 */
	public ResultInfo queryMtHisTaskDetail(MtHisTaskPageDto dto) {
		ResultInfo result = new ResultInfo();
		result.setIsSucc(true);
		// 查询任务信息
		MtTask task = mtHisTaskQueryService.queryMtTaskHisById(
				dto.getGroupOrganId(), dto.getTaskId());
		if (null == task) {
			result.setIsSucc(false);
			result.setMessage("任务不存在");
			return result;
		}
		// 查询任务扩展
		MtTaskExt mtTaskExt = mtHisTaskQueryService.queryMtTaskExtHis(
				dto.getGroupOrganId(), dto.getTaskId());
		MtTaskDetailRetIce detailIce = BeanCopierUtils.copyProperties(
				task, MtTaskDetailRetIce.class, true);
        if (null != mtTaskExt) {
			BeanCopierUtils.copyProperties(mtTaskExt, detailIce, true);
		}
        try {
			// 处理详情基础信息
			handleTaskDetailInfo(detailIce, task);
			// 处理详情其他信息
			handleTaskDetailExtInfo(detailIce, task, dto);
			// 设置签收人信息
			MtTaskHisQueryUtil.handleReceiverInfo(detailIce);
		} catch (Exception e) {
			result.setIsSucc(false);
			result.setMessage("详情信息处理出错！");
			LOGGER.error("queryMtHisTaskDetail", e);
		}
        result.setObject1(detailIce);
        return result;
	}
	
	/**
	 * @Title: handleTaskDetailExtInfo   
	 *  处理详情其他信息
	 * @author zhaoqing  
	 * @param detailIce
	 * @param task  
	 */
	private void handleTaskDetailExtInfo(MtTaskDetailRetIce detailIce, 
			MtTask task, MtHisTaskPageDto dto) {
		// 添加性别
        detailIce.setPatientGenderName(SexEnum.getNameByCode(detailIce.getPatientGender()));
        dto.setTaskIds(String.valueOf(task.getTaskId()));  
        List<MtTaskExecutors> exeUserList = new ArrayList<>();  
        if (StringUtils.isNotEmpty(dto.getTaskIds())) {
        	// 查询运送员
        	exeUserList = mtHisTaskQueryService.getHisTaskUserInfo(dto.getGroupOrganId(), dto);
        }
        // list排序
        exeUserListSort(exeUserList);
        // 获取用户信息
        Map<Integer, UserInfo> userInfoMap = getAllUserInfoMap(
        		Arrays.asList(task), MtTask.class, exeUserList);
        List<ExeUser> userList = new ArrayList<>();
        // 处理执行人信息
        handleExeUserInfo(exeUserList, task, userInfoMap, userList);
        // 设置执行人信息
        detailIce.setUserList(userList);
        // 设置创建人名称
        detailIce.setCreateByName(MtTaskHisQueryUtil
        		.getUserName(task.getCreateBy(), userInfoMap));	
        // 查询路线
        MtTaskRoute taskRoute = mtHisTaskQueryService
        		.queryTaskRouteHisByTask(dto.getGroupOrganId(), task);
        if (taskRoute.getRouteId() != null) {
        	detailIce.setRouteId(String.valueOf(taskRoute.getRouteId()));
        }     
        if (taskRoute.getFinishContent() != null) {
        	detailIce.setFinishContent(taskRoute.getFinishContent());
        }
        // 设置服务处信息
        detailIce.setServiceGroupList(getGroupInfoList(task));
	}
	
	/**
	 * @Title: handleExeUserInfo   
	 *  处理执行人信息
	 * @author zhaoqing  
	 * @param exeUserList
	 * @param task
	 * @param userInfoMap
	 * @param userList  
	 */
	private void handleExeUserInfo(List<MtTaskExecutors> exeUserList, MtTask task, 
			Map<Integer, UserInfo> userInfoMap, List<ExeUser> userList) {
		if (!AppUtils.isNotEmpty(exeUserList)) {
        	return;
        }
        if (!(TransStatusEnum.TRANS_BACK.getCode().equals(task.getStatus())
        		|| TransStatusEnum.TRANS_CANCEL.getCode().equals(task.getStatus()))) {
        	// 处理执行人信息
        	for (MtTaskExecutors exeUser : exeUserList) {
        		ExeUser user = MtTaskHisQueryUtil.initUserInfo(
        				exeUser.getExeUserId(), userInfoMap);
	        	userList.add(user);
			}
		}
	}
	
	/**
	 * @Title: exeUserListSort   
	 *  执行人信息根据是否是主责任人排序，主责任人在最前面
	 * @author zhaoqing  
	 * @param exeUserList 
	 */
	private void exeUserListSort(List<MtTaskExecutors> exeUserList) {
		Collections.sort(exeUserList, new Comparator<MtTaskExecutors>() {
			@Override
			public int compare(MtTaskExecutors o1, MtTaskExecutors o2) {
				String isExeEndUserO1 = o1.getIsExeEndUser();
				String isExeEndUserO2 = o2.getIsExeEndUser();
				if (StringUtils.isEmpty(isExeEndUserO1)) {
					isExeEndUserO1 = MtConstant.DEFAULT_PERSON_LIABLE;
				}
				if (StringUtils.isEmpty(isExeEndUserO2)) {
					isExeEndUserO2 = MtConstant.DEFAULT_PERSON_LIABLE;
				}
				return isExeEndUserO2.compareTo(isExeEndUserO1);
			}   	
        });
	}
	
	/**
	 * @Title: getGroupInfoList   
	 *  获取服务处信息 
	 * @author zhaoqing  
	 * @param task
	 * @return 
	 */
	private List<MtServiceGroupIce> getGroupInfoList(MtTask task) {
		List<MtServiceGroupIce> groupInfoList = new ArrayList<>();
        if (!TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode().equals(task.getTaskType())) {
        	return groupInfoList;
        }	
		// 通过taskId查询任务服务处关联关系
		List<MtTaskGroupRel> groupList = mtCommonTaskService
				.queryServiceByTaskId(task.getGroupOrganId(), task.getTaskId());
		if (AppUtils.isNotEmpty(groupList)) {
			return groupInfoList;
		}
		// 获取服务处ID集合
		List<Integer> groupIdList = AppUtils.list2ParamsList(
				groupList, MtTaskGroupRel.class, "groupId");
		// 通过groupIdList查询服务处信息
		GroupUserBrief[] groups = MtIbatchQueryServiceUtils
				.queryUserListByGroupIds(AppUtils.listToString(groupIdList, ','));
		// 服务处信息转换成map
		Map<Integer, GroupUserBrief> groupMap = AppUtils.list2Map(
				Arrays.asList(groups), "groupId", GroupUserBrief.class);
		if (!AppUtils.isNotEmpty(groupIdList)) {
			return groupInfoList;
		}
		// 服务组信息去重
		distinctGroupInfo(groupInfoList, groupIdList, groupMap);
		return groupInfoList;
	}
	
	/**
	 * @Title: distinctGroupInfo   
	 *  服务组信息去重 
	 * @author zhaoqing  
	 * @param groupInfoList
	 * @param groupIdList
	 * @param groupMap
	 */
	private void distinctGroupInfo(List<MtServiceGroupIce> groupInfoList, 
			List<Integer> groupIdList, Map<Integer, GroupUserBrief> groupMap) {
		for (Integer groupId : groupIdList) {
			MtServiceGroupIce group = null;
			GroupUserBrief groupUserBrief = groupMap.get(groupId);
			if (null == groupUserBrief) {
				continue;
			}
			group = BeanCopierUtils.copyProperties(
					groupUserBrief, MtServiceGroupIce.class, true);
			if (groupInfoList.contains(group)) {
				// 去重
				continue;
			}
			groupInfoList.add(group);		
		}
	}
	
	/**
	 * @Title: handleTaskDetailInfo   
	 *  处理运送任务详情基础信息
	 * @author zhaoqing  
	 * @param taskDetailRetIce
	 * @param task 
	 */
	private void handleTaskDetailInfo(MtTaskDetailRetIce taskDetailRetIce, MtTask task) {
		if (null == taskDetailRetIce) {
			return;
		}
		// 查询组织名称
		Organ organ = CommonServiceUtils.getOrganByID(task.getOrganId());
		taskDetailRetIce.setOrganName(organ == null ? "" : organ.getOrganName());
		// 运送状态
		taskDetailRetIce.setStatusName(TransStatusEnum.getNameByCode(task.getStatus()));
		// 处理大类名称
		taskDetailRetIce.setTransTypeParentCodeName(
				TransTypeEnum.getNameByCode(task.getTransTypeParentCode()));    
		// 处理任务类型
		handleTaskTypeInfo(taskDetailRetIce, task);
        // 位置信息处理
		MtTaskHisQueryUtil.setDetailPositionInfo(taskDetailRetIce, task.getOrganId(), 
				task.getSourceHouseId(), task.getFromHouseId(), task.getToHouseId());
        // 开始结束时间处理 
		MtTaskHisQueryUtil.setDetailTimes(taskDetailRetIce, 
				task.getCreateDate(), task.getBeginTime(), task.getEndTime());  
    	// 运送工具名称
        taskDetailRetIce.setTransToolsName(TransToolsEnum.getNameByCode(task.getTransTools()));
        // 查询运送紧急程度
        taskDetailRetIce.setUrgencyName(UrgencyEnum.getNameByCode(task.getUrgency()));
        // 查询任务类型
        taskDetailRetIce.setTaskTypeName(TransTaskTypeEnum.getNameByCode(task.getTaskType()));
        // 设置响应类型  
        taskDetailRetIce.setResTypeName(TransDispatchTypeEnum.getNameByCode(task.getResType()));    
	}
	
	/**
	 * @Title: handleTaskTypeInfo   
	 *  处理任务类型信息 
	 * @author zhaoqing  
	 * @param taskDetailRetIce
	 * @param task 
	 */
	private void handleTaskTypeInfo(MtTaskDetailRetIce taskDetailRetIce, MtTask task) {
		if (null != task.getTransTypeId() && task.getTransTypeId().intValue() > 0) {
        	// 查询运输类型
        	TransTypeInfo typeInfo = MtCommonServiceUtils
        			.getTransTypeInfoByTransTypeId(task.getTransTypeId());
        	if (typeInfo != null) {
        		// 查询运输类型名称
        		taskDetailRetIce.setTransTypeName(typeInfo.getTransTypeName());
        		if (TransTaskTypeEnum.TASK_TYPE_SELF.getCode().equals(task.getTaskType())) {
            		// 自主任务设置任务限定时间
        			taskDetailRetIce.setLimitMinute(typeInfo.getStandardMinite());
            		task.setLimitMinute(Integer.valueOf(typeInfo.getStandardMinite()));
    			}
        	}
		}
	}

	/**
	 * <p>Title: queryMtHisTaskFixedDetail</p>   
	 * <p>Description: 运送任务详情(固定任务)</p> 
	 * <p>zhaoqing</p>
	 * @param dto
	 * @return   
	 */
	public ResultInfo queryMtHisTaskFixedDetail(MtHisTaskPageDto dto) {
		ResultInfo result = new ResultInfo();
		result.setIsSucc(true);
		// 查询任务信息
		MtTask task = mtHisTaskQueryService.queryMtTaskHisById(
				dto.getGroupOrganId(), dto.getTaskId());
		if (null == task) {
			result.setIsSucc(false);
			result.setMessage("任务不存在");
			return result;
		}
		// 查询任务扩展信息
		MtTaskExt mtTaskExt = mtHisTaskQueryService.queryMtTaskExtHis(
				dto.getGroupOrganId(), dto.getTaskId());
		MtTaskDetailRetIce detailIce = BeanCopierUtils.copyProperties(
				task, MtTaskDetailRetIce.class, true);
        if (null != mtTaskExt) {
			BeanCopierUtils.copyProperties(mtTaskExt, detailIce, true);
		}
        try {
			// 处理详情基础信息
			handleTaskDetailInfo(detailIce, task);
			// 处理详情其他信息
			handleMtHisTaskFixedDetailExt(detailIce, task, dto);
		} catch (Exception e) {
			result.setIsSucc(false);
			result.setMessage("详情信息处理出错！");
			LOGGER.error("queryMtHisTaskFixedDetail", e);
		}
        result.setObject1(detailIce);
        return result;
	}
	
	/**
	 * @Title: handleMtHisTaskFixedDetailExt   
	 *  处理固定任务详情其他信息
	 * @author zhaoqing  
	 * @param detailIce
	 * @param task 
	 */
	private void handleMtHisTaskFixedDetailExt(MtTaskDetailRetIce detailIce, 
			MtTask task, MtHisTaskPageDto dto) {
		// 添加性别
		detailIce.setPatientGenderName(SexEnum.getNameByCode(detailIce.getPatientGender()));
        dto.setTaskIds(String.valueOf(task.getTaskId()));  
        List<MtTaskExecutors> exeUserList = new ArrayList<>(); 
        if (StringUtils.isNotEmpty(dto.getTaskIds())) {
        	// 查询运送员
        	exeUserList = mtHisTaskQueryService.getHisTaskUserInfo(dto.getGroupOrganId(), dto);
        }
        // 获取用户信息
        Map<Integer, UserInfo> userInfoMap = getAllUserInfoMap(
        		Arrays.asList(task), MtTask.class, exeUserList);
        List<ExeUser> userList = new ArrayList<>();
        // 处理执行人信息
        handleExeUserInfo(exeUserList, userList, userInfoMap);
        // 设置执行人信息
        detailIce.setUserList(userList);
        //  设置创建者
        detailIce.setCreateByName(MtTaskHisQueryUtil
        		.getUserName(task.getCreateBy(), userInfoMap));
	}
	
	/**
	 * @Title: handleExeUserInfo   
	 *  处理执行人信息 
	 * @author zhaoqing  
	 * @param exeUserList
	 * @param userList
	 * @param userInfoMap
	 */
	private void handleExeUserInfo(List<MtTaskExecutors> exeUserList, 
			List<ExeUser> userList, Map<Integer, UserInfo> userInfoMap) {
		if (!AppUtils.isNotEmpty(exeUserList)) {
        	return;
        }
        for (MtTaskExecutors exeUser : exeUserList) {
        	ExeUser user = MtTaskHisQueryUtil.initUserInfo(
        			exeUser.getExeUserId(), userInfoMap);
        	userList.add(user);
		}
	}

	/**
	 * <p>Title: queryMtHisFixedTaskExeDetail</p>   
	 * <p>Description: 固定任务某个点执行信息详情</p> 
	 * <p>zhaoqing</p>
	 * @param routeId
	 * @param organId
	 * @param groupOrganId
	 * @return   
	 */
	public ResultInfo queryMtHisFixedTaskExeDetail(MtHisTaskPageDto dto) {
		ResultInfo result = new ResultInfo();
		result.setIsSucc(true);
		// 查询路线详情
		MtTaskRoute mtTaskRoute = mtHisTaskQueryService
				.queryTaskRouteHisById(dto.getGroupOrganId(), dto.getRouteId());
		if (null == mtTaskRoute) {
			result.setIsSucc(false);
			result.setMessage("没有该路线信息");
			return result;
		}
		FixedTaskExeDetailIce detail = BeanCopierUtils.copyProperties(
				mtTaskRoute, FixedTaskExeDetailIce.class, true);
		// 详情数据处理
		handleMtHisFixTaskExeDetail(result, dto.getOrganId(), mtTaskRoute, detail);
		if (!result.getIsSucc()) {
			return result;
		}
		result.setObject1(detail);
		return result;
	}
	
	/**
	 * @Title: handleMtHisFixTaskExeDetail   
	 *  处理固定任务某个点执行信息详情
	 * @author zhaoqing  
	 * @param result
	 * @param organId
	 * @param mtTaskRoute
	 * @param detail 
	 */
	private void handleMtHisFixTaskExeDetail(ResultInfo result, int organId, 
			MtTaskRoute mtTaskRoute, FixedTaskExeDetailIce detail) {
		try {
			List<FileParams> autographFileList = new ArrayList<>();
			
			// 获取签名信息
			CommonFileIce[] autographFile = CommonServiceUtils.getCommonFileByRefType(
					FileRefType.MT_TASK_AUTOGRAPH_FILE, detail.getRouteId());
			if (null != autographFile && autographFile.length > 0) {
				// 有签名信息则处理数据，设置签名详情
				List<CommonFileIce> asList = Arrays.asList(autographFile);
				autographFileList = BeanCopierUtils.copyList2List(asList, FileParams.class, true);
				detail.setAutographFileList(autographFileList);
			}
			// 处理已打卡的情况
			handleFinishedInfo(mtTaskRoute, detail);
			// 位置Id
			List<Integer> locationIdList = Arrays.asList(mtTaskRoute.getHouseId());
			// 获取位置信息
			List<MtLocationInfoIce> locationInfoList = MtCommonServiceUtils
					.getLocationInfoList(organId, locationIdList);
			if (AppUtils.isNotEmpty(locationInfoList)) {
				// 设置科室名称
				detail.setHouseName(locationInfoList.get(0).getLocationName());
			}
		} catch (Exception e) {
			result.setIsSucc(false);
			result.setMessage("固定任务执行详情信息处理出错！");
			LOGGER.error("handleMtHisFixTaskExeDetail", e);
		}
	}
	
	/**
	 * @Title: handleFinishedInfo   
	 *  处理已打卡的数据 
	 * @author zhaoqing  
	 * @param mtTaskRoute
	 * @param detail
	 */
	private void handleFinishedInfo(MtTaskRoute mtTaskRoute, FixedTaskExeDetailIce detail) {
		List<FileParams> finishFileList = new ArrayList<>();
		if (MtConstant.CLOCK_STATUS_1.equals(mtTaskRoute.getStatus())) {
			// 已打卡,查询完成图片
			CommonFileIce[] finishFile = CommonServiceUtils.getCommonFileByRefType(
					FileRefType.MT_TASK_FINISH_FILE, detail.getRouteId());
			if (null != finishFile && finishFile.length > 0) {
				// 有打卡图片数据，则处理数据，设置打卡信息
				List<CommonFileIce> asList = Arrays.asList(finishFile);
				finishFileList = BeanCopierUtils.copyList2List(asList, FileParams.class, true);
				detail.setFinishFileList(finishFileList);
			}
			// 完成时间
			detail.setFinishTime(DateUtil.formatDateToString(
					mtTaskRoute.getUpdateDate(), MtConstant.FORMAT_YYYY_MM_DD_HH_MM));
		}
	}
	
}
