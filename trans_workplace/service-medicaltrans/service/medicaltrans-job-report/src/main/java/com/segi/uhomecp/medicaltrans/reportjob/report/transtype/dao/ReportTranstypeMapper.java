package com.segi.uhomecp.medicaltrans.reportjob.report.transtype.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.segi.uhomecp.medicaltrans.report.monthamount.transtype.model.TranstypeStatistics;

/**
 * Title: ReportTranstypeMapper.java    
 * @Description: 运送类型运输总量报表sql方法
 * @author yangyh@segimail.com       
 * @created 2018年8月13日 下午12:17:16
 */
public interface ReportTranstypeMapper {
	/**
	 * @discription 根据部门id和月份查询运输类型list
	 * @author yangyh@segimail.com       
	 * @created 2018年8月13日 下午12:18:07     
	 * @param organId
	 * @param cycleYm
	 * @return list
	 */
	public List<TranstypeStatistics> selectByOrganIdAndCycleYm(@Param("organId")Integer organId, @Param("cycleYm")Integer cycleYm);
	
	/**
	 * @discription 根据部门idList和月份查询运输类型list
	 * @author yangyh@segimail.com       
	 * @created 2018年8月13日 下午12:18:07     
	 * @param organId
	 * @param cycleYm
	 * @return list
	 */
	public List<TranstypeStatistics> selectByOrganIdListAndCycleYm(@Param("list")String organIdListStr, @Param("cycleYm")Integer cycleYm);

	/**
	 * @discription 根据部门id和月份list查询运输类型list
	 * @author yangyh@segimail.com       
	 * @created 2018年8月13日 下午12:18:07     
	 * @param organId
	 * @param cycleYm
	 * @return list
	 */
	public List<TranstypeStatistics> selectByOrganIdAndCycleYmList(@Param("organId")Integer organId, @Param("list")String cycleYmListStr);
	
	/**
	 * @discription 根据运输类型对象修改
	 * @author yangyh@segimail.com       
	 * @created 2018年8月13日 下午12:18:07     
	 * @param organId
	 * @param cycleYm
	 * @return list
	 */
	public Integer updateByTranstype(TranstypeStatistics transtypeStatistics);
	
	/**
	 * @discription 根据运输类型对象修改
	 * @author yangyh@segimail.com       
	 * @created 2018年8月13日 下午12:18:07     
	 * @param organId
	 * @param cycleYm
	 */
	public void saveByTranstype(TranstypeStatistics transtypeStatistics);
	
	/**
	 * @discription 批量修改运送类型运输量
	 * @author yangyh@segimail.com       
	 * @created 2018年8月7日 下午12:16:20     
	 * @param list
	 */
	public void updateBatchTranstype(@Param("list") List<TranstypeStatistics> list);
	
	/**
	 * @discription 批量新增运送类型运输量
	 * @author yangyh@segimail.com       
	 * @created 2018年8月7日 下午12:16:20     
	 * @param list
	 */
	public void saveBatchTranstype(@Param("list") List<TranstypeStatistics> list);

	/**
	 * @discription 根据部门id和月份删除
	 * @author yangyh@segimail.com       
	 * @created 2018年8月13日 下午12:18:07     
	 * @param organId
	 * @param cycleYm
	 */
	public void deleteByOrganIdAndCycleYm(@Param("organId")Integer organId, @Param("cycleYm")Integer cycleYm);
	
	/**
	 * @discription 根据部门idList和月份删除
	 * @author yangyh@segimail.com       
	 * @created 2018年8月13日 下午12:18:07     
	 * @param organId
	 * @param cycleYm
	 */
	public void deleteByOrganListAndCycleYm(@Param("organIdList")String organIdList, @Param("cycleYm")Integer cycleYm);
}
