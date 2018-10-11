package com.segi.uhomecp.medicaltrans.reportjob.inf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.segi.uhomecp.medicaltrans.report.schedule.model.ExecuteHandler;

/**
 * 
 *     
 * 包: com.segi.uhomecp.medicaltrans.reportjob.inf 
 * 类名称: AbstractDefaultHandler.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 下午7:03:38
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
public abstract class AbstractDefaultHandler<K> implements HandlerExecute<K> {
	
	private List<ExecuteHandler> handlerInfos = Collections.synchronizedList(new ArrayList<ExecuteHandler>());
	
	private Integer organId;
	
	private Integer groupOrganId;
	
	private Date startTime;
	
	private Date endTime;
	
	public List<ExecuteHandler> getHandlerInfos() {
		return handlerInfos;
	}

	public void setHandlerInfos(List<ExecuteHandler> handlerInfos) {
		this.handlerInfos = handlerInfos;
	}

	public Integer getOrganId() {
		return organId;
	}

	public void setOrganId(Integer organId) {
		this.organId = organId;
	}

	public Integer getGroupOrganId() {
		return groupOrganId;
	}

	public void setGroupOrganId(Integer groupOrganId) {
		this.groupOrganId = groupOrganId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}
