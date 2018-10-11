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
		module common {
			module report {
				module monthrank{
					module personal{				
						// 个人运送量月报表业务接口
						["java:getset"]
						interface PerVolMonthRptServiceIce{	
							// 当月运送量排名增量保存接口(多个用户Id用逗号分隔)
							resp::RpcRespIce savePersonalVolumeIncrease(
								int organId, string userIds, int transCount, string exeBeginTime, string taskType);						
						};
					};
				};
			};
		};
	};
};