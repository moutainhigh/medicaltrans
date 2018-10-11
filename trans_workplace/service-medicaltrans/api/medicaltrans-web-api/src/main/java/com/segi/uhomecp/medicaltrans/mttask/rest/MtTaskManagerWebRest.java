package com.segi.uhomecp.medicaltrans.mttask.rest;

import java.util.Date;
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
import segi.medicaltrans.mttask.manager.MtTaskManagerCreateServiceIcePrx;
import segi.medicaltrans.mttask.manager.MtTaskManagerHandleServiceIcePrx;
import segi.medicaltrans.mttask.manager.MtTaskReq;
import segi.medicaltrans.mttask.manager.SendTaskIceParam;
import IceExt.IceClientUtil;

import com.segi.uhomecp.common.constant.RestApiConstant;
import com.segi.uhomecp.common.rest.AbsActionRest;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.enums.TransDispatchTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTaskTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTypeEnum;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.sso.auth.vo.User;
import com.segi.uhomecp.sso.client.annotation.AdminUserParam;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.web.rest.RestResponse;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.enums.DataSourceEnum;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.CheckRestParams;
import com.segi.uhomecp.wh.common.utils.DateUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(RestApiConstant.API_PATH + "/medicaltrans/mtTaskManagerWebRest")
@Api(value = "medicaltrans/mtTaskManagerWebRest", description = "医院运送管理(web端)")
public class MtTaskManagerWebRest extends AbsActionRest {
	private static final Logger logger = LoggerFactory.getLogger(MtTaskManagerWebRest.class);
	private static final String PREFIXPROJECTNAME = RestApiConstant.PREFIXPROJECTNAME;
	
