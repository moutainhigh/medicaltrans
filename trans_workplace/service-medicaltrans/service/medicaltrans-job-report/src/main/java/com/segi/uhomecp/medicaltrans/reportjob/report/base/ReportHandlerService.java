package com.segi.uhomecp.medicaltrans.reportjob.report.base;

import java.util.List;

import com.segi.uhomecp.medicaltrans.report.schedule.model.ExecuteHandler;
/**
 * 执行器业务类
 *     
 * 包: com.segi.uhomecp.medicaltrans.reportjob.report.base 
 * 类名称: ReportHandlerService.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 下午2:23:33
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
public interface ReportHandlerService {
	
	/**
	 * 获取执行器配置
	 * @return
	 */
	public List<ExecuteHandler> getHandlerList();
	
}
