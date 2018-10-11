// **********************************************************************
//
// Copyright (c) 2003-2006 ZeroC, Inc. All rights reserved.
// @author zhangyang@segimail.com
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
// **********************************************************************
#pragma once
#include <commons.ice>
#include <segi_wh_common.ice>
#include <medicaltrans_base.ice>
module segi {
     module medicaltrans {
          module mttask {
              module query {
              
				  // 医疗运输-运送管理分页查询入参
	              ["java:getset"]
	              struct MtTaskPageIceParam{
	                   string organId;
	                   string transTypeParentCode;
	                   string evaluateStatus;
	                   string urgency;
	                   string beginTime;
	                   string endTime;
	                   string status;
	                   string taskType;
	                   string pageNo;
	                   string pageLength;
	                   string userOrganId;
	                   string userId;
	                   string taskId;
	                   // 评价标志
	                   bool evaluateFlag;
	                   //用户科室Id
	                   string userHouseId;
	                   // 导出标识
	                   bool exportFlag;
	                   // 接口调用来源标志(app/pad/web/WeChat微信公众号)
	                   string invokingFlag;
	                   // app/pad版本号
	                   string version;
	              };   
	              
	              // 医疗运输-运送单预警时间(平台维护)
	              ["java:getset"]
	              struct WarnMinute{
	                   string noSendWarnMinute;
	                   string noStartWarnMinute;
	                   string noRobWarnMinute;
	                   // 当前系统时间
	                   string systemTime;
	              }; 
	               
	              // 医疗运输-运送分页查询返回参数
	              ["java:getset"]
	              struct MtTaskPageRerurnIce{
	                   string taskId;
	                   string organId;
	                   string organName;
	                   string createDate;
	                   string createBy;
	                   string createByName;
	                   string sourceHouseId;
	                   string sourceHouseName;
	                   string transTypeParentCode;
	                   string transTypeParentCodeName;
	                   string urgency;
	                   string urgencyName;
	                   string fromHouseId;
	                   string fromHouseName;
	                   string toHouseId;
	                   string toHouseName;
	                   segiwh::common::UserList userList;
	                   string taskType;
	                   string taskTypeName;
	                   string resType;
	                   string resTypeName;
	                   string status;
	                   string statusName;
	                   // 派单时间
	                   string sendTime;
	                   // 预计开始时间
	                   string beginTime;
	                   // 预计结束时间
	                   string endTime;
	                   // 实际开始时间
	                   string exeBeginTime;
	                   // 实际结束时间
	                   string exeEndTime;
	                   // 运送单来源(web/app/pad/公众号)
	                   string dataSource;
	                   // 导出新增字段
	                   // 派单人
	                   string dispatchUserId;
	                   // 派单人姓名			
	                   string dispatchUserName;	
	                   // 运送内容描述		
	                   string taskContent;
	                   // 签收人				
	                   string receiver;	
	                   // 签收人姓名				
	                   string receiverUserName;
	                   // 签收时间				
	                   string receiveTime;
	                   // 评价结论,取值1-5,表示1-5颗星				
	                   string evaluate; 
	                   // 评价信息            	
		               string evaluateContent;
	              };
	               
	              ["java:type:java.util.ArrayList"]sequence<MtTaskPageRerurnIce> MtTaskList;
	               
	              // 运送管理分页返回
	              ["java:getset"]
	              struct MtTaskPaginatorIce{
	                   string code;
	                   string message;
	                   page::RpcPageIce paginator;
	                   MtTaskList resultList;
	                   WarnMinute warnMinute;
	              };
	               
	              // 医疗运输-历史任务返回参数
	              ["java:getset"]
	              struct MtTaskHistoryRerurnIce{
	                   string taskId;
	                   string status;
	                   string statusName;
	                   string urgency;
	                   string urgencyName;
	                   string fromHouseId;
	                   string fromHouseName;
	                   string toHouseId;
	                   string toHouseName;
	                   string transTools;
	                   string transToolsName;
	                   string transTypeParentCode;
	                   string transTypeParentCodeName;
	                   string transTypeId;
	                   string transTypeName;
	                   string exeBeginTime;
	                   string exeEndTime;
	              };
	               
	              ["java:type:java.util.ArrayList"]sequence<MtTaskHistoryRerurnIce> HistoryList;
	               
	              // 运送历史任务分页返回
	              ["java:getset"]
	              struct MtTaskHistoryPaginatorIce{
	                   string code;
	                   string message;
	                   page::RpcPageIce paginator;
	                   HistoryList resultList;
	              };
	               
