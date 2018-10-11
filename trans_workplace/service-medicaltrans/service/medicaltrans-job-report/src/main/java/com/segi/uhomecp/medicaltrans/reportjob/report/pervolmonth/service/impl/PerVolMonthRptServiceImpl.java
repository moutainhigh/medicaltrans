package com.segi.uhomecp.medicaltrans.reportjob.report.pervolmonth.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tddl.sequences.SequenceException;

import com.segi.uhomecp.medicaltrans.cache.UpdatePerVolumeRedisCache;
import com.segi.uhomecp.medicaltrans.constant.MedicalTransRedisConstant;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.constant.MtSeqContants;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonth;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonthCriteria;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonthHis;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonthHisCriteria;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.service.PersonalVolumeMonthHisService;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.service.PersonalVolumeMonthService;
import com.segi.uhomecp.medicaltrans.reportjob.query.pervolmonth.util.PerVolMonthStatServiceUtil;
import com.segi.uhomecp.medicaltrans.reportjob.report.organmonth.dto.AmountMonthUserDto;
import com.segi.uhomecp.medicaltrans.reportjob.report.organmonth.service.ReportOrganMonthService;
import com.segi.uhomecp.medicaltrans.reportjob.report.pervolmonth.dao.PerVolMonthHisInfoMapper;
import com.segi.uhomecp.medicaltrans.reportjob.report.pervolmonth.dao.PerVolMonthInfoMapper;
import com.segi.uhomecp.medicaltrans.reportjob.report.pervolmonth.service.PerVolMonthRptService;
import com.segi.uhomecp.medicaltrans.utils.DateCommonUtil;
import com.segi.uhomecp.redis.cluster.SegiRedisClusterBuilder;
import com.segi.uhomecp.sequence.SequenceRpcClient;
import com.segi.uhomecp.utils.SeqContants;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * @ClassName:  PerVolMonthRptServiceImpl   
 * @Description:个人运送量月报表数据操作接口实现类   
 * @author: zhaoqing
 * @date:   2018年7月30日 下午2:27:01
 */
