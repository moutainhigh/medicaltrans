package com.segi.uhomecp.medicaltrans.reportjob.inf;

import java.util.List;

import com.segi.uhomecp.medicaltrans.report.schedule.model.TransSchedule;

/**
 * JOB执行入口
 *     
 * 包: com.segi.uhomecp.medicaltrans.reportjob.inf 
 * 类名称: JobExecute.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 下午5:24:37
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
public interface JobExecute {
	/**
	 * job执行入口
	 */
	public void execute(List<TransSchedule> organList);
	
}
