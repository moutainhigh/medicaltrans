package com.segi.uhomecp.init;

import java.util.Map;

import com.google.common.collect.Maps;
import com.segi.uhomecp.utils.SpringContextUtils;

import Ice.Object;
import IceExt.AbstractIceBoxService;
import segi.medicaltrans.base.dbsource.MtDbSourceRuleServiceIce;
import segi.medicaltrans.base.location.MtLocationManagerServiceIce;
import segi.medicaltrans.base.spacemanage.SpaceManageServiceIce;
import segi.medicaltrans.base.taskloop.TaskLoopServiceIce;
import segi.medicaltrans.base.transtype.TransTypeServiceIce;
import segi.medicaltrans.base.userhosp.UserHospServiceIce;
import segi.medicaltrans.base.userposit.UserPositServiceIce;
import segi.medicaltrans.base.userstatus.UserStatusServiceIce;
import segi.medicaltrans.common.taskloop.MtTaskLoopCommonServiceIce;
import segi.medicaltrans.common.transType.MtCommonTransTypeServiceIce;
import segi.medicaltrans.common.userhosp.UserHospCommonServiceIce;
import segi.medicaltrans.common.userposit.MtUserPositCommonServiceIce;
import segi.medicaltrans.location.common.MtLocationCommonServiceIce;

public class MedicaltransBaseIceBoxService extends AbstractIceBoxService{

	@Override
	public Map<String, Object> createIceServiceObj(String[] args) {
		SpringContextUtils.instance();
		
		Map<String,Object> resMap=Maps.newHashMap();
		
		//运输类型管理
		TransTypeServiceIce transTypeServiceIce = SpringContextUtils.getBean(TransTypeServiceIce.class);
		resMap.put(TransTypeServiceIce.class.getName(), transTypeServiceIce);
		
		//运输类型公共服务
		MtCommonTransTypeServiceIce mtCommonTransTypeServiceIce = SpringContextUtils.getBean(MtCommonTransTypeServiceIce.class);
		resMap.put(MtCommonTransTypeServiceIce.class.getName(), mtCommonTransTypeServiceIce);
		
		//医院人员位置
		UserPositServiceIce userPositServiceIce = SpringContextUtils.getBean(UserPositServiceIce.class);
		resMap.put(UserPositServiceIce.class.getName(), userPositServiceIce);
		
		//员工状态切换
		UserStatusServiceIce userStatusServiceIce = SpringContextUtils.getBean(UserStatusServiceIce.class);
		resMap.put(UserStatusServiceIce.class.getName(), userStatusServiceIce);

		//医院人员位置_公共服务
		MtUserPositCommonServiceIce mtUserPositCommonServiceIce = SpringContextUtils.getBean(MtUserPositCommonServiceIce.class);
		resMap.put(MtUserPositCommonServiceIce.class.getName(), mtUserPositCommonServiceIce);
		
		//位置管理
		MtLocationManagerServiceIce mtLocationManagerServiceIce = SpringContextUtils.getBean(MtLocationManagerServiceIce.class);
		resMap.put(MtLocationManagerServiceIce.class.getName(), mtLocationManagerServiceIce);
		
		//位置管理_公共服务
		MtLocationCommonServiceIce mtLocationCommonServiceIce = SpringContextUtils.getBean(MtLocationCommonServiceIce.class);
		resMap.put(MtLocationCommonServiceIce.class.getName(), mtLocationCommonServiceIce);
		
		//用户科室管理
		UserHospServiceIce userHospServiceIce = SpringContextUtils.getBean(UserHospServiceIce.class);
		resMap.put(UserHospServiceIce.class.getName(), userHospServiceIce);

		// 空间位置管理高速查询
		SpaceManageServiceIce spaceManageServiceIce = SpringContextUtils.getBean(SpaceManageServiceIce.class);
		resMap.put(SpaceManageServiceIce.class.getName(), spaceManageServiceIce);

		// 循环任务管理
		TaskLoopServiceIce taskLoopServiceIce = SpringContextUtils.getBean(TaskLoopServiceIce.class);
		resMap.put(TaskLoopServiceIce.class.getName(), taskLoopServiceIce);

		// 循环任务管理公共服务
		MtTaskLoopCommonServiceIce mtTaskLoopCommonServiceIce = SpringContextUtils
				.getBean(MtTaskLoopCommonServiceIce.class);
		resMap.put(MtTaskLoopCommonServiceIce.class.getName(), mtTaskLoopCommonServiceIce);
		
		MtDbSourceRuleServiceIce mtDbSourceRuleServiceIce = SpringContextUtils
				.getBean(MtDbSourceRuleServiceIce.class);
		resMap.put(MtDbSourceRuleServiceIce.class.getName(), mtDbSourceRuleServiceIce);
		
		UserHospCommonServiceIce userHospCommonServiceIce = SpringContextUtils.getBean(UserHospCommonServiceIce.class);
		resMap.put(UserHospCommonServiceIce.class.getName(), userHospCommonServiceIce);

		return resMap;
	}
}
