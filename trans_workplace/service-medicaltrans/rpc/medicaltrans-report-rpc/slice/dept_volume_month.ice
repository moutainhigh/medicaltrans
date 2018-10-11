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
		module report{
			module deptvolumemonth {
				// 本月科室运送量排名入参
				["java:getset"]
				struct DeptVolumeMonthParam{
					string organId;
					string houseId;
					string cycleYm;
					string pageNo;
					string pageLength;
				};
				
				// 本月科室运送量
				["java:getset"]
				struct DeptVolumeMonthIce{
					string organId;
					string organName;
					string houseId;
					string houseName;
					string buildId;
					string buildName;
					string sid;
					string sidName;
					string floorId;
					string floorName;
					string transAmount;
					string dispatchAmount;
					string autonomousAmount;
				};
				
				["java:type:java.util.ArrayList"]sequence<DeptVolumeMonthIce> DeptVolumeMonthList;
				
				// 本月科室运送量返回对象
				["java:getset"]
				struct DeptVolumeMonthPaginator{
					string code;
					string msg;
					page::RpcPageIce paginator;
					DeptVolumeMonthList resultList;
				};
				
				// 本月科室运送量业务接口
				["java:getset"]
				interface MtrDeptVolumeMonthServiceIce{
					// 本月科室月运送量排名分页
					DeptVolumeMonthPaginator getDeptVolumeMonthPage(DeptVolumeMonthParam deptVolumeMonthParam);
					// 更新科室月运送量排名
					//resp::RpcRespIce updateDeptVolumeMonth(string cycleYm);
				};
			};
		};
	};
};