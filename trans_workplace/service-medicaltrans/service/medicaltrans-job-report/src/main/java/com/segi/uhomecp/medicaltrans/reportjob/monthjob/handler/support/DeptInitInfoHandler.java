package com.segi.uhomecp.medicaltrans.reportjob.monthjob.handler.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocation;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.model.DeptVolumeMonth;
import com.segi.uhomecp.medicaltrans.reportjob.monthjob.handler.JobFullMonthHandler;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.ReportJobTimeDto;
import com.segi.uhomecp.medicaltrans.reportjob.report.deptvolumemonth.service.ReportDeptVolumeMonthService;
import com.segi.uhomecp.medicaltrans.reportjob.report.deptvolumemonth.utils.DeptCommonUtils;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.dto.ResultDto;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.DataTypeConverUtils;
import com.xxl.job.core.log.XxlJobLogger;
	
@Component(value = "deptInitInfoHandler")
public class DeptInitInfoHandler implements JobFullMonthHandler {

	private static final  Logger LOGGER = LoggerFactory.getLogger(DeptFullMonthHandler.class);

	@Autowired
	private DeptCommonUtils deptCommonUtils;

	@Autowired
	private ReportDeptVolumeMonthService reportDeptVolumeMonthService;
	
	@Override
	public ResultDto<String, String, Integer> invoke(ReportJobTimeDto dto) {
		ResultDto<String, String, Integer> resultDto = this.check(dto, "项目月运输量表");
		XxlJobLogger.log("==============>开始初始化科室信息...");
		XxlJobLogger.log("==============>params: {" 
				+ FastjsonUtils.toJsonString(dto) + "}");
		if (!resultDto.getIsSucc()) {
			// 验证失败 不用继续
			return resultDto;
		}
		// 记录出错的项目id
		List<Integer> erOrganList = new ArrayList<Integer>();
		// 获取全部科室
		List<Integer> organIdList = dto.getOrganIdList();
		List<MtBuildLocation> buildList = deptCommonUtils.queryLocationInfoByOrganIdList(organIdList);
		// 计算科室报表信息剔除掉报表里纯在的对象
		buildList = reportDeptVolumeMonthService.countDeptInitInfo(buildList, dto.getExeYearMonth());
		XxlJobLogger.log("==============>开始初始化科室信息除掉报表里纯在的对象后还有..." + buildList.size());
		if (AppUtils.isNotEmpty(buildList)) {
			// 科室数据初始化成科室报表对象
			Map<Integer, List<DeptVolumeMonth>> deptMap = deptInitInfo(buildList, dto);
			// 保存科室报表初始化对象
			sqveDeptInitInfo(organIdList, resultDto, deptMap, erOrganList);
		}
		return resultDto;
	}

	/**
	 * @Title: check 
	 * @Description: 入参检查 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月16日下午6:55:47
	 */
	private ResultDto<String, String, Integer> check(ReportJobTimeDto reportJobTimeDto,
			String name) {
		ResultDto<String, String, Integer> resultDto = new ResultDto<>(true, name + "更新成功！"); 
	    if (!AppUtils.isNotEmpty(reportJobTimeDto.getOrganIdList())) {
	      resultDto.setIsSucc(false);
	      resultDto.setMessage(name + "更新失败！项目Id集合为空！");
	      return resultDto;
	    }
	    return resultDto;
	}

	/**
	 * @Title: sqveDeptInitInfo 
	 * @Description: TODO 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月16日上午11:31:32
	 */
	private void sqveDeptInitInfo(List<Integer> organIdList,
			ResultDto<String, String, Integer> resultDto, Map<Integer, List<DeptVolumeMonth>> deptMap,
			List<Integer> erOrganList) {
		// 通过organId为单位修改数据库
		for (Integer organId : organIdList) {
			try {
				// 保存统计数据
				reportDeptVolumeMonthService.saveDeptVolumeMonthInfo(deptMap.get(organId));
			} catch (Exception e) {
				LOGGER.error("sqveDeptInitInfo 初始化科室信息错误 项目为:-------" + organId, e);
				XxlJobLogger.log("sqveDeptInitInfo 初始化科室信息错误 项目为:-------" + organId, e);
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
	 * @Title: DeptInitInfo 
	 * @Description: 科室数据初始化成科室报表对象
	 * @author liuyi@segimail.com 
	 * @date 2018年8月16日上午10:01:37
	 */
	private Map<Integer, List<DeptVolumeMonth>> deptInitInfo(
			List<MtBuildLocation> buildList, ReportJobTimeDto dto) {
		String yearMonth = dto.getExeYearMonth();
		List<DeptVolumeMonth> monthList = new ArrayList<>();
		Long nowDate = DataTypeConverUtils.formatDateToLongYYYYMMDDHHMMSS(new Date());
		for (MtBuildLocation mtBuildLocation : buildList) {
			if (mtBuildLocation == null) {
				continue;
			}
			if (Constant.STATUS_CD_DEL.equals(mtBuildLocation.getStatus())) {
				// 如果是无效科室就不新增
				continue;
			}
			// 获得科室信息报表对象
			DeptVolumeMonth deptMonth = deptCommonUtils.getDeptMonthBean(mtBuildLocation, nowDate);
			deptMonth.setCycleYm(Integer.valueOf(yearMonth));
			monthList.add(deptMonth);
		}
		return AppUtils.listGroup2Map(monthList, DeptVolumeMonth.class,
				"organId", DeptVolumeMonth.class);
	}
}
