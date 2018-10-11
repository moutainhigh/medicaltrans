package com.segi.uhomecp.medicaltrans.transtype.rest;

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

import resp.RpcRespIce;
import segi.medicaltrans.base.transtype.TransTypeDetailIce;
import segi.medicaltrans.base.transtype.TransTypeDetailReturnIce;
import segi.medicaltrans.base.transtype.TransTypeIce;
import segi.medicaltrans.base.transtype.TransTypeRetPageIce;
import segi.medicaltrans.base.transtype.TransTypeServiceIcePrx;
import IceExt.IceClientUtil;

import com.segi.uhomecp.common.constant.RestApiConstant;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.utils.ResponseDownloadUtils;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.sso.auth.vo.User;
import com.segi.uhomecp.sso.client.annotation.AdminUserParam;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.web.rest.RestResponse;
import com.segi.uhomecp.wh.common.constant.AppConCurrentTypeConstant;
import com.segi.uhomecp.wh.common.utils.AppConCurrentUtils;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.CheckRestParams;
import com.segi.uhomecp.wh.common.utils.ExcelWriter;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/rest-api/v1/medicaltrans/transTypeWebRest")
@Api(value = "medicaltrans/transTypeWebRest", description = "业务类型")
public class MtTransTypeWebRest {
	
	private static final Logger logger = LoggerFactory.getLogger(MtTransTypeWebRest.class);

