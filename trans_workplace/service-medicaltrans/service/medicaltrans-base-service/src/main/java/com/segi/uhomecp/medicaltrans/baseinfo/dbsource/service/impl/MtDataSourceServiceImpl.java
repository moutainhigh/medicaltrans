package com.segi.uhomecp.medicaltrans.baseinfo.dbsource.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.medicaltrans.base.dbsourcerule.model.DbSourceRule;
import com.segi.uhomecp.medicaltrans.base.dbsourcerule.model.DbSourceRuleCriteria;
import com.segi.uhomecp.medicaltrans.base.dbsourcerule.service.DbSourceRuleService;
import com.segi.uhomecp.medicaltrans.baseinfo.dbsource.dao.MtDbSourceInfoMapper;
import com.segi.uhomecp.medicaltrans.baseinfo.dbsource.dto.MtDbSourceInfoDto;
import com.segi.uhomecp.medicaltrans.baseinfo.dbsource.service.MtDataSourceService;
import com.segi.uhomecp.medicaltrans.cache.MtDbSourceRedisCache;
import com.segi.uhomecp.medicaltrans.constant.MtSeqContants;
import com.segi.uhomecp.medicaltrans.enums.PartDataBaseEnum;
import com.segi.uhomecp.medicaltrans.enums.PartTableEnum;
import com.segi.uhomecp.utils.SeqContants;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.AppUtils;

@Service
public class MtDataSourceServiceImpl implements MtDataSourceService {
	
	@Autowired
	private DbSourceRuleService dbSourceRuleService;
	
	@Autowired
	private MtDbSourceRedisCache mtDbSourceRedisCache;
	
	@Autowired
	private MtDbSourceInfoMapper mtDbSourceInfoMapper;

	/**
	 * @discription 通过groupOrganId查询数据源规则(数据库)
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月14日 下午2:32:45      
	 * @param groupOrganId
	 * @return     
	 */
	@Override
	public DbSourceRule queryDbSourceByGroupOrganId(int groupOrganId) {
		DbSourceRuleCriteria example = new DbSourceRuleCriteria();
		DbSourceRuleCriteria.Criteria criteria = example.createCriteria();
		criteria.andGroupOrganIdEqualTo(groupOrganId);
		List<DbSourceRule> ruleList = dbSourceRuleService.selectByExample(example);
		if (AppUtils.isNotEmpty(ruleList)) {
			return ruleList.get(0);
		}
		return null;
	}

	/**
	 * @discription 同时保存数据源规则到数据库和缓存
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月14日 下午2:33:28      
	 * @param groupOrganId
	 * @param dbIdx
	 * @param tableIdx     
	 */
	@Override
	public DbSourceRule saveDbSource(int groupOrganId, String dbIdx, String tableIdx) {
		Date nowDate = new Date();
		DbSourceRule dbSourceRule = new DbSourceRule();
		// 获取序列
		dbSourceRule.setIdxId(SeqContants.getSequnces(MtSeqContants.MT_DB_SOURCE_RULE_ID_SEQ).intValue());
		dbSourceRule.setGroupOrganId(groupOrganId);
		dbSourceRule.setDbIdx(dbIdx);
		dbSourceRule.setTableIdx(tableIdx);
		dbSourceRule.setUpdateDate(nowDate);
		dbSourceRule.setCreateDate(nowDate);
		dbSourceRule.setStatus(Constant.STATUS_CD_NORMAL);
		// 存数据库
		dbSourceRuleService.insert(dbSourceRule);
		// 存缓存
		this.addDbSourceRedis(dbSourceRule);
		return dbSourceRule;
	}

	/**
	 * @discription 通过groupOrganId查询数据源规则(数据库)
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月14日 下午2:34:30      
	 * @param groupOrganId
	 * @return     
	 */
	@Override
	public DbSourceRule getDbSourceByGroupOrganIdRedis(int groupOrganId) {
		DbSourceRule dbSource = mtDbSourceRedisCache.getDbSourceByGroupOrganIdRedis(groupOrganId);
		if (null != dbSource) {
			return dbSource;
		}
		// 查询表
		dbSource = this.queryDbSourceByGroupOrganId(groupOrganId);
		if (null != dbSource) {
			// 保存到缓存
			this.addDbSourceRedis(dbSource);
		}
		return dbSource;
	}

	/**
	 * @discription 保存数据源规则到缓存
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月14日 下午2:34:47      
	 * @param dbSourceRule     
	 */
	@Override
	public void addDbSourceRedis(DbSourceRule dbSourceRule) {
		mtDbSourceRedisCache.addDbSourceRedis(dbSourceRule);
	}

	/**
	 * @discription 获取和保存GroupOrganId数据规则
	 * @author wangxiong@segimail.com       
	 * @created 2018年8月15日 下午1:27:01      
	 * @param groupOrganId     
	 */
	@Override
	public synchronized DbSourceRule saveOrGetNewDbSourceRuleAndSave(int groupOrganId) {
		Map<String, Double> dbKeysMembers = new HashMap<>();
		Map<String, Double> tableIdxMembers = new HashMap<>();
		String dbKey = queryMtDbSourceRedis(dbKeysMembers);
		String tableIdx = queryMtTableRedis(tableIdxMembers, dbKey);
		DbSourceRule dbSourceRule = this.saveDbSource(groupOrganId, dbKey, tableIdx);
		saveDbSourceRuleRedis(dbKeysMembers, tableIdxMembers, dbKey, tableIdx);
		return dbSourceRule;
		
	}
	
