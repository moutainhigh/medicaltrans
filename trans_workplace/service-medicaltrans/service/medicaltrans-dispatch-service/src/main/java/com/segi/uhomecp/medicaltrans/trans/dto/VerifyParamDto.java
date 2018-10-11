package com.segi.uhomecp.medicaltrans.trans.dto;

import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;

public class VerifyParamDto {
	private Boolean isSucc;
	
	private MtTask mtTask;
	
	private CommonTaskDto commonTaskDto;

	private String message;
	
	public Boolean getIsSucc() {
		return this.isSucc;
	}

	public void setIsSucc(Boolean isSucc) {
		this.isSucc = isSucc;
	}

	public MtTask getMtTask() {
		return mtTask;
	}

	public void setMtTask(MtTask mtTask) {
		this.mtTask = mtTask;
	}

	public CommonTaskDto getCommonTaskDto() {
		return commonTaskDto;
	}

	public void setCommonTaskDto(CommonTaskDto commonTaskDto) {
		this.commonTaskDto = commonTaskDto;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
