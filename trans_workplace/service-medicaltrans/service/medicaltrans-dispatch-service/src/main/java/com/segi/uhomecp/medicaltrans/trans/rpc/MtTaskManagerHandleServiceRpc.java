package com.segi.uhomecp.medicaltrans.trans.rpc;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import resp.RpcRespIce;
import segi.datacachesvr.queryInfo.TOrganInfo;
import segi.medicaltrans.mttask.manager.MtCommonIceParam;
import segi.medicaltrans.mttask.manager.MtTaskApplyPersonLiableRsp;
import segi.medicaltrans.mttask.manager.MtTaskGrabRsp;
import segi.medicaltrans.mttask.manager._MtTaskManagerHandleServiceIceDisp;
import Ice.Current;

import com.segi.uhomecp.medicaltrans.constant.MedicalTransRedisConstant;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.enums.TransStatusEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTaskTypeEnum;
import com.segi.uhomecp.medicaltrans.trans.dto.CommonTaskDto;
import com.segi.uhomecp.medicaltrans.trans.dto.VerifyParamDto;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskService;
import com.segi.uhomecp.medicaltrans.trans.service.AutonomousTaskService;
import com.segi.uhomecp.medicaltrans.trans.service.DispatchTaskService;
import com.segi.uhomecp.medicaltrans.trans.service.FixedTaskService;
import com.segi.uhomecp.medicaltrans.trans.service.MtCommonTaskService;
import com.segi.uhomecp.medicaltrans.trans.utils.TaskSendNoticeUtils;
import com.segi.uhomecp.medicaltrans.trans.utils.TaskStatusVerify;
import com.segi.uhomecp.medicaltrans.trans.utils.UpdateUserPositUnTaskNumUtils;
import com.segi.uhomecp.medicaltrans.utils.RedisLock;
import com.segi.uhomecp.redis.cluster.SegiRedisClusterBuilder;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.dto.ResultDto;
import com.segi.uhomecp.wh.common.interfaces.InvocationHandler;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;
/**
 * 
 * Title: MtTaskRpc.java    
 * @Description: 医疗运送任务RPC
 * @author wangxiong@segimail.com       
 * @created 2018年3月26日 上午9:55:31
 */
@Component
public class MtTaskManagerHandleServiceRpc  extends _MtTaskManagerHandleServiceIceDisp {

	private static final long serialVersionUID = 2986379028628667913L;
	
	private Logger logger = LoggerFactory.getLogger(MtTaskManagerHandleServiceRpc.class);
	
	@Resource
	private AutonomousTaskService autonomousTaskService;
	
	@Resource
	private DispatchTaskService dispatchTaskService;
	
	@Resource
	private FixedTaskService fixedTaskService;
	
	@Resource
	private MtCommonTaskService mtCommonTaskService;
	
	@Autowired
	private UpdateUserPositUnTaskNumUtils updateUserPositUnTaskNumUtils;
	
	@Autowired
	private TaskSendNoticeUtils taskSendNoticeUtils;
	
	@Resource
	private MtTaskService mtTaskService;
	
	@Resource(name = "segiRedisCluster")
    private SegiRedisClusterBuilder segiRedisCluster;

