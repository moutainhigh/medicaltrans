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
			module userinfomanage {
				// 员工档案查询入参信息
				["java:getset"]
				struct UserInfoParamsIce {
					string organId;     
					string userName;    
					string tel;         
					string jobNumber;   
					string status;      
					string pageNo;      
					string pageLength;
				};
				
				// 用户信息ICE
				["java:getset"]
				struct UserInfoIce {
					string userId;         
					string userName;       
					string jobNumber;      
					string organId;        
					string organName;     
					string tel;           
					string sex;           
					string sexName;       
					string status;      
					string statusName;   
					string identity; 
				};
				
				["java:type:java.util.ArrayList"]sequence<UserInfoIce> UserInfoIceList;
				
				// 用户档案信息List返回
				["java:getset"]
				struct UserInfoIceRespIce {
					string code;
					string message;
					page::RpcPageIce paginator;
					UserInfoIceList resultList;
				};
				
				// 员工档案查询接口
				interface UserInfoManageServiceIce {
					// 通过员工姓名、员工手机号、员工工号查询员工档案信息
					UserInfoIceRespIce queryUserListByOrgIdAndOpts(UserInfoParamsIce params);
				};
			};
		};
	};
};