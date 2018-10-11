package com.segi.uhomecp.medicaltrans.trans.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import segi.datacachesvr.queryInfo.GroupUserBrief;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.enums.InvokingFlagEnum;
import com.segi.uhomecp.medicaltrans.enums.TransStatusEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTaskTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransUserStatusEnum;
import com.segi.uhomecp.medicaltrans.trans.cache.MtTaskGrabRedisCache;
import com.segi.uhomecp.medicaltrans.trans.dao.MtTaskCommonMapper;
import com.segi.uhomecp.medicaltrans.trans.dao.MtTaskQueryMapper;
import com.segi.uhomecp.medicaltrans.trans.dto.MtTaskPageDto;
import com.segi.uhomecp.medicaltrans.trans.dto.PageDto;
import com.segi.uhomecp.medicaltrans.trans.dto.TaskAppQueryDto;
import com.segi.uhomecp.medicaltrans.trans.dto.TaskPadQueryDto;
import com.segi.uhomecp.medicaltrans.trans.dto.TaskWebQueryDto;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskCriteria;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutorsCriteria;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutorsLog;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutorsLogCriteria;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExt;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExtCriteria;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRoute;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRouteCriteria;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskExecutorsLogService;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskExtService;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskRouteService;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskService;
import com.segi.uhomecp.medicaltrans.trans.service.MtTaskQueryService;
import com.segi.uhomecp.medicaltrans.utils.DynamicTableSourceKeyHolder;
import com.segi.uhomecp.medicaltrans.utils.MtIbatchQueryServiceUtils;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.AppUtils;

@Service
public class MtTaskQueryServiceImpl implements MtTaskQueryService {
	
	@Autowired
	public MtTaskService mtTaskService;
	
	@Autowired
	private MtTaskQueryMapper mtTaskQueryMapper;
	
	@Autowired
	private MtTaskCommonMapper mtTaskCommonMapper;

	@Autowired
	private MtTaskRouteService mtTaskRouteService;
	
	@Autowired
	private MtTaskGrabRedisCache mtTaskGrabRedisCache;
	
	@Autowired
	private MtTaskExtService mtTaskExtService;
	
	@Autowired
	public MtTaskExecutorsLogService mtTaskExecutorsLogService;

	/**
	 * @discription 运送任务分页查询
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月4日 下午4:56:29      
	 * @param params
	 * @return     
	 */
	@Override
	public PageList<TaskWebQueryDto> queryMtTaskPage(int groupOrganId, MtTaskPageDto dto) {
		MtTaskCriteria example = new MtTaskCriteria();
		MtTaskCriteria.Criteria criteria = example.createCriteria();
		int pageNo = Constant.INIT_PAGE_NO;
		if (null != dto.getPageNo() 
				&& dto.getPageNo().intValue() != 0) {
			//先对页码初始化,然后进行判断并赋值
			pageNo = dto.getPageNo();
		}
		int pageLength = Constant.DEFAULT_PAGE_LENGTH;
		if (null != dto.getPageLength() 
				&& dto.getPageLength().intValue() != 0) { 
			//先对每页记录数初始化,然后进行判断并赋值
			pageLength = dto.getPageLength();
		}
		PageDto pageLimit = getPageLimit(pageNo, pageLength);
		if (null != dto.getTaskId()
				&& dto.getTaskId().intValue() > 0) {
			// 添加任务Id条件(非必填)
			criteria.andTaskIdEqualTo(dto.getTaskId());
		}
		criteria.andOrganIdEqualTo(dto.getOrganId());
		
		if (StringUtils.isNotEmpty(dto.getUrgency())) {
			criteria.andUrgencyEqualTo(dto.getUrgency());
		}
		if (StringUtils.isNotEmpty(dto.getStatus())) {
			criteria.andStatusEqualTo(dto.getStatus());
		}
		if (StringUtils.isNotEmpty(dto.getTransTypeParentCode())) {
			criteria.andTransTypeParentCodeEqualTo(dto.getTransTypeParentCode());
		}
		if (StringUtils.isNotEmpty(dto.getTaskType())) {
			criteria.andTaskTypeEqualTo(dto.getTaskType());
		}
		criteria.andCreateDateBetween(Long.valueOf(dto.getBeginTime()), Long.valueOf(dto.getEndTime()));
		example.setOrderByClause(MtConstant.CREATE_DATE_DESC);
		List<Integer> taskIdList = this.mtTaskQueryMapper.queryTaskIdPage(example, pageLimit);
		List<TaskWebQueryDto> taskList = new ArrayList<>();
		int totalCount = 0;
		if (AppUtils.isNotEmpty(taskIdList)) {
			taskList = mtTaskQueryMapper.queryTaskPageForWebByTaskIds(taskIdList);
			totalCount = mtTaskQueryMapper.countTaskTotalNum(example);
		}
		Paginator paginator = new Paginator(pageNo, pageLength, totalCount);
		return new PageList<TaskWebQueryDto>(taskList, paginator);
	}
	
