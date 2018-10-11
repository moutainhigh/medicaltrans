package com.segi.uhomecp.medicaltrans.mttask.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import resp.RpcRespIce;
import segi.medicaltrans.base.userhosp.UserHospIce;
import segi.medicaltrans.base.userhosp.UserHospRelReturnPadIce;
import segi.medicaltrans.base.userhosp.UserHospServiceIcePrx;
import segi.medicaltrans.mttask.manager.MtCommonIceParam;
import segi.medicaltrans.mttask.manager.MtTaskManagerCreateServiceIcePrx;
import segi.medicaltrans.mttask.manager.MtTaskManagerHandleServiceIcePrx;
import segi.medicaltrans.mttask.manager.MtTaskReq;
import segi.medicaltrans.mttask.query.MtPadCommonPageIce;
import segi.medicaltrans.mttask.query.MtTaskDetailIceParam;
import segi.medicaltrans.mttask.query.MtTaskDetailRetIce;
import segi.medicaltrans.mttask.query.MtTaskDetailRetIceRsp;
import segi.medicaltrans.mttask.query.MtTaskPageIceParam;
import segi.medicaltrans.mttask.query.MtTaskQueryServiceIcePrx;
import segi.medicaltrans.mttask.query.TaskAndEvaluatePaginatorIceRsp;
import IceExt.IceClientUtil;

import com.segi.uhomecp.common.constant.RestApiConstant;
import com.segi.uhomecp.common.rest.AbsActionRest;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.enums.InvokingFlagEnum;
import com.segi.uhomecp.medicaltrans.enums.TransStatusEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTaskTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTypeEnum;
import com.segi.uhomecp.medicaltrans.utils.ResponseDownloadUtils;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.sso.auth.vo.User;
import com.segi.uhomecp.sso.client.annotation.AdminUserParam;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.web.rest.RestResponse;
import com.segi.uhomecp.wh.common.constant.AppConCurrentTypeConstant;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.enums.DataSourceEnum;
import com.segi.uhomecp.wh.common.utils.AppConCurrentUtils;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.CheckRestParams;
import com.segi.uhomecp.wh.common.utils.DateUtil;
import com.segi.uhomecp.wh.common.utils.ExcelWriter;
import com.segi.uhomecp.wh.common.utils.NumberUtils;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * Title: MtTaskDispatchUserWebRest.java    
 * @Description: 和pad端一样流程的接口
 * @author zhangyang@segimail.com       
 * @created 2018年9月10日 上午10:34:17
 */
@Controller
@RequestMapping(RestApiConstant.API_PATH + "/medicaltrans/mtTaskDispatchUserWebRest")
@Api(value = "medicaltrans/mtTaskDispatchUserWebRest", description = "医院运送管理web端流程")
public class MtTaskDispatchUserWebRest extends AbsActionRest {
	private static final Logger logger = LoggerFactory.getLogger(MtTaskDispatchUserWebRest.class);
	private static final String PREFIXPROJECTNAME = RestApiConstant.PREFIXPROJECTNAME;
	
