package com.segi.uhomecp.medicaltrans.mttask.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import segi.medicaltrans.base.location.LocationInfoPaginatorIce;
import segi.medicaltrans.base.location.MtBuildLocationIceParam;
import segi.medicaltrans.base.location.MtLocationManagerServiceIcePrx;
import IceExt.IceClientUtil;

import com.segi.uhomecp.common.constant.RestApiConstant;
import com.segi.uhomecp.common.rest.AbsActionRest;
import com.segi.uhomecp.sso.auth.vo.User;
import com.segi.uhomecp.sso.client.annotation.AdminUserParam;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.web.rest.RestResponse;
import com.segi.uhomecp.wh.common.utils.CheckRestParams;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(RestApiConstant.API_PATH + "/medicaltrans/mtBuilldHouseAppRest")
@Api(value = "medicaltrans/mtBuilldHouseAppRest", description = "医院运送管理(pad端)")
public class MtBuilldHouseAppRest extends AbsActionRest {
	private static final Logger logger = LoggerFactory.getLogger(MtBuilldHouseAppRest.class);
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
	 * 位置分页列表展示
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "位置分页列表展示（组织机构和位置名称）", response = String.class, notes = "{<br/>" + 
			"\"locationName\":\"位置名称\",<br/>" +
			"\"pageNo\":\"页码\",<br/>" +
			"\"pageLength\":\"每页记录数\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryHousePageByOrganId.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryHousePageByOrganId(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtBuildLocationIceParam params = FastjsonUtils.parseObject(paramJson, MtBuildLocationIceParam.class);
			//必填参数验证
			String fields = "pageNo,pageLength";
			String messages = "页码,每页记录数";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			// 从头部获取organId
			params.setOrganId(this.getOrganId());
			//获取ICE服务
			LocationInfoPaginatorIce rsp = this.getMtLocaitonManagerServiceIcePrx().queryLocationPageByFloorId(params);
			return RestResponse.RestResponseBuilder.createBuilder(rsp.code, rsp.message)
					.setResult(rsp.paginator, rsp.resultList.toArray()).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,查询位置失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,查询位置失败").buidler();
		}
	}
}