	/**
	 * @discription 历史任务分页查询
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月8日 下午6:55:02      
	 * @param params
	 * @return     
	 */
	@Override
	public PageList<TaskAppQueryDto> queryMtTaskHistoryPage(int groupOrganId, MtTaskPageDto dto) {
		int pageNo = Constant.INIT_PAGE_NO;
		if (null != dto.getPageNo() 
				&& Integer.valueOf(dto.getPageNo()).intValue() != 0) {
			//先对页码初始化,然后进行判断并赋值
			pageNo = Integer.valueOf(dto.getPageNo());
		}
		int pageLength = Constant.DEFAULT_PAGE_LENGTH;
		if (null != dto.getPageLength() 
				&& Integer.valueOf(dto.getPageLength()).intValue() != 0) { 
			//先对每页记录数初始化,然后进行判断并赋值
			pageLength = Integer.valueOf(dto.getPageLength());
		}
		// 获取分页对象
		PageDto pageLimit = getPageLimit(pageNo, pageLength);
		List<TaskAppQueryDto> taskList = new ArrayList<>();
		dto.setMtTaskStatus(TransStatusEnum.TRANS_COMPLETED.getCode());
		List<Integer> taskIdList = queryTaskIdForApp(dto, pageLimit, MtConstant.EXE_END_TIME_DESC);
		int totalCount = 0;
		if (AppUtils.isNotEmpty(taskIdList)) {
			taskList = this.mtTaskQueryMapper.queryTaskPageForAppByTaskIds(taskIdList, MtConstant.EXE_END_TIME_DESC);
			totalCount = mtTaskQueryMapper.countHistoryTask(dto);
		}
		Paginator paginator = new Paginator(pageNo, pageLength, totalCount);
		return new PageList<TaskAppQueryDto>(taskList, paginator);
	}
	
	/**
	 * @discription 查询app端列表分页任务Id
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月22日 上午11:08:29     
	 * @param dto
	 * @param pageLimit
	 * @return
	 */
	private List<Integer> queryTaskIdForApp(MtTaskPageDto dto, PageDto pageLimit, String sort) {
		MtTaskExecutorsCriteria example = new MtTaskExecutorsCriteria();
		MtTaskExecutorsCriteria.Criteria criteria = example.createCriteria();
		criteria.andTaskTypeEqualTo(dto.getTaskType());
		criteria.andOrganIdEqualTo(dto.getOrganId());
		criteria.andExeUserIdEqualTo(dto.getUserId());
		if (StringUtils.isNotBlank(dto.getMtTaskStatus())) {
			criteria.andTaskStatusEqualTo(dto.getMtTaskStatus());
		}
		if (AppUtils.isNotEmpty(dto.getTaskStatusList())) {
			criteria.andTaskStatusIn(dto.getTaskStatusList());
		}
		example.setOrderByClause(sort);
		return mtTaskQueryMapper.queryTaskIdPageForApp(example, pageLimit);
	}

