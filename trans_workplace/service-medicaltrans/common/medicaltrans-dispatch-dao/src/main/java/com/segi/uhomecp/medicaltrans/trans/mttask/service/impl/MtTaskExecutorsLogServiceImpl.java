package com.segi.uhomecp.medicaltrans.trans.mttask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.trans.mttask.dao.MtTaskExecutorsLogMapper;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutorsLog;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutorsLogCriteria;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskExecutorsLogService;
@Service
public class MtTaskExecutorsLogServiceImpl 
	extends GenericServiceImpl<MtTaskExecutorsLog, MtTaskExecutorsLogCriteria, Integer>
	implements MtTaskExecutorsLogService {
	
	/**  描述   (@author: zhangyang@segimail.com) */      
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MtTaskExecutorsLogMapper mtTaskExecutorsLogMapper;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mtTaskExecutorsLogMapper);
	}
}
