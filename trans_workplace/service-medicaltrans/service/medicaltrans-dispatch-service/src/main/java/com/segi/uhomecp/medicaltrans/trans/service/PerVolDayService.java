package com.segi.uhomecp.medicaltrans.trans.service;

import java.util.List;

import com.segi.uhomecp.medicaltrans.trans.dto.PersonalVolumeDayDto;

/**
 * @ClassName:  PerVolDayService   
 * @Description:个人运送量日排名接口   
 * @author: zhaoqing
 * @date:   2018年9月17日 上午11:46:50
 */
public interface PerVolDayService {

	/**
	 * @Title: getDayTransVolRank   
	 *  分页查询个人运送量日排名信息 
	 * @author zhaoqing  
	 * @param groupOrganId
	 * @param paramsDto
	 * @return
	 */
	public List<PersonalVolumeDayDto> getDayTransVolRank(
			int groupOrganId, PersonalVolumeDayDto paramsDto);
	
}
