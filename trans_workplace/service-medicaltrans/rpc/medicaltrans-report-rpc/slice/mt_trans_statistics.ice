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
		module report {
			module trans{
				
				["java:getset"]
				struct TransTimeIce{
					string timeName;
					string time;
				};
				
				["java:getset"]
				struct TransRatioIce{
					string amountName;
					string amount;
				};
				
				["java:type:java.util.ArrayList"]sequence<TransTimeIce> TransTimeIceList;
				
				["java:type:java.util.ArrayList"]sequence<TransRatioIce> TransRatioIceList;
				
				["java:getset"]
				struct TransTimeReturnIce{
					string code;
					string msg;
					TransTimeIceList resultList;
				};
				
				["java:getset"]
				struct TransRatioReturnIce{
					string code;
					string msg;
					TransRatioIceList resultList;
				};
				
				["java:getset"]
				interface TransStatisticsServiceIce{
					TransTimeReturnIce queryTransportTime(int organId);
					TransRatioReturnIce queryTaskStructureRatio(int organId);
					TransRatioReturnIce queryTransModeRatio(int organId);
					TransRatioReturnIce queryFromHouseRatio(int organId);
				};
			};
		};
	};
};