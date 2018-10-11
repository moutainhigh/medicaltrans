package com.segi.uhomecp.medicaltrans.userhosp.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import resp.RpcRespIce;
import segi.medicaltrans.base.userhosp.UserHospIce;
import segi.medicaltrans.base.userhosp.UserHospParamIce;
import segi.medicaltrans.base.userhosp.UserHospRelReturnIce;
import segi.medicaltrans.base.userhosp.UserHospRetPageIce;
import segi.medicaltrans.base.userhosp.UserHospServiceIcePrx;
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
@RequestMapping("/rest-api/v1/medicaltrans/userHospWebRest")
@Api(value = "medicaltrans/userHospWebRest", description = "用户科室")
public class MtUserHospWebRest {
	
	private static final Logger logger = LoggerFactory.getLogger(MtUserHospWebRest.class);

	private UserHospServiceIcePrx getUserHospServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(UserHospServiceIcePrx.class);
	}
	
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "用户科室信息", response = String.class, notes = "{<br/>"
            + "\"userId\":\"员工id\",<br/>" 
            + "}<br/>")
    @ApiImplicitParams({})
    @RequestMapping(value = "/queryUserHospInfo.json", method = { RequestMethod.POST })
    @ResponseBody
    public RestResponse queryUserHospInfo(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
        if (null == user) {
            return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
        }
        try {
            // 前端传进来的json转对象
        	UserHospIce params = FastjsonUtils.parseObject(paramJson, UserHospIce.class);
        	// 必填参数验证
			String fields = "userId";
			String messages = "员工id";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
            // 获取ICE服务
			UserHospRelReturnIce rpcResp = this.getUserHospServiceIcePrx().queryUserHospInfo(params);
            if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
                return RestResponse.RestResponseBuilder.createSuccessBuilder("查询用户科室信息成功!")
						.setResult(rpcResp.getUserHospReturnIce()).buidler();
            }
            return RestResponse.RestResponseBuilder.createFailBuilder(rpcResp.message).buidler();
        } catch (Exception e) {
            logger.warn("系统异常,查询用户科室信息失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,查询用户科室信息失败").buidler();
        }
    }
	
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "用户科室信息保存", response = String.class, notes = "{<br/>"
            + "\"userId\":\"员工id\",<br/>" 
            + "\"organList\": [{<br/>"
    		+ "\"organId\":\"项目id\",<br/>"
    		+ "\"houseId\":\"科室id\",<br/>"
    		+ "},<br/>"
    		+ "{<br/>"
    		+ "\"organId\": \"项目id\",<br/>" 
    		+ "\"houseId\":\"科室id\",<br/>"
    		+ "}]<br/>"
            + "}<br/>")
    @ApiImplicitParams({})
    @RequestMapping(value = "/saveUserHospInfo.json", method = { RequestMethod.POST })
    @ResponseBody
    public RestResponse saveUserHospInfo(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
        if (null == user) {
            return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
        }
        try {
            // 前端传进来的json转对象
        	UserHospParamIce params = FastjsonUtils.parseObject(paramJson, UserHospParamIce.class);
        	// 必填参数验证
			String fields = "userId";
			String messages = "员工id";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			// 设置修改人
			params.setUpdateBy(String.valueOf(user.getUserId()));
            // 获取ICE服务
			RpcRespIce rpcResp = this.getUserHospServiceIcePrx().saveUserHospInfo(params);
            if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
                return RestResponse.RestResponseBuilder.createSuccessBuilder("用户科室信息保存成功!")
						.setResult(rpcResp.getMessage()).buidler();
            }
            return RestResponse.RestResponseBuilder.createFailBuilder(rpcResp.message).buidler();
        } catch (Exception e) {
            logger.warn("系统异常,用户科室信息保存失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,用户科室信息保存失败").buidler();
        }
    }
	
	
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "用户科室分页", response = String.class, notes = "{<br/>"
            + "\"organId\":\"项目id\",<br/>" 
            + "\"userName\":\"员工姓名\",<br/>" 
            + "\"tel\":\"员工手机号\",<br/>" 
            + "\"userNo\":\"员工工号\",<br/>" 
            + "\"pageNo\":\"页码\",<br/>" 
            + "\"pageLength\":\"每页记录数\",<br/>" 
            + "}<br/>")
    @ApiImplicitParams({})
    @RequestMapping(value = "/queryUserHospPage.json", method = { RequestMethod.POST })
    @ResponseBody
    public RestResponse queryUserHospPage(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
        if (null == user) {
            return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
        }
        try {
            // 前端传进来的json转对象
        	UserHospIce params = FastjsonUtils.parseObject(paramJson, UserHospIce.class);
        	// 必填参数验证
			String fields = "organId, pageNo, pageLength";
			String messages = "项目id, 页码, 每页记录数";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
            // 获取ICE服务
			UserHospRetPageIce rpcResp = this.getUserHospServiceIcePrx().queryUserHospPage(params);
            if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
                return RestResponse.RestResponseBuilder.createSuccessBuilder("用户科室分页查询成功!")
						.setResult(rpcResp.getPaginator(), rpcResp.getResultList().toArray()).buidler();
            }
            return RestResponse.RestResponseBuilder.createFailBuilder(rpcResp.message).buidler();
        } catch (Exception e) {
            logger.warn("系统异常,用户科室分页查询失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,用户科室分页查询失败").buidler();
        }
    }
}