	/**
	 * @discription 获取ice服务
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月25日 上午10:43:17     
	 * @return
	 */
	private MtTaskManagerCreateServiceIcePrx getMtTaskManagerCreateServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(MtTaskManagerCreateServiceIcePrx.class);
	}
	
	private MtTaskManagerHandleServiceIcePrx getMtTaskManagerHandleServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(MtTaskManagerHandleServiceIcePrx.class);
	}
	
	/**
	 * @discription 调度任务新建(web端)
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月25日 上午10:42:43     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "运送任务发起", response = String.class, notes = "{<br/>" + 
			"\"organId\":\"项目(医院)Id\",<br/>" +
			"\"transTypeParentCode\":\"运送大类\",<br/>" +
			"\"transTypeId\":\"运送类型Id\",<br/>" +
			"\"urgency\":\"紧急程度,1:一般;2:紧急;3:特急,选全部就不传\",<br/>" +
			"\"sourceHouseId\":\"任务来源地\",<br/>" +
			"\"fromHouseId\":\"出发地\",<br/>" +
			"\"toHouseId\":\"目的地\",<br/>" +
			"\"transTools\":\"运送工具,1步行;2推床;3平车;4轮椅\",<br/>" +
			"\"transPersonCount\":\"运送人数\",<br/>" +
			"\"isReservedFlag\":\"是否预约: 0:没预约;1:预约了\",<br/>" +
			"\"beginTime\":\"预约时间 YYYY-MM-DD HH：mm\",<br/>" +
			"\"limitMinute\":\"时限,范围5-999，单位分钟\",<br/>" +
			"\"taskContent\":\"运送描述\",<br/>" +
			"\"patientName\":\"患者姓名,运送大类为病人护送时必填\",<br/>" +
			"\"bedNo\":\"床号\",<br/>" +
			"\"patientGender\":\"患者性别\",<br/>" +
			"\"medicalRecNo\":\"病历号\",<br/>" +
			"\"resType\":\"响应类型:1:指定;2:抢单\",<br/>" +
			"\"exeUserIds\":\"运送员,响应类型为指定时必填,多个以逗号分隔\",<br/>" +
			"\"exeEndUserId\":\"责任人,响应类型为指定时必填\",<br/>" +
			"\"serviceGroupIds\":\"项目服务处,响应类型为抢单时必填,多个以逗号分隔\",<br/>" +
			"\"sendContent\":\"派单详情\",<br/>" +
			"\"fileIds\":\"附件 refType:MT_TASK_FILE\"<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/saveMtTask.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse saveMtTask(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtTaskReq params = FastjsonUtils.parseObject(paramJson, MtTaskReq.class);
			//必填参数验证
			String fields = "organId, transTypeParentCode, transTypeId, urgency, sourceHouseId, fromHouseId, toHouseId, transTools,"
					+ "transPersonCount, isReservedFlag, resType, limitMinute";
			String messages = "项目(医院)Id, 运送大类, 运送类型, 紧急程度, 运送单来源, 出发地, 目的地, 运送工具, 运送人数, 是否预约, 响应类型, 时限";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//数字类型参数验证
			fields = "organId, transTypeId, sourceHouseId, fromHouseId, toHouseId, transPersonCount, limitMinute";
			messages = "项目(医院)Id, 运送类型, 运送单来源, 出发地, 目的地, 运送人数, 时限";
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//正则表达式验证
			if (!AppUtils.match(MtConstant.MT_TASK_URGENCY_REGEX, params.getUrgency())) {
				//验证任务紧急程度
				return RestResponse.RestResponseBuilder.createFailBuilder("紧急程度输入有误!").buidler();
			} 
			if (!AppUtils.match(MtConstant.MT_TASK_TRANSTOOLS_REGEX, params.getTransTools())) {
				//验证运送工具
				return RestResponse.RestResponseBuilder.createFailBuilder("运送工具输入有误!").buidler();
			} 
			if(TransTypeEnum.TRANS_TYPE_04.getCode().equals(params.getTransTypeParentCode())) {
				// 运送大类是病人看护，需要对下面的字段进行效验必填性
				fields = "patientName, bedNo, patientGender";
				messages = "患者姓名, 床号, 患者性别";
				errInfo = CheckRestParams.checkEmpty(params, fields, messages);
				if (null != errInfo) {
					return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
				}
			}
			if (!MtConstant.IS_RESERVED_FLAG.equals(params.getIsReservedFlag()) && 
					StringUtils.isBlank(params.getBeginTime())) {
				//预约了
				return RestResponse.RestResponseBuilder.createFailBuilder("必须填写预约时间").buidler();
			}
			if (StringUtils.isNotBlank(params.getBeginTime())) {
				errInfo = CheckRestParams.checkDateByRegex(params, "beginTime", "预约时间", Constant.DATA_FORMATE_YYYY_MM_DD_HH_MM_REG);
				if (null != errInfo) {
					return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
				}
				params.setBeginTime(params.getBeginTime().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", ""));
				if (DateUtil.formatDateToString(DateUtil.addDateDay(new Date(), 7), 
						DateUtil.FORMAT_YYYY_MM_DD_HH_MM).compareTo(params.getBeginTime()) < 0) {
					// 当前时间+7天小于预约时间
					return RestResponse.RestResponseBuilder.createFailBuilder("预约时间不可超过当前时间7天!").buidler();
				}
			}
			if (Integer.valueOf(params.getLimitMinute()).intValue() < 5 
					|| Integer.valueOf(params.getLimitMinute()).intValue() > 999) {
				return RestResponse.RestResponseBuilder.createFailBuilder("时限不在限定范围[5-999]分钟内!").buidler();
			}
			if (StringUtils.isNotBlank(params.getPatientGender()) 
					&& !AppUtils.match(MtConstant.MT_TASK_SEX_REGEX, params.getPatientGender())) {
				//验证患者性别
				return RestResponse.RestResponseBuilder.createFailBuilder("患者性别输入有误!").buidler();
			}
			if (!AppUtils.match(MtConstant.MT_TASK_RES_TYPE_REGEX, params.getResType())) {
				return RestResponse.RestResponseBuilder.createFailBuilder("响应类型输入有误").buidler();
			}
			if (TransDispatchTypeEnum.TRANS_DISPATCH_TYPE_01.getCode().equals(params.getResType()) 
					&& (StringUtils.isBlank(params.getExeUserIds()) 
					|| StringUtils.isBlank(params.getExeEndUserId()))) {
				// 指定
				return RestResponse.RestResponseBuilder.createFailBuilder("需要选择任务执行人,并指定责任人!").buidler();
			}
			if (TransDispatchTypeEnum.TRANS_DISPATCH_TYPE_02.getCode().equals(params.getResType()) 
					&& StringUtils.isBlank(params.getServiceGroupIds())) {
				// 抢单
				return RestResponse.RestResponseBuilder.createFailBuilder("需要选择项目服务处!").buidler();
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			params.setTaskType(TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode());
			params.setDataSource(DataSourceEnum.DATA_SOURCE_WEB.getCode());
			RpcRespIce rsp = getMtTaskManagerCreateServiceIcePrx().createTask(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, String> map = new HashMap<>();
				map.put("id", rsp.getData());
				return RestResponse.RestResponseBuilder.createSuccessBuilder(rsp.message).setResult(map).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,运送任务发起失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,运送任务发起失败").buidler();
		}
	}
	
	/**
	 * @discription 运送任务取消
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月25日 上午10:54:49     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "运送任务取消", response = String.class, notes = "{<br/>" + 
			"\"taskId\":\"运送任务Id\",<br/>" +
			"\"organId\":\"组织(医院)Id\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/cancelMtTask.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse cancelMtTask(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtCommonIceParam params = FastjsonUtils.parseObject(paramJson, MtCommonIceParam.class);
			//必填参数验证
			String errInfo = CheckRestParams.checkEmpty(params, "taskId, organId", "运送任务Id, 组织(医院)Id");
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//数字类型参数验证
			errInfo = CheckRestParams.checkInteger(params, "taskId, organId", "运送任务Id, 组织(医院)Id");
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			RpcRespIce rsp = getMtTaskManagerHandleServiceIcePrx().cancelMtTask(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, String> map = new HashMap<>();
				map.put("id", rsp.getData());
				return RestResponse.RestResponseBuilder.createSuccessBuilder(rsp.message).setResult(map).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,运送任务取消失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,运送任务取消失败").buidler();
		}
	}
	
	/**
	 * @discription 调度任务派单
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月25日 上午11:10:28     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "调度任务派单", response = String.class, notes = "{<br/>" + 
			"\"taskId\":\"运送任务Id\",<br/>" +
			"\"organId\":\"组织(医院)Id\",<br/>" +
			"\"resType\":\"响应类型 1:指定;2:抢单\",<br/>" +
			"\"exeUserIds\":\"运送员,响应类型为指定时必填,多个以,分隔\",<br/>" +
			"\"exeEndUserId\":\"责任人,响应类型为指定时必填\",<br/>" +
			"\"serviceGroupIds\":\"项目服务处,响应类型为抢单时必填,多个以,分隔\",<br/>" +
			"\"sendContent\":\"派单详情\",<br/>" +
			"\"limitMinute\":\"时限\",<br/>" +
			"\"transPersonCount\":\"运送人数\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/dispatchTask.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse dispatchTask(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			SendTaskIceParam params = FastjsonUtils.parseObject(paramJson, SendTaskIceParam.class);
			//必填参数验证
			String fields = "taskId, resType, organId, limitMinute, transPersonCount";
			String messages = "运送任务Id, 响应类型, 组织(医院)Id, 时限, 运送人数";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//数字类型参数验证
			errInfo = CheckRestParams.checkInteger(params, "taskId, organId, limitMinute, transPersonCount", 
					"运送任务Id, 组织(医院)Id, 时限, 运送人数");
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (!AppUtils.match(MtConstant.MT_TASK_RES_TYPE_REGEX, params.getResType())) {
				return RestResponse.RestResponseBuilder.createFailBuilder("响应类型输入有误").buidler();
			}
			if (TransDispatchTypeEnum.TRANS_DISPATCH_TYPE_01.getCode().equals(params.getResType()) 
					&& (StringUtils.isBlank(params.getExeUserIds()) 
					|| StringUtils.isBlank(params.getExeEndUserId()))) {
				// 指定
				return RestResponse.RestResponseBuilder.createFailBuilder("需要选择任务执行人,并指定责任人!").buidler();
			}
			if (TransDispatchTypeEnum.TRANS_DISPATCH_TYPE_02.getCode().equals(params.getResType()) 
					&& StringUtils.isBlank(params.getServiceGroupIds())) {
				// 抢单
				return RestResponse.RestResponseBuilder.createFailBuilder("需要选择项目服务处!").buidler();
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			RpcRespIce rsp = getMtTaskManagerCreateServiceIcePrx().dispatchTask(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, String> map = new HashMap<>();
				map.put("id", rsp.getData());
				return RestResponse.RestResponseBuilder.createSuccessBuilder(rsp.message).setResult(map).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,调度任务派单失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,调度任务派单失败").buidler();
		}
	}
	
	/**
	 * @discription 调度任务重新派单
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月25日 上午11:13:40     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "调度任务重新派单", response = String.class, notes = "{<br/>" + 
			"\"taskId\":\"运送任务Id\",<br/>" +
			"\"organId\":\"组织(医院)Id\",<br/>" +
			"\"resType\":\"响应类型 1:指定;2:抢单\",<br/>" +
			"\"exeUserIds\":\"运送员,响应类型为指定时必填,多个以,分隔\",<br/>" +
			"\"exeEndUserId\":\"责任人,响应类型为指定时必填\",<br/>" +
			"\"serviceGroupIds\":\"项目服务处,响应类型为抢单时必填,多个以,分隔\",<br/>" +
			"\"sendContent\":\"派单详情\",<br/>" +
			"\"limitMinute\":\"时限\",<br/>" +
			"\"transPersonCount\":\"运送人数\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/againDispatchTask.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse againDispatchTask(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			SendTaskIceParam params = FastjsonUtils.parseObject(paramJson, SendTaskIceParam.class);
			//必填参数验证
			String fields = "taskId, resType, organId, limitMinute, transPersonCount";
			String messages = "运送任务Id, 响应类型, 组织(医院)Id, 时限, 运送人数";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//数字类型参数验证
			errInfo = CheckRestParams.checkInteger(params, "taskId, organId, limitMinute, transPersonCount", 
					"运送任务Id, 组织(医院)Id, 时限, 运送人数");
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (!AppUtils.match(MtConstant.MT_TASK_RES_TYPE_REGEX, params.getResType())) {
				return RestResponse.RestResponseBuilder.createFailBuilder("响应类型输入有误").buidler();
			}
			if (TransDispatchTypeEnum.TRANS_DISPATCH_TYPE_01.getCode().equals(params.getResType()) 
					&& (StringUtils.isBlank(params.getExeUserIds()) 
					|| StringUtils.isBlank(params.getExeEndUserId()))) {
				// 指定
				return RestResponse.RestResponseBuilder.createFailBuilder("需要选择任务执行人,并指定责任人!").buidler();
			}
			if (TransDispatchTypeEnum.TRANS_DISPATCH_TYPE_02.getCode().equals(params.getResType()) 
					&& StringUtils.isBlank(params.getServiceGroupIds())) {
				// 抢单
				return RestResponse.RestResponseBuilder.createFailBuilder("需要选择项目服务处!").buidler();
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			RpcRespIce rsp = getMtTaskManagerCreateServiceIcePrx().againDispatchTask(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, String> map = new HashMap<>();
				map.put("id", rsp.getData());
				return RestResponse.RestResponseBuilder.createSuccessBuilder(rsp.message).setResult(map).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,调度任务重新派单失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,调度任务重新派单失败").buidler();
		}
	}
	
	/**
	 * @discription 固定任务重新派单
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月25日 上午11:20:36     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "固定任务重新派单", response = String.class, notes = "{<br/>" + 
			"\"taskId\":\"运送任务Id\",<br/>" +
			"\"organId\":\"组织(医院)Id\",<br/>" +
			"\"exeUserId\":\"运送员Id\",<br/>" +
			"\"exeEndUserId\":\"运送员Id\",<br/>" +
			"\"sendContent\":\"派单详情\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/againFixedTask.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse againFixedTask(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			SendTaskIceParam params = FastjsonUtils.parseObject(paramJson, SendTaskIceParam.class);
			//必填参数验证
			String fields = "taskId, exeUserId, organId";
			String messages = "运送任务Id, 运送员Id, 组织(医院)Id";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//数字类型参数验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			RpcRespIce rsp = getMtTaskManagerCreateServiceIcePrx().againFixedTask(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, String> map = new HashMap<>();
				map.put("id", rsp.getData());
				return RestResponse.RestResponseBuilder.createSuccessBuilder(rsp.message).setResult(map).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,固定任务重新派单失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,固定任务重新派单失败").buidler();
		}
	}
	
	/**
	 * @Title: startMtDispatchTask 
	 * @Description: 调度任务开始 
	 * @author liuyi@segimail.com 
	 * @date 2018年9月10日下午6:11:05
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "调度任务开始", response = String.class, notes = "{<br/>"
			+ "\"taskId\": \"运送任务Id\",<br/>"          
			+ "\"organId\": \"项目(医院)Id\",<br/>"          
			+ "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/startTaskForWeb.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse startTaskForWeb(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
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
			RpcRespIce rsp = getMtTaskManagerHandleServiceIcePrx().startTaskForWeb(params);
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
	 * @Title: finishTaskForWeb 
	 * @Description: web运送任务完成 
	 * @author liuyi@segimail.com 
	 * @date 2018年9月11日上午10:37:58
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "运送任务完成", response = String.class, notes = "{<br/>" + 
			"\"taskId\":\"运送任务Id\",<br/>" +
			"\"organId\":\"项目Id\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/finishTaskForWeb.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse finishTaskForWeb(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtCommonIceParam params = FastjsonUtils.parseObject(paramJson, MtCommonIceParam.class);
			String fields = "organId";
			String messages = "项目Id";
			//必填参数验证
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			//获取ICE服务
			RpcRespIce rsp = this.getMtTaskManagerHandleServiceIcePrx().finishTaskForWeb(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, String> dataMap = new HashMap<>();
				dataMap.put("id", rsp.getData());
				return RestResponse.RestResponseBuilder.createSuccessBuilder(rsp.message).setResult(dataMap).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.getMessage()).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,运送任务执行失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,运送任务执行失败").buidler();
		}
	} 
}
