package com.segi.uhomecp.medicaltrans.baseinfo.location.rpc;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import segi.datacachesvr.queryInfo.BuildDetail;
import segi.datacachesvr.queryInfo.BuildDetailInfo;
import segi.datacachesvr.queryInfo.BuildInfoV2;
import segi.datacachesvr.queryInfo.FloorBriefInfo;
import segi.datacachesvr.queryInfo.FloorDetailInfo;
import segi.datacachesvr.queryInfo.HouseBriefInfo;
import segi.datacachesvr.queryInfo.PositionV2;
import segi.datacachesvr.queryInfo.UnitDetailInfo;
import segi.medicaltrans.base.spacemanage.BuildDetailIce;
import segi.medicaltrans.base.spacemanage.BuildDetailReturnIce;
import segi.medicaltrans.base.spacemanage.BuildSimpleIce;
import segi.medicaltrans.base.spacemanage.BuildSimpleRetIce;
import segi.medicaltrans.base.spacemanage.BuildSimpleReturnIce;
import segi.medicaltrans.base.spacemanage.FloorDetailIce;
import segi.medicaltrans.base.spacemanage.FloorDetailReturnIce;
import segi.medicaltrans.base.spacemanage.FloorSimpleIce;
import segi.medicaltrans.base.spacemanage.FloorSimpleReturnIce;
import segi.medicaltrans.base.spacemanage.HouseInfoIce;
import segi.medicaltrans.base.spacemanage.HouseInfoReturnIce;
import segi.medicaltrans.base.spacemanage.PositionIce;
import segi.medicaltrans.base.spacemanage.PositionRetIce;
import segi.medicaltrans.base.spacemanage.UnitDetailIce;
import segi.medicaltrans.base.spacemanage.UnitDetailReturnIce;
import segi.medicaltrans.base.spacemanage.UnitSimpleIce;
import segi.medicaltrans.base.spacemanage.UnitSimpleReturnIce;
import segi.medicaltrans.base.spacemanage._SpaceManageServiceIceDisp;
import Ice.Current;

import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.utils.MtSpaceManageServiceUtils;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;

/**
 * @ClassName:  MtSpaceManageRpc   
 * @Description:空间位置管理高速查询Rpc   
 * @author: zhaoqing
 * @date:   2018年5月22日 下午4:32:37
 */
@Component
public class MtSpaceManageRpc extends _SpaceManageServiceIceDisp {

	private static final long serialVersionUID = -7405029116800607803L;
	
	private static final Logger logger = LoggerFactory.getLogger(MtSpaceManageRpc.class);

