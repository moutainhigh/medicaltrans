package com.segi.uhomecp.medicaltrans.reportjob.monthjob.inf.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.reportjob.monthjob.dto.MonthReportExecuteDto;
import com.segi.uhomecp.medicaltrans.reportjob.monthjob.dto.MonthReportExecuteInitialDto;
import com.segi.uhomecp.medicaltrans.reportjob.monthjob.inf.AbsFullMonthDefaultExecute;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.ReportJobTimeDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.service.TransService;
import com.segi.uhomecp.medicaltrans.reportjob.report.pervolmonth.utils.MtPerVolMonthRptHandleUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;

@Component(value = "repairDataUpdateReportExecute")
public class RepairDataUpdateReportExecute extends AbsFullMonthDefaultExecute {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbsFullMonthDefaultExecute.class);

	@Autowired
	private TransService transService;
	
	@Autowired
	private MtPerVolMonthRptHandleUtils mtPerVolMonthRptHandleUtils;

	@Override
	public MonthReportExecuteInitialDto init(Integer groupOrganId, 
			List<Integer> processOrganIds, List<String> months) {
		MonthReportExecuteInitialDto rst = new MonthReportExecuteInitialDto();
		List<MonthReportExecuteDto> executeList = new ArrayList<>();
		rst.setExecuteList(executeList);
		// 获取当前时间年月
		String currentMonth = DateUtil.formatDateToString(new Date(), DateUtil.FORMAT_YYYYMM);
		String lastMonth = DateUtil.formatDateToString(DateUtil.getPreviousMonthFirstDay(), DateUtil.FORMAT_YYYYMM);
		MonthReportExecuteDto executeDto = null;
		LOGGER.debug("repairDataUpdateReportExecute===>init========================》months：{}", months);
		for (String month : months) {
			ReportJobTimeDto reportJobTimeDto = transService.getJobEnterParam(groupOrganId, processOrganIds, month);
			if (reportJobTimeDto != null) {
				executeDto = new MonthReportExecuteDto();
				executeDto.setExecuteObj("reportOrganFullMonthHandler");
				executeDto.setParams(reportJobTimeDto);
				executeList.add(executeDto);

				executeDto = new MonthReportExecuteDto();
				executeDto.setExecuteObj("deptFullMonthHandler");
				executeDto.setParams(reportJobTimeDto);
				executeList.add(executeDto);

				executeDto = new MonthReportExecuteDto();
				executeDto.setExecuteObj("reportTranstypeFullMonthHandler");
				executeDto.setParams(reportJobTimeDto);
				executeList.add(executeDto);
				
				if(lastMonth.equals(month)) {
					// 如果上个月和month 相等，就会更新上个月数据， 这样排程表的上个月数据截止时间也会更新
					rst.setLastUpdateFlag(true);
				}
				if (!currentMonth.equals(month)) {
					// 当月执行人报表job另外再调
					executeDto = new MonthReportExecuteDto();
					executeDto.setExecuteObj("reportPerVolFullMonthHandler");
					executeDto.setParams(reportJobTimeDto);
					executeList.add(executeDto);
				} 
				LOGGER.debug("repairDataUpdateReportExecute===>init========================》currentMonth：{},month:{}", currentMonth, month);
				if (currentMonth.equals(month)) {
					LOGGER.debug("repairDataUpdateReportExecute===>init========================》currentMonth：{},month:{}", currentMonth, month);
					// 当月需要更新排程表
					rst.setUpdateFlag(true);
					// 当天的凌晨00:00
					rst.setExcEndDate(DateUtil.getCurDayHHmmss(0, 0, 0, 0));
					try {
						// 调用执行人报表当月job
						mtPerVolMonthRptHandleUtils.handleJobData(groupOrganId, processOrganIds, null);
						mtPerVolMonthRptHandleUtils.handleRedisJobData(groupOrganId, processOrganIds, null);
					} catch (Exception e) {
						LOGGER.error("调用执行人报表当月job", e);
					}
				}
			}
		}
		return rst;
	}
	
}
