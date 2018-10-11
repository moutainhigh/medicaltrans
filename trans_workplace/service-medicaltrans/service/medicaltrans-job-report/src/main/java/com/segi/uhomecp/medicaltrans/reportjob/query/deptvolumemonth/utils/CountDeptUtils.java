package com.segi.uhomecp.medicaltrans.reportjob.query.deptvolumemonth.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocation;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.model.DeptVolumeMonth;
import com.segi.uhomecp.medicaltrans.reportjob.report.deptvolumemonth.utils.DeptCommonUtils;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.DataTypeConverUtils;
import com.xxl.job.core.log.XxlJobLogger;

@Component
public class CountDeptUtils {
	
	@Autowired
	private DeptCommonUtils deptCommonUtils;

	/**
	 * @Title: setHouseInfo 
	 * @Description: 设置科室信息 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月1日下午8:19:15
	 */
	public List<DeptVolumeMonth> setHouseInfo(List<MtBuildLocation> mtBuildList, Map<Integer, DeptVolumeMonth> deptMap, String cycleYm) {
		List<DeptVolumeMonth> monthList = new ArrayList<>();
		Long nowDate = DataTypeConverUtils.formatDateToLongYYYYMMDDHHMMSS(new Date());
		for (MtBuildLocation mtBuildLocation : mtBuildList) {
			if (mtBuildLocation == null) {
				continue;
			}
//			XxlJobLogger.log(mtBuildLocation.getLocationId() + "科室--------" + mtBuildLocation.getStatus());
//			XxlJobLogger.log("科室--------=============" + deptMap.get(mtBuildLocation.getLocationId()));
			if (Constant.STATUS_CD_DEL.equals(mtBuildLocation.getStatus()) && deptMap.get(mtBuildLocation.getLocationId()) == null) {
				// 如果是无效科室并且没有数据就不新增
				XxlJobLogger.log("如果是无效科室并且没有数据就不新增--------" + mtBuildLocation.getLocationId());
				continue;
			}
			// 获得科室信息报表对象
			DeptVolumeMonth deptMonth = deptCommonUtils.getDeptMonthBean(mtBuildLocation, nowDate);
			deptMonth.setCycleYm(Integer.valueOf(cycleYm));
			// 对运送量进行统计
			countDeptMonthInfo(deptMap, mtBuildLocation, deptMonth);
			monthList.add(deptMonth);
		}
		return monthList;
	}
	
	/**
	 * @Title: countDeptMonthInfo 
	 * @Description: 计算科室运送量 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月14日下午4:27:27
	 */
	private void countDeptMonthInfo(Map<Integer, DeptVolumeMonth> deptMap,
			MtBuildLocation mtBuildLocation, DeptVolumeMonth deptMonth) {
		if (null != deptMap && deptMap.get(mtBuildLocation.getLocationId()) != null) {
			// 有数据的科室 把结果放入报表对象中
			DeptVolumeMonth deptVolumeMonth = deptMap.get(mtBuildLocation.getLocationId());
			deptMonth.setTransAmount(deptVolumeMonth.getDispatchAmount() + deptVolumeMonth.getAutonomousAmount());
			deptMonth.setDispatchAmount(deptVolumeMonth.getDispatchAmount());
			deptMonth.setAutonomousAmount(deptVolumeMonth.getAutonomousAmount());
			deptMonth.setSpecialAmount(deptVolumeMonth.getSpecialAmount());
			deptMonth.setUrgentAmount(deptVolumeMonth.getUrgentAmount());
			deptMonth.setCommonAmount(deptVolumeMonth.getCommonAmount());
		}
	}
	
	/**
	 * @Title: getIntradayStartTime 
	 * @Description: 返回 当天yyyyMMddHHmmss到 000000
	 * @author liuyi@segimail.com 
	 * @date 2018年8月14日下午7:53:31
	 */
	public Long getIntradayStartTime() {
		Date date = new Date();   
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 00);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return Long.valueOf(lastDayOfMonth);
	}
}
