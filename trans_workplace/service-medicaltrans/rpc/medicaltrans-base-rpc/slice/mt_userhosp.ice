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
		module base{
			module userhosp {
				// 基础入参
				["java:getset"]
				struct UserHospIce{
					string userId;
					string userName;
					string organId;
					string organName;
					string houseId;
					string houseName;
					string tel;
					string userNo;
					string gender;
					string genderName;
					string status;
					string statusName;
					string pageNo;
					string pageLength;
				};
				
				["java:getset"]
				struct UserHospInfoIce{
					string organId;
					string organName;
					string houseId;
					string houseName;
					string userId;
					string userName;
					string status;
				};
				
				// 用户科室入参数的集合
				["java:type:java.util.ArrayList"]sequence<UserHospInfoIce> UserHospInfoIceList;
				
				// 用户科室分页出参数的集合
				["java:type:java.util.ArrayList"]sequence<UserHospIce> UserHospIceList;
				
				["java:getset"]
				struct UserHospReturnIce{
					string userName;
					string userNo;
					string tel;
					UserHospInfoIceList organList;
				};
				
				// 用户科室返回
				["java:getset"]
				struct UserHospRelReturnIce {
					string code;
					string message;
					UserHospReturnIce userHospReturnIce;
				};
				
				// 用户科室pad返回
				["java:getset"]
				struct UserHospRelReturnPadIce {
					string code;
					string message;
					UserHospInfoIce userHospInfoIce;
				};
				
				["java:getset"]
				struct UserHospParamIce{
					string userId;
					string updateBy;
					UserHospInfoIceList organList;
				};
				
				// 用户科室分页返回web
				["java:getset"]
				struct UserHospRetPageIce{
					string code;
					string message;
					page::RpcPageIce paginator;
					UserHospIceList resultList;
				};
				
				interface UserHospServiceIce{
					// 用户科室查询	
					UserHospRelReturnIce queryUserHospInfo(UserHospIce userHospIce);
					// 用户科室保存
					resp::RpcRespIce saveUserHospInfo(UserHospParamIce userHospParamIce);
					// 用户科室详情
					UserHospRelReturnPadIce queryHospUserPad(UserHospIce userHospIce);
					// 切换科室
					resp::RpcRespIce switchoverUserHosp(UserHospIce userHospIce);
					// 用户科室分页
					UserHospRetPageIce queryUserHospPage(UserHospIce userHospIce);
				};
			};
		};
	};
};