package com.segi.uhomecp.medicaltrans.report.mtpervolamount.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

import segi.medicaltrans.mthistask.query.MtHisTaskQueryServiceIcePrx;
import segi.medicaltrans.mthistask.query.MtHisTaskRptQueryServiceIcePrx;
import segi.medicaltrans.mthistask.query.MtTaskPageIce;
import segi.medicaltrans.mthistask.query.MtTaskPageIceParam;
import segi.medicaltrans.mthistask.query.MtTaskPaginatorIceRsp;
import segi.medicaltrans.report.pervolume.month.PerTaskAmoMonthIce;
import segi.medicaltrans.report.pervolume.month.PerTaskAmoMonthPageResp;
import segi.medicaltrans.report.pervolume.month.PerTaskAmoMonthParam;
import segi.medicaltrans.report.pervolume.month.PerTaskAmoMonthServiceIcePrx;
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
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.AppConCurrentUtils;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.CheckRestParams;
import com.segi.uhomecp.wh.common.utils.DateUtil;
import com.segi.uhomecp.wh.common.utils.ExcelWriter;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * @ClassName:  MtTaskAmountWebRest   
 * @Description:运送员运送量月报表Api
 * @author: zhaoqing
 * @date:   2018年7月25日 下午2:09:16
 */
@Controller
@RequestMapping("/rest-api/v1/medicaltrans/mtPerVolAmountWebRest/")
@Api(value = "medicaltrans/mtPerVolAmountWebRest", description = "运送员运送量月报表")
public class MtPerVolAmountWebRest {
	
	private static final Logger logger = LoggerFactory.getLogger(MtPerVolAmountWebRest.class);
	
