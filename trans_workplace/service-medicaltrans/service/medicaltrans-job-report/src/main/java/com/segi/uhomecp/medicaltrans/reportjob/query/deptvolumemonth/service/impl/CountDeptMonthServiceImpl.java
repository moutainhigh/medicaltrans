package com.segi.uhomecp.medicaltrans.reportjob.query.deptvolumemonth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocation;
import com.segi.uhomecp.medicaltrans.enums.TransTaskTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.UrgencyEnum;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.model.DeptVolumeMonth;
import com.segi.uhomecp.medicaltrans.reportjob.monthjob.handler.support.DeptFullMonthHandler;
import com.segi.uhomecp.medicaltrans.reportjob.query.deptvolumemonth.dao.CountDeptMonthMapper;
import com.segi.uhomecp.medicaltrans.reportjob.query.deptvolumemonth.dto.DeptSqlDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.deptvolumemonth.dto.DeptVolumeMonthDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.deptvolumemonth.service.CountDeptMonthService;
import com.segi.uhomecp.medicaltrans.reportjob.query.deptvolumemonth.utils.CountDeptUtils;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.ReportJobTimeDto;
import com.segi.uhomecp.medicaltrans.reportjob.report.deptvolumemonth.utils.DeptCommonUtils;
import com.segi.uhomecp.medicaltrans.utils.DynamicTableSourceKeyHolder;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.xxl.job.core.log.XxlJobLogger;

@Service
public class CountDeptMonthServiceImpl<V> implements CountDeptMonthService {

	private static final  Logger LOGGER = LoggerFactory.getLogger(DeptFullMonthHandler.class);
	
	@Autowired
	private CountDeptMonthMapper countDeptMonthMapper;
	
	@Autowired
	private CountDeptMonthService countDeptMonthService;
	
	@Autowired
	private CountDeptUtils countDeptUtils;
	
	@Autowired
	private DeptCommonUtils deptCommonUtils;
	
	/**
	 * @Title: queryDeptVolumeMonthInfo 
	 * @Description:  查询科室运送量 
	 * @author liuyi@segimail.com 
	 * @date 2018年7月30日下午3:33:18
	 */
	@Override
	public ResultInfo queryDeptVolumeMonthInfo(Integer groupOrganId, List<MtBuildLocation> buildList, ReportJobTimeDto jobTDto) {
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setIsSucc(true);
		// 查询科室信息每次查询5000条
		if (AppUtils.isNotEmpty(buildList)) {
			XxlJobLogger.log("queryDeptVolumeMonthInfo==============>");
			List<MtBuildLocation> mtBuildList = BeanCopierUtils.copyList2List(buildList, MtBuildLocation.class, true);
			List<Integer> locationIdList = AppUtils.list2ParamsList(mtBuildList, (obj) -> obj.getLocationId());
			DeptSqlDto deptSqlDto = new DeptSqlDto();
			deptSqlDto.setHouseIdList(locationIdList);
			deptSqlDto.setOrganIdList(jobTDto.getOrganIdList());
			deptSqlDto.setStartTime(jobTDto.getStartTime());
			deptSqlDto.setEndTime(jobTDto.getMonthEndTime());
			//  统计调度和自主的任务
			List<Integer> taskTypeList = new ArrayList<Integer>();
			taskTypeList.add(Integer.valueOf(TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode()));
			taskTypeList.add(Integer.valueOf(TransTaskTypeEnum.TASK_TYPE_SELF.getCode()));
			deptSqlDto.setTaskTypeList(taskTypeList);
			// 只统计前一天创建的任务
			deptSqlDto.setCountEndTime(countDeptUtils.getIntradayStartTime());	
			String tableIndex = DynamicTableSourceKeyHolder.getDataSourceKey();
			deptSqlDto.setTableIndex(tableIndex);
//			List<DeptVolumeMonth> deptVolumeMonthList = countDeptMonthMapper.getDeptVolumeMonth(deptSqlDto);
			List<DeptVolumeMonth> deptVolumeMonthList = getDeptVolumeMonth(deptSqlDto);
			XxlJobLogger.log("统计出来list: {" 
					+ FastjsonUtils.toJsonString(deptVolumeMonthList) + "}");
			Map<Integer, DeptVolumeMonth> deptMap = new HashMap<Integer, DeptVolumeMonth>();
			if (AppUtils.isNotEmpty(deptVolumeMonthList)) {
				deptMap = AppUtils.list2Map(deptVolumeMonthList, (obj) -> obj.getHouseId());
			}
			// 设置科室统计信息
			List<DeptVolumeMonth> monthList = countDeptUtils.setHouseInfo(mtBuildList, deptMap, 
					StringUtils.isNotBlank(jobTDto.getExeYearMonth()) ? jobTDto.getExeYearMonth() : "");
			resultInfo.setObjList(monthList);
		}
		return resultInfo;
	}
	
