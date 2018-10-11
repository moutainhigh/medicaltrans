package com.segi.uhomecp.medicaltrans.report.taskrpt.service;

import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.report.taskrpt.dto.MtHisTaskPageDto;

/**
 * @ClassName:  MtHisTaskRptQueryService   
 * @Description:运送记录查询接口   
 * @author: zhaoqing
 * @date:   2018年8月7日 上午10:54:33
 */
public interface MtHisTaskRptQueryService {
	
	/**
	 * @Title: queryMtHisTaskPage   
	 *  运送记录分页查询 
	 * @author zhaoqing  
	 * @param dto
	 * @return 
	 */
	public ResultInfo queryMtHisTaskPage(MtHisTaskPageDto dto);
	
	/**
	 * @Title: queryMtHisFixedTaskExeInfoPage   
	 *  固定任务执行信息分页查询
	 * @author zhaoqing  
	 * @param dto
	 * @return
	 */
	public ResultInfo queryMtHisFixedTaskExeInfoPage(MtHisTaskPageDto dto);
	
	/**
	 * @Title: queryMtHisTaskDetail   
	 *  运送任务详情(自主任务/调度任务)
	 * @author zhaoqing  
	 * @param dto
	 * @return
	 */
	public ResultInfo queryMtHisTaskDetail(MtHisTaskPageDto dto);
	
	/**
	 * @Title: queryMtHisTaskFixedDetail   
	 *  运送任务详情(固定任务) 
	 * @author zhaoqing  
	 * @param dto
	 * @return
	 */
	public ResultInfo queryMtHisTaskFixedDetail(MtHisTaskPageDto dto);
	
	/**
	 * @Title: queryMtHisFixedTaskExeDetail   
	 *  固定任务某个点执行信息详情 
	 * @author zhaoqing  
	 * @param dto
	 * @return 
	 */
	public ResultInfo queryMtHisFixedTaskExeDetail(MtHisTaskPageDto dto);
	
}
