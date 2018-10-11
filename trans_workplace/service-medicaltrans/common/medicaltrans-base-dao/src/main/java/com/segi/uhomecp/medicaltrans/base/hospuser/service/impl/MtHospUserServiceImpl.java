package com.segi.uhomecp.medicaltrans.base.hospuser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.base.hospuser.dao.MtHospUserMapper;
import com.segi.uhomecp.medicaltrans.base.hospuser.model.MtHospUser;
import com.segi.uhomecp.medicaltrans.base.hospuser.model.MtHospUserCriteria;
import com.segi.uhomecp.medicaltrans.base.hospuser.service.MtHospUserService;
@Service
public class MtHospUserServiceImpl extends GenericServiceImpl<MtHospUser, MtHospUserCriteria, Integer>
   implements MtHospUserService {

	/**
	 * 类描述: 
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年4月3日 上午11:50:42   
	 */
	private static final long serialVersionUID = 8636634445758717533L;

	@Autowired
	private MtHospUserMapper mtHospUserMapper;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mtHospUserMapper);
		
	}

}
