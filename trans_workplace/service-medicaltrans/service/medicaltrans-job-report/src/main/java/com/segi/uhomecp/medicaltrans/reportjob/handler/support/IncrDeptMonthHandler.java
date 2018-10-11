package com.segi.uhomecp.medicaltrans.reportjob.handler.support;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import segi.medicaltrans.location.common.LocationInfoListReturn;

import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocation;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.enums.TransTaskTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.UrgencyEnum;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.model.DeptVolumeMonth;
import com.segi.uhomecp.medicaltrans.reportjob.handler.AbstractHandler;
import com.segi.uhomecp.medicaltrans.reportjob.query.deptvolumemonth.service.CountDeptMonthService;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.model.MtTaskExtract;
import com.segi.uhomecp.medicaltrans.reportjob.report.deptvolumemonth.service.ReportDeptVolumeMonthService;
import com.segi.uhomecp.medicaltrans.reportjob.report.deptvolumemonth.utils.DeptCommonUtils;
import com.segi.uhomecp.medicaltrans.reportjob.util.ReportUtils;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.DataTypeConverUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * 
 * IncrDeptMonth.java
 * @Description: 科室运送量增量统计
 * @author liuyi@segimail.com 
 * @param <V>
 * @param <E>
 * @created 2018年8月3日上午11:31:49
 */
@Component
public class IncrDeptMonthHandler<V, E> extends AbstractHandler<List<MtTaskExtract>> {
	
	private static final  Logger LOGGER = LoggerFactory.getLogger(IncrDeptMonthHandler.class);
	
	@Autowired
	private CountDeptMonthService countDeptMonthService;
	
	@Autowired
	private ReportDeptVolumeMonthService reportDeptVolumeMonthService;
	
	@Autowired
	private DeptCommonUtils deptCommonUtils;

	@Override
	public String getHandlerName() {
		return MtConstant.INCR_DEPT_MONTH_HANDLER;
	}

	@Override
	public void doInvoke(List<MtTaskExtract> taskList) {
		if (!AppUtils.isNotEmpty(taskList)) {
			
			
			return;
		}
		// 对入参进行统计
		Map<String, Map<Integer, DeptVolumeMonth>> yearMonthMap = countMtTaskList(taskList);
		if (null != yearMonthMap && yearMonthMap.size() != 0) {
			this.getStatisticsMap().putIfAbsent(this.getHandlerName(), yearMonthMap);
		}
	}
	
	/**
	 * @Title: countMtTaskList 
	 * @Description: 对入参进行统计
	 * @author liuyi@segimail.com 
	 * @date 2018年8月6日下午4:34:55
	 */
	private Map<String, Map<Integer, DeptVolumeMonth>> countMtTaskList(List<MtTaskExtract> taskList) {
		// <yearMonth, <houseId, DeptVolumeMonth>>
		Map<String, Map<Integer, DeptVolumeMonth>> yearMonthMap = new HashMap<String, Map<Integer, DeptVolumeMonth>>();
		Map<Integer, DeptVolumeMonth> deptMap = new HashMap<Integer, DeptVolumeMonth>();
		DeptVolumeMonth deptMonth = new DeptVolumeMonth();
		Integer organId = getOrganId();
		// 缓存查询科室信息
		Map<Integer, MtBuildLocation> houseMap = queryLocationInfoByOrganId(organId);
		MtBuildLocation house = new MtBuildLocation();
//		Long startTime = DataTypeConverUtils.formatDateToLongYYYYMMDDHHMMSS(getStartTime());
//		Long endTime = DataTypeConverUtils.formatDateToLongYYYYMMDDHHMMSS(getEndTime());
		Long nowDate = DataTypeConverUtils.formatDateToLongYYYYMMDDHHMMSS(new Date());
		for (MtTaskExtract mtTaskExtract : taskList) {
			Integer houseId = mtTaskExtract.getSourceHouseId();
			house = houseMap.get(houseId);
			if (null == house) {
				LOGGER.error("科室增量报表统计 科室id----" + houseId + "缓存查询不存在");
				XxlJobLogger.log("科室增量报表统计 科室id----" + houseId + "缓存查询不存在");
				continue;
			}
			if (TransTaskTypeEnum.TASK_TYPE_LOOP.getCode().equals(mtTaskExtract.getTaskType())) {
				// 如果是固定任务就不进行统计
				continue;
			}
//			Long createTime = mtTaskExtract.getCreateDate();
//			if (startTime > createTime || endTime <= createTime) {
//				// 如果创建时间小于开始时间  或者大于等于结束时间 就不进行统计
//				XxlJobLogger.log("如果创建时间小于开始时间  或者大于等于结束时间 就不进行统计");
//				XxlJobLogger.log("startTime" + startTime + "endTime" + endTime + "createTime" + createTime);
//				continue;
//			}
			String yearMonth = DateUtil.getYearMonthOfDate(String.valueOf(mtTaskExtract.getBeginTime()));
			deptMap = yearMonthMap.get(yearMonth);
			XxlJobLogger.log("开始统计 -----------------yearMonth" + yearMonth + "--------" + houseId);
			if (null != deptMap) {
				// map里存在年月的key就修改科室运送量map
				deptMap = yearMonthMap.get(yearMonth);
				deptMonth = deptMap.get(houseId);
				if (null != deptMonth) {
					this.setDeptInfo(mtTaskExtract, deptMonth);
				} else {
					deptMonth = deptCommonUtils.getDeptMonthBean(house, nowDate);
					setDeptInfo(deptMap, deptMonth, houseId, yearMonth, mtTaskExtract);
				}
			} else {
				// map里不存在年月的key就新增科室运送量map
				deptMap = new HashMap<Integer, DeptVolumeMonth>();
				deptMonth = deptCommonUtils.getDeptMonthBean(house, nowDate);
				setDeptInfo(deptMap, deptMonth, houseId, yearMonth, mtTaskExtract);
				XxlJobLogger.log("yearMonthMap  key----"  + yearMonth);
				yearMonthMap.put(yearMonth, deptMap);
			}
		}
		return yearMonthMap;
	}
	
