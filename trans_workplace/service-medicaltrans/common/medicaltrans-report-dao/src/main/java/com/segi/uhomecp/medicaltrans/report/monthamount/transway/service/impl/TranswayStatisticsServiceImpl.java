package com.segi.uhomecp.medicaltrans.report.monthamount.transway.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.report.monthamount.transway.dao.TranswayStatisticsMapper;
import com.segi.uhomecp.medicaltrans.report.monthamount.transway.model.TranswayStatistics;
import com.segi.uhomecp.medicaltrans.report.monthamount.transway.model.TranswayStatisticsCriteria;
import com.segi.uhomecp.medicaltrans.report.monthamount.transway.service.TranswayStatisticsService;

@Service
public class TranswayStatisticsServiceImpl
		extends
			GenericServiceImpl<TranswayStatistics, TranswayStatisticsCriteria, Integer>
		implements
		TranswayStatisticsService {

	/** 描述 (@author: yangyh@segimail.com) */
	private static final long serialVersionUID = -6443661152228206195L;

	@Autowired
	private TranswayStatisticsMapper mapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mapper);
	}

}
