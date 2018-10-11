package com.segi.uhomecp.wh.common.rocketMQ;


import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 消费者监听接口，业务需要实现此接口并配置到Consumer中。
 * Created by zouzhenze on 2017/4/14.
 */
public interface RocketMqMessageListener {
    boolean onMessage(List<MessageExt> messages, ConsumeConcurrentlyContext Context);
}
