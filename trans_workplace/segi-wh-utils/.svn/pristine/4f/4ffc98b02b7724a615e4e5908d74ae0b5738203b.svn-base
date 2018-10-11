package com.segi.uhomecp.wh.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import resp.RpcRespIce;
import segi.common.organ.COrginfoIQueryRpcClient;
import segi.datacachesvr.queryInfo.CCommunityIPrx;
import segi.datacachesvr.queryInfo.COrganEmployeeIPrx;
import segi.datacachesvr.queryInfo.COrginfoIPrx;
import segi.datacachesvr.queryInfo.Organ;
import segi.datacachesvr.queryInfo.TOrganInfo;
import segi.datacachesvr.queryInfo.UserInfo;
import segi.datacachesvr.queryInfo.UserOrganInfo;
import segi.datacachesvr.queryInfo.group;
import segi.filecommon.CommonFileIce;
import segi.filecommon.CommonFileServiceIcePrx;
import segi.whcommon.cache.CacheDictDataServiceIcePrx;
import segi.whcommon.cache.CacheParamIce;
import segi.whcommon.cache.DataDirDetailIce;
import segi.whcommon.cache.DataDirIce;
import segi.whcommon.cache.DataDirIceListResp;
import IceExt.IceClientUtil;

import com.google.common.collect.Lists;
import com.segi.uhomecp.common.contant.CommonContant;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.dto.CommunityInfo;
import com.segi.uhomecp.wh.common.dto.SubOrganInfoDto;
import com.segi.uhomecp.wh.common.interfaces.InvocationHandler;
import com.segi.uhomecp.wh.common.interfaces.impl.OrganInvocationImpl;
import com.segi.uhomecp.wh.common.interfaces.impl.UserInfoInvocationImpl;
/**
 * 通用业务工具类
 * @author kinas
 *
 */
public class CommonServiceUtils {
	
