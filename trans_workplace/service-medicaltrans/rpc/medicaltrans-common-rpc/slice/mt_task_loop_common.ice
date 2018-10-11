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
		module common {
			module taskloop {
				// 修改循环任务状态入参
				["java:getset"]
				struct TaskLoopStatusParam{
					string taskLoopId;
					string status;
					string updateBy;
					string loseRemark;
				};
				
				// 查询循环任务入参
				["java:getset"]
				struct MtTaskLoopIce{
					string taskLoopId;
				    string organId;
				    string transTypeParentCode;
				    string transTypeId;
				    string taskName;
				    string transTools;
				    string loopType;
				    string taskBeginTime;
				    string taskEndTime;
				    string loopDays;
				    string loopWeeks;
				    string loopMonths;
				    string preGenerateMinute;
				    string status;
				    string createDate;
				    string createBy;
				    string updateDate;
				    string updateBy;
				    string taskCron;
				    string loseRemark;
				    string limitMinute;
				    string groupOrganId;
				};
				
				["java:type:java.util.ArrayList"]sequence<MtTaskLoopIce> MtTaskLoopIceList;
				
				// 查询循环任务出参
				["java:getset"]
				struct MtTaskLoopIceListReturn{
					string code;
					string msg;
					MtTaskLoopIceList resultList;
				};
				
				["java:type:java.util.ArrayList"]sequence<int> IntegerList;
				
				["java:getset"]
				struct ReturnInteger{
					string code;
					string msg;
					IntegerList resultList;
				};
				
				["java:getset"]
				interface MtTaskLoopCommonServiceIce{
					// 写为公共方法
					// 0304_循环任务停用启用、删除
					resp::RpcRespIce updateTaskLoopStatus(TaskLoopStatusParam taskLoopStatusParam);
					// 根据organId查出按月的任务
					MtTaskLoopIceListReturn getMtTaskLoopListByMonth(int organId);
					// 根据organId查出按周的任务
					MtTaskLoopIceListReturn getMtTaskLoopListByWeek(int organId);
					// 根据循环任务id查询线路IdList
					ReturnInteger selectLocationIdList(string taskLoopId);
					// 根据循环任务id查询执行人IdList
					ReturnInteger selectUserIdList(string taskLoopId);
				};
			};
		};
	};
};