package com.segi.uhomecp.medicaltrans.reportjob.inf;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.segi.uhomecp.medicaltrans.report.schedule.model.TransSchedule;
import com.segi.uhomecp.medicaltrans.reportjob.inf.bean.ExtractData;
import com.segi.uhomecp.medicaltrans.reportjob.util.ReportUtils;
import com.segi.uhomecp.medicaltrans.taskgroup.TaskGroupContainer;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * 默认执行器
 *     
 * 包: com.segi.uhomecp.medicaltrans.reportjob.inf 
 * 类名称: AbstractDefaultExecute.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 下午5:28:40
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
public abstract class AbstractDefaultExecute<T> implements JobExecute {
	
	private static final Logger LOG = LoggerFactory.getLogger(AbstractDefaultExecute.class);
	
	private static final int MIN = -5;
	/**
	 * 获取排程项目数
	 * @return
	 */
	public abstract List<TransSchedule> getOrganList();
	/**
	 * 获取增量业务数据
	 * @param organId organId
	 * @param startDate startDate
	 * @param endDate endDate
	 * @return
	 */
	public abstract Map<String, List<T>> getBusinessData(Integer groupOrganId, Integer organId, Date startDate, Date endDate);
	
	@Override
	public void execute(List<TransSchedule> organList) {
		LOG.info("增量获取数据开始调度...");
		//获取项目
//		List<TransSchedule> organList = getOrganList();
//		LOG.info("获取项目清单：{}", organList);
		
		//取值范围提前5分钟保证取数据正确性
		Date nextDate = ReportUtils.getNextMinDate(MIN);

		for (TransSchedule schedule : organList) {
			try {
				if (!checkParamsDate(schedule.getParamDate(), nextDate)) {
					XxlJobLogger.log("organId=[" + schedule.getOrganId() + "]执行时间需大于5分钟");
					continue;
				}
				//本月全量取数job判断取数开始时间在本月截止时间-执行时间之前，取数开始时间取本月截止时间
				if (null != schedule.getExcEndDate() && null != schedule.getExcDate()) {
					if (schedule.getExcEndDate().getTime() < schedule.getParamDate().getTime() 
							&& schedule.getParamDate().getTime() < schedule.getExcDate().getTime()) {
						schedule.setParamDate(schedule.getExcEndDate());
					}
				}
				
				//上月全量job执行，判断上月执行时间取值
				if (null != schedule.getLastExcDate()) {
					if (schedule.getParamDate().getTime() < schedule.getLastExcDate().getTime()) {
						schedule.setLastExcDate(schedule.getParamDate());
					}
				}
				
				//获取数据
				Map<String, List<T>> map = this.getBusinessData(schedule.getGroupOrganId(), schedule.getOrganId(), schedule.getParamDate(), nextDate);
				
				ExtractData<?> data = new ExtractData(schedule.getOrganId(),
						schedule.getGroupOrganId(), schedule.getParamDate(), nextDate,
						schedule.getExcDate(), schedule.getLastExcDate(), schedule.getExcEndDate(), map);
				//生成任务组
				TaskGroupContainer taskGroupContainer = new TaskGroupContainer(data);
				taskGroupContainer.start();
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error("更新排程系统异常", e);
			}
		}
	}
	/**
	 * 校验开始时间和结束时间
	 * @param start
	 * @param end
	 * @return
	 */
	private boolean checkParamsDate(Date start, Date end) {
		long min = end.getTime() - start.getTime();
		if (min > 5 * 60 * 1000) {
			return true;
		}
		
		return false;
		
	}
}
