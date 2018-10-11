package com.segi.uhomecp.medicaltrans.base.taskloop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoop;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoopCriteria;

public interface MtTaskLoopMapper extends AbstractMapperDao<MtTaskLoop, MtTaskLoopCriteria, Integer> {
    int countByExample(MtTaskLoopCriteria example);

    int deleteByExample(MtTaskLoopCriteria example);

    int deleteByPrimaryKey(Integer taskLoopId);

    int insert(MtTaskLoop record);

    int insertSelective(MtTaskLoop record);

    PageList<MtTaskLoop> selectByExampleWithRowbounds(MtTaskLoopCriteria example, RowBounds rowBounds);

    List<MtTaskLoop> selectByExample(MtTaskLoopCriteria example);

    MtTaskLoop selectByPrimaryKey(Integer taskLoopId);

    int updateByExampleSelective(@Param("record") MtTaskLoop record, @Param("example") MtTaskLoopCriteria example);

    int updateByExample(@Param("record") MtTaskLoop record, @Param("example") MtTaskLoopCriteria example);

    int updateByPrimaryKeySelective(MtTaskLoop record);

    int updateByPrimaryKey(MtTaskLoop record);
}