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
			module userstatus {
				// 根据当前登录用户查询切换员工状态
				["java:getset"]
				struct UserStatusIce{
					string organId;
					string userId;
					string status;
				};
				
				// 根据当前登录用户查询最后一次定位和时间出参
				["java:getset"]
				struct UserStatusRetIce{
					string userId;
					string status;					 
				};
				
				// 根据当前登录用户切换员工状态出参
				["java:getset"]
				struct UserStatusReturnIce{
					string code;
					string message;
					UserStatusRetIce userStatusRetIce;
				};
				
				
				// 人员位置管理
				interface UserStatusServiceIce{
					// 根据当前登录用户切换员工状态
					UserStatusReturnIce updateStausByUser(UserStatusIce userStatusIce);
					
					// 根据当前登录用户查询员工状态
					UserStatusReturnIce getStatusByUser(UserStatusIce userStatusIce);
				};
			};
		};
	};
};