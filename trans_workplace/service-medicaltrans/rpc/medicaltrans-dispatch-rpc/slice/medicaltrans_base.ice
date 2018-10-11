// **********************************************************************
//
// Copyright (c) 2003-2006 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//   
// **********************************************************************
  
#pragma once

module segimedicaltrans {
		module base {
			["java:getset"]
			class MtTaskBaseRsp {
				string taskId;                    // 运送任务主键Id
				string organId ;                  // 项目(医院)Id
				string organName ;                // 项目(医院)名称
				string transTypeParentCode ;      // 运送大类Code
				string transTypeParentCodeName ;  // 运送大类名称
				string transTypeId ;              // 运送类型Id(大类下类型)
				string transTypeName ;            // 运送类型名称(大类下类型)
				string fromHouseId ;              // 出发地Id
				string fromHouseName ;            // 出发地名称
				string toHouseId ;                // 目的地Id
				string toHouseName ;              // 目的地名称
				string patientName ;              // 患者姓名,该条运送大类为病人护送会展示
				string bedNo ;                    // 床号,该条运送大类为病人护送会展示
				string patientGender ;        	  // 患者性别,该条运送大类为病人护送会展示
				string patientGenderName ;        // 患者性别,该条运送大类为病人护送会展示
				string medicalRecNo;              // 病历号,该条运送大类为病人护送会展示
				string transTools;                // 运送工具
				string transToolsName;            // 运送工具名称
				string taskContent ;              // 运送描述
				string beginTime ;				  // 运送计划开始时间 YYYY-MM-DD HH：mm
				string endTime;					  // 运送计划结时间 YYYY-MM-DD HH：mm
				string exeBeginTime ;             // 任务实际开始时间 HH : mm
				string exeEndTime ;               // 任务实际结束时间 HH : mm
				string status ;					  // 状态
				string statusName ;				  // 状态名称
				string fromHouseLocateType;		  // 出发地科室定位方式
				string fromHouseMac;			  // 出发地科室mac
				string toHouseLocateType;		  // 目的地科室定位方式
				string toHouseMac;				  // 目的地科室mac
				string exeEndUserId;              // 主负责人id
				string sourceHouseId;			  // 来源科室id
	            string sourceHouseName;			  // 来源科室名称
			};
			
			["java:getset"]
			class MtConnonPageRsp {
				string taskId;                    // 运送任务主键Id
				string organId ;                  // 项目(医院)Id
				string organName ;    			  // 项目(医院)名称
				string fromHouseId ;              // 出发地Id
				string fromHouseName ;            // 出发地名称
				string toHouseId ;                // 目的地Id
				string toHouseName ;              // 目的地名称            
				string transTypeParentCode ;      // 运送大类Code
				string transTypeParentCodeName ;  // 运送大类名称
				string transTypeId ;              // 运送类型Id(大类下类型)
				string transTypeName ;            // 运送类型名称(大类下类型)
				string transTools;                // 运送工具
				string transToolsName;            // 运送工具名称
			};
	};
};