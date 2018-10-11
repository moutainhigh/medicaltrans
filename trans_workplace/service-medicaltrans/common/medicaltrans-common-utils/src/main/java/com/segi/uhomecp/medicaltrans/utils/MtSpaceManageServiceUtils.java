package com.segi.uhomecp.medicaltrans.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import segi.datacachesvr.queryInfo.BuildDetail;
import segi.datacachesvr.queryInfo.BuildDetailInfo;
import segi.datacachesvr.queryInfo.BuildInfoV2;
import segi.datacachesvr.queryInfo.CCBuildIPrx;
import segi.datacachesvr.queryInfo.CCommunityIPrx;
import segi.datacachesvr.queryInfo.CHouseIPrx;
import segi.datacachesvr.queryInfo.COrginfoIPrx;
import segi.datacachesvr.queryInfo.FloorBriefInfo;
import segi.datacachesvr.queryInfo.FloorDetailInfo;
import segi.datacachesvr.queryInfo.HouseBriefInfo;
import segi.datacachesvr.queryInfo.HouseDetail;
import segi.datacachesvr.queryInfo.PositionV2;
import segi.datacachesvr.queryInfo.UnitDetailInfo;
import segi.datacachesvr.queryInfo.retBuildInfoV2List;
import segi.datacachesvr.queryInfo.retHouseBriefInfoList;
import IceExt.IceClientUtil;

import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.wh.common.utils.AppUtils;

/**
 * @ClassName:  MtSpaceManageServiceUtils   
 * @Description:空间位置管理高速查询工具类   
 * @author: zhaoqing
 * @date:   2018年5月22日 下午5:57:48
 */