	private TransTypeServiceIcePrx getTransTypeServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(TransTypeServiceIcePrx.class);
	}
	
	/**
	 * 
	 * 类描述: 运送类型新增
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月21日 下午4:27:12
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "运送类型新增", response = String.class, notes = "{<br/>"
            + "\"organId\":\"项目ID\",<br/>" 
            + "\"transTypeParentCode\":\"一级分类编码（大类）\",<br/>"
            + "\"transTypeCode\":\"运送类型编码（小类）\",<br/>" 
            + "\"transTypeName\":\"运送类型名称\",<br/>" 
            + "\"standardMinite\":\"标准时间(分钟)\",<br/>"
            + "\"warnMinite\":\"预警时间(分钟)\",<br/>" 
            + "\"timeOut\":\"超时时间(分钟)\",<br/>"
            + "\"status\":\"状态\",<br/>"
            + "\"remark\":\"备注\",<br/>" 
            + "\"oprGuide\":\"操作指引\"<br/>" 
            + "}<br/>")
    @ApiImplicitParams({})
    @RequestMapping(value = "/saveTransType.json", method = { RequestMethod.POST })
    @ResponseBody
    public RestResponse saveTransType(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
        if (null == user) {
            return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
        }
        try {
            // 前端传进来的json转对象
        	TransTypeDetailIce params = FastjsonUtils.parseObject(paramJson, TransTypeDetailIce.class);
        	//必填参数验证
			String fields = "organId, transTypeParentCode, transTypeCode, transTypeName, standardMinite, warnMinite, status, timeOut";
			String messages = "项目ID, 一级分类编码（大类）, 运送类型编码（小类）, 运送类型名称, 标准时间(分钟), 预警时间(分钟), 状态, 超时时间(分钟)";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			fields = "organId, standardMinite, warnMinite, status, timeOut";
			messages = "组织机构Id, 标准时间(分钟), 预警时间(分钟), 状态, 超时时间(分钟)";
			errInfo = CheckRestParams.checkDigits(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//设置创建人
			params.setCreateBy(String.valueOf(user.getUserId()));
			//验证预警时间是否小于等于标准时间
			if (Integer.valueOf(params.getWarnMinite()) > Integer.valueOf(params.getStandardMinite())) {
				return RestResponse.RestResponseBuilder.createFailBuilder("预警时间必须小于等于标准时间").buidler();
			}
            // 获取ICE服务
			RpcRespIce rpcResp = this.getTransTypeServiceIcePrx().saveTransType(params);
            if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
                return RestResponse.RestResponseBuilder.createBuilder(
						RpcError.SUCCESS.getCode(), "新增成功").setResult(rpcResp.data).buidler();
            }
            return RestResponse.RestResponseBuilder.createFailBuilder(rpcResp.message).buidler();
        } catch (Exception e) {
            logger.warn("系统异常,运送类型新增失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,运送类型新增失败").buidler();
        }
    }

	/**
	 * 
	 * 类描述: 运输类型修改
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月22日 下午5:30:39
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "运送类型修改", response = String.class, notes = "{<br/>"
			+ "\"organId\":\"项目ID\",<br/>" 
			+ "\"transTypeId\":\"运输类型id\",<br/>" 
            + "\"transTypeParentCode\":\"一级分类编码（大类）\",<br/>"
            + "\"transTypeCode\":\"运送类型编码（小类）\",<br/>" 
            + "\"transTypeName\":\"运送类型名称\",<br/>" 
            + "\"standardMinite\":\"标准时间(分钟)\",<br/>"
            + "\"timeOut\":\"超时时间(分钟)\",<br/>"
            + "\"warnMinite\":\"预警时间(分钟)\",<br/>" 
            + "\"remark\":\"备注\",<br/>"
            + "\"status\":\"状态\",<br/>"	
            + "\"oprGuide\":\"操作指引\"<br/>" 
            + "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/updateTransType.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse updateTransType(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"用户未登录,请重新登录!").buidler();
		}
		try {
			// 前端传进来的json转对象
			TransTypeDetailIce params = FastjsonUtils.parseObject(paramJson, TransTypeDetailIce.class);
			// 必填参数验证
			String fields = "transTypeId, transTypeParentCode, transTypeCode, transTypeName, standardMinite, warnMinite, timeOut";
			String messages = "运输类型id, 一级分类编码（大类）, 运送类型编码（小类）, 运送类型名称, 标准时间(分钟), 预警时间(分钟), 超时时间(分钟)";
			String errInfo = CheckRestParams.checkEmpty(params, fields,
					messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(
						errInfo).buidler();
			}
			//Number类型格式验证
			fields = "transTypeId, standardMinite, warnMinite, timeOut";
			messages = "运输类型id, 标准时间(分钟), 预警时间(分钟), 超时时间(分钟)";
			errInfo = CheckRestParams.checkDigits(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			// 设置修改人
			params.setUpdateBy(String.valueOf(user.getUserId()));
			//验证预警时间是否小于等于标准时间
			if (Integer.valueOf(params.getWarnMinite()) > Integer.valueOf(params.getStandardMinite())) {
				return RestResponse.RestResponseBuilder.createFailBuilder("预警时间必须小于等于标准时间").buidler();
			}
			// 获取ICE服务
			RpcRespIce rpcResp = this.getTransTypeServiceIcePrx()
					.updateTransType(params);
			if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
				return RestResponse.RestResponseBuilder.createBuilder(
						RpcError.SUCCESS.getCode(), "运送类型修改成功").setResult(rpcResp.getData()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(
					rpcResp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,运送类型修改失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"系统异常,运送类型修改失败").buidler();
		}
	}
	
	/**
	 * 
	 * 类描述: 运送类型停用、启用、删除
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月22日 下午5:30:39
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "运送类型停用、启用、删除", response = String.class, notes = "{<br/>"
			+ "\"transTypeId\":\"运输类型id\",<br/>" 
            + "\"status\":\"状态：1有效；2停用；0已删除\",<br/>"
            + "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/updateStatusTransType.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse updateStatusTransType(@AdminUserParam User user,
			@RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"用户未登录,请重新登录!").buidler();
		}
		String statusName = "";
		try {
			// 前端传进来的json转对象
			TransTypeIce params = FastjsonUtils.parseObject(paramJson, TransTypeIce.class);
			// 必填参数验证
			String fields = "transTypeId, status";
			String messages = "运送类型id, 状态";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			fields = "transTypeId, status";
			messages = "运送类型id, 状态";
			errInfo = CheckRestParams.checkDigits(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			// 设置修改人
			params.setUpdateBy(String.valueOf(user.getUserId()));
			// 设置错误提示返回提示
			if ("0".equals(params.getStatus())) {
				statusName = "删除";
			}else if ("1".equals(params.getStatus())) {
				statusName = "启用";
			}else if ("2".equals(params.getStatus())) {
				statusName = "停用";
			}
			// 获取ICE服务
			RpcRespIce rpcResp = this.getTransTypeServiceIcePrx().updateStatusTransType(params);
			if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
				return RestResponse.RestResponseBuilder.createBuilder(
						RpcError.SUCCESS.getCode(), rpcResp.getMessage()).setResult(rpcResp.getData()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rpcResp.message)
					.buidler();
		} catch (Exception e) {
			logger.warn("系统异常,运送类型" + statusName + "失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"系统异常,运送类型" + statusName + "失败").buidler();
		}
	}
	
	/**
	 * 
	 * 类描述: 运送类型详情
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月22日 下午5:30:39
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "运送类型详情", response = String.class, notes = "{<br/>"
			+ "\"transTypeId\":\"运输类型id\",<br/>" 
            + "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryTransType.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryTransType(@AdminUserParam User user,
			@RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!")
					.buidler();
		}
		try {
			// 前端传进来的json转对象
			TransTypeIce params = FastjsonUtils.parseObject(paramJson,
					TransTypeIce.class);
			// 必填参数验证
			String fields = "transTypeId";
			String messages = "运送类型id";
			String errInfo = CheckRestParams.checkEmpty(params, fields,
					messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(
						errInfo).buidler();
			}
			//设置修改人
			params.setUpdateBy(String.valueOf(user.getUserId()));
			// 获取ICE服务
			TransTypeDetailReturnIce rpcResp = this.getTransTypeServiceIcePrx()
					.queryTransType(params);
			if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
				return RestResponse.RestResponseBuilder.createSuccessBuilder("查询运送类型详情成功!")
						.setResult(rpcResp.getTransTypeDetailIce()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(
					rpcResp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,查询运送类型详情失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"系统异常,查询运送类型详情失败").buidler();
		}
	}
	
	/**
	 * 
	 * 类描述: 运送类型分页展示
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月22日 下午5:30:39
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "运送类型分页展示", response = String.class, notes = "{<br/>"
			+ "\"organId\":\"项目ID\",<br/>" 
			+ "\"transTypeParentCode\":\"一级分类编码(大类)\",<br/>" 
			+ "\"transTypeName\":\"运送类型名称\",<br/>" 
			+ "\"pageNo\":\"分页页码  第几页\",<br/>" 
			+ "\"pageLength\":\"每页数目	每页记录数\",<br/>" 
            + "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/transTypePage.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse transTypePage(@AdminUserParam User user,
			@RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"用户未登录,请重新登录!").buidler();
		}
		try {
			// 前端传进来的json转对象
			TransTypeIce params = FastjsonUtils.parseObject(paramJson,
					TransTypeIce.class);
			// 必填参数验证
			String fields = "pageNo, pageLength, organId";
			String messages = "分页页码, 每页数目, 项目ID";
			String errInfo = CheckRestParams.checkEmpty(params, fields,
					messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(
						errInfo).buidler();
			}
			// 获取ICE服务
			TransTypeRetPageIce rpcResp = this.getTransTypeServiceIcePrx()
					.transTypePage(params);
			if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
				return RestResponse.RestResponseBuilder.createBuilder(rpcResp.code, rpcResp.message)
						.setResult(rpcResp.paginator, rpcResp.transTypeListIce.toArray()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(
					rpcResp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,查询运送类型分页失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"系统异常,查询运送类型分页失败").buidler();
		}
	}
	
	/**
	 * 
	 * 类描述: 运送类型列表展示
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月22日 下午5:30:39
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "运送类型列表展示", response = String.class, notes = "{<br/>"
			+ "\"organId\":\"项目ID\",<br/>" 
			+ "\"transTypeParentCode\":\"一级分类编码(大类)\",<br/>" 
			+ "\"status\":\"传1表示返回启用停用 的\",<br/>" 
            + "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/transTypeList.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse transTypeList(@AdminUserParam User user,
			@RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"用户未登录,请重新登录!").buidler();
		}
		try {
			// 前端传进来的json转对象
			TransTypeIce params = FastjsonUtils.parseObject(paramJson,
					TransTypeIce.class);
			// 必填参数验证
			String fields = "organId, transTypeParentCode";
			String messages = "项目ID, 一级分类编码(大类)";
			String errInfo = CheckRestParams.checkEmpty(params, fields,
					messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(
						errInfo).buidler();
			}
			// 获取ICE服务
			TransTypeRetPageIce rpcResp = this.getTransTypeServiceIcePrx()
					.transTypeList(params);
			if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
				Map<String ,Object> resMap = new HashMap<>();
				resMap.put("resultList", rpcResp.transTypeListIce);
				return RestResponse.RestResponseBuilder.createBuilder(rpcResp.code, rpcResp.message)
						.setResult(resMap).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(
					rpcResp.message).buidler();
		} catch (Exception e) {                                                     
			logger.warn("系统异常,查询运送类型列表失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"系统异常,查询运送类型列表失败").buidler();
		}
	}
	
	/**
	 * 
	 * 类描述: 运送类型导出
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月8日 下午4:07:21
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "运送类型导出", response = String.class, notes = "运送类型导出")
	@ApiImplicitParams({})
	@RequestMapping(value = "/expoetTransType.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse expoetTransType(@AdminUserParam User user, HttpServletResponse response,
            @RequestParam(value = "organId", required = true) String organId,
            @RequestParam(value = "transTypeParentCode", required = false) String transTypeParentCode,
            @RequestParam(value = "transTypeName", required = false) String transTypeName) {
		File file = null;
		try {
			// 下载并发次数新增
			boolean exeFlag = AppConCurrentUtils.getInstace().addConCurCount(
					AppConCurrentTypeConstant.MAX_CURRENCY_EXCEL_EXP);
			if (!exeFlag) {
				return RestResponse.RestResponseBuilder.createSuccessBuilder(
						"当前数据导出操作的人数过多，请稍后再试！").buidler(); 
			}
			TransTypeIce params = new TransTypeIce();
			//Number类型格式验证
			String fields = "organId";
			String messages = "项目(医院)Id";
			String errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (StringUtils.isNotBlank(transTypeParentCode)) {
				params.setTransTypeParentCode(transTypeParentCode);
			}
			if (StringUtils.isNotBlank(transTypeName)) {
				params.setTransTypeName(transTypeName);
			}
			params.setOrganId(organId);
			// 设置每次导出1000条
			String pageLength = MtConstant.EXPORT_EVERY_PAGE_LENGTH_V1;
            params.setPageLength(pageLength);
            params.setPageNo(MtConstant.EXPORT_PAGE_NO);
			// 获取ICE服务
            TransTypeServiceIcePrx icePrx = this.getTransTypeServiceIcePrx();
			TransTypeRetPageIce resource = icePrx.transTypePage(params);
			// 判断总条数是否大于5000条
			String totalCount = resource.getPaginator().getTotalCount();
			int totalCountInt = Integer.parseInt(totalCount);
			if (Integer.parseInt(MtConstant.EXPORT_PAGE_LENGTH) < totalCountInt) {
				return RestResponse.RestResponseBuilder.createFailBuilder("当前导出数据条数大于限制的最大条数["
						+ MtConstant.EXPORT_PAGE_LENGTH + "]条，请缩小查询范围再导出！").buidler();
			}
			List<TransTypeIce> resultList = new ArrayList<TransTypeIce>();
			resultList.addAll(resource.getTransTypeListIce());
			int pageLengthInt = Integer.parseInt(pageLength);
			String curPageNo = resource.getPaginator().getPageNo();
			int curPageNoInt = Integer.parseInt(curPageNo);
			// 当前数据条数
			int curCount = curPageNoInt * pageLengthInt;
			while (curCount < totalCountInt) {
				params.setPageNo(String.valueOf(curPageNoInt + 1));
				resource = icePrx.transTypePage(params);
				if (AppUtils.isNotEmpty(resource.getTransTypeListIce())) {
					resultList.addAll(resource.getTransTypeListIce());
				}
				curPageNo = resource.getPaginator().getPageNo();
				curPageNoInt = Integer.parseInt(curPageNo);
				curCount = curPageNoInt * pageLengthInt;
			}
			
			String title = "运送类型报表导出.xls";
			String[] columnTitles = { "所属项目", "运送大类", "运送类型名称", "运送类型编码", "标准时间（分钟）", "预警时间(分钟)", "状态"};
            String[] data = null;
            List<String[]> dataList = new ArrayList<String[]>();
            if (AppUtils.isNotEmpty(resultList)) {
                for (int i = 0; i < resultList.size(); i++) {
                	TransTypeIce transTypeIce = resultList.get(i);
                    data = new String[7];
                    data[0] = transTypeIce.getOrganName(); // 所属组织
                    data[1] = transTypeIce.getTransTypeParentCodeName(); // 运送大类
                    data[2] = transTypeIce.getTransTypeName(); // 运送类型名称
                    data[3] = transTypeIce.getTransTypeCode(); // 运送类型编码
                    data[4] = transTypeIce.getStandardMinite(); // 标准时间（分钟）
                    data[5] = transTypeIce.getWarnMinite(); // 预警时间(分钟)
                    data[6] = transTypeIce.getStatusName(); // 状态
                    dataList.add(data);
                }
            }
            file = ExcelWriter.simpleCreate(RestApiConstant.MEDICALTRANS, columnTitles, dataList);
            ResponseDownloadUtils.downloadAndDelete(response, file, title);
		} catch (Exception e) {
			logger.warn("系统异常,运送类型导出", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,运送类型导出").buidler();
		} finally {
			// 下载并发次数释放
			AppConCurrentUtils.getInstace().subConCurCount(
					AppConCurrentTypeConstant.MAX_CURRENCY_EXCEL_EXP);
		}
		return null;
	}
	
}
