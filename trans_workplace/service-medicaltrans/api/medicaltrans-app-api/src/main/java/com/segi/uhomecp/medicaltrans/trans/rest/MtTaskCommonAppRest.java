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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.segi.uhomecp.common.constant.RestApiConstant;
import com.segi.uhomecp.common.rest.AbsActionRest;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.sso.auth.vo.User;
import com.segi.uhomecp.sso.client.annotation.AdminUserParam;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.web.rest.RestResponse;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.CheckRestParams;
import com.segi.uhomecp.wh.common.utils.NumberUtils;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

import IceExt.IceClientUtil;
import resp.RpcRespIce;
import segi.medicaltrans.mttask.manager.MtCommonIceParam;
import segi.medicaltrans.mttask.manager.MtTaskManagerHandleServiceIcePrx;
import segi.medicaltrans.mttask.query.MtTaskHistoryPaginatorIce;
import segi.medicaltrans.mttask.query.MtTaskPageIceParam;
import segi.medicaltrans.mttask.query.MtTaskQueryServiceIcePrx;
import segi.medicaltrans.mttask.query.TaskIsTimeOutIceParam;
import segi.medicaltrans.mttask.track.MtTaskTrackServiceIcePrx;
import segi.medicaltrans.mttask.track.TrackDetailRspIce;

@Controller
@RequestMapping(RestApiConstant.API_PATH + "/medicaltrans/mtTaskCommonAppRest")
@Api(value = "medicaltrans/mtTaskCommonAppRest", description = "医院运送公共接口(app端)")
public class MtTaskCommonAppRest extends AbsActionRest {
	private static final Logger logger = LoggerFactory.getLogger(MtTaskCommonAppRest.class);
	private static final String PREFIXPROJECTNAME = RestApiConstant.PREFIXPROJECTNAME;
	
	/**
	 * @discription 获取ice服务
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月4日 下午12:01:18     
	 * @return
	 */
	private MtTaskQueryServiceIcePrx getMtTaskQueryServiceIcePrxPrx(){
		return IceClientUtil.getServicePrxByClass(MtTaskQueryServiceIcePrx.class);
	}
	
