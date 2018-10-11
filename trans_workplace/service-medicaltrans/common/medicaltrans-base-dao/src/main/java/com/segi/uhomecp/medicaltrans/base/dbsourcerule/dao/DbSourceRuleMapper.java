package com.segi.uhomecp.medicaltrans.base.dbsourcerule.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.base.dbsourcerule.model.DbSourceRule;
import com.segi.uhomecp.medicaltrans.base.dbsourcerule.model.DbSourceRuleCriteria;

public interface DbSourceRuleMapper extends AbstractMapperDao<DbSourceRule, DbSourceRuleCriteria, Integer> {
    int countByExample(DbSourceRuleCriteria example);

    int deleteByExample(DbSourceRuleCriteria example);

    int deleteByPrimaryKey(Integer idxId);

    int insert(DbSourceRule record);

    int insertSelective(DbSourceRule record);

    PageList<DbSourceRule> selectByExampleWithRowbounds(DbSourceRuleCriteria example, RowBounds rowBounds);

    List<DbSourceRule> selectByExample(DbSourceRuleCriteria example);

    DbSourceRule selectByPrimaryKey(Integer idxId);

    int updateByExampleSelective(@Param("record") DbSourceRule record, @Param("example") DbSourceRuleCriteria example);

    int updateByExample(@Param("record") DbSourceRule record, @Param("example") DbSourceRuleCriteria example);

    int updateByPrimaryKeySelective(DbSourceRule record);

    int updateByPrimaryKey(DbSourceRule record);
}