	/**
	 * @discription 调度任务分页查询(活动中的任务 抢单/抢单完成)
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月26日 下午3:33:53      
	 * @param dto
	 * @return     
	 */
	@Override
    public List<MtTask> queryDispatchTaskPageByRob(int groupOrganId, MtTaskPageDto dto) {
		//1通过userId获取服务处
		Set<MtTask> mtTaskList = new HashSet<>();
		List<Integer> groupIdList = new ArrayList<>();
		GroupUserBrief[] groupArr = MtIbatchQueryServiceUtils.queryGroupIdsByUserList(String.valueOf(dto.getUserId()));
		if (null != groupArr && groupArr.length > 0) {
			groupIdList = AppUtils.list2ParamsList(Arrays.asList(groupArr), (obj) -> obj.getGroupId());
		}
		for (Integer groupId : groupIdList) {
			//2.通过服务处查缓存 获取List<MtTask>
			List<MtTask> taskRedisList = mtTaskGrabRedisCache.getGradTaskDepRedis(groupId);
			if (AppUtils.isNotEmpty(taskRedisList)) {
				mtTaskList.addAll(taskRedisList);
			}
		}
		// 通过organId筛选
		List<MtTask> delList = new ArrayList<>();
		if (AppUtils.isNotEmpty(mtTaskList)) {
			for (MtTask task : mtTaskList) {
				if (null != dto.getOrganId() && null != task.getOrganId()
						&& dto.getOrganId().intValue() != task.getOrganId().intValue()) {
					delList.add(task);
				}
			}
			// 删除过滤的数据
			mtTaskList.removeAll(delList);
		}
		return new ArrayList<MtTask>(mtTaskList);
    }

	/**
	 * 
	 * 类描述: 自主任务分页(app)
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月2日 上午11:38:36
	 */
	@Override
	public List<TaskAppQueryDto> queryAutonomousTransTaskPage(int groupOrganId, MtTaskPageDto dto) {
		int pageNo = Constant.INIT_PAGE_NO;
		if (null != dto.getPageNo() 
				&& Integer.valueOf(dto.getPageNo()).intValue() != 0) {
			//先对页码初始化,然后进行判断并赋值
			pageNo = Integer.valueOf(dto.getPageNo());
		}
		int pageLength = Constant.DEFAULT_PAGE_LENGTH;
		if (null != dto.getPageLength() 
				&& Integer.valueOf(dto.getPageLength()).intValue() != 0) { 
			//先对每页记录数初始化,然后进行判断并赋值
			pageLength = Integer.valueOf(dto.getPageLength());
		}
		PageDto pageLimit = getPageLimit(pageNo, pageLength);
		dto.setMtTaskStatus(TransStatusEnum.TRANS_RUNNING.getCode());
		List<Integer> taskIdList = queryTaskIdForApp(dto, pageLimit, MtConstant.END_TIME_ASC);
		List<TaskAppQueryDto> taskList = new ArrayList<>();
		if (AppUtils.isNotEmpty(taskIdList)) {
			taskList = this.mtTaskQueryMapper.queryTaskPageForAppByTaskIds(taskIdList, MtConstant.END_TIME_ASC);
		}
		return taskList;
	}