@Service
public class PerVolMonthRptServiceImpl implements PerVolMonthRptService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PerVolMonthRptServiceImpl.class);
	
	@Autowired
	private PersonalVolumeMonthService personalVolumeMonthService;
	
	@Autowired
	private PersonalVolumeMonthHisService personalVolumeMontHisService;
	
	@Autowired
	private PerVolMonthStatServiceUtil perVolMonthStatServiceUtil;
	
	@Autowired
	private PerVolMonthInfoMapper perVolMonthInfoMapper;
	
	@Autowired
	private PerVolMonthHisInfoMapper perVolMonthHisInfoMapper;
	
	@Autowired
	private ReportOrganMonthService reportOrganMonthService;
	
	@Resource(name = "segiRedisCluster")
	private SegiRedisClusterBuilder segiRedisClusterBuilder;
	
	@Resource
	private UpdatePerVolumeRedisCache updatePerVolumeRedisCache;
	
	/**
	 * <p>Title: savePerVolMonthIbatch</p>   
	 * <p>Description: 批量保存个人运送量月报表统计数据 </p> 
	 * <p>zhaoqing</p>
	 * @param list   
	 */
	@Override
	public void savePerVolMonthIbatch(List<PersonalVolumeMonth> list) {
		if (!AppUtils.isNotEmpty(list)) {
			return;
		}
		// 获取主键
		List<Long> idSeqList = getIdSeqList(list.size(), MtSeqContants.MT_PERSONAL_VOLUME_MONTH_ID_SEQ);
		int index = 0;
		for (PersonalVolumeMonth personalVolumeMonth : list) {
			if (AppUtils.isNotEmpty(idSeqList)) {
				// 设置主键
				personalVolumeMonth.setId(idSeqList.get(index).intValue());
				index++;
			} else {
				personalVolumeMonth.setId(SeqContants.getSequnces(
						MtSeqContants.MT_PERSONAL_VOLUME_MONTH_ID_SEQ).intValue());
			}
		}
		long beginTime = System.currentTimeMillis();
		perVolMonthInfoMapper.savePerVolMonthIbatch(list);
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("=================>PerVolMonthRptServiceImpl savePerVolMonthIbatch ")
			.append("save data [").append(list.size()).append("] rows, ")
			.append("take [").append(System.currentTimeMillis() - beginTime).append("] ms");
		LOGGER.debug(strBuffer.toString());
		XxlJobLogger.log(strBuffer.toString());
	}
	
	/**
	 * @Title: getIdSeqList   
	 *  获取主键 
	 * @author zhaoqing  
	 * @param size
	 * @param seqName
	 * @return 
	 */
	private List<Long> getIdSeqList(int size, String seqName) {
		long beginTime = System.currentTimeMillis();
		List<Long> idSeqList = new ArrayList<>();
		StringBuffer strBuffer = new StringBuffer();
		try {
			idSeqList = SequenceRpcClient.getCurrentSequenceListValueByIce(seqName, size);
			strBuffer.append("=================>PerVolMonthRptServiceImpl savePerVolMonthIbatch ")
				.append("get sequences [").append(idSeqList.size()).append("] rows, ")
				.append("take [").append(System.currentTimeMillis() - beginTime).append("] ms");
			LOGGER.debug(strBuffer.toString());
			XxlJobLogger.log(strBuffer.toString());
		} catch (SequenceException e) {
			LOGGER.error("getSequence[" + seqName + "]Exception", e);
		}
		return idSeqList;
	}
	
	/**
	 * <p>Title: deletePerVolMonthByOrganId</p>   
	 * <p>Description: 根据组织机构删除个人运送量月报表当月表的统计数据</p> 
	 * <p>zhaoqing</p>
	 * @param organIdList  
	 */
	@Override
	public void deletePerVolMonthByOrganId(List<Integer> organIdList) {
		PersonalVolumeMonthCriteria example = new PersonalVolumeMonthCriteria();
		PersonalVolumeMonthCriteria.Criteria criteria = example.createCriteria();
		criteria.andOrganIdIn(organIdList);
		personalVolumeMonthService.deleteByExample(example);
	}

	/**
	 * <p>Title: savePerVolMonthHisIbatch</p>   
	 * <p>Description: 批量保存个人运送量月报表统计数据(历史表数据)</p> 
	 * <p>zhaoqing</p>
	 * @param list   
	 */
	@Override
	public void savePerVolMonthHisIbatch(List<PersonalVolumeMonth> list) {
		if (!AppUtils.isNotEmpty(list)) {
			return;
		}
		// 获取主键
		List<Long> idSeqList = getIdSeqList(list.size(), 
				MtSeqContants.MT_PERSONAL_VOLUME_MONTH_HIS_ID_SEQ);
		// 设置主键
		setSeqId(list, idSeqList);
		long beginTime = System.currentTimeMillis();
		// 对象转换
		List<PersonalVolumeMonthHis> hisList = BeanCopierUtils.copyList2List(
				list, PersonalVolumeMonthHis.class, true);
		if (AppUtils.isNotEmpty(hisList)) {
			perVolMonthHisInfoMapper.savePerVolMonthHisIbatch(hisList);
		}
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("=================>PerVolMonthRptServiceImpl savePerVolMonthHisIbatch ")
			.append("save data [").append(list.size()).append("] rows, ")
			.append("take [").append(System.currentTimeMillis() - beginTime).append("] ms");
		LOGGER.debug(strBuffer.toString());
		XxlJobLogger.log(strBuffer.toString());
	}
	
	/**
	 * @Title: setSeqId   
	 *  设置主键 
	 * @author zhaoqing  
	 * @param list
	 * @param idSeqList    
	 */
	private void setSeqId(List<PersonalVolumeMonth> list, List<Long> idSeqList) {
		int index = 0;
		for (PersonalVolumeMonth personalVolumeMonth : list) {
			if (AppUtils.isNotEmpty(idSeqList)) {
				// 设置主键
				personalVolumeMonth.setId(idSeqList.get(index).intValue());
				index++;
			} else {
				personalVolumeMonth.setId(SeqContants.getSequnces(
						MtSeqContants.MT_PERSONAL_VOLUME_MONTH_HIS_ID_SEQ).intValue());
			}
		}
	}

	/**
	 * <p>Title: deletePerVolMonth</p>   
	 * <p>Description: 清空当月表的数据</p> 
	 * <p>zhaoqing</p>   
	 */
	@Override
	public void deletePerVolMonth() {
		personalVolumeMonthService.deleteByExample(new PersonalVolumeMonthCriteria());
	}

	/**
	 * <p>Title: updatePerVolMonthByMonth</p>   
	 * <p>Description: 按项目更新个人运送量月报表历史信息</p> 
	 * <p>zhaoqing</p>
	 * @param organId
	 * @param cycleYm
	 * @return   
	 */
	@Override
	public void updatePerVolMonthByMonth(Integer groupOrganId, Integer organId, 
			Integer cycleYm, List<PersonalVolumeMonth> perVolMonthList) {		
		// 更新月报表历史表数据库信息
		updatePerVolMonthDataByMonth(organId, cycleYm, perVolMonthList);
		// 初始化项目运送量表中的运送人数信息
		List<AmountMonthUserDto> amountMonUsrDtoList = initAmountMonthUserDtoList(
				groupOrganId, perVolMonthList, Arrays.asList(organId), cycleYm);
		// 更新项目运送量表中的运送人数信息
		reportOrganMonthService.updateOrganMonthUser(amountMonUsrDtoList);
		XxlJobLogger.log("==============>成功更新组织[" + organId + "]的项目运送量表中的每月人数信息");
	}
	
	/**
	 * @Title: updatePerVolMonthDataByMonth   
	 *  更新月报表历史表数据库信息
	 * @author zhaoqing  
	 * @param organId
	 * @param cycleYm
	 * @param perVolMonthList 
	 */
	private void updatePerVolMonthDataByMonth(Integer organId, 
			Integer cycleYm, List<PersonalVolumeMonth> perVolMonthList) {
		long beginTime = System.currentTimeMillis();
		// 清空历史表的旧数据（个人运输量按月排名表历史表）
		deletePerVolMonthHis(organId, cycleYm);
		String logInfo = "==============>成功删除组织[" + organId 
				+ "]、月份[" + cycleYm + "]的个人运送量数据库信息";
		XxlJobLogger.log(logInfo);
		LOGGER.debug(logInfo);
		if (AppUtils.isNotEmpty(perVolMonthList)) {
			// 个人运送量数据新增到历史表
			savePerVolMonthHisIbatch(perVolMonthList);
		}
		int listSize = perVolMonthList == null ? 0 : perVolMonthList.size();
		logInfo = "==============>成功更新组织[" + organId + "]的人员个人运送量数据信息[" + listSize 
				+ "]条, 耗时[" + (System.currentTimeMillis() - beginTime) + "]毫秒";
		XxlJobLogger.log(logInfo);
		LOGGER.debug(logInfo);
	}
	
	/**
	 * @Title: deletePerVolMonthHis   
	 *  删除个人运送量月报表历史表数据  
	 * @author zhaoqing  
	 * @param organId
	 * @param cycleYm  
	 */
	private void deletePerVolMonthHis(Integer organId, Integer cycleYm) {
		LOGGER.debug("============>deletePerVolMonthHis: cycleYm:{}", cycleYm);
		if (null == organId || null == cycleYm) {
			return;
		} 
		PersonalVolumeMonthHisCriteria example = new PersonalVolumeMonthHisCriteria();
		PersonalVolumeMonthHisCriteria.Criteria criteria = example.createCriteria();
		criteria.andOrganIdEqualTo(organId);
		criteria.andCycleYmEqualTo(Integer.valueOf(cycleYm));
		int rowCount = personalVolumeMontHisService.deleteByExample(example);
		LOGGER.debug("============>deletePerVolMonthHis: delete [" + rowCount + "] rows");
	}
	
	/**
	 * <p>Title: updatePersonalVolumeMonthHis</p>   
	 * <p>Description: 根据运送员Id和月份更新运送员的历史运送量信息 </p> 
	 * <p>zhaoqing</p>
	 * @param userIds
	 * @param exeBeginTime   
	 */
	public void updatePersonalVolumeMonthHis(String userIds, String exeBeginTime) {
		LOGGER.debug("=============>updatePersonalVolumeMonthHis: "
				+ "userIds:{}, exeBeginTime:{}", userIds, exeBeginTime);
		// 实际任务开始时间转换成日期格式
		Date exeBeginDate = DateUtil.parseStringToDateYYYYMMDDHHMMSS(exeBeginTime);
		// 获取月份
		String cycleYm = DateUtil.formatDateToStringYYMM(exeBeginDate);		
		// 得到实际开始时间第一天00：00：00时间
		Date monthFirstDay = DateCommonUtil.getMonthFirstDay(exeBeginDate);
		// 得到实际开始时间最后一天23：59：59时间
		Date monthLastDay = DateCommonUtil.getMonthLastDay(exeBeginDate);
		// 根据运送员员工ID和任务实际开始时间所在月份统计个人运送量数据 
		List<PersonalVolumeMonth> perVolMonthList = perVolMonthStatServiceUtil
				.getPersonalVolumeMonthByUserId(userIds, monthFirstDay, monthLastDay);
		if (!AppUtils.isNotEmpty(perVolMonthList)) {
			// 统计数据为空则返回
			return;
		}
		// 清除个人运送量月报表历史表中对应的旧数据
		deletePerVolMonthHisByUserIds(userIds, cycleYm);
		// 批量插入新数据
		savePerVolMonthHisIbatch(perVolMonthList);
		LOGGER.debug("=============>updatePersonalVolumeMonthHis End");
	}
	
	/**
	 * @Title: deletePerVolMonthHisByUserIds   
	 *  根据运送员员工ID和月份删除历史表数据
	 * @author zhaoqing  
	 * @param userIds
	 * @param cycleYm  
	 */
	private void deletePerVolMonthHisByUserIds(String userIds, String cycleYm) {
		LOGGER.debug("============>deletePerVolMonthHisByUserIds: "
				+ "userIds:{}, cycleYm:{}", userIds, cycleYm); 
		List<Integer> userIdList = AppUtils.str2Integer(
				StringUtils.replace(userIds, Constant.SPLIT_BLANK, ""));
		int rowCount = 0;
		if (AppUtils.isNotEmpty(userIdList)) {
			PersonalVolumeMonthHisCriteria example = new PersonalVolumeMonthHisCriteria();
			PersonalVolumeMonthHisCriteria.Criteria criteria = example.createCriteria();
			criteria.andUserIdIn(userIdList);
			criteria.andCycleYmEqualTo(Integer.valueOf(cycleYm));
			rowCount = personalVolumeMontHisService.deleteByExample(example);
		}
		LOGGER.debug("============>deletePerVolMonthHisByUserIds: delete [" + rowCount + "] rows");
	}

	/**
	 * @Title: updatePersonalVolumeMonth   
	 *  每天凌晨定时刷新人员个人运送量数据库信息 
	 * @author zhaoqing  
	 * @param organList
	 * @param perVolMonthList
	 * @return
	 */
	@Override
	public void updatePersonalVolumeMonth(List<Integer> organIdList, 
			List<PersonalVolumeMonth> perVolMonthList) {
		XxlJobLogger.log("==============>开始刷新所有组织人员个人运送量数据库最新信息");
		// 清空当月表的旧数据（个人运输量按月排名表）
		deletePerVolMonthByOrganId(organIdList);
		XxlJobLogger.log("==============>成功清空前一天保存的个人运送量数据库信息");
		// 当月最新的个人运送量数据新增到当月表
		savePerVolMonthIbatch(perVolMonthList);
		XxlJobLogger.log("==============>成功刷新所有组织人员个人运送量数据库最新信息");
	}
	
	/**
	 * @Title: initAmountMonthUserDtoList   
	 *  初始化项目运送量表中的运送人数信息
	 * @author zhaoqing  
	 * @param perVolMonthList
	 * @return 
	 */
	@Override
	public List<AmountMonthUserDto> initAmountMonthUserDtoList(Integer groupOrganId, 
			List<PersonalVolumeMonth> perVolMonthList, List<Integer> organIdList, Integer exeYearMonth) {
		List<AmountMonthUserDto> amountMonUsrDtoList = new ArrayList<>();
		try {
			// 人员运送量月报表数据按组织机构分组
			Map<Integer, List<PersonalVolumeMonth>> perVolMonGroupMap = AppUtils.listGroup2Map(
					perVolMonthList, PersonalVolumeMonth.class, "organId", PersonalVolumeMonth.class);
			if (!AppUtils.isNotEmpty(perVolMonGroupMap)) {
				return initAmountMonthUserDtoNull(groupOrganId, organIdList, exeYearMonth);
			} 
			// 循环分组后的数据，初始化项目运送量报表中的人数信息
			for (Map.Entry<Integer, List<PersonalVolumeMonth>> entry : perVolMonGroupMap.entrySet()) {
				List<PersonalVolumeMonth> list = entry.getValue();		
				AmountMonthUserDto dto = initAmountMonthUserDto(groupOrganId, 
						entry.getKey(), exeYearMonth, list.size());
				amountMonUsrDtoList.add(dto);
			}
		} catch (Exception e) {
			LOGGER.error("初始化项目运送量表中的运送人数信息异常", e);
			throw new RuntimeException("初始化项目运送量表中的运送人数信息异常");
		}
		return amountMonUsrDtoList;
	}
	
	/**
	 * @Title: initAmountMonthUserDto   
	 *  初始化AmountMonthUserDto对象
	 * @author zhaoqing  
	 * @param groupOrganId
	 * @param organId
	 * @param exeYearMonth
	 * @param userAmount
	 */
	private AmountMonthUserDto initAmountMonthUserDto(Integer groupOrganId, 
			Integer organId, Integer exeYearMonth, Integer userAmount) {
		AmountMonthUserDto dto = new AmountMonthUserDto();
		dto.setOrganId(organId);
		dto.setGroupOrganId(groupOrganId);
		dto.setExeYearMonth(exeYearMonth);
		dto.setUserAmount(userAmount);
		return dto;
	}
	
	/**
	 * @Title: initAmountMonthUserDtoNull   
	 *  初始化项目运送量表中的运送人数信息(个人运送量返回为空)
	 * @author zhaoqing  
	 * @param groupOrganId
	 * @param organIdList
	 * @param exeYearMonth
	 * @return 
	 */
	@Override
	public List<AmountMonthUserDto> initAmountMonthUserDtoNull(Integer groupOrganId, 
			List<Integer> organIdList, Integer exeYearMonth) {
		List<AmountMonthUserDto> list = new ArrayList<>();
		if (!AppUtils.isNotEmpty(organIdList)) {
			AmountMonthUserDto dto = initAmountMonthUserDto(groupOrganId, 0, exeYearMonth, 0);
			list.add(dto);
		}
		for (Integer organId : organIdList) {
			AmountMonthUserDto dto = initAmountMonthUserDto(groupOrganId, organId, exeYearMonth, 0);
			list.add(dto);
		}
		return list;
	}

	/**
	 * <p>Title: updatePersonalVolumeMonthByOrgan</p>   
	 * <p>Description: 按组织机构刷新个人运送量信息 </p> 
	 * <p>zhaoqing</p>
	 * @param groupOrganId
	 * @param organId
	 * @param perVolMonthList   
	 */
	@Override
	public void updatePersonalVolumeMonthByOrgan(Integer groupOrganId,
			Integer organId, List<PersonalVolumeMonth> perVolMonthList, Integer cycleYm) {
		int perVolSize = 0;
		if (AppUtils.isNotEmpty(perVolMonthList)) {
			perVolSize = perVolMonthList.size();
			// 根据组织机构Id刷新个人运送量数据库信息
			updatePersonalVolumeMonthByOrganId(organId, perVolMonthList);
		}
		// 初始化项目运送量表中的运送人数信息
		AmountMonthUserDto dto = initAmountMonthUserDto(groupOrganId, 
				organId, cycleYm, perVolSize);
		// 更新项目运送量表中的运送人数信息
		reportOrganMonthService.updateOrganMonthUser(Arrays.asList(dto));
		XxlJobLogger.log("==============>成功更新组织[" + organId + "]的项目运送量表中的每月人数信息");	
		if (AppUtils.isNotEmpty(perVolMonthList)) {
			// 刷新人员当月运送量排名缓存信息
			refreshPerVolumeRedis(organId, perVolMonthList, null);
		}
	}
	
	/**
	 * <p>Title: refreshPerVolumeRedis</p>   
	 * <p>Description: 按组织机构刷新个人运送量缓存信息 </p> 
	 * <p>zhaoqing</p>
	 * @param organId
	 * @param list
	 * @param updateType 缓存刷新类型（1:全量刷新, 其他则是增量刷新）  
	 */
	@Override
	public void refreshPerVolumeRedis(int organId, List<PersonalVolumeMonth> list, String updateType) {
		try {
			XxlJobLogger.log("==============>开始刷新当前组织人员当月运送量排名缓存信息：organId:" + organId);
			if (MtConstant.MT_PERVOL_REDIS_UPDATE_TYPE_All.equals(updateType)) {
				// redis缓存：key：organid; field：userId; value:transCount
				String key = MedicalTransRedisConstant.MT_PERSONAL_VOLUME_CUR_MONTH + String.valueOf(organId);
				// 清空个人运送量排名缓存信息
				segiRedisClusterBuilder.getSegiRedisCluster().del(key);
				XxlJobLogger.log("==============>成功清空旧的个人运送量缓存信息");
			}		
			XxlJobLogger.log("==============>成功获取当月个人运送量最新信息[" + list.size() + "]条");
			// 缓存人员个人排名信息
			updatePerVolumeRedisCache.savePerVolumeAll(list);
			XxlJobLogger.log("==============>成功缓存当月个人运送量最新信息");
			XxlJobLogger.log("==============>结束刷新当前组织人员当月运送量排名缓存信息：organId:" + organId);
		} catch (Exception e) {
			LOGGER.error("RefreshPerVolumeRedisException", e);
			XxlJobLogger.log(e);
			throw new RuntimeException("按组织机构刷新组织[" + organId + "]个人运送量缓存信息失败");
		}
	}
	
	/**
	 * @Title: updatePersonalVolumeMonthByOrganId   
	 *  根据组织机构Id刷新个人运送量数据库信息
	 * @author zhaoqing  
	 * @param organId
	 * @param perVolMonthList
	 */
	private void updatePersonalVolumeMonthByOrganId(Integer organId, 
			List<PersonalVolumeMonth> perVolMonthList) {
		XxlJobLogger.log("==============>开始刷新组织[" + organId + "]人员个人运送量数据库最新信息");
		// 清空当月表的旧数据（个人运输量按月排名表）
		deletePerVolMonthByOrganId(Arrays.asList(organId));
		XxlJobLogger.log("==============>成功清空前一天保存的个人运送量数据库信息");
		// 当月最新的个人运送量数据新增到当月表
		savePerVolMonthIbatch(perVolMonthList);
		XxlJobLogger.log("==============>成功刷新组织[" + organId + "]人员个人运送量数据库最新信息");
	}
}