	/**
	 * @discription 抢单管理
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月24日 下午3:36:02      
	 * @param param
	 * @param __current
	 * @return  
	 *	1.获取redis同步锁
	 *	2.调用业务方法
	 *	3.释放redis同步锁   
	 */
	@Override
	public MtTaskGrabRsp grabMtDispatchTask(MtCommonIceParam param,
			Current __current) {
		MtTaskGrabRsp resp = new MtTaskGrabRsp();
		resp.setCode(RpcError.SUCCESS.getCode());
		resp.setMessage(RpcError.SUCCESS.getMessage());
		CommonTaskDto dto = BeanCopierUtils.copyProperties(param, CommonTaskDto.class, true);
		// 获取一级物业Id
		TOrganInfo organ = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
		Integer groupOrganId = null;
		if (null != organ) {
			groupOrganId = organ.getOrganId();
		} else {
			resp.setCode(RpcError.FAIL.getCode());
			resp.setMessage("查不到一级物业,抢单失败!");
			return resp;
		}
		String key = new StringBuffer(MedicalTransRedisConstant.DISPATCH_TASK_GRAB_LOCK).append(param.getTaskId()).toString();
		RedisLock lock = new RedisLock(key, segiRedisCluster.getSegiRedisCluster());
		boolean lockflag = lock.lock(60);//默认超时时间60秒
		if(!lockflag) {
			resp.setCode(RpcError.FAIL.getCode());
			resp.setMessage("抢单失败！");
			return resp;
		}
		// 验证运送任务状态
		VerifyParamDto verfiyRst = judgeTaskStatus(groupOrganId, dto,new TaskStatusVerify.GrabMtVerify()); 
		if(!verfiyRst.getIsSucc()) {
			resp.setCode(RpcError.FAIL.getCode());
			resp.setMessage(verfiyRst.getMessage());
			lock.unlock();
			return resp;
		}
		try {
			MtTask mtTask = verfiyRst.getMtTask();
			//到Service层对数据库进行操作
			ResultDto<String, Integer, Boolean> rst = this.dispatchTaskService.handlerGrabTask(groupOrganId, mtTask, dto);
			if(!rst.getIsSucc()) {
				resp.setCode(RpcError.SYSTEM_ERROR.getCode());
				resp.setMessage(rst.getMessage());
			} else {
				Boolean flag = rst.getObj();
				if ((null != mtTask.getExeEndUserId() && mtTask.getExeEndUserId().intValue() > 0) || flag) {
					resp.setIsPersonLiable(MtConstant.IS_PERSON_LIABLE);
				}
				resp.setIsGrabTask(MtConstant.IS_GRAB_TASK);
				resp.setTaskId(String.valueOf(mtTask.getTaskId()));
			}
		} catch (Exception e) {
			logger.error("grabMtDispatchTask", e);
			resp.code = RpcError.SYSTEM_ERROR.getCode();
			resp.message = RpcError.SYSTEM_ERROR.getMessage();
		} finally {
			lock.unlock();
		}
		return resp;
	}
	
	/**
	 * @discription 验证执行运送任务的状态
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月24日 下午8:08:36     
	 * @param dto
	 * @return
	 */
	private VerifyParamDto judgeTaskStatus(int groupOrganId, CommonTaskDto dto,
			InvocationHandler<VerifyParamDto, VerifyParamDto> handle) {
		VerifyParamDto rst = this.mtCommonTaskService.queryMtTaskById(groupOrganId, dto.getTaskId());
		rst.setCommonTaskDto(dto);
		return handle.invoke(rst);
	}
	
	/**
	 * @discription 设置主责任人
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月24日 上午10:53:13      
	 * @param mtCommonIceParam
	 * @param __current
	 * @return     
	 */
	@Override
	public MtTaskApplyPersonLiableRsp applyPersonLiable(
			MtCommonIceParam param, Current __current) {
		MtTaskApplyPersonLiableRsp resp = new MtTaskApplyPersonLiableRsp();
		resp.setCode(RpcError.SUCCESS.getCode());
		resp.setMessage(RpcError.SUCCESS.getMessage());
		CommonTaskDto dto = BeanCopierUtils.copyProperties(param, CommonTaskDto.class, true);
		// 获取一级物业Id
		TOrganInfo organ = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
		Integer groupOrganId = null;
		if (null != organ) {
			groupOrganId = organ.getOrganId();
		} else {
			resp.setCode(RpcError.FAIL.getCode());
			resp.setMessage("查不到一级物业,设置责任人失败!");
			return resp;
		}
		// 验证运送任务状态
		VerifyParamDto verfiyRst = this.mtCommonTaskService.queryMtTaskById(groupOrganId, dto.getTaskId());
		try {
			if(!verfiyRst.getIsSucc()) {
				resp.setCode(RpcError.FAIL.getCode());
				resp.setMessage(verfiyRst.getMessage());
				return resp;
			}
			MtTask mtTask = verfiyRst.getMtTask();
			ResultDto<String, String, MtTask> rst = this.dispatchTaskService.handlerSettingResponsiblePerson(groupOrganId, dto, mtTask);
			if (!rst.getIsSucc()) {
				resp.setCode(RpcError.FAIL.getCode());
				resp.setMessage(rst.getMessage());
			} else {
				resp.setExeEndUserId(String.valueOf(rst.getObj().getExeEndUserId()));
				resp.setTaskId(String.valueOf(mtTask.getTaskId()));
			}
		} catch (Exception e) {
			logger.error("applyPersonLiable", e);
			resp.code = RpcError.SYSTEM_ERROR.getCode();
			resp.message = "系统错误";
		}
		return resp;
	}

