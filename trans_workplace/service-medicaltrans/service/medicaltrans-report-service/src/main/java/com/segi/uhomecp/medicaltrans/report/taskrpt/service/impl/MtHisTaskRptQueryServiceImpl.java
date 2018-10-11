package com.segi.uhomecp.medicaltrans.report.taskrpt.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import segi.datacachesvr.queryInfo.Organ;
import segi.datacachesvr.queryInfo.UserInfo;
import segi.filecommon.CommonFileIce;
import segi.medicaltrans.common.transType.TransTypeInfo;
import segi.medicaltrans.location.common.MtLocationInfoIce;
import segi.medicaltrans.mthistask.query.ExeUser;
import segi.medicaltrans.mthistask.query.FileParams;
import segi.medicaltrans.mthistask.query.FixedTaskExeDetailIce;
import segi.medicaltrans.mthistask.query.FixedTaskHisExeInfoIce;
import segi.medicaltrans.mthistask.query.MtTaskDetailRetIce;
import segi.medicaltrans.mthistask.query.MtTaskPageIce;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.constant.MtConstant.FileRefType;
import com.segi.uhomecp.medicaltrans.constants.ReportConstant;
import com.segi.uhomecp.medicaltrans.dto.ExeUserDto;
import com.segi.uhomecp.medicaltrans.enums.IsTimeOutEnum;
import com.segi.uhomecp.medicaltrans.enums.TransDispatchTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransStatusEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTaskTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.TransToolsEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTypeEnum;
import com.segi.uhomecp.medicaltrans.enums.UrgencyEnum;
import com.segi.uhomecp.medicaltrans.report.taskrpt.dao.MtTaskHisRptQueryMapper;
import com.segi.uhomecp.medicaltrans.report.taskrpt.dto.MtHisTaskPageDto;
import com.segi.uhomecp.medicaltrans.report.taskrpt.service.MtHisTaskRptQueryService;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExecutors;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskExt;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRoute;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.medicaltrans.utils.MtTaskHisQueryUtil;
import com.segi.uhomecp.utils.UhomePropUtils;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.enums.SexEnum;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;

/**
 * @ClassName:  MtHisTaskQueryServiceImpl   
 * @Description:运送记录查询接口服务实现类   
 * @author: zhaoqing
 * @date:   2018年8月7日 上午10:55:03
 */
