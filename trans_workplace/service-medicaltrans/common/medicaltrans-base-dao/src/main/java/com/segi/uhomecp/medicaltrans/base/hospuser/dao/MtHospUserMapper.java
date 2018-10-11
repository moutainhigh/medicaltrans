package com.segi.uhomecp.medicaltrans.base.hospuser.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.base.hospuser.model.MtHospUser;
import com.segi.uhomecp.medicaltrans.base.hospuser.model.MtHospUserCriteria;

public interface MtHospUserMapper extends AbstractMapperDao<MtHospUser, MtHospUserCriteria, Integer> {
    int countByExample(MtHospUserCriteria example);

    int deleteByExample(MtHospUserCriteria example);

    int deleteByPrimaryKey(Integer mtUserId);

    int insert(MtHospUser record);

    int insertSelective(MtHospUser record);

    PageList<MtHospUser> selectByExampleWithRowbounds(MtHospUserCriteria example, RowBounds rowBounds);

    List<MtHospUser> selectByExample(MtHospUserCriteria example);

    MtHospUser selectByPrimaryKey(Integer mtUserId);

    int updateByExampleSelective(@Param("record") MtHospUser record, @Param("example") MtHospUserCriteria example);

    int updateByExample(@Param("record") MtHospUser record, @Param("example") MtHospUserCriteria example);

    int updateByPrimaryKeySelective(MtHospUser record);

    int updateByPrimaryKey(MtHospUser record);
}