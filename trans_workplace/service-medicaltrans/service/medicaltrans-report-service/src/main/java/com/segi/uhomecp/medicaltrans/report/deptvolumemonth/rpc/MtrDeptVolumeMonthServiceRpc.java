package com.segi.uhomecp.medicaltrans.report.deptvolumemonth.rpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import segi.datacachesvr.queryInfo.BuildDetail;
import segi.datacachesvr.queryInfo.FloorBriefInfo;
import segi.datacachesvr.queryInfo.Organ;
import segi.datacachesvr.queryInfo.UnitDetailInfo;
import segi.medicaltrans.location.common.LocationInfoIce;
import segi.medicaltrans.location.common.LocationInfoListReturn;
import segi.medicaltrans.report.deptvolumemonth.DeptVolumeMonthIce;
import segi.medicaltrans.report.deptvolumemonth.DeptVolumeMonthPaginator;
import segi.medicaltrans.report.deptvolumemonth.DeptVolumeMonthParam;
import segi.medicaltrans.report.deptvolumemonth._MtrDeptVolumeMonthServiceIceDisp;
import Ice.Current;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.model.DeptVolumeMonth;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.model.DeptVolumeMonthCriteria;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.service.DeptVolumeMonthService;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.medicaltrans.utils.MtSpaceManageServiceUtils;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.web.PageUtils;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;

/**
 * MtrDeptVolumeMonthServiceRpc.java
 * @Description: 本月科室统计
 * @author liuyi@segimail.com 
 * @created 2018年7月23日下午2:35:44 
 */
@Component
public class MtrDeptVolumeMonthServiceRpc extends _MtrDeptVolumeMonthServiceIceDisp {

	/**
	 * 2018年7月23日下午2:32:56
	 */
	private static final long serialVersionUID = -8681668866567850897L;
	
	private static final Logger logger = LoggerFactory.getLogger(MtrDeptVolumeMonthServiceRpc.class);

	@Autowired
	private DeptVolumeMonthService deptVolumeMonthService;
	
