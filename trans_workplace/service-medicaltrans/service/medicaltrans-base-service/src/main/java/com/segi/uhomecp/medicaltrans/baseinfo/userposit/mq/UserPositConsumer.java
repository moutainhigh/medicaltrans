package com.segi.uhomecp.medicaltrans.baseinfo.userposit.mq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.segi.uhomecp.wh.common.rocketMQ.RocketMqMessageListener;
import com.segi.uhomecp.wh.common.rocketMQ.RocketMqMessageWrapper;
/**
 * 消费者，装载监听实现后，启动监听。
 */
public class UserPositConsumer {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private DefaultMQPushConsumer consumer;
    private String topic;
    private String subExpression;
    private String consumerGroup;
    private String namesrvAddr;
    private RocketMqMessageListener rocketMqMessageListener;

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

    public RocketMqMessageListener getRocketMqMessageListener() {
        return rocketMqMessageListener;
    }

    public void setRocketMqMessageListener(RocketMqMessageListener rocketMqMessageListener) {
        this.rocketMqMessageListener = rocketMqMessageListener;
    }
    
    public void init() { 
        logger.debug("启动RocketMq监听...{}", this);
        consumer = new DefaultMQPushConsumer();
        consumer.setConsumerGroup(consumerGroup);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setVipChannelEnabled(false);
        try {
            consumer.subscribe(topic, subExpression);
            //程序第一次启动从消息队列头取数据
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            RocketMqMessageWrapper rocketMqMessageWrapper = new RocketMqMessageWrapper();
            if (this.rocketMqMessageListener == null) {
                throw new RuntimeException("please define a rocketMqMessageListener for consumer!");
            }
            rocketMqMessageWrapper.setRocketMqMessageListener(this.rocketMqMessageListener);
            consumer.registerMessageListener(rocketMqMessageWrapper);
            consumer.start();
            logger.debug("启动RocketMq监听成功！");
        } catch (Exception e) {
        	logger.debug("启动RocketMq监听异常！", e);
        }
    }

    public void shutdown(){
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
                ", rocketMqMessageListener=" + rocketMqMessageListener +
                '}';
    }
}