	/**
	 * @discription 固定任务执行信息分页查询
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月2日 下午12:00:57      
	 * @param dto
	 * @return     
	 */
	@Override
	public PageList<MtTaskRoute> queryFixedTaskExeInfoPage(int groupOrganId, MtTaskPageDto dto) {
		int pageNo = Constant.INIT_PAGE_NO;
		if (null != dto.getPageNo() 
				&& Integer.valueOf(dto.getPageNo()).intValue() != 0) {
			//先对页码初始化,然后进行判断并赋值
			pageNo = Integer.valueOf(dto.getPageNo());
		}
		int pageLength = Constant.DEFAULT_PAGE_LENGTH;
		if (null != dto.getPageLength() 
				&& Integer.valueOf(dto.getPageLength()).intValue() != 0) { 
			//先对每页记录数初始化,然后进行判断并赋值
			pageLength = Integer.valueOf(dto.getPageLength());
		}
		PageDto pageLimit = getPageLimit(pageNo, pageLength);
		List<MtTaskRoute> routeList = mtTaskQueryMapper.queryFixedTaskExeInfoPage(dto.getTaskId(), pageLimit);
		Integer totalCount = 0;
		if (AppUtils.isNotEmpty(routeList)) {
			totalCount = mtTaskQueryMapper.countFixedTaskRoute(dto.getTaskId());
		}
		Paginator paginator = new Paginator(pageNo, pageLength, totalCount);
		return new PageList<MtTaskRoute>(routeList, paginator);
	}

	/**
	 * @discription 固定任务分页查询(活动中的任务)
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月8日 下午2:34:50      
	 * @param dto
	 * @return     
	 */
	@Override
	public List<TaskAppQueryDto> queryFixedTaskPage(int groupOrganId, MtTaskPageDto dto) {
		int pageNo = Constant.INIT_PAGE_NO;
		if (null != dto.getPageNo() 
				&& Integer.valueOf(dto.getPageNo()).intValue() != 0) {
			//先对页码初始化,然后进行判断并赋值
			pageNo = Integer.valueOf(dto.getPageNo());
		}
		int pageLength = Constant.DEFAULT_PAGE_LENGTH;
		if (null != dto.getPageLength() 
				&& Integer.valueOf(dto.getPageLength()).intValue() != 0) { 
			//先对每页记录数初始化,然后进行判断并赋值
			pageLength = Integer.valueOf(dto.getPageLength());
		}
		PageDto pageLimit = getPageLimit(pageNo, pageLength);
		
		List<String> taskStatusList = new ArrayList<>();
		taskStatusList.add(TransStatusEnum.TRANS_RUNNING.getCode());
		taskStatusList.add(TransStatusEnum.TRANS_NOT_START.getCode());
		dto.setTaskStatusList(taskStatusList);
		// begin liuyi 修改排序方式
//		List<Integer> taskIdList = queryTaskIdForApp(dto, pageLimit, MtConstant.TASK_STATUS_ASC + 
//				Constant.SPLIT_COMMA + MtConstant.CREATE_DATE_DESC + Constant.SPLIT_COMMA + MtConstant.TASK_ID_DESC);
		List<Integer> taskIdList = queryTaskIdForApp(dto, pageLimit, "BEGIN_TIME asc" + Constant.SPLIT_COMMA + MtConstant.TASK_ID_DESC);
		List<TaskAppQueryDto> taskList = new ArrayList<>();
		if (AppUtils.isNotEmpty(taskIdList)) {
//			taskList = this.mtTaskQueryMapper.queryTaskPageForAppByTaskIds(taskIdList, MtConstant.STATUS_ASC + 
//					Constant.SPLIT_COMMA + MtConstant.CREATE_DATE_DESC + Constant.SPLIT_COMMA + MtConstant.TASK_ID_DESC);
			taskList = this.mtTaskQueryMapper.queryTaskPageForAppByTaskIds(taskIdList,"BEGIN_TIME asc" + Constant.SPLIT_COMMA + MtConstant.TASK_ID_DESC);
			// end
		}
		return taskList;
	}

