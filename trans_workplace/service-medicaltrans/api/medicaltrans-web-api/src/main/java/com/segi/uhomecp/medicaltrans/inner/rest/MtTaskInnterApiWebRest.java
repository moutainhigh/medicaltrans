package com.segi.uhomecp.medicaltrans.inner.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.segi.uhomecp.wh.common.enums.DataSourceEnum;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.CheckRestParams;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

import IceExt.IceClientUtil;
import resp.RpcRespIce;
import segi.medicaltrans.base.location.MtLocationManagerServiceIcePrx;
import segi.medicaltrans.common.userposit.MtUserPositCommonServiceIcePrx;
import segi.medicaltrans.common.userposit.UserLocationTaskNumParam;
import segi.medicaltrans.mttask.manager.MtFixedTaskParam;
import segi.medicaltrans.mttask.manager.MtTaskManagerCreateServiceIcePrx;

@Controller
@RequestMapping("/inner-rest-api/v1/medicaltrans/mtInnerWebRest")
@Api(value = "/inner-rest-api/v1/medicaltrans/mtInnerWebRest", description = "医院运送管理(内部接口)")
public class MtTaskInnterApiWebRest extends AbsActionRest {
	private static final Logger logger = LoggerFactory.getLogger(MtTaskInnterApiWebRest.class);
	private static final String PREFIXPROJECTNAME = RestApiConstant.PREFIXPROJECTNAME;
	
	/**
	 * @discription 获取ice服务
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月25日 上午10:43:17     
	 * @return
	 */
	private MtTaskManagerCreateServiceIcePrx getMtTaskManagerCreateServiceIcePrx() {
		return IceClientUtil.getServicePrxByClass(MtTaskManagerCreateServiceIcePrx.class);
	}
	
	 /**
     * @discription 人员位置公共service
     * @author yangyh@segimail.com       
     * @created 2018年6月1日 上午10:29:46     
     * @return
     */
    private static MtUserPositCommonServiceIcePrx getMtUserPositCommonServiceIcePrx(){
    	return IceClientUtil.getServicePrxByClass(MtUserPositCommonServiceIcePrx.class);
    }
	
