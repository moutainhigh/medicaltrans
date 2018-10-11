package com.segi.uhomecp.medicaltrans.transType.rest;

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

import segi.medicaltrans.base.transtype.TransTypeIce;
import segi.medicaltrans.base.transtype.TransTypeRetPageIce;
import segi.medicaltrans.base.transtype.TransTypeServiceIcePrx;
import IceExt.IceClientUtil;

import com.segi.uhomecp.common.constant.RestApiConstant;
import com.segi.uhomecp.common.rest.AbsActionRest;
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
@RequestMapping("/rest-api/v1/medicaltrans/transTypeHisRest")
@Api(value = "medicaltrans/transTypeHisRest", description = "运送类型")
public class MtTransTypeHisRest  extends AbsActionRest{

	private static final Logger logger = LoggerFactory.getLogger(MtTransTypeHisRest.class);

	private TransTypeServiceIcePrx getTransTypeServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(TransTypeServiceIcePrx.class);
	}
	
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "运送类型列表", response = String.class, notes = "{<br/>"
			+ "\"transTypeParentCode\":\"一级分类编码（大类）\",<br/>"
			+ "}<br/>")
    @ApiImplicitParams({})
    @RequestMapping(value = "/transTypeList.json", method = { RequestMethod.POST })
    @ResponseBody
    public RestResponse transTypeList(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"用户未登录,请重新登录!").buidler();
		}
		try {
			// 前端传进来的json转对象
			TransTypeIce params = FastjsonUtils.parseObject(paramJson,
					TransTypeIce.class);
			// 必填参数验证
			String fields = "transTypeParentCode";
			String messages = "一级分类编码(大类)";
			String errInfo = CheckRestParams.checkEmpty(params, fields,
					messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(
						errInfo).buidler();
			}
			String organId = getOrganId();
			if (StringUtils.isEmpty(organId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder(
						"organId取值为空!").buidler();
			}
			params.setOrganId(organId);
			// 获取ICE服务
			TransTypeRetPageIce rpcResp = this.getTransTypeServiceIcePrx()
					.transTypeList(params);
			if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
				Map<String ,Object> resMap = new HashMap<>();
				resMap.put("resultList", rpcResp.transTypeListIce);
				return RestResponse.RestResponseBuilder.createBuilder(rpcResp.code, rpcResp.message)
						.setResult(resMap).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(
					rpcResp.message).buidler();
		} catch (Exception e) {                                                     
			logger.warn("系统异常,查询运送类型列表失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"系统异常,查询运送类型列表失败").buidler();
		}
	}
}
