package com.segi.uhomecp.medicaltrans.report.schedule.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.report.schedule.model.TransSchedule;
import com.segi.uhomecp.medicaltrans.report.schedule.model.TransScheduleCriteria;

public interface TransScheduleMapper extends AbstractMapperDao<TransSchedule, TransScheduleCriteria, Integer> {
    int countByExample(TransScheduleCriteria example);

    int deleteByExample(TransScheduleCriteria example);

    int deleteByPrimaryKey(Integer organId);

    int insert(TransSchedule record);

    int insertSelective(TransSchedule record);

    PageList<TransSchedule> selectByExampleWithRowbounds(TransScheduleCriteria example, RowBounds rowBounds);

    List<TransSchedule> selectByExample(TransScheduleCriteria example);

    TransSchedule selectByPrimaryKey(Integer organId);

    int updateByExampleSelective(@Param("record") TransSchedule record, @Param("example") TransScheduleCriteria example);

    int updateByExample(@Param("record") TransSchedule record, @Param("example") TransScheduleCriteria example);

    int updateByPrimaryKeySelective(TransSchedule record);

    int updateByPrimaryKey(TransSchedule record);
}