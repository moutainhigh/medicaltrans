// **********************************************************************
//
// Copyright (c) 2003-2006 ZeroC, Inc. All rights reserved.
// @author dengdong@segimail.com
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
// **********************************************************************


#pragma once
#include <commons.ice>
module segi {
	module medicaltrans {
		module base {
		    module location{
				// 医疗运输-位置入参
				["java:getset"]
				struct MtBuildLocationIceParam{
					string organId;
					string buildId;
					string sid;
					string floorId;
					string floorNum;
					string locationName;
					string locationId;
					string houseSpaces;
					string floorPositX;
					string floorPositY;
					string locateType;
					string mac;
					string remark;
					string curUserId;
					string pageNo;
					string pageLength;
					string defaultLocationId;
				};
				
				["java:getset"]
				struct MtBuildLocationQueryIceParam{
					string organId;
					string locationName;
					string status;
					string pageNo;
					string pageLength;
				};
				
				// 医疗运输-位置详情返回参数
				["java:getset"]
				struct MtBuildLocationDetailIce{
					string locationId;
					string locationName;
					string houseSpaces;
					string houseSpacesNames;
					string floorPositX;
					string floorPositY;
					string locateType;
					string mac;
					string remark;
				};
				
				// 医院位置信息详情返回
				["java:getset"]
				struct MtBuildLocationDetailReturnIce{
					string code;
					string message;
					MtBuildLocationDetailIce mtBuildLocationDetailIce;
				};
				
				// 位置列表返回参数
				["java:getset"]
				struct LocationListIce{
					string locationId;
					string locationName;
					string floorPositX;
					string floorPositY;
					string locateType;
					string mac;
					string status;
				};
				
				["java:type:java.util.ArrayList"]sequence<LocationListIce> LocationListIceList;
				
				// 位置信息分页返回
				["java:getset"]
				struct LocationInfoPaginatorIce{
					string code;
					string message;
					page::RpcPageIce paginator;
					LocationListIceList resultList;
				};
				
				//位置信息查看(非分页)
				["java:getset"]
				struct LocationInfoListReturnIce{
					string code;
					string message;
					LocationListIceList resultList;
				};
				
				
				
				interface MtLocationManagerServiceIce{
					// 位置新增
					resp::RpcRespIce saveMtBuildLocation(MtBuildLocationIceParam mtBuildLocationIceParam);
					
					// 位置详情
					MtBuildLocationDetailReturnIce queryMtBuildLocationDetail(string locationId);
					
					// 位置详情(根据项目id和mac地址)
					MtBuildLocationDetailReturnIce getLocationInfoByMac(MtBuildLocationIceParam mtBuildLocationIceParam);
					
					// 位置编辑
					resp::RpcRespIce updateMtBuildLocation(MtBuildLocationIceParam mtBuildLocationIceParam);
					
					// 位置删除
					resp::RpcRespIce deleteMtBuildLocation(MtBuildLocationIceParam mtBuildLocationIceParam);
					
					// 根据楼层展示分页位置列表
					LocationInfoPaginatorIce queryLocationPageByFloorId(MtBuildLocationIceParam mtBuildLocationIceParam);
					
					//根据组织机构和楼层id查位置信息
					LocationInfoListReturnIce queryLocationListByFloorId(MtBuildLocationIceParam mtBuildLocationIceParam);
					
					//根据组织机构、位置名称、状态加载
					LocationInfoPaginatorIce queryLocationPageByOrgName(MtBuildLocationQueryIceParam mtBuildLocationQueryIceParam);
					
					//根据组织机构、位置名称和默认位置加载
					LocationInfoPaginatorIce queryLocationPageByOrgNameDefault(MtBuildLocationIceParam mtBuildLocationIceParam);
					
					//根据项目id查位置信息（缓存查看）
					string queryLocationByOrganIdRedis(string organId);
				};
			};
		};
	};
};