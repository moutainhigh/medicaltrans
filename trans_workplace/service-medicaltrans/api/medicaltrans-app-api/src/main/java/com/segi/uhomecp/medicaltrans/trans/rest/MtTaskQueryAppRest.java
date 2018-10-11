package com.segi.uhomecp.medicaltrans.trans.rest;

import java.util.HashMap;
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

import segi.medicaltrans.mttask.query.FixedTaskExeDetailReturnIce;
import segi.medicaltrans.mttask.query.FixedTaskExePaginatorIceRsp;
import segi.medicaltrans.mttask.query.MtAutonomousTaskDetailIceRsp;
import segi.medicaltrans.mttask.query.MtAutonomousTaskPaginatorIceRsp;
import segi.medicaltrans.mttask.query.MtDispatchTaskDetailIceRsp;
import segi.medicaltrans.mttask.query.MtDispatchTaskPaginatorIceRsp;
import segi.medicaltrans.mttask.query.MtFixedTaskDetailIceRsp;
import segi.medicaltrans.mttask.query.MtFixedTaskPaginatorIceRsp;
import segi.medicaltrans.mttask.query.MtTaskPageIceParam;
import segi.medicaltrans.mttask.query.MtTaskQueryServiceIcePrx;
import segi.medicaltrans.report.pervolume.month.PerTaskAmoMonthParam;
import segi.medicaltrans.report.pervolume.month.PersonalVolumeMonthPaginator;
import segi.medicaltrans.report.pervolume.month.PersonalVolumeMonthServiceIcePrx;
import IceExt.IceClientUtil;

import com.segi.uhomecp.common.constant.RestApiConstant;
import com.segi.uhomecp.common.rest.AbsActionRest;
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

@Controller
@RequestMapping(RestApiConstant.API_PATH + "/medicaltrans/mtTaskQueryAppRest")
@Api(value = "medicaltrans/mtTaskQueryAppRest", description = "医院运送公共接口(app端)")
public class MtTaskQueryAppRest extends AbsActionRest {
	
	private static final Logger logger = LoggerFactory.getLogger(MtTaskQueryAppRest.class);
	
	private static final String PREFIXPROJECTNAME = RestApiConstant.PREFIXPROJECTNAME;
	
	/**
	 * @discription 获取ice服务
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月4日 下午12:01:18     
	 * @return
	 */
	private MtTaskQueryServiceIcePrx getMtTaskQueryServiceIcePrx() {
		return IceClientUtil.getServicePrxByClass(MtTaskQueryServiceIcePrx.class);
	}
	
	private PersonalVolumeMonthServiceIcePrx getPersonalVolumeMonthServiceIcePrx() {
		return IceClientUtil.getServicePrxByClass(PersonalVolumeMonthServiceIcePrx.class);
	}
	
