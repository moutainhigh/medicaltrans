// **********************************************************************
//
// Copyright (c) 2003-2006 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
#pragma once

//[["java:package:segi.common"]]
module excep
{
	["preserve-slice","java:getset"]
	exception RpcExceptionIce
	{
	    string code;
	    string message;
	    string data;
	};
};

module resp
{
	["java:getset"]
	class RpcRespBaseIce{
			string code;
			string message;
	};

	["java:getset"]
	class RpcRespIce{
			string code;
			string message;
			string data;
	};
};

module page
{

	["java:getset"]
	class PageReqIce{
			string pageLength;
			string pageNo;
			string orderByColumn;
			//ASC（生序）,DESC（倒序）
			string orderBySequence;
	};
	
	["java:getset"]
	class RpcPageIce{
			string pageLength;
			string pageNo;
			string totalCount;
	};
	
};



module model
{
	class UserIce {
		 string userId;
	
	    string organId;
	
		string userName;
	};
	
	class LoginUserIce extends UserIce{
		
	    string token;
	};
	
	class UserInfoIce extends UserIce{
		string organName;
		
		string department;
	};
	
	struct AreaIce{
	    int areaId;
	
	    string areaName;
	};
	
	class CommonQryIce{
	    string pageNo;
       	string pageLength;
    	string orderByColumn;
    	string orderBySequence;
	};
	
	//动作信息
	["java:getset"]
	struct HandleActionIce{
		string actionCode;
		string actionName;
	};
	
	sequence<HandleActionIce> HandleActionList;
};