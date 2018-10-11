package com.segi.uhomecp.medicaltrans.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.code.shardbatis.strategy.ShardStrategy;

public class TaskShardStrategyImpl implements ShardStrategy {
	private static final Logger logger = LoggerFactory.getLogger(TaskShardStrategyImpl.class);
	/** * 得到实际表名 * 
	 * @param baseTableName 逻辑表名,一般是没有前缀或者是后缀的表名
	 * @param params mybatis执行某个statement时使用的参数 * 
	 * @param mapperId mybatis配置的statement id * 
	 * @return 
	 */ 
	@Override 
	public String getTargetTableName(String baseTableName, Object params, String mapperId) { 
		try {
			String strIndex =  DynamicTableSourceKeyHolder.getDataSourceKey();
			logger.debug("切换表的索引是  strIndex{}{}=====>"+ strIndex);
			return baseTableName + "_" + strIndex;
		} catch (Exception e) { 
			throw new RuntimeException(e.getMessage(), e); 
		}
	}
}
