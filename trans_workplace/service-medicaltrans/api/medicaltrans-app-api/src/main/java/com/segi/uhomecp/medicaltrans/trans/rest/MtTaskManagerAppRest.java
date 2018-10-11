package com.segi.uhomecp.medicaltrans.trans.rest;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import resp.RpcRespIce;
import segi.medicaltrans.mttask.manager.MtCommonIceParam;
import segi.medicaltrans.mttask.manager.MtTaskApplyPersonLiableRsp;
import segi.medicaltrans.mttask.manager.MtTaskGrabRsp;
import segi.medicaltrans.mttask.manager.MtTaskManagerCreateServiceIcePrx;
import segi.medicaltrans.mttask.manager.MtTaskManagerHandleServiceIcePrx;
import segi.medicaltrans.mttask.manager.MtTaskReq;
import IceExt.IceClientUtil;

import com.segi.uhomecp.common.rest.AbsActionRest;
import com.segi.uhomecp.medicaltrans.common.MtApiConstant;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.enums.TransTaskTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTypeEnum;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.sso.auth.vo.User;
import com.segi.uhomecp.sso.client.annotation.AdminUserParam;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.web.rest.RestResponse;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.CheckRestParams;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 
 * Title: MedicaltransAppRest.java    
 * @Description: 描述 运送类型管理
 * @author wangxiong@segimail.com       
 * @created 2018年4月3日 上午10:55:19
 */
@Controller
@RequestMapping("/rest-api/v1/medicaltrans/mtTaskManagerAppRest")
@Api(value = "medicaltrans/mtTaskManagerAppRest", description = "运送任务管理")
public class MtTaskManagerAppRest extends AbsActionRest{

	private static final Logger logger = LoggerFactory.getLogger(MtTaskManagerAppRest.class);

	private static final String PREFIXPROJECTNAME = "[" + MtApiConstant.MODEL_NAME + "]";
	
