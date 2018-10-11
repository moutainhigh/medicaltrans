package com.segi.uhomecp.medicaltrans.reportjob.report.pervolmonth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonth;

/**
 * @ClassName:  PerVolMonthInfoMapper   
 * @Description:个人运送量月报表数据操作接口   
 * @author: zhaoqing
 * @date:   2018年8月3日 上午9:12:51
 */
public interface PerVolMonthInfoMapper {
	
	/**
	 * @Title: savePerVolMonthIbatch   
	 *  批量保存个人运送量月报表统计数据 
	 * @author zhaoqing  
	 * @param list      
	 * void   
	 */
	public void savePerVolMonthIbatch(@Param("list") List<PersonalVolumeMonth> list); 

}
