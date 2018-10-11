// **********************************************************************
//
// Copyright (c) 2003-2006 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
// **********************************************************************

// 个人运送量日排名ICE文件
#pragma once
#include <commons.ice>
module segi {
	module medicaltrans {
		module report {
			module pervolume{
				module day{ 
					// 运送员运送量日排名入参
					["java:getset"]
					struct PerDayRankParam{
						// 项目ID
						string organId;	
						// 开始日期	
						string beginTime;
						// 结束日期	
						string endTime;
						// 服务组Id
						string sergroupIds;
						// 运送员ID
						string userId;
					};
					
					// 个人日排名信息ICE返回对象
					["java:getset"]
					struct PerVolDayIce{
						// 排名
						string rank;
						// 用户Id
						string userId;
						// 用户名称
						string userName;
						// 用户工号
						string userNo;
						// 服务服Id
						string sergroupId;
						// 服务服名称
						string sergroupName;
						// 运送量
						string transVolume;
						// 一级物业Id
						string groupOrganId;
						// 组织机构Id
						string organId;
						// 组织机构名称
						string organName;
					};
					
					// 个人日排名信息ICE返回对象集合
					["java:type:java.util.ArrayList"]sequence<PerVolDayIce> DayRankList;
					
					// 个人日排名分页返回对象
					["java:getset"]
					struct PerVolDayRsp{
						string code;
						string msg;
						DayRankList resultList;
					};
					
					// 个人日排名查询接口
					["java:getset"]
					interface PerVolDayServiceIce{
						// 0951_个人日运送量排名
						PerVolDayRsp getDayTransVolRank(PerDayRankParam params);
					};
				};
				
			};
		};
	};
};