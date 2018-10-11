package com.segi.uhomecp.medicaltrans.trans.mttask.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskGroupRel;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskGroupRelCriteria;

public interface MtTaskGroupRelMapper extends AbstractMapperDao<MtTaskGroupRel, MtTaskGroupRelCriteria, Integer> {
    int countByExample(MtTaskGroupRelCriteria example);

    int deleteByExample(MtTaskGroupRelCriteria example);

    int deleteByPrimaryKey(Integer taskGroupId);

    int insert(MtTaskGroupRel record);

    int insertSelective(MtTaskGroupRel record);

    PageList<MtTaskGroupRel> selectByExampleWithRowbounds(MtTaskGroupRelCriteria example, RowBounds rowBounds);

    List<MtTaskGroupRel> selectByExample(MtTaskGroupRelCriteria example);

    MtTaskGroupRel selectByPrimaryKey(Integer taskGroupId);

    int updateByExampleSelective(@Param("record") MtTaskGroupRel record, @Param("example") MtTaskGroupRelCriteria example);

    int updateByExample(@Param("record") MtTaskGroupRel record, @Param("example") MtTaskGroupRelCriteria example);

    int updateByPrimaryKeySelective(MtTaskGroupRel record);

    int updateByPrimaryKey(MtTaskGroupRel record);
}