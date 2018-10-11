package com.segi.uhomecp.medicaltrans.taskhis.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.taskhis.dao.MtTaskHisQueryMapper;
import com.segi.uhomecp.medicaltrans.taskhis.dto.MtHisTaskPageDto;
import com.segi.uhomecp.medicaltrans.taskhis.service.MtHisTaskQueryService;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutors;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExt;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExtCriteria;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRoute;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRouteCriteria;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskExtService;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskRouteService;
import com.segi.uhomecp.medicaltrans.trans.mttask.service.MtTaskService;
import com.segi.uhomecp.web.PageUtils;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.AppUtils;

/**
 * @ClassName:  MtHisTaskQueryServiceImpl   
 * @Description:运送记录查询接口服务实现类   
 * @author: zhaoqing
 * @date:   2018年8月7日 上午10:55:03
 */
@Service
public class MtHisTaskQueryServiceImpl implements MtHisTaskQueryService {
	
	@Autowired
	private MtTaskHisQueryMapper mtTaskHisQueryMapper;
	
	@Autowired
	private MtTaskService mtTaskService;
	
	@Autowired
	private MtTaskExtService mtTaskExtService;
	
	@Autowired
	private MtTaskRouteService mtTaskRouteService;

	/**
	 * <p>Title: queryMtHisTaskPage</p>   
	 * <p>Description: 运送记录分页查询</p> 
	 * <p>zhaoqing</p>
	 * @param groupOrganId
	 * @param dto
	 * @return   
	 */
	@Override
	public PageList<MtHisTaskPageDto> queryMtHisTaskPage(Integer groupOrganId,
			MtHisTaskPageDto dto) {
		// 分页查询任务Id
		List<Integer> taskIdList = new ArrayList<>();
		if (MtConstant.TASK_HIS_QUERY_FLAG_TWO.equals(dto.getQueryFlag())) {
			// 分页查询运送员运送明细数据的任务Id(查询执行人表)
			taskIdList = mtTaskHisQueryMapper.queryHisTaskIdPageByUser(dto);
		} else {
			// 分页查询任务Id（查询任务信息表）
			taskIdList = mtTaskHisQueryMapper.queryHisTaskIdPage(dto);
		}
		List<MtHisTaskPageDto> taskList = new ArrayList<>();
		int totalCount = 0;
		String taskIds = AppUtils.listToString(taskIdList,
				CharUtils.toChar(Constant.SPLIT_COMMA));
		if (StringUtils.isNotEmpty(taskIds)) {
			dto.setTaskIds(taskIds);
			taskList = mtTaskHisQueryMapper.queryHisTaskPageByTaskIds(dto);
			if (MtConstant.TASK_HIS_QUERY_FLAG_TWO.equals(dto.getQueryFlag())) {
				// 查询运送员运送明细数据条数（查执行人表）
				totalCount = mtTaskHisQueryMapper.countHisTaskPageByUser(dto);
			} else {
				totalCount = mtTaskHisQueryMapper.countHisTaskPage(dto);
			}
		}
		Paginator paginator = new Paginator(dto.getPageNo(),
				dto.getPageLength(), totalCount);
		return new PageList<MtHisTaskPageDto>(taskList, paginator);
	}
	
	/**
	 * <p>Title: queryMtHisTaskExtByTaskIds</p>   
	 * <p>Description: 根据任务Id集合查询任务扩展信息</p> 
	 * <p>zhaoqing</p>
	 * @param groupOrganId
	 * @param taskIdList
	 * @return   
	 */
	@Override
	public List<MtTaskExt> queryMtHisTaskExtByTaskIds(
			Integer groupOrganId, List<Integer> taskIdList) {
		List<MtTaskExt> taskExtList = new ArrayList<>();
		if (AppUtils.isNotEmpty(taskIdList)) {
			MtTaskExtCriteria example = new MtTaskExtCriteria();
			MtTaskExtCriteria.Criteria criteria = example.createCriteria();
			criteria.andTaskIdIn(taskIdList);
			taskExtList = mtTaskExtService.selectByExample(example);
		}	
		return taskExtList;
	}

