package com.segi.uhomecp.medicaltrans.base.transtype.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.base.transtype.dao.MtTransTypeImpMidMapper;
import com.segi.uhomecp.medicaltrans.base.transtype.model.MtTransTypeImpMid;
import com.segi.uhomecp.medicaltrans.base.transtype.model.MtTransTypeImpMidCriteria;
import com.segi.uhomecp.medicaltrans.base.transtype.service.MtTransTypeImpMidService;
@Service
public class MtTransTypeImpMidServiceImpl   
	extends GenericServiceImpl<MtTransTypeImpMid, MtTransTypeImpMidCriteria, Integer>
	implements MtTransTypeImpMidService {
	
	/**
	 * 2018年10月8日上午11:19:16
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MtTransTypeImpMidMapper mtTransTypeImpMidMapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mtTransTypeImpMidMapper);
	}

}
