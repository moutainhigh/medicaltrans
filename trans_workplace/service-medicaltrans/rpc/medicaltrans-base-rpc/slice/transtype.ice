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
		module base{
			module transtype {
				// 基础入参
				["java:getset"]
				class TransTypeIce{
					string transTypeId;
					string groupOrganId;
					string organId;
					string organName;
					string transTypeParentCode;
					string transTypeParentCodeName;
					string transTypeCode;
					string transTypeName;
					string standardMinite;
					string warnMinite;
					string status;
					string statusName;
				//	string remark;
					string createDate;
					string createBy;
					string updateDate;
					string updateBy;
					//分页
					string pageNo;
					string pageLength;
					string timeOut;
				//	string oprGuide;
				};
				
				// 运送类型详情
				["java:getset"]
				class TransTypeDetailIce extends TransTypeIce {
					string remark;
					string oprGuide;
				};
				
				// 运送类型返回
				["java:getset"]
				struct TransTypeReturnIce {
					string code;
					string message;
					TransTypeIce transTypeIce;
				};
				
				// 运送类型详情返回
				["java:getset"]
				struct TransTypeDetailReturnIce {
					string code;
					string message;
					TransTypeDetailIce transTypeDetailIce;
				};
				
				// 运送类型基础数据
				["java:getset"]
				struct TransTypeBaseIce{
					string transTypeId;
					string transTypeName;
				};
				
				// 运送类型基础数据返回参数的集合
				["java:type:java.util.ArrayList"]sequence<TransTypeBaseIce> TransTypeBaseListIce;
				
				// 运送类型分页返回参数的集合
				["java:type:java.util.ArrayList"]sequence<TransTypeIce> TransTypeListIce;
				
				// 运送类型分页返回
				["java:getset"]
				struct TransTypeRetPageIce{
					string code;
					string message;
					page::RpcPageIce paginator;
					TransTypeListIce transTypeListIce;
				};
				
				// 运送类型列表返回
				["java:getset"]
				struct TransTypeRetIce{
					string transTypeParentCode;
					string transTypeParentCodeName;
					TransTypeBaseListIce transTypeList;
				};
				
				// 运送类型分页返回参数的列表
				["java:type:java.util.ArrayList"]sequence<TransTypeRetIce> TransTypeRetListIce;
				
				// 运送类型列表全部返回
				["java:getset"]
				struct TransTypeAllListIce{
					string code;
					string message;
					TransTypeRetListIce transTypeList;
				};
				
				["java:type:java.util.ArrayList"]sequence<int> TransTypeIdList;
				
				// 查询运送类型信息
				["java:getset"]
				struct TransTypeInfoIce{
					string code;
					string message;
					TransTypeListIce resultList;
				};
				
				// 运送类型操作指引返回
				["java:getset"]
				struct TransTypeOprGuideIce{
					string transTypeId;
					string transTypeName;
					string transTypeParentCode;
					string transTypeParentCodeName;
					string oprGuide;
				};
				
				// 查询运送操作指引信息
				["java:getset"]
				struct TransTypeOprGuideRetIce{
					string code;
					string message;
					TransTypeOprGuideIce transTypeOprGuideIce;
				};
				
				interface TransTypeServiceIce{
					// 运送类型新增	
					resp::RpcRespIce saveTransType(TransTypeDetailIce transTypeDetailIce);
					// 运送类型修改
					resp::RpcRespIce updateTransType(TransTypeDetailIce transTypeDetailIce);
					// 运送类型状态修改
					resp::RpcRespIce updateStatusTransType(TransTypeIce transTypeIce);
					// 运送类型详情
					TransTypeDetailReturnIce queryTransType(TransTypeIce transTypeIce);
					// 运送类型分页
					TransTypeRetPageIce transTypePage(TransTypeIce transTypeIce);
					// 运送类型列表
					TransTypeRetPageIce transTypeList(TransTypeIce transTypeIce);
					// 运送类型列表全部展示
					TransTypeAllListIce transTypeAllList(TransTypeIce transTypeIce);
					// 运送类型批量导入
					// resp::RpcRespIce uploadTransTypeList(TransTypeListIce transTypeListIce);
					// 运送类型操作指引
					TransTypeOprGuideRetIce queryOprGuide(TransTypeIce transTypeIce);
				};
			};
		};
	};
};