	/**
	 * @Title: setDeptInfo 
	 * @Description: 统计运送量  
	 * @author liuyi@segimail.com 
	 * @date 2018年8月11日上午10:12:50
	 */
	private void setDeptInfo(Map<Integer, DeptVolumeMonth> deptMap, DeptVolumeMonth deptMonth, Integer houseId, 
			String yearMonth, MtTaskExtract mtTaskExtract) {
		this.setDeptInfo(mtTaskExtract, deptMonth);
		deptMonth.setCycleYm(Integer.valueOf(yearMonth));
		deptMap.put(houseId, deptMonth);
	}

	/**
	 * @Title: setDeptInfo 
	 * @Description: 统计运送量 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月3日下午7:11:45
	 */
	public DeptVolumeMonth setDeptInfo(MtTaskExtract mtTaskExtract, DeptVolumeMonth deptMonth) {
		if (TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode().equals(mtTaskExtract.getTaskType())) {
			// 判断是否是调度任务
			deptMonth.setDispatchAmount(deptMonth.getDispatchAmount() + 1);
			deptMonth.setTransAmount(deptMonth.getTransAmount() + 1);
			// 只统计调度任务的紧急程度
			setUrgencyInfo(mtTaskExtract, deptMonth);
		}
		if (TransTaskTypeEnum.TASK_TYPE_SELF.getCode().equals(mtTaskExtract.getTaskType())) {
			// 判断是否是自主任务
			deptMonth.setAutonomousAmount(deptMonth.getAutonomousAmount() + 1);
			deptMonth.setTransAmount(deptMonth.getTransAmount() + 1);
		}
		return deptMonth;
	}
	
	/**
	 * @Title: setUrgencyInfo 
	 * @Description: 设置紧急程度信息 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月14日下午2:49:13
	 */
	private void setUrgencyInfo(MtTaskExtract mtTaskExtract, DeptVolumeMonth deptMonth) {
		if (UrgencyEnum.URGENCY_COMMONLY.getCode().equals(mtTaskExtract.getUrgency())) {
			// 判断紧急程度是否是一般
			deptMonth.setCommonAmount(deptMonth.getCommonAmount() + 1);
		}
		if (UrgencyEnum.URGENCY_URGENT.getCode().equals(mtTaskExtract.getUrgency())) {
			// 判断紧急程度是否是紧急
			deptMonth.setUrgentAmount(deptMonth.getUrgentAmount() + 1);
		}
		if (UrgencyEnum.URGENCY_EXTRAURGENT.getCode().equals(mtTaskExtract.getUrgency())) {
			// 判断紧急程度是否是特级
			deptMonth.setSpecialAmount(deptMonth.getSpecialAmount() + 1);
		}
	}

	/**
	 * @Title: queryLocationInfoByOrganId 
	 * @Description: 缓存查询科室信息 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月11日下午4:15:14
	 */
	private Map<Integer, MtBuildLocation> queryLocationInfoByOrganId(Integer organId) {
		Map<Integer, MtBuildLocation> buildMap = new HashMap<Integer, MtBuildLocation>();
		LocationInfoListReturn rsp = MtCommonServiceUtils.queryLocationInfoByOrganIdRedis(organId);
		if (RpcError.SUCCESS.getCode().equals(rsp.getCode()) && AppUtils.isNotEmpty(rsp.getResultList())) {
			buildMap = AppUtils.list2Map(BeanCopierUtils.copyList2List(
					rsp.getResultList(), MtBuildLocation.class, true), (obj) -> obj.getLocationId());
		}
		return buildMap;
	}

	@Override
	public String getDataKey() {
		return ReportUtils.TASK_CREATE_LIST;
	}
}
