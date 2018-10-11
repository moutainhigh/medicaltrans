package com.segi.uhomecp.medicaltrans.settingmanage.rest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import resp.RpcRespIce;
import segi.medicaltrans.base.userhosp.UserHospIce;
import segi.medicaltrans.base.userhosp.UserHospRelReturnPadIce;
import segi.medicaltrans.base.userhosp.UserHospServiceIcePrx;
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
@RequestMapping("/rest-api/v1/medicaltrans/mtSettingManageWebRest")
@Api(value = "medicaltrans/mtSettingManageWebRest", description = "业务类型")
public class MtSettingManageWebRest extends AbsActionRest{
	private static final Logger logger = LoggerFactory.getLogger(MtSettingManageWebRest.class);

	private UserHospServiceIcePrx getUserHospServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(UserHospServiceIcePrx.class);
	}
	
	/**
	 * 
	 * 类描述: pad用户科室详情
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年4月9日 下午2:48:09
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "用户科室详情", response = String.class, notes = "{<br/>"
            + "}<br/>")
    @ApiImplicitParams({})
    @RequestMapping(value = "/queryUserHospPad.json", method = { RequestMethod.GET })
    @ResponseBody
    public RestResponse queryUserHospPad(@AdminUserParam User user) {
        if (null == user) {
            return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
        }
        try {
        	UserHospIce params = new UserHospIce();
        	params.setUserId(String.valueOf(user.getUserId()));
        	String organId = getOrganId();
			if (StringUtils.isEmpty(organId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder(
						"organId取值为空!").buidler();
			}
        	params.setOrganId(organId);
            // 获取ICE服务
			UserHospRelReturnPadIce rpcResp = this.getUserHospServiceIcePrx().queryHospUserPad(params);
            if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
                return RestResponse.RestResponseBuilder.createSuccessBuilder("查询用户科室详成功!")
						.setResult(rpcResp.getUserHospInfoIce()).buidler();
            }
            return RestResponse.RestResponseBuilder.createFailBuilder(rpcResp.message).buidler();
        } catch (Exception e) {
            logger.warn("系统异常,查询用户科室详情失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,查询用户科室详情失败").buidler();
        }
    }
	
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "根据用户Id以及项目修改科室", response = String.class, notes = "{<br/>"
            + "\"houseId\":\"科室Id\",<br/>"
            + "}<br/>")
    @ApiImplicitParams({})
    @RequestMapping(value = "/updateUserHosp.json", method = { RequestMethod.POST })
    @ResponseBody
    public RestResponse updateUserHosp(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
        if (null == user) {
            return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
        }
        try {
            // 前端传进来的json转对象
        	UserHospIce params = FastjsonUtils.parseObject(paramJson, UserHospIce.class);
        	// 必填参数验证
			String fields = "houseId";
			String messages = "科室Id";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			params.setUserId(String.valueOf(user.getUserId()));
			String organId = getOrganId();
			if (StringUtils.isEmpty(organId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder(
						"organId取值为空!").buidler();
			}
        	params.setOrganId(organId);
            // 获取ICE服务
			RpcRespIce rpcResp = this.getUserHospServiceIcePrx().switchoverUserHosp(params);
            if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
                return RestResponse.RestResponseBuilder.createSuccessBuilder("切换科室成功!")
						.setResult(rpcResp.getMessage()).buidler();
            }
            return RestResponse.RestResponseBuilder.createFailBuilder(rpcResp.message).buidler();
        } catch (Exception e) {
            logger.warn("系统异常,切换科室失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,切换科室失败").buidler();
        }
    }
}
