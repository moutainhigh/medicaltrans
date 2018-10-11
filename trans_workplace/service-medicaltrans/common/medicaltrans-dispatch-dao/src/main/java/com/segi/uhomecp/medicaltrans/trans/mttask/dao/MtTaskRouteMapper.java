package com.segi.uhomecp.medicaltrans.trans.mttask.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRoute;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRouteCriteria;

public interface MtTaskRouteMapper extends AbstractMapperDao<MtTaskRoute, MtTaskRouteCriteria, Integer> {
    int countByExample(MtTaskRouteCriteria example);

    int deleteByExample(MtTaskRouteCriteria example);

    int deleteByPrimaryKey(Integer routeId);

    int insert(MtTaskRoute record);

    int insertSelective(MtTaskRoute record);

    PageList<MtTaskRoute> selectByExampleWithRowbounds(MtTaskRouteCriteria example, RowBounds rowBounds);

    List<MtTaskRoute> selectByExample(MtTaskRouteCriteria example);

    MtTaskRoute selectByPrimaryKey(Integer routeId);

    int updateByExampleSelective(@Param("record") MtTaskRoute record, @Param("example") MtTaskRouteCriteria example);

    int updateByExample(@Param("record") MtTaskRoute record, @Param("example") MtTaskRouteCriteria example);

    int updateByPrimaryKeySelective(MtTaskRoute record);

    int updateByPrimaryKey(MtTaskRoute record);
}