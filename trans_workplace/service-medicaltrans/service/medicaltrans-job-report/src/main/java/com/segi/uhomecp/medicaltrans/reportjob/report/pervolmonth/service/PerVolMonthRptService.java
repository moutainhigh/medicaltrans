package com.segi.uhomecp.medicaltrans.reportjob.report.pervolmonth.service;

import java.util.List;

import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonth;
import com.segi.uhomecp.medicaltrans.reportjob.report.organmonth.dto.AmountMonthUserDto;

/**
 * @ClassName:  PerVolMonthRptService   
 * @Description:个人运送量月报表数据操作接口   
 * @author: zhaoqing
 * @date:   2018年7月30日 下午2:26:05
 */
public interface PerVolMonthRptService {
	
	/**
	 * @Title: savePerVolMonthIbatch   
	 *  批量保存个人运送量月报表统计数据 
	 * @author zhaoqing  
	 * @param list
	 */
	public void savePerVolMonthIbatch(List<PersonalVolumeMonth> list);
	
	/**
	 * @Title: deletePerVolMonthByOrganId   
	 *  根据组织机构删除个人运送量月报表当月表的统计数据 
	 * @author zhaoqing  
	 * @param organIdList
	 */
	public void deletePerVolMonthByOrganId(List<Integer> organIdList);
	
	/**
	 * @Title: savePerVolMonthHisIbatch   
	 *  批量保存个人运送量月报表统计数据(历史表数据)
	 * @author zhaoqing  
	 * @param list
	 */
	public void savePerVolMonthHisIbatch(List<PersonalVolumeMonth> list);
	
	/**
	 * @Title: deletePerVolMonth   
	 *  清空当月表的数据
	 * @author zhaoqing        
	 * void   
	 */
	public void deletePerVolMonth();
	
	/**
	 * @Title: updatePerVolMonthByMonth   
	 *  按项目更新个人运送量月报表历史信息 
	 * @author zhaoqing 
	 * @param organId 
	 * @param cycleYm
	 * @return
	 */
	public void updatePerVolMonthByMonth(Integer groupOrganId, Integer organId, 
			Integer cycleYm, List<PersonalVolumeMonth> perVolMonthList);
	
	/**
	 * @Title: updatePersonalVolumeMonth   
	 *  每天凌晨定时刷新人员个人运送量数据库信息 
	 * @author zhaoqing  
	 * @param organList
	 * @param perVolMonthList
	 * @return
	 */
	public void updatePersonalVolumeMonth(List<Integer> organIdList, 
			List<PersonalVolumeMonth> perVolMonthList);
	
	/**
	 * @Title: updatePersonalVolumeMonthByOrgan   
	 *  按组织机构刷新个人运送量信息 
	 * @author zhaoqing  
	 * @param groupOrganId
	 * @param organId
	 * @param perVolMonthList 
	 */
	public void updatePersonalVolumeMonthByOrgan(Integer groupOrganId, 
			Integer organId, List<PersonalVolumeMonth> perVolMonthList, Integer cycleYm);
	
	/**
	 * @Title: initAmountMonthUserDtoList   
	 *  初始化项目运送量表中的运送人数信息 
	 * @author zhaoqing  
	 * @param perVolMonthList
	 * @return
	 */
	public List<AmountMonthUserDto> initAmountMonthUserDtoList(Integer groupOrganId, 
			List<PersonalVolumeMonth> perVolMonthList, List<Integer> organIdList, Integer exeYearMonth);
	
	/**
	 * @Title: initAmountMonthUserDtoNull   
	 *  初始化项目运送量表中的运送人数信息(个人运送量返回为空)
	 * @author zhaoqing  
	 * @param perVolMonthList
	 * @return
	 */
	public List<AmountMonthUserDto> initAmountMonthUserDtoNull(Integer groupOrganId, 
			List<Integer> organIdList, Integer exeYearMonth);
	
	/**
	 * @Title: refreshPerVolumeRedis   
	 *  按组织机构刷新个人运送量缓存信息 
	 * @author zhaoqing  
	 * @param organId
	 * @param list
	 * @param updateType 缓存刷新类型（1:全量刷新, 其他则是增量刷新）
	 */
	public void refreshPerVolumeRedis(int organId, List<PersonalVolumeMonth> list, String updateType);
}
