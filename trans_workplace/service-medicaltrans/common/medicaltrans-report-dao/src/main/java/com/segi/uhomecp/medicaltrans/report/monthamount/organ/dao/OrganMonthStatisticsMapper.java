package com.segi.uhomecp.medicaltrans.report.monthamount.organ.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.report.monthamount.organ.model.OrganMonthStatistics;
import com.segi.uhomecp.medicaltrans.report.monthamount.organ.model.OrganMonthStatisticsCriteria;

public interface OrganMonthStatisticsMapper
		extends
			AbstractMapperDao<OrganMonthStatistics, OrganMonthStatisticsCriteria, Integer> {
	int countByExample(OrganMonthStatisticsCriteria example);

	int deleteByExample(OrganMonthStatisticsCriteria example);

	int deleteByPrimaryKey(Integer id);

	int insert(OrganMonthStatistics record);

	int insertSelective(OrganMonthStatistics record);

	PageList<OrganMonthStatistics> selectByExampleWithRowbounds(OrganMonthStatisticsCriteria example,
			RowBounds rowBounds);

	List<OrganMonthStatistics> selectByExample(OrganMonthStatisticsCriteria example);

	OrganMonthStatistics selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") OrganMonthStatistics record,
			@Param("example") OrganMonthStatisticsCriteria example);

	int updateByExample(@Param("record") OrganMonthStatistics record,
			@Param("example") OrganMonthStatisticsCriteria example);

	int updateByPrimaryKeySelective(OrganMonthStatistics record);

	int updateByPrimaryKey(OrganMonthStatistics record);
}