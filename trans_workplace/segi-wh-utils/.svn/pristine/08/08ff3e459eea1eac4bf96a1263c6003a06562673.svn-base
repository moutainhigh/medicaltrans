package com.segi.uhomecp.wh.common.rocketMQ;


import java.util.List;

import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 消费者监听接口，业务需要实现此接口并配置到Consumer中。
 * Created by zouzhenze on 2017/4/14.
 */
public interface RocketMqMessageOrderlyListener {
    boolean onMessage(List<MessageExt> messages, ConsumeOrderlyContext Context);
}
