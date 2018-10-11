package com.segi.uhomecp.medicaltrans.reportjob.query.trans.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.medicaltrans.enums.TransStatusEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTaskTypeEnum;
import com.segi.uhomecp.medicaltrans.report.monthamount.source.model.SourceStatistics;
import com.segi.uhomecp.medicaltrans.report.monthamount.transtype.model.TranstypeStatistics;
import com.segi.uhomecp.medicaltrans.report.monthamount.transway.model.TranswayStatistics;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dao.MtTaskExtractMapper;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dao.MtTaskOrganMonthMapper;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.MonthDayDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.MtTaskExtractParamsDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.OrganMonthEntryParamDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.OrganMonthStatisticsDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.OrganTimeStatisticsDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.ReportJobTimeDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.TranstypeParamsDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.model.MtTaskExtract;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.service.TransService;
import com.segi.uhomecp.medicaltrans.utils.DynamicTableSourceKeyHolder;
import com.segi.uhomecp.rest.utils.StringUtils;
import com.segi.uhomecp.wh.common.enums.DataSourceEnum;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.DataTypeConverUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;

@Service
public class TransServiceImpl implements TransService {
	
	public static final Logger logger = LoggerFactory.getLogger(TransServiceImpl.class);
	
	@Autowired
	private MtTaskExtractMapper mtTaskExtractMapper;
	
	@Autowired
	private MtTaskOrganMonthMapper mtTaskOrganMonthMapper;
	
	@Override
	public List<MtTaskExtract> getTaskList(Integer groupOrangId, Integer organId, Date beginTime,
			Date endTime) {
		MtTaskExtractParamsDto example = new MtTaskExtractParamsDto();
		example.setOrganId(organId);
		example.setBeginTime(beginTime);
		example.setEndTime(endTime);
//		example.setUpdateDate(updateDate);
		List<String> statusList = new ArrayList<String>();
//		statusList.add("1"); //未派单
//		statusList.add("2"); //抢单
		statusList.add(TransStatusEnum.TRANS_COMPLETED.getCode()); //完成
		statusList.add(TransStatusEnum.TRANS_CANCEL.getCode()); //取消
		statusList.add(TransStatusEnum.TRANS_UNUSUAL_CLOSE.getCode()); //异常关闭
		example.setStatusList(statusList);
		return mtTaskExtractMapper.getTaskList(groupOrangId, example);
	}

	/**
	 * 根据项目id 获取项目月运送量
	 */
	@Override
	public List<OrganMonthStatisticsDto> getOrganMonthList(int groupOrganId, ReportJobTimeDto reportJobTimeDto) {
		OrganMonthEntryParamDto organMonthEntryParamDto = new OrganMonthEntryParamDto();
		//取状态为已完成的
		String status = TransStatusEnum.TRANS_COMPLETED.getCode();
		organMonthEntryParamDto.setStatus(status);
		//任务状态
		String disTask = TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode();
		organMonthEntryParamDto.setDisTask(disTask);
		String selfTask = TransTaskTypeEnum.TASK_TYPE_SELF.getCode();
		organMonthEntryParamDto.setSelfTask(selfTask);
		String loopTask = TransTaskTypeEnum.TASK_TYPE_LOOP.getCode();
		organMonthEntryParamDto.setLoopTask(loopTask);
		//运送来源
		String ios = DataSourceEnum.DATA_SOURCE_IOS.getCode();
		organMonthEntryParamDto.setIos(ios);
		String padIos = DataSourceEnum.DATA_SOURCE_PAD_IOS.getCode();
		organMonthEntryParamDto.setPadIos(padIos);
		String android = DataSourceEnum.DATA_SOURCE_ANDROID.getCode();
		organMonthEntryParamDto.setAndroid(android);
		String padandroid = DataSourceEnum.DATA_SOURCE_PAD_ANDROID.getCode();
		organMonthEntryParamDto.setPadandroid(padandroid);
		String web = DataSourceEnum.DATA_SOURCE_WEB.getCode();
		organMonthEntryParamDto.setWeb(web);
		String wechat = DataSourceEnum.DATA_SOURCE_WECHAT.getCode();
		organMonthEntryParamDto.setWechat(wechat);
		
		organMonthEntryParamDto.setGroupOrganId(reportJobTimeDto.getGroupOrganId());
		organMonthEntryParamDto.setOrganIdList(reportJobTimeDto.getOrganIdList());
		organMonthEntryParamDto.setStartTime(reportJobTimeDto.getStartTime());
		organMonthEntryParamDto.setEndTime(reportJobTimeDto.getEndTime());
		organMonthEntryParamDto.setExcDate(reportJobTimeDto.getExcDate());
		
		//分表的表尾名
		String tableIndex = DynamicTableSourceKeyHolder.getDataSourceKey();
		organMonthEntryParamDto.setTableIndex(tableIndex);
		List<OrganMonthStatisticsDto> organMonthStatisticsDtoList =  mtTaskOrganMonthMapper.getOrganMonthList(organMonthEntryParamDto);
		//对查到的list补充完成月份和对应是否有工单天数的字段
		Integer cycleYm = Integer.valueOf(reportJobTimeDto.getExeYearMonth());
		Map<Integer, String> organWorkMap = reportJobTimeDto.getOrganWorkMap();
		if (!AppUtils.isNotEmpty(organMonthStatisticsDtoList)) {
			return null;
		}
		for (OrganMonthStatisticsDto dto : organMonthStatisticsDtoList) {
			dto.setCycleYm(cycleYm);
			String istaskday = organWorkMap.get(dto.getOrganId());
			dto.setIstaskday(istaskday);
		}
		return organMonthStatisticsDtoList;
	}
	
