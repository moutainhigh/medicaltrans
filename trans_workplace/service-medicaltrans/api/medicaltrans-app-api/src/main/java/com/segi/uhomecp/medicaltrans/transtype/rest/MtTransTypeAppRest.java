package com.segi.uhomecp.medicaltrans.transtype.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import segi.medicaltrans.base.transtype.TransTypeAllListIce;
import segi.medicaltrans.base.transtype.TransTypeIce;
import segi.medicaltrans.base.transtype.TransTypeOprGuideRetIce;
import segi.medicaltrans.base.transtype.TransTypeServiceIcePrx;
import IceExt.IceClientUtil;

import com.segi.uhomecp.common.constant.RestApiConstant;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.sso.auth.vo.User;
import com.segi.uhomecp.sso.client.annotation.AdminUserParam;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.web.rest.RestResponse;
import com.segi.uhomecp.wh.common.utils.CheckRestParams;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/rest-api/v1/medicaltrans/transTypeAppRest")
@Api(value = "medicaltrans/transTypeAppRest", description = "业务类型")
public class MtTransTypeAppRest {
	private static final Logger LOGGER = LoggerFactory.getLogger(MtTransTypeAppRest.class);

	private TransTypeServiceIcePrx getTransTypeServiceIcePrx() {
		return IceClientUtil.getServicePrxByClass(TransTypeServiceIcePrx.class);
	}
	
	/**
	 * 
	 * 类描述: 运送类型列表展示
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月2日 下午4:55:20
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "运送类型列表展示", response = String.class, notes = "{<br/>"
			+ "\"organId\":\"项目ID\",<br/>" 
            + "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/TransTypeAllList.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse TransTypeAllList(@AdminUserParam User user,
			@RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"用户未登录,请重新登录!").buidler();
		}
		try {
			// 前端传进来的json转对象
			TransTypeIce params = FastjsonUtils.parseObject(paramJson,
					TransTypeIce.class);
			// 必填参数验证
			String fields = "organId";
			String messages = "项目ID";
			String errInfo = CheckRestParams.checkEmpty(params, fields,
					messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(
						errInfo).buidler();
			}
			// 获取ICE服务
			TransTypeAllListIce rpcResp = this.getTransTypeServiceIcePrx()
					.transTypeAllList(params);
			if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
				Map<String, Object> resMap = new HashMap<>();
				resMap.put("resultList", rpcResp.transTypeList);
				return RestResponse.RestResponseBuilder.createBuilder(rpcResp.code, rpcResp.message)
						.setResult(resMap).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(
					rpcResp.message).buidler();
		} catch (Exception e) {
			LOGGER.warn("系统异常,查询运送类型列表失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"系统异常,查询运送类型列表失败").buidler();
		}
	}
	
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "运送类型操作指引", response = String.class, notes = "运送类型操作指引")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryOprGuide.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryOprGuide(@AdminUserParam User user, HttpServletResponse response,
			@RequestParam(value = "organId", required = true) String organId,
			@RequestParam(value = "transTypeId", required = true) String transTypeId) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!")
					.buidler();
		}
		try {
			// 前端传进来的json转对象
			TransTypeIce params = new TransTypeIce();
			params.setOrganId(organId);
			params.setTransTypeId(transTypeId);
			// 获取ICE服务
			TransTypeOprGuideRetIce rpcResp = this.getTransTypeServiceIcePrx()
					.queryOprGuide(params);
			if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
				return RestResponse.RestResponseBuilder.createSuccessBuilder("查询运送类型操作指引成功!")
						.setResult(rpcResp.getTransTypeOprGuideIce()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(
					rpcResp.message).buidler();
		} catch (Exception e) {
			LOGGER.warn("系统异常,查询运送类型操作指引失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"系统异常,查询运送类型操作指引失败").buidler();
		}
	}
}
