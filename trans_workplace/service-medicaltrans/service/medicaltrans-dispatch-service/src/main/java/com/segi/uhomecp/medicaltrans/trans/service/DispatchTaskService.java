package com.segi.uhomecp.medicaltrans.trans.service;

import com.segi.uhomecp.medicaltrans.trans.dto.CommonTaskDto;
import com.segi.uhomecp.medicaltrans.trans.dto.DispatchTaskDto;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.wh.common.dto.ResultDto;

/**
 * Title: DispatchTaskService.java    
 * @Description: 调度任务业务处理
 * @author wangxiong@segimail.com       
 * @created 2018年3月26日 上午9:52:18
 */

public interface DispatchTaskService {
	/**
	 * @discription 创建任务
	 * @author wangxiong@segimail.com       
	 * @created 2018年3月26日 上午9:49:11     
	 * @param t
	 */
	public ResultDto<String, String, Integer> createPadTask(int groupOrganId, DispatchTaskDto t) ;
	
	/**
	 * @discription 任务派发
	 * @author wangxiong@segimail.com       
	 * @created 2018年3月28日 下午8:21:23     
	 * @param t
	 * @return
	 */
	public ResultDto<String, String, Integer> handlerDispatchTask(int groupOrganId, DispatchTaskDto dto, MtTask beforeMtTask);
	
	/**
	 * @discription 调度任务重新派发
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月5日 下午2:13:16     
	 * @param t
	 * @return
	 */
	public ResultDto<String, String, Integer> handlerAgainDispatchTask(int groupOrganId, MtTask beforeMtTask, DispatchTaskDto t);
	
	/**
	 * @discription 创建任务
	 * @author wangxiong@segimail.com       
	 * @created 2018年3月26日 上午9:49:11     
	 * @param t
	 */
	public ResultDto<String, String, Integer> createTask(int groupOrganId, DispatchTaskDto t) ;

	/**
	 * @discription 取消任务
	 * @author wangxiong@segimail.com       
	 * @created 2018年3月26日 上午9:49:11     
	 * @param t
	 */
	public ResultDto<String,String, Integer> handlerCancelTask(int groupOrganId, MtTask obj,
			Integer userId, Integer userOrganId);
	
	/**
	 * @discription 退单
	 * @author wangxiong@segimail.com       
	 * @created 2018年3月26日 上午9:49:11     
	 * @param t
	 */
	public ResultDto<String, String, MtTask> handlerBackTask(int groupOrganId, MtTask task,	CommonTaskDto dto);
	
	/**
	 * @discription 抢单
	 * @author wangxiong@segimail.com       
	 * @created 2018年3月26日 上午9:49:11     
	 * @param t
	 */
	public ResultDto<String, Integer, Boolean> handlerGrabTask(int groupOrganId, MtTask mtTask, CommonTaskDto t);
	
	/**
	 * @discription 设置责任人
	 * @author wangxiong@segimail.com       
	 * @created 2018年3月26日 上午9:49:11     
	 * @param t
	 */
	public ResultDto<String,String, MtTask> handlerSettingResponsiblePerson(int groupOrganId, CommonTaskDto dto, MtTask t);

	/**
	 * @discription 编辑任务单
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月26日 下午3:17:22     
	 * @param dto
	 * @param mtTask
	 */
	public ResultDto<String, String, String> handlerEditTask(int groupOrganId, DispatchTaskDto dto, MtTask mtTask);

	/**
	 * @discription 完成任务
	 * @author wangxiong@segimail.com       
	 * @created 2018年3月26日 上午9:49:11     
	 * @param t
	 */
	public ResultDto<String, String, String> handlerFinishTask(int groupOrganId, MtTask mtTask,
			CommonTaskDto dto);
	/**
	 * @discription 开始任务
	 * @author wangxiong@segimail.com       
	 * @created 2018年3月26日 上午9:49:11     
	 * @param t
	 */
	public ResultDto<String, String, String> handlerStartTask(int groupOrganId, CommonTaskDto dto,
			MtTask mtTask);

	/**
	 * @Title: handlerFinishWebTask 
	 * @Description: 完成任务web 
	 * @author liuyi@segimail.com 
	 * @date 2018年9月12日上午9:32:49
	 */
	public ResultDto<String, String, String> handlerFinishWebTask(
			Integer groupOrganId, MtTask mtTask, CommonTaskDto dto);

}
