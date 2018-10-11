// **********************************************************************
//
// Copyright (c) 2003-2006 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//   
// **********************************************************************
  
#pragma once
#include <commons.ice>
#include <medicaltrans_base.ice>
#include <segi_wh_common.ice>
module segi {
	module medicaltrans {
		module mttask {
			  module manager {
			  
			  	["java:getset"]
				struct MtTaskReq {
					string taskId;
					string organId;       
					string taskType;    
					string transTypeParentCode;
					string transTypeId;
					string urgency;
					string sourceHouseId;
					string fromHouseId;
					string toHouseId;
					string transPersonCount;
					string transTools;
					string fileIds;
					string delFileIds;
					string taskContent;
					string patientName;
					string bedNo;
					string patientGender;
					string medicalRecNo;
					string dataSource;
					// 是否预约
					string isReservedFlag;
					// 预约时间
					string beginTime;
					// 时限
					string limitMinute;
					// 登录信息
					string userId;
					string userOrganId;
					// web端新增调度任务时派单参数
					string resType;
					string exeUserIds;
					// 响应类型为指定时,需要选择责任人
					string exeEndUserId;
					string serviceGroupIds;
					string sendContent;
					// pad端语音附件
					string voiceIds;
					string delVoiceIds;
					// pad端 用户所在科室Id
					string userHouseId;
					// web端任务发起flag
					bool flag;
				};
				
				// 固定任务参数
				["java:getset"]
				struct MtFixedTaskParam {
					string organId;       
					string taskType;    
					string transTypeParentCode;
					string urgency;
					string fromHouseId;
					string toHouseId;
					string transTools;
					string taskContent;
					string patientName;
					string bedNo;
					string patientGender;
					string medicalRecNo;
					string dataSource;
					// 预约时间
					string beginTime;
					string limitMinute;
					segiwh::common::IntList transactors;
					segiwh::common::IntList routeList;
					// 循环任务创建者
					string createBy;
					// 循环任务主键Id
					string taskLoopId;
				};
				
				// 公共的信息入参
				["java:getset"]
				struct MtCommonIceParam {
					// 任务完成
					string taskId;
					string houseId;
					string organId;
					string mac;
					string finishFileIds;
					string taskUserIds;
				    string finishContent;
				    string applyStatus;
					
					string dataSource;
					string userId;
					string userOrganId;
					// 评价
					string evaluate;
					string evaluateContent;
					// 签名
					string routeId;
					string autographFileIds;
					//照片
					string photographFileIds;
					// 超时原因
					string timeOutReason;
					// 退单原因
					string backTaskReason;
				};
				
				// 抢单返回信息
				["java:getset"]
				struct MtTaskGrabRsp {
					string code;
					string message;
					string taskId;
					// 是否已经设置责任人
					string isPersonLiable;
					// 当前用户是否已抢单
					string isGrabTask;
				};
				
				// 申请为主责任人
				["java:getset"]
				struct MtTaskApplyPersonLiableRsp {
					string code;
					string message;
					string taskId;
					string exeEndUserId;
				};
				
				// 任务派单信息入参
				["java:getset"]
				struct SendTaskIceParam {
					string taskId;
					string resType;
					string exeUserIds;
					// 责任人Id
					string exeEndUserId;
					string serviceGroupIds;
					string sendContent;
					// 固定任务
					string exeUserId;
					// 派单人id
					string userId;
					// 用户所在organId
					string userOrganId;
					// 时限
					string limitMinute;
					// 运送人数
					string transPersonCount;
					// 组织Id
					string organId;
				};
				
				interface MtTaskManagerCreateServiceIce{
				
					// 创建任务
					resp::RpcRespIce createTask(MtTaskReq param);
					
					// 新增固定任务
					resp::RpcRespIce createFixedTask(MtFixedTaskParam param);
					
					// 调度任务派单
					resp::RpcRespIce dispatchTask(SendTaskIceParam sendTaskIceParam);
					
					// 调度任务重新派单
					resp::RpcRespIce againDispatchTask(SendTaskIceParam sendTaskIceParam);
					
					// 固定任务重新派单
					resp::RpcRespIce againFixedTask(SendTaskIceParam sendTaskIceParam);
					
					// 编辑任务
					resp::RpcRespIce editTask(MtTaskReq param);
					
				};
				
				interface MtTaskManagerHandleServiceIce{
					// 固定任务完成
					resp::RpcRespIce finishFixedTask(MtCommonIceParam mtCommonIceParam);
					
					// 签名
					resp::RpcRespIce autograph(MtCommonIceParam mtCommonIceParam);
					
					// 运送评价
					resp::RpcRespIce evaluate(MtCommonIceParam mtCommonIceParam);
										
					// 抢单
					MtTaskGrabRsp grabMtDispatchTask(MtCommonIceParam mtCommonIceParam);
					
					// 申请为主责任人
					MtTaskApplyPersonLiableRsp applyPersonLiable(MtCommonIceParam mtCommonIceParam);
					
					// 任务退单
					resp::RpcRespIce backMtTask(MtCommonIceParam mtCommonIceParam);
					
					// 任务开始
					resp::RpcRespIce startMtTask(MtCommonIceParam mtCommonIceParam);
					
					// 取消任务
					resp::RpcRespIce cancelMtTask(MtCommonIceParam mtCommonIceParam);
					
					// 完成任务
					resp::RpcRespIce finishMtTask(MtCommonIceParam mtCommonIceParam);
					
					// 任务开始web  liuyi
					resp::RpcRespIce startTaskForWeb(MtCommonIceParam mtCommonIceParam);
					
					// 完成任务web  liuyi
					resp::RpcRespIce finishTaskForWeb(MtCommonIceParam mtCommonIceParam);
					
					// 拍照App    liuyi
					resp::RpcRespIce photograph(MtCommonIceParam mtCommonIceParam);
				};
			};
		};
	};
};