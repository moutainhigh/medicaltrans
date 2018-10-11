package com.segi.uhomecp.medicaltrans.monitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.alibaba.fastjson.JSONObject;
import com.segi.uhomecp.utils.UhomePropUtils;
import com.segi.uhomecp.wh.common.rocketMQ.RocketMqMessageOrderlyListener;

import net.sf.ehcache.util.concurrent.ConcurrentHashMap;

/**
 * RocketMQ消费者工具类
 *     
 * 包: com.segi.uhomecp.medicaltrans.monitor    
 * 类名称: ConsumerMonitor.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 2018年3月22日 下午5:11:18
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
public class ConsumerMonitor implements InitializingBean {
	public static final Logger log = LoggerFactory.getLogger(ConsumerMonitor.class);
	//消费者初始化
	private static volatile Boolean isInitConsumer = false;
	//变量初始化
	private static Boolean isInitVariable = false;
	//消费者实例
	private static Consumer consumer = null;
	//队列消息
	private static Map<String, BlockingQueue<Map<String, String>>> taskQueue = new ConcurrentHashMap<String, BlockingQueue<Map<String, String>>>();
	//线程池
//	private static final ExecutorService threadPool = Executors.newFixedThreadPool(1);
	//处理线程
	public static Map<String, AtomicBoolean> hisCountMap = new ConcurrentHashMap<String, AtomicBoolean>();
	//1M字节数
//	private static long oneMb = 1 * 1024 * 1024L;
	//GC时间控制
//	private static long gcTime = System.currentTimeMillis();
//	private static long gcMinute = 5 * 60 * 1000;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		start();
	}
	
	/**
	 * 初始化
	 */
	public static void init() {
		//变量初始化
		if (!isInitVariable) {
			synchronized (ConsumerMonitor.class) {
				if (!isInitVariable) {
					isInitVariable = true;
				}
			}
		}
		//MQ消费者初始化
		if (!isInitConsumer) {
			synchronized (ConsumerMonitor.class) {
				if (!isInitConsumer) {
					consumer = new Consumer();
					//订阅topic
					consumer.setTopic(UhomePropUtils.getProperty("report.rocketmq.topic")/*"TRANSPORT_REPORT_TOPIC"*/);
					//订阅topic下tags
					consumer.setSubExpression(UhomePropUtils.getProperty("report.rocketmq.subExpression")/*"*"*/);
					consumer.setConsumerGroup(UhomePropUtils.getProperty("report.rocketmq.consumerGroup")/*"GROUP_TRANSPORT_REPORT"*/);
					consumer.setNamesrvAddr(UhomePropUtils.getProperty("report.rocketmq.namesrvAddr")/*"192.168.1.5:9876"*/);
					consumer.setRocketMqMessageOrderlyListener(new MqMessageListener());
					consumer.init();
					isInitConsumer = true;
				}
			}
		}
	}
	
	/**
	 * 启动MQ监听
	 */
	public static void start() {
		init();
	}
	
	/**
	 * 关闭MQ监听
	 */
	public static void stop() {
		if (isInitConsumer) {
			synchronized (ConsumerMonitor.class) {
				if (isInitConsumer) {
					consumer.shutdown();
					consumer = null;
					isInitConsumer = false;
				}
			}
		}
	}
	
	/**
	 * 根据表名返回队列
	 * @param tableName
	 * @return
	 */
	public static Map<String, BlockingQueue<Map<String, String>>> getMessageMap() {
		return taskQueue;
	}
	
	//默认消费者监听器(实现消息处理方法)
	public static class MqMessageListener implements RocketMqMessageOrderlyListener {
		
		@Override
		public boolean onMessage(List<MessageExt> messages, ConsumeOrderlyContext Context) {
			log.info("pull message from mq");
        	try {
        		boolean flag = false;
        		MessageExt msg = messages.get(0);
    			log.debug("========================================organ:{},{}", msg.getKeys(), hisCountMap.get(msg.getKeys()));
    			if (null == ConsumerMonitor.hisCountMap.get(msg.getKeys())) {
    				ConsumerMonitor.hisCountMap.put(msg.getKeys(), new AtomicBoolean(false));
    			}
    			if (hisCountMap.get(msg.getKeys()).get()) {
    				log.debug("organ[{}] statistics is running. waiting next time!!", msg.getKeys());
    				return flag;
        		}
    			log.info(msg.getTags() + "-" + msg.getMsgId() + "-{}", new String(msg.getBody()));
        		Map<String, String> map = new HashMap<String, String>();
        		
        		if (null == taskQueue.get(msg.getKeys())) {
        			taskQueue.put(msg.getKeys(), new LinkedBlockingQueue<Map<String, String>>());
        		}
        		
        		map = (Map<String, String>) JSONObject.parse(new String(msg.getBody()));
    			taskQueue.get(msg.getKeys()).offer(map);
    			flag = true;
    			
        		return flag;
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				return false;
			}
		}
	}

}
