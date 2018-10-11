package com.segi.uhomecp.medicaltrans.location.rest;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import segi.medicaltrans.base.location.LocationInfoListReturnIce;
import segi.medicaltrans.base.location.LocationInfoPaginatorIce;
import segi.medicaltrans.base.location.MtBuildLocationDetailReturnIce;
import segi.medicaltrans.base.location.MtBuildLocationIceParam;
import segi.medicaltrans.base.location.MtBuildLocationQueryIceParam;
import segi.medicaltrans.base.location.MtLocationManagerServiceIcePrx;
import segi.medicaltrans.location.common.MtLocationInfoIce;
import IceExt.IceClientUtil;
import resp.RpcRespIce;

import com.alibaba.fastjson.JSONObject;
import com.segi.uhomecp.common.constant.RestApiConstant;
import com.segi.uhomecp.common.rest.AbsActionRest;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.medicaltrans.utils.QrCodeUtils;
import com.segi.uhomecp.medicaltrans.utils.ResponseDownloadUtils;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.sso.auth.vo.User;
import com.segi.uhomecp.sso.client.annotation.AdminUserParam;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.web.rest.RestResponse;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.CheckRestParams;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * 医疗运输--位置管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(RestApiConstant.API_PATH+ "/medicaltrans/mtLocationManagerWebRest")
@Api(value = "medicaltrans/mtLocationManagerWebRest", description = "医院位置信息管理")
public class MtLocationManagerWebRest extends AbsActionRest {
	private static final Logger logger = LoggerFactory.getLogger(MtLocationManagerWebRest.class);
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
	 * @discription 位置新增
	 * @author dengdong@segimail.com       
	 * @created 2018年5月9日 下午4:47:37     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "医院位置信息新增", response = String.class, notes = "{<br/>" + 
			"\"organId\":\"项目(医院)Id\",<br/>" +
			"\"buildId\":\"楼栋Id\",<br/>" +
			"\"sid\":\"单元Id\",<br/>" +
			"\"floorId\":\"楼层Id\",<br/>" +
			"\"floorNum\":\"楼层号\",<br/>" +
			"\"locationName\":\"位置名称\",<br/>" +
			"\"houseSpaces\":\"物理空间\",<br/>" +
			"\"floorPositX\":\"位置平面图X坐标\",<br/>" +
			"\"floorPositY\":\"位置平面图Y坐标\",<br/>" +
			"\"locateType\":\"定位方式:1 二维码;2 NFC ;1,2 代表二维码和NFC\",<br/>" +
			"\"mac\":\"mac地址,选择了NFC定位时必填\",<br/>" +
			"\"remark\":\"描述\"<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/saveMtBuildLocation.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse saveMtBuildFloor(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtBuildLocationIceParam params = FastjsonUtils.parseObject(paramJson, MtBuildLocationIceParam.class);
			//必填参数验证
			String fields = "organId, buildId,houseSpaces, floorId,locationName,locateType";
			String messages = "项目(医院)Id, 楼栋Id,物理空间, 楼层Id,位置名称,定位方式";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			fields = "organId, buildId, floorId,sid,floorNum";
			messages = "项目(医院)Id, 楼栋Id, 楼层Id,单元id,楼层号";
			//Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//校验定位方式
			errInfo = checkLocateType(params);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//校验楼层X/Y轴坐标
			errInfo = checkFloorPositXY(params);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			
			//获取当前用户userId
			params.setCurUserId(String.valueOf(user.getUserId()));
			//获取ICE服务
			RpcRespIce rpcResp = this.getMtLocaitonManagerServiceIcePrx().saveMtBuildLocation(params);
			
