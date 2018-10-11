package com.segi.uhomecp.medicaltrans.monitor;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.segi.uhomecp.wh.common.rocketMQ.RocketMqMessageOrderlyListener;
import com.segi.uhomecp.wh.common.rocketMQ.RocketMqMessageOrderlyWrapper;

/**
 * 消费者，装载监听实现后，启动监听。
 */
public class Consumer {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private DefaultMQPushConsumer consumer;
    private String topic;
    private String subExpression;
    private String consumerGroup;
    private String namesrvAddr;
    private RocketMqMessageOrderlyListener rocketMqMessageOrderlyListener;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSubExpression() {
        return subExpression;
    }

    public void setSubExpression(String subExpression) {
        this.subExpression = subExpression;
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public RocketMqMessageOrderlyListener getRocketMqMessageOrderlyListener() {
		return rocketMqMessageOrderlyListener;
	}

	public void setRocketMqMessageOrderlyListener(RocketMqMessageOrderlyListener rocketMqMessageOrderlyListener) {
		this.rocketMqMessageOrderlyListener = rocketMqMessageOrderlyListener;
	}

	/**
     * 初始化
     */
    public void init() { 
        logger.debug("启动RocketMq监听...{}", this);
        consumer = new DefaultMQPushConsumer();
        consumer.setConsumerGroup(consumerGroup);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setVipChannelEnabled(false);
//        consumer.setConsumeMessageBatchMaxSize(10);
        try {
            consumer.subscribe(topic, subExpression);
            //程序第一次启动从消息队列头取数据
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            RocketMqMessageOrderlyWrapper rocketMqMessageOrderlyWrapper = new RocketMqMessageOrderlyWrapper();
            if (this.rocketMqMessageOrderlyListener == null) {
                throw new RuntimeException("please define a rocketMqMessageListener for consumer!");
            }
            rocketMqMessageOrderlyWrapper.setRocketMqMessageOrderlyListener(this.rocketMqMessageOrderlyListener);
            consumer.registerMessageListener(rocketMqMessageOrderlyWrapper);
            consumer.start();
            logger.debug("启动RocketMq监听成功！");
        } catch (Exception e) {
        	logger.debug("启动RocketMq监听异常！", e);
        }
    }
    
    /**
     * 关闭监听
     */
    public void shutdown() {
    	consumer.shutdown();
    	logger.debug("关闭RocketMq监听成功！");
    }
    
    @Override
    public String toString() {
        return "Consumer{" +
                "topic='" + topic + '\'' +
                ", subExpression='" + subExpression + '\'' +
                ", consumerGroup='" + consumerGroup + '\'' +
                ", namesrvAddr='" + namesrvAddr + '\'' +
                ", rocketMqMessageListener=" + rocketMqMessageOrderlyListener +
                '}';
    }
}
