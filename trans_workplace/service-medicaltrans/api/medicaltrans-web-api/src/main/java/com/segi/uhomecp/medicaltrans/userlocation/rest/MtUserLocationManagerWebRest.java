package com.segi.uhomecp.medicaltrans.userlocation.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import segi.medicaltrans.base.userposit.SumUserLocationInfoIce;
import segi.medicaltrans.base.userposit.UserGraLocationIceParam;
import segi.medicaltrans.base.userposit.UserGraLocationInfoPaginatorIce;
import segi.medicaltrans.base.userposit.UserInfoIce;
import segi.medicaltrans.base.userposit.UserInfoPageParam;
import segi.medicaltrans.base.userposit.UserInfoPaginatorIce;
import segi.medicaltrans.base.userposit.UserPlaLocationIceParam;
import segi.medicaltrans.base.userposit.UserPlaLocationInfoIce;
import segi.medicaltrans.base.userposit.UserPositServiceIcePrx;
import segi.medicaltrans.common.userposit.MtUserPositCommonServiceIcePrx;
import segi.medicaltrans.common.userposit.UserLocationTaskNumParam;
import IceExt.IceClientUtil;
import resp.RpcRespIce;

import com.segi.uhomecp.common.constant.RestApiConstant;
import com.segi.uhomecp.common.rest.AbsActionRest;
import com.segi.uhomecp.sso.auth.vo.User;
import com.segi.uhomecp.sso.client.annotation.AdminUserParam;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.web.rest.RestResponse;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.CheckRestParams;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 人员位置管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(RestApiConstant.API_PATH+ "/medicaltrans/mtUserLocationManagerWebRest")
@Api(value = "medicaltrans/mtUserLocationManagerWebRest", description = "人员位置管理")
public class MtUserLocationManagerWebRest extends AbsActionRest {
	private static final Logger logger = LoggerFactory.getLogger(MtUserLocationManagerWebRest.class);
	private static final String PREFIXPROJECTNAME = RestApiConstant.PREFIXPROJECTNAME;
	
