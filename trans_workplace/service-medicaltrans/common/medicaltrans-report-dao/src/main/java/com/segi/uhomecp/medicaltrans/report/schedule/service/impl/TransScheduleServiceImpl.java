package com.segi.uhomecp.medicaltrans.report.schedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.report.schedule.dao.TransScheduleMapper;
import com.segi.uhomecp.medicaltrans.report.schedule.model.TransSchedule;
import com.segi.uhomecp.medicaltrans.report.schedule.model.TransScheduleCriteria;
import com.segi.uhomecp.medicaltrans.report.schedule.service.TransScheduleService;

/**
 * Title: TransScheduleServiceImpl.java
 * @Description: 描述
 * @author yangyh@segimail.com
 * @created 2018年5月15日 下午3:30:28
 */
@Service
public class TransScheduleServiceImpl extends GenericServiceImpl<TransSchedule, TransScheduleCriteria, Integer>
		implements TransScheduleService {

	/** 描述 (@author: yangyh@segimail.com) */
	private static final long serialVersionUID = 3826959832685522067L;

	@Autowired
	private TransScheduleMapper scheduleMapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(scheduleMapper);
	}
}
