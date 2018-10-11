package com.segi.uhomecp.medicaltrans.trans.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.medicaltrans.trans.dto.MtTaskPageDto;
import com.segi.uhomecp.medicaltrans.trans.dto.PageDto;
import com.segi.uhomecp.medicaltrans.trans.dto.TaskAppQueryDto;
import com.segi.uhomecp.medicaltrans.trans.dto.TaskPadQueryDto;
import com.segi.uhomecp.medicaltrans.trans.dto.TaskWebQueryDto;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskCriteria;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutors;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutorsCriteria;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRoute;

public interface MtTaskQueryMapper {
	
	/**
	 * @discription 查询运送任务执行人,根据版本号
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月10日 上午9:59:38     
	 * @param taskIdList
	 * @return
	 */
	public <V> List<MtTaskExecutors> getTaskUserInfo(@Param("list")List<V> taskList);
	
	/**
	 * @discription 查询运送任务执行人,根据版本号和userId
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月14日 下午5:54:00     
	 * @param taskList
	 * @param status
	 * @param exeUserId
	 * @return
	 */
	public List<MtTaskExecutors> getTaskUserInfoByExeUserId(@Param("list")List<MtTask> taskList, 
			@Param("exeUserId") Integer exeUserId);

    /**
     * @discription 根据groupId查询抢单任务列表
     * @author wangxiong@segimail.com       
     * @created 2018年5月18日 上午11:52:58     
     * @param example
     * @param groupId
     * @return
     */
    PageList<MtTask> selectGradTaskByGroupId(@Param("example")MtTaskCriteria example,
    		@Param("groupId")Integer groupId);
    
    /**
     * @discription 通过分页条件按页返回taskId(web/pad端公用)
     * @author zhangyang@segimail.com       
     * @created 2018年7月25日 下午4:17:14     
     * @param example
     * @param dto
     * @return
     */
    List<Integer> queryTaskIdPage(@Param("example") MtTaskCriteria example, @Param("dto")PageDto dto);
    
    /**
     * @discription 获取满足条件的总任务数(web/pad端公用)
     * @author zhangyang@segimail.com       
     * @created 2018年7月25日 下午4:17:46     
     * @param example
     * @return
     */
    Integer countTaskTotalNum(@Param("example") MtTaskCriteria example);
    
    /**
     * @discription 通过taskIdList 查询任务单(web端)
     * @author zhangyang@segimail.com       
     * @created 2018年7月25日 下午4:18:28     
     * @param taskIdList
     * @return
     */
    List<TaskWebQueryDto> queryTaskPageForWebByTaskIds(@Param("list")List<Integer> taskIdList);
    
    /**
     * @discription 通过taskIdList 查询任务单(pad端)
     * @author zhangyang@segimail.com       
     * @created 2018年7月25日 下午4:18:34     
     * @param taskIdList
     * @return
     */
    List<TaskPadQueryDto> queryTaskPageForPadByTaskIds(@Param("list")List<Integer> taskIdList);
    
    /**
     * @discription 通过taskIdList 查询任务单(app端)
     * @author zhangyang@segimail.com       
     * @created 2018年7月25日 下午4:20:35     
     * @param taskIdList
     * @return
     */
    List<TaskAppQueryDto> queryTaskPageForAppByTaskIds(@Param("list")List<Integer> taskIdList, @Param("sort")String sort);
    
    /**
     * @discription 获取调度任务数(筛选后的app端)
     * @author zhangyang@segimail.com       
     * @created 2018年8月6日 下午6:34:39     
     * @param example
     * @param userId
     * @return
     */
    Integer countDispatchTaskForApp(@Param("example") MtTaskExecutorsCriteria example);
    
    /**
     * @discription 获取历史任务数(app端)
     * @author zhangyang@segimail.com       
     * @created 2018年8月6日 下午6:35:19     
     * @param dto
     * @return
     */
    Integer countHistoryTask(MtTaskPageDto dto);
    
    /**
     * @discription 查询固定任务执行信息分页
     * @author zhangyang@segimail.com       
     * @created 2018年8月22日 上午9:39:48     
     * @param taskId
     * @param pageLimit
     * @return
     */
    List<MtTaskRoute> queryFixedTaskExeInfoPage(@Param("taskId") Integer taskId, @Param("dto")PageDto pageLimit);
    
    /**
     * @discription 查询固定任务路线数
     * @author zhangyang@segimail.com       
     * @created 2018年8月22日 上午9:40:16     
     * @param taskId
     * @return
     */
    Integer countFixedTaskRoute(@Param("taskId") Integer taskId);
    
    /**
     * @discription 查询满足条件的taskId List(app端)
     * @author zhangyang@segimail.com       
     * @created 2018年8月22日 上午9:44:12     
     * @param dto
     * @param example
     * @param pageLimit
     * @return
     */
    List<Integer> queryTaskIdPageForApp(@Param("example") MtTaskExecutorsCriteria example, @Param("dto")PageDto pageLimit);
}
