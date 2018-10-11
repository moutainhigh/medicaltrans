package com.segi.uhomecp.medicaltrans.userStatus.rest;

import javax.servlet.http.HttpServletResponse;

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
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.sso.auth.vo.User;
import com.segi.uhomecp.sso.client.annotation.AdminUserParam;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.web.rest.RestResponse;
import com.segi.uhomecp.wh.common.utils.CheckRestParams;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

import IceExt.IceClientUtil;
import segi.medicaltrans.base.userstatus.UserStatusIce;
import segi.medicaltrans.base.userstatus.UserStatusReturnIce;
import segi.medicaltrans.base.userstatus.UserStatusServiceIcePrx;

/**
 * 医疗运输--员工状态切换
 * @author Administrator
 *
 */
@Controller
@RequestMapping(RestApiConstant.API_PATH+ "/medicaltrans/mtUserStatusAppRest")
@Api(value = "medicaltrans/mtUserStatusAppRest", description = "员工状态切换")
public class MtUserStatusAppRest extends AbsActionRest {
	private static final Logger logger = LoggerFactory.getLogger(MtUserStatusAppRest.class);
	private static final String PREFIXPROJECTNAME = RestApiConstant.PREFIXPROJECTNAME;
	
	private UserStatusServiceIcePrx  getUserStatusServiceIcePrx() {
		return IceClientUtil.getServicePrxByClass(UserStatusServiceIcePrx.class);
	}
	
	
	@ApiOperation(value = PREFIXPROJECTNAME + "切换员工上下班状态", response = String.class, notes = "{<br/>"
			+ "\"organId\": \"项目组织Id\",<br/>"  
			+ "\"status\": \"上下班状态：1是上班，0是下班\",<br/>"
			+ "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/updateStausByUser.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse updateLocatePosit(@AdminUserParam User user,
			@RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			UserStatusIce params = FastjsonUtils.parseObject(paramJson, UserStatusIce.class);
			//必填参数验证
			String fields = "organId,status";
			String messages = "项目Id,状态";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			String status = params.getStatus();
			if(!(status.equals("1") || status.equals("0"))){
				return RestResponse.RestResponseBuilder.createFailBuilder("status只能为1或者0").buidler();
			}
			//获取当前用户userId
			params.setUserId((String.valueOf(user.getUserId())));
			//获取ICE服务
			UserStatusReturnIce rsp = this.getUserStatusServiceIcePrx().updateStausByUser(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.code)) {
				return RestResponse.RestResponseBuilder.createBuilder(rsp.code, rsp.message)
						.setResult(rsp.getUserStatusRetIce()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(
					rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,切换员工状态失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"系统异常,切换员工状态失败").buidler();
		}
	}
	
	@ApiOperation(value = PREFIXPROJECTNAME + "根据当前的登陆用户查询上下班状态", response = String.class, notes = "{<br/>"
            + "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/getStatusByUser.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse getStatusByUser(@AdminUserParam User user, HttpServletResponse response,
    	    @RequestParam(value = "organId", required = true) String organId) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			if (null == organId) {
				return RestResponse.RestResponseBuilder.createFailBuilder("项目id为空").buidler();
			}
			UserStatusIce userStatusIce = new UserStatusIce();
			userStatusIce.setUserId((String.valueOf(user.getUserId())));
			userStatusIce.setOrganId(organId);
			//获取ICE服务
			UserStatusReturnIce rsp = this.getUserStatusServiceIcePrx().getStatusByUser(userStatusIce);
			if (RpcError.SUCCESS.getCode().equals(rsp.code)) {
				return RestResponse.RestResponseBuilder.createBuilder(rsp.code, rsp.message)
						.setResult(rsp.getUserStatusRetIce()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(
					rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,查询员工状态失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"系统异常,查询员工状态失败").buidler();
		}
	}
	
}