	/**
	 * @discription 退单
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月24日 上午10:53:51      
	 * @param mtCommonIceParam
	 * @param __current 
	 * @return     
	 */
	@Override
	public RpcRespIce backMtTask(MtCommonIceParam param,
			Current __current) {
		RpcRespIce resp = new RpcRespIce();
		resp.setCode(RpcError.SUCCESS.getCode());
		resp.setMessage(RpcError.SUCCESS.getMessage());
		CommonTaskDto dto = BeanCopierUtils.copyProperties(param, CommonTaskDto.class, true);
		// 获取一级物业Id
		TOrganInfo organ = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
		Integer groupOrganId = null;
		if (null != organ) {
			groupOrganId = organ.getOrganId();
		} else {
			resp.setCode(RpcError.FAIL.getCode());
			resp.setMessage("查不到一级物业,退单失败!");
			return resp;
		}
		VerifyParamDto verifyRst = this.mtCommonTaskService.queryMtTaskById(groupOrganId, dto.getTaskId());
		if(!verifyRst.getIsSucc()) {
			resp.setCode(RpcError.FAIL.getCode());
			resp.setMessage(verifyRst.getMessage());
			return resp;
		}
		try {
			MtTask task = verifyRst.getMtTask();
			ResultDto<String, String, MtTask> rst = new ResultDto<>(true,"");
			if(TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode().equals(task.getTaskType())) {
				// 调度任务单处理
				rst = this.dispatchTaskService.handlerBackTask(groupOrganId, task, dto);
			}
			if(TransTaskTypeEnum.TASK_TYPE_LOOP.getCode().equals(task.getTaskType())) {
				// 循环任务单处理
				rst = this.fixedTaskService.handlerBackTask(groupOrganId, task, dto);
			}
			if(!rst.getIsSucc()) {
				resp.setCode(RpcError.SYSTEM_ERROR.getCode());
				resp.setMessage(rst.getMessage());
			} else {
				resp.setData(rst.getVal());
			}
		} catch (Exception e) {
			logger.error("backMtTask", e);
			resp.code = RpcError.SYSTEM_ERROR.getCode();
			resp.message = RpcError.SYSTEM_ERROR.getMessage();
		}
		return resp;
	}



