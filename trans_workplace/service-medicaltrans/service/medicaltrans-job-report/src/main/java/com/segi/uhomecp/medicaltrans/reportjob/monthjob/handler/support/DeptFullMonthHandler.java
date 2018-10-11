package com.segi.uhomecp.medicaltrans.reportjob.monthjob.handler.support;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocation;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.model.DeptVolumeMonth;
import com.segi.uhomecp.medicaltrans.reportjob.monthjob.handler.JobFullMonthHandler;
import com.segi.uhomecp.medicaltrans.reportjob.monthjob.utils.CheckParamUtils;
import com.segi.uhomecp.medicaltrans.reportjob.query.deptvolumemonth.service.CountDeptMonthService;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.ReportJobTimeDto;
import com.segi.uhomecp.medicaltrans.reportjob.report.deptvolumemonth.service.ReportDeptVolumeMonthService;
import com.segi.uhomecp.medicaltrans.reportjob.report.deptvolumemonth.utils.DeptCommonUtils;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.wh.common.dto.ResultDto;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.xxl.job.core.log.XxlJobLogger;

@Component(value = "deptFullMonthHandler")
public class DeptFullMonthHandler implements JobFullMonthHandler {

	private static final  Logger LOGGER = LoggerFactory.getLogger(DeptFullMonthHandler.class);

	@Autowired
	private CountDeptMonthService countDeptMonthService;
	
	@Autowired
	private DeptCommonUtils deptCommonUtils;

	@Autowired
	private ReportDeptVolumeMonthService reportDeptVolumeMonthService;

	@Override
	public ResultDto<String, String, Integer> invoke(ReportJobTimeDto reportJobTimeDto) {
		ResultDto<String, String, Integer> resultDto = CheckParamUtils.check(reportJobTimeDto, "项目月运输量表");
		XxlJobLogger.log("deptFullMonthHandler==============>开始按月全量统计科室信息...");
		XxlJobLogger.log("deptFullMonthHandler==============>params: {" 
				+ FastjsonUtils.toJsonString(reportJobTimeDto) + "}");
		if (!resultDto.getIsSucc()) {
			XxlJobLogger.log("deptFullMonthHandler==============>验证失败 不用继续");
			// 验证失败 不用继续
			return resultDto;
		}
		// 记录出错的项目id
		List<Integer> erOrganList = new ArrayList<Integer>();
		List<Integer> organIdList = reportJobTimeDto.getOrganIdList();
		String cycleYm = reportJobTimeDto.getExeYearMonth();
		// 获取全部科室
		XxlJobLogger.log("deptFullMonthHandler==============>获取全部科室 Start");
		List<MtBuildLocation> buildList = deptCommonUtils.queryLocationInfoByOrganIdList(organIdList);
		XxlJobLogger.log("==============>成功从科室缓存获得科室信息[" + buildList.size() + "]条");
		// 对报表库数据进行统计 以organId分类
		XxlJobLogger.log("deptFullMonthHandler==============>对报表库数据进行统计 以organId分类 Start");
		Map<Integer, List<DeptVolumeMonth>> deptMap = countDeptInfo(buildList, reportJobTimeDto);
		XxlJobLogger.log("==============>结束全量统计科室总量信息 开始写入科室运送量表");
		// 把统计数据保存到报表库
		saveDeptInfo(organIdList, cycleYm, resultDto, deptMap, erOrganList);
		return resultDto;
	}
	
	/**
	 * @Title: sqveDeptInfo 
	 * @Description: 保存科室统计信息 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月14日下午4:17:11
	 */
	private void saveDeptInfo(List<Integer> organIdList, String cycleYm, ResultDto<String, String, Integer> resultDto,
			Map<Integer, List<DeptVolumeMonth>> deptMap, List<Integer> erOrganList) {
		// 通过organId为单位修改数据库
		for (Integer organId : organIdList) {
			try {
				reportDeptVolumeMonthService.saveDeptInfo(organId, cycleYm, resultDto, deptMap);
			} catch (Exception e) {
				StringWriter sw = new StringWriter();
				e.printStackTrace(new PrintWriter(sw, true));
				XxlJobLogger.log("================>>更新科室运输量表错误！organId:{" + organId + "},月份:{" + cycleYm + "},错误原因:{"
						+ sw.toString() + "}");
				LOGGER.error("================>>更新科室运输量表错误！organId:{},月份:{},错误原因:{}", organId, cycleYm, e);
				resultDto.setIsSucc(false);
				erOrganList.add(organId);
				resultDto.setMessage("统计科室运送量失败");
			}
		}
		if (erOrganList != null && erOrganList.size() > 0) {
			resultDto.setList(erOrganList);
		}
	}

	/**
	 * @Title: countDeptInfo 
	 * @Description: 统计科室性息 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月14日下午3:54:03
	 */
	private Map<Integer, List<DeptVolumeMonth>> countDeptInfo(List<MtBuildLocation> buildList, ReportJobTimeDto reportJobTimeDto) {
		// 一次查询统计5000条科室信息
		List<DeptVolumeMonth> deptmonthList = new ArrayList<DeptVolumeMonth>();
		Integer groupOrganId = reportJobTimeDto.getGroupOrganId();
		List<MtBuildLocation> newList = null;
		Paginator page = null;
		if (MtConstant.MAX_QUERY_LENGTH < buildList.size()) {
			page = new Paginator(1, MtConstant.MAX_QUERY_LENGTH, buildList.size());
			for (int i = 0; i < page.getTotalPages(); i++) {
				page = new Paginator(i + 1, MtConstant.MAX_QUERY_LENGTH, buildList.size());
				newList = buildList.subList(page.getStartRow() - 1, page.getEndRow());
				XxlJobLogger.log("deptFullMonthHandler==============>countDeptInfo Start i==>{}" + i + "{}groupOrganId{}" + groupOrganId);
				deptmonthList.addAll(countDeptMonthService.queryCountDeptInfo(groupOrganId, newList, reportJobTimeDto));
			}
		} else {
			XxlJobLogger.log("deptFullMonthHandler==============>countDeptInfo Start groupOrganId{}" + groupOrganId);
			deptmonthList = countDeptMonthService.queryCountDeptInfo(groupOrganId, buildList, reportJobTimeDto);
		}
		XxlJobLogger.log("统计出来科室对象--------------" + deptmonthList.size());
		XxlJobLogger.log("deptFullMonthHandler==============>countDeptInfo End");
		return AppUtils.listGroup2Map(deptmonthList, DeptVolumeMonth.class,
				"organId", DeptVolumeMonth.class);
	}
}
