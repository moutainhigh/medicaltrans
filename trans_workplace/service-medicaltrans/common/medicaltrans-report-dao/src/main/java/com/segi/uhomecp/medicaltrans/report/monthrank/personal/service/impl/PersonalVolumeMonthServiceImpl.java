package com.segi.uhomecp.medicaltrans.report.monthrank.personal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.dao.PersonalVolumeMonthMapper;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonth;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonthCriteria;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.service.PersonalVolumeMonthService;
@Service
public class PersonalVolumeMonthServiceImpl extends
		GenericServiceImpl<PersonalVolumeMonth, PersonalVolumeMonthCriteria, Integer> implements
		PersonalVolumeMonthService {

	/** 描述 (@author: yangyh@segimail.com) */
	private static final long serialVersionUID = 6065113553116570433L;

	@Autowired
	private PersonalVolumeMonthMapper mapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mapper);
	}
}
