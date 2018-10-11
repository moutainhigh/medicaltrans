package com.segi.uhomecp.medicaltrans.report.monthrank.dept.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.model.DeptVolumeMonth;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.model.DeptVolumeMonthCriteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface DeptVolumeMonthMapper extends AbstractMapperDao<DeptVolumeMonth, DeptVolumeMonthCriteria, Integer> {
    int countByExample(DeptVolumeMonthCriteria example);

    int deleteByExample(DeptVolumeMonthCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeptVolumeMonth record);

    int insertSelective(DeptVolumeMonth record);

    PageList<DeptVolumeMonth> selectByExampleWithRowbounds(DeptVolumeMonthCriteria example, RowBounds rowBounds);

    List<DeptVolumeMonth> selectByExample(DeptVolumeMonthCriteria example);

    DeptVolumeMonth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeptVolumeMonth record, @Param("example") DeptVolumeMonthCriteria example);

    int updateByExample(@Param("record") DeptVolumeMonth record, @Param("example") DeptVolumeMonthCriteria example);

    int updateByPrimaryKeySelective(DeptVolumeMonth record);

    int updateByPrimaryKey(DeptVolumeMonth record);
}