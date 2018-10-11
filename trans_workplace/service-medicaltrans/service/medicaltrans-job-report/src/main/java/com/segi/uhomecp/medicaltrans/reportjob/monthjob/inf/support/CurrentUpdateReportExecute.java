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

@Component(value = "currentUpdateReportExecute")
public class CurrentUpdateReportExecute extends AbsFullMonthDefaultExecute {

	@Autowired
	private TransService transService;

	@Override
	public MonthReportExecuteInitialDto init(Integer groupOrganId,
			List<Integer> processOrganIds, List<String> months) {
		MonthReportExecuteInitialDto rst = new MonthReportExecuteInitialDto();
		rst.setExcEndDate(DateUtil.getCurDayHHmmss(0, 0, 0, 0));// 设置当前月截止时间
		rst.setUpdateFlag(true);
		List<MonthReportExecuteDto> executeList = new ArrayList<>();
		rst.setExecuteList(executeList);
		Date nowDate = new Date();
		String currentMonth = DateUtil.formatDateToString(nowDate, DateUtil.FORMAT_YYYYMM);
		ReportJobTimeDto currentMonthParams = transService.getJobEnterParam(groupOrganId, processOrganIds,
				currentMonth);
		MonthReportExecuteDto curTranstypeMonth = new MonthReportExecuteDto();
		curTranstypeMonth.setExecuteObj("reportTranstypeFullMonthHandler");
		curTranstypeMonth.setParams(currentMonthParams);
		executeList.add(curTranstypeMonth);
		
		MonthReportExecuteDto curOrganMonth = new MonthReportExecuteDto();
		curOrganMonth.setExecuteObj("reportOrganFullMonthHandler");
		curOrganMonth.setParams(currentMonthParams);
		executeList.add(curOrganMonth);

		MonthReportExecuteDto curDeptMonth = new MonthReportExecuteDto();
		curDeptMonth.setExecuteObj("deptFullMonthHandler");
		curDeptMonth.setParams(currentMonthParams);
		executeList.add(curDeptMonth);
		return rst;
	}

}
