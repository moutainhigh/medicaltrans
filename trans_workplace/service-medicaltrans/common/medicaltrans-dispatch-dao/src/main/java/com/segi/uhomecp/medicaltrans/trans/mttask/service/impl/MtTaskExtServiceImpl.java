package com.segi.uhomecp.medicaltrans.trans.mttask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.trans.mttask.dao.MtTaskExtMapper;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExt;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExtCriteria;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskExtService;
@Service
public class MtTaskExtServiceImpl 
	extends GenericServiceImpl<MtTaskExt, MtTaskExtCriteria, Integer>
	implements MtTaskExtService {

	/**  描述   (@author: zhangyang@segimail.com) */      
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MtTaskExtMapper mtTaskExtMapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mtTaskExtMapper);
	}

}
