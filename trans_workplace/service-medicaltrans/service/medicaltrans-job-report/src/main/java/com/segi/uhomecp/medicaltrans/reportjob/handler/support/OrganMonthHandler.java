package com.segi.uhomecp.medicaltrans.reportjob.handler.support;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.enums.TransTaskTypeEnum;
import com.segi.uhomecp.medicaltrans.reportjob.handler.AbstractHandler;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.AddJudgeParamsDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.OrganMonthStatisticsDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.model.MtTaskExtract;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.utils.JudgeMtTaskExtractUtils;
import com.segi.uhomecp.medicaltrans.reportjob.util.ReportUtils;
import com.segi.uhomecp.wh.common.enums.DataSourceEnum;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * 增量处理项目月运送量表
 * @author Administrator
 *
 */
@Component
public class OrganMonthHandler extends AbstractHandler<List<MtTaskExtract>> {
	
	private static final  Logger logger = LoggerFactory.getLogger(OrganMonthHandler.class);
	
	
	@SuppressWarnings("unused")
	@Override
	public void doInvoke(List<MtTaskExtract> list) {
		AddJudgeParamsDto addJudgeParamsDto = new AddJudgeParamsDto();
		//取数开始时间
		Date paramDate = this.getStartTime();
		addJudgeParamsDto.setParamDate(paramDate);
		//本月执行时间
		Date excTime = this.getExcTime();
		addJudgeParamsDto.setExcTime(excTime);
		//本月截止时间
		Date excEndTime = this.getExcEndTime();
		addJudgeParamsDto.setExcEndTime(excEndTime);
		//上月执行时间
		Date lastExcTime = this.getLastExcTime();
		addJudgeParamsDto.setLastExcTime(lastExcTime);
		
		XxlJobLogger.log("==============>开始增量统计项目月运送量表信息...");
		//logger.debug("增量list的入参=========" + FastjsonUtils.toJsonString(list));
		Map<Integer, OrganMonthStatisticsDto> resultMap = new HashMap<Integer, OrganMonthStatisticsDto>();
		if (!AppUtils.isNotEmpty(list)) {
			return;
		}
		//初始化是否有工作天的字段
		char[] isTaskDayInt = new char[31];
		for (int i = 0; i < 31; i++) {
			isTaskDayInt[i] = '0';
		}
		for (MtTaskExtract mtTaskExtract:list) {
			//1、只取完成状态，2、当状态为已完成，已评价，实际完成时间小于上次执行时间，过滤掉
			if (!JudgeMtTaskExtractUtils.judgeMtTaskExtract(mtTaskExtract,addJudgeParamsDto)) {
				continue;
			}
			//判断该月份是否已经有数据了
			Integer cycleYm = Integer.valueOf(String.valueOf(mtTaskExtract.getBeginTime()).substring(0, 6));
			OrganMonthStatisticsDto organMonthDto = resultMap.get(cycleYm);
			if (null == organMonthDto) {
				organMonthDto = new OrganMonthStatisticsDto();
			}
			OrganMonthStatisticsDto resultDto = getOrganMonthStatisticsDto(mtTaskExtract, organMonthDto);
			//获取工作日
			if (mtTaskExtract.getTaskTime() != null) {
				Integer dayNum = Integer.valueOf(String.valueOf(mtTaskExtract.getTaskTime()).substring(6, 8)) - 1;
				isTaskDayInt[dayNum] = '1';
			}
			if (resultDto != null) {
				resultDto.setIstaskday(new String(isTaskDayInt));
				resultMap.put(cycleYm, resultDto);
			}
		}
		//logger.debug("处理list的结果resultList=========" + FastjsonUtils.toJsonString(resultMap));
		XxlJobLogger.log("==============>结束增量统计项目月运送量表信息...");
		this.getStatisticsMap().putIfAbsent(this.getHandlerName(), resultMap);
	}
	
	@Override
	public String getHandlerName() {
		return MtConstant.ORGAN_MONTH_HANDLER;
	}
	