	private MtTaskManagerHandleServiceIcePrx getMtTaskManagerHandleServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(MtTaskManagerHandleServiceIcePrx.class);
	}
	
	private MtTaskTrackServiceIcePrx getMtTaskTrackServiceIcePrx() {
	    return IceClientUtil.getServicePrxByClass(MtTaskTrackServiceIcePrx.class);
	}
	
	/**
	 * @discription 运送任务历史任务分页(调度,自主,固定任务)
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月9日 下午2:18:57     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "运送任务历史任务分页(调度,自主,固定任务)", response = String.class, notes = "{<br/>" + 
			"\"taskType\":\"任务类型:1调度任务;2自主任务;3固定任务\",<br/>" +
			"\"organId\":\"组织(医院)Id\",<br/>" +
			"\"pageNo\":\"页码\",<br/>" +
			"\"pageLength\":\"每页记录数\"<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryMtTaskHistoryPage.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryMtTaskHistoryPage(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtTaskPageIceParam params = FastjsonUtils.parseObject(paramJson, MtTaskPageIceParam.class);
			//必填参数验证
			String fields = "taskType, organId, pageNo, pageLength";
			String messages = "任务类型, 组织(医院)Id, 页码, 每页记录数";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (!AppUtils.match(MtConstant.MT_TASK_TYPE_REGEX, params.getTaskType())) {
				//验证任务类型
				return RestResponse.RestResponseBuilder.createFailBuilder("任务类型输入有误!").buidler();
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			//获取ICE服务
			MtTaskHistoryPaginatorIce rsp = this.getMtTaskQueryServiceIcePrxPrx().queryMtTaskHistoryPage(params);
			return RestResponse.RestResponseBuilder.createBuilder(rsp.code, rsp.message)
					.setResult(rsp.paginator, rsp.resultList.toArray()).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,历史任务分页查询失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,历史任务分页查询失败").buidler();
		}
	} 
	
	/**
	 * @discription 签名
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月25日 下午4:49:59     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "签名", response = String.class, notes = "{<br/>" + 
			"\"routeId\":\"运送路线Id\",<br/>" +
			"\"autographFileIds\":\"签名附件\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/autograph.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse autograph(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtCommonIceParam params = FastjsonUtils.parseObject(paramJson, MtCommonIceParam.class);
			//必填参数验证
			String fields = "routeId, autographFileIds";
			String messages = "运送路线Id, 签名附件";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, "routeId", "运送路线Id");
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			params.setOrganId(this.getOrganId());
			//获取ICE服务
			RpcRespIce rsp = this.getMtTaskManagerHandleServiceIcePrx().autograph(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, String> dataMap = new HashMap<>();
				dataMap.put("id", rsp.getData());
				return RestResponse.RestResponseBuilder.createSuccessBuilder("签名成功").setResult(dataMap).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.getMessage()).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,签名失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,签名失败").buidler();
		}
	} 
	
	/**
	 * @discription 运送任务完成
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月25日 下午5:04:37     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "运送任务完成", response = String.class, notes = "{<br/>" + 
			"\"taskId\":\"运送任务Id\",<br/>" +
			"\"organId\":\"项目Id\",<br/>" +
			"\"mac\":\"NFC方式必传\",<br/>" +
			"\"houseId\":\"二维码方式必传  目的地科室Id\",<br/>" +
			"\"routeId\":\"路线id\",<br/>" +
			"\"finishFileIds\":\"任务完成时传的附件  refType:MT_TASK_FINISH_FILE\",<br/>" +
			"\"taskUserIds\":\"运送员,多个以,分隔,只有调度任务有\",<br/>" +
			"\"finishContent\":\"完成情况\",<br/>" +
			"\"timeOutReason\":\"超时原因\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/finishMtTask.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse finishMtTask(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtCommonIceParam params = FastjsonUtils.parseObject(paramJson, MtCommonIceParam.class);
			String fields = "taskId, organId, routeId";
			String messages = "运送路线Id, 项目Id, 路线id";
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
			if (StringUtils.isNotBlank(params.getHouseId())) {
				errInfo = CheckRestParams.checkInteger(params, "houseId", "科室Id");
				if (null != errInfo) {
					return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
				}
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			//获取ICE服务
			RpcRespIce rsp = this.getMtTaskManagerHandleServiceIcePrx().finishMtTask(params);
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
	
	/**
	 * @Title: photograph 
	 * @Description: 运送任务拍照 
	 * @author liuyi@segimail.com 
	 * @date 2018年9月12日下午3:48:20
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "运送任务拍照", response = String.class, notes = "{<br/>" + 
			"\"taskId\":\"运送任务Id\",<br/>" +
			"\"photographFileIds\":\"拍照附件\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/photograph.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse photograph(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtCommonIceParam params = FastjsonUtils.parseObject(paramJson, MtCommonIceParam.class);
			//必填参数验证
			String fields = "taskId, photographFileIds";
			String messages = "运送任务Id, 拍照附件";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, "taskId", "运送任务Id");
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			params.setOrganId(this.getOrganId());
			//获取ICE服务
			RpcRespIce rsp = this.getMtTaskManagerHandleServiceIcePrx().photograph(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, String> dataMap = new HashMap<>();
				dataMap.put("id", rsp.getData());
				return RestResponse.RestResponseBuilder.createSuccessBuilder("拍照成功").setResult(dataMap).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.getMessage()).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,拍照失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,拍照失败").buidler();
		}
	} 
	
	/**
	 * @discription 查询轨迹详情
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月26日 下午6:48:30     
	 * @param user
	 * @param taskId
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "查询轨迹详情", response = String.class, notes = "")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryTaskTrackDetail.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryTaskTrackDetail(@AdminUserParam User user, 
			@RequestParam(value="taskId", required=true) String taskId) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//必填参数验证
			if (StringUtils.isBlank(taskId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder("参数缺失").buidler();
			}
			//验证number类型
			if (!NumberUtils.isDigits(taskId)) {
				//判断是否是number类型的
				return RestResponse.RestResponseBuilder.createFailBuilder("格式错误！").buidler();
			}
			//获取ICE服务
			TrackDetailRspIce rsp = this.getMtTaskTrackServiceIcePrx().queryTaskTrackById(Integer.valueOf(taskId));
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, Object> pageData = new HashMap<>(); 
				pageData.put("resultList", rsp.resultList);
				return RestResponse.RestResponseBuilder.createSuccessBuilder(rsp.message).setResult(pageData).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,查询轨迹详情失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,查询轨迹详情失败").buidler();
		}
	}
	
	/**
	 * @discription 查询任务是否超时
	 * @author zhangyang@segimail.com       
	 * @created 2018年10月9日 下午5:07:50     
	 * @param user
	 * @param taskId
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "查询任务是否超时", response = String.class, notes = "")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryTaskIsTimeOut.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryTaskIsTimeOut(@AdminUserParam User user, 
			@RequestParam(value="taskId", required=true) String taskId) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//必填参数验证
			if (StringUtils.isBlank(taskId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder("参数缺失").buidler();
			}
			//验证number类型
			if (!NumberUtils.isDigits(taskId)) {
				//判断是否是number类型的
				return RestResponse.RestResponseBuilder.createFailBuilder("格式错误！").buidler();
			}
			//获取ICE服务
			TaskIsTimeOutIceParam rsp = this.getMtTaskQueryServiceIcePrxPrx().queryTaskIsTimeOut(
					Integer.valueOf(this.getOrganId()), Integer.valueOf(taskId));
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, Object> pageData = new HashMap<>(); 
				pageData.put("id", rsp.taskId);
				pageData.put("isTimeOut", rsp.isTimeOut);
				return RestResponse.RestResponseBuilder.createSuccessBuilder(rsp.message).setResult(pageData).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,查询轨迹详情失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,查询任务是否超时失败").buidler();
		}
	}
}
