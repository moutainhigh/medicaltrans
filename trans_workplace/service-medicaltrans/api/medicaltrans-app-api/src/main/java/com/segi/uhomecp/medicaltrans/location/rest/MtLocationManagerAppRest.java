package com.segi.uhomecp.medicaltrans.location.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import segi.medicaltrans.base.location.MtBuildLocationDetailReturnIce;
import segi.medicaltrans.base.location.MtBuildLocationIceParam;
import segi.medicaltrans.base.location.MtLocationManagerServiceIcePrx;

/**
 * 医疗运输--位置管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(RestApiConstant.API_PATH+ "/medicaltrans/MtLocationManagerAppRest")
@Api(value = "medicaltrans/MtLocationManagerAppRest", description = "医院位置信息管理")
public class MtLocationManagerAppRest extends AbsActionRest {
	private static final Logger logger = LoggerFactory.getLogger(MtLocationManagerAppRest.class);
	private static final String PREFIXPROJECTNAME = RestApiConstant.PREFIXPROJECTNAME;
	
	/**
	 * @discription 获取ice服务
	 * @author dengdong@segimail.com       
	 * @created 2018年5月9日 下午4:47:14     
	 * @return
	 */
	private MtLocationManagerServiceIcePrx getMtLocaitonManagerServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(MtLocationManagerServiceIcePrx.class);
	}
 
	
	/**
	 * @discription 位置详情(根据项目id和mac)
	 * @author dengdong@segimail.com       
	 * @created 2018年5月9日 下午4:47:37     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "根据项目id和mac地址查位置信息", response = String.class, notes = "{<br/>" + 
			"\"organId\":\"项目Id\",<br/>" +
			"\"mac\":\"mac地址\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/getLocationInfoByMac.json", method = { RequestMethod.POST  })
	@ResponseBody
	public RestResponse getLocationInfoByMac(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtBuildLocationIceParam params = FastjsonUtils.parseObject(paramJson, MtBuildLocationIceParam.class);
			//必填参数验证
			String fields = "organId,mac";
			String messages = "组织机构Id,mac地址";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			fields = "organId";
			messages = "组织机构Id";
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			MtBuildLocationDetailReturnIce rsp = this.getMtLocaitonManagerServiceIcePrx().getLocationInfoByMac(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.code)) {
				/*Map<String, MtBuildLocationDetailIce> dataMap = new HashMap<>();
				dataMap.put("data", rsp.getMtBuildLocationDetailIce());*/
				return RestResponse.RestResponseBuilder.createSuccessBuilder("查看位置详情成功!").setResult(rsp.getMtBuildLocationDetailIce()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,查看位置详情失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,查看位置详情失败！").buidler();
		}
	}
	

	
}
