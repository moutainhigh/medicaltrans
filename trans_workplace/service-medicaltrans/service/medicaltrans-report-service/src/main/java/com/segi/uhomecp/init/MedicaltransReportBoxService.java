package com.segi.uhomecp.init;

import java.util.Map;

import segi.medicaltrans.common.report.monthrank.personal.PerVolMonthRptServiceIce;
import segi.medicaltrans.common.report.organ.TransReportOrganServiceIce;
import segi.medicaltrans.common.report.transschedule.TransScheduleServiceIce;
import segi.medicaltrans.mthistask.query.MtHisTaskRptQueryServiceIce;
import segi.medicaltrans.report.deptvolumemonth.MtrDeptVolumeMonthServiceIce;
import segi.medicaltrans.report.organmonth.OrganMonthAmountServiceIce;
import segi.medicaltrans.report.pervolume.month.PerTaskAmoMonthServiceIce;
import segi.medicaltrans.report.pervolume.month.PersonalVolumeMonthServiceIce;
import segi.medicaltrans.report.ratio.RatioReportServiceIce;
import segi.medicaltrans.report.trans.TransStatisticsServiceIce;
import Ice.Object;
import IceExt.AbstractIceBoxService;

import com.google.common.collect.Maps;
import com.segi.uhomecp.utils.SpringContextUtils;

public class MedicaltransReportBoxService extends AbstractIceBoxService {

	@Override
	public Map<String, Object> createIceServiceObj(String[] args) {
		SpringContextUtils.instance("classpath*:spring/spring-common.xml");
		
		Map<String, Object> resMap = Maps.newHashMap();

		PersonalVolumeMonthServiceIce personalVolumeMonthServiceIce = SpringContextUtils.getBean(PersonalVolumeMonthServiceIce.class);
		resMap.put(PersonalVolumeMonthServiceIce.class.getName(), personalVolumeMonthServiceIce);
		
		TransReportOrganServiceIce transReportOrganServiceIce = SpringContextUtils.getBean(TransReportOrganServiceIce.class);
		resMap.put(TransReportOrganServiceIce.class.getName(), transReportOrganServiceIce);
		
		PerTaskAmoMonthServiceIce perTaskAmoMonthServiceIce = SpringContextUtils.getBean(PerTaskAmoMonthServiceIce.class);
		resMap.put(PerTaskAmoMonthServiceIce.class.getName(), perTaskAmoMonthServiceIce);
		
		MtrDeptVolumeMonthServiceIce mtrDeptVolumeMonthServiceIce = SpringContextUtils.getBean(MtrDeptVolumeMonthServiceIce.class);
		resMap.put(MtrDeptVolumeMonthServiceIce.class.getName(), mtrDeptVolumeMonthServiceIce);
		
		OrganMonthAmountServiceIce organMonthAmountServiceIce =  SpringContextUtils.getBean(OrganMonthAmountServiceIce.class);
		resMap.put(OrganMonthAmountServiceIce.class.getName(), organMonthAmountServiceIce);
		
		// 报表饼状图：任务类型、运送类型、任务发起源占比
		RatioReportServiceIce ratioReportServiceIce =  SpringContextUtils.getBean(RatioReportServiceIce.class);
		resMap.put(RatioReportServiceIce.class.getName(), ratioReportServiceIce);
		
		// 运送记录查询（报表）
		MtHisTaskRptQueryServiceIce mtHisTaskQueryServiceIce = SpringContextUtils.getBean(MtHisTaskRptQueryServiceIce.class);
		resMap.put(MtHisTaskRptQueryServiceIce.class.getName(), mtHisTaskQueryServiceIce);
		
		// 排程表
		TransScheduleServiceIce transScheduleServiceIce = SpringContextUtils.getBean(TransScheduleServiceIce.class);
		resMap.put(TransScheduleServiceIce.class.getName(), transScheduleServiceIce);
		
		// 个人运送量增量新增
		PerVolMonthRptServiceIce perVolMonthRptServiceIce = SpringContextUtils.getBean(PerVolMonthRptServiceIce.class);
		resMap.put(PerVolMonthRptServiceIce.class.getName(), perVolMonthRptServiceIce);

		// 大屏运送时间统计
//		TransStatisticsServiceIce transStatisticsServiceIce = SpringContextUtils.getBean(TransStatisticsServiceIce.class);
//		resMap.put(TransStatisticsServiceIce.class.getName(), transStatisticsServiceIce);
		
		return resMap;
	}
}
