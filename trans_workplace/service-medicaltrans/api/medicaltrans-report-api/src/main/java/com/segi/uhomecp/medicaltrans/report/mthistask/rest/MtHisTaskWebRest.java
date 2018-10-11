package com.segi.uhomecp.medicaltrans.report.mthistask.rest;

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

import segi.medicaltrans.mthistask.query.ExeUser;
import segi.medicaltrans.mthistask.query.FixedTaskExeDetailReturnIce;
import segi.medicaltrans.mthistask.query.FixedTaskHisExePageRsp;
import segi.medicaltrans.mthistask.query.MtHisTaskQueryServiceIcePrx;
import segi.medicaltrans.mthistask.query.MtHisTaskRptQueryServiceIcePrx;
import segi.medicaltrans.mthistask.query.MtTaskDetailIceParam;
import segi.medicaltrans.mthistask.query.MtTaskDetailRetIceRsp;
import segi.medicaltrans.mthistask.query.MtTaskPageIce;
import segi.medicaltrans.mthistask.query.MtTaskPageIceParam;
import segi.medicaltrans.mthistask.query.MtTaskPaginatorIceRsp;
import IceExt.IceClientUtil;

import com.segi.uhomecp.common.constant.RestApiConstant;
import com.segi.uhomecp.common.rest.AbsActionRest;
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
 * @ClassName:  MtHisTaskWebRest   
 * @Description:运送记录查询WebApi   
 * @author: zhaoqing
 * @date:   2018年8月6日 上午9:58:47
 */
@Controller
@RequestMapping(RestApiConstant.API_PATH + "/medicaltrans/mtHisTaskWebRest")
@Api(value = "medicaltrans/mtHisTaskWebRest", description = "运送记录查询")
public class MtHisTaskWebRest extends AbsActionRest {
	
	private static final Logger logger = LoggerFactory.getLogger(MtHisTaskWebRest.class);
	