	private MtTaskManagerCreateServiceIcePrx getMtTaskManagerCreateServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(MtTaskManagerCreateServiceIcePrx.class);
	}
	
	private MtTaskManagerHandleServiceIcePrx getMtTaskManagerHandleServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(MtTaskManagerHandleServiceIcePrx.class);
	}

	@ApiOperation(value = PREFIXPROJECTNAME + "创建自主任务单", response = String.class, notes = "{<br/>"
		+ "\"organId\": \"项目ID\",<br/>"          
		+ "\"transTypeParentCode\": \"一级运送大类\",<br/>"
		+ "\"transTypeId\": \"二级运送类型\",<br/>"      
		+ "\"fromHouseId\": \"科室出发地\",<br/>"      
		+ "\"toHouseId\": \"科室目的地\",<br/>"       
		+ "\"sourceHouseId\": \"运送单来源\",<br/>"      
		+ "\"transTools\": \"工具\",<br/>"       
		+ "\"fileIds\": \"文件IDs\",<br/>"          
		+ "\"taskContent\": \"任务备注\",<br/>"       
		+ "\"patientName\": \"患者姓名\",<br/>"     
		+ "\"bedNo\": \"床号\",<br/>"             
		+ "\"patientGender\": \"患者性别\",<br/>"     
		+ "\"medicalRecNo\": \"病历号\"<br/>"    
		+ "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/saveTaskByTransUser.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse saveTaskByTransUser(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (user == null) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录，请重新登录!").buidler();
		}
		try {
			MtTaskReq params = FastjsonUtils.parseObject(paramJson, MtTaskReq.class);
			String fields = null;
			String messages = null;
			String errInfo = null;
			if(TransTypeEnum.TRANS_TYPE_04.getCode().equals(params.getTransTypeParentCode())) {
				// 运送大类是病人看护，需要对下面的字段进行效验必填性
				fields = "organId, transTypeParentCode, transTypeId, fromHouseId, toHouseId, transTools, patientName, bedNo, patientGender";
				messages = "项目ID, 一级分类编码（大类）, 运送类型编码（小类）, 出发地编码, 目的地编码, 工具, 患者姓名, 床号, 患者性别";
				errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			}else {
				//必填参数验证
				fields = "organId, transTypeParentCode, transTypeId, fromHouseId, toHouseId, transTools";
				messages = "项目ID, 一级分类编码（大类）, 运送类型编码（小类）, 出发地编码, 目的地编码, 工具";
				errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			}
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			fields = "organId, transTypeId, fromHouseId, toHouseId";
			messages = "组织机构Id, 二级运送类型, 出发地科室, 目的地科室";
			errInfo = CheckRestParams.checkDigits(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			params.setDataSource(this.getSource());
			if (StringUtils.isEmpty(params.getSourceHouseId())) {
				params.setSourceHouseId(params.getFromHouseId());
			}
			// 添加运输类型
			params.setTaskType(TransTaskTypeEnum.TASK_TYPE_SELF.getCode());
			RpcRespIce resp =  this.getMtTaskManagerCreateServiceIcePrx().createTask(params);
			if (RpcError.SUCCESS.getCode().equals(resp.code)) {
				Map<String, String> dataMap = new HashMap<>();
				dataMap.put("id", resp.getData());
				return RestResponse.RestResponseBuilder.createSuccessBuilder("自主任务创建成功").setResult(dataMap).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(resp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常，创建自主任务单失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常，创建自主任务单失败！").buidler();
		}
	}
	
	/**
	 * @discription 调度任务抢单
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月24日 下午5:49:39     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "调度任务抢单", response = String.class, notes = "{<br/>"
			+ "\"taskId\": \"运送任务Id\",<br/>"          
			+ "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/grabMtDispatchTask.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse grabMtDispatchTask(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (user == null) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录，请重新登录!").buidler();
		}
		try {
			MtCommonIceParam params = FastjsonUtils.parseObject(paramJson, MtCommonIceParam.class);
			String errInfo = CheckRestParams.checkEmpty(params, "taskId", "运送任务Id");
			if (null != errInfo) {
				//非空验证
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			errInfo = CheckRestParams.checkInteger(params, "taskId", "运送任务Id");
			if (null != errInfo) {
				//number类型参数验证
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			params.setOrganId(this.getOrganId());
			MtTaskGrabRsp rsp = getMtTaskManagerHandleServiceIcePrx().grabMtDispatchTask(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, String> map = new HashMap<>();
				map.put("id", rsp.getTaskId());
				map.put("isPersonLiable", rsp.getIsPersonLiable());
				map.put("isGrabTask", rsp.getIsGrabTask());
				return RestResponse.RestResponseBuilder.createSuccessBuilder("抢单成功").setResult(map).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常，抢单失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常，抢单失败！").buidler();
		}
	}
	
	/**
	 * @discription 申请为主责任人
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月24日 下午7:14:06     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "申请为主责任人", response = String.class, notes = "{<br/>"
			+ "\"taskId\": \"运送任务Id\",<br/>"     
			+ "\"applyStatus\": \"是否申请主责任人 1:是         0不是\"<br/>"   
			+ "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/applyPersonLiable.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse applyPersonLiable(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (user == null) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录，请重新登录!").buidler();
		}
		try {
			MtCommonIceParam params = FastjsonUtils.parseObject(paramJson, MtCommonIceParam.class);
			String errInfo = CheckRestParams.checkEmpty(params, "taskId,applyStatus", "运送任务Id,是否担任主责任人");
			if (null != errInfo) {
				//非空验证
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			errInfo = CheckRestParams.checkInteger(params, "taskId", "运送任务Id");
			if (null != errInfo) {
				//number类型参数验证
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (!AppUtils.match(MtConstant.MT_TASK_APPLY_STATUS_REGEX, params.getApplyStatus())) {
				return RestResponse.RestResponseBuilder.createFailBuilder("是否担任主责任人参数有误").buidler();
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			params.setOrganId(this.getOrganId());
			MtTaskApplyPersonLiableRsp rsp = getMtTaskManagerHandleServiceIcePrx().applyPersonLiable(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, String> map = new HashMap<>();
				map.put("id", rsp.getTaskId());
				map.put("exeEndUserId", rsp.getExeEndUserId());
				return RestResponse.RestResponseBuilder.createSuccessBuilder(rsp.message).setResult(map).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常，申请为主责任人失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常，申请为主责任人失败！").buidler();
		}
	}
	
	/**
	 * @discription 调度任务退单
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月24日 下午7:18:51     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "调度任务退单", response = String.class, notes = "{<br/>"
			+ "\"taskId\": \"运送任务Id\",<br/>"          
			+ "\"backTaskReason\": \"退单原因\",<br/>"          
			+ "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/backMtDispatchTask.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse backMtDispatchTask(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (user == null) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录，请重新登录!").buidler();
		}
		try {
			MtCommonIceParam params = FastjsonUtils.parseObject(paramJson, MtCommonIceParam.class);
			String errInfo = CheckRestParams.checkEmpty(params, "taskId", "运送任务Id");
			if (null != errInfo) {
				//非空验证
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			errInfo = CheckRestParams.checkInteger(params, "taskId", "运送任务Id");
			if (null != errInfo) {
				//number类型参数验证
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			params.setOrganId(this.getOrganId());
			RpcRespIce rsp = getMtTaskManagerHandleServiceIcePrx().backMtTask(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, String> map = new HashMap<>();
				map.put("id", rsp.getData());
				return RestResponse.RestResponseBuilder.createSuccessBuilder("退单成功").setResult(map).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常，调度任务退单失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常，调度任务退单失败！").buidler();
		}
	}
	
	/**
	 * @discription 调度任务开始
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月24日 下午7:24:39     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "调度任务开始", response = String.class, notes = "{<br/>"
			+ "\"taskId\": \"运送任务Id\",<br/>"          
			+ "\"organId\": \"项目(医院)Id\",<br/>"          
			+ "\"houseId\": \"科室Id\",<br/>"          
			+ "\"mac\": \"mac地址\",<br/>"          
			+ "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/startMtDispatchTask.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse startMtDispatchTask(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (user == null) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录，请重新登录!").buidler();
		}
		try {
			MtCommonIceParam params = FastjsonUtils.parseObject(paramJson, MtCommonIceParam.class);
			String errInfo = CheckRestParams.checkEmpty(params, "taskId, organId", "运送任务Id, 项目(医院)Id");
			if (null != errInfo) {
				//非空验证
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			errInfo = CheckRestParams.checkInteger(params, "taskId, organId", "运送任务Id, 项目(医院)Id");
			if (null != errInfo) {
				//number类型参数验证
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			params.setUserId(String.valueOf(user.getUserId()));
			RpcRespIce rsp = getMtTaskManagerHandleServiceIcePrx().startMtTask(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, String> map = new HashMap<>();
				map.put("id", rsp.getData());
				return RestResponse.RestResponseBuilder.createSuccessBuilder(rsp.message).setResult(map).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常，调度任务开始执行失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常，调度任务开始执行失败！").buidler();
		}
	}
	
	/**
	 * @discription 固定任务完成
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月24日 下午7:32:53     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "固定任务完成", response = String.class, notes = "{<br/>"
			+ "\"taskId\": \"运送任务Id\",<br/>"          
			+ "\"organId\": \"项目(医院)Id\",<br/>"          
			+ "\"houseId\": \"科室Id\",<br/>"          
			+ "\"mac\": \"mac地址\",<br/>"
			+ "\"finishFileIds\": \"完成图片\",<br/>" 
			+ "\"finishContent\": \"处理详情\",<br/>"
			+ "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/finishMtFixedTask.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse finishMtFixedTask(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (user == null) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录，请重新登录!").buidler();
		}
		try {
			MtCommonIceParam params = FastjsonUtils.parseObject(paramJson, MtCommonIceParam.class);
			String errInfo = CheckRestParams.checkEmpty(params, "taskId, organId", "运送任务Id, 项目(医院)Id");
			if (null != errInfo) {
				//非空验证
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			errInfo = CheckRestParams.checkInteger(params, "taskId, organId", "运送任务Id, 项目(医院)Id");
			if (null != errInfo) {
				//number类型参数验证
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			params.setUserId(String.valueOf(user.getUserId()));
			RpcRespIce rsp = getMtTaskManagerHandleServiceIcePrx().finishFixedTask(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, String> map = new HashMap<>();
				map.put("id", rsp.getData());
				return RestResponse.RestResponseBuilder.createSuccessBuilder(rsp.message).setResult(map).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常，固定任务完成失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常，固定任务完成失败！").buidler();
		}
	}
	
	/**
	 * @discription 删除自主任务
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月24日 下午7:38:58     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "删除自主任务", response = String.class, notes = "{<br/>"
			+ "\"taskId\": \"运送任务Id\",<br/>"          
			+ "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/deleteAutonomousTask.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse deleteAutonomousTask(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (user == null) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录，请重新登录!").buidler();
		}
		try {
			MtCommonIceParam params = FastjsonUtils.parseObject(paramJson, MtCommonIceParam.class);
			String errInfo = CheckRestParams.checkEmpty(params, "taskId", "运送任务Id");
			if (null != errInfo) {
				//非空验证
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			errInfo = CheckRestParams.checkInteger(params, "taskId", "运送任务Id");
			if (null != errInfo) {
				//number类型参数验证
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			// 设置操作人
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			params.setOrganId(this.getOrganId());
			RpcRespIce rsp = getMtTaskManagerHandleServiceIcePrx().cancelMtTask(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				return RestResponse.RestResponseBuilder.createSuccessBuilder(rsp.message).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常，删除自主任务失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常，删除自主任务失败！").buidler();
		}
	}
}