	/**
	 * @discription 获取ice服务
	 * @author dengdong@segimail.com       
	 * @created 2018年5月9日 下午4:47:14     
	 * @return
	 */
	private MtLocationManagerServiceIcePrx getMtLocaitonManagerServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(MtLocationManagerServiceIcePrx.class);
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
			"\"urgency\":\"紧急程度,1:一般;2:紧急;3:特急,选全部就不传\",<br/>" +
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
			"\"flieIds\":\"附件 refType:MT_TASK_FILE\"<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/saveMtFixedTask.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse saveMtFixedTask(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		try {
			//前端传进来的json转对象
			MtFixedTaskParam params = FastjsonUtils.parseObject(paramJson, MtFixedTaskParam.class);
			//必填参数验证
			String fields = "organId, transTypeParentCode, fromHouseId, toHouseId, transTools,createBy,limitMinute, taskLoopId";
			String messages = "项目(医院)Id, 运送大类, 出发地, 目的地, 运送工具, 创建者, 时限,循环任务编码";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//数字类型参数验证
			fields = "organId, fromHouseId, toHouseId, createBy, limitMinute, taskLoopId";
			messages = "项目(医院)Id, 出发地, 目的地, 创建者, 时限,循环任务编码";
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//正则表达式验证
			if (!AppUtils.match(MtConstant.MT_TASK_TRANSTOOLS_REGEX, params.getTransTools())) {
				//验证运送工具
				return RestResponse.RestResponseBuilder.createFailBuilder("运送工具输入有误!").buidler();
			} 
			if(TransTypeEnum.TRANS_TYPE_04.getCode().equals(params.getTransTypeParentCode())) {
				// 运送大类是病人看护，需要对下面的字段进行效验必填性
				fields = "patientName, bedNo, patientGender, medicalRecNo";
				messages = "患者姓名, 床号, 患者性别, 病历号";
				errInfo = CheckRestParams.checkEmpty(params, fields, messages);
				if (null != errInfo) {
					return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
				}
			}
			if (StringUtils.isNotBlank(params.getBeginTime())) {
				errInfo = CheckRestParams.checkDateByRegex(params, "beginTime", "开始时间", Constant.DATA_FORMATE_YYYY_MM_DD_HH_MM_REG);
				if (null != errInfo) {
					return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
				}
				params.setBeginTime(params.getBeginTime().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", ""));
			}
			if (StringUtils.isNotBlank(params.getLimitMinute()) 
					&& Integer.valueOf(params.getLimitMinute()).intValue() >= 5 
					&& Integer.valueOf(params.getLimitMinute()).intValue() <= 999) {
				errInfo = CheckRestParams.checkInteger(params, "limitMinute", "时限");
				if (null != errInfo) {
					return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
				}
			}
			if (StringUtils.isNotBlank(params.getPatientGender()) 
					&& !AppUtils.match(MtConstant.MT_TASK_SEX_REGEX, params.getPatientGender())) {
				// 验证患者性别
				return RestResponse.RestResponseBuilder.createFailBuilder("患者性别输入有误!").buidler();
			}
			if(!AppUtils.isNotEmpty(params.getTransactors())) {
				// 运送人员参数为空
				return RestResponse.RestResponseBuilder.createFailBuilder("运送人员参数为空!").buidler();
			}
			
			if(!AppUtils.isNotEmpty(params.getRouteList())) {
				// 运送路线参数为空
				return RestResponse.RestResponseBuilder.createFailBuilder("运送路线参数为空！").buidler();
			}
			
			params.setTaskType(TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode());
			params.setDataSource(DataSourceEnum.DATA_SOURCE_WEB.getCode());
			RpcRespIce rsp = getMtTaskManagerCreateServiceIcePrx().createFixedTask(params);
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
	
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "根据项目id查位置信息（缓存查看）", response = String.class, notes = "根据项目id查位置信息（缓存查看）")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryLocationByOrganIdRedis.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryLocationByOrganIdRedis(@AdminUserParam User user, 
            @RequestParam(value = "organId", required = false) String organId) {
		try {
			// 获取ICE服务
			String loactionJson = getMtLocaitonManagerServiceIcePrx().queryLocationByOrganIdRedis(organId);
			return RestResponse.RestResponseBuilder.createSuccessBuilder("根据项目id查位置信息（缓存查看）")
					.setResult(loactionJson).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过项目ID查询所有楼栋简单信息失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "根据项目id查人员位置信息（缓存查看）", response = String.class, notes = "根据项目id查人员位置信息（缓存查看）")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryUserLocationByOrganIdRedis.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryUserLocationByOrganIdRedis(@AdminUserParam User user, 
            @RequestParam(value = "organId", required = false) String organId) {
		try {
			// 获取ICE服务
			String loactionJson = getMtLocaitonManagerServiceIcePrx().queryLocationByOrganIdRedis(organId);
			return RestResponse.RestResponseBuilder.createSuccessBuilder("根据项目id查位置信息（缓存查看）")
					.setResult(loactionJson).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过项目ID查询所有楼栋简单信息失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	@ApiOperation(value = PREFIXPROJECTNAME + "更新人员运送单未完成数", response = String.class, notes = "{<br/>"
			+ "\"organId\":\"项目(医院)Id\",<br/>"
			+ "\"userIds\":\"员工集合\",<br/>"
			+ "\"unTaskNum\":\"未完成工单数\",<br/>"
			+ "\"runTaskNum\":\"正在进行工单数\",<br/>"
			+ "\"executeDate\":\"触发时间\",<br/>"
			+ "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/updateUserPositInfo.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse updateUserPositInfo(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			// 前端传进来的json转对象
			UserLocationTaskNumParam params = FastjsonUtils.parseObject(paramJson, UserLocationTaskNumParam.class);
			// 必填参数验证
			String fields = "organId, userIds, unTaskNum,runTaskNum,executeDate";
			String messages = "项目(医院)Id, 员工集合, 未完成工单数,正在进行工单数,触发时间";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			Integer organId = Integer.valueOf(params.getOrganId());
			String[] userIdArr = params.getUserIds().split(",");
			List<Integer> userIds = new ArrayList<Integer>();
			for (String str : userIdArr) {
				userIds.add(Integer.valueOf(str));
			}
			short unTaskNum = Short.parseShort(params.getUnTaskNum());
			short runTaskNum = Short.parseShort(params.getRunTaskNum());
			// 获取ICE服务
			RpcRespIce rsp = getMtUserPositCommonServiceIcePrx().updateUserPositInfo(organId, userIds, unTaskNum, runTaskNum,params.getExecuteDate(),null);
			return RestResponse.RestResponseBuilder.createBuilder(rsp.code, rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("更新人员运送单未完成数异常!", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,更新人员运送单未完成数异常!").buidler();
		}
	}
	
	
}
