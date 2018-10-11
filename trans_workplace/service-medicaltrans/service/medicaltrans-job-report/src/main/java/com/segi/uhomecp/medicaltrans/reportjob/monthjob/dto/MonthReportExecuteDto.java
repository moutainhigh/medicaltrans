package com.segi.uhomecp.medicaltrans.reportjob.monthjob.dto;

import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.ReportJobTimeDto;

public class MonthReportExecuteDto {

	private String executeObj;

	private ReportJobTimeDto params;

	public String getExecuteObj() {
		return executeObj;
	}

	public void setExecuteObj(String executeObj) {
		this.executeObj = executeObj;
	}

	public ReportJobTimeDto getParams() {
		return params;
	}

	public void setParams(ReportJobTimeDto params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "MonthReportExecuteDto [executeObj=" + executeObj + ", params=" + params + "]";
	}

}
