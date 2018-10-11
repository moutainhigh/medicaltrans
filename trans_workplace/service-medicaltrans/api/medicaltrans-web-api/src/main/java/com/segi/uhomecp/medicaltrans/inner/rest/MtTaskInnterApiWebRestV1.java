package com.segi.uhomecp.medicaltrans.inner.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import resp.RpcRespIce;
import segi.medicaltrans.base.taskloop.TaskLoopIce;
import segi.medicaltrans.base.taskloop.TaskLoopInfo;
import segi.medicaltrans.base.taskloop.TaskLoopServiceIcePrx;
import segi.whcommon.push.MessageIce;
import segi.whcommon.push.PushServiceIcePrx;
import IceExt.IceClientUtil;

import com.segi.uhomecp.common.constant.RestApiConstant;
import com.segi.uhomecp.common.rest.AbsActionRest;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.web.rest.RestResponse;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(RestApiConstant.API_PATH + "/medicaltrans/mtInnerWebRestV1")
@Api(value = "medicaltrans/mtInnerWebRestV1", description = "医院运送管理(内部接口)")
public class MtTaskInnterApiWebRestV1 extends AbsActionRest {
	private static final Logger logger = LoggerFactory.getLogger(MtTaskInnterApiWebRestV1.class);
	private static final String PREFIXPROJECTNAME = RestApiConstant.PREFIXPROJECTNAME;
	
	private PushServiceIcePrx getPushServiceIcePrx() {
		return IceClientUtil.getServicePrxByClass(PushServiceIcePrx.class);
	}
	
	private TaskLoopServiceIcePrx getTaskLoopServiceIcePrx() {
		return IceClientUtil.getServicePrxByClass(TaskLoopServiceIcePrx.class);
	}
	
	/**
	 * 触发消息
	 * @param user
	 * @param paramJson
	 * @return
	 */
	@ApiOperation(value = PREFIXPROJECTNAME + "触发消息", response = String.class, notes = "消息触发")
	@ApiImplicitParams({})
	@RequestMapping(value = "/tiggerMessage.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse tiggerMessage(@ApiParam(name = "code", value = "APP_MSG|MEDICAL_TAKS_LOOP") @RequestParam(required=true) String code,  
			@ApiParam(name = "time", value = "执行时间yyyy-MM-dd HH:mm:ss") @RequestParam(required=true) String time,
			@ApiParam(name = "key", value = "关键字") @RequestParam(required=false) String key, 
			@ApiParam(name = "params", value = "入参") @RequestParam(required=true) String params, 
			@ApiParam(name = "taskId", value = "任务ID") @RequestParam(required=false) String taskId) {
		try {
			
			SimpleDateFormat format =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = format.parse(time);
			
			MessageIce message = new MessageIce();
			message.setBusinessKey(key);
			message.setMsgParams(params);
			message.setMsgTime(date.getTime());
			message.setMsgTypeCode(code);
			
			if (StringUtils.isNotBlank(taskId)) {
				TaskLoopInfo rsp = this.getTaskLoopServiceIcePrx().queryTaskLoopDetail(taskId);
				TaskLoopIce taks = rsp.getTaskLoopIce();
				
//				Date date = CrontabConstructUtil.generatorCronDate(taks.get, currentDate);
//				if (null == date) {
//					continue;
//				}
			} 
			
			RpcRespIce rsp = this.getPushServiceIcePrx().push(message);
			
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				Map<String, String> map = new HashMap<>();
				map.put("id", rsp.getData());
				return RestResponse.RestResponseBuilder.createSuccessBuilder(rsp.message).setResult(map).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(rsp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,触发失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常,触发失败").buidler();
		}
	}
}
