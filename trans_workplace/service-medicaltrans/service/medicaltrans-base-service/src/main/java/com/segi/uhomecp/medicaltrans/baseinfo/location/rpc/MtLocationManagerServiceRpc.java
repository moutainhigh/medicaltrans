package com.segi.uhomecp.medicaltrans.baseinfo.location.rpc;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import resp.RpcRespIce;
import segi.datacachesvr.queryInfo.HouseDetail;
import segi.medicaltrans.base.location.LocationInfoListReturnIce;
import segi.medicaltrans.base.location.LocationInfoPaginatorIce;
import segi.medicaltrans.base.location.LocationListIce;
import segi.medicaltrans.base.location.MtBuildLocationDetailIce;
import segi.medicaltrans.base.location.MtBuildLocationDetailReturnIce;
import segi.medicaltrans.base.location.MtBuildLocationIceParam;
import segi.medicaltrans.base.location.MtBuildLocationQueryIceParam;
import segi.medicaltrans.base.location._MtLocationManagerServiceIceDisp;
import Ice.Current;
import cn.jpush.api.utils.StringUtils;
import page.RpcPageIce;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocation;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocationRel;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocationRelCriteria;
import com.segi.uhomecp.medicaltrans.base.location.service.MtBuildLocationRelService;
import com.segi.uhomecp.medicaltrans.base.location.service.MtBuildLocationService;
import com.segi.uhomecp.medicaltrans.baseinfo.location.dto.MtBuildLocationDto;
import com.segi.uhomecp.medicaltrans.baseinfo.location.service.MtBuildLocationManagerService;
import com.segi.uhomecp.medicaltrans.cache.MtLocationGrabRedisCache;
import com.segi.uhomecp.medicaltrans.utils.MtSpaceManageServiceUtils;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.PageUtils;

/**
 * 
 * Title: MtLocationManagerServiceRpc.java    
 * @Description: 位置管理
 * @author dengdong@segimail.com       
 * @created 2018年5月9日 下午4:48:55
 */
@Component
public class MtLocationManagerServiceRpc extends _MtLocationManagerServiceIceDisp {
	
	private static final long serialVersionUID = 1L;
	
	public static final Logger logger = LoggerFactory.getLogger(MtLocationManagerServiceRpc.class);
	
	@Autowired
	MtBuildLocationManagerService mtBuildLocationManagerService;
	
	@Autowired
	MtBuildLocationService mtBuildLocationService;
	
	@Autowired
	MtBuildLocationRelService mtBuildLocationRelService;
	
	@Autowired
	private MtLocationGrabRedisCache mtLocationGrabRedisCache;

