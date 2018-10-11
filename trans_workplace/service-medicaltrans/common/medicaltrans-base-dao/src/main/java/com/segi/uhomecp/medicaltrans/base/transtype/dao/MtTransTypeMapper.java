package com.segi.uhomecp.medicaltrans.base.transtype.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.base.transtype.model.MtTransType;
import com.segi.uhomecp.medicaltrans.base.transtype.model.MtTransTypeCriteria;

public interface MtTransTypeMapper extends AbstractMapperDao<MtTransType, MtTransTypeCriteria, Integer> {
    int countByExample(MtTransTypeCriteria example);

    int deleteByExample(MtTransTypeCriteria example);

    int deleteByPrimaryKey(Integer transTypeId);

    int insert(MtTransType record);

    int insertSelective(MtTransType record);

    PageList<MtTransType> selectByExampleWithRowbounds(MtTransTypeCriteria example, RowBounds rowBounds);

    List<MtTransType> selectByExample(MtTransTypeCriteria example);

    MtTransType selectByPrimaryKey(Integer transTypeId);

    int updateByExampleSelective(@Param("record") MtTransType record, @Param("example") MtTransTypeCriteria example);

    int updateByExample(@Param("record") MtTransType record, @Param("example") MtTransTypeCriteria example);

    int updateByPrimaryKeySelective(MtTransType record);

    int updateByPrimaryKey(MtTransType record);
}