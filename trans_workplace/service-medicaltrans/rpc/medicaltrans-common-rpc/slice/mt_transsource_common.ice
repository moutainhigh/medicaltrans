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
				module transsource {	
					// 返回参数
					["java:getset"]
					struct TransSourceInfo{
						    string id;
							// 组织机构id
						    string organId;
						    // 科室数
						    string fromHouseNum;
							// 科室1id
							string fromHouseId1;
							// 科室1运送量
							string fromHouseAmount1;
							// 科室2id
							string fromHouseId2;
							// 科室2运送量
							string fromHouseAmount2;
							// 科室3id
							string fromHouseId3;
							// 科室3运送量
							string fromHouseAmount3;
							// 科室4id
							string fromHouseId4;
							// 科室4运送量
							string fromHouseAmount4;
							// 科室5id
							string fromHouseId5;
							// 科室5运送量
							string fromHouseAmount5;
							// 其他科室运送量
							string fromHouseAmountOthers; 
					};
					
					// 运送类型分页返回参数的集合
					//["java:type:java.util.ArrayList"]sequence<TransTypeInfo> TransTypeInfoList;
					
					// 查询运送类型信息
					["java:getset"]
					struct TransSourceInfoReturnIce{
						string code;
						string msg;
						TransSourceInfo data;
					};
					
					interface MtCommonTransSourceServiceIce{
						// 新增运送来源
						resp::RpcRespIce saveTransSource(TransSourceInfo transSourceInfo);
						// 根据id修改运送来源
						resp::RpcRespIce updateTransSourceById(int id, TransSourceInfo transSourceInfo);
						// 根据id删除运送来源
						resp::RpcRespIce deleteTransSourceById(int id);
						// 根据id查询运送类型信息
						TransSourceInfoReturnIce getTransSourceById(int id);
					};
				};
			};
		};	
	};
};