	/**
	 * @discription 开始运送任务
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月24日 上午10:54:57      
	 * @param mtCommonIceParam
	 * @param __current
	 * @return     
	 */
	@Override
	public RpcRespIce startMtTask(MtCommonIceParam param,
			Current __current) {
		RpcRespIce resp = new RpcRespIce();
		resp.setCode(RpcError.SUCCESS.getCode());
		resp.setMessage(RpcError.SUCCESS.getMessage());
		
		CommonTaskDto dto = BeanCopierUtils.copyProperties(param, CommonTaskDto.class, true);
		// 获取一级物业Id
		TOrganInfo organ = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
		Integer groupOrganId = null;
		if (null != organ) {
			groupOrganId = organ.getOrganId();
		} else {
			resp.setCode(RpcError.FAIL.getCode());
			resp.setMessage("查不到一级物业,任务开始失败!");
			return resp;
		}
		VerifyParamDto verfiyRst = judgeTaskStatus(groupOrganId, dto, new TaskStatusVerify.StartTaskVerify());
		if(!verfiyRst.getIsSucc()) {
			resp.setCode(RpcError.FAIL.getCode());
			resp.setMessage(verfiyRst.getMessage());
			return resp;
		}
		try {
			MtTask mtTask = verfiyRst.getMtTask();
			ResultDto<String, String, String> resultDto = 
					this.dispatchTaskService.handlerStartTask(groupOrganId, dto, mtTask);
			if(!resultDto.getIsSucc()) {
				resp.setCode(RpcError.FAIL.getCode());
				resp.setMessage(resultDto.getMessage());
			} else {
				resp.setData(resultDto.getObj());
			}
		} catch (Exception e) {
			logger.error("startMtTask", e);
			resp.code = RpcError.SYSTEM_ERROR.getCode();
			resp.message = "系统错误";
		}
		return resp;
	}

	/**
	 * @discription 取消运送任务
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月24日 上午10:55:12      
	 * @param mtCommonIceParam
	 * @param __current
	 * @return     
	 */
	@Override
	public RpcRespIce cancelMtTask(MtCommonIceParam param, Current __current) {
		RpcRespIce resp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		CommonTaskDto dto = BeanCopierUtils.copyProperties(param, CommonTaskDto.class, true);
		try {
			Integer curOrganId = dto.getOrganId();
			if (curOrganId == null) {
				curOrganId = dto.getUserOrganId();
			}
			// 获取一级物业Id
			TOrganInfo organ = CommonServiceUtils.getTopOrganByOrganID(curOrganId);
			Integer groupOrganId = null;
			if (null != organ) {
				groupOrganId = organ.getOrganId();
			} else {
				resp.setCode(RpcError.FAIL.getCode());
				resp.setMessage("查不到一级物业,取消任务失败!");
				return resp;
			}
			VerifyParamDto verfiyRst = judgeTaskStatus(groupOrganId, dto, new TaskStatusVerify.CancelTaskVerify());
			if(!verfiyRst.getIsSucc()) {
				resp.setCode(RpcError.FAIL.getCode());
				resp.setMessage(verfiyRst.getMessage());
				return resp;
			}
			ResultDto<String, String, Integer> rst = new ResultDto<>();
			MtTask mtTask = verfiyRst.getMtTask();
			if(TransTaskTypeEnum.TASK_TYPE_LOOP.getCode().equals(mtTask.getTaskType())) {
				// 循环任务取消
				rst = this.fixedTaskService.handlerCancelTask(groupOrganId, verfiyRst.getMtTask(), dto.getUserId(), dto.getUserOrganId());
			}
			if(TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode().equals(mtTask.getTaskType())) {
				// 调度任务取消
				rst = this.dispatchTaskService.handlerCancelTask(groupOrganId, verfiyRst.getMtTask(), dto.getUserId(), dto.getUserOrganId());
			}
			if(TransTaskTypeEnum.TASK_TYPE_SELF.getCode().equals(mtTask.getTaskType())) {
				// 自主任务取消
				rst = this.autonomousTaskService.handlerCancelTask(groupOrganId, verfiyRst.getMtTask(), dto.getUserId());
			}
			if(!rst.getIsSucc()) {
				resp.setCode(RpcError.FAIL.getCode());
				resp.setMessage(rst.getMessage());
			} else {
				resp.setData(String.valueOf(rst.getObj()));
			}
		} catch (Exception e) {
			logger.error("cancelMtTask", e);
			resp.code = RpcError.SYSTEM_ERROR.getCode();
			resp.message = "系统错误";
		}
		return resp;
	}
	
