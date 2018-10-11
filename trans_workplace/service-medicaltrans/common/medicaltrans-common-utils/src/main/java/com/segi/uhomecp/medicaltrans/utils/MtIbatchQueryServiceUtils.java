package com.segi.uhomecp.medicaltrans.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import segi.datacachesvr.queryInfo.CCommunityIPrx;
import segi.datacachesvr.queryInfo.COrganEmployeeIPrx;
import segi.datacachesvr.queryInfo.COrginfoIPrx;
import segi.datacachesvr.queryInfo.GroupUserBrief;
import segi.datacachesvr.queryInfo.Organ;
import segi.datacachesvr.queryInfo.UserInfoV2;
import segi.datacachesvr.queryInfo.retGroupUserList;
import segi.datacachesvr.queryInfo.retOrganInfoList;
import segi.datacachesvr.queryInfo.retUserInfoV2List;
import IceExt.IceClientUtil;

import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.AppUtils;

/**
 * @ClassName:  MtIbatchQueryServiceUtils   
 * @Description:批量高速查询工具类   
 * @author: zhaoqing
 * @date:   2018年5月22日 下午5:57:48
 */
public class MtIbatchQueryServiceUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(MtIbatchQueryServiceUtils.class);
	
	/**
	 * 组织机构路由用途的默认值
	 */
	public static final int NORGANID = 0;
	
	/**
	 * 请求渠道（1 物业服务；2 商业服务）物业服务类型
	 */
	public static final int CHANNEL = 1;
	
	/**
	 * @Title: getCCommunityIPrx   
	 *  社区管理公共ICE服务 
	 * @author zhaoqing  
	 * @return  
	 */
	private static CCommunityIPrx getCCommunityIPrx() {
		return IceClientUtil.getServicePrxByClass(CCommunityIPrx.class);
	}
	
	/**
	 * @Title: getCOrganEmployeeIPrx   
	 *  员工信息公共ICE服务
	 * @author zhaoqing  
	 * @return 
	 */
	private static COrganEmployeeIPrx getCOrganEmployeeIPrx() {
		return IceClientUtil.getServicePrxByClass(COrganEmployeeIPrx.class);
	}
	
	/**
	 * @Title: getCOrginfoIPrx   
	 *  组织机构信息公共ICE服务
	 * @author zhaoqing  
	 * @return 
	 */
	private static COrginfoIPrx getCOrginfoIPrx() {
		return IceClientUtil.getServicePrxByClass(COrginfoIPrx.class);
	}
	
	/**
	 * @Title: queryUserListByGroupIds   
	 *  多个服务组id查询用户信息列表 
	 * @author zhaoqing  
	 * @param groupIds 服务组Id，格式如“66,67”
	 * @param nOrganID 为登录用户所属的组织机构，db路由用，不作查询条件，暂时无值可以传0
	 * @param channel 请求渠道（1 物业服务；2 商业服务，目前统一传1）
	 * @return 
	 */
	public static GroupUserBrief[] queryUserListByGroupIds(String groupIds,int nOrganID,int channel) {
		logger.info("==================>多个服务组id查询用户信息列表: groupIds:{}", groupIds);
		GroupUserBrief[] groupUserBriefs = null;
		retGroupUserList retGroupUserList = getCCommunityIPrx()
				.queryUserListByGroupIds(groupIds, nOrganID, channel);
		if (null != retGroupUserList) {
			groupUserBriefs = retGroupUserList.getData();
		}
		logger.info("==================>多个服务组id查询用户信息列表: resultList:{}", 
				FastjsonUtils.toJsonString(groupUserBriefs));
		return groupUserBriefs;
	}
	
	/**
	 * @Title: queryUserListByGroupIds   
	 *  多个服务组id查询用户信息列表 
	 * @author zhaoqing  
	 * @param groupIds 服务组Id，格式如“66,67”
	 * @return 
	 */
	public static GroupUserBrief[] queryUserListByGroupIds(String groupIds) {
		return queryUserListByGroupIds(groupIds, NORGANID, CHANNEL);
	}
	
	/**
	 * @Title: queryUserListByUserIds   
	 *  多个用户id查询用户信息列表
	 * @author zhaoqing  
	 * @param userIds 用户IDs，格式如“66,67”
	 * @param nOrgId 为登录用户所属的组织机构，db路由用，不作查询条件，暂时无值可以传0
	 * @param channel 请求渠道（1 物业服务；2 商业服务，目前统一传1）
	 * @return 
	 */
	public static UserInfoV2[] queryUserListByUserIds(String userIds, int nOrgId, int channel) {
		logger.info("==================>多个用户id查询用户信息列表: userIds:{}", userIds);
		UserInfoV2[] userInfoV2s = null;
		retUserInfoV2List retUserInfoList = getCOrganEmployeeIPrx()
				.queryUserListByUserIds(userIds, nOrgId, channel);	
		if (null != retUserInfoList) {
			userInfoV2s = retUserInfoList.getData();
		}		
		logger.info("==================>多个用户id查询用户信息列表: resultList:{}", 
				FastjsonUtils.toJsonString(userInfoV2s));
		return userInfoV2s;	
	}
	
	/**
	 * @Title: queryUserListByUserIds   
	 *  多个用户id查询用户信息列表
	 * @author zhaoqing  
	 * @param userIds 用户IDs，格式如“66,67”
	 * @return 
	 */
	public static UserInfoV2[] queryUserListByUserIds(String userIds) {
		return queryUserListByUserIds(userIds, NORGANID, CHANNEL);
	}
	
	/**
	 * @Title: queryUserMapByUserIds   
	 *  多个用户id查询用户信息Map集合 
	 * @author zhaoqing  
	 * @param userIds
	 * @return
	 */
	public static Map<Integer, UserInfoV2> queryUserMapByUserIds(List<Integer> userIdList) {
		Map<Integer, UserInfoV2> userMap = new HashMap<Integer, UserInfoV2>();
		if (!AppUtils.isNotEmpty(userIdList)) {
			return userMap;
		}
		String userIds = AppUtils.listToString(userIdList, 
				CharUtils.toChar(Constant.SPLIT_COMMA));
		if (StringUtils.isEmpty(userIds)) {
			return userMap;
		}
		UserInfoV2[] userInfoArr = queryUserListByUserIds(userIds);
		if (null != userInfoArr) {
			for (UserInfoV2 userInfoV2 : userInfoArr) {
				if (null != userInfoV2) {
					userMap.put(Integer.valueOf(userInfoV2.getUserId()), userInfoV2);
				}
			}
		}
		return userMap;
	}

	/**
	 * @Title: queryOrganListByOrgIds   
	 *  多个组织机构ID查询组织机构信息列表 
	 * @author zhaoqing  
	 * @param orgIds 组织机构IDS，格式如“66,67”
	 * @param nOrganId 为登录用户所属的组织机构，db路由用，不作查询条件，暂时无值可以传0
	 * @param channel 请求渠道（1 物业服务；2 商业服务，目前统一传1）
	 * @return
	 */
	public static Organ[] queryOrganListByOrgIds(String orgIds, int nOrganId, int channel) {
		logger.info("==================>多个组织机构ID查询组织机构信息列表: orgIds:{}", orgIds);	
		Organ[] organs = null;
		retOrganInfoList retOrganInfoList = getCOrginfoIPrx()
				.queryOrganListByOrgIds(orgIds, nOrganId, channel);
		if (null != retOrganInfoList) {
			organs = retOrganInfoList.getData();
		}
		logger.info("==================>多个组织机构ID查询组织机构信息列表: resultList:{}", 
				FastjsonUtils.toJsonString(organs));
		return organs;
	}
	
	/**
	 * @Title: queryOrganListByOrgIds   
	 *  多个组织机构ID查询组织机构信息列表 
	 * @author zhaoqing  
	 * @param orgIds 组织机构IDS，格式如“66,67”
	 * @return
	 */
	public static Organ[] queryOrganListByOrgIds(String orgIds) {
		return queryOrganListByOrgIds(orgIds, NORGANID, CHANNEL);
	}
	
	/**
	 * @Title: queryGroupListByUserIds   
	 *  根据多个员工ID查询员工所属服务组信息集合
	 * @author zhaoqing  
	 * @param userIds
	 * @param nOrganID
	 * @param channel
	 * @return
	 */
	public static GroupUserBrief[] queryGroupIdsByUserList(String userIds, int nOrganID, int channel) {
		logger.info("==================>根据多个员工ID查询员工所属服务组信息列表: userIds:{}", userIds);	
		long[] userIdList = null;
		if (StringUtils.isNotEmpty(userIds)) {
			// 字符串的userIds转换成数组类型
			List<Integer> userIdIntList = AppUtils.str2Integer(StringUtils.replace(userIds, " ", ""));
			if (AppUtils.isNotEmpty(userIdIntList)) {
				userIdList = new long[userIdIntList.size()];
				for (int i=0; i<userIdIntList.size(); i++) {
					userIdList[i] = userIdIntList.get(i);
				}
			}
		}
		GroupUserBrief[] groupUserBriefs = null;
		if (null != userIdList) {
			retGroupUserList retGroupUserList = getCCommunityIPrx()
					.queryGroupIdsByUserList(userIdList, nOrganID, channel);
			
			if (null != retGroupUserList) {
				groupUserBriefs = retGroupUserList.getData();
			}
		}
		logger.info("==================>根据多个员工ID查询员工所属服务组信息列表: resultList:{}", 
				FastjsonUtils.toJsonString(groupUserBriefs));
		return groupUserBriefs;
	}
	
	/**
	 * @Title: queryGroupListByUserIds   
	 *  根据多个员工ID查询员工所属服务组信息集合
	 * @author zhaoqing  
	 * @param userIds
	 * @param nOrganID
	 * @param channel
	 * @return
	 */
	public static GroupUserBrief[] queryGroupIdsByUserList(String userIds) {
		return queryGroupIdsByUserList(userIds, NORGANID, CHANNEL);
	}
	
	/**
	 * @Title: getGroupInfoMap   
	 *  获取服务组信息
	 * @author zhaoqing  
	 * @param userIdList
	 * @return 
	 */
	public static Map<Integer, GroupUserBrief> getGroupInfoMap(List<Integer> userIdList) {
		userIdList.removeAll(Collections.singleton(null));
		String userIds = AppUtils.listToString(userIdList, CharUtils.toChar(Constant.SPLIT_COMMA));
		// 服务组信息
		GroupUserBrief[] groupInfoArr = queryGroupIdsByUserList(userIds);
		Map<Integer, GroupUserBrief> groupMap = new HashMap<Integer, GroupUserBrief>();
		if (groupInfoArr != null) {
			for (GroupUserBrief groupUserBrief : groupInfoArr) {
				Integer userId = Integer.valueOf(groupUserBrief.getUserId());
				if (null != groupUserBrief && !groupMap.containsKey(userId)) {
					groupMap.put(userId, groupUserBrief);
				}
			}
		}
		return groupMap;
	}
}
