package com.segi.uhomecp.medicaltrans.reportjob.report.source.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.segi.uhomecp.medicaltrans.report.monthamount.source.model.SourceStatistics;

/**
 * 
 * Title: ReportSourceMapper.java    
 * @Description: 运送来源运输总量报表sql方法
 * @author yangyh@segimail.com       
 * @created 2018年9月12日 下午4:58:28
 */
public interface ReportSourceMapper {
	/**
	 * @discription 根据部门id和月份查询运输类型list
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:54:43     
	 * @param organId
	 * @param cycleYm
	 * @return list
	 */
	public List<SourceStatistics> selectByOrganIdAndCycleYm(@Param("organId")Integer organId, @Param("cycleYm")Integer cycleYm);
	
	/**
	 * @discription 根据部门idList和月份查询运输类型list
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:54:43     
	 * @param organId
	 * @param cycleYm
	 * @return list
	 */
	public List<SourceStatistics> selectByOrganIdListAndCycleYm(@Param("list")String organIdListStr, @Param("cycleYm")Integer cycleYm);

	/**
	 * @discription 根据部门id和月份list查询运输类型list
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:54:43     
	 * @param organId
	 * @param cycleYm
	 * @return list
	 */
	public List<SourceStatistics> selectByOrganIdAndCycleYmList(@Param("organId")Integer organId, @Param("list")String cycleYmListStr);
	
	/**
	 * @discription 根据运输类型对象修改
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:54:43     
	 * @param organId
	 * @param cycleYm
	 * @return list
	 */
	public Integer updateBySource(SourceStatistics SourceStatistics);
	
	/**
	 * @discription 根据运输类型对象修改
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:54:43     
	 * @param organId
	 * @param cycleYm
	 */
	public void saveBySource(SourceStatistics SourceStatistics);
	
	/**
	 * @discription 批量修改运送类型运输量
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:54:43     
	 * @param list
	 */
	public void updateBatchSource(@Param("list") List<SourceStatistics> list);
	
	/**
	 * @discription 批量新增运送类型运输量
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:54:43     
	 * @param list
	 */
	public void saveBatchSource(@Param("list") List<SourceStatistics> list);

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