	/**
	 * @discription 完成运送任务
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月24日 上午10:55:34      
	 * @param mtCommonIceParam
	 * @param __current
	 * @return     
	 */
	@Override
	public RpcRespIce finishMtTask(MtCommonIceParam param,
			Current __current) {
		RpcRespIce resp = new RpcRespIce();
		resp.setCode(RpcError.SUCCESS.getCode());
		resp.setMessage(RpcError.SUCCESS.getMessage());
		CommonTaskDto dto = BeanCopierUtils.copyProperties(param, CommonTaskDto.class, true);
		// 获取一级物业Id
		TOrganInfo organ = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
		Integer groupOrganId = null;
		if (null != organ) {
			groupOrganId = organ.getOrganId();
		} else {
			resp.setCode(RpcError.FAIL.getCode());
			resp.setMessage("查不到一级物业,完成任务失败!");
			return resp;
		}
		VerifyParamDto verfiyRst = judgeTaskStatus(groupOrganId, dto,new TaskStatusVerify.FinishTaskVerify());
		if(!verfiyRst.getIsSucc()) {
			resp.setCode(RpcError.FAIL.getCode());
			resp.setMessage(verfiyRst.getMessage());
			return resp;
		}
		// 更新验证是否执行 true 表示执行， false 表示没有
		MtTask mtTask = verfiyRst.getMtTask();
		try {
			ResultDto<String, String, String> resultDto = null;
			if(TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode().equals(mtTask.getTaskType())) {
				// 调度任务完成
				resultDto = this.dispatchTaskService.handlerFinishTask(groupOrganId, mtTask, dto);
			}
			if(TransTaskTypeEnum.TASK_TYPE_SELF.getCode().equals(mtTask.getTaskType())) {
				// 自主任务完成
				resultDto = this.autonomousTaskService.handlerFinishTask(groupOrganId, mtTask, dto);
			}
			if(!resultDto.getIsSucc()) {
				resp.setCode(RpcError.FAIL.getCode());
				resp.setMessage(resultDto.getMessage());
			} else {
				resp.setData(resultDto.getObj());
			}
		} catch (Exception e) {
			logger.error("finishMtTask", e);
			resp.code = RpcError.SYSTEM_ERROR.getCode();
			resp.message = "系统错误";
		} 
		return resp;
	}

	/**
	 * @discription 运送评价
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月24日 下午3:31:46      
	 * @param mtCommonIceParam
	 * @param __current
	 * @return     
	 */
	@Override
	public RpcRespIce evaluate(MtCommonIceParam param,
			Current __current) {
		RpcRespIce resp = new RpcRespIce();
		resp.setCode(RpcError.SUCCESS.getCode());
		resp.setMessage(RpcError.SUCCESS.getMessage());
		CommonTaskDto dto = BeanCopierUtils.copyProperties(param, CommonTaskDto.class, true);
		// 获取一级物业Id
		TOrganInfo organ = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
		Integer groupOrganId = null;
		if (null != organ) {
			groupOrganId = organ.getOrganId();
		} else {
			resp.setCode(RpcError.FAIL.getCode());
			resp.setMessage("查不到一级物业,评价失败!");
			return resp;
		}
		VerifyParamDto verfiyRst = judgeTaskStatus(groupOrganId, dto,new TaskStatusVerify.EvaluateTaskVerify());
		if(!verfiyRst.getIsSucc()) {
			resp.setCode(RpcError.FAIL.getCode());
			resp.setMessage(verfiyRst.getMessage());
			return resp;
		}
		try {
			ResultDto<String, String, String> resultDto = 
					this.mtCommonTaskService.handlerEvaluate(groupOrganId, dto, verfiyRst.getMtTask());
			if(!resultDto.getIsSucc()) {
				resp.setCode(RpcError.SUCCESS.getCode());
				resp.setMessage(RpcError.SUCCESS.getMessage());
			} else {
				resp.setData(resultDto.getObj());
			}
		} catch (Exception e) {
			logger.error("evaluate", e);
			resp.code = RpcError.SYSTEM_ERROR.getCode();
			resp.message = "系统错误";
		}
		return resp;
	}

