// **********************************************************************
//
// Copyright (c) 2003-2006 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
// **********************************************************************

// 个人运送量月报表ICE文件
#pragma once
#include <commons.ice>
module segi {
	module medicaltrans {
		module report {
			module pervolume{
				module month{
					// 运送员运送量月报表入参
					["java:getset"]
					struct PerTaskAmoMonthParam{
						// 项目ID
						string organId;	
						// 运送员IDS
						string userIds;
						// 月份	
						string cycleYm;	
						// 页码
						string pageNo;
						// 每页条数	
						string pageLength;
						// 运送员ID
						string userId;
						// 服务组Id
						string sergroupIds;
					};
					
					// 运送员运送量月报表信息ICE返回对象
					["java:getset"]
					struct PerTaskAmoMonthIce{
						string organId;	
						// 组织id
                		string organName;	
                		// 组织名称
                		string sergroupId;	
                		// 服务组id
               			string sergroupName;	
               			// 服务组名称
                		string userId;	
                		// 运送员id
                		string userName;	
                		// 运送员姓名
                		string userNo;	
                		// 运送员工号
                		string transAmount;	
                		// 总运送量
                		string dispatchAmount;	
                		// 调度任务运送量
                		string dispatchSatisfactionRatio;	
                		// 调度任务满意率
                		string dispatchTimelyRatio;	
                		// 调度任务及时率
                		string autonomousAmount;	
                		// 自主任务运送量
                		string autonomousSatisfactionRatio;	
                		// 自主任务满意率
                		string autonomousTimelyRatio;	
                		// 自主任务及时率
                		string fixedAmount;	
                		// 固定任务运送量
                		string fixedSatisfactionRatio;	
                		// 固定任务满意率
                		string fixedTimelyRatio;	
                		// 固定任务及时率
                		string groupOrganId;
                		// 一级物业Id
                		string createDate;
					};
					
					// 运送员运送量月报表信息ICE集合返回对象
					["java:type:java.util.ArrayList"]sequence<PerTaskAmoMonthIce> PerTaskAmoMonthIceList;
					
					// 运送员运送量月报表分页返回对象
					["java:getset"]
					struct PerTaskAmoMonthPageResp{
						string code;
						string msg;
						page::RpcPageIce paginator;
						PerTaskAmoMonthIceList resultList;
					};
					
					// 运送员运送量月报表业务接口
					["java:getset"]
					interface PerTaskAmoMonthServiceIce{
						// 运送员运送量月报表分页查询
						PerTaskAmoMonthPageResp getPerTaskAmoMonthPage(PerTaskAmoMonthParam params);
					};
					
					// 个人月排名信息
					["java:getset"]
					struct PersonalVolumeMonthIce{
						string rank;
						string userId;
						string userName;
						string userNo;
						string team;
						string transVolume;
					};
					
					// 当前用户对象
					["java:getset"]
					struct CurUserRankIce{
						string userId;
						string rank;
						string transVolume;
					};
					
					["java:type:java.util.ArrayList"]sequence<PersonalVolumeMonthIce> RankList;
					
					// 个人月排名分页返回对象
					["java:getset"]
					struct PersonalVolumeMonthPaginator{
						string code;
						string msg;
						page::RpcPageIce paginator;
						RankList resultList;
						CurUserRankIce curUserRankIce;
					};
						
					// 个人月排名查询接口
					["java:getset"]
					interface PersonalVolumeMonthServiceIce{
						// 0402_本月运送量排名
						PersonalVolumeMonthPaginator getMonthTransVolumeRank(PerTaskAmoMonthParam params);
					};
							
				};
			};
		};
	};
};