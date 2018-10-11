package com.segi.uhomecp.medicaltrans.base.location.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocation;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocationCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface MtBuildLocationMapper extends AbstractMapperDao<MtBuildLocation,MtBuildLocationCriteria,Integer> {
    int countByExample(MtBuildLocationCriteria example);

    int deleteByExample(MtBuildLocationCriteria example);

    int deleteByPrimaryKey(Integer locationId);

    int insert(MtBuildLocation record);

    int insertSelective(MtBuildLocation record);

    PageList<MtBuildLocation> selectByExampleWithRowbounds(MtBuildLocationCriteria example, RowBounds rowBounds);

    List<MtBuildLocation> selectByExample(MtBuildLocationCriteria example);

    MtBuildLocation selectByPrimaryKey(Integer locationId);

    int updateByExampleSelective(@Param("record") MtBuildLocation record, @Param("example") MtBuildLocationCriteria example);

    int updateByExample(@Param("record") MtBuildLocation record, @Param("example") MtBuildLocationCriteria example);

    int updateByPrimaryKeySelective(MtBuildLocation record);

    int updateByPrimaryKey(MtBuildLocation record);
}