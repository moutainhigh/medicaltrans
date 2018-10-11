package com.segi.uhomecp.medicaltrans.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.base.dbsourcerule.model.DbSourceRule;
import com.segi.uhomecp.medicaltrans.constant.MedicalTransRedisConstant;
import com.segi.uhomecp.redis.cluster.SegiRedisClusterBuilder;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.wh.common.dto.ResultDto;
import com.segi.uhomecp.wh.common.utils.AppUtils;

/**
 * Title: MtDbSourceRedisCache.java    
 * @Description: 切换数据源和分表缓存
 * @author zhangyang@segimail.com       
 * @created 2018年8月14日 上午9:38:14
 */
@Component
public class MtDbSourceRedisCache {
	public static final Logger logger = LoggerFactory.getLogger(MtDbSourceRedisCache.class);
	
	@Resource(name="segiRedisCluster")
	private SegiRedisClusterBuilder segiRedisClusterBuilder;
	
	/**
	 * @discription 保存医院数据源规则缓存
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月14日 上午10:03:51     
	 * @param dbSourceRule
	 * @return
	 */
	public ResultDto<String, String, String> addDbSourceRedis(DbSourceRule dbSourceRule) {
		ResultDto<String, String, String> rst = new ResultDto<>(true, "Redis缓存医院数据源规则成功!");
		if (null == dbSourceRule || null == dbSourceRule.getGroupOrganId()) {
			rst.setIsSucc(false);
			rst.setMessage("数据分表分库规则对象不可为空!");
			return rst;
		}
		String key = MedicalTransRedisConstant.DB_SOURCE_RULE;
		String value = null;
		try{
			value = FastjsonUtils.toJsonString(dbSourceRule);
			this.segiRedisClusterBuilder.getSegiRedisCluster().hset(key, String.valueOf(dbSourceRule.getGroupOrganId()), value);
		} catch (Exception e) {
			logger.error("Redis缓存医院数据源规则失败!", e);
			rst.setMessage("Redis缓存医院数据源规则失败!");
			rst.setIsSucc(false);
		}
		return rst;
	}
	
	/**
	 * @discription 查询医院数据源规则缓存
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月14日 上午10:02:49     
	 * @param groupOrganId
	 * @return
	 */
	public DbSourceRule getDbSourceByGroupOrganIdRedis(Integer groupOrganId) {
		DbSourceRule dbSourceRule = null;
		if (null == groupOrganId) {
			return dbSourceRule;
		}
		String key = MedicalTransRedisConstant.DB_SOURCE_RULE;
		String field = String.valueOf(groupOrganId);
		String value = null;
		try {
			value = this.segiRedisClusterBuilder.getSegiRedisCluster().hget(key, field);
			if (StringUtils.isNotBlank(value)) {
				dbSourceRule = FastjsonUtils.parseObject(value, DbSourceRule.class);
			}
		} catch (Exception e) {
			logger.warn("查询医院数据源规则缓存失败!", e);
		}
		return dbSourceRule;
	}
	
	/**
	 * @discription 查询所有一级物业数据源缓存
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月14日 上午10:12:13     
	 * @return
	 */
	public Map<Integer, DbSourceRule> getAllDbSourceRedis() {
		Map<Integer, DbSourceRule> ruleMap = new HashMap<>();
		try {
			String key = MedicalTransRedisConstant.DB_SOURCE_RULE;
			Map<String, String> valueMap = this.segiRedisClusterBuilder.getSegiRedisCluster().hgetAll(key);
			if (valueMap == null || valueMap.isEmpty()) {
				return ruleMap;
			}
			
			List<DbSourceRule> ruleList = new ArrayList<DbSourceRule>();
			DbSourceRule dbSourceRule = null;
			for (String val : valueMap.values()) {
				dbSourceRule = FastjsonUtils.parseObject(val, DbSourceRule.class);
				if (null != dbSourceRule) {
					ruleList.add(dbSourceRule);
				}
			}
			if (AppUtils.isNotEmpty(ruleList)) {
				ruleMap = AppUtils.list2Map(ruleList, (obj) -> obj.getGroupOrganId());
			}
		} catch (Exception e) {
			logger.error("查询所有一级物业数据源缓存失败!", e);
		}
		return ruleMap;
	}
	