	public final static int NORGANID = 0;
    public final static  int CHANNEL = 1;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonServiceUtils.class);
	/**
	 * 组织机构缓存service
	 * @return
	 */
	private static COrginfoIPrx getCOrginfoIPrx() {
		return IceClientUtil.getServicePrxByClass(COrginfoIPrx.class);
	}
	
	/**
	 * 员工信息缓存service
	 * 
	 */
	private static COrganEmployeeIPrx getCOrganEmployeeIPrx() {
		return IceClientUtil.getServicePrxByClass(COrganEmployeeIPrx.class);
	}
	
	/**
	 * @discription 公共附件sevice
	 * @author wangfan@segimail.com       
	 * @created 2017年10月20日 上午10:19:54     
	 * @return
	 */
	private static CommonFileServiceIcePrx getCommonFileServiceIcePrx() {
		return IceClientUtil.getServicePrxByClass(CommonFileServiceIcePrx.class);
	}
	
	/**
	 * @discription 缓存管理
	 * @author wangxiong@segimail.com       
	 * @created 2017年11月3日 下午3:54:38     
	 * @return
	 */
	private static CacheDictDataServiceIcePrx getCacheDictDataServiceIcePrx() {
		return IceClientUtil.getServicePrxByClass(CacheDictDataServiceIcePrx.class);
	}
	
	/**
	 * @discription 获取服务组信息
	 * @author wangfan@segimail.com       
	 * @created 2017年11月2日 下午9:04:03     
	 * @return
	 */
	private static CCommunityIPrx getCCommunityI() {
		return IceClientUtil.getServicePrxByClass(CCommunityIPrx.class);
	}
	
	/**
	 * @discription 获取工单服务处
	 * @author wangfan@segimail.com       
	 * @created 2017年11月2日 下午9:07:33     
	 * @param userId
	 * @param organId
	 * @return
	 */
	public static group getGroup(int userId, int organId) {
		group[] groups = getCCommunityI().queryGroupListByUserId(userId,
				organId, CommonContant.SERVCIE_ROUTE_DEFAULT_ORGAN,
				CommonContant.SERVICE_CHANNEL_COMPANY);
		if (groups == null || groups.length < 1) {
			return null;
		}
		return groups[0];
	}
	
	/**
	 * 根据组织机构ID查询组织机构信息
	 * @param nOrgId
	 * @return
	 */
	public static Organ getOrganByID(int organId) {
		return getCOrginfoIPrx().queryOrganByID(organId,
				COrginfoIQueryRpcClient.nOrganID,
				COrginfoIQueryRpcClient.channel);
	}
	
	/**
	 * 根据组织机构id，查询一级物业
	 * @param organId
	 * @return
	 */
	public static TOrganInfo getTopOrganByOrganID(int organId) {
		return getCOrginfoIPrx().queryTopOrganByOrganID(organId,
				COrginfoIQueryRpcClient.nOrganID,
				COrginfoIQueryRpcClient.channel);
	}
	
	/**
	 * @Title: getTopOrganByOrganIDs   
	 *  根据组织机构ids，查询一级物业 
	 * @author zhaoqing  
	 * @param organIds
	 * @return
	 */
	public static Map<Integer, TOrganInfo> getTopOrganByOrganIDs(List<Integer> organIds) {
		Map<Integer, TOrganInfo> tOrganInfoMap = new HashMap<>();
		if (!AppUtils.isNotEmpty(organIds)) {
			return tOrganInfoMap;
		}
		for (Integer organId : organIds) {
			if (!tOrganInfoMap.containsKey(organId)) {
				tOrganInfoMap.put(organId, getTopOrganByOrganID(organId));
			}	
		}
		return tOrganInfoMap;
	}
	
	/**
	 * @discription 根据员工ID查询员工信息
	 * @author wangfan@segimail.com       
	 * @created 2017年10月14日 上午11:46:07      
	 * @param nUserID
	 * @param nOrganId
	 * @param channel
	 * @return     
	 * @see com.segi.uhomecp.common.service.LsManagerCommonService#getUserByID(int, int, int)
	 */
	public static UserInfo getUserByID(int nUserID, int nOrganId, int channel) {
		return getCOrganEmployeeIPrx().getUserByID(nUserID, nOrganId, channel);
	}
	
	/**
	 * @discription 根据员工ID查询员工信息
	 * @author wangxiong@segimail.com       
	 * @created 2017-10-16   
	 * @param nUserID
	 * @return     
	 * @see com.segi.uhomecp.common.service.LsManagerCommonService#getUserByID(int, int, int)
	 */
	public static UserInfo getCurrentUserInfoByID(int nUserID) {
		return getCOrganEmployeeIPrx().getUserByID(nUserID, NORGANID, CHANNEL);
	}
	
	/**
	 * @discription 更新公共附件
	 * @author wangfan@segimail.com       
	 * @created 2017年10月20日 上午10:24:11      
	 * @param commonFileIce
	 * @return     
	 * @see com.segi.uhomecp.common.service.LsManagerCommonService#updateCommonFile(segi.filecommon.CommonFileIce)
	 */
	public static RpcRespIce updateRefIdByFileId(CommonFileIce commonFileIce) {
		RpcRespIce rpcRespIce = getCommonFileServiceIcePrx().updateRefIdByFileId(
						Integer.parseInt(commonFileIce.getFileId()),
						commonFileIce.getRefId());
		return rpcRespIce;
	}
	
	/**
	 * @discription 根据fileIds更新公共附件
	 * @author wangfan@segimail.com       
	 * @created 2017年10月20日 上午11:38:18     
	 * @param fileIds
	 * @param refId
	 * @return
	 */
	public static RpcRespIce updateRefIdByFileIds(String fileIds, String refId) {
		RpcRespIce rpcRespIce = getCommonFileServiceIcePrx().updateRefIdByFileIds(fileIds, refId);
		if(!RpcError.SUCCESS.getCode().equals(rpcRespIce.getCode())) {
			throw new RuntimeException("同步业务编码系统异常!");
		}
		return rpcRespIce;
	}
	
	/**
	 * @discription 修改公共附件
	 * @author wangfan@segimail.com       
	 * @created 2017年10月20日 上午10:17:49     
	 * @param commonFileIce
	 * @return
	 */
	public static RpcRespIce updateStatusByFileIds(List<String> fileIds) {
		return getCommonFileServiceIcePrx().updateStatusByFileIds(fileIds);
	}
	
	/**
	 * @discription 删除公共附件
	 * @author wangfan@segimail.com       
	 * @created 2017年10月20日 上午10:42:43     
	 * @param refType
	 * @param refId
	 * @return
	 */
	public static RpcRespIce deleteCommonFile(String refType, String refId) {
		RpcRespIce rpcRespIce = getCommonFileServiceIcePrx().deleteCommonFile(refType, refId);
		return rpcRespIce;
	}

	/**
	 * @discription 删除公共附件
	 * @author wangfan@segimail.com       
	 * @created 2017年10月20日 上午10:42:43     
	 * @param refType
	 * @param refId
	 * @return
	 */
	public static RpcRespIce deleteCommonFileByFileId(int fileId) {
		RpcRespIce rpcRespIce = getCommonFileServiceIcePrx().deleteCommonFileById(fileId);
		return rpcRespIce;
	}
	
	/**
	 * @discription 批量删除公共附件
	 * @author wangfan@segimail.com       
	 * @created 2017年10月20日 上午10:42:43     
	 * @param refType
	 * @param refId
	 * @return
	 */
	public static RpcRespIce deleteCommonFileByFileIds(String fileId) {
		RpcRespIce rpcRespIce = getCommonFileServiceIcePrx().deleteCommonFileByIds(fileId);
		return rpcRespIce;
	}
	
	/**
	 * @discription 获取公共附件
	 * @author wangfan@segimail.com       
	 * @created 2017年11月14日 下午3:54:31     
	 * @param fileId
	 * @return
	 */
	public static CommonFileIce getCommonFile(int fileId) {
		CommonFileIce commonFileIce = getCommonFileServiceIcePrx().getCommonFile(fileId);
		return commonFileIce;
	}

	/**
	 * @discription 根据fileIds 获取文件列表
	 * @author wangxiong@segimail.com       
	 * @created 2018年2月2日 下午10:33:23     
	 * @param fileIds
	 * @return
	 */
	public static CommonFileIce[] getCommonFileByIds(String fileIds) {
		return getCommonFileServiceIcePrx().getCommonFileListByIds(fileIds);
	}
	
	/**
	 * @discription 根据引用类型和引用Id 获取文件列表
	 * @author wangxiong@segimail.com       
	 * @created 2018年2月2日 下午10:33:40     
	 * @param refType
	 * @param refId
	 * @return
	 */
	public static CommonFileIce[] getCommonFileByRefType(String refType, String refId) {
		return getCommonFileServiceIcePrx().getCommonFileList(refType, refId);
	}
	
	/**
	 * @discription 根据dirCode和code 获取字典name
	 * @author wangxiong@segimail.com       
	 * @created 2017年11月2日 下午4:39:12     
	 * @param dirCode
	 * @param code
	 * @return
	 */
	public static String getDetailName(String dirCode,String code) {
		CacheParamIce ice = new CacheParamIce();
		ice.setDirCodes(dirCode);
		ice.setCode(code);
		RpcRespIce rpcResp = getCacheDictDataServiceIcePrx().getDataDirDetailName(ice);
		if(RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
			return rpcResp.getData();
		}
		return null;
	}
	
	/**
	 * @discription 
	 * @author wangxiong@segimail.com       
	 * @created 2017年11月7日 下午8:24:42     
	 * @param dirCode
	 * @return
	 */
	public static List<DataDirIce> getDataDetailList(String dirCode,String status) {
		CacheParamIce ice = new CacheParamIce();
		ice.setDirCodes(dirCode);
		ice.setStatusCd(status);
		DataDirIceListResp resp = getCacheDictDataServiceIcePrx().getDataDirList(ice);
		if(RpcError.SUCCESS.getCode().equals(resp.code)) {
			return resp.getResultList();
		}
		return null;
	}
	
	/**
	 * @Title: getDetailNameMap   
	 *  根据字典表编码获取字典名称map
	 *  key为：dirCode,code(字典表编码和明细表编码，中间用英文逗号分隔)
	 *  value为字典名称
	 * @author zhaoqing  
	 * @param dirCode
	 * @param status
	 * @return  
	 */
	public static Map<String, String> getDetailNameMap(String dirCode) {
		return getDetailNameMap(dirCode, Constant.STATUS_CD_NORMAL);
	}
	
	/**
	 * @Title: getDetailNameMap   
	 *  根据字典表编码获取字典名称map
	 *  key为：dirCode,code(字典表编码和明细表编码，中间用英文逗号分隔)
	 *  value为字典名称
	 * @author zhaoqing  
	 * @param dirCode
	 * @param status
	 * @return  
	 */
	public static Map<String, String> getDetailNameMap(String dirCode, String status) {
		Map<String, String> detailNameMap = new HashMap<>();
		// 获取字典表信息
		List<DataDirIce> detaDirList = getDataDetailList(dirCode,  status);
		try {
			for (DataDirIce dataDirIce : detaDirList) {
				// 字典表明细信息
				List<DataDirDetailIce> dirDetailList = dataDirIce.getDirDetailList();
				for (DataDirDetailIce dataDirDetailIce : dirDetailList) {
					String key = dataDirIce.getDirCode() + Constant.SPLIT_COMMA + dataDirDetailIce.getDetailCode();
					detailNameMap.put(key, dataDirDetailIce.getDetailName());
				}
			}
		} catch (Exception e) {
			LOGGER.error("getDetailNameMap", e);
		}
		LOGGER.debug("detailNameMap:{}", FastjsonUtils.toJsonString(detailNameMap));
		return detailNameMap;
	}
	
	/**
	 * 获取用户组织机构信息
	 * @param userId	用户ID
	 * @param exclusionOrgTypes	排除组织机构类型
	 * @param depth	获取层级
	 * @return
	 */
	public static UserOrganInfo[] getUserOrganInfo(int userId,
			String exclusionOrgTypes, int depth) {
		UserOrganInfo[] userOrganInfos = getCOrginfoIPrx().queryUserOrganInfo(userId, exclusionOrgTypes,
						depth,
						CommonContant.SERVCIE_ROUTE_DEFAULT_ORGAN,
						CommonContant.SERVICE_CHANNEL_COMPANY);
		LOGGER.debug("userorganInfos:{}", FastjsonUtils.toJsonString(userOrganInfos));
		return userOrganInfos;
	}
	
	/**
	 * 根据父节点获取组织机构信息
	 * @param userId	用户ID
	 * @param organId	组织机构ID 
	 * @param exclusionOrgTypes 排除组织机构类型
	 * @param depth		获取层级
	 * @return
	 */
	public static UserOrganInfo[] queryOrganListByParentID(int userId, int organId,
			String exclusionOrgTypes, int depth) {
		UserOrganInfo[] userOrganInfos = getCOrginfoIPrx()
				.queryOrganListByParentID(userId, organId, exclusionOrgTypes,
						depth, CommonContant.SERVICE_CHANNEL_COMPANY);
		LOGGER.debug("sub tree:{}", FastjsonUtils.toJsonString(userOrganInfos));
		return userOrganInfos;
	}
	
	/**
	 * 根据父节点获取组织机构以及下面部门和项目信息
	 * @param userId
	 * @param organId
	 * @return
	 */
	public static SubOrganInfoDto querySubOrganInfoByParentId(int userId, int organId) {
		SubOrganInfoDto dto = null;
		UserOrganInfo[] userOrganInfos = queryOrganListByParentID(userId, organId, "1,2,3,4,5", 1);
		if (null != userOrganInfos && userOrganInfos.length > 0) {
			dto = new SubOrganInfoDto();
			for (int i = 0; i < userOrganInfos.length; i++) {
				if (userOrganInfos[i].organType == Integer.parseInt(Constant.ORGAN_TYPE_COMMUNITY)) {
					dto.getCommunityList().add(userOrganInfos[i]);
				}
				if (userOrganInfos[i].organType == Integer.parseInt(Constant.ORGAN_TYPE_DEPT)) {
					dto.getDeptList().add(userOrganInfos[i]);
				}
			}
		}
		return dto;
	}
	
	/**
	 * @Title: getUserInfoList   
	 *  根据userIds查询用户信息
	 * @author zhaoqing  
	 * @param userIdSet
	 * @return 
	 */
	public static List<UserInfo> getUserInfoList(Set<Integer> userIdSet) {
		List<UserInfo> userInfoList = new ArrayList<>();
		if (null == userIdSet || userIdSet.size() == 0) {
			return userInfoList;	
		}
		for (Integer userId : userIdSet) {
			try {
				UserInfo userInfo = getCurrentUserInfoByID(userId);
				userInfoList.add(userInfo);
			} catch (Exception e) {
				LOGGER.error("getUserInfoList", e);
			}
		}
		return userInfoList;	
	}
	
	/**
	 * @Title: getOrganInfoList   
	 *  根据organIds查询组织机构信息
	 * @author zhaoqing  
	 * @param organIdSet
	 * @return   
	 */
	public static List<Organ> getOrganInfoList(Set<Integer> organIdSet) {
		List<Organ> organList = new ArrayList<>();
		if (null == organIdSet || organIdSet.size() == 0){
			return organList;
		}
		try {
			for (Integer organId : organIdSet) {
				Organ organ = getOrganByID(organId);
				organList.add(organ);
			}
		} catch (Exception e) {
			LOGGER.error("getOrganInfoList", e);
		}
		return organList;
	}
	
	/**
	 * @Title: getUserInfoMap   
	 *  获取用户信息的Map集合
	 * @author zhaoqing  
	 * @param carFleetManagerList
	 * @return   
	 */
	public static <V> Map<Integer, UserInfo> getUserInfoMap(List<V> list,
			InvocationHandler<Integer, V> handler) {	
		Map<Integer, UserInfo> map = new HashMap<>();
		if (AppUtils.isNotEmpty(list)){
			// 获取去重后的所有用户Id
			Set<Integer> userIdList = AppUtils.list2ParamsSet(list, handler);
			// 查询用户信息
			List<UserInfo> userInfoList = getUserInfoList(userIdList);
			if (AppUtils.isNotEmpty(userInfoList)) {
				map = AppUtils.list2Map(userInfoList, new UserInfoInvocationImpl());
			}
		}	
		return map;	
	}

	/**
	 * @Title: getOrganInfoMap   
	 *  获取组织信息Map集合
	 * @author zhaoqing  
	 * @param carFleetRangeList
	 * @return   
	 */
	public static <V> Map<Integer, Organ> getOrganInfoMap(List<V> list, InvocationHandler<Integer, V> handler) {
		Map<Integer, Organ> map = new HashMap<>();
		if (AppUtils.isNotEmpty(list)) {
			Set<Integer> organIds = AppUtils.list2ParamsSet(list,handler);
			// 查询组织信息
			List<Organ> organInfoList = getOrganInfoList(organIds);
			if (AppUtils.isNotEmpty(organInfoList)) {
				map = AppUtils.list2Map(organInfoList, new OrganInvocationImpl());
			}
		}		
		return map;
	}

	/**
	 * @discription 根据用户的organId判断是否是平台账号   是 ：true 否 false
	 * @author wangxiong@segimail.com       
	 * @created 2018年2月1日 下午12:01:11     
	 * @param organId
	 * @return  food.getOrganId() + " 无法找到对应的一级物业，登录账户不能归属在第一层级组织机构"
	 */
	public static boolean verfyPlatformUserByOrganId(int organId) {
		if (Constant.PORTAL_ORGAN_ID.intValue() == organId) {
			return true;
		}
		return false;
	}

	/**
	 * 根据User获取有权限的项目列表
	 * @param userId
	 * @return
	 */
	public static List<CommunityInfo> getCommunitysByUser(Integer userId) {
		UserOrganInfo[] userOrganInfos = getCOrginfoIPrx().queryCommunityListByUserId(userId, 
				CommonContant.SERVCIE_ROUTE_DEFAULT_ORGAN, CommonContant.SERVICE_CHANNEL_COMPANY);
		
		Set<Integer> tmp = new HashSet<Integer>();
		
		List<CommunityInfo> communityInfos = Lists.newArrayList();
		if (userOrganInfos != null && userOrganInfos.length > 0) {
			for (UserOrganInfo organ : userOrganInfos) {
				// 去重
				if(tmp.contains(organ.getOrganId())){
					continue;
				}
				CommunityInfo communityInfo = new CommunityInfo();
				communityInfo.setCommunityId(organ.getOrganId());
				communityInfo.setName(organ.getOrganName());
				communityInfos.add(communityInfo);
				
				tmp.add(organ.getOrganId());
			}
		}
		return communityInfos;
	}
}
