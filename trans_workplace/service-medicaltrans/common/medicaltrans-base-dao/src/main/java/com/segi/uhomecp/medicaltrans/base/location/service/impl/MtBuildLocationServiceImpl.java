package com.segi.uhomecp.medicaltrans.base.location.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.base.location.dao.MtBuildLocationMapper;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocation;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocationCriteria;
import com.segi.uhomecp.medicaltrans.base.location.service.MtBuildLocationService;
@Service
public class MtBuildLocationServiceImpl 
	extends GenericServiceImpl<MtBuildLocation, MtBuildLocationCriteria, Integer>
	implements MtBuildLocationService {

	/**  描述   (@author: dengdong@segimail.com) */      
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MtBuildLocationMapper mtBuildLocationMapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mtBuildLocationMapper);
	}

}
