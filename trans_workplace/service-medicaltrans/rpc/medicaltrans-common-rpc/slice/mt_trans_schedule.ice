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
				module transschedule {
					["java:type:java.util.ArrayList"]
					sequence<int> OrganIdList;
					
					["java:getset"]
					struct TransScheduleIceParams{
						// 一级物业Id
						string groupOrganId;
						// 组织机构Id
						string organId;
						// 运送状态
					    string runningStatus;
					    string userId;
					    string userOrganId;
					    string pageLength;
					    string pageNo;
					};
					
					["java:getset"]
					struct TransScheduleIce{
						// 一级物业Id
						string groupOrganId;
						// 一级物业Id名称
						string groupOrganName;
						// 组织机构Id
						string organId;
						// 组织机构Id
						string organName;
						// 项目开始使用时间
					    string startDate;
						// 上次计算时间
					    string paramDate;
						// 运送状态
					    string runningStatus;
					    // 运送状态
					    string runningStatusName;
						// 创建时间
					    string createDate;
						// 修改时间
					    string updateDate;
					    // begin liuyi
					    string excDate;  //执行时间
					    string lastExcDate;  //上月执行时间
					    string excEndDate;  //本月截止时间
					    string status;  //状态
					    // end
					};
					["java:type:java.util.ArrayList"]sequence<TransScheduleIce> TransScheduleIceList;
					
					dictionary<int, OrganIdList> GroupOrganMap;
					
					// 排程表信息分页返回
					["java:getset"]
					struct TransSchedulePaginatorIce{
						string code;
						string message;
						page::RpcPageIce paginator;
						TransScheduleIceList transScheduleIceList;
					};
					
					["java:getset"]
					struct TransScheduleByOrganIdIce{
						string code;
						string message;
						TransScheduleIce transScheduleIce;
					};
					
					// 排程信息分页返回
					["java:getset"]
					struct TransScheduleGroupOrganMapIce{
						string code;
						string message;
						GroupOrganMap groupOrganMap;
					};
					
					// 使用医疗项目
					["java:getset"]
					interface TransScheduleServiceIce{
						
						TransSchedulePaginatorIce queryTransSchedulePaginator(TransScheduleIceParams params);
	
						TransScheduleGroupOrganMapIce getTransScheduleMap();
						
						TransScheduleByOrganIdIce queryTransSchedule(int organId);
						
					};
					
				};
			};
		};
	};
};