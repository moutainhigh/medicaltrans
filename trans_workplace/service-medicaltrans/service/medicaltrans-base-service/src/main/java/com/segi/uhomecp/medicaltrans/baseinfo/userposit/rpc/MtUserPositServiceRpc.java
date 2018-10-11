package com.segi.uhomecp.medicaltrans.baseinfo.userposit.rpc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import page.RpcPageIce;
import segi.datacachesvr.queryInfo.BuildDetail;
import segi.datacachesvr.queryInfo.CCommunityIPrx;
import segi.datacachesvr.queryInfo.FloorBriefInfo;
import segi.datacachesvr.queryInfo.FloorDetailInfo;
import segi.datacachesvr.queryInfo.GroupUserInfo;
import segi.datacachesvr.queryInfo.UnitDetailInfo;
import segi.medicaltrans.base.userposit.BuildFloorIce;
import segi.medicaltrans.base.userposit.BuildIce;
import segi.medicaltrans.base.userposit.BuildLocationIce;
import segi.medicaltrans.base.userposit.ResultDateIce;
import segi.medicaltrans.base.userposit.SumLocationListIce;
import segi.medicaltrans.base.userposit.SumUserLocationInfoIce;
import segi.medicaltrans.base.userposit.UserGraLocationIceParam;
import segi.medicaltrans.base.userposit.UserGraLocationInfoPaginatorIce;
import segi.medicaltrans.base.userposit.UserGraLocationListIce;
import segi.medicaltrans.base.userposit.UserInfoIce;
import segi.medicaltrans.base.userposit.UserInfoPageParam;
import segi.medicaltrans.base.userposit.UserInfoPaginatorIce;
import segi.medicaltrans.base.userposit.UserListIce;
import segi.medicaltrans.base.userposit.UserPlaLocationIceParam;
import segi.medicaltrans.base.userposit.UserPlaLocationInfoIce;
import segi.medicaltrans.base.userposit.UserPositIce;
import segi.medicaltrans.base.userposit.UserPositRetIce;
import segi.medicaltrans.base.userposit.UserPositReturnIce;
import segi.medicaltrans.base.userposit.UserSumLocationListIce;
import segi.medicaltrans.base.userposit._UserPositServiceIceDisp;
import segi.medicaltrans.common.userstatus.UserStatusDetailRspIce;
import Ice.Current;
import IceExt.IceClientUtil;

import com.alibaba.druid.util.StringUtils;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.contant.CommonContant;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocation;
import com.segi.uhomecp.medicaltrans.base.posit.bean.MtCurUserPosit;
import com.segi.uhomecp.medicaltrans.baseinfo.location.service.MtBuildLocationManagerService;
import com.segi.uhomecp.medicaltrans.baseinfo.userposit.dto.MtUserLocationDto;
import com.segi.uhomecp.medicaltrans.baseinfo.userposit.dto.MtUserPositDto;
import com.segi.uhomecp.medicaltrans.baseinfo.userposit.enums.UserPositStatusEnum;
import com.segi.uhomecp.medicaltrans.baseinfo.userposit.service.MtUserpositInfoService;
import com.segi.uhomecp.medicaltrans.cache.CurUserLocationRedisCache;
import com.segi.uhomecp.medicaltrans.cache.MtLocationGrabRedisCache;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.medicaltrans.utils.MtSpaceManageServiceUtils;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.wh.common.enums.SexEnum;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;
import com.segi.uhomecp.wh.common.utils.PageUtils;

@Component
public class MtUserPositServiceRpc extends _UserPositServiceIceDisp{

	/**
	 * 类描述: 
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月2日 下午8:07:17   
	 */
	private static final long serialVersionUID = 1L;

	public static final Logger logger = LoggerFactory.getLogger(MtUserPositServiceRpc.class);
	
	@Autowired
	public MtUserpositInfoService mtUserpositInfoService;
	
	/*@Autowired
	private MtUserPositService mtUserPositService;*/
	
	@Autowired
	public CurUserLocationRedisCache curUserLocationRedisCache;
	
	@Autowired
	private MtLocationGrabRedisCache mtLocationGrabRedisCache;
	
	private CCommunityIPrx getCCommunityI() {
		return  IceClientUtil.getServicePrxByClass(CCommunityIPrx.class);
	}
	
