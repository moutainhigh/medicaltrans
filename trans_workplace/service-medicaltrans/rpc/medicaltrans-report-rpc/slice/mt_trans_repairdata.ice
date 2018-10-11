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
		module report {
			module repairdata {
			
				["java:getset"]
				struct RepairReportDataIceParams{
					// 组织机构Id
					string organId;
					// 密码
				    string pwd;
				    string beginTime;
				    string endTime;
				    string userId;
				    string userOrganId;
				    string groupOrganId;
				};
				
				// 数据修复
				["java:getset"]
				interface RepairDataServiceIce{
					
					resp::RpcRespIce repairTaskReportData(RepairReportDataIceParams params);
										
				};
				
			};
		};
	};
};