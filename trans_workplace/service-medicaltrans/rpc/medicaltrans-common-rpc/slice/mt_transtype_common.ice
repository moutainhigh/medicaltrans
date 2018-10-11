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
				module transtype {	
					// 返回参数
					["java:getset"]
					struct TransTypeInfo{
						    string id;
							// 组织机构id
						    string organId;
						   	// 药品运送运输量
							string drugTransTypeAmount;
							// 药品平均运输时间
							string drugTransTypeAvgTime;
							// 标本运输量
							string sampleTransTypeAmount;
							// 标本平均运输时间
							string sampleTransTypeAvgTime;
							// 血制品运输量
							string bloodTransTypeAmount;
							// 血制品平均运输时间
							string bloodTransTypeAvgTime;
							// 病人护送运输量
							string patientTransTypeAmount;
							// 病人平均运输时间
							string patientTransTypeAvgTime;
							// 物品运输量
							string goodTransTypeAmount;
							// 物品平均运输时间
							string goodTransTypeAvgTime;
							// 文书运输量
							string bookTransTypeAmount;
							// 文书平均运输时间
							string bookTransTypeAvgTime;
					};
					
					// 运送类型分页返回参数的集合
					//["java:type:java.util.ArrayList"]sequence<TransTypeInfo> TransTypeInfoList;
					
					// 查询运送类型信息
					["java:getset"]
					struct TransTypeInfoReturnIce{
						string code;
						string msg;
						TransTypeInfo data;
					};
					
					interface ReportCommonTransTypeServiceIce{
						// 新增运送类型
						resp::RpcRespIce saveTransType(TransTypeInfo transTypeInfo);
						// 根据id修改运送类型
						resp::RpcRespIce updateTransTypeById(int id, TransTypeInfo transTypeInfo);
						// 根据id删除运送类型
						resp::RpcRespIce deleteTransTypeById(int id);
						// 根据id查询运送类型信息
						TransTypeInfoReturnIce getTransTypeById(int id);
					};
				};
			};
		};	
	};
};