	/**
	 * <p>Title: getHisTaskUserInfo</p>   
	 * <p>Description: 根据任务Ids查询运送员信息</p> 
	 * <p>zhaoqing</p>
	 * @param groupOrganId
	 * @param dto
	 * @return   
	 */
	@Override
	public List<MtTaskExecutors> getHisTaskUserInfo(Integer groupOrganId,
			MtHisTaskPageDto dto) {
		return mtTaskHisQueryMapper.getHisTaskUserInfo(dto);
	}

	/**
	 * <p>Title: queryMtTaskRouteHisPage</p>   
	 * <p>Description: 执行信息分页查询 </p> 
	 * <p>zhaoqing</p>
	 * @param groupOrganId
	 * @param dto
	 * @return   
	 */
	@Override
	public PageList<MtTaskRoute> queryMtTaskRouteHisPage(
			Integer groupOrganId, MtHisTaskPageDto dto) {
		MtTaskRouteCriteria example = new MtTaskRouteCriteria();
		MtTaskRouteCriteria.Criteria criteria = example.createCriteria();
		criteria.andGroupOrganIdEqualTo(dto.getGroupOrganId());
		criteria.andTaskIdEqualTo(dto.getTaskId());
		example.setOrderByClause(MtConstant.SORT_NO_ASC);
		return mtTaskRouteService.selectByExampleWithRowbounds(example, 
				PageUtils.getPageBoundsByPageNo(dto.getPageNo(), dto.getPageLength()));
	}

	/**
	 * <p>Title: queryMtTaskHisById</p>   
	 * <p>Description: 根据任务Id查询任务信息</p> 
	 * <p>zhaoqing</p>
	 * @param groupOrganId
	 * @param taskId
	 * @return   
	 */
	@Override
	public MtTask queryMtTaskHisById(Integer groupOrganId, Integer taskId) {
		return mtTaskService.selectByPrimaryKey(taskId);
	}

	/**
	 * <p>Title: queryMtTaskExtHis</p>   
	 * <p>Description: 根据任务Id查询任务扩展信息</p> 
	 * <p>zhaoqing</p>
	 * @param groupOrganId
	 * @param taskId
	 * @return   
	 */
	@Override
	public MtTaskExt queryMtTaskExtHis(Integer groupOrganId, Integer taskId) {
		MtTaskExtCriteria example = new MtTaskExtCriteria();
		MtTaskExtCriteria.Criteria criteria = example.createCriteria();
		criteria.andTaskIdEqualTo(taskId);
		List<MtTaskExt> list = mtTaskExtService.selectByExample(example);
		if (AppUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * <p>Title: queryTaskRouteHisByTask</p>   
	 * <p>Description: 根据运送任务查询运输路线</p> 
	 * <p>zhaoqing</p>
	 * @param groupOrganId
	 * @param task
	 * @return   
	 */
	@Override
	public MtTaskRoute queryTaskRouteHisByTask(Integer groupOrganId, MtTask task) {
		MtTaskRoute taskRoute = new MtTaskRoute();
		MtTaskRouteCriteria example = new MtTaskRouteCriteria();
		MtTaskRouteCriteria.Criteria criteria = example.createCriteria();
		criteria.andGroupOrganIdEqualTo(task.getGroupOrganId());
		criteria.andTaskIdEqualTo(task.getTaskId());
		if (task.getToHouseId() != null) {
			criteria.andHouseIdEqualTo(task.getToHouseId());
		}
		List<MtTaskRoute> list = mtTaskRouteService.selectByExample(example);
		if (AppUtils.isNotEmpty(list)) {
			taskRoute = list.get(0);
		}
		return taskRoute;
	}

	/**
	 * <p>Title: queryTaskRouteHisById</p>   
	 * <p>Description: 根据路线Id查询路线详情 </p> 
	 * <p>zhaoqing</p>
	 * @param groupOrganId
	 * @param routeId
	 * @return   
	 */
	@Override
	public MtTaskRoute queryTaskRouteHisById(Integer groupOrganId,
			Integer routeId) {
		return mtTaskRouteService.selectByPrimaryKey(routeId);
	}
	
}
