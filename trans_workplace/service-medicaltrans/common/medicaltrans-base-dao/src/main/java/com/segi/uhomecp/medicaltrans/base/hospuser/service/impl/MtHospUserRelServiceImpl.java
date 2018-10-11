package com.segi.uhomecp.medicaltrans.base.hospuser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.base.hospuser.dao.MtHospUserRelMapper;
import com.segi.uhomecp.medicaltrans.base.hospuser.model.MtHospUserRel;
import com.segi.uhomecp.medicaltrans.base.hospuser.model.MtHospUserRelCriteria;
import com.segi.uhomecp.medicaltrans.base.hospuser.service.MtHospUserRelService;
@Service
public class MtHospUserRelServiceImpl extends GenericServiceImpl<MtHospUserRel, MtHospUserRelCriteria, Integer>
			implements MtHospUserRelService{

	/**
	 * 类描述: 
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年4月3日 上午11:55:54   
	 */
	private static final long serialVersionUID = -2443859476791388741L;

	@Autowired
	private MtHospUserRelMapper mtHospUserRelMapper;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mtHospUserRelMapper);
	}

}
