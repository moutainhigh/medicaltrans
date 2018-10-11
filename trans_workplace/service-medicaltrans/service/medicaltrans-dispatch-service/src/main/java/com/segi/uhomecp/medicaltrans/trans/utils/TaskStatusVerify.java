package com.segi.uhomecp.medicaltrans.trans.utils;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import segi.medicaltrans.location.common.MtLocationInfoIce;

import com.segi.uhomecp.medicaltrans.enums.TransDispatchTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransStatusEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTaskTypeEnum;
import com.segi.uhomecp.medicaltrans.trans.dto.CommonTaskDto;
import com.segi.uhomecp.medicaltrans.trans.dto.VerifyParamDto;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.wh.common.interfaces.InvocationHandler;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;

public class TaskStatusVerify {
	
	
	/**
	 * Title: TaskStatusVerify.java    
	 * @Description: 描述任务完成服务验证
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月2日 下午7:35:50
	 */
	public static class FinishTaskVerify implements
			InvocationHandler<VerifyParamDto, VerifyParamDto> {

		@Override
		public VerifyParamDto invoke(VerifyParamDto arg0) {
			MtTask mtTask = arg0.getMtTask();
			CommonTaskDto dto = arg0.getCommonTaskDto();
			Integer userId = dto.getUserId();
			Integer exeEndUserId = mtTask.getExeEndUserId(); // 主责任人
			if(arg0.getIsSucc() && TransTaskTypeEnum.TASK_TYPE_LOOP.getCode().equals(mtTask.getTaskType())) {
				arg0.setIsSucc(false);
				arg0.setMessage("接口调用错误！");
			} 
			
			if(arg0.getIsSucc() && (exeEndUserId == null || userId == null || 
					userId.intValue() != exeEndUserId.intValue())) {
				arg0.setIsSucc(false);
				arg0.setMessage("主责任人才能完成任务");
			} 
			
			if(arg0.getIsSucc() && !TransStatusEnum.TRANS_RUNNING.getCode().equals(mtTask.getStatus())) {
				// 进行中可以进行此操作
				arg0.setIsSucc(false);
				arg0.setMessage("运送任务当前状态无法进行完成操作！");
			}
//			String regex = new StringBuffer(",").append(userId)
//								.append(",|,").append(userId)
//								.append("$|^").append(userId)
//								.append(",|^").append(userId).append("$").toString();
//			if (arg0.getIsSucc() &&
//					(StringUtils.isBlank(dto.getTaskUserIds()) || 
//							!AppUtils.isContainsStr(regex, dto.getTaskUserIds()))) {
//				// 完成的任务的人员不可以没有主责任人
//				arg0.setIsSucc(false);
//				arg0.setMessage("执行人中没有主责任人【"+dto.getTaskUserIds()+"】！");
//			}
			
			if (arg0.getIsSucc() && StringUtils.isNotBlank(dto.getTaskUserIds()) 
					&& !AppUtils.str2List(dto.getTaskUserIds()).contains(String.valueOf(userId))) {
				// 选择的运送人员必须包含自己(主责任人)
				arg0.setIsSucc(false);
				arg0.setMessage("您是主责任人,选择运送人员时不可以删除自己！");
			}
			if (arg0.getIsSucc() && StringUtils.isNotBlank(dto.getTaskUserIds()) 
					&& AppUtils.str2List(dto.getTaskUserIds()).size() > mtTask.getTransPersonCount().intValue() + 1) {
				// 完成任务时选择的运送人员大于任务单运送人数
				arg0.setIsSucc(false);
				arg0.setMessage("选择的运送人员人数不可以大于该运送单运送人数[" + (mtTask.getTransPersonCount().intValue() + 1) + "]！");
			}
			//判断目的地科室与完成任务定位时的科室是否一致
			if (arg0.getIsSucc() && null != dto.getHouseId() && dto.getHouseId().intValue() > 0 
					&& dto.getHouseId().intValue() != mtTask.getToHouseId().intValue()) {
				arg0.setIsSucc(false);
				arg0.setMessage("当前定位不是目的地科室,不可完成任务");
			}
			if (arg0.getIsSucc() && StringUtils.isNotBlank(dto.getMac())) {
				MtLocationInfoIce toHouseInfo = MtCommonServiceUtils.getLocationInfoByMac(dto.getOrganId(), dto.getMac());
				if (null == toHouseInfo) {
					arg0.setIsSucc(false);
					arg0.setMessage("医院没有该科室信息");
				}
				if (arg0.getIsSucc() && Integer.valueOf(toHouseInfo.getLocationId()).intValue() != mtTask.getToHouseId().intValue()) {
					arg0.setIsSucc(false);
					arg0.setMessage("当前定位不是目的地科室,不可完成任务");
				}
			}
			return arg0;
		}

	}

