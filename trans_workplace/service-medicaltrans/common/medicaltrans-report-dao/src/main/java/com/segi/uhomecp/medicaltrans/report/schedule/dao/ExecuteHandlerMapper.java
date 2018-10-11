package com.segi.uhomecp.medicaltrans.report.schedule.dao;

import com.segi.uhomecp.common.dao.AbstractMapperDao;
import com.segi.uhomecp.medicaltrans.report.schedule.model.ExecuteHandler;
import com.segi.uhomecp.medicaltrans.report.schedule.model.ExecuteHandlerCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ExecuteHandlerMapper extends AbstractMapperDao {
    int countByExample(ExecuteHandlerCriteria example);

    int deleteByExample(ExecuteHandlerCriteria example);

    int deleteByPrimaryKey(Integer handlerId);

    int insert(ExecuteHandler record);

    int insertSelective(ExecuteHandler record);

    List<ExecuteHandler> selectByExampleWithRowbounds(ExecuteHandlerCriteria example, RowBounds rowBounds);

    List<ExecuteHandler> selectByExample(ExecuteHandlerCriteria example);

    ExecuteHandler selectByPrimaryKey(Integer handlerId);

    int updateByExampleSelective(@Param("record") ExecuteHandler record, @Param("example") ExecuteHandlerCriteria example);

    int updateByExample(@Param("record") ExecuteHandler record, @Param("example") ExecuteHandlerCriteria example);

    int updateByPrimaryKeySelective(ExecuteHandler record);

    int updateByPrimaryKey(ExecuteHandler record);
}