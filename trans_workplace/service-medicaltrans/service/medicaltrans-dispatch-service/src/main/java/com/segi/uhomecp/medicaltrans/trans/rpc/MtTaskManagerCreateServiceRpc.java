package com.segi.uhomecp.medicaltrans.trans.rpc;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.enums.TransTaskTypeEnum;
import com.segi.uhomecp.medicaltrans.trans.dto.AutonomousTaskDto;
import com.segi.uhomecp.medicaltrans.trans.dto.CommonTaskDto;
import com.segi.uhomecp.medicaltrans.trans.dto.DispatchTaskDto;
import com.segi.uhomecp.medicaltrans.trans.dto.FixedTaskDto;
import com.segi.uhomecp.medicaltrans.trans.dto.VerifyParamDto;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskService;
import com.segi.uhomecp.medicaltrans.trans.service.AutonomousTaskService;
import com.segi.uhomecp.medicaltrans.trans.service.DispatchTaskService;
import com.segi.uhomecp.medicaltrans.trans.service.FixedTaskService;
import com.segi.uhomecp.medicaltrans.trans.service.MtCommonTaskService;
import com.segi.uhomecp.medicaltrans.trans.utils.TaskStatusVerify;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.redis.cluster.SegiRedisClusterBuilder;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.dto.ResultDto;
import com.segi.uhomecp.wh.common.enums.DataSourceEnum;
import com.segi.uhomecp.wh.common.interfaces.InvocationHandler;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;

import Ice.Current;
import resp.RpcRespIce;
import segi.datacachesvr.queryInfo.TOrganInfo;
import segi.medicaltrans.mttask.manager.MtFixedTaskParam;
import segi.medicaltrans.mttask.manager.MtTaskReq;
import segi.medicaltrans.mttask.manager.SendTaskIceParam;
import segi.medicaltrans.mttask.manager._MtTaskManagerCreateServiceIceDisp;
/**
 * 
 * Title: MtTaskRpc.java    
 * @Description: 医疗运送任务RPC
 * @author wangxiong@segimail.com       
 * @created 2018年3月26日 上午9:55:31
 */
@Component
public class MtTaskManagerCreateServiceRpc  extends _MtTaskManagerCreateServiceIceDisp {

	private static final long serialVersionUID = 2986379028628667913L;
	
	private Logger logger = LoggerFactory.getLogger(MtTaskManagerCreateServiceRpc.class);
	
	@Resource
	private AutonomousTaskService autonomousTaskService;
	
	@Resource
	private DispatchTaskService dispatchTaskService;
	
	@Resource
	private FixedTaskService fixedTaskService;
	
	@Resource
	private MtCommonTaskService mtCommonTaskService;
	
	@Resource
	private MtTaskService mtTaskService;
	
	@Resource(name = "segiRedisCluster")
    private SegiRedisClusterBuilder segiRedisCluster;
	
