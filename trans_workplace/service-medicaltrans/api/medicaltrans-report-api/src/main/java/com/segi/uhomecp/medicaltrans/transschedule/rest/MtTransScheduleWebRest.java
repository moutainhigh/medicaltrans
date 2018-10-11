package com.segi.uhomecp.medicaltrans.transschedule.rest;

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
import segi.medicaltrans.common.report.transschedule.TransScheduleIceParams;
import segi.medicaltrans.common.report.transschedule.TransSchedulePaginatorIce;
import segi.medicaltrans.common.report.transschedule.TransScheduleServiceIcePrx;
import segi.medicaltrans.report.repairdata.Callback_RepairDataServiceIce_repairTaskReportData;
import segi.medicaltrans.report.repairdata.RepairDataServiceIcePrx;
import segi.medicaltrans.report.repairdata.RepairReportDataIceParams;
import Ice.LocalException;
import IceExt.IceClientUtil;

import com.segi.uhomecp.common.rest.AbsActionRest;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.sso.auth.vo.User;
import com.segi.uhomecp.sso.client.annotation.AdminUserParam;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.utils.MD5Util;
import com.segi.uhomecp.web.rest.RestResponse;
import com.segi.uhomecp.wh.common.utils.CheckRestParams;
import com.segi.uhomecp.wh.common.utils.DateUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/rest-api/v1/medicaltrans/mtTransScheduleWebRest")
@Api(value = "medicaltrans/mtTransScheduleWebRest", description = "数据校正管理")
public class MtTransScheduleWebRest extends AbsActionRest {
	private static final Logger logger = LoggerFactory.getLogger(MtTransScheduleWebRest.class);
	
	private static TransScheduleServiceIcePrx getTransScheduleServiceIcePrx() {
    	return IceClientUtil.getServicePrxByClass(TransScheduleServiceIcePrx.class);
    }
	
	private static RepairDataServiceIcePrx getRepairDataServiceIcePrx() {
    	return IceClientUtil.getServicePrxByClass(RepairDataServiceIcePrx.class);
    }
	
	/**
	 * @discription 校正列表分页查询
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月9日 下午6:10:48     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = "校正列表分页查询", response = String.class, notes = "{<br/>" + 
			"\"groupOrganId\":\"一级物业Id\",<br/>" +
			"\"organId\":\"项目(医院)Id\",<br/>" +
			"\"runningStatus\":\"执行状态 0：执行中，1：成功，2：失败\",<br/>" +
			"\"pageLength\":\"每页条数\",<br/>" +
			"\"pageNo\":\"页码\"<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryTransSchedulePage.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryTransSchedulePage(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			TransScheduleIceParams params = FastjsonUtils.parseObject(paramJson, TransScheduleIceParams.class);
			//必填参数验证
			String fields = "pageLength, pageNo";
			String messages = "每页条数, 页码";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//数字类型参数验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (StringUtils.isNotBlank(params.getGroupOrganId())) {
				errInfo = CheckRestParams.checkInteger(params, "groupOrganId", "一级物业Id");
				if (null != errInfo) {
					return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
				}
			}
			if (StringUtils.isNotBlank(params.getOrganId())) {
				errInfo = CheckRestParams.checkInteger(params, "organId", "组织机构Id");
				if (null != errInfo) {
					return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
				}
			}
			// 获取登录用户的userId和organId
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			TransSchedulePaginatorIce rsp = getTransScheduleServiceIcePrx().queryTransSchedulePaginator(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, Object> pageData = new HashMap<>(); 
				pageData.put("paginator", rsp.paginator);
				pageData.put("resultList", rsp.getTransScheduleIceList());
				return RestResponse.RestResponseBuilder.createSuccessBuilder(rsp.message).setResult(pageData).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,校正列表分页查询失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,校正列表分页查询失败").buidler();
		}
	}
	
	/**
	 * @discription 校正报表数据
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月16日 上午9:27:59     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = "校正报表数据", response = String.class, notes = "{<br/>" + 
			"\"organId\":\"项目(医院)Id\",<br/>" +
			"\"groupOrganId\":\"一级物业Id\",<br/>" +
			"\"pwd\":\"密码\",<br/>" +
			"\"beginTime\":\"校正开始时间\",<br/>" +
			"\"endTime\":\"校正结束时间\"<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/repairTaskReportData.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse repairTaskReportData(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			RepairReportDataIceParams params = FastjsonUtils.parseObject(paramJson, RepairReportDataIceParams.class);
			//必填参数验证
			String fields = "organId, groupOrganId, pwd, beginTime, endTime";
			String messages = "项目Id, 一级物业Id, 密码, 校正开始时间, 校正结束时间";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//数字类型参数验证
			errInfo = CheckRestParams.checkInteger(params, "organId, groupOrganId", "项目Id, 一级物业Id");
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			errInfo = CheckRestParams.checkDateByRegex(params, "beginTime, endTime", "校正开始时间, 校正结束时间", 
					MtConstant.FORMAT_YYYY_MM_REGEX);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			params.setBeginTime(params.getBeginTime().replaceAll("-", ""));
			params.setEndTime(params.getEndTime().replaceAll("-", ""));
			if (params.getBeginTime().compareTo(params.getEndTime()) > 0) {
				return RestResponse.RestResponseBuilder.createFailBuilder("开始时间必须小于结束时间!").buidler();
			}
			if (DateUtil.isTimeDifferYears(params.getBeginTime(), params.getEndTime())) {
				return RestResponse.RestResponseBuilder.createFailBuilder("校正时间不可跨年!").buidler();
			}
			if (!MtConstant.REPAIR_DATA_PWD.equals(MD5Util.string2MD5(params.getPwd()))) {
				// 检验密码  密码:SEGI-WH-trans!@#
				return RestResponse.RestResponseBuilder.createFailBuilder("密码输入有误!").buidler();
			}
			// 获取登录用户的userId和organId
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			getRepairDataServiceIcePrx().begin_repairTaskReportData(params,
					new Callback_RepairDataServiceIce_repairTaskReportData() {
				@Override
				public void exception(LocalException __ex) {
					logger.warn("数据校正执行异常!", __ex);
				}
				
				@Override
				public void response(RpcRespIce arg) {
					if (!RpcError.SUCCESS.getCode().equals(arg.getCode())) {
						logger.warn("数据校正执行失败!", arg.message);
					}
				}
			});
			// 异步执行 3秒后返回成功
			Thread.sleep(3000);
			Map<String, String> map = new HashMap<>();
			map.put("id", params.getOrganId());
			return RestResponse.RestResponseBuilder.createSuccessBuilder("成功").setResult(map).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,校正报表数据失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,校正报表数据失败").buidler();
		}
	}
	
}
