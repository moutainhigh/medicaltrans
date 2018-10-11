package com.segi.uhomecp.medicaltrans.report.monthrank.personal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonthHis;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonthHisCriteria;

public interface PersonalVolumeMonthHisMapper extends 
	AbstractMapperDao<PersonalVolumeMonthHis, PersonalVolumeMonthHisCriteria, Integer> {
    int countByExample(PersonalVolumeMonthHisCriteria example);

    int deleteByExample(PersonalVolumeMonthHisCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(PersonalVolumeMonthHis record);

    int insertSelective(PersonalVolumeMonthHis record);

    PageList<PersonalVolumeMonthHis> selectByExampleWithRowbounds(PersonalVolumeMonthHisCriteria example, RowBounds rowBounds);

    List<PersonalVolumeMonthHis> selectByExample(PersonalVolumeMonthHisCriteria example);

    PersonalVolumeMonthHis selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PersonalVolumeMonthHis record, @Param("example") PersonalVolumeMonthHisCriteria example);

    int updateByExample(@Param("record") PersonalVolumeMonthHis record, @Param("example") PersonalVolumeMonthHisCriteria example);

    int updateByPrimaryKeySelective(PersonalVolumeMonthHis record);

    int updateByPrimaryKey(PersonalVolumeMonthHis record);
}