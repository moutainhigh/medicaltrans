package com.segi.uhomecp.medicaltrans.base.userhosprel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.base.userhosprel.dao.MtUserHospRelMapper;
import com.segi.uhomecp.medicaltrans.base.userhosprel.model.MtUserHospRel;
import com.segi.uhomecp.medicaltrans.base.userhosprel.model.MtUserHospRelCriteria;
import com.segi.uhomecp.medicaltrans.base.userhosprel.service.MtUserHospRelService;

@Service
public class MtUserHospRelServiceImpl extends
		GenericServiceImpl<MtUserHospRel, MtUserHospRelCriteria, Integer> implements
		MtUserHospRelService {

	/**
	 * 类描述: 
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月10日 下午2:16:07   
	 */
	private static final long serialVersionUID = -7267610419355913118L;

	@Autowired
	private MtUserHospRelMapper mtUserHospRelMapper;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mtUserHospRelMapper);
	}

}
