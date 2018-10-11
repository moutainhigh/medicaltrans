// **********************************************************************
//
// Copyright (c) 2003-2006 ZeroC, Inc. All rights reserved.
// @author zhangyang@segimail.com
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
// **********************************************************************
#pragma once
#include <commons.ice>
#include <medicaltrans_base_common.ice>
module segi {
     module medicaltrans {
          module mthistask {
              module query {        
				  // 运送记录分页查询入参
	              ["java:getset"]
	              struct MtTaskPageIceParam{
	                   string organId;				// 组织（医院）Id
	                   string transTypeParentCode;	// 运送大类类型编码
	                   string transTypeId;			// 所选大类带出来的小类Id
	                   string urgency;				// 紧急程度
	                   string beginTime;			// 下单开始时间 YYYY-MM-DD
	                   string endTime;				// 下单结束时间 YYYY-MM-DD
	                   string status;				// 状态
	                   string taskType;				// 任务类型
	                   string pageNo;				// 页码
	                   string pageLength;			// 每页条数
	                   string userOrganId;			// 用户所属组织机构
	                   string userId;				// 用户员工ID
	                   string taskId;				// 任务Id	                   
	                   bool evaluateFlag;			// 评价标志                 
	                   string userHouseId;			// 用户科室Id
	                   string sourceHouseId;		// 任务来源科室Id
	                   string groupOrganId;			// 一级物业Id
	                   string cycleYm;
	                   string createDate;
	                   // 导出标识
	                   bool exportFlag;
	              };
	              
	              // 执行人信息
	              ["java:getset"]
				  struct ExeUser {
					   string userId;      	// 员工Id 四格的员工表主键    
					   string userName;	   	// 员工姓名
					   string userNo;      	// 员工工号 
					   string userStatus;	// 员工状态
					   string userNameNo;
				  };
				  ["java:type:java.util.ArrayList"]sequence<ExeUser> UserList;  
	              
	              // 运送记录分页查询返回参数
	              ["java:getset"]
	              struct MtTaskPageIce{
	                   string taskId;					// 任务Id	 
	                   string organId;					// 组织（医院）Id
	                   string organName;				// 组织（医院）名称
	                   string createDate;				// 创建时间
	                   string createBy;					// 创建人员工ID
	                   string createByName;				// 创建人姓名
	                   string sourceHouseId;			// 任务来源科室ID
	                   string sourceHouseName;			// 任务来源科室名称
	                   string transTypeParentCode;		// 运送大类类型编码
	                   string transTypeParentCodeName;	// 运送大类类型名称
	                   string urgency;					// 紧急程度
	                   string urgencyName;				// 紧急程度名称
	                   string fromHouseId;				// 出发地科室Id
	                   string fromHouseName;			// 出发地科室名称
	                   string toHouseId;				// 目的地科室Id
	                   string toHouseName;				// 目的地科室名称
	                   UserList userList;				// 任务执行人
	                   string taskType;					// 任务类型
	                   string taskTypeName;				// 任务类型名称
	                   string resType;					// 响应类型
	                   string resTypeName;				// 响应类型名称
	                   string status;					// 任务状态
	                   string statusName;				// 任务状态名称  
	                   string sendTime;					// 派单时间	                   
	                   string beginTime;				// 预计开始时间	                   
	                   string endTime;					// 预计结束时间	                   
	                   string exeBeginTime;				// 实际开始时间	                   
	                   string exeEndTime;				// 实际结束时间               
	                   string dataSource;				// 运送单来源(web/app/pad/公众号)
	                   string isTimeOut;				// 是否超时
	                   string isTimeOutName;			// 是否超时名称
	                   string groupOrganId;				// 一级物业Id	                
	                   string dispatchUserId;			// 派单人
	                   string dispatchUserName;			// 派单人姓名
	                   string taskContent;				// 运送内容描述
	                   string receiver;					// 签收人
	                   string receiverUserName;			// 签收人名称
	                   string receiveTime;				// 签收时间
	                   string evaluate;             	// 评价结论,取值1-5,表示1-5颗星
		               string evaluateContent;
	              };
	              
	              // 运送记录分页查询返回参数List集合 
	              ["java:type:java.util.ArrayList"]sequence<MtTaskPageIce> MtTaskList;
	               
	              // 运送记录分页返回
	              ["java:getset"]
	              struct MtTaskPaginatorIceRsp{
	                   string code;
	                   string message;
	                   page::RpcPageIce paginator;
	                   MtTaskList resultList;
	              };
	               
		          // 固定任务运送执行信息分页返回参数
		          ["java:getset"]
		          struct FixedTaskHisExeInfoIce {
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
		               FixedTaskExeDetailIce data;
		          };
		          
              	  ["java:type:java.util.ArrayList"]sequence<FixedTaskHisExeInfoIce> TaskExeInfoList;
               
	              // 固定任务执行信息分页返回
	              ["java:getset"]
	              struct FixedTaskHisExePageRsp {
	                   string code;
	                   string message;
	                   page::RpcPageIce paginator;
	                   TaskExeInfoList resultList;
	              };
	               
	              // 运送任务详情查询入参
	              ["java:getset"]
	              struct MtTaskDetailIceParam{
	                   string taskId;
	                   string taskType;
	                   string groupOrganId;
	                   string organId;                   
	                   string routeId;		// 运送线路Id
	                   string cycleYm;
	              };   
	              
	              // 服务处信息
	              ["java:getset"]
	              struct MtServiceGroupIce{
	                   string groupId;
	                   string groupName;
	              };
	              
	              ["java:type:java.util.ArrayList"]sequence<MtServiceGroupIce> MtServiceGroupIceList;
               
