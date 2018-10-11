package com.segi.uhomecp.medicaltrans.report.monthamount.organ.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.report.monthamount.organ.dao.OrganWeekVolumeMapper;
import com.segi.uhomecp.medicaltrans.report.monthamount.organ.model.OrganWeekVolume;
import com.segi.uhomecp.medicaltrans.report.monthamount.organ.model.OrganWeekVolumeCriteria;
import com.segi.uhomecp.medicaltrans.report.monthamount.organ.service.OrganWeekVolumeService;

@Service
public class OrganWeekVolumeServiceImpl extends
		GenericServiceImpl<OrganWeekVolume, OrganWeekVolumeCriteria, Integer>
		implements OrganWeekVolumeService {

	private static final long serialVersionUID = -6443661152228206195L;

	@Autowired
	private OrganWeekVolumeMapper mapper;

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(mapper);
	}
}