	/**
	 * 
	 * Title: TaskStatusVerify.java    
	 * @Description: 描述完成固定任务
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月2日 上午11:18:39
	 */
	public static class FinishFixedTaskVerify implements
			InvocationHandler<VerifyParamDto, VerifyParamDto> {

		@Override
		public VerifyParamDto invoke(VerifyParamDto arg0) {
			MtTask mtTask = arg0.getMtTask();
			CommonTaskDto dto = arg0.getCommonTaskDto();
			Integer userId = dto.getUserId();
			Integer exeEndUserId = mtTask.getExeEndUserId(); // 主责任人
			if(arg0.getIsSucc() && !TransTaskTypeEnum.TASK_TYPE_LOOP.getCode().equals(mtTask.getTaskType())) {
				arg0.setIsSucc(false);
				arg0.setMessage("接口调用错误！");
			} 
			
			if(arg0.getIsSucc() && (exeEndUserId == null || userId == null || 
					userId.intValue() != exeEndUserId.intValue())) {
				arg0.setIsSucc(false);
				arg0.setMessage("主责任人才能完成任务");
			} 
			
			if(arg0.getIsSucc() && !(TransStatusEnum.TRANS_NOT_START.getCode().equals(mtTask.getStatus()) || 
					TransStatusEnum.TRANS_RUNNING.getCode().equals(mtTask.getStatus()))) {
				// 未开始 或进行中可以进行此操作
				arg0.setIsSucc(false);
				arg0.setMessage("运送任务当前状态无法进行完成操作！");
			}
			return arg0;
		}

	}

	/**
	 * 
	 * Title: CancelTaskVerify.java    
	 * @Description: 描述取消验证
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月27日 下午2:50:30
	 */
	public static class CancelTaskVerify implements
			InvocationHandler<VerifyParamDto, VerifyParamDto> {
		
		@Override
		public VerifyParamDto invoke(VerifyParamDto arg0) {
			MtTask mtTask = arg0.getMtTask();
			CommonTaskDto dto = arg0.getCommonTaskDto();
			Integer userId = dto.getUserId();
			Integer createBy = mtTask.getCreateBy();
			String status = mtTask.getStatus();
			
			if(arg0.getIsSucc() && (createBy == null || userId == null || userId != createBy.intValue())
					&& !TransTaskTypeEnum.TASK_TYPE_LOOP.getCode().equals(mtTask.getTaskType())) {
				arg0.setIsSucc(false);
				arg0.setMessage("该任务只有创建者才能取消");
			}
			if(arg0.getIsSucc() && TransTaskTypeEnum.TASK_TYPE_LOOP.getCode().equals(mtTask.getTaskType())
					&& !TransStatusEnum.TRANS_NOT_START.getCode().equals(status)) {
				arg0.setIsSucc(false);
				arg0.setMessage("当前状态不可取消!");
			}
			if (arg0.getIsSucc() && TransTaskTypeEnum.TASK_TYPE_SELF.getCode().equals(mtTask.getTaskType())
					&& !TransStatusEnum.TRANS_RUNNING.getCode().equals(status)) {
				arg0.setIsSucc(false);
				arg0.setMessage("当前状态不可取消!");
			}
			if(arg0.getIsSucc() && TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode().equals(mtTask.getTaskType())
					&& !(TransStatusEnum.TRANS_NON_DISPATCH.getCode().equals(status) ||
					TransStatusEnum.TRANS_NOT_START.getCode().equals(status)|| TransStatusEnum.TRANS_ROBBING.getCode().equals(status)||
					TransStatusEnum.TRANS_BACK.getCode().equals(status))){
				arg0.setIsSucc(false);
				arg0.setMessage("当前状态不可取消!");
			}
			return arg0;
		}
	}
	