	@Autowired
	public MtBuildLocationManagerService mtBuildLocationManagerService;
	
	/**
	 * 
	 * 类描述: 根据当前登录用户查询最后一次定位和时间
	 * 创建人: liuyi@sige.com    
	 * 创建时间: 2018年5月2日 下午8:07:29
	 */
	@Override
	public UserPositReturnIce getLastPositByUser(UserPositIce userPositIce,
			Current __current) {
		UserPositReturnIce result = new UserPositReturnIce();
		result.code = RpcError.SUCCESS.getCode();
		result.message = RpcError.SUCCESS.getMessage();
		try { 
			// 从缓存中查询
			MtCurUserPosit userPosit = new MtCurUserPosit();
			if (StringUtils.isEmpty(userPositIce.getUserOrganId()) && StringUtils.isEmpty(userPositIce.getUserId())) {
				userPosit = curUserLocationRedisCache.getUserLocationByOrganAndUserIdRedis(Integer.valueOf(userPositIce.getUserOrganId()), Integer.valueOf(userPositIce.getUserId()));
			}
			UserPositRetIce userPositRetIce = new UserPositRetIce();
			if (null != userPosit) {
				// 判断缓存中有没有查到数据
				userPositRetIce.setUpdateDate(userPosit.getLastUpdateTime() == null ? null : userPosit.getLastUpdateTime());
				// 查询科室名称
				if (userPosit.getLocationId() != null) {
					userPositRetIce.setLocationId(String.valueOf(userPosit.getLocationId()));
					List<Integer> list = new ArrayList<Integer>();
					list.add(userPosit.getLocationId());
					List<MtBuildLocation> locationInfoList = mtBuildLocationManagerService.getLocationInfoByRefIdList(userPosit.getOrganId(), list);
					if (AppUtils.isNotEmpty(locationInfoList) && null != locationInfoList.get(0)) {
						MtBuildLocation houseInfo = locationInfoList.get(0);
						userPositRetIce.setLocationName(houseInfo.getLocationName() == null ? null : houseInfo.getLocationName());
					}
				}
				result.setUserPositRetIce(userPositRetIce);
			}else {
				/*// 数据库中查询
				MtUserPosit mtUserPosit = mtUserpositInfoService.getLastPositByUser(Integer.valueOf(userPositIce.getOrganId())
						, Integer.valueOf(userPositIce.getUserId()));
				userPositRetIce.setUpdateDate(mtUserPosit.getUpdateDate() == null ? null : DateUtil.formatDateToString(mtUserPosit.getUpdateDate(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS));
				// 查询科室名称
				if (mtUserPosit.getHouseId() != null) {
					userPositRetIce.setHouseId(String.valueOf(mtUserPosit.getHouseId()));
					MtBuildHouse houseInfo = mtBuildHouseManagerService.getHouseInfoByHouseId(mtUserPosit.getHouseId());
					if (houseInfo != null) {
						userPositRetIce.setHouseName(houseInfo.getHouseName() == null ? null : houseInfo.getHouseName());
					}
				}
				result.setUserPositRetIce(userPositRetIce);*/
			}
		} catch (Exception e) {
			logger.error("getLastPositByUser", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("根据当前登录用户查询最后一次定位和时间失败");
		}
		return result;
	}

	/**
	 * 
	 * 类描述: 上传当前用户位置信息
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月3日 下午8:22:33
	 */
	@Override
	public UserPositReturnIce updateLocatePosit(UserPositIce userPositIce,
			Current __current) {
		UserPositReturnIce result = new UserPositReturnIce();
		result.code = RpcError.SUCCESS.getCode();
		result.message = RpcError.SUCCESS.getMessage();
		try {
			MtBuildLocation BuildLocation = null;
			// 查询科室信息
			if (!StringUtils.isEmpty(userPositIce.getLocationId()) && !StringUtils.isEmpty(userPositIce.getOrganId())) {
				// 判断科室id是否为空
				List<Integer> list = new ArrayList<Integer>();
				list.add(Integer.valueOf(userPositIce.getLocationId()));
				List<MtBuildLocation> locationInfoList = mtBuildLocationManagerService.getLocationInfoByRefIdList(Integer.valueOf(userPositIce.getOrganId()), list);
				if (AppUtils.isNotEmpty(locationInfoList) && locationInfoList.get(0) != null) {
					BuildLocation = locationInfoList.get(0);
				}
			}else {
					BuildLocation = mtBuildLocationManagerService.getLocationInfoByMac(Integer.valueOf(userPositIce.getOrganId()), userPositIce.getMac());
			}
			if (BuildLocation == null) {
				result.setCode(RpcError.FAIL.getCode());
				result.setMessage("查询当前科室信息失败");
				return result;
			}
			MtUserPositDto dto = BeanCopierUtils.copyProperties(userPositIce, MtUserPositDto.class, true);
			dto.setLocationId(null == BuildLocation.getLocationId() ? null : BuildLocation.getLocationId());
			dto.setBuildId(null == BuildLocation.getBuildId() ? null : BuildLocation.getBuildId());
			dto.setFloorId(null == BuildLocation.getFloorId() ? null : BuildLocation.getFloorId());
			mtUserpositInfoService.updateLocatePosit(dto);
			
			UserPositRetIce userPositRetIce = new UserPositRetIce();
			userPositRetIce.setLocationId(null == BuildLocation.getLocationId() ? "" : String.valueOf(BuildLocation.getLocationId()));
			userPositRetIce.setLocationName(StringUtils.isEmpty(BuildLocation.getLocationName())? "" : BuildLocation.getLocationName());
			userPositRetIce.setUpdateDate(DateUtil.nowDateToStringYYMMDDHHmmss());
			result.setUserPositRetIce(userPositRetIce);
		} catch (Exception e) {
			logger.error("updateLocatePosit", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("上传当前用户位置信息失败");
		}
		return result;
	}
	
	/**
	 * 人员位置列表分页查询(3D图)
	 */
	@Override
	public UserGraLocationInfoPaginatorIce queryUserLocationPage(UserGraLocationIceParam userGraLocationIceParam,
			Current __current) {
		UserGraLocationInfoPaginatorIce rsp = new UserGraLocationInfoPaginatorIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new RpcPageIce(), new ArrayList<UserGraLocationListIce>());
		try {
			MtUserLocationDto params = BeanCopierUtils.copyProperties(userGraLocationIceParam,
					MtUserLocationDto.class, true);
			PageList<MtUserLocationDto> locationPageList = mtUserpositInfoService.queryUserLocationPage(params);
			if(AppUtils.isNotEmpty(locationPageList)){
				List<UserGraLocationListIce> locationInfoList = new ArrayList<UserGraLocationListIce>();
				UserGraLocationListIce userGraLocationListIce = null;
				for(MtUserLocationDto mtUserLocationDto:locationPageList){
					if(mtUserLocationDto!=null){
						userGraLocationListIce = BeanCopierUtils.copyProperties(mtUserLocationDto, UserGraLocationListIce.class, true);
						locationInfoList.add(userGraLocationListIce);
					}
				}
				rsp.setPaginator(PageUtils.paginator2RpcPageIce(locationPageList.getPaginator()));
				rsp.setResultList(locationInfoList);
			}else{
				//rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("3D图查询，没有该位置下的人员信息");
			}
			
		} catch (Exception e) {
			logger.error("updateLocatePosit", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("3D图查询，没有该位置下的人员信息");
		}
		return rsp;
	}
	
	/**
	 * 查询人员位置3D总数
	 */
	@Override
	public SumUserLocationInfoIce querySumUserLocation(UserGraLocationIceParam userGraLocationIceParam,
			Current __current) {
		SumUserLocationInfoIce rsp = new SumUserLocationInfoIce();
		rsp.code = RpcError.SUCCESS.getCode();
		rsp.message = RpcError.SUCCESS.getMessage();
		ResultDateIce resultDateIce = new ResultDateIce(); 
		try{
			MtUserLocationDto params = BeanCopierUtils.copyProperties(userGraLocationIceParam,
					MtUserLocationDto.class, true);
			List<MtBuildLocation> MtBuildLocationList = mtUserpositInfoService.querySumUserLocation(params);
			if(AppUtils.isNotEmpty(MtBuildLocationList)){
				List<SumLocationListIce> sumLocationListIceList = new ArrayList<SumLocationListIce>(); 
				for(MtBuildLocation mtBuildLocation:MtBuildLocationList){
					SumLocationListIce sumLocationListIce = new SumLocationListIce();
					sumLocationListIce.setLoactionId(String.valueOf(mtBuildLocation.getLocationId()));
					sumLocationListIce.setLoactionName(mtBuildLocation.getLocationName());
					sumLocationListIce.setFloorPositX(String.valueOf(mtBuildLocation.getFloorPositX()));
					sumLocationListIce.setFloorPositY(String.valueOf(mtBuildLocation.getFloorPositY()));
					
					List<MtCurUserPosit> MtCurUserPositList = mtBuildLocation.getMtCurUserPositList();
					if(AppUtils.isNotEmpty(MtCurUserPositList)){
						List<UserSumLocationListIce> userSumLocationListIceList = new ArrayList<UserSumLocationListIce>();
						UserSumLocationListIce userSumLocationListIce = null;
						for(MtCurUserPosit mtCurUserPosit:MtCurUserPositList){
							
							userSumLocationListIce = BeanCopierUtils.copyProperties(mtCurUserPosit, UserSumLocationListIce.class, true);
							if(!StringUtils.isEmpty(mtCurUserPosit.getStatus())){
								userSumLocationListIce.setStatusName(UserPositStatusEnum.getName(mtCurUserPosit.getStatus()));
							}
							userSumLocationListIceList.add(userSumLocationListIce);
						}
						sumLocationListIce.setUserListSize(String.valueOf(userSumLocationListIceList.size()));
						sumLocationListIce.setUserSumLocationListIceList(userSumLocationListIceList);
						
					}else{
						sumLocationListIce.setUserListSize("0");
						sumLocationListIce.setUserSumLocationListIceList(null);
					}
					sumLocationListIceList.add(sumLocationListIce);
				}
				
				resultDateIce.setResultList(sumLocationListIceList);
				
			}else{
				//rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("3D图查询，没有该位置下的人员信息");
			}
			if(params.getFloorId()!=null){
				FloorDetailInfo floorDetailInfo = MtSpaceManageServiceUtils.queryFloorDetailByID(params.getFloorId());
				//公共接口查询楼层的url
				String planUrl = floorDetailInfo.getPlanUrl();
				resultDateIce.setPlanUrl(planUrl);
			}
			rsp.setResult(resultDateIce);
		}catch (Exception e) {
			logger.error("querySumUserLocation", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("没有该位置下的人员信息");
		}
		return rsp;
	}
	
	/**
	 * 人员位置列表分页查询(2D图)
	 */
	@Override
	public UserPlaLocationInfoIce queryUserPlaLocation(UserPlaLocationIceParam serPlaLocationIceParam,
			Current __current) {
		UserPlaLocationInfoIce rsp = new UserPlaLocationInfoIce();
		rsp.code = RpcError.SUCCESS.getCode();
		rsp.message = RpcError.SUCCESS.getMessage();
		try {
			MtUserLocationDto params = BeanCopierUtils.copyProperties(serPlaLocationIceParam,
					MtUserLocationDto.class, true);
			Map<String, Map<Integer,List<MtBuildLocation>>> resultBuildMap =  mtUserpositInfoService.queryUserPlaLocation(params);
			//将四层机构的map转换成UserPlaLocationInfoIce结构
			if(resultBuildMap!=null && resultBuildMap.size()>0){
				List<BuildIce> BuildIceList = new ArrayList<BuildIce>();
				for(String buildSid:resultBuildMap.keySet()){
					BuildIce buildIce = new BuildIce();
					buildIce.setBuildId(buildSid);
					//遗留调公共接口查楼栋和单元名
					if(buildSid.indexOf("_")==-1){
						BuildDetail buildDetail = MtSpaceManageServiceUtils.queryBuildByID(Integer.valueOf(buildSid));
						buildIce.setBuildName(buildDetail.getName());
					}else{
						String[] parts = buildSid.split("_");
						BuildDetail buildDetail = MtSpaceManageServiceUtils.queryBuildByID(Integer.valueOf(parts[0]));
						UnitDetailInfo unitDetailInfo = MtSpaceManageServiceUtils.queryUnitDetailByID(Integer.valueOf(parts[1]));
						buildIce.setBuildName(buildDetail.getName()+"_"+unitDetailInfo.getUnitName());
					}
					//楼栋下的楼层
					Map<Integer,List<MtBuildLocation>> floorMap = resultBuildMap.get(buildSid);
					logger.info("==================>楼层排序前数据: floorMap:{}", 
							FastjsonUtils.toJsonString(floorMap));
					//将楼层去排序
					List<Map<Integer,List<MtBuildLocation>>> floorMapList = mtUserpositInfoService.sortFloorMap(floorMap);
					logger.info("==================>楼层排序后数据: data:{}", 
							FastjsonUtils.toJsonString(floorMapList));
					List<BuildFloorIce> buildFloorIceList =new ArrayList<BuildFloorIce>();
					buildIce.setBuildFloorIceListList(buildFloorIceList);
					if(floorMapList!=null && floorMapList.size()>0){
						for(Map<Integer,List<MtBuildLocation>> map:floorMapList){
							logger.info("==================>楼层排序后map: data:{}", 
									FastjsonUtils.toJsonString(map));
							Integer floorId = map.keySet().iterator().next();
							logger.info("==================>楼层排序后floorId: data:{}", 
									floorId);
							BuildFloorIce buildFloorIce = new BuildFloorIce();
							buildFloorIce.setFloorId(String.valueOf(floorId));
							//遗留调公共接口查楼层名
							FloorBriefInfo floorBriefInfo = MtSpaceManageServiceUtils.queryFloorBriefByID(floorId);
							buildFloorIce.setFloorNum(floorBriefInfo.getFloorName());
							buildFloorIce.setFloorName(floorBriefInfo.getFloorName());
							//楼层下位置的list
							List<MtBuildLocation> mtBuildLocationList = map.get(floorId);
							List<BuildLocationIce> buildLocationIceList = new ArrayList<BuildLocationIce>();
							buildFloorIce.setBuildLocationIceList(buildLocationIceList);
							if(AppUtils.isNotEmpty(mtBuildLocationList)){
								for(MtBuildLocation mtBuildLocation:mtBuildLocationList){
									if(mtBuildLocation!=null){
										BuildLocationIce buildLocationIce = new BuildLocationIce();
										buildLocationIce.setLocationId(String.valueOf(mtBuildLocation.getLocationId()));	
										buildLocationIce.setLocationName(mtBuildLocation.getLocationName());
										//位置下的人员列表
										List<MtCurUserPosit> mtCurUserPositList = mtBuildLocation.getMtCurUserPositList();
										List<UserListIce> userListIceList = new ArrayList<UserListIce>();
										buildLocationIce.setUserListIceList(userListIceList);
										if(AppUtils.isNotEmpty(mtCurUserPositList)){
											for(MtCurUserPosit mtCurUserPosit:mtCurUserPositList){
												if(mtCurUserPosit!=null){
													UserListIce userListIce = new UserListIce();
													userListIce.setUserId(String.valueOf(mtCurUserPosit.getUserId()));
													userListIce.setUserName(mtCurUserPosit.getUserName());
													userListIce.setUserWorkNo(mtCurUserPosit.getUserWorkNo());
													userListIce.setStatus(mtCurUserPosit.getStatus());
													userListIce.setStatusName(UserPositStatusEnum.getName(mtCurUserPosit.getStatus()));
													userListIceList.add(userListIce);
												}
											}
										}
										buildLocationIceList.add(buildLocationIce);
									}
								}
							}
							buildFloorIceList.add(buildFloorIce);
						}
					}
					BuildIceList.add(buildIce);
				}
				rsp.setResultList(BuildIceList);
			}else{
				//rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("2D图查询，没有该位置下的人员信息");
			}
		} catch (Exception e) {
			logger.error("queryUserPlaLocation", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("2D图查询，没有该位置下的人员信息");
		}
		
		return rsp;
	}

	/** @discription 根据部门员工姓名员工工号查询员工信息
	 * @author yangyh@segimail.com       
	 * @param userInfoPageParam
	 * @return        
	 */
	@Override
	public UserInfoPaginatorIce queryUserInfoPage(UserInfoPageParam userInfoPageParam, Current __current) {
		UserInfoPaginatorIce result = new UserInfoPaginatorIce(RpcError.SUCCESS.getCode(),
				RpcError.SUCCESS.getMessage(), new ArrayList<UserInfoIce>());
		try {
			// 获取ICE服务
			GroupUserInfo[] users = getCCommunityI().queryUserListByGroupId(
					Integer.valueOf(userInfoPageParam.getGroupId()).intValue(),
					CommonContant.SERVCIE_ROUTE_DEFAULT_ORGAN, CommonContant.SERVICE_CHANNEL_COMPANY);
			logger.info("==================>根据服务组查员工返回：users: users:{}", 
					FastjsonUtils.toJsonString(users));
			if (null != users) {
				List<UserInfoIce> resultList = BeanCopierUtils.copyList2List(Arrays.asList(users), UserInfoIce.class,
						true);
				logger.info("==================>根据服务组查员工返回：resultList: resultList:{}", 
						FastjsonUtils.toJsonString(resultList));
				resultList.removeAll(Collections.singleton(null));
				if (AppUtils.isNotEmpty(resultList)) {
					for (UserInfoIce userInfoIce : resultList) {
						userInfoIce.setUserSexName(SexEnum.getNameByCode(userInfoIce.getSex()));
						String groupId = userInfoIce.getGroupId();
						String userId = userInfoIce.getUserId();
						logger.info("==================>根据服务组查员工返回：userInfoIce: userInfoIce:{}", 
								FastjsonUtils.toJsonString(userInfoIce));
						if ((!StringUtils.isEmpty(userInfoPageParam.getOrganId())) && (!StringUtils.isEmpty(userId))) {
							MtCurUserPosit mtCurUserPosit = curUserLocationRedisCache
									.getUserLocationByOrganAndUserIdRedis(Integer.valueOf(userInfoPageParam.getOrganId()),
											Integer.valueOf(userId));
							if (null != mtCurUserPosit) {
								userInfoIce.setLocationId(null == mtCurUserPosit.getLocationId() ? "0" : String
										.valueOf(mtCurUserPosit.getLocationId()));
								userInfoIce.setStatus(mtCurUserPosit.getStatus());
								userInfoIce.setStatusName(UserPositStatusEnum.getName(mtCurUserPosit.getStatus()));
								// 为null时设为0
								userInfoIce.setUnTaskNum(null == mtCurUserPosit.getUnTaskNum() ? "0" : String
										.valueOf(mtCurUserPosit.getUnTaskNum()));
								MtBuildLocation redisLocation = mtLocationGrabRedisCache
										.getLocationByOrganAndLocationIdRedis(mtCurUserPosit.getOrganId(),
												mtCurUserPosit.getLocationId());
								userInfoIce.setLocationName(null == redisLocation ? "" : redisLocation
										.getLocationName());
							}else{
								userInfoIce.setStatus(UserPositStatusEnum.STATUS_LEISURE.getCode());
								userInfoIce.setStatusName(UserPositStatusEnum.STATUS_LEISURE.getName());
							}
							//2018.10.10,状态在空闲的时候要展示是否是下班状态--DD
							if(UserPositStatusEnum.STATUS_LEISURE.getCode().equals(userInfoIce.getStatus())){
								UserStatusDetailRspIce userStatusDetailRspIce =MtCommonServiceUtils.getStatusByUser(userInfoPageParam.getOrganId(),userInfoIce.getUserId());
								if(RpcError.SUCCESS.getCode().equals(userStatusDetailRspIce.getCode()) && "0".equals(userStatusDetailRspIce.getUserStatusDetailIce().getStatus())){
									userInfoIce.setStatusName(UserPositStatusEnum.getName(UserPositStatusEnum.STATUS_LEAVE.getCode()));
									userInfoIce.setStatus(UserPositStatusEnum.STATUS_LEAVE.getCode());
								}
							}
						}
					}
				}
				result.setResultList(resultList);
				logger.info("==================>返回数据：resultList: resultList:{}", 
						FastjsonUtils.toJsonString(resultList));
			}
		} catch (Exception e) {
			logger.error("queryUserInfoPage", e);
			result.code = RpcError.FAIL.getCode();
			result.msg = org.apache.commons.lang3.StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : "查询人员信息失败!";
		}
		return result;
	}

	@Override
	public String queryUserLocationByOrganIdRedis(String organId, Current __current) {
		String result="";
		try{
			List<MtCurUserPosit> mtCurUserPositList = curUserLocationRedisCache.getUserLocationByOrganRedis(Integer.valueOf(organId));
			result = FastjsonUtils.toJsonString(mtCurUserPositList);
		}catch (NumberFormatException e) {
			logger.error("queryUserLocationByOrganIdRedis", e);
		}
		return result;
	}
}