	/**
	 * @Title: getPerTaskAmoMonthServiceIcePrx   
	 *  获取ICE服务 
	 * @author zhaoqing  
	 * @return
	 */
	private PerTaskAmoMonthServiceIcePrx getPerTaskAmoMonthServiceIcePrx() {
		return IceClientUtil.getServicePrxByClass(PerTaskAmoMonthServiceIcePrx.class);
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
	 * @Title: getMtHisTaskRptQueryServiceIcePrx   
	 *  获取ICE服务（查询报表库） 
	 * @author zhaoqing  
	 * @return 
	 */
	private MtHisTaskRptQueryServiceIcePrx getMtHisTaskRptQueryServiceIcePrx() {
		return IceClientUtil.getServicePrxByClass(MtHisTaskRptQueryServiceIcePrx.class);
	}
	
	/**
	 * @Title: queryUserAmountPage   
	 *  运送员运送量月报表信息分页查询 
	 * @author zhaoqing  
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "运送员运送量月报表信息分页查询", 
			response = String.class, notes = "{<br/>" + 
			"\"organId\":\"项目id\",<br/>" + 
			"\"userIds\":\"运送员Ids,之个ID之间用英文逗号分隔\",<br/>" + 
			"\"cycleYm\":\"月份（格式：YYYY-MM）\",<br/>" + 
			"\"sergroupIds\":\"服务组Id, 多个Id之用英文逗号分隔\",<br/>" + 
			"\"pageNo\":\"当前页码\",<br/>" + 
			"\"pageLength\":\"每页条数\"<br/>" + 
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryUserAmountPage.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryUserAmountPage(@AdminUserParam User user, 
			@RequestBody(required = true) String paramJson) {
		if (null == user) {
            return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
        }
		try {
			// 前端传进来的json转对象
			PerTaskAmoMonthParam params = FastjsonUtils.parseObject(paramJson, PerTaskAmoMonthParam.class);
			// 必填参数验证
			String fields = "organId, cycleYm, pageNo, pageLength";
			String messages = "项目ID, 月份, 当前页码, 每页条数";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			// Number类型格式验证
			errInfo = CheckRestParams.checkDigits(params, 
					"organId, pageNo, pageLength", "项目ID, 当前页码, 每页条数");
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (StringUtils.isNotEmpty(params.getCycleYm())) {
				if (!Pattern.matches(MtConstant.FORMAT_YYYY_MM_REGEX, params.getCycleYm())) {
					// 检验月份格式
					return RestResponse.RestResponseBuilder.
							createFailBuilder("月份格式错误，请传YYYY-MM格式的月份").buidler();
				} else {
					// 去掉月份YYYY-MM中的横杠
					params.setCycleYm(DateUtil.converDateToNum(params.getCycleYm()));
				}
			}
			// 校验服务组Ids
			RestResponse restresponse = ReportApiUtils.checkSergroupIds(params.getSergroupIds());
			if (null != restresponse) {
				return restresponse;
			}
			if (StringUtils.isEmpty(params.getOrganId())) {
				// 默认设置当前用户的所属组织
				params.setOrganId(String.valueOf(user.getOrganId()));
			}
			// 调用ICE服务
			PerTaskAmoMonthPageResp resp = this.getPerTaskAmoMonthServiceIcePrx()
					.getPerTaskAmoMonthPage(params);
			if (RpcError.SUCCESS.getCode().equals(resp.code)) {
                return RestResponse.RestResponseBuilder.createSuccessBuilder("运送员运送量月报表信息分页查询成功!")
                		.setResult(resp.getPaginator(), resp.getResultList().toArray()).buidler();
            }
            return RestResponse.RestResponseBuilder.createFailBuilder(resp.getMsg()).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,运送员运送量月报表信息分页查询失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder(
            		"系统异常,数据查询失败,请联系管理员！").buidler();
		}
	}
	
	/**
	 * @Title: exportUserAmountPage   
	 *  运送员运送量月报表导出
	 * @author zhaoqing  
	 * @param user
	 * @param response
	 * @param organId
	 * @param userIds
	 * @param sergroupIds
	 * @param cycleYm
	 * @return  
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "运送员运送量月报表导出", response = String.class, 
			notes = "运送员运送量月报表导出")
	@ApiImplicitParams({})
	@RequestMapping(value = "/exportUserAmountPage.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse exportUserAmountPage(@AdminUserParam User user, HttpServletResponse response, 
            @RequestParam(value = "organId", required = true) String organId,
            @RequestParam(value = "userIds", required = false) String userIds,
            @RequestParam(value = "sergroupIds", required = false) String sergroupIds,
            @RequestParam(value = "cycleYm", required = true) String cycleYm) {
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
			PerTaskAmoMonthParam params = new PerTaskAmoMonthParam();
			params.setOrganId(organId);
			params.setUserIds(userIds);
			params.setCycleYm(cycleYm);
			String fields = "organId, cycleYm";
			String messages = "项目(医院)Id, 月份";
			// 非空验证
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			// Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, "organId", "项目(医院)Id");
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (!Pattern.matches(MtConstant.FORMAT_YYYY_MM_REGEX, params.getCycleYm())) {
				// 检验月份格式
				return RestResponse.RestResponseBuilder.createFailBuilder(
						"月份格式错误，请传YYYY-MM格式的月份").buidler();
			} else {
				params.setCycleYm(DateUtil.converDateToNum(cycleYm));
			}
			// 校验服务组Ids
			RestResponse restresponse = ReportApiUtils.checkSergroupIds(sergroupIds);
			if (null != restresponse) {
				return restresponse;
			}
			params.setSergroupIds(sergroupIds);
            // 设置长度为2500
            params.setPageLength(MtConstant.EXPORT_EVERY_PAGE_LENGTH);
            params.setPageNo(MtConstant.EXPORT_PAGE_NO);
			// 获取ICE服务
            PerTaskAmoMonthPageResp resp = this.getPerTaskAmoMonthServiceIcePrx()
            		.getPerTaskAmoMonthPage(params);
			// 获取需要导出的数据
			List<PerTaskAmoMonthIce> resultList = new ArrayList<>();
			if (AppUtils.isNotEmpty(resp.getResultList())) {
				resultList.addAll(resp.getResultList());
			}			
			String totalCount = resp.getPaginator().getTotalCount();
			int totalCountInt = Integer.parseInt(totalCount);
			if (Integer.parseInt(MtConstant.EXPORT_PAGE_LENGTH) < totalCountInt) {
				return RestResponse.RestResponseBuilder.createFailBuilder("当前导出数据条数大于限制的最大条数["
						+ MtConstant.EXPORT_PAGE_LENGTH + "]条，请缩小查询范围再导出！").buidler();
			}
			if (Integer.parseInt(MtConstant.EXPORT_EVERY_PAGE_LENGTH) < totalCountInt) {
				// 总条数大于2500时再查询一次
				params.setPageNo(String.valueOf(Integer.parseInt(MtConstant.EXPORT_PAGE_NO) + 1));
				resp = this.getPerTaskAmoMonthServiceIcePrx().getPerTaskAmoMonthPage(params);
				if (AppUtils.isNotEmpty(resp.getResultList())) {
					resultList.addAll(resp.getResultList());
				}
			}
            // 数据导出
            return exportUserAmountExcel(response, resultList);
		} catch (Exception e) {
			logger.warn("系统异常,运送员运送量月报表导出失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"系统异常,运送员运送量月报表导出失败, 请联系系统管理员！").buidler();	
		} finally {
			// 下载并发次数释放
			AppConCurrentUtils.getInstace().subConCurCount(
					AppConCurrentTypeConstant.MAX_CURRENCY_EXCEL_EXP);
		}	
	}
	
	/**
	 * @Title: exportUserAmountExcel   
	 *  运送量报表数据导出 
	 * @author zhaoqing  
	 * @param response
	 * @param resp 
	 */
	private RestResponse exportUserAmountExcel(HttpServletResponse response, List<PerTaskAmoMonthIce> resultList) {
		try {
			File file = null;
			String title = "运送员运送量月报表导出.xls";
			List<String[]> columnTitles = new ArrayList<String[]>();
			String[] columnTitles1 = {"所属项目", "项目组", "姓名", "工号", "总运送数量",  
					"调度任务", "#cspan", "#cspan", "自主任务", "#cspan", "#cspan", "固定任务", "#cspan", "#cspan"};
			String[] columnTitles2 = {"#rspan", "#rspan", "#rspan", "#rspan", "#rspan", "运送量", 
					"满意率", "及时率", "运送量", "满意率", "及时率", "运送量", "满意率", "及时率"};
			columnTitles.add(columnTitles1);
			columnTitles.add(columnTitles2);
			String[] data = null;
			List<String[]> dataList = new ArrayList<String[]>();
			if (AppUtils.isNotEmpty(resultList)) {
				for (int i = 0; i < resultList.size(); i++) {
					PerTaskAmoMonthIce taskIce = resultList.get(i);
					data = new String[14];
					data[0] = taskIce.getOrganName(); // 所属项目
					data[1] = taskIce.getSergroupName(); // 项目组
					data[2] = taskIce.getUserName(); // 姓名
					data[3] = taskIce.getUserNo(); // 工号
					data[4] = taskIce.getTransAmount(); // 运送总量
					data[5] = taskIce.getDispatchAmount(); // 调度任务运送量
					data[6] = taskIce.getDispatchSatisfactionRatio(); // 调度任务满意率
					data[7] = taskIce.getDispatchTimelyRatio(); // 调度任务及时率
					data[8] = taskIce.getAutonomousAmount(); // 自主任务运送量
					data[9] = taskIce.getAutonomousSatisfactionRatio(); // 自主任务满意率
					data[10] = taskIce.getAutonomousTimelyRatio(); // 自主任务及时率
					data[11] = taskIce.getFixedAmount(); // 固定任务运送量
					data[12] = taskIce.getFixedSatisfactionRatio(); // 固定任务满意率
					data[13] = taskIce.getFixedTimelyRatio(); // 固定任务及时率
					dataList.add(data);
				}
			}
			file = ExcelWriter.mulitCreate(RestApiConstant.MEDICALTRANS, columnTitles, dataList);
			ResponseDownloadUtils.downloadAndDelete(response, file, title);
			return null;
		} catch (Exception e) {
			logger.error("运送员运送量月报表数据导出异常", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("运送员运送量月报表数据导出异常").buidler();
		}
	}
	
	/**
	 * @Title: queryUserHisTaskPage   
	 *  运送员运送明细分页查询
	 * @author zhaoqing  
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "运送员运送明细分页查询", response = String.class, 
			notes = "{<br/>" + 
			"\"groupOrganId\":\"一级物业Id\",<br/>" +
			"\"organId\":\"项目(医院)Id\",<br/>" +
			"\"userId\":\"运送员Id\",<br/>" + 
			"\"cycleYm\":\"月份（格式：YYYY-MM）\",<br/>" +
			"\"pageNo\":\"页码\",<br/>" +
			"\"pageLength\":\"每页记录数\"<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryUserHisTaskPage.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryUserHisTaskPage(@AdminUserParam User user, 
			@RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtTaskPageIceParam params = FastjsonUtils.parseObject(paramJson, MtTaskPageIceParam.class);
			//必填参数验证
			String fields = "organId, pageNo, pageLength, groupOrganId, userId, cycleYm";
			String messages = "项目(医院)Id, 页码, 每页记录数, 一级物业Id, 运送员Id, 月份";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			fields = "organId, pageNo, pageLength, groupOrganId, userId";
			messages = "项目(医院)Id, 页码, 每页记录数, 一级物业Id, 运送员Id";
			//Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (!Pattern.matches(MtConstant.FORMAT_YYYY_MM_REGEX, params.getCycleYm())) {
				// 检验月份格式
				return RestResponse.RestResponseBuilder.
						createFailBuilder("月份格式错误，请传YYYY-MM格式的月份").buidler();
			} 
			// 拆分月份
			String[] cycleYms = params.getCycleYm().split(Constant.SPLIT_BAR);
			int year = Integer.parseInt(cycleYms[0]);
			int month = Integer.parseInt(cycleYms[1]);
			// 设置开始结束时间
			params.setBeginTime(DateUtil.getFirstTimeOfMonthYYYYMMDDHHMMSS(year, month));
			params.setEndTime(DateUtil.getLastTimeOfMonthYYYYMMDDHHMMSS(year, month));
			params.setCycleYm(DateUtil.converDateToNum(params.getCycleYm()));
			// 获取ICE服务
			MtTaskPaginatorIceRsp rsp = new MtTaskPaginatorIceRsp();
            if (ReportApiUtils.queryRptDataBaseFlag(params.getCycleYm(), 
            		MtConstant.TASK_HIS_QUERY_FLAG_TWO)) {
				// 查询报表库
            	rsp = this.getMtHisTaskRptQueryServiceIcePrx().queryMtHisTaskRptPageByUser(params);
			} else {
				// 查询业务库
				rsp = this.getMtHisTaskQueryServiceIcePrx().queryMtHisTaskPageByUser(params);
			}
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
	 * @Title: exportUserHisTaskPage   
	 *  运送员运送任务列表导出 
	 * @author zhaoqing  
	 * @param user
	 * @param response
	 * @param groupOrganId
	 * @param organId
	 * @param userId
	 * @param cycleYm   
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "运送员运送任务列表导出", response = String.class, 
			notes = "运送员运送任务列表导出")
	@ApiImplicitParams({})
	@RequestMapping(value = "/exportUserHisTaskPage.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse exportUserHisTaskPage(@AdminUserParam User user, HttpServletResponse response,       
            @RequestParam(value = "groupOrganId", required = true) String groupOrganId,
            @RequestParam(value = "organId", required = true) String organId,
            @RequestParam(value = "userId", required = true) String userId,            
            @RequestParam(value = "cycleYm", required = true) String cycleYm) {
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
			params.setCycleYm(cycleYm);
			String fields = "groupOrganId, organId, userId, cycleYm";
			String messages = "一级物业Id, 项目(医院)Id, 运送员Id, 月份";
			// 非空验证
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			fields = "groupOrganId, organId, userId";
			messages = "一级物业Id, 项目(医院)Id, 运送员Id";
			// Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (!Pattern.matches(MtConstant.FORMAT_YYYY_MM_REGEX, params.getCycleYm())) {
				// 检验月份格式
				return RestResponse.RestResponseBuilder.createFailBuilder(
						"月份格式错误，请传YYYY-MM格式的月份").buidler();
			}
			// 拆分月份
			String[] cycleYms = params.getCycleYm().split(Constant.SPLIT_BAR);
			int year = Integer.parseInt(cycleYms[0]);
			int month = Integer.parseInt(cycleYms[1]);
			// 设置开始结束时间
			params.setBeginTime(DateUtil.getFirstTimeOfMonthYYYYMMDDHHMMSS(year, month));
			params.setEndTime(DateUtil.getLastTimeOfMonthYYYYMMDDHHMMSS(year, month));		
			String pageLength = MtConstant.EXPORT_EVERY_PAGE_LENGTH_V1;
			// 设置长度为1000
            params.setPageLength(pageLength);
            params.setPageNo(MtConstant.EXPORT_PAGE_NO);
            params.setCycleYm(DateUtil.converDateToNum(cycleYm));
            // 导出标识
            params.setExportFlag(true);
            // 获取ICE服务
            MtTaskPaginatorIceRsp resource = getExportMtHisTaskData(params);
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
				resource = getExportMtHisTaskData(params);
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
	
	/**
	 * @Title: getExportMtHisTaskData   
	 *  查询需要导出的数据 
	 * @author zhaoqing  
	 * @param params
	 * @return 
	 */
	private MtTaskPaginatorIceRsp getExportMtHisTaskData(MtTaskPageIceParam params) {	
		MtTaskPaginatorIceRsp resource = new MtTaskPaginatorIceRsp();
        if (ReportApiUtils.queryRptDataBaseFlag(params.getCycleYm(), 
        		MtConstant.TASK_HIS_QUERY_FLAG_TWO)) {
			// 查询报表库
			resource = this.getMtHisTaskRptQueryServiceIcePrx().queryMtHisTaskRptPageByUser(params);
		} else {
			// 查询业务库
			resource = this.getMtHisTaskQueryServiceIcePrx().queryMtHisTaskPageByUser(params);
		}
        return resource;        
	}
}