	/**
	 * <p>Title: queryBuildByCommID</p>   
	 * <p>Description: 根据项目ID查询楼栋列表 </p> 
	 * <p>zhaoqing</p>
	 * @param communityId
	 * @param __current
	 * @return  
	 */
	@Override
	public BuildSimpleRetIce queryBuildByCommID(String communityId,
			Current __current) {
		BuildSimpleRetIce rsp = new BuildSimpleRetIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), null);
		try {
			
			BuildInfoV2[]	buildInfos = MtSpaceManageServiceUtils
					.queryBuildByCommID(Integer.valueOf(communityId));
			if (null != buildInfos && buildInfos.length > 0) {
				List<BuildSimpleIce> resultList = new ArrayList<>();
				for (BuildInfoV2 buildInfo : buildInfos) {
					if (null != buildInfo) {
						resultList.add(BeanCopierUtils.copyProperties(
								buildInfo, BuildSimpleIce.class, true));
					}
				}
				rsp.setResultList(resultList);
			}
		} catch (Exception e) {
			logger.error("根据项目ID查询楼栋列表异常", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("根据项目ID查询楼栋列表失败！");
		}
		return rsp;
	}

	/**
	 * <p>Title: getBuildSimpleInfo</p>   
	 * <p>Description: 通过楼栋ID查询楼栋简单信息</p> 
	 * <p>zhaoqing</p>
	 * @param buildId
	 * @param __current
	 * @return
	 */
	@Override
	public BuildSimpleReturnIce getBuildSimpleInfo(String buildId,
			Current __current) {
		BuildSimpleReturnIce rsp = new BuildSimpleReturnIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), null);
		try {
			BuildDetail	buildDetail = MtSpaceManageServiceUtils
					.queryBuildByID(Integer.valueOf(buildId));
			if (null != buildDetail) {
				rsp.setData(BeanCopierUtils.copyProperties(
						buildDetail, BuildSimpleIce.class, true));
			}	
		} catch (Exception e) {
			logger.error("通过楼栋ID查询楼栋简单信息异常", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("通过楼栋ID查询楼栋简单信息失败！");
		}
		return rsp;
	}

	/**
	 * <p>Title: getUnitSimpleInfo</p>   
	 * <p>Description: 根据单元ID查询单元简单信息</p> 
	 * <p>zhaoqing</p>
	 * @param unitId
	 * @param __current
	 * @return   
	 */
	@Override
	public UnitSimpleReturnIce getUnitSimpleInfo(String unitId,
			Current __current) {
		UnitSimpleReturnIce rsp = new UnitSimpleReturnIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), null);
		try {
			UnitDetailInfo unitDetailInfo = MtSpaceManageServiceUtils
					.queryUnitDetailByID(Integer.valueOf(unitId));
			if (null != unitDetailInfo) {
				rsp.setData(BeanCopierUtils.copyProperties(
						unitDetailInfo, UnitSimpleIce.class, true));
			}	
		} catch (Exception e) {
			logger.error("根据单元ID查询单元简单信息异常", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("根据单元ID查询单元简单信息失败！");
		}
		return rsp;
	}

	/**
	 * <p>Title: getFloorInfoByFloorId</p>   
	 * <p>Description: 通过楼层ID查询楼层简单信息</p> 
	 * <p>zhaoqing</p>
	 * @param floorId
	 * @param __current
	 * @return   
	 */
	@Override
	public FloorSimpleReturnIce getFloorInfoByFloorId(String floorId,
			Current __current) {
		FloorSimpleReturnIce rsp = new FloorSimpleReturnIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), null);
		try {
			FloorBriefInfo floorBriefInfo = MtSpaceManageServiceUtils
					.queryFloorBriefByID(Integer.valueOf(floorId));
			if (null != floorBriefInfo) {
				rsp.setData(BeanCopierUtils.copyProperties(
						floorBriefInfo, FloorSimpleIce.class, true));
			}	
		} catch (Exception e) {
			logger.error("通过楼层ID查询楼层简单信息异常", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("通过楼层ID查询楼层简单信息失败！");
		}
		return rsp;
	}

	/**
	 * <p>Title: queryPositionListByParId</p>   
	 * <p>Description: 通过空间/位置ID查询下一节点信息</p> 
	 * <p>zhaoqing</p>
	 * @param communityId
	 * @param parId
	 * @param type
	 * @param __current
	 * @return   
	 */
	@Override
	public PositionRetIce queryPositionListByParId(String communityId, 
			String parId, String type, Current __current) {
		PositionRetIce rsp = new PositionRetIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), null);
		try {
			PositionV2[] positionV2s = MtSpaceManageServiceUtils.queryPositionListByParId(
					Integer.valueOf(communityId), Integer.valueOf(parId), Integer.valueOf(type));
			if (null != positionV2s && positionV2s.length > 0) {
				List<PositionIce> resultList = new ArrayList<>();
				for (PositionV2 positionV2 : positionV2s) {
					if (null != positionV2) {
						PositionIce positionIce = BeanCopierUtils.copyProperties(
								positionV2, PositionIce.class, true);		
						resultList.add(positionIce);
					}
				}	
				rsp.setResultList(resultList);
			}
		} catch (Exception e) {
			logger.error("通过空间/位置ID查询下一节点信息异常", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("通过空间/位置ID查询下一节点信息失败！");
		}
		return rsp;
	}
	
	/**
	 * <p>Title: getBuildDetail</p>   
	 * <p>Description: 通过楼栋ID查询楼栋详情</p> 
	 * <p>zhaoqing</p>
	 * @param buildId
	 * @param __current
	 * @return   
	 */
	@Override
	public BuildDetailReturnIce getBuildDetail(String buildId, Current __current) {
		BuildDetailReturnIce rsp = new BuildDetailReturnIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), null);
		try {
			BuildDetailInfo	buildDetailInfo = MtSpaceManageServiceUtils
					.queryBuildDetailByID(Integer.valueOf(buildId));
			if (null != buildDetailInfo) {
				rsp.setData(BeanCopierUtils.copyProperties(
						buildDetailInfo, BuildDetailIce.class, true));
			}	
		} catch (Exception e) {
			logger.error("通过楼栋ID查询楼栋详情异常", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("通过楼栋ID查询楼栋详情失败！");
		}
		return rsp;
	}

	/**
	 * <p>Title: getUnitDetail</p>   
	 * <p>Description: 根据单元ID查询单元详情</p> 
	 * <p>zhaoqing</p>
	 * @param unitId
	 * @param __current
	 * @return   
	 */
	@Override
	public UnitDetailReturnIce getUnitDetail(String unitId, Current __current) {
		UnitDetailReturnIce rsp = new UnitDetailReturnIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), null);
		try {
			UnitDetailInfo unitDetailInfo = MtSpaceManageServiceUtils
					.queryUnitDetailByID(Integer.valueOf(unitId));
			if (null != unitDetailInfo) {
				rsp.setData(BeanCopierUtils.copyProperties(
						unitDetailInfo, UnitDetailIce.class, true));
			}	
		} catch (Exception e) {
			logger.error("根据单元ID查询单元详情异常", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("根据单元ID查询单元详情失败！");
		}
		return rsp;
	}

	/**
	 * <p>Title: getFloorDetail</p>   
	 * <p>Description: 通过楼层ID查询楼层详情</p> 
	 * <p>zhaoqing</p>
	 * @param floorId
	 * @param __current
	 * @return   
	 */
	@Override
	public FloorDetailReturnIce getFloorDetail(String floorId, Current __current) {
		FloorDetailReturnIce rsp = new FloorDetailReturnIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), null);
		try {
			FloorDetailInfo floorDetailInfo = MtSpaceManageServiceUtils
					.queryFloorDetailByID(Integer.valueOf(floorId));
			if (null != floorDetailInfo) {
				FloorDetailIce floorDetailIce = BeanCopierUtils.copyProperties(
						floorDetailInfo, FloorDetailIce.class, true);
				// 设置楼层类型
				floorDetailIce.setFloorType(floorDetailInfo.getSortNo() < 0 
						? MtConstant.FLOOR_TYPE_UNDER_NAME : MtConstant.FLOOR_TYPE_UP_NAME);
				rsp.setData(floorDetailIce);
			}	
		} catch (Exception e) {
			logger.error("通过楼层ID查询楼层详情异常", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("通过楼层ID查询楼层详情失败！");
		}
		return rsp;
	}

	/**
	 * <p>Title: getHouseAll</p>   
	 * <p>Description: 通过楼层ID获取所有物理房间信息</p> 
	 * <p>zhaoqing</p>
	 * @param houseInfoIce
	 * @param __current
	 * @return   
	 */
	@Override
	public HouseInfoReturnIce getHouseAll(HouseInfoIce houseInfoIce,
			Current __current) {
		HouseInfoReturnIce rsp = new HouseInfoReturnIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), null);
		try {
			HouseBriefInfo[] houseBriefInfos = MtSpaceManageServiceUtils
					.queryHouseBriefByFloorId(Integer.valueOf(houseInfoIce.getFloorId()));
			if (null != houseBriefInfos && houseBriefInfos.length > 0) {
				List<HouseInfoIce> resultList = new ArrayList<>();
				for (HouseBriefInfo houseBriefInfo : houseBriefInfos) {
					if (null != houseBriefInfo) {
						resultList.add(BeanCopierUtils.copyProperties(
								houseBriefInfo, HouseInfoIce.class, true));
					}				
				}
				rsp.setResultList(resultList);
			}
		} catch (Exception e) {
			logger.error("通过楼层ID获取所有物理房间信息异常", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("通过楼层ID获取所有物理房间信息失败！");
		}
		return rsp;
	}
	
	/**
	 * <p>Title: queryPositionListByParIdInd</p>   
	 * <p>Description: 通过空间/位置ID查询下一节点信息(个性化)</p> 
	 * <p>zhaoqing</p>
	 * @param communityId
	 * @param parId
	 * @param type
	 * @param __current
	 * @return   
	 */
	@Override
	public PositionRetIce queryPositionListByParIdInd(String communityId,
			String parId, String type, Current __current) {
		PositionRetIce rsp = new PositionRetIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), null);
		try {
			PositionV2[] positionV2s = MtSpaceManageServiceUtils
					.queryPositionListByParId(Integer.valueOf(communityId),
							Integer.valueOf(parId), Integer.valueOf(type));
			if (null != positionV2s && positionV2s.length > 0) {
				List<PositionIce> resultList = new ArrayList<>();					
				for (PositionV2 positionV2 : positionV2s) {
					if (null != positionV2) {
						// 处理楼栋下的单元信息
						handleUnitInfoOfBuild(positionV2, resultList);
					}
				}	
				rsp.setResultList(resultList);
			}
		} catch (Exception e) {
			logger.error("通过空间/位置ID查询下一节点信息异常", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("通过空间/位置ID查询下一节点信息失败！");
		}
		return rsp;
	}
	
	/**
	 * @Title: handleUnitInfoOfBuild   
	 *  处理楼栋下的单元信息(楼栋下有单元时，位置信息)
	 * @author zhaoqing  
	 * @param positionV2
	 * @param resultList  
	 */
	private void handleUnitInfoOfBuild(PositionV2 positionV2, List<PositionIce> resultList) {
		// 楼栋属性转换
		PositionIce positionIce = BeanCopierUtils.copyProperties(positionV2, PositionIce.class, true);
		if (MtConstant.POSITION_TYPE_ONE == positionV2.getPositionType()
				&& MtConstant.SUB_POSITION_TYPE_ZERO == positionV2.getSubPositionType()) {
			// 当前位置类型为楼栋时，要查询其子位置信息，子位置类型为单元时，位置信息名称要以“楼栋_单元”的形式展示
			PositionV2[] positionV2sTemp = MtSpaceManageServiceUtils
					.queryPositionListByParId(positionV2.getCommunityId(),
							positionV2.getPositionId(), positionV2.getPositionType());
			if (null != positionV2sTemp && positionV2sTemp.length > 0) {
				// 楼栋有下级节点
				int unitCount = 0;
				for (PositionV2 positionV22 : positionV2sTemp) {
					if (null != positionV22 && MtConstant.SUB_POSITION_TYPE_ONE 
							== positionV22.getSubPositionType()) {
						// 楼栋名称+单元名称组合
						positionV22.setPositionName(String.valueOf(new StringBuilder()
								.append(positionV2.getPositionName())
								.append(Constant.SPLIT_UNDER_LINE)
								.append(positionV22.getPositionName())));
						PositionIce positionIceTemp = BeanCopierUtils
								.copyProperties(positionV22, PositionIce.class, true);
						// 把当前位置Id设置到单元Id字段，前端会用到
						positionIceTemp.setUnitId(String.valueOf(positionV22.getPositionId()));
						resultList.add(positionIceTemp);
						unitCount++;
					} 
				}
				if (unitCount == 0) {
					// 楼栋有下级节点但没有单元		
					resultList.add(positionIce);
				}
			} else {
				// 楼栋没有下级节点	
				resultList.add(positionIce);
			}
		} else {		
			resultList.add(positionIce);
		}
	}

}
