package com.segi.uhomecp.medicaltrans.report.monthamount.organ.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.report.monthamount.organ.dao.OrganMonthStatisticsMapper;
import com.segi.uhomecp.medicaltrans.report.monthamount.organ.model.OrganMonthStatistics;
import com.segi.uhomecp.medicaltrans.report.monthamount.organ.model.OrganMonthStatisticsCriteria;
import com.segi.uhomecp.medicaltrans.report.monthamount.organ.service.OrganMonthStatisticsService;

@Service
public class OrganMonthStatisticsServiceImpl
		extends
			GenericServiceImpl<OrganMonthStatistics, OrganMonthStatisticsCriteria, Integer>
		implements
			OrganMonthStatisticsService {

	/** 描述 (@author: yangyh@segimail.com) */
	private static final long serialVersionUID = -6443661152228206195L;

	@Autowired
	private OrganMonthStatisticsMapper mapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mapper);
	}

}
