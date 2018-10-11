package com.segi.uhomecp.medicaltrans.spacemanage.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.segi.uhomecp.common.constant.RestApiConstant;
import com.segi.uhomecp.common.contant.CommonContant;
import com.segi.uhomecp.sso.auth.vo.User;
import com.segi.uhomecp.sso.client.annotation.AdminUserParam;
import com.segi.uhomecp.web.rest.RestResponse;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

import IceExt.IceClientUtil;
import segi.datacachesvr.queryInfo.BuildDetail;
import segi.datacachesvr.queryInfo.BuildDetailInfo;
import segi.datacachesvr.queryInfo.BuildInfoV2;
import segi.datacachesvr.queryInfo.CCBuildIPrx;
import segi.datacachesvr.queryInfo.CCommunityIPrx;
import segi.datacachesvr.queryInfo.CHouseIPrx;
import segi.datacachesvr.queryInfo.COrganEmployeeIPrx;
import segi.datacachesvr.queryInfo.COrginfoIPrx;
import segi.datacachesvr.queryInfo.FloorBriefInfo;
import segi.datacachesvr.queryInfo.FloorDetailInfo;
import segi.datacachesvr.queryInfo.GroupUserBrief;
import segi.datacachesvr.queryInfo.HouseBriefInfo;
import segi.datacachesvr.queryInfo.HouseDetail;
import segi.datacachesvr.queryInfo.Organ;
import segi.datacachesvr.queryInfo.PositionV2;
import segi.datacachesvr.queryInfo.TUnitInfo;
import segi.datacachesvr.queryInfo.UnitDetailInfo;
import segi.datacachesvr.queryInfo.UserInfoV2;
import segi.datacachesvr.queryInfo.communityGroup;
import segi.datacachesvr.queryInfo.group;
import segi.datacachesvr.queryInfo.retBuildInfoV2List;
import segi.datacachesvr.queryInfo.retGroupUserList;
import segi.datacachesvr.queryInfo.retHouseBriefInfoList;
import segi.datacachesvr.queryInfo.retOrganInfoList;
import segi.datacachesvr.queryInfo.retUserInfoV2List;

import com.segi.uhomecp.wh.common.utils.AppUtils;

/**
 * 
 * @author dengdong
 *
 */
@Controller
@RequestMapping("/rest-api/v1/medicaltrans/testmtSpaceManageWebRest")
@Api(value = "medicaltrans/testmtSpaceManageWebRest", description = "空间管理测试")
public class TestMtSpaceManageWebRest {
	
	private static final Logger logger = LoggerFactory.getLogger(TestMtSpaceManageWebRest.class);

	/**
	 * 组织机构路由用途的默认值
	 */
	public static final int NORGANID = 0;
	
	/**
	 * 请求渠道（1 物业服务；2 商业服务）物业服务类型
	 */
	public static final int CHANNEL = 1;
	
