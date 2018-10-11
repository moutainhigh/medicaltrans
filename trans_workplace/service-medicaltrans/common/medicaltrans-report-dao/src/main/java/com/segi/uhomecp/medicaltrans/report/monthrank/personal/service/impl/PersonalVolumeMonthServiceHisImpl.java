package com.segi.uhomecp.medicaltrans.report.monthrank.personal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.dao.PersonalVolumeMonthHisMapper;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonthHis;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonthHisCriteria;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.service.PersonalVolumeMonthHisService;

@Service
public class PersonalVolumeMonthServiceHisImpl extends
		GenericServiceImpl<PersonalVolumeMonthHis, PersonalVolumeMonthHisCriteria, Integer> implements
		PersonalVolumeMonthHisService {

	/** 描述 (@author: yangyh@segimail.com) */
	private static final long serialVersionUID = 6065113553116570433L;

	@Autowired
	private PersonalVolumeMonthHisMapper mapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mapper);
	}
}
