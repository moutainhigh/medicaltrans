package com.segi.uhomecp.medicaltrans.trans.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.segi.uhomecp.medicaltrans.trans.dto.PersonalVolumeDayDto;

/**
 * @ClassName:  PerVolDayMapper   
 * @Description:个人运送量日排名查询接口   
 * @author: zhaoqing
 * @date:   2018年9月17日 下午2:33:02
 */
public interface PerVolDayMapper {
	
	/**
	 * @Title: getDayTransVolRank   
	 *  分页查询个人运送日排名信息 
	 * @author zhaoqing  
	 * @param dto
	 * @return
	 */
	public List<PersonalVolumeDayDto> getDayTransVolRank(
			@Param(value = "dto")PersonalVolumeDayDto dto);
	
}
