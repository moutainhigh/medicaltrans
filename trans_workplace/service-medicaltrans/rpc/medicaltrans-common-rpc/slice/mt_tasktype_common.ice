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
			module transType {	
				// 返回参数
				["java:getset"]
				struct TransTypeInfo{
					string transTypeId;
					string transTypeName;
					string status;
					//预警时间
					string warnMinite; 
					//标准时间
					string standardMinite;  
				};
				
				// 运送类型分页返回参数的集合
				["java:type:java.util.ArrayList"]sequence<TransTypeInfo> TransTypeInfoList;
				
				["java:type:java.util.ArrayList"]sequence<int> TransTypeIdList;
				
				// 查询运送类型信息
				["java:getset"]
				struct TransTypeInfoReturnIce{
					string code;
					string message;
					TransTypeInfoList resultList;
				};
				
				interface MtCommonTransTypeServiceIce{
					// 根据运送小类id List 查询运送类型信息
					TransTypeInfoReturnIce getTransTypeInfoByTransTypeIdList(TransTypeIdList transTypeIdList);
					// 刷新运送类型缓存
					resp::RpcRespIce refreshRedisTransType(string organIds);
					
					// 删除批次表和中间表
					resp::RpcRespIce delTransTypeImpTable();
					
					// 修改导入批次表状态
					resp::RpcRespIce updateTransTypeImpBatchMsg();
				};
			};
		};
	};
};