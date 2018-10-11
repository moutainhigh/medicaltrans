package com.segi.uhomecp.medicaltrans.report.monthrank.personal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.segi.uhomecp.medicaltrans.report.monthrank.personal.dto.PersonalVolumeMonthDto;

/** 
 * Title: PersonalVolumeMonthInfoMapper.java    
 * @Description: 描述
 * @author yangyh@segimail.com       
 * @created 2018年5月8日 下午4:15:49    
 */
public interface PersonalVolumeMonthInfoMapper {

	/**
	 * @Title: selectIdByExamplePage   
	 *  分页查询个人运送量月报表数据的主键ID 
	 * @author zhaoqing  
	 * @param example
	 * @param dto
	 * @return
	 */
	public List<Integer> selectIdByExamplePage(@Param("dto")PersonalVolumeMonthDto dto);
}
