// **********************************************************************
//
// Copyright (c) 2003-2006 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//   
// **********************************************************************
  
#pragma once
#include <commons.ice>
module segiwh {
	module common {
		["java:getset"]
		struct User {
			string userId;      // 员工Id 四格的员工表主键    
			string userName;
			string userNo;      // 员工工号 
			string userStatus;  // 员工状态
			// 员工手机号
			string tel;
		};
		["java:type:java.util.ArrayList"]sequence<User> UserList;
		
		["java:type:java.util.ArrayList"]sequence<int> IntList;
	};
};