	/**
	 * @discription 获取ice服务
	 * @author dengdong@segimail.com       
	 * @created 2018年5月9日 下午4:47:14     
	 * @return
	 */
	private UserPositServiceIcePrx getUserPositServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(UserPositServiceIcePrx.class);
	}
	
	 /**
     * @discription 人员位置公共service
     * @author yangyh@segimail.com       
     * @created 2018年6月1日 上午10:29:46     
     * @return
     */
    private static MtUserPositCommonServiceIcePrx getMtUserPositCommonServiceIcePrx(){
    	return IceClientUtil.getServicePrxByClass(MtUserPositCommonServiceIcePrx.class);
    }
	
	/**
	 * 人员位置列表分页查询(3D图)
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "人员位置列表分页查询(3D图)", response = String.class, notes = "{<br/>" + 
			"\"organId\":\"项目Id\",<br/>" +
			"\"userName\":\"员工姓名\",<br/>" +
			"\"userWorkNo\":\"员工工号\",<br/>" +
			"\"buildId\":\"楼栋Id\",<br/>" +
			"\"floorId\":\"楼层Id\",<br/>" +
			"\"sid\":\"单元\",<br/>" +
			"\"pageNo\":\"页码\",<br/>" +
			"\"pageLength\":\"每页记录数\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryUserLocationPage.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryUserLocationPage(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			UserGraLocationIceParam params = FastjsonUtils.parseObject(paramJson, UserGraLocationIceParam.class);
			//必填参数验证
			String fields = "organId,pageNo,pageLength";
			String messages = "项目Id,页码,每页记录数";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//获取当前用户userId
			params.setCurUserId(String.valueOf(user.getUserId()));
			//获取ICE服务
			UserGraLocationInfoPaginatorIce rsp = this.getUserPositServiceIcePrx().queryUserLocationPage(params);
			return RestResponse.RestResponseBuilder.createBuilder(rsp.code, rsp.message)
					.setResult(rsp.paginator, rsp.resultList.toArray()).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,查询3D人员位置失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,查询3D人员位置失败").buidler();
		}
	}
	
	@ApiOperation(value = PREFIXPROJECTNAME + "人员位置列表总体查询(3D图)", response = String.class, notes = "{<br/>" + 
			"\"organId\":\"项目Id\",<br/>" +
			"\"userName\":\"员工姓名\",<br/>" +
			"\"userWorkNo\":\"员工工号\",<br/>" +
			"\"buildId\":\"楼栋Id\",<br/>" +
			"\"sid\":\"单元\",<br/>" +
			"\"floorId\":\"楼层Id\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/querySumUserLocation.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse querySumUserLocation(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			UserGraLocationIceParam params = FastjsonUtils.parseObject(paramJson, UserGraLocationIceParam.class);
			//必填参数验证
			String fields = "organId,floorId";
			String messages = "项目Id,楼层Id";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//获取ICE服务
			SumUserLocationInfoIce rsp = this.getUserPositServiceIcePrx().querySumUserLocation(params);
			return RestResponse.RestResponseBuilder.createBuilder(rsp.code, rsp.message)
					.setResult(rsp.result).buidler();
		}catch (Exception e) {
			logger.warn("系统异常,查询位置失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,查询位置失败").buidler();
		}
	}
	
	@ApiOperation(value = PREFIXPROJECTNAME + "人员位置列表查询(2D图)", response = String.class, notes = "{<br/>" + 
			"\"organId\":\"项目Id\",<br/>" +
			"\"buildId\":\"楼栋Id\",<br/>" +
			"\"sid\":\"单元Id\",<br/>" +
			"\"floorId\":\"楼层Id\",<br/>" +
			"\"status\":\"状态\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryUserPlaLocation.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryUserPlaLocation(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			UserPlaLocationIceParam params = FastjsonUtils.parseObject(paramJson, UserPlaLocationIceParam.class);
			//必填参数验证
			String fields = "organId";
			String messages = "项目Id";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			
			//获取ICE服务
			UserPlaLocationInfoIce rsp = this.getUserPositServiceIcePrx().queryUserPlaLocation(params);
			return RestResponse.RestResponseBuilder.createBuilder(rsp.code, rsp.message)
					.setResult(rsp.resultList.toArray()).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,查询位置失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,查询位置失败").buidler();
		}
	}
	
	@ApiOperation(value = PREFIXPROJECTNAME + "根据部门员工姓名员工工号查询员工信息", response = String.class, notes = "{<br/>"
			+ "\"organId\":\"项目(医院)Id\",<br/>"
			+ "\"userName\":\"员工姓名\",<br/>"
			+ "\"userNo\":\"员工工号\",<br/>"
			+ "\"pageNo\":\"页码\",<br/>"
			+ "\"pageLength\":\"每页记录数\",<br/>"
			+ "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryUserInfoPage.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryUserInfoPage(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			// 前端传进来的json转对象
			UserInfoPageParam params = FastjsonUtils.parseObject(paramJson, UserInfoPageParam.class);
			// 必填参数验证
			String fields = "organId,groupId, pageNo, pageLength";
			String messages = "项目(医院)Id,分组id,页码, 每页记录数";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			// Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			// 获取ICE服务
			UserInfoPaginatorIce rsp = this.getUserPositServiceIcePrx().queryUserInfoPage(params);
			Map<String, List<UserInfoIce>> map = new HashMap<>();
			map.put("resultList", rsp.resultList);
			return RestResponse.RestResponseBuilder.createBuilder(rsp.code, rsp.msg)
					.setResult(map).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,员工信息分页查询失败!", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,员工信息分页查询失败!").buidler();
		}
	}
	
	
	@ApiOperation(value = PREFIXPROJECTNAME + "更新人员运送单未完成数", response = String.class, notes = "{<br/>"
			+ "\"organId\":\"项目(医院)Id\",<br/>"
			+ "\"userIds\":\"员工集合\",<br/>"
			+ "\"unTaskNum\":\"未完成工单数\",<br/>"
			+ "\"runTaskNum\":\"正在进行工单数\",<br/>"
			+ "\"executeDate\":\"触发时间\",<br/>"
			+ "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/updateUserPositInfo.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse updateUserPositInfo(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			// 前端传进来的json转对象
			UserLocationTaskNumParam params = FastjsonUtils.parseObject(paramJson, UserLocationTaskNumParam.class);
			// 必填参数验证
			String fields = "organId, userIds, unTaskNum,runTaskNum,executeDate";
			String messages = "项目(医院)Id, 员工集合, 未完成工单数,正在进行工单数,触发时间";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			Integer organId = Integer.valueOf(params.getOrganId());
			String[] userIdArr = params.getUserIds().split(",");
			List<Integer> userIds = new ArrayList<Integer>();
			for (String str : userIdArr) {
				userIds.add(Integer.valueOf(str));
			}
			short unTaskNum = Short.parseShort(params.getUnTaskNum());
			short runTaskNum = Short.parseShort(params.getRunTaskNum());
			// 获取ICE服务
			RpcRespIce rsp = getMtUserPositCommonServiceIcePrx().updateUserPositInfo(organId, userIds, unTaskNum, runTaskNum,params.getExecuteDate(),null);
			return RestResponse.RestResponseBuilder.createBuilder(rsp.code, rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("更新人员运送单未完成数异常!", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,更新人员运送单未完成数异常!").buidler();
		}
	}
	
}
