package com.segi.uhomecp.medicaltrans.report.mtpervolamount.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import segi.medicaltrans.mthistask.query.MtHisTaskQueryServiceIcePrx;
import segi.medicaltrans.mthistask.query.MtTaskPageIce;
import segi.medicaltrans.mthistask.query.MtTaskPageIceParam;
import segi.medicaltrans.mthistask.query.MtTaskPaginatorIceRsp;
import segi.medicaltrans.report.pervolume.day.PerDayRankParam;
import segi.medicaltrans.report.pervolume.day.PerVolDayIce;
import segi.medicaltrans.report.pervolume.day.PerVolDayRsp;
import segi.medicaltrans.report.pervolume.day.PerVolDayServiceIcePrx;
import IceExt.IceClientUtil;

import com.segi.uhomecp.common.constant.RestApiConstant;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.utils.ReportApiUtils;
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
import com.segi.uhomecp.wh.common.utils.DateUtil;
import com.segi.uhomecp.wh.common.utils.ExcelWriter;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * @ClassName:  MtPerVolAmountDaylyWebRest   
 * @Description:运送员个人运送量日排名Api   
 * @author: zhaoqing
 * @date:   2018年9月14日 下午3:50:47
 */
@Controller
@RequestMapping("/rest-api/v1/medicaltrans/mtPerVolAmountDaylyWebRest/")
@Api(value = "medicaltrans/mtPerVolAmountDaylyWebRest", description = "运送员运送量日排名")
public class MtPerVolAmountDaylyWebRest {
	
	private static final Logger logger = LoggerFactory.getLogger(MtPerVolAmountDaylyWebRest.class);
	
	/**
	 * @Title: getPerVolDayServiceIcePrx   
	 *  获取ICE服务 
	 * @author zhaoqing  
	 * @return
	 */
	private PerVolDayServiceIcePrx getPerVolDayServiceIcePrx() {
		return IceClientUtil.getServicePrxByClass(PerVolDayServiceIcePrx.class);
	}
	
	/**
	 * @Title: getMtHisTaskQueryServiceIcePrx   
	 *  获取ICE服务（查询业务库） 
	 * @author zhaoqing  
	 * @return      
	 */
	private MtHisTaskQueryServiceIcePrx getMtHisTaskQueryServiceIcePrx() {
		return IceClientUtil.getServicePrxByClass(MtHisTaskQueryServiceIcePrx.class);
	}
	
	/**
	 * @Title: getDayTransVolumeRank   
	 *  运送员运送量日排名信息查询
	 * @author zhaoqing  
	 * @param user
	 * @param paramJson
	 * @return 
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "运送员运送量日排名信息查询", 
			response = String.class, notes = "{<br/>" + 
			"\"organId\":\"项目Id\",<br/>" + 
			"\"beginTime\":\"开始日期(格式：YYYY-MM-DD)\",<br/>" + 
			"\"endTime\":\"结束日期(格式：YYYY-MM-DD)\",<br/>" + 
			"\"sergroupIds\":\"服务组Id, 以逗号分隔，全选不传\"<br/>" + 
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/getDayTransVolumeRank.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse getDayTransVolumeRank(@AdminUserParam User user, 
			@RequestBody(required = true) String paramJson) {
		if (null == user) {
            return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
        }
		try {
			// 前端传进来的json转对象
			PerDayRankParam params = FastjsonUtils.parseObject(paramJson, PerDayRankParam.class);
			// 参数校验
			RestResponse restResponse = checkPerDayRankParams(params);
			if (null != restResponse) {
				return restResponse;
			}
			// 设置开始结束时间
			params.setBeginTime(DateUtil.convertBeginDate(params.getBeginTime()));
			params.setEndTime(DateUtil.convertEndDate(params.getEndTime()));		
			// 调用ICE服务
			PerVolDayRsp rsp = this.getPerVolDayServiceIcePrx().getDayTransVolRank(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, List<PerVolDayIce>> map = new HashMap<>();
				map.put("resultList", rsp.getResultList());
                return RestResponse.RestResponseBuilder.createSuccessBuilder(
                		"运送员运送量日排名信息查询成功!").setResult(map).buidler();
            }
            return RestResponse.RestResponseBuilder.createFailBuilder(rsp.getMsg()).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,运送员运送量日排名信息查询失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder(
            		"系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	/**
	 * @Title: checkPerDayRankParams   
	 *  运送员运送量日排名信息查询参数校验
	 * @author zhaoqing  
	 * @param params
	 * @return      
	 * RestResponse 
	 */
	private RestResponse checkPerDayRankParams(PerDayRankParam params) {
		// 必填参数验证
		String fields = "organId, beginTime, endTime";
		String messages = "项目ID, 开始日期, 结束日期";
		String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
		if (null != errInfo) {
			return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
		}
		// Number类型格式验证
		errInfo = CheckRestParams.checkInteger(params, "organId", "项目ID");
		if (null != errInfo) {
			return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
		}
		// 日期校验
		errInfo = CheckRestParams.checkDateYYYY_MM_DD(params,"beginTime, endTime", "开始日期, 结束日期");
		if (null != errInfo) {
			return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
		}
		// 去掉日期YYYY-MM-DD中的横杠
		params.setBeginTime(DateUtil.converDateToNum(params.getBeginTime()));
		params.setEndTime(DateUtil.converDateToNum(params.getEndTime()));
		// 校验服务组Ids
		RestResponse restresponse = ReportApiUtils.checkSergroupIds(params.getSergroupIds());
		if (null != restresponse) {
			return restresponse;
		}
		return null;
	}
	
