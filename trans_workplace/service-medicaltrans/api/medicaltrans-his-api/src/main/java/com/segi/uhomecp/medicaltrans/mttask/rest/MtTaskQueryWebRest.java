package com.segi.uhomecp.medicaltrans.mttask.rest;

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

import segi.medicaltrans.mttask.query.MtTaskDetailIceParam;
import segi.medicaltrans.mttask.query.MtTaskDetailRetIce;
import segi.medicaltrans.mttask.query.MtTaskDetailRetIceRsp;
import segi.medicaltrans.mttask.query.MtTaskPageIceParam;
import segi.medicaltrans.mttask.query.MtTaskQueryServiceIcePrx;
import segi.medicaltrans.mttask.query.TaskAndEvaluatePaginatorIceRsp;
import segi.medicaltrans.mttask.track.MtTaskTrackServiceIcePrx;
import segi.medicaltrans.mttask.track.TrackDetailRspIce;
import IceExt.IceClientUtil;

import com.segi.uhomecp.common.constant.RestApiConstant;
import com.segi.uhomecp.common.rest.AbsActionRest;
import com.segi.uhomecp.medicaltrans.enums.InvokingFlagEnum;
import com.segi.uhomecp.medicaltrans.enums.TransStatusEnum;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.sso.auth.vo.User;
import com.segi.uhomecp.sso.client.annotation.AdminUserParam;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.web.rest.RestResponse;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.CheckRestParams;
import com.segi.uhomecp.wh.common.utils.NumberUtils;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(RestApiConstant.API_PATH + "/medicaltrans/mtTaskQueryWebRest")
@Api(value = "medicaltrans/mtTaskQueryWebRest", description = "医院运送管理(pad端)")
public class MtTaskQueryWebRest extends AbsActionRest {
	private static final Logger logger = LoggerFactory.getLogger(MtTaskQueryWebRest.class);
	private static final String PREFIXPROJECTNAME = RestApiConstant.PREFIXPROJECTNAME;
	
	/**
	 * @discription 获取ice服务
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月24日 下午2:18:23     
	 * @return
	 */
	private MtTaskQueryServiceIcePrx getMtTaskQueryServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(MtTaskQueryServiceIcePrx.class);
	}
	
	private MtTaskTrackServiceIcePrx getMtTaskTrackServiceIcePrx() {
	    return IceClientUtil.getServicePrxByClass(MtTaskTrackServiceIcePrx.class);
	} 
	
	/**
	 * @discription 分页查询当前用户发起的运送任务
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月24日 下午2:53:28     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "分页查询当前用户发起的运送任务", response = String.class, notes = "{<br/>" + 
			"\"transTypeParentCode\":\"运送大类\",<br/>" +
			"\"beginTime\":\"运送开始时间 YYYY-MM-DD\",<br/>" +
			"\"endTime\":\"运送结束时间 YYYY-MM-DD\",<br/>" +
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
			String fields = "beginTime, endTime, pageNo, pageLength";
			String messages = "运送开始时间, 运送结束时间, 页码, 每页记录数";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			fields = "pageNo, pageLength";
			messages = "页码, 每页记录数";
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
				if (Long.valueOf(params.getBeginTime()).intValue() > Long.valueOf(params.getEndTime()).intValue()) {
					return RestResponse.RestResponseBuilder.createFailBuilder("开始时间必须小于或等于结束时间").buidler();
				}
			}
			params.setUserId(this.getUserId());
			params.setOrganId(this.getOrganId());
			params.setInvokingFlag(InvokingFlagEnum.INVOKING_FROM_PAD.getCode());
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
	 * @created 2018年4月24日 下午4:08:51     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "评价信息分页查询", response = String.class, notes = "{<br/>" + 
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
			String fields = "beginTime, endTime, pageNo, pageLength";
			String messages = "运送开始时间, 运送结束时间, 页码, 每页记录数";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			fields = "pageNo, pageLength";
			messages = "页码, 每页记录数";
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
				if (Long.valueOf(params.getBeginTime()).intValue() > Long.valueOf(params.getEndTime()).intValue()) {
					return RestResponse.RestResponseBuilder.createFailBuilder("开始时间必须小于或等于结束时间").buidler();
				}
			}
			//获取登录用户所在科室Id
			params.setUserHouseId(this.getHouseId());
			params.setOrganId(this.getOrganId());
			params.setUserId(this.getUserId());
			params.setVersion(this.getVersion());
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
	 * @created 2018年4月24日 下午4:12:27     
	 * @param user
	 * @param taskId
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "运送任务详情和评价详情公用接口", response = String.class, notes = "")
	@ApiParam(name="taskId", value="运送任务Id") 
	@ApiImplicitParams({})
	@RequestMapping(value="/queryTaskOrEvaluateDetail.json", method = {RequestMethod.GET})
	@ResponseBody
	public RestResponse queryTaskOrEvaluateDetail(@AdminUserParam User user, @RequestParam(value="taskId", required=true) String taskId) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			if (StringUtils.isEmpty(taskId)) {
				//接口必填参数判断
				return RestResponse.RestResponseBuilder.createFailBuilder("查询运送任务评价详情必填参数缺失!").buidler();
			}
			if (!NumberUtils.isDigits(taskId)) {
				//判断运送任务Id是否是number类型的
				return RestResponse.RestResponseBuilder.createFailBuilder("运送任务主键格式错误！").buidler();
			}
			MtTaskDetailIceParam params = new MtTaskDetailIceParam();
			params.setTaskId(taskId);
			params.setOrganId(this.getOrganId());
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
	 * @discription 查询轨迹详情
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月26日 下午6:48:52     
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
	
}
