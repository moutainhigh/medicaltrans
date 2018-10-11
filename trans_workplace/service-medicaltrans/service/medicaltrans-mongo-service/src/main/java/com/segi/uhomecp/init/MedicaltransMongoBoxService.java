package com.segi.uhomecp.init;

import java.util.Map;

import segi.medicaltrans.common.userstatus.MtUserStatusServiceIce;
import segi.medicaltrans.mttask.track.MtTaskTrackServiceIce;
import segi.medicaltrans.report.common.transsource.MtCommonTransSourceServiceIce;
import segi.medicaltrans.report.common.transtype.ReportCommonTransTypeServiceIce;
import segi.medicaltrans.report.common.transway.MtCommonTransWayServiceIce;
import Ice.Object;
import IceExt.AbstractIceBoxService;

import com.google.common.collect.Maps;
import com.segi.uhomecp.utils.SpringContextUtils;

public class MedicaltransMongoBoxService extends AbstractIceBoxService {

	@Override
	public Map<String, Object> createIceServiceObj(String[] args) {
		SpringContextUtils.instance("classpath*:applicationContext.xml");
		
		Map<String, Object> resMap = Maps.newHashMap();
		
		// 运送轨迹
		MtTaskTrackServiceIce mtTaskTrackServiceIce = SpringContextUtils.getBean(MtTaskTrackServiceIce.class);
		resMap.put(MtTaskTrackServiceIce.class.getName(), mtTaskTrackServiceIce);
		
		//人员状态切换
		MtUserStatusServiceIce mtUserStatusServiceIce = SpringContextUtils.getBean(MtUserStatusServiceIce.class);
		resMap.put(MtUserStatusServiceIce.class.getName(), mtUserStatusServiceIce);
		
		// 运送来源
		MtCommonTransSourceServiceIce mtCommonTransSourceServiceIce = SpringContextUtils.getBean(MtCommonTransSourceServiceIce.class);
		resMap.put(MtCommonTransSourceServiceIce.class.getName(), mtCommonTransSourceServiceIce);
	
		// 运送类型
		ReportCommonTransTypeServiceIce reportCommonTransTypeServiceIce = SpringContextUtils.getBean(ReportCommonTransTypeServiceIce.class);
		resMap.put(ReportCommonTransTypeServiceIce.class.getName(), reportCommonTransTypeServiceIce);

		// 运送方式
		MtCommonTransWayServiceIce mtCommonTransWayServiceIce = SpringContextUtils.getBean(MtCommonTransWayServiceIce.class);
		resMap.put(MtCommonTransWayServiceIce.class.getName(), mtCommonTransWayServiceIce);
		
		return resMap;
	}
}