	/**
	 * @discription 调度任务分页查询(活动中的任务  未开始/进行中)
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月14日 下午7:32:43      
	 * @param dto
	 * @return     
	 */
	@Override
	public PageList<TaskAppQueryDto> queryDispatchTaskPageByAssign(int groupOrganId, MtTaskPageDto dto, Boolean isFilter) {
		MtTaskExecutorsCriteria example = new MtTaskExecutorsCriteria();
		MtTaskExecutorsCriteria.Criteria criteria = example.createCriteria();
		
		int pageNo = Constant.INIT_PAGE_NO;
		if (null != dto.getPageNo() 
				&& Integer.valueOf(dto.getPageNo()).intValue() != 0) {
			//先对页码初始化,然后进行判断并赋值
			pageNo = Integer.valueOf(dto.getPageNo());
		}
		int pageLength = Constant.DEFAULT_PAGE_LENGTH;
		if (null != dto.getPageLength() 
				&& Integer.valueOf(dto.getPageLength()).intValue() != 0) { 
			//先对每页记录数初始化,然后进行判断并赋值
			pageLength = Integer.valueOf(dto.getPageLength());
		}
		PageDto pageLimit = getPageLimit(pageNo, pageLength);
		criteria.andTaskTypeEqualTo(TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode());
		criteria.andOrganIdEqualTo(dto.getOrganId());
		criteria.andExeUserIdEqualTo(dto.getUserId());
		if (StringUtils.isNotBlank(dto.getStatus())) {
			criteria.andTaskStatusIn(AppUtils.str2List(dto.getStatus()));
		} else {
			//没有筛选默认查 未开始和进行中的任务
			List<String> statusList = new ArrayList<>();
			statusList.add(TransStatusEnum.TRANS_NOT_START.getCode());
			statusList.add(TransStatusEnum.TRANS_RUNNING.getCode());
			criteria.andTaskStatusIn(statusList);
		}
		if (StringUtils.isNotBlank(dto.getUrgency())) {
			criteria.andUrgencyIn(AppUtils.str2List(dto.getUrgency()));
		}
		if (StringUtils.isNotBlank(dto.getTransTypeParentCode())) {
			criteria.andTransTypeParentCodeIn(AppUtils.str2List(dto.getTransTypeParentCode()));
		}
		example.setOrderByClause(MtConstant.DISPATCH_TASK_ID_SORT);
		List<Integer> taskIdList = mtTaskQueryMapper.queryTaskIdPageForApp(example, pageLimit);
		List<TaskAppQueryDto> taskList = new ArrayList<>();
		Integer totalCount = 0;
		if (AppUtils.isNotEmpty(taskIdList)) {
			taskList = this.mtTaskQueryMapper.queryTaskPageForAppByTaskIds(taskIdList, MtConstant.DISPATCH_TASK_SORT);
			if (isFilter) {
				totalCount = mtTaskQueryMapper.countDispatchTaskForApp(example);
			}
		}
		Paginator paginator = new Paginator(pageNo, pageLength, totalCount);
		return new PageList<TaskAppQueryDto>(taskList, paginator);
	}

