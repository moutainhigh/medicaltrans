package com.segi.uhomecp.medicaltrans.trans.service;

import com.segi.uhomecp.medicaltrans.trans.dto.CommonTaskDto;
import com.segi.uhomecp.medicaltrans.trans.dto.FixedTaskDto;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.wh.common.dto.ResultDto;

/**
 * 
 * Title: FixedTaskService.java    
 * @Description: 固定任务业务处理
 * @author wangxiong@segimail.com       
 * @created 2018年3月26日 上午9:53:25
 */
public interface FixedTaskService {
	/**
	 * @discription 创建任务
	 * @author wangxiong@segimail.com       
	 * @created 2018年3月26日 上午9:49:11     
	 * @param t
	 */
	public ResultDto<String, String,Integer> createTask(int groupOrganId, FixedTaskDto t) ;
	
	/**
	 * @discription 重新派单
	 * @author wangxiong@segimail.com       
	 * @created 2018年3月26日 上午9:49:11     
	 * @param t
	 */
	public ResultDto<String, String, Integer> handlerAgainTask(int groupOrganId, 
			MtTask beforeMtTask, FixedTaskDto t);
	
	/**
	 * @discription 退单
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月9日 下午7:50:05     
	 * @param dto
	 * @return
	 */
	public ResultDto<String, String, MtTask> handlerBackTask(int groupOrganId, 
			MtTask task, CommonTaskDto dto);

	/**
	 * @discription 完成任务
	 * @author wangxiong@segimail.com       
	 * @created 2018年3月26日 上午9:49:11     
	 * @param t
	 */
	public ResultDto<String, String, String> handlerFinishTask(int groupOrganId, 
			MtTask mtTask, CommonTaskDto dto);

	/**
	 * @discription 取消任务
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月8日 下午8:20:53     
	 * @param mtTask
	 * @param userId
	 * @param userOrganId
	 * @return
	 */
	public ResultDto<String, String, Integer> handlerCancelTask(int groupOrganId, 
			MtTask mtTask, Integer userId, Integer userOrganId);
	
}
