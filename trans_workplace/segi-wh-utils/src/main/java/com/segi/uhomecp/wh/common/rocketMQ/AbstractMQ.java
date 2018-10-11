package com.segi.uhomecp.wh.common.rocketMQ;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.wh.common.redis.RedisUtil;

/**
 * Created by zouzhenze on 2017/6/8.
 */
@Component
public abstract class AbstractMQ implements RocketMqMessageListener {

    public static final Logger logger = LoggerFactory.getLogger(AbstractMQ.class);

    public abstract boolean handle(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext);


    private static String lastReqTime = "lastReqTime";
    private static String inCount = "inCount";
    
    @Resource
    protected RedisUtil redisUtil;

    private Integer second() {
        Integer sec = 0;
        long millis = System.currentTimeMillis();//hao秒
        String lastReqTimes = redisUtil.getString(lastReqTime);
        redisUtil.setString(lastReqTime, String.valueOf(millis));
        if (StringUtils.isNotBlank(lastReqTimes)) {
            Long second = millis - Long.valueOf(lastReqTimes);
            if (second <= 60 * 1000) {
                long incr = redisUtil.incr(inCount);
                if (incr > 1000) {
                    logger.info("本次休眠：" + 50000);
                    sec = 50000;
                    redisUtil.incrSetZero(inCount);
                    redisUtil.delKey(lastReqTime);
                }
            } else {
                redisUtil.incrSetZero(inCount);
            }
        } else {
            redisUtil.setString(lastReqTime, String.valueOf(millis));
        }
        return sec;
    }

    @Override
    public boolean onMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

        Integer second = second();
        if (second > 0) {
            try {
                Thread.sleep(second());
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        }
        return handle(list, consumeConcurrentlyContext);
    }
}
