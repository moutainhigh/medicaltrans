package com.segi.uhomecp.medicaltrans.mttask.rest;

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

import segi.medicaltrans.mttask.query.FixedTaskExeDetailReturnIce;
import segi.medicaltrans.mttask.query.FixedTaskExePaginatorIceRsp;
import segi.medicaltrans.mttask.query.MtTaskDetailIceParam;
import segi.medicaltrans.mttask.query.MtTaskDetailRetIceRsp;
import segi.medicaltrans.mttask.query.MtTaskPageIceParam;
import segi.medicaltrans.mttask.query.MtTaskPageRerurnIce;
import segi.medicaltrans.mttask.query.MtTaskPaginatorIce;
import segi.medicaltrans.mttask.query.MtTaskQueryServiceIcePrx;
import segi.medicaltrans.mttask.track.MtTaskTrackServiceIcePrx;
import segi.medicaltrans.mttask.track.TrackDetailRspIce;
import IceExt.IceClientUtil;

import com.segi.uhomecp.common.constant.RestApiConstant;
import com.segi.uhomecp.common.rest.AbsActionRest;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
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
import com.segi.uhomecp.wh.common.utils.ExcelWriter;
import com.segi.uhomecp.wh.common.utils.NumberUtils;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping(RestApiConstant.API_PATH + "/medicaltrans/mtTaskQueryWebRest")
@Api(value = "medicaltrans/mtTaskQueryWebRest", description = "医院运送管理(web端)")
public class MtTaskQueryWebRest extends AbsActionRest {
	private static final Logger logger = LoggerFactory.getLogger(MtTaskQueryWebRest.class);
	private static final String PREFIXPROJECTNAME = RestApiConstant.PREFIXPROJECTNAME;
	
