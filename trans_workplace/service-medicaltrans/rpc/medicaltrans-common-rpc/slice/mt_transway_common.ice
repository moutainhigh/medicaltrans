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
		module report {
			module common {	
				module transway {	
					// 返回参数
					["java:getset"]
					struct TransWayInfo{
						    string id;
							// 组织机构id
						    string organId;
							// 步行运输量
							string walkTypeAmount;
							// 推床运输量
							string pushingBedTypeAmount;
							// 平车运输量
							string flatCartypeAmount;
							// 轮椅运输量
							string wheelchairTypeAmount;
					};
					
					// 运送类型分页返回参数的集合
					//["java:type:java.util.ArrayList"]sequence<TransTypeInfo> TransTypeInfoList;
					
					// 查询运送类型信息
					["java:getset"]
					struct TransWayInfoReturnIce{
						string code;
						string msg;
						TransWayInfo data;
					};
					
					interface MtCommonTransWayServiceIce{
						// 新增运送来源
						resp::RpcRespIce saveTransWay(TransWayInfo transWayInfo);
						// 根据id修改运送来源
						resp::RpcRespIce updateTransWayById(int id, TransWayInfo transWayInfo);
						// 根据id删除运送来源
						resp::RpcRespIce deleteTransWayById(int id);
						// 根据id查询运送类型信息
						TransWayInfoReturnIce getTransWayById(int id);
					};
				};
			};
		};	
	};
};