	/**
	 * 通过年月获取月的开始时间和结束时间（注意：当月是开始时间到前一天23:59分，如果是当月1号，开始时间和结束时间一致）
	 */
	@Override
	public ReportJobTimeDto getJobEnterParam(Integer groupOrganId, List<Integer> organIdList, String exeYearMonth) {
		if (groupOrganId == null || !AppUtils.isNotEmpty(organIdList) || StringUtils.isNullOrEmpty(exeYearMonth)) {
			logger.error("查询job入参为空");
			return null;
		}
		ReportJobTimeDto reportJobTimeDto = new ReportJobTimeDto();

		int year = Integer.valueOf(exeYearMonth.substring(0, 4));
		int month = Integer.valueOf(exeYearMonth.substring(4));

		Long startTime = Long.valueOf(DateUtil.getFirstTimeOfMonth(year, month));
		// 获取结束时间
		Long endTime = getEndtime(groupOrganId, exeYearMonth, year, month);
		reportJobTimeDto.setStartTime(startTime);
		reportJobTimeDto.setEndTime(endTime);
		// 直接获取月的结束时间
		Long monthEndTime = Long.valueOf(DateUtil.getLastTimeOfMonth(year, month));
		reportJobTimeDto.setMonthEndTime(monthEndTime);

		// 查询每个项目当月有工单的日期字段
		Map<Integer, String> organWorkMap = new HashMap<Integer, String>();
		for (Integer organId : organIdList) {
			// 根据项目、年月查询当月的工单有无情况
			String isTaskDay = getIsTaskDay(groupOrganId, organId, exeYearMonth);
			organWorkMap.put(organId, isTaskDay);
		}
		reportJobTimeDto.setOrganWorkMap(organWorkMap);
		reportJobTimeDto.setGroupOrganId(groupOrganId);
		reportJobTimeDto.setOrganIdList(organIdList);
		reportJobTimeDto.setExeYearMonth(exeYearMonth);
		return reportJobTimeDto;
	}
	
	/**
	 * 获取结束时间
	 * @param exeYearMonth
	 * @param year
	 * @param month
	 * @return
	 */
	public Long getEndtime(Integer groupOrganId, String exeYearMonth, int year, int month) {
		Long endTime = null;
		Date nowDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
		String yearMonth = sdf.format(nowDate);
		String day = sdf2.format(nowDate);
		if (yearMonth.equals(exeYearMonth)) {
			if (day.equals("01")) {
				// 每月一号没法取前一天数据，开始时间和结束时间都是月开始时间
				endTime = Long.valueOf(DateUtil.getFirstTimeOfMonth(year, month));
			} else {
				// 当月是开始时间到前一天23:59分
				endTime = Long.valueOf(DateUtil.getNewDayBeforeDayTime(year, month));
			}
		} else {
			// 不为本月取月份的最后一天的23：59分
			endTime = Long.valueOf(DateUtil.getLastTimeOfMonth(year, month));
		}
		return endTime;
	}
	