	/**
	 * Title: TaskStatusVerify.java    
	 * @Description: 描述
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月27日 下午2:51:36
	 */
	public static class StartTaskVerify
			implements
			InvocationHandler<VerifyParamDto, VerifyParamDto> {

		@Override
		public VerifyParamDto invoke(VerifyParamDto arg0) {
			MtTask mtTask = arg0.getMtTask();
			CommonTaskDto dto = arg0.getCommonTaskDto();
			Integer userId = dto.getUserId();
			Integer exeEndUserId = mtTask.getExeEndUserId();
			if(arg0.getIsSucc() && !TransStatusEnum.TRANS_NOT_START.getCode().equals(arg0.getMtTask().getStatus())) {
				// 抢单已结束
				arg0.setIsSucc(false);
				arg0.setMessage("当前状态不可开始！");
			}
			if(arg0.getIsSucc() && (userId == null || exeEndUserId == null || 
					userId.intValue() != exeEndUserId.intValue())) {
				// 當前操作人員非主責任人
				arg0.setIsSucc(false);
				arg0.setMessage("主责任人才能开始运送任务！");
			}
			//判断出发地科室与开始任务定位时的科室是否一致
			if (arg0.getIsSucc() && null != dto.getHouseId() && dto.getHouseId().intValue() > 0 
					&& dto.getHouseId().intValue() != mtTask.getFromHouseId().intValue()) {
				arg0.setIsSucc(false);
				arg0.setMessage("当前定位不是出发地科室,不可开始任务");
			}
			if (arg0.getIsSucc() && StringUtils.isNotBlank(dto.getMac())) {
				MtLocationInfoIce fromHouseInfo = MtCommonServiceUtils.getLocationInfoByMac(dto.getOrganId(), dto.getMac());
				if (null == fromHouseInfo) {
					arg0.setIsSucc(false);
					arg0.setMessage("医院没有该科室信息");
				}
				if (arg0.getIsSucc() && Integer.valueOf(fromHouseInfo.getLocationId()).intValue() != mtTask.getFromHouseId().intValue()) {
					arg0.setIsSucc(false);
					arg0.setMessage("当前定位不是出发地科室,不可开始任务");
				}
			}
			return arg0;
		}
	}

	/**
	 * 
	 * Title: TaskStatusVerify.java    
	 * @Description: 设置主责任验证
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月26日 上午11:06:23
	 */
	public static class ApplyPersonVerify implements 
		InvocationHandler<VerifyParamDto, VerifyParamDto> {
		@Override
		public VerifyParamDto invoke(VerifyParamDto arg0) {
			if(arg0.getIsSucc() && !TransStatusEnum.TRANS_ROBBING.getCode().equals(arg0.getMtTask().getStatus())) {
				// 抢单已结束
				arg0.setIsSucc(false);
				arg0.setMessage("抢单已结束");
			}
			Integer exeEndUserId = arg0.getMtTask().getExeEndUserId();
			if(arg0.getIsSucc() && !(exeEndUserId == null || exeEndUserId.intValue() == 0)) {
				// 抢单已结束
				arg0.setIsSucc(false);
				arg0.setMessage("主负责人已设置");
			}
			return arg0;
		}
	}
	
