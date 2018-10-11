package com.segi.uhomecp.medicaltrans.report.taskrpt.dto;

import java.util.Date;

import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;

public class MtHisTaskPageDto extends MtTask {

	private static final long serialVersionUID = 8321588611052300340L;
	
	/** 开始时间（下单） */
	private Long beginTime;
	
	/** 结束时间（下单） */
	private Long endTime;
	
	/** 当前页码 */
	private Integer pageNo;
	
	/** 每页条数 */
	private Integer pageLength;
	
	/** 起始偏移量 */
	private Integer startIndex;
	
	/** 任务Ids */
	private String taskIds;
	
	/** 任务线路Id */
	private Integer routeId;
	
	/** 查询标识 */
	private String queryFlag;
	
	/** 员工Id */
	private Integer userId;
	
	/** 任务状态（执行人表） */
	private String taskStatus;
	
	/** 项目数据统计时间（科室分页查询用） */
	private Long paramDate;
	
	/** 执行结束日期限定日期 */
	private Date limitExeEndDate;
	
	/** 年份 */
	private Integer year;
	
	/** 执行路线Ids */
	private String routeIds;
	
	/** 表名 */
	private String tableName;
	
	/** 表所在的数据库实例 */
	private String tableSchema;
	
	/** 是否需要限定时期标识（1：需要限定） */
	private String limitExeEndDateFlag;
	
	/** 年月 */
	private Integer cycleYm;
	
	/** 运送内容描述*/
	private String taskContent;				 
	
	/** 签收人 */
	private Integer receiver;
	
	/** 签收人名称 */
	private Integer receiverName;
	
	/** 签收时间 */
	private Date receiveTime;	             	
	
	/** 评价信息 */
	private String evaluateContent;
	
	/** 派单人名称 */
	private String dispatchUserName;
	
	/** 导出标识 */ 
	private boolean exportFlag;
	
	public Long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageLength() {
		return pageLength;
	}

	public void setPageLength(Integer pageLength) {
		this.pageLength = pageLength;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public String getTaskIds() {
		return taskIds;
	}

	public void setTaskIds(String taskIds) {
		this.taskIds = taskIds;
	}

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	public String getQueryFlag() {
		return queryFlag;
	}

	public void setQueryFlag(String queryFlag) {
		this.queryFlag = queryFlag;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Long getParamDate() {
		return paramDate;
	}

	public void setParamDate(Long paramDate) {
		this.paramDate = paramDate;
	}

	public Date getLimitExeEndDate() {
		return limitExeEndDate;
	}

	public void setLimitExeEndDate(Date limitExeEndDate) {
		this.limitExeEndDate = limitExeEndDate;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getRouteIds() {
		return routeIds;
	}

	public void setRouteIds(String routeIds) {
		this.routeIds = routeIds;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableSchema() {
		return tableSchema;
	}

	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}

	public String getLimitExeEndDateFlag() {
		return limitExeEndDateFlag;
	}

	public void setLimitExeEndDateFlag(String limitExeEndDateFlag) {
		this.limitExeEndDateFlag = limitExeEndDateFlag;
	}

	public Integer getCycleYm() {
		return cycleYm;
	}

	public void setCycleYm(Integer cycleYm) {
		this.cycleYm = cycleYm;
	}

	public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

	public Integer getReceiver() {
		return receiver;
	}

	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}

	public Integer getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(Integer receiverName) {
		this.receiverName = receiverName;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getEvaluateContent() {
		return evaluateContent;
	}

	public void setEvaluateContent(String evaluateContent) {
		this.evaluateContent = evaluateContent;
	}

	public String getDispatchUserName() {
		return dispatchUserName;
	}

	public void setDispatchUserName(String dispatchUserName) {
		this.dispatchUserName = dispatchUserName;
	}

	public boolean isExportFlag() {
		return exportFlag;
	}

	public void setExportFlag(boolean exportFlag) {
		this.exportFlag = exportFlag;
	}

}