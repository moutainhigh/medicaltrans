package com.segi.uhomecp.medicaltrans.report.monthamount.transtype.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.report.monthamount.transtype.dao.TranstypeStatisticsMapper;
import com.segi.uhomecp.medicaltrans.report.monthamount.transtype.model.TranstypeStatistics;
import com.segi.uhomecp.medicaltrans.report.monthamount.transtype.model.TranstypeStatisticsCriteria;
import com.segi.uhomecp.medicaltrans.report.monthamount.transtype.service.TranstypeStatisticsService;

@Service
public class TranstypeStatisticsServiceImpl
		extends
			GenericServiceImpl<TranstypeStatistics, TranstypeStatisticsCriteria, Integer>
		implements
		TranstypeStatisticsService {

	/** 描述 (@author: yangyh@segimail.com) */
	private static final long serialVersionUID = -6443661152228206195L;

	@Autowired
	private TranstypeStatisticsMapper mapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mapper);
	}

}
