package com.segi.uhomecp.medicaltrans.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.constant.MedicalTransRedisConstant;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.dto.PersonalVolumeMonthDto;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonth;
import com.segi.uhomecp.redis.cluster.SegiRedisClusterBuilder;
import com.segi.uhomecp.utils.DateUtil;
import com.segi.uhomecp.utils.FastjsonUtils;

/**
 * @ClassName:  PersonalVolumeRedisCache   
 * @Description:人员当月运送量排名信息缓存类
 * @author: zhaoqing
 * @date:   2018年6月25日 下午8:30:32
 */
@Component
public class PersonalVolumeRedisCache {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(PersonalVolumeRedisCache.class);
	
	@Resource(name = "segiRedisCluster")
	private SegiRedisClusterBuilder segiRedisClusterBuilder;
	
	/**
	 * @Title: savePerVolumeIncrease  
	 *  增量缓存人员当月运送量排名信息 
	 * @author zhaoqing  
	 * @param organId 组织机构ID
	 * @param userId 用户ID
	 * @param transCount 运输量
	 * @return 
	 */
	public ResultInfo savePerVolumeIncrease(int organId, int userId, int transCount) {
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setIsSucc(true);
		StringBuffer userInfo = new StringBuffer();
		userInfo.append("[organId:").append(organId)
			.append(", userId:").append(userId)
			.append(", transVolume:").append(transCount)
			.append("]");
		// 获取当天的23:59:59
		String endDateStr = DateUtil.convertEndDate(DateUtil.formatDateToStringYYYYMMDD(new Date()));
		// 获取当天最后时刻的毫秒值
		long endDateTime = DateUtil.parseStringToDateYYYYMMDDHHMMSS(endDateStr).getTime();
		// redis缓存：key：organid; field：userId; value:transCount
		String key = MedicalTransRedisConstant.MT_PERSONAL_VOLUME_CUR_MONTH + String.valueOf(organId);
		try {
			this.segiRedisClusterBuilder.getSegiRedisCluster()
				.hincrBy(key, String.valueOf(userId), transCount);
			// 设置当天最后时刻为失效时间
			this.segiRedisClusterBuilder.getSegiRedisCluster().pexpireAt(key, endDateTime);
		} catch (Exception e) {
			LOGGER.warn("=============>Redis缓存人员" + String.valueOf(userInfo) + "当月运送量排名信息失败", e);
			resultInfo.setMessage("Redis缓存人员" + String.valueOf(userInfo) + "当月运送量排名信息失败");
			resultInfo.setIsSucc(false);
			return resultInfo;
		}
		LOGGER.info("=============>Redis缓存人员" + String.valueOf(userInfo) + "当月运送量排名信息成功");
		return resultInfo;
	}
	
	/**
	 * @Title: getPersonalVolumeRed   
	 *  获取人员当月运送量排名的缓存信息
	 * @author zhaoqing  
	 * @param organId
	 * @param userId
	 * @return     
	 */
	public PersonalVolumeMonth getPersonalVolumeRed(int organId, int userId) {
		PersonalVolumeMonth personalVolumeMonth = new PersonalVolumeMonth();
		String key = MedicalTransRedisConstant.MT_PERSONAL_VOLUME_CUR_MONTH + String.valueOf(organId);
		String value = this.segiRedisClusterBuilder
				.getSegiRedisCluster().hget(key, String.valueOf(userId));
		if (StringUtils.isEmpty(value)) {
			return null;
		}
		personalVolumeMonth.setOrganId(organId);
		personalVolumeMonth.setUserId(userId);
		personalVolumeMonth.setTransAmount(Integer.valueOf(value));
		return personalVolumeMonth;
		
	}
	
	/**
	 * @Title: getPersonalVolumeRedAll   
	 *  根据组织Id查询组织下所有人员当月运送量排名信息
	 * @author zhaoqing  
	 * @param organId
	 * @return 
	 */
	public List<PersonalVolumeMonthDto> getPersonalVolumeAll(int organId) {
		LOGGER.debug("===========>根据组织Id查询组织下所有人员当月运送量排名信息: organId:{}", organId);
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setIsSucc(true);
		// redis缓存：key：organid; field：userId; value:transCount
		String key = MedicalTransRedisConstant.MT_PERSONAL_VOLUME_CUR_MONTH + String.valueOf(organId);
		// 获取当前项目的所有人员当月排名信息
		Map<String, String> valueMap = new HashMap<>();
		try {
			valueMap = this.segiRedisClusterBuilder.getSegiRedisCluster().hgetAll(key);
		} catch (Exception e) {
			LOGGER.error("根据组织Id查询组织下所有人员当月运送量排名的缓存信息失败", e);
		}
		if (valueMap == null || valueMap.isEmpty()) {
			return null;
		}
		
		List<PersonalVolumeMonthDto> perVolumeList = new ArrayList<PersonalVolumeMonthDto>();
		PersonalVolumeMonthDto personalVolumeMonth = null;
		for (Map.Entry<String, String> entry : valueMap.entrySet()) {
			if (null == entry) {
				continue;
			}
			personalVolumeMonth = new PersonalVolumeMonthDto();
			personalVolumeMonth.setOrganId(organId);
			personalVolumeMonth.setUserId(Integer.valueOf(entry.getKey()));
			personalVolumeMonth.setTransAmount(Integer.valueOf(entry.getValue()));
			personalVolumeMonth.setTransVolume(Integer.valueOf(entry.getValue()));
			perVolumeList.add(personalVolumeMonth);
		}
		// 人员当月运送量按运送量降序排名
		sortPerVolumeList(perVolumeList);
		int rank = 1;
		for (PersonalVolumeMonthDto personalVolumeMonthDto : perVolumeList) {
			// 设置排名
			personalVolumeMonthDto.setRank(rank++);
		}
		LOGGER.debug("===========>根据组织Id查询组织下所有人员当月运送量排名信息: resultList:{}", 
				FastjsonUtils.toJsonString(perVolumeList));
		return perVolumeList;
	}
	
	/**
	 * @Title: sortPerVolumeList   
	 *  人员当月运送量按运送量降序排名
	 * @author zhaoqing  
	 * @param perVolumeList 
	 */
	private void sortPerVolumeList(List<PersonalVolumeMonthDto> perVolumeList) {
		Collections.sort(perVolumeList, new Comparator<PersonalVolumeMonthDto>() {
			/**
			 * int compare(PersonalVolumeMonth o1, PersonalVolumeMonth o2)
			 * 返回一个基本类型的整型， 返回负数表示：o1 小于o2， 返回0 表示：o1和o2相等， 返回正数表示：o1大于o2。
			 */
			public int compare(PersonalVolumeMonthDto o1, PersonalVolumeMonthDto o2) {
				// 按照运输量大小进行降序排列
				if (o1.getTransAmount() - o2.getTransAmount() < 0) {
					return 1;
				}
				if (o1.getTransAmount() - o2.getTransAmount() == 0) {
					return 0;
				}
				return -1;
			}
		});
	}
}