	/**
	 * 获取每天是否有运单
	 * @param groupOrganId
	 * @param organId
	 * @param exeYearMonth
	 * @return
	 */
	public String getIsTaskDay(int groupOrganId, int organId, String exeYearMonth) {
		MonthDayDto monthDayDto = new MonthDayDto();
		monthDayDto.setGroupOrganId(groupOrganId);
		monthDayDto.setOrganId(organId);
		// 取状态为已完成的
		String status = TransStatusEnum.TRANS_COMPLETED.getCode();
		monthDayDto.setStatus(status);

		List<Integer> yearMonthDayList = new ArrayList<Integer>();
		for (int i = 1; i < 32; i++) {
			String numDay = String.format("%02d", i);
			Integer yearMonthDay = Integer.valueOf(exeYearMonth + numDay);
			yearMonthDayList.add(yearMonthDay);
		}
		monthDayDto.setYearMonthDayList(yearMonthDayList);
		
		//分表的表尾名
		String tableIndex = DynamicTableSourceKeyHolder.getDataSourceKey();
		monthDayDto.setTableIndex(tableIndex);
		List<MtTaskExtract> mtTaskExtractList = mtTaskOrganMonthMapper.getIsTaskDay(monthDayDto);
		// 初始化是否有工作天的字段
		char[] isTaskDayInt = new char[31];
		for (int i = 1; i < 32; i++) {
			String numDay = String.format("%02d", i);
			Integer yearMonthDay = Integer.valueOf(exeYearMonth + numDay);
			isTaskDayInt[i - 1] = '0';
			for (MtTaskExtract mtTaskExtract : mtTaskExtractList) {
				if (yearMonthDay.equals(mtTaskExtract.getTaskTime())) {
					isTaskDayInt[i - 1] = '1';
					break;
				}
			}
		}
		String result = new String(isTaskDayInt);
		return result;
	}
	
	/**
	 * 获取月份的开始时间到结束时间的list
	 * @param year
	 * @param month
	 * @return
	 */
	public List<ReportJobTimeDto> getReportJobTimeDtoList(Integer groupOrganId, int year, int month) {
		List<ReportJobTimeDto> result = new ArrayList<ReportJobTimeDto>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);

		int dayNumOfMonth = DateUtil.getDaysByYearMonth(year, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);	// 从一号开始

