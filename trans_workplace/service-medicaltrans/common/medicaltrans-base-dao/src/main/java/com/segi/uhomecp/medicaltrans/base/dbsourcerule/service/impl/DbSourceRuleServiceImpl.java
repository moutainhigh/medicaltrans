package com.segi.uhomecp.medicaltrans.base.dbsourcerule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.base.dbsourcerule.dao.DbSourceRuleMapper;
import com.segi.uhomecp.medicaltrans.base.dbsourcerule.model.DbSourceRule;
import com.segi.uhomecp.medicaltrans.base.dbsourcerule.model.DbSourceRuleCriteria;
import com.segi.uhomecp.medicaltrans.base.dbsourcerule.service.DbSourceRuleService;
@Service
public class DbSourceRuleServiceImpl extends GenericServiceImpl<DbSourceRule, DbSourceRuleCriteria, Integer>
	implements DbSourceRuleService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private DbSourceRuleMapper dbSourceRuleMapper;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(dbSourceRuleMapper);
		
	}
}
