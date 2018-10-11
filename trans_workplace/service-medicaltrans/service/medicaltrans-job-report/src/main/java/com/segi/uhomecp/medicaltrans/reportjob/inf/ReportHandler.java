package com.segi.uhomecp.medicaltrans.reportjob.inf;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import com.segi.uhomecp.medicaltrans.reportjob.handler.HandlerCommunication;



/**
 * 数据处理接口
 * @author kinas
 *
 * @param <E>
 * 
 */
public interface ReportHandler<E> {
	/**
	 * 入口
	 * @param e e
	 */
	void invoke(E e);
	/**
	 * HandlerCommunication配置
	 * @param communication communication
	 */
	void setCommunication(HandlerCommunication communication);
	/**
	 * get
	 * @return
	 */
	HandlerCommunication getCommunication();
	/**
	 * 
	 * @param map
	 */
	void setStatisticsMap(ConcurrentHashMap<String, Object> map);
	/**
	 * 
	 * @return
	 */
	ConcurrentHashMap<String, Object> getStatisticsMap();
	/**
	 * getOrganId
	 * @return
	 */
	Integer getOrganId();
	/**
	 * setOrganId
	 * @param organId
	 */
	void setOrganId(Integer organId);
	/**
	 * getGroupOrganId
	 * @return
	 */
	Integer getGroupOrganId();
	/**
	 * setGroupOrganId
	 * @param groupOrganId
	 */
	void setGroupOrganId(Integer groupOrganId);
	/**
	 * getStartTime
	 * @return
	 */
	Date getStartTime();
	/**
	 * setStartTime
	 * @param startTime
	 */
	void setStartTime(Date startTime);
	/**
	 * getEndTime
	 * @return
	 */
	Date getEndTime();
	/**
	 * setEndTime
	 * @param endTime
	 */
	void setEndTime(Date endTime);
	/**
	 * getDataKey
	 * @return
	 */
	String getDataKey();
	/**
	 * getExcTime
	 * @return
	 */
	Date getExcTime();
	/**
	 * setExcTime
	 * @param excTime
	 */
	void setExcTime(Date excTime);
	/**
	 * getLastExcTime
	 * @return
	 */
	Date getLastExcTime();
	/**
	 * setLastExcTime
	 * @param lastExcTime
	 */
	void setLastExcTime(Date lastExcTime);
	/**
	 * getExcEndTime
	 * @return
	 */
	Date getExcEndTime();
	/**
	 * setExcEndTime
	 * @param excEndTime
	 */
	void setExcEndTime(Date excEndTime);
}