@Service
public class MtHisTaskRptQueryServiceImpl implements MtHisTaskRptQueryService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MtHisTaskRptQueryServiceImpl.class);
	
	@Autowired
	private MtTaskHisRptQueryMapper mtTaskHisQueryMapper;
	
	/**
	 * <p>Title: queryMtHisTaskPage</p>   
	 * <p>Description: 运送记录分页查询</p> 
	 * <p>zhaoqing</p>
	 * @param dto
	 * @return   
	 */
	@Override
	public ResultInfo queryMtHisTaskPage(MtHisTaskPageDto dto) {
		ResultInfo result = new ResultInfo();
		result.setIsSucc(true);
		List<MtTaskExecutors> taskUserInfoList = new ArrayList<>();
		// 分页查询数据
		PageList<MtHisTaskPageDto> pageList = queryMtHisTaskPageData(dto, result, taskUserInfoList);
		if (!result.getIsSucc() || !AppUtils.isNotEmpty(pageList)) {
			return result;
		}
		// 任务ID集合
		String taskIds = String.valueOf(result.getObject1());
		// 查询所属组织
		Organ organ = CommonServiceUtils.getOrganByID(dto.getOrganId());
		// 通过taskPageList查询运送员List
		if (StringUtils.isNotEmpty(taskIds)) {
			dto.setTaskIds(taskIds);
			List<MtTaskExecutors> taskUserInfoListTemp = getTaskUserInfoList(dto);
			if (AppUtils.isNotEmpty(taskUserInfoListTemp)) {
				taskUserInfoList.addAll(taskUserInfoListTemp);
			}
		}
		// 获取所有的用户信息map
		Map<Integer, UserInfo> allUserInfoMap = getAllUserInfoMap(
				pageList, MtHisTaskPageDto.class, taskUserInfoList);
		// 获取任务执行人用户信息map
		Map<Integer, List<ExeUser>> userIceMap = getExeUserInfoMap(taskUserInfoList, allUserInfoMap);
		// 获取所有的科室信息
		Map<String, MtLocationInfoIce> houseInfoMap = MtTaskHisQueryUtil.getHouseInfoByTaskList(
				pageList, MtHisTaskPageDto.class, dto.getOrganId());
		// 处理运行单分页数据
		List<MtTaskPageIce> resultList = initHisTaskPageData(result,
				pageList, houseInfoMap, userIceMap, organ, allUserInfoMap);
		if (!result.getIsSucc() || !AppUtils.isNotEmpty(resultList)) {
			return result;
		}
		// 设置签收人信息
		setUserHsopInfoList(resultList, pageList, dto);
		result.setObject1(pageList);
		result.setObjList(resultList);
		return result;
	}
	
	/**
	 * @Title: setUserHsopInfoList   
	 *  设置签收人信息 
	 * @author zhaoqing  
	 * @param resultList
	 * @param pageList
	 * @param dto 
	 */
	private void setUserHsopInfoList(List<MtTaskPageIce> resultList, 
			PageList<MtHisTaskPageDto> pageList, MtHisTaskPageDto dto) {
		if (dto.isExportFlag()) {
			// 获取签收人Id集合
			Set<Integer> userIdSet = AppUtils.list2ParamsSet(
					pageList, MtHisTaskPageDto.class, "receiver");
			List<Integer> userIdList = new ArrayList<>(userIdSet);
			// 删除null对象
			userIdList.removeAll(Collections.singleton(null));
			MtTaskHisQueryUtil.handleReceiverInfoList(
					String.valueOf(dto.getOrganId()), userIdList, resultList);
		}
	}
	
	/**
	 * @Title: getTaskUserInfoList   
	 *  查询执行人信息
	 * @author zhaoqing  
	 * @param dto
	 * @return 
	 */
	private List<MtTaskExecutors> getTaskUserInfoList(MtHisTaskPageDto dto) {
		List<MtTaskExecutors> list = new ArrayList<>();
		String tableName = ReportConstant.TABLE_EVT_MT_TASK_EXECUTORS_HIS + dto.getYear();
		// 表是否存在
		boolean tableExist = checkTableExist(tableName);
		if (tableExist) {
			list = mtTaskHisQueryMapper.getHisTaskUserInfo(dto);
		}
		return list;
	}
	
	/**
	 * @Title: initHisTaskPageData   
	 *  处理运行单分页数据 
	 * @author zhaoqing  
	 * @param taskPageList
	 * @param houseInfoMap
	 * @param userIceMap
	 * @param organ
	 * @param allUserInfoMap
	 * @return 
	 */
	private List<MtTaskPageIce> initHisTaskPageData(ResultInfo result, 
			PageList<MtHisTaskPageDto> taskPageList,
			Map<String, MtLocationInfoIce> houseInfoMap,
			Map<Integer, List<ExeUser>> userIceMap, Organ organ, 
			Map<Integer, UserInfo> allUserInfoMap) {
		MtTaskPageIce returnIce = null;	
		List<MtTaskPageIce> resultList = new ArrayList<>();
		List<ExeUser> userList = new ArrayList<>();
		try {
			for (MtHisTaskPageDto task : taskPageList) {
				returnIce = BeanCopierUtils.copyProperties(task, MtTaskPageIce.class, true);
				// 组织名称
				returnIce.setOrganName(null != organ ? organ.getOrganName() : "");
				// 下单人
				returnIce.setCreateByName(MtTaskHisQueryUtil.getUserName(
						task.getCreateBy(), allUserInfoMap));
				// 派单人
				returnIce.setDispatchUserName(MtTaskHisQueryUtil.getUserName(
						task.getDispatchUserId(), allUserInfoMap));
				// 运送类型名称
				returnIce.setTransTypeParentCodeName(
						TransTypeEnum.getNameByCode(task.getTransTypeParentCode()));
				// 紧急程度名称
				returnIce.setUrgencyName(UrgencyEnum.getNameByCode(task.getUrgency()));
				// 设置位置信息
				MtTaskHisQueryUtil.setPositionInfo(houseInfoMap, returnIce, 
						task.getSourceHouseId(), task.getFromHouseId(), task.getToHouseId());
				// 设置执行人信息
				setExeUserInfo(userList, task, userIceMap, returnIce);
				// 响应类型
				returnIce.setResTypeName(TransDispatchTypeEnum.getNameByCode(task.getResType()));
				// 任务类型
				returnIce.setTaskTypeName(TransTaskTypeEnum.getNameByCode(task.getTaskType()));
				// 任务状态
				returnIce.setStatusName(TransStatusEnum.getNameByCode(task.getStatus()));
				// 是否超时
				returnIce.setIsTimeOutName(IsTimeOutEnum.getNameByCode(task.getIsTimeOut()));
				// 设置数据的时间信息
				MtTaskHisQueryUtil.setPageDateTimes(returnIce, 
						task.getCreateDate(), task.getBeginTime(), task.getEndTime());
				// 设置评价信息
				MtTaskHisQueryUtil.setEvaluate(task.getEvaluate(), returnIce);
				resultList.add(returnIce);
			}
		} catch (NumberFormatException e) {
			LOGGER.error("initHisTaskPageData", e);
			result.setIsSucc(false);
			result.setMessage("运送记录分页数据处理失败！");
		}
		return resultList;
	}
	
	/**
	 * @Title: setUserInfo   
	 *  设置执行人信息 
	 * @author zhaoqing  
	 * @param userList
	 * @param task
	 * @param userIceMap
	 * @param returnIce  
	 */
	private void setExeUserInfo(List<ExeUser> userList, MtHisTaskPageDto task, 
			Map<Integer, List<ExeUser>> userIceMap, MtTaskPageIce returnIce) {
		// 执行人
		if (!(TransStatusEnum.TRANS_BACK.getCode().equals(task.getStatus())
	    		|| TransStatusEnum.TRANS_CANCEL.getCode().equals(task.getStatus()))) {
			userList = userIceMap.get(task.getTaskId());
			if (AppUtils.isNotEmpty(userList)) {
				returnIce.setUserList(userList);
			}
		}
	}

	/**
	 * @Title: getExeUserInfoMap   
	 *  获取任务执行人用户信息map
	 * @author zhaoqing  
	 * @param taskUserInfoList
	 * @param userInfoMap
	 * @return
	 */
	private Map<Integer, List<ExeUser>> getExeUserInfoMap(
			List<MtTaskExecutors> taskUserInfoList,
			Map<Integer, UserInfo> userInfoMap) {	
		Map<Integer, List<ExeUser>> map = new HashMap<>();
		if (!AppUtils.isNotEmpty(taskUserInfoList)) {
			return map;
		}
		List<ExeUserDto> userList = new ArrayList<>(); 	
		for (MtTaskExecutors exeUserInfo : taskUserInfoList) {
			// 获取执行人信息
			ExeUserDto userDto = MtTaskHisQueryUtil.initExeUserDto(
					exeUserInfo.getExeUserId(), exeUserInfo.getTaskId(), userInfoMap);
			userList.add(userDto);
		}
		return AppUtils.listGroup2Map(userList, ExeUserDto.class, "taskId", ExeUser.class);
	} 
	
	/**
	 * @Title: getAllUserInfoMap   
	 *  获取所有的用户信息map 
	 * @author zhaoqing  
	 * @param taskList
	 * @param c
	 * @return
	 */
	private <V> Map<Integer, UserInfo> getAllUserInfoMap(List<V> taskList, 
			Class<V> c, List<MtTaskExecutors> exeUserList) {
		Set<Integer> exeUserIdSet = new HashSet<>();
		if (AppUtils.isNotEmpty(exeUserList)) {
			exeUserIdSet = AppUtils.list2ParamsSet(exeUserList, (obj) -> obj.getExeUserId());
		}
		return MtTaskHisQueryUtil.getAllUserInfoMap(taskList, c, exeUserIdSet);
	}
	
	/**
	 * @Title: queryMtHisTaskPageData   
	 *  分页查询数据
	 * @author zhaoqing  
	 * @param dto
	 * @param result
	 * @return 
	 */
	private PageList<MtHisTaskPageDto> queryMtHisTaskPageData(
			MtHisTaskPageDto dto, ResultInfo result, List<MtTaskExecutors> taskUserInfoList) {
		try {
			// 初始化分页对象
			initPageInfo(dto);
			int totalCount = 0;
			// 跨表数据条数
			int crossDataCount = 0;
			MtHisTaskPageDto queryDto = new MtHisTaskPageDto();
			if (MtConstant.TASK_HIS_QUERY_FLAG_TWO.equals(dto.getQueryFlag())) {
				// 查询运送员运送明细数据条数（查执行人表）
				totalCount = getHisTaskCountByUser(dto);
				// 获取处理后的查询条件
				queryDto = getCrossMonthQueryDto(dto);
				// 查询跨表的数据条数
				crossDataCount = countCrossMonthData(queryDto);
				totalCount = totalCount + crossDataCount;
			} else {
				totalCount = getHisTaskCount(dto);
			}
			// 分页查询任务Id
			List<Integer> taskIdList = getTaskIdList(dto, totalCount, crossDataCount, queryDto);		
			List<MtHisTaskPageDto> taskList = new ArrayList<>();
			// 获取任务Id
			String taskIds = AppUtils.listToString(taskIdList, 
					CharUtils.toChar(Constant.SPLIT_COMMA));
			if (StringUtils.isNotEmpty(taskIds)) {
				result.setObject1(taskIds);
				dto.setTaskIds(taskIds);
				queryDto.setTaskIds(taskIds);
				// 查询任务信息
				taskList = getHisTaskPageByTaskIds(dto, crossDataCount, queryDto);
				if (crossDataCount > 0) {
					// 查询跨月表执行人信息
					List<MtTaskExecutors> taskUserInfoListTemp = getTaskUserInfoList(queryDto);
					if (AppUtils.isNotEmpty(taskUserInfoListTemp)) {
						taskUserInfoList.addAll(taskUserInfoListTemp);
					}
				}
			}	
			Paginator paginator = new Paginator(dto.getPageNo(), dto.getPageLength(), totalCount);
			return new PageList<MtHisTaskPageDto>(taskList, paginator);
		} catch (Exception e) {
			result.setIsSucc(false);
			result.setMessage("分页数据查询失败！");
			LOGGER.error("queryMtHisTaskPage", e);
		}
		return null;
	}
	
	/**
	 * @Title: getTaskIdList   
	 *  获取任务Id 
	 * @author zhaoqing  
	 * @param dto
	 * @param totalCount
	 * @return
	 */
	private List<Integer> getTaskIdList(MtHisTaskPageDto dto, 
			int totalCount, int crossDataCount, MtHisTaskPageDto queryDto) {
		List<Integer> taskIdList = new ArrayList<>();
		if (MtConstant.TASK_HIS_QUERY_FLAG_TWO.equals(dto.getQueryFlag())) {
			// 分页查询运送员运送明细数据的任务Id(查询执行人表)
			if (crossDataCount == 0) {
				// 不存在跨表数据
				taskIdList = getTaskIdListByUser(dto);
			} else {
				// 存在跨表数据
				taskIdList = getTaskIdListByUserCross(dto, queryDto, totalCount);
			}
		} else {
			// 分页查询任务Id
			taskIdList = getTaskIdList(dto);
		}
		return taskIdList;
	}
	
	/**
	 * @Title: getHisTaskCount   
	 *  查询任务总条数 
	 * @author zhaoqing  
	 * @param dto
	 * @return  
	 */
	private int getHisTaskCount(MtHisTaskPageDto dto) {
		int count = 0;
		String tableName = ReportConstant.TABLE_EVT_MT_TASK_HIS + dto.getYear();
		// 表是否存在
		boolean tableExist = checkTableExist(tableName);
		if (tableExist) {
			count = mtTaskHisQueryMapper.countHisTaskPage(dto);
		}
		return count;
	}
	
	/**
	 * @Title: getHisTaskCountByUser   
	 *  获取任务总条数(查询执行人表)
	 * @author zhaoqing  
	 * @param dto
	 * @return
	 */
	private int getHisTaskCountByUser(MtHisTaskPageDto dto) {
		int count = 0;
		String tableName = ReportConstant.TABLE_EVT_MT_TASK_EXECUTORS_HIS + dto.getYear();
		// 表是否存在
		boolean tableExist = checkTableExist(tableName);
		if (tableExist) {
			count = mtTaskHisQueryMapper.countHisTaskPageByUser(dto);
		}
		return count;
	}
	
	/**
	 * @Title:    
	 *  根据任务Id查询任务信息
	 * @author zhaoqing  
	 * @param dto
	 * @return
	 */
	private List<MtHisTaskPageDto> getHisTaskPageByTaskIds(
			MtHisTaskPageDto dto, int crossDataCount, MtHisTaskPageDto queryDto) {
		List<MtHisTaskPageDto> list = new ArrayList<>();
		List<MtTaskExt> taskExtList = new ArrayList<>();
		String tableName = ReportConstant.TABLE_EVT_MT_TASK_HIS + dto.getYear();
		// 表是否存在
		boolean tableExist = checkTableExist(tableName);
		if (tableExist) {
			list = mtTaskHisQueryMapper.queryHisTaskPageByTaskIds(dto);			
			// 任务扩展表信息
			taskExtList.addAll(getMtTaskExtByIds(dto));		
		}
		if (crossDataCount > 0 && list.size() < dto.getPageLength()) {
			List<MtHisTaskPageDto> crossList = mtTaskHisQueryMapper.queryHisTaskPageByTaskIds(queryDto);
			if (AppUtils.isNotEmpty(crossList)) {
				list.addAll(crossList);
			}			
			// 任务扩展表信息
			taskExtList.addAll(getMtTaskExtByIds(queryDto));		
		}
		
		// 将扩展信息设置到返回的结果集中
		transferTaskExtToTaskPageDto(taskExtList, list);
		return list;
	}
	
	/**
	 * @Title: transferTaskExtToTaskPageDto   
	 *  将扩展信息设置到返回的结果集中
	 * @author zhaoqing  
	 * @param taskExtList
	 * @param pageDtoList
	 */
	private void transferTaskExtToTaskPageDto(
			List<MtTaskExt> taskExtList, List<MtHisTaskPageDto> pageDtoList) {
		if (!AppUtils.isNotEmpty(taskExtList) || !AppUtils.isNotEmpty(pageDtoList)) {
			return;
		}
		// list对象转成Map集合
		Map<Integer, MtTaskExt> taskExtMap = AppUtils.list2Map(taskExtList, "taskId", MtTaskExt.class);
		for (MtHisTaskPageDto taskDto : pageDtoList) {
			MtTaskExt mtTaskExt = taskExtMap.get(taskDto.getTaskId());
			if (null != mtTaskExt) {
				taskDto.setTaskContent(mtTaskExt.getTaskContent());
				taskDto.setReceiver(mtTaskExt.getReceiver());
				taskDto.setReceiveTime(mtTaskExt.getReceiveTime());
				taskDto.setEvaluateContent(mtTaskExt.getEvaluateContent());
			}
		}
	}
	
	/**
	 * @Title: getMtTaskExtByIds   
	 *  获取任务扩展表信息 
	 * @author zhaoqing  
	 * @param dto
	 * @return
	 */
	private List<MtTaskExt> getMtTaskExtByIds(MtHisTaskPageDto dto) {
		List<MtTaskExt> taskExtList = new ArrayList<>();
		if (dto.isExportFlag()) {
			// 根据任务Id查询任务扩展信息
			List<MtTaskExt> taskExtListTemp = mtTaskHisQueryMapper
					.queryMtTaskExtHisByTaskIds(dto);
			if (AppUtils.isNotEmpty(taskExtListTemp)) {
				taskExtList.addAll(taskExtListTemp);
			}
		}
		return taskExtList;
	}
	
	/**
	 * @Title: getTaskIdList   
	 *  分页查询任务ID
	 * @author zhaoqing  
	 * @param dto
	 * @return
	 */
	private List<Integer> getTaskIdList(MtHisTaskPageDto dto) {
		List<Integer> list = new ArrayList<>();
		String tableName = ReportConstant.TABLE_EVT_MT_TASK_HIS + dto.getYear();
		// 表是否存在
		boolean tableExist = checkTableExist(tableName);
		if (tableExist) {
			list = mtTaskHisQueryMapper.queryHisTaskIdPage(dto);
		}
		return list;
	}
	
	/**
	 * @Title: getTaskIdListByUser   
	 *  分页查询任务ID（查执行人表）
	 * @author zhaoqing  
	 * @param dto
	 * @return
	 */
	private List<Integer> getTaskIdListByUser(MtHisTaskPageDto dto) {
		List<Integer> list = new ArrayList<>();
		String tableName = ReportConstant.TABLE_EVT_MT_TASK_EXECUTORS_HIS + dto.getYear();
		// 表是否存在
		boolean tableExist = checkTableExist(tableName);
		if (tableExist) {
			list = mtTaskHisQueryMapper.queryHisTaskIdPageByUser(dto);
		}
		return list;
	}
	
	/**
	 * @Title: getTaskIdListByUserCross   
	 *  查询执行人表存在跨表数据的任务Id
	 * @author zhaoqing  
	 * @param dto
	 * @param queryDto
	 * @param totalCount
	 * @return 
	 */
	private List<Integer> getTaskIdListByUserCross(MtHisTaskPageDto dto, 
			MtHisTaskPageDto queryDto, int totalCount) {
		List<Integer> list = new ArrayList<>();
		String tableName = ReportConstant.TABLE_EVT_MT_TASK_EXECUTORS_HIS + dto.getYear();
		// 表是否存在
		boolean tableExist = checkTableExist(tableName);
		if (tableExist && totalCount >= dto.getStartIndex().intValue()) {
			// 查询主历史表
			list = mtTaskHisQueryMapper.queryHisTaskIdPageByUser(dto);
		}
		if (list.size() < dto.getPageLength().intValue()) {
			// 初始化跨表查询的分页信息
			initQueryDtoPage(dto, queryDto, totalCount);
			// 查询跨年的历史数据
			List<Integer> crossList = mtTaskHisQueryMapper.queryHisTaskIdPageByUser(queryDto);
			if (AppUtils.isNotEmpty(crossList)) {
				list.addAll(crossList);
			}	
		} 
		return list;
	}
	
	/**
	 * @Title: initQueryDtoPage   
	 *  初始化跨表查询的分页信息 
	 * @author zhaoqing  
	 * @param dto
	 * @param queryDto
	 * @param totalCount 
	 */
	private void initQueryDtoPage(MtHisTaskPageDto dto, MtHisTaskPageDto queryDto, int totalCount) {
		int pageLength = dto.getPageLength();
		// 主表数据的页数
		int pageNoMain = (totalCount - totalCount % pageLength) / pageLength + 1;	
		// 跨表数据第一页的条数
		int firstPageLength = pageLength - totalCount % pageLength;
		// 跨表数据的下标
		int starIndex = 0;
		// 跨表数据的页码
		int pageNo = dto.getPageNo() - pageNoMain + 1;
		if (pageNo == 1) {
			pageLength = firstPageLength;
		} else {
			starIndex = (pageNo - 1) * pageLength - firstPageLength;
		}
		queryDto.setPageLength(pageLength);
		queryDto.setPageNo(pageNo);
		queryDto.setStartIndex(Integer.valueOf(starIndex));
	}
	
	/**
	 * @Title: initPageInfo   
	 *  初始化分页对象 
	 * @author zhaoqing  
	 * @param dto
	 */
	private void initPageInfo(MtHisTaskPageDto dto) {
		int pageNo = Constant.INIT_PAGE_NO + 1;
		if (null != dto.getPageNo() && dto.getPageNo().intValue() != 0) {
			//先对页码初始化,然后进行判断并赋值
			pageNo = dto.getPageNo();
		}
		int pageLength = Constant.DEFAULT_PAGE_LENGTH;
		if (null != dto.getPageLength() && dto.getPageLength().intValue() != 0) { 
			//先对每页记录数初始化,然后进行判断并赋值
			pageLength = dto.getPageLength();
		}
		// 初始偏移量
		dto.setStartIndex((pageNo - 1) * pageLength);
		dto.setPageNo(pageNo);
		dto.setPageLength(pageLength);
	}
	
	/**
	 * @discription 固定任务执行信息分页查询
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月2日 下午12:00:57      
	 * @param dto
	 * @return     
	 */
	@Override
	public ResultInfo queryMtHisFixedTaskExeInfoPage(MtHisTaskPageDto dto) {
		ResultInfo result = new ResultInfo();
		result.setIsSucc(true);
		// 分页查询执行线路数据
		PageList<MtTaskRoute> pageList = queryMtTaskRouteHisPage(dto, result);
		if (!result.getIsSucc() || !AppUtils.isNotEmpty(pageList)) {
			return result;
		}
		// 查询科室信息
		Map<String, MtLocationInfoIce> houseInfoMap = getHouseInfoMap(dto, pageList);
		// 处理执行信息数据
		List<FixedTaskHisExeInfoIce> exeInfoList = initExeInfoData(result, pageList, houseInfoMap);
		if (!result.getIsSucc() || !AppUtils.isNotEmpty(exeInfoList)) {
			return result;
		}
		result.setObject1(pageList);
		result.setObjList(exeInfoList);
		return result;
	}
	
	/**
	 * @Title: initExeInfoData   
	 *  处理固定任务执行信息数据 
	 * @author zhaoqing  
	 * @param result
	 * @param taskRouteList
	 * @param houseInfoMap
	 * @return
	 */
	private List<FixedTaskHisExeInfoIce> initExeInfoData(ResultInfo result, 
			PageList<MtTaskRoute> taskRouteList, 
			Map<String, MtLocationInfoIce> houseInfoMap) {
		List<FixedTaskHisExeInfoIce> exeInfoList = new ArrayList<>();
		try {
			for (MtTaskRoute mtTaskRoute : taskRouteList) {
				FixedTaskHisExeInfoIce exeInfo = BeanCopierUtils.copyProperties(
						mtTaskRoute, FixedTaskHisExeInfoIce.class, true);
				if (Constant.STATUS_CD_NORMAL.equals(mtTaskRoute.getStatus())) {
					// 已打卡,设置更新时间为完成时间
					String finishTime = DateUtil.formatDateToString(
							mtTaskRoute.getUpdateDate(), MtConstant.FORMAT_YYYY_MM_DD_HH_MM);
					exeInfo.setFinishTime(finishTime);
				}
				MtLocationInfoIce positInfo = houseInfoMap.get(
						String.valueOf(mtTaskRoute.getHouseId()));
				if (null != positInfo) {
					exeInfo.setHouseName(positInfo.getLocationName());
					// 定位方式
					exeInfo.setLocateType(positInfo.getLocateType());
					// mac地址
					exeInfo.setMac(positInfo.getMac());
				} 
				exeInfo.setIsAutograph(mtTaskRoute.getIsAutograph());
				exeInfoList.add(exeInfo);
			}
		} catch (Exception e) {
			LOGGER.error("initExeInfoData", e);
			result.setIsSucc(false);
			result.setMessage("处理固定任务执行信息数据失败");
		}
		return exeInfoList;
	}

	/**
	 * @Title: getHouseInfoMap   
	 *  查询固定任务路线house信息 
	 * @author zhaoqing  
	 * @param dto
	 * @param taskRouteList
	 * @return
	 */
	private Map<String, MtLocationInfoIce> getHouseInfoMap(
			MtHisTaskPageDto dto, PageList<MtTaskRoute> taskRouteList) {
		// 获取houseId和Set集合
		Set<Integer> houseIdSet = AppUtils.list2ParamsSet(
				taskRouteList, MtTaskRoute.class, "houseId");
		// set转list
		List<Integer> houseIdList = new ArrayList<>(houseIdSet);
		// 获取位置信息
		List<MtLocationInfoIce> locationInfoList = MtCommonServiceUtils
				.getLocationInfoList(dto.getOrganId(), houseIdList);
		if (AppUtils.isNotEmpty(locationInfoList)) {
			return AppUtils.list2Map(locationInfoList, "locationId", MtLocationInfoIce.class);
		}
		return new HashMap<>();
	}
	
	/**
	 * @Title: queryMtTaskRouteHisPage   
	 *  固定任务执行信息分页查询数据
	 * @author zhaoqing  
	 * @param dto
	 * @param result
	 * @return
	 */
	private PageList<MtTaskRoute> queryMtTaskRouteHisPage(MtHisTaskPageDto dto, ResultInfo result) {
		PageList<MtTaskRoute> pageList = new PageList<MtTaskRoute>();
		try {
			MtHisTaskPageDto queryDto = new MtHisTaskPageDto();
			queryDto.setGroupOrganId(dto.getGroupOrganId());
			queryDto.setTaskId(dto.getTaskId());
			// 初始化分页对象
			initPageInfo(dto);
			queryDto.setStartIndex(dto.getStartIndex());
			queryDto.setPageLength(dto.getPageLength());
			queryDto.setYear(dto.getYear());
			// 分页查询执行信息Id
			List<Integer> routeIdList = getMtTaskRouteIdHisPage(queryDto);
			String routeIds = AppUtils.listToString(routeIdList, CharUtils.toChar(Constant.SPLIT_COMMA));
			List<MtTaskRoute> routeList = new ArrayList<>();
			int totalCount = 0;
			if (StringUtils.isNotEmpty(routeIds)) {
				// 查询分页条数
				totalCount = getMtTaskRouteIdHisCount(queryDto);
				queryDto.setRouteIds(routeIds);
				// 根据Ids查询数据
				routeList = getMtTaskRouteHisByIds(queryDto);
			}
			Paginator paginator = new Paginator(dto.getPageNo(), dto.getPageLength(), totalCount);
			return new PageList<MtTaskRoute>(routeList, paginator);
		} catch (Exception e) {
			LOGGER.error("queryMtTaskRouteHisPage", e);
			result.setIsSucc(false);
			result.setMessage("固定任务执行信息分页查询数据失败");
		}
		return pageList;
	}
	
	/**
	 * @Title: getMtTaskRouteHisByIds   
	 *  根据Id查询执行路线信息
	 * @author zhaoqing  
	 * @param dto
	 * @return 
	 */
	private List<MtTaskRoute> getMtTaskRouteHisByIds(MtHisTaskPageDto dto) {
		List<MtTaskRoute> list = new ArrayList<>();
		String tableName = ReportConstant.TABLE_EVT_MT_TASK_ROUTE_HIS + dto.getYear();
		// 表是否存在
		boolean tableExist = checkTableExist(tableName);
		if (tableExist) {
			list = mtTaskHisQueryMapper.queryMtTaskRouteHisByIds(dto);
		}
		return list;
	}
	
	/**
	 * @Title: getMtTaskRouteIdHisCount   
	 *  查询路线信息总条数 
	 * @author zhaoqing  
	 * @param dto
	 * @return  
	 */
	private int getMtTaskRouteIdHisCount(MtHisTaskPageDto dto) {
		int count = 0;
		String tableName = ReportConstant.TABLE_EVT_MT_TASK_ROUTE_HIS + dto.getYear();
		// 表是否存在
		boolean tableExist = checkTableExist(tableName);
		if (tableExist) {
			count = mtTaskHisQueryMapper.countMtTaskRouteIdHisPage(dto);
		}
		return count;
	}
	
	/**
	 * @Title: getMtTaskRouteIdHisPage   
	 *  分页查询执行路线Id 
	 * @author zhaoqing  
	 * @param dto
	 * @return
	 */
	private List<Integer> getMtTaskRouteIdHisPage(MtHisTaskPageDto dto) {
		List<Integer> list = new ArrayList<>();
		String tableName = ReportConstant.TABLE_EVT_MT_TASK_ROUTE_HIS + dto.getYear();
		// 表是否存在
		boolean tableExist = checkTableExist(tableName);
		if (tableExist) {
			list = mtTaskHisQueryMapper.queryMtTaskRouteIdHisPage(dto);
		}
		return list;
	}

	/**
	 * <p>Title: queryMtHisTaskDetail</p>   
	 * <p>Description: 运送任务详情(自主任务/调度任务)</p> 
	 * <p>zhaoqing</p>
	 * @param dto
	 * @return   
	 */
	@Override
	public ResultInfo queryMtHisTaskDetail(MtHisTaskPageDto dto) {
		ResultInfo result = new ResultInfo();
		result.setIsSucc(true);
		MtTask task = getMtTaskHisByTaskId(dto);
		if (null == task) {
			result.setIsSucc(false);
			result.setMessage("任务不存在");
			return result;
		}
		MtTaskExt mtTaskExt = getMtTaskExtHis(dto);
		MtTaskDetailRetIce detailIce = BeanCopierUtils.copyProperties(
				task, MtTaskDetailRetIce.class, true);
        if (null != mtTaskExt) {
			BeanCopierUtils.copyProperties(mtTaskExt, detailIce, true);
		}
        try {
			// 处理详情基础信息
			handleTaskDetailInfo(detailIce, task);
			// 处理详情其他信息
			handleTaskDetailExtInfo(detailIce, task, dto);
			// 设置签收人信息
			MtTaskHisQueryUtil.handleReceiverInfo(detailIce);
		} catch (Exception e) {
			result.setIsSucc(false);
			result.setMessage("详情信息处理出错！");
			LOGGER.error("queryMtHisTaskDetail", e);
		}
        result.setObject1(detailIce);
        return result;
	}
	
	/**
	 * @Title: getMtTaskHisByTaskId   
	 *  查询任务详情 
	 * @author zhaoqing  
	 * @param dto
	 * @return 
	 */
	private MtTask getMtTaskHisByTaskId(MtHisTaskPageDto dto) {
		String tableName = ReportConstant.TABLE_EVT_MT_TASK_HIS + dto.getYear();
		// 表是否存在
		boolean tableExist = checkTableExist(tableName);
		if (tableExist) {
			return mtTaskHisQueryMapper.queryMtTaskHisByTaskId(dto);
		}
		return null;
	}
	
	/**
	 * @Title: handleTaskDetailExtInfo   
	 *  处理详情其他信息
	 * @author zhaoqing  
	 * @param detailIce
	 * @param task  
	 */
	private void handleTaskDetailExtInfo(MtTaskDetailRetIce detailIce, 
			MtTask task, MtHisTaskPageDto dto) {
		// 添加性别
        detailIce.setPatientGenderName(SexEnum.getNameByCode(detailIce.getPatientGender()));
        dto.setTaskIds(String.valueOf(task.getTaskId()));  
        List<MtTaskExecutors> exeUserList = new ArrayList<>();  
        if (StringUtils.isNotEmpty(dto.getTaskIds())) {
        	// 查询运送员
        	exeUserList = getTaskUserInfoList(dto);
        }
        // list排序
        exeUserListSort(exeUserList);
        // 获取用户信息
        Map<Integer, UserInfo> userInfoMap = getAllUserInfoMap(
        		Arrays.asList(task), MtTask.class, exeUserList);
        List<ExeUser> userList = new ArrayList<>();
        // 处理执行人信息
        handleExeUserInfo(task, userInfoMap, exeUserList, userList);
        // 设置执行人信息
        detailIce.setUserList(userList);
        // 设置创建人名称
        detailIce.setCreateByName(MtTaskHisQueryUtil
        		.getUserName(task.getCreateBy(), userInfoMap));      
        // 查询路线
        MtTaskRoute taskRoute = this.queryTaskRouteHisByTask(task);
        if (taskRoute.getRouteId() != null) {
        	detailIce.setRouteId(String.valueOf(taskRoute.getRouteId()));
        }     
        if (taskRoute.getFinishContent() != null) {
        	detailIce.setFinishContent(taskRoute.getFinishContent());
        }
	}
	
	/**
	 * @Title: handleExeUserInfo   
	 *  处理执行人信息 
	 * @author zhaoqing  
	 * @param task
	 * @param userInfoMap
	 * @param exeUserList
	 * @param userList
	 */
	private void handleExeUserInfo(MtTask task, Map<Integer, UserInfo> userInfoMap, 
			List<MtTaskExecutors> exeUserList, List<ExeUser> userList) {
		if (!AppUtils.isNotEmpty(exeUserList)) {
        	return;
        }
		if (!(TransStatusEnum.TRANS_BACK.getCode().equals(task.getStatus())
        		|| TransStatusEnum.TRANS_CANCEL.getCode().equals(task.getStatus()))) {
        	// 处理执行人信息
        	for (MtTaskExecutors exeUser : exeUserList) {
        		ExeUser user = MtTaskHisQueryUtil.initUserInfo(
        				exeUser.getExeUserId(), userInfoMap);
	        	userList.add(user);
			}
		}
	}
	
	/**
	 * @Title: exeUserListSort   
	 *  执行人信息根据是否是主责任人排序，主责任人在最前面
	 * @author zhaoqing  
	 * @param exeUserList 
	 */
	private void exeUserListSort(List<MtTaskExecutors> exeUserList) {
		Collections.sort(exeUserList, new Comparator<MtTaskExecutors>() {
			@Override
			public int compare(MtTaskExecutors o1, MtTaskExecutors o2) {
				String isExeEndUserO1 = o1.getIsExeEndUser();
				String isExeEndUserO2 = o2.getIsExeEndUser();
				if (StringUtils.isEmpty(isExeEndUserO1)) {
					isExeEndUserO1 = MtConstant.DEFAULT_PERSON_LIABLE;
				}
				if (StringUtils.isEmpty(isExeEndUserO2)) {
					isExeEndUserO2 = MtConstant.DEFAULT_PERSON_LIABLE;
				}
				return isExeEndUserO2.compareTo(isExeEndUserO1);
			}   	
        });
	}
	
	/**
	 * @Title: queryTaskRouteHisByTask   
	 *  根据运送任务查询运输路线 
	 * @author zhaoqing  
	 * @param task
	 * @return 
	 */
	public MtTaskRoute queryTaskRouteHisByTask(MtTask task) {
		MtHisTaskPageDto dto = new MtHisTaskPageDto();
		dto.setTaskId(task.getTaskId());
		if (task.getToHouseId() != null) {
			dto.setToHouseId(task.getToHouseId());
		}
		String tableName = ReportConstant.TABLE_EVT_MT_TASK_ROUTE_HIS + dto.getYear();
		// 表是否存在
		boolean tableExist = checkTableExist(tableName);
		List<MtTaskRoute> list = new ArrayList<>();
		MtTaskRoute mtTaskRoute = new MtTaskRoute();
		if (tableExist) {
			list = mtTaskHisQueryMapper.queryMtTaskRouteHisByExample(dto);
		}
		if (AppUtils.isNotEmpty(list)) {
			mtTaskRoute = list.get(0);
		}
		return mtTaskRoute;
	}
	
	/**
	 * @Title: handleTaskDetailInfo   
	 *  处理运送任务详情基础信息
	 * @author zhaoqing  
	 * @param taskDetailRetIce
	 * @param task 
	 */
	private void handleTaskDetailInfo(MtTaskDetailRetIce taskDetailRetIce, MtTask task) {
		if (null == taskDetailRetIce) {
			return;
		}
		// 查询组织名称
		Organ organ = CommonServiceUtils.getOrganByID(task.getOrganId());
		taskDetailRetIce.setOrganName(organ == null ? "" : organ.getOrganName());
		// 运送状态
		taskDetailRetIce.setStatusName(TransStatusEnum.getNameByCode(task.getStatus()));
		// 处理大类名称
		taskDetailRetIce.setTransTypeParentCodeName(
				TransTypeEnum.getNameByCode(task.getTransTypeParentCode())); 
        // 设置运输类型信息
		setDetailTaskType(taskDetailRetIce, task);
        // 设置详情位置信息
		MtTaskHisQueryUtil.setDetailPositionInfo(taskDetailRetIce, task.getOrganId(), 
				task.getSourceHouseId(), task.getFromHouseId(), task.getToHouseId());
        // 开始结束时间处理 
		MtTaskHisQueryUtil.setDetailTimes(taskDetailRetIce, 
				task.getCreateDate(), task.getBeginTime(), task.getEndTime()); 
    	// 运送工具名称
        taskDetailRetIce.setTransToolsName(TransToolsEnum.getNameByCode(task.getTransTools()));
        // 查询运送紧急程度
        taskDetailRetIce.setUrgencyName(UrgencyEnum.getNameByCode(task.getUrgency()));
        // 查询任务类型
        taskDetailRetIce.setTaskTypeName(TransTaskTypeEnum.getNameByCode(task.getTaskType()));
        // 设置相应类型  
        taskDetailRetIce.setResTypeName(TransDispatchTypeEnum.getNameByCode(task.getResType()));    
	}
	
	/**
	 * @Title: setDetailTaskType   
	 *  设置运输类型信息 
	 * @author zhaoqing  
	 * @param taskDetailRetIce
	 * @param task 
	 */
	private void setDetailTaskType(MtTaskDetailRetIce taskDetailRetIce, MtTask task) {
		if (null != task.getTransTypeId() && task.getTransTypeId().intValue() > 0) {
        	// 查询运输类型
        	TransTypeInfo typeInfo = MtCommonServiceUtils
        			.getTransTypeInfoByTransTypeId(task.getTransTypeId());
        	if (typeInfo != null) {
        		// 查询运输类型名称
        		taskDetailRetIce.setTransTypeName(typeInfo.getTransTypeName());
        		if (TransTaskTypeEnum.TASK_TYPE_SELF.getCode().equals(task.getTaskType())) {
            		// 自主任务设置任务限定时间
        			taskDetailRetIce.setLimitMinute(typeInfo.getStandardMinite());
            		task.setLimitMinute(Integer.valueOf(typeInfo.getStandardMinite()));
    			}
        	}
		}
	}
	
	/**
	 * @Title: getMtTaskExtHis   
	 *  查询任务扩展表信息
	 * @author zhaoqing  
	 * @param dto
	 * @return 
	 */
	private MtTaskExt getMtTaskExtHis(MtHisTaskPageDto dto) {
		String tableName = ReportConstant.TABLE_EVT_MT_TASK_EXT_HIS + dto.getYear();
		// 表是否存在
		boolean tableExist = checkTableExist(tableName);
		List<MtTaskExt> list = new ArrayList<>();
		if (tableExist) {
			list = mtTaskHisQueryMapper.queryMtTaskExtHisByTaskId(dto);
		}
		if (AppUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * <p>Title: queryMtHisTaskFixedDetail</p>   
	 * <p>Description: 运送任务详情(固定任务)</p> 
	 * <p>zhaoqing</p>
	 * @param dto
	 * @return   
	 */
	@Override
	public ResultInfo queryMtHisTaskFixedDetail(MtHisTaskPageDto dto) {
		ResultInfo result = new ResultInfo();
		result.setIsSucc(true);
		MtTask task = getMtTaskHisByTaskId(dto);
		if (null == task) {
			result.setIsSucc(false);
			result.setMessage("任务不存在");
			return result;
		}
		MtTaskExt mtTaskExt = getMtTaskExtHis(dto);
		MtTaskDetailRetIce detailIce = BeanCopierUtils.copyProperties(
				task, MtTaskDetailRetIce.class, true);
        if (null != mtTaskExt) {
			BeanCopierUtils.copyProperties(mtTaskExt, detailIce, true);
		}
        try {
			// 处理详情基础信息
			handleTaskDetailInfo(detailIce, task);
			// 处理详情其他信息
			handleMtHisTaskFixedDetailExt(detailIce, task, dto);
		} catch (Exception e) {
			result.setIsSucc(false);
			result.setMessage("详情信息处理出错！");
			LOGGER.error("queryMtHisTaskFixedDetail", e);
		}
        result.setObject1(detailIce);
        return result;
	}
	
	/**
	 * @Title: handleMtHisTaskFixedDetailExt   
	 *  处理固定任务详情其他信息
	 * @author zhaoqing  
	 * @param detailIce
	 * @param task 
	 */
	private void handleMtHisTaskFixedDetailExt(MtTaskDetailRetIce detailIce, 
			MtTask task, MtHisTaskPageDto dto) {
		// 添加性别
		detailIce.setPatientGenderName(SexEnum.getNameByCode(detailIce.getPatientGender()));
        dto.setTaskIds(String.valueOf(task.getTaskId()));  
        List<MtTaskExecutors> exeUserList = new ArrayList<>(); 
        if (StringUtils.isNotEmpty(dto.getTaskIds())) {
        	// 查询运送员
        	exeUserList = getTaskUserInfoList(dto);
        }
        // 获取用户信息
        Map<Integer, UserInfo> userInfoMap = getAllUserInfoMap(
        		Arrays.asList(task), MtTask.class, exeUserList);
        List<ExeUser> userList = new ArrayList<>();
        // 处理执行人信息
        handleExeUserInfo(userInfoMap, userList, exeUserList);
        // 设置执行人信息
        detailIce.setUserList(userList);
        //  设置创建者
        detailIce.setCreateByName(MtTaskHisQueryUtil
        		.getUserName(task.getCreateBy(), userInfoMap));
	}
	
	/**
	 * @Title: handleExeUserInfo   
	 *  处理执行人信息 
	 * @author zhaoqing  
	 * @param userInfoMap
	 * @param userList
	 * @param exeUserList  
	 */
	private void handleExeUserInfo(Map<Integer, UserInfo> userInfoMap, 
			List<ExeUser> userList, List<MtTaskExecutors> exeUserList) {
		if (!AppUtils.isNotEmpty(exeUserList)) {
        	return;
        }
        for (MtTaskExecutors exeUser : exeUserList) {
        	ExeUser user = MtTaskHisQueryUtil.initUserInfo(
        			exeUser.getExeUserId(), userInfoMap);	
        	userList.add(user);
		}
	}

	/**
	 * <p>Title: queryMtHisFixedTaskExeDetail</p>   
	 * <p>Description: 固定任务某个点执行信息详情</p> 
	 * <p>zhaoqing</p>
	 * @param routeId
	 * @param organId
	 * @param groupOrganId
	 * @return   
	 */
	@Override
	public ResultInfo queryMtHisFixedTaskExeDetail(MtHisTaskPageDto dto) {
		ResultInfo result = new ResultInfo();
		result.setIsSucc(true);
		MtTaskRoute mtTaskRoute = getMtTaskRouteHis(dto.getRouteId(), dto.getYear());
		if (null == mtTaskRoute) {
			result.setIsSucc(false);
			result.setMessage("没有该路线信息");
			return result;
		}
		FixedTaskExeDetailIce detail = BeanCopierUtils.copyProperties(
				mtTaskRoute, FixedTaskExeDetailIce.class, true);
		// 详情数据处理
		handleMtHisFixTaskExeDetail(result, dto.getOrganId(), mtTaskRoute, detail);
		if (!result.getIsSucc()) {
			return result;
		}
		result.setObject1(detail);
		return result;
	}
	
	/**
	 * @Title: handleMtHisFixTaskExeDetail   
	 *  处理固定任务某个点执行信息详情
	 * @author zhaoqing  
	 * @param result
	 * @param organId
	 * @param mtTaskRoute
	 * @param detail 
	 */
	private void handleMtHisFixTaskExeDetail(ResultInfo result, int organId, 
			MtTaskRoute mtTaskRoute, FixedTaskExeDetailIce detail) {
		try {
			List<FileParams> autographFileList = new ArrayList<>();
			
			// 获取签名信息
			CommonFileIce[] autographFile = CommonServiceUtils.getCommonFileByRefType(
					FileRefType.MT_TASK_AUTOGRAPH_FILE, detail.getRouteId());
			if (null != autographFile && autographFile.length > 0) {
				// 有签名信息则处理数据，设置签名详情
				List<CommonFileIce> asList = Arrays.asList(autographFile);
				autographFileList = BeanCopierUtils.copyList2List(asList, FileParams.class, true);
				detail.setAutographFileList(autographFileList);
			}
			// 处理已打卡后的信息
			handleFinishInfo(mtTaskRoute, detail);
			// 位置Id
			List<Integer> locationIdList = Arrays.asList(mtTaskRoute.getHouseId());
			// 获取位置信息
			List<MtLocationInfoIce> locationInfoList = MtCommonServiceUtils
					.getLocationInfoList(organId, locationIdList);
			if (AppUtils.isNotEmpty(locationInfoList)) {
				// 设置科室名称
				detail.setHouseName(locationInfoList.get(0).getLocationName());
			}
		} catch (Exception e) {
			result.setIsSucc(false);
			result.setMessage("固定任务执行详情信息处理出错！");
			LOGGER.error("handleMtHisFixTaskExeDetail", e);
		}
	}
	
	/**
	 * @Title: handleFinishInfo   
	 *  处理已打卡后的信息
	 * @author zhaoqing  
	 * @param mtTaskRoute
	 * @param detail   
	 */
	private void handleFinishInfo(MtTaskRoute mtTaskRoute, FixedTaskExeDetailIce detail) {
		List<FileParams> finishFileList = new ArrayList<>();
		if (MtConstant.CLOCK_STATUS_1.equals(mtTaskRoute.getStatus())) {
			// 已打卡,查询完成图片
			CommonFileIce[] finishFile = CommonServiceUtils.getCommonFileByRefType(
					FileRefType.MT_TASK_FINISH_FILE, detail.getRouteId());
			if (null != finishFile && finishFile.length > 0) {
				// 有打卡图片数据，则处理数据，设置打卡信息
				List<CommonFileIce> asList = Arrays.asList(finishFile);
				finishFileList = BeanCopierUtils.copyList2List(asList, FileParams.class, true);
				detail.setFinishFileList(finishFileList);
			}
			// 完成时间
			detail.setFinishTime(DateUtil.formatDateToString(
					mtTaskRoute.getUpdateDate(), MtConstant.FORMAT_YYYY_MM_DD_HH_MM));
		}
	}
	
	/**
	 * @Title: getMtTaskRouteHis   
	 *  查询线路信息
	 * @author zhaoqing  
	 * @param routeId
	 * @param groupOrganId
	 * @return
	 */
	private MtTaskRoute getMtTaskRouteHis(Integer routeId, Integer year) {
		MtHisTaskPageDto dto = new MtHisTaskPageDto();
		dto.setRouteId(routeId);
		dto.setYear(year);
		String tableName = ReportConstant.TABLE_EVT_MT_TASK_ROUTE_HIS + dto.getYear();
		// 表是否存在
		boolean tableExist = checkTableExist(tableName);
		List<MtTaskRoute> list = new ArrayList<>();
		if (tableExist) {
			list = mtTaskHisQueryMapper.queryMtTaskRouteHisByExample(dto);
		} 
		if (AppUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * @Title: checkTableExist   
	 *  判断表名是否存在 
	 * @author zhaoqing  
	 * @param tableName
	 * @return 存在返回true, 不存在返回false
	 */
	private boolean checkTableExist(String tableName) {	
		MtHisTaskPageDto dto = new MtHisTaskPageDto();
		dto.setTableSchema(UhomePropUtils.getProperty("report_table_schema"));
		dto.setTableName(tableName);
		int count = mtTaskHisQueryMapper.countTableName(dto);
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * @Title: countCrossMonthData   
	 *  查询跨表的数据条数
	 * @author zhaoqing  
	 * @param queryDto
	 * @return
	 */
	private int countCrossMonthData(MtHisTaskPageDto queryDto) {
		String tableName = ReportConstant.TABLE_EVT_MT_TASK_EXECUTORS_HIS + queryDto.getYear();
		// 表是否存在
		boolean tableExist = checkTableExist(tableName);
		int count = 0;
		if (tableExist) {
			count = mtTaskHisQueryMapper.countHisTaskPageByUser(queryDto);
		}
		return count;
	}
	
	/**
	 * @Title: getCrossMonthQueryDto   
	 *  获取需要跨月查询时的查询条件 
	 * @author zhaoqing  
	 * @param dto
	 * @return 
	 */
	private MtHisTaskPageDto getCrossMonthQueryDto(MtHisTaskPageDto dto) {
		MtHisTaskPageDto queryDto = BeanCopierUtils.copyProperties(dto, 
				MtHisTaskPageDto.class, true, DateUtil.FORMAT_YYYYMMDDHHMMSS);
		if (DateUtil.isFirstMonthOfYear(String.valueOf(dto.getCycleYm()))) {
			// 查询一月份数据时
			queryDto.setYear(dto.getYear() - 1);	
		}
		if (DateUtil.isLastMonthOfYear(String.valueOf(dto.getCycleYm()))) {
			// 查询十二月份数据时
			queryDto.setYear(dto.getYear() + 1);
		}
		return queryDto;
	}
}
