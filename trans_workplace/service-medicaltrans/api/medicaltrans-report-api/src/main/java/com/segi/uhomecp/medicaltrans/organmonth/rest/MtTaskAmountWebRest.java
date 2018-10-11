package com.segi.uhomecp.medicaltrans.organmonth.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.segi.uhomecp.common.rest.AbsActionRest;
import com.segi.uhomecp.rest.utils.StringUtils;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.sso.auth.vo.User;
import com.segi.uhomecp.sso.client.annotation.AdminUserParam;
import com.segi.uhomecp.web.rest.RestResponse;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

import IceExt.IceClientUtil;
import segi.medicaltrans.report.organmonth.AvgRespTimeReturnIce;
import segi.medicaltrans.report.organmonth.OrganMonthAmontReturnIce;
import segi.medicaltrans.report.organmonth.OrganMonthAmountServiceIcePrx;
import segi.medicaltrans.report.organmonth.TimelyAmountMonthReturnIce;
import segi.medicaltrans.report.organmonth.TransProfileReturnIce;
import segi.medicaltrans.report.organmonth.UserAmountMonthReturnIce;

@Controller
@RequestMapping("/rest-api/v1/medicaltrans/mtTaskAmountWebRest")
@Api(value = "medicaltrans/mtTaskAmountWebRest", description = "医院运送管理报表数据分析")
public class MtTaskAmountWebRest extends AbsActionRest {
	private static final Logger logger = LoggerFactory.getLogger(MtTaskAmountWebRest.class);
	