	              // 运送单详情
	              ["java:getset"]
	              class MtAutonomousTaskDetailIce extends segimedicaltrans::base::MtTaskBaseRsp {
	                   string limitMinute  ;      // 时限,范围5-999，单位分钟
	                   string finishContent;      // 处理详情,任务状态为完成时返回
	                   string routeId;			  // 路线Id
	                   string evaluate;			  // 评价结论,取值1-5,表示1-5颗星,任务状态为完成时返回
	                   string evaluateContent ;   // 评价结论,3星以下必填,3星及以上非必填,任务状态为完成时返回
	                   string receiver; 		  // 签收人
		         	   string receiverUserName;	  // 签收人姓名
		         	   string receiverHouseName;  // 签收人科室
		         	   string receiveTime; 		  // 签收时间
	              };
	               
	              // 运送单详情
	              ["java:getset"]
	              class MtDispatchTaskDetailIce extends segimedicaltrans::base::MtTaskBaseRsp {
	                   string limitMinute ;//时限,范围5-999，单位分钟
	                   string finishContent ;//处理详情,任务状态为完成时返回
	                   string evaluate;//评价结论,取值1-5,表示1-5颗星,任务状态为完成时返回
	                   string evaluateContent ;//评价结论,3星以下必填,3星及以上非必填,任务状态为完成时返回
	                   string routeId;//路线Id
	                   segiwh::common::UserList userList;
	                   string urgency;  //紧急程度
	                   string urgencyName;  //紧急程度名称
	                   string transPersonCount;  //运送人数
		         	   string receiver;  // 签收人
		         	   string receiverUserName;// 签收人姓名
		         	   string receiverHouseName;// 签收人科室
		         	   string receiveTime; // 签收时间
	              };
	               
	              // 运送单详情
	              ["java:getset"]
	              class MtFixedTaskDetailIce extends segimedicaltrans::base::MtTaskBaseRsp {
	                   //string taskBeginTime ;            // 固定任务开始时间 HH : mm
	                   //string taskEndTime ;              // 固定任务结束时间 HH : mm
	              };
	               
	              // 固定任务详情展示
	              ["java:getset"]
	              struct MtFixedTaskDetailIceRsp {
	                   string code;
	                   string message;
	                   MtFixedTaskDetailIce mtFixedTaskDetailIce;
	              };
	               
	              // 调度任务详情展示
	              ["java:getset"]
	              struct MtDispatchTaskDetailIceRsp {
	                   string code;
	                   string message;
	                   MtDispatchTaskDetailIce mtDispatchTaskDetailIce;
	              };
	               
	              // 调度任务详情展示
	              ["java:getset"]
	              struct MtAutonomousTaskDetailIceRsp {
	                   string code;
	                   string message;
	                   MtAutonomousTaskDetailIce mtAutonomousTaskDetailIce;
	              };
	               
	              // 调度任务活动中任务分页展示(app端)
	              ["java:getset"]
	              class MtDispatchTaskPageIce extends segimedicaltrans::base::MtConnonPageRsp {
	                   string status;
	                   string statusName;
	                   string urgency;
	                   string urgencyName;
	                   // 剩余时间
	                   string remainTime;
	                   // 预计开始时间
	                   string beginTime;
	                   // 预计结束时间
	                   string endTime;
	              };
	               
	              ["java:type:java.util.ArrayList"]sequence<MtDispatchTaskPageIce> DispatchTaskPageList;
	               
	               // 不同任务未完成数
	              ["java:getset"]
	              struct TaskCount {
	                   string dispatchTaskCount;
	                   string robTaskCount;
	                   string fixedTaskCount;
	                   string autonomousTaskCount;
	              };
	               
	              // 调度任务分页展示(活动中的任务)
	              ["java:getset"]
	              struct MtDispatchTaskPaginatorIceRsp {
	                   string code;
	                   string message;
	                   page::RpcPageIce paginator;
	                   // 未完成任务数
	                   TaskCount taskCount;
	                   DispatchTaskPageList resultList;
	              };
               
	              // 固定任务活动中任务分页展示(app端)
	              ["java:getset"]
	              class MtFixedTaskPageIce extends segimedicaltrans::base::MtConnonPageRsp {
	                   string status;
	                   string statusName;
	                   //计划开始时间
	                   string beginTime;
	                   //计划结束时间
	                   string endTime;
	                   //剩余时间
	                   string remainTime;
	              };
	               
	              ["java:type:java.util.ArrayList"]sequence<MtFixedTaskPageIce> FixedTaskPageList;
	               
	              // 固定任务分页展示(活动中的任务)
	              ["java:getset"]
	              struct MtFixedTaskPaginatorIceRsp {
	                   string code;
	                   string message;
	                   page::RpcPageIce paginator;
	                   // 未完成任务数
	                   TaskCount taskCount;
	                   FixedTaskPageList resultList;
	              };
	               
	              // 自主任务活动中任务分页展示(app端)
	              ["java:getset"]
	              class MtAutonomousTaskPageIce extends segimedicaltrans::base::MtConnonPageRsp {
	                  // 任务剩余时间
	                  string remainTime;
	              };
	               
