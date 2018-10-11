package com.segi.uhomecp.medicaltrans.spacemanage.rest;

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

import segi.medicaltrans.base.spacemanage.BuildDetailReturnIce;
import segi.medicaltrans.base.spacemanage.BuildSimpleIce;
import segi.medicaltrans.base.spacemanage.BuildSimpleRetIce;
import segi.medicaltrans.base.spacemanage.BuildSimpleReturnIce;
import segi.medicaltrans.base.spacemanage.FloorDetailReturnIce;
import segi.medicaltrans.base.spacemanage.FloorSimpleReturnIce;
import segi.medicaltrans.base.spacemanage.HouseInfoIce;
import segi.medicaltrans.base.spacemanage.HouseInfoReturnIce;
import segi.medicaltrans.base.spacemanage.PositionIce;
import segi.medicaltrans.base.spacemanage.PositionRetIce;
import segi.medicaltrans.base.spacemanage.SpaceManageServiceIcePrx;
import segi.medicaltrans.base.spacemanage.UnitDetailReturnIce;
import segi.medicaltrans.base.spacemanage.UnitSimpleReturnIce;
import IceExt.IceClientUtil;

import com.segi.uhomecp.common.constant.RestApiConstant;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.sso.auth.vo.User;
import com.segi.uhomecp.sso.client.annotation.AdminUserParam;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.web.rest.RestResponse;
import com.segi.uhomecp.wh.common.utils.CheckRestParams;
import com.segi.uhomecp.wh.common.utils.NumberUtils;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * @ClassName:  MtSpaceManageWebRest   
 * @Description:空间管理高速查询Api 
 * @author: zhaoqing
 * @date:   2018年5月21日 下午7:11:43
 */
@Controller
@RequestMapping("/rest-api/v1/medicaltrans/mtSpaceManageWebRest")
@Api(value = "medicaltrans/mtSpaceManageWebRest", description = "空间管理")
public class MtSpaceManageWebRest {
	
	private static final Logger logger = LoggerFactory.getLogger(MtSpaceManageWebRest.class);

