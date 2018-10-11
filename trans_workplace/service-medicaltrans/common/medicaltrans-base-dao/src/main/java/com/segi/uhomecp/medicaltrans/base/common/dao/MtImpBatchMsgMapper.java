package com.segi.uhomecp.medicaltrans.base.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.base.common.model.MtImpBatchMsg;
import com.segi.uhomecp.medicaltrans.base.common.model.MtImpBatchMsgCriteria;

public interface MtImpBatchMsgMapper extends AbstractMapperDao<MtImpBatchMsg, MtImpBatchMsgCriteria, Integer> {
    int countByExample(MtImpBatchMsgCriteria example);

    int deleteByExample(MtImpBatchMsgCriteria example);

    int deleteByPrimaryKey(Integer batchId);

    int insert(MtImpBatchMsg record);

    int insertSelective(MtImpBatchMsg record);

    PageList<MtImpBatchMsg> selectByExampleWithRowbounds(MtImpBatchMsgCriteria example, RowBounds rowBounds);

    List<MtImpBatchMsg> selectByExample(MtImpBatchMsgCriteria example);

    MtImpBatchMsg selectByPrimaryKey(Integer batchId);

    int updateByExampleSelective(@Param("record") MtImpBatchMsg record, @Param("example") MtImpBatchMsgCriteria example);

    int updateByExample(@Param("record") MtImpBatchMsg record, @Param("example") MtImpBatchMsgCriteria example);

    int updateByPrimaryKeySelective(MtImpBatchMsg record);

    int updateByPrimaryKey(MtImpBatchMsg record);
}