	/**
	 * 
	 * 类描述: 查询调度任务详情
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年4月20日 下午2:54:41
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "调度任务详情", response = String.class, notes = "{<br/>"
            + "\"taskId\":\"运送任务Id\",<br/>" 
            + "}<br/>")
    @ApiImplicitParams({})
    @RequestMapping(value = "/queryMtDispatchTaskDetail.json", method = { RequestMethod.GET })
    @ResponseBody
    public RestResponse queryMtDispatchTaskDetail(@AdminUserParam User user, HttpServletResponse response,
    @RequestParam(value = "taskId", required = true) String taskId) {
		if (null == user) {
            return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
        }
        try {
			if (!NumberUtils.isDigits(String.valueOf(taskId))) {
				return RestResponse.RestResponseBuilder.createFailBuilder("参数[taskId]入参有误!").buidler();
			}
            // 获取ICE服务
			MtDispatchTaskDetailIceRsp rpcResp = this.getMtTaskQueryServiceIcePrx()
					.queryMtDispatchTaskDetailApp(Integer.valueOf(this.getOrganId()), 
					Integer.valueOf(taskId), String.valueOf(user.getUserId()));
            if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
                return RestResponse.RestResponseBuilder.createSuccessBuilder("查询调度任务详情成功!")
						.setResult(rpcResp.getMtDispatchTaskDetailIce()).buidler();
            }
            return RestResponse.RestResponseBuilder.createFailBuilder(rpcResp.message).buidler();
        } catch (Exception e) {
            logger.warn("系统异常,查询调度任务详情失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,查询调度任务详情失败").buidler();
        }
    }
	
	/**
	 * 
	 * 类描述: 查询固定任务详情
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年4月20日 下午2:54:49
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "固定任务详情", response = String.class, notes = "{<br/>"
            + "\"taskId\":\"运送任务Id\",<br/>" 
            + "}<br/>")
    @ApiImplicitParams({})
    @RequestMapping(value = "/queryFixedTaskDetail.json", method = { RequestMethod.GET })
    @ResponseBody
    public RestResponse queryFixedTaskDetail(@AdminUserParam User user, HttpServletResponse response,
    	    @RequestParam(value = "taskId", required = true) String taskId) {
        if (null == user) {
            return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
        }
        try {
        	if (!NumberUtils.isDigits(String.valueOf(taskId))) {
				return RestResponse.RestResponseBuilder.createFailBuilder("参数[taskId]入参有误!").buidler();
			}
            // 获取ICE服务
			MtFixedTaskDetailIceRsp rpcResp = this.getMtTaskQueryServiceIcePrx().
					queryFixedTaskDetailApp(Integer.valueOf(this.getOrganId()), Integer.valueOf(taskId));
            if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
                return RestResponse.RestResponseBuilder.createSuccessBuilder("查询固定任务详情成功!")
						.setResult(rpcResp.getMtFixedTaskDetailIce()).buidler();
            }
            return RestResponse.RestResponseBuilder.createFailBuilder(rpcResp.message).buidler();
        } catch (Exception e) {
            logger.warn("系统异常,查询固定任务详情失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,查询固定任务详情败").buidler();
        }
    }
	
	/**
	 * 
	 * 类描述: 查询自主任务详情
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年4月20日 下午2:54:56
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "自主任务详情", response = String.class, notes = "{<br/>"
            + "\"taskId\":\"运送任务Id\",<br/>" 
            + "}<br/>")
    @ApiImplicitParams({})
    @RequestMapping(value = "/queryAutonomousTaskDetail.json", method = { RequestMethod.GET })
    @ResponseBody
    public RestResponse queryAutonomousTaskDetail(@AdminUserParam User user, HttpServletResponse response,
    	    @RequestParam(value = "taskId", required = true) String taskId) {
        if (null == user) {
            return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
        }
        try {
        	if (!NumberUtils.isDigits(String.valueOf(taskId))) {
				return RestResponse.RestResponseBuilder.createFailBuilder("参数[taskId]入参有误!").buidler();
			}
            // 获取ICE服务
			MtAutonomousTaskDetailIceRsp rpcResp = this.getMtTaskQueryServiceIcePrx().
					queryAutonomousTaskDetailApp(Integer.valueOf(this.getOrganId()), Integer.valueOf(taskId));
            if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
                return RestResponse.RestResponseBuilder.createSuccessBuilder("查询自主任务详情成功!")
						.setResult(rpcResp.getMtAutonomousTaskDetailIce()).buidler();
            }
            return RestResponse.RestResponseBuilder.createFailBuilder(rpcResp.message).buidler();
        } catch (Exception e) {
            logger.warn("系统异常,查询自主任务详情失败", e);
            return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,查询自主任务详情失败").buidler();
        }
    }
	
	/**
	 * @discription 指定调度任务分页查询(未开始/进行中,app端)
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月24日 下午7:43:56     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "指定调度任务分页查询(未开始/进行中,app端)", response = String.class, notes = "{<br/>" + 
			"\"organId\":\"项目(医院)Id\",<br/>" +
			"\"transTypeParentCode\":\"运送大类code,调度任务筛选时必填,可多选,多个用逗号分隔\",<br/>" +
			"\"status\":\"任务状态\",<br/>" +
			"\"urgency\":\"紧急程度:1:一般;2:紧急;3:特急,调度任务筛选时必填,可多选,多个用逗号分隔\",<br/>" +
			"\"pageNo\":\"页码\",<br/>" +
			"\"pageLength\":\"每页记录数\"<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryDispatchTaskPage.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryDispatchTaskPage(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtTaskPageIceParam params = FastjsonUtils.parseObject(paramJson, MtTaskPageIceParam.class);
			//必填参数验证
			String fields = "organId, pageNo, pageLength";
			String messages = "项目(医院)Id, 页码, 每页记录数";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			//获取ICE服务
			MtDispatchTaskPaginatorIceRsp rsp = this.getMtTaskQueryServiceIcePrx().queryDispatchTaskPageByAssign(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, Object> pageData = new HashMap<>(); 
				pageData.put("paginator", rsp.paginator);
				pageData.put("resultList", rsp.resultList);
				pageData.put("taskCount", rsp.taskCount);
				return RestResponse.RestResponseBuilder.createSuccessBuilder(rsp.message).setResult(pageData).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,指定调度任务分页查询失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,指定调度任务分页查询失败").buidler();
		}
	} 
	
	/**
	 * @discription 抢单调度任务分页查询(抢单/抢单完成,app端)
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月22日 上午11:53:57     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "抢单调度任务分页查询(抢单/抢单完成,app端)", response = String.class, notes = "{<br/>" + 
			"\"organId\":\"项目(医院)Id\",<br/>" +
			"\"pageNo\":\"页码\",<br/>" +
			"\"pageLength\":\"每页记录数\"<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryDispatchTaskPageByRob.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryDispatchTaskPageByRob(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtTaskPageIceParam params = FastjsonUtils.parseObject(paramJson, MtTaskPageIceParam.class);
			//必填参数验证
			String fields = "organId, pageNo, pageLength";
			String messages = "项目(医院)Id, 页码, 每页记录数";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			//获取ICE服务
			MtDispatchTaskPaginatorIceRsp rsp = this.getMtTaskQueryServiceIcePrx().queryDispatchTaskPageByRob(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, Object> pageData = new HashMap<>(); 
				pageData.put("paginator", rsp.paginator);
				pageData.put("resultList", rsp.resultList);
				pageData.put("taskCount", rsp.taskCount);
				return RestResponse.RestResponseBuilder.createSuccessBuilder(rsp.message).setResult(pageData).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,抢单调度任务分页查询失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,抢单调度任务分页查询失败").buidler();
		}
	} 
	
	/**
	 * @discription 自主任务分页查询(活动中的任务)
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月27日 上午11:28:34     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "自主任务分页查询(活动中的任务)", response = String.class, notes = "{<br/>" + 
			"\"organId\":\"项目(医院)Id\",<br/>" +
			"\"pageNo\":\"页码\",<br/>" +
			"\"pageLength\":\"每页记录数\"<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryAutonomousTaskPage.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryAutonomousTaskPage(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtTaskPageIceParam params = FastjsonUtils.parseObject(paramJson, MtTaskPageIceParam.class);
			//必填参数验证
			String fields = "organId, pageNo, pageLength";
			String messages = "项目(医院)Id, 页码, 每页记录数";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			//获取ICE服务
			MtAutonomousTaskPaginatorIceRsp rsp = this.getMtTaskQueryServiceIcePrx().queryAutonomousTaskPage(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, Object> pageData = new HashMap<>(); 
				pageData.put("paginator", rsp.paginator);
				pageData.put("resultList", rsp.resultList);
				pageData.put("taskCount", rsp.taskCount);
				return RestResponse.RestResponseBuilder.createSuccessBuilder(rsp.message).setResult(pageData).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,自主任务分页查询失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,自主任务分页查询失败").buidler();
		}
	} 
	
	/**
	 * @discription 固定任务分页查询(活动中的任务)
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月27日 上午11:29:23     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "固定任务分页查询(活动中的任务)", response = String.class, notes = "{<br/>" + 
			"\"organId\":\"项目(医院)Id\",<br/>" +
			"\"pageNo\":\"页码\",<br/>" +
			"\"pageLength\":\"每页记录数\"<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryFixedTaskPage.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryFixedTaskPage(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtTaskPageIceParam params = FastjsonUtils.parseObject(paramJson, MtTaskPageIceParam.class);
			//必填参数验证
			String fields = "organId, pageNo, pageLength";
			String messages = "项目(医院)Id, 页码, 每页记录数";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			//获取ICE服务
			MtFixedTaskPaginatorIceRsp rsp = this.getMtTaskQueryServiceIcePrx().queryFixedTaskPage(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, Object> pageData = new HashMap<>(); 
				pageData.put("paginator", rsp.paginator);
				pageData.put("resultList", rsp.resultList);
				pageData.put("taskCount", rsp.taskCount);
				return RestResponse.RestResponseBuilder.createSuccessBuilder(rsp.message).setResult(pageData).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,固定任务分页查询失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,固定任务分页查询失败").buidler();
		}
	} 
	
	/**
	 * @discription 固定任务执行信息分页查询
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月24日 下午8:11:47     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "固定任务执行信息分页查询", response = String.class, notes = "{<br/>" + 
			"\"taskId\":\"运送任务Id\",<br/>" +
			"\"organId\":\"组织(医院)Id\",<br/>" +
			"\"pageNo\":\"页码\",<br/>" +
			"\"pageLength\":\"每页记录数\"<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryFixedTaskExeInfoPage.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryFixedTaskExeInfoPage(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtTaskPageIceParam params = FastjsonUtils.parseObject(paramJson, MtTaskPageIceParam.class);
			//必填参数验证
			String fields = "taskId, organId, pageNo, pageLength";
			String messages = "项目(医院)Id, 组织(医院)Id, 页码, 每页记录数";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			//获取ICE服务
			FixedTaskExePaginatorIceRsp rsp = this.getMtTaskQueryServiceIcePrx().queryFixedTaskExeInfoPage(params);
			return RestResponse.RestResponseBuilder.createBuilder(rsp.code, rsp.message)
					.setResult(rsp.paginator, rsp.resultList.toArray()).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,固定任务执行信息分页查询失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,固定任务执行信息分页查询失败").buidler();
		}
	} 
	
	/**
	 * @discription 本月运送量排名
	 * @author yangyh@segimail.com       
	 * @created 2018年5月7日 下午3:05:06     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "本月运送量排名", response = String.class, notes = "{<br/>"
			+ "\"organId\":\"项目id\",<br/>" 
			+ "\"pageNo\":\"当前页码\",<br/>" 
			+ "\"pageLength\":\"每页条数\"<br/>" 
			+ "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/getMonthTransVolumeRank.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse getMonthTransVolumeRank(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (user == null) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录，请重新登录!").buidler();
		}
		try {
			PerTaskAmoMonthParam params = FastjsonUtils.parseObject(paramJson, PerTaskAmoMonthParam.class);
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
			params.setUserId(String.valueOf(user.getUserId()));
			PersonalVolumeMonthPaginator rsp = this.getPersonalVolumeMonthServiceIcePrx().getMonthTransVolumeRank(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.code)) {
				Map<String, Object> map = new HashMap<>();
				map.put("paginator", rsp.paginator);
				map.put("resultList", rsp.resultList);
				map.put("curUserRank", rsp.curUserRankIce);
				return RestResponse.RestResponseBuilder.createSuccessBuilder("本月运送量排名查询成功！").setResult(map).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.msg).buidler();
		} catch (Exception e) {
			logger.warn("系统异常，本月运送量排名查询失败！", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常，本月运送量排名查询失败！").buidler();
		}
	}
	
	/**
	 * @discription 固定任务某个点执行信息详情
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月22日 上午11:44:29     
	 * @param user
	 * @param routeId
	 * @param organId
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "固定任务某个点执行信息详情", response = String.class, notes = "")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryFixedTaskExeDetail.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryFixedTaskExeDetail(@AdminUserParam User user, 
			@RequestParam(value="routeId", required=true) String routeId,
			@RequestParam(value="organId", required=true) String organId) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//必填参数验证
			if (StringUtils.isBlank(organId) || StringUtils.isBlank(routeId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder("参数缺失").buidler();
			}
			//验证number类型
			if (!NumberUtils.isDigits(organId) || !NumberUtils.isDigits(routeId)) {
				//判断是否是number类型的
				return RestResponse.RestResponseBuilder.createFailBuilder("格式错误！").buidler();
			}
			//获取ICE服务
			FixedTaskExeDetailReturnIce rsp = this.getMtTaskQueryServiceIcePrx().
					queryFixedTaskExeDetail(Integer.valueOf(routeId), Integer.valueOf(organId));
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				return RestResponse.RestResponseBuilder.createSuccessBuilder("查询固定任务某个点执行信息详情成功!")
						.setResult(rsp.getDetail()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,固定任务某个点执行信息详情失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,固定任务某个点执行信息详情失败").buidler();
		}
	}
}
