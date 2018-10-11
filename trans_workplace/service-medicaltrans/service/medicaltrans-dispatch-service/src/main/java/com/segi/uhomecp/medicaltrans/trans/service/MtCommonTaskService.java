package com.segi.uhomecp.medicaltrans.trans.service;

import java.util.Date;
import java.util.List;

import resp.RpcRespIce;

import com.segi.uhomecp.medicaltrans.trans.dto.CommonTaskDto;
import com.segi.uhomecp.medicaltrans.trans.dto.VerifyParamDto;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutors;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutorsLog;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExt;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskGroupRel;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRoute;
import com.segi.uhomecp.wh.common.dto.ResultDto;

/**
 * 
 * Title: MtCommonTaskService.java    
 * @Description:公共任务业务处理
 * @author wangxiong@segimail.com       
 * @created 2018年3月26日 上午9:53:25
 */
public interface MtCommonTaskService {
	
	/**
	 * @discription 批量保存执行人信息
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月4日 上午11:51:39     
	 * @param taskId
	 * @param userId
	 * @param version
	 */
	public void saveBatchMtTaskExcutors(int groupOrganId, List<MtTaskExecutors> taskExcutorsList, String tableIndex);
	
	/**
	 * @discription 批量保存执行人log表
	 * @author zhangyang@segimail.com       
	 * @created 2018年7月30日 上午10:58:41     
	 * @param taskExcutorsList
	 */
	public void saveBatchMtTaskExcutorsLog(int groupOrganId, List<MtTaskExecutorsLog> taskExcutorsList, String tableIndex);

	/**
	 * @discription 批量保存路径信息
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月4日 上午11:51:20     
	 * @param taskRouteList
	 */
	public void saveBatchMtTaskRoute(int groupOrganId, List<MtTaskRoute> taskRouteList, String tableIndex);

	/**
	 * @discription 批量保存任务
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月4日 上午11:51:39     
	 * @param taskId
	 * @param userId
	 * @param version
	 */
	public void saveBatchMtTask(int groupOrganId, List<MtTask> taskList, String tableIndex);
	
	/**
	 * @discription 批量保存任务扩展信息
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月4日 上午11:51:39     
	 * @param taskId
	 * @param userId
	 * @param version
	 */
	public void saveBatchMtTaskExt(int groupOrganId, List<MtTaskExt> taskExtList, String tableIndex);
	
	/**
	 * @discription 更新人员的未完成数量信息
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月8日 下午5:36:34     
	 * @param mtTask
	 * @param userId
	 * @param nowDate
	 */
	public void updateUserPositUnTaskNum(int groupOrganId, MtTask mtTask, String locationId); 
	
	/**
	 * @discription 更新退单状态
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月10日 上午10:50:31     
	 * @param userId
	 * @param taskId
	 * @param nowDate
	 */
	public ResultDto<String, String, MtTask> updateBackTaskNotStartStatus(int groupOrganId, 
			MtTask mtTask, Integer userId, Date nowDate, String taskType);
	
	/**
	 * 
	 * 类描述: 根据organId和transTypeCode判断有没有使用中的类型
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年4月4日 下午4:11:27
	 */
//	Boolean judgeTransType(int groupOrganId, String organId, String transTypeId);
	
	/**
	 * 根据位置id查是否有在途运送单
	 * @param locationId
	 * @return
	 */
//	Boolean judgeOnWayTask(int groupOrganId, String locationId);
	
	/**
	 * @discription 通过taskList获取任务执行人
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月10日 上午11:16:50     
	 * @param taskList
	 * @return
	 */
	public <V> List<MtTaskExecutors> getTaskUserInfoList(int groupOrganId, List<V> taskList);
	
	/**
	 * @discription 查询运送任务执行人,根据exeUserId
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月14日 下午5:50:20     
	 * @param taskList
	 * @param status
	 * @param exeUserId
	 * @return
	 */
	public List<MtTaskExecutors> getTaskUserInfoByExeUserId(int groupOrganId, List<MtTask> taskList, Integer exeUserId);
	
	/**
	 * 
	 * 类描述: 根据taskId查询扩展信息
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年4月21日 上午9:51:57
	 */
	public MtTaskExt queryMtTaskExtInfo(int groupOrganId, Integer taskId);
	
	
	/**
	 * @discription 在此输入一句话描述作用
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月25日 下午5:31:33     
	 * @param t
	 * @return
	 */
	public VerifyParamDto queryMtTaskById(int groupOrganId, Integer taskId) ;

	/**
	 * @discription 评价
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月26日 下午3:46:33     
	 * @param dto
	 * @return
	 */
	public ResultDto<String, String, String> handlerEvaluate(int groupOrganId, CommonTaskDto dto, MtTask mtTask);

	/**
	 * @discription 根据beforeList以及taskId更新任务状态
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月27日 下午12:01:44     
	 * @param taskId
	 * @param afterStatus
	 * @param beforeList
	 * @return
	 */
	public int updateMtTaskStatus(int groupOrganId, MtTask dto,List<String> beforeList,Integer taskId);
	
