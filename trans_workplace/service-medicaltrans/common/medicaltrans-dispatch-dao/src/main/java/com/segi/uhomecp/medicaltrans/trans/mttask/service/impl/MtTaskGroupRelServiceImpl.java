package com.segi.uhomecp.medicaltrans.trans.mttask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.trans.mttask.dao.MtTaskGroupRelMapper;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskGroupRel;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskGroupRelCriteria;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskGroupRelService;
@Service
public class MtTaskGroupRelServiceImpl 
	extends GenericServiceImpl<MtTaskGroupRel, MtTaskGroupRelCriteria, Integer>
	implements MtTaskGroupRelService {

	/**  描述   (@author: zhangyang@segimail.com) */      
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MtTaskGroupRelMapper mtTaskGroupRelMapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mtTaskGroupRelMapper);
	}

}
