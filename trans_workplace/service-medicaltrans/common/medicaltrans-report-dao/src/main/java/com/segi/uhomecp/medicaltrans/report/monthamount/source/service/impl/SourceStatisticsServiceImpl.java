package com.segi.uhomecp.medicaltrans.report.monthamount.source.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.report.monthamount.source.dao.SourceStatisticsMapper;
import com.segi.uhomecp.medicaltrans.report.monthamount.source.model.SourceStatistics;
import com.segi.uhomecp.medicaltrans.report.monthamount.source.model.SourceStatisticsCriteria;
import com.segi.uhomecp.medicaltrans.report.monthamount.source.service.SourceStatisticsService;

@Service
public class SourceStatisticsServiceImpl
		extends
			GenericServiceImpl<SourceStatistics, SourceStatisticsCriteria, Integer>
		implements
		SourceStatisticsService {

	/** 描述 (@author: yangyh@segimail.com) */
	private static final long serialVersionUID = -6443661152228206195L;

	@Autowired
	private SourceStatisticsMapper mapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mapper);
	}

}
