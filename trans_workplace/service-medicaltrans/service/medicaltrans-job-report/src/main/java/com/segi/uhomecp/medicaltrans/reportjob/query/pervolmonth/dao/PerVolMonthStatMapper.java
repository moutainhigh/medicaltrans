package com.segi.uhomecp.medicaltrans.reportjob.query.pervolmonth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.segi.uhomecp.medicaltrans.reportjob.query.pervolmonth.dto.PersonalVolumeMonthDto;

/**
 * @ClassName:  PerVolMonthStatMapper   
 * @Description:个人运送量月报表数据汇总接口类   
 * @author: zhaoqing
 * @date:   2018年7月27日 下午4:07:55
 */
public interface PerVolMonthStatMapper {
	
	/**
	 * @Title: getPersonalVolumeMonth   
	 *  根据项目id统计个人月运送量 
	 * @author zhaoqing  
	 * @param dto
	 * @return
	 */
	public List<PersonalVolumeMonthDto> getPersonalVolumeMonth(
			@Param("dto") PersonalVolumeMonthDto dto);
	
	/**
	 * @Title: getUserAmountByOrganMonth   
	 *  根据组织机构Id和月份查询运送员数量 
	 * @author zhaoqing  
	 * @param dto
	 * @return
	 */
	public List<PersonalVolumeMonthDto> getUserAmountByOrganMonth(
			@Param("dto") PersonalVolumeMonthDto dto);
	
	/**
	 * @Title: countPerVolMonthTransAmount   
	 *  根据项目id统计个人月运送量
	 * @author zhaoqing  
	 * @param dto
	 * @return 
	 */
	public List<PersonalVolumeMonthDto> countPerVolMonthTransAmount(
			@Param("dto") PersonalVolumeMonthDto dto);
	
	/**
	 * @Title: countPerVolMonthUnSatisAmount   
	 *  根据项目id统计个人月运送量不满意数量 
	 * @author zhaoqing  
	 * @param dto
	 * @return
	 */
	public List<PersonalVolumeMonthDto> countPerVolMonthUnSatisAmount(
			@Param("dto") PersonalVolumeMonthDto dto);
	
	/**
	 * @Title: countPerVolMonthIsTimeOutAmount   
	 *  根据项目id统计个人月运送量超时数量  
	 * @author zhaoqing  
	 * @param dto
	 * @return
	 */
	public List<PersonalVolumeMonthDto> countPerVolMonthIsTimeOutAmount(
			@Param("dto") PersonalVolumeMonthDto dto);
	
}