	private static final String PREFIXPROJECTNAME = RestApiConstant.PREFIXPROJECTNAME;
	
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
	 * @Title: queryMtHisTaskPage   
	 *  运送记录分页查询
	 * @author zhaoqing  
	 * @param user
	 * @param paramJson
	 * @return 
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "运送记录分页查询", response = String.class, notes = "{<br/>" + 
			"\"organId\":\"项目(医院)Id\",<br/>" +
			"\"transTypeParentCode\":\"运送类型\",<br/>" +
			"\"transTypeId\":\"所选大类带出来的小类Id,选全部就不传\",<br/>" +
			"\"beginTime\":\"下单开始时间 YYYY-MM-DD\",<br/>" +
			"\"endTime\":\"下单结束时间 YYYY-MM-DD\",<br/>" +
			"\"status\":\"状态,1:未派单;2:抢单中;3:进行中;4:未开始;5:已完成;6:已取消;7:退单,选全部就不传\",<br/>" +
			"\"taskType\":\"任务类型:1调度任务;2自主任务;3固定任务  选全部就不传\",<br/>" +
			"\"sourceHouseId\":\"任务来源科室Id\",<br/>" +
			"\"urgency\":\"紧急程度,1:一般;2:紧急;3:特急,选全部就不传\",<br/>" +
			"\"pageNo\":\"页码\",<br/>" +
			"\"pageLength\":\"每页记录数\"<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryMtHisTaskPage.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryMtHisTaskPage(@AdminUserParam User user, 
			@RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			// 前端传进来的json转对象
			MtTaskPageIceParam params = FastjsonUtils.parseObject(paramJson, MtTaskPageIceParam.class);
			// 必填参数验证
			String fields = "organId, pageNo, pageLength, beginTime, endTime";
			String messages = "项目(医院)Id, 页码, 每页记录数, 下单开始时间, 下单开始时间";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			fields = "organId, pageNo, pageLength";
			messages = "项目(医院)Id, 页码, 每页记录数";
			// Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			// 其他参数校验
			errInfo = checkPageQueryParams(params);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			// 获取ICE服务
			MtTaskPaginatorIceRsp rsp = new MtTaskPaginatorIceRsp();
			if (ReportApiUtils.queryRptDataBaseFlag(params.getBeginTime())) {
				// 查询报表库
				rsp = this.getMtHisTaskRptQueryServiceIcePrx().queryMtHisTaskRptPage(params);
			} else {
				// 查询业务库
				rsp = this.getMtHisTaskQueryServiceIcePrx().queryMtHisTaskPage(params);
			}
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				return RestResponse.RestResponseBuilder.createSuccessBuilder("运送记录查询成功")
						.setResult(rsp.paginator, rsp.resultList.toArray()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,查询运送记录失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,查询运送记录失败").buidler();
		}
	} 
	
	/**
	 * @Title: checkPageQueryParams   
	 *  检验分页查询的参数
	 * @author zhaoqing  
	 * @param params
	 * @return 
	 */
	private String checkPageQueryParams(MtTaskPageIceParam params) {
		String errorInfo = null;
		boolean flag = true;
		if (StringUtils.isNotEmpty(params.getUrgency()) && 
				!AppUtils.match(MtConstant.MT_TASK_URGENCY_REGEX, params.getUrgency())) {
			// 验证任务紧急程度
			errorInfo = "紧急程度输入有误!";
			flag = false;
		} 
		if (flag && StringUtils.isNotEmpty(params.getStatus()) && 
				!AppUtils.match(MtConstant.MT_TASK_STATUS_REGEX, params.getStatus())) {
			// 验证任务状态
			errorInfo = "任务状态输入有误!";
			flag = false;
		}
		if (flag && StringUtils.isNotEmpty(params.getTaskType()) && 
				!AppUtils.match(MtConstant.MT_TASK_TYPE_REGEX, params.getTaskType())) {
			// 验证任务类型
			errorInfo = "任务类型输入有误!";
			flag = false;
		}	
		String fields = "beginTime, endTime";
		String messages = "下单开始时间, 下单结束时间";
		errorInfo = CheckRestParams.checkDateByRegex(
				params, fields, messages, Constant.DATA_FORMATE_YYYY_MM_DD_REG);
		if (flag && null != errorInfo) {
			flag = false;
		}
		String beginTimeStr = DateUtil.converDateToNum(params.getBeginTime());
		String endTimeStr = DateUtil.converDateToNum(params.getEndTime());
		if (flag && Integer.parseInt(beginTimeStr) > Integer.parseInt(endTimeStr)) {
			errorInfo = "开始时间不能大于结束时间";
			flag = false;
		}
		if (flag && DateUtil.isTimeDifferYears(beginTimeStr, endTimeStr)) {
			errorInfo = "开始时间和结束时间不能跨年";
			flag = false;
		}
		if (flag && DateUtil.isMoreThanMonths(beginTimeStr, 
				endTimeStr, MtConstant.QUERY_TIME_SPAN_MONTH)) {
			errorInfo = "开始时间和结束时间最大跨度为3个月";
			flag = false;
		}
		params.setBeginTime(beginTimeStr);
		params.setEndTime(endTimeStr);
		// 验证来源科室Id
		if (flag && StringUtils.isNotBlank(params.getSourceHouseId())) {
			errorInfo = CheckRestParams.checkInteger(params, "sourceHouseId", "任务来源科室Id");
		}
		return errorInfo;
	}
	
	/**
	 * @Title: queryMtHisTaskDetail   
	 *  运送任务详情(调度/自主任务) 
	 * @author zhaoqing  
	 * @param user
	 * @param paramJson
	 * @return 
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "运送任务详情(调度/自主任务)", response = String.class, 
			notes = "{<br/>" + 
			"\"groupOrganId\":\"一级物业Id\",<br/>" +
			"\"taskId\":\"运送任务Id\",<br/>" +
			"\"cycleYm\":\"年月\"<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryMtHisTaskDetail.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryMtHisTaskDetail(@AdminUserParam User user, 
			@RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			// 前端传进来的json转对象
			MtTaskDetailIceParam params = FastjsonUtils.parseObject(paramJson, MtTaskDetailIceParam.class);
			String cycleYm = params.getCycleYm();
			// 年月转换成数字
			params.setCycleYm(DateUtil.converDateToNum(cycleYm));
			// 必填参数验证
			String fields = "taskId, groupOrganId, cycleYm";
			String messages = "运送任务id, 一级物业Id, 年月";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			// Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (!Pattern.matches(MtConstant.FORMAT_YYYY_MM_REGEX, cycleYm)) {
				// 检验月份格式
				return RestResponse.RestResponseBuilder.createFailBuilder(
						"月份格式错误，请传YYYY-MM格式的月份").buidler();
			}
			// 获取ICE服务
			MtTaskDetailRetIceRsp rsp = new MtTaskDetailRetIceRsp();
			if (ReportApiUtils.queryRptDataBaseFlag(params.getCycleYm())) {
				// 查询报表库
				rsp = this.getMtHisTaskRptQueryServiceIcePrx().queryMtHisTaskRptDetail(params);
			} else {
				// 查询业务库
				rsp = this.getMtHisTaskQueryServiceIcePrx().queryMtHisTaskDetail(params);
			}
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				return RestResponse.RestResponseBuilder.createSuccessBuilder("查询运送任务详情成功!")
						.setResult(rsp.getData()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,运送任务详情", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,运送任务详情").buidler();
		}
	} 
	
	/**
	 * @Title: queryMtHisTaskLoopDetail   
	 *  运送任务详情(固定任务) 
	 * @author zhaoqing  
	 * @param user
	 * @param paramJson
	 * @return 
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "运送任务详情(固定任务)", response = String.class, 
			notes = "{<br/>" + 
			"\"groupOrganId\":\"一级物业Id\",<br/>" +
			"\"taskId\":\"运送任务Id\",<br/>" +
			"\"cycleYm\":\"年月\"<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryMtHisTaskLoopDetail.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryMtHisTaskLoopDetail(@AdminUserParam User user, 
			@RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			// 前端传进来的json转对象
			MtTaskDetailIceParam params = FastjsonUtils.parseObject(paramJson, MtTaskDetailIceParam.class);
			String cycleYm = params.getCycleYm();
			// 年月转换成数字
			params.setCycleYm(DateUtil.converDateToNum(cycleYm));
			// 必填参数验证
			String fields = "taskId, groupOrganId, cycleYm";
			String messages = "运送任务id, 一级物业Id, 年月";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			// Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (!Pattern.matches(MtConstant.FORMAT_YYYY_MM_REGEX, cycleYm)) {
				// 检验月份格式
				return RestResponse.RestResponseBuilder.createFailBuilder(
						"月份格式错误，请传YYYY-MM格式的月份").buidler();
			}
			// 获取ICE服务
			MtTaskDetailRetIceRsp rsp = new MtTaskDetailRetIceRsp();
			if (ReportApiUtils.queryRptDataBaseFlag(params.getCycleYm())) {
				// 查询报表库
				rsp = this.getMtHisTaskRptQueryServiceIcePrx().queryMtHisTaskFixedRptDetail(params);
			} else {
				// 查询业务库
				rsp = this.getMtHisTaskQueryServiceIcePrx().queryMtHisTaskFixedDetail(params);
			}
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				return RestResponse.RestResponseBuilder.createSuccessBuilder("查询运送任务详情成功!")
						.setResult(rsp.getData()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,运送任务详情", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,运送任务详情").buidler();
		}
	}
	
	/**
	 * @Title: queryMtHisFixedTaskExeInfoPage   
	 *  固定任务执行信息分页查询 
	 * @author zhaoqing  
	 * @param user
	 * @param paramJson
	 * @return 
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "固定任务执行信息分页查询", response = String.class, 
			notes = "{<br/>" + 
			"\"taskId\":\"运送任务Id\",<br/>" +
			"\"groupOrganId\":\"一级物业Id\",<br/>" +
			"\"pageNo\":\"页码\",<br/>" +
			"\"pageLength\":\"每页记录数\",<br/>" +
			"\"organId\":\"项目Id\",<br/>" +
			"\"cycleYm\":\"年月\"<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryMtHisFixedTaskExeInfoPage.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryMtHisFixedTaskExeInfoPage(@AdminUserParam User user, 
			@RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			// 前端传进来的json转对象
			MtTaskPageIceParam params = FastjsonUtils.parseObject(paramJson, MtTaskPageIceParam.class);
			String cycleYm = params.getCycleYm();
			// 年月转换成数字
			params.setCycleYm(DateUtil.converDateToNum(cycleYm));
			// 必填参数验证
			String fields = "taskId, pageNo, pageLength, organId, groupOrganId, cycleYm";
			String messages = "项目(医院)Id, 页码, 每页记录数, 组织(医院)Id, 一级物业Id, 年月";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			// Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (!Pattern.matches(MtConstant.FORMAT_YYYY_MM_REGEX, cycleYm)) {
				// 检验月份格式
				return RestResponse.RestResponseBuilder.createFailBuilder(
						"月份格式错误，请传YYYY-MM格式的月份").buidler();
			}
			// 获取ICE服务
			FixedTaskHisExePageRsp rsp = new FixedTaskHisExePageRsp();
			if (ReportApiUtils.queryRptDataBaseFlag(params.getCycleYm())) {
				// 查询报表库
				rsp = this.getMtHisTaskRptQueryServiceIcePrx().queryMtHisFixedTaskExeInfoRptPage(params);
			} else {
				// 查询业务库
				rsp = this.getMtHisTaskQueryServiceIcePrx().queryMtHisFixedTaskExeInfoPage(params);
			}
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				return RestResponse.RestResponseBuilder.createSuccessBuilder("固定任务执行信息分页查询成功")
						.setResult(rsp.paginator, rsp.resultList.toArray()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,固定任务执行信息分页查询失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"系统异常,固定任务执行信息分页查询失败").buidler();
		}
	}
	
	/**
	 * @Title: queryMtHisFixedTaskExeDetail   
	 *  固定任务某个点执行信息详情 
	 * @author zhaoqing  
	 * @param user
	 * @param routeId
	 * @param organId
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "固定任务某个点执行信息详情", response = String.class, notes = "")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryMtHisFixedTaskExeDetail.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryMtHisFixedTaskExeDetail(@AdminUserParam User user, 
			@RequestParam(value="routeId", required=true) String routeId,
			@RequestParam(value="organId", required=true) String organId,
			@RequestParam(value="groupOrganId", required=true) String groupOrganId,
			@RequestParam(value="cycleYm", required=true) String cycleYm) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			MtTaskDetailIceParam params = new MtTaskDetailIceParam();
			params.setRouteId(routeId);
			params.setGroupOrganId(groupOrganId);
			params.setOrganId(organId);
			// 年月转换成数字
			params.setCycleYm(DateUtil.converDateToNum(cycleYm));
			// 必填参数验证
			String fields = "routeId, organId, groupOrganId";
			String messages = "运送线路Id, 组织(医院)Id, 一级物业Id";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			// Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (!Pattern.matches(MtConstant.FORMAT_YYYY_MM_REGEX, cycleYm)) {
				// 检验月份格式
				return RestResponse.RestResponseBuilder.createFailBuilder(
						"月份格式错误，请传YYYY-MM格式的月份").buidler();
			}
			// 获取ICE服务
			FixedTaskExeDetailReturnIce rsp = new FixedTaskExeDetailReturnIce();
			if (ReportApiUtils.queryRptDataBaseFlag(params.getCycleYm())) {
				// 查询报表库
				rsp = this.getMtHisTaskRptQueryServiceIcePrx().queryMtHisFixedTaskExeRptDetail(params);
			} else {
				// 查询业务库
				rsp = this.getMtHisTaskQueryServiceIcePrx().queryMtHisFixedTaskExeDetail(params);
			}
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				return RestResponse.RestResponseBuilder.createSuccessBuilder("查询固定任务某个点执行信息详情成功!")
						.setResult(rsp.getData()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,固定任务某个点执行信息详情失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"系统异常,固定任务某个点执行信息详情失败").buidler();
		}
	}
	
	/**
	 * @Title: exportMtHisTaskPage   
	 *  运送记录导出 
	 * @author zhaoqing  
	 * @param user
	 * @param response
	 * @param organId
	 * @param transTypeParentCode
	 * @param transTypeId
	 * @param beginTime
	 * @param endTime
	 * @param status
	 * @param taskType
	 * @param sourceHouseId
	 * @param urgency 
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "运送记录导出", response = String.class, 
			notes = "运送记录导出")
	@ApiImplicitParams({})
	@RequestMapping(value = "/exportMtHisTaskPage.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse exportMtHisTaskPage(@AdminUserParam User user, HttpServletResponse response,
            @RequestParam(value = "organId", required = true) String organId,
            @RequestParam(value = "transTypeParentCode", required = false) String transTypeParentCode,
            @RequestParam(value = "transTypeId", required = false) String transTypeId,            
            @RequestParam(value = "beginTime", required = true) String beginTime,
            @RequestParam(value = "endTime", required = true) String endTime,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "taskType", required = false) String taskType,
            @RequestParam(value = "sourceHouseId", required = false) String sourceHouseId,
            @RequestParam(value = "urgency", required = false) String urgency) {
		try {
			if (null == user) {
	            return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
	        }
			// 下载并发次数新增
			boolean exeFlag = AppConCurrentUtils.getInstace().addConCurCount(
					AppConCurrentTypeConstant.MAX_CURRENCY_EXCEL_EXP);
			if (!exeFlag) {
				return RestResponse.RestResponseBuilder.createSuccessBuilder(
						"当前数据导出操作的人数过多，请稍后再试！").buidler();
			}
			MtTaskPageIceParam params = new MtTaskPageIceParam();
			params.setOrganId(organId);
			params.setUrgency(urgency);
			params.setStatus(status);
			params.setTaskType(taskType);
			params.setBeginTime(beginTime);
			params.setEndTime(endTime);
			params.setSourceHouseId(sourceHouseId);
			params.setTransTypeParentCode(transTypeParentCode);
			params.setTransTypeId(transTypeId);
			// Number类型格式验证
			String errInfo = CheckRestParams.checkInteger(params, "organId", "项目(医院)Id");
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler(); 
			}	
			// 参数校验
			errInfo = checkPageQueryParams(params);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			String pageLength = MtConstant.EXPORT_EVERY_PAGE_LENGTH_V1;
			// 设置长度为1000
            params.setPageLength(pageLength);
            params.setPageNo(MtConstant.EXPORT_PAGE_NO);
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
			// 运送记录数据导出 
			return exportMtHisTaskExcel(response, resultList);	
		} catch (Exception e) {
			logger.warn("系统异常,运送记录导出失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"系统异常,运送记录导出失败,请联系系统管理员！").buidler();
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
		if (ReportApiUtils.queryRptDataBaseFlag(params.getBeginTime())) {
			// 查询报表库
			resource = this.getMtHisTaskRptQueryServiceIcePrx().queryMtHisTaskRptPage(params);
		} else {
			// 查询业务库
			resource = this.getMtHisTaskQueryServiceIcePrx().queryMtHisTaskPage(params);
		}
		return resource;		
	}
	
	/**
	 * @Title: exportMtHisTaskExcel   
	 *  运送记录数据导出 
	 * @author zhaoqing  
	 * @param response
	 * @param resource 
	 */
	private RestResponse exportMtHisTaskExcel(HttpServletResponse response, List<MtTaskPageIce> resultList) {
		try {
			File file = null;
			String title = "运送记录导出.xls";
			String[] columnTitles = {"所属项目", "任务Id", "运送开始时间", "运送结束时间", "运送来源", "运送类型", 
					"紧急程度", "出发地", "目的地", "运送员", "派单类型", "状态", "任务类型", "运送内容描述", "派单人", 
					"派单时间", "任务实际开始时间", "任务实际结束时间", "签收时间", "签收人", "评价结论", "评价信息"};		
			String[] data = null;
			List<String[]> dataList = new ArrayList<String[]>();
			if (AppUtils.isNotEmpty(resultList)) {;
				for (int i = 0; i < resultList.size(); i++) {
					MtTaskPageIce taskIce = resultList.get(i);
					data = new String[22];		
					data[0] = taskIce.getOrganName(); // 所属项目
					data[1] = taskIce.getTaskId(); // 任务Id
					data[2] = taskIce.getBeginTime(); // 运送开始时间
					data[3] = taskIce.getEndTime(); // 运送结束时间
					data[4] = taskIce.getSourceHouseName(); // 运送来源
					data[5] = taskIce.getTransTypeParentCodeName(); // 运送类型
					data[6] = taskIce.getUrgencyName(); // 紧急程度
					data[7] = taskIce.getFromHouseName(); // 出发地
					data[8] = taskIce.getToHouseName(); // 目的地
					if (taskIce.getUserList() != null
							&& AppUtils.isNotEmpty(taskIce.getUserList())) {
						List<ExeUser> userList = taskIce.getUserList();
						List<String> userNameNoList = AppUtils.list2ParamsList(
								userList, ExeUser.class, "userNameNo");
						data[9] = AppUtils.listToString(userNameNoList, ','); // 运送员
					}
					data[10] = taskIce.getResTypeName(); // 派单类型
					data[11] = taskIce.getStatusName(); // 状态
					data[12] = taskIce.getTaskTypeName(); // 任务类型
					data[13] = taskIce.getTaskContent(); // 运送内容描述	
					data[14] = taskIce.getDispatchUserName(); // 派单人
					data[15] = taskIce.getSendTime(); // 派单时间
					data[16] = taskIce.getExeBeginTime(); // 任务实际开始时间
					data[17] = taskIce.getExeEndTime(); // 任务实际结束时间
					data[18] = taskIce.getReceiveTime(); // 签收时间
					data[19] = taskIce.getReceiverUserName(); // 签收人
					data[20] = taskIce.getEvaluate(); // 评价结论
					data[21] = taskIce.getEvaluateContent(); // 评价信息
					dataList.add(data);
				}
			}
			file = ExcelWriter.simpleCreate(RestApiConstant.MEDICALTRANS, columnTitles, dataList);
			ResponseDownloadUtils.downloadAndDelete(response, file, title);
			return null; 
		} catch (Exception e) {
			logger.error("运送记录数据导出异常", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("运送记录数据导出异常！").buidler();
		}
	}
}
