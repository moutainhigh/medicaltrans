package com.segi.uhomecp.init;

import java.util.Map;

import com.google.common.collect.Maps;
import com.segi.uhomecp.utils.SpringContextUtils;

import Ice.Object;
import IceExt.AbstractIceBoxService;
import segi.medicaltrans.mthistask.query.MtHisTaskQueryServiceIce;
import segi.medicaltrans.mttask.common.MtTaskCommonServiceIce;
import segi.medicaltrans.mttask.manager.MtTaskManagerCreateServiceIce;
import segi.medicaltrans.mttask.manager.MtTaskManagerHandleServiceIce;
import segi.medicaltrans.mttask.query.MtTaskQueryServiceIce;
import segi.medicaltrans.report.pervolume.day.PerVolDayServiceIce;

public class MedicaltransDispatchBoxService extends AbstractIceBoxService {

	@Override
	public Map<String, Object> createIceServiceObj(String[] args) {
		SpringContextUtils.instance();

		Map<String, Object> resMap = Maps.newHashMap();

		// 任务管理
		MtTaskManagerCreateServiceIce mtTaskManagerCreateServiceIce = SpringContextUtils.getBean(MtTaskManagerCreateServiceIce.class);
		resMap.put(MtTaskManagerCreateServiceIce.class.getName(), mtTaskManagerCreateServiceIce);
		
		MtTaskManagerHandleServiceIce mtTaskManagerHanleServiceIce = SpringContextUtils.getBean(MtTaskManagerHandleServiceIce.class);
		resMap.put(MtTaskManagerHandleServiceIce.class.getName(), mtTaskManagerHanleServiceIce);

		// 任务管理公共服务
		MtTaskCommonServiceIce mtTaskCommonServiceIce = SpringContextUtils.getBean(MtTaskCommonServiceIce.class);
		resMap.put(MtTaskCommonServiceIce.class.getName(), mtTaskCommonServiceIce);

		// 任务查询管理
		MtTaskQueryServiceIce mtTaskQueryServiceIce = SpringContextUtils.getBean(MtTaskQueryServiceIce.class);
		resMap.put(MtTaskQueryServiceIce.class.getName(), mtTaskQueryServiceIce);

		// 运送记录查询（报表）
		MtHisTaskQueryServiceIce mtHisTaskQueryServiceIce = SpringContextUtils.getBean(MtHisTaskQueryServiceIce.class);
		resMap.put(MtHisTaskQueryServiceIce.class.getName(), mtHisTaskQueryServiceIce);
		
		// 运送员运送量日排名
		PerVolDayServiceIce perVolDayServiceIce = SpringContextUtils.getBean(PerVolDayServiceIce.class);
		resMap.put(PerVolDayServiceIce.class.getName(), perVolDayServiceIce);
		
		return resMap;
	}
}