	/**
	 * @discription 创建运送任务
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月17日 下午8:20:59      
	 * @param param
	 * @param __current
	 * @return     
	 */
	@Override
	public RpcRespIce createTask(MtTaskReq param,
			Current __current) {
		RpcRespIce resp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			ResultDto<String, String, Integer> rst = null;
			// 获取一级物业Id
			TOrganInfo organ = CommonServiceUtils.getTopOrganByOrganID(Integer.valueOf(param.getOrganId()));
			Integer groupOrganId = null;
			if (null != organ) {
				groupOrganId = organ.getOrganId();
			} else {
				resp.setCode(RpcError.FAIL.getCode());
				resp.setMessage("查不到一级物业,创建任务失败!");
				return resp;
			}
			if(TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode().equals(param.getTaskType())) {
				// 到Service层对数据库进行操作
				DispatchTaskDto dto = BeanCopierUtils.copyProperties(param, DispatchTaskDto.class, true);
				if (!DataSourceEnum.DATA_SOURCE_WEB.getCode().equals(dto.getDataSource())) {
					// pad端医护人员发起调度任务
					rst = this.dispatchTaskService.createPadTask(groupOrganId, dto);
				} else {
					// 是否web端医护人员发起调度任务 ? web端医护人员发起调度任务 : web端调度任务发起调度任务并派单
					rst = dto.getFlag() ? this.dispatchTaskService.createPadTask(groupOrganId, dto) :
						this.dispatchTaskService.createTask(groupOrganId, dto);
				}
			} else if(TransTaskTypeEnum.TASK_TYPE_SELF.getCode().equals(param.getTaskType())) {
				// 自主任务创建
				AutonomousTaskDto dto = BeanCopierUtils.copyProperties(param, AutonomousTaskDto.class, true);
				rst = this.autonomousTaskService.createTask(groupOrganId, dto);
			} else {
				rst = new ResultDto<>(false,"不支持该类型运送任务");
			}
			if(!rst.getIsSucc()) {
				resp.setCode(RpcError.SYSTEM_ERROR.getCode());
				resp.setMessage(rst.getMessage());
			} else {
				resp.setData(String.valueOf(rst.getObj()));
				MtCommonServiceUtils.addOrgan(param.getOrganId());
			}
		} catch (Exception e) {
			logger.error("createTask", e);
			resp.code = RpcError.SYSTEM_ERROR.getCode();
			resp.message = RpcError.SYSTEM_ERROR.getMessage();
		}
		return resp;
	}

	/**
	 * @discription 创建循环运送任务
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月17日 下午8:23:26      
	 * @param param
	 * @param __current
	 * @return     
	 */
	@Override
	public RpcRespIce createFixedTask(MtFixedTaskParam param, Current __current) {
		RpcRespIce resp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			FixedTaskDto dto = BeanCopierUtils.copyProperties(param, FixedTaskDto.class, true);
			dto.setTransactors(param.getTransactors());
			dto.setRouteList(param.getRouteList());
			Integer groupOrganId = dto.getGroupOrganId();
			if (null == groupOrganId || groupOrganId.intValue() < 0) {
				TOrganInfo organ = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
				if (null != organ) {
					groupOrganId = organ.getOrganId();
				} else {
					resp.setCode(RpcError.FAIL.getCode());
					resp.setMessage("创建失败,查不到一级物业!");
					return resp;
				}
			}
			//到Service层对数据库进行操作
			ResultDto<String, String, Integer> rst = this.fixedTaskService.createTask(groupOrganId, dto);
			if(!rst.getIsSucc()) {
				resp.setCode(RpcError.SYSTEM_ERROR.getCode());
				resp.setMessage(rst.getMessage());
			}
		} catch (Exception e) {
			logger.error("createFixedTask", e);
			resp.code = RpcError.SYSTEM_ERROR.getCode();
			resp.message = RpcError.SYSTEM_ERROR.getMessage();
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
	 * @discription 编辑运送任务
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月24日 上午10:54:38      
	 * @param param
	 * @param __current
	 * @return     
	 */
	@Override
	public RpcRespIce editTask(MtTaskReq param, Current __current) {
		RpcRespIce resp = new RpcRespIce();
		resp.setCode(RpcError.SUCCESS.getCode());
		resp.setMessage(RpcError.SUCCESS.getMessage());
		CommonTaskDto commomTashDto = BeanCopierUtils.copyProperties(param, CommonTaskDto.class, true);
		// 获取一级物业Id
		TOrganInfo organ = CommonServiceUtils.getTopOrganByOrganID(commomTashDto.getOrganId());
		Integer groupOrganId = null;
		if (null != organ) {
			groupOrganId = organ.getOrganId();
		} else {
			resp.setCode(RpcError.FAIL.getCode());
			resp.setMessage("查不到一级物业,编辑任务单失败!");
			return resp;
		}
		VerifyParamDto verfiyRst = 
				judgeTaskStatus(groupOrganId, commomTashDto, new TaskStatusVerify.EditTaskVerify());
		if(!verfiyRst.getIsSucc()) {
			resp.setCode(RpcError.FAIL.getCode());
			resp.setMessage(verfiyRst.getMessage());
			return resp;
		}
		try {
			MtTask mtTask = verfiyRst.getMtTask();
			DispatchTaskDto dto = BeanCopierUtils.copyProperties(param, DispatchTaskDto.class, true);
			ResultDto<String, String, String> resultDto = this.dispatchTaskService.handlerEditTask(groupOrganId, dto, mtTask);
			if(!resultDto.getIsSucc()) {
				resp.setCode(RpcError.FAIL.getCode());
				resp.setMessage(resultDto.getMessage());
			} else {
				resp.setData(resultDto.getObj());
			}
		} catch (Exception e) {
			logger.error("editTask", e);
			resp.code = RpcError.SYSTEM_ERROR.getCode();
			resp.message = "系统错误";
		}
		return resp;
	}

	/**
	 * @discription 调度任务重新派单
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月25日 下午4:37:10      
	 * @param sendTaskIceParam
	 * @param __current
	 * @return     
	 */
	@Override
	public RpcRespIce againDispatchTask(SendTaskIceParam sendTaskIceParam,
			Current __current) {
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		CommonTaskDto dto = BeanCopierUtils.copyProperties(sendTaskIceParam, CommonTaskDto.class, true);
		// 获取一级物业Id
		TOrganInfo organ = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
		Integer groupOrganId = null;
		if (null != organ) {
			groupOrganId = organ.getOrganId();
		} else {
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("查不到一级物业,重新派单失败!");
			return rsp;
		}
		VerifyParamDto verfiyRst = 
				judgeTaskStatus(groupOrganId, dto,new TaskStatusVerify.AgainDispatchTaskVerify());
		if(!verfiyRst.getIsSucc()) {
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(verfiyRst.getMessage());
			return rsp;
		}
		try {
			DispatchTaskDto taskDto = BeanCopierUtils.copyProperties(sendTaskIceParam, DispatchTaskDto.class, true);
			ResultDto<String, String, Integer> rst = dispatchTaskService.handlerAgainDispatchTask(
					groupOrganId, verfiyRst.getMtTask(), taskDto);
			if (!rst.getIsSucc()) {
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage(rst.getMessage());
			} else {
				rsp.setData(String.valueOf(rst.getObj()));
			}
		} catch (Exception e) {
			logger.error("againDispatchTask", e);
			rsp.code = RpcError.SYSTEM_ERROR.getCode();
			rsp.message = "系统错误";
		}
		return rsp;
	}

	/**
	 * @discription 固定任务重新派单
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月25日 下午4:37:31      
	 * @param sendTaskIceParam
	 * @param __current
	 * @return     
	 */
	@Override
	public RpcRespIce againFixedTask(SendTaskIceParam sendTaskIceParam,
			Current __current) {
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		CommonTaskDto dto = BeanCopierUtils.copyProperties(sendTaskIceParam, CommonTaskDto.class, true);
		// 获取一级物业Id
		TOrganInfo organ = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
		Integer groupOrganId = null;
		if (null != organ) {
			groupOrganId = organ.getOrganId();
		} else {
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("查不到一级物业,重新派单失败!");
			return rsp;
		}
		VerifyParamDto verfiyRst = 
				judgeTaskStatus(groupOrganId, dto,new TaskStatusVerify.AgainFixedTaskVerify());
		if(!verfiyRst.getIsSucc()) {
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(verfiyRst.getMessage());
			return rsp;
		}
		try {
			FixedTaskDto taskDto = BeanCopierUtils.copyProperties(sendTaskIceParam, FixedTaskDto.class, true);
			ResultDto<String, String, Integer> rst = fixedTaskService.handlerAgainTask(groupOrganId, verfiyRst.getMtTask(), taskDto);
			if (!rst.getIsSucc()) {
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage(rst.getMessage());
			} else {
				rsp.setData(String.valueOf(rst.getObj()));
			}
		} catch (Exception e) {
			logger.error("againDispatchTask", e);
			rsp.code = RpcError.SYSTEM_ERROR.getCode();
			rsp.message = "系统错误";
		}
		return rsp;
	}
	
	/**
	 * @discription 调度任务派单
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月25日 下午4:36:57      
	 * @param sendTaskIceParam
	 * @param __current
	 * @return     
	 */
	@Override
	public RpcRespIce dispatchTask(SendTaskIceParam sendTaskIceParam,
			Current __current) {
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		CommonTaskDto dto = BeanCopierUtils.copyProperties(sendTaskIceParam, CommonTaskDto.class, true);
		// 获取一级物业Id
		TOrganInfo organ = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
		Integer groupOrganId = null;
		if (null != organ) {
			groupOrganId = organ.getOrganId();
		} else {
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("查不到一级物业,派单失败!");
			return rsp;
		}
		VerifyParamDto verfiyRst = judgeTaskStatus(groupOrganId, dto,new TaskStatusVerify.DispatchTaskVerify());
		if(!verfiyRst.getIsSucc()) {
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(verfiyRst.getMessage());
			return rsp;
		}
		try {
			DispatchTaskDto taskDto = BeanCopierUtils.copyProperties(sendTaskIceParam, DispatchTaskDto.class, true);
			ResultDto<String, String, Integer> rst = dispatchTaskService.handlerDispatchTask(groupOrganId, taskDto, verfiyRst.getMtTask());
			if (!rst.getIsSucc()) {
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage(rst.getMessage());
			} else {
				rsp.setData(String.valueOf(rst.getObj()));
			}
		} catch (Exception e) {
			logger.error("dispatchTask", e);
			rsp.code = RpcError.SYSTEM_ERROR.getCode();
			rsp.message = "系统错误";
		}
		return rsp;
	}
}
