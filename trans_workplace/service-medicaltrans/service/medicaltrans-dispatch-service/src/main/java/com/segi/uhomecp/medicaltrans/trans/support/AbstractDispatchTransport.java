package com.segi.uhomecp.medicaltrans.trans.support;

import java.util.Date;
import java.util.List;

import org.apache.rocketmq.client.producer.SendResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.segi.uhomecp.exception.BusinessException;
import com.segi.uhomecp.medicaltrans.constant.MtMqConstant;
import com.segi.uhomecp.medicaltrans.constant.MtMqConstant.TaskMqTopic;
import com.segi.uhomecp.medicaltrans.trans.dto.TransportMqDto;
import com.segi.uhomecp.medicaltrans.trans.inf.TransportBillI;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.wh.common.rocketMQ.RocketMQUtils;

/*     
 * 包: com.segi.uhomecp.medicaltrans.trans.support    
 * 类名称: AbstractDispatchTransport.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 2018年3月20日 下午7:50:16
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
public abstract class AbstractDispatchTransport<V> implements TransportBillI<V> {
	public static final Logger logger = LoggerFactory.getLogger(AbstractDispatchTransport.class);
	@Override
	public void create(List<V> v, String exeUserIds, Date nowDate) throws BusinessException {
//		List<TransportMqDto> mqList = createHandler(v, exeUserIds, nowDate);
//		//推送消息到mq type为create
//		try {
//			if (AppUtils.isNotEmpty(mqList)) {
//				for (TransportMqDto dto : mqList) {
//					String content = FastjsonUtils.toJsonString(dto);
//					logger.debug("[CREATE_TASK] message: {}", content);
//					SendResult result = RocketMQUtils.send(TaskMqTopic.TRANSPORT_REPORT_TOPIC, 
//							MtMqConstant.MT_TASK_TRANSPORT_STATISTICS_MQ_TAG, content, dto.getOrganId());
//
//					logger.debug("mq result:{}", result);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("Send mq error", e);
//		}
	}

	@Override
	public void take(V v, V beforeTask, String exeUserIds, Date nowDate) throws BusinessException {
//		TransportMqDto dto = takeHandler(v, beforeTask, exeUserIds, nowDate);
//		//推送消息到mq type为update
//		try {
//			if (null != dto) {
//				String content = FastjsonUtils.toJsonString(dto);
//				logger.debug("[HANDLER_TASK] message: {}", content);
//				SendResult result = RocketMQUtils.send(TaskMqTopic.TRANSPORT_REPORT_TOPIC, 
//						MtMqConstant.MT_TASK_TRANSPORT_STATISTICS_MQ_TAG, content, dto.getOrganId());
//				logger.debug("mq result:{}", result);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("Send mq error", e);
//		}
	}

	@Override
	public void cancel(V v, Date nowDate) throws BusinessException {
//		TransportMqDto dto = cancelHandler(v, nowDate);
//		//推送消息到mq type为cancel
//		try {
//			if (null != dto) {
//				String content = FastjsonUtils.toJsonString(dto);
//				logger.debug("[CANCEL_TASK] message: {}", content);
//				SendResult result = RocketMQUtils.send(TaskMqTopic.TRANSPORT_REPORT_TOPIC,
//						MtMqConstant.MT_TASK_TRANSPORT_STATISTICS_MQ_TAG, content, dto.getOrganId());
//				logger.debug("mq result:{}", result);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("Send mq error", e);
//		}
	}
	
	@Override
	public void grab(V v, String exeEndUserId, Date nowDate) throws BusinessException {
//		TransportMqDto dto = grabHandler(v, exeEndUserId, nowDate);
//		//推送消息到mq type为grab
//		try {
//			if (null != dto) {
//				String content = FastjsonUtils.toJsonString(dto);
//				logger.debug("[GRAB_TASK] message: {}", content);
//				SendResult result = RocketMQUtils.send(TaskMqTopic.TRANSPORT_REPORT_TOPIC,
//						MtMqConstant.MT_TASK_TRANSPORT_STATISTICS_MQ_TAG, content, dto.getOrganId());
//				logger.debug("mq result:{}", result);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("Send mq error", e);
//		}
	}
	
	@Override
	public void editSourceHouce(V v, V beforeTask, Date nowDate) {
		TransportMqDto dto = editSourceHouseHandler(v, beforeTask, nowDate);
		//推送消息到mq type为editSourceHouse
		try {
			if (null != dto) {
				String content = FastjsonUtils.toJsonString(dto);
				logger.debug("[EDIT_SOURCE_HOUSE] message: {}", content);
				SendResult result = RocketMQUtils.send(TaskMqTopic.TRANSPORT_REPORT_TOPIC,
						MtMqConstant.MT_TASK_TRANSPORT_STATISTICS_MQ_TAG, content, dto.getOrganId());
				logger.debug("mq result:{}", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Send mq error", e);
		}
	}

	/**
	 * 创建运输单业务实现
	 * @param T
	 */
	public abstract List<TransportMqDto> createHandler(List<V> v, String exeUserIds, Date nowDate);
	
	/**
	 * 接单业务实现
	 * @param T
	 */
	public abstract TransportMqDto takeHandler(V v, V beforeTask, String exeUserIds, Date nowDate);
	
	/**
	 * 取消运输单业务实现
	 * @param T
	 */
	public abstract TransportMqDto cancelHandler(V v, Date nowDate);
	
	/**
	 * 抢单运输单业务实现
	 * @param T
	 */
	public abstract TransportMqDto grabHandler(V v, String exeEndUserId, Date nowDate);
	
	/**
	 * 修改任务单时 修改了任务来源或者紧急程度推送mq
	 * @param T
	 */
	public abstract TransportMqDto editSourceHouseHandler(V v, V beforeTask, Date nowDate);
}
