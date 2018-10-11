package com.segi.uhomecp.init;

import java.util.Map;

import com.google.common.collect.Maps;
import com.segi.uhomecp.utils.SpringContextUtils;

import Ice.Object;
import IceExt.AbstractIceBoxService;

public class MedicaltransJobIceBoxService extends AbstractIceBoxService {

	@Override
	public Map<String, Object> createIceServiceObj(String[] args) {
		SpringContextUtils.instance();
		
		Map<String, Object> resMap = Maps.newHashMap();
		
		return resMap;
	}
}
