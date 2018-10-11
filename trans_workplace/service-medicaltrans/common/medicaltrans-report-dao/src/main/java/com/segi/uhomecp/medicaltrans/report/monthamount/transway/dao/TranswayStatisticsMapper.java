package com.segi.uhomecp.medicaltrans.report.monthamount.transway.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.report.monthamount.transway.model.TranswayStatistics;
import com.segi.uhomecp.medicaltrans.report.monthamount.transway.model.TranswayStatisticsCriteria;

public interface TranswayStatisticsMapper extends AbstractMapperDao<TranswayStatistics, TranswayStatisticsCriteria, Integer> {
    int countByExample(TranswayStatisticsCriteria example);

    int deleteByExample(TranswayStatisticsCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(TranswayStatistics record);

    int insertSelective(TranswayStatistics record);

    PageList<TranswayStatistics> selectByExampleWithRowbounds(TranswayStatisticsCriteria example, RowBounds rowBounds);

    List<TranswayStatistics> selectByExample(TranswayStatisticsCriteria example);

    TranswayStatistics selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TranswayStatistics record, @Param("example") TranswayStatisticsCriteria example);

    int updateByExample(@Param("record") TranswayStatistics record, @Param("example") TranswayStatisticsCriteria example);

    int updateByPrimaryKeySelective(TranswayStatistics record);

    int updateByPrimaryKey(TranswayStatistics record);
}