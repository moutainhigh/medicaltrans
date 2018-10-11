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
			module ratio{
				["java:getset"]
				struct ReportCommonParam{
					string organId;
					string cycleY;
				};
				
				["java:getset"]
				struct TaskTypeIce{
					string dispatchTask;
					string dispatchTaskAmount;
					string autonomousTask;
					string autonomousTaskAmount;
					string fixedTask;
					string fixedTaskAmount;
				};
				
				["java:getset"]
				struct TranstypeIce{
					string drugTransTypeName;
					string drugTransTypeAmount;
					string sampleTransTypeName;
					string sampleTransTypeAmount;
					string bloodTransTypeName;
					string bloodTransTypeAmount;
					string patientTransTypeName;
					string patientTransTypeAmount;
					string goodTransTypeName;
					string goodTransTypeAmount;
					string bookTransTypeName;
					string bookTransTypeAmount;
				};
				
				["java:getset"]
				struct TaskDateSourceIce{
					string webFromHouseName;
					string webFromHouseAmount;
					string wechatFromHouseName;
					string wechatFromHouseAmount;
					string padFromHouseName;
					string padFromHouseAmount;
					string appFromHouseName;
					string appFromHouseAmount;
				};
				
				["java:getset"]
				struct TaskTypeReturnIce{
					string code;
					string msg;
					TaskTypeIce data;
				};
				
				["java:getset"]
				struct TranstypeReturnIce{
					string code;
					string msg;
					TranstypeIce data;
				};
				
				["java:getset"]
				struct TaskDateSourceReturnIce{
					string code;
					string msg;
					TaskDateSourceIce data;
				};
				
				["java:getset"]
				interface RatioReportServiceIce{
					TaskTypeReturnIce queryTaskTypeRatio(ReportCommonParam reportCommonParam);
					TranstypeReturnIce queryTransTypeRatio(ReportCommonParam reportCommonParam);
					TaskDateSourceReturnIce queryTaskDateSourceRatio(ReportCommonParam reportCommonParam);
				};
			};
		};
	};
};