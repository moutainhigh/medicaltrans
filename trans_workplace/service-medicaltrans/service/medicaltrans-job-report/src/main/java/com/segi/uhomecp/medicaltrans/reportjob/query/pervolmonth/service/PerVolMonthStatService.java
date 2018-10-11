package com.segi.uhomecp.medicaltrans.reportjob.query.pervolmonth.service;

import java.util.List;

import com.segi.uhomecp.medicaltrans.reportjob.query.pervolmonth.dto.PersonalVolumeMonthDto;

/**
 * @ClassName:  PerVolMonthStatService   
 * @Description: 个人运送量月报表数据汇总接口   
 * @author: zhaoqing
 * @date:   2018年7月27日 下午3:02:45
 */
public interface PerVolMonthStatService {
	
	/**
	 * @Title: getPersonalVolumeMonth   
	 *  根据项目id统计个人月运送量 
	 * @author zhaoqing  
	 * @param groupOrganId
	 * @param dto
	 * @return
	 */
	public List<PersonalVolumeMonthDto> getPersonalVolumeMonth(
			Integer groupOrganId, PersonalVolumeMonthDto dto);
	
	/**
	 * @Title: getUserAmountByOrganMonth   
	 *  根据项目Id和月份查询运送员人数 
	 * @author zhaoqing  
	 * @param groupOrganId
	 * @param dto
	 * @return
	 */
	public List<PersonalVolumeMonthDto> getUserAmountByOrganMonth(
			Integer groupOrganId, PersonalVolumeMonthDto dto);
	
	/**
	 * @Title: countPerVolMonthTransAmount   
	 *  根据项目id统计个人月运送量
	 * @author zhaoqing  
	 * @param dto
	 * @return 
	 */
	public List<PersonalVolumeMonthDto> countPerVolMonthTransAmount(
			Integer groupOrganId, PersonalVolumeMonthDto dto);
	
	/**
	 * @Title: countPerVolMonthUnSatisAmount   
	 *  根据项目id统计个人月运送量不满意数量 
	 * @author zhaoqing  
	 * @param dto
	 * @return
	 */
	public List<PersonalVolumeMonthDto> countPerVolMonthUnSatisAmount(
			Integer groupOrganId, PersonalVolumeMonthDto dto);
	
	/**
	 * @Title: countPerVolMonthIsTimeOutAmount   
	 *  根据项目id统计个人月运送量超时数量  
	 * @author zhaoqing  
	 * @param dto
	 * @return
	 */
	public List<PersonalVolumeMonthDto> countPerVolMonthIsTimeOutAmount(
			Integer groupOrganId, PersonalVolumeMonthDto dto);
}
