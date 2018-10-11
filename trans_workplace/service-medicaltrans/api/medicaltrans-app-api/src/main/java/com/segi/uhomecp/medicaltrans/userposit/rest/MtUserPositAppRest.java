package com.segi.uhomecp.medicaltrans.userposit.rest;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import segi.medicaltrans.base.userposit.UserPositIce;
import segi.medicaltrans.base.userposit.UserPositReturnIce;
import segi.medicaltrans.base.userposit.UserPositServiceIcePrx;
import IceExt.IceClientUtil;

import com.segi.uhomecp.medicaltrans.common.MtApiConstant;
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
@RequestMapping("/rest-api/v1/medicaltrans/mtUserPositAppRest")
@Api(value = "medicaltrans/mtUserPositAppRest", description = "人员位置管理")
public class MtUserPositAppRest {

	private static final Logger logger = LoggerFactory.getLogger(MtUserPositAppRest.class);

	private static final String PREFIXPROJECTNAME = "[" + MtApiConstant.MODEL_NAME + "]";
	
	private UserPositServiceIcePrx getUserPositServiceIcePrx() {
		return IceClientUtil.getServicePrxByClass(UserPositServiceIcePrx.class);
	}
	
	@ApiOperation(value = PREFIXPROJECTNAME + "根据当前登录用户查询最后一次定位和时间", response = String.class, notes = "{<br/>"
            + "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/getLastPositByUser.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse getLastPositByUser(@AdminUserParam User user, HttpServletResponse response,
    	    @RequestParam(value = "organId", required = true) String organId) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"用户未登录,请重新登录!").buidler();
		}
		try {
			UserPositIce params = new UserPositIce();
			params.setUserOrganId(organId);
			params.setUserId(String.valueOf(user.getUserId()));
			// 获取ICE服务
			UserPositReturnIce rpcResp = this.getUserPositServiceIcePrx()
					.getLastPositByUser(params);
			if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
				return RestResponse.RestResponseBuilder.createBuilder(rpcResp.code, rpcResp.message)
						.setResult(rpcResp.getUserPositRetIce()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(
					rpcResp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,根据当前登录用户查询最后一次定位和时间失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"系统异常,根据当前登录用户查询最后一次定位和时间失败").buidler();
		}
	}
	
	@ApiOperation(value = PREFIXPROJECTNAME + "上传当前用户位置信息", response = String.class, notes = "{<br/>"
			+ "\"organId\": \"项目组织Id\",<br/>"  
			+ "\"locationId\": \"二维码数据,使用二维码定位时必填\",<br/>"
			+ "\"mac\": \"mac地址,使用NFC定位时必填\",<br/>"
			+ "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/updateLocatePosit.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse updateLocatePosit(@AdminUserParam User user,
			@RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"用户未登录,请重新登录!").buidler();
		}
		try {
			UserPositIce params = FastjsonUtils.parseObject(paramJson, UserPositIce.class);
			String errInfo = CheckRestParams.checkEmpty(params, "organId", "项目组织Id");
			if (null != errInfo) {
				//非空验证
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (params.getLocationId() == null && params.getMac() == null) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserName(user.getName());
			params.setUserWorkNo(user.getJobNumber());
			UserPositReturnIce rpcResp = getUserPositServiceIcePrx().updateLocatePosit(params);
			if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
				return RestResponse.RestResponseBuilder.createBuilder(rpcResp.code, rpcResp.message)
						.setResult(rpcResp.getUserPositRetIce()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(
					rpcResp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,上传当前用户位置信息", e);
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"系统异常,上传当前用户位置信息").buidler();
		}
	}
}
