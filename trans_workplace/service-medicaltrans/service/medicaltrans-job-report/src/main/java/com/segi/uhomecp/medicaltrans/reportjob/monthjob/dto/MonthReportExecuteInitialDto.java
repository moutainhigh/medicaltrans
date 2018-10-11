package com.segi.uhomecp.medicaltrans.reportjob.monthjob.dto;

import java.util.Date;
import java.util.List;

/**
 * 
 * Title: MonthReportExecuteInitialDto.java    
 * @Description: 描述 初始数据对象
 * @author wangxiong@segimail.com       
 * @created 2018年9月6日 下午3:23:37
 */
public class MonthReportExecuteInitialDto {

    private Date excEndDate;  // 当月任务执行统计截止时间
    
    private boolean updateFlag = false;// 当月更新表示
    
    private boolean lastUpdateFlag = false; // 上月更新标识
	
	private List<MonthReportExecuteDto> executeList;

	public List<MonthReportExecuteDto> getExecuteList() {
		return executeList;
	}

	public void setExecuteList(List<MonthReportExecuteDto> executeList) {
		this.executeList = executeList;
	}

	public Date getExcEndDate() {
		return excEndDate;
	}

	public void setExcEndDate(Date excEndDate) {
		this.excEndDate = excEndDate;
	}
	
	public boolean isUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}

	public boolean isLastUpdateFlag() {
		return lastUpdateFlag;
	}

	public void setLastUpdateFlag(boolean lastUpdateFlag) {
		this.lastUpdateFlag = lastUpdateFlag;
	}

	@Override
	public String toString() {
		return "MonthReportExecuteInitialDto [excEndDate="
				+ excEndDate + ", updateFlag=" + updateFlag + ", lastUpdateFlag=" + lastUpdateFlag + ", executeList="
				+ executeList + "]";
	}
	
}