	/**
	 * @discription 查询数据统计缓存
	 * @author wangxiong@segimail.com       
	 * @created 2018年8月15日 上午11:37:33     
	 * @return
	 */
	public String getDbSourceMinDb() {
		String key = MedicalTransRedisConstant.DB_SOURCE_RULE_DB_COUNT;
		String value = null;
		try {
			Set<String> dbKeySet = this.segiRedisClusterBuilder.getSegiRedisCluster().zrange(key, 0, -1);
			if (AppUtils.isNotEmpty(dbKeySet)) {
				value = dbKeySet.iterator().next();
			}
		} catch (Exception e) {
			logger.error("查询医院数据源规则 数据DbKey统计缓存失败!", e);
		}
		return value;
	}
	
	/**
	 * @discription  查询数据Table统计缓存
	 * @author wangxiong@segimail.com       
	 * @created 2018年8月15日 上午11:38:05     
	 * @param dbKey
	 * @return
	 */
	public String getDbSourceMinTable(String dbKey) {
		String key = MedicalTransRedisConstant.DB_SOURCE_RULE_DB_COUNT + dbKey;
		String value = null;
		try {
			Set<String> tableIdxSet = this.segiRedisClusterBuilder.getSegiRedisCluster().zrange(key, 0, -1);
			if (AppUtils.isNotEmpty(tableIdxSet)) {
				value = tableIdxSet.iterator().next();
			}
		} catch (Exception e) {
			logger.error("查询医院数据源TableInx统计缓存查询失败!", e);
		}
		return value;
	}
	
	/**
	 * @discription 更新缓存
	 * @author wangxiong@segimail.com       
	 * @created 2018年8月15日 上午11:37:33     
	 * @return
	 */
	public void updateDbSource(Map<String,Double> scoreMembers) {
		String key = MedicalTransRedisConstant.DB_SOURCE_RULE_DB_COUNT;
		try {
			this.segiRedisClusterBuilder.getSegiRedisCluster().zadd(key, scoreMembers);
		} catch (Exception e) {
			logger.error("查询医院数据源规则 数据DbKey统计缓存失败!", e);
		}
	}
	
	/**
	 * @discription 更新缓存
	 * @author wangxiong@segimail.com       
	 * @created 2018年8月15日 上午11:37:33     
	 * @return
	 */
	public void updateDbSource(String dbKey) {
		String key = MedicalTransRedisConstant.DB_SOURCE_RULE_DB_COUNT;
		try {
			this.segiRedisClusterBuilder.getSegiRedisCluster().zincrby(key, 1, dbKey);
		} catch (Exception e) {
			logger.error("查询医院数据源规则 数据DbKey统计缓存失败!", e);
		}
	}
	
	/**
	 * @discription 更新缓存
	 * @author wangxiong@segimail.com       
	 * @created 2018年8月15日 上午11:37:33     
	 * @return
	 */
	public void updateTableIdx(String dbKey, Map<String,Double> scoreMembers) {
		String key = MedicalTransRedisConstant.DB_SOURCE_RULE_TABLE_COUNT + dbKey;
		try {
			this.segiRedisClusterBuilder.getSegiRedisCluster().zadd(key, scoreMembers);
		} catch (Exception e) {
			logger.error("查询医院数据源规则 数据DbKey统计缓存失败!", e);
		}
	}
	
	/**
	 * @discription 更新缓存
	 * @author wangxiong@segimail.com       
	 * @created 2018年8月15日 上午11:37:33     
	 * @return
	 */
	public void updateTableIdx(String dbKey, String tableIdx) {
		String key = MedicalTransRedisConstant.DB_SOURCE_RULE_DB_COUNT + dbKey;
		try {
			this.segiRedisClusterBuilder.getSegiRedisCluster().zincrby(key, 1, tableIdx);
		} catch (Exception e) {
			logger.error("查询医院数据源规则 数据DbKey统计缓存失败!", e);
		}
	}
}
