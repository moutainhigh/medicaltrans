package com.segi.uhomecp.medicaltrans.base.transtype.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.base.transtype.model.MtTransTypeImpMid;
import com.segi.uhomecp.medicaltrans.base.transtype.model.MtTransTypeImpMidCriteria;

public interface MtTransTypeImpMidMapper extends AbstractMapperDao<MtTransTypeImpMid, MtTransTypeImpMidCriteria, Integer> {
    int countByExample(MtTransTypeImpMidCriteria example);

    int deleteByExample(MtTransTypeImpMidCriteria example);

    int deleteByPrimaryKey(Integer itemId);

    int insert(MtTransTypeImpMid record);

    int insertSelective(MtTransTypeImpMid record);

    PageList<MtTransTypeImpMid> selectByExampleWithRowbounds(MtTransTypeImpMidCriteria example, RowBounds rowBounds);

    List<MtTransTypeImpMid> selectByExample(MtTransTypeImpMidCriteria example);

    MtTransTypeImpMid selectByPrimaryKey(Integer itemId);

    int updateByExampleSelective(@Param("record") MtTransTypeImpMid record, @Param("example") MtTransTypeImpMidCriteria example);

    int updateByExample(@Param("record") MtTransTypeImpMid record, @Param("example") MtTransTypeImpMidCriteria example);

    int updateByPrimaryKeySelective(MtTransTypeImpMid record);

    int updateByPrimaryKey(MtTransTypeImpMid record);
}