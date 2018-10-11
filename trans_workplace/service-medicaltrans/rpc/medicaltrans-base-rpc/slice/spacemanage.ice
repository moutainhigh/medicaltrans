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
			module spacemanage {
				// 楼栋简单信息
				["java:getset"]
				struct BuildSimpleIce{
					// 楼栋ID
					string buildId;
					// 楼栋名称
					string name;
					// 排序号
					string sortNo;
				};
				
				// 楼栋简单信息返回List集合
				["java:type:java.util.ArrayList"]sequence<BuildSimpleIce> BuildSimpleIceList;
				
				// 楼栋简单信息List返回
				["java:getset"]
				struct BuildSimpleRetIce{
					string code;
					string message;
					BuildSimpleIceList resultList;
				};
				
				// 楼栋简单信息返回
				["java:getset"]
				struct BuildSimpleReturnIce{
					string code;
					string message;
					BuildSimpleIce data;
				};
				
				// 单元简单信息
				["java:getset"]
				struct UnitSimpleIce{
					string unitId;
					string unitName;
					string buildId;
				};
				
				// 单元简单信息返回
				["java:getset"]
				struct UnitSimpleReturnIce{
					string code;
					string message;
					UnitSimpleIce data;
				};
				
				// 楼层简单信息
				["java:getset"]
				struct FloorSimpleIce{
					string floorId;
					string floorName;
					string unitId;
					string buildId;
				};
				
				// 楼层简单信息返回
				["java:getset"]
				struct FloorSimpleReturnIce{
					string code;
					string message;
					FloorSimpleIce data;
				};
				
				// 位置信息
				["java:getset"]
				struct PositionIce{
					string positionId;
					string positionName;
					string upPositionId;
					string status;
					string positionType;
					string subPositionType;
					// 单元ID（个性化位置接口用）
					string unitId;
				};
				
				["java:type:java.util.ArrayList"]sequence<PositionIce> PositionIceList;
				
				// 位置简单信息List返回
				["java:getset"]
				struct PositionRetIce{
					string code;
					string message;
					PositionIceList resultList;
				};
				
				// 楼栋详情信息
				["java:getset"]
				struct BuildDetailIce{
					string buildId;
					string buildName;
					string buildFloor;
					string status;
					string statusName;
					string elevatorNum;
					string contractor;
					string developers;
					string high;
					string floorArea;
					string upFloor;
					string underFloor;
					string upHigh;
					string underHigh;
					string sortNo;
					string remark;
					string picUrl;
				};
				
				// 楼栋详情信息返回
				["java:getset"]
				struct BuildDetailReturnIce{
					string code;
					string message;
					BuildDetailIce data;
				};
				
				// 单元详情信息
				["java:getset"]
				struct UnitDetailIce{
					string unitId;
					string unitName;
					string sortNo;
					string status;
					string statusName;
					// string elevatorNum;
					string remark;
					string picUrl;
				};
				
				// 单元详情信息返回
				["java:getset"]
				struct UnitDetailReturnIce{
					string code;
					string message;
					UnitDetailIce data;
				};
				
				// 楼层详情信息
				["java:getset"]
				struct FloorDetailIce{
					string floorId;
					string floorName;
					string floorArea;
					string floorType;
					string sortNo;
					string status;
					string statusName;
					string planUrl;
					string remark;
					string picUrl;
				};
				
				// 楼层详情信息返回
				["java:getset"]
				struct FloorDetailReturnIce{
					string code;
					string message;
					FloorDetailIce data;
				};
				
				// 科室信息
				["java:getset"]
				struct HouseInfoIce{
					string floorId;
					string houseId;
					string houseName;
					string sortNo;
					string status;
					string statusName;
				};
				
				["java:type:java.util.ArrayList"]sequence<HouseInfoIce> HouseInfoIceList;
				
				// 楼栋简单信息List返回
				["java:getset"]
				struct HouseInfoReturnIce{
					string code;
					string message;
					HouseInfoIceList resultList;
				};
				
				// 空间管理
				interface SpaceManageServiceIce{
					// 通过项目ID查询所有楼栋简单信息
					BuildSimpleRetIce queryBuildByCommID(string communityId);
					
					// 根据楼栋id查询楼栋简单信息
					BuildSimpleReturnIce getBuildSimpleInfo(string buildId);
					
					// 根据单元id查询单元简单信息
					UnitSimpleReturnIce getUnitSimpleInfo(string unitId);
					
					// 通过楼层ID查询楼层简单信息
					FloorSimpleReturnIce getFloorInfoByFloorId(string floorId);
					
					// 通过位置ID查询下一节点信息
					PositionRetIce queryPositionListByParId(
						string communityId, string parId, string type);
					
					// 根据楼栋id查询楼栋详情
					BuildDetailReturnIce getBuildDetail(string buildId);
					
					// 通过单元id查询单元详情
					UnitDetailReturnIce getUnitDetail(string unitId);
					
					// 通过楼层ID获取楼层详情
					FloorDetailReturnIce getFloorDetail(string floorId);
					
					// 通过楼层ID获取所有物理房间
					HouseInfoReturnIce getHouseAll(HouseInfoIce houseInfoIce);
					
					// 通过位置ID查询下一节点信息(个性化)
					PositionRetIce queryPositionListByParIdInd(
						string communityId, string parId, string type);
				};
			};
		};
	};
};