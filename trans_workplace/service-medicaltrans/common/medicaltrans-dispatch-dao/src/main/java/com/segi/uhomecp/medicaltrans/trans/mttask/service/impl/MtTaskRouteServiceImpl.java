package com.segi.uhomecp.medicaltrans.trans.mttask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.trans.mttask.dao.MtTaskRouteMapper;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRoute;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRouteCriteria;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskRouteService;
@Service
public class MtTaskRouteServiceImpl 
	extends GenericServiceImpl<MtTaskRoute, MtTaskRouteCriteria, Integer>
	implements MtTaskRouteService {

	/**  描述   (@author: zhangyang@segimail.com) */      
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MtTaskRouteMapper mtTaskRouteMapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mtTaskRouteMapper);
	}

}