	/**
	 * 处理项目月运送量对象
	 * @param mtTaskExtract(每次传入的list新对象)
	 * @param organMonthDto(可能存在的老对象)
	 * @return
	 */
	public OrganMonthStatisticsDto getOrganMonthStatisticsDto(MtTaskExtract mtTaskExtract,
			OrganMonthStatisticsDto organMonthDto) {
		Integer cycleYm = Integer.valueOf(String.valueOf(mtTaskExtract.getBeginTime()).substring(0, 6));
		organMonthDto.setCycleYm(cycleYm);
		organMonthDto.setOrganId(mtTaskExtract.getOrganId());
		organMonthDto.setGroupOrganId(mtTaskExtract.getGroupOrganId());
		// 运送总量
		organMonthDto.setTransAmount(organMonthDto.getTransAmount() == null ? 1 : (organMonthDto.getTransAmount() + 1));
		if (TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode().equals(mtTaskExtract.getTaskType())) {
			// 调度任务
			organMonthDto.setDispatchAmount(
					organMonthDto.getDispatchAmount() == null ? 1 : (organMonthDto.getDispatchAmount() + 1));
			// 即时任务响应时间
			Integer respTime = mtTaskExtract.getRespTime() == null ? 0 : mtTaskExtract.getRespTime();
			// 防止太长的三目运算
			if (organMonthDto.getTransInstantTime() == null) {
				organMonthDto.setTransInstantTime(Long.valueOf(respTime));
			} else {
				organMonthDto.setTransInstantTime(organMonthDto.getTransInstantTime() + respTime);
			}
		} else if (TransTaskTypeEnum.TASK_TYPE_SELF.getCode().equals(mtTaskExtract.getTaskType())) {
			// 自主任务
			organMonthDto.setAutonomousAmount(
					organMonthDto.getAutonomousAmount() == null ? 1 : (organMonthDto.getAutonomousAmount() + 1));
		} else if (TransTaskTypeEnum.TASK_TYPE_LOOP.getCode().equals(mtTaskExtract.getTaskType())) {
			// 固定任务
			organMonthDto
					.setFixedAmount(organMonthDto.getFixedAmount() == null ? 1 : (organMonthDto.getFixedAmount() + 1));
		}
		// 任务耗时
		Integer transTime = mtTaskExtract.getTimeConsuming() == null ? 0 : mtTaskExtract.getTimeConsuming();
		if (organMonthDto.getTransTime() == null) {
			organMonthDto.setTransTime(Long.valueOf(transTime));
		} else {
			organMonthDto.setTransTime(organMonthDto.getTransTime() + transTime);
		}
		// 及时任务总量
		if (MtConstant.NO_TIME_OUT.equals(mtTaskExtract.getIsTimeOut())) {
			organMonthDto.setTimelyAmount(
					organMonthDto.getTimelyAmount() == null ? 1 : (organMonthDto.getTimelyAmount() + 1));
		}
		// 满意运输总量
		if (mtTaskExtract.getEvaluate() == null || mtTaskExtract.getEvaluate() == MtConstant.EVALUATE_ZERO
				|| mtTaskExtract.getEvaluate() >= MtConstant.EVALUATE_Three) {
			organMonthDto.setSatisfactionAmount(
					organMonthDto.getSatisfactionAmount() == null ? 1 : (organMonthDto.getSatisfactionAmount() + 1));
		}
		// 数据来源
		if (DataSourceEnum.DATA_SOURCE_IOS.getCode().equals(mtTaskExtract.getDataSource())
				|| DataSourceEnum.DATA_SOURCE_ANDROID.getCode().equals(mtTaskExtract.getDataSource())) {
			organMonthDto.setAppDatasource(
					organMonthDto.getAppDatasource() == null ? 1 : (organMonthDto.getAppDatasource() + 1));
		} else if (DataSourceEnum.DATA_SOURCE_PAD_IOS.getCode().equals(mtTaskExtract.getDataSource())
				|| DataSourceEnum.DATA_SOURCE_PAD_ANDROID.getCode().equals(mtTaskExtract.getDataSource())) {
			organMonthDto.setPadDatasource(
					organMonthDto.getPadDatasource() == null ? 1 : (organMonthDto.getPadDatasource() + 1));
		} else if (DataSourceEnum.DATA_SOURCE_WEB.getCode().equals(mtTaskExtract.getDataSource())) {
			organMonthDto.setWebDatasource(
					organMonthDto.getWebDatasource() == null ? 1 : organMonthDto.getWebDatasource() + 1);
		} else if (DataSourceEnum.DATA_SOURCE_WECHAT.getCode().equals(mtTaskExtract.getDataSource())) {
			organMonthDto.setWechatDatasource(
					organMonthDto.getWechatDatasource() == null ? 1 : organMonthDto.getWechatDatasource() + 1);
		}
		return organMonthDto;
	}
	
	@Override
	public String getDataKey() {
		return ReportUtils.TASK_LIST;
	}
}
