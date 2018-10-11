package com.segi.uhomecp.medicaltrans.reportjob.handler;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.segi.uhomecp.medicaltrans.reportjob.enums.HandlerStateEnums;
import com.segi.uhomecp.medicaltrans.reportjob.inf.ReportHandler;

public abstract class AbstractHandler<E> implements ReportHandler<E> {
	
	private static final Logger LOG = LoggerFactory.getLogger(AbstractHandler.class);
	
	private HandlerCommunication communication;
	
	private ConcurrentHashMap<String, Object> countMap;
	/**
	 * 项目ID
	 */
	private Integer organId;
	/**
	 * 集团ID
	 */
	private Integer groupOrganId;
	/**
	 * 取数开始时间
	 */
	private Date startTime;
	/**
	 * 取数截止时间
	 */
	private Date endTime;
	/**
	 * 本月执行时间
	 */
	private Date excTime;
	/**
	 * 上月执行时间
	 */
	private Date lastExcTime;
	/**
	 * 本月截止时间
	 */
	private Date excEndTime;
	
	public void setCommunication(HandlerCommunication communication) {
		this.communication = communication;
	}
	
	public HandlerCommunication getCommunication() {
		return communication;
	}
	/**
	 * handlerName
	 * @return
	 */
	public abstract String getHandlerName();
	/**
	 * getDataKey
	 * @return
	 */
	public abstract String getDataKey();
	/**
	 * doInvoke
	 * @param e
	 */
	public abstract void doInvoke(final E e);
	
	@Override
	public void invoke(final E e) {
		communication.setName(this.getHandlerName());
		LOG.info("Handler start...");
		try {
			this.doInvoke(e);
			communication.setState(HandlerStateEnums.COMMPLETED);
		} catch (Exception ex) {
			LOG.error("处理器处理异常", ex);
			communication.setState(HandlerStateEnums.ERROR);
			communication.setThrowable(ex);
		}
		LOG.info("Handler end...");
	}
	
	@Override
	public void setStatisticsMap(ConcurrentHashMap<String, Object> map) {
		this.countMap = map;
	}
	
	@Override
	public ConcurrentHashMap<String, Object> getStatisticsMap() {
		return countMap;
	}
	/**
	 * 项目ID
	 */
	@Override
	public Integer getOrganId() {
		return this.organId;
	}

	@Override
	public void setOrganId(Integer organId) {
		this.organId = organId;
	}
	/**
	 * 集团ID
	 */
	@Override
	public Integer getGroupOrganId() {
		return this.groupOrganId;
	}

	@Override
	public void setGroupOrganId(Integer groupOrganId) {
		this.groupOrganId = groupOrganId;
	}
	/**
	 * 取数开始时间
	 */
	@Override
	public Date getStartTime() {
		return this.startTime;
	}

	@Override
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 取数截止时间
	 */
	@Override
	public Date getEndTime() {
		return this.endTime;
	}

	@Override
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public Date getExcTime() {
		return excTime;
	}

	@Override
	public void setExcTime(Date excTime) {
		this.excTime = excTime;
	}

	@Override
	public Date getLastExcTime() {
		return this.lastExcTime;
	}

	@Override
	public void setLastExcTime(Date lastExcTime) {
		this.lastExcTime = lastExcTime;
	}

	@Override
	public Date getExcEndTime() {
		return this.excEndTime;
	}

	@Override
	public void setExcEndTime(Date excEndTime) {
		this.excEndTime = excEndTime;
	}

}
