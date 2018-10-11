package com.segi.uhomecp.medicaltrans.report.monthamount.transtype.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.report.monthamount.transtype.model.TranstypeStatistics;
import com.segi.uhomecp.medicaltrans.report.monthamount.transtype.model.TranstypeStatisticsCriteria;

public interface TranstypeStatisticsMapper extends AbstractMapperDao<TranstypeStatistics, TranstypeStatisticsCriteria, Integer> {
    int countByExample(TranstypeStatisticsCriteria example);

    int deleteByExample(TranstypeStatisticsCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(TranstypeStatistics record);

    int insertSelective(TranstypeStatistics record);

    PageList<TranstypeStatistics> selectByExampleWithRowbounds(TranstypeStatisticsCriteria example, RowBounds rowBounds);

    List<TranstypeStatistics> selectByExample(TranstypeStatisticsCriteria example);

    TranstypeStatistics selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TranstypeStatistics record, @Param("example") TranstypeStatisticsCriteria example);

    int updateByExample(@Param("record") TranstypeStatistics record, @Param("example") TranstypeStatisticsCriteria example);

    int updateByPrimaryKeySelective(TranstypeStatistics record);

    int updateByPrimaryKey(TranstypeStatistics record);
}