	/**
	 * @discription  根据beforeList以及taskId更新任务状态
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月27日 下午2:13:09     
	 * @param taskId
	 * @param afterStatus
	 * @param beforeStatus
	 * @return
	 */
	public int updateMtTaskStatus(int groupOrganId, MtTask dto, String beforeStatus,Integer taskId) ;
	
	/**
	 * 
	 * 类描述: 根据taskId查询运送任务人员信息
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年4月21日 上午11:13:16
	 */
	public List<MtTaskExecutors> queryMtTaskExcutorsByTaskId(int groupOrganId, Integer taskId, String sort);
	
	/**
	 * @discription 批量保存服务处信息
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月7日 下午3:19:26     
	 * @param taskGroupList
	 */
	public void saveBatchMtTaskGroup(int groupOrganId, List<MtTaskGroupRel> taskGroupList, String tableIndex);
	
	/**
	 * @discription 通过taskId删除服务处信息
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月7日 下午3:40:19     
	 * @param taskId
	 */
	public void deleteMtTaskGroupByTaskId(int groupOrganId, Integer taskId);
	
	/**
	 * @discription 设置责任人,设置具体的用户为责任人
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月8日 下午8:06:23     
	 * @param taskId
	 * @param exeUserId
	 * @param isExeEndUserId
	 */
	public void updateTaskExcutors(int groupOrganId, Integer taskId, String exeUserId, String isExeEndUser, String taskStatus);
	
	public void updateTaskExcutors(int groupOrganId, Integer taskId, String exeUserId, String isExeEndUser, String taskStatus, 
			String isTimeOut, Date exeBeginTime, Date exeEndTime);
	
	public void updateTaskExcutors(int groupOrganId, Integer taskId, List<Integer> exeUserId, String isExeEndUser, 
			String taskStatus, String isTimeOut, Date exeBeginTime, Date exeEndTime);
	
	/**
	 * @discription 评价时修改执行人表
	 * @author zhangyang@segimail.com       
	 * @created 2018年7月31日 下午6:57:22     
	 * @param taskId
	 * @param exeUserId
	 * @param version
	 */
	public void updateTaskExcutorsByEvaluate(int groupOrganId, Integer taskId, Short evaluate, Date nowDate);
	
	/**
	 * @discription 根据taskId获取MtTaskGroupRel 的list
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月16日 下午3:21:05     
	 * @param taskId
	 * @param exeUserId
	 * @param isExeEndUser
	 */
	public List<MtTaskGroupRel> queryServiceByTaskId(int groupOrganId, Integer taskId);
	
	/**
	 * @discription 修改扩展表信息
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月18日 下午6:54:45     
	 * @param dto
	 * @param taskId
	 */
	public void updateMtTaskExt(int groupOrganId, MtTaskExt ext);
	
	public void updateMtTaskExtContent(int groupOrganId, MtTaskExt ext, Integer taskId);
	
	/**
	 * @discription 签名
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月28日 上午9:50:13     
	 * @param dto
	 */
	public String handlerAutograph(int groupOrganId, CommonTaskDto dto);
	
	/**
	 * @discription 验证是否可以签名
	 * @author zhangyang@segimail.com       
	 * @created 2018年6月29日 下午4:13:44     
	 * @param routeId
	 * @return
	 */
	public RpcRespIce checkCanAutograph(int groupOrganId, Integer routeId);
	
	/**
	 * @discription 通过taskId和exeUserId删除执行人
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月6日 下午3:53:17     
	 * @param taskId
	 * @param userId
	 */
	public void deleteTaskExecutorsByTaskId(int groupOrganId, Integer taskId, String userId);
	
	/**
	 * @discription 通过taskId删除执行人
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月6日 下午3:54:04     
	 * @param taskId
	 */
	public void deleteTaskExecutorsByTaskId(int groupOrganId, Integer taskId);
	
	public void deleteTaskExecutorsByTaskId(int groupOrganId, Integer taskId, List<Integer> userIdList);
	
	/**
	 * @discription 完成任务时修改路线表
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月6日 下午3:54:38     
	 * @param mtTask
	 * @param finishContent
	 * @param taskId
	 * @param nowDate
	 */
	public void updateTaskRoute(int groupOrganId, MtTask mtTask, String finishContent,
			Integer taskId, Date nowDate);
	
	/**
	 * @discription 通过taskId查询任务单
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月14日 下午4:32:59     
	 * @param groupOrganId
	 * @param taskId
	 * @return
	 */
	public MtTask queryMtTaskByTaskId(int groupOrganId, int taskId);
	
	/**
	 * @discription 运送任务预约时间7天没完成异常关闭(除取消/完成状态)
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月31日 上午10:01:10     
	 * @param groupOrganId
	 * @param organIdList
	 * @param limitDate
	 * @param nowDate
	 * @param statusList
	 * @param rsp
	 * @return
	 */
    public RpcRespIce handlerTaskUnusalClose(int groupOrganId, List<Integer> organIdList, long limitDate, 
    		Date nowDate, List<String> statusList, RpcRespIce rsp);

    /**
     * @Title: checkCanPhotograph 
     * @Description: 验证是否能够拍照 
     * @author liuyi@segimail.com 
     * @date 2018年9月12日下午4:03:25
     */
	public RpcRespIce checkCanPhotograph(Integer groupOrganId, Integer taskId);
	
}
