package com.segi.uhomecp.medicaltrans.trans.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutors;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutorsLog;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExt;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskGroupRel;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRoute;

public interface MtTaskCommonMapper {
	
	/**
	 * @discription 批量保存路由信息
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月4日 下午1:59:04     
	 * @param mtTaskRouteList
	 */
	public void saveBatchMtTaskRoute(@Param("list")List<MtTaskRoute> mtTaskRouteList, @Param("tableIndex")String tableIndex);

	/**
	 * @discription 批量保存执行人信息
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月4日 下午1:59:30     
	 * @param taskExcutorsList
	 */
	public void saveBatchMtTaskExcutors(@Param("list")List<MtTaskExecutors> taskExcutorsList, @Param("tableIndex")String tableIndex);
	
	/**
	 * @discription 批量保存执行人log
	 * @author zhangyang@segimail.com       
	 * @created 2018年7月30日 上午11:02:37     
	 * @param taskExcutorsList
	 */
	public void saveBatchMtTaskExcutorsLog(@Param("list")List<MtTaskExecutorsLog> taskExcutorsList, @Param("tableIndex")String tableIndex);
	
	/**
	 * @discription 批量保存扩展信息
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月4日 下午1:59:30     
	 * @param taskExcutorsList
	 */
	public void saveBatchMtTaskExt(@Param("list")List<MtTaskExt> taskExtList, @Param("tableIndex")String tableIndex);
	
	/**
	 * @discription 批量保存任务信息
	 * @author wangxiong@segimail.com       
	 * @created 2018年4月4日 下午1:59:30     
	 * @param taskExcutorsList
	 */
	public void saveBatchMtTask(@Param("list")List<MtTask> taskList, @Param("tableIndex")String tableIndex);
	
	/**
	 * @discription 批量保存服务处信息
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月7日 下午2:27:33     
	 * @param taskGroupList
	 */
	public void saveBatchMtTaskGroup(@Param("list")List<MtTaskGroupRel> taskGroupList, @Param("tableIndex")String tableIndex); 
	
	/**
     * @discription 查询所有任务未完成数
     * @author zhangyang@segimail.com       
     * @created 2018年6月6日 下午8:45:13     
     * @param organId
     * @param status
     * @param userId
     * @param exeUserStatus
     */
    List<Integer> queryAllTaskCount(@Param("organId")Integer organId, 
    		@Param("userId")Integer userId, @Param("tableIndex")String tableIndex);
}
