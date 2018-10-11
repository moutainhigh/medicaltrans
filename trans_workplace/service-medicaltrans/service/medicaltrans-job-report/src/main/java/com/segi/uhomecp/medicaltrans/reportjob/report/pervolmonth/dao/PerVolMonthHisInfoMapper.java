package com.segi.uhomecp.medicaltrans.reportjob.report.pervolmonth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonthHis;

/**
 * @ClassName:  PerVolMonthHisInfoMapper   
 * @Description:个人运送量月报表历史表数据操作接口   
 * @author: zhaoqing
 * @date:   2018年8月3日 上午9:15:06
 */
public interface PerVolMonthHisInfoMapper {
    
	/**
	 * @Title: savePerVolMonthHisIbatch   
	 *  批量保存个人运送量月报表统计数据 
	 * @author zhaoqing  
	 * @param list      
	 * void   
	 */
	public void savePerVolMonthHisIbatch(@Param("list") List<PersonalVolumeMonthHis> list);
	
}
