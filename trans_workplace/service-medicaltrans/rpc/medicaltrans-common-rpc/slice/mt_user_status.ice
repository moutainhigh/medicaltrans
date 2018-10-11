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
			module userstatus {		
				
				// 人员状态切换入参
				["java:getset"]
				struct UserStatusIce{
    				string userId; 
    				string organId; 
    				string status; 
    				string paramTime; 
    				string updateTime;
				};
				
				// 人员状态切换返回参数
				["java:getset"]
				struct UserStatusDetailIce{
    				string userId; 
    				string status; 
				};
				
				// 人员状态切换返回参数
				["java:getset"]
				struct  UserStatusDetailRspIce{
					string code;
					string message;
					UserStatusDetailIce userStatusDetailIce;
				};
				
				["java:type:java.util.ArrayList"]sequence<string> UserList;
				
				//批量人员状态查询入参
				["java:getset"]
				struct UserLotStatusIce{
					string organId; 
					UserList userList;
				};
				
				["java:type:java.util.ArrayList"]sequence<UserStatusDetailIce> UserStatusDetailList;
				
				// 批量人员状态切换返回参数
				["java:getset"]
				struct  UserLotStatusDetailRspIce{
					string code;
					string message;
					UserStatusDetailList userStatusDetailList;
				};
				
				interface MtUserStatusServiceIce{
					// 人员状态切换
					UserStatusDetailRspIce updateStausByUser(UserStatusIce userStatusIce);
					
					//人员状态查询
					UserStatusDetailRspIce getStatusByUser(UserStatusIce userStatusIce);
					
					//批量查询人员状态
					UserLotStatusDetailRspIce getLotStatusByUser(UserLotStatusIce userLotStatusIce);
				};
			};
		};
	};
};