	/**
	 * @Title: getDeptVolumeMonth 
	 * @Description: 查询运送量 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月23日上午10:14:59
	 */
	private List<DeptVolumeMonth> getDeptVolumeMonth(DeptSqlDto deptSqlDto) {
		//任务类型数量
		List<DeptVolumeMonthDto> deptTaskTypeList = countDeptMonthMapper.getDeptMonthTaskType(deptSqlDto);
		//统计科室紧急程度
		List<DeptVolumeMonthDto> deptUrgencyList = countDeptMonthMapper.getDeptMonthUrgency(deptSqlDto);
		Map<Integer, DeptVolumeMonth> deptMap = new HashMap<Integer, DeptVolumeMonth>();
		List<DeptVolumeMonth> deptlist = new ArrayList<DeptVolumeMonth>();
		DeptVolumeMonth deptMonth = new DeptVolumeMonth();
		for (DeptVolumeMonthDto  deptTaskType : deptTaskTypeList) {
			deptMonth = deptMap.get(deptTaskType.getHouseId());
			if (deptMonth != null) {
				if (TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode().equals(deptTaskType.getTaskType())) {
					// 判断是不是调度
					deptMonth.setDispatchAmount(deptMonth.getDispatchAmount() + deptTaskType.getNum());
					deptMonth.setTransAmount(deptMonth.getTransAmount() + deptTaskType.getNum());
				}
				if (TransTaskTypeEnum.TASK_TYPE_SELF.getCode().equals(deptTaskType.getTaskType())) {
					// 判断是不是自主
					deptMonth.setAutonomousAmount(deptMonth.getAutonomousAmount() + deptTaskType.getNum());
					deptMonth.setTransAmount(deptMonth.getTransAmount() + deptTaskType.getNum());
				}
			} else {
				deptMonth = deptCommonUtils.getDeptMonthBean();
				deptMonth.setHouseId(deptTaskType.getHouseId());
				if (TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode().equals(deptTaskType.getTaskType())) {
					// 判断是不是调度
					deptMonth.setDispatchAmount(deptMonth.getDispatchAmount() + deptTaskType.getNum());
					deptMonth.setTransAmount(deptMonth.getTransAmount() + deptTaskType.getNum());
				}
				if (TransTaskTypeEnum.TASK_TYPE_SELF.getCode().equals(deptTaskType.getTaskType())) {
					// 判断是不是自主
					deptMonth.setAutonomousAmount(deptMonth.getAutonomousAmount() + deptTaskType.getNum());
					deptMonth.setTransAmount(deptMonth.getTransAmount() + deptTaskType.getNum());
				}
				deptMap.put(deptTaskType.getHouseId(), deptMonth);
			}
		}
		for (DeptVolumeMonthDto deptTaskType : deptUrgencyList) {
			deptMonth = deptMap.get(deptTaskType.getHouseId());
			if (deptMonth != null) {
				if (UrgencyEnum.URGENCY_COMMONLY.getCode().equals(deptTaskType.getUrgency())) {
					// 判断紧急程度是否是一般
					deptMonth.setCommonAmount(deptMonth.getCommonAmount() + deptTaskType.getNum());
				}
				if (UrgencyEnum.URGENCY_URGENT.getCode().equals(deptTaskType.getUrgency())) {
					// 判断紧急程度是否是紧急
					deptMonth.setUrgentAmount(deptMonth.getUrgentAmount() + deptTaskType.getNum());
				}
				if (UrgencyEnum.URGENCY_EXTRAURGENT.getCode().equals(deptTaskType.getUrgency())) {
					// 判断紧急程度是否是特级
					deptMonth.setSpecialAmount(deptMonth.getSpecialAmount() + deptTaskType.getNum());
				}
			} else {
				deptMonth = deptCommonUtils.getDeptMonthBean();
				deptMonth.setHouseId(deptTaskType.getHouseId());
				if (UrgencyEnum.URGENCY_COMMONLY.getCode().equals(deptTaskType.getUrgency())) {
					// 判断紧急程度是否是一般
					deptMonth.setCommonAmount(deptMonth.getCommonAmount() + deptTaskType.getNum());
				}
				if (UrgencyEnum.URGENCY_URGENT.getCode().equals(deptTaskType.getUrgency())) {
					// 判断紧急程度是否是紧急
					deptMonth.setUrgentAmount(deptMonth.getUrgentAmount() + deptTaskType.getNum());
				}
				if (UrgencyEnum.URGENCY_EXTRAURGENT.getCode().equals(deptTaskType.getUrgency())) {
					// 判断紧急程度是否是特级
					deptMonth.setSpecialAmount(deptMonth.getSpecialAmount() + deptTaskType.getNum());
				}
				deptMap.put(deptTaskType.getHouseId(), deptMonth);
			}
		}
		for (Map.Entry<Integer, DeptVolumeMonth> entry : deptMap.entrySet()) {
			deptlist.add(entry.getValue());
		}
		return deptlist;
	}
	
