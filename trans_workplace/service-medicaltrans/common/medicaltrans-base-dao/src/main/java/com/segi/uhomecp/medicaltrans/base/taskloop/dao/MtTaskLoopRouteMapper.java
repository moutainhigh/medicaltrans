package com.segi.uhomecp.medicaltrans.base.taskloop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoopRoute;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoopRouteCriteria;

public interface MtTaskLoopRouteMapper 
	extends AbstractMapperDao<MtTaskLoopRoute, MtTaskLoopRouteCriteria, Integer> {
    int countByExample(MtTaskLoopRouteCriteria example);

    int deleteByExample(MtTaskLoopRouteCriteria example);

    int deleteByPrimaryKey(Integer routeId);

    int insert(MtTaskLoopRoute record);

    int insertSelective(MtTaskLoopRoute record);

    PageList<MtTaskLoopRoute> selectByExampleWithRowbounds(MtTaskLoopRouteCriteria example, RowBounds rowBounds);

    List<MtTaskLoopRoute> selectByExample(MtTaskLoopRouteCriteria example);

    MtTaskLoopRoute selectByPrimaryKey(Integer routeId);

    int updateByExampleSelective(@Param("record") MtTaskLoopRoute record, @Param("example") MtTaskLoopRouteCriteria example);

    int updateByExample(@Param("record") MtTaskLoopRoute record, @Param("example") MtTaskLoopRouteCriteria example);

    int updateByPrimaryKeySelective(MtTaskLoopRoute record);

    int updateByPrimaryKey(MtTaskLoopRoute record);
}