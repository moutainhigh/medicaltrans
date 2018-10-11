package com.segi.uhomecp.medicaltrans.cache;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocation;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocationCriteria;
import com.segi.uhomecp.medicaltrans.base.location.service.MtBuildLocationService;
import com.segi.uhomecp.medicaltrans.constant.MedicalTransRedisConstant;
import com.segi.uhomecp.medicaltrans.seriable.KryoSeriableStringUtils;
import com.segi.uhomecp.redis.cluster.SegiRedisClusterBuilder;
import com.segi.uhomecp.rest.utils.StringUtils;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.AppUtils;

/**
 * 位置redis缓存
 * @author Administrator
 *
 */
@Component
public class MtLocationGrabRedisCache {
	public static final Logger logger = LoggerFactory.getLogger(MtLocationGrabRedisCache.class);
	
	@Resource(name="segiRedisCluster")
	private SegiRedisClusterBuilder segiRedisClusterBuilder;
	
	@Autowired
	MtBuildLocationService mtBuildLocationService;
	
	/**
	 * 保存位置信息到缓存（新增和修改）
	 * @param groupId
	 * @param mtTask
	 */
	public ResultInfo addOrUpdateLocationRedis(MtBuildLocation mtBuildLocation) {
		ResultInfo resultInfo = new ResultInfo();
		if (mtBuildLocation == null || mtBuildLocation.getOrganId() == null || mtBuildLocation.getLocationId() == null) {
			resultInfo.setIsSucc(false);
			resultInfo.setMessage("organId或locationId为空，位置缓存保存失败");
			return resultInfo;
		}
		//redis的哈希表存储。key：organid;field：locationId;value:mtBuildLocation
		String key = MedicalTransRedisConstant.LOCATION + mtBuildLocation.getOrganId();
		String value = null;
		try {
			value = KryoSeriableStringUtils.seriaObject(mtBuildLocation);
		} catch (Exception e) {
			logger.warn("Redis缓存位置失败,序列化失败", e);
			resultInfo.setMessage("Redis缓存位置失败,序列化失败");
			resultInfo.setIsSucc(false);
			return resultInfo;
		}
		try{
			this.segiRedisClusterBuilder.getSegiRedisCluster().hset(key, String.valueOf(mtBuildLocation.getLocationId()), value);
		}catch (Exception e) {
			logger.warn("Redis缓存位置失败", e);
			resultInfo.setMessage("Redis缓存位置失败");
			resultInfo.setIsSucc(false);
			return resultInfo;
		}
		resultInfo.setIsSucc(true);
		
		return resultInfo;
	}
	
	/**
	 * 批量插入locationList
	 * @return
	 */
	public ResultInfo addLocationListRedis(List<MtBuildLocation> mtBuildLocationList){
		ResultInfo resultInfo = new ResultInfo();
		if(!AppUtils.isNotEmpty(mtBuildLocationList)){
			resultInfo.setIsSucc(false);
			resultInfo.setMessage("mtBuildLocationList为空，位置缓存保存失败");
			return resultInfo;
		}
		try{
			for(MtBuildLocation mtBuildLocation:mtBuildLocationList){
				String key = MedicalTransRedisConstant.LOCATION + mtBuildLocation.getOrganId();
				String value = KryoSeriableStringUtils.seriaObject(mtBuildLocation);
				this.segiRedisClusterBuilder.getSegiRedisCluster().hset(key, String.valueOf(mtBuildLocation.getLocationId()), value);
			}
		}catch (Exception e) {
			logger.warn("Redis缓存位置失败", e);
			resultInfo.setMessage("Redis缓存位置失败");
			resultInfo.setIsSucc(false);
			return resultInfo;
		}
		resultInfo.setIsSucc(true);
		return resultInfo;
	}
	
	/**
	 * 删除位置信息缓存(项目id和位置id)
	 * @param mtBuildLocationDto
	 * @return
	 */
	public ResultInfo delLocationRedis(MtBuildLocation mtBuildLocation){
		ResultInfo resultInfo = new ResultInfo();
		if (mtBuildLocation == null || mtBuildLocation.getOrganId() == null || mtBuildLocation.getLocationId() == null) {
			resultInfo.setIsSucc(false);
			resultInfo.setMessage("organId或locationId为空，删除缓存失败");
			return resultInfo;
		}
		String key =  MedicalTransRedisConstant.LOCATION + mtBuildLocation.getOrganId();
		try {
			this.segiRedisClusterBuilder.getSegiRedisCluster().hdel(key, String.valueOf(mtBuildLocation.getLocationId()));
		} catch (Exception e) {
			logger.warn("Redis删除缓存位置失败", e);
			resultInfo.setMessage("Redis删除缓存位置失败");
			resultInfo.setIsSucc(false);
			return resultInfo;
		}
		resultInfo.setIsSucc(true);
		return resultInfo;
	}
	
