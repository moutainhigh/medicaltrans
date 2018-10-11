package com.segi.uhomecp.medicaltrans.reportjob.query.pervolmonth.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.CharUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import segi.datacachesvr.queryInfo.GroupUserBrief;
import segi.datacachesvr.queryInfo.TOrganInfo;

import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.enums.TransStatusEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTaskTypeEnum;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonth;
import com.segi.uhomecp.medicaltrans.reportjob.query.pervolmonth.dto.PersonalVolumeMonthDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.pervolmonth.service.PerVolMonthStatService;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.ReportJobTimeDto;
import com.segi.uhomecp.medicaltrans.utils.MtCommonUtils;
import com.segi.uhomecp.medicaltrans.utils.MtIbatchQueryServiceUtils;
import com.segi.uhomecp.rest.utils.StringUtils;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;
import com.segi.uhomecp.wh.common.utils.DataTypeConverUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * @ClassName:  PerVolMonthStatService   
 * @Description: 个人运送量月报表数据汇总接口   
 * @author: zhaoqing
 * @date:   2018年7月27日 下午3:02:45
 */
@Component
public class PerVolMonthStatServiceUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PerVolMonthStatServiceUtil.class);
	
	@Autowired
	private PerVolMonthStatService perVolMonthStatService;
	
	/**
	 * <p>Title: getPersonalVolumeMonth</p>   
	 * <p>Description: 根据项目id统计个人月运送量</p> 
	 * <p>zhaoqing</p>
	 * @param dto
	 * @return   
	 */
	public List<PersonalVolumeMonth> getPersonalVolumeMonth(PersonalVolumeMonthDto dto) {
		List<PersonalVolumeMonth> list = new ArrayList<>();
		List<Integer> organIdList = dto.getOrganIdList();
		if (!AppUtils.isNotEmpty(organIdList)) {
			return list;
		}
		// 组织机构ID
		String organIds = AppUtils.listToString(organIdList, 
				CharUtils.toChar(Constant.SPLIT_COMMA));	
		dto.setOrganIds(organIds);
		// 任务状态为已完成
		dto.setTaskStatus(TransStatusEnum.TRANS_COMPLETED.getCode());
		long beginTime = System.currentTimeMillis();
		// 根据项目id统计个人月运送量
//		List<PersonalVolumeMonthDto> perVolMonthDtoList = perVolMonthStatService
//				.getPersonalVolumeMonth(dto.getGroupOrganId(), dto);
		List<PersonalVolumeMonthDto> perVolMonthDtoList = getPersonalVolumeMonth(dto.getGroupOrganId(), dto);
		if (!AppUtils.isNotEmpty(perVolMonthDtoList)) {
			return list;
		}
		// 日志
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("=================>PerVolMonthStatServiceImpl getPersonalVolumeMonth ")
			.append("query data [").append(perVolMonthDtoList.size()).append("] rows, ")
			.append("take [").append(System.currentTimeMillis() - beginTime).append("] ms");
		LOGGER.debug(strBuffer.toString());
		XxlJobLogger.log(strBuffer.toString());
		// 统计数据信息处理
		list = handlePerVolMonthListData(organIdList, perVolMonthDtoList, dto.getCycleYm());
		return list;
	}
	
	/**
	 * @Title: getPersonalVolumeMonth   
	 *  获取统计数据 
	 * @author zhaoqing  
	 * @param groupOrganId
	 * @param dto
	 * @return 
	 */
	private List<PersonalVolumeMonthDto> getPersonalVolumeMonth(
			Integer groupOrganId, PersonalVolumeMonthDto dto) {
		// 统计运送量
		List<PersonalVolumeMonthDto> transAmountList = 
				countPerVolMonthTransAmount(groupOrganId, dto);
		// 统计不满意量
		List<PersonalVolumeMonthDto> unSatisAmountList = 
				countPerVolMonthUnSatisAmount(groupOrganId, dto);
		// 统计超时量
		List<PersonalVolumeMonthDto> isTimeOutAmountList = 
				countPerVolMonthIsTimeOutAmount(groupOrganId, dto);
		// 返回合并后的数据
		return mergePersonalVolumeMonth(transAmountList, unSatisAmountList, isTimeOutAmountList);
	}
	
	/**
	 * @Title: countPerVolMonthIsTimeOutAmount   
	 *  统计超时量 
	 * @author zhaoqing  
	 * @param groupOrganId
	 * @param dto
	 * @return 
	 */
	private List<PersonalVolumeMonthDto> countPerVolMonthIsTimeOutAmount(
			Integer groupOrganId, PersonalVolumeMonthDto dto) {
		int dataSize = 0;
		long beginTime = System.currentTimeMillis();
		// 设置超时
		dto.setIsTimeOut(MtConstant.TIME_OUT);
		List<PersonalVolumeMonthDto> isTimeOutAmountList = perVolMonthStatService
				.countPerVolMonthIsTimeOutAmount(groupOrganId, dto);
		if (AppUtils.isNotEmpty(isTimeOutAmountList)) {
			dataSize = isTimeOutAmountList.size();
		}
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("=================>getPersonalVolumeMonth countPerVolMonthIsTimeOutAmount ")
			.append("query data [").append(dataSize).append("] rows, ")
			.append("take [").append(System.currentTimeMillis() - beginTime).append("] ms");
		LOGGER.debug(strBuffer.toString());
		return isTimeOutAmountList;
	}
	
	/**
	 * @Title: countPerVolMonthUnSatisAmount   
	 *  统计个人运送量不满意量 
	 * @author zhaoqing  
	 * @param groupOrganId
	 * @param dto
	 * @return 
	 */
	private List<PersonalVolumeMonthDto> countPerVolMonthUnSatisAmount(
			Integer groupOrganId, PersonalVolumeMonthDto dto) {
		int dataSize = 0;
		long beginTime = System.currentTimeMillis();
		// 设置不满意星级
		dto.setUnSatisEvaluates(MtConstant.MT_UNSATIS_EVALUATES);
		List<PersonalVolumeMonthDto> unSatisAmountList = perVolMonthStatService
				.countPerVolMonthUnSatisAmount(groupOrganId, dto);
		if (AppUtils.isNotEmpty(unSatisAmountList)) {
			dataSize = unSatisAmountList.size();
		}
		dto.setUnSatisEvaluates(null);
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("=================>getPersonalVolumeMonth countPerVolMonthUnSatisAmount ")
			.append("query data [").append(dataSize).append("] rows, ")
			.append("take [").append(System.currentTimeMillis() - beginTime).append("] ms");
		LOGGER.debug(strBuffer.toString());
		return unSatisAmountList;
	}
	
	/**
	 * @Title: countPerVolMonthTransAmount   
	 *  统计运送量
	 * @author zhaoqing  
	 * @param groupOrganId
	 * @param dto
	 * @return 
	 */
	private List<PersonalVolumeMonthDto> countPerVolMonthTransAmount(
			Integer groupOrganId, PersonalVolumeMonthDto dto) {
		int dataSize = 0;
		long beginTime = System.currentTimeMillis();
		List<PersonalVolumeMonthDto> transAmountList = perVolMonthStatService
				.countPerVolMonthTransAmount(groupOrganId, dto);
		if (AppUtils.isNotEmpty(transAmountList)) {
			dataSize = transAmountList.size();
		}
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("=================>getPersonalVolumeMonth countPerVolMonthTransAmount ")
			.append("query data [").append(dataSize).append("] rows, ")
			.append("take [").append(System.currentTimeMillis() - beginTime).append("] ms");
		LOGGER.debug(strBuffer.toString());
		return transAmountList;
	}
	
	/**
	 * @Title: mergePersonalVolumeMonth   
	 *  合并统计数据 
	 * @author zhaoqing  
	 * @param transAmountList
	 * @param unSatisAmountList
	 * @param isTimeOutAmountList
	 * @return
	 */
	private List<PersonalVolumeMonthDto> mergePersonalVolumeMonth(
			List<PersonalVolumeMonthDto> transAmountList, 
			List<PersonalVolumeMonthDto> unSatisAmountList, 
			List<PersonalVolumeMonthDto> isTimeOutAmountList) {
		List<PersonalVolumeMonthDto> resultList = new ArrayList<>();
		if (!AppUtils.isNotEmpty(transAmountList)) {
			return resultList;
		}
		// 获取不满意量信息的Map集合
		Map<String, PersonalVolumeMonthDto> unStatisAmountMap = 
				perVolMonthListToMap(unSatisAmountList);
		// 获取超时任务量信息的Map集合
		Map<String, PersonalVolumeMonthDto> isTimeOutAmountMap = 
				perVolMonthListToMap(isTimeOutAmountList);
		String key = null;
		PersonalVolumeMonthDto dto = null;
		for (PersonalVolumeMonthDto transDto : transAmountList) {
			key = transDto.getOrganId() + Constant.SPLIT_COMMA + transDto.getUserId() 
					+ Constant.SPLIT_COMMA + transDto.getTaskType();
			// 获取不满意量信息
			dto = unStatisAmountMap.get(key);
			if (null != dto) {
				transDto.setUnSatisAmount(dto.getUnSatisAmount());
			} else {
				transDto.setUnSatisAmount(0);
			}
			// 获取超时量信息
			dto = isTimeOutAmountMap.get(key);
			if (null != dto) {
				transDto.setTimeOutAmount(dto.getTimeOutAmount());
			} else {
				transDto.setTimeOutAmount(0);
			}
			resultList.add(transDto);
		}
		LOGGER.debug("==========================resultList size [" + resultList.size() + "] rows");
		return resultList;
	}
	
	/**
	 * @Title: perVolMonthListToMap   
	 *  统计数据List对象转换成map集合 
	 * @author zhaoqing  
	 * @param dtoList
	 * @return 
	 */
	private Map<String, PersonalVolumeMonthDto> perVolMonthListToMap(
			List<PersonalVolumeMonthDto> dtoList) {
		Map<String, PersonalVolumeMonthDto> map = new HashMap<>();
		if (AppUtils.isNotEmpty(dtoList)) {
			String key = null;
			for (PersonalVolumeMonthDto dto : dtoList) {
				// key值为: organId,userId,taskType
				key = dto.getOrganId() + Constant.SPLIT_COMMA + dto.getUserId() 
						+ Constant.SPLIT_COMMA + dto.getTaskType();
				map.put(key, dto);
			}
		}
		return map;
	}
	
	/**
	 * @Title: handlePerVolMonthListData   
	 *  处理统计数据信息 
	 * @author zhaoqing  
	 * @param organIdList
	 * @param perVolMonthDtoList
	 * @param monthFirstDay
	 * @return 
	 */
	private List<PersonalVolumeMonth> handlePerVolMonthListData(List<Integer> organIdList, 
			List<PersonalVolumeMonthDto> perVolMonthDtoList, Integer cycleYm) {
		List<PersonalVolumeMonth> list = new ArrayList<>();	
		long beginTime = System.currentTimeMillis();
		// 根据组织机构获取一级物业信息
		Map<Integer, TOrganInfo> tOrganInfoMap = CommonServiceUtils.getTopOrganByOrganIDs(organIdList);
		// 获取运送员ID集合
		List<Integer> userIdList = AppUtils.list2ParamsList(
				perVolMonthDtoList, PersonalVolumeMonthDto.class, "userId");
		// 获取服务服信息
		Map<Integer, GroupUserBrief> groupInfoMap = 
				MtIbatchQueryServiceUtils.getGroupInfoMap(userIdList);
		// 统计数据通过"organId,userId"为key进行分组处理
		Map<String, PersonalVolumeMonth> perVolMonthMap = new HashMap<>();
		for (PersonalVolumeMonthDto dto : perVolMonthDtoList) {
			PersonalVolumeMonth personalVolumeMonth = null;
			// key值为"organId,userId"
			String perMapKey = dto.getOrganId() + Constant.SPLIT_COMMA + dto.getUserId();
			personalVolumeMonth = perVolMonthMap.get(perMapKey);
			if (personalVolumeMonth == null) {
				personalVolumeMonth = new PersonalVolumeMonth();
				perVolMonthMap.put(perMapKey, personalVolumeMonth);
				// 设置基础信息
				setPerVolMonthBaseInfo(personalVolumeMonth, 
						tOrganInfoMap, groupInfoMap, dto, cycleYm);
			}
			/* 处理各类型的运送量信息 */
			handleAmountDataInfo(dto, personalVolumeMonth);
			// 设置为空的统计属性值为0
			MtCommonUtils.initNumberNullProperties(personalVolumeMonth);
			personalVolumeMonth.setUpdateDate(null);
		}
		if (!perVolMonthMap.isEmpty()) {
			// 遍历Map获取处理后的报表统计数据
			for (Entry<String, PersonalVolumeMonth> entry : perVolMonthMap.entrySet()) {
				list.add(entry.getValue());
			}
		}
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("=================>PerVolMonthStatServiceImpl handlePerVolMonthListData: ")
			.append("handle data [").append(perVolMonthDtoList.size()).append("] rows, ")
			.append("take [").append(System.currentTimeMillis() - beginTime).append("] ms");
		LOGGER.debug(strBuffer.toString());
		XxlJobLogger.log(strBuffer.toString());
		return list;
	}
	
	/**
	 * @Title: handleAmountDataInfo   
	 *  处理各类型的运送量信息
	 * @author zhaoqing  
	 * @param dto
	 * @param personalVolumeMonth 
	 */
	private void handleAmountDataInfo(PersonalVolumeMonthDto dto,
			PersonalVolumeMonth personalVolumeMonth) {
		// 各任务类型的运送总量
		Integer transAmount = dto.getTransAmount();
		// 满意的任务量
		Integer satisAmount = transAmount - dto.getUnSatisAmount();
		// 及时任务量
		Integer timelyAmount = transAmount - dto.getTimeOutAmount();
		// 运送总量
		Integer transAmountCount = personalVolumeMonth.getTransAmount();
		transAmountCount = transAmountCount == null ? 0 : transAmountCount;
		// 设置总运送量
		personalVolumeMonth.setTransAmount(transAmount + transAmountCount);
		if (TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode().equals(dto.getTaskType())) {
			// 设置调度任务运送量统计数据
			personalVolumeMonth.setDispatchAmount(transAmount);
			personalVolumeMonth.setDispatchSatisfactionAmount(satisAmount);
			personalVolumeMonth.setDispatchTimelyAmount(timelyAmount);
		}
		if (TransTaskTypeEnum.TASK_TYPE_SELF.getCode().equals(dto.getTaskType())) {
			// 设置自主任务运送量统计数据
			personalVolumeMonth.setAutonomousAmount(transAmount);
			personalVolumeMonth.setAutonomousSatisfactionAmount(satisAmount);
			personalVolumeMonth.setAutonomousTimelyAmount(timelyAmount);
		}
		if (TransTaskTypeEnum.TASK_TYPE_LOOP.getCode().equals(dto.getTaskType())) {
			// 设置固定任务运送量统计数据
			personalVolumeMonth.setFixedAmount(transAmount);
			personalVolumeMonth.setFixedSatisfactionAmount(satisAmount);
			personalVolumeMonth.setFixedTimelyAmount(timelyAmount);
		}
	}
	
	/**
	 * @Title: setPerVolMonthBaseInfo   
	 *  设置基础信息
	 * @author zhaoqing  
	 * @param personalVolumeMonth
	 * @param tOrganInfoMap
	 * @param groupInfoMap
	 * @param dto
	 * @param cycleYm      
	 */
	private void setPerVolMonthBaseInfo(PersonalVolumeMonth personalVolumeMonth, 
			Map<Integer, TOrganInfo> tOrganInfoMap, Map<Integer, GroupUserBrief> groupInfoMap,
			PersonalVolumeMonthDto dto, Integer cycleYm) {
		personalVolumeMonth.setOrganId(dto.getOrganId());
		personalVolumeMonth.setUserId(dto.getUserId());
		// 设置统计月份
		personalVolumeMonth.setCycleYm(cycleYm);
		// 一级物业信息
		TOrganInfo tOragnInfo = tOrganInfoMap.get(dto.getOrganId());
		// 设置一级物业ID
		personalVolumeMonth.setGroupOrganId(tOragnInfo == null ? null
				: tOragnInfo.getOrganId());
		// 服务组信息
		GroupUserBrief groupInfo = groupInfoMap.get(dto.getUserId());
		// 设置服务组ID
		personalVolumeMonth.setSergroupId(groupInfo == null ? null : groupInfo
				.getGroupId());
		personalVolumeMonth.setCreateDate(DataTypeConverUtils
				.formatDateToLongYYYYMMDDHHMMSS(new Date()));
	}

	/**
	 * <p>Title: getPersonalVolumeMonthByUserId</p>   
	 * <p>Description: 根据运送员员工Id统计个人月运送量</p> 
	 * <p>zhaoqing</p>
	 * @param userIds
	 * @param monthFirstDay
	 * @param monthLastDay
	 * @return   
	 */
	public List<PersonalVolumeMonth> getPersonalVolumeMonthByUserId(
			String userIds, Date monthFirstDay, Date monthLastDay) {
		List<PersonalVolumeMonth> list = new ArrayList<>();
		PersonalVolumeMonthDto dto = new PersonalVolumeMonthDto();
		if (StringUtils.isEmpty(userIds)) {
			return list;
		}
		// 运送员员工ID
		dto.setUserIds(userIds);
		// 开始日期
		dto.setBeginDate(monthFirstDay);
		// 结束日期
		dto.setEndDate(monthLastDay);
		// 任务状态为已完成
		dto.setTaskStatus(TransStatusEnum.TRANS_COMPLETED.getCode());
		long beginTime = System.currentTimeMillis();
		// 根据运送员员工Id统计个人月运送量
		List<PersonalVolumeMonthDto> perVolMonthDtoList = getPersonalVolumeMonth(dto.getGroupOrganId(), dto);
		if (!AppUtils.isNotEmpty(perVolMonthDtoList)) {
			return list;
		}
		// 日志
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("=================>PerVolMonthStatServiceImpl getPersonalVolumeMonthByUserId ")
			.append("query data [").append(perVolMonthDtoList.size())
			.append("] rows, ").append("take [").append(System.currentTimeMillis() - beginTime).append("] ms");
		LOGGER.debug(strBuffer.toString());
		XxlJobLogger.log(strBuffer.toString());
		// 获取组织机构ID集合
		List<Integer> organIdList = AppUtils.list2ParamsList(
				perVolMonthDtoList, PersonalVolumeMonthDto.class, "organId");
		String cycleYm = DateUtil.formatDateToStringYYMM(monthFirstDay);
		// 统计数据信息处理
		list = handlePerVolMonthListData(organIdList, perVolMonthDtoList, Integer.valueOf(cycleYm));
		return list;
	}

	/**
	 * <p>Title: getUserAmountByOrganMonth</p>   
	 * <p>Description: 根据组织机构Id和月份查询运送员数量</p> 
	 * <p>zhaoqing</p>
	 * @param organIdList
	 * @param cycleYm
	 * @return   
	 */
	public List<PersonalVolumeMonthDto> getUserAmountByOrganMonth(
			List<Integer> organIdList, Integer cycleYm) {
		LOGGER.debug("==============>getUserAmountByOrganMonth: organIdList:{}, cycleYm:{}",
				FastjsonUtils.toJsonString(organIdList), cycleYm);
		List<PersonalVolumeMonthDto> perVolMonthDtoList = new ArrayList<>();
		if (!AppUtils.isNotEmpty(organIdList) || DateUtil.checkCycleYm(cycleYm)) {
			return perVolMonthDtoList;
		}
		PersonalVolumeMonthDto dto = new PersonalVolumeMonthDto();
		// 组织机构ID
		String organIds = AppUtils.listToString(organIdList,
				CharUtils.toChar(Constant.SPLIT_COMMA));
		dto.setOrganIds(organIds);
		// 设置开始和结束日期
		setBeginAndEndDate(dto, cycleYm);
		// 任务状态为已完成
		dto.setTaskStatus(TransStatusEnum.TRANS_COMPLETED.getCode());
		long beginTime = System.currentTimeMillis();
		// 根据项目Id和月份查询运送员人数
		perVolMonthDtoList = perVolMonthStatService.getUserAmountByOrganMonth(
				dto.getGroupOrganId(), dto);
		if (!AppUtils.isNotEmpty(perVolMonthDtoList)) {
			return perVolMonthDtoList;
		}
		// 日志
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("=================>PerVolMonthStatServiceImpl getUserAmountByOrganMonth ")
			.append("query data [").append(perVolMonthDtoList.size()).append("] rows, ")
			.append("take [").append(System.currentTimeMillis() - beginTime).append("] ms");
		LOGGER.debug(strBuffer.toString());
		XxlJobLogger.log(strBuffer.toString());
		return handleUserAmountData(perVolMonthDtoList);
	}
	
	/**
	 * @Title: setBeginAndEndDate   
	 *  设置开始和结束日期 
	 * @author zhaoqing  
	 * @param dto
	 * @param cycleYm 
	 */
	private void setBeginAndEndDate(PersonalVolumeMonthDto dto, Integer cycleYm) {
		// 开始和结束时间处理
		String cycleYmStr = String.valueOf(cycleYm);
		int year = Integer.valueOf(DateUtil.getYearOfDate(cycleYmStr));
		int month = Integer.valueOf(DateUtil.getMonthOfDate(cycleYmStr));
		String monthFirstDay = DateUtil.getFirstTimeOfMonthYYYYMMDDHHMMSS(year, month);
		String monthLastDay = DateUtil.getLastTimeOfMonthYYYYMMDDHHMMSS(year, month);
		// 开始日期
		dto.setBeginTime(Long.valueOf(monthFirstDay));
		// 结束日期
		dto.setEndTime(Long.valueOf(monthLastDay));
	}
	
	/**
	 * @Title: handleUserAmountData   
	 *  处理运送员数据统计的结果信息
	 * @author zhaoqing  
	 * @param perVolMonthDtoList
	 * @return
	 */
	private List<PersonalVolumeMonthDto> handleUserAmountData(
			List<PersonalVolumeMonthDto> perVolMonthDtoList) {
		List<PersonalVolumeMonthDto> resultList = new ArrayList<>();
		long beginTime = System.currentTimeMillis();
		// 统计结果按组织机构分组
		Map<Integer, List<PersonalVolumeMonthDto>> userAmountMap = AppUtils.listGroup2Map(
				perVolMonthDtoList, PersonalVolumeMonthDto.class, "organId", PersonalVolumeMonthDto.class);
		if (AppUtils.isNotEmpty(userAmountMap)) {
			return resultList;
		}
		for (Map.Entry<Integer, List<PersonalVolumeMonthDto>> entry : userAmountMap.entrySet()) {
			PersonalVolumeMonthDto dto = new PersonalVolumeMonthDto();
			dto.setOrganId(entry.getKey());
			dto.setUserAmount(entry.getValue().size());
			resultList.add(dto);
		}
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("=================>PerVolMonthStatServiceImpl handleUserAmountData: ")
			.append("handle data [").append(perVolMonthDtoList.size()).append("] rows, ")
			.append("take [").append(System.currentTimeMillis() - beginTime).append("] ms");
		LOGGER.debug(strBuffer.toString());
		XxlJobLogger.log(strBuffer.toString());
		return resultList;
	}
	
	/**
	 * @Title: getPersonalVolumeMonth   
	 *  根据项目Id统计个人月运送量数据
	 * @author zhaoqing  
	 * @param reportJobTimeDto
	 * @return 
	 */
	public List<PersonalVolumeMonth> getPersonalVolumeMonth(ReportJobTimeDto reportJobTimeDto) {
		// 查询条件转换
		PersonalVolumeMonthDto dto = new PersonalVolumeMonthDto();
		dto.setOrganIdList(reportJobTimeDto.getOrganIdList());
		dto.setGroupOrganId(reportJobTimeDto.getGroupOrganId());
		String beginDate = DateUtil.getFirstTimeOfMonthYYYYMMDDHHMMSS(reportJobTimeDto.getExeYearMonth());
		String endDate = DateUtil.getLastTimeOfMonthYYYYMMDDHHMMSS(reportJobTimeDto.getExeYearMonth()); 
		dto.setBeginDate(DateUtil.parseStringToDateYYYYMMDDHHMMSS(beginDate));
		dto.setEndDate(DateUtil.parseStringToDateYYYYMMDDHHMMSS(endDate));
		dto.setCycleYm(Integer.valueOf(reportJobTimeDto.getExeYearMonth()));
		return getPersonalVolumeMonth(dto);
	}

}
