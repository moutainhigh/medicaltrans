package com.segi.uhomecp.medicaltrans.report.monthamount.organ.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.report.monthamount.organ.model.OrganWeekVolume;
import com.segi.uhomecp.medicaltrans.report.monthamount.organ.model.OrganWeekVolumeCriteria;

public interface OrganWeekVolumeMapper extends
		AbstractMapperDao<OrganWeekVolume, OrganWeekVolumeCriteria, Integer> {
    int countByExample(OrganWeekVolumeCriteria example);

    int deleteByExample(OrganWeekVolumeCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrganWeekVolume record);

    int insertSelective(OrganWeekVolume record);

    PageList<OrganWeekVolume> selectByExampleWithRowbounds(OrganWeekVolumeCriteria example, RowBounds rowBounds);

    List<OrganWeekVolume> selectByExample(OrganWeekVolumeCriteria example);

    OrganWeekVolume selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrganWeekVolume record, @Param("example") OrganWeekVolumeCriteria example);

    int updateByExample(@Param("record") OrganWeekVolume record, @Param("example") OrganWeekVolumeCriteria example);

    int updateByPrimaryKeySelective(OrganWeekVolume record);

    int updateByPrimaryKey(OrganWeekVolume record);
}