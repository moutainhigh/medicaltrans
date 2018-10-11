package com.segi.uhomecp.medicaltrans.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import segi.medicaltrans.base.transtype.TransTypeIce;

import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.baseinfo.transtype.dto.MtTransTypeParentDto;
import com.segi.uhomecp.medicaltrans.baseinfo.transtype.service.MtTransTypeInfoService;
import com.segi.uhomecp.medicaltrans.constant.MedicalTransRedisConstant;
import com.segi.uhomecp.medicaltrans.seriable.KryoSeriableStringUtils;
import com.segi.uhomecp.redis.cluster.SegiRedisClusterBuilder;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;

/**
 * 
 * 描述: 运输类型redis
 * 创建人: liuyi@sige.com   
 * 创建时间: 2018年3月29日 下午4:21:11
 */
@Component
public class TransTypeRedisCache {

	public static final Logger logger = LoggerFactory.getLogger(TransTypeRedisCache.class);
	
	@Resource(name="segiRedisCluster")
	private SegiRedisClusterBuilder segiRedisClusterBuilder;
	
	@Autowired
	public MtTransTypeInfoService mtTransTypeInfoService;
	
	/**
	 * 删除项目下全部运输类型信息
	 * @param organId
	 */
	public void delTransTypeRedis(Integer organId) {
		String key = MedicalTransRedisConstant.TRANS_TYPE + organId;
		this.segiRedisClusterBuilder.getSegiRedisCluster().del(key);
	}
	
	/**
	 * 删除项目下单个field运输类型信息
	 * @param organId
	 */
	public void delTransTypeRedis(Integer organId, String transTypeParentCode) {
		String key = MedicalTransRedisConstant.TRANS_TYPE + organId;
		this.segiRedisClusterBuilder.getSegiRedisCluster().hdel(key, transTypeParentCode);
	}
	
	/**
	 * 通过organId获取所有运输类型信息
	 * @param organId
	 * @return
	 */
	public Map<String, MtTransTypeParentDto> getTransTypeDepRedis(Integer organId) {
		String key = MedicalTransRedisConstant.TRANS_TYPE + organId;
		Map<String, String> valueMap = null;
		try {
			valueMap = this.segiRedisClusterBuilder.getSegiRedisCluster().hgetAll(key);
		} catch (Exception e) {
			logger.warn("organId=" + organId + "，获取redis缓存失败", e);
		}
		if (valueMap == null || valueMap.isEmpty()) {
			//缓存没有查到就根据organId刷新缓存
			return refreshRedis(organId);
		}
		Map<String, MtTransTypeParentDto> resultMap = new TreeMap<String, MtTransTypeParentDto>();
		MtTransTypeParentDto build = null;
		for (String val : valueMap.values()) {
			try {
				build = KryoSeriableStringUtils.deSerialObject(val, MtTransTypeParentDto.class);
			} catch (Exception e) {
				logger.warn("Kryo反序列化用运输类型失败", e);
			}
			if (build != null) {
				resultMap.put(build.getTransTypeParentCode(), build);
			}
		}
		return resultMap;
	}
	
	/**
	 * 
	 * 类描述: 获取项目运输大类下所有运输类型
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月29日 下午6:50:53
	 */
	public MtTransTypeParentDto getTransTypeParent(Integer organId, String transTypeParentCode){
		if (organId == null) {
			return null;
		}
		String value = null;
		try {
			String key = MedicalTransRedisConstant.TRANS_TYPE + organId;
			value = this.segiRedisClusterBuilder.getSegiRedisCluster().hget(key, transTypeParentCode);
		} catch (Exception e) {
			logger.warn("organId=" + organId + "，获取redis缓存失败", e);
		}
		if (StringUtils.isEmpty(value)) {
			// 查询缓存没有结果 就刷新缓存
			Map<String, MtTransTypeParentDto> transTypeMap = refreshRedis(organId, transTypeParentCode);
			if (null == transTypeMap || transTypeMap.size() == 0) {
				return null;
			}
			return transTypeMap.get(transTypeParentCode);
		}
		MtTransTypeParentDto build = null;
		try {
			build = KryoSeriableStringUtils.deSerialObject(value, MtTransTypeParentDto.class);
		} catch (Exception e) {
			logger.warn("Kryo反序列化用运输类型失败", e);
		}
		return build;
	}
	
	/**
	 * 
	 * 类描述: 新增或修改运送类型
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月29日 下午7:53:45
	 */
	public ResultInfo addOrUpdateTranstType(Integer organId, String transTypeParentCode, TransTypeIce transTypeIce){
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setIsSucc(true);
		resultInfo.setMessage("新增或修改运送类型成功");
		if (transTypeIce == null 
				|| organId == null 
				|| transTypeParentCode == null) {
			resultInfo.setIsSucc(false);
			resultInfo.setMessage("organId或transTypeParentCode或mtTransTypeDto为空，刷新缓存失败");
			return resultInfo;
		}
		MtTransTypeParentDto typeParent = this.getTransTypeParent(organId, transTypeParentCode);
		String key = MedicalTransRedisConstant.TRANS_TYPE + organId;
		if (typeParent == null) { // 缓存中没有， 直接添加
			// 设置父类
			// 设置MtTransTypeParentDto
			typeParent = new MtTransTypeParentDto();
			Map<String,TransTypeIce> transTypeMap = new HashMap<String, TransTypeIce>();
			typeParent.setTransTypeParentCode(transTypeParentCode);
			transTypeMap.put(String.valueOf(transTypeIce.getTransTypeId()), transTypeIce);
			typeParent.setTransTypetMap(transTypeMap);
			String value = KryoSeriableStringUtils.seriaObject(typeParent);
			// key organID field 大类  value Map<主键 小类>
			this.segiRedisClusterBuilder.getSegiRedisCluster().hset(key, transTypeParentCode, value);
		}else {
			// 缓存中存在 更新 MtTransTypeParentDto
			TransTypeIce typeIce = typeParent.getTransTypetMap().get(transTypeIce.getTransTypeId());
			if (typeIce == null) {
				// 不存在运输类型直接新增
				typeParent.getTransTypetMap().put(transTypeIce.getTransTypeId(), transTypeIce);
				String value = KryoSeriableStringUtils.seriaObject(typeParent);
				this.segiRedisClusterBuilder.getSegiRedisCluster().hset(key, transTypeParentCode, value);
			} else {
				// 存在修改
				BeanCopierUtils.copyProperties(transTypeIce, typeIce, true);
				String value = KryoSeriableStringUtils.seriaObject(typeParent);
				this.segiRedisClusterBuilder.getSegiRedisCluster().hset(key, transTypeParentCode, value);
			}
		}
		return resultInfo;
	}
	
