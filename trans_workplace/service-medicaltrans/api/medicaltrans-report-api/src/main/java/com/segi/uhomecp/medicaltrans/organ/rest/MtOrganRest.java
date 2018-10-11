package com.segi.uhomecp.medicaltrans.organ.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.segi.uhomecp.common.rest.AbsActionRest;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.sso.auth.vo.User;
import com.segi.uhomecp.sso.client.annotation.AdminUserParam;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.web.rest.RestResponse;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/rest-api/v1/medicaltrans/MtOrganRest")
@Api(value = "medicaltrans/MtOrganRest", description = "医院运送管理")
public class MtOrganRest extends AbsActionRest {
	private static final Logger logger = LoggerFactory.getLogger(MtOrganRest.class);
	
	/**
	 * 医疗组织机构
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = "医疗组织机构", response = String.class, notes = "")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryOrganList.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryHousePageByOrganId(@AdminUserParam User user) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			List<Integer> list = MtCommonServiceUtils.queryOrgan();
			return RestResponse.RestResponseBuilder.createSuccessBuilder().setResult(FastjsonUtils.toJsonString(list)).buidler();
		} catch (Exception e) {
			logger.warn("系统异常", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常").buidler();
		}
	}
	
	
}
