package com.segi.uhomecp.medicaltrans.reportjob.report.deptvolumemonth.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import segi.medicaltrans.location.common.LocationInfoListReturn;

import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocation;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.model.DeptVolumeMonth;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.service.DeptVolumeMonthService;
import com.segi.uhomecp.medicaltrans.reportjob.query.deptvolumemonth.service.CountDeptMonthService;
import com.segi.uhomecp.medicaltrans.reportjob.report.deptvolumemonth.service.ReportDeptVolumeMonthService;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.xxl.job.core.log.XxlJobLogger;

@Component
public class DeptCommonUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DeptCommonUtils.class);

	@Autowired
	private CountDeptMonthService countDeptMonthService;
	
	@Autowired
	private DeptVolumeMonthService deptVolumeMonthService;
	
	@Autowired
	private ReportDeptVolumeMonthService reportDeptVolumeMonthService;
	
	/**
	 * @Title: updateDeptMonth 
	 * @Description:  按月修改科室运送量报表库 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月6日上午11:04:38
	 */
	public void updateDeptMonth(Map<Integer, DeptVolumeMonth> deptMap, String cycleYm) {
		try {
			DeptVolumeMonth deptMonth = new DeptVolumeMonth();
			for (Map.Entry<Integer, DeptVolumeMonth> entry : deptMap.entrySet()) {
				// 先修改科室信息再新增
				deptMonth = entry.getValue();
				if (null == deptMonth) {
					continue;
				}
				XxlJobLogger.log("updateDeptMonth 修改科室运送量报表库" + deptMonth.getHouseId() + "-------" + deptMonth.getCycleYm() + "-------" + deptMonth.getTransAmount());
				reportDeptVolumeMonthService.updateDeptCount(deptMonth);
			}
		} catch (Exception e) {
			LOGGER.error("updateDeptMonth 修改科室运送量报表库出错", e);
		}
	}

	/**
	 * @Title: updateDeptMonthInfo 
	 * @Description: 修改科室运送量报表库 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月7日下午5:21:29
	 */
	public void updateDeptMonthInfo(Map<String, Map<Integer, DeptVolumeMonth>> yearMonthMap) {
		if (null == yearMonthMap || yearMonthMap.size() == 0) {
			return;
		}
		for (Map.Entry<String, Map<Integer, DeptVolumeMonth>> entry : yearMonthMap.entrySet()) {
			updateDeptMonth(entry.getValue(), entry.getKey());
		}
	}
	
	/**
	 * @Title: getBlankDeptMonth 
	 * @Description: 获得报表对象 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月11日下午4:56:42
	 */
	public DeptVolumeMonth getDeptMonthBean() {
		DeptVolumeMonth deptMonth = new DeptVolumeMonth();
		deptMonth.setHouseId(0);
		deptMonth.setCycleYm(0);
		deptMonth.setCreateDate(0L);
		deptMonth.setUpdateDate(0L);
		deptMonth.setTransAmount(0);
		deptMonth.setDispatchAmount(0);
		deptMonth.setAutonomousAmount(0);
		deptMonth.setSpecialAmount(0);
		deptMonth.setUrgentAmount(0);
		deptMonth.setCommonAmount(0);
		return deptMonth;
	}
	
	/**
	 * @Title: getBlankDeptMonth 
	 * @Description: 获得报表对象
	 * @author liuyi@segimail.com 
	 * @date 2018年8月11日下午4:55:29
	 */
	public DeptVolumeMonth getDeptMonthBean(MtBuildLocation house, Long nowDate) {
		DeptVolumeMonth deptMonth = getDeptMonthBean();
		deptMonth.setCreateDate(nowDate);
		deptMonth.setUpdateDate(nowDate);
		if (null == house) {
			return deptMonth;
		}
		deptMonth.setOrganId(house.getOrganId());
		deptMonth.setGroupOrganId(house.getGroupOrganId());
		deptMonth.setHouseId(house.getLocationId());
		deptMonth.setBuildId(house.getBuildId());
		deptMonth.setSid(house.getSid());
		deptMonth.setFloorId(house.getFloorId());
		return deptMonth;
	}
	
	/**
	 * @Title: queryLocationInfoByOrganIdList 
	 * @Description: 通过项目id查询全部科室信息 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月2日下午2:56:41
	 */
	public List<MtBuildLocation> queryLocationInfoByOrganIdList(List<Integer> organIdList) {
		List<MtBuildLocation> mtBuildList = new ArrayList<MtBuildLocation>();
		LocationInfoListReturn rsp = new LocationInfoListReturn();
		for (Integer organId : organIdList) {
			try {
				rsp = MtCommonServiceUtils.queryLocationInfoByOrganIdRedis(organId);
			} catch (Exception e) {	
				LOGGER.error("CountDeptMonthServiceImpl 统计科室运送量  缓存查询科室信息报错 organId-----" + organId, e);
			}
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode()) && AppUtils.isNotEmpty(rsp.getResultList())) {
				mtBuildList.addAll(BeanCopierUtils.copyList2List(rsp.getResultList(), MtBuildLocation.class, true));
			}
		}
		return mtBuildList;
	}
}
