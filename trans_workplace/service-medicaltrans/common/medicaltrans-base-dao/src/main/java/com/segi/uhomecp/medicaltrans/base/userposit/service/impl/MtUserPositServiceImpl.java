package com.segi.uhomecp.medicaltrans.base.userposit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.base.userposit.dao.MtUserPositMapper;
import com.segi.uhomecp.medicaltrans.base.userposit.model.MtUserPosit;
import com.segi.uhomecp.medicaltrans.base.userposit.model.MtUserPositCriteria;
import com.segi.uhomecp.medicaltrans.base.userposit.service.MtUserPositService;

@Service
public class MtUserPositServiceImpl extends
		GenericServiceImpl<MtUserPosit, MtUserPositCriteria, Integer> implements
		MtUserPositService {

	/**
	 * 类描述: 
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月3日 下午9:01:51   
	 */
	private static final long serialVersionUID = -6370984320854476934L;
	
	@Autowired
	private MtUserPositMapper mtUserPositMapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mtUserPositMapper);
	}

}
