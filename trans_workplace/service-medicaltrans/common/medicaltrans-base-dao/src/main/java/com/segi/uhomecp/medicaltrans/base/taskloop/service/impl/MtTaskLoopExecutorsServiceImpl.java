package com.segi.uhomecp.medicaltrans.base.taskloop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.base.taskloop.dao.MtTaskLoopExecutorsMapper;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoopExecutors;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoopExecutorsCriteria;
import com.segi.uhomecp.medicaltrans.base.taskloop.service.MtTaskLoopExecutorsService;

@Service
public class MtTaskLoopExecutorsServiceImpl extends 
	GenericServiceImpl<MtTaskLoopExecutors, MtTaskLoopExecutorsCriteria, Integer> implements MtTaskLoopExecutorsService {
	
	/**  描述   (@author: yangyh@segimail.com) */      
	private static final long serialVersionUID = 5233922061560884009L;

	@Autowired
	private MtTaskLoopExecutorsMapper mtTaskLoopExecutorsMapper;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mtTaskLoopExecutorsMapper);
	}
}
