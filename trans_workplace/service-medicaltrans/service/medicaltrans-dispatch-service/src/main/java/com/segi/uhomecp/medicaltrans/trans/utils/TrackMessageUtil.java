package com.segi.uhomecp.medicaltrans.trans.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import segi.medicaltrans.mttask.track.ItemIce;

import com.segi.uhomecp.medicaltrans.trans.dto.Item;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.utils.UhomePropUtils;
import com.segi.uhomecp.wh.common.utils.AppUtils;

/**
 * Title: TrackMessageUtil.java    
 * @Description: 运送单轨迹处理工具类
 * @author zhangyang@segimail.com       
 * @created 2018年9月25日 下午4:59:14
 */
public class TrackMessageUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TrackMessageUtil.class);
	/**
	 * 是否处理运送轨迹开关 关闭：0 开启：1
	 */
	private static final String SWITCH_ON = "1";
	
	private List<String> actionTypes;
	
	public List<String> getActionTypes() {
		return actionTypes;
	}

	public void setActionTypes(List<String> actionTypes) {
		this.actionTypes = actionTypes;
	}

	private Map<String, Item> trackMessageMap = new HashMap<String, Item>();
	
	public void init() {
		initTrack();
	}
	
	private void initTrack() {
		if (!actionTypes.isEmpty()) {
			Item item = null;
			for (String type : actionTypes) {
				try {
					item = new Item(UhomePropUtils.getProperty(type + ".message"));
					trackMessageMap.put(type, item);
				} catch (Exception e) {
					LOGGER.error("TrackMessageUtil initTrack:{}", e);
				}
			}
		}
		LOGGER.debug("TrackMessageUtil trackMessageMap:{}", trackMessageMap);
	}
	
	/**
	 * 根据业务类型获取消息
	 * @param bussinessType
	 * @param extra
	 * @return
	 */
	private Item getTrackMessage(String bussinessType) throws RuntimeException {
		return trackMessageMap.get(bussinessType);
	}
	
	/**
	 * @discription 处理轨迹消息
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月25日 下午4:06:36     
	 * @param actionType
	 * @param item
	 * @param argArray
	 * @return
	 */
	public ItemIce initTaskTrackMessage(String actionType, String userId, 
			String userName, String createDate, String specialType, Object... argArray) {
		if (StringUtils.isBlank(actionType) || StringUtils.isBlank(createDate) 
				|| StringUtils.isBlank(userName) || StringUtils.isBlank(userId)) {
			return null;
		}
		ItemIce trackIce = null;
		try {
			if (SWITCH_ON.equals(UhomePropUtils.getProperty(actionType+".switch"))) {
				trackIce = new ItemIce();
				
				Item item = this.getTrackMessage(actionType);
				LOGGER.debug("=====================》argArray{}"+ Arrays.toString(argArray));
				LOGGER.debug("=====================》message{}"+ item.getMessage());
				String trackMessage = argArray == null ? item.getMessage() : AppUtils.messageFormatter
						(item.getMessage(), argArray);
				LOGGER.debug("=====================》trackMessage{}" + trackMessage);
				
				trackIce.setMessage(trackMessage);
				trackIce.setAction(actionType);
				trackIce.setUserId(userId);
				trackIce.setUserName(userName);
				trackIce.setCreateDate(createDate);
				if (StringUtils.isNotBlank(specialType)) {
					trackIce.setSpecialType(specialType);
				}
			}
		} catch (RuntimeException e) {
			LOGGER.error("TrackMessageUtil -> initTaskTrackMessage：{}", e);
		}
		return trackIce;
	}
	
	public ItemIce initTaskTrackMessage(String actionType, String userId, 
			String userName, String createDate, Object... argArray) {
		return initTaskTrackMessage(actionType, userId, userName, createDate, null, argArray);
	}
	
	/**
	 * @discription 保存单个轨迹
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月26日 下午1:15:41     
	 * @param taskId
	 * @param itemIce
	 */
	public void saveTaskTrackMessage(Integer taskId, ItemIce itemIce) {
		if (null == taskId || null == itemIce) {
			return ;
		}
		try {
			MtCommonServiceUtils.saveTaskTrackMessage(taskId, itemIce);
		} catch (RuntimeException e) {
			LOGGER.error("TrackMessageUtil -> saveTaskTrackMessage：{}", e);
		}
	}
	
	/**
	 * @discription 创建任务时,保存轨迹
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月25日 下午4:11:05     
	 * @param taskId
	 * @param organId
	 */
	public void saveTrackForCreateTask(String taskId, Integer organId, Long beginTime, List<ItemIce> itemIceList) {
		if (StringUtils.isBlank(taskId) || null == organId 
				|| null == beginTime || !AppUtils.isNotEmpty(itemIceList)) {
			return ;
		}
		try {
			MtCommonServiceUtils.saveTrackForCreateTask(taskId, organId, beginTime, itemIceList);
		} catch (RuntimeException e) {
			LOGGER.error("TrackMessageUtil -> saveTrackForCreateTask：{}", e);
		}
	}
	
	/**
	 * @discription 编辑任务时修改了预约时间，处理轨迹
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月28日 上午11:28:01     
	 * @param taskId
	 * @param organId
	 * @param beginTime
	 */
	public void updateTrackForEditTask(String taskId, Integer organId, Long beginTime) {
		if (StringUtils.isBlank(taskId) || null == organId || null == beginTime) {
			return ;
		}
		try {
			MtCommonServiceUtils.editTaskTrackMessage(taskId, organId, beginTime);
		} catch (RuntimeException e) {
			LOGGER.error("TrackMessageUtil -> editTaskTrackMessage：{}", e);
		}
	}
	
}