	/**
	 * @discription 查询跟踪任务分页(pad端)
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月10日 下午2:22:39      
	 * @param dto
	 * @return     
	 */
	@Override
	public PageList<TaskPadQueryDto> queryMtTaskPageForPad(int groupOrganId, MtTaskPageDto dto) {
		MtTaskCriteria example = new MtTaskCriteria();
		MtTaskCriteria.Criteria criteria = example.createCriteria();
		
		int pageNo = Constant.INIT_PAGE_NO;
		if (null != dto.getPageNo() 
				&& Integer.valueOf(dto.getPageNo()).intValue() != 0) {
			//先对页码初始化,然后进行判断并赋值
			pageNo = Integer.valueOf(dto.getPageNo());
		}
		int pageLength = Constant.DEFAULT_PAGE_LENGTH;
		if (null != dto.getPageLength() 
				&& Integer.valueOf(dto.getPageLength()).intValue() != 0) { 
			//先对每页记录数初始化,然后进行判断并赋值
			pageLength = Integer.valueOf(dto.getPageLength());
		}
		PageDto pageLimit = getPageLimit(pageNo, pageLength);
		criteria.andOrganIdEqualTo(dto.getOrganId());
		if (InvokingFlagEnum.INVOKING_FROM_PAD.getCode().equals(dto.getInvokingFlag())) {
			// pad端使用预约时间过滤
			criteria.andBeginTimeBetween(dto.getBeginTime(), dto.getEndTime());
		} else if (InvokingFlagEnum.INVOKING_FROM_WEB.getCode().equals(dto.getInvokingFlag())) {
			// web端使用下单时间过滤
			criteria.andCreateDateBetween(dto.getBeginTime(), dto.getEndTime());
		}
		
		criteria.andTaskTypeEqualTo(TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode());
		criteria.andCreateByEqualTo(dto.getUserId());
		if (StringUtils.isNotBlank(dto.getTransTypeParentCode())) {
			//运送大类 可多选
			criteria.andTransTypeParentCodeIn(AppUtils.str2List(dto.getTransTypeParentCode()));
		}
		if (StringUtils.isNotBlank(dto.getUrgency())) {
			// 紧急程度 单选
			criteria.andUrgencyEqualTo(dto.getUrgency());
		}
		if (StringUtils.isNotBlank(dto.getStatus())) {
			// 状态 可多选
			List<String> statusList = AppUtils.str2List(dto.getStatus());
			if (statusList.contains(TransStatusEnum.TRANS_NOT_START.getCode())) {
				// 未开始将抢单中、退单加到条件中
				statusList.add(TransStatusEnum.TRANS_ROBBING.getCode());
				statusList.add(TransStatusEnum.TRANS_BACK.getCode());
			}
			criteria.andStatusIn(statusList);
		}
		//下单时间倒序
		example.setOrderByClause(MtConstant.CREATE_DATE_DESC);
		List<Integer> taskIdList = this.mtTaskQueryMapper.queryTaskIdPage(example, pageLimit);
		int totalCount = 0;
		List<TaskPadQueryDto> taskList = new ArrayList<>();
		if (AppUtils.isNotEmpty(taskIdList)) {
			taskList = this.mtTaskQueryMapper.queryTaskPageForPadByTaskIds(taskIdList);
			totalCount = mtTaskQueryMapper.countTaskTotalNum(example);
		}
		Paginator paginator = new Paginator(pageNo, pageLength, totalCount);
		return new PageList<TaskPadQueryDto>(taskList, paginator);
	}

