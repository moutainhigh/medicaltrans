package com.segi.uhomecp.medicaltrans.monitor.handle;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.segi.uhomecp.medicaltrans.monitor.MessageHandler;
import com.segi.uhomecp.redis.cluster.SegiRedisCluster;
import com.segi.uhomecp.redis.cluster.SegiRedisClusterBuilder;

/**
 * 运单实时数量统计
 *     
 * 包: com.segi.uhomecp.medicaltrans.monitor.service    
 * 类名称: TransportCountService.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 2018年3月22日 下午5:11:18
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
public class TransportCountHandler implements MessageHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(TransportCountHandler.class);
	
	@Resource(name = "segiRedisCluster")
    private SegiRedisClusterBuilder segiRedisCluster;
	
	@Override
	public void excute(Map<String, String> params) {
		LOG.info("TransportCountService is completed.{}", params);
		//TODO 处理每日统计数量
		SegiRedisCluster client = segiRedisCluster.getSegiRedisCluster();
		String organId = params.get("organId");
		client.hincrBy("count:" + organId, "TEST", 1);
	}

}
