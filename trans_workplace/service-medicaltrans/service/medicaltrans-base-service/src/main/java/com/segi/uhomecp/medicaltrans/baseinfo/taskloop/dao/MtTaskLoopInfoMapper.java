package com.segi.uhomecp.medicaltrans.baseinfo.taskloop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoop;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoopExecutors;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoopExecutorsCriteria;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoopRoute;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoopRouteCriteria;

public interface MtTaskLoopInfoMapper {
	
	/**
	 * @discription 批量保存路线信息
	 * @author yangyh@segimail.com       
	 * @created 2018年5月3日 下午4:13:35     
	 * @param mtTaskRouteList
	 */
	public void saveBatchMtTaskLoopRoute(@Param("list")List<MtTaskLoopRoute> routeList);

	/**
	 * @discription 批量保存执行人信息
	 * @author yangyh@segimail.com       
	 * @created 2018年5月3日 下午4:13:47     
	 * @param taskExcutorsList
	 */
	public void saveBatchMtTaskLoopExecutors(@Param("list")List<MtTaskLoopExecutors> executorsList);
	
	/**
	 * 获取循环任务
	 * @param organId
	 * @param status
	 * @param loopType
	 * @return
	 */
	public List<MtTaskLoop> queryTaskLoopInstList(
			@Param("organId") Integer organId, @Param("status") String status,
			@Param("loopType") String loopType);
	
	/**
	 * 根据taskLoopIdList查出所有关联路线
	 * @param example
	 * @return
	 */
	public List<MtTaskLoopRoute> selectLocationIdListByList(MtTaskLoopRouteCriteria example);

	/**
	 * 根据taskLoopIdList查出所有关联执行人
	 * @param example
	 * @return
	 */
	public List<MtTaskLoopExecutors> selectUserIdListByList(MtTaskLoopExecutorsCriteria example);
	
	/**
	 * 根据taskLoopId查出关联路线id
	 * @param taskLoopId
	 * @return
	 */
	public List<Integer> selectLocationIdList(@Param("taskLoopId")Integer taskLoopId);
	
	/**
	 * 根据taskLoopId查出关联执行人id
	 * @param taskLoopId
	 * @return
	 */	
	public List<Integer> selectUserIdList(@Param("taskLoopId")Integer taskLoopId);
	
	/**
	 * 根据位置id查询循环任务
	 * @param locationId
	 * @return
	 */
	public List<MtTaskLoop> findOnWayTaskLoopList(@Param("locationId")Integer locationId);

}
