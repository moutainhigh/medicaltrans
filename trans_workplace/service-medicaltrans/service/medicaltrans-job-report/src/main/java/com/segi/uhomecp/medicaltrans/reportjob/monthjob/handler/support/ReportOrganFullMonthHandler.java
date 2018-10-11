package com.segi.uhomecp.medicaltrans.reportjob.monthjob.handler.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.reportjob.monthjob.handler.JobFullMonthHandler;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.OrganMonthStatisticsDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.ReportJobTimeDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.service.TransService;
import com.segi.uhomecp.medicaltrans.reportjob.report.organmonth.service.ReportOrganMonthService;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.wh.common.dto.ResultDto;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.CheckRestParams;
import com.xxl.job.core.log.XxlJobLogger;

@Component(value = "reportOrganFullMonthHandler")
public class ReportOrganFullMonthHandler implements JobFullMonthHandler {

	public static final Logger logger = LoggerFactory.getLogger(ReportOrganFullMonthHandler.class);

	@Autowired
	private ReportOrganMonthService reportOrganMonthService;
	
	@Autowired
	private TransService transService;
	
	/**
	 * 项目月运送量统计
	 */
	@Override
	public ResultDto<String, String, Integer> invoke(ReportJobTimeDto reportJobTimeDto) {
		XxlJobLogger.log("==============>开始全量项目月运送量表信息...");
		long beginTime = System.currentTimeMillis();
		ResultDto<String, String, Integer> resultDto = new ResultDto<>(true, "项目月运输量表更新成功");
		// 必填参数验证
		String fields = "exeYearMonth, startTime,endTime, groupOrganId,excDate";
		String messages = "年月,开始时间,结束时间,一级物业id,执行时间";
		String errInfo = CheckRestParams.checkEmpty(reportJobTimeDto, fields, messages);
		if (null != errInfo) {
			logger.error(errInfo);
			resultDto.setIsSucc(false);
			resultDto.setMessage(errInfo);
		}
		if (!AppUtils.isNotEmpty(reportJobTimeDto.getOrganIdList())) {
			logger.error("项目月运输量表更新失败,入参reportJobTimeDto.getOrganIdList()为空");
			resultDto.setIsSucc(false);
			resultDto.setMessage("项目月运输量表更新失败,入参reportJobTimeDto.getOrganIdList()为空");
		}
		if (reportJobTimeDto.getOrganWorkMap() == null || reportJobTimeDto.getOrganWorkMap().size() == 0) {
			logger.error("项目月运输量表更新失败,入参reportJobTimeDto.getOrganWorkMap()为空");
			resultDto.setIsSucc(false);
			resultDto.setMessage("项目月运输量表更新失败,入参reportJobTimeDto.getOrganWorkMap()为空");
		}
		Long startTime = reportJobTimeDto.getStartTime();
		Long endTime = reportJobTimeDto.getEndTime();
		if (startTime != null && endTime != null
				&& !startTime.toString().substring(0, 6).equals(endTime.toString().substring(0, 6))) {
			logger.error("项目月运输量表更新失败,开始时间和结束时间不在同一个月,startTime=" + startTime + ",endTime=" + endTime);
			resultDto.setIsSucc(false);
			resultDto.setMessage("项目月运输量表更新失败,开始时间和结束时间不在同一个月,startTime=" + startTime + ",endTime=" + endTime);
			return resultDto;
		}
		// 记录出错的项目id
		List<Integer> erOrganList = new ArrayList<Integer>();
		Integer cycleYm = Integer.valueOf(reportJobTimeDto.getExeYearMonth());
		int groupOrganId = reportJobTimeDto.getGroupOrganId();
		// 从业务库查询全体需要更新的数据
		List<OrganMonthStatisticsDto> organMonthStatisticsDtoList = transService.getOrganMonthList(groupOrganId, reportJobTimeDto);
		XxlJobLogger.log("==============>成功查出全量项目月运送量表信息[" + (organMonthStatisticsDtoList == null ? 0 : organMonthStatisticsDtoList.size()) + "]条");
		//logger.debug("项目月运送量从业务库查询全体需要更新的数据" + FastjsonUtils.toJsonString(organMonthStatisticsDtoList));
		//1、先查出原数据的运送人数
		Map<Integer,Integer> oldOrganMonthUserMap = reportOrganMonthService.queryTrantUserNum(reportJobTimeDto);
		// 2、先删除本次需要更新的全部项目数据
		reportOrganMonthService.deleteOrganMonthList(reportJobTimeDto);
		if (!AppUtils.isNotEmpty(organMonthStatisticsDtoList)) {
			return resultDto;
		}
		// 3、再新增查到的数据
		for (OrganMonthStatisticsDto dto : organMonthStatisticsDtoList) {
			if (dto == null || dto.getOrganId() == null) {
				continue;
			}
			try {
				Integer transUserAmount = oldOrganMonthUserMap.get(dto.getOrganId());
				if(transUserAmount != null){
					dto.setTransUserAmount(transUserAmount);
				}
				reportOrganMonthService.updateOrganMonthList(dto, cycleYm);
			} catch (Exception e) {
				// 记录出错的organList
				erOrganList.add(dto.getOrganId());
				logger.error("项目月运输量表更新失败,失败的项目是:" + dto.getOrganId() + "月份：" + reportJobTimeDto.getExeYearMonth()
						+ ",错误：" + e);
			}
		}
		if (AppUtils.isNotEmpty(erOrganList)) {
			resultDto.setList(erOrganList);
			resultDto.setIsSucc(false);
			resultDto.setMessage("项目月运输量表更新失败,失败的项目是:" + FastjsonUtils.toJsonString(erOrganList));
		}
		String logInfo = "==============>成功更新组织[" + FastjsonUtils.toJsonString(reportJobTimeDto.getOrganIdList()) + "]全量项目月运送量表信息[" + organMonthStatisticsDtoList.size() + "]条, 耗时["
				+ (System.currentTimeMillis() - beginTime) + "]毫秒";
		XxlJobLogger.log(logInfo);
		XxlJobLogger.log("==============>结束全量项目月运送量表信息...");
		return resultDto;
	}

}
