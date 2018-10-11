package com.segi.uhomecp.medicaltrans.reportjob.query.trans.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.segi.uhomecp.medicaltrans.report.monthamount.source.model.SourceStatistics;
import com.segi.uhomecp.medicaltrans.report.monthamount.transtype.model.TranstypeStatistics;
import com.segi.uhomecp.medicaltrans.report.monthamount.transway.model.TranswayStatistics;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.MtTaskExtractParamsDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.TranstypeParamsDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.model.MtTaskExtract;
/**
 * 数据抽取
 *     
 * 包: com.segi.uhomecp.medicaltrans.reportjob.query.trans.dao 
 * 类名称: MtTaskExtractMapper.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 下午2:36:20
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
public interface MtTaskExtractMapper {
	
	/**
	 * 获取增量运输单
	 * @param params
	 * @return
	 */
	List<MtTaskExtract> getTaskList(@Param("groupOrganId") int groupOrganId, @Param("params") MtTaskExtractParamsDto params);
	
	/**
	 * 通过创建时间获取运输单
	 * @param params
	 * @return
	 */
	List<MtTaskExtract> getTaskListByCreate(@Param("groupOrganId") int groupOrganId, @Param("params") MtTaskExtractParamsDto params);
	
	
	/**
	 * @discription 根据查询条件对象去业务数据中取到对应的运送类型list
	 * @author yangyh@segimail.com       
	 * @created 2018年8月11日 上午10:17:39     
	 * @param transtypeParamsDto
	 * @return
	 */
	public List<TranstypeStatistics> getTranstypeList(TranstypeParamsDto transtypeParamsDto);
	
	/**
	 * @discription 根据查询条件对象去业务数据中取到对应的运送方式list
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:25:48     
	 * @param transtypeParamsDto
	 * @return
	 */
	public List<TranswayStatistics> getTranswayList(TranstypeParamsDto transtypeParamsDto);
	
	/**
	 * @discription 根据查询条件对象去业务数据中取到对应的运送来源list
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:25:48     
	 * @param transtypeParamsDto
	 * @return
	 */
	public List<SourceStatistics> getSourceList(TranstypeParamsDto transtypeParamsDto);
}
