package com.segi.uhomecp.medicaltrans.baseinfo.dbsource.dto;
import com.segi.uhomecp.common.model.AbstractModel;

/**
 * 
 * Title: MtDbSourceInfoDto.java    
 * @Description: 描述 数据源统计
 * @author wangxiong@segimail.com       
 * @created 2018年8月15日 上午11:46:29
 */
public class MtDbSourceInfoDto extends AbstractModel{
	
	/**  描述   (@author: dengdong@segimail.com) */      
	    
	private static final long serialVersionUID = 1L;

	private String dbIdx;
	
	private String tableIdx;
	
	private Integer cnt;

	public String getDbIdx() {
		return dbIdx;
	}

	public void setDbIdx(String dbIdx) {
		this.dbIdx = dbIdx;
	}

	public String getTableIdx() {
		return tableIdx;
	}

	public void setTableIdx(String tableIdx) {
		this.tableIdx = tableIdx;
	}

	public Integer getCnt() {
		return cnt;
	}

	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}

	public MtDbSourceInfoDto(String dbIdx, String tableIdx, Integer cnt) {
		super();
		this.dbIdx = dbIdx;
		this.tableIdx = tableIdx;
		this.cnt = cnt;
	}

	public MtDbSourceInfoDto() {
		super();
	}

	@Override
	public String toString() {
		return "MtDbSourceInfoDto [dbIdx=" + dbIdx + ", tableIdx=" + tableIdx + ", cnt=" + cnt + "]";
	}
}