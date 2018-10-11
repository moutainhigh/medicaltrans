package com.segi.uhomecp.medicaltrans.reportjob.monthjob.handler.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.report.monthamount.source.model.SourceStatistics;
import com.segi.uhomecp.medicaltrans.reportjob.monthjob.handler.JobFullMonthHandler;
import com.segi.uhomecp.medicaltrans.reportjob.monthjob.utils.CheckParamUtils;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.ReportJobTimeDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.service.TransService;
import com.segi.uhomecp.medicaltrans.reportjob.report.source.service.ReportSourceService;
import com.segi.uhomecp.wh.common.dto.ResultDto;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.xxl.job.core.log.XxlJobLogger;

@Component(value = "reportSourceFullMonthHandler")
public class ReportSourceFullMonthHandler implements JobFullMonthHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportSourceFullMonthHandler.class);
	
	@Autowired
	private TransService transService;
	
	@Autowired
	private ReportSourceService reportSourceService;
	
	/**
	 * 根据项目id和开始结束时间更新报表库运送来源总量表
	 */
	@Override
	public ResultDto<String, String, Integer> invoke(ReportJobTimeDto reportJobTimeDto) {
		XxlJobLogger.log("==============>开始全量统计运送来源运输总量表信息...");
		ResultDto<String, String, Integer> resultDto = CheckParamUtils.check(reportJobTimeDto, "运送来源运输总量表");
		if (!resultDto.getIsSucc()) {
			return resultDto;
		}
		// 根据传进来的月份得到cycleYm
		Integer cycleYm = Integer.valueOf(reportJobTimeDto.getExeYearMonth());
		// 根据对象属性查出对象list
		List<SourceStatistics> sourceTaskList = getSourceList(reportJobTimeDto);
		if (!AppUtils.isNotEmpty(sourceTaskList)) {
			resultDto.setIsSucc(false);
			resultDto.setMessage("没有需要更新的任务单数据！");
			return resultDto;
		}
		XxlJobLogger.log("==============>成功从运输单表获取运送量信息[" + sourceTaskList.size() + "]条");
		Map<Integer, List<SourceStatistics>> mtTaskMap = groupByOrganId(cycleYm, sourceTaskList);
		XxlJobLogger.log("==============>结束全量统计运送来源运输总量表信息");
		// 1、先删除本次需要更新的全部项目数据
		reportSourceService.deleteByOrganListAndCycleYm(reportJobTimeDto.getOrganIdList(), cycleYm);
		XxlJobLogger.log("成功删除组织[" + reportJobTimeDto.getOrganIdList() + "]、月份[" + cycleYm + "]的个人运送量数据库信息");
		updateSourceMonthByOrgan(resultDto, cycleYm, mtTaskMap);
		return resultDto;
	}

	/**
	 * @discription 根据部门修改运送来源总量表
	 * @author yangyh@segimail.com       
	 * @created 2018年9月13日 上午10:15:16     
	 * @param resultDto
	 * @param cycleYm
	 * @param mtTaskMap
	 */
	private void updateSourceMonthByOrgan(ResultDto<String, String, Integer> resultDto, Integer cycleYm,
			Map<Integer, List<SourceStatistics>> mtTaskMap) {
		XxlJobLogger.log("==============>开始全量更新运送来源运输总量表信息...");
		// 记录出错的项目id
		List<Integer> erOrganList = new ArrayList<Integer>();
		for (Integer organId : mtTaskMap.keySet()) {
			try {
				long beginTime = System.currentTimeMillis();
				List<SourceStatistics> list = mtTaskMap.get(organId);
				if (!AppUtils.isNotEmpty(list)) {
					XxlJobLogger.log("==============>组织[" + organId + "]没有需要更新更新数据");
					continue;
				}
				reportSourceService.updateSourceMonth(list, organId, cycleYm);
				String logInfo = "==============>成功更新组织[" + organId + "]的运送来源运输总量数据信息[" + list.size() + "]条, 耗时["
						+ (System.currentTimeMillis() - beginTime) + "]毫秒";
				XxlJobLogger.log(logInfo);
			} catch (Exception e) {
				// 记录出错的organId
				erOrganList.add(organId);
				XxlJobLogger.log("================>>更新运送来源运输总量表错误！organId:{" + organId + "},月份:{" + organId + "},错误原因:{"
						+ e + "}");
				LOGGER.error("================>>更新运送来源运输总量表错误！organId:{},月份:{},错误原因:{}", organId, cycleYm, e);
			}
		}
		if (erOrganList != null && erOrganList.size() > 0) {
			resultDto.setList(erOrganList);
		}
	}

	/**
	 * @discription 根据对象属性查出对象list
	 * @author yangyh@segimail.com       
	 * @created 2018年9月13日 上午10:15:16     
	 * @param reportJobTimeDto
	 * @return sourceTaskList
	 */
	private List<SourceStatistics> getSourceList(ReportJobTimeDto reportJobTimeDto) {
		List<SourceStatistics> sourceTaskList = transService.getSourceList(reportJobTimeDto.getGroupOrganId(),
				reportJobTimeDto);
		return sourceTaskList;
	}

	/**
	 * @discription 根据organId分好组
	 * @author yangyh@segimail.com       
	 * @created 2018年9月13日 上午10:15:16     
	 * @param cycleYm
	 * @param sourceTaskList
	 * @return map
	 */
	private Map<Integer, List<SourceStatistics>> groupByOrganId(Integer cycleYm,
			List<SourceStatistics> sourceTaskList) {
		Map<Integer, List<SourceStatistics>> mtTaskMap = new HashMap<Integer, List<SourceStatistics>>();
		List<SourceStatistics> groupList = null;
		// 根据organId分好组,每组的value是不同运送来源的List
		for (SourceStatistics sourceTask : sourceTaskList) {
			// 加上月日
			sourceTask.setCycleYm(cycleYm);
			Integer organId = sourceTask.getOrganId();
			groupList = mtTaskMap.get(organId);
			if (null == groupList) {
				groupList = new ArrayList<SourceStatistics>();
				mtTaskMap.put(organId, groupList);
			}
			groupList.add(sourceTask);
		}
		return mtTaskMap;
	}
}
