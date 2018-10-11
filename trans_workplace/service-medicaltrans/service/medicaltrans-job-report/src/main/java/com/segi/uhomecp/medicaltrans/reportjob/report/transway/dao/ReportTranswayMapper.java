package com.segi.uhomecp.medicaltrans.reportjob.report.transway.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.segi.uhomecp.medicaltrans.report.monthamount.transway.model.TranswayStatistics;

/**
 * Title: ReportTranswayMapper.java    
 * @Description: 运送方式运输总量报表sql方法
 * @author yangyh@segimail.com       
 * @created 2018年9月12日 下午4:58:28
 */
public interface ReportTranswayMapper {
	/**
	 * @discription 根据部门id和月份查询运输类型list
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:54:43     
	 * @param organId
	 * @param cycleYm
	 * @return list
	 */
	public List<TranswayStatistics> selectByOrganIdAndCycleYm(@Param("organId")Integer organId, @Param("cycleYm")Integer cycleYm);
	
	/**
	 * @discription 根据部门idList和月份查询运输类型list
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:54:43     
	 * @param organId
	 * @param cycleYm
	 * @return list
	 */
	public List<TranswayStatistics> selectByOrganIdListAndCycleYm(@Param("list")String organIdListStr, @Param("cycleYm")Integer cycleYm);

	/**
	 * @discription 根据部门id和月份list查询运输类型list
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:54:43     
	 * @param organId
	 * @param cycleYm
	 * @return list
	 */
	public List<TranswayStatistics> selectByOrganIdAndCycleYmList(@Param("organId")Integer organId, @Param("list")String cycleYmListStr);
	
	/**
	 * @discription 根据运输类型对象修改
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:54:43     
	 * @param organId
	 * @param cycleYm
	 * @return list
	 */
	public Integer updateByTransway(TranswayStatistics transwayStatistics);
	
	/**
	 * @discription 根据运输类型对象修改
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:54:43     
	 * @param organId
	 * @param cycleYm
	 */
	public void saveByTransway(TranswayStatistics transwayStatistics);
	
	/**
	 * @discription 批量修改运送类型运输量
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:54:43     
	 * @param list
	 */
	public void updateBatchTransway(@Param("list") List<TranswayStatistics> list);
	
	/**
	 * @discription 批量新增运送类型运输量
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:54:43     
	 * @param list
	 */
	public void saveBatchTransway(@Param("list") List<TranswayStatistics> list);

	/**
	 * @discription 根据部门id和月份删除
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:54:43     
	 * @param organId
	 * @param cycleYm
	 */
	public void deleteByOrganIdAndCycleYm(@Param("organId")Integer organId, @Param("cycleYm")Integer cycleYm);
	
	/**
	 * @discription 根据部门idList和月份删除
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:54:43     
	 * @param organId
	 * @param cycleYm
	 */
	public void deleteByOrganListAndCycleYm(@Param("organIdList")String organIdList, @Param("cycleYm")Integer cycleYm);
}
