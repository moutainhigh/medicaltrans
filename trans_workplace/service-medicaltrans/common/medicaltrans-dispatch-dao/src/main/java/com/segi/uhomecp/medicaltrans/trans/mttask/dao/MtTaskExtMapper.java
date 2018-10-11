package com.segi.uhomecp.medicaltrans.trans.mttask.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExt;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExtCriteria;

public interface MtTaskExtMapper extends AbstractMapperDao<MtTaskExt, MtTaskExtCriteria, Integer> {
    int countByExample(MtTaskExtCriteria example);

    int deleteByExample(MtTaskExtCriteria example);

    int deleteByPrimaryKey(Integer taskExtId);

    int insert(MtTaskExt record);

    int insertSelective(MtTaskExt record);

    PageList<MtTaskExt> selectByExampleWithRowbounds(MtTaskExtCriteria example, RowBounds rowBounds);

    List<MtTaskExt> selectByExample(MtTaskExtCriteria example);

    MtTaskExt selectByPrimaryKey(Integer taskExtId);

    int updateByExampleSelective(@Param("record") MtTaskExt record, @Param("example") MtTaskExtCriteria example);

    int updateByExample(@Param("record") MtTaskExt record, @Param("example") MtTaskExtCriteria example);

    int updateByPrimaryKeySelective(MtTaskExt record);

    int updateByPrimaryKey(MtTaskExt record);
}