			if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
				Map<String, String> map = new HashMap<>();
				map.put("id", rpcResp.getData());
				return RestResponse.RestResponseBuilder.createSuccessBuilder(rpcResp.message).setResult(map).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rpcResp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,医院位置信息保存失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,医院位置信息保存失败").buidler();
		}
	} 
	
	/**
	 * @discription 位置详情
	 * @author dengdong@segimail.com       
	 * @created 2018年5月9日 下午4:47:37     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "医院位置详情", response = String.class, notes = "{<br/>" + 
			"\"locationId\":\"位置Id\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryMtBuildLocationDetail.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryMtBuildLocationDetail(@AdminUserParam User user, @RequestParam(value="locationId", required=true) String locationId) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		if (StringUtils.isEmpty(locationId)) {
			return RestResponse.RestResponseBuilder.createFailBuilder("查询位置详情必填参数位置Id缺失!").buidler();
		}
		try {
			MtBuildLocationDetailReturnIce rsp = this.getMtLocaitonManagerServiceIcePrx().queryMtBuildLocationDetail(locationId);
			if (RpcError.SUCCESS.getCode().equals(rsp.code)) {
			    return RestResponse.RestResponseBuilder.createSuccessBuilder("查看位置详情成功!").setResult(rsp).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,查看位置详情失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,查看位置详情失败！").buidler();
		}
	}
	
	/**
	 * @discription 位置编辑
	 * @author dengdong@segimail.com       
	 * @created 2018年5月9日 下午4:47:37     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "医院位置修改", response = String.class, notes = "{<br/>" + 
			"\"locationId\":\"位置Id\",<br/>" +
			"\"locationName\":\"位置名称\",<br/>" +
			"\"houseSpaces\":\"物理空间\",<br/>" +
			"\"floorPositX\":\"位置平面图X坐标\",<br/>" +
			"\"floorPositY\":\"位置平面图Y坐标\",<br/>" +
			"\"locateType\":\"定位方式:1 二维码;2 NFC ;1,2 代表二维码和NFC\",<br/>" +
			"\"mac\":\"mac地址,选择了NFC定位时必填\",<br/>" +
			"\"remark\":\"描述\"<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/updateMtBuildLocation.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse updateMtBuildLocation(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtBuildLocationIceParam params = FastjsonUtils.parseObject(paramJson, MtBuildLocationIceParam.class);
			//必填参数验证
			String fields = "locationId,locationName,houseSpaces,locateType";
			String messages = "位置Id,位置名称,物理空间,定位方式";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			fields = "locationId";
			messages = "位置Id";
			//Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//校验定位方式
			errInfo = checkLocateType(params);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//校验楼层X/Y轴坐标
			errInfo = checkFloorPositXY(params);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//获取当前用户userId
			params.setCurUserId(String.valueOf(user.getUserId()));
			//获取ICE服务
			RpcRespIce rpcResp = this.getMtLocaitonManagerServiceIcePrx().updateMtBuildLocation(params);
			
			if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
				Map<String, String> map = new HashMap<>();
				map.put("id", rpcResp.getData());
				return RestResponse.RestResponseBuilder.createSuccessBuilder(rpcResp.message).setResult(map).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rpcResp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,医院位置编辑失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,医院位置编辑失败").buidler();
		}
	}
	
	/**
	 * 位置删除接口
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "医院位置删除", response = String.class, notes = "{<br/>" + 
			"\"locationId\":\"位置Id\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/deleteMtBuildLocation.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse deleteMtBuildLocation(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtBuildLocationIceParam params = FastjsonUtils.parseObject(paramJson, MtBuildLocationIceParam.class);
			//必填参数验证
			String fields = "locationId";
			String messages = "位置Id";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			fields = "locationId";
			messages = "位置Id";
			//Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//获取当前用户userId
			params.setCurUserId(String.valueOf(user.getUserId()));
			//获取ICE服务
			RpcRespIce rpcResp = this.getMtLocaitonManagerServiceIcePrx().deleteMtBuildLocation(params);
			
			if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
				Map<String, String> map = new HashMap<>();
				map.put("delLoopName", rpcResp.getData());
				return RestResponse.RestResponseBuilder.createSuccessBuilder(rpcResp.message).setResult(map).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rpcResp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,医院位置删除失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,医院位置删除失败").buidler();
		}
	}
	
	/**
	 * 根据楼层展示位置列表
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "位置分页展示(楼层)", response = String.class, notes = "{<br/>" + 
			"\"organId\":\"项目Id\",<br/>" +
			"\"floorId\":\"楼层Id\",<br/>" +
			"\"pageNo\":\"页码\",<br/>" +
			"\"pageLength\":\"每页记录数\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryLocationPageByFloorId.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryLocationPageByFloorId(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtBuildLocationIceParam params = FastjsonUtils.parseObject(paramJson, MtBuildLocationIceParam.class);
			//必填参数验证
			String fields = "organId,floorId,pageNo,pageLength";
			String messages = "项目Id,楼层Id,页码,每页记录数";
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
			LocationInfoPaginatorIce rsp = this.getMtLocaitonManagerServiceIcePrx().queryLocationPageByFloorId(params);
			return RestResponse.RestResponseBuilder.createBuilder(rsp.code, rsp.message)
					.setResult(rsp.paginator, rsp.resultList.toArray()).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,查询位置失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,查询位置失败").buidler();
		}
	}
	
	/**
	 * 根据组织机构和楼层显示位置信息
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "位置列表展示", response = String.class, notes = "{<br/>" + 
			"\"organId\":\"组织机构Id\",<br/>" +
			"\"floorId\":\"楼层Id\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryLocationListByFloorId.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryLocationListByFloorId(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try{
			//前端传进来的json转对象
			MtBuildLocationIceParam params = FastjsonUtils.parseObject(paramJson, MtBuildLocationIceParam.class);
			//必填参数验证
			String fields = "organId,floorId";
			String messages = "组织机构Id,楼层Id";
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
			LocationInfoListReturnIce rsp = this.getMtLocaitonManagerServiceIcePrx().queryLocationListByFloorId(params);
			return RestResponse.RestResponseBuilder.createBuilder(rsp.code, rsp.message)
					.setResult(rsp.resultList.toArray()).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,查询位置失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,查询位置失败").buidler();
		}
	}
	
	/**
	 * 位置分页列表展示
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "位置分页列表展示（组织机构和位置名称）", response = String.class, notes = "{<br/>" + 
			"\"organId\":\"组织机构Id\",<br/>" +
			"\"locationName\":\"位置名称\",<br/>" +
			"\"status\":\"状态\",<br/>" +
			"\"pageNo\":\"页码\",<br/>" +
			"\"pageLength\":\"每页记录数\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryLocationPageByOrgName.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryLocationPageByOrgName(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtBuildLocationQueryIceParam params = FastjsonUtils.parseObject(paramJson, MtBuildLocationQueryIceParam.class);
			//必填参数验证
			String fields = "organId,pageNo,pageLength";
			String messages = "组织机构Id,页码,每页记录数";
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
			LocationInfoPaginatorIce rsp = this.getMtLocaitonManagerServiceIcePrx().queryLocationPageByOrgName(params);
			return RestResponse.RestResponseBuilder.createBuilder(rsp.code, rsp.message)
					.setResult(rsp.paginator, rsp.resultList.toArray()).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,查询位置失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,查询位置失败").buidler();
		}
	}
	
	/**
	 * @discription 校验定位方式
	 * @author dengdong@segimail.com       
	 * @created 2018年5月9日 下午7:40:54     
	 * @param params
	 * @return
	 */
	private String checkLocateType(MtBuildLocationIceParam params) {
		String errInfo = null;
		if (MtConstant.LOCATE_TYPE_QRCODE.equals(params.getLocateType()) 
				|| MtConstant.LOCATE_TYPE_NFC.equals(params.getLocateType())
				|| MtConstant.LOCATE_TYPE_QRCODE_AND_NFC.equals(params.getLocateType())) {
			if (MtConstant.LOCATE_TYPE_NFC.equals(params.getLocateType()) 
					&& StringUtils.isEmpty(params.getMac())
					|| MtConstant.LOCATE_TYPE_QRCODE_AND_NFC.equals(params.getLocateType()) 
					&& StringUtils.isEmpty(params.getMac())) {
				errInfo = "定位方式选择了NFC,必须输入MAC地址"; 
			} 
		} else {
			errInfo = "定位方式选择有误"; 
		}
		return errInfo;
	} 
	
	/**
	 * @discription 校验x、y坐标
	 * @author dengdong@segimail.com       
	 * @created 2018年5月9日 下午7:43:20     
	 * @param params
	 * @return
	 */
	private String checkFloorPositXY(MtBuildLocationIceParam params) {
		String fields;
		String messages;
		String errInfo = null;
		if (StringUtils.isNotEmpty(params.getFloorPositX())
				&& StringUtils.isNotEmpty(params.getFloorPositY())) {
			fields = "floorPositX, floorPositY";
			messages = "科室所在楼层平面图X坐标, 科室所在楼层平面图Y坐标";
			errInfo = CheckRestParams.checkDecimal(params, fields, messages);
			if (null != errInfo) {
				return errInfo;
			}
		}
		return errInfo;
	}
	
	/**
	 * 根据项目ID位置Id返回二维码图片
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "根据项目ID位置Id返回二维码图片", response = String.class, notes = "根据项目ID位置Id返回二维码图片")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryQrCodePic.json", method = { RequestMethod.GET })
	@ResponseBody
	public void queryQrCodePic(@AdminUserParam User user, HttpServletResponse response,
            @RequestParam(value = "organId", required = true) String organId,
            @RequestParam(value = "locationId", required = false) String locationId) {
		File file = null;
		try {
			//前端传进来的json转对象
			MtBuildLocationIceParam params = new MtBuildLocationIceParam();
			params.setOrganId(organId);
			params.setLocationId(locationId);
			//必填参数验证
			String fields = "organId,locationId";
			String messages = "组织机构Id,位置Id";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return ;
			}
			List<Integer> locationIdList = new ArrayList<Integer>();
			locationIdList.add(Integer.valueOf(params.getLocationId()));
			List<MtLocationInfoIce> locationList = MtCommonServiceUtils.getLocationInfoList(Integer.valueOf(params.getOrganId()),locationIdList);
			if(AppUtils.isNotEmpty(locationList)){
				MtLocationInfoIce mtBuildLocation = locationList.get(0);
				if(mtBuildLocation!=null){
					JSONObject object1 = new JSONObject();
					object1.put("locationId", mtBuildLocation.getLocationId());
					object1.put("locationName", mtBuildLocation.getLocationName());
					JSONObject object = new JSONObject();
					object.put("type", "MT_LOCATION_INFO");
					object.put("code", object1);
					String value = FastjsonUtils.toJsonString(object);
					String path = MtLocationManagerWebRest.class.getClassLoader().getResource("/").getPath() ;
					String name = String.valueOf(mtBuildLocation.getLocationId())+".jpg";
					QrCodeUtils.encode(value, null, path,name, true);  
					file =new File(path+name);
					ResponseDownloadUtils.downloadAndDelete(response, file, name);
				}
			}
		} catch (Exception e) {
			logger.warn("根据项目ID位置Id返回二维码图片", e);
		}
	}
	
	/**
	 * 位置信息分页展示（组织机构，名称）带默认
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "位置信息分页展示（组织机构，名称）带默认", response = String.class, notes = "{<br/>" + 
			"\"organId\":\"组织机构Id\",<br/>" +
			"\"locationName\":\"位置名称\",<br/>" +
			"\"defaultLocationId\":\"默认位置\",<br/>" +
			"\"pageNo\":\"页码\",<br/>" +
			"\"pageLength\":\"每页记录数\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryLocationPageByOrgNameDefault.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryLocationPageByOrgNameDefault(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtBuildLocationIceParam params = FastjsonUtils.parseObject(paramJson, MtBuildLocationIceParam.class);
			//必填参数验证
			String fields = "organId,defaultLocationId,pageNo,pageLength";
			String messages = "组织机构Id,默认位置,页码,每页记录数";
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
			LocationInfoPaginatorIce rsp = this.getMtLocaitonManagerServiceIcePrx().queryLocationPageByOrgNameDefault(params);
			return RestResponse.RestResponseBuilder.createBuilder(rsp.code, rsp.message)
					.setResult(rsp.paginator, rsp.resultList.toArray()).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,查询位置失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,查询位置失败").buidler();
		}
	}
	
}
