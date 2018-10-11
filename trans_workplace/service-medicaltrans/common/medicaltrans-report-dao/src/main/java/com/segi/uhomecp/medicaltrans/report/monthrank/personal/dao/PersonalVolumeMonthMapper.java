package com.segi.uhomecp.medicaltrans.report.monthrank.personal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonth;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonthCriteria;

public interface PersonalVolumeMonthMapper extends 
	AbstractMapperDao<PersonalVolumeMonth, PersonalVolumeMonthCriteria, Integer> {
    int countByExample(PersonalVolumeMonthCriteria example);

    int deleteByExample(PersonalVolumeMonthCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(PersonalVolumeMonth record);

    int insertSelective(PersonalVolumeMonth record);

    PageList<PersonalVolumeMonth> selectByExampleWithRowbounds(PersonalVolumeMonthCriteria example, RowBounds rowBounds);

    List<PersonalVolumeMonth> selectByExample(PersonalVolumeMonthCriteria example);

    PersonalVolumeMonth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PersonalVolumeMonth record, @Param("example") PersonalVolumeMonthCriteria example);

    int updateByExample(@Param("record") PersonalVolumeMonth record, @Param("example") PersonalVolumeMonthCriteria example);

    int updateByPrimaryKeySelective(PersonalVolumeMonth record);

    int updateByPrimaryKey(PersonalVolumeMonth record);
}