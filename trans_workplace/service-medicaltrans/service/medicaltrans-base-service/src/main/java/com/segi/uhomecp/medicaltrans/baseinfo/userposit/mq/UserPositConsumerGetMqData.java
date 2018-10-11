/*package com.segi.uhomecp.medicaltrans.baseinfo.userposit.mq;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import net.sf.ehcache.util.concurrent.ConcurrentHashMap;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.segi.uhomecp.medicaltrans.baseinfo.userposit.service.MtUserpositInfoService;
import com.segi.uhomecp.utils.SpringContextUtils;
import com.segi.uhomecp.utils.UhomePropUtils;
import com.segi.uhomecp.wh.common.rocketMQ.RocketMqMessageListener;

*//**
 * RocketMQ消费者工具类
 *//*
@Service
@Lazy(false)
public class UserPositConsumerGetMqData implements InitializingBean{
	public static final Logger log = LoggerFactory.getLogger(UserPositConsumerGetMqData.class);
	//消费者初始化
	private static volatile Boolean isInitConsumer = false;
	//变量初始化
//	private static Boolean isInitVariable = false;
	//消费者实例
	private static UserPositConsumer consumer = null;
	//需要获取的数据表名集合(表名小写,且包含schema;举例：uhome.service_order_his)
//	private static Set<String> tabNameSet = new HashSet<String>();
	//线程池
//	private static final ExecutorService threadPool = Executors.newFixedThreadPool(1);
	//表名和数据库变更数量map
	private static Map<String, AtomicInteger> tableCountMap = new ConcurrentHashMap<String, AtomicInteger>();
//	private static int tableNum = 20;
//	private static List<String> tableList = new ArrayList<String>();
	//1M字节数
//	private static long oneMb = 1 * 1024 * 1024L;
	//GC时间控制
//	private static long gcTime = System.currentTimeMillis();
//	private static long gcMinute = 5 * 60 * 1000;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		start();
	}
	
	public static void init(){
		//MQ消费者初始化
		if(!isInitConsumer){
			synchronized (UserPositConsumerGetMqData.class) {
				if(!isInitConsumer){
					consumer = new UserPositConsumer();
					consumer.setTopic(UhomePropUtils.getProperty("mttask.userposit.rocketmq.topic"));
					consumer.setSubExpression(UhomePropUtils.getProperty("mttask.userposit.rocketmq.subExpression"));
					consumer.setConsumerGroup(UhomePropUtils.getProperty("mttask.userposit.rocketmq.consumerGroup"));
					consumer.setNamesrvAddr(UhomePropUtils.getProperty("mttask.userposit.rocketmq.namesrvAddr"));
					consumer.setRocketMqMessageListener(new LsRocketMqMessageListener());
					consumer.init();
					isInitConsumer = true;
				}
			}
		}
	}
	
	//启动MQ监听
	public static void start(){
		init();
	}
	
	//关闭MQ监听
	public static void stop(){
		if(isInitConsumer){
			synchronized (UserPositConsumerGetMqData.class) {
				if(isInitConsumer){
					consumer.shutdown();
					consumer = null;
					isInitConsumer = false;
				}
			}
		}
	}
	
	//默认消费者监听器(实现消息处理方法)
	public static class LsRocketMqMessageListener implements RocketMqMessageListener{
		
		@SuppressWarnings("unchecked")
		@Override
		public boolean onMessage(List<MessageExt> messages, ConsumeConcurrentlyContext Context) {
			log.info("pull message from mq");
			try {
				MessageExt msg = messages.get(0);
				if (null == tableCountMap.get(msg.getKeys())) {
					tableCountMap.put(msg.getKeys(), new AtomicInteger());
				}
				Map<String, Object> map = (Map<String, Object>) JSONObject.parse(new String(msg.getBody()));
				MtUserpositInfoService mtUserpositInfoService = (MtUserpositInfoService) SpringContextUtils
						.getBean("mtUserpositInfoService");
				Integer organId = null != map.get("organId") ? (int) (map.get("organId")) : null;
				List<Integer> userIds = null != map.get("userIds") ? (List<Integer>) map.get("userIds") : null;
				Short unTaskNum = null != map.get("unTaskNum") ? (short) map.get("unTaskNum") : null;
				Short runTaskNum = null != map.get("runTaskNum") ? (short) map.get("runTaskNum") : null;
				mtUserpositInfoService.updateUserPositInfo(organId, userIds, unTaskNum, runTaskNum);
				return true;
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return false;
			}
		}

		
		*//**
		 * 从tags中截取表名(小写,包含schema;例如:uhome.service_order_his)
		 * @param tags 例如:sghldev2/192.168.1.12:3306|-1|uhome.tb_report_equipment_a1
		 * @return
		 *//*
//	    private String getTableName(String tags){
//	    	if(tags == null){
//	    		return null;
//	    	}
//	    	try {
//	        	String schemaTableName = tags.substring(tags.lastIndexOf("|")+1);
//	        	if(schemaTableName!=null){
//	        		schemaTableName = schemaTableName.toLowerCase();
//	        	}
//	        	return schemaTableName;
//			} catch (Exception e) {
//				log.error("从tags中截取表名异常!tags="+tags, e);
//			}
//	    	return null;
//	    }
	}
}
*/