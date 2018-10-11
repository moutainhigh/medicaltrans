package com.segi.uhomecp.medicaltrans.taskloop.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import resp.RpcRespIce;
import segi.medicaltrans.base.taskloop.House;
import segi.medicaltrans.base.taskloop.HouseInfo;
import segi.medicaltrans.base.taskloop.TaskLoopIce;
import segi.medicaltrans.base.taskloop.TaskLoopInfo;
import segi.medicaltrans.base.taskloop.TaskLoopPageParam;
import segi.medicaltrans.base.taskloop.TaskLoopPaginator;
import segi.medicaltrans.base.taskloop.TaskLoopParam;
import segi.medicaltrans.base.taskloop.TaskLoopServiceIcePrx;
import segi.medicaltrans.base.taskloop.TaskLoopStatusParam;
import segi.medicaltrans.base.taskloop.UserInfo;
import IceExt.IceClientUtil;

import com.alibaba.fastjson.JSONObject;
import com.segi.uhomecp.common.constant.RestApiConstant;
import com.segi.uhomecp.common.rest.AbsActionRest;
import com.segi.uhomecp.medicaltrans.constant.DateConstant;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.utils.ResponseDownloadUtils;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.sso.auth.vo.User;
import com.segi.uhomecp.sso.client.annotation.AdminUserParam;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.web.rest.RestResponse;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.CheckRestParams;
import com.segi.uhomecp.wh.common.utils.ExcelWriter;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * Title: MtTaskLoopWebRest.java
 * @Description: 描述
 * @author yangyh@segimail.com
 * @created 2018年3月29日 下午4:17:39
 */
@Controller
@RequestMapping(RestApiConstant.API_PATH + "/medicaltrans/mtTaskLoopWebRest")
@Api(value = "medicaltrans/mtTaskLoopWebRest", description = "循环任务管理")
public class MtTaskLoopWebRest extends AbsActionRest {

	private static final Logger logger = LoggerFactory.getLogger(MtTaskLoopWebRest.class);

	private static final String PREFIXPROJECTNAME = RestApiConstant.PREFIXPROJECTNAME;

	private TaskLoopServiceIcePrx getTaskLoopServiceIcePrx() {
		return IceClientUtil.getServicePrxByClass(TaskLoopServiceIcePrx.class);
	}

