package com.segi.uhomecp.medicaltrans.baseinfo.location.rpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import resp.RpcRespIce;
import segi.medicaltrans.location.common.LocationInfoIce;
import segi.medicaltrans.location.common.LocationInfoListReturn;
import segi.medicaltrans.location.common.LocationInfoListReturnIce;
import segi.medicaltrans.location.common.LocationInfoReturn;
import segi.medicaltrans.location.common.LocationInfoReturnIce;
import segi.medicaltrans.location.common.MtLocationInfoIce;
import segi.medicaltrans.location.common._MtLocationCommonServiceIceDisp;
import Ice.Current;

import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocation;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocationCriteria;
import com.segi.uhomecp.medicaltrans.base.location.service.MtBuildLocationService;
import com.segi.uhomecp.medicaltrans.baseinfo.location.service.MtBuildLocationManagerService;
import com.segi.uhomecp.medicaltrans.cache.MtLocationGrabRedisCache;
import com.segi.uhomecp.rest.utils.StringUtils;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
@Component
public class MtLocationCommonServiceRpc extends _MtLocationCommonServiceIceDisp {

	private static final long serialVersionUID = 1L;
	
	public static final Logger logger = LoggerFactory.getLogger(MtLocationCommonServiceRpc.class);
	
	@Autowired
	MtBuildLocationManagerService mtBuildLocationManagerService;
	
	@Autowired
	MtBuildLocationService mtBuildLocationService;
	
	@Autowired
	MtLocationGrabRedisCache mtLocationGrabRedisCache;
	
