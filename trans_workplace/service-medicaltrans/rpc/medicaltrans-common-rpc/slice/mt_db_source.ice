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
		module base {
			module  dbsource {	
				
				// 个人月运送量返回参数
				["java:getset"]
				struct DbSourceRuleIce{
					string groupId;
					string dataSourceIdx;
					string tableIdx;
				};
				
				
				// 个人月运送量返回参数
				["java:getset"]
				struct DbSourceRuleRspIce{
					string code;
					string message;
					DbSourceRuleIce dbSourceRuleIce;
				};
				
				interface MtDbSourceRuleServiceIce{
				
					DbSourceRuleRspIce getDbSourceRuleByGroupId(int groupOrganId);
				};
			};
		};
	};
};