	/**
	 * 
	 * Title: TaskStatusVerify.java    
	 * @Description: 抢单验证
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月26日 上午11:08:02
	 */
	public static class GrabMtVerify implements 
		InvocationHandler<VerifyParamDto, VerifyParamDto> {
		@Override
		public VerifyParamDto invoke(VerifyParamDto arg0) {
			if (arg0.getIsSucc() && TransStatusEnum.TRANS_NOT_START.getCode().equals(arg0.getMtTask().getStatus())
					&& TransDispatchTypeEnum.TRANS_DISPATCH_TYPE_02.getCode().equals(arg0.getMtTask().getResType())) {
				arg0.setIsSucc(false);
				arg0.setMessage("抢单失败,运送单已经满员了!");
			}
			if(arg0.getIsSucc() && !TransStatusEnum.TRANS_ROBBING.getCode().equals(arg0.getMtTask().getStatus())) {
				// 抢单已结束
				arg0.setIsSucc(false);
				arg0.setMessage("当前状态不可抢单!");
			}
			return arg0;
		}
	}
	
	/**
	 * 
	 * Title: TaskStatusVerify.java    
	 * @Description: 描述
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月26日 下午3:00:17
	 */
	public static class EditTaskVerify implements 
		InvocationHandler<VerifyParamDto, VerifyParamDto> {
		@Override
		public VerifyParamDto invoke(VerifyParamDto arg0) {
			if(arg0.getIsSucc() && !TransStatusEnum.TRANS_NON_DISPATCH.getCode().equals(arg0.getMtTask().getStatus())) {
				// 抢单已结束
				arg0.setIsSucc(false);
				arg0.setMessage("当前状态不可编辑运送单");
			}
			return arg0;
		}
	}
	
	/**
	 * Title: TaskStatusVerify.java    
	 * @Description: 评价信息验证
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月26日 下午3:53:23
	 */
	public static class EvaluateTaskVerify	implements
		InvocationHandler<VerifyParamDto, VerifyParamDto> {
		@Override
		public VerifyParamDto invoke(VerifyParamDto arg0) {
			if(arg0.getIsSucc() && !TransStatusEnum.TRANS_COMPLETED.getCode().equals(arg0.getMtTask().getStatus())) {
				// 抢单已结束
				arg0.setIsSucc(false);
				arg0.setMessage("当前状态不可评价运送单");
				return arg0;
			}
			long beginTime = DateUtil.parseStringToDate(String.valueOf(arg0.getMtTask().getBeginTime()), 
					DateUtil.FORMAT_YYYY_MM_DD_HH_MM).getTime();
			long limitDate = DateUtil.addDateMonth(new Date(), -1).getTime();
			if (beginTime < limitDate) {
				arg0.setIsSucc(false);
				arg0.setMessage("该运送单已超过1个月,不可评价!");
			}
			return arg0;
		}
	}
	
	/**
	 * Title: TaskStatusVerify.java    
	 * @Description: 调度任务派单验证
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月5日 上午10:35:57
	 */
	public static class DispatchTaskVerify	implements
		InvocationHandler<VerifyParamDto, VerifyParamDto> {
		@Override
		public VerifyParamDto invoke(VerifyParamDto arg0) {
			if(arg0.getIsSucc() && !TransStatusEnum.TRANS_NON_DISPATCH.getCode().equals(arg0.getMtTask().getStatus())) {
				//不是未派单状态,不可派单
				arg0.setIsSucc(false);
				arg0.setMessage("当前状态不可派单");
			}
			return arg0;
		}
	}
	
	/**
	 * Title: TaskStatusVerify.java    
	 * @Description: 调度任务重新派单验证
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月5日 上午10:47:59
	 */
	public static class AgainDispatchTaskVerify	implements
		InvocationHandler<VerifyParamDto, VerifyParamDto> {
		@Override
		public VerifyParamDto invoke(VerifyParamDto arg0) {
			String status = arg0.getMtTask().getStatus();
			if(arg0.getIsSucc() && !(TransStatusEnum.TRANS_NOT_START.getCode().equals(status) || 
					TransStatusEnum.TRANS_ROBBING.getCode().equals(status) ||
					TransStatusEnum.TRANS_BACK.getCode().equals(status))) {
				// 不是未开始,抢单中,退单状态,不可重新派单
				arg0.setIsSucc(false);
				arg0.setMessage("当前状态不可重新派单");
			}
			return arg0;
		}
	}
	
