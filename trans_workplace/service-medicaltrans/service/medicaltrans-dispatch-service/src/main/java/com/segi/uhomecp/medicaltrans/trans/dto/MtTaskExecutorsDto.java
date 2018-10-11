package com.segi.uhomecp.medicaltrans.trans.dto;

import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutors;

  
/** 
 * Title: MtTaskExecutorsDto.java    
 * @Description: 描述
 * @author yangyh@segimail.com       
 * @created 2018年5月16日 上午10:45:09    
 */

public class MtTaskExecutorsDto extends MtTaskExecutors {

	/**  描述   (@author: yangyh@segimail.com) */      
	private static final long serialVersionUID = 1490744244424842304L;
	
	private Integer userId;
	
	private Integer transVolume;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTransVolume() {
		return transVolume;
	}

	public void setTransVolume(Integer transVolume) {
		this.transVolume = transVolume;
	}
}
