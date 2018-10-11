package com.segi.uhomecp.medicaltrans.cache;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.constant.MedicalTransRedisConstant;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonth;
import com.segi.uhomecp.redis.cluster.SegiRedisClusterBuilder;
import com.segi.uhomecp.utils.DateUtil;
import com.segi.uhomecp.wh.common.utils.AppUtils;

/**
 * @ClassName:  UpdatePerVolumeRedisCache   
 * @Description:人员当月运送量排名信息缓存类
 * @author: zhaoqing
 * @date:   2018年6月25日 下午8:30:32
 */
@Component
public class UpdatePerVolumeRedisCache {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(UpdatePerVolumeRedisCache.class);
	
	@Resource(name = "segiRedisCluster")
	private SegiRedisClusterBuilder segiRedisClusterBuilder;
	
	/**
	 * @Title: savePerVolumeIncreaseRedCache   
	 *  全量缓存人员当月运送量排名信息 
	 * @author zhaoqing  
	 * @return 
	 */
	public ResultInfo savePerVolumeAll(List<PersonalVolumeMonth> list) {
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setIsSucc(true);
		if (!AppUtils.isNotEmpty(list)) {
			return resultInfo;
		}
		// 获取当天的23:59:59
		String endDateStr = DateUtil.convertEndDate(DateUtil.formatDateToStringYYYYMMDD(new Date()));
		// 获取当天最后时刻的毫秒值
		long endDateTime = DateUtil.parseStringToDateYYYYMMDDHHMMSS(endDateStr).getTime();
		for (PersonalVolumeMonth personalVolumeMonth : list) {
			// redis缓存：key：organid; field：userId; value:transCount
			String key = MedicalTransRedisConstant.MT_PERSONAL_VOLUME_CUR_MONTH 
					+ String.valueOf(personalVolumeMonth.getOrganId());
			try {
				this.segiRedisClusterBuilder.getSegiRedisCluster()
					.hincrBy(key, String.valueOf(personalVolumeMonth.getUserId()), 
							personalVolumeMonth.getTransAmount());
				// 设置当天最后时刻为失效时间
				this.segiRedisClusterBuilder.getSegiRedisCluster().pexpireAt(key, endDateTime);
			} catch (Exception e) {
				StringBuffer userInfo = new StringBuffer();
				userInfo.append("[organId:").append(personalVolumeMonth.getOrganId())
					.append(", userId:").append(personalVolumeMonth.getUserId())
					.append(", transVolume:").append(personalVolumeMonth.getTransAmount())
					.append("]");
				LOGGER.warn("Redis缓存人员" + String.valueOf(userInfo) + "当月运送量排名信息失败", e);
				resultInfo.setMessage("Redis缓存人员" + String.valueOf(userInfo) + "当月运送量排名信息失败");
				resultInfo.setIsSucc(false);
			}
		}
		return resultInfo;
	}
}
