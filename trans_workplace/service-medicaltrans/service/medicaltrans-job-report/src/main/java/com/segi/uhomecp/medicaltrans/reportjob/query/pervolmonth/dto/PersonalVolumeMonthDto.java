package com.segi.uhomecp.medicaltrans.reportjob.query.pervolmonth.dto;

import java.util.Date;
import java.util.List;

import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonth;
  
/** 
 * Title: MtPersonalVolumeMonthDto.java    
 * @Description: 描述
 * @author yangyh@segimail.com       
 * @created 2018年5月6日 下午3:10:32    
 */
public class PersonalVolumeMonthDto extends PersonalVolumeMonth {
      
	private static final long serialVersionUID = 1L;
	
	/** 组织机构Id集合 */
	private String organIds;
	
	/** 开始日期 */
	private Date beginDate;
	
	/** 结束日期 */
	private Date endDate;
	
	/** 任务类型 */
	private String taskType;
	
	/** 任务状态 */
	private String taskStatus;
	
	/** 不满意任务量 */
	private Integer unSatisAmount;
	
	/** 超时任务量 */
	private Integer timeOutAmount;
	
	/** 运送员Id集合 */
	private String userIds;
	
	/** 运送员数量 */
	private Integer userAmount;
	
	/** 开始时间 */
	private long beginTime;
	
	/** 结束时间 */
	private long endTime;
	
	/** 组织机构Id集合 */
	private List<Integer> organIdList;
	
	/** 分表的后缀名 */
	private String tableIndex;
	
	/** 不满意评价值 */
	private String unSatisEvaluates;
	
	/** 是否超时（0:未超时;1:超时） */
	private String isTimeOut;
	
	/** 统计类型（1:当月数据统计; 2:历史数据统计） */
	private String statType;
	
	public String getOrganIds() {
		return organIds;
	}

	public void setOrganIds(String organIds) {
		this.organIds = organIds;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Integer getUnSatisAmount() {
		return unSatisAmount;
	}

	public void setUnSatisAmount(Integer unSatisAmount) {
		this.unSatisAmount = unSatisAmount;
	}

	public Integer getTimeOutAmount() {
		return timeOutAmount;
	}

	public void setTimeOutAmount(Integer timeOutAmount) {
		this.timeOutAmount = timeOutAmount;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public Integer getUserAmount() {
		return userAmount;
	}

	public void setUserAmount(Integer userAmount) {
		this.userAmount = userAmount;
	}

	public long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public List<Integer> getOrganIdList() {
		return organIdList;
	}

	public void setOrganIdList(List<Integer> organIdList) {
		this.organIdList = organIdList;
	}

	public String getTableIndex() {
		return tableIndex;
	}

	public void setTableIndex(String tableIndex) {
		this.tableIndex = tableIndex;
	}

	public String getUnSatisEvaluates() {
		return unSatisEvaluates;
	}

	public void setUnSatisEvaluates(String unSatisEvaluates) {
		this.unSatisEvaluates = unSatisEvaluates;
	}

	public String getIsTimeOut() {
		return isTimeOut;
	}

	public void setIsTimeOut(String isTimeOut) {
		this.isTimeOut = isTimeOut;
	}

	public String getStatType() {
		return statType;
	}

	public void setStatType(String statType) {
		this.statType = statType;
	}

}
