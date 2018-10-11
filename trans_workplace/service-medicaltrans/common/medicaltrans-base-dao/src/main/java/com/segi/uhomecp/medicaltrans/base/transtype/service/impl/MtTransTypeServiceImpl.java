package com.segi.uhomecp.medicaltrans.base.transtype.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.base.transtype.dao.MtTransTypeMapper;
import com.segi.uhomecp.medicaltrans.base.transtype.model.MtTransType;
import com.segi.uhomecp.medicaltrans.base.transtype.model.MtTransTypeCriteria;
import com.segi.uhomecp.medicaltrans.base.transtype.service.MtTransTypeService;
@Service
public class MtTransTypeServiceImpl 
	extends GenericServiceImpl<MtTransType, MtTransTypeCriteria, Integer>
	implements MtTransTypeService {

	/**  描述   (@author: zhangyang@segimail.com) */      
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MtTransTypeMapper mtTransTypeMapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mtTransTypeMapper);
	}

}
