package com.segi.uhomecp.wh.common.appmsg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import resp.RpcRespIce;
import segi.whcommon.push.MessageIce;
import segi.whcommon.push.PushServiceIcePrx;
import IceExt.IceClientUtil;

import com.alibaba.fastjson.JSONObject;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.utils.UhomePropUtils;
import com.segi.uhomecp.wh.common.appmsg.model.AppMessage;
import com.segi.uhomecp.wh.common.appmsg.model.Extra;
import com.segi.uhomecp.wh.common.appmsg.model.PushMessage;
import com.segi.uhomecp.wh.common.utils.AppUtils;

/**
 * 消息推送工具
 *     
 * 包: com.segi.uhomecp.wh.common.appmsg 
 * 类名称: AppMessageUtil.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 下午15:30:55
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
public class AppMessageUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppMessageUtil.class);
	/**
	 * 消息推送开关 关闭：0 开启：1
	 */
	private static final String SWITCH_ON = "1";
	/**
	 * 一次最大推送数据量
	 */
	private static final int MAX_PUSH_COUNT = 100;
	
	private static final String APP_MESSAGE = "APP_MSG";
	
	private PushServiceIcePrx getPushServiceIcePrx() {
		return IceClientUtil.getServicePrxByClass(PushServiceIcePrx.class);
	}
	
	private PushMessage pushMessage;
	private RestTemplate restTemplate;
	private HttpHeaders jsonHttpHeaders;
	private String HTTP_URL;
	
	private String sendType;
	
	private String notice;
	
	private String stationMessage;
	
	private List<String> messageTypes;
	
	private Map<String, AppMessage> messageMap = new HashMap<String, AppMessage>();
	
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public void setStationMessage(String stationMessage) {
		this.stationMessage = stationMessage;
	}
	
	public void setMessageTypes(List<String> messageTypes) {
		this.messageTypes = messageTypes;
	}

	public void init() {
		initMessage();
		initHttp();
	}
	
	private void initMessage() {
		if (!messageTypes.isEmpty()) {
			AppMessage message = null;
			for (String type : messageTypes) {
				try {
					message = new AppMessage(UhomePropUtils.getProperty(type+".code"), UhomePropUtils.getProperty(type+".type"), 
							UhomePropUtils.getProperty(type+".title"), UhomePropUtils.getProperty(type+".message"));
					messageMap.put(type, message);
				} catch (Exception e) {
					LOGGER.error("AppMessageUtil initMessage:{}", e);
				}
			}
		}
		LOGGER.debug("AppMessageUtil messageMap:{}",messageMap);
	}
	
	private void initHttp() {
		pushMessage = new PushMessage();
		restTemplate = new RestTemplate();
		jsonHttpHeaders = new HttpHeaders();

		pushMessage.setSendType(sendType);
		pushMessage.setNotice(notice);
		pushMessage.setStationMessage(stationMessage);

		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		jsonHttpHeaders.setContentType(type);
		jsonHttpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);

		HTTP_URL = UhomePropUtils.getProperty("restapi.root.message.producer") + "/producer/push/notice";

		LOGGER.debug("sendType:{},notice:{},stationMessage:{}", sendType, notice, stationMessage);
	}
	
	/**
	 * 根据业务类型获取消息
	 * @param bussinessType
	 * @param extra
	 * @return
	 */
	private AppMessage getAppMessage(String bussinessType) throws RuntimeException {
		return messageMap.get(bussinessType);
	}
	
	/**
	 * 发送消息
	 * @param userId	用户ID
	 * @param serviceId	业务ID
	 * @param bussinessType	业务类型
	 * @param argArray	消息格式化入参
	 * 
	 * appMessageUtil.syncSendAppMessage("userId", "serviceId", "bussinessType", new Object[]{"AAA", "BBB", ...})
	 */
	public void syncSendAppMessage(String userId, String serviceId, String bussinessType, Object... argArray) {
		syncSendAppMessage(userId, serviceId, bussinessType, null, argArray);
	}
	
	/**
	 * 
	 * @param userId     	 用户Id
	 * @param serviceId   	业务Id
	 * @param bussinessType 类型
	 * @param dataTypeWh    数据类型，初步使用任务类型
	 * @param argArray
	 */
	public void syncSendAppMessage(String userId, String serviceId,
			String bussinessType, String dataTypeWh, Object... argArray) {
		try {
			//业务消息开关打开推送消息
			if (SWITCH_ON.equals(UhomePropUtils.getProperty(bussinessType+".switch"))) {
				AppMessage message = this.getAppMessage(bussinessType);
				LOGGER.debug("=====================》argArray{}"+Arrays.toString(argArray));
				LOGGER.debug("=====================》message{}"+message.getMessage());
				String sendContent = argArray == null ? message.getMessage() : AppUtils.messageFormatter(message.getMessage(), argArray);
				LOGGER.debug("=====================》sendContent{}"+sendContent);
				Extra extra = this.getExtra(serviceId, message.getType(), message.getCode(), userId, message.getTitle(), sendContent, dataTypeWh);
				pushMessage.setExtra(extra);
				pushMessage.setKeys(bussinessType + serviceId);
				// 封装请求参数
				HttpEntity<PushMessage> entity = new HttpEntity<PushMessage>(pushMessage, jsonHttpHeaders);
				LOGGER.info("=============================调用请求参数:" + FastjsonUtils.toJsonString(entity));
				String result = restTemplate.postForObject(HTTP_URL, entity, String.class);
				LOGGER.info("=============================调用返回结果:" + result);
			}
		} catch (RestClientException e) {
			LOGGER.error("调用接口{}异常：{}", HTTP_URL, e);
		} catch (RuntimeException e) {
			LOGGER.error("发送消息异常：{}", e);
		}
	}
	
	/**
	 * 发送消息
	 * @param userId	用户ID
	 * @param serviceId	业务ID
	 * @param bussinessType	业务类型
	 * 
	 * appMessageUtil.syncSendAppMessage("userId", "serviceId", "bussinessType")
	 */
	public void syncSendAppMessage(String userId, String serviceId, String bussinessType) {
		this.syncSendAppMessage(userId, serviceId, bussinessType, null);
	}
	
	/**
	 * 发送消息
	 * @param userId	用户ID集合
	 * @param serviceId	业务ID
	 * @param bussinessType	业务类型
	 * 
	 * appMessageUtil.syncSendAppMessage("userId", "serviceId", "bussinessType")
	 */
	public void syncSendAppMessage(List<String> userList, String serviceId, String bussinessType) {
		this.syncSendAppMessage(userList, serviceId, bussinessType, null);
	}
	
	/**
	 * 批量发送消息
	 * @param userList 用户ID集合
	 * @param serviceId
	 * @param bussinessType
	 * @param argArray
	 */
	public void syncSendAppMessage(List<String> userList, String serviceId, String bussinessType, Object... argArray) {
		this.syncSendAppMessage(userList, serviceId, bussinessType, null, argArray);
	}
	
	public void syncSendAppMessage(List<String> userList, String serviceId, String bussinessType, String taskType, Object... argArray) {
		if (userList != null) {
			if (userList.size() <= MAX_PUSH_COUNT) {
				this.syncSendAppMessage(AppUtils.listToString(userList, ','), serviceId, bussinessType, taskType, argArray);
			} else {
				//取模
				int mod = (int)Math.floor(userList.size() / MAX_PUSH_COUNT);
				//取余
				int rem = (int)Math.floor(userList.size() % MAX_PUSH_COUNT);
				for (int i = 0; i < mod; i++) {
					List<String> subList = userList.subList(i * MAX_PUSH_COUNT, i * MAX_PUSH_COUNT + MAX_PUSH_COUNT);
					this.syncSendAppMessage(AppUtils.listToString(subList, ','), serviceId, bussinessType, taskType, argArray);
				}
				
				if (rem != 0) {
					List<String> remList = userList.subList(mod * MAX_PUSH_COUNT, userList.size());
					this.syncSendAppMessage(AppUtils.listToString(remList, ','), serviceId, bussinessType, taskType, argArray);
				}
			}
		}
	}
	
	/**
	 * 定时发送消息
	 * @param userId
	 * @param serviceId
	 * @param scheduleTime
	 * @param bussinessType
	 * @param argArray
	 */
	public void scheduleSendAppMessage(List<String> userList, Integer objectId, String businessKey, long scheduleTime, String bussinessType, String dataTypeWh, Object... argArray) {
		if (userList != null) {
			if (userList.size() <= MAX_PUSH_COUNT) {
				this.initAppMessage(AppUtils.listToString(userList, ','), objectId, businessKey, scheduleTime, bussinessType, dataTypeWh, argArray);
			} else {
				//取模
				int mod = (int)Math.floor(userList.size() / MAX_PUSH_COUNT);
				//取余
				int rem = (int)Math.floor(userList.size() % MAX_PUSH_COUNT);
				for (int i = 0; i < mod; i++) {
					List<String> subList = userList.subList(i * MAX_PUSH_COUNT, i * MAX_PUSH_COUNT + MAX_PUSH_COUNT);
					this.initAppMessage(AppUtils.listToString(subList, ','), objectId, businessKey, scheduleTime, bussinessType, dataTypeWh, argArray);
				}
				
				if (rem != 0) {
					List<String> remList = userList.subList(mod * MAX_PUSH_COUNT, userList.size());
					this.initAppMessage(AppUtils.listToString(remList, ','), objectId, businessKey, scheduleTime, bussinessType, dataTypeWh, argArray);
				}
			}
		}
	}
		
	/**
	 * 定时发送消息
	 * @param userId
	 * @param serviceId
	 * @param scheduleTime
	 * @param bussinessType
	 * @param argArray
	 */
	public void scheduleSendAppMessage(List<String> userList, Integer objectId, String businessKey,  long scheduleTime, String bussinessType, Object... argArray) {
		scheduleSendAppMessage(userList, objectId, businessKey, scheduleTime, bussinessType, null, argArray);
	}
	
	
	/**
	 * 删除消息
	 * @param serviceId
	 * @return
	 */
	public RpcRespIce delAppMessage(String serviceId) {
		return this.getPushServiceIcePrx().delete(serviceId, APP_MESSAGE);
	}
	
	private void initAppMessage(String userIds, Integer objectId, String businessKey, long scheduleTime,String bussinessType, String dataTypeWh, Object... argArray) {
		try {
			//业务消息开关打开推送消息
			if (SWITCH_ON.equals(UhomePropUtils.getProperty(bussinessType+".switch"))) {
				AppMessage message = this.getAppMessage(bussinessType);
				String sendContent = (null == argArray || argArray.length == 0) ? message.getMessage() : AppUtils.messageFormatter(message.getMessage(), argArray);
				Extra extra = this.getExtra(String.valueOf(objectId), message.getType(), message.getCode(), userIds, message.getTitle(), sendContent, dataTypeWh);
				pushMessage.setExtra(extra);
				pushMessage.setKeys(bussinessType + String.valueOf(objectId));
				
				MessageIce msg = new MessageIce();
				msg.setMsgTypeCode(APP_MESSAGE); // 推送CODE
				msg.setMsgTime(scheduleTime); // 执行时间
				msg.setMsgParams(JSONObject.toJSONString(pushMessage)); // 参数
				msg.setBusinessKey(businessKey);
				RpcRespIce rpc = this.getPushServiceIcePrx().push(msg);
				if (RpcError.FAIL.getCode().equals(rpc.code)) {
					LOGGER.error("发送消息异常：{}", JSONObject.toJSONString(pushMessage));
				}
			}
		} catch (RuntimeException e) {
			LOGGER.error("发送消息异常：{}", e);
		}
	}
	
	private Extra getExtra(String refId, String type, String instCode, String userId, String title, String sendContent, String dataTypeWh) {
		Extra extra = new Extra();
		extra.setUserId(userId);
		extra.setUserType("2");
		extra.setContent(sendContent);
		extra.setTitle(sendContent);
		extra.setMessageTitle(title);
		extra.setObjectId(refId);
		extra.setType(type);
		extra.setInstCode(instCode);
		if(StringUtils.isNotBlank(dataTypeWh)) {
			extra.setDataTypeWh(dataTypeWh);
		}
		return extra;
	}

}