package com.segi.uhomecp.medicaltrans.base.location.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocationRel;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocationRelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface MtBuildLocationRelMapper extends AbstractMapperDao<MtBuildLocationRel,MtBuildLocationRelCriteria,Integer> {
    int countByExample(MtBuildLocationRelCriteria example);

    int deleteByExample(MtBuildLocationRelCriteria example);

    int deleteByPrimaryKey(Integer locationId);

    int insert(MtBuildLocationRel record);

    int insertSelective(MtBuildLocationRel record);

    PageList<MtBuildLocationRel> selectByExampleWithRowbounds(MtBuildLocationRelCriteria example, RowBounds rowBounds);

    List<MtBuildLocationRel> selectByExample(MtBuildLocationRelCriteria example);

    MtBuildLocationRel selectByPrimaryKey(Integer locationId);

    int updateByExampleSelective(@Param("record") MtBuildLocationRel record, @Param("example") MtBuildLocationRelCriteria example);

    int updateByExample(@Param("record") MtBuildLocationRel record, @Param("example") MtBuildLocationRelCriteria example);

    int updateByPrimaryKeySelective(MtBuildLocationRel record);

    int updateByPrimaryKey(MtBuildLocationRel record);
}