package com.segi.uhomecp.medicaltrans.report.monthrank.personal.dto;

import java.io.Serializable;

/** 
 * Title: CurUserRankDto.java    
 * @Description: 描述
 * @author yangyh@segimail.com       
 * @created 2018年5月7日 下午2:19:11    
 */
public class CurUserRankDto implements Serializable {
	
	private static final long serialVersionUID = -4933388668403795158L;

	private Integer userId;
	
	private Integer rank;
	
	private Integer transVolume;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getTransVolume() {
		return transVolume;
	}

	public void setTransVolume(Integer transVolume) {
		this.transVolume = transVolume;
	}
}
