package com.segi.uhomecp.medicaltrans.report.rest;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import segi.medicaltrans.report.ratio.RatioReportServiceIcePrx;
import segi.medicaltrans.report.ratio.ReportCommonParam;
import segi.medicaltrans.report.ratio.TaskDateSourceReturnIce;
import segi.medicaltrans.report.ratio.TaskTypeReturnIce;
import segi.medicaltrans.report.ratio.TranstypeReturnIce;
import IceExt.IceClientUtil;

import com.segi.uhomecp.common.constant.RestApiConstant;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.sso.auth.vo.User;
import com.segi.uhomecp.sso.client.annotation.AdminUserParam;
import com.segi.uhomecp.web.rest.RestResponse;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(RestApiConstant.API_PATH + "/medicaltrans/mtTaskAmountRatioWebRest")
@Api(value = "medicaltrans/mtTaskAmountRatioWebRest", description = "大屏报表管理")
public class MtTaskAmountRatioWebRest {

	private static final Logger LOGGER = LoggerFactory.getLogger(MtTaskAmountRatioWebRest.class);

	private static final String PREFIXPROJECTNAME = RestApiConstant.PREFIXPROJECTNAME;
	
	private RatioReportServiceIcePrx getRatioReportServiceIcePrx() {
		return IceClientUtil.getServicePrxByClass(RatioReportServiceIcePrx.class);
	}
	
	/**
	 * @discription 任务类型占比
	 * @author yangyh@segimail.com       
	 * @created 2018年7月24日 上午11:59:37     
	 * @param user
	 * @param response
	 * @param organId
	 * @param cycleY
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "任务类型占比", response = String.class, notes = "任务类型占比")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryTaskTypeRatio.json", method = {RequestMethod.GET})
	@ResponseBody
	public RestResponse queryTaskTypeRatio(@AdminUserParam User user, HttpServletResponse response,
			@RequestParam(value = "organId", required = true) String organId,
			@RequestParam(value = "cycleY", required = true) String cycleY) {
		try {
			RestResponse checkParam = checkParam(user, organId, cycleY);
			if (null != checkParam) {
				return checkParam;
			}
			ReportCommonParam param = new ReportCommonParam();
			param.setOrganId(organId);
			param.setCycleY(cycleY);
			TaskTypeReturnIce rsp = getRatioReportServiceIcePrx().queryTaskTypeRatio(param);
			return RpcError.SUCCESS.getCode().equals(rsp.code)
					? RestResponse.RestResponseBuilder.createSuccessBuilder("任务类型占比查询成功！").setResult(rsp.data)
							.buidler()
					: RestResponse.RestResponseBuilder.createFailBuilder("任务类型占比查询失败！").buidler();
		} catch (Exception e) {
			LOGGER.warn("系统异常，任务类型占比查询失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常，任务类型占比查询失败！").buidler();
		}
	}

	/**
	 * @discription 入参验证公共方法
	 * @author yangyh@segimail.com       
	 * @created 2018年8月14日 下午5:08:30     
	 * @param user
	 * @param organId
	 * @param cycleY
	 * @return
	 */
	private RestResponse checkParam(User user, String organId, String cycleY) {
		if (user == null) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录，请重新登录!").buidler();
		}
		if (StringUtils.isBlank(organId) || StringUtils.isBlank(cycleY)) {
			return RestResponse.RestResponseBuilder.createFailBuilder("查询失败，参数不全！").buidler();
		}
		return null;
	}
	
	/**
	 * @discription 运送类型占比
	 * @author yangyh@segimail.com       
	 * @created 2018年7月24日 上午11:59:37     
	 * @param user
	 * @param response
	 * @param organId
	 * @param cycleY
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "运送类型占比", response = String.class, notes = "运送类型占比")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryTransTypeRatio.json", method = {RequestMethod.GET})
	@ResponseBody
	public RestResponse queryTransTypeRatio(@AdminUserParam User user, HttpServletResponse response,
			@RequestParam(value = "organId", required = true) String organId,
			@RequestParam(value = "cycleY", required = true) String cycleY) {
		try {
			RestResponse checkParam = checkParam(user, organId, cycleY);
			if (null != checkParam) {
				return checkParam;
			}
			ReportCommonParam param = new ReportCommonParam();
			param.setOrganId(organId);
			param.setCycleY(cycleY);
			TranstypeReturnIce rsp = getRatioReportServiceIcePrx().queryTransTypeRatio(param);
			return RpcError.SUCCESS.getCode().equals(rsp.code)
					? RestResponse.RestResponseBuilder.createSuccessBuilder("运送类型占比查询成功！").setResult(rsp.data)
							.buidler()
					: RestResponse.RestResponseBuilder.createFailBuilder("运送类型占比查询失败！").buidler();
		} catch (Exception e) {
			LOGGER.warn("系统异常，运送类型占比查询失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常，运送类型占比查询失败！").buidler();
		}
	}
	
	/**
	 * @discription 任务发起源占比
	 * @author yangyh@segimail.com       
	 * @created 2018年7月24日 上午11:59:37     
	 * @param user
	 * @param response
	 * @param organId
	 * @param cycleY
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "任务发起源占比", response = String.class, notes = "任务发起源占比")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryTaskDateSourceRatio.json", method = {RequestMethod.GET})
	@ResponseBody
	public RestResponse queryTaskDateSourceRatio(@AdminUserParam User user, HttpServletResponse response,
			@RequestParam(value = "organId", required = true) String organId,
			@RequestParam(value = "cycleY", required = true) String cycleY) {
		try {
			RestResponse checkParam = checkParam(user, organId, cycleY);
			if (null != checkParam) {
				return checkParam;
			}
			ReportCommonParam param = new ReportCommonParam();
			param.setOrganId(organId);
			param.setCycleY(cycleY);
			TaskDateSourceReturnIce rsp = getRatioReportServiceIcePrx().queryTaskDateSourceRatio(param);
			return RpcError.SUCCESS.getCode().equals(rsp.code)
					? RestResponse.RestResponseBuilder.createSuccessBuilder("任务发起源占比查询成功！").setResult(rsp.data)
							.buidler()
					: RestResponse.RestResponseBuilder.createFailBuilder("任务发起源占比查询失败！").buidler();
		} catch (Exception e) {
			LOGGER.warn("系统异常，任务发起源占比查询失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常，任务发起源占比查询失败！").buidler();
		}
	}
}