	/**
	 * @Title: setHouseInfo 
	 * @Description: 设置科室信息 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月1日下午8:19:15
	 */
//	public List<DeptVolumeMonth> setHouseInfo(List<MtBuildLocation> mtBuildList, Map<Integer, DeptVolumeMonth> deptMap, String cycleYm) {
//		List<DeptVolumeMonth> monthList = new ArrayList<>();
//		Long nowDate = DataTypeConverUtils.formatDateToLongYYYYMMDDHHMMSS(new Date());
//		for (MtBuildLocation mtBuildLocation : mtBuildList) {
//			if (mtBuildLocation == null) {
//				continue;
//			}
//			if (Constant.STATUS_CD_DEL.equals(mtBuildLocation.getStatus()) && deptMap.get(mtBuildLocation.getLocationId()) == null) {
//				// 如果是无效科室并且没有数据就不新增
//				continue;
//			}
//			// 获得科室信息报表对象
//			DeptVolumeMonth deptMonth = deptCommonUtils.getDeptMonthBean(mtBuildLocation, nowDate);
//			deptMonth.setCycleYm(Integer.valueOf(cycleYm));
//			// 对运送量进行统计
//			countDeptMonthInfo(deptMap, mtBuildLocation, deptMonth);
//			monthList.add(deptMonth);
//		}
//		return monthList;
//	}

	/**
	 * @Title: countDeptMonthInfo 
	 * @Description: 计算科室运送量 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月14日下午4:27:27
	 */
//	private void countDeptMonthInfo(Map<Integer, DeptVolumeMonth> deptMap,
//			MtBuildLocation mtBuildLocation, DeptVolumeMonth deptMonth) {
//		if (null != deptMap && deptMap.get(mtBuildLocation.getLocationId()) != null) {
//			// 有数据的科室 把结果放入报表对象中
//			DeptVolumeMonth deptVolumeMonth = deptMap.get(mtBuildLocation.getLocationId());
//			deptMonth.setTransAmount(deptVolumeMonth.getDispatchAmount() + deptVolumeMonth.getAutonomousAmount());
//			deptMonth.setDispatchAmount(deptVolumeMonth.getDispatchAmount());
//			deptMonth.setAutonomousAmount(deptVolumeMonth.getAutonomousAmount());
//			deptMonth.setSpecialAmount(deptVolumeMonth.getSpecialAmount());
//			deptMonth.setUrgentAmount(deptVolumeMonth.getUrgentAmount());
//			deptMonth.setCommonAmount(deptVolumeMonth.getCommonAmount());
//		}
//	}

	/**
	 * @Title: queryCountDeptInfo 
	 * @Description: 统计科室信息   
	 * @author liuyi@segimail.com 
	 * @date 2018年8月14日下午7:20:35
	 */
	@Override
	public List<DeptVolumeMonth> queryCountDeptInfo(Integer groupOrganId, List<MtBuildLocation> buildList, 
			ReportJobTimeDto reportJobTimeDto) {
		ResultInfo result = new ResultInfo();
		List<DeptVolumeMonth> deptmonthList = new ArrayList<DeptVolumeMonth>();
		try {
			result = countDeptMonthService.queryDeptVolumeMonthInfo(groupOrganId, buildList, reportJobTimeDto);
		} catch (Exception e) {
			LOGGER.error("queryDeptVolumeMonthInfo 统计科室运送量  统计科室信息报错", e);
		}
		if (result.getIsSucc() && AppUtils.isNotEmpty(result.getObjList())) {
			@SuppressWarnings("unchecked")
			List<DeptVolumeMonth> deptList = result.getObjList();
			deptmonthList.addAll(deptList);
		}
		return deptmonthList;
	}
	
//	/**
//	 * @Title: getIntradayStartTime 
//	 * @Description: 返回 当天yyyyMMddHHmmss到 000000
//	 * @author liuyi@segimail.com 
//	 * @date 2018年8月14日下午7:53:31
//	 */
//	public Long getIntradayStartTime() {
//		Date date = new Date();   
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(date);
//        cal.set(Calendar.HOUR_OF_DAY, 00);
//        cal.set(Calendar.MINUTE, 00);
//        cal.set(Calendar.SECOND, 00);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        String lastDayOfMonth = sdf.format(cal.getTime());
//        return Long.valueOf(lastDayOfMonth);
//	}
}
