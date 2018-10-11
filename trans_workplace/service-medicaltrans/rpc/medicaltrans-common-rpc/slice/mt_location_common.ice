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
		module location {	
		 	module  common {	
				// 位置返回参数
				["java:getset"]
				struct MtLocationInfoIce{
					// 位置id
					string locationId;
					// 位置名称
					string locationName;
					// 状态
					string status;
					//定位方式
					string locateType;
					//mac地址
					string mac;
				};
				
				["java:type:java.util.ArrayList"]sequence<int> LocationIdList;
				
				["java:type:java.util.ArrayList"]sequence<MtLocationInfoIce> LocationInfoList;
				
				["java:getset"]
				struct LocationInfoListReturnIce{
					string code;
					string message;
					LocationInfoList locationInfoList;
				};
				
				["java:getset"]
				struct LocationInfoReturnIce{
					string code;
					string message;
					MtLocationInfoIce mtLocationInfoIce;
				};
				
				// 科室列表返回参数
				["java:getset"]
				struct LocationInfoIce{
					// 位置id
					string locationId;
					// 位置名称
					string locationName;
					// 楼栋id
					string buildId;
					// 单元id
					string sid;
					// 楼层id
					string floorId;
					// 一级组织id
					string groupOrganId;
					// 项目id
					string organId;
					// 状态
					string status;
				};
				
				["java:type:java.util.ArrayList"]sequence<LocationInfoIce> LocationInfoIceList;
				
				// 科室列表返回对象
				["java:getset"]
				struct LocationInfoListReturn{
					string code;
					string msg;
					LocationInfoIceList resultList;
				};
				
				// 科室返回对象
				["java:getset"]
				struct LocationInfoReturn{
					string code;
					string msg;
					LocationInfoIce locationInfoIce;
				};
				
				interface MtLocationCommonServiceIce{
					// 根据传过来的位置IdList和项目查询位置信息（查缓存）
					LocationInfoListReturnIce getLocationInfoByRefIdList(int organId, LocationIdList locationIdList);
					
					//根据传过来的位置IdList和项目查询位置信息(查数据库)
					LocationInfoListReturnIce getLocationInfoBylocationIdList(LocationIdList locationIdList);
					
					// 根据nfc mac地址查询科室信息
					LocationInfoReturnIce getLocationInfoByMac(int organId, string mac);
					
					// 缓存查询科室信息  liuyi
					LocationInfoListReturn queryLocationInfoByOrganIdList(int organId);
					
					// 缓存查询科室信息  liuyi
					LocationInfoReturn queryLocationInfo(int organId, int locationId);
					
					// 查询科室信息列表 liuyi
					LocationInfoListReturn queryLocationList(LocationIdList locationIdList);
					
					// 缓存查询科室信息  liuyi
					LocationInfoListReturnIce queryLocationInfoListByOrganId(int organId, LocationIdList locationIdList);
					
					// 位置缓存刷新
					resp::RpcRespIce refreshRedisLocaiton();
					
					// 人员位置缓存刷新
					resp::RpcRespIce refreshRedisUserLocaiton();
				};
			};
		};
	};
};