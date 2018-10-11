package com.segi.uhomecp.medicaltrans.report.schedule.dao;

import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.report.schedule.model.ScheduleLog;
import com.segi.uhomecp.medicaltrans.report.schedule.model.ScheduleLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ScheduleLogMapper extends AbstractMapperDao {
    int countByExample(ScheduleLogCriteria example);

    int deleteByExample(ScheduleLogCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(ScheduleLog record);

    int insertSelective(ScheduleLog record);

    List<ScheduleLog> selectByExampleWithRowbounds(ScheduleLogCriteria example, RowBounds rowBounds);

    List<ScheduleLog> selectByExample(ScheduleLogCriteria example);

    ScheduleLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScheduleLog record, @Param("example") ScheduleLogCriteria example);

    int updateByExample(@Param("record") ScheduleLog record, @Param("example") ScheduleLogCriteria example);

    int updateByPrimaryKeySelective(ScheduleLog record);

    int updateByPrimaryKey(ScheduleLog record);
}