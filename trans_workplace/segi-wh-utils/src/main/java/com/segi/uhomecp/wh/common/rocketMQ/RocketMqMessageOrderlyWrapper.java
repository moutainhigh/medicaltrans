package com.segi.uhomecp.wh.common.rocketMQ;

import java.util.List;

import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 监听wrapper，用于处理共通业务，并转发消息到业务监听中。
 * Created by zouzhenze on 2017/4/14.
 */
public class RocketMqMessageOrderlyWrapper implements MessageListenerOrderly {
    private RocketMqMessageOrderlyListener rocketMqMessageOrderlyListener;

    public RocketMqMessageOrderlyListener getRocketMqMessageOrderlyListener() {
		return rocketMqMessageOrderlyListener;
	}

	public void setRocketMqMessageOrderlyListener(
			RocketMqMessageOrderlyListener rocketMqMessageOrderlyListener) {
		this.rocketMqMessageOrderlyListener = rocketMqMessageOrderlyListener;
	}

	@Override
	public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs,
			ConsumeOrderlyContext context) {
		if(rocketMqMessageOrderlyListener.onMessage(msgs, context)){
            return ConsumeOrderlyStatus.SUCCESS;
        }else{
            return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
        }
	}
}
