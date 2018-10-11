package com.segi.uhomecp.medicaltrans.baseinfo.taskloop.service;

import java.util.List;

import segiwh.common.User;

import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoop;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoopExecutors;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoopRoute;
import com.segi.uhomecp.medicaltrans.baseinfo.taskloop.dto.HouseDto;
import com.segi.uhomecp.medicaltrans.baseinfo.taskloop.dto.MtTaskLoopDto;
import com.segi.uhomecp.medicaltrans.baseinfo.taskloop.dto.MtTaskLoopPageDto;

public interface MtTaskLoopInfoService {
	
	/**
	 * @discription 循环任务分页列表
	 * @author yangyh@segimail.com       
	 * @created 2018年3月27日 下午3:31:08     
	 * @return
	 */
	public MtTaskLoopPageDto queryTaskLoopByPage(MtTaskLoopDto mtTaskLoopDto);
	
	/**
	 * @discription 新建循环任务
	 * @author yangyh@segimail.com       
	 * @created 2018年3月27日 下午3:03:32     
	 * @param mtTaskLoopDto
	 * @return
	 */
	public Integer saveTaskLoop(MtTaskLoopDto mtTaskLoopDto);
	
	/**
	 * @discription 编辑循环任务
	 * @author yangyh@segimail.com       
	 * @created 2018年3月27日 下午3:03:36     
	 * @param mtTaskLoopDto
	 * @return
	 */
	public Integer updateTaskLoop(MtTaskLoopDto mtTaskLoopDto);
	
	/**
	 * @discription 循环任务停用启用、删除修改状态
	 * @author yangyh@segimail.com       
	 * @created 2018年3月27日 下午3:03:14     
	 * @param taskLoopId
	 * @return
	 */
	public Integer updateTaskLoopStatus(MtTaskLoopDto mtTaskLoopDto);
	
	/**
	 * @discription 循环任务详情
	 * @author yangyh@segimail.com       
	 * @created 2018年3月27日 下午3:03:47     
	 * @param taskLoopId
	 * @return
	 */
	public MtTaskLoopDto queryTaskLoopDetail(Integer taskLoopId);
	
	/** @discription 根据循环任务id查询执行人List
	 * @author yangyh@segimail.com       
	 * @created 2018年3月28日 下午7:59:12     
	 * @param taskLoopId
	 * @return     
	 */
	public List<MtTaskLoopExecutors> queryExecutorsList(Integer taskLoopId);
	
	
	/** @discription 根据循环任务id查询线路List
	 * @author yangyh@segimail.com       
	 * @created 2018年3月28日 下午7:59:24     
	 * @param taskLoopId
	 * @return     
	 */
	public List<MtTaskLoopRoute> queryRouteList(Integer taskLoopId);
	
	/**
	 * @discription 查询线路（科室List，带状态）
	 * @author yangyh@segimail.com       
	 * @created 2018年3月27日 下午3:03:58     
	 * @param taskLoopId
	 * @return
	 */
	public List<HouseDto> queryHouseList(Integer taskLoopId);
	
	/**
	 * 获取循环任务
	 * @param organId
	 * @param status
	 * @param loopType
	 * @return
	 */
	public List<MtTaskLoop> queryTaskLoopInstList(Integer organId, String status, String loopType);
	
	/**
	 * @discription 根据循环任务id查询执行人List
	 * @author yangyh@segimail.com
	 * @created 2018年3月28日 下午7:41:52
	 * @param taskLoopId
	 * @return
	 */
	public List<MtTaskLoopExecutors> queryExecutorsListByList(List<Integer> taskLoopIdList);

	/**
	 * @discription 根据循环任务id查询线路List
	 * @author yangyh@segimail.com
	 * @created 2018年3月28日 下午7:42:37
	 * @param taskLoopId
	 * @return
	 */
	public List<MtTaskLoopRoute> queryRouteListByList(List<Integer> taskLoopIdList);

	/**
	 * @discription 根据循环任务id查询执行人List
	 * @author yangyh@segimail.com
	 * @created 2018年3月28日 下午7:42:37
	 * @param taskLoopId
	 * @return
	 */
	public List<User> queryUserInfoList(Integer taskLoopId);
	
	/**
	 * @discription 根据循环任务id查询线路IdList
	 * @author yangyh@segimail.com       
	 * @created 2018年7月31日 下午5:39:26     
	 * @param taskLoopId
	 * @return
	 */
	public List<Integer> selectLocationIdList(Integer taskLoopId);

	/**
	 * @discription 根据循环任务id查询执行人IdList
	 * @author yangyh@segimail.com       
	 * @created 2018年7月31日 下午5:39:34     
	 * @param taskLoopId
	 * @return
	 */
	public List<Integer> selectUserIdList(Integer taskLoopId);
	
	
	/**
	 * 根据位置id查询循环任务
	 * @param locationId
	 * @return
	 */
	public List<MtTaskLoop> findOnWayTaskLoopList(String locationId);

}