	/**
	 * @Title: exportDayTransVolumeRank   
	 *  运送员运送量日排名信息导出 
	 * @author zhaoqing  
	 * @param user
	 * @param response
	 * @param organId
	 * @param beginTime
	 * @param endTime
	 * @param sergroupIds
	 * @return 
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "运送员运送量日排名信息导出", response = String.class, 
			notes = "运送员运送量日排名信息导出")
	@ApiImplicitParams({})
	@RequestMapping(value = "/exportDayTransVolumeRank.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse exportDayTransVolumeRank(
			@AdminUserParam User user, HttpServletResponse response,
			@RequestParam(value = "organId", required = true) String organId,
			@RequestParam(value = "beginTime", required = true) String beginTime,
			@RequestParam(value = "endTime", required = true) String endTime,
			@RequestParam(value = "sergroupIds", required = false) String sergroupIds) {
		try {
			if (null == user) {
	            return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
	        }
			// 下载并发次数新增
			boolean exeFlag = AppConCurrentUtils.getInstace().addConCurCount(
					AppConCurrentTypeConstant.MAX_CURRENCY_EXCEL_EXP);
			if (!exeFlag) {
				return RestResponse.RestResponseBuilder.createFailBuilder(
						"当前数据导出操作的人数过多，请稍后再试！").buidler();
			}
			PerDayRankParam params = new PerDayRankParam();
			params.setOrganId(organId);
			params.setBeginTime(beginTime);
			params.setEndTime(endTime);
			params.setSergroupIds(sergroupIds);
			// 参数校验
			RestResponse restResponse = checkPerDayRankParams(params);
			if (null != restResponse) {
				return restResponse;
			}
			// 设置开始结束时间
			params.setBeginTime(DateUtil.convertBeginDate(params.getBeginTime()));
			params.setEndTime(DateUtil.convertEndDate(params.getEndTime()));		
			// 调用ICE服务
			PerVolDayRsp rsp = this.getPerVolDayServiceIcePrx().getDayTransVolRank(params);
			List<PerVolDayIce> resultList = new ArrayList<>();
			if (AppUtils.isNotEmpty(rsp.getResultList())) {
				resultList.addAll(rsp.getResultList());
			}
			int totalCountInt = resultList.size();
			if (Integer.parseInt(MtConstant.EXPORT_PAGE_LENGTH) < totalCountInt) {
				return RestResponse.RestResponseBuilder.createFailBuilder(
						"当前导出数据条数大于限制的最大条数[" + MtConstant.EXPORT_PAGE_LENGTH
								+ "]条，请缩小查询范围再导出！").buidler();
			}
			// 数据导出
			return exportDayTransVolumeRankExcel(response, resultList);
		} catch (Exception e) {
			logger.warn("系统异常,运送员运送量日排名信息导出失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"系统异常,运送员运送量日排名信息导出失败, 请联系系统管理员！").buidler();
		} finally {
			// 下载并发次数释放
			AppConCurrentUtils.getInstace().subConCurCount(
					AppConCurrentTypeConstant.MAX_CURRENCY_EXCEL_EXP);
		}
	}
	
	/**
	 * @Title: exportDayTransVolumeRankExcel   
	 *  运送员运送量日排名信息数据导出 
	 * @author zhaoqing  
	 * @param response
	 * @param resultList
	 * @return
	 */
	private RestResponse exportDayTransVolumeRankExcel(
			HttpServletResponse response, List<PerVolDayIce> resultList) {
		try {
			File file = null;
			String title = "运送员运送量日排名信息导出.xls";
			String[] columnTitles = {"排名", "运送组", "姓名", "工号", "总运送数量"};		
			String[] data = null;
			List<String[]> dataList = new ArrayList<String[]>();
			if (AppUtils.isNotEmpty(resultList)) {
				for (int i = 0; i < resultList.size(); i++) {
					PerVolDayIce resultIce = resultList.get(i);
					data = new String[5];
					data[0] = resultIce.getRank(); // 排名
					data[1] = resultIce.getSergroupName(); // 运送组
					data[2] = resultIce.getUserName(); // 姓名
					data[3] = resultIce.getUserNo(); // 工号
					data[4] = resultIce.getTransVolume(); // 总运送数量
					dataList.add(data);
				}
			}
			file = ExcelWriter.simpleCreate(RestApiConstant.MEDICALTRANS, columnTitles, dataList);
			ResponseDownloadUtils.downloadAndDelete(response, file, title);
			return null;
		} catch (Exception e) {
			logger.error("运送员运送量日排名信息数据导出异常", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("运送员运送量日排名信息数据导出异常").buidler();
		} 
	}
	
	/**
	 * @Title: queryUserHisTaskDaylyPage   
	 *  运送员日排名运送明细分页查询
	 * @author zhaoqing  
	 * @param user
	 * @param paramJson
	 * @return 
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "运送员日排名运送明细分页查询", response = String.class, 
			notes = "{<br/>" + 
			"\"groupOrganId\":\"一级物业Id\",<br/>" +
			"\"organId\":\"项目(医院)Id\",<br/>" +
			"\"userId\":\"运送员Id\",<br/>" + 
			"\"beginTime\":\"开始日期(格式：YYYY-MM-DD)\",<br/>" + 
			"\"endTime\":\"结束日期(格式：YYYY-MM-DD)\",<br/>" + 
			"\"pageNo\":\"页码\",<br/>" +
			"\"pageLength\":\"每页记录数\"<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryUserHisTaskDaylyPage.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryUserHisTaskDaylyPage(@AdminUserParam User user, 
			@RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			// 前端传进来的json转对象
			MtTaskPageIceParam params = FastjsonUtils.parseObject(paramJson, MtTaskPageIceParam.class);
			// 公共参数校验
			RestResponse restResponse = checkMtTaskParamCom(params);
			if (null != restResponse) {
				return restResponse;
			}
			// 必填参数验证
			String errInfo = CheckRestParams.checkEmpty(params, "pageNo, pageLength", "页码, 每页记录数");
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			// Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, "pageNo, pageLength", "页码, 每页记录数");
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			// 设置开始结束时间
			params.setBeginTime(DateUtil.convertBeginDate(params.getBeginTime()));
			params.setEndTime(DateUtil.convertEndDate(params.getEndTime()));
			// 获取ICE服务
			MtTaskPaginatorIceRsp rsp = this.getMtHisTaskQueryServiceIcePrx()
					.queryUserHisTaskDaylyPage(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				return RestResponse.RestResponseBuilder.createSuccessBuilder("运送员运送明细查询成功")
						.setResult(rsp.paginator, rsp.resultList.toArray()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,运送员运送明细查询失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,运送员运送明细查询失败").buidler();
		}
	}
	
	/**
	 * @Title: checkMtTaskParamCom   
	 *  校验任务列表查询公共参数 
	 * @author zhaoqing  
	 * @param params
	 * @return 
	 */
	private RestResponse checkMtTaskParamCom(MtTaskPageIceParam params) {
		// 必填参数验证
		String fields = "organId, groupOrganId, userId, beginTime, endTime";
		String messages = "项目(医院)Id, 一级物业Id, 运送员Id, 开始日期, 结束日期";
		String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
		if (null != errInfo) {
			return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
		}
		fields = "organId, groupOrganId, userId";
		messages = "项目(医院)Id, 一级物业Id, 运送员Id";
		// Number类型格式验证
		errInfo = CheckRestParams.checkInteger(params, fields, messages);
		if (null != errInfo) {
			return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
		}
		// 日期校验
		errInfo = CheckRestParams.checkDateYYYY_MM_DD(params, "beginTime, endTime", "开始日期, 结束日期");
		if (null != errInfo) {
			return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
		}
		return null;
	}
	
	/**
	 * @Title: exportUserHisTaskDayly   
	 *  运送员日排名运送任务列表导出 
	 * @author zhaoqing  
	 * @param user
	 * @param response
	 * @param groupOrganId
	 * @param organId
	 * @param userId
	 * @param beginTime
	 * @param endTime
	 * @return 
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "运送员日排名运送任务列表导出", response = String.class, 
			notes = "运送员日排名运送任务列表导出")
	@ApiImplicitParams({})
	@RequestMapping(value = "/exportUserHisTaskDayly.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse exportUserHisTaskDayly(@AdminUserParam User user, HttpServletResponse response,       
            @RequestParam(value = "groupOrganId", required = true) String groupOrganId,
            @RequestParam(value = "organId", required = true) String organId,
            @RequestParam(value = "userId", required = true) String userId,
            @RequestParam(value = "beginTime", required = true) String beginTime,
			@RequestParam(value = "endTime", required = true) String endTime) {
		try {
			if (null == user) {
	            return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
	        }
			// 下载并发次数新增
			boolean exeFlag = AppConCurrentUtils.getInstace().addConCurCount(
					AppConCurrentTypeConstant.MAX_CURRENCY_EXCEL_EXP);
			if (!exeFlag) {
				return RestResponse.RestResponseBuilder.createFailBuilder(
						"当前数据导出操作的人数过多，请稍后再试！").buidler();
			}
			MtTaskPageIceParam params = new MtTaskPageIceParam();
			params.setGroupOrganId(groupOrganId);
			params.setOrganId(organId);
			params.setUserId(userId);
			params.setBeginTime(beginTime);
			params.setEndTime(endTime);
			// 公共参数校验
			RestResponse restResponse = checkMtTaskParamCom(params);
			if (null != restResponse) {
				return restResponse;
			}
			// 设置开始结束时间
			params.setBeginTime(DateUtil.convertBeginDate(params.getBeginTime()));
			params.setEndTime(DateUtil.convertEndDate(params.getEndTime()));		
			String pageLength = MtConstant.EXPORT_EVERY_PAGE_LENGTH_V1;
			// 设置长度为1000
            params.setPageLength(pageLength);
            params.setPageNo(MtConstant.EXPORT_PAGE_NO);
            // 导出标识
            params.setExportFlag(true);
         	// 获取ICE服务
         	MtTaskPaginatorIceRsp resource = this.getMtHisTaskQueryServiceIcePrx()
         			.queryUserHisTaskDaylyPage(params);
			// 获取需要导出的数据
			List<MtTaskPageIce> resultList = new ArrayList<>();
			if (AppUtils.isNotEmpty(resource.getResultList())) {
				resultList.addAll(resource.getResultList());
			}
			// 数据总条数
			String totalCount = resource.getPaginator().getTotalCount();
			int totalCountInt = Integer.parseInt(totalCount);
			if (Integer.parseInt(MtConstant.EXPORT_PAGE_LENGTH) < totalCountInt) {
				return RestResponse.RestResponseBuilder.createFailBuilder("当前导出数据条数大于限制的最大条数["
						+ MtConstant.EXPORT_PAGE_LENGTH + "]条，请缩小查询范围再导出！").buidler();
			}
			int pageLengthInt = Integer.parseInt(pageLength);
			String curPageNo = resource.getPaginator().getPageNo();
			int curPageNoInt = Integer.parseInt(curPageNo);
			// 当前数据条数
			int curCount = curPageNoInt * pageLengthInt;
			while (curCount < totalCountInt) {
				params.setPageNo(String.valueOf(curPageNoInt + 1));
				resource = this.getMtHisTaskQueryServiceIcePrx()
	         			.queryUserHisTaskDaylyPage(params);
				if (AppUtils.isNotEmpty(resource.getResultList())) {
					resultList.addAll(resource.getResultList());
				}
				curPageNo = resource.getPaginator().getPageNo();
				curPageNoInt = Integer.parseInt(curPageNo);
				curCount = curPageNoInt * pageLengthInt;
			}
            // 运送员运送任务列表数据导出
            return ReportApiUtils.exportUserHisTaskExcel(response, resultList);
		} catch (Exception e) {
			logger.warn("系统异常,运送员运送任务列表导出失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"系统异常,运送员运送任务列表导出失败,请联系系统管理员").buidler();
		} finally {
			// 下载并发次数释放
			AppConCurrentUtils.getInstace().subConCurCount(
					AppConCurrentTypeConstant.MAX_CURRENCY_EXCEL_EXP);
		}
	}
}
