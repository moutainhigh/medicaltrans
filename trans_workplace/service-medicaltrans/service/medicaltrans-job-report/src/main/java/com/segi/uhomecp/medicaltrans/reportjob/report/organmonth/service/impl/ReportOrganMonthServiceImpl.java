package com.segi.uhomecp.medicaltrans.reportjob.report.organmonth.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.constant.MtSeqContants;
import com.segi.uhomecp.medicaltrans.report.monthamount.organ.model.OrganMonthStatistics;
import com.segi.uhomecp.medicaltrans.report.monthamount.organ.model.OrganMonthStatisticsCriteria;
import com.segi.uhomecp.medicaltrans.report.monthamount.organ.service.OrganMonthStatisticsService;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.OrganMonthStatisticsDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.ReportJobTimeDto;
import com.segi.uhomecp.medicaltrans.reportjob.report.organmonth.dto.AmountMonthUserDto;
import com.segi.uhomecp.medicaltrans.reportjob.report.organmonth.service.ReportOrganMonthService;
import com.segi.uhomecp.medicaltrans.utils.MtCommonUtils;
import com.segi.uhomecp.utils.SeqContants;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;

/**
 * 
 *     
 * 包: com.segi.uhomecp.medicaltrans.reportjob.base.service.impl 
 * 类名称: ReportScheduleServiceImpl.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 下午6:07:21
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
@Service
public class ReportOrganMonthServiceImpl  implements ReportOrganMonthService {
	
	public static final Logger logger = LoggerFactory.getLogger(ReportOrganMonthServiceImpl.class);

	@Autowired
	private OrganMonthStatisticsService organMonthStatisticsService;
	
	/**
	 * 先删除需要更新的项目
	 * @param reportJobTimeDto
	 */
	public void deleteOrganMonthList(ReportJobTimeDto reportJobTimeDto) {
		if (reportJobTimeDto == null) {
			return;
		}
		List<Integer> organList = reportJobTimeDto.getOrganIdList();
		Integer exeYearMonth = Integer.valueOf(reportJobTimeDto.getExeYearMonth());
		Integer groupOrganId = reportJobTimeDto.getGroupOrganId();
		if (!AppUtils.isNotEmpty(organList) || exeYearMonth == null || groupOrganId == null) {
			return;
		}
		OrganMonthStatisticsCriteria example = new OrganMonthStatisticsCriteria();
		OrganMonthStatisticsCriteria.Criteria criteria = example.createCriteria();
		criteria.andGroupOrganIdEqualTo(groupOrganId);
		criteria.andOrganIdIn(organList);
		criteria.andCycleYmEqualTo(exeYearMonth);
		organMonthStatisticsService.deleteByExample(example);
	}
	
	/**
	 * 按单个对象dto（项目、月份）更新报表库
	 */
	@Override
	public void updateOrganMonthList(OrganMonthStatisticsDto organMonthStatisticsDto, Integer cycleYm) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Long dateLong = Long.valueOf(sdf.format(new Date()));
		OrganMonthStatistics organMonthStatistics = BeanCopierUtils.copyProperties(organMonthStatisticsDto,
				OrganMonthStatistics.class, true);
		int organMonthId = SeqContants.getSequnces(MtSeqContants.MT_ORGAN_MONTH_STATISTICS_SEQ).intValue();
		organMonthStatistics.setId(organMonthId);
		organMonthStatistics.setCreateDate(dateLong);
		organMonthStatistics.setUpdateDate(dateLong);
		int result = organMonthStatisticsService.insert(organMonthStatistics);
		if (result != 1) {
			throw new RuntimeException("项目月运输量新增出错");
		}
	}

	/**
	 * 提供项目月运送量更新人数的接口
	 */
	@Override
	public void updateOrganMonthUser(List<AmountMonthUserDto> amountMonthUserDtoList) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Long dateLong = Long.valueOf(sdf.format(new Date()));

		if (!AppUtils.isNotEmpty(amountMonthUserDtoList)) {
			throw new RuntimeException("提供项目月运送量更新人数的接口入参错误");
		}
		for (AmountMonthUserDto amountMonthUserDto : amountMonthUserDtoList) {
			if (amountMonthUserDto.getOrganId() == null || amountMonthUserDto.getExeYearMonth() == null
					|| amountMonthUserDto.getUserAmount() == null || amountMonthUserDto.getGroupOrganId() == null) {
				throw new RuntimeException("提供项目月运送量更新人数的接口入参错误");
			}
			OrganMonthStatisticsCriteria example = new OrganMonthStatisticsCriteria();
			OrganMonthStatisticsCriteria.Criteria criteria = example.createCriteria();
			criteria.andOrganIdEqualTo(amountMonthUserDto.getOrganId());
			criteria.andCycleYmEqualTo(amountMonthUserDto.getExeYearMonth());
			criteria.andGroupOrganIdEqualTo(amountMonthUserDto.getGroupOrganId());

			List<OrganMonthStatistics> beforeDto = organMonthStatisticsService.selectByExample(example);
			if (beforeDto != null && beforeDto.size() > 0) {
				OrganMonthStatistics organMonthStatistics = beforeDto.get(0);
				organMonthStatistics.setTransUserAmount(amountMonthUserDto.getUserAmount());
				organMonthStatistics.setUpdateDate(dateLong);
				int result = organMonthStatisticsService.updateByPrimaryKey(organMonthStatistics);
				if (result != 1) {
					throw new RuntimeException("项目月运输人数更新出错");
				}
			} else {
				OrganMonthStatistics organMonthStatistics = new OrganMonthStatistics();
				int organMonthId = SeqContants.getSequnces(MtSeqContants.MT_ORGAN_MONTH_STATISTICS_SEQ).intValue();
				organMonthStatistics.setId(organMonthId);
				organMonthStatistics.setGroupOrganId(amountMonthUserDto.getGroupOrganId());
				organMonthStatistics.setOrganId(amountMonthUserDto.getOrganId());
				organMonthStatistics.setCycleYm(amountMonthUserDto.getExeYearMonth());
				organMonthStatistics.setTransUserAmount(amountMonthUserDto.getUserAmount());
				organMonthStatistics.setCreateDate(dateLong);
				int result = organMonthStatisticsService.insert(organMonthStatistics);
				if (result != 1) {
					throw new RuntimeException("项目月运输人数更新出错");
				}
			}
		}

	}

	/**
	 * 增量job更新报表库接口
	 */
	@Override
	public void updateOrganMonthByMapAdd(Map<Integer, OrganMonthStatisticsDto> organMonthDtoMap) {
		//logger.debug("======增量job插入数据库参数organMonthDtoMap=" + FastjsonUtils.toJsonString(organMonthDtoMap));
		if (organMonthDtoMap == null) {
			return;
		}
		for (Entry<Integer, OrganMonthStatisticsDto> entry : organMonthDtoMap.entrySet()) {
			Integer cycleYm = entry.getKey();
			OrganMonthStatisticsDto dto = entry.getValue();
			Integer organId = dto.getOrganId();
			Integer groupOrganId = dto.getGroupOrganId();
			if (null == cycleYm || null == organId || null == groupOrganId) {
				continue;
			}

			OrganMonthStatisticsCriteria example = new OrganMonthStatisticsCriteria();
			OrganMonthStatisticsCriteria.Criteria criteria = example.createCriteria();
			criteria.andOrganIdEqualTo(organId);
			criteria.andCycleYmEqualTo(cycleYm);
			criteria.andGroupOrganIdEqualTo(groupOrganId);

			List<OrganMonthStatistics> beforeDto = organMonthStatisticsService.selectByExample(example);
			if (AppUtils.isNotEmpty(beforeDto)) {
				OrganMonthStatistics organMonthStatistics = beforeDto.get(0);
				OrganMonthStatistics organMonthNew = addOrganMonthInfo(dto, organMonthStatistics);
				organMonthStatisticsService.updateByPrimaryKey(organMonthNew);
			} else {
				int organMonthId = SeqContants.getSequnces(MtSeqContants.MT_ORGAN_MONTH_STATISTICS_SEQ).intValue();
				dto.setId(organMonthId);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				Long dateLong = Long.valueOf(sdf.format(new Date()));
				dto.setCreateDate(dateLong);
				dto.setUpdateDate(dateLong);
				organMonthStatisticsService.insert(dto);
			}
		}

	}
	
	/**
	 * 统计项目月运送量新旧相加的数量
	 * @param dto(增量job数量)
	 * @param organMonthStatistics(原有数量)
	 * @return
	 */
	public OrganMonthStatistics addOrganMonthInfo(OrganMonthStatisticsDto dto,
			OrganMonthStatistics organMonthStatistics) {
		OrganMonthStatistics organMonthNew = new OrganMonthStatistics();

		// 基础信息设置
		organMonthNew.setId(organMonthStatistics.getId());
		organMonthNew.setGroupOrganId(organMonthStatistics.getGroupOrganId());
		organMonthNew.setOrganId(organMonthStatistics.getOrganId());
		organMonthNew.setCycleYm(organMonthStatistics.getCycleYm());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Long dateLong = Long.valueOf(sdf.format(new Date()));
		organMonthNew.setUpdateDate(dateLong);
		organMonthNew.setCreateDate(organMonthStatistics.getCreateDate());
		organMonthNew.setTransUserAmount(organMonthStatistics.getTransUserAmount());

		// 对比新旧isTaskDayInt
		char[] isTaskDayIntbefore = null;
		if (organMonthStatistics.getIstaskday() != null) {
			 isTaskDayIntbefore = organMonthStatistics.getIstaskday().toCharArray();
		} else {
			 isTaskDayIntbefore = new char[31];
		}
		char[] isTaskDayIntold = null;
		if (dto.getIstaskday() != null) {
			isTaskDayIntold = dto.getIstaskday().toCharArray();
		} else {
			isTaskDayIntold = new char[31];
		}
		char[] isTaskDayIntNew = new char[31];
		for (int i = 0; i < 31; i++) {
			if (isTaskDayIntbefore[i] == '1' || isTaskDayIntold[i] == '1') {
				isTaskDayIntNew[i] = '1';
			} else {
				isTaskDayIntNew[i] = '0';
			}
		}
		organMonthNew.setIstaskday(new String(isTaskDayIntNew));

		// 相关数据新旧相加
		organMonthNew.setTransAmount(
				(Integer)MtCommonUtils.initNullToZero(organMonthStatistics.getTransAmount(),MtConstant.INT_ZERO) +
				(Integer)MtCommonUtils.initNullToZero(dto.getTransAmount(),MtConstant.INT_ZERO)
				);
		organMonthNew.setDispatchAmount(
				(Integer)MtCommonUtils.initNullToZero(organMonthStatistics.getDispatchAmount(),MtConstant.INT_ZERO) +
				(Integer)MtCommonUtils.initNullToZero(dto.getDispatchAmount(),MtConstant.INT_ZERO)
				);
		organMonthNew.setAutonomousAmount(
				(Integer)MtCommonUtils.initNullToZero(organMonthStatistics.getAutonomousAmount(),MtConstant.INT_ZERO) +
				(Integer)MtCommonUtils.initNullToZero(dto.getAutonomousAmount(),MtConstant.INT_ZERO)
				);
		organMonthNew.setFixedAmount(
				(Integer)MtCommonUtils.initNullToZero(organMonthStatistics.getFixedAmount(),MtConstant.INT_ZERO) +
				(Integer)MtCommonUtils.initNullToZero(dto.getFixedAmount(),MtConstant.INT_ZERO)
				);
		organMonthNew.setTransInstantTime(
				(Long)MtCommonUtils.initNullToZero(organMonthStatistics.getTransInstantTime(),MtConstant.LONG_ZERO) +
				(Long)MtCommonUtils.initNullToZero(dto.getTransInstantTime(),MtConstant.LONG_ZERO)
				);
		organMonthNew.setTransTime(
				(Long)MtCommonUtils.initNullToZero(organMonthStatistics.getTransTime(),MtConstant.LONG_ZERO) +
				(Long)MtCommonUtils.initNullToZero(dto.getTransTime(),MtConstant.LONG_ZERO)
				);
		organMonthNew.setTimelyAmount(
				(Integer)MtCommonUtils.initNullToZero(organMonthStatistics.getTimelyAmount(),MtConstant.INT_ZERO) +
				(Integer)MtCommonUtils.initNullToZero(dto.getTimelyAmount(),MtConstant.INT_ZERO)
				);
		organMonthNew.setSatisfactionAmount(
				(Integer)MtCommonUtils.initNullToZero(organMonthStatistics.getSatisfactionAmount(),MtConstant.INT_ZERO) +
				(Integer)MtCommonUtils.initNullToZero(dto.getSatisfactionAmount(),MtConstant.INT_ZERO)
				);
		organMonthNew.setWebDatasource(
				(Integer)MtCommonUtils.initNullToZero(organMonthStatistics.getWebDatasource(),MtConstant.INT_ZERO) +
				(Integer)MtCommonUtils.initNullToZero(dto.getWebDatasource(),MtConstant.INT_ZERO)
				);
		organMonthNew.setWechatDatasource(
				(Integer)MtCommonUtils.initNullToZero(organMonthStatistics.getWechatDatasource(),MtConstant.INT_ZERO) +
				(Integer)MtCommonUtils.initNullToZero(dto.getWechatDatasource(),MtConstant.INT_ZERO)
				);
		organMonthNew.setPadDatasource(
				(Integer)MtCommonUtils.initNullToZero(organMonthStatistics.getPadDatasource(),MtConstant.INT_ZERO) +
				(Integer)MtCommonUtils.initNullToZero(dto.getPadDatasource(),MtConstant.INT_ZERO)
				);
		organMonthNew.setAppDatasource(
				(Integer)MtCommonUtils.initNullToZero(organMonthStatistics.getAppDatasource(),MtConstant.INT_ZERO) +
				(Integer)MtCommonUtils.initNullToZero(dto.getAppDatasource(),MtConstant.INT_ZERO)
				);
		return organMonthNew;
	}
	
	/**
	 * 批量更新删除老数据时，先查出原数据的运输人数字段
	 * @return(项目，人数)
	 */
	@Override
	public Map<Integer,Integer> queryTrantUserNum(ReportJobTimeDto reportJobTimeDto){
		Map<Integer,Integer> resultMap = new HashMap<Integer,Integer>();
		if (reportJobTimeDto == null) {
			throw new RuntimeException("先查出原数据的运输人数字段出错，入参为空");
		}
		List<Integer> organList = reportJobTimeDto.getOrganIdList();
		Integer exeYearMonth = Integer.valueOf(reportJobTimeDto.getExeYearMonth());
		Integer groupOrganId = reportJobTimeDto.getGroupOrganId();
		if (!AppUtils.isNotEmpty(organList) || exeYearMonth == null || groupOrganId == null) {
			throw new RuntimeException("先查出原数据的运输人数字段出错，入参为空");
		}
		
		OrganMonthStatisticsCriteria example = new OrganMonthStatisticsCriteria();
		OrganMonthStatisticsCriteria.Criteria criteria = example.createCriteria();
		criteria.andGroupOrganIdEqualTo(groupOrganId);
		criteria.andOrganIdIn(organList);
		criteria.andCycleYmEqualTo(exeYearMonth);
		List<OrganMonthStatistics> resultList = organMonthStatisticsService.selectByExample(example);
		if(!AppUtils.isNotEmpty(resultList)){
			return resultMap;
		}
		for (OrganMonthStatistics organMonth:resultList) {
			if (organMonth == null || organMonth.getOrganId() == null) {
				continue;
			}
			Integer transUserAmount = organMonth.getTransUserAmount() == null ? 0 : organMonth.getTransUserAmount();
			resultMap.put(organMonth.getOrganId(), transUserAmount);
		}
		return resultMap;
	}
	

}
