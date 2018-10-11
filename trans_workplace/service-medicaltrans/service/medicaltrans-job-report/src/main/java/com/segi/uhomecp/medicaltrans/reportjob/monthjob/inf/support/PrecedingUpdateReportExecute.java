package com.segi.uhomecp.medicaltrans.reportjob.monthjob.inf.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.reportjob.monthjob.dto.MonthReportExecuteDto;
import com.segi.uhomecp.medicaltrans.reportjob.monthjob.dto.MonthReportExecuteInitialDto;
import com.segi.uhomecp.medicaltrans.reportjob.monthjob.inf.AbsFullMonthDefaultExecute;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.ReportJobTimeDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.service.TransService;
import com.segi.uhomecp.wh.common.utils.DateUtil;

@Component(value = "precedingUpdateReportExecute")
public class PrecedingUpdateReportExecute extends AbsFullMonthDefaultExecute {

	@Autowired
	private TransService transService;

	@Override
	public MonthReportExecuteInitialDto init(Integer groupOrganId, List<Integer> processOrganIds, List<String> months) {
		MonthReportExecuteInitialDto rst = new MonthReportExecuteInitialDto();
		List<MonthReportExecuteDto> executeList = new ArrayList<>();
		// 不更新排程表 使用默认值
		rst.setExecuteList(executeList);
		rst.setLastUpdateFlag(true);// 更新上个执行时间
		Date nowDate = new Date();
		String oneMonthBefore = DateUtil.formatDateToString(DateUtil.addDateMonth(nowDate, -1), DateUtil.FORMAT_YYYYMM);
		ReportJobTimeDto oneMonthBeforeParams = transService.getJobEnterParam(groupOrganId, processOrganIds,	oneMonthBefore);
		if (oneMonthBeforeParams != null) {
			MonthReportExecuteDto oneOrganMonth = new MonthReportExecuteDto();
			oneOrganMonth.setExecuteObj("reportOrganFullMonthHandler");
			oneOrganMonth.setParams(oneMonthBeforeParams);
			executeList.add(oneOrganMonth);

			MonthReportExecuteDto oneDeptMonth = new MonthReportExecuteDto();
			oneDeptMonth.setExecuteObj("deptFullMonthHandler");
			oneDeptMonth.setParams(oneMonthBeforeParams);
			executeList.add(oneDeptMonth);

			MonthReportExecuteDto oneTranstypeMonth = new MonthReportExecuteDto();
			oneTranstypeMonth.setExecuteObj("reportTranstypeFullMonthHandler");
			oneTranstypeMonth.setParams(oneMonthBeforeParams);
			executeList.add(oneTranstypeMonth);

			MonthReportExecuteDto onePerVolMonth = new MonthReportExecuteDto();
			onePerVolMonth.setExecuteObj("reportPerVolFullMonthHandler");
			onePerVolMonth.setParams(oneMonthBeforeParams);
			executeList.add(onePerVolMonth);

		}

		String towMonthBefore = DateUtil.formatDateToString(
				DateUtil.addDateMonth(nowDate, -2), DateUtil.FORMAT_YYYYMM);
		ReportJobTimeDto towMonthBeforeParams = transService.getJobEnterParam(
				groupOrganId, processOrganIds,towMonthBefore);
		if (towMonthBeforeParams != null) {
			MonthReportExecuteDto towOrganMonth = new MonthReportExecuteDto();
			towOrganMonth.setExecuteObj("reportOrganFullMonthHandler");
			towOrganMonth.setParams(towMonthBeforeParams);
			executeList.add(towOrganMonth);

			MonthReportExecuteDto towPerVolMonth = new MonthReportExecuteDto();
			towPerVolMonth.setExecuteObj("reportPerVolFullMonthHandler");
			towPerVolMonth.setParams(towMonthBeforeParams);
			executeList.add(towPerVolMonth);
			
			MonthReportExecuteDto towTranstypeMonth = new MonthReportExecuteDto();
			towTranstypeMonth.setExecuteObj("reportTranstypeFullMonthHandler");
			towTranstypeMonth.setParams(oneMonthBeforeParams);
			executeList.add(towTranstypeMonth);
		}

		// 每月一号初始化科室报表基础信息 没有运送量的科室都以零运送量存入报表库 
		String newMonthBefore = DateUtil.formatDateToString(nowDate, DateUtil.FORMAT_YYYYMM);
		ReportJobTimeDto newMonthBeforeParams = new ReportJobTimeDto();
		// 只用传organIdList 年月
		newMonthBeforeParams.setOrganIdList(processOrganIds);
		newMonthBeforeParams.setExeYearMonth(newMonthBefore);
		MonthReportExecuteDto newDeptMonth = new MonthReportExecuteDto();
		newDeptMonth.setExecuteObj("deptInitInfoHandler");
		newDeptMonth.setParams(newMonthBeforeParams);
		executeList.add(newDeptMonth);
		return rst;
	}
	
}
