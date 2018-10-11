package com.segi.uhomecp.medicaltrans.trans.mttask.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskCriteria;

public interface MtTaskMapper extends AbstractMapperDao<MtTask, MtTaskCriteria, Integer> {
    int countByExample(MtTaskCriteria example);

    int deleteByExample(MtTaskCriteria example);

    int deleteByPrimaryKey(Integer taskId);

    int insert(MtTask record);

    int insertSelective(MtTask record);

    PageList<MtTask> selectByExampleWithRowbounds(MtTaskCriteria example, RowBounds rowBounds);

    List<MtTask> selectByExample(MtTaskCriteria example);

    MtTask selectByPrimaryKey(Integer taskId);

    int updateByExampleSelective(@Param("record") MtTask record, @Param("example") MtTaskCriteria example);

    int updateByExample(@Param("record") MtTask record, @Param("example") MtTaskCriteria example);

    int updateByPrimaryKeySelective(MtTask record);

    int updateByPrimaryKey(MtTask record);
}