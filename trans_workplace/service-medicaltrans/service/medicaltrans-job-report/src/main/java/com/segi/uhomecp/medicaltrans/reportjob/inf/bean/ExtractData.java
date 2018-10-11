package com.segi.uhomecp.medicaltrans.reportjob.inf.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 数据抽取通用类
 *     
 * 包: com.segi.uhomecp.medicaltrans.reportjob.inf.bean 
 * 类名称: ExtractData.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 上午9:51:51
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
public class ExtractData<T> implements Serializable {

	private static final long serialVersionUID = 3125100762327073967L;

	private Integer organId;
	
	private Integer groupOrganId;
	
	private Date startTime;
	
	private Date endTime;
	
	private Date excTime;
	
	private Date lastExcTime;
	
	private Date excEndTime;
	
	private Map<String, List<T>> dataList;
	
	public ExtractData() {
	}
	
	public ExtractData(Integer organId, Integer groupOrganId, Date startTime,
			Date endTime, Date excTime, Date lastExcTime, Date excEndTime, 
			Map<String, List<T>> dataList) {
		super();
		this.organId = organId;
		this.groupOrganId = groupOrganId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.excTime = excTime;
		this.lastExcTime = lastExcTime;
		this.excEndTime = excEndTime;
		this.dataList = dataList;
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

	public Date getExcTime() {
		return excTime;
	}

	public void setExcTime(Date excTime) {
		this.excTime = excTime;
	}
	
	public Date getLastExcTime() {
		return lastExcTime;
	}

	public void setLastExcTime(Date lastExcTime) {
		this.lastExcTime = lastExcTime;
	}

	public Date getExcEndTime() {
		return excEndTime;
	}

	public void setExcEndTime(Date excEndTime) {
		this.excEndTime = excEndTime;
	}

	public Map<String, List<T>> getDataList() {
		return dataList;
	}

	public void setDataList(Map<String, List<T>> dataList) {
		this.dataList = dataList;
	}
	
}
