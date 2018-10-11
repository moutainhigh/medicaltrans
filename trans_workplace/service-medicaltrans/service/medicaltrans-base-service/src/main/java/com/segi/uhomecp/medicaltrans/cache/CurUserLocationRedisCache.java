package com.segi.uhomecp.medicaltrans.cache;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.base.posit.bean.MtCurUserPosit;
import com.segi.uhomecp.medicaltrans.constant.MedicalTransRedisConstant;
import com.segi.uhomecp.medicaltrans.seriable.KryoSeriableStringUtils;
import com.segi.uhomecp.redis.cluster.SegiRedisClusterBuilder;
import com.segi.uhomecp.rest.utils.StringUtils;
import com.segi.uhomecp.wh.common.utils.AppUtils;

/**
 * 人员最新位置缓存
 * @author Administrator
 *
 */
@Component
public class CurUserLocationRedisCache {
	public static final Logger logger = LoggerFactory.getLogger(CurUserLocationRedisCache.class);
	
	@Resource(name="segiRedisCluster")
	private SegiRedisClusterBuilder segiRedisClusterBuilder;
	
	/**
	 * 保存人员位置信息到缓存（新增和修改）
	 * @param mtBuildLocation
	 * @return
	 */
	public ResultInfo addOrUpdateUserLocationRedis(MtCurUserPosit curUserPosit) {
		ResultInfo resultInfo = new ResultInfo();
		if (curUserPosit.getOrganId() == null || curUserPosit.getUserId() == null) {
			resultInfo.setIsSucc(false);
			resultInfo.setMessage("刷新当前人员位置缓存失败,organId为空或userId为空.");
			return resultInfo;
		}
		//redis的哈希表存储。key：organid;field：userId;value:MtCurUserPosit
		String key = MedicalTransRedisConstant.CUR_USER_POSIT + curUserPosit.getOrganId();
		String value = null;
		try {
			value = KryoSeriableStringUtils.seriaObject(curUserPosit);
		} catch (Exception e) {
			logger.warn("Redis缓存人员位置失败,序列化失败", e);
			resultInfo.setMessage("Redis缓存人员位置失败,序列化失败");
			resultInfo.setIsSucc(false);
			return resultInfo;
		}
		try{
			this.segiRedisClusterBuilder.getSegiRedisCluster().hset(key, String.valueOf(curUserPosit.getUserId()), value);
		} catch (Exception e) {
			logger.warn("Redis缓存人员位置失败", e);
			resultInfo.setMessage("Redis缓存人员位置失败");
			resultInfo.setIsSucc(false);
			return resultInfo;
		}
		resultInfo.setIsSucc(true);
		return resultInfo;
	}
	
	/**
	 * 批量插入
	 * @param mtBuildLocationList
	 * @return
	 */
	public ResultInfo addUserLocationListRedis(List<MtCurUserPosit> mtCurUserPositList){
		ResultInfo resultInfo = new ResultInfo();
		if(!AppUtils.isNotEmpty(mtCurUserPositList)){
			resultInfo.setIsSucc(false);
			resultInfo.setMessage("mtCurUserPositList为空，人员位置缓存保存失败");
			return resultInfo;
		}
		try{
			for(MtCurUserPosit mtCurUserPosit:mtCurUserPositList){
				String key = MedicalTransRedisConstant.CUR_USER_POSIT + mtCurUserPosit.getOrganId();
				String value = KryoSeriableStringUtils.seriaObject(mtCurUserPosit);
				this.segiRedisClusterBuilder.getSegiRedisCluster().hset(key, String.valueOf(mtCurUserPosit.getUserId()), value);
			}
		}catch (Exception e) {
			logger.warn("Redis缓存人员最新位置失败", e);
			resultInfo.setMessage("Redis缓存人员最新位置失败");
			resultInfo.setIsSucc(false);
			return resultInfo;
		}
		resultInfo.setIsSucc(true);
		return resultInfo;
	}
	
	/**
	 * 根据项目id查询所有人员位置信息
	 * @param organId
	 * @return
	 */
	public List<MtCurUserPosit> getUserLocationByOrganRedis(Integer organId) {
		if (organId == null) {
			return null;
		}
		try {
			String key = MedicalTransRedisConstant.CUR_USER_POSIT + organId;
			Map<String, String> valueMap = this.segiRedisClusterBuilder.getSegiRedisCluster().hgetAll(key);
			if (valueMap == null || valueMap.isEmpty()) {
				return null;
			}
			
			List<MtCurUserPosit> userLocationList = new ArrayList<MtCurUserPosit>();
			MtCurUserPosit userLocation = null;
			for (String val : valueMap.values()) {
				userLocation = KryoSeriableStringUtils.deSerialObject(val, MtCurUserPosit.class);
				if (userLocation != null) {
					userLocationList.add(userLocation);
				}
			}
			return userLocationList;
		} catch (Exception e) {
			logger.warn("Redis根据项目id查所有缓存人员位置信息失败", e);
			return null;
		}
	}
	
	/**
	 * 通过organId和userId获取某个人员最新的位置信息
	 * @param organId
	 * @param userId
	 * @return
	 */
	public MtCurUserPosit getUserLocationByOrganAndUserIdRedis(Integer organId, Integer userId) {
		if (organId == null || userId == null) {
			return null;
		}
		String key = MedicalTransRedisConstant.CUR_USER_POSIT + organId;
		String field = String.valueOf(userId);
		String value = null;
		try {
			value = this.segiRedisClusterBuilder.getSegiRedisCluster().hget(key, field);
		} catch (Exception e) {
			logger.warn("Redis根据项目id和人员查缓存人员位置信息失败", e);
			return null;
		}
		
		if (!StringUtils.isNotEmpty(value)) {
			return null;
		}
		
		try{
			return KryoSeriableStringUtils.deSerialObject(value, MtCurUserPosit.class);
		} catch (Exception e) {
			logger.warn("Redis根据项目id和人员查缓存人员位置信息失败,反序列化失败", e);
			return null;
		}
	}
	
	/**
	 * 根据organId删除人员位置缓存
	 * @param organId
	 * @return
	 */
	public ResultInfo delUserLocationRedisByOrganId(Integer organId){
		ResultInfo resultInfo = new ResultInfo();
		if (organId == null) {
			resultInfo.setIsSucc(false);
			resultInfo.setMessage("organId为空，删除缓存失败");
			return resultInfo;
		}
		String key = MedicalTransRedisConstant.CUR_USER_POSIT + organId;
		try {
			this.segiRedisClusterBuilder.getSegiRedisCluster().del(key);
		} catch (Exception e) {
			logger.warn("Redis删除人员缓存位置失败", e);
			resultInfo.setMessage("Redis删除人员缓存位置失败");
			resultInfo.setIsSucc(false);
			return resultInfo;
		}
		resultInfo.setIsSucc(true);
		return resultInfo;
	}
	
}
