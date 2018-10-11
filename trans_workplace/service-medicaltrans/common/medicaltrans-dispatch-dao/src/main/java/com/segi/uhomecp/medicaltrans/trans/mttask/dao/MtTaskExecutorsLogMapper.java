package com.segi.uhomecp.medicaltrans.trans.mttask.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutorsLog;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutorsLogCriteria;

public interface MtTaskExecutorsLogMapper extends AbstractMapperDao<MtTaskExecutorsLog, 
	MtTaskExecutorsLogCriteria, Integer> {
    int countByExample(MtTaskExecutorsLogCriteria example);

    int deleteByExample(MtTaskExecutorsLogCriteria example);

    int deleteByPrimaryKey(Integer taskExeId);

    int insert(MtTaskExecutorsLog record);

    int insertSelective(MtTaskExecutorsLog record);

    PageList<MtTaskExecutorsLog> selectByExampleWithRowbounds(MtTaskExecutorsLogCriteria example, RowBounds rowBounds);

    List<MtTaskExecutorsLog> selectByExample(MtTaskExecutorsLogCriteria example);

    MtTaskExecutorsLog selectByPrimaryKey(Integer taskExeId);

    int updateByExampleSelective(@Param("record") MtTaskExecutorsLog record, @Param("example") MtTaskExecutorsLogCriteria example);

    int updateByExample(@Param("record") MtTaskExecutorsLog record, @Param("example") MtTaskExecutorsLogCriteria example);

    int updateByPrimaryKeySelective(MtTaskExecutorsLog record);

    int updateByPrimaryKey(MtTaskExecutorsLog record);
}