	/**
	 * Title: TaskStatusVerify.java    
	 * @Description: 固定任务重新派单验证
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月5日 下午2:43:22
	 */
	public static class AgainFixedTaskVerify implements
		InvocationHandler<VerifyParamDto, VerifyParamDto> {
		@Override
		public VerifyParamDto invoke(VerifyParamDto arg0) {
			String status = arg0.getMtTask().getStatus();
			if(arg0.getIsSucc() && !(TransStatusEnum.TRANS_NOT_START.getCode().equals(status) || 
					TransStatusEnum.TRANS_BACK.getCode().equals(status))) {
				// 不是未开始,取消状态,不可重新派单
				arg0.setIsSucc(false);
				arg0.setMessage("当前状态不可重新派单");
			}
			return arg0;
		}
	}
	
	/**
	 * 
	 * StartWebTaskVerify.java
	 * @Description: web端开始任务
	 * @author liuyi@segimail.com 
	 * @created 2018年9月10日下午6:32:02
	 */
	public static class StartWebTaskVerify implements
			InvocationHandler<VerifyParamDto, VerifyParamDto> {

		@Override
		public VerifyParamDto invoke(VerifyParamDto arg0) {
			if(arg0.getIsSucc() && !TransStatusEnum.TRANS_NOT_START.getCode().equals(arg0.getMtTask().getStatus())) {
				// 抢单已结束
				arg0.setIsSucc(false);
				arg0.setMessage("当前状态不可开始！");
			}
			return arg0;
		}
	}
	
	/**
	 * 
	 * TaskStatusVerify.java
	 * @Description: 完成任务验证web 调度 自主
	 * @author liuyi@segimail.com 
	 * @created 2018年9月11日上午11:05:26
	 */
	public static class FinishWebTaskVerify implements
			InvocationHandler<VerifyParamDto, VerifyParamDto> {

		@Override
		public VerifyParamDto invoke(VerifyParamDto arg0) {
			MtTask mtTask = arg0.getMtTask();
			CommonTaskDto dto = arg0.getCommonTaskDto();
			Integer userId = mtTask.getExeEndUserId();
			if (arg0.getIsSucc()
					&& TransTaskTypeEnum.TASK_TYPE_LOOP.getCode().equals(
							mtTask.getTaskType())) {
				arg0.setIsSucc(false);
				arg0.setMessage("接口调用错误！");
			}

			if (arg0.getIsSucc()
					&& !TransStatusEnum.TRANS_RUNNING.getCode().equals(
							mtTask.getStatus())) {
				// 进行中可以进行此操作
				arg0.setIsSucc(false);
				arg0.setMessage("运送任务当前状态无法进行完成操作！");
			}
			// String regex = new StringBuffer(",").append(userId)
			// .append(",|,").append(userId)
			// .append("$|^").append(userId)
			// .append(",|^").append(userId).append("$").toString();
			// if (arg0.getIsSucc() &&
			// (StringUtils.isBlank(dto.getTaskUserIds()) ||
			// !AppUtils.isContainsStr(regex, dto.getTaskUserIds()))) {
			// // 完成的任务的人员不可以没有主责任人
			// arg0.setIsSucc(false);
			// arg0.setMessage("执行人中没有主责任人【"+dto.getTaskUserIds()+"】！");
			// }

			if (arg0.getIsSucc()
					&& StringUtils.isNotBlank(dto.getTaskUserIds())
					&& userId != null
					&& !AppUtils.str2List(dto.getTaskUserIds()).contains(
							String.valueOf(userId))) {
				// 选择的运送人员必须包含自己(主责任人)
				arg0.setIsSucc(false);
				arg0.setMessage("选择的运送人员必须包含主责任人");
			}
			if (arg0.getIsSucc()
					&& StringUtils.isNotBlank(dto.getTaskUserIds())
					&& AppUtils.str2List(dto.getTaskUserIds()).size() > mtTask
							.getTransPersonCount().intValue() + 1) {
				// 完成任务时选择的运送人员大于任务单运送人数
				arg0.setIsSucc(false);
				arg0.setMessage("选择的运送人员人数不可以大于该运送单运送人数["
						+ (mtTask.getTransPersonCount().intValue() + 1) + "]！");
			}
			return arg0;
		}
	}
}