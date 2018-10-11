package com.segi.uhomecp.medicaltrans.baseinfo.transtype.dto;

import java.io.Serializable;
import java.util.Map;

import segi.medicaltrans.base.transtype.TransTypeIce;

public class MtTransTypeParentDto implements Serializable{

	/**
	 * 类描述: 创建人: liuyi@sige.com 创建时间: 2018年3月31日 下午2:04:06
	 */
	private static final long serialVersionUID = 1L;
	
	// k organId
	public Map<String, TransTypeIce> transTypetMap; // redis缓存使用
	
	public Map<String, TransTypeIce> getTransTypetMap() {
		return transTypetMap;
	}
	public void setTransTypetMap(Map<String, TransTypeIce> transTypetMap) {
		this.transTypetMap = transTypetMap;
	}
	
	public String transTypeParentCode;

	public String getTransTypeParentCode() {
		return transTypeParentCode;
	}
	
	public void setTransTypeParentCode(String transTypeParentCode) {
		this.transTypeParentCode = transTypeParentCode;
	}
}
