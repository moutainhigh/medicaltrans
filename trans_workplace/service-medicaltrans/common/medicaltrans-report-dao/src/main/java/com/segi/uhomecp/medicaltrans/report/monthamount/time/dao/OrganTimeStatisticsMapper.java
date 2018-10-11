package com.segi.uhomecp.medicaltrans.report.monthamount.time.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.report.monthamount.time.model.OrganTimeStatistics;
import com.segi.uhomecp.medicaltrans.report.monthamount.time.model.OrganTimeStatisticsCriteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OrganTimeStatisticsMapper extends AbstractMapperDao<OrganTimeStatistics, OrganTimeStatisticsCriteria, Integer> {
    int countByExample(OrganTimeStatisticsCriteria example);

    int deleteByExample(OrganTimeStatisticsCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrganTimeStatistics record);

    int insertSelective(OrganTimeStatistics record);

    PageList<OrganTimeStatistics> selectByExampleWithRowbounds(OrganTimeStatisticsCriteria example, RowBounds rowBounds);

    List<OrganTimeStatistics> selectByExample(OrganTimeStatisticsCriteria example);

    OrganTimeStatistics selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrganTimeStatistics record, @Param("example") OrganTimeStatisticsCriteria example);

    int updateByExample(@Param("record") OrganTimeStatistics record, @Param("example") OrganTimeStatisticsCriteria example);

    int updateByPrimaryKeySelective(OrganTimeStatistics record);

    int updateByPrimaryKey(OrganTimeStatistics record);
}