	              ["java:type:java.util.ArrayList"]sequence<MtAutonomousTaskPageIce> AutonomousTaskPageList;
               
	              // 自主任务分页展示(活动中的任务)
	              ["java:getset"]
	              struct MtAutonomousTaskPaginatorIceRsp {
	                   string code;
	                   string message;
	                   page::RpcPageIce paginator;
	                   // 未完成任务数
	                   TaskCount taskCount;
	                   AutonomousTaskPageList resultList;
	              };
               
		          // 固定任务运送执行信息分页返回参数
		          ["java:getset"]
		          struct FixedTaskExeInfoIce {
		               string routeId;
		               string houseId;
		               string houseName;
		               string finishTime;
		               // 是否打卡(完成任务)
		               string status;
		               // 是否签名
		               string isAutograph;
		               // 科室定位方式
		               string locateType;
		               string mac;  // 科室mac地址
		               // 开始时间
		               string beginTime;
		          };
		          
		          // 返回附件参数
		          ["java:getset"]
		          struct FileParams {
		               string fileId;
		               string refType;
		               string refId;
		               string fileName;
		               string reqFileUrl;
		               string narrowFileUrl;
		               string fileSize;
		          };
		          
		          ["java:type:java.util.ArrayList"]sequence<FileParams> AutographFileList;
		          
		          ["java:type:java.util.ArrayList"]sequence<FileParams> FinishFileList;
		          
		          // 固定任务某个点执行信息详情返回参数
		          ["java:getset"]
		          struct FixedTaskExeDetailIce {
		               string routeId;
		               string houseId;
		               string houseName;
		               string finishContent;
		               AutographFileList autographFileList;
		               FinishFileList finishFileList;
		               string finishTime; // 完成时间
		          };
		          
		          // 固定任务某个点执行信息详情返回
		          ["java:getset"]
		          struct FixedTaskExeDetailReturnIce {
		               string code;
		               string message;
		               FixedTaskExeDetailIce detail;
		          };
		          
              	  ["java:type:java.util.ArrayList"]sequence<FixedTaskExeInfoIce> TaskExeInfoList;
               
	              // 固定任务执行信息分页返回
	              ["java:getset"]
	              struct FixedTaskExePaginatorIceRsp {
	                   string code;
	                   string message;
	                   page::RpcPageIce paginator;
	                   TaskExeInfoList resultList;
	              };
	               
	              // 医疗运输-运送任务详情查询入参
	              ["java:getset"]
	              struct MtTaskDetailIceParam{
	                   string taskId;
	                   string taskType;
	                   string userOrganId;
	                   string organId;
	              };   
	              
	              // 医疗运输-服务处信息
	              ["java:getset"]
	              struct MtServiceGroupIce{
	                   string groupId;
	                   string groupName;
	              };
	              
	              ["java:type:java.util.ArrayList"]sequence<MtServiceGroupIce> MtServiceGroupIceList;
               
		          // 医疗运输-运送任务详情查询返回参数
		          ["java:getset"]
		          class MtTaskDetailRetIce extends segimedicaltrans::base::MtTaskBaseRsp {
		          	   string urgency;            // 紧急程度
		               string urgencyName;         // 紧急程度名称
		               string createBy;          // 下单人
		               string createByName;        // 下单人名字
		               string createDate;          // 下单时间 YYYY-MM-DD HH：mm : ss
		               string taskType;           // 任务类型
		               string taskTypeName;        // 任务类型名称
		               string transPersonCount;    // 运送人数
		               string limitMinute;         // 时限,范围5-999，单位分钟
		               string resType;            // 响应类型
		               string resTypeName;         // 响应类型名称
		               segiwh::common::UserList userList;
		               string sendContent;       // 派单详情
		               string sendTime;          // 派单时间 YYYY-MM-DD HH：mm : ss
		               string useParts;          // 使用配件,进行中和已完成状态展示
		               string finishContent;      // 处理详情,进行中和已完成状态展示
		               string evaluate;             //评价结论,取值1-5,表示1-5颗星,任务状态为完成时返回
		               string evaluateContent;     //评价结论,3星以下必填,3星及以上非必填,任务状态为完成时返回
		         	   string routeId;              //路线Id
		         	   MtServiceGroupIceList serviceGroupList;  // 服务处信息
		         	   // 签收人
		         	   string receiver;
		         	   // 签收人姓名
		         	   string receiverUserName;
		         	   // 签收人科室
		         	   string receiverHouseName;
		         	   // 签收时间
		         	   string receiveTime;
		         	   // 任务总耗时
		         	   string timeConsuming;
		         	   // 派单人userId
		         	   string dispatchUserId;
		         	   // 派单人姓名
		         	   string dispatchUserName;
		         	   // 派单人工号
		         	   string dispatchUserNo;
		         	   // 一级物业名称
		         	   string groupOrganName;
		          };
               
