package com.segi.uhomecp.medicaltrans.trans.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.medicaltrans.trans.dto.MtTaskPageDto;
import com.segi.uhomecp.medicaltrans.trans.dto.TaskAppQueryDto;
import com.segi.uhomecp.medicaltrans.trans.dto.TaskPadQueryDto;
import com.segi.uhomecp.medicaltrans.trans.dto.TaskWebQueryDto;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutorsLog;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExt;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRoute;

public interface MtTaskQueryService {
	/**
	 * @discription 运送任务分页查询
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月4日 下午4:55:45     
	 * @param params
	 * @return
	 */
	PageList<TaskWebQueryDto> queryMtTaskPage(int groupOrganId, MtTaskPageDto dto);
	
	/**
	 * @discription 历史任务分页查询
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月8日 下午6:50:49     
	 * @param params
	 * @return
	 */
	PageList<TaskAppQueryDto> queryMtTaskHistoryPage(int groupOrganId, MtTaskPageDto params);
	
	/**
	 * @discription 调度任务分页查询(活动中的任务 抢单/抢单完成)
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月26日 下午3:33:05     
	 * @param dto
	 * @return
	 */
	List<MtTask> queryDispatchTaskPageByRob(int groupOrganId, MtTaskPageDto dto);

	/**
	 * 
	 * 类描述: 自主任务分页(app)
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月2日 上午11:38:36
	 */
	List<TaskAppQueryDto> queryAutonomousTransTaskPage(int groupOrganId, MtTaskPageDto dto);

	
	/**
	 * @discription 固定任务执行信息分页查询
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月2日 上午11:25:26     
	 * @param dto
	 * @return
	 */
	PageList<MtTaskRoute> queryFixedTaskExeInfoPage(int groupOrganId, MtTaskPageDto dto);
	
	/**
	 * @discription 固定任务分页查询(活动中的任务)
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月8日 下午2:32:27     
	 * @param dto
	 * @return
	 */
	List<TaskAppQueryDto> queryFixedTaskPage(int groupOrganId, MtTaskPageDto dto);
	
	/**
	 * @discription 查询跟踪任务分页(pad端)
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月10日 下午2:22:11     
	 * @param dto
	 * @return
	 */
	PageList<TaskPadQueryDto> queryMtTaskPageForPad(int groupOrganId, MtTaskPageDto dto);
	
	/**
	 * @discription 查询任务评价信息分页
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月21日 上午11:09:47     
	 * @param dto
	 * @return
	 */
	PageList<TaskPadQueryDto> queryTaskEvaluatePageForPad(int groupOrganId, MtTaskPageDto dto);
	
	/**
	 * @discription 调度任务分页查询(活动中的任务  指定给用户)
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月14日 上午10:24:41     
	 * @param dto
	 * @return
	 */
	PageList<TaskAppQueryDto> queryDispatchTaskPageByAssign(int groupOrganId, MtTaskPageDto dto, Boolean isFilter);

	/**
	 * @discription 查询抢单数据
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月18日 上午11:54:12     
	 * @param groupId
	 * @param taskId
	 * @return
	 */
	List<MtTask> queryGradTaskByGroupId(int groupOrganId, Integer groupId, Integer taskId);
	
	/**
	 * @discription 查询抢单数据
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月18日 上午11:54:12     
	 * @param groupId
	 * @param taskId
	 * @return
	 */
	MtTask queryGradTaskByGroupTaskId(int groupOrganId, Integer groupId, Integer taskId);
	
	/**
	 * @discription 查询所有任务未完成数
	 * @author zhangyang@segimail.com       
	 * @created 2018年6月7日 上午11:56:26     
	 * @param organId
	 * @param userId
	 * @param exeUserStatus
	 * @return
	 */
	List<Integer> queryAllTaskCount(int groupOrganId, Integer organId, Integer userId);
	
	/**
	 * @discription 查询任务路线
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月11日 下午3:40:26     
	 * @param groupOrganId
	 * @param routeId
	 * @return
	 */
	MtTaskRoute queryMtTaskRoute(int groupOrganId, Integer routeId);
	
	/**
	 * @discription 通过taskId查询任务路线
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月27日 下午5:42:19     
	 * @param groupOrganId
	 * @param taskId
	 * @return
	 */
	MtTaskRoute queryMtTaskRouteByTaskId(int groupOrganId, Integer taskId);
	
	/**
	 * @discription 通过任务Id集合查询扩展表信息
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月11日 上午9:36:40     
	 * @param groupOrganId
	 * @param taskIds
	 * @return
	 */
	List<MtTaskExt> queryTaskExtInfoByTaskIds(int groupOrganId, List<Integer> taskIds);
	
	/**
	 * @discription 在执行人log表查询退单人员信息
	 * @author zhangyang@segimail.com 
	 * @param groupOrganId
	 * @param version
	 * @param taskId
	 * @return
	 */
	List<MtTaskExecutorsLog> queryBackTaskExeInfo(int groupOrganId, Integer version, Integer taskId);
	
}
