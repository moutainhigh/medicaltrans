package com.segi.uhomecp.medicaltrans.jobhandle;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.reportjob.report.pervolmonth.utils.MtPerVolMonthRptHandleUtils;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * @ClassName:  PerVolMonthReportHandle   
 * @Description:个人运送量月报表当月数据全量统计Job   
 * @author: zhaoqing
 * @date:   2018年7月30日 下午5:49:42
 */
@JobHandler(value = "MtPerVolMonthReportHandleJob")
@Component
public class MtPerVolMonthReportHandle extends IJobHandler {
	
	@Autowired
	private MtPerVolMonthRptHandleUtils mtPerVolMonthRptHandleUtils;
	
	/**
	 * <p>Title: execute</p>   
	 * <p>Description: </p> 
	 * <p>zhaoqing</p>
	 * @param arg0 参数支持"groupOrganId,organId"样式，传入参数则按传的组织统计数据
	 * @return
	 */
	@Override
	public ReturnT<String> execute(String arg0) throws Exception {
		XxlJobLogger.log("===========>MtPerVolMonthReportHandle: arg0:" + arg0);
		try {
			Map<Integer, List<Integer>> groupOrganMap = mtPerVolMonthRptHandleUtils.getGroupOrganMap(arg0);
			XxlJobLogger.log("===========>成功获取使用医疗运输的项目信息: groupOrganMap: " 
					+ FastjsonUtils.toJsonString(groupOrganMap));
			// 业务处理
			ResultInfo result = mtPerVolMonthRptHandleUtils.handle(groupOrganMap);
			if (result.getIsSucc()) {
				return SUCCESS;
			} else {
				FAIL.setMsg(result.getMessage());
				return FAIL;
			}
		} catch (Exception e) {
			XxlJobLogger.log(e);
		}
		return SUCCESS;
	}
}
