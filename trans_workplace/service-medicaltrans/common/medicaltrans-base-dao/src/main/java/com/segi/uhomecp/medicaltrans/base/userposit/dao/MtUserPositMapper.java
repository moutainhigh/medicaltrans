package com.segi.uhomecp.medicaltrans.base.userposit.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.base.userposit.model.MtUserPosit;
import com.segi.uhomecp.medicaltrans.base.userposit.model.MtUserPositCriteria;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface MtUserPositMapper extends AbstractMapperDao<MtUserPosit, MtUserPositCriteria, Integer> {
    int countByExample(MtUserPositCriteria example);

    int deleteByExample(MtUserPositCriteria example);

    int deleteByPrimaryKey(Integer userPositId);

    int insert(MtUserPosit record);

    int insertSelective(MtUserPosit record);

    PageList<MtUserPosit> selectByExampleWithRowbounds(MtUserPositCriteria example, RowBounds rowBounds);

    List<MtUserPosit> selectByExample(MtUserPositCriteria example);

    MtUserPosit selectByPrimaryKey(Integer userPositId);

    int updateByExampleSelective(@Param("record") MtUserPosit record, @Param("example") MtUserPositCriteria example);

    int updateByExample(@Param("record") MtUserPosit record, @Param("example") MtUserPositCriteria example);

    int updateByPrimaryKeySelective(MtUserPosit record);

    int updateByPrimaryKey(MtUserPosit record);
}