	/**
	 * @Title: getCCBuildIPrx   
	 *  楼栋单元信息公共服务ICE 
	 * @author zhaoqing  
	 * @return 
	 */
	private static CCBuildIPrx getCCBuildIPrx() {
		return IceClientUtil.getServicePrxByClass(CCBuildIPrx.class);
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
	 * @Title: getCHouseIPrx   
	 *  房屋信息公共服务ICE 
	 * @author zhaoqing  
	 * @return 
	 */
	private static CHouseIPrx getCHouseIPrx() {
		return IceClientUtil.getServicePrxByClass(CHouseIPrx.class);
	}
	
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
	 * @Title: getCOrginfoIPrx   
	 *  组织机构信息公共ICE服务
	 * @author zhaoqing  
	 * @return 
	 */
	private static COrginfoIPrx getCOrginfoIPrx() {
		return IceClientUtil.getServicePrxByClass(COrginfoIPrx.class);
	}
	
	private CCommunityIPrx getCCommunityI() {
		return IceClientUtil.getServicePrxByClass(CCommunityIPrx.class);
	}
	
	/**
	 * 
	 * 类描述: 3.1.1.	根据楼栋ID查询楼栋详细信息
	 * 创建人: dengdong@sige.com   
	 * 创建时间: 2018年5月16日 上午10:29:38
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "3.1.1.根据楼栋ID查询楼栋详细信息", response = String.class, notes = "3.1.1.根据楼栋ID查询楼栋详细信息")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryBuildByID.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryBuildByID(@AdminUserParam User user, 
            @RequestParam(value = "nBuildID", required = false) String nBuildID) {
		try {
			// 获取ICE服务
			BuildDetail buildInfos = getCCBuildIPrx().queryBuildByID(Integer.valueOf(nBuildID), NORGANID, CHANNEL);
			return RestResponse.RestResponseBuilder.createSuccessBuilder("根据楼栋ID查询楼栋详细信息!")
					.setResult(buildInfos).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过项目ID查询所有楼栋简单信息失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	/**
	 * 
	 * 类描述: 3.1.2.根据单元ID查询单元详细信息
	 * 创建人: dengdong@sige.com   
	 * 创建时间: 2018年5月16日 上午10:29:38
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "3.1.2.根据单元ID查询单元详细信息", response = String.class, notes = "3.1.2.根据单元ID查询单元详细信息")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryUnitByID.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryUnitByID(@AdminUserParam User user, 
            @RequestParam(value = "unitId", required = false) String unitId) {
		try {
			// 获取ICE服务
			TUnitInfo unitDetailInfo = getCCBuildIPrx().queryUnitByID(Integer.valueOf(unitId));
			return RestResponse.RestResponseBuilder.createSuccessBuilder("根据单元ID查询单元详细信息!")
					.setResult(unitDetailInfo).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过项目ID查询所有楼栋简单信息失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	/**
	 * 
	 * 类描述: 3.1.3.根据楼层ID查询楼层概要信息
	 * 创建人: dengdong@sige.com   
	 * 创建时间: 2018年5月16日 上午10:29:38
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "3.1.3.根据楼层ID查询楼层概要信息", response = String.class, notes = "3.1.3.根据楼层ID查询楼层概要信息")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryFloorBriefByID.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryFloorBriefByID(@AdminUserParam User user, 
            @RequestParam(value = "nFloorID", required = false) String nFloorID) {
		try {
			// 获取ICE服务
			FloorBriefInfo floorBriefInfo = getCCBuildIPrx().queryFloorBriefByID(Integer.valueOf(nFloorID), NORGANID, CHANNEL);
			return RestResponse.RestResponseBuilder.createSuccessBuilder("根据单元ID查询单元详细信息!")
					.setResult(floorBriefInfo).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过项目ID查询所有楼栋简单信息失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	/**
	 * 
	 * 类描述: 3.1.4.根据楼栋ID查询楼栋详细信息
	 * 创建人: dengdong@sige.com   
	 * 创建时间: 2018年5月16日 上午10:29:38
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "3.1.4.根据楼栋ID查询楼栋详细信息", response = String.class, notes = "3.1.4.根据楼栋ID查询楼栋详细信息")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryBuildDetailByID.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryBuildDetailByID(@AdminUserParam User user, 
            @RequestParam(value = "nBuildID", required = false) String nBuildID) {
		try {
			// 获取ICE服务
			BuildDetailInfo buildDetailInfo = getCCBuildIPrx().queryBuildDetailByID(Integer.valueOf(nBuildID), NORGANID, CHANNEL);
			return RestResponse.RestResponseBuilder.createSuccessBuilder("根据单元ID查询单元详细信息!")
					.setResult(buildDetailInfo).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过项目ID查询所有楼栋简单信息失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	/**
	 * 
	 * 类描述: 3.1.5.根据单元ID查询单元详细信息
	 * 创建人: dengdong@sige.com   
	 * 创建时间: 2018年5月16日 上午10:29:38
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "3.1.5.根据单元ID查询单元详细信息", response = String.class, notes = "3.1.5.根据单元ID查询单元详细信息")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryUnitDetailByID.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryUnitDetailByID(@AdminUserParam User user, 
            @RequestParam(value = "unitId", required = false) String unitId) {
		try {
			// 获取ICE服务
			UnitDetailInfo unitDetailInfo = getCCBuildIPrx().queryUnitDetailByID(Integer.valueOf(unitId), NORGANID, CHANNEL);
			return RestResponse.RestResponseBuilder.createSuccessBuilder("根据单元ID查询单元详细信息!")
					.setResult(unitDetailInfo).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过项目ID查询所有楼栋简单信息失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	/**
	 * 
	 * 类描述: 3.1.6.根据楼层ID查询楼层概要信息
	 * 创建人: dengdong@sige.com   
	 * 创建时间: 2018年5月16日 上午10:29:38
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "3.1.6.根据楼层ID查询楼层概要信息", response = String.class, notes = "3.1.6.根据楼层ID查询楼层概要信息")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryFloorDetailByID.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryFloorDetailByID(@AdminUserParam User user, 
            @RequestParam(value = "nFloorID", required = false) String nFloorID) {
		try {
			// 获取ICE服务
			FloorDetailInfo floorDetailInfo = getCCBuildIPrx().queryFloorDetailByID(Integer.valueOf(nFloorID), NORGANID, CHANNEL);
			return RestResponse.RestResponseBuilder.createSuccessBuilder("根据单元ID查询单元详细信息!")
					.setResult(floorDetailInfo).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过项目ID查询所有楼栋简单信息失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	
	/**
	 * 
	 * 类描述: 3.1.7.根据项目ID查询楼栋集合
	 * 创建人: dengdong@sige.com   
	 * 创建时间: 2018年5月16日 上午10:29:38
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "3.1.7.根据项目ID查询楼栋集合", response = String.class, notes = "3.1.7.根据项目ID查询楼栋集合")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryBuildByCommID.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryBuildByCommID(@AdminUserParam User user, 
            @RequestParam(value = "communityId", required = false) String communityId) {
		try {
			// 获取ICE服务
			BuildInfoV2[] buildInfoV2List = null;
			retBuildInfoV2List retBuildInfoV2List = getCCBuildIPrx()
					.queryBuildInfoV2ListByCommID (Integer.valueOf(communityId), NORGANID, CHANNEL);	
			if (null != retBuildInfoV2List) {
				buildInfoV2List = retBuildInfoV2List.getData();
			}
			
			//BuildInfo[] buildInfos = getCCBuildIPrx().queryBuildByCommID(Integer.valueOf(communityId), NORGANID, CHANNEL);
			List<BuildInfoV2> resultList = new ArrayList<>();
			for (BuildInfoV2 buildInfo : buildInfoV2List) {
				if (null != buildInfo) {
					resultList.add(BeanCopierUtils.copyProperties(
							buildInfo, BuildInfoV2.class, true));
				}
			}
			Map<String, List<BuildInfoV2>> map = new HashMap<>();
			map.put("resultList",resultList);
            return RestResponse.RestResponseBuilder.createSuccessBuilder("通过项目ID查询所有楼栋简单信息成功!")
					.setResult(map).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过项目ID查询所有楼栋简单信息失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	/**
	 * 
	 * 类描述: 3.1.8.位置树异步查询
	 * 创建人: dengdong@sige.com   
	 * 创建时间: 2018年5月16日 上午10:29:38
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "3.1.8.位置树异步查询", response = String.class, notes = "3.1.8.位置树异步查询")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryPositionListByParId.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryPositionListByParId(@AdminUserParam User user, 
            @RequestParam(value = "communityId", required = false) String communityId,
            @RequestParam(value = "positionId", required = false) String positionId) {
		try {
			// 获取ICE服务
			PositionV2[] positionV2s =null ;
			int parId = Integer.valueOf(positionId);
			if(parId==-1){
				//return queryPositionListByCommuIdV2(communityId, NORGANID, CHANNEL);	
				BuildInfoV2[] buildInfoV2List = null;
				
				retBuildInfoV2List retBuildInfoV2List = getCCBuildIPrx()
						.queryBuildInfoV2ListByCommID (Integer.valueOf(communityId), NORGANID, CHANNEL);	
				if (null != retBuildInfoV2List && retBuildInfoV2List.getData() != null
						&& retBuildInfoV2List.getData().length > 0) {
					buildInfoV2List = retBuildInfoV2List.getData();	
					positionV2s =new PositionV2[buildInfoV2List.length];
					for(int i=0; i<positionV2s.length; i++){
						PositionV2 positionV2 = new PositionV2();
						BuildInfoV2 buildInfoV2 = buildInfoV2List[i]; 
						positionV2.setCommunityId(Integer.valueOf(communityId));
						positionV2.setPositionId(buildInfoV2.getBuildId());
						positionV2.setPositionName(buildInfoV2.getName());
						positionV2.setUpPositionId(Integer.valueOf(communityId));
						positionV2s[i] = positionV2;
					} 
				}		
				
			}else{
				positionV2s = getCCommunityIPrx()
						.queryPositionListByParIdV2(Integer.valueOf(communityId), parId, 1, 4, NORGANID, CHANNEL);
			}
			List<PositionV2> resultList = new ArrayList<>();
			if(positionV2s!=null){
				for (PositionV2 positionV2 : positionV2s) {
					if (null != positionV2) {
						resultList.add(BeanCopierUtils.copyProperties(
								positionV2, PositionV2.class, true));
					}
				}
			}
			
			Map<String, List<PositionV2>> map = new HashMap<>();
			map.put("resultList",resultList);
            return RestResponse.RestResponseBuilder.createSuccessBuilder("通过项目ID查询所有楼栋简单信息成功!")
					.setResult(map).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过项目ID查询所有楼栋简单信息失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	/**
	 * 
	 * 类描述: 3.1.9.根据位置查询房间集合
	 * 创建人: dengdong@sige.com   
	 * 创建时间: 2018年5月16日 上午10:29:38
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "3.1.9.根据位置查询房间集合", response = String.class, notes = "3.1.9.根据位置查询房间集合")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryHouseListByParam.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryHouseListByParam(@AdminUserParam User user, 
            @RequestParam(value = "nFloorID", required = false) String nFloorID) {
		try {
			HouseBriefInfo[] houseBriefInfos = null;
			retHouseBriefInfoList resp = getCHouseIPrx()
					.queryHouseBriefByFloorId(Integer.valueOf(nFloorID), NORGANID, CHANNEL);
			if (null != resp) {
				houseBriefInfos = resp.getData();
			}

			List<HouseBriefInfo> resultList = new ArrayList<>();
			for (HouseBriefInfo houseBriefInfo : houseBriefInfos) {
				if (null != houseBriefInfo) {
					resultList.add(BeanCopierUtils.copyProperties(
							houseBriefInfo, HouseBriefInfo.class, true));
				}
			}
			Map<String, List<HouseBriefInfo>> map = new HashMap<>();
			map.put("resultList",resultList);
            return RestResponse.RestResponseBuilder.createSuccessBuilder("通过项目ID查询所有楼栋简单信息成功!")
					.setResult(map).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过项目ID查询所有楼栋简单信息失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	/**
	 * 
	 * 类描述: 3.1.10.根据房屋id 查 房屋信息
	 * 创建人: dengdong@sige.com   
	 * 创建时间: 2018年5月16日 上午10:29:38
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "3.1.10.根据房屋id 查 房屋信息", response = String.class, notes = "3.1.10.根据房屋id 查 房屋信息")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryHouseDetailByID.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryHouseDetailByID(@AdminUserParam User user, 
            @RequestParam(value = "nHouseID", required = false) String nHouseID) {
		try {
			// 获取ICE服务
			HouseDetail houseDetail = getCHouseIPrx().queryHouseDetailByID(Integer.valueOf(nHouseID), NORGANID, CHANNEL);
			return RestResponse.RestResponseBuilder.createSuccessBuilder("根据单元ID查询单元详细信息!")
					.setResult(houseDetail).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过项目ID查询所有楼栋简单信息失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	
	/**
	 * 
	 * 类描述: 3.2.2.根据多个员工ID查询员工信息集合
	 * 创建人: dengdong@sige.com   
	 * 创建时间: 2018年5月16日 上午10:29:38
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "3.2.2.根据多个员工ID查询员工信息集合", response = String.class, notes = "3.2.2.根据多个员工ID查询员工信息集合")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryUserListByUserIds.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryUserListByUserIds(@AdminUserParam User user, 
            @RequestParam(value = "userIds", required = false) String userIds) {
		try {
			// 获取ICE服务
			UserInfoV2[] rserDepInfos = null;
			retUserInfoV2List retUserInfoList = getCOrganEmployeeIPrx()
					.queryUserListByUserIds(userIds, NORGANID, CHANNEL);	
			if (null != retUserInfoList) {
				rserDepInfos = retUserInfoList.getData();
			}
			List<UserInfoV2> resultList = new ArrayList<>();
			for (UserInfoV2 userInfoV2 : rserDepInfos) {
				if (null != userInfoV2) {
					resultList.add(BeanCopierUtils.copyProperties(
							userInfoV2, UserInfoV2.class, true));
				}
			}
			Map<String, List<UserInfoV2>> map = new HashMap<>();
			map.put("resultList",resultList);
            return RestResponse.RestResponseBuilder.createSuccessBuilder("通过项目ID查询所有楼栋简单信息成功!")
					.setResult(map).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过项目ID查询所有楼栋简单信息失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	/**
	 * 
	 * 类描述: 3.2.3.根据多个服务组ID查询员工信息集合
	 * 创建人: dengdong@sige.com   
	 * 创建时间: 2018年5月16日 上午10:29:38
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "3.2.3.根据多个服务组ID查询员工信息集合", response = String.class, notes = "3.2.3.根据多个服务组ID查询员工信息集合")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryUserListByGroupIds.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryUserListByGroupIds(@AdminUserParam User user, 
            @RequestParam(value = "groupIds", required = false) String groupIds) {
		try {
			// 获取ICE服务
			GroupUserBrief[] groupUserBriefs = null;
			retGroupUserList retGroupUserList = getCCommunityIPrx()
					.queryUserListByGroupIds(groupIds, NORGANID, CHANNEL);
			if (null != retGroupUserList) {
				groupUserBriefs = retGroupUserList.getData();
			}
			List<GroupUserBrief> resultList = new ArrayList<>();
			for (GroupUserBrief groupUserBrief : groupUserBriefs) {
				if (null != groupUserBrief) {
					resultList.add(BeanCopierUtils.copyProperties(
							groupUserBrief, GroupUserBrief.class, true));
				}
			}
			Map<String, List<GroupUserBrief>> map = new HashMap<>();
			map.put("resultList",resultList);
            return RestResponse.RestResponseBuilder.createSuccessBuilder("通过项目ID查询所有楼栋简单信息成功!")
					.setResult(map).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过项目ID查询所有楼栋简单信息失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	/**
	 * 
	 * 类描述: 3.2.4.根据多个员工ID查询员工所属服务组信息集合
	 * 创建人: dengdong@sige.com   
	 * 创建时间: 2018年5月16日 上午10:29:38
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "3.2.4.根据多个员工ID查询员工所属服务组信息集合", response = String.class, notes = "3.2.4.根据多个员工ID查询员工所属服务组信息集合")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryGroupListByUserIds.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryGroupListByUserIds(@AdminUserParam User user, 
            @RequestParam(value = "userIds", required = false) String userIds) {
		try {
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
						.queryGroupIdsByUserList(userIdList, NORGANID, CHANNEL);
				
				if (null != retGroupUserList) {
					groupUserBriefs = retGroupUserList.getData();
				}
			}
			List<GroupUserBrief> resultList = new ArrayList<>();
			for (GroupUserBrief groupUserBrief : groupUserBriefs) {
				if (null != groupUserBrief) {
					resultList.add(BeanCopierUtils.copyProperties(
							groupUserBrief, GroupUserBrief.class, true));
				}
			}
			Map<String, List<GroupUserBrief>> map = new HashMap<>();
			map.put("resultList",resultList);
            return RestResponse.RestResponseBuilder.createSuccessBuilder("通过项目ID查询所有楼栋简单信息成功!")
					.setResult(map).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过项目ID查询所有楼栋简单信息失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	/**
	 * 
	 * 类描述: 3.3.1.根据多个组织ID查询组织信息集合
	 * 创建人: dengdong@sige.com   
	 * 创建时间: 2018年5月16日 上午10:29:38
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "3.3.1.根据多个组织ID查询组织信息集合", response = String.class, notes = "3.3.1.根据多个组织ID查询组织信息集合")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryOrganListByIds.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryOrganListByIds(@AdminUserParam User user, 
            @RequestParam(value = "organIds", required = false) String organIds) {
		try {
			// 获取ICE服务
			Organ[] organs = null;
			retOrganInfoList retOrganInfoList = getCOrginfoIPrx()
					.queryOrganListByOrgIds(organIds, NORGANID, CHANNEL);
			if (null != retOrganInfoList) {
				organs = retOrganInfoList.getData();
			}
			List<Organ> resultList = new ArrayList<>();
			for (Organ organ : organs) {
				if (null != organ) {
					resultList.add(BeanCopierUtils.copyProperties(
							organ, Organ.class, true));
				}
			}
			Map<String, List<Organ>> map = new HashMap<>();
			map.put("resultList",resultList);
            return RestResponse.RestResponseBuilder.createSuccessBuilder("通过项目ID查询所有楼栋简单信息成功!")
					.setResult(map).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过项目ID查询所有楼栋简单信息失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	/**
	 * 
	 * 类描述: 3.4.1.查询项目的服务处信息集合
	 * 创建人: dengdong@sige.com   
	 * 创建时间: 2018年5月16日 上午10:29:38
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "3.4.1.查询项目的服务处信息集合", response = String.class, notes = "3.4.1.查询项目的服务处信息集合")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryGroupByCommunityVid.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryGroupByCommunityVid(@AdminUserParam User user, 
            @RequestParam(value = "organId", required = false) String organId) {
		try {
			// 获取当前组织机构信息
			group[] groups = null;
			communityGroup communityGroup = getCCommunityI().queryGroupByCommunityVid(Integer.valueOf(organId), CommonContant.SERVCIE_ROUTE_DEFAULT_ORGAN, CommonContant.SERVICE_CHANNEL_COMPANY);
			
			if(communityGroup!=null){
				groups=communityGroup.manageGroup;
			}	
			List<group> resultList = new ArrayList<>();
			for (group group : groups) {
				if (null != group) {
					resultList.add(group);
				}
			}
			Map<String, List<group>> map = new HashMap<>();
			map.put("resultList",resultList);
            return RestResponse.RestResponseBuilder.createSuccessBuilder("通过项目ID查询所有楼栋简单信息成功!")
					.setResult(map).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过项目ID查询所有楼栋简单信息失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
}
