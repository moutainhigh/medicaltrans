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

import com.segi.uhomecp.common.constant.RestApiConstant;
import com.segi.uhomecp.common.rest.AbsActionRest;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.enums.TransTaskTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTypeEnum;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.sso.auth.vo.User;
import com.segi.uhomecp.sso.client.annotation.AdminUserParam;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.web.rest.RestResponse;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.CheckRestParams;
import com.segi.uhomecp.wh.common.utils.DateUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

import IceExt.IceClientUtil;
import resp.RpcRespIce;
import segi.medicaltrans.mttask.manager.MtCommonIceParam;
import segi.medicaltrans.mttask.manager.MtTaskManagerCreateServiceIcePrx;
import segi.medicaltrans.mttask.manager.MtTaskManagerHandleServiceIcePrx;
import segi.medicaltrans.mttask.manager.MtTaskReq;

@Controller
@RequestMapping(RestApiConstant.API_PATH + "/medicaltrans/mtTaskManagerWebRest")
@Api(value = "medicaltrans/mtTaskManagerWebRest", description = "医院运送管理(pad端)")
public class MtTaskManagerWebRest extends AbsActionRest {
	private static final Logger logger = LoggerFactory.getLogger(MtTaskManagerWebRest.class);
	private static final String PREFIXPROJECTNAME = RestApiConstant.PREFIXPROJECTNAME;
	
	/**
	 * @discription 获取ice服务
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月24日 下午2:18:23     
	 * @return
	 */
	private MtTaskManagerCreateServiceIcePrx getMtTaskManagerCreateServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(MtTaskManagerCreateServiceIcePrx.class);
	}
	
	private MtTaskManagerHandleServiceIcePrx getMtTaskManagerHandleServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(MtTaskManagerHandleServiceIcePrx.class);
	}
	/**
	 * @discription 运送任务发起
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月24日 下午2:16:50     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "运送任务发起", response = String.class, notes = "{<br/>" + 
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
			"\"voiceIds\":\"语音附件\",<br/>" +
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
			String fields = "transTypeParentCode, transTypeId, urgency, fromHouseId, toHouseId, transTools,"
					+ "transPersonCount, isReservedFlag, limitMinute";
			String messages = "运送大类, 运送类型, 紧急程度, 出发地, 目的地, 运送工具, 运送人数, 是否预约, 时限";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//数字类型参数验证
			fields = "transTypeId, fromHouseId, toHouseId, transPersonCount, limitMinute";
			messages = "运送类型, 出发地, 目的地, 运送人数, 时限";
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
			// 从头部获取用户所在科室Id
			params.setUserHouseId(this.getHouseId());
			params.setOrganId(this.getOrganId());
			params.setUserId(this.getUserId());
			
			params.setTaskType(TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode());
			//从头部获取数据来源
			params.setDataSource(this.getSource());
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
	 * @created 2018年4月24日 下午3:04:50     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "运送任务编辑", response = String.class, notes = "{<br/>" + 
			"\"taskId\":\"运送任务Id\",<br/>" +
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
			"\"voiceIds\":\"语音附件 \",<br/>" +
			"\"delVoiceIds\":\"删除语音附件 \",<br/>" +
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
					+ "transPersonCount, isReservedFlag";
			String messages = "运送任务Id, 运送大类, 运送类型, 紧急程度, 出发地, 目的地, 运送工具, 运送人数, 是否预约";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//数字类型参数验证
			fields = "taskId, transTypeId, fromHouseId, toHouseId, transPersonCount";
			messages = "运送任务Id, 运送类型, 出发地, 目的地, 运送人数";
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
			}
			if (StringUtils.isNotBlank(params.getLimitMinute())) {
				errInfo = CheckRestParams.checkInteger(params, "limitMinute", "时限");
				if (null != errInfo) {
					return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
				}
				if (Integer.valueOf(params.getLimitMinute()).intValue() < 5 
						|| Integer.valueOf(params.getLimitMinute()).intValue() > 999) {
					return RestResponse.RestResponseBuilder.createFailBuilder("时限不在限定范围[5-999]分钟内!").buidler();
				}
			}
			if (StringUtils.isNotBlank(params.getPatientGender()) 
					&& !AppUtils.match(MtConstant.MT_TASK_SEX_REGEX, params.getPatientGender())) {
				//验证患者性别
				return RestResponse.RestResponseBuilder.createFailBuilder("患者性别输入有误!").buidler();
			}
			// 从头部获取用户所在科室Id
			params.setUserHouseId(this.getHouseId());
			params.setUserId(this.getUserId());
			params.setOrganId(this.getOrganId());
			
			params.setTaskType(TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode());
			//从头部获取数据来源
			params.setDataSource(this.getSource());
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
	 * @created 2018年4月24日 下午3:22:51     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "运送任务取消", response = String.class, notes = "{<br/>" + 
			"\"taskId\":\"运送任务Id\",<br/>" +
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
			String errInfo = CheckRestParams.checkEmpty(params, "taskId", "运送任务Id");
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//数字类型参数验证
			errInfo = CheckRestParams.checkInteger(params, "taskId", "运送任务Id");
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			params.setUserId(this.getUserId());
			params.setOrganId(this.getOrganId());
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
	 * @created 2018年4月24日 下午3:44:27     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "运送评价", response = String.class, notes = "{<br/>" + 
			"\"taskId\":\"运送任务Id\",<br/>" +
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
			String errInfo = CheckRestParams.checkEmpty(params, "taskId, evaluate", "运送任务Id, 评价结论");
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//数字类型参数验证
			errInfo = CheckRestParams.checkInteger(params, "taskId", "运送任务Id");
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (!AppUtils.match(MtConstant.MT_TASK_EVALUATE_REGEX, params.getEvaluate())) {
				//验证运送工具
				return RestResponse.RestResponseBuilder.createFailBuilder("评价结论输入有误!").buidler();
			} 
			if (Integer.valueOf(params.getEvaluate()).intValue() <3 
					&& StringUtils.isBlank(params.getEvaluateContent())) {
				//评价结论小于3,评价描述不能为空
				return RestResponse.RestResponseBuilder.createFailBuilder("小于3星必须填写评价描述!").buidler();
			}
			params.setUserId(this.getUserId());
			params.setOrganId(this.getOrganId());
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
	
}
