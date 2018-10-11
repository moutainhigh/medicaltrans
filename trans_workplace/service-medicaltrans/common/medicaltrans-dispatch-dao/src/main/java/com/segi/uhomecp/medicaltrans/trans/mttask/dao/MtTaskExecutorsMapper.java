package com.segi.uhomecp.medicaltrans.trans.mttask.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutors;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutorsCriteria;

public interface MtTaskExecutorsMapper extends AbstractMapperDao<MtTaskExecutors, MtTaskExecutorsCriteria, Integer> {
    int countByExample(MtTaskExecutorsCriteria example);

    int deleteByExample(MtTaskExecutorsCriteria example);

    int deleteByPrimaryKey(Integer taskExeId);

    int insert(MtTaskExecutors record);

    int insertSelective(MtTaskExecutors record);

    PageList<MtTaskExecutors> selectByExampleWithRowbounds(MtTaskExecutorsCriteria example, RowBounds rowBounds);

    List<MtTaskExecutors> selectByExample(MtTaskExecutorsCriteria example);

    MtTaskExecutors selectByPrimaryKey(Integer taskExeId);

    int updateByExampleSelective(@Param("record") MtTaskExecutors record, @Param("example") MtTaskExecutorsCriteria example);

    int updateByExample(@Param("record") MtTaskExecutors record, @Param("example") MtTaskExecutorsCriteria example);

    int updateByPrimaryKeySelective(MtTaskExecutors record);

    int updateByPrimaryKey(MtTaskExecutors record);
}