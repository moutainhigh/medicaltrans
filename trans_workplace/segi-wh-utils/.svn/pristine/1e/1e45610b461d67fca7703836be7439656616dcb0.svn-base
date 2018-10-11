package com.segi.uhomecp.wh.common.rocketMQ;


import com.segi.uhomecp.utils.UhomePropUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;

/**
 * 生产者，初始化MQProducer
 *
 * @author zouzhenze
 */
public class RocketMQUtils {

    private static String namesrvAddr;
    private static String producerGroup;
    private static volatile DefaultMQProducer producer;

    public static Logger LOGGER = LoggerFactory.getLogger(RocketMQUtils.class);

    static {
        System.setProperty("rocketmq.client.logRoot","/log/rocketmq");
        namesrvAddr = UhomePropUtils.getProperty("producer.rocketmq.namesrvAddr");
        producerGroup = UhomePropUtils.getProperty("producer.rocketmq.producer.group");
    }

    private static DefaultMQProducer init() {
        if (null == producer) {
            synchronized (RocketMQUtils.class) {
                if (null == producer) {
                    producer = new DefaultMQProducer(producerGroup);
                    producer.setNamesrvAddr(namesrvAddr);
                    producer.setVipChannelEnabled(false);
                    try {
                        producer.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                    }
                }
            }
        }
        return producer;
    }


    /**
     * @param topic   主题 必填
     * @param tag     标签 必填
     * @param content 消息体 必填
     * @param keys    关键词  选填，查询索引用
     * @return
     */
    public static SendResult send(final String topic, final String tag, final String content, final String keys) throws Exception {
        //ExecutorService executorService = Executors.newFixedThreadPool(1);
        producer = init();
        //Set<Future> set = new HashSet<Future>();
        //Callable callable = new MessageSend(producer, content, topic, tag, keys);
        //Future future = executorService.submit(callable);
        //set.add(future);
        //return future;

        SendResult sendResult = call(topic, tag, content, keys);
        return sendResult;
    }
    
    /**
     * @param topic   主题 必填
     * @param tag     标签 必填
     * @param content 消息体 必填
     * @param keys    关键词  选填，查询索引用
     * @return
     */
    public static SendResult send(final String topic, final String tag, final String content) throws Exception {
    	return send(topic, tag, content, null);
    }

    private static SendResult call(final String topic, final String tag, final String messageBody, final String keys) throws Exception {
        SendResult send = null;
        Message msg = new Message();
        try {
            msg.setTopic(topic);
            msg.setTags(tag);
            if (StringUtils.isNotBlank(keys)) {
                msg.setKeys(keys);
            }
            try {
                msg.setBody(messageBody.getBytes(RemotingHelper.DEFAULT_CHARSET));
                send = producer.send(msg);
                /*LOGGER.info("==============================================");
                LOGGER.info("发送状态：" + send.getSendStatus());
                LOGGER.info("发送消息体：" + messageBody);
                LOGGER.info("msgId：" + send.getMsgId());
                LOGGER.info("topic：" + topic);
                LOGGER.info("tag：" + tag);
                LOGGER.info("keys：" + keys);
                LOGGER.info("brokerName：" + send.getMessageQueue().getBrokerName());
                LOGGER.info("queueId：" + send.getMessageQueue().getQueueId());
                LOGGER.info("操作时间：" + DateTimeUtils.formatDate(DateTimeUtils.nowDate(), DateTimeUtils.FORMAT_YYYY_MM_DD_HH_MM_SS));
                LOGGER.info("==============================================");*/
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                LOGGER.error("写消息报错了", e);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("写消息报错了", e);
            throw e;
        } finally {
            return send;
        }
    }

    public static void main(String[] args) {

        /*Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", "332462");
        param.put("code", "like");
        try {
            //RocketMQUtils.send(MessageTopic.BEHAVIOR_TOPIC, MessageTag.BEHAVIOR_TAG, JsonUtils.toJsonString(param), "");
            //RabbitMQUtil.push("behaviorQueue", JSON.toJSONString(param));
            RestTemplate restTemplate = new RestTemplate();
            String s = restTemplate.postForObject("http://192.168.1.11:10080/integral-api/behavior/analyseBehavior?userId="+332462+"&code=like", param, String.class);
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        RestTemplate restTemplate = new RestTemplate();
        String s = restTemplate.postForObject("http://192.168.1.11:10080/integral-api/behavior/analyseBehavior?userId=" + 332479 + "&code=like", null, String.class);
        System.out.println(s);
        /*Integer[] userids = new Integer[]{332479};
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            int length = userids.length;
            int length1 = i % length;

            final Integer userid = userids[length1];
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    RestTemplate restTemplate = new RestTemplate();
                    String s = restTemplate.postForObject("http://192.168.1.11:10080/integral-api/behavior/analyseBehavior?userId=" + userid + "&code=like", null, String.class);
                    String s1 = restTemplate.postForObject("http://192.168.1.11:10080/integral-api/behavior/analyseBehavior?userId=" + userid + "&code=reply", null, String.class);
                    System.out.println(s);
                    System.out.println(s1);

                }
            });
        }*/
    }
}