	/**
	 * @discription 签名
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月25日 下午4:47:52      
	 * @param mtCommonIceParam
	 * @param __current
	 * @return     
	 */
	@Override
	public RpcRespIce autograph(MtCommonIceParam mtCommonIceParam,
			Current __current) {
		RpcRespIce resp = new RpcRespIce();
		resp.setCode(RpcError.SUCCESS.getCode());
		resp.setMessage(RpcError.SUCCESS.getMessage());
		try {
			CommonTaskDto dto = BeanCopierUtils.copyProperties(mtCommonIceParam, CommonTaskDto.class, true);
			// 获取一级物业Id
			TOrganInfo organ = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
			Integer groupOrganId = null;
			if (null != organ) {
				groupOrganId = organ.getOrganId();
			} else {
				resp.setCode(RpcError.FAIL.getCode());
				resp.setMessage("查不到一级物业,签名失败!");
				return resp;
			}
			// 处理是否可签名
			resp = mtCommonTaskService.checkCanAutograph(groupOrganId, dto.getRouteId());
			if (RpcError.SUCCESS.getCode().equals(resp.code)) {
				String routeId = mtCommonTaskService.handlerAutograph(groupOrganId, dto);
				resp.setData(routeId);
			}
		} catch (Exception e) {
			logger.error("autograph", e);
			resp.code = RpcError.SYSTEM_ERROR.getCode();
			resp.message = "运送任务签名失败!";
		}
		return resp;
	}

	/**
	 * @discription 固定任务完成
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月2日 上午10:08:00      
	 * @param mtCommonIceParam
	 * @param __current
	 * @return     
	 */
	@Override
	public RpcRespIce finishFixedTask(MtCommonIceParam param,
			Current __current) {
		RpcRespIce resp = new RpcRespIce();
		resp.setCode(RpcError.SUCCESS.getCode());
		resp.setMessage(RpcError.SUCCESS.getMessage());
		CommonTaskDto dto = BeanCopierUtils.copyProperties(param, CommonTaskDto.class, true);
		// 获取一级物业Id
		TOrganInfo organ = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
		Integer groupOrganId = null;
		if (null != organ) {
			groupOrganId = organ.getOrganId();
		} else {
			resp.setCode(RpcError.FAIL.getCode());
			resp.setMessage("查不到一级物业,固定任务完成失败!");
			return resp;
		}
		VerifyParamDto verfiyRst = judgeTaskStatus(groupOrganId, dto,new TaskStatusVerify.FinishFixedTaskVerify());
		if(!verfiyRst.getIsSucc()) {
			resp.setCode(RpcError.FAIL.getCode());
			resp.setMessage(verfiyRst.getMessage());
			return resp;
		}
		// 更新验证是否执行 true 表示执行， false 表示没有
		boolean updateVerify = false;
		MtTask mtTask = verfiyRst.getMtTask();
		Date nowDate = new Date();
		long currentTime = nowDate.getTime();
		try {
			if(TransStatusEnum.TRANS_NOT_START.getCode().equals(mtTask.getStatus())) {
				updateVerify = true;
				// 预计开始时间
				long beginTime = DateUtil.parseStringToDate(String.valueOf(mtTask.getBeginTime() + "00"), 
						DateUtil.FORMAT_YYYYMMDDHHMMSS).getTime();
				// 响应时间 返回秒
				Integer respTime = 0;
				if (currentTime - beginTime > 0) {
					Long rspTime = (currentTime - beginTime) / 1000;
					respTime = rspTime.intValue();
				}
				MtTask updateMtTask = new MtTask();
				updateMtTask.setUpdateBy(dto.getUserId());
				updateMtTask.setUpdateDate(nowDate);
				updateMtTask.setExeBeginTime(nowDate);
				updateMtTask.setStatus(TransStatusEnum.TRANS_RUNNING.getCode());
				updateMtTask.setRespTime(respTime);
				int cnt = this.mtCommonTaskService.updateMtTaskStatus(groupOrganId, updateMtTask,
						TransStatusEnum.TRANS_NOT_START.getCode(), dto.getTaskId());
				if(cnt <= 0) {
					resp.setCode(RpcError.SYSTEM_ERROR.getCode());
					resp.setMessage("当前运送单已被其他人操作！");
					return resp;
				}
				// 修改执行人任务状态及实际开始时间
				this.mtCommonTaskService.updateTaskExcutors(groupOrganId, mtTask.getTaskId(), String.valueOf(mtTask.getExeEndUserId()), null, 
						TransStatusEnum.TRANS_RUNNING.getCode(), null, nowDate, null);
			}
			ResultDto<String, String, String> resultDto = this.fixedTaskService.handlerFinishTask(groupOrganId, mtTask, dto);
			if(!resultDto.getIsSucc()) {
				resp.setCode(RpcError.FAIL.getCode());
				resp.setMessage(resultDto.getMessage());
			} else {
				resp.setData(resultDto.getObj());
				if (TransStatusEnum.TRANS_NOT_START.getCode().equals(mtTask.getStatus())) {
					// 事务一致性
					this.updateUserPositUnTaskNumUtils.updateUserPositUnTaskNum(mtTask.getOrganId(), dto.getUserId(), 
							(short)0, (short)1, mtTask.getBeginTime(), String.valueOf(mtTask.getFromHouseId()));
					// 第一个点任务完成,删除超时未开始提醒
					this.taskSendNoticeUtils.delOverTimeNotStarted(mtTask.getTaskId());
				}
			}
		} catch (Exception e) {
			logger.error("finishFixedTask", e);
			resp.code = RpcError.SYSTEM_ERROR.getCode();
			resp.message = "系统错误";
			if(updateVerify) {
				MtTask updateMtTask = new MtTask();
				updateMtTask.setUpdateBy(mtTask.getUpdateBy());
				updateMtTask.setUpdateDate(mtTask.getUpdateDate());
				updateMtTask.setStatus(TransStatusEnum.TRANS_NOT_START.getCode());
				this.mtCommonTaskService.updateMtTaskStatus(groupOrganId, updateMtTask,
						TransStatusEnum.TRANS_RUNNING.getCode(), dto.getTaskId());	
			}
		} 
		return resp;
	}

