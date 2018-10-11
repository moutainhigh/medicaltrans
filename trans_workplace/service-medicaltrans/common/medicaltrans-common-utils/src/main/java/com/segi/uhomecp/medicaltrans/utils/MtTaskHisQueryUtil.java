package com.segi.uhomecp.medicaltrans.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import segi.datacachesvr.queryInfo.UserInfo;
import segi.datacachesvr.queryInfo.UserInfoV2;
import segi.medicaltrans.common.userhosp.UserHospCommonIce;
import segi.medicaltrans.location.common.MtLocationInfoIce;
import segi.medicaltrans.mthistask.query.ExeUser;
import segi.medicaltrans.mthistask.query.MtTaskDetailRetIce;
import segi.medicaltrans.mthistask.query.MtTaskPageIce;

import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.dto.ExeUserDto;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;
import com.segi.uhomecp.wh.common.utils.DataTypeConverUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;

/**
 * @ClassName:  MtTaskHisQueryUtil   
 * @Description:运送记录查询公共方法工具类   
 * @author: zhaoqing
 * @date:   2018年9月13日 上午11:36:04
 */
public class MtTaskHisQueryUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MtTaskHisQueryUtil.class);
	
	/**
	 * @Title: getAllUserInfoMap   
	 *  获取所有的用户信息map 
	 * @author zhaoqing  
	 * @param taskList
	 * @param c
	 * @param exeUserList
	 * @return
	 */
	public static <V> Map<Integer, UserInfo> getAllUserInfoMap(
			List<V> taskList, Class<V> c, Set<Integer> exeUserIdSet) {
		List<Integer> userIdList = new ArrayList<>();
		if (AppUtils.isNotEmpty(taskList)) {
			// 下单人Id集合
			Set<Integer> createBySet = AppUtils.list2ParamsSet(taskList, c, "createBy");
			userIdList.addAll(createBySet);
			// 派单人Id集合
			Set<Integer> dispatchUserIdSet = AppUtils.list2ParamsSet(taskList, c, "dispatchUserId");
			userIdList.addAll(dispatchUserIdSet);
		}
		if (AppUtils.isNotEmpty(exeUserIdSet)) {
			userIdList.addAll(exeUserIdSet);
		}
		return getAllUserInfoMap(userIdList);
	}
	
	/**
	 * @Title: getAllUserInfoMapByUserId   
	 *  获取所有的用户信息map
	 * @author zhaoqing  
	 * @param userIdSet
	 * @return
	 */
	public static Map<Integer, UserInfo> getAllUserInfoMap(List<Integer> userIdList) {
		Map<Integer, UserInfo> userInfoMap = new HashMap<>();
		userIdList.removeAll(Collections.singleton(null));
		String userIds = AppUtils.listToString(userIdList, 
				CharUtils.toChar(Constant.SPLIT_COMMA));
		long beginTime = System.currentTimeMillis();
		int count = 0;
		UserInfoV2[] userInfoV2s = MtIbatchQueryServiceUtils.queryUserListByUserIds(userIds);
		if (null != userInfoV2s) {
			count = userInfoV2s.length;
			for (int i = 0; i < count; i++) {
				UserInfo userInfo = BeanCopierUtils.copyProperties(
						userInfoV2s[i], UserInfo.class, true);
				userInfoMap.put(userInfo.getUserId(), userInfo);
			}
		}
		LOGGER.debug("=============>共查询员工信息数据[" + count + "]条, 耗时[" 
				+ (System.currentTimeMillis() - beginTime) + "]毫秒");
		return userInfoMap;
	}
	
	/**
	 * @Title: handleReceiverInfo   
	 *  详情查询处理签收人信息 
	 * @author zhaoqing  
	 * @param detailIce 
	 */
	public static void handleReceiverInfo(MtTaskDetailRetIce detailIce) {
		// 签收人Id
        String receiverIdStr = detailIce.getReceiver();
        if (StringUtils.isNotEmpty(receiverIdStr) && NumberUtils.isDigits(receiverIdStr)) {
        	Integer receiverId = Integer.valueOf(receiverIdStr);
        	// 签收人信息
        	UserInfo userInfo = CommonServiceUtils.getCurrentUserInfoByID(receiverId);
        	// 设置签收人名称
        	detailIce.setReceiverUserName(null == userInfo ? "" : userInfo.getUserName());
        	// 查询签收人科室信息
        	UserHospCommonIce userHospCommonIce = MtCommonServiceUtils
            		.queryHospUserInfo(detailIce.getOrganId(), receiverIdStr);  	
			if (null != userHospCommonIce) {
				detailIce.setReceiverHouseId(userHospCommonIce.getHouseId());
				detailIce.setReceiverHouseName(userHospCommonIce.getHouseName());
			}       	
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
	public static void handleReceiverInfoList(String organId, 
			List<Integer> userIdList, List<MtTaskPageIce> resultList) {
		if (!AppUtils.isNotEmpty(resultList)) {
			return;
		}
		long beginTime = System.currentTimeMillis();
		userIdList.removeAll(Collections.singleton(null));
		// 查询签收人科室信息
		List<UserHospCommonIce> userHospList = MtCommonServiceUtils
				.queryHospUserInfoList(organId, userIdList);
		// List集合转Map
		Map<String, UserHospCommonIce> userHospMap = AppUtils.list2Map(
				userHospList, "userId", UserHospCommonIce.class);
		// 查询用户信息
		Map<Integer, UserInfoV2> userInfoMap = MtIbatchQueryServiceUtils
				.queryUserMapByUserIds(userIdList);
		for (MtTaskPageIce mtTaskPageIce : resultList) {
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
		LOGGER.debug("=============>共设置签收人信息[" + resultList.size() 
				+ "]条, 耗时[" + (System.currentTimeMillis() - beginTime) + "]毫秒");
	}
	
	/**
	 * @Title: getUserName   
	 *  获取用户名称 
	 * @author zhaoqing  
	 * @param userInfoMap
	 * @param userId
	 * @return   
	 */
	public static String getUserName(Integer userId, 
			Map<Integer, UserInfo> userInfoMap) {
		String userName = "";
		UserInfo userInfo = userInfoMap.get(userId);
		if (null != userInfo) {
			userName = userInfo.getUserName();
		}
		return userName;
	}
	
	/**
	 * @Title: initUserInfo   
	 *  初始化执行人信息 
	 * @author zhaoqing  
	 * @param userInfoMap
	 * @param exeUserId
	 * @return 
	 */
	public static ExeUser initUserInfo(Integer exeUserId, 
			Map<Integer, UserInfo> userInfoMap) {
		ExeUser user = new ExeUser();
    	user.setUserId(String.valueOf(exeUserId));
    	UserInfo userInfo = userInfoMap.get(exeUserId);
    	if (null != userInfo) {
    		String userName = userInfo.getUserName();
			String userNo = userInfo.getJobNumber();
			user.setUserName(userName);
			user.setUserNo(userNo);
			user.setUserNameNo(userName + "(" + userNo + ")");
    	}
    	return user;
	}
	
	/**
	 * @Title: initExeUserDto   
	 *  初始化执行人信息 
	 * @author zhaoqing  
	 * @param userId
	 * @param taskId
	 * @param userInfoMap
	 * @return 
	 */
	public static ExeUserDto initExeUserDto(Integer userId, 
			Integer taskId, Map<Integer, UserInfo> userInfoMap) {
		ExeUserDto userDto = new ExeUserDto();
		UserInfo userInfo = userInfoMap.get(userId);
		userDto.setTaskId(taskId);
		userDto.setUserId(String.valueOf(userId));
		if (null != userInfo) {
			String userName = userInfo.getUserName();
			String userNo = userInfo.getJobNumber();
			userDto.setUserName(userName);
			userDto.setUserNo(userNo);
			userDto.setUserNameNo(userName + "(" + userNo + ")");
		}
		return userDto;
	}
	
	/**
	 * @Title: setPageDateTimes   
	 *  设置分页数据的时间信息 
	 * @author zhaoqing  
	 * @param returnIce
	 * @param createDate
	 * @param beginTime
	 * @param endTime 
	 */
	public static void setPageDateTimes(MtTaskPageIce returnIce, 
			Long createDate, Long beginTime, Long endTime) {
		// 下单时间
		String createDateStr = DataTypeConverUtils.paresLongToString(
				createDate, DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
		returnIce.setCreateDate(createDateStr);
		// 预计开始时间
		returnIce.setBeginTime(DataTypeConverUtils.paresNumberToString(beginTime, 
				DateUtil.FORMAT_YYYY_MM_DD_HH_MM, MtConstant.FORMAT_YYYY_MM_DD_HH_MM));	
		// 预计结束时间
		returnIce.setEndTime(DataTypeConverUtils.paresNumberToString(endTime, 
				DateUtil.FORMAT_YYYY_MM_DD_HH_MM, MtConstant.FORMAT_YYYY_MM_DD_HH_MM));
	}
	
	/**
	 * @Title: setDetailTimes   
	 *  设置详情数据的时间信息 
	 * @author zhaoqing  
	 * @param taskDetailRetIce
	 * @param createDate
	 * @param beginTime
	 * @param endTime
	 */
	public static void setDetailTimes(MtTaskDetailRetIce taskDetailRetIce, 
			Long createDate, Long beginTime, Long endTime) {
		// 下单时间
		String createDateStr = DataTypeConverUtils.paresLongToString(
				createDate, DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
		taskDetailRetIce.setCreateDate(createDateStr);
		// 预计开始时间
		taskDetailRetIce.setBeginTime(DataTypeConverUtils.paresNumberToString(beginTime, 
				DateUtil.FORMAT_YYYY_MM_DD_HH_MM, MtConstant.FORMAT_YYYY_MM_DD_HH_MM));
		// 预计结束时间
		taskDetailRetIce.setEndTime(DataTypeConverUtils.paresNumberToString(endTime,
				DateUtil.FORMAT_YYYY_MM_DD_HH_MM, MtConstant.FORMAT_YYYY_MM_DD_HH_MM));
	}
	
	/**
	 * @Title: getHouseInfoByTaskList   
	 *  根据taskList查询所有的科室信息,返回map
	 * @author zhaoqing  
	 * @param taskPageList
	 * @param c
	 * @param organId
	 * @return 
	 */
	public static <V> Map<String, MtLocationInfoIce> getHouseInfoByTaskList(
			List<V> taskPageList, Class<V> c, Integer organId) {
		Set<Integer> houseIdSet = new HashSet<>();
		// 获取各个位置的Id集合
		List<Integer> fromHouseIdSet = AppUtils.list2ParamsList(taskPageList, c, "fromHouseId");
		List<Integer> toHouseIdSet = AppUtils.list2ParamsList(taskPageList, c, "toHouseId");
		List<Integer> sourceHouseIdSet = AppUtils.list2ParamsList(taskPageList, c, "sourceHouseId");
		// 位置Id集合合并
		houseIdSet.addAll(fromHouseIdSet);
		houseIdSet.addAll(toHouseIdSet);
		houseIdSet.addAll(sourceHouseIdSet);
		if (AppUtils.isNotEmpty(houseIdSet)) {
			long beginTime = System.currentTimeMillis();
			//查询house信息,并返回Map
			List<Integer> houseIdList = new ArrayList<Integer>(houseIdSet);
			houseIdList.removeAll(Collections.singleton(null));
//			List<MtLocationInfoIce> locationInfoList = MtCommonServiceUtils
//					.getLocationInfoList(organId, houseIdList);
			List<MtLocationInfoIce> locationInfoList = MtCommonServiceUtils
					.queryLocationInfoListByOrganId(organId, houseIdList);
			if (AppUtils.isNotEmpty(locationInfoList)) {
				LOGGER.debug("=============>查询科室信息[" + locationInfoList.size() 
						+ "]条, 耗时[" + (System.currentTimeMillis() - beginTime) + "]毫秒");
				return AppUtils.list2Map(locationInfoList, (obj) -> obj.getLocationId());
			}
		}
		
		return new HashMap<>();
	}
	
	/**
	 * @Title: setDetailPositionInfo   
	 *  设置详情位置信息 
	 * @author zhaoqing  
	 * @param taskDetailRetIce
	 * @param organId
	 * @param sourceHouseId
	 * @param fromHouseId
	 * @param toHouseId 
	 */
	public static void setDetailPositionInfo(MtTaskDetailRetIce taskDetailRetIce, 
			Integer organId, Integer sourceHouseId, Integer fromHouseId, Integer toHouseId) {
		// 查询科室位置信息
        Map<String, MtLocationInfoIce> houseMap = getHouseInfoByTask(
        		organId, sourceHouseId, fromHouseId, toHouseId);
        /* 位置信息处理 */
        if (null != houseMap && houseMap.size() > 0) {
        	// 出发地
        	MtLocationInfoIce fromHouse = houseMap.get(taskDetailRetIce.getFromHouseId());
        	taskDetailRetIce.setFromHouseName(fromHouse == null ? "" : fromHouse.getLocationName());
        	// 目的地
        	MtLocationInfoIce toHouse = houseMap.get(taskDetailRetIce.getToHouseId());
        	taskDetailRetIce.setToHouseName(toHouse == null ? "" : toHouse.getLocationName());
        	// 任务来源科室
        	MtLocationInfoIce sourceHouse = houseMap.get(taskDetailRetIce.getSourceHouseId());
        	taskDetailRetIce.setSourceHouseName(sourceHouse == null ? "" : sourceHouse.getLocationName());
		} 
	}
	
	/**
	 * @Title: setPositionInfo   
	 *  设置位置信息 
	 * @author zhaoqing  
	 * @param houseInfoMap
	 * @param returnIce
	 * @param sourceHouseId
	 * @param fromHouseId
	 * @param toHouseId 
	 */
	public static void setPositionInfo(Map<String, MtLocationInfoIce> houseInfoMap, 
			MtTaskPageIce returnIce, Integer sourceHouseId, Integer fromHouseId, Integer toHouseId) {
		MtLocationInfoIce positInfo = null;
		// 出发地
		positInfo = houseInfoMap.get(String.valueOf(fromHouseId));
		returnIce.setFromHouseName(null != positInfo ? positInfo.getLocationName() : "");
		// 目的地
		positInfo = houseInfoMap.get(String.valueOf(toHouseId));
		returnIce.setToHouseName(null != positInfo ? positInfo.getLocationName() : "");
		// 任务来源
		positInfo = houseInfoMap.get(String.valueOf(sourceHouseId));
		returnIce.setSourceHouseName(null != positInfo ? positInfo.getLocationName() : "");
	}
	
	/**
	 * @Title: getHouseInfoByTask   
	 *  根据组织机构Id和位置Id获取位置信息 
	 * @author zhaoqing  
	 * @param organId
	 * @param sourceHouseId
	 * @param fromHouseId
	 * @param toHouseId
	 * @return 
	 */
	public static Map<String, MtLocationInfoIce> getHouseInfoByTask(
			Integer organId, Integer sourceHouseId, Integer fromHouseId, Integer toHouseId) {
		Set<Integer> houseIdSet = new HashSet<>();
		houseIdSet.add(null != sourceHouseId ? sourceHouseId : 1);
		houseIdSet.add(null != fromHouseId ? fromHouseId : 1);
		houseIdSet.add(null != toHouseId ? toHouseId : 1);
		List<Integer> houseIdList = new ArrayList<Integer>(houseIdSet);
		houseIdList.removeAll(Collections.singleton(null));
		List<MtLocationInfoIce> locationInfoList = MtCommonServiceUtils
				.getLocationInfoList(organId, houseIdList);
		if (AppUtils.isNotEmpty(locationInfoList)) {
			return AppUtils.list2Map(locationInfoList, (obj) -> obj.getLocationId());
		}
		return new HashMap<>();
	}
	
	/**
	 * @Title: setEvaluate   
	 *  设置评价值信息 
	 * @author zhaoqing  
	 * @param evaluate
	 * @param returnIce 
	 */
	public static void setEvaluate(Short evaluate, MtTaskPageIce returnIce) {
		if (evaluate == null || MtConstant.EVALUATE_DEFAULT_VALUE == evaluate) {
			// 评价值为默认值时，评价结论设置为空
			returnIce.setEvaluate("");
		} else {
			returnIce.setEvaluate(evaluate + MtConstant.EVALUATE_NOTE);
		}
	}
}
