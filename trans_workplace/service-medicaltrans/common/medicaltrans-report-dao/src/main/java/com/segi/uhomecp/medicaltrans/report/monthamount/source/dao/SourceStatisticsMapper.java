package com.segi.uhomecp.medicaltrans.report.monthamount.source.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.report.monthamount.source.model.SourceStatistics;
import com.segi.uhomecp.medicaltrans.report.monthamount.source.model.SourceStatisticsCriteria;

public interface SourceStatisticsMapper extends AbstractMapperDao<SourceStatistics, SourceStatisticsCriteria, Integer> {
    int countByExample(SourceStatisticsCriteria example);

    int deleteByExample(SourceStatisticsCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(SourceStatistics record);

    int insertSelective(SourceStatistics record);

    PageList<SourceStatistics> selectByExampleWithRowbounds(SourceStatisticsCriteria example, RowBounds rowBounds);

    List<SourceStatistics> selectByExample(SourceStatisticsCriteria example);

    SourceStatistics selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SourceStatistics record, @Param("example") SourceStatisticsCriteria example);

    int updateByExample(@Param("record") SourceStatistics record, @Param("example") SourceStatisticsCriteria example);

    int updateByPrimaryKeySelective(SourceStatistics record);

    int updateByPrimaryKey(SourceStatistics record);
}