	/**
	 * @discription 保存Redis数据
	 * @author wangxiong@segimail.com       
	 * @created 2018年8月15日 下午4:42:51     
	 * @param dbKeysMembers  
	 * @param tableIdxMembers
	 * @param dbKey
	 * @param tableIdx
	 */
	public void saveDbSourceRuleRedis(Map<String, Double> dbKeysMembers, Map<String, Double> tableIdxMembers,
			String dbKey, String tableIdx) {
		if (dbKeysMembers.isEmpty()) {
			mtDbSourceRedisCache.updateDbSource(dbKey);
		} else {
			dbKeysMembers.put(dbKey, dbKeysMembers.get(dbKey) + 1);
			mtDbSourceRedisCache.updateDbSource(dbKeysMembers);
		}
		
		if (tableIdxMembers.isEmpty()) {
			mtDbSourceRedisCache.updateTableIdx(dbKey, tableIdx);
		} else {
			tableIdxMembers.put(tableIdx, tableIdxMembers.get(tableIdx) + 1);
			mtDbSourceRedisCache.updateTableIdx(dbKey, tableIdxMembers);
		}
	}

	/**
	 * @discription 查询 表的数据Redis索引
	 * @author wangxiong@segimail.com       
	 * @created 2018年8月15日 下午4:43:21     
	 * @param tableIdxMembers
	 * @param dbKey
	 * @return
	 */
	public String queryMtTableRedis(Map<String, Double> tableIdxMembers, String dbKey) {
		String tableIdx = mtDbSourceRedisCache.getDbSourceMinTable(dbKey);
		if (StringUtils.isBlank(tableIdx)) {
			List<MtDbSourceInfoDto> tableIdxList = this.mtDbSourceInfoMapper.queryTableIdxCnt(dbKey);
			Map<String, MtDbSourceInfoDto> tableIdxMap = AppUtils.list2Map(tableIdxList, "tableIdx", 
					MtDbSourceInfoDto.class);
			MtDbSourceInfoDto mtTableIdxDto = null;
			MtDbSourceInfoDto mtTableIdxTmp = null;
			for (PartTableEnum tmp : PartTableEnum.values()) {
				mtTableIdxDto = tableIdxMap.get(tmp.getCode());
				if (mtTableIdxDto != null && tmp.getCode().equals(mtTableIdxDto.getTableIdx())) {
					tableIdxMembers.put(mtTableIdxDto.getTableIdx(), Double.valueOf(mtTableIdxDto.getCnt()));
				} else {
					tableIdxMembers.put(tmp.getCode(), Double.valueOf(0));
					mtTableIdxDto = new MtDbSourceInfoDto(null, tmp.getCode(), 0);
				}
				if (mtTableIdxTmp == null || mtTableIdxTmp.getCnt() > mtTableIdxDto.getCnt()) {
					mtTableIdxTmp = mtTableIdxDto;
				}
			}
			tableIdx = mtTableIdxTmp.getTableIdx();
		}
		return tableIdx;
	}

	/**
	 * @discription 查询数据源Redis索引
	 * @author wangxiong@segimail.com       
	 * @created 2018年8月15日 下午4:44:09     
	 * @param dbKeysMembers
	 * @return
	 */
	public String queryMtDbSourceRedis(Map<String, Double> dbKeysMembers) {
		// 获取数据源统计的数据
		String dbKey = mtDbSourceRedisCache.getDbSourceMinDb();
		if (StringUtils.isBlank(dbKey)) { 
			List<MtDbSourceInfoDto> dbKeyList = this.mtDbSourceInfoMapper.queryDbKeyCnt();
			Map<String, MtDbSourceInfoDto> dbKeyMap = AppUtils.list2Map(dbKeyList, "dbIdx", MtDbSourceInfoDto.class);
			MtDbSourceInfoDto mtDbSourceInfoDto = null;
			MtDbSourceInfoDto mtDbSourceInfoTmp = null;
			for (PartDataBaseEnum tmp : PartDataBaseEnum.values()) {
				mtDbSourceInfoDto = dbKeyMap.get(tmp.getCode());
				if (mtDbSourceInfoDto != null && tmp.getCode().equals(mtDbSourceInfoDto.getDbIdx())) {
					
					dbKeysMembers.put(mtDbSourceInfoDto.getDbIdx(), Double.valueOf(mtDbSourceInfoDto.getCnt()));
				} else {
					dbKeysMembers.put(tmp.getCode(), Double.valueOf(0));
					mtDbSourceInfoDto = new MtDbSourceInfoDto(tmp.getCode(), null, 0);
				}
				if (mtDbSourceInfoTmp == null || mtDbSourceInfoTmp.getCnt() > mtDbSourceInfoDto.getCnt()) {
					mtDbSourceInfoTmp = mtDbSourceInfoDto;
				}
			}
			dbKey = mtDbSourceInfoTmp.getDbIdx();
		}
		return dbKey;
	}

}
