package com.segi.uhomecp.medicaltrans.report.monthrank.personal.service;

import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.dto.PersonalVolumeMonthDto;

/** 
 * Title: MtPersonalVolumeMonthInfoService.java    
 * @Description: 描述
 * @author yangyh@segimail.com       
 * @created 2018年5月6日 下午3:24:55    
 */
public interface PersonalVolumeMonthInfoService {

	/**
	 * @discription 根据organId查询本月运送量排名（organId必传）
	 * @author yangyh@segimail.com       
	 * @created 2018年5月7日 上午10:53:06     
	 * @param PersonalVolumeMonthDto
	 * @return
	 */
	public ResultInfo getMonthTransVolumeRank(PersonalVolumeMonthDto personalVolumeMonthDto);
	
	/**
	 * @Title: getMonthTransVolumePage   
	 *  个人运送量月报表分页查询 
	 * @author zhaoqing  
	 * @param dto
	 * @return
	 */
	public ResultInfo getMonthTransVolumePage(PersonalVolumeMonthDto dto);
	
	/**
	 * @Title: updatePersonalVolumeMonthHis   
	 *  根据运送员Id和月份更新运送员的历史运送量信息  
	 * @author zhaoqing  
	 * @param userIds
	 * @param cycleYm
	 * @param transCount
	 * @param taskType  
	 */
	public void updatePersonalVolumeMonthHis(String userIds, 
			Integer cycleYm, Integer transCount, String taskType);
}
