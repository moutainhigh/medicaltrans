package com.segi.uhomecp.medicaltrans.baseinfo.dbsource.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.segi.uhomecp.medicaltrans.baseinfo.dbsource.dto.MtDbSourceInfoDto;

/**
 * 
 * Title: MtDbSourceInfoMapper.java    
 * @Description: 描述 数据源统计个性化SQL
 * @author wangxiong@segimail.com       
 * @created 2018年8月15日 上午11:43:45
 */
public interface MtDbSourceInfoMapper {

	/**
	 * @discription 统计每个数据源的使用个数
	 * @author wangxiong@segimail.com       
	 * @created 2018年8月15日 上午11:47:21     
	 * @return
	 */
	public List<MtDbSourceInfoDto> queryDbKeyCnt();
	
	public List<MtDbSourceInfoDto> queryTableIdxCnt(@Param("dbIdx") String dbIdx);
}