public class MtSpaceManageServiceUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(MtSpaceManageServiceUtils.class);
	
	/**
	 * 组织机构路由用途的默认值
	 */
	public static final int NORGANID = 0;
	
	/**
	 * 请求渠道（1 物业服务；2 商业服务）物业服务类型
	 */
	public static final int CHANNEL = 1;
	
	/**
	 * 楼层、单元过滤位置授权，4是医疗运输
	 */
	public static final int PRIVTYPE = 4;
	
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
	 * @Title: getCCBuildIPrx   
	 *  楼栋单元信息公共服务ICE 
	 * @author zhaoqing  
	 * @return 
	 */
	private static CCBuildIPrx getCCBuildIPrx() {
		return IceClientUtil.getServicePrxByClass(CCBuildIPrx.class);
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
	 * @Title: getCOrginfoIPrx   
	 *  组织机构信息公共ICE服务
	 * @author zhaoqing  
	 * @return 
	 */
	private static COrginfoIPrx getCOrginfoIPrx() {
		return IceClientUtil.getServicePrxByClass(COrginfoIPrx.class);
	}
	
	/**
	 * @Title: queryBuildByCommID   
	 *  根据项目ID查询楼栋列表 
	 * @author zhaoqing  
	 * @param communityId 项目ID
	 * @param nOrganID 为登录用户所属的组织机构，db路由用，不作查询条件，暂时无值可以传0
	 * @param channel 请求渠道（1 物业服务；2 商业服务，目前统一传1）
	 * @return 
	 */
	public static BuildInfoV2[] queryBuildByCommID(int organId, int nOrganID, int channel) {
		int communityId = getCOrginfoIPrx().queryCommunityIdByOrganid(organId);
		logger.info("==================>根据项目ID查询楼栋列表: communityId:{}", communityId);
//		BuildInfo[] buildInfos = getCCBuildIPrx().queryBuildByCommID(communityId, nOrganID, channel);
		
		BuildInfoV2[] buildInfoV2List = null;
		retBuildInfoV2List retBuildInfoV2List = getCCBuildIPrx()
				.queryBuildInfoV2ListByCommID (communityId, NORGANID, CHANNEL);	
		if (null != retBuildInfoV2List) {
			buildInfoV2List = retBuildInfoV2List.getData();
		}
		logger.info("==================>根据项目ID查询楼栋列表: resultList:{}", 
				FastjsonUtils.toJsonString(buildInfoV2List));
		return buildInfoV2List;
	}
	
	/**
	 * @Title: queryBuildByCommID   
	 *  根据项目ID查询楼栋列表 
	 * @author zhaoqing  
	 * @param communityId 项目ID
	 * @return  
	 */
	public static BuildInfoV2[] queryBuildByCommID(int communityId){
		return queryBuildByCommID(communityId, NORGANID, CHANNEL);
	}
	
	/**
	 * @Title: queryBuildByID   
	 *  通过楼栋ID查询楼栋简单信息
	 * @author zhaoqing  
	 * @param nBuildID 楼栋ID
	 * @param nOrganID 为登录用户所属的组织机构，db路由用，不作查询条件，暂时无值可以传0
	 * @param channel 请求渠道（1 物业服务；2 商业服务，目前统一传1）
	 * @return  
	 */
	public static BuildDetail queryBuildByID(int nBuildID, int nOrganID, int channel){
		logger.info("==================>通过楼栋ID查询楼栋简单信息: buildId:{}", nBuildID);
		BuildDetail buildDetail = getCCBuildIPrx().queryBuildByID(nBuildID, nOrganID, channel);
		logger.info("==================>通过楼栋ID查询楼栋简单信息: data:{}",
				FastjsonUtils.toJsonString(buildDetail));
		return buildDetail;
	}

	/**
	 * @Title: queryBuildByID   
	 *  通过楼栋ID查询楼栋简单信息
	 * @author zhaoqing  
	 * @param buildID 楼栋ID
	 * @return  
	 */
	public static BuildDetail queryBuildByID(int buildID){
		return queryBuildByID(buildID, NORGANID, CHANNEL);
	}
	
	/**
	 * @Title: queryUnitDetailByID   
	 *  根据单元ID查询单元详细信息
	 * @author zhaoqing  
	 * @param unitId 单元ID
	 * @param nOrganID 为登录用户所属的组织机构，db路由用，不作查询条件，暂时无值可以传0
	 * @param channel 请求渠道（1 物业服务；2 商业服务，目前统一传1）
	 * @return  
	 */
	public static UnitDetailInfo queryUnitDetailByID(int unitId, int nOrganID, int channel){
		logger.info("==================>根据单元ID查询单元详细信息: unitId:{}", unitId);
		UnitDetailInfo unitDetailInfo = getCCBuildIPrx()
				.queryUnitDetailByID(unitId, nOrganID, channel);
		logger.info("==================>根据单元ID查询单元详细信息: data:{}",
				FastjsonUtils.toJsonString(unitDetailInfo));
		return unitDetailInfo;
	}

	/**
	 * @Title: queryUnitDetailByID   
	 *  根据单元ID查询单元详细信息
	 * @author zhaoqing  
	 * @param unitId 单元ID
	 * @return  
	 */
	public static UnitDetailInfo queryUnitDetailByID(int unitId){
		return queryUnitDetailByID(unitId, NORGANID, CHANNEL);
	}
	
	/**
	 * @Title: queryFloorBriefByID   
	 *  通过楼层ID查询楼层简单信息 
	 * @author zhaoqing  
	 * @param nFloorID 楼层ID
	 * @param nOrganID 为登录用户所属的组织机构，db路由用，不作查询条件，暂时无值可以传0
	 * @param channel 请求渠道（1 物业服务；2 商业服务，目前统一传1）
	 * @return 
	 */
	public static FloorBriefInfo queryFloorBriefByID(int nFloorID, int nOrganID, int channel){
		logger.info("==================>通过楼层ID查询楼层简单信息: floorID:{}", nFloorID);
		FloorBriefInfo floorBriefInfo = getCCBuildIPrx()
				.queryFloorBriefByID(nFloorID, nOrganID, channel);
		logger.info("==================>通过楼层ID查询楼层简单信息: data:{}",
				FastjsonUtils.toJsonString(floorBriefInfo));
		return floorBriefInfo;
	}
	
	/**
	 * @Title: queryFloorBriefByID   
	 *  通过楼层ID查询楼层简单信息 
	 * @author zhaoqing  
	 * @param floorID 楼层ID
	 * @return 
	 */
	public static FloorBriefInfo queryFloorBriefByID(int floorID){
		return queryFloorBriefByID(floorID, NORGANID, CHANNEL);
	}
	
	/**
	 * @Title: queryPositionListByParId   
	 *  通过空间/位置ID查询下一节点信息 
	 * @author zhaoqing  
	 * @param communityId 小区ID
	 * @param ParId 父级位置ID
	 * @param type 位置类型(1：建筑类，2：公共区域，3：停车场)
	 * @param nOrganID 为登录用户所属的组织机构，db路由用，不作查询条件，暂时无值可以传0
	 * @param channel 请求渠道（1 物业服务；2 商业服务，目前统一传1）
	 * @return
	 */
	/*public static PositionV2[] queryPositionListByParId(
			int communityId, int parId, int type,int privType, int nOrganID, int channel){
		logger.info("==================>通过空间/位置ID查询下一节点信息: "
				+ "communityId:{}, parentId:{}, type:{}", communityId, parId, type);
		PositionV2[] positionV2s = getCCommunityIPrx().queryPositionListByParIdV2(communityId, parId, type,privType,nOrganID, channel);
		//当查出的下一层是楼层的话，通过楼层详情的序列号排序
		if(positionV2s!=null && positionV2s.length>0 &&positionV2s[0].getPositionType()==1 &&positionV2s[0].getSubPositionType()==2){
			return getFloorSortByFloorId(positionV2s);
		}
		logger.info("==================>通过空间/位置ID查询下一节点信息: resultList:{}", 
				FastjsonUtils.toJsonString(positionV2s));
		return positionV2s;
	}*/
	
	/**
	 * @Title: queryPositionListByParId   
	 *  通过空间/位置ID查询下一节点信息 
	 * @author zhaoqing  
	 * @param communityId 小区ID
	 * @param ParId 父级位置ID
	 * @param type 位置类型(1：建筑类，2：公共区域，3：停车场)
	 * @param nOrganID 为登录用户所属的组织机构，db路由用，不作查询条件，暂时无值可以传0
	 * @param channel 请求渠道（1 物业服务；2 商业服务，目前统一传1）
	 * @return
	 */
	public static PositionV2[] queryPositionListByParId(
			int organId, int parId, int type,int privType, int nOrganID, int channel){
		int communityId = getCOrginfoIPrx().queryCommunityIdByOrganid(organId);
		logger.info("==================>通过空间/位置ID查询下一节点信息: "
				+ "communityId:{}, parentId:{}, type:{}", communityId, parId, type);
		PositionV2[] positionV2s = getCCommunityIPrx().queryPositionListByParIdV2(communityId, parId, type,privType,nOrganID, channel);
		//当查出的下一层是楼层的话，通过楼层详情的序列号排序
		//楼层的
		List<PositionV2> floorList = new ArrayList<PositionV2>();
		//单元的
		List<PositionV2> unitList = new ArrayList<PositionV2>();
		if(positionV2s!=null && positionV2s.length>0){
			for(int i=0 ;i<positionV2s.length;i++){
				if(positionV2s[i]!=null){
					if(positionV2s[i].getPositionType()==1 &&positionV2s[i].getSubPositionType()==2){
						floorList.add(positionV2s[i]);
					}else{
						unitList.add(positionV2s[i]);
					}
				}
			}
		}
		PositionV2[] PositionV2Flow = null;
		if(floorList!=null &&floorList.size()>0){
			PositionV2[] positionV2sSort = new PositionV2[floorList.size()];
			for(int i=0 ;i<floorList.size();i++){
				positionV2sSort[i]=floorList.get(i);
			}
			PositionV2Flow=getFloorSortByFloorId(positionV2sSort,organId);
		}
		PositionV2[] PositionV2Unit = null;
		if(unitList!=null &&unitList.size()>0){
			PositionV2Unit = new PositionV2[unitList.size()];
			for(int i=0 ;i<unitList.size();i++){
				PositionV2Unit[i]=unitList.get(i);
			}
		}
		
		PositionV2[] both = (PositionV2[]) ArrayUtils.addAll(PositionV2Flow, PositionV2Unit);
		
	/*	if(positionV2s!=null && positionV2s.length>0 &&positionV2s[0].getPositionType()==1 &&positionV2s[0].getSubPositionType()==2){
			return getFloorSortByFloorId(positionV2s);
		}*/
		logger.info("==================>通过空间/位置ID查询下一节点信息: resultList:{}", 
				FastjsonUtils.toJsonString(positionV2s));
		return both;
	}
	
	/**
	 * 将楼层根据楼层详细信息中的顺序号重新排序
	 * @return
	 */
	public static  PositionV2[] getFloorSortByFloorId(PositionV2[] positionV2s,int organId){
		if(positionV2s==null || positionV2s.length<=0){
			logger.error("楼层排序必填参数为空");
			return null;
		}
		List<FloorDetailInfo> floorDetailInfoList =new ArrayList<FloorDetailInfo>();
		PositionV2[] positionV2sNew =null;
		/*int communityId = positionV2s[0].getCommunityId();*/
		int upPositionId = positionV2s[0].getUpPositionId();
		for(PositionV2 positionV2:positionV2s){
			if(positionV2!=null){
				FloorDetailInfo floorDetailInfo = queryFloorDetailByID(positionV2.getPositionId());
				if(floorDetailInfo!=null){
					floorDetailInfoList.add(floorDetailInfo);	
				}
			}
		}
		if(AppUtils.isNotEmpty(floorDetailInfoList)){
			Collections.sort(floorDetailInfoList, new Comparator<FloorDetailInfo>() { 
				@Override  
	            public int compare(FloorDetailInfo floorDetailInfo1, FloorDetailInfo floorDetailInfo2) {  
					int i = floorDetailInfo1.getSortNo()-floorDetailInfo2.getSortNo();
					return i;
				}
			} );
			positionV2sNew = new PositionV2[floorDetailInfoList.size()];
			for(int i=0;i<positionV2sNew.length;i++){
				PositionV2 positionV2 = new PositionV2();
				FloorDetailInfo floorDetailInfo = floorDetailInfoList.get(i);
				positionV2.setCommunityId(organId);
				positionV2.setObjectId(floorDetailInfo.getFloorId());
				positionV2.setPositionId(floorDetailInfo.getFloorId());
				positionV2.setPositionName(floorDetailInfo.getFloorName());
				positionV2.setUpPositionId(upPositionId);
				// 类型在RPC层中会用到
				positionV2.setPositionType(MtConstant.POSITION_TYPE_ONE);
				positionV2.setSubPositionType(MtConstant.SUB_POSITION_TYPE_TWO);
				positionV2sNew[i] = positionV2;
			}
		}
		return positionV2sNew;
	}
	
	/**
	 * @Title: queryPositionListByParId   
	 *  通过空间/位置ID查询下一节点信息 
	 * @author zhaoqing  
	 * @param communityId 小区ID
	 * @param ParId 父级位置ID(传负1查的是项目下)
	 * @param type 位置类型(1：建筑类，2：公共区域，3：停车场)
	 * @return
	 */
	public static PositionV2[] queryPositionListByParId(int organId, int parId, int type){
		int communityId = getCOrginfoIPrx().queryCommunityIdByOrganid(organId);
		if(parId==-1){
			logger.info("==================>通过项目ID查询下面的楼栋信息: "
					+ "communityId:{}, parentId:{}, type:{}", communityId, parId, type);
			//return queryPositionListByCommuIdV2(communityId, NORGANID, CHANNEL);	
			BuildInfoV2[] buildInfoV2List = null;
			PositionV2[] positionV2s = null;
			retBuildInfoV2List retBuildInfoV2List = getCCBuildIPrx()
					.queryBuildInfoV2ListByCommID (communityId, NORGANID, CHANNEL);	
			if (null != retBuildInfoV2List && retBuildInfoV2List.getData() != null
					&& retBuildInfoV2List.getData().length > 0) {
				buildInfoV2List = retBuildInfoV2List.getData();	
				positionV2s =new PositionV2[buildInfoV2List.length];
				for(int i=0; i<positionV2s.length; i++){
					PositionV2 positionV2 = new PositionV2();
					BuildInfoV2 buildInfoV2 = buildInfoV2List[i]; 
					positionV2.setCommunityId(organId);
					positionV2.setPositionId(buildInfoV2.getBuildId());
					positionV2.setPositionName(buildInfoV2.getName());
					positionV2.setUpPositionId(organId);
					// 类型在RPC层中会用到
					positionV2.setPositionType(MtConstant.POSITION_TYPE_ONE);
					positionV2.setSubPositionType(MtConstant.SUB_POSITION_TYPE_ZERO);
					positionV2s[i] = positionV2;
				} 
			}
			logger.info("==================>通过项目ID查询下面的楼栋信息: resultList:{}", 
					FastjsonUtils.toJsonString(positionV2s));
			return positionV2s;
		}else{
			return queryPositionListByParId(organId, parId, type,PRIVTYPE,NORGANID, CHANNEL);
		}
	}
	
	/**
	 * @Title: queryPositionListByCommuIdV2   
	 *  根据实体项目ID查询位置信息
	 * @author dengdong  
	 * @param communityId 小区ID
	 * @param ParId 父级位置ID
	 * @param type 位置类型(1：建筑类，2：公共区域，3：停车场)
	 * @param nOrganID 为登录用户所属的组织机构，db路由用，不作查询条件，暂时无值可以传0
	 * @param channel 请求渠道（1 物业服务；2 商业服务，目前统一传1）
	 * @return
	 */
	/*public static PositionV2[] queryPositionListByCommuIdV2(
			int communityId, int nOrganID, int channel){
		logger.info("==================>根据实体项目ID查询位置信息: "
				+ "communityId:{}, type:{}", communityId);
		PositionV2[] positionV2s = getCCommunityIPrx()
				.queryPositionListByCommuIdV2(communityId, nOrganID, channel);
		logger.info("==================>根据实体项目ID查询位置信息: resultList:{}", 
				FastjsonUtils.toJsonString(positionV2s));
		return positionV2s;
	}*/
	
	/**
	 * @Title: queryBuildDetailByID   
	 *  通过楼栋ID获取楼栋详情 
	 * @author zhaoqing  
	 * @param nBuildID 楼栋ID
	 * @param nOrganID 为登录用户所属的组织机构，db路由用，不作查询条件，暂时无值可以传0
	 * @param channel 请求渠道（1 物业服务；2 商业服务，目前统一传1）
	 * @return 
	 */
	public static BuildDetailInfo queryBuildDetailByID(int nBuildID, int nOrganID, int channel){
		logger.info("==================>通过楼栋ID获取楼栋详情 : buildId:{}", nBuildID);
		BuildDetailInfo buildDetailInfo = getCCBuildIPrx()
				.queryBuildDetailByID(nBuildID, nOrganID, channel);
		logger.info("==================>通过楼栋ID获取楼栋详情 : data:{}",
				FastjsonUtils.toJsonString(buildDetailInfo));
		return buildDetailInfo;
	}

	/**
	 * @Title: queryBuildDetailByID   
	 *  通过楼栋ID获取楼栋详情 
	 * @author zhaoqing  
	 * @param nBuildID 楼栋ID
	 * @return 
	 */
	public static BuildDetailInfo queryBuildDetailByID(int nBuildID){
		return queryBuildDetailByID(nBuildID, NORGANID, CHANNEL);
	}
	
	/**
	 * @Title: queryFloorDetailByID   
	 *  通过楼层ID获取楼层详情 
	 * @author zhaoqing  
	 * @param nFloorID 楼层ID
	 * @param nOrganID 为登录用户所属的组织机构，db路由用，不作查询条件，暂时无值可以传0
	 * @param channel 请求渠道（1 物业服务；2 商业服务，目前统一传1）
	 * @return 
	 */
	public static FloorDetailInfo queryFloorDetailByID(int nFloorID, int nOrganID, int channel){
		logger.info("==================>通过楼栋ID获取楼栋详情 : floorID:{}", nFloorID);
		FloorDetailInfo floorDetailInfo = getCCBuildIPrx()
				.queryFloorDetailByID(nFloorID, nOrganID, channel);
		logger.info("==================>通过楼栋ID获取楼栋详情 : data:{}",
				FastjsonUtils.toJsonString(floorDetailInfo));
		return floorDetailInfo;
	}
	
	/**
	 * @Title: queryFloorDetailByID   
	 *  通过楼层ID获取楼层详情 
	 * @author zhaoqing  
	 * @param nFloorID 楼层ID
	 * @param nOrganID 为登录用户所属的组织机构，db路由用，不作查询条件，暂时无值可以传0
	 * @param channel 请求渠道（1 物业服务；2 商业服务，目前统一传1）
	 * @return 
	 */
	public static FloorDetailInfo queryFloorDetailByID(int nFloorID){
		return queryFloorDetailByID(nFloorID, NORGANID, CHANNEL);
	}
	
	/**
	 * @Title: queryHouseBriefByFloorId   
	 *  通过楼层ID获取所有物理房间
	 * @author zhaoqing  
	 * @param nFloorID 楼层ID
	 * @param nOrganID 为登录用户所属的组织机构，db路由用，不作查询条件，暂时无值可以传0
	 * @param channel 请求渠道（1 物业服务；2 商业服务，目前统一传1）
	 * @return
	 */
	public static HouseBriefInfo[] queryHouseBriefByFloorId(int nFloorID, int nOrganID, int channel){
		logger.info("==================>通过楼层ID获取所有物理房间: floorID:{}", nFloorID);
		HouseBriefInfo[] houseBriefInfos = null;
		retHouseBriefInfoList resp = getCHouseIPrx()
				.queryHouseBriefByFloorId(nFloorID, nOrganID, channel);
		if (null != resp) {
			houseBriefInfos = resp.getData();
		}
		logger.info("==================>通过楼层ID获取所有物理房间: resultList:{}", 
				FastjsonUtils.toJsonString(houseBriefInfos));
		return houseBriefInfos;
	}
	
	/**
	 * @Title: queryHouseBriefByFloorId   
	 *  通过楼层ID获取所有物理房间
	 * @author zhaoqing  
	 * @param nFloorID 楼层ID
	 * @return
	 */
	public static HouseBriefInfo[] queryHouseBriefByFloorId(int nFloorID){
		return queryHouseBriefByFloorId(nFloorID, NORGANID, CHANNEL);
	}
	
	/**
	 * @Title: queryHouseDetailByID   
	 *  根据房屋ID查询房屋信息 
	 * @author zhaoqing  
	 * @param nHouseID 房屋ID
	 * @param nOrganID 为登录用户所属的组织机构，db路由用，不作查询条件，暂时无值可以传0
	 * @param channel 请求渠道（1 物业服务；2 商业服务，目前统一传1）
	 * @return
	 */
	public static HouseDetail queryHouseDetailByID(int nHouseID, int nOrganID, int channel) {
		logger.info("==================>根据房屋ID查询房屋信息: nHouseID:{}", nHouseID);
		HouseDetail houseDetail = getCHouseIPrx().queryHouseDetailByID(nHouseID, nOrganID, channel);
		logger.info("==================>根据房屋ID查询房屋信息: data:{}", 
				FastjsonUtils.toJsonString(houseDetail));
		return houseDetail;
	}
	
	/**
	 * @Title: queryHouseDetailByID   
	 *  根据房屋ID查询房屋信息 
	 * @author zhaoqing  
	 * @param nHouseID 房屋ID
	 * @return
	 */
	public static HouseDetail queryHouseDetailByID(int nHouseID) {
		return queryHouseDetailByID(nHouseID, NORGANID, CHANNEL);
	}
	
}
