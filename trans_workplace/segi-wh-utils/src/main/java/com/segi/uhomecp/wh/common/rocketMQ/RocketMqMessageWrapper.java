package com.segi.uhomecp.wh.common.rocketMQ;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 监听wrapper，用于处理共通业务，并转发消息到业务监听中。
 * Created by zouzhenze on 2017/4/14.
 */
public class RocketMqMessageWrapper implements MessageListenerConcurrently {
    private RocketMqMessageListener rocketMqMessageListener;

    public RocketMqMessageListener getRocketMqMessageListener() {
        return rocketMqMessageListener;
    }

    public void setRocketMqMessageListener(RocketMqMessageListener rocketMqMessageListener) {
        this.rocketMqMessageListener = rocketMqMessageListener;
    }

    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        //TODO 实现共通的业务
        if(rocketMqMessageListener.onMessage(list,consumeConcurrentlyContext)){
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }else{
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
    }
}
