package com.segi.uhomecp.medicaltrans.base.taskloop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.base.taskloop.dao.MtTaskLoopRouteMapper;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoopRoute;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoopRouteCriteria;
import com.segi.uhomecp.medicaltrans.base.taskloop.service.MtTaskLoopRouteService;

@Service
public class MtTaskLoopRouteServiceImpl extends 
	GenericServiceImpl<MtTaskLoopRoute, MtTaskLoopRouteCriteria, Integer> implements MtTaskLoopRouteService {
	
	/**  描述   (@author: yangyh@segimail.com) */      
	private static final long serialVersionUID = -1096540709000044559L;
	
	@Autowired
	private MtTaskLoopRouteMapper mtTaskLoopRouteMapper;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mtTaskLoopRouteMapper);
	}
}
