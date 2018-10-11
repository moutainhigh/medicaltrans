package com.segi.uhomecp.medicaltrans.report.organmonth.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.enums.TransTaskTypeEnum;
import com.segi.uhomecp.medicaltrans.report.common.dto.CommonDto;
import com.segi.uhomecp.medicaltrans.report.monthamount.organ.model.OrganMonthStatistics;
import com.segi.uhomecp.medicaltrans.report.monthamount.organ.model.OrganMonthStatisticsCriteria;
import com.segi.uhomecp.medicaltrans.report.monthamount.organ.service.OrganMonthStatisticsService;
import com.segi.uhomecp.medicaltrans.report.monthamount.transtype.dao.ReportCommonMapper;
import com.segi.uhomecp.medicaltrans.report.organmonth.dto.AmountMonthDto;
import com.segi.uhomecp.medicaltrans.report.organmonth.dto.TaskDateSourceDto;
import com.segi.uhomecp.medicaltrans.report.organmonth.dto.TaskTypeDto;
import com.segi.uhomecp.medicaltrans.report.organmonth.dto.TransProfileDto;
import com.segi.uhomecp.medicaltrans.report.organmonth.service.OrganMonthAmountService;


/**
 * 项目月运送量接口实现
 * @author Administrator
 *
 */
@Service
public class OrganMonthAmountServiceImpl implements OrganMonthAmountService {

	@Autowired
	private OrganMonthStatisticsService organMonthStatisticsService;
	
	@Autowired
	private ReportCommonMapper organMonthStatisticsMapper;
	
	private static final String[] monthShow = { "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月",
			"12月"};

