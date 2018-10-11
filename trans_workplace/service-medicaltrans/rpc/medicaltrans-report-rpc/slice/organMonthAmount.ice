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
			module organmonth{
				//项目月运送量入参
				["java:getset"]
				struct TransProfileIceParam{
					string organId;
					string cycleY;
				};
			
				//  运送概况对象
				["java:getset"]
				struct TransProfileIce{
					string transUserAmountAverage;
					string transAmountTotal;
					string transAmountAverage;
					string instantTaskTimeAverage;
					string timelyRatio;
					string satisfactionRatio;
				};
				
				// 运送概况返回参数
				["java:getset"]
				struct TransProfileReturnIce{
					string code;
					string msg;
					TransProfileIce transProfile;
				};
				
				//每月运送量趋势分析对象
				["java:getset"]
				struct OrganMonthAmontIce{
					string cycleYm;
					string transAmount;
				};
				
				// 每月运送量趋势分析对象集合返回对象
				["java:type:java.util.ArrayList"]sequence<OrganMonthAmontIce> OrganMonthAmontIceList;
				
				// 每月运送量趋势分析返回参数
				["java:getset"]
				struct OrganMonthAmontReturnIce{
					string code;
					string msg;
					OrganMonthAmontIceList organMonthAmontIceList;
				};
				
				//及时率满意率趋势分析
				["java:getset"]
				struct TimelyAmountMonthIce{
					string cycleYm;
					string timelyRatio;
					string satisfactionRatio;
				};
				
				// 及时率满意率趋势分析集合返回对象
				["java:type:java.util.ArrayList"]sequence<TimelyAmountMonthIce> TimelyAmountMonthIceList;
				
				// 及时率满意率趋势分析返回参数
				["java:getset"]
				struct TimelyAmountMonthReturnIce{
					string code;
					string msg;
					TimelyAmountMonthIceList timelyAmountMonthIceList;
				};
				
				//运送员每月平均运送量趋势分析对象
				["java:getset"]
				struct UserAmountMonthIce{
					string cycleYm;
					string transAmountAverage;
				};
				
				// 运送员每月平均运送量趋势分析集合返回对象
				["java:type:java.util.ArrayList"]sequence<UserAmountMonthIce> UserAmountMonthIceList;
				
				// 运送员每月平均运送量趋势分析返回参数
				["java:getset"]
				struct UserAmountMonthReturnIce{
					string code;
					string msg;
					UserAmountMonthIceList userAmountMonthIceList;
				};
				
				//即时任务响应时间趋势分析对象
				["java:getset"]
				struct AvgRespTimeIce{
					string cycleYm;
					string avgRespTime;
				};
				
				// 即时任务响应时间趋势分析集合返回对象
				["java:type:java.util.ArrayList"]sequence<AvgRespTimeIce> AvgRespTimeIceList;
				
				// 即时任务响应时间趋势分析集合返回对象
				["java:getset"]
				struct AvgRespTimeReturnIce{
					string code;
					string msg;
					AvgRespTimeIceList avgRespTimeIceList;
				};
				
				// 项目月运送量接口
				["java:getset"]
				interface OrganMonthAmountServiceIce{
					// 运送概况接口
					TransProfileReturnIce getTransProfile(string organId,string cycleY);
					//每月运送量趋势分析
					OrganMonthAmontReturnIce queryAmountMonth(string organId,string cycleY);
					//及时率满意率趋势分析
					TimelyAmountMonthReturnIce queryTimelyAmountMonth(string organId,string cycleY);
					//运送员每月平均运送量趋势
					UserAmountMonthReturnIce queryUserAmountMonth(string organId,string cycleY);
					//即时任务响应时间趋势分析
					AvgRespTimeReturnIce queryHisRespTime(string organId,string cycleY);
				};
			};
		};
	};
};