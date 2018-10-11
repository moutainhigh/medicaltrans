package com.segi.uhomecp.medicaltrans.base.location.service;

import com.segi.uhomecp.common.service.GenericService;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocation;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocationCriteria;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocationRel;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocationRelCriteria;

public interface MtBuildLocationRelService extends 
	GenericService<MtBuildLocationRel, MtBuildLocationRelCriteria, Integer> {
}
