package com.segi.uhomecp.medicaltrans.reportjob.report.base;

import java.util.Date;
import java.util.List;

import com.segi.uhomecp.common.service.GenericService;
import com.segi.uhomecp.medicaltrans.report.schedule.model.ScheduleLog;
import com.segi.uhomecp.medicaltrans.report.schedule.model.TransSchedule;
import com.segi.uhomecp.medicaltrans.report.schedule.model.TransScheduleCriteria;
import com.segi.uhomecp.medicaltrans.reportjob.enums.ScheduleStatusEnums;
/**
 * 报表排程Service
 *     
 * 包: com.segi.uhomecp.medicaltrans.reportjob.base.service 
 * 类名称: ReportScheduleService.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 下午5:46:26
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
public interface ReportScheduleService extends GenericService<TransSchedule, TransScheduleCriteria, Integer> {
	/**
	 * 获取排程数据
	 * @return
	 */
	public List<TransSchedule> getScheduleList();
	/**
	 * 更新状态
	 * @param organId	 项目ID
	 * @param enums     排程状态枚举
	 * @param updateDate 更新时间
	 */
	public void updateScheduleByOrganId(Integer organId, ScheduleStatusEnums enums, Date updateDate);
	
	/**
	 * 更新状态
	 * @param enums     排程状态枚举
	 * @param updateDate 更新时间
	 */
	public void updateScheduleByOrganIds(List<Integer> organIds, ScheduleStatusEnums enums,
			Date excDate, Date lastExcDate, Date excEndDate) ;

	/**
	 * 完成
	 * @param organId 项目ID
	 * @param status 排程状态枚举
	 * @param updateDate 更新时间
	 * @param log 日志
	 */
	public void completed(Integer organId, ScheduleStatusEnums status, Date updateDate, ScheduleLog log);
	
	/**
	 * @discription 获取全部排程表信息
	 * @author wangxiong@segimail.com       
	 * @created 2018年8月3日 上午10:43:30    
	 * 
	 * @param organIds	 项目ID集合
	 * @return
	 */
	public List<TransSchedule> getAllTransSchedule(List<Integer> organIds);
	
	/**
	 * @discription 修改执行时间点
	 * @author wangxiong@segimail.com       
	 * @created 2018年9月11日 下午8:46:45     
	 * @param organIds
	 * @param exeDate
	 */
	public void updateScheduleExeDateByOrganIds(List<Integer> organIds, Date exeDate) ;
	
	/**
	 * @discription 批量保存日志
	 * @author wangxiong@segimail.com       
	 * @created 2018年9月13日 下午7:14:31     
	 * @param logList
	 */
	public void insertBatchLog(List<ScheduleLog> logList);
}