	/**
	 * @discription 获取ice服务
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月24日 下午2:18:23     
	 * @return
	 */
	private MtTaskQueryServiceIcePrx getMtTaskQueryServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(MtTaskQueryServiceIcePrx.class);
	}
	
	private MtTaskTrackServiceIcePrx getMtTaskTrackServiceIcePrx() {
	    return IceClientUtil.getServicePrxByClass(MtTaskTrackServiceIcePrx.class);
	} 
	  
	/**
	 * @discription 运送任务分页查询
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月4日 下午2:09:09     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "运送任务分页查询", response = String.class, notes = "{<br/>" + 
			"\"organId\":\"项目(医院)Id\",<br/>" +
			"\"transTypeParentCode\":\"运送类型\",<br/>" +
			"\"urgency\":\"紧急程度,1:一般;2:紧急;3:特急,选全部就不传\",<br/>" +
			"\"beginTime\":\"下单开始时间 YYYY-MM-DD HH：mm\",<br/>" +
			"\"endTime\":\"下单结束时间 YYYY-MM-DD HH：mm\",<br/>" +
			"\"status\":\"状态,1:未派单;2:抢单中;3:进行中;4:未开始;5:已完成;6:已取消;7:退单,选全部就不传\",<br/>" +
			"\"taskType\":\"任务类型:1调度任务;2自主任务;3固定任务  选全部就不传\",<br/>" +
			"\"taskId\":\"运送任务Id\",<br/>" +
			"\"pageNo\":\"页码\",<br/>" +
			"\"pageLength\":\"每页记录数\"<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryMtTaskPage.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryMtTaskPage(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
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
			if (StringUtils.isNotEmpty(params.getUrgency()) && 
					!AppUtils.match(MtConstant.MT_TASK_URGENCY_REGEX, params.getUrgency())) {
				//验证任务紧急程度
				return RestResponse.RestResponseBuilder.createFailBuilder("紧急程度输入有误!").buidler();
			} 
			if (StringUtils.isNotEmpty(params.getStatus()) && 
					!AppUtils.match(MtConstant.MT_TASK_STATUS_REGEX, params.getStatus())) {
				//验证任务状态
				return RestResponse.RestResponseBuilder.createFailBuilder("任务状态输入有误!").buidler();
			}
			if (StringUtils.isNotEmpty(params.getTaskType()) && 
					!AppUtils.match(MtConstant.MT_TASK_TYPE_REGEX, params.getTaskType())) {
				//验证任务类型
				return RestResponse.RestResponseBuilder.createFailBuilder("任务类型输入有误!").buidler();
			}
			if (StringUtils.isNotEmpty(params.getBeginTime()) && StringUtils.isNotEmpty(params.getEndTime())) {
				fields = "beginTime, endTime";
				messages = "下单开始时间, 下单结束时间";
				errInfo = CheckRestParams.checkDateByRegex(params, fields, messages, Constant.DATA_FORMATE_YYYY_MM_DD_HH_MM_REG);
				if (null != errInfo) {
					return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
				}
				params.setBeginTime(params.getBeginTime().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "") + "00");
				params.setEndTime(params.getEndTime().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "") + "00");
			}
			// 验证任务Id
			if (StringUtils.isNotBlank(params.getTaskId())) {
				errInfo = CheckRestParams.checkInteger(params, "taskId", "运送任务Id");
				if (null != errInfo) {
					return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
				}
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			//获取ICE服务
			MtTaskPaginatorIce rsp = this.getMtTaskQueryServiceIcePrx().queryMtTaskPage(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, Object> pageData = new HashMap<>(); 
				pageData.put("paginator", rsp.paginator);
				pageData.put("resultList", rsp.resultList);
				pageData.put("warnMinute", rsp.warnMinute);
				pageData.put("oprUserId", String.valueOf(user.getUserId()));
				return RestResponse.RestResponseBuilder.createSuccessBuilder(rsp.message).setResult(pageData).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,查询运送任务失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,查询运送任务失败").buidler();
		}
	} 
	
	/**
	 * @discription 固定任务执行信息分页查询
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月25日 下午2:20:34     
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "固定任务执行信息分页查询", response = String.class, notes = "{<br/>" + 
			"\"taskId\":\"运送任务Id\",<br/>" +
			"\"pageNo\":\"页码\",<br/>" +
			"\"pageLength\":\"每页记录数\",<br/>" +
			"\"organId\":\"项目id\"<br/>" +
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
			String fields = "taskId, pageNo, pageLength, organId";
			String messages = "项目(医院)Id, 页码, 每页记录数, 组织(医院)Id";
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
	
	@ApiOperation(value = PREFIXPROJECTNAME + "运送任务详情", response = String.class, notes = "{<br/>" + 
			"\"taskId\":\"运送任务id\",<br/>" +
			"\"taskType\":\"任务类型\",<br/>" +
			"\"organId\":\"组织(医院)Id\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryMtTaskDetail.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryMtTaskDetail(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtTaskDetailIceParam params = FastjsonUtils.parseObject(paramJson, MtTaskDetailIceParam.class);
			//必填参数验证
			String fields = "taskId, taskType, organId";
			String messages = "运送任务id, 任务类型, 组织(医院)Id";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (StringUtils.isNotEmpty(params.getTaskType()) && 
					!AppUtils.match(MtConstant.MT_TASK_TYPE_DISPATCH_AND_SELF_REGEX, params.getTaskType())) {
				//验证任务类型
				return RestResponse.RestResponseBuilder.createFailBuilder("任务类型输入有误!").buidler();
			}
			params.setUserOrganId(user.getOrganId());
			//获取ICE服务
			MtTaskDetailRetIceRsp rsp = this.getMtTaskQueryServiceIcePrx().queryMtTaskDetail(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				return RestResponse.RestResponseBuilder.createSuccessBuilder("查询运送任务详情成功!")
						.setResult(rsp.getMtTaskDetailRetIce()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,运送任务详情", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,运送任务详情").buidler();
		}
	} 
	
	@ApiOperation(value = PREFIXPROJECTNAME + "运送任务详情(固定任务)", response = String.class, notes = "{<br/>" + 
			"\"taskId\":\"运送任务id\",<br/>" +
			"\"taskType\":\"任务类型\",<br/>" +
			"\"organId\":\"组织(医院)Id\",<br/>" +
			"}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryMtTaskLoopDetail.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse queryMtTaskLoopDetail(@AdminUserParam User user, @RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//前端传进来的json转对象
			MtTaskDetailIceParam params = FastjsonUtils.parseObject(paramJson, MtTaskDetailIceParam.class);
			//必填参数验证
			String fields = "taskId, taskType, organId";
			String messages = "运送任务id, 任务类型, 组织(医院)Id";
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (StringUtils.isNotEmpty(params.getTaskType()) && 
					!AppUtils.match(MtConstant.MT_TASK_TYPE_LOOP_REGEX, params.getTaskType())) {
				//验证任务类型
				return RestResponse.RestResponseBuilder.createFailBuilder("任务类型输入有误!").buidler();
			}
			params.setUserOrganId(user.getOrganId());
			//获取ICE服务
			MtTaskDetailRetIceRsp rsp = this.getMtTaskQueryServiceIcePrx().queryMtTaskFixedDetail(params);
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				return RestResponse.RestResponseBuilder.createSuccessBuilder("查询运送任务详情成功!")
						.setResult(rsp.getMtTaskDetailRetIce()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,运送任务详情", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,运送任务详情").buidler();
		}
	}
	
	/**
	 * @Title: expoetMtTask 
	 * @Description:  运送管理导出
	 * @author liuyi@segimail.com 
	 * @date 2018年9月14日下午2:53:18
	 */
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "运送管理导出", response = String.class, notes = "运送类型导出")
	@ApiImplicitParams({})
	@RequestMapping(value = "/expoetMtTask.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse expoetMtTask(@AdminUserParam User user, HttpServletResponse response,
            @RequestParam(value = "organId", required = true) String organId,
            @RequestParam(value = "transTypeParentCode", required = false) String transTypeParentCode,
            @RequestParam(value = "urgency", required = false) String urgency,
            @RequestParam(value = "beginTime", required = true) String beginTime,
            @RequestParam(value = "endTime", required = true) String endTime,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "taskType", required = false) String taskType) {
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
			String fields = "organId";
			String messages = "项目(医院)Id";
			//Number类型格式验证
			String errInfo = CheckRestParams.checkInteger(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler(); 
			}
			if (StringUtils.isNotEmpty(urgency) && 
					AppUtils.match(MtConstant.MT_TASK_URGENCY_REGEX, urgency)) {
				//验证任务紧急程度
				params.setUrgency(urgency);;
			} 
			if (StringUtils.isNotEmpty(status) && 
					AppUtils.match(MtConstant.MT_TASK_STATUS_REGEX, status)) {
				//验证任务状态
				params.setStatus(status);;
			}
			if (StringUtils.isNotEmpty(taskType) && 
					AppUtils.match(MtConstant.MT_TASK_TYPE_REGEX, taskType)) {
				//验证任务类型
				params.setTaskType(taskType);
			}
			if (StringUtils.isNotEmpty(beginTime) && StringUtils.isNotEmpty(endTime)) {
				fields = "beginTime, endTime";
				messages = "下单开始时间, 下单结束时间";
				params.setBeginTime(beginTime);
				params.setEndTime(endTime);
				errInfo = CheckRestParams.checkDateByRegex(params, fields, messages, Constant.DATA_FORMATE_YYYY_MM_DD_HH_MM_REG);
				if (null != errInfo) {
					return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler(); 
				}
				params.setBeginTime(params.getBeginTime().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "") + "00");
				params.setEndTime(params.getEndTime().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "") + "00");
			}
			params.setUserId(String.valueOf(user.getUserId()));
			params.setUserOrganId(user.getOrganId());
			params.setOrganId(organId);
			String pageLength = MtConstant.EXPORT_EVERY_PAGE_LENGTH_V1;
			 // 设置长度为5000
            params.setPageLength(pageLength);
            params.setPageNo(MtConstant.EXPORT_PAGE_NO);
            // 设置导出标识
            params.setExportFlag(true);
			// 获取ICE服务
            MtTaskQueryServiceIcePrx icePrx = this.getMtTaskQueryServiceIcePrx();
            MtTaskPaginatorIce resource = icePrx.queryMtTaskPage(params);
            List<MtTaskPageRerurnIce> resultList = new ArrayList<>();
			if (AppUtils.isNotEmpty(resource.getResultList())) {
				resultList.addAll(resource.getResultList());
			}
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
				resource = icePrx.queryMtTaskPage(params);
				if (AppUtils.isNotEmpty(resource.getResultList())) {
					resultList.addAll(resource.getResultList());
				}
				curPageNo = resource.getPaginator().getPageNo();
				curPageNoInt = Integer.parseInt(curPageNo);
				curCount = curPageNoInt * pageLengthInt;
			}
	        return expoetMtTask(response, resultList);
		} catch (Exception e) {
			logger.warn("系统异常,运送管理导出", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,运送管理导出").buidler(); 
		} finally {
			// 下载并发次数释放
			AppConCurrentUtils.getInstace().subConCurCount(
					AppConCurrentTypeConstant.MAX_CURRENCY_EXCEL_EXP);
		}
	}
	
	private RestResponse expoetMtTask(HttpServletResponse response,  List<MtTaskPageRerurnIce> resultList){
		try {
			File file = null;
			String title = "运送管理导出.xls";
			String[] columnTitles = { "所属项目", "任务ID", "下单时间", "下单人", "任务来源", "运送类型", 
					"紧急程度", "出发地", "目的地", "运送员", "任务类型", "状态", "运送内容描述", "派单人", 
					"派单时间", "任务实际开始时间", "任务实际结束时间", "签收时间", "签收人", "评价结论", "评价信息"};
			
			String[] data = null;
	            List<String[]> dataList = new ArrayList<String[]>();
	            if (AppUtils.isNotEmpty(resultList)) {
	                for (int i = 0; i < resultList.size(); i++) {
	                	MtTaskPageRerurnIce taskIce = resultList.get(i);
	                    data = new String[21];
	                    data[0] = taskIce.getOrganName(); // 所属组织
	                    data[1] = taskIce.getTaskId(); // 任务ID
	                    data[2] = taskIce.getCreateDate(); // 下单时间
	                    data[3] = taskIce.getCreateByName(); // 下单人
	                    data[4] = taskIce.getSourceHouseName(); // 任务来源
	                    data[5] = taskIce.getTransTypeParentCodeName(); // 运送类型
	                    data[6] = taskIce.getUrgencyName(); // 紧急程度
	                    data[7] = taskIce.getFromHouseName(); // 出发地
	                    data[8] = taskIce.getToHouseName(); // 目的地
	                    if (taskIce.getUserList() != null && AppUtils.isNotEmpty(taskIce.getUserList())) {
	                    	List<segiwh.common.User> userList = taskIce.getUserList();
	                    	List<String> userNameList = AppUtils.list2ParamsList(userList, (obj) -> obj.getUserName());
	                    	data[9] = AppUtils.listToString(userNameList, ','); // 运送员
						}
	                    data[10] = taskIce.getTaskTypeName(); // 任务类型
	                    data[11] = taskIce.getStatusName(); // 状态
	                    data[12] = taskIce.getTaskContent(); // 运送内容描述	
						data[13] = taskIce.getDispatchUserName(); // 派单人
						data[14] = taskIce.getSendTime(); // 派单时间
						data[15] = taskIce.getExeBeginTime(); // 任务实际开始时间
						data[16] = taskIce.getExeEndTime(); // 任务实际结束时间
						data[17] = taskIce.getReceiveTime(); // 签收时间
						data[18] = taskIce.getReceiverUserName(); // 签收人
						data[19] = taskIce.getEvaluate(); // 评价结论
						data[20] = taskIce.getEvaluateContent(); // 评价信息
	                    dataList.add(data);
	                }
	            }
	            file = ExcelWriter.simpleCreate(RestApiConstant.MEDICALTRANS, columnTitles, dataList);
	            ResponseDownloadUtils.downloadAndDelete(response, file, title);
	            return null;
		} catch (Exception e) {
			logger.error("运送管理导出异常", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("运送管理导出异常！").buidler();
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
	
	/**
	 * @discription 查询轨迹详情
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月26日 下午6:48:52     
	 * @param user
	 * @param taskId
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "查询轨迹详情", response = String.class, notes = "")
	@ApiImplicitParams({})
	@RequestMapping(value = "/queryTaskTrackDetail.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse queryTaskTrackDetail(@AdminUserParam User user, 
			@RequestParam(value="taskId", required=true) String taskId) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder("用户未登录,请重新登录!").buidler();
		}
		try {
			//必填参数验证
			if (StringUtils.isBlank(taskId)) {
				return RestResponse.RestResponseBuilder.createFailBuilder("参数缺失").buidler();
			}
			//验证number类型
			if (!NumberUtils.isDigits(taskId)) {
				//判断是否是number类型的
				return RestResponse.RestResponseBuilder.createFailBuilder("格式错误！").buidler();
			}
			//获取ICE服务
			TrackDetailRspIce rsp = this.getMtTaskTrackServiceIcePrx().queryTaskTrackById(Integer.valueOf(taskId));
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, Object> pageData = new HashMap<>(); 
				pageData.put("resultList", rsp.resultList);
				return RestResponse.RestResponseBuilder.createSuccessBuilder(rsp.message).setResult(pageData).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,查询轨迹详情失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,查询轨迹详情失败").buidler();
		}
	}
}
