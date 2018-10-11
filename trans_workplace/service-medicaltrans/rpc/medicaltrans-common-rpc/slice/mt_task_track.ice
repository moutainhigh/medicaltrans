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
		module mttask {
			module track {	
				
				// 运送轨迹参数
				["java:getset"]
				struct ItemIce{
    				string userId; // 用户ID
    				string userName; // 用户名字
    				string action; // 动作编码
    				string message; // 跟踪描述
    				string createDate; // 创建时间
    				string specialType; // 特殊类型（退单、超时）
				};
				
				["java:type:java.util.ArrayList"]sequence<ItemIce> ItemList;
				
				// 轨迹详情返回
				["java:getset"]
				struct TrackDetailIce{
    				string message; // 跟踪描述
    				string createDate; // 创建时间
    				string action; // 动作编码
    				string actionName;  // 动作名称
    				string userName;  // 处理人名字
				};
				
				["java:type:java.util.ArrayList"]sequence<TrackDetailIce> ResultList;
				
				// 轨迹详情返回
				["java:getset"]
				struct TrackDetailRspIce{
					string code;
					string message;
					ResultList resultList;
				};
				
				interface MtTaskTrackServiceIce{
					// 创建任务时保存轨迹
					resp::RpcRespIce saveTrackForCreateTask(string taskId, int organId, string beginTime, ItemList itemList);
					
					// 保存单个轨迹
					resp::RpcRespIce saveTaskTrackMessage(int taskId, ItemIce itemIce);
					
					// 查询轨迹
					TrackDetailRspIce queryTaskTrackById(int id);
					
					// 修改beginTime 编辑任务时修改了beginTime
					resp::RpcRespIce updateTrackForEditTask(string taskId, int organId, string beginTime);
				};
			};
		};
	};
};