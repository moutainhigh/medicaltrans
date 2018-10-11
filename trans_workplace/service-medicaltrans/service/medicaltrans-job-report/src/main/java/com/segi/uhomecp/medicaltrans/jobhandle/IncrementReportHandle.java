package com.segi.uhomecp.medicaltrans.jobhandle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.report.schedule.model.TransSchedule;
import com.segi.uhomecp.medicaltrans.reportjob.inf.JobExecute;
import com.segi.uhomecp.medicaltrans.reportjob.report.base.ReportScheduleService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.util.ShardingUtil;

/**
 * 运单增量数据计算JOB
 *     
 * 包: com.segi.uhomecp.medicaltrans.report.jobhandle 
 * 类名称: IncrementReportHandle.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 上午10:17:02
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
@JobHandler(value = "IncrementReportHandleJob")
@Component
public class IncrementReportHandle extends IJobHandler {
	
	@Resource(name = "reportJobExecute")
	private JobExecute jobExecute;
	
	@Autowired
	private ReportScheduleService reportScheduleService;
	
	@Override
	public ReturnT<String> execute(String arg0) throws Exception {
		List<TransSchedule> schedueList = reportScheduleService.getScheduleList();
		
		List<TransSchedule> shardSchedueList = new ArrayList<TransSchedule>();
		// 获取分片信息,按照group_organ_id进行分片处理
		ShardingUtil.ShardingVO shardingVO = ShardingUtil.getShardingVo();
		if (shardingVO.getTotal() == 1) {
			shardSchedueList = schedueList;
		} else {
			for (TransSchedule tmp : schedueList) {
				if (tmp.getGroupOrganId().intValue() % shardingVO.getTotal() == shardingVO.getIndex()) {
					shardSchedueList.add(tmp);
				}
			}
		}
		
		jobExecute.execute(shardSchedueList);
		
		return SUCCESS;
	}

}