	private SpaceManageServiceIcePrx getSpaceManageServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(SpaceManageServiceIcePrx.class);
	}
	
	/**
	 * 
	 * 类描述: 0501通过项目ID查询所有楼栋简单信息
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月16日 上午10:29:38
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "查询所有楼栋简单信息", response = String.class, notes = "通过项目ID查询所有楼栋简单信息")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryBuildByCommID.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryBuildByCommID(@AdminUserParam User user, 
            @RequestParam(value = "communityId", required = true) String communityId) {
		if (null == user) {
            return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
        }
		try {
			if (StringUtils.isEmpty(communityId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder("项目ID不能为空!").buidler();
			}
			//Number类型格式验证
			if (!NumberUtils.isDigits(communityId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder("项目ID不是数字类型!").buidler();
			}
			// 获取ICE服务
			BuildSimpleRetIce resource = this.getSpaceManageServiceIcePrx().queryBuildByCommID(communityId);
			if (RpcError.SUCCESS.getCode().equals(resource.code)) {
				Map<String, List<BuildSimpleIce>> map = new HashMap<>();
				map.put("resultList", resource.getResultList());
                return RestResponse.RestResponseBuilder.createSuccessBuilder("通过项目ID查询所有楼栋简单信息成功!")
						.setResult(map).buidler();
            }
            return RestResponse.RestResponseBuilder.createFailBuilder(resource.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过项目ID查询所有楼栋简单信息失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	/**
	 * 
	 * 类描述: 0502根据楼栋id查询楼栋简单信息
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月16日 下午2:44:07
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "根据楼栋ID查询楼栋简单信息", response = String.class, notes = "根据楼栋id查询楼栋简单信息")
	@ApiImplicitParams({})
	@RequestMapping(value = "/getBuildSimpleInfo.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse getBuildSimpleInfo(@AdminUserParam User user, HttpServletResponse response,
            @RequestParam(value = "buildId", required = true) String buildId) {
		if (null == user) {
            return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
        }
		try {
			if (StringUtils.isEmpty(buildId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder("楼栋ID不能为空!").buidler();
			}
			//Number类型格式验证
			if (!NumberUtils.isDigits(buildId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder("楼栋ID不是数字类型!").buidler();
			}
			// 获取ICE服务
			BuildSimpleReturnIce resource = this.getSpaceManageServiceIcePrx().getBuildSimpleInfo(buildId);
			if (RpcError.SUCCESS.getCode().equals(resource.code)) {
                return RestResponse.RestResponseBuilder.createSuccessBuilder("查询楼栋简单信息成功!")
						.setResult(resource.getData()).buidler();
            }
            return RestResponse.RestResponseBuilder.createFailBuilder(resource.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,查询楼栋简单信息失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	/** 
	 * 类描述: 0503根据单元id查询单元简单信息
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月16日 下午2:48:37
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "根据单元id查询单元简单信息", response = String.class, notes = "根据单元id查询单元简单信息")
	@ApiImplicitParams({})
	@RequestMapping(value = "/getUnitSimpleInfo.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse getUnitSimpleInfo(@AdminUserParam User user, HttpServletResponse response,
            @RequestParam(value = "unitId", required = true) String unitId) {
		if (null == user) {
            return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
        }
		try {
			if (StringUtils.isEmpty(unitId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder("单元ID不能为空!").buidler();
			}
			//Number类型格式验证
			if (!NumberUtils.isDigits(unitId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder("单元ID不是数字类型!").buidler();
			}
			// 获取ICE服务
			UnitSimpleReturnIce resource = this.getSpaceManageServiceIcePrx().getUnitSimpleInfo(unitId);
			if (RpcError.SUCCESS.getCode().equals(resource.code)) {
                return RestResponse.RestResponseBuilder.createSuccessBuilder("查询单元简单信息成功!")
						.setResult(resource.getData()).buidler();
            }
            return RestResponse.RestResponseBuilder.createFailBuilder(resource.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,查询单元简单信息失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	/**
	 * 
	 * 类描述: 0504通过楼层ID查询楼层简单信息
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月16日 下午2:55:46
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "通过楼层ID查询楼层简单信息", response = String.class, notes = "通过楼层ID查询单元简单信息")
	@ApiImplicitParams({})
	@RequestMapping(value = "/getFloorInfoByFloorId.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse getFloorInfoByFloorId(@AdminUserParam User user, HttpServletResponse response,
            @RequestParam(value = "floorId", required = true) String floorId) {
		if (null == user) {
            return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
        }
		try {
			if (StringUtils.isEmpty(floorId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder("楼层ID不能为空!").buidler();
			}
			//Number类型格式验证
			if (!NumberUtils.isDigits(floorId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder("楼层ID不是数字类型!").buidler();
			}
			// 获取ICE服务
			FloorSimpleReturnIce resource = this.getSpaceManageServiceIcePrx().getFloorInfoByFloorId(floorId);
			if (RpcError.SUCCESS.getCode().equals(resource.code)) {
                return RestResponse.RestResponseBuilder.createSuccessBuilder("通过楼层ID查询楼层简单信息成功!")
						.setResult(resource.getData()).buidler();
            }
            return RestResponse.RestResponseBuilder.createFailBuilder(resource.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过楼层ID查询楼层简单信息失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	/**
	 * 
	 * 类描述: 0505通过空间/位置ID查询下一节点信息
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月16日 下午3:08:01
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "通过空间/位置ID查询下一节点信息", response = String.class, notes = "通过空间/位置ID查询下一节点信息")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryPositionListByParId.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryPositionListByParId(@AdminUserParam User user, HttpServletResponse response,
            @RequestParam(value = "communityId", required = true) String communityId,
            @RequestParam(value = "parId", required = true) String parId,
            @RequestParam(value = "type", required = true) String type) {
		if (null == user) {
            return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
        }
		try {
			if (StringUtils.isEmpty(communityId) 
					|| StringUtils.isEmpty(parId) || StringUtils.isEmpty(type)) {
				return RestResponse.RestResponseBuilder.createFailBuilder(
						"小区ID、位置ID、位置类型不能为空!").buidler();
			}
			//Number类型格式验证
			if (!NumberUtils.isDigits(communityId) 
					 || !NumberUtils.isDigits(type)) {
				return RestResponse.RestResponseBuilder.createFailBuilder(
						"小区ID、位置类型传参有误!").buidler();
			}
			// 获取ICE服务
			PositionRetIce resource = this.getSpaceManageServiceIcePrx()
					.queryPositionListByParId(communityId, parId, type);
			if (RpcError.SUCCESS.getCode().equals(resource.code)) {
				Map<String, List<PositionIce>> map = new HashMap<>();
				map.put("resultList", resource.getResultList());
                return RestResponse.RestResponseBuilder.createSuccessBuilder("通过位置ID查询下一节点信息成功!")
						.setResult(map).buidler();
            }
            return RestResponse.RestResponseBuilder.createFailBuilder(resource.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过位置ID查询下一节点信息失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	/**
	 * 
	 * 类描述: 0506通过楼栋ID获取楼栋详情
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月16日 下午3:28:26
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "通过楼栋ID获取楼栋详情", response = String.class, notes = "通过楼栋ID获取楼栋详情")
	@ApiImplicitParams({})
	@RequestMapping(value = "/getBuildDetail.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse getBuildDetail(@AdminUserParam User user, HttpServletResponse response,
            @RequestParam(value = "buildId", required = true) String buildId) {
		if (null == user) {
            return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
        }
		try {
			if (StringUtils.isEmpty(buildId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder("楼栋ID不能为空!").buidler();
			}
			//Number类型格式验证
			if (!NumberUtils.isDigits(buildId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder("楼栋ID不是数字类型!").buidler();
			}
			// 获取ICE服务
			BuildDetailReturnIce resource = this.getSpaceManageServiceIcePrx().getBuildDetail(buildId);
			if (RpcError.SUCCESS.getCode().equals(resource.code)) {
                return RestResponse.RestResponseBuilder.createSuccessBuilder("通过楼栋ID获取楼栋详情成功!")
						.setResult(resource.getData()).buidler();
            }
            return RestResponse.RestResponseBuilder.createFailBuilder(resource.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过楼栋ID获取楼栋详情失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	/**
	 * 
	 * 类描述: 0507通过单元ID获取单元详情
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月16日 下午3:51:11
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "通过单元ID获取单元详情", response = String.class, notes = "通过单元ID获取单元详情")
	@ApiImplicitParams({})
	@RequestMapping(value = "/getUnitDetail.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse getUnitDetail(@AdminUserParam User user, HttpServletResponse response,
            @RequestParam(value = "unitId", required = true) String unitId) {
		if (null == user) {
            return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
        }
		try {
			if (StringUtils.isEmpty(unitId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder("单元ID不能为空!").buidler();
			}
			//Number类型格式验证
			if (!NumberUtils.isDigits(unitId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder("单元ID不是数字类型!").buidler();
			}
			// 获取ICE服务
			UnitDetailReturnIce resource = this.getSpaceManageServiceIcePrx().getUnitDetail(unitId);
			if (RpcError.SUCCESS.getCode().equals(resource.code)) {
                return RestResponse.RestResponseBuilder.createSuccessBuilder("通过单元ID获取单元详情成功!")
						.setResult(resource.getData()).buidler();
            }
            return RestResponse.RestResponseBuilder.createFailBuilder(resource.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过单元ID获取单元详情失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	/**
	 * 
	 * 类描述: 0508通过楼层ID获取楼层详情
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月16日 下午4:16:35
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "通过楼层ID获取楼层详情", response = String.class, notes = "通过楼层ID获取楼层详情")
	@ApiImplicitParams({})
	@RequestMapping(value = "/getFloorDetail.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse getFloorDetail(@AdminUserParam User user, HttpServletResponse response,
            @RequestParam(value = "floorId", required = true) String floorId) {
		if (null == user) {
            return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
        }
		try {
			if (StringUtils.isEmpty(floorId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder("楼层ID不能为空!").buidler();
			}
			//Number类型格式验证
			if (!NumberUtils.isDigits(floorId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder("楼层ID不是数字类型!").buidler();
			}
			// 获取ICE服务
			FloorDetailReturnIce resource = this.getSpaceManageServiceIcePrx().getFloorDetail(floorId);
			if (RpcError.SUCCESS.getCode().equals(resource.code)) {
                return RestResponse.RestResponseBuilder.createSuccessBuilder("通过楼层ID获取楼层详情成功!")
						.setResult(resource.getData()).buidler();
            }
            return RestResponse.RestResponseBuilder.createFailBuilder(resource.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过楼层ID获取楼层详情失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	/**
	 * 
	 * 类描述: 通过楼层ID获取所有物理房间
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月16日 下午4:47:35
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "通过楼层ID获取所有物理房间", response = String.class, notes = "通过楼层ID获取所有物理房间")
	@ApiImplicitParams({})
	@RequestMapping(value = "/getHouseAll.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse getHouseAll(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
            return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
        }
		try {
			// 前端传进来的json转对象
			HouseInfoIce params = FastjsonUtils.parseObject(paramJson, HouseInfoIce.class);
			// 必填参数验证
			String fields = "floorId";
			String messages = "楼层ID";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			errInfo = CheckRestParams.checkDigits(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			// 获取ICE服务
			HouseInfoReturnIce resource = this.getSpaceManageServiceIcePrx().getHouseAll(params);
			if (RpcError.SUCCESS.getCode().equals(resource.code)) {
				Map<String, List<HouseInfoIce>> map = new HashMap<>();
				map.put("resultList", resource.getResultList());
                return RestResponse.RestResponseBuilder.createSuccessBuilder("通过楼层ID获取所有物理房间成功!")
						.setResult(map).buidler();
            }
            return RestResponse.RestResponseBuilder.createFailBuilder(resource.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过楼层ID获取所有物理房间失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	/**
	 * @Title: queryPositionListByParIdInd   
	 *  通过空间/位置ID查询下一节点信息（个性化）
	 * @author zhaoqing  
	 * @param user
	 * @param response
	 * @param communityId
	 * @param parId
	 * @param type
	 * @return  
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "通过空间/位置ID查询下一节点信息（个性化）", 
			response = String.class, notes = "通过空间/位置ID查询下一节点信息（个性化）")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryPositionListByParIdInd.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryPositionListByParIdInd(@AdminUserParam User user, HttpServletResponse response,
            @RequestParam(value = "communityId", required = true) String communityId,
            @RequestParam(value = "parId", required = true) String parId,
            @RequestParam(value = "type", required = true) String type) {
		if (null == user) {
            return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
        }
		try {
			if (StringUtils.isEmpty(communityId) 
					|| StringUtils.isEmpty(parId) || StringUtils.isEmpty(type)) {
				return RestResponse.RestResponseBuilder.createFailBuilder(
						"小区ID、位置ID、位置类型不能为空!").buidler();
			}
			//Number类型格式验证
			if (!NumberUtils.isDigits(communityId) 
					 || !NumberUtils.isDigits(type)) {
				return RestResponse.RestResponseBuilder.createFailBuilder(
						"小区ID、位置类型传参有误!").buidler();
			}
			// 获取ICE服务
			PositionRetIce resource = this.getSpaceManageServiceIcePrx()
					.queryPositionListByParIdInd(communityId, parId, type);
			if (RpcError.SUCCESS.getCode().equals(resource.code)) {
				Map<String, List<PositionIce>> map = new HashMap<>();
				map.put("resultList", resource.getResultList());
                return RestResponse.RestResponseBuilder.createSuccessBuilder("通过位置ID查询下一节点信息成功!")
						.setResult(map).buidler();
            }
            return RestResponse.RestResponseBuilder.createFailBuilder(resource.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,通过位置ID查询下一节点信息失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
}
