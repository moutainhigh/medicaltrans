// **********************************************************************
//
// Copyright (c) 2003-2006 ZeroC, Inc. All rights reserved.
// @author zhangyang@segimail.com
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
// **********************************************************************


#pragma once
#include <commons.ice>
module segi {
	module medicaltrans {
		module mttask {	
			module  common {	
				// 个人月运送量
				["java:getset"]
				struct PersonalVolumeMonthIce{
					// 人员Id
					string userId;
					// 部门id
					string organId;
					// 运送量
					string transVolume;
				};
				
				["java:type:java.util.ArrayList"]sequence<PersonalVolumeMonthIce> PersonalVolumeMonthList;
				
				["java:type:java.util.ArrayList"]sequence<int> OrganIdList;
				
				// 个人月运送量返回参数
				["java:getset"]
				struct PersonalVolumeMonthReturnIce{
					string code;
					string message;
					PersonalVolumeMonthList personalVolumeMonthList;
				};
				
				interface MtTaskCommonServiceIce{
					// 根据organId和transTypeCode判断有没有使用中的类型
					//resp::RpcRespIce judgeTransType(string organId, string transTypeId);
					
					//根据locationId判断有没有在途的运送任务
					//resp::RpcRespIce judgeOnWayTask(string loactionId);
					
					// 修改任务单异常关闭
					resp::RpcRespIce updateTaskUnusalClose(int groupOrganId, OrganIdList organIdList, long limitDate);
				};
			};
		};
	};
};