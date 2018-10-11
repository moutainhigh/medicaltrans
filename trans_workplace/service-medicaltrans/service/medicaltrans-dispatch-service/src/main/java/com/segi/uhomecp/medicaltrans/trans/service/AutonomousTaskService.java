package com.segi.uhomecp.medicaltrans.trans.service;

import com.segi.uhomecp.medicaltrans.trans.dto.AutonomousTaskDto;
import com.segi.uhomecp.medicaltrans.trans.dto.CommonTaskDto;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.wh.common.dto.ResultDto;

/**
 * 
 * Title: AutonomousTaskService.java    
 * @Description: 自主任务业务类
 * @author wangxiong@segimail.com       
 * @created 2018年3月26日 上午9:46:39
 */
public interface AutonomousTaskService {
	/**
	 * @discription 创建任务
	 * @author wangxiong@segimail.com       
	 * @created 2018年3月26日 上午9:49:11     
	 * @param t
	 */
	public ResultDto<String,String, Integer> createTask(int groupOrganId, AutonomousTaskDto t) ;
	
	/**
	 * @discription 取消任务
	 * @author wangxiong@segimail.com       
	 * @created 2018年3月26日 上午9:49:11     
	 * @param t
	 */
	public ResultDto<String,String, Integer> handlerCancelTask(int groupOrganId, MtTask obj, Integer userId);
	
	/**
	 * @discription 完成任务
	 * @author wangxiong@segimail.com       
	 * @created 2018年3月26日 上午9:49:11     
	 * @param t
	 */
	public ResultDto<String, String, String> handlerFinishTask(int groupOrganId, MtTask mtTask,
			CommonTaskDto dto);

	/**
	 * @Title: handlerFinishWebTask 
	 * @Description:  完成任务Web
	 * @author liuyi@segimail.com 
	 * @date 2018年9月12日上午11:25:34
	 */
	public ResultDto<String, String, String> handlerFinishWebTask(
			Integer groupOrganId, MtTask mtTask, CommonTaskDto dto);

}
