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
			module userposit {
				// 根据当前登录用户查询最后一次定位和时间入参
				["java:getset"]
				struct UserPositIce{
					string organId;
					string userId;
					string locationId;
					string houseId;
					string userOrganId;
					string mac;
					string userName;
					string userWorkNo;
				};
				
				// 根据当前登录用户查询最后一次定位和时间出参
				["java:getset"]
				struct UserPositRetIce{
					string locationId;
					string locationName; 
					string updateDate; 
				};
				
				// 根据当前登录用户查询最后一次定位和时间出参
				["java:getset"]
				struct UserPositReturnIce{
					string code;
					string message;
					UserPositRetIce userPositRetIce;
				};
				
				//人员位置列表分页查询(3D图)入参
				["java:getset"]
				struct UserGraLocationIceParam{
					string organId;
					string userName;
					string userWorkNo;
					string buildId;
					string floorId;
					string sid;
					string curUserId;
					string pageNo;
					string pageLength;
				};
				
				//人员位置列表分页查询(3D图)分页返回参数
				["java:getset"]
				struct UserGraLocationListIce{
					string userId;
					string userName;
					string userWorkNo;
					string floorName;
					string sidName;
					string loactionName;
					string blendName;
					string lastUpdateTime;
					string statusName;
					string buildId;
					string sid;
					string floorId;
				};
				
				["java:type:java.util.ArrayList"]sequence<UserGraLocationListIce> UserGraLocationListIceList;
				
				// 位置信息分页返回(3D图)
				["java:getset"]
				struct UserGraLocationInfoPaginatorIce{
					string code;
					string message;
					page::RpcPageIce paginator;
					UserGraLocationListIceList resultList;
				};
				
				//人员位置列表分页查询(3D图)总数入参公用
				
				//人员位置列表分页查询(3D图)总数返回返回参数
				["java:getset"]
				struct UserSumLocationListIce{
					string userId;
					string userName;
					string userWorkNo;
					string statusName;
				};
				["java:type:java.util.ArrayList"]sequence<UserSumLocationListIce> UserSumLocationListIceList;
				["java:getset"]
				struct SumLocationListIce{
					string loactionId;
					string loactionName;
					string floorPositX;
					string floorPositY;
					string userListSize;
					UserSumLocationListIceList userSumLocationListIceList;
				};
				["java:type:java.util.ArrayList"]sequence<SumLocationListIce> SumLocationListIceList;
				["java:getset"]
				struct ResultDateIce{
					SumLocationListIceList resultList;
					string planUrl;
				};
				// 位置信息总算返回(3D图)
				["java:getset"]
				struct SumUserLocationInfoIce{
					string code;
					string message;
					ResultDateIce result;
				};
				
				
				//人员位置列表分页查询(2D图)入参
				["java:getset"]
				struct UserPlaLocationIceParam{
					string organId;
					string buildId;
					string floorId;
					string sid;
					string status;
				};
				
				//2D图返回参数
				["java:getset"]
				struct UserListIce{
					string userId;
					string userName;
					string userWorkNo;
					string statusName;
					string status;
				};
				
				["java:type:java.util.ArrayList"]sequence<UserListIce> UserListIceList;
				
				["java:getset"]
				struct BuildLocationIce{
					string locationId;
					string locationName;
					UserListIceList userListIceList;
				};
				
				["java:type:java.util.ArrayList"]sequence<BuildLocationIce> BuildLocationIceList;
				
				["java:getset"]
				struct BuildFloorIce{
					string floorId;
					string floorNum;
					string floorName;
					BuildLocationIceList buildLocationIceList;
				};
				
				["java:type:java.util.ArrayList"]sequence<BuildFloorIce> BuildFloorIceList;
				
				["java:getset"]
				struct BuildIce{
					string buildId;
					string buildName;
					BuildFloorIceList buildFloorIceListList;
				};
				
				["java:type:java.util.ArrayList"]sequence<BuildIce> BuildIceList;
				
				// 位置信息返回(2D图)
				["java:getset"]
				struct UserPlaLocationInfoIce{
					string code;
					string message;
					BuildIceList resultList;
				};
				
				// start by yangyh:接口0604
				// 根据部门员工姓名员工工号查询员工信息入参
				["java:getset"]
				struct UserInfoPageParam{
					string organId;
					string groupId;
					string userName;
					string userNo;
					string pageNo;
					string pageLength;
				};
				
				// 员工信息
				["java:getset"]
				struct UserInfoIce{
					string userId;
					string userName;
					string nickName;
					string sex;
					string tel;
					string qq;
					string email;
					string jobNumber;
					string groupId;
					string groupName;
					string userSexName;
					string status;
					string statusName;
					string unTaskNum;
					string locationId;
					string locationName;
				};
				
				["java:type:java.util.ArrayList"]sequence<UserInfoIce> UserInfoIceList;
				
				["java:getset"]
				struct UserInfoPaginatorIce{
					string code;
					string msg;
					UserInfoIceList resultList;
				};
				// end by yangyh
				
				// 人员位置管理
				interface UserPositServiceIce{
					//根据当前登录用户查询最后一次定位和时间
					UserPositReturnIce getLastPositByUser(UserPositIce userPositIce);
					
					//上传当前用户位置信息
					UserPositReturnIce updateLocatePosit(UserPositIce userPositIce);
					
					//人员位置列表分页查询(3D图)
					UserGraLocationInfoPaginatorIce queryUserLocationPage(UserGraLocationIceParam userGraLocationIceParam);
					
					//人员位置列表(3D图)
					SumUserLocationInfoIce querySumUserLocation(UserGraLocationIceParam userGraLocationIceParam);
					
					//人员位置列表(2D图)
					UserPlaLocationInfoIce queryUserPlaLocation(UserPlaLocationIceParam userPlaLocationIceParam);
					
					//根据项目id查人员位置信息（缓存查看）
					string queryUserLocationByOrganIdRedis(string organId);
					
					// start by yangyh:接口0604
					// 根据部门员工姓名员工工号查询员工信息
					UserInfoPaginatorIce queryUserInfoPage(UserInfoPageParam userInfoPageParam);
					// end by yangyh
				};
			};
		};
	};
};