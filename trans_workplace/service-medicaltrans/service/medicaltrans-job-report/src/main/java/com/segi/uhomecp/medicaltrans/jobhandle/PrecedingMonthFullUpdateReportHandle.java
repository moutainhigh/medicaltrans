package com.segi.uhomecp.medicaltrans.jobhandle;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.reportjob.monthjob.inf.support.PrecedingUpdateReportExecute;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

/**
 * 前一个月全量数据更新JOB
 *     
 * 包: com.segi.uhomecp.medicaltrans.report.jobhandle 
 * 类名称: PrecedingMonthFullUpdateReportHandle.java
 * 类描述: 
 * 创建人: wangxiong    
 * 创建时间: 上午10:17:02
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
@JobHandler(value = "PrecedingMonthFullUpdateReportHandleJob")
@Component
public class PrecedingMonthFullUpdateReportHandle extends IJobHandler {

	@Autowired
	private PrecedingUpdateReportExecute precedingUpdateReportExecute;
	
	@Override
	public ReturnT<String> execute(String arg0) throws Exception {
		List<Integer> organIds = new ArrayList<>();
		if (StringUtils.isNotBlank(arg0)) {
			// 如果任务传入组织机构参数，则按传入的参数执行
			organIds = AppUtils.str2Integer(arg0);
		}
		// 计算开始时间
		precedingUpdateReportExecute.execute(organIds);
		// 计算结束时间
		return SUCCESS;
	}

}