	/**
	 * @Title: startTaskForWeb 
	 * @Description: 调度任务开始  
	 * @author liuyi@segimail.com 
	 * @date 2018年9月10日下午6:16:42
	 */
	@Override
	public RpcRespIce startTaskForWeb(MtCommonIceParam param,
			Current __current) {
		RpcRespIce resp = new RpcRespIce();
		resp.setCode(RpcError.SUCCESS.getCode());
		resp.setMessage(RpcError.SUCCESS.getMessage());
		
		CommonTaskDto dto = BeanCopierUtils.copyProperties(param, CommonTaskDto.class, true);
		// 获取一级物业Id
		TOrganInfo organ = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
		Integer groupOrganId = null;
		if (null != organ) {
			groupOrganId = organ.getOrganId();
		} else {
			resp.setCode(RpcError.FAIL.getCode());
			resp.setMessage("查不到一级物业,任务开始失败!");
			return resp;
		}
		VerifyParamDto verfiyRst = judgeTaskStatus(groupOrganId, dto, new TaskStatusVerify.StartWebTaskVerify());
		if(!verfiyRst.getIsSucc()) {
			resp.setCode(RpcError.FAIL.getCode());
			resp.setMessage(verfiyRst.getMessage());
			return resp;
		}
		try {
			MtTask mtTask = verfiyRst.getMtTask();
			ResultDto<String, String, String> resultDto = 
					this.dispatchTaskService.handlerStartTask(groupOrganId, dto, mtTask);
			if(!resultDto.getIsSucc()) {
				resp.setCode(RpcError.FAIL.getCode());
				resp.setMessage(resultDto.getMessage());
			} else {
				resp.setData(resultDto.getObj());
			}
		} catch (Exception e) {
			logger.error("startTaskForWeb", e);
			resp.code = RpcError.SYSTEM_ERROR.getCode();
			resp.message = "系统错误";
		}
		return resp;
	}

