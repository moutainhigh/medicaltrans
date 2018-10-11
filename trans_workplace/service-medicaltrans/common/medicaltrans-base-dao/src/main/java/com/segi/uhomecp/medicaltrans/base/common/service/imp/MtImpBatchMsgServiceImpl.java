package com.segi.uhomecp.medicaltrans.base.common.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.base.common.dao.MtImpBatchMsgMapper;
import com.segi.uhomecp.medicaltrans.base.common.model.MtImpBatchMsg;
import com.segi.uhomecp.medicaltrans.base.common.model.MtImpBatchMsgCriteria;
import com.segi.uhomecp.medicaltrans.base.common.service.MtImpBatchMsgService;
@Service
public class MtImpBatchMsgServiceImpl  
	extends GenericServiceImpl<MtImpBatchMsg, MtImpBatchMsgCriteria, Integer>
	implements MtImpBatchMsgService {

	/**
	 * 2018年10月8日上午11:14:44
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MtImpBatchMsgMapper mtImpBatchMsgMapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mtImpBatchMsgMapper);
	}

}
