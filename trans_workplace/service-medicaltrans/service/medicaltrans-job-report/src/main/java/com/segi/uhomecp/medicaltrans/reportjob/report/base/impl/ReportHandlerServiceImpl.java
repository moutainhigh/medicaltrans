package com.segi.uhomecp.medicaltrans.reportjob.report.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.medicaltrans.report.schedule.dao.ExecuteHandlerMapper;
import com.segi.uhomecp.medicaltrans.report.schedule.model.ExecuteHandler;
import com.segi.uhomecp.medicaltrans.report.schedule.model.ExecuteHandlerCriteria;
import com.segi.uhomecp.medicaltrans.reportjob.report.base.ReportHandlerService;
/**
 * 
 *     
 * 包: com.segi.uhomecp.medicaltrans.reportjob.base.service.impl 
 * 类名称: ReportHandlerServiceImpl.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 下午7:44:23
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
@Service
public class ReportHandlerServiceImpl implements ReportHandlerService {
	
	@Autowired
	private ExecuteHandlerMapper executeHandlerMapper;
	
	@Override
	public List<ExecuteHandler> getHandlerList() {
		ExecuteHandlerCriteria example = new ExecuteHandlerCriteria();
		ExecuteHandlerCriteria.Criteria criteria = example.createCriteria();
		criteria.andRunningStatusEqualTo("1");	//取有效
		example.setOrderByClause("EXE_ORDER ASC");
	
		return executeHandlerMapper.selectByExample(example);
	}

}