	/**
	 * @discription 位置新增
	 * @author dengdong@segimail.com       
	 * @created 2018年5月9日 下午4:49:33      
	 * @param mtBuildLocationIceParam
	 * @param __current
	 * @return     
	 * @see segi.medicaltrans.base.location._MtLocationManagerServiceIceOperations#saveMtBuildLocation(segi.medicaltrans.base.location.MtBuildLocationIceParam, Ice.Current)
	 */
	@Override
	public RpcRespIce saveMtBuildLocation(MtBuildLocationIceParam mtBuildLocationIceParam, Current __current) {
		
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			MtBuildLocationDto params = BeanCopierUtils.copyProperties(mtBuildLocationIceParam,
					MtBuildLocationDto.class, true);
			ResultInfo resultInfo = mtBuildLocationManagerService.saveMtBuildLocation(params);
			if (resultInfo.getIsSucc()) {
				rsp.setData(String.valueOf(resultInfo.getObject1()));
			} else {
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage(resultInfo.getMessage());
			}
		} catch (Exception e) {
			logger.error("saveMtBuildLocation", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		
		return rsp;
	}

	/**
	 * @discription 查询位置详情
	 * @author dengdong@segimail.com       
	 * @created 2018年5月9日 下午5:13:43      
	 * @param locationId
	 * @param __current
	 * @return     
	 * @see segi.medicaltrans.base.location._MtLocationManagerServiceIceOperations#queryMtBuildLocationDetail(java.lang.String, Ice.Current)
	 */
	@Override
	public MtBuildLocationDetailReturnIce queryMtBuildLocationDetail(String locationId, Current __current) {
		MtBuildLocationDetailReturnIce rsp = new MtBuildLocationDetailReturnIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),new MtBuildLocationDetailIce());
		try {
			MtBuildLocation mtBuildLocation = mtBuildLocationService.selectByPrimaryKey(Integer.valueOf(locationId));
			if(mtBuildLocation!=null){
				if(!Constant.STATUS_CD_NORMAL.equals(mtBuildLocation.getStatus())){
					rsp.setCode(RpcError.FAIL.getCode());
					rsp.setMessage("该位置信息已失效");
					return rsp;
				}
				MtBuildLocationDetailIce mtBuildLocationDetailIce = BeanCopierUtils.copyProperties(mtBuildLocation,MtBuildLocationDetailIce.class,true);
				//去掉X、Y坐标的小数
				/*if(mtBuildLocationDetailIce!=null && mtBuildLocationDetailIce.getFloorPositX()!=null){
					String stringx = mtBuildLocationDetailIce.getFloorPositX().substring(0, mtBuildLocationDetailIce.getFloorPositX().indexOf("."));
					mtBuildLocationDetailIce.setFloorPositX(stringx);
				}
				if(mtBuildLocationDetailIce!=null && mtBuildLocationDetailIce.getFloorPositY()!=null){
					String stringy = mtBuildLocationDetailIce.getFloorPositY().substring(0, mtBuildLocationDetailIce.getFloorPositY().indexOf("."));
					mtBuildLocationDetailIce.setFloorPositY(stringy);
				}*/
				//通过位置查出相关物理空间
				MtBuildLocationRelCriteria example = new MtBuildLocationRelCriteria();
				MtBuildLocationRelCriteria.Criteria criteria = example.createCriteria();
				criteria.andLocationIdEqualTo(mtBuildLocation.getLocationId());
				List<MtBuildLocationRel> locationRelList = mtBuildLocationRelService.selectByExample(example);
				if(AppUtils.isNotEmpty(locationRelList)){
					StringBuffer houseSpaces= new StringBuffer();
					StringBuffer houseSpacesNames= new StringBuffer();
					for(MtBuildLocationRel rel:locationRelList){
						//根据房屋id查房屋信息
						if(rel!=null && rel.getHouseId()!=null){
							HouseDetail houseDetail = MtSpaceManageServiceUtils.queryHouseDetailByID(rel.getHouseId());
							if(houseDetail!=null && houseDetail.getHouseName()!=null){
								houseSpaces.append(houseDetail.getHouseId()).append(",");
								houseSpacesNames.append(houseDetail.getHouseName()).append(",");
							}
						}
					}
					if(StringUtils.isNotEmpty(houseSpaces.toString())){
						String result = houseSpaces.toString().substring(0,houseSpaces.toString().length()-1);
						mtBuildLocationDetailIce.setHouseSpaces(result);
					}
					if(StringUtils.isNotEmpty(houseSpacesNames.toString())){
						String result2 = houseSpacesNames.toString().substring(0,houseSpacesNames.toString().length()-1);
						mtBuildLocationDetailIce.setHouseSpacesNames(result2);
					}
					
				}
				rsp.setMtBuildLocationDetailIce(mtBuildLocationDetailIce);
			}else{
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("没有该位置信息");
			}
		} catch (NumberFormatException e) {
			logger.error("MtBuildLocationDetailReturnIce", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}

	/**
	 * @discription 编辑位置详情
	 * @author dengdong@segimail.com       
	 * @created 2018年5月9日 下午5:13:43      
	 * @param locationId
	 * @param __current
	 * @return     
	 * @see segi.medicaltrans.base.location._MtLocationManagerServiceIceOperations#queryMtBuildLocationDetail(java.lang.String, Ice.Current)
	 */
	@Override
	public RpcRespIce updateMtBuildLocation(MtBuildLocationIceParam mtBuildLocationIceParam, Current __current) {
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			MtBuildLocationDto params = BeanCopierUtils.copyProperties(mtBuildLocationIceParam,
					MtBuildLocationDto.class, true);
			ResultInfo resultInfo = mtBuildLocationManagerService.updateMtBuildLocation(params);
			if (resultInfo.getIsSucc()) {
				rsp.setData(String.valueOf(resultInfo.getObject1()));
			} else {
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage(resultInfo.getMessage());
			}
		} catch (Exception e) {
			logger.error("updateMtBuildLocation", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}

	/**
	 * @discription 删除位置详情
	 * @author dengdong@segimail.com       
	 * @created 2018年5月9日 下午5:13:43      
	 * @param locationId
	 * @param __current
	 * @return     
	 * @see segi.medicaltrans.base.location._MtLocationManagerServiceIceOperations#queryMtBuildLocationDetail(java.lang.String, Ice.Current)
	 */
	@Override
	public RpcRespIce deleteMtBuildLocation(MtBuildLocationIceParam mtBuildLocationIceParam, Current __current) {
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			MtBuildLocationDto params = BeanCopierUtils.copyProperties(mtBuildLocationIceParam,
					MtBuildLocationDto.class, true);
			ResultInfo resultInfo = mtBuildLocationManagerService.deleteMtBuildLocation(params);
			if (resultInfo.getIsSucc()) {
				rsp.setData(String.valueOf(resultInfo.getObject1()));
			} else {
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage(resultInfo.getMessage());
			}
		} catch (Exception e) {
			logger.error("deleteMtBuildLocation", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}

	/**
	 * @discription 根据楼层展示位置信息
	 * @author dengdong@segimail.com       
	 * @created 2018年5月9日 下午5:13:43      
	 * @param locationId
	 * @param __current
	 * @return     
	 * @see segi.medicaltrans.base.location._MtLocationManagerServiceIceOperations#queryMtBuildLocationDetail(java.lang.String, Ice.Current)
	 */
	@Override
	public LocationInfoPaginatorIce queryLocationPageByFloorId(MtBuildLocationIceParam mtBuildLocationIceParam,
			Current __current) {
		LocationInfoPaginatorIce rsp = new LocationInfoPaginatorIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new RpcPageIce(), new ArrayList<LocationListIce>());
		try {
			MtBuildLocationDto params = BeanCopierUtils.copyProperties(mtBuildLocationIceParam,
					MtBuildLocationDto.class, true);
			PageList<MtBuildLocation> locationPageList = mtBuildLocationManagerService.queryLocationPageByFloorId(params);
			if(AppUtils.isNotEmpty(locationPageList)){
				List<LocationListIce> locationInfoList = new ArrayList<>();
				LocationListIce locationListIce = null;
				for(MtBuildLocation mtBuildLocation:locationPageList){
					locationListIce = BeanCopierUtils.copyProperties(mtBuildLocation, LocationListIce.class, true);
					locationInfoList.add(locationListIce);
				}
				rsp.setPaginator(PageUtils.paginator2RpcPageIce(locationPageList.getPaginator()));
				rsp.setResultList(locationInfoList);
			}else{
				rsp.setMessage("没有位置信息");
			}
		} catch (Exception e) {
			logger.error("queryLocationPageByFloorId", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}
		
	/**
	 * 列表展示位置信息
	 */
	@Override
	public LocationInfoListReturnIce queryLocationListByFloorId(MtBuildLocationIceParam mtBuildLocationIceParam,
			Current __current) {
		LocationInfoListReturnIce rsp = new LocationInfoListReturnIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new ArrayList<LocationListIce>());
		try{
			MtBuildLocationDto params = BeanCopierUtils.copyProperties(mtBuildLocationIceParam,
					MtBuildLocationDto.class, true);
			List<MtBuildLocation> locationList = mtBuildLocationManagerService.queryLocationListByFloorId(params);
			if(AppUtils.isNotEmpty(locationList)){
				List<LocationListIce> locationInfoList = new ArrayList<>();
				LocationListIce locationListIce = null;
				for(MtBuildLocation mtBuildLocation:locationList){
					locationListIce = BeanCopierUtils.copyProperties(mtBuildLocation, LocationListIce.class, true);
					locationInfoList.add(locationListIce);
				}
				rsp.setResultList(locationInfoList);
			}else{
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("该楼层下没有位置信息");
			}
		} catch (Exception e) {
			logger.error("queryLocationListByFloorId", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}

	/**
	 * 位置信息分页展示（组织机构，名称）带默认
	 */
	@Override
	public LocationInfoPaginatorIce queryLocationPageByOrgNameDefault(MtBuildLocationIceParam mtBuildLocationIceParam,
			Current __current) {
		LocationInfoPaginatorIce rsp = new LocationInfoPaginatorIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new RpcPageIce(), new ArrayList<LocationListIce>());
		try {
			MtBuildLocationDto params = BeanCopierUtils.copyProperties(mtBuildLocationIceParam,
					MtBuildLocationDto.class, true);
			PageList<MtBuildLocation> locationPageList = mtBuildLocationManagerService.queryLocationPageByOrgNameDefault(params);
			if(AppUtils.isNotEmpty(locationPageList)){
				List<LocationListIce> locationInfoList = new ArrayList<>();
				LocationListIce locationListIce = null;
				for(MtBuildLocation mtBuildLocation:locationPageList){
					locationListIce = BeanCopierUtils.copyProperties(mtBuildLocation, LocationListIce.class, true);
					locationInfoList.add(locationListIce);
				}
				rsp.setPaginator(PageUtils.paginator2RpcPageIce(locationPageList.getPaginator()));
				rsp.setResultList(locationInfoList);
			}else{
				rsp.setMessage("没有位置信息");
			}
		} catch (Exception e) {
			logger.error("queryLocationPageByOrgNameDefault", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}

	/**
	 * 根据项目id和mac地址查位置详情
	 */
	@Override
	public MtBuildLocationDetailReturnIce getLocationInfoByMac(MtBuildLocationIceParam mtBuildLocationIceParam,
			Current __current) {
		MtBuildLocationDetailReturnIce rsp = new MtBuildLocationDetailReturnIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),new MtBuildLocationDetailIce());
		try {
			MtBuildLocation mtBuildLocation = mtBuildLocationManagerService.getLocationInfoByMac(Integer.valueOf(mtBuildLocationIceParam.getOrganId()),mtBuildLocationIceParam.getMac());
			
			if(mtBuildLocation!=null){
				if(!Constant.STATUS_CD_NORMAL.equals(mtBuildLocation.getStatus())){
					rsp.setCode(RpcError.FAIL.getCode());
					rsp.setMessage("该位置信息已失效");
					return rsp;
				}
				MtBuildLocationDetailIce mtBuildLocationDetailIce = BeanCopierUtils.copyProperties(mtBuildLocation,MtBuildLocationDetailIce.class,true);
				//去掉X、Y坐标的小数
				if(mtBuildLocationDetailIce!=null && mtBuildLocationDetailIce.getFloorPositX()!=null){
					String stringx = mtBuildLocationDetailIce.getFloorPositX().substring(0, mtBuildLocationDetailIce.getFloorPositX().indexOf("."));
					mtBuildLocationDetailIce.setFloorPositX(stringx);
				}
				if(mtBuildLocationDetailIce!=null && mtBuildLocationDetailIce.getFloorPositY()!=null){
					String stringy = mtBuildLocationDetailIce.getFloorPositY().substring(0, mtBuildLocationDetailIce.getFloorPositY().indexOf("."));
					mtBuildLocationDetailIce.setFloorPositY(stringy);
				}
				//通过位置查出相关物理空间
				MtBuildLocationRelCriteria example = new MtBuildLocationRelCriteria();
				MtBuildLocationRelCriteria.Criteria criteria = example.createCriteria();
				criteria.andLocationIdEqualTo(mtBuildLocation.getLocationId());
				List<MtBuildLocationRel> locationRelList = mtBuildLocationRelService.selectByExample(example);
				if(AppUtils.isNotEmpty(locationRelList)){
					StringBuffer houseSpaces= new StringBuffer();
					StringBuffer houseSpacesNames= new StringBuffer();
					for(MtBuildLocationRel rel:locationRelList){
						//根据房屋id查房屋信息
						if(rel!=null && rel.getHouseId()!=null){
							HouseDetail houseDetail = MtSpaceManageServiceUtils.queryHouseDetailByID(rel.getHouseId());
							if(houseDetail!=null && houseDetail.getHouseName()!=null){
								houseSpaces.append(houseDetail.getHouseId()).append(",");
								houseSpacesNames.append(houseDetail.getHouseName()).append(",");
							}
						}
					}
					if(StringUtils.isNotEmpty(houseSpaces.toString())){
						String result = houseSpaces.toString().substring(0,houseSpaces.toString().length()-1);
						mtBuildLocationDetailIce.setHouseSpaces(result);
					}
					if(StringUtils.isNotEmpty(houseSpacesNames.toString())){
						String result2 = houseSpacesNames.toString().substring(0,houseSpacesNames.toString().length()-1);
						mtBuildLocationDetailIce.setHouseSpacesNames(result2);
					}
					
				}
				rsp.setMtBuildLocationDetailIce(mtBuildLocationDetailIce);
			}else{
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage("没有该位置信息");
			}
		} catch (NumberFormatException e) {
			logger.error("MtBuildLocationDetailReturnIce", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}

	@Override
	public String queryLocationByOrganIdRedis(String organId, Current __current) {
		String result="";
		try{
			List<MtBuildLocation> MtBuildLocationList = mtLocationGrabRedisCache.getLocationByOrganRedis(Integer.valueOf(organId));
			result = FastjsonUtils.toJsonString(MtBuildLocationList);
		}catch (NumberFormatException e) {
			logger.error("queryLocationByOrganIdRedis", e);
		}
		return result;
	}

	/**
	 * 根据组织机构、位置名称、状态加载
	 */
	@Override
	public LocationInfoPaginatorIce queryLocationPageByOrgName(MtBuildLocationQueryIceParam mtBuildLocationQueryIceParam,
			Current __current) {
		LocationInfoPaginatorIce rsp = new LocationInfoPaginatorIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new RpcPageIce(), new ArrayList<LocationListIce>());
		try {
			MtBuildLocationDto params = BeanCopierUtils.copyProperties(mtBuildLocationQueryIceParam,
					MtBuildLocationDto.class, true);
			PageList<MtBuildLocation> locationPageList = mtBuildLocationManagerService.queryLocationPageByOrgName(params);
			
			if(AppUtils.isNotEmpty(locationPageList)){
				List<LocationListIce> locationInfoList = new ArrayList<>();
				LocationListIce locationListIce = null;
				for(MtBuildLocation mtBuildLocation:locationPageList){
					locationListIce = BeanCopierUtils.copyProperties(mtBuildLocation, LocationListIce.class, true);
					locationInfoList.add(locationListIce);
				}
				rsp.setPaginator(PageUtils.paginator2RpcPageIce(locationPageList.getPaginator()));
				rsp.setResultList(locationInfoList);
			}else{
				rsp.setMessage("没有位置信息");
			}
		} catch (Exception e) {
			logger.error("queryLocationPageByFloorId", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}
}
