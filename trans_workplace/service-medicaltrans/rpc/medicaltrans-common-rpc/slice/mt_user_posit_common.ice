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
		module common {	
			module userposit {	
				["java:getset"]
				struct UserPositParam{
					string organId;
					string userId;
					string locationId;
					string unTaskNum; 
					string runTaskNum;
				};
				
				["java:getset"]
				struct UserLocationTaskNumParam{
					string organId;
					string userIds;
					string unTaskNum;
					string runTaskNum; 
					string executeDate;
				};
				
				["java:type:java.util.ArrayList"]sequence<UserPositParam> UserPositParamList;

				["java:type:java.util.ArrayList"]sequence<int> UserIds;
				
				interface MtUserPositCommonServiceIce{
					// 修改人员状态和未完成任务数
					resp::RpcRespIce updateUserPositInfo(int organId, UserIds userIds, short unTaskNum, short runTaskNum,string executeDate,string locaitonId);
					
					//更新人员最新位置
					resp::RpcRespIce updateUserNewLocationList(UserPositParamList userPositParamList);
				};
			};
		};
	};
};