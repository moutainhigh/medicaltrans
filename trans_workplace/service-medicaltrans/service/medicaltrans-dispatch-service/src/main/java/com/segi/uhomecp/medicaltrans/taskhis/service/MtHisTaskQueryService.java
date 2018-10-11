package com.segi.uhomecp.medicaltrans.taskhis.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.medicaltrans.taskhis.dto.MtHisTaskPageDto;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutors;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExt;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRoute;

/**
 * @ClassName:  MtHisTaskQueryService   
 * @Description:运送记录查询接口   
 * @author: zhaoqing
 * @date:   2018年8月7日 上午10:54:33
 */
public interface MtHisTaskQueryService {
	
	/**
	 * @Title: queryMtHisTaskPage   
	 *  运送记录分页查询 
	 * @author zhaoqing  
	 * @param dto
	 * @return 
	 */
	public PageList<MtHisTaskPageDto> queryMtHisTaskPage(Integer groupOrganId, MtHisTaskPageDto dto);
	
	/**
	 * @Title: queryMtHisTaskExtByTaskIds   
	 *  根据任务Id集合查询任务扩展信息
	 * @author zhaoqing  
	 * @param groupOrganId
	 * @param taskIdList
	 * @return
	 */
	public List<MtTaskExt> queryMtHisTaskExtByTaskIds(Integer groupOrganId, List<Integer> taskIdList);
	
	/**
	 * @Title: getHisTaskUserInfo   
	 *  根据任务Ids查询运送员信息 
	 * @author zhaoqing  
	 * @param groupOrganId
	 * @param dto
	 * @return 
	 */
	public List<MtTaskExecutors> getHisTaskUserInfo(Integer groupOrganId, MtHisTaskPageDto dto);
	
	/**
	 * @Title: queryMtTaskRouteHisPage   
	 *  执行信息分页查询 
	 * @author zhaoqing  
	 * @param groupOrganId
	 * @param dto
	 * @return 
	 */
	public PageList<MtTaskRoute> queryMtTaskRouteHisPage(Integer groupOrganId, MtHisTaskPageDto dto);
	
	/**
	 * @Title: queryMtTaskHisById   
	 *  根据任务Id查询任务信息 
	 * @author zhaoqing  
	 * @param groupOrganId
	 * @param taskId
	 * @return 
	 */
	public MtTask queryMtTaskHisById(Integer groupOrganId, Integer taskId);
	
	/**
	 * @Title: queryMtTaskExtHis   
	 *  根据任务Id查询任务扩展信息  
	 * @author zhaoqing  
	 * @param groupOrganId
	 * @param taskId
	 * @return 
	 */
	public MtTaskExt queryMtTaskExtHis(Integer groupOrganId, Integer taskId);
	
	/**
	 * @Title: queryTaskRouteHisByTask   
	 *  根据运送任务查询运输路线 
	 * @author zhaoqing  
	 * @param groupOrganId
	 * @param task
	 * @return      
	 */
	public MtTaskRoute queryTaskRouteHisByTask(Integer groupOrganId, MtTask task);
	
	/**
	 * @Title: queryTaskRouteHisById   
	 *  根据路线Id查询路线详情 
	 * @author zhaoqing  
	 * @param groupOrganId
	 * @param routeId
	 * @return
	 */
	public MtTaskRoute queryTaskRouteHisById(Integer groupOrganId, Integer routeId);
}
