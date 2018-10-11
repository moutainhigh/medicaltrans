package com.segi.uhomecp.medicaltrans.base.location.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.base.location.dao.MtBuildLocationRelMapper;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocationRel;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocationRelCriteria;
import com.segi.uhomecp.medicaltrans.base.location.service.MtBuildLocationRelService;
@Service
public class MtBuildLocationRelServiceImpl 
	extends GenericServiceImpl<MtBuildLocationRel, MtBuildLocationRelCriteria, Integer>
	implements MtBuildLocationRelService {

	/**  描述   (@author: dengdong@segimail.com) */      
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MtBuildLocationRelMapper mtBuildLocationRelMapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mtBuildLocationRelMapper);
	}

}
