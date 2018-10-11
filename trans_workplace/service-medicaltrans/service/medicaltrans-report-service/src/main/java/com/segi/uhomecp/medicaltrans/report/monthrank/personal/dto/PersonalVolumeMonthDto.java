package com.segi.uhomecp.medicaltrans.report.monthrank.personal.dto;

import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonth;
  
/** 
 * Title: MtPersonalVolumeMonthDto.java    
 * @Description: 描述
 * @author yangyh@segimail.com       
 * @created 2018年5月6日 下午3:10:32    
 */
public class PersonalVolumeMonthDto extends PersonalVolumeMonth {

	/**  描述   (@author: yangyh@segimail.com) */      
	private static final long serialVersionUID = 1266288207897824128L;

	/** 当前页码 */
	private Integer pageNo;
	
	/** 每页条数 */
	private Integer pageLength;
	
	/** 分页查询第一个返回记录行的偏移量 */
	private Integer offset;
		
	/** 分页查询每页限制数据条数 */
	private Integer limit;
	
	/** 排名名次 */
	private Integer rank;
	
	/** 组织名称 */
	private String organName;
	
	/** 运送员名称 */
	private String userName;
	
	/** 运送员员工号 */
	private String userNo;
	
	/** 员工IDS */
	private String userIds;
	
	/** 员工所属的服务组名称 */
	private String sergroupName; 
	
	/** 调度任务满意率 */
	private String dispatchSatisfactionRatio;	

	/** 调度任务及时率 */
	private String dispatchTimelyRatio;	

	/** 自主任务满意率 */
	private String autonomousSatisfactionRatio;	
	
	/** 自主任务及时率 */
	private String autonomousTimelyRatio;	
	
	/** 固定任务满意率 */
	private String fixedSatisfactionRatio;	
 
	/** 固定任务及时率 */
	private String fixedTimelyRatio; 
	
	private String team;
	
	/** 个人运送量（个人排名查询用） */
	private Integer transVolume;
	
	/** 任务类型 */
	private String taskType;
	
	/** 服务组Ids */
	private String sergroupIds;

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
	
	public Integer getRank() {
		return rank;
	}
	
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public String getSergroupName() {
		return sergroupName;
	}

	public void setSergroupName(String sergroupName) {
		this.sergroupName = sergroupName;
	}

	public String getDispatchSatisfactionRatio() {
		return dispatchSatisfactionRatio;
	}

	public void setDispatchSatisfactionRatio(String dispatchSatisfactionRatio) {
		this.dispatchSatisfactionRatio = dispatchSatisfactionRatio;
	}

	public String getDispatchTimelyRatio() {
		return dispatchTimelyRatio;
	}

	public void setDispatchTimelyRatio(String dispatchTimelyRatio) {
		this.dispatchTimelyRatio = dispatchTimelyRatio;
	}

	public String getAutonomousSatisfactionRatio() {
		return autonomousSatisfactionRatio;
	}

	public void setAutonomousSatisfactionRatio(String autonomousSatisfactionRatio) {
		this.autonomousSatisfactionRatio = autonomousSatisfactionRatio;
	}

	public String getAutonomousTimelyRatio() {
		return autonomousTimelyRatio;
	}

	public void setAutonomousTimelyRatio(String autonomousTimelyRatio) {
		this.autonomousTimelyRatio = autonomousTimelyRatio;
	}

	public String getFixedSatisfactionRatio() {
		return fixedSatisfactionRatio;
	}

	public void setFixedSatisfactionRatio(String fixedSatisfactionRatio) {
		this.fixedSatisfactionRatio = fixedSatisfactionRatio;
	}

	public String getFixedTimelyRatio() {
		return fixedTimelyRatio;
	}

	public void setFixedTimelyRatio(String fixedTimelyRatio) {
		this.fixedTimelyRatio = fixedTimelyRatio;
	}

	public Integer getTransVolume() {
		return transVolume;
	}

	public void setTransVolume(Integer transVolume) {
		this.transVolume = transVolume;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getSergroupIds() {
		return sergroupIds;
	}

	public void setSergroupIds(String sergroupIds) {
		this.sergroupIds = sergroupIds;
	}
	
}
