package com.segi.uhomecp.medicaltrans.reportjob.inf.support;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.report.schedule.model.TransSchedule;
import com.segi.uhomecp.medicaltrans.reportjob.inf.AbstractDefaultExecute;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.model.MtTaskExtract;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.service.TransService;
import com.segi.uhomecp.medicaltrans.reportjob.report.base.ReportScheduleService;
import com.segi.uhomecp.medicaltrans.reportjob.util.ReportUtils;
/**
 * 报表job默认执行器
 *     
 * 包: com.segi.uhomecp.medicaltrans.reportjob.inf.support 
 * 类名称: ReportJobDefaultExecute.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 下午2:24:29
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
@Component(value = "reportJobExecute")
public class ReportJobDefaultExecute extends AbstractDefaultExecute<MtTaskExtract> {
	private static final Logger LOG = LoggerFactory.getLogger(ReportJobDefaultExecute.class);
	@Autowired
	private TransService transService;
	
	@Autowired
	private ReportScheduleService reportScheduleService;

	@Override
	public List<TransSchedule> getOrganList() {
		return reportScheduleService.getScheduleList();
	}

	@Override
	public Map<String, List<MtTaskExtract>> getBusinessData(Integer groupOrganId, Integer organId, Date startDate, Date endDate) {
		LOG.info("开始获取增量数据==>组织id:{},截止时间：{}-{}", organId, startDate, endDate);
		Map<String, List<MtTaskExtract>> dataMap = new HashMap<String, List<MtTaskExtract>>();
		List<MtTaskExtract> taskList = transService.getTaskList(groupOrganId, organId, startDate, endDate);
		dataMap.put(ReportUtils.TASK_LIST, taskList);
		List<MtTaskExtract> taskCreateList = transService.getTaskListByCreate(groupOrganId, organId, startDate, endDate);
		dataMap.put(ReportUtils.TASK_CREATE_LIST, taskCreateList);
		
		return dataMap;
	}
	
}