	/**
	 * 
	 * 类描述: 删除运输类型
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年4月1日 上午11:55:38
	 */
	public ResultInfo delTransType(Integer organId, String transTypeParentCode, TransTypeIce transTypeIce){
		ResultInfo resultInfo = new ResultInfo();
		if (transTypeIce == null 
				|| organId == null 
				|| transTypeParentCode == null) {
			resultInfo.setIsSucc(false);
			resultInfo.setMessage("organId或transTypeParentCode或mtTransTypeDto为空，刷新缓存失败");
			return resultInfo;
		}
		MtTransTypeParentDto typeParent = this.getTransTypeParent(organId, transTypeParentCode);
		String key = MedicalTransRedisConstant.TRANS_TYPE + organId;
		
		if (typeParent == null) {
			//没有不用删除
		}else {
			typeParent.getTransTypetMap().remove(transTypeIce.getTransTypeId());
			String value = KryoSeriableStringUtils.seriaObject(typeParent);
			this.segiRedisClusterBuilder.getSegiRedisCluster().hset(key, transTypeParentCode, value);
		}
		return resultInfo;
	}

	/**
	 * 
	 * @param 添加运输
	 */
	public Map<String, MtTransTypeParentDto> addTransTypeRedis(Integer organId, Map<String, List<TransTypeIce>> transTypeMap) {
		if (null == transTypeMap || transTypeMap.size() == 0) {
			return null;
		}
		try {
			Map<String, String> redisMap = new HashMap<String, String>();
			Map<String, MtTransTypeParentDto> transTypeParentDtoMap= new HashMap<String, MtTransTypeParentDto>();
			for (Entry<String, List<TransTypeIce>> entry : transTypeMap.entrySet()) {
				// 设置MtTransTypeParentDto
				MtTransTypeParentDto typeParent = new MtTransTypeParentDto();
				Map<String,TransTypeIce> transTypeRedisMap = new HashMap<String, TransTypeIce>();
				typeParent.setTransTypeParentCode(entry.getKey());
				transTypeParentDtoMap.put(entry.getKey(), typeParent);
				for (TransTypeIce transTypeIce : entry.getValue()) {
					transTypeRedisMap.put(String.valueOf(transTypeIce.getTransTypeId()), transTypeIce);
				}
				typeParent.setTransTypetMap(transTypeRedisMap);
				String value = KryoSeriableStringUtils.seriaObject(typeParent);
				redisMap.put(entry.getKey(), value);
			}
			String key = MedicalTransRedisConstant.TRANS_TYPE + organId;
			this.segiRedisClusterBuilder.getSegiRedisCluster().hmset(key, redisMap);
			return transTypeParentDtoMap;
		} catch (Exception e) {
			logger.warn("Redis缓存更新失败", e);
			return null;
		}
	}
	
	//根据organId刷新缓存
	public Map<String, MtTransTypeParentDto> refreshRedis(Integer organId){
		try {
			// 查询数据库该organId下的运送类型信息
			List<TransTypeIce> transTypeIceList = mtTransTypeInfoService.getTransTypeListByOrganId(organId);
			// 删除缓存该项目下已有的位置信息
			delTransTypeRedis(organId);
			// 批量插入改项目下从数据库查到的项目
			Map<String, List<TransTypeIce>> map = mtTransTypeInfoService.getFleetRangeGroupMaps(transTypeIceList);
			return addTransTypeRedis(organId, map);
		} catch (Exception e) {
			logger.warn("organId=" + organId + "，刷新缓存失败redis缓存失败", e);
		}
		return null;
	}
	
	//根据organId刷新缓存
	public Map<String, MtTransTypeParentDto> refreshRedis(Integer organId, String transTypeParentCode){
		try {
			// 查询数据库该organId下的运送类型信息
			List<TransTypeIce> transTypeIceList = mtTransTypeInfoService.getTransTypeList(organId, transTypeParentCode);
			// 删除项目下单个field运输类型信息
			delTransTypeRedis(organId, transTypeParentCode);
			// 批量插入改项目下从数据库查到的项目
			Map<String, List<TransTypeIce>> map = mtTransTypeInfoService.getFleetRangeGroupMaps(transTypeIceList);
			return addTransTypeRedis(organId, map);
		} catch (Exception e) {
			logger.warn("organId=" + organId + "transTypeParentCode=" + transTypeParentCode + "，刷新缓存失败redis缓存失败", e);
		}
		return null;
	}
}