	/**
	 * @discription 查询评价信息分页(pad端)
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月21日 上午11:11:32      
	 * @param dto
	 * @return     
	 */
	@Override
	public PageList<TaskPadQueryDto> queryTaskEvaluatePageForPad(int groupOrganId, MtTaskPageDto dto) {
		MtTaskCriteria example = new MtTaskCriteria();
		MtTaskCriteria.Criteria criteria = example.createCriteria();
		
		int pageNo = Constant.INIT_PAGE_NO;
		if (null != dto.getPageNo() 
				&& Integer.valueOf(dto.getPageNo()).intValue() != 0) {
			//先对页码初始化,然后进行判断并赋值
			pageNo = Integer.valueOf(dto.getPageNo());
		}
		int pageLength = Constant.DEFAULT_PAGE_LENGTH;
		if (null != dto.getPageLength() 
				&& Integer.valueOf(dto.getPageLength()).intValue() != 0) { 
			//先对每页记录数初始化,然后进行判断并赋值
			pageLength = Integer.valueOf(dto.getPageLength());
		}
		PageDto pageLimit = getPageLimit(pageNo, pageLength);
		
		criteria.andOrganIdEqualTo(dto.getOrganId());
		criteria.andBeginTimeBetween(dto.getBeginTime(), dto.getEndTime());
		criteria.andToHouseIdEqualTo(dto.getUserHouseId());
		// 只查询调度/自主任务
		List<String> TransTypeList = new ArrayList<>();
		TransTypeList.add(TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode());
		TransTypeList.add(TransTaskTypeEnum.TASK_TYPE_SELF.getCode());
		criteria.andTaskTypeIn(TransTypeList);
		// 任务状态
		List<String> statusList = new ArrayList<>();
		statusList.add(TransStatusEnum.TRANS_NON_DISPATCH.getCode());
		statusList.add(TransStatusEnum.TRANS_ROBBING.getCode());
		statusList.add(TransStatusEnum.TRANS_RUNNING.getCode());
		statusList.add(TransStatusEnum.TRANS_NOT_START.getCode());
		statusList.add(TransStatusEnum.TRANS_COMPLETED.getCode());
		statusList.add(TransStatusEnum.TRANS_BACK.getCode());
		
		if (StringUtils.isNotBlank(dto.getEvaluateStatus())) {
			//处理评价状态条件
			if (MtConstant.NOT_EVALUATE.equals(dto.getEvaluateStatus())) {
				// 没评价
				if (StringUtils.isNotBlank(dto.getVersion()) && MtConstant.PAD_OLD_VERSION.equals(dto.getVersion())) {
					// 版本号为1 只查询完成状态
					criteria.andStatusEqualTo(TransStatusEnum.TRANS_COMPLETED.getCode());
				} else {
					criteria.andStatusIn(statusList);
				}
				criteria.andEvaluateEqualTo(MtConstant.EVALUATE_DEFAULT_VALUE);
			} else {
				// 评价过
				criteria.andStatusEqualTo(TransStatusEnum.TRANS_COMPLETED.getCode());
				criteria.andEvaluateGreaterThan(MtConstant.EVALUATE_DEFAULT_VALUE);
			}
		} else {
			// 评价状态为全部
			if (StringUtils.isNotBlank(dto.getVersion()) && MtConstant.PAD_OLD_VERSION.equals(dto.getVersion())) {
				// 版本号为1 只查询完成状态
				criteria.andStatusEqualTo(TransStatusEnum.TRANS_COMPLETED.getCode());
			} else {
			    criteria.andStatusIn(statusList);
			}
		}
		example.setOrderByClause(MtConstant.CREATE_DATE_DESC);
		
		List<Integer> taskIdList = this.mtTaskQueryMapper.queryTaskIdPage(example, pageLimit);
		int totalCount = 0;
		List<TaskPadQueryDto> taskList = new ArrayList<>();
		if (AppUtils.isNotEmpty(taskIdList)) {
			taskList = this.mtTaskQueryMapper.queryTaskPageForPadByTaskIds(taskIdList);
			totalCount = mtTaskQueryMapper.countTaskTotalNum(example);
		}
		Paginator paginator = new Paginator(pageNo, pageLength, totalCount);
		return new PageList<TaskPadQueryDto>(taskList, paginator);
	}

	/**
	 * @discription 获取抢单列表根据groupId
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月18日 下午12:01:30      
	 * @param groupId
	 * @param taskId
	 * @return     
	 */
	public List<MtTask> queryGradTaskByGroupId(int groupOrganId, Integer groupId, Integer taskId) {
		MtTaskCriteria example = new MtTaskCriteria();
		MtTaskCriteria.Criteria criteria = example.createCriteria();
		if(taskId != null && taskId.intValue() != 0 ) {
			criteria.andTaskIdEqualTo(taskId);
		}
		criteria.andStatusEqualTo(TransStatusEnum.TRANS_ROBBING.getCode());
		criteria.andTaskTypeEqualTo(TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode());
		return mtTaskQueryMapper.selectGradTaskByGroupId(example, groupId);
	}