	/**
	 * 
	 * @Title: getDeptVolumeMonthPage 
	 * @Description: 本月科室统计分页
	 * @author liuyi@segimail.com 
	 * @date 2018年7月23日下午2:39:45
	 */
	@Override
	public DeptVolumeMonthPaginator getDeptVolumeMonthPage(
			DeptVolumeMonthParam deptVolumeMonthParam, Current __current) {
		DeptVolumeMonthPaginator result = new DeptVolumeMonthPaginator();
		result.code = RpcError.SUCCESS.getCode();
		result.msg = RpcError.SUCCESS.getMessage();
		try {
			//创建查询模板 查询科室月运送量信息
			DeptVolumeMonthCriteria example = new DeptVolumeMonthCriteria();
			DeptVolumeMonthCriteria.Criteria criteria = example.createCriteria(); 
			criteria.andOrganIdEqualTo(Integer.valueOf(deptVolumeMonthParam.getOrganId()));
			criteria.andCycleYmEqualTo(Integer.valueOf(deptVolumeMonthParam.getCycleYm()));
			if (StringUtils.isNotBlank(deptVolumeMonthParam.getHouseId())) {
				criteria.andHouseIdEqualTo(Integer.valueOf(deptVolumeMonthParam.getHouseId()));
			}
			int pageNo = MtConstant.INIT_PAGE_NO;
			if (StringUtils.isNotEmpty(deptVolumeMonthParam.getPageNo())) {
				pageNo = Integer.parseInt(deptVolumeMonthParam.getPageNo());
			}
			int pageLength = MtConstant.DEFAULT_PAGE_LENGTH;
			if (StringUtils.isNotEmpty(deptVolumeMonthParam.getPageLength())) {
				pageLength = Integer.parseInt(deptVolumeMonthParam.getPageLength());
			}
			// 按照楼栋Id、单元Id、楼层Id、科室Id升序、运送量倒序排序
			example.setOrderByClause("BUILD_ID, SID, FLOOR_ID, HOUSE_ID");
			PageList<DeptVolumeMonth> deptVolumeMonthList = deptVolumeMonthService.selectByExampleWithRowbounds(
					example, PageUtils.getPageBoundsByPageNo(pageNo, pageLength));
			// 查询项目信息
			Organ organ = CommonServiceUtils.getOrganByID(Integer.valueOf(deptVolumeMonthParam.getOrganId()));
			String organName = null != organ ? organ.getOrganName() : "";
			// 分页信息设置值
			List<DeptVolumeMonthIce> deptVolumeMonthIceList = new ArrayList<DeptVolumeMonthIce>();
			if (AppUtils.isNotEmpty(deptVolumeMonthList)) {
				deptVolumeMonthIceList = BeanCopierUtils.copyList2List(deptVolumeMonthList, DeptVolumeMonthIce.class, true);
				// 查询楼栋名称
				Set<String> buildIdSet = AppUtils.list2ParamsSet(deptVolumeMonthIceList, (obj) -> obj.getBuildId());
				Map<Integer, BuildDetail> buildDetailMap = queryBuildDetailMapByFloorIdSet(buildIdSet);
				// 查询单元名称
				Set<String> sidSet = AppUtils.list2ParamsSet(deptVolumeMonthIceList, (obj) -> obj.getSid());
				Map<Integer, UnitDetailInfo> unitDetailMap = queryUnitDetailInfoMapByUnitIdSet(sidSet);
				// 查询楼层名称
				Set<String> floorIdSet = AppUtils.list2ParamsSet(deptVolumeMonthIceList, (obj) -> obj.getFloorId());
				Map<Integer, FloorBriefInfo> floorMap = queryFloorBriefInfoMapByFloorIdSet(floorIdSet);
				// 查询科室信息 
				List<Integer> houseIdList = AppUtils.list2ParamsList(deptVolumeMonthList, (obj) -> obj.getHouseId());
				Map<String, LocationInfoIce> houseInfoMap = queryHouseInfoList(houseIdList);
				for (DeptVolumeMonthIce deptVolumeMonthIce : deptVolumeMonthIceList) {
					if (StringUtils.isNotBlank(deptVolumeMonthIce.getBuildId()) && buildDetailMap.get(Integer.valueOf(deptVolumeMonthIce.getBuildId())) != null) {
						deptVolumeMonthIce.setBuildName(StringUtils.isNotBlank(buildDetailMap.get(Integer.valueOf(deptVolumeMonthIce.getBuildId())).getName())
								? buildDetailMap.get(Integer.valueOf(deptVolumeMonthIce.getBuildId())).getName() : "");
					}
					if (StringUtils.isNotBlank(deptVolumeMonthIce.getSid()) && unitDetailMap.get(Integer.valueOf(deptVolumeMonthIce.getSid())) != null) {
						deptVolumeMonthIce.setSidName(StringUtils.isNotBlank(unitDetailMap.get(Integer.valueOf(deptVolumeMonthIce.getSid())).getUnitName()) ?
								unitDetailMap.get(Integer.valueOf(deptVolumeMonthIce.getSid())).getUnitName() : "");
					}
					if (StringUtils.isNotBlank(deptVolumeMonthIce.getFloorId()) && floorMap.get(Integer.valueOf(deptVolumeMonthIce.getFloorId())) != null) {
						deptVolumeMonthIce.setFloorName(StringUtils.isNotBlank(floorMap.get(Integer.valueOf(deptVolumeMonthIce.getFloorId())).getFloorName()) ?
								floorMap.get(Integer.valueOf(deptVolumeMonthIce.getFloorId())).getFloorName() : "");
					}
					if (StringUtils.isNotBlank(deptVolumeMonthIce.getHouseId()) && houseInfoMap.get(deptVolumeMonthIce.getHouseId()) != null) {
						deptVolumeMonthIce.setHouseName(StringUtils.isNotBlank(houseInfoMap.get(deptVolumeMonthIce.getHouseId()).getLocationName()) ?
								houseInfoMap.get(deptVolumeMonthIce.getHouseId()).getLocationName() : "");
					}
					deptVolumeMonthIce.setOrganName(organName);
				}
			}
			result.setResultList(deptVolumeMonthIceList);
			result.setPaginator(com.segi.uhomecp.wh.common.utils
	        		 .PageUtils.paginator2RpcPageIce(deptVolumeMonthList.getPaginator()));
		} catch (Exception e) {
			logger.error("getDeptVolumeMonthPage", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMsg("各科室运送量月报表查询失败");
		}
		return result;
	}

	/**
	 * 
	 * @Title: queryFloorBriefInfoList 
	 * @Description:  查询楼栋信息
	 * @author liuyi@segimail.com 
	 * @date 2018年7月25日下午5:10:21
	 */
	private Map<Integer, BuildDetail> queryBuildDetailMapByFloorIdSet(Set<String> buildIdSet) {
		List<BuildDetail> buildDetailList = new ArrayList<BuildDetail>();
		BuildDetail buildDetail =  new BuildDetail();
		for (String buildId : buildIdSet) {
			try {
				buildDetail = MtSpaceManageServiceUtils.queryBuildByID(Integer.valueOf(buildId));
			} catch (Exception e) {
				logger.error("通过楼层ID查询楼层简单信息异常 buildId:--->" + buildId, e);
			}
			if (buildDetail != null) {
				buildDetailList.add(buildDetail);
			}
		}
		Map<Integer, BuildDetail> list2Map = AppUtils.list2Map(buildDetailList, "buildId", BuildDetail.class);
		return list2Map;
	}
	
	/**
	 * 
	 * @Title: queryUnitDetailInfoMapByUnitIdSet 
	 * @Description: 查询单元信息 
	 * @author liuyi@segimail.com 
	 * @date 2018年7月25日下午5:19:31
	 */
	private Map<Integer, UnitDetailInfo> queryUnitDetailInfoMapByUnitIdSet(Set<String> sidSet) {
		List<UnitDetailInfo> unitDetailList = new ArrayList<UnitDetailInfo>();
		UnitDetailInfo unitDetailInfo = new UnitDetailInfo();
		for (String sid : sidSet) {
			try {
				unitDetailInfo = MtSpaceManageServiceUtils.queryUnitDetailByID(Integer.valueOf(sid));
			} catch (Exception e) {
				logger.error("通过单元ID查询单元简单信息异常 sid:--->" + sid, e);
			}
			if (unitDetailInfo != null) {
				unitDetailList.add(unitDetailInfo);
			}
		}
		return AppUtils.list2Map(unitDetailList, "unitId", UnitDetailInfo.class);
	}
	
	/**
	 * 
	 * @Title: queryFloorBriefInfoMapByFloorIdSet 
	 * @Description: 查询楼层信息 
	 * @author liuyi@segimail.com 
	 * @date 2018年7月25日下午5:30:15
	 */
	private Map<Integer, FloorBriefInfo> queryFloorBriefInfoMapByFloorIdSet(Set<String> floorIdSet) {
		List<FloorBriefInfo> floorIdlList = new ArrayList<FloorBriefInfo>();
		FloorBriefInfo queryFloorBriefByID = new FloorBriefInfo();
		for (String floorId : floorIdSet) {
			try {
				queryFloorBriefByID = MtSpaceManageServiceUtils.queryFloorBriefByID(Integer.valueOf(floorId));
			} catch (Exception e) {
				logger.error("通过楼层ID查询楼层简单信息异常 floorId:--->" + floorId, e);
			}
			if (queryFloorBriefByID != null) {
				floorIdlList.add(queryFloorBriefByID);
			}
		}
		return AppUtils.list2Map(floorIdlList, "floorId", FloorBriefInfo.class);
	}
	
	/**
	 * 
	 * @Title: queryHouseInfoList 
	 * @Description: 查询科室信息  
	 * @author liuyi@segimail.com 
	 * @date 2018年7月27日下午3:18:58
	 */
	private Map<String, LocationInfoIce> queryHouseInfoList(List<Integer> houseIdList) {
		//每次查询5000条科室信息
		int listSize = houseIdList.size();
        int toIndex = MtConstant.MAX_QUERY_LENGTH;
        LocationInfoListReturn param = null;
        List<LocationInfoIce> resultList = new ArrayList<LocationInfoIce>();
        for (int i = 0; i < houseIdList.size(); i += toIndex) {
            if (i + toIndex > listSize) {       
                toIndex = listSize - i;
            }
        List<Integer> newList = houseIdList.subList(i, i + toIndex);
        param = MtCommonServiceUtils.queryLocationInfoList(newList);
        if (param != null && RpcError.SUCCESS.getCode().equals(param.code)) {
        	resultList.addAll(param.getResultList());
			}
        }
        return AppUtils.list2Map(resultList, "locationId", LocationInfoIce.class);
	}
}