	/**
	 * 根据位置id list和项目查位置信息list(查缓存)
	 */
	@Override
	public LocationInfoListReturnIce getLocationInfoByRefIdList(int organId,List<Integer> locationIdList, Current __current) {
		LocationInfoListReturnIce rsp = new LocationInfoListReturnIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new ArrayList<MtLocationInfoIce>());
		if((!AppUtils.isNotEmpty(locationIdList))||organId==0){
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("没有传入位置id的list或者项目id");
			return rsp;
		}
		try{
			List<MtBuildLocation> locationList = mtBuildLocationManagerService.getLocationInfoByRefIdList(Integer.valueOf(organId),locationIdList);
			if(AppUtils.isNotEmpty(locationList)){
				List<MtLocationInfoIce> locationInfoList = new ArrayList<>();
				MtLocationInfoIce locationListIce = null;
				for(MtBuildLocation mtBuildLocation:locationList){
					locationListIce = BeanCopierUtils.copyProperties(mtBuildLocation, MtLocationInfoIce.class, true);
					locationInfoList.add(locationListIce);
				}
				rsp.setLocationInfoList(locationInfoList);
			}else{
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("没有位置信息");
			}
		} catch (Exception e) {
			logger.error("getLocationInfoByRefIdList", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}
	
	/**
	 * 根据位置id list查位置信息list(查数据库)
	 */
	@Override
	public LocationInfoListReturnIce getLocationInfoBylocationIdList(List<Integer> locationIdList, Current __current) {
		LocationInfoListReturnIce rsp = new LocationInfoListReturnIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new ArrayList<MtLocationInfoIce>());
		if(AppUtils.isNotEmpty(locationIdList)){
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("没有传入位置id的list");
			return rsp;
		}
		try{
			List<MtBuildLocation> locationList = mtBuildLocationManagerService.getLocationInfoBylocationIdList(locationIdList);
			if(AppUtils.isNotEmpty(locationList)){
				List<MtLocationInfoIce> locationInfoList = new ArrayList<>();
				MtLocationInfoIce locationListIce = null;
				for(MtBuildLocation mtBuildLocation:locationList){
					locationListIce = BeanCopierUtils.copyProperties(mtBuildLocation, MtLocationInfoIce.class, true);
					locationInfoList.add(locationListIce);
				}
				rsp.setLocationInfoList(locationInfoList);
			}else{
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("没有位置信息");
			}
		} catch (Exception e) {
			logger.error("getLocationInfoByRefIdList", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}

	/**
	 * 根据组织机构和mac地址查询位置详情
	 */
	@Override
	public LocationInfoReturnIce getLocationInfoByMac(int organId, String mac, Current __current) {
		LocationInfoReturnIce rsp = new LocationInfoReturnIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new MtLocationInfoIce());
		if(StringUtils.isEmpty(mac)||organId==0){
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("没有传入mac或者项目id");
			return rsp;
		}
		try{
			//从缓存中查
			MtBuildLocation mtBuildLocation = mtBuildLocationManagerService.getLocationInfoByMac(Integer.valueOf(organId),mac);
			if(mtBuildLocation!=null){
				MtLocationInfoIce locationListIce = BeanCopierUtils.copyProperties(mtBuildLocation, MtLocationInfoIce.class, true);
				rsp.setMtLocationInfoIce(locationListIce);
			}else{
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("没有该位置信息");
			}
			
			//屏蔽从数据库查询的办法
			/*MtLocationInfoIce mtLocationInfoIce =null;
			MtBuildLocationCriteria example = new MtBuildLocationCriteria();
			MtBuildLocationCriteria.Criteria criteria = example.createCriteria();
			criteria.andOrganIdEqualTo(Integer.valueOf(organId));
			criteria.andMacEqualTo(mac);
			criteria.andStatusEqualTo(Constant.STATUS_CD_NORMAL);
			List<MtBuildLocation>  mtbuildLoactionList = mtBuildLocationService.selectByExample(example);
			if(mtbuildLoactionList!=null && mtbuildLoactionList.size()>0){
				if(mtbuildLoactionList.size()==1){
					MtBuildLocation mtBuildLocation = mtbuildLoactionList.get(0);
					mtLocationInfoIce = BeanCopierUtils.copyProperties(mtBuildLocation, MtLocationInfoIce.class, true);
					rsp.setMtLocationInfoIce(mtLocationInfoIce);
				}else{
					rsp.setCode(RpcError.FAIL.getCode());
					rsp.setMessage("位置信息有重复");
				}
			}else{
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("没有位置信息");
			}*/
		}catch (Exception e) {
			logger.error("LocationInfoReturnIce", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}

	/**
	 * 
	 * @Title: queryLocationInfoPage 
	 * @Description: 科室信息列表 
	 * @author liuyi@segimail.com 
	 * @date 2018年7月24日下午2:24:58
	 */
	@Override
	public LocationInfoListReturn queryLocationList(
			List<Integer> LocationList, Current __current) {
		LocationInfoListReturn rsp = new LocationInfoListReturn();
		rsp.setCode(RpcError.SUCCESS.getCode());
		rsp.setMsg(RpcError.SUCCESS.getMessage());
		try {
			if (AppUtils.isNotEmpty(LocationList)) {
				MtBuildLocationCriteria example = new MtBuildLocationCriteria();
				MtBuildLocationCriteria.Criteria criteria = example.createCriteria(); 
				criteria.andLocationIdIn(LocationList);
				List<MtBuildLocation> mtBuildList = mtBuildLocationService.selectByExample(example);
				if (AppUtils.isNotEmpty(mtBuildList)) {
					rsp.setResultList(BeanCopierUtils.copyList2List(mtBuildList, LocationInfoIce.class, true));
				}
			}
		} catch (Exception e) {
			logger.error("queryLocationList", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMsg("查询科室信息列表失败");
		}
		return rsp;
	}

	/**
	 * 每晚刷新位置缓存
	 */
	@Override
	public RpcRespIce refreshRedisLocaiton(Current __current) {
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			ResultInfo resultInfo = mtBuildLocationManagerService.refreshRedisLocaiton();
			if (!resultInfo.getIsSucc()) {
				rsp.setCode(RpcError.FAIL.getCode());
			} 
		} catch (Exception e) {
			logger.error("refreshRedisLocaiton", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}

	/**
	 * 每晚刷新人员位置缓存
	 */
	@Override
	public RpcRespIce refreshRedisUserLocaiton(Current __current) {
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			ResultInfo resultInfo = mtBuildLocationManagerService.refreshRedisUserLocaiton();
			if (!resultInfo.getIsSucc()) {
				rsp.setCode(RpcError.FAIL.getCode());
			} 
		} catch (Exception e) {
			logger.error("refreshRedisUserLocaiton", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}

	/**
	 * @Title: queryLocationInfoByOrganIdList 
	 * @Description: 缓存查询科室信息 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月11日下午3:40:17
	 */
	@Override
	public LocationInfoListReturn queryLocationInfoByOrganIdList(int organId,
			Current __current) {
		LocationInfoListReturn rsp = new LocationInfoListReturn(RpcError.SUCCESS.getCode(),
				RpcError.SUCCESS.getMessage(), new ArrayList<LocationInfoIce>());
		try {
			List<MtBuildLocation> MtBuildLocationList = mtLocationGrabRedisCache.getLocationByOrganRedis(Integer.valueOf(organId));
			if (AppUtils.isNotEmpty(MtBuildLocationList)) {
				rsp.setResultList(BeanCopierUtils.copyList2List(MtBuildLocationList, LocationInfoIce.class, true));
			}
		} catch (Exception e) {
			logger.error("queryLocationInfoByOrganIdList", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMsg(RpcError.FAIL.getMessage());
		}
		return rsp;
	}

	/**
	 * @Title: queryLocationInfo 
	 * @Description: 通过organId  locationId 查询缓存科室信息
	 * @author liuyi@segimail.com 
	 * @date 2018年9月17日上午10:57:39
	 */
	@Override
	public LocationInfoReturn queryLocationInfo(int organId, int locationId,
			Current __current) {
		LocationInfoReturn rsp = new LocationInfoReturn(RpcError.SUCCESS.getCode(),
				RpcError.SUCCESS.getMessage(), new LocationInfoIce());
		try {
			MtBuildLocation MtBuildLocation = mtLocationGrabRedisCache.getLocationByOrganAndLocationIdRedis(organId,locationId);
			if (MtBuildLocation != null) {
				rsp.setLocationInfoIce(BeanCopierUtils.copyProperties(MtBuildLocation, LocationInfoIce.class, true));
			}
		} catch (Exception e) {
			logger.error("queryLocationInfo", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMsg(RpcError.FAIL.getMessage());
		}
		return rsp;
	}

	/**
	 * @Title: queryLocationInfoListByOrganId 
	 * @Description: 查询科室信息 
	 * @author liuyi@segimail.com 
	 * @date 2018年9月26日下午6:23:54
	 */
	@Override
	public LocationInfoListReturnIce queryLocationInfoListByOrganId(
			int organId, List<Integer> locationIdList, Current __current) {
		LocationInfoListReturnIce rsp = new LocationInfoListReturnIce(RpcError.SUCCESS.getCode(),
				RpcError.SUCCESS.getMessage(), new ArrayList<MtLocationInfoIce>());
		try {
			List<MtBuildLocation> MtBuildLocationList = mtLocationGrabRedisCache.getLocationByOrganRedis(Integer.valueOf(organId));
			if (AppUtils.isNotEmpty(MtBuildLocationList)) {
				List<MtLocationInfoIce> locationIdicelist = new ArrayList<MtLocationInfoIce>(); 
				Map<Integer, MtBuildLocation> locationMap = AppUtils.list2Map(MtBuildLocationList, (obj) -> obj.getLocationId());
				for (Integer locationid : locationIdList) {
					if (locationMap.get(locationid) != null) {
						locationIdicelist.add(BeanCopierUtils.copyProperties( locationMap.get(locationid), MtLocationInfoIce.class, true));
					}
				}
				rsp.setLocationInfoList(locationIdicelist);
			}
		} catch (Exception e) {
			logger.error("queryLocationInfoListByOrganId", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}
}
