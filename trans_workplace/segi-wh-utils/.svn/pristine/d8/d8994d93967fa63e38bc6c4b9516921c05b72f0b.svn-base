package com.segi.uhomecp.wh.common.rocketMQ;

import java.util.concurrent.Callable;

/**
 * Created by zouzhenze on 2017/4/18.
 */
public class MessageSend implements Callable {
    @Override
    public Object call() throws Exception {
        return null;
    }

    /*public static Logger LOGGER = LoggerFactory.getLogger(MessageSend.class);

    private DefaultMQProducer producer;
    private String topic;
    private String tag;
    private String messageBody;
    private String key;

    *//**
     * @param topic   主题 必填
     * @param tag     标签 必填
     * @param messageBody 消息体 必填
     * @param key    关键词  选填，查询索引用
     * @return
     *//*
    MessageSend(DefaultMQProducer producer, String messageBody, String topic, String tag, String key) {
        this.producer = producer;
        this.messageBody = messageBody;
        this.topic = topic;
        this.tag = tag;
        this.key = key;
    }


    @Override
    public Message call() throws Exception {
        Message msg = new Message();
        try {
            msg.setTopic(topic);
            msg.setTags(tag);
            if (StringUtils.isNotBlank(key)) {
                msg.setKeys(key);
            }
            try {
                msg.setBody(messageBody.getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult send = producer.send(msg);
                LOGGER.info("==============================================");
                LOGGER.info("发送状态：" + send.getSendStatus());
                LOGGER.info("发送消息体：" + messageBody);
                LOGGER.info("msgId：" + send.getMsgId());
                LOGGER.info("topic：" + topic);
                LOGGER.info("tag：" + tag);
                LOGGER.info("keys：" + key);
                LOGGER.info("brokerName：" + send.getMessageQueue().getBrokerName());
                LOGGER.info("queueId：" + send.getMessageQueue().getQueueId());
                LOGGER.info("操作时间：" + DateTimeUtils.formatDate(DateTimeUtils.nowDate(), DateTimeUtils.FORMAT_YYYY_MM_DD_HH_MM_SS));
                LOGGER.info("==============================================");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return msg;
    }*/
}
