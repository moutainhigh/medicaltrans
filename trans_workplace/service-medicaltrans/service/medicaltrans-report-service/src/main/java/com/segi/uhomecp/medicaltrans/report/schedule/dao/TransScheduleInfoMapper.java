/**
 * 
 */
package com.segi.uhomecp.medicaltrans.report.schedule.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.segi.uhomecp.medicaltrans.report.schedule.model.TransSchedule;
  
/** 
 * Title: TransScheduleInfoMapper.java    
 * @Description: 描述
 * @author yangyh@segimail.com       
 * @created 2018年5月18日 上午11:30:54    
 */
public interface TransScheduleInfoMapper {

	public void saveBatchTransSchedule(@Param("list")List<TransSchedule> transScheduleList);
}
