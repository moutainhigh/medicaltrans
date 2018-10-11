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
		module common {
			module report {
				module organ {
					["java:type:java.util.ArrayList"]
					sequence<int> OrganIdList;
					
					["java:getset"]
					struct ReportOrganIce{
						string code;
						string message;
						OrganIdList organIds;
					};
					
					
					// 使用医疗项目
					["java:getset"]
					interface TransReportOrganServiceIce{
						resp::RpcRespIce add(int organId);
						
						ReportOrganIce getOragnList();
					};
				};
			};
		};
	};
};