	/**
	 * @discription 获取抢单列表根据groupId 
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月21日 下午3:40:20      
	 * @param groupId
	 * @param taskId
	 * @return     
	 */
	@Override
	public MtTask queryGradTaskByGroupTaskId(int groupOrganId, Integer groupId, Integer taskId) {
		List<MtTask> taskList = this.queryGradTaskByGroupId(groupOrganId, groupId, taskId);
		if(AppUtils.isNotEmpty(taskList)) {
			return taskList.get(0);
		}
		return null;
	}

	/**
	 * @discription 查询所有任务未完成数
	 * @author zhangyang@segimail.com       
	 * @created 2018年6月7日 上午11:56:55      
	 * @param organId
	 * @param userId
	 * @param exeUserStatus
	 * @return     
	 */
	@Override
	public List<Integer> queryAllTaskCount(int groupOrganId, Integer organId, Integer userId) {
		String tableIndex = DynamicTableSourceKeyHolder.getDataSourceKey();
		return mtTaskCommonMapper.queryAllTaskCount(organId, userId, tableIndex);
	}
	
	private PageDto getPageLimit(Integer pageNo, Integer pageLength) {
		PageDto pageLimit = new PageDto();
		pageLimit.setOffset((pageNo - 1) * pageLength);
		pageLimit.setLimit(pageLength);
		return pageLimit;
	}
	
	/**
	 * @discription 查询任务路线
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月11日 下午3:41:30      
	 * @param groupOrganId
	 * @param routeId
	 * @return     
	 */
	@Override
	public MtTaskRoute queryMtTaskRoute(int groupOrganId, Integer routeId) {
		return mtTaskRouteService.selectByPrimaryKey(routeId);
	}

	/**
	 * @discription 通过taskId查询任务路线
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月27日 下午5:42:53      
	 * @param groupOrganId
	 * @param taskId
	 * @return     
	 */
	@Override
	public MtTaskRoute queryMtTaskRouteByTaskId(int groupOrganId, Integer taskId) {
		MtTaskRouteCriteria example = new MtTaskRouteCriteria();
		MtTaskRouteCriteria.Criteria criteria = example.createCriteria();
		criteria.andTaskIdEqualTo(taskId);
		MtTaskRoute taskRoute= new MtTaskRoute();
		List<MtTaskRoute> list = mtTaskRouteService.selectByExample(example);
		if (AppUtils.isNotEmpty(list)) {
			taskRoute = list.get(0);
		}
		return taskRoute;
	}

	/**
	 * @discription 通过任务Id集合查询扩展表信息
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月11日 上午9:37:43      
	 * @param groupOrganId
	 * @param taskIds
	 * @return     
	 */
	@Override
	public List<MtTaskExt> queryTaskExtInfoByTaskIds(int groupOrganId,
			List<Integer> taskIds) {
		MtTaskExtCriteria example = new MtTaskExtCriteria();
		MtTaskExtCriteria.Criteria criteria = example.createCriteria();
		criteria.andTaskIdIn(taskIds);
		return mtTaskExtService.selectByExample(example);
	}

	/**
	 * @discription 在执行人log表查询退单人员信息
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月13日 下午2:52:16      
	 * @param groupOrganId
	 * @param version
	 * @param taskId
	 * @return     
	 */
	@Override
	public List<MtTaskExecutorsLog> queryBackTaskExeInfo(int groupOrganId,
			Integer version, Integer taskId) {
		MtTaskExecutorsLogCriteria example = new MtTaskExecutorsLogCriteria();
		MtTaskExecutorsLogCriteria.Criteria criteria = example.createCriteria();
		criteria.andTaskIdEqualTo(taskId);
		criteria.andVersionEqualTo(version);
		criteria.andStatusEqualTo(TransUserStatusEnum.TRANS_USER_STATUS_02.getCode());
		List<String> list = new ArrayList<>();
		list.add(MtConstant.IS_EXE_END_USER);
		list.add(MtConstant.SETTING_PERSON_LIABLE);
		criteria.andIsExeEndUserIn(list);
		
		return mtTaskExecutorsLogService.selectByExample(example);
	}

}
