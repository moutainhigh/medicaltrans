package com.segi.uhomecp.medicaltrans.reportjob.monthjob.handler.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonth;
import com.segi.uhomecp.medicaltrans.reportjob.monthjob.handler.JobFullMonthHandler;
import com.segi.uhomecp.medicaltrans.reportjob.monthjob.utils.CheckParamUtils;
import com.segi.uhomecp.medicaltrans.reportjob.query.pervolmonth.util.PerVolMonthStatServiceUtil;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.ReportJobTimeDto;
import com.segi.uhomecp.medicaltrans.reportjob.report.pervolmonth.service.PerVolMonthRptService;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.wh.common.dto.ResultDto;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * @ClassName:  ReportPerVolFullMonthHandler   
 * @Description:个人月运送量报表按月统计历史数据   
 * @author: zhaoqing
 * @date:   2018年8月14日 下午7:53:49
 */
@Component(value = "reportPerVolFullMonthHandler")
public class ReportPerVolFullMonthHandler implements JobFullMonthHandler {

	public static final Logger LOGGER = LoggerFactory.getLogger(ReportPerVolFullMonthHandler.class);

	@Autowired
	private PerVolMonthRptService perVolMonthRptService;
	
	@Autowired
	private PerVolMonthStatServiceUtil perVolMonthStatServiceUtil;
	
	/**
	 * <p>Title: invoke</p>   
	 * <p>Description: 个人月运送量报表按月统计历史数据 </p> 
	 * <p>zhaoqing</p>
	 * @param reportJobTimeDto
	 * @return   
	 */
	@Override
	public ResultDto<String, String, Integer> invoke(ReportJobTimeDto reportJobTimeDto) {
		
		XxlJobLogger.log("==============>开始按月全量统计个人运送量月报表历史信息...");
		XxlJobLogger.log("==============>params: {" 
				+ FastjsonUtils.toJsonString(reportJobTimeDto) + "}");
		// 参数检验
		ResultDto<String, String, Integer> resultDto = 
				CheckParamUtils.check(reportJobTimeDto, "个人月运输量历史表");
		if (!resultDto.getIsSucc()) {
			return resultDto;
		}
		// 记录出错的项目id
		List<Integer> erOrganList = new ArrayList<Integer>();
		// 获取月份
		Integer cycleYm = Integer.valueOf(reportJobTimeDto.getExeYearMonth());
		long beginTime = System.currentTimeMillis();
		// 按组织机构从业务库统计个人运送量数据
		List<PersonalVolumeMonth> perVolMonthList = perVolMonthStatServiceUtil
				.getPersonalVolumeMonth(reportJobTimeDto);
		XxlJobLogger.log("==============>成功从执行人表获取个人运送量信息[" + perVolMonthList.size() + "]条");
		// 按组织分组统计数据
		Map<Integer, List<PersonalVolumeMonth>> perVolMonthGruopMap = AppUtils.listGroup2Map(
				perVolMonthList, PersonalVolumeMonth.class, "organId", PersonalVolumeMonth.class);
		// 遍历分组后的组织机构ID
		for (Integer organId : reportJobTimeDto.getOrganIdList()) {
			try {
				List<PersonalVolumeMonth> groupList = perVolMonthGruopMap.get(organId);
				// 按组织更新个人运送量报表数据
				perVolMonthRptService.updatePerVolMonthByMonth(
						reportJobTimeDto.getGroupOrganId(), organId, cycleYm, groupList);
			} catch (Exception e) {	
				// 记录出错的organList
				erOrganList.add(organId);
				LOGGER.error("个人月运输量历史表更新失败,失败的项目是:" + organId + "月份：" 
						+ reportJobTimeDto.getExeYearMonth() + ",错误：" + e);
			}	
		}
		String logInfo = "==============>成功统计人员个人运送量数据信息[" + perVolMonthList.size() 
				+ "]条, 耗时[" + (System.currentTimeMillis() - beginTime) + "]毫秒";
		XxlJobLogger.log(logInfo);
		XxlJobLogger.log("==============>结束按月全量统计个人运送量月报表历史信息");
		
		if (AppUtils.isNotEmpty(erOrganList)) {
			resultDto.setList(erOrganList);
			resultDto.setIsSucc(false);
			resultDto.setMessage("个人月运输量历史表更新失败,失败的项目是:" + FastjsonUtils.toJsonString(erOrganList));
		}
		return resultDto;
	}

}