	/**
	 * @discription 获取ice服务
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月10日 下午2:18:43     
	 * @return
	 */
	private MtTaskManagerCreateServiceIcePrx getMtTaskManagerCreateServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(MtTaskManagerCreateServiceIcePrx.class);
	}
	
	private MtTaskManagerHandleServiceIcePrx getMtTaskManagerHandleServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(MtTaskManagerHandleServiceIcePrx.class);
	}
	
	private MtTaskQueryServiceIcePrx getMtTaskQueryServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(MtTaskQueryServiceIcePrx.class);
	}
	
	private UserHospServiceIcePrx getUserHospServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(UserHospServiceIcePrx.class);
	}
	
	/**
	 * @discription 运送任务发起
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月10日 下午2:19:04     
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
			"\"fileIds\":\"附件 \",<br/>" +
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
			String fields = "organId, transTypeParentCode, transTypeId, urgency, fromHouseId, "
					+ "toHouseId, transTools, transPersonCount, isReservedFlag, limitMinute, sourceHouseId";
			String messages = "项目(医院)Id, 运送大类, 运送类型, 紧急程度, 出发地, 目的地, 运送工具, "
					+ "运送人数, 是否预约, 时限, 任务来源地";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//数字类型参数验证
			fields = "transTypeId, fromHouseId, toHouseId, transPersonCount, limitMinute, organId, sourceHouseId";
			messages = "运送类型, 出发地, 目的地, 运送人数, 时限, 项目(医院)Id, 任务来源地";
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
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			params.setTaskType(TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode());
			params.setDataSource(DataSourceEnum.DATA_SOURCE_WEB.getCode());
			params.setFlag(true);
			RpcRespIce rsp = getMtTaskManagerCreateServiceIcePrx().createTask(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, String> map = new HashMap<>();
				map.put("id", rsp.getData());
				return RestResponse.RestResponseBuilder.createSuccessBuilder("任务创建成功").setResult(map).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,运送任务发起失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,运送任务发起失败").buidler();
		}
	}
	
	/**
	 * @discription 运送任务编辑
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月10日 下午2:19:19     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "运送任务编辑", response = String.class, notes = "{<br/>" + 
			"\"taskId\":\"运送任务Id\",<br/>" +
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
			"\"fileIds\":\"附件 \",<br/>" +
			"\"delFileIds\":\"删除附件 \",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/updateMtTask.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse updateMtTask(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtTaskReq params = FastjsonUtils.parseObject(paramJson, MtTaskReq.class);
			//必填参数验证
			String fields = "taskId, transTypeParentCode, transTypeId, urgency, fromHouseId, toHouseId, transTools,"
					+ "transPersonCount, isReservedFlag, organId, sourceHouseId, limitMinute";
			String messages = "运送任务Id, 运送大类, 运送类型, 紧急程度, 出发地, 目的地, 运送工具, "
					+ "运送人数, 是否预约, 项目(医院)Id, 任务来源地, 时限";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//数字类型参数验证
			fields = "taskId, transTypeId, fromHouseId, toHouseId, transPersonCount, organId, sourceHouseId, limitMinute";
			messages = "运送任务Id, 运送类型, 出发地, 目的地, 运送人数, 项目(医院)Id, 任务来源地, 时限";
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
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			params.setTaskType(TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode());
			params.setDataSource(DataSourceEnum.DATA_SOURCE_WEB.getCode());
			RpcRespIce rsp = getMtTaskManagerCreateServiceIcePrx().editTask(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, String> map = new HashMap<>();
				map.put("id", rsp.getData());
				return RestResponse.RestResponseBuilder.createSuccessBuilder("任务编辑成功").setResult(map).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,运送任务编辑失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,运送任务编辑失败").buidler();
		}
	}
	
	/**
	 * @discription 运送任务取消
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月10日 下午2:19:31     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "运送任务取消", response = String.class, notes = "{<br/>" + 
			"\"taskId\":\"运送任务Id\",<br/>" +
			"\"organId\":\"项目(医院)Id\",<br/>" +
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
			String errInfo = CheckRestParams.checkEmpty(params, "taskId, organId", "运送任务Id, 项目(医院)Id");
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//数字类型参数验证
			errInfo = CheckRestParams.checkInteger(params, "taskId, organId", "运送任务Id, 项目(医院)Id");
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			RpcRespIce rsp = getMtTaskManagerHandleServiceIcePrx().cancelMtTask(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, String> map = new HashMap<>();
				map.put("id", rsp.getData());
				return RestResponse.RestResponseBuilder.createSuccessBuilder("任务取消成功").setResult(map).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,运送任务取消失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,运送任务取消失败").buidler();
		}
	}
	
	/**
	 * @discription 运送评价
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月10日 下午2:19:45     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "运送评价", response = String.class, notes = "{<br/>" + 
			"\"taskId\":\"运送任务Id\",<br/>" +
			"\"organId\":\"项目(医院)Id\",<br/>" +
			"\"evaluate\":\"评价结论,取值1-5,表示1-5颗星\",<br/>" +
			"\"evaluateContent\":\"评价描述,3星以下必填,3星及以上非必填\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/evaluate.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse evaluate(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtCommonIceParam params = FastjsonUtils.parseObject(paramJson, MtCommonIceParam.class);
			//必填参数验证
			String errInfo = CheckRestParams.checkEmpty(params, "taskId, evaluate, organId", 
					"运送任务Id, 评价结论, 项目(医院)Id");
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//数字类型参数验证
			errInfo = CheckRestParams.checkInteger(params, "taskId, organId", "运送任务Id, 项目(医院)Id");
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (!AppUtils.match(MtConstant.MT_TASK_EVALUATE_REGEX, params.getEvaluate())) {
				//验证评价值
				return RestResponse.RestResponseBuilder.createFailBuilder("评价结论输入有误!").buidler();
			} 
			if (Integer.valueOf(params.getEvaluate()).intValue() <3 
					&& StringUtils.isBlank(params.getEvaluateContent())) {
				//评价结论小于3,评价描述不能为空
				return RestResponse.RestResponseBuilder.createFailBuilder("小于3星必须填写评价描述!").buidler();
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			RpcRespIce rsp = getMtTaskManagerHandleServiceIcePrx().evaluate(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, String> map = new HashMap<>();
				map.put("id", rsp.getData());
				return RestResponse.RestResponseBuilder.createSuccessBuilder("评价成功").setResult(map).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,运送评价失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,运送评价失败").buidler();
		}
	}
	
	/**
	 * @discription 分页查询当前用户发起的运送任务
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月10日 下午2:19:59     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "分页查询当前用户发起的运送任务", response = String.class, notes = "{<br/>" + 
			"\"organId\":\"项目(医院)Id\",<br/>" +
			"\"transTypeParentCode\":\"运送大类\",<br/>" +
			"\"urgency\":\"紧急程度\",<br/>" +
			"\"status\":\"状态\",<br/>" +
			"\"beginTime\":\"运送开始时间 YYYY-MM-DD HH:mm\",<br/>" +
			"\"endTime\":\"运送结束时间 YYYY-MM-DD HH：mm\",<br/>" +
			"\"pageNo\":\"页码\",<br/>" +
			"\"pageLength\":\"每页记录数\"<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryMtTaskPage.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryMtTaskPage(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtTaskPageIceParam params = FastjsonUtils.parseObject(paramJson, MtTaskPageIceParam.class);
			//必填参数验证
			String fields = "organId, beginTime, endTime, pageNo, pageLength";
			String messages = "项目(医院)Id, 运送开始时间, 运送结束时间, 页码, 每页记录数";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			fields = "pageNo, pageLength, organId";
			messages = "页码, 每页记录数, 项目(医院)Id";
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (StringUtils.isNotEmpty(params.getBeginTime()) && StringUtils.isNotEmpty(params.getEndTime())) {
				fields = "beginTime, endTime";
				messages = "运送开始时间, 运送结束时间";
				errInfo = CheckRestParams.checkDateByRegex(params, fields, messages, Constant.DATA_FORMATE_YYYY_MM_DD_HH_MM_REG);
				if (null != errInfo) {
					return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
				}
				params.setBeginTime(params.getBeginTime().replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "") + "00");
				params.setEndTime(params.getEndTime().replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "") + "59");
				if (Long.valueOf(params.getBeginTime()).longValue() > Long.valueOf(params.getEndTime()).longValue()) {
					return RestResponse.RestResponseBuilder.createFailBuilder("开始时间必须小于或等于结束时间").buidler();
				}
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			params.setInvokingFlag(InvokingFlagEnum.INVOKING_FROM_WEB.getCode());
			//获取ICE服务
			TaskAndEvaluatePaginatorIceRsp rsp = this.getMtTaskQueryServiceIcePrx().queryMtTaskPageForPad(params);
			return RestResponse.RestResponseBuilder.createBuilder(rsp.code, rsp.message)
					.setResult(rsp.paginator, rsp.resultList.toArray()).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,查询当前用户发起的运送任务失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,查询当前用户发起的运送任务失败").buidler();
		}
	} 
	
	/**
	 * @discription 评价信息分页查询
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月10日 下午2:20:11     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "评价信息分页查询", response = String.class, notes = "{<br/>" + 
			"\"organId\":\"项目(医院)Id\",<br/>" +
			"\"userHouseId\":\"用户所在科室Id\",<br/>" +
			"\"evaluateStatus\":\"评价状态,0:未评价;1:已评价,选全部就不传\",<br/>" +
			"\"beginTime\":\"运送开始时间 YYYY-MM-DD\",<br/>" +
			"\"endTime\":\"运送结束时间 YYYY-MM-DD\",<br/>" +
			"\"pageNo\":\"页码\",<br/>" +
			"\"pageLength\":\"每页记录数\"<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryMtTaskEvaluatePage.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryMtTaskEvaluatePage(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtTaskPageIceParam params = FastjsonUtils.parseObject(paramJson, MtTaskPageIceParam.class);
			//必填参数验证
			String fields = "beginTime, endTime, pageNo, pageLength, organId, userHouseId";
			String messages = "运送开始时间, 运送结束时间, 页码, 每页记录数, 项目(医院)Id, 用户所在科室Id";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			fields = "pageNo, pageLength, organId, userHouseId";
			messages = "页码, 每页记录数, 项目(医院)Id, 用户所在科室Id";
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (StringUtils.isNotEmpty(params.getBeginTime()) && StringUtils.isNotEmpty(params.getEndTime())) {
				fields = "beginTime, endTime";
				messages = "运送开始时间, 运送结束时间";
				errInfo = CheckRestParams.checkDateByRegex(params, fields, messages, Constant.DATA_FORMATE_YYYY_MM_DD_REG);
				if (null != errInfo) {
					return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
				}
				params.setBeginTime(params.getBeginTime().replaceAll("-", "") + "0000");
				params.setEndTime(params.getEndTime().replaceAll("-", "") + "2359");
				if (Long.valueOf(params.getBeginTime()).longValue() > Long.valueOf(params.getEndTime()).longValue()) {
					return RestResponse.RestResponseBuilder.createFailBuilder("开始时间必须小于或等于结束时间").buidler();
				}
			}
			params.setUserOrganId(user.getOrganId());
			params.setUserId(String.valueOf(user.getUserId()));
			//获取ICE服务
			TaskAndEvaluatePaginatorIceRsp rsp = this.getMtTaskQueryServiceIcePrx().queryEvaluatePageForPad(params);
			return RestResponse.RestResponseBuilder.createBuilder(rsp.code, rsp.message)
					.setResult(rsp.paginator, rsp.resultList.toArray()).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,评价信息分页查询失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,评价信息分页查询失败").buidler();
		}
	} 
	
	/**
	 * @discription 运送任务详情和评价详情公用接口
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月10日 下午2:20:26     
	 * @param user
	 * @param taskId
	 * @param organId
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "运送任务详情和评价详情公用接口", response = String.class, notes = "")
	@ApiParam(name="taskId", value="运送任务Id") 
	@ApiImplicitParams({})
	@RequestMapping(value="/queryTaskOrEvaluateDetail.json", method = {RequestMethod.GET})
	@ResponseBody
	public RestResponse queryTaskOrEvaluateDetail(@AdminUserParam User user, 
			@RequestParam(value="taskId", required=true) String taskId,
			@RequestParam(value="organId", required=true) String organId) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			if (StringUtils.isBlank(taskId) || StringUtils.isBlank(organId)) {
				//接口必填参数判断
				return RestResponse.RestResponseBuilder.createFailBuilder("必填参数缺失!").buidler();
			}
			if (!NumberUtils.isDigits(taskId)) {
				//判断运送任务Id是否是number类型的
				return RestResponse.RestResponseBuilder.createFailBuilder("运送任务主键格式错误！").buidler();
			}
			if (!NumberUtils.isDigits(organId)) {
				//判断项目(医院)Id是否是number类型的
				return RestResponse.RestResponseBuilder.createFailBuilder("项目(医院)Id格式错误！").buidler();
			}
			MtTaskDetailIceParam params = new MtTaskDetailIceParam();
			params.setTaskId(taskId);
			params.setUserOrganId(user.getOrganId());
			params.setOrganId(organId);
			MtTaskDetailRetIceRsp rsp = getMtTaskQueryServiceIcePrx().queryMtTaskDetail(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.code)) {
				MtTaskDetailRetIce detail = rsp.getMtTaskDetailRetIce();
				if (TransStatusEnum.TRANS_BACK.getCode().equals(detail.getStatus())
						|| TransStatusEnum.TRANS_ROBBING.getCode().equals(detail.getStatus())) {
					// 退单/抢单中状态在pad端展示为未开始
					detail.setStatus(TransStatusEnum.TRANS_NOT_START.getCode());
					detail.setStatusName(TransStatusEnum.TRANS_NOT_START.getName());
				}
                return RestResponse.RestResponseBuilder.createSuccessBuilder("查看运送任务详情成功!")
                		.setResult(detail).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,查看运送任务详情失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,查看运送任务详情失败！").buidler();
		}
	}
	
	/**
	 * @Title: exportMtTask 
	 * @Description: 运送发起导出 
	 * @author liuyi@segimail.com 
	 * @date 2018年9月12日下午5:57:17
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "运送发起导出", response = String.class, notes = "运送发起导出")
	@ApiImplicitParams({})
	@RequestMapping(value = "/exportMtTask.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse exportMtTask(@AdminUserParam User user, HttpServletResponse response,
            @RequestParam(value = "organId", required = true) String organId,
            @RequestParam(value = "transTypeParentCode", required = false) String transTypeParentCode,
            @RequestParam(value = "urgency", required = false) String urgency,
            @RequestParam(value = "beginTime", required = true) String beginTime,
            @RequestParam(value = "endTime", required = true) String endTime,
            @RequestParam(value = "status", required = false) String status) {
		try {
			if (null == user) {
				return RestResponse.RestResponseBuilder.createFailBuilder(
						"用户未登录,请重新登录!").buidler();
			}
			//前端传进来的json转对象
			MtTaskPageIceParam params = new MtTaskPageIceParam();
			params.setOrganId(organId);
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			if (StringUtils.isNotEmpty(urgency) && 
					AppUtils.match(MtConstant.MT_TASK_URGENCY_REGEX, urgency)) {
				//验证任务紧急程度
				params.setUrgency(urgency);
			} 
			// 设置任务状态
			params.setStatus(status);
			if (StringUtils.isNotEmpty(transTypeParentCode)) {
				params.setTransTypeParentCode(transTypeParentCode);
			}
			if (StringUtils.isNotEmpty(beginTime) && StringUtils.isNotEmpty(endTime)) {
				String fields = "beginTime, endTime";
				String messages = "运送开始时间, 运送结束时间";
				params.setBeginTime(beginTime);
				params.setEndTime(endTime);
				String errInfo = CheckRestParams.checkDateByRegex(params, fields, messages, Constant.DATA_FORMATE_YYYY_MM_DD_HH_MM_REG);
				if (null != errInfo) {
					return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
				}
				params.setBeginTime(params.getBeginTime().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "") + "00");
				params.setEndTime(params.getEndTime().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "") + "59");
				if (Long.valueOf(params.getBeginTime()).longValue() > Long.valueOf(params.getEndTime()).longValue()) {
					return RestResponse.RestResponseBuilder.createFailBuilder("开始时间必须小于或等于结束时间").buidler();
				}
			}
			// 导出标识
            params.setExportFlag(true);
			// 每次查询2500条
			params.setPageLength(MtConstant.EXPORT_EVERY_PAGE_LENGTH);
			params.setPageNo(MtConstant.EXPORT_PAGE_NO);
			params.setInvokingFlag(InvokingFlagEnum.INVOKING_FROM_WEB.getCode());
			//获取ICE服务
			TaskAndEvaluatePaginatorIceRsp rsp = this.getMtTaskQueryServiceIcePrx().queryMtTaskPageForPad(params);
			// 获取需要导出的数据
			List<MtPadCommonPageIce> resultList = new ArrayList<>();
			if (rsp != null && rsp.getPaginator() != null) {
				resultList.addAll(rsp.getResultList());
			}
			String totalCount = rsp.getPaginator().getTotalCount();
			int totalCountInt = Integer.parseInt(totalCount);
			// 判断导出是否超过5000条
			if (Integer.parseInt(MtConstant.EXPORT_PAGE_LENGTH) < totalCountInt) {
				return RestResponse.RestResponseBuilder.createFailBuilder("导出超过5000条， 请缩小查询范围！").buidler();
			}
			if (Integer.parseInt(MtConstant.EXPORT_EVERY_PAGE_LENGTH) < totalCountInt) {
				// 总条数大于2500时再查询一次
				params.setPageNo(String.valueOf(Integer.parseInt(MtConstant.EXPORT_PAGE_NO) + 1));
				rsp = this.getMtTaskQueryServiceIcePrx().queryMtTaskPageForPad(params);
				if (AppUtils.isNotEmpty(rsp.getResultList())) {
					resultList.addAll(rsp.getResultList());
				}
			}
			return exportMtTask(response, resultList);
		} catch (Exception e) {
			logger.warn("系统异常,运送发起导出失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,运送发起导出失败！").buidler();
		}
	}
	
	private RestResponse exportMtTask(HttpServletResponse response, List<MtPadCommonPageIce> resultList){
		try {
			// 下载并发次数新增
			boolean exeFlag = AppConCurrentUtils.getInstace().addConCurCount(
					AppConCurrentTypeConstant.MAX_CURRENCY_EXCEL_EXP);
			if (!exeFlag) {
				return RestResponse.RestResponseBuilder.createFailBuilder(
						"当前数据导出操作的人数过多，请稍后再试！").buidler();
			}
			String title = "运送发起导出.xls";
			String[] columnTitles = {"所属项目", "下单时间", "下单人", "任务来源", "运送类型", "紧急程度", "出发地", "目的地", "运送员", "状态"};
			File file = null;
			String[] data = null;
	            List<String[]> dataList = new ArrayList<String[]>();
	            if (AppUtils.isNotEmpty(resultList)) {
	               List<String> userNameList = new ArrayList<String>();
	                for (int i = 0; i < resultList.size(); i++) {
	                	MtPadCommonPageIce taskIce = resultList.get(i);
	                    data = new String[10];
	                    data[0] = taskIce.getOrganName(); // 所属组织
	                    data[1] = taskIce.getCreateDate(); // 下单时间
	                    data[2] = taskIce.getCreateByName(); // 下单人
	                    data[3] = taskIce.getSourceHouseName(); // 任务来源
	                    data[4] = taskIce.getTransTypeParentCodeName(); // 运送类型
	                    data[5] = taskIce.getUrgencyName(); // 紧急程度
	                    data[6] = taskIce.getFromHouseName(); // 出发地
	                    data[7] = taskIce.getToHouseName(); // 目的地
	                    if (taskIce.getUserList() != null && AppUtils.isNotEmpty(taskIce.getUserList())) {
	                    	for (segiwh.common.User user : taskIce.getUserList()) {
	                    		userNameList.add(user.getUserName() + "(" + user.getTel()  + "）");
							}
	                    	data[8] = AppUtils.listToString(userNameList, ','); // 运送员
						}
	                    userNameList.clear();
	                    data[9] = taskIce.getStatusName(); // 状态
	                    dataList.add(data);
	                }
	            }
	            file = ExcelWriter.simpleCreate(RestApiConstant.MEDICALTRANS, columnTitles, dataList);
	            ResponseDownloadUtils.downloadAndDelete(response, file, title);
			return null;
		} catch (Exception e) {
			logger.warn("系统异常,运送发起导出失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,运送发起导出失败！").buidler();
		} finally {
			// 下载并发次数释放
			AppConCurrentUtils.getInstace().subConCurCount(
					AppConCurrentTypeConstant.MAX_CURRENCY_EXCEL_EXP);
		}
	}
	
	/**
	 * @Title: exportMtTask 
	 * @Description: 运送任务评价信息分页导出 
	 * @author liuyi@segimail.com 
	 * @date 2018年9月13日上午9:55:56
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "运送任务评价信息分页导出 ", response = String.class, notes = "运送任务评价信息分页导出 ")
	@ApiImplicitParams({})
	@RequestMapping(value = "/exportMtTaskEvaluate.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse exportMtTaskEvaluate(@AdminUserParam User user, HttpServletResponse response,
            @RequestParam(value = "organId", required = true) String organId,
            @RequestParam(value = "userHouseId", required = true) String userHouseId,
            @RequestParam(value = "evaluateStatus", required = false) String evaluateStatus,
            @RequestParam(value = "beginTime", required = true) String beginTime,
            @RequestParam(value = "endTime", required = true) String endTime) {
		try {
			if (null == user) {
				return RestResponse.RestResponseBuilder.createFailBuilder(
						"用户未登录,请重新登录!").buidler();
			}
			//前端传进来的json转对象
			MtTaskPageIceParam params = new MtTaskPageIceParam();
			params.setOrganId(organId);
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			params.setUserHouseId(userHouseId);
			if (StringUtils.isNotEmpty(beginTime) && StringUtils.isNotEmpty(endTime)) {
				String fields = "beginTime, endTime";
				String messages = "运送开始时间, 运送结束时间";
				params.setBeginTime(beginTime);
				params.setEndTime(endTime);
				String errInfo = CheckRestParams.checkDateByRegex(params, fields, messages, Constant.DATA_FORMATE_YYYY_MM_DD_REG);
				if (null != errInfo) {
					return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
				}
				params.setBeginTime(params.getBeginTime().replaceAll("-", "") + "0000");
				params.setEndTime(params.getEndTime().replaceAll("-", "") + "2359");
				if (Long.valueOf(params.getBeginTime()).longValue() > Long.valueOf(params.getEndTime()).longValue()) {
					return RestResponse.RestResponseBuilder.createFailBuilder("开始时间必须小于或等于结束时间").buidler();
				}
			}
			// 导出标识
            params.setExportFlag(true);
			// 每次查询2500条
			params.setPageLength(MtConstant.EXPORT_EVERY_PAGE_LENGTH);
			params.setPageNo(MtConstant.EXPORT_PAGE_NO);
			//获取ICE服务
			TaskAndEvaluatePaginatorIceRsp rsp = this.getMtTaskQueryServiceIcePrx().queryEvaluatePageForPad(params);
			// 获取需要导出的数据
			List<MtPadCommonPageIce> resultList = new ArrayList<>();
			if (rsp != null && rsp.getPaginator() != null) {
				resultList.addAll(rsp.getResultList());
			}
			String totalCount = rsp.getPaginator().getTotalCount();
			int totalCountInt = Integer.parseInt(totalCount);
			// 判断导出是否超过5000条
			if (Integer.parseInt(MtConstant.EXPORT_PAGE_LENGTH) < totalCountInt) {
				return RestResponse.RestResponseBuilder.createFailBuilder("导出超过5000条， 请缩小查询范围！").buidler();
			}
			if (Integer.parseInt(MtConstant.EXPORT_EVERY_PAGE_LENGTH) < totalCountInt) {
				// 总条数大于2500时再查询一次
				params.setPageNo(String.valueOf(Integer.parseInt(MtConstant.EXPORT_PAGE_NO) + 1));
				rsp = this.getMtTaskQueryServiceIcePrx().queryEvaluatePageForPad(params);
				if (AppUtils.isNotEmpty(rsp.getResultList())) {
					resultList.addAll(rsp.getResultList());
				}
			}
			return exportMtEvaluateTask(response, resultList);
		} catch (Exception e) {
			logger.warn("系统异常,签收评价导出失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,签收评价导出失败！").buidler();
		}
	}

	private RestResponse exportMtEvaluateTask(HttpServletResponse response,
			List<MtPadCommonPageIce> resultList) {
		try {
			// 下载并发次数新增
			boolean exeFlag = AppConCurrentUtils.getInstace().addConCurCount(
					AppConCurrentTypeConstant.MAX_CURRENCY_EXCEL_EXP);
			if (!exeFlag) {
				return RestResponse.RestResponseBuilder.createFailBuilder(
						"当前数据导出操作的人数过多，请稍后再试！").buidler();
			}
			String title = "签收评价导出.xls";
			String[] columnTitles = {"预约时间", "下单人", "任务来源", "类型", "运送内容", "运送员", "状态"};
			File file = null;
			String[] data = null;
	            List<String[]> dataList = new ArrayList<String[]>();
	            if (AppUtils.isNotEmpty(resultList)) {
	            	StringBuilder builder = new StringBuilder();
	                List<String> userNameList = new ArrayList<String>();
	                for (int i = 0; i < resultList.size(); i++) {
	                	MtPadCommonPageIce taskIce = resultList.get(i);
	                    data = new String[7];
	                    data[0] = taskIce.getBeginTime(); // 预约时间
	                    data[1] = taskIce.getCreateByName(); // 下单人
	                    data[2] = taskIce.getSourceHouseName(); // 任务来源
	                    data[3] = taskIce.getTransTypeParentCodeName(); // 类型
	                    builder.append("【").append(taskIce.getTransTypeName()).append("】").append(taskIce.getFromHouseName());
	                    if (TransTypeEnum.TRANS_TYPE_04.getCode().equals(taskIce.getTransTypeParentCode())) {
	                    	builder.append("，").append(taskIce.getPatientName()).append("，").append(taskIce.getBedNo());
						}
	                    builder.append("，目的地：").append(taskIce.getToHouseName()).append("，工具：").append(taskIce.getTransToolsName());
	                    data[4] = builder.toString(); // 运送内容
	                    builder.delete( 0, builder.length());
	                    if (taskIce.getUserList() != null && AppUtils.isNotEmpty(taskIce.getUserList())) {
	                    	for (segiwh.common.User user : taskIce.getUserList()) {
	                    		if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getTel())) {
									continue;
								}
	                    		userNameList.add(user.getUserName() + "(" + user.getTel()  + ")");
							}
	                    	data[5] = AppUtils.listToString(userNameList, ','); // 运送员
						}
	                    userNameList.clear();
	                    if (taskIce.getEvaluate().equals("0")) {
	                    	data[6] = taskIce.getStatusName();
						} else {
							data[6] = "评价结果：" + taskIce.getEvaluate() + "颗星"; // 状态
						}
	                    dataList.add(data);
	                }
	            }
	            file = ExcelWriter.simpleCreate(RestApiConstant.MEDICALTRANS, columnTitles, dataList);
	            ResponseDownloadUtils.downloadAndDelete(response, file, title);
			return null;
		} catch (Exception e) {
			logger.warn("系统异常,签收评价导出失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,签收评价导出失败！").buidler();
		} finally {
			// 下载并发次数释放
			AppConCurrentUtils.getInstace().subConCurCount(
					AppConCurrentTypeConstant.MAX_CURRENCY_EXCEL_EXP);
		}
	}
	
	/**
	 * @discription 查询用户所在科室
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月18日 上午11:40:57     
	 * @param user
	 * @param organId
	 * @return
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "用户科室详情", response = String.class, notes = "")
    @ApiImplicitParams({})
    @RequestMapping(value = "/queryUserHospWeb.json", method = { RequestMethod.GET })
    @ResponseBody
    public RestResponse queryUserHospWeb(@AdminUserParam User user, 
    		@RequestParam(value = "organId", required = true) String organId) {
    	if (null == user) {
    		return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
    	}
    	try {
    		//必填参数验证
    		if (StringUtils.isBlank(organId)) {
    			return RestResponse.RestResponseBuilder.createFailBuilder("参数缺失").buidler();
    		}
    		//验证number类型
    		if (!NumberUtils.isDigits(organId)) {
    			//判断是否是number类型的
    			return RestResponse.RestResponseBuilder.createFailBuilder("格式错误！").buidler();
    		} 
        	UserHospIce params = new UserHospIce();
        	params.setUserId(String.valueOf(user.getUserId()));
        	params.setOrganId(organId);
            // 获取ICE服务
			UserHospRelReturnPadIce rpcResp = this.getUserHospServiceIcePrx().queryHospUserPad(params);
            if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
                return RestResponse.RestResponseBuilder.createSuccessBuilder("查询用户科室详成功!")
						.setResult(rpcResp.getUserHospInfoIce()).buidler();
            }
            return RestResponse.RestResponseBuilder.createFailBuilder(rpcResp.message).buidler();
        } catch (Exception e) {
            logger.warn("系统异常,查询用户科室详情失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,查询用户科室详情失败").buidler();
        }
    }
}