		for (int i = 0; i < dayNumOfMonth; i++, cal.add(Calendar.DATE, 1)) {
			ReportJobTimeDto reportJobTimeDto = new ReportJobTimeDto();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			String startTime = simpleDateFormat.format(cal.getTime());
			reportJobTimeDto.setStartTime(Long.valueOf(startTime));

			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			String endTime = simpleDateFormat.format(cal.getTime());
			reportJobTimeDto.setEndTime(Long.valueOf(endTime));
			result.add(reportJobTimeDto);
		}
		return result;
	}

	/**
	 * @discription 根据时间对象去业务数据中取指定时间类型为已完成的任务单
	 * @author yangyh@segimail.com       
	 * @created 2018年8月2日 上午10:52:35     
	 * @param reportJobTimeDto
	 * @return
	 */
	@Override
	public List<TranstypeStatistics> getTranstypeList(int groupOrganId, ReportJobTimeDto reportJobTimeDto) {
		TranstypeParamsDto transtypeParamsDto = BeanCopierUtils.copyProperties(reportJobTimeDto,
				TranstypeParamsDto.class, true);
		transtypeParamsDto.setOrganIdListStr(AppUtils.listToString(reportJobTimeDto.getOrganIdList(), ','));
		transtypeParamsDto.setStatus(TransStatusEnum.TRANS_COMPLETED.getCode());
		// 将organIdList拼接
		return mtTaskExtractMapper.getTranstypeList(transtypeParamsDto);
	}
	
	/**
	 * @discription 根据条件对象去业务数据中取运送方式list
	 * @author yangyh@segimail.com       
	 * @created 2018年9月13日 上午10:15:16     
	 * @param reportJobTimeDto
	 * @return
	 */
	@Override
	public List<TranswayStatistics> getTranswayList(int groupOrganId, ReportJobTimeDto reportJobTimeDto) {
		TranstypeParamsDto transtypeParamsDto = BeanCopierUtils.copyProperties(reportJobTimeDto,
				TranstypeParamsDto.class, true);
		transtypeParamsDto.setOrganIdListStr(AppUtils.listToString(reportJobTimeDto.getOrganIdList(), ','));
		transtypeParamsDto.setStatus(TransStatusEnum.TRANS_COMPLETED.getCode());
		// 将organIdList拼接
		return mtTaskExtractMapper.getTranswayList(transtypeParamsDto);
	}
	
	/**
	 * @discription 根据条件对象去业务数据中取运送来源list
	 * @author yangyh@segimail.com       
	 * @created 2018年9月13日 上午10:15:16     
	 * @param reportJobTimeDto
	 * @return
	 */
	@Override
	public List<SourceStatistics> getSourceList(int groupOrganId, ReportJobTimeDto reportJobTimeDto) {
		TranstypeParamsDto transtypeParamsDto = BeanCopierUtils.copyProperties(reportJobTimeDto,
				TranstypeParamsDto.class, true);
		transtypeParamsDto.setOrganIdListStr(AppUtils.listToString(reportJobTimeDto.getOrganIdList(), ','));
		transtypeParamsDto.setStatus(TransStatusEnum.TRANS_COMPLETED.getCode());
		// 将organIdList拼接
		return mtTaskExtractMapper.getSourceList(transtypeParamsDto);
	}

	@Override
	public List<MtTaskExtract> getTaskListByCreate(Integer groupOrangId,
			Integer organId, Date beginTime, Date endTime) {
		MtTaskExtractParamsDto example = new MtTaskExtractParamsDto();
		example.setOrganId(organId);
		example.setBeginTimeLog(DataTypeConverUtils.formatDateToLongYYYYMMDDHHMMSS(beginTime));
		example.setEndTimeLog(DataTypeConverUtils.formatDateToLongYYYYMMDDHHMMSS(endTime));
//		example.setUpdateDate(updateDate);
//		List<String> statusList = new ArrayList<String>();
//		statusList.add("1"); //未派单
//		statusList.add("2"); //抢单
//		statusList.add("5"); //完成
//		statusList.add("6"); //取消
//		statusList.add("9"); //异常关闭
//		example.setStatusList(statusList);
		return mtTaskExtractMapper.getTaskListByCreate(groupOrangId, example);
	}

	/**
	 *  查出各个时段运送量总数据
	 */
	@Override
	public List<OrganTimeStatisticsDto> getOrganTimeList(int groupOrganId, ReportJobTimeDto reportJobTimeDto) {
		OrganMonthEntryParamDto organMonthEntryParamDto = new OrganMonthEntryParamDto();
		//取状态为已完成的
		String status = TransStatusEnum.TRANS_COMPLETED.getCode();
		organMonthEntryParamDto.setStatus(status);
		
		organMonthEntryParamDto.setGroupOrganId(reportJobTimeDto.getGroupOrganId());
		organMonthEntryParamDto.setOrganIdList(reportJobTimeDto.getOrganIdList());
		organMonthEntryParamDto.setStartTime(reportJobTimeDto.getStartTime());
		organMonthEntryParamDto.setEndTime(reportJobTimeDto.getEndTime());
		organMonthEntryParamDto.setExcDate(reportJobTimeDto.getExcDate());
		
		//分表的表尾名
		String tableIndex = DynamicTableSourceKeyHolder.getDataSourceKey();
		organMonthEntryParamDto.setTableIndex(tableIndex);
		List<OrganTimeStatisticsDto> organTimeStatisticsDtoList =  mtTaskOrganMonthMapper.getOrganTimeList(organMonthEntryParamDto);
		//对查到的list补充完成月份
		Integer cycleYm = Integer.valueOf(reportJobTimeDto.getExeYearMonth());
		for (OrganTimeStatisticsDto dto : organTimeStatisticsDtoList) {
			dto.setCycleYm(cycleYm);
		}
		return organTimeStatisticsDtoList;
	}
}