	/**
	 * 删除位置信息缓存(项目id)
	 * @param mtBuildLocationDto
	 * @return
	 */
	public ResultInfo delLocationRedisByOrganId(Integer organId){
		ResultInfo resultInfo = new ResultInfo();
		if (organId == null) {
			resultInfo.setIsSucc(false);
			resultInfo.setMessage("organId为空，删除缓存失败");
			return resultInfo;
		}
		String key =  MedicalTransRedisConstant.LOCATION + organId;
		try {
			this.segiRedisClusterBuilder.getSegiRedisCluster().del(key);
		} catch (Exception e) {
			logger.warn("Redis删除缓存位置失败", e);
			resultInfo.setMessage("Redis删除缓存位置失败");
			resultInfo.setIsSucc(false);
			return resultInfo;
		}
		resultInfo.setIsSucc(true);
		return resultInfo;
	}
	
	/**
	 * 根据项目id查询所有位置信息
	 * @param organId
	 * @return
	 */
	public List<MtBuildLocation> getLocationByOrganRedis(Integer organId) {
		if (organId == null) {
			return null;
		}
		try {
			String key = MedicalTransRedisConstant.LOCATION + organId;
			Map<String, String> valueMap = this.segiRedisClusterBuilder.getSegiRedisCluster().hgetAll(key);
			if (valueMap == null || valueMap.isEmpty()) {
				List<MtBuildLocation> mtBuildLocationList = getLocationDbByOrganId(organId);
				if(AppUtils.isNotEmpty(mtBuildLocationList)){
					addLocationListRedis(mtBuildLocationList);
					return mtBuildLocationList;
				}
			}
			
			List<MtBuildLocation> locationList = new ArrayList<MtBuildLocation>();
			MtBuildLocation location = null;
			for (String val : valueMap.values()) {
				location = KryoSeriableStringUtils.deSerialObject(val, MtBuildLocation.class);
				if (location != null) {
					locationList.add(location);
				}
			}
			return locationList;
		} catch (Exception e) {
			logger.warn("Redis根据项目id查所有缓存位置信息失败", e);
			return null;
		}
	} 
	
	/**
	 * 根据项目id和位置id查位置信息
	 * @param organId
	 * @param locationId
	 * @return
	 */
	public MtBuildLocation getLocationByOrganAndLocationIdRedis(Integer organId,Integer locationId){
		if (organId == null || locationId == null) {
			return null;
		}
		MtBuildLocation result = null;
		String key = MedicalTransRedisConstant.LOCATION + organId;
		String field = String.valueOf(locationId);
		String value = null;
		try {
			value = this.segiRedisClusterBuilder.getSegiRedisCluster().hget(key, field);
			if(StringUtils.isEmpty(value)){
				//先判断key是否存在,当存在，只校验科室;当不存在，需要校验整个organid。
				boolean keyesit = this.segiRedisClusterBuilder.getSegiRedisCluster().exists(key);
				if(keyesit){
					//当key存在，缓存中没查到数据需要去数据库验证
					MtBuildLocation locationDb = getLocationDb(locationId);
					if(locationDb!=null){
						addOrUpdateLocationRedis(locationDb);
						return locationDb;
					}
				}else{
					List<MtBuildLocation> mtBuildLocationList = getLocationDbByOrganId(organId);
					if(AppUtils.isNotEmpty(mtBuildLocationList)){
						addLocationListRedis(mtBuildLocationList);
					}
					MtBuildLocation locationDb = getLocationDb(locationId);
					return locationDb;
				}
			}
		}catch (Exception e) {
			logger.warn("Redis根据项目id和位置id查所有缓存位置信息失败", e);
			return null;
		}
		if (value!=null && StringUtils.isNotEmpty(value)) {
			try {
				result = KryoSeriableStringUtils.deSerialObject(value, MtBuildLocation.class);
			}catch (Exception e) {
				logger.warn("Redis根据项目id和位置id查所有缓存位置信息失败，反序列化失败", e);
				return null;
			}
		}
		return result;
	}
	
	/**
	 * 通过数据库查位置详情
	 * @param locationId
	 * @return
	 */
	public MtBuildLocation  getLocationDb(Integer locationId){
		MtBuildLocation mtBuildLocation = mtBuildLocationService.selectByPrimaryKey(locationId);
		if(mtBuildLocation!=null){
			return mtBuildLocation;
		}else{
			return null;
		}
	}
	
	/**
	 * 根据项目ID查位置信息list(数据库)
	 */
	public List<MtBuildLocation> getLocationDbByOrganId(Integer organId){
		MtBuildLocationCriteria example = new MtBuildLocationCriteria();
		MtBuildLocationCriteria.Criteria criteria = example.createCriteria();
		criteria.andOrganIdEqualTo(organId);
		//criteria.andStatusEqualTo((Constant.STATUS_CD_NORMAL));
		return mtBuildLocationService.selectByExample(example);
	}
	

}