	/**
	 * @discription 循环任务新建
	 * @author yangyh@segimail.com
	 * @created 2018年3月29日 下午4:59:03
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "循环任务新建", response = String.class, notes = "{<br/>"
			+ "\"organId\":\"所属项目id\",<br/>"
			+ "\"taskName\":\"循环任务名称\",<br/>"
			+ "\"transTypeParentCode\":\"运送大类code\",<br/>"
			+ "\"houseIds\":\"运送线路（科室id，多个以,分隔，请按照先后顺序拼组起来）\",<br/>"
			+ "\"transTools\":\"运送工具code\",<br/>"
			+ "\"loopType\":\"循环任务排班类型（1按日按月； 2按周）\",<br/>"
			+ "\"loopMonths\":\"循环任务月（loopType为月日必传）\",<br/>"
			+ "\"loopWeeks\":\"循环任务周（loopType为周必传）\",<br/>"
			+ "\"loopDays\":\"循环任务日（loopType为月日必传）\",<br/>"
			+ "\"taskBeginTime\":\"任务开始时间\",<br/>"
			+ "\"taskEndTime\":\"任务结束时间\",<br/>"
			+ "\"userIds\":\"执行人（多个以,分隔）\",<br/>"
			+ "\"preGenerateMinute\":\"提前触发时间\"<br/>" + "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/saveTaskLoop.json", method = {RequestMethod.POST})
	@ResponseBody
	public RestResponse saveTaskLoop(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (user == null) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录，请重新登录!").buidler();
		}
		try {
			TaskLoopParam params = FastjsonUtils.parseObject(paramJson, TaskLoopParam.class);
			// 判断入参是否为空
			String fields = "organId, taskName, transTypeParentCode, houseIds, transTools,"
					+ "loopType, taskBeginTime, taskEndTime, userIds, preGenerateMinute";
			String messages = "所属项目id, 循环任务名称, 运送大类code, 运送线路, 运送工具code," + "循环任务排班类型, 任务开始时间, 任务结束时间, 执行人, 提前触发时间";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			String loopType = params.getLoopType();
			String weeks = params.getLoopWeeks();
			String months = params.getLoopMonths();
			String days = params.getLoopDays();
			// 排班类型 1按日按月
			if (MtConstant.MT_TASK_LOOP_LOOP_TYPE_MONTH.equals(loopType)) {
				if (StringUtils.isNotBlank(weeks)) {
					return RestResponse.RestResponseBuilder.createFailBuilder("周不应传值!").buidler();
				}
				if (StringUtils.isBlank(months)) {
					return RestResponse.RestResponseBuilder.createFailBuilder("选择月为空!").buidler();
				}
				if (StringUtils.isBlank(days)) {
					return RestResponse.RestResponseBuilder.createFailBuilder("选择日为空!").buidler();
				}
				if (!"*".equals(months)
						&& !AppUtils.match(MtConstant.MT_TASK_LOOP_LOOP_MONTH_REGEX, months + Constant.SPLIT_COMMA)) {
					return RestResponse.RestResponseBuilder.createFailBuilder("月格式输入有误!").buidler();
				}
				if (!"*".equals(days)
						&& !AppUtils.match(MtConstant.MT_TASK_LOOP_LOOP_DAY_REGEX, days + Constant.SPLIT_COMMA)) {
					return RestResponse.RestResponseBuilder.createFailBuilder("日格式输入有误!").buidler();
				}
				String[] monthsArr = months.split(",");
				String[] daysArr = days.split(",");
				// 月和日不能存在重复
				Set<String> monthsSet = new HashSet<String>(Arrays.asList(monthsArr));
				Set<String> daysSet = new HashSet<String>(Arrays.asList(daysArr));
				if (monthsSet.size() < monthsArr.length) {
					return RestResponse.RestResponseBuilder.createFailBuilder("月输入重复!").buidler();
				}
				if (daysSet.size() < daysArr.length) {
					return RestResponse.RestResponseBuilder.createFailBuilder("日输入重复!").buidler();
				}
				Map<Boolean, String> map = DateConstant.isValidExpression(monthsArr, daysArr);
				if (map.size() != 0) {
					return RestResponse.RestResponseBuilder.createFailBuilder(map.get(false)).buidler();
				}
			}
			// 排班类型 2按周
			if (MtConstant.MT_TASK_LOOP_LOOP_TYPE_WEEK.equals(loopType)) {
				if (StringUtils.isNotBlank(months)) {
					return RestResponse.RestResponseBuilder.createFailBuilder("月不应传值!").buidler();
				}
				if (StringUtils.isNotBlank(days)) {
					return RestResponse.RestResponseBuilder.createFailBuilder("日不应传值!").buidler();
				}
				if (StringUtils.isBlank(weeks)) {
					return RestResponse.RestResponseBuilder.createFailBuilder("选择周为空!").buidler();
				}
				if (!AppUtils.match(MtConstant.MT_TASK_LOOP_LOOP_WEEK_REGEX, weeks + Constant.SPLIT_COMMA)) {
					return RestResponse.RestResponseBuilder.createFailBuilder("周输入有误!").buidler();
				}
				String[] weeksArr = weeks.split(",");
				// 月和日不能存在重复
				Set<String> weeksSet = new HashSet<String>(Arrays.asList(weeksArr));
				if (weeksSet.size() < weeksArr.length) {
					return RestResponse.RestResponseBuilder.createFailBuilder("周输入重复!").buidler();
				}
			}
			// 判断入参是否为数字类型
			fields = "organId, loopType, preGenerateMinute";
			messages = "所属项目id, 循环任务排班类型, 提前触发时间";
			errInfo = CheckRestParams.checkDigits(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (!AppUtils.match(MtConstant.MT_TASK_LOOP_LOOP_TYPE_REGEX, loopType)) {
				// 验证循环任务排班类型
				return RestResponse.RestResponseBuilder.createFailBuilder("排班类型输入有误,只能取1,2!").buidler();
			}
			if (params.getHouseIds().split(",").length < 2) {
				// 验证循环任务路线至少两个
				return RestResponse.RestResponseBuilder.createFailBuilder("路线输入有误,至少选两个!").buidler();
			}
			// 处理时间
			params.setTaskBeginTime(params.getTaskBeginTime().replaceAll(":", ""));
			params.setTaskEndTime(params.getTaskEndTime().replaceAll(":", ""));
			// 获取当前用户id
			params.setCreateBy(String.valueOf(user.getUserId()));
			RpcRespIce rsp = this.getTaskLoopServiceIcePrx().saveTaskLoop(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.code)) {
				return RestResponse.RestResponseBuilder.createSuccessBuilder("循环任务新建成功！").setResult(rsp.data).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常，循环任务新建失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常，循环任务新建失败！").buidler();
		}
	}

	/**
	 * @discription 循环任务编辑
	 * @author yangyh@segimail.com
	 * @created 2018年3月30日 下午2:35:48
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "循环任务编辑", response = String.class, notes = "{<br/>"
			+ "\"taskLoopId\":\"循环任务主键id\",<br/>"
			+ "\"organId\":\"所属项目id\",<br/>"
			+ "\"taskName\":\"循环任务名称\",<br/>"
			+ "\"transTypeParentCode\":\"运送大类code\",<br/>"
			+ "\"houseIds\":\"运送线路（科室id，多个以,分隔，请按照先后顺序拼组起来）\",<br/>"
			+ "\"transTools\":\"运送工具code\",<br/>"
			+ "\"loopType\":\"循环任务排班类型（1按日按月； 2按周）\",<br/>"
			+ "\"loopMonths\":\"循环任务月（loopType为月日必传）\",<br/>"
			+ "\"loopWeeks\":\"循环任务周（loopType为周必传）\",<br/>"
			+ "\"loopDays\":\"循环任务日（loopType为月日必传）\",<br/>"
			+ "\"taskBeginTime\":\"任务开始时间\",<br/>"
			+ "\"taskEndTime\":\"任务结束时间\",<br/>"
			+ "\"userIds\":\"执行人（多个以,分隔）\",<br/>"
			+ "\"preGenerateMinute\":\"提前触发时间\"<br/>" + "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/updateTaskLoop.json", method = {RequestMethod.POST})
	@ResponseBody
	public RestResponse updateTaskLoop(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (user == null) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录，请重新登录!").buidler();
		}
		try {
			TaskLoopParam params = FastjsonUtils.parseObject(paramJson, TaskLoopParam.class);
			// 判断入参是否为空
			String fields = "taskLoopId, organId, taskName, transTypeParentCode, houseIds, transTools,"
					+ "loopType, taskBeginTime, taskEndTime, userIds, preGenerateMinute";
			String messages = "循环任务主键id, 所属项目id, 循环任务名称, 运送大类code, 运送线路, 运送工具code,"
					+ "循环任务排班类型, 任务开始时间, 任务结束时间, 执行人, 提前触发时间";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			String loopType = params.getLoopType();
			String weeks = params.getLoopWeeks();
			String months = params.getLoopMonths();
			String days = params.getLoopDays();
			// 排班类型 1按日按月
			if (MtConstant.MT_TASK_LOOP_LOOP_TYPE_MONTH.equals(loopType)) {
				if (StringUtils.isNotBlank(weeks)) {
					return RestResponse.RestResponseBuilder.createFailBuilder("周不应传值!").buidler();
				}
				if (StringUtils.isBlank(months)) {
					return RestResponse.RestResponseBuilder.createFailBuilder("选择月为空!").buidler();
				}
				if (StringUtils.isBlank(days)) {
					return RestResponse.RestResponseBuilder.createFailBuilder("选择日为空!").buidler();
				}
				if (!"*".equals(months)
						&& !AppUtils.match(MtConstant.MT_TASK_LOOP_LOOP_MONTH_REGEX, months + Constant.SPLIT_COMMA)) {
					return RestResponse.RestResponseBuilder.createFailBuilder("月格式输入有误!").buidler();
				}
				if (!"*".equals(days)
						&& !AppUtils.match(MtConstant.MT_TASK_LOOP_LOOP_DAY_REGEX, days + Constant.SPLIT_COMMA)) {
					return RestResponse.RestResponseBuilder.createFailBuilder("日格式输入有误!").buidler();
				}
				Map<Boolean, String> map = DateConstant.isValidExpression(months.split(","), days.split(","));
				if (map.size() != 0) {
					return RestResponse.RestResponseBuilder.createFailBuilder(map.get(false)).buidler();
				}
			}
			// 排班类型 2按周
			if (MtConstant.MT_TASK_LOOP_LOOP_TYPE_WEEK.equals(loopType)) {
				if (StringUtils.isNotBlank(months)) {
					return RestResponse.RestResponseBuilder.createFailBuilder("月不应传值!").buidler();
				}
				if (StringUtils.isNotBlank(days)) {
					return RestResponse.RestResponseBuilder.createFailBuilder("日不应传值!").buidler();
				}
				if (StringUtils.isBlank(weeks)) {
					return RestResponse.RestResponseBuilder.createFailBuilder("选择周为空!").buidler();
				}
				if (!AppUtils.match(MtConstant.MT_TASK_LOOP_LOOP_WEEK_REGEX, weeks + Constant.SPLIT_COMMA)) {
					return RestResponse.RestResponseBuilder.createFailBuilder("周输入有误!").buidler();
				}
			}
			// 判断入参是否为数字类型
			fields = "taskLoopId, organId, loopType, preGenerateMinute";
			messages = "循环任务主键id, 所属项目id, 循环任务排班类型, 提前触发时间";
			errInfo = CheckRestParams.checkDigits(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (!AppUtils.match(MtConstant.MT_TASK_LOOP_LOOP_TYPE_REGEX, loopType)) {
				// 验证循环任务排班类型
				return RestResponse.RestResponseBuilder.createFailBuilder("排班类型输入有误,只能取1,2!").buidler();
			}
			if (params.getHouseIds().split(",").length < 2) {
				// 验证循环任务路线至少两个
				return RestResponse.RestResponseBuilder.createFailBuilder("路线输入有误,至少选两个!").buidler();
			}
			// 处理时间
			params.setTaskBeginTime(params.getTaskBeginTime().replaceAll(":", ""));
			params.setTaskEndTime(params.getTaskEndTime().replaceAll(":", ""));
			// 获取当前用户id
			params.setUpdateBy(String.valueOf(user.getUserId()));
			RpcRespIce rsp = this.getTaskLoopServiceIcePrx().updateTaskLoop(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.code)) {
				return RestResponse.RestResponseBuilder.createSuccessBuilder("循环任务编辑成功！").setResult(rsp.data).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常，循环任务编辑失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常，循环任务编辑失败！").buidler();
		}
	}

	/**
	 * @discription 循环任务停用启用、删除
	 * @author yangyh@segimail.com
	 * @created 2018年3月30日 下午4:21:30
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "循环任务停用启用、删除", response = String.class, notes = "{<br/>"
			+ "\"organId\":\"组织机构id\",<br/>"
			+ "\"taskLoopId\":\"循环任务主键id\",<br/>"
			+ "\"status\":\"状态：1有效；2停用；0已删除\"<br/>" + "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/updateTaskLoopStatus.json", method = {RequestMethod.POST})
	@ResponseBody
	public RestResponse updateTaskLoopStatus(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (user == null) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录，请重新登录!").buidler();
		}
		try {
			TaskLoopStatusParam params = FastjsonUtils.parseObject(paramJson, TaskLoopStatusParam.class);
			// 判断入参是否为空
			String fields = "organId,taskLoopId, status";
			String messages = "组织机构id,循环任务主键id, 状态";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			// 判断入参是否为数字类型
			fields = "taskLoopId";
			messages = "循环任务主键id";
			errInfo = CheckRestParams.checkDigits(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (!AppUtils.match(MtConstant.MT_TASK_LOOP_STATUS_REGEX, params.getStatus())) {
				// 验证循环任务状态
				return RestResponse.RestResponseBuilder.createFailBuilder("任务状态输入有误,只能取0,1,2!").buidler();
			}
			// 获取当前用户id
			params.setUpdateBy(String.valueOf(user.getUserId()));
			//当人工置为停用的时候，将停用原因插入
			if(Constant.STATUS_CD_STOP.equals(params.getStatus())){
				params.setLoseRemark(MtConstant.HANDLE_LOST);
			}
			RpcRespIce rsp = this.getTaskLoopServiceIcePrx().updateTaskLoopStatus(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.code)) {
				if (Constant.STATUS_CD_DEL.equals(params.getStatus())) {
					rsp.message = "循环任务删除成功！";
				}
				if (Constant.STATUS_CD_NORMAL.equals(params.getStatus())) {
					rsp.message = "循环任务启用成功！";
				}
				if (Constant.STATUS_CD_STOP.equals(params.getStatus())) {
					rsp.message = "循环任务停用成功！";
				}
				return RestResponse.RestResponseBuilder.createSuccessBuilder(rsp.message).setResult(rsp.data).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常，循环任务停用/启用/删除失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常，循环任务停用/启用/删除失败！").buidler();
		}
	}

	/**
	 * @discription 循环任务详情展示
	 * @author yangyh@segimail.com
	 * @created 2018年3月30日 下午5:09:39
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "循环任务详情展示", response = String.class, notes = "{<br/>"
			+ "\"taskLoopId\":\"循环任务主键id\"<br/>" + "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryTaskLoopDetail.json", method = {RequestMethod.POST})
	@ResponseBody
	public RestResponse queryTaskLoopDetail(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (user == null) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录，请重新登录!").buidler();
		}
		try {
			JSONObject jsonObject = FastjsonUtils.parseObject(paramJson);
			String taskLoopId = jsonObject.getString("taskLoopId");
			if (StringUtils.isEmpty(taskLoopId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder("循环任务主键id为空").buidler();
			}
			if (!NumberUtils.isDigits(taskLoopId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder("循环任务主键id不为数字类型").buidler();
			}
			TaskLoopInfo rsp = this.getTaskLoopServiceIcePrx().queryTaskLoopDetail(taskLoopId);
			if (RpcError.SUCCESS.getCode().equals(rsp.code)) {
				return RestResponse.RestResponseBuilder.createSuccessBuilder("循环任务详情展示成功！").setResult(rsp.taskLoopIce)
						.buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.msg).buidler();
		} catch (Exception e) {
			logger.warn("系统异常，循环任务详情展示失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常，循环任务详情展示失败！").buidler();
		}
	}

	/**
	 * @discription 循环任务分页列表
	 * @author yangyh@segimail.com
	 * @created 2018年3月30日 下午7:10:57
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "循环任务分页列表", response = String.class, notes = "{<br/>"
			+ "\"organId\":\"项目id\",<br/>"
			+ "\"transTypeParentCode\":\"运送大类（全部则不传）\",<br/>"
			+ "\"taskName\":\"循环任务名称\",<br/>"
			+ "\"status\":\"循环任务状态\",<br/>"
			+ "\"pageNo\":\"当前页码\",<br/>"
			+ "\"pageLength\":\"每页条数\"<br/>" + "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryTaskLoopByPage.json", method = {RequestMethod.POST})
	@ResponseBody
	public RestResponse queryTaskLoopByPage(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (user == null) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录，请重新登录!").buidler();
		}
		try {
			TaskLoopPageParam params = FastjsonUtils.parseObject(paramJson, TaskLoopPageParam.class);
			// 判断入参是否为空
			String fields = "organId, pageNo, pageLength";
			String messages = "所属项目id, 当前页码, 每页条数";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			// 判断入参是否为数字类型
			fields = "organId, pageNo, pageLength";
			messages = "所属项目id, 当前页码, 每页条数";
			errInfo = CheckRestParams.checkDigits(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			TaskLoopPaginator rsp = this.getTaskLoopServiceIcePrx().queryTaskLoopByPage(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.code)) {
				Map<String, Object> map = new HashMap<>();
				map.put("paginator", rsp.paginator);
				map.put("resultList", rsp.resultList);
				return RestResponse.RestResponseBuilder.createSuccessBuilder("循环任务分页列表查询成功！").setResult(map).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.msg).buidler();
		} catch (Exception e) {
			logger.warn("系统异常，循环任务分页列表查询失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常，循环任务分页列表查询失败！").buidler();
		}
	}

	/**
	 * 
	 * 类描述: 循环任务导出 创建人: liuyi@sige.com 创建时间: 2018年5月8日 下午7:07:53
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "循环任务导出", response = String.class, notes = "循环任务导出")
	@ApiImplicitParams({})
	@RequestMapping(value = "/exportTaskLoop.json", method = {RequestMethod.GET})
	@ResponseBody
	public void exportTaskLoop(@AdminUserParam User user,HttpServletResponse response,
			@RequestParam(value = "organId", required = true) String organId,
			@RequestParam(value = "transTypeParentCode", required = false) String transTypeParentCode,
			@RequestParam(value = "taskName", required = false) String taskName,
			@RequestParam(value = "status", required = false) String status) {
		File file = null;
		try {
			TaskLoopPageParam params = new TaskLoopPageParam();
			// Number类型格式验证
			String fields = "organId";
			String messages = "项目(医院)Id";
			params.setOrganId(organId);
			String errInfo = CheckRestParams.checkInteger(params, fields,
					messages);
			if (null != errInfo) {
				return;
			}
			if (StringUtils.isNotBlank(transTypeParentCode)) {
				params.setTransTypeParentCode(transTypeParentCode);
			}
			if (StringUtils.isNotBlank(taskName)) {
				params.setTaskName(taskName);
			}
			if (StringUtils.isNotBlank(status)) {
				params.setStatus(status);
			}
			// 设置长度为5000
            params.setPageLength(MtConstant.EXPORT_PAGE_LENGTH);
            params.setPageNo(MtConstant.EXPORT_PAGE_NO);
			TaskLoopPaginator resource = this.getTaskLoopServiceIcePrx()
					.queryTaskLoopByPage(params);
			String title = "循环任务管理导出.xls";
			String[] columnTitles = {"所属项目", "循环任务ID", "循环任务名称", "运送类型", "运送工具", "开始时间",
					"结束时间", "触发时间(提前分钟)", "执行人", "状态", "失效原因"};
			String[] data = null;
			List<String[]> dataList = new ArrayList<String[]>();
			if (resource.getResultList() != null
					&& resource.getResultList().size() > 0) {
				List<TaskLoopIce> resultList = resource.getResultList();
				for (int i = 0; i < resultList.size(); i++) {
					TaskLoopIce taskLoopIce = resultList.get(i);
					data = new String[11];
					data[0] = taskLoopIce.getOrganName(); // 所属组织
					data[1] = taskLoopIce.getTaskLoopId(); // 循环任务ID
					data[2] = taskLoopIce.getTaskName(); // 循环任务名称
					data[3] = taskLoopIce.getTransTypeParentName(); // 运送类型
					data[4] = taskLoopIce.getTransToolsName(); // 运送工具
					data[5] = taskLoopIce.getTaskBeginTimeStr(); // 开始时间
					data[6] = taskLoopIce.getTaskEndTimeStr(); // 结束时间
					data[7] = taskLoopIce.getPreGenerateMinute(); // 触发时间(提前分钟)
					data[8] = taskLoopIce.getUserNames(); // 执行人
					data[9] = taskLoopIce.getStatusName(); // 状态
					data[10] = taskLoopIce.getLoseRemark(); // 失效原因
					dataList.add(data);
				}
			}
			file = ExcelWriter.simpleCreate(RestApiConstant.MEDICALTRANS,
					columnTitles, dataList);
			ResponseDownloadUtils.downloadAndDelete(response, file, title);
		} catch (Exception e) {
			logger.warn("系统异常，循环任务分页列表查询失败！", e);
		}
	}
	
	/**
	 * @discription 根据循环任务id查询科室信息列表(带状态)
	 * @author yangyh@segimail.com
	 * @created 2018年6月6日 下午11:20:39
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "科室信息列表展示", response = String.class, notes = "{<br/>"
			+ "\"taskLoopId\":\"循环任务主键id\"<br/>" + "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryHouseList.json", method = {RequestMethod.POST})
	@ResponseBody
	public RestResponse queryHouseList(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (user == null) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录，请重新登录!").buidler();
		}
		try {
			JSONObject jsonObject = FastjsonUtils.parseObject(paramJson);
			String taskLoopId = jsonObject.getString("taskLoopId");
			if (StringUtils.isEmpty(taskLoopId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder("循环任务主键id为空").buidler();
			}
			if (!NumberUtils.isDigits(taskLoopId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder("循环任务主键id不为数字类型").buidler();
			}
			HouseInfo rsp = this.getTaskLoopServiceIcePrx().queryHouseList(taskLoopId);
			if (RpcError.SUCCESS.getCode().equals(rsp.code)) {
				Map<String,List<House>> map = new HashMap<>();
				map.put("houseList", rsp.houseList);
				return RestResponse.RestResponseBuilder.createSuccessBuilder("科室信息列表展示成功！").setResult(map).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.msg).buidler();
		} catch (Exception e) {
			logger.warn("系统异常，科室信息列表展示失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常，科室信息列表展示失败！").buidler();
		}
	}
	
	/**
	 * @discription 根据循环任务id查询人员信息列表(带状态)
	 * @author yangyh@segimail.com
	 * @created 2018年6月19日 下午9:58:39
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "人员信息列表展示", response = String.class, notes = "{<br/>"
			+ "\"taskLoopId\":\"循环任务主键id\"<br/>" + "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryUserList.json", method = {RequestMethod.POST})
	@ResponseBody
	public RestResponse queryUserList(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (user == null) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录，请重新登录!").buidler();
		}
		try {
			JSONObject jsonObject = FastjsonUtils.parseObject(paramJson);
			String taskLoopId = jsonObject.getString("taskLoopId");
			if (StringUtils.isEmpty(taskLoopId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder("循环任务主键id为空").buidler();
			}
			if (!NumberUtils.isDigits(taskLoopId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder("循环任务主键id不为数字类型").buidler();
			}
			UserInfo rsp = this.getTaskLoopServiceIcePrx().queryUserInfoList(taskLoopId);
			if (RpcError.SUCCESS.getCode().equals(rsp.code)) {
				Map<String,List<segiwh.common.User>> map = new HashMap<>();
				map.put("userList", rsp.userList);
				return RestResponse.RestResponseBuilder.createSuccessBuilder("人员信息列表展示成功！").setResult(map).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.msg).buidler();
		} catch (Exception e) {
			logger.warn("系统异常，人员信息列表展示失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常，人员信息列表展示失败！").buidler();
		}
	}
}
