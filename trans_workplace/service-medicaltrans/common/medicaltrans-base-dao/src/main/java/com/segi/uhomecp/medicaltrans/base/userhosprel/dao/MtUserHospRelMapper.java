package com.segi.uhomecp.medicaltrans.base.userhosprel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.base.userhosprel.model.MtUserHospRel;
import com.segi.uhomecp.medicaltrans.base.userhosprel.model.MtUserHospRelCriteria;

public interface MtUserHospRelMapper extends AbstractMapperDao<MtUserHospRel, MtUserHospRelCriteria, Integer> {
    int countByExample(MtUserHospRelCriteria example);

    int deleteByExample(MtUserHospRelCriteria example);

    int deleteByPrimaryKey(Integer userHospRelId);

    int insert(MtUserHospRel record);

    int insertSelective(MtUserHospRel record);

    PageList<MtUserHospRel> selectByExampleWithRowbounds(MtUserHospRelCriteria example, RowBounds rowBounds);

    List<MtUserHospRel> selectByExample(MtUserHospRelCriteria example);

    MtUserHospRel selectByPrimaryKey(Integer userHospRelId);

    int updateByExampleSelective(@Param("record") MtUserHospRel record, @Param("example") MtUserHospRelCriteria example);

    int updateByExample(@Param("record") MtUserHospRel record, @Param("example") MtUserHospRelCriteria example);

    int updateByPrimaryKeySelective(MtUserHospRel record);

    int updateByPrimaryKey(MtUserHospRel record);
}