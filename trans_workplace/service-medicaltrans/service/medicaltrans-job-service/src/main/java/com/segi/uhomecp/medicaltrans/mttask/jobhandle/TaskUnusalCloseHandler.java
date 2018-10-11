package com.segi.uhomecp.medicaltrans.mttask.jobhandle;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import resp.RpcRespIce;

import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * Title: TaskUnusalCloseHandler.java    
 * @Description: 任务单超过预约时间7天后异常关闭
 * @author zhangyang@segimail.com       
 * @created 2018年8月7日 下午4:06:26
 */
@JobHandler(value = "taskUnusalClose")
@Component
public class TaskUnusalCloseHandler extends IJobHandler {
	
	@Override
	public ReturnT<String> execute(String arg0) throws Exception {
		ReturnT<String> resp = new ReturnT<String>();
		resp.setCode(ReturnT.SUCCESS_CODE);
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		XxlJobLogger.log("***************更新任务单状态异常关闭job开始***************");
		// 获取任务异常关闭的限定时间(当天0点0分减7天)
		Date date = DateUtil.addDateDay(DateUtil.getCurDayHHmmss(0, 0, 0), -7);
		Long limitDate = Long.valueOf(DateUtil.formatDateToString(
				date, DateUtil.FORMAT_YYYY_MM_DD_HH_MM));
		XxlJobLogger.log("***************当前任务预约时间小于" + limitDate + "都会异常关闭***************");
		// 获取排程表已经在使用中的医院Map<groupOrganId, List<organId>> 
		Map<Integer, List<Integer>> organMap = MtCommonServiceUtils.getTransScheduleMap();
		Integer groupOrganId = null;
		List<Integer> organIdList = null;
		Paginator page = null;
		List<Integer> processOrganIds = null;
		// 判断map是否为空
		if (organMap.isEmpty()) {
			resp.setMsg("查询排程表为空");
			XxlJobLogger.log("***************查询排程表为空,job执行结束***************");
			return resp;
		}
		for (Map.Entry<Integer, List<Integer>> entry : organMap.entrySet()) {
			groupOrganId = entry.getKey();
			XxlJobLogger.log("***************当前要更新的一级物业为" + groupOrganId + "***************");
			organIdList = entry.getValue();
			if (!AppUtils.isNotEmpty(organIdList)) {
				continue;
			}
			//判断是否有必要分批
			if (MtConstant.MONTH_UPDATE_LIMIT <= organIdList.size()) {
				page = new Paginator(1, MtConstant.MONTH_UPDATE_LIMIT, organIdList.size());
				for (int i = 0; i < page.getTotalPages(); i++) {
					page = new Paginator(i + 1, MtConstant.MONTH_UPDATE_LIMIT, organIdList.size());
					processOrganIds = organIdList.subList(page.getStartRow() - 1, page.getEndRow());
					XxlJobLogger.log("***************当前要更新的项目有:" + processOrganIds + "***************");
					rsp = MtCommonServiceUtils.updateTaskUnusualClose(groupOrganId, processOrganIds, limitDate);
					if (!RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
						resp.setCode(ReturnT.FAIL_CODE);
						resp.setMsg(rsp.getMessage());
						return resp;
					}
				}
			} else {
				XxlJobLogger.log("***************当前要更新的项目有:" + organIdList + "***************");
				rsp = MtCommonServiceUtils.updateTaskUnusualClose(groupOrganId, organIdList, limitDate);
				if (!RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
					resp.setCode(ReturnT.FAIL_CODE);
					resp.setMsg(rsp.getMessage());
					return resp;
				}
			}
		}
		XxlJobLogger.log("***************更新任务单状态异常关闭job执行完成***************");
		return resp;
	}	
}
