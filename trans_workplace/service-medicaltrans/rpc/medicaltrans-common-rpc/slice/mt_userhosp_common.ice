// **********************************************************************
//
// Copyright (c) 2003-2006 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
// **********************************************************************


#pragma once
#include <commons.ice>
module segi {
	module medicaltrans {
		module common{
			module userhosp {
				// 用户Id集合
				["java:type:java.util.ArrayList"]sequence<int> UserIdIntList;
				
				// 基础入参
				["java:getset"]
				struct UserHospIce {
					string userId;
					string organId;
					UserIdIntList userIdList;
				};
				
				["java:getset"]
				struct UserHospCommonIce {
					string organId;
					string organName;
					string houseId;
					string houseName;
					string userId;
					string userName;
				};
				
				// 用户科室pad返回
				["java:getset"]
				struct UserHospRelReturnInfoIce {
					string code;
					string message;
					UserHospCommonIce userHospCommonIce;
				};
				
				// 用户科室ICE对象返回集合
				["java:type:java.util.ArrayList"]sequence<UserHospCommonIce> UserHospCommonIceList;
				
				// 用户科室信息列表返回
				["java:getset"]
				struct UserHospRelIceListRsp {
					string code;
					string message;
					UserHospCommonIceList rerultList;
				};
				
				["java:type:java.util.ArrayList"]sequence<string> UserIdList;
				
				// 科室查询人员返回
				["java:getset"]
				struct UserIdsByHouseIdReturnIce {
					string code;
					string message;
					UserIdList userIdList;
				};
				
				interface UserHospCommonServiceIce {
					// 用户科室详情
					UserHospRelReturnInfoIce queryHospUserInfo(UserHospIce userHospIce );
					
					// 用户科室详情列表查询
					UserHospRelIceListRsp queryHospUserInfoList(UserHospIce userHospIce );
					
					// 通过科室ID查询科室人员
					UserIdsByHouseIdReturnIce queryUserIdsByHouseId(string houseId );
				};
			};
		};
	};
};