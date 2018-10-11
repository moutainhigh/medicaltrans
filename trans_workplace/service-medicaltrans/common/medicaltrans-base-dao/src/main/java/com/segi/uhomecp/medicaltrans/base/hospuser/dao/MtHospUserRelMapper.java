package com.segi.uhomecp.medicaltrans.base.hospuser.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.common.model.AbstractModel;
import com.segi.uhomecp.medicaltrans.base.hospuser.model.MtHospUserRel;
import com.segi.uhomecp.medicaltrans.base.hospuser.model.MtHospUserRelCriteria;

public interface MtHospUserRelMapper extends AbstractMapperDao<MtHospUserRel, MtHospUserRelCriteria, Integer> {
    int countByExample(MtHospUserRelCriteria example);

    int deleteByExample(MtHospUserRelCriteria example);

    int deleteByPrimaryKey(Integer mtUserRelId);

    int insert(MtHospUserRel record);

    int insertSelective(MtHospUserRel record);

    PageList<MtHospUserRel> selectByExampleWithRowbounds(MtHospUserRelCriteria example, RowBounds rowBounds);

    List<MtHospUserRel> selectByExample(MtHospUserRelCriteria example);

    MtHospUserRel selectByPrimaryKey(Integer mtUserRelId);

    int updateByExampleSelective(@Param("record") MtHospUserRel record, @Param("example") MtHospUserRelCriteria example);

    int updateByExample(@Param("record") MtHospUserRel record, @Param("example") MtHospUserRelCriteria example);

    int updateByPrimaryKeySelective(MtHospUserRel record);

    int updateByPrimaryKey(MtHospUserRel record);
}