		          // 运送任务详情查询返回参数
		          ["java:getset"]
		          class MtTaskDetailRetIce extends segimedicaltrans::basecommon::MtTaskBaseRsp {
		          	   string urgency;            	// 紧急程度
		               string urgencyName;        	// 紧急程度名称
		               string createBy;          	// 下单人
		               string createByName;        	// 下单人名字
		               string createDate;          	// 下单时间 YYYY-MM-DD HH：mm : ss
		               string sourceHouseId;      	// 任务来源科室id
		               string sourceHouseName;      // 任务来源科室名称
		               string taskType;           	// 任务类型
		               string taskTypeName;        	// 任务类型名称
		               string transPersonCount;    	// 运送人数
		               string limitMinute;         	// 时限,范围5-999，单位分钟
		               string resType;            	// 响应类型
		               string resTypeName;         	// 响应类型名称
		               UserList userList;			// 执行人
		               string sendContent;       	// 派单详情
		               string sendTime;          	// 派单时间 YYYY-MM-DD HH：mm : ss
		               string useParts;          	// 使用配件,进行中和已完成状态展示
		               string finishContent;      	// 处理详情,进行中和已完成状态展示
		               string evaluate;             // 评价结论,取值1-5,表示1-5颗星,任务状态为完成时返回
		               string evaluateContent;     	// 评价信息,3星以下必填,3星及以上非必填,任务状态为完成时返回
		         	   string routeId;              // 路线Id
		         	   MtServiceGroupIceList serviceGroupList;  // 服务处信息
		         	   string receiver;				// 签收人
	                   string receiverUserName;		// 签收人姓名
	                   string receiveTime;			// 签收时间
	                   string receiverHouseId; 		// 签收人科室
	                   string receiverHouseName;
		          };
               
		          // 调度任务详情展示
		          ["java:getset"]
		          struct MtTaskDetailRetIceRsp {
		               string code;
		               string message;
		               MtTaskDetailRetIce data;
		          };
		          
		          // 科室运送量列表分页入参
				  ["java:getset"]
				  struct DeptTaskPageIceParam{
					  string organId;
					  string houseId;
					  string cycleYm;
					  string beginTime;			               
	                  string endTime;			  
					  string paramDate;
					  string pageNo;
					  string pageLength;
					  // 导出标识
	                  bool exportFlag;
				  };
				  		          
		          // 业务库记录查询接口
	              interface MtHisTaskQueryServiceIce{
	                   // 运送记录分页查询
	                   MtTaskPaginatorIceRsp queryMtHisTaskPage(MtTaskPageIceParam mtTaskPageIceParam);
	                   
	                   // 固定任务执行信息分页查询
	                   FixedTaskHisExePageRsp queryMtHisFixedTaskExeInfoPage(MtTaskPageIceParam mtTaskPageIceParam);
	                    
	                   // 运送任务详情
	                   MtTaskDetailRetIceRsp queryMtHisTaskDetail(MtTaskDetailIceParam mtTaskDetailIceParam);
	               
	                   // 固定任务详情(web端)
	                   MtTaskDetailRetIceRsp queryMtHisTaskFixedDetail(MtTaskDetailIceParam mtTaskDetailIceParam);
	                   
	                   // 固定任务某个点执行信息详情
	                   FixedTaskExeDetailReturnIce queryMtHisFixedTaskExeDetail(MtTaskDetailIceParam mtTaskDetailIceParam);   
	                   
	                   // 运送员运送明细分页查询
	                   MtTaskPaginatorIceRsp queryMtHisTaskPageByUser(MtTaskPageIceParam mtTaskPageIceParam);            
	              
	              	   // 科室运送任务列表分页liuyi
	                   MtTaskPaginatorIceRsp getDeptTaskPage(DeptTaskPageIceParam deptTaskPageIceParam);
	                   
	                   // 运送员日排名运送明细分页查询
	                   MtTaskPaginatorIceRsp queryUserHisTaskDaylyPage(MtTaskPageIceParam mtTaskPageIceParam);
	              };
	              
	              // 报表库记录查询接口
	              interface MtHisTaskRptQueryServiceIce{
	                   // 运送记录分页查询
	                   MtTaskPaginatorIceRsp queryMtHisTaskRptPage(MtTaskPageIceParam mtTaskPageIceParam);
	                   
	                   // 固定任务执行信息分页查询
	                   FixedTaskHisExePageRsp queryMtHisFixedTaskExeInfoRptPage(MtTaskPageIceParam mtTaskPageIceParam);
	                    
	                   // 运送任务详情
	                   MtTaskDetailRetIceRsp queryMtHisTaskRptDetail(MtTaskDetailIceParam mtTaskDetailIceParam);
	               
	                   // 固定任务详情(web端)
	                   MtTaskDetailRetIceRsp queryMtHisTaskFixedRptDetail(MtTaskDetailIceParam mtTaskDetailIceParam);
	                   
	                   // 固定任务某个点执行信息详情
	                   FixedTaskExeDetailReturnIce queryMtHisFixedTaskExeRptDetail(MtTaskDetailIceParam mtTaskDetailIceParam);   
	                   
	                   // 运送员运送明细分页查询
	                   MtTaskPaginatorIceRsp queryMtHisTaskRptPageByUser(MtTaskPageIceParam mtTaskPageIceParam); 
	                   
	                   // 科室运送任务列表分页liuyi
	                   MtTaskPaginatorIceRsp getDeptTaskRptPage(DeptTaskPageIceParam deptTaskPageIceParam);
	              };
              };
          };
     };
};




