package com.segi.uhomecp.medicaltrans.trans.mttask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.trans.mttask.dao.MtTaskExecutorsMapper;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutors;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutorsCriteria;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskExecutorsService;
@Service
public class MtTaskExecutorsServiceImpl 
	extends GenericServiceImpl<MtTaskExecutors, MtTaskExecutorsCriteria, Integer>
	implements MtTaskExecutorsService {

	/**  描述   (@author: zhangyang@segimail.com) */      
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MtTaskExecutorsMapper mtTaskExecutorsMapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mtTaskExecutorsMapper);
	}

}
