package com.segi.uhomecp.medicaltrans.base.taskloop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.base.taskloop.dao.MtTaskLoopMapper;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoop;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoopCriteria;
import com.segi.uhomecp.medicaltrans.base.taskloop.service.MtTaskLoopService;

@Service
public class MtTaskLoopServiceImpl extends GenericServiceImpl<MtTaskLoop, MtTaskLoopCriteria, Integer> implements MtTaskLoopService {

	/**  描述   (@author: yangyh@segimail.com) */      
	private static final long serialVersionUID = -5959395982366805491L;

	@Autowired
	private MtTaskLoopMapper mtTaskLoopMapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mtTaskLoopMapper);
	}
}
