package com.segi.uhomecp.medicaltrans.base.taskloop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoopExecutors;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoopExecutorsCriteria;

public interface MtTaskLoopExecutorsMapper 
	extends AbstractMapperDao<MtTaskLoopExecutors, MtTaskLoopExecutorsCriteria, Integer> {
    int countByExample(MtTaskLoopExecutorsCriteria example);

    int deleteByExample(MtTaskLoopExecutorsCriteria example);

    int deleteByPrimaryKey(Integer executorId);

    int insert(MtTaskLoopExecutors record);

    int insertSelective(MtTaskLoopExecutors record);

    PageList<MtTaskLoopExecutors> selectByExampleWithRowbounds(MtTaskLoopExecutorsCriteria example, RowBounds rowBounds);

    List<MtTaskLoopExecutors> selectByExample(MtTaskLoopExecutorsCriteria example);

    MtTaskLoopExecutors selectByPrimaryKey(Integer executorId);

    int updateByExampleSelective(@Param("record") MtTaskLoopExecutors record, @Param("example") MtTaskLoopExecutorsCriteria example);

    int updateByExample(@Param("record") MtTaskLoopExecutors record, @Param("example") MtTaskLoopExecutorsCriteria example);

    int updateByPrimaryKeySelective(MtTaskLoopExecutors record);

    int updateByPrimaryKey(MtTaskLoopExecutors record);
}