	/**
	 * @discription 获取ice服务
	 * @author dengdong@segimail.com       
	 * @created 2018年8月1日 下午4:47:14     
	 * @return
	 */
	private OrganMonthAmountServiceIcePrx getOrganMonthAmountServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(OrganMonthAmountServiceIcePrx.class);
	}
	
	/**
	 * 运送概况
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = "运送概况", response = String.class, notes = "{<br/>" + 
			"\"organId\":\"项目Id\",<br/>" +
			"\"cycleY\":\"年\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryTransProfile.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryTransProfile(@AdminUserParam User user,@RequestParam(value="organId", required=true) String organId,
			@RequestParam(value="cycleY", required=true) String cycleY) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		if(StringUtils.isEmpty(organId)){
			return RestResponse.RestResponseBuilder.createFailBuilder("运送概况查询必填参数项目id为空!").buidler();
		}
		if(StringUtils.isEmpty(cycleY)){
			return RestResponse.RestResponseBuilder.createFailBuilder("运送概况查询必填参数年为空!").buidler();
		}
		try {
			TransProfileReturnIce rsp =this.getOrganMonthAmountServiceIcePrx().getTransProfile(organId,cycleY);
			if (RpcError.SUCCESS.getCode().equals(rsp.code)) {
			    return RestResponse.RestResponseBuilder.createSuccessBuilder("查看运送概况成功!").setResult(rsp.getTransProfile()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.msg).buidler();
		} catch (Exception e) {
			logger.warn("系统异常", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常").buidler();
		}
	}
	
	/**
	 *每月运送量趋势分析
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = "每月运送量趋势分析", response = String.class, notes = "{<br/>" + 
			"\"organId\":\"项目Id\",<br/>" +
			"\"cycleY\":\"年\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryAmountMonth.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryAmountMonth(@AdminUserParam User user,@RequestParam(value="organId", required=true) String organId,
			@RequestParam(value="cycleY", required=true) String cycleY) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		if(StringUtils.isEmpty(organId)){
			return RestResponse.RestResponseBuilder.createFailBuilder("每月运送量趋势分析必填参数项目id为空!").buidler();
		}
		if(StringUtils.isEmpty(cycleY)){
			return RestResponse.RestResponseBuilder.createFailBuilder("每月运送量趋势分析必填参数年为空!").buidler();
		}
		try {
			OrganMonthAmontReturnIce rsp =this.getOrganMonthAmountServiceIcePrx().queryAmountMonth(organId, cycleY);
			if (RpcError.SUCCESS.getCode().equals(rsp.code)) {
			    return RestResponse.RestResponseBuilder.createSuccessBuilder("每月运送量趋势分析成功!").setResult(rsp.getOrganMonthAmontIceList()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.msg).buidler();
		} catch (Exception e) {
			logger.warn("系统异常", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常").buidler();
		}
	}
	
	/**
	 *及时率/满意率趋势分析
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = "及时率/满意率趋势分析", response = String.class, notes = "{<br/>" + 
			"\"organId\":\"项目Id\",<br/>" +
			"\"cycleY\":\"年\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryTimelyAmountMonth.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryTimelyAmountMonth(@AdminUserParam User user,@RequestParam(value="organId", required=true) String organId,
			@RequestParam(value="cycleY", required=true) String cycleY) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		if(StringUtils.isEmpty(organId)){
			return RestResponse.RestResponseBuilder.createFailBuilder("及时率/满意率趋势分析必填参数项目id为空!").buidler();
		}
		if(StringUtils.isEmpty(cycleY)){
			return RestResponse.RestResponseBuilder.createFailBuilder("及时率/满意率趋势分析必填参数年为空!").buidler();
		}
		try {
			TimelyAmountMonthReturnIce rsp = this.getOrganMonthAmountServiceIcePrx().queryTimelyAmountMonth(organId, cycleY);
			if (RpcError.SUCCESS.getCode().equals(rsp.code)) {
			    return RestResponse.RestResponseBuilder.createSuccessBuilder("及时率/满意率趋势分析成功!").setResult(rsp.getTimelyAmountMonthIceList()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.msg).buidler();
		} catch (Exception e) {
			logger.warn("系统异常", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常").buidler();
		}
	}
	
	
	/**
	 *运送员每月平均运送量趋势分析
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = "运送员每月平均运送量趋势分析", response = String.class, notes = "{<br/>" + 
			"\"organId\":\"项目Id\",<br/>" +
			"\"cycleY\":\"年\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryUserAmountMonth.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryUserAmountMonth(@AdminUserParam User user,@RequestParam(value="organId", required=true) String organId,
			@RequestParam(value="cycleY", required=true) String cycleY) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		if(StringUtils.isEmpty(organId)){
			return RestResponse.RestResponseBuilder.createFailBuilder("运送员每月平均运送量趋势分析必填参数项目id为空!").buidler();
		}
		if(StringUtils.isEmpty(cycleY)){
			return RestResponse.RestResponseBuilder.createFailBuilder("运送员每月平均运送量趋势分析必填参数年为空!").buidler();
		}
		try {
			UserAmountMonthReturnIce rsp = this.getOrganMonthAmountServiceIcePrx().queryUserAmountMonth(organId, cycleY);
			if (RpcError.SUCCESS.getCode().equals(rsp.code)) {
			    return RestResponse.RestResponseBuilder.createSuccessBuilder("运送员每月平均运送量趋势分析成功!").setResult(rsp.getUserAmountMonthIceList()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.msg).buidler();
		} catch (Exception e) {
			logger.warn("系统异常", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常").buidler();
		}
	}
	
	/**
	 *即时任务响应时间趋势分析
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = "即时任务响应时间趋势分析", response = String.class, notes = "{<br/>" + 
			"\"organId\":\"项目Id\",<br/>" +
			"\"cycleY\":\"年\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryHisRespTime.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryHisRespTime(@AdminUserParam User user,@RequestParam(value="organId", required=true) String organId,
			@RequestParam(value="cycleY", required=true) String cycleY) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		if(StringUtils.isEmpty(organId)){
			return RestResponse.RestResponseBuilder.createFailBuilder("即时任务响应时间趋势分析必填参数项目id为空!").buidler();
		}
		if(StringUtils.isEmpty(cycleY)){
			return RestResponse.RestResponseBuilder.createFailBuilder("即时任务响应时间趋势分析必填参数年为空!").buidler();
		}
		try {
			AvgRespTimeReturnIce rsp = this.getOrganMonthAmountServiceIcePrx().queryHisRespTime(organId, cycleY);
			if (RpcError.SUCCESS.getCode().equals(rsp.code)) {
			    return RestResponse.RestResponseBuilder.createSuccessBuilder("即时任务响应时间趋势分析成功!").setResult(rsp.getAvgRespTimeIceList()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.msg).buidler();
		} catch (Exception e) {
			logger.warn("系统异常", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常").buidler();
		}
	}
	
}
