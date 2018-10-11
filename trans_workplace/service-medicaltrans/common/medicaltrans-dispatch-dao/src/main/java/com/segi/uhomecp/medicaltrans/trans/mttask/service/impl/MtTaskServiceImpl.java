package com.segi.uhomecp.medicaltrans.trans.mttask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.trans.mttask.dao.MtTaskMapper;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskCriteria;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskService;
@Service
public class MtTaskServiceImpl 
	extends GenericServiceImpl<MtTask, MtTaskCriteria, Integer>
	implements MtTaskService {

	/**  描述   (@author: zhangyang@segimail.com) */      
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MtTaskMapper mtTaskMapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mtTaskMapper);
	}

}
