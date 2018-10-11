package com.segi.uhomecp.medicaltrans.reportjob.report.pervolmonth.utils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.cache.UpdatePerVolumeRedisCache;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonth;
import com.segi.uhomecp.medicaltrans.reportjob.query.pervolmonth.dto.PersonalVolumeMonthDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.pervolmonth.util.PerVolMonthStatServiceUtil;
import com.segi.uhomecp.medicaltrans.reportjob.report.organmonth.dto.AmountMonthUserDto;
import com.segi.uhomecp.medicaltrans.reportjob.report.organmonth.service.ReportOrganMonthService;
import com.segi.uhomecp.medicaltrans.reportjob.report.pervolmonth.service.PerVolMonthRptService;
import com.segi.uhomecp.medicaltrans.utils.DateCommonUtil;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.medicaltrans.utils.MtCommonUtils;
import com.segi.uhomecp.redis.cluster.SegiRedisClusterBuilder;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * @ClassName:  MtPerVolMonthRptHandleUtils   
 * @Description:个人运送量月报表统计Job业务处理类 
 * @author: zhaoqing
 * @date:   2018年8月2日 下午7:17:51
 */
@Component(value = "mtPerVolMonthRptHandleUtils")
public class MtPerVolMonthRptHandleUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MtPerVolMonthRptHandleUtils.class);
	
	@Autowired
	private PerVolMonthStatServiceUtil perVolMonthStatServiceUtil;
	
	@Autowired
	private PerVolMonthRptService perVolMonthRptService;
	
	@Autowired
	private ReportOrganMonthService reportOrganMonthService;
	
	@Resource(name = "segiRedisCluster")
	private SegiRedisClusterBuilder segiRedisClusterBuilder;
	
	@Resource
	private UpdatePerVolumeRedisCache updatePerVolumeRedisCache;
	
	/**
	 * @Title: handle   
	 *  月报表统计job业务处理方法 
	 * @author zhaoqing  
	 * @param groupOrganMap
	 * @return 
	 */
	public ResultInfo handle(Map<Integer, List<Integer>> groupOrganMap) {
		ResultInfo result = new ResultInfo();
		result.setIsSucc(true);
		if (!AppUtils.isNotEmpty(groupOrganMap)) {
			return result;
		}
		// 按照一级物业ID循环处理数据
		for (Map.Entry<Integer, List<Integer>> entry : groupOrganMap.entrySet()) {
			handleJobData(entry.getKey(), entry.getValue(), result);
		}	
		return result;
	}
	
	/**
	 * @Title: handleJobData   
	 *  按一级物业Id分批处理业务月报表数据
	 * @author zhaoqing  
	 * @param groupOrganId
	 * @param organIdList
	 * @param result
	 * @return 
	 */
	public ResultInfo handleJobData(Integer groupOrganId, 
			List<Integer> organIdList, ResultInfo result) {
		Calendar calendar = Calendar.getInstance();
		Date nowDate = new Date();
		calendar.setTime(nowDate);
		if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
			// 每月第一天清空个人运送量当月表所有的数据
			perVolMonthRptService.deletePerVolMonth();
			XxlJobLogger.log("==============>第一天成功清空个人运送量当月表所有的数据");
			return result;
		}
		Date beginDate = DateCommonUtil.getMonthFirstDay(nowDate);
		Date endDate = DateCommonUtil.getYesterDayLastMoment(nowDate);
		String cycleYmStr = DateUtil.getYearMonthOfDate(
				DateUtil.formatDateToStringYYYYMMDD(nowDate));
		// 调用list分组方法将organList以20分组
		List<List<Integer>> oganIdListGroup = MtCommonUtils.splitList(
				organIdList, MtConstant.ORAGAN_ID_PAGESIZE);
		if (!AppUtils.isNotEmpty(oganIdListGroup)) {
			return result;
		}
		// 遍历分组后的组织机构ID
		for (List<Integer> splitOrganIdList : oganIdListGroup) {
			try {
				// 从执行人表统计数据
				List<PersonalVolumeMonth> personalVolumeList = statPerVolMonthData(
						groupOrganId,  splitOrganIdList, beginDate, endDate);
				// 人员运送量月报表数据按组织机构分组
				Map<Integer, List<PersonalVolumeMonth>> perVolMonGroupMap = AppUtils.listGroup2Map(
						personalVolumeList, PersonalVolumeMonth.class, "organId", PersonalVolumeMonth.class);
				// 项目运送量表中的人数据信息
				List<AmountMonthUserDto> amountMonUsrDtoList = null;
				if (!AppUtils.isNotEmpty(perVolMonGroupMap)) {
					// 初始化项目运送量表中的运送人数信息
					amountMonUsrDtoList = perVolMonthRptService.initAmountMonthUserDtoNull(
							groupOrganId, organIdList, Integer.valueOf(cycleYmStr));
					// 更新项目运送量表中的运送人数信息
					reportOrganMonthService.updateOrganMonthUser(amountMonUsrDtoList);
					XxlJobLogger.log("==============>成功更新项目运送量表中的每月人数信息");
					continue;
				}
				// 循环分组后的数据，按项目处理统计的个人运送数据
				handlePerVolMonthByOrgan(groupOrganId, cycleYmStr, perVolMonGroupMap, splitOrganIdList);
			} catch (Exception e) {
				result.setIsSucc(false);
				LOGGER.error("handle Exception", e);
				XxlJobLogger.log(e);
			}	
		}
		return result;
	}
	
	/**
	 * @Title: handlePerVolMonthByOrgan   
	 *  根据组织机构刷新个人运送量信息 
	 * @author zhaoqing  
	 * @param groupOrganId
	 * @param cycleYmStr
	 * @param perVolMonGroupMap  
	 */
	private void handlePerVolMonthByOrgan(Integer groupOrganId, String cycleYmStr, 
			Map<Integer, List<PersonalVolumeMonth>> perVolMonGroupMap, List<Integer> organIdList) {
		for (Integer organId : organIdList) {
			try {
				List<PersonalVolumeMonth> list = perVolMonGroupMap.get(organId);
				// 根据组织机构刷新个人运送量信息
				perVolMonthRptService.updatePersonalVolumeMonthByOrgan(
						groupOrganId, organId, list, Integer.valueOf(cycleYmStr));
			} catch (Exception e) {
				LOGGER.error("handlePerVolMonthByOrgan Exception", e);
				XxlJobLogger.log(e);
			}
		}
	}
	
	/**
	 * @Title: updatePerVolumeRedis   
	 *  循环组织Id刷新人员当月运送量排名缓存信息
	 * @author zhaoqing  
	 * @param personalVolumeList
	 * @param splitOrganIdList 
	 */
	private void updatePerVolumeRedis(List<PersonalVolumeMonth> personalVolumeList, 
			List<Integer> splitOrganIdList, String updateType) {
		Map<Integer, List<PersonalVolumeMonth>> perVolMap = new HashMap<>();					
		// 根据组织机构Id对个人运送量信息进行分组
		perVolMap = AppUtils.listGroup2Map(
				personalVolumeList, PersonalVolumeMonth.class, "organId", PersonalVolumeMonth.class);
		// 循环刷新运送量排名缓存信息	
		for (Integer organId : splitOrganIdList) {
			List<PersonalVolumeMonth> list = perVolMap.get(organId);
			if (AppUtils.isNotEmpty(list)) {
				// 全量刷新人员当月运送量排名缓存信息
				perVolMonthRptService.refreshPerVolumeRedis(organId, list, updateType);			
			}	
		}
	}
	
	/**
	 * @Title: statPerVolMonthData   
	 *  从执行人表统计人员月运送量信息
	 * @author zhaoqing  
	 * @param groupOrganId
	 * @param organIdList
	 * @return 
	 */
	private List<PersonalVolumeMonth> statPerVolMonthData(Integer groupOrganId, 
			List<Integer> organIdList, Date beginDate, Date endDate) {
		PersonalVolumeMonthDto dto = new PersonalVolumeMonthDto();
		dto.setOrganIdList(organIdList);
		dto.setGroupOrganId(groupOrganId);
		dto.setBeginDate(beginDate);
		dto.setEndDate(endDate);
		String cycleYmStr = DateUtil.getYearMonthOfDate(
				DateUtil.formatDateToStringYYYYMMDD(beginDate));
		dto.setCycleYm(Integer.valueOf(cycleYmStr));
		dto.setStatType(MtConstant.MT_PERVOL_STAT_TYPE_ONE);
		// 通过项目id（先按每次in 20条）统计EVT_MT_TASK_EXCUTORS（医疗运输单执行人）表的数据。
		List<PersonalVolumeMonth> perVolMonthList = perVolMonthStatServiceUtil.getPersonalVolumeMonth(dto);
		XxlJobLogger.log("==============>成功从执行人表获取个人运送量最新信息[" + perVolMonthList.size() + "]条");
		if (AppUtils.isNotEmpty(perVolMonthList)) {
			return perVolMonthList;
		}
		return null;
	}
	
	/**
	 * @Title: getGroupOrganMap   
	 *  获取排名表Map信息 
	 * @author zhaoqing  
	 * @param arg0
	 * @return 
	 */
	public Map<Integer, List<Integer>> getGroupOrganMap(String arg0) {
		Map<Integer, List<Integer>> groupOrganMap = new HashMap<>();
		if (StringUtils.isNotBlank(arg0)) {
			String[] params = arg0.replaceAll(" ", "").split(Constant.SPLIT_COMMA);
			if (params.length == 2 && NumberUtils.isDigits(params[0]) 
					&& NumberUtils.isDigits(params[1])) {
				// 如果任务传入组织机构参数，则按传入的参数执行
				groupOrganMap.put(Integer.valueOf(params[0]), 
						Arrays.asList(Integer.valueOf(params[1])));
			}
		} else {
			groupOrganMap = MtCommonServiceUtils.getTransScheduleMap();
		}
		return groupOrganMap;
	}
	
	/**
	 * @Title: redisJobhandle   
	 *  个人月运送量排名缓存刷新Job业务处理 
	 * @author zhaoqing  
	 * @param groupOrganMap
	 * @return  
	 */
	public ResultInfo redisJobhandle(Map<Integer, List<Integer>> groupOrganMap) {
		ResultInfo result = new ResultInfo();
		result.setIsSucc(true);
		if (!AppUtils.isNotEmpty(groupOrganMap)) {
			return result;
		}
		// 按照一级物业ID循环处理数据
		for (Map.Entry<Integer, List<Integer>> entry : groupOrganMap.entrySet()) {
			handleRedisJobData(entry.getKey(), entry.getValue(), result);
		}	
		return result;
	}
	
	/**
	 * @Title: handleRedisJobData   
	 *  实时刷新个人月排名缓存信息
	 * @author zhaoqing  
	 * @param groupOrganId
	 * @param organIdList
	 * @param result
	 * @return
	 */
	public ResultInfo handleRedisJobData(Integer groupOrganId, 
			List<Integer> organIdList, ResultInfo result) {
		Date nowDate = new Date();
		Date beginDate = DateCommonUtil.getMonthFirstDay(nowDate);
		Date endDate = DateCommonUtil.getMonthLastDay(nowDate);
		// 调用list分组方法将organList以20分组
		List<List<Integer>> oganIdListGroup = MtCommonUtils.splitList(
				organIdList, MtConstant.ORAGAN_ID_PAGESIZE);
		if (!AppUtils.isNotEmpty(oganIdListGroup)) {
			return result;
		}
		// 遍历分组后的组织机构ID
		for (List<Integer> splitOrganIdList : oganIdListGroup) {
			try {
				// 从执行人表统计数据
				List<PersonalVolumeMonth> personalVolumeList = statPerVolMonthData(
						groupOrganId,  organIdList, beginDate, endDate);
				// 循环组织Id刷新人员当月运送量排名缓存信息
				updatePerVolumeRedis(personalVolumeList, splitOrganIdList, 
						MtConstant.MT_PERVOL_REDIS_UPDATE_TYPE_All);
			} catch (Exception e) {
				result.setIsSucc(false);
				LOGGER.error("handle Exception", e);
				XxlJobLogger.log(e);
			}	
		}
		return result;
	}
	
}
