// **********************************************************************
//
// Copyright (c) 2003-2006 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
// **********************************************************************


#pragma once
#include <commons.ice>
#include <segi_wh_common.ice>
module segi {
	module medicaltrans {
		module base {
			module taskloop{
				// 新增或者修改循环任务入参
				["java:getset"]
				struct TaskLoopParam{
					string taskLoopId;
					string organId;
					string taskName;
					string transTypeParentCode;
					string transTypeId;
					string houseIds;
					string transTools;
					string loopType;
					string taskBeginTime;
					string taskEndTime;
					string loopMonths;
					string loopWeeks;
					string loopDays;
					string userIds;
					// 提前触发时间(分钟)
					string preGenerateMinute;
					// 其他
					string createBy;
					string updateBy;
				};
				
				// 修改循环任务状态入参
				["java:getset"]
				struct TaskLoopStatusParam{
					string organId;
					string taskLoopId;
					string status;
					string updateBy;
					string loseRemark;
				};
				
								
				// 科室信息
				["java:getset"]
				struct House{
					string houseId;
					string houseName;
					string status;
				};
				
				["java:type:java.util.ArrayList"]sequence<House> HouseList;
				
				// 循环任务信息
				["java:getset"]
				struct TaskLoopIce{
					string taskLoopId;
					string organId;
					string organName;
					string taskName;
					string transTypeParentCode;
					string transTypeParentName;
					string transTypeId;
					string transTypeName;
					string transTools;
					string transToolsName;
					string houseIds;
					string houseNames;
					string status;
					string statusName;
					string loopType;
					string loopMonths;
					string loopWeeks;
					string loopDays;
					string taskBeginTime;
					string taskEndTime;
					string taskBeginTimeStr;
					string taskEndTimeStr;
					string userIds;
					string userNames;
					HouseList houseList;
					segiwh::common::UserList userList;
					string preGenerateMinute;
					string loseRemark;
				};
				
				// 循环任务详情返回对象
				["java:getset"]
				struct TaskLoopInfo{
					string code;
					string msg;
					TaskLoopIce taskLoopIce;
				};
				
				["java:type:java.util.ArrayList"]sequence<TaskLoopIce> TaskLoopList;
				
				// 循环任务分页入参
				["java:getset"]
				struct TaskLoopPageParam{
					string organId;
					string taskName;
					string transTypeParentCode;
					string status;
					string pageNo;
					string pageLength;
				};
				
				// 循环任务分页返回对象
				["java:getset"]
				struct TaskLoopPaginator{
					string code;
					string msg;
					page::RpcPageIce paginator;
					TaskLoopList resultList;
				};
				
				// 科室信息列表返回
				["java:getset"]
				struct HouseInfo{
					string code;
					string msg;
					HouseList houseList;
				};
				
				// 科室信息列表返回
				["java:getset"]
				struct UserInfo{
					string code;
					string msg;
					segiwh::common::UserList userList;
				};
				
				// 循环任务业务接口
				["java:getset"]
				interface TaskLoopServiceIce{
					// 0301_循环任务分页列表
					TaskLoopPaginator queryTaskLoopByPage(TaskLoopPageParam taskLoopPageParam);
					// 0302_循环任务新建
					resp::RpcRespIce saveTaskLoop(TaskLoopParam taskLoopParam);
					// 0303_循环任务编辑
					resp::RpcRespIce updateTaskLoop(TaskLoopParam taskLoopParam);
					// 0304_循环任务停用启用、删除
					resp::RpcRespIce updateTaskLoopStatus(TaskLoopStatusParam taskLoopStatusParam);
					// 0305_循环任务详情展示
					TaskLoopInfo queryTaskLoopDetail(string taskLoopId);
					// 0306_根据循环任务id查询科室信息列表
					HouseInfo queryHouseList(string taskLoopId);
					// 0308_根据循环任务id查询人员信息列表
					UserInfo queryUserInfoList(string taskLoopId);
				};
			};
		};
	};
};