	private static final String[] monthString = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
			"12"};
	/**
	 * 运送概况查询
	 */
	@Override
	public TransProfileDto getTransProfile(String organId, String cycleY) {
		TransProfileDto transProfileDto = new TransProfileDto();
		Integer startYm = Integer.valueOf(cycleY + "01");
		Integer endYm = Integer.valueOf(cycleY + "12");
		OrganMonthStatisticsCriteria example = new OrganMonthStatisticsCriteria();
		OrganMonthStatisticsCriteria.Criteria criteria = example.createCriteria();
		criteria.andOrganIdEqualTo(Integer.valueOf(organId));
		criteria.andCycleYmBetween(startYm, endYm);
		List<OrganMonthStatistics> organMonthList = organMonthStatisticsService.selectByExample(example);
		// 运输总量
		Long transAmount = 0L;
		// 即时任务总时间
		Long transInstantTime = 0L;
		// 即时任务个数(调度任务)
		Long dispatchAmount = 0L;
		// 及时任务总量
		Long timelyAmount = 0L;
		// 满意任务总量
		Long satisfactionAmount = 0L;
		if (organMonthList != null && organMonthList.size() > 0) {
			for (OrganMonthStatistics statistics : organMonthList) {
				transAmount += (statistics.getTransAmount() == null ? 0 : statistics.getTransAmount());
				transInstantTime += (statistics.getTransInstantTime() == null ? 0 : statistics.getTransInstantTime());
				dispatchAmount += (statistics.getDispatchAmount() == null ? 0 : statistics.getDispatchAmount());
				timelyAmount += (statistics.getTimelyAmount() == null ? 0 : statistics.getTimelyAmount());
				satisfactionAmount += (statistics.getSatisfactionAmount() == null ? 0
						: statistics.getSatisfactionAmount());
			}
		}
		DecimalFormat df = new DecimalFormat("######0.00");
		DecimalFormat df2 = new DecimalFormat("######0.0000");
		// 计算运送任务总数、即时任务平均响应时间、及时率、满意率
		if (transAmount == 0) {
			transProfileDto.setTransAmountTotal("0");
			transProfileDto.setTimelyRatio("0.0000");
			transProfileDto.setSatisfactionRatio("0.0000");
		} else {
			transProfileDto.setTransAmountTotal(transAmount.toString());
			transProfileDto.setTimelyRatio(df2.format(mathDivBybit(timelyAmount, transAmount, 4)));
			transProfileDto.setSatisfactionRatio(df2.format(mathDivBybit(satisfactionAmount, transAmount, 4)));
		}
		if (dispatchAmount == 0) {
			transProfileDto.setInstantTaskTimeAverage("0.00");
		} else {
			// 记得乘以60，按分钟计算
			transProfileDto
					.setInstantTaskTimeAverage(df.format(mathDivBybit(transInstantTime, dispatchAmount * 60, 2)));
		}
		// 计算运送员平均数量和平均每月任务数
		Map<String, String> resultAverage = getAverage(organId, cycleY);
		transProfileDto.setTransUserAmountAverage(resultAverage.get("transUserAmountAverage"));
		transProfileDto.setTransAmountAverage(resultAverage.get("transAmountAverage"));
		return transProfileDto;
	}

	/**
	 * 取运送员平均数量和平均每月任务数
	 * 
	 * @return
	 */
	public Map<String, String> getAverage(String organId, String cycleY) {
		DecimalFormat df = new DecimalFormat("######0.00");
		Map<String, String> result = new HashMap<String, String>();
		// 开始月份
		Integer startYm = Integer.valueOf(cycleY + "01");
		OrganMonthStatisticsCriteria example = new OrganMonthStatisticsCriteria();
		OrganMonthStatisticsCriteria.Criteria criteria = example.createCriteria();
		criteria.andOrganIdEqualTo(Integer.valueOf(organId));
		// 查看结束月份(特殊情况)---(运送员平均数量,平均每月任务数 要取上个月)
		String endYm = getStartEndym(cycleY, false);
		// 当endYm为空，说明当月为1月，两个平均数直接为0
		if (StringUtils.isEmpty(endYm)) {
			result.put("transUserAmountAverage", "0.00");
			result.put("transAmountAverage", "0.00");
			return result;
		}
		criteria.andCycleYmBetween(startYm, Integer.valueOf(endYm));
		List<OrganMonthStatistics> organMonthList = organMonthStatisticsService.selectByExample(example);
		// 运送员总数量
		Long transUserAmount = 0L;
		// 任务数总数量
		Long transAmount = 0L;
		// 有运输单的月份个数（取运送员平均数量和平均每月任务数要排除运输单为0的月份）
		Long monthNum = 0L;
		if (organMonthList != null && organMonthList.size() > 0) {
			for (OrganMonthStatistics statistics : organMonthList) {
				if(statistics == null){
					continue;
				}
				transUserAmount += (statistics.getTransUserAmount() == null ? 0 
						: statistics.getTransUserAmount());
				transAmount += (statistics.getTransAmount() == null ? 0 : statistics.getTransAmount());
				if ( null != statistics.getTransAmount() && statistics.getTransAmount() != 0) {
					monthNum++;
				}
			}
		}
		if (monthNum == 0) {
			result.put("transUserAmountAverage", "0.00");
			result.put("transAmountAverage", "0.00");
		} else {
			result.put("transUserAmountAverage", df.format(mathDivBybit(transUserAmount, monthNum, 2)));
			result.put("transAmountAverage", df.format(mathDivBybit(transAmount, monthNum, 2)));
		}
		return result;
	}

	/**
	 * 报表每月运输趋势分析
	 */
	@Override
	public List<AmountMonthDto> queryAmountMonth(String organId, String cycleY) {
		List<AmountMonthDto> result = new ArrayList<AmountMonthDto>();
		 Calendar cal = Calendar.getInstance();  
		 int month = cal.get(Calendar.MONTH) + 1; 
		 int year = cal.get(Calendar.YEAR);
		
		OrganMonthStatisticsCriteria example = new OrganMonthStatisticsCriteria();
		OrganMonthStatisticsCriteria.Criteria criteria = example.createCriteria();
		criteria.andOrganIdEqualTo(Integer.valueOf(organId));
		criteria.andCycleYmBetween(Integer.valueOf(cycleY + "01"), Integer.valueOf(cycleY + "12"));
		List<OrganMonthStatistics> organMonthList = organMonthStatisticsService.selectByExample(example);
		for (int i = 0; i < 12; i++) {
			String cycleYm = cycleY + monthString[i];
			AmountMonthDto dto = new AmountMonthDto();
			dto.setCycleYm(monthShow[i]);
			//只展示当月和当月之前的
			if (i < month || !cycleY.equals(String.valueOf(year))) {
				dto.setTransAmount("0");
				for (OrganMonthStatistics statistics : organMonthList) {
					if (statistics != null && cycleYm.equals(statistics.getCycleYm().toString())) {
						dto.setTransAmount(
								statistics.getTransAmount() == null ? "0" : statistics.getTransAmount().toString());
						break;
					}
				}
			}
			result.add(dto);
		}
		return result;
	}

	/**
	 * 通过年得到结束月份，当isSameMonth为true到当月，false到上月
	 * 
	 * @param cycleY
	 * @param isSameMonth
	 * @return
	 */
	public String getStartEndym(String cycleY, boolean isSameMonth) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		StringBuilder endYM = new StringBuilder();
		// 判断是否是当年
		if (cycleY.equals(String.valueOf(year))) {
			if (!isSameMonth) {
				// 取上月，一月没有上月
				if (month == 1) {
					return "";
				} else {
					cal.set(year, month - 2, 1, 0, 0, 0);
					endYM.append(sdf.format(cal.getTime()));
				}
			} else {
				endYM.append(sdf.format(cal.getTime()));
			}

		} else {
			endYM.append(cycleY).append("12");
		}
		return endYM.toString();
	}

	/**
	 * 及时率/满意率趋势分析
	 */
	@Override
	public List<AmountMonthDto> queryTimelyAmountMonth(String organId, String cycleY) {
		List<AmountMonthDto> result = new ArrayList<AmountMonthDto>();
		 Calendar cal = Calendar.getInstance();  
		 int month = cal.get(Calendar.MONTH) + 1; 
		 int year = cal.get(Calendar.YEAR);
		OrganMonthStatisticsCriteria example = new OrganMonthStatisticsCriteria();
		OrganMonthStatisticsCriteria.Criteria criteria = example.createCriteria();
		criteria.andOrganIdEqualTo(Integer.valueOf(organId));
		criteria.andCycleYmBetween(Integer.valueOf(cycleY + "01"), Integer.valueOf(cycleY + "12"));
		List<OrganMonthStatistics> organMonthList = organMonthStatisticsService.selectByExample(example);
		DecimalFormat df = new DecimalFormat("######0.0000");
		for (int i = 0; i < 12; i++) {
			String cycleYm = cycleY + monthString[i];
			AmountMonthDto dto = new AmountMonthDto();
			dto.setCycleYm(monthShow[i]);
			if(i<month || !cycleY.equals(String.valueOf(year))){
				dto.setSatisfactionRatio("0.0000");
				dto.setTimelyRatio("0.0000");
				for (OrganMonthStatistics statistics : organMonthList) {
					if (statistics != null && cycleYm.equals(statistics.getCycleYm().toString())) {
						Integer transAmount = statistics.getTransAmount() == null ? 0 : statistics.getTransAmount();
						Integer timelyAmount = statistics.getTimelyAmount() == null ? 0 : statistics.getTimelyAmount();
						Integer satisfactionAmount = statistics.getSatisfactionAmount() == null ? 0
								: statistics.getSatisfactionAmount();
						if (transAmount != 0) {
							dto.setSatisfactionRatio(df.format(mathDivIntegerBybit(satisfactionAmount, transAmount, 4)));
							dto.setTimelyRatio(df.format(mathDivIntegerBybit(timelyAmount, transAmount, 4)));
						}
						break;
					}
				}
			}
			result.add(dto);
		}
		return result;
	}

	/**
	 * 运送员每月平均运送量趋势分析
	 */
	@Override
	public List<AmountMonthDto> queryUserAmountMonth(String organId, String cycleY) {
		List<AmountMonthDto> result = new ArrayList<AmountMonthDto>();
		Calendar cal = Calendar.getInstance();  
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		OrganMonthStatisticsCriteria example = new OrganMonthStatisticsCriteria();
		OrganMonthStatisticsCriteria.Criteria criteria = example.createCriteria();
		criteria.andOrganIdEqualTo(Integer.valueOf(organId));
		criteria.andCycleYmBetween(Integer.valueOf(cycleY + "01"), Integer.valueOf(cycleY + "12"));
		List<OrganMonthStatistics> organMonthList = organMonthStatisticsService.selectByExample(example);
		DecimalFormat df = new DecimalFormat("######0.00");
		for (int i = 0; i < 12; i++) {
			String cycleYm = cycleY + monthString[i];
			AmountMonthDto dto = new AmountMonthDto();
			dto.setCycleYm(monthShow[i]);
			if(i<month || !cycleY.equals(String.valueOf(year))){
				dto.setTransAmountAverage("0.00");
				for (OrganMonthStatistics statistics : organMonthList) {
					if (statistics != null && cycleYm.equals(statistics.getCycleYm().toString())) {
						Integer transAmount = statistics.getTransAmount() == null ? 0 : statistics.getTransAmount();
						Integer transUserAmount = statistics.getTransUserAmount() == null ? 0
								: statistics.getTransUserAmount();
						if (transUserAmount != 0) {
							dto.setTransAmountAverage(df.format(mathDivIntegerBybit(transAmount, transUserAmount, 2)));
						}
						break;
					}
				}
			}
			
			result.add(dto);
		}
		return result;
	}

	/**
	 * 即时任务响应时间趋势分析
	 * 
	 * @param organId
	 * @param cycleY
	 * @return
	 */
	@Override
	public List<AmountMonthDto> queryHisRespTime(String organId, String cycleY) {
		List<AmountMonthDto> result = new ArrayList<AmountMonthDto>();
		Calendar cal = Calendar.getInstance();  
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		OrganMonthStatisticsCriteria example = new OrganMonthStatisticsCriteria();
		OrganMonthStatisticsCriteria.Criteria criteria = example.createCriteria();
		criteria.andOrganIdEqualTo(Integer.valueOf(organId));
		criteria.andCycleYmBetween(Integer.valueOf(cycleY + "01"), Integer.valueOf(cycleY + "12"));
		List<OrganMonthStatistics> organMonthList = organMonthStatisticsService.selectByExample(example);
		DecimalFormat df = new DecimalFormat("######0.0");
		for (int i = 0; i < 12; i++) {
			String cycleYm = cycleY + monthString[i];
			AmountMonthDto dto = new AmountMonthDto();
			dto.setCycleYm(monthShow[i]);
			if(i<month|| !cycleY.equals(String.valueOf(year))){
				dto.setAvgRespTime("0.0");
				for (OrganMonthStatistics statistics : organMonthList) {
					if (statistics != null && cycleYm.equals(statistics.getCycleYm().toString())) {
						Integer dispatchAmount = statistics.getDispatchAmount() == null ? 0
								: statistics.getDispatchAmount();
						Long transInstantTime = statistics.getTransInstantTime() == null ? 0L
								: statistics.getTransInstantTime();
						if (dispatchAmount != 0) {
							dto.setAvgRespTime(df.format(
									mathDivBybit(transInstantTime, Long.valueOf(dispatchAmount.toString()) * 60, 1)));
						}
						break;
					}
				}
			}
			result.add(dto);
		}
		return result;
	}

	/**
	 * 提供相对精确的除法运算
	 * 
	 * @param v1
	 *            除数
	 * @param v2被除数
	 * @param bit保留位数
	 * @return
	 */
	public static float mathDivBybit(Long v1, Long v2, Integer bit) {
		BigDecimal b1 = BigDecimal.valueOf(v1);
		BigDecimal b2 = BigDecimal.valueOf(v2);
		return b1.divide(b2, bit, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	/**
	 * 提供相对精确的除法运算
	 * 
	 * @param v1
	 *            除数
	 * @param v2被除数
	 * @param bit保留位数
	 * @return
	 */
	public static float mathDivIntegerBybit(Integer v1, Integer v2, Integer bit) {
		BigDecimal b1 = BigDecimal.valueOf(v1);
		BigDecimal b2 = BigDecimal.valueOf(v2);
		return b1.divide(b2, bit, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	@Override
	public TaskTypeDto queryTaskTypeRatio(CommonDto commonDto) {
		// 年份后面加上月份
		Integer cycleY = commonDto.getCycleY();
		commonDto.setCycleYmBeg(cycleY.intValue() * MtConstant.MT_CONSTANT_HUNDRED + MtConstant.MT_MONTH_JANUARY);
		commonDto.setCycleYmEnd(cycleY.intValue() * MtConstant.MT_CONSTANT_HUNDRED + MtConstant.MT_MONTH_DECEMBER);
		TaskTypeDto dto = organMonthStatisticsMapper.selectTaskTypeByOrganIdAndCycleYm(commonDto);
		if (null == dto) {
			dto = new TaskTypeDto();
			dto.setAutonomousTaskAmount(0L);
			dto.setDispatchTaskAmount(0L);
			dto.setFixedTaskAmount(0L);
		}
		dto.setDispatchTask(TransTaskTypeEnum.TASK_TYPE_DISPATCH.getName());
		dto.setAutonomousTask(TransTaskTypeEnum.TASK_TYPE_SELF.getName());
		dto.setFixedTask(TransTaskTypeEnum.TASK_TYPE_LOOP.getName());
		return dto;
	}

	@Override
	public TaskDateSourceDto queryTaskDateSourceRatio(CommonDto commonDto) {
		// 年份后面加上月份
		Integer cycleY = commonDto.getCycleY();
		commonDto.setCycleYmBeg(cycleY.intValue() * MtConstant.MT_CONSTANT_HUNDRED + MtConstant.MT_MONTH_JANUARY);
		commonDto.setCycleYmEnd(cycleY.intValue() * MtConstant.MT_CONSTANT_HUNDRED + MtConstant.MT_MONTH_DECEMBER);
		TaskDateSourceDto dto = organMonthStatisticsMapper.selectTaskDateSourceByOrganIdAndCycleYm(commonDto);
		if (null == dto) {
			dto = new TaskDateSourceDto();
			dto.setAppFromHouseAmount(0L);
			dto.setPadFromHouseAmount(0L);
			dto.setWebFromHouseAmount(0L);
			dto.setWechatFromHouseAmount(0L);
		}
		dto.setAppFromHouseName(MtConstant.MT_DATASOURCE_APP);
		dto.setPadFromHouseName(MtConstant.MT_DATASOURCE_PAD);
		dto.setWebFromHouseName(MtConstant.MT_DATASOURCE_WEB);
		dto.setWechatFromHouseName(MtConstant.MT_DATASOURCE_WECHAT);
		return dto;
	}
}