		          // 调度任务详情展示
		          ["java:getset"]
		          struct MtTaskDetailRetIceRsp {
		               string code;
		               string message;
		               MtTaskDetailRetIce mtTaskDetailRetIce;
		          };
		          
		          // 任务跟踪分页展示和评价信息分页查询(pad端)
	              ["java:getset"]
	              class MtPadCommonPageIce extends segimedicaltrans::base::MtConnonPageRsp {
	                   string status;
	                   string statusName;
	                   segiwh::common::UserList userList;
	                   // 完成时间
	                   string exeEndTime;
	                   // 评价值
	                   string evaluate;
	                   // 预约时间
	                   string beginTime;
	                   string sourceHouseId;
	                   string sourceHouseName;
	                   string urgency;
	                   string urgencyName;
	                   string createBy;
	                   string createByName;
	                   string createDate;
	                   string patientName;
	                   string bedNo;
	              };
	              
	              ["java:type:java.util.ArrayList"]sequence<MtPadCommonPageIce> MtTaskPadPageList;
	              
	              ["java:getset"]
	              struct TaskAndEvaluatePaginatorIceRsp {
	                   string code;
	                   string message;
	                   page::RpcPageIce paginator;
	                   MtTaskPadPageList resultList;
	              };
	              
	              // 任务是否超时返回
				  ["java:getset"]
				  struct TaskIsTimeOutIceParam {
				      string code;
				      string message;
					  string taskId;
					  string isTimeOut;
				  };
               
	              interface MtTaskQueryServiceIce{
	                   // 运送任务分页查询
	                   MtTaskPaginatorIce queryMtTaskPage(MtTaskPageIceParam mtTaskPageIceParam);
	                   
	                   // 分页查询历史任务
	                   MtTaskHistoryPaginatorIce queryMtTaskHistoryPage(MtTaskPageIceParam mtTaskPageIceParam);
	                    
	                   // 查看调度任务
	                   MtDispatchTaskDetailIceRsp queryMtDispatchTaskDetailApp(int organId, int taskId, string userId);
	                    
	                   // 查看固定任务
	                   MtFixedTaskDetailIceRsp queryFixedTaskDetailApp(int organId, int taskId);
	                    
	                   // 查看自主任务
	                   MtAutonomousTaskDetailIceRsp queryAutonomousTaskDetailApp(int organId, int taskId);
	                    
	                   // 抢单或抢单完成的调度任务分页查询(活动中的任务 App端)
	                   MtDispatchTaskPaginatorIceRsp queryDispatchTaskPageByRob(MtTaskPageIceParam mtTaskPageIceParam);
	                    
	                   // 固定任务分页展示(活动中的任务 App端)
	                   MtFixedTaskPaginatorIceRsp queryFixedTaskPage(MtTaskPageIceParam mtTaskPageIceParam);
	                    
	                   // 自主任务分页展示(活动中的任务 App端)
	                   MtAutonomousTaskPaginatorIceRsp queryAutonomousTaskPage(MtTaskPageIceParam mtTaskPageIceParam);
	                    
	                   // 查询跟踪任务分页(pad端)
	                   TaskAndEvaluatePaginatorIceRsp queryMtTaskPageForPad(MtTaskPageIceParam mtTaskPageIceParam);
	                   
	                    // 查询评价信息分页(pad端)
	                   TaskAndEvaluatePaginatorIceRsp queryEvaluatePageForPad(MtTaskPageIceParam mtTaskPageIceParam);
	                    
	                   // 固定任务执行信息分页查询
	                   FixedTaskExePaginatorIceRsp queryFixedTaskExeInfoPage(MtTaskPageIceParam mtTaskPageIceParam);
	                    
	                   // 运送任务详情
	                   MtTaskDetailRetIceRsp queryMtTaskDetail(MtTaskDetailIceParam mtTaskDetailIceParam);
	               
	                   // 固定任务详情(web端)
	                   MtTaskDetailRetIceRsp queryMtTaskFixedDetail(MtTaskDetailIceParam mtTaskDetailIceParam);
	                   
	                   // 固定任务某个点执行信息详情
	                   FixedTaskExeDetailReturnIce queryFixedTaskExeDetail(int routeId, int organId);
	                  
	                   // 查询已经指定给用户的调度任务分页查询(活动中的任务)
	                   MtDispatchTaskPaginatorIceRsp queryDispatchTaskPageByAssign(MtTaskPageIceParam mtTaskPageIceParam);
	                   
	                   // 查询运送任务是否超时
	                   TaskIsTimeOutIceParam queryTaskIsTimeOut(int organId, int taskId);
	              };
                   
              };
          };
     };
};
