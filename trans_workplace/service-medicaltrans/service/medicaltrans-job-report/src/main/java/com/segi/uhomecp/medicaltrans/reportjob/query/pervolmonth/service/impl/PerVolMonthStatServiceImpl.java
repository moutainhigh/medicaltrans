package com.segi.uhomecp.medicaltrans.reportjob.query.pervolmonth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.medicaltrans.reportjob.query.pervolmonth.dao.PerVolMonthStatMapper;
import com.segi.uhomecp.medicaltrans.reportjob.query.pervolmonth.dto.PersonalVolumeMonthDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.pervolmonth.service.PerVolMonthStatService;
import com.segi.uhomecp.medicaltrans.utils.DynamicTableSourceKeyHolder;

/**
 * @ClassName:  PerVolMonthStatServiceImpl   
 * @Description:个人运送量月报表数据汇总接口业务实现类  
 * @author: zhaoqing
 * @date:   2018年7月27日 下午3:08:20
 */
@Service
public class PerVolMonthStatServiceImpl implements PerVolMonthStatService {
	
	@Autowired
	private PerVolMonthStatMapper perVolMonthStatMapper;

	/**
	 * <p>Title: getPersonalVolumeMonth</p>   
	 * <p>Description: 根据项目id统计个人月运送量</p> 
	 * <p>zhaoqing</p>
	 * @param groupOrganId
	 * @param dto
	 * @return   
	 */
	@Override
	public List<PersonalVolumeMonthDto> getPersonalVolumeMonth(
			Integer groupOrganId, PersonalVolumeMonthDto dto) {
		String tableIndex = DynamicTableSourceKeyHolder.getDataSourceKey();
		dto.setTableIndex(tableIndex);
		return perVolMonthStatMapper.getPersonalVolumeMonth(dto);
	}
	
	/**
	 * <p>Title: getUserAmountByOrganMonth</p>   
	 * <p>Description: 根据项目Id和月份查询运送员人数</p> 
	 * <p>zhaoqing</p>
	 * @param groupOrganId
	 * @param dto
	 * @return   
	 */
	@Override
	public List<PersonalVolumeMonthDto> getUserAmountByOrganMonth(
			Integer groupOrganId, PersonalVolumeMonthDto dto) {
		String tableIndex = DynamicTableSourceKeyHolder.getDataSourceKey();
		dto.setTableIndex(tableIndex);
		return perVolMonthStatMapper.getUserAmountByOrganMonth(dto);
	}

	/**
	 * <p>Title: countPerVolMonthTransAmount</p>   
	 * <p>Description: 根据项目id统计个人月运送量</p> 
	 * <p>zhaoqing</p>
	 * @param groupOrganId
	 * @param dto
	 * @return   
	 */
	@Override
	public List<PersonalVolumeMonthDto> countPerVolMonthTransAmount(
			Integer groupOrganId, PersonalVolumeMonthDto dto) {
		String tableIndex = DynamicTableSourceKeyHolder.getDataSourceKey();
		dto.setTableIndex(tableIndex);
		return perVolMonthStatMapper.countPerVolMonthTransAmount(dto);
	}

	/**
	 * <p>Title: countPerVolMonthUnSatisAmount</p>   
	 * <p>Description: 根据项目id统计个人月运送量不满意数量</p> 
	 * <p>zhaoqing</p>
	 * @param groupOrganId
	 * @param dto
	 * @return   
	 */
	@Override
	public List<PersonalVolumeMonthDto> countPerVolMonthUnSatisAmount(
			Integer groupOrganId, PersonalVolumeMonthDto dto) {
		String tableIndex = DynamicTableSourceKeyHolder.getDataSourceKey();
		dto.setTableIndex(tableIndex);
		return perVolMonthStatMapper.countPerVolMonthUnSatisAmount(dto);
	}

	/**
	 * <p>Title: countPerVolMonthIsTimeOutAmount</p>   
	 * <p>Description: 根据项目id统计个人月运送量超时数量</p> 
	 * <p>zhaoqing</p>
	 * @param groupOrganId
	 * @param dto
	 * @return   
	 */
	@Override
	public List<PersonalVolumeMonthDto> countPerVolMonthIsTimeOutAmount(
			Integer groupOrganId, PersonalVolumeMonthDto dto) {
		String tableIndex = DynamicTableSourceKeyHolder.getDataSourceKey();
		dto.setTableIndex(tableIndex);
		return perVolMonthStatMapper.countPerVolMonthIsTimeOutAmount(dto);
	}

}