	/**
	 * @Title: finishTaskForWeb 
	 * @Description: 完成任务web 调度 自主
	 * @author liuyi@segimail.com 
	 * @date 2018年9月11日上午10:52:49
	 */
	@Override
	public RpcRespIce finishTaskForWeb(MtCommonIceParam param,
			Current __current) {
		RpcRespIce resp = new RpcRespIce();
		resp.setCode(RpcError.SUCCESS.getCode());
		resp.setMessage(RpcError.SUCCESS.getMessage());
		CommonTaskDto dto = BeanCopierUtils.copyProperties(param, CommonTaskDto.class, true);
		// 获取一级物业Id
		TOrganInfo organ = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
		Integer groupOrganId = null;
		if (null != organ) {
			groupOrganId = organ.getOrganId();
		} else {
			resp.setCode(RpcError.FAIL.getCode());
			resp.setMessage("查不到一级物业,完成任务失败!");
			return resp;
		}
		VerifyParamDto verfiyRst = judgeTaskStatus(groupOrganId, dto,new TaskStatusVerify.FinishWebTaskVerify());
		if(!verfiyRst.getIsSucc()) {
			resp.setCode(RpcError.FAIL.getCode());
			resp.setMessage(verfiyRst.getMessage());
			return resp;
		}
		// 更新验证是否执行 true 表示执行， false 表示没有
		MtTask mtTask = verfiyRst.getMtTask();
		try {
			ResultDto<String, String, String> resultDto = null;
			if(TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode().equals(mtTask.getTaskType())) {
				// 调度任务完成
				resultDto = this.dispatchTaskService.handlerFinishWebTask(groupOrganId, mtTask, dto);
			}
			if(TransTaskTypeEnum.TASK_TYPE_SELF.getCode().equals(mtTask.getTaskType())) {
				// 自主任务完成
				resultDto = this.autonomousTaskService.handlerFinishWebTask(groupOrganId, mtTask, dto);
			}
			if(!resultDto.getIsSucc()) {
				resp.setCode(RpcError.FAIL.getCode());
				resp.setMessage(resultDto.getMessage());
			} else {
				resp.setData(resultDto.getObj());
			}
		} catch (Exception e) {
			logger.error("finishMtTask", e);
			resp.code = RpcError.SYSTEM_ERROR.getCode();
			resp.message = "系统错误";
		} 
		return resp;
	}

	/**
	 * @Title: photograph 
	 * @Description: 运送任务拍照 
	 * @author liuyi@segimail.com 
	 * @date 2018年9月12日下午3:55:08
	 */
	@Override
	public RpcRespIce photograph(MtCommonIceParam mtCommonIceParam,
			Current __current) {
		RpcRespIce resp = new RpcRespIce();
		resp.setCode(RpcError.SUCCESS.getCode());
		resp.setMessage(RpcError.SUCCESS.getMessage());
		try {
			CommonTaskDto dto = BeanCopierUtils.copyProperties(mtCommonIceParam, CommonTaskDto.class, true);
			// 获取一级物业Id
			TOrganInfo organ = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
			Integer groupOrganId = null;
			if (null != organ) {
				groupOrganId = organ.getOrganId();
			} else {
				resp.setCode(RpcError.FAIL.getCode());
				resp.setMessage("查不到一级物业,拍照失败!");
				return resp;
			}
			// 处理是否可拍照
			resp = mtCommonTaskService.checkCanPhotograph(groupOrganId, dto.getTaskId());
			if (RpcError.SUCCESS.getCode().equals(resp.code)) {
				// 处理附件
				CommonServiceUtils.updateRefIdByFileIds(mtCommonIceParam.getPhotographFileIds(), mtCommonIceParam.getTaskId());
				resp.setData(mtCommonIceParam.getTaskId());
			}
		} catch (Exception e) {
			logger.error("photograph", e);
			resp.code = RpcError.SYSTEM_ERROR.getCode();
			resp.message = "运送任务拍照失败!";
		}
		return resp;
	}
}
