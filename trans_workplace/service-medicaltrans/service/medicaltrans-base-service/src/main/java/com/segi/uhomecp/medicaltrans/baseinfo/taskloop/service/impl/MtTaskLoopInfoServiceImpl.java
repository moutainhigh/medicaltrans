package com.segi.uhomecp.medicaltrans.baseinfo.taskloop.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import segi.datacachesvr.queryInfo.Organ;
import segi.datacachesvr.queryInfo.TOrganInfo;
import segi.datacachesvr.queryInfo.UserInfoV2;
import segi.medicaltrans.location.common.MtLocationInfoIce;
import segiwh.common.User;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoop;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoopCriteria;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoopExecutors;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoopExecutorsCriteria;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoopRoute;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoopRouteCriteria;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoopCriteria.Criteria;
import com.segi.uhomecp.medicaltrans.base.taskloop.service.MtTaskLoopExecutorsService;
import com.segi.uhomecp.medicaltrans.base.taskloop.service.MtTaskLoopRouteService;
import com.segi.uhomecp.medicaltrans.base.taskloop.service.MtTaskLoopService;
import com.segi.uhomecp.medicaltrans.baseinfo.taskloop.dao.MtTaskLoopInfoMapper;
import com.segi.uhomecp.medicaltrans.baseinfo.taskloop.dto.HouseDto;
import com.segi.uhomecp.medicaltrans.baseinfo.taskloop.dto.MtTaskLoopDto;
import com.segi.uhomecp.medicaltrans.baseinfo.taskloop.dto.MtTaskLoopPageDto;
import com.segi.uhomecp.medicaltrans.baseinfo.taskloop.enums.TaskLoopStatusEnum;
import com.segi.uhomecp.medicaltrans.baseinfo.taskloop.service.MtTaskLoopInfoService;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.constant.MtConstant.MtDictType;
import com.segi.uhomecp.medicaltrans.constant.MtSeqContants;
import com.segi.uhomecp.medicaltrans.dto.CrontabDto;
import com.segi.uhomecp.medicaltrans.utils.CrontabConstructUtil;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.medicaltrans.utils.MtIbatchQueryServiceUtils;
import com.segi.uhomecp.utils.SeqContants;
import com.segi.uhomecp.web.PageUtils;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.interfaces.InvocationHandler;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;

@Service
public class MtTaskLoopInfoServiceImpl implements MtTaskLoopInfoService {

	@Autowired
	private MtTaskLoopService mtTaskLoopService;

	@Autowired
	private MtTaskLoopRouteService mtTaskLoopRouteService;

	@Autowired
	private MtTaskLoopExecutorsService mtTaskLoopExecutorsService;

	@Autowired
	private MtTaskLoopInfoMapper mtTaskLoopInfoMapper;

	/**
	 * @discription 循环任务分页列表
	 * @author yangyh@segimail.com
	 * @created 2018年3月27日 下午3:32:25
	 * @param mtTaskLoopDto
	 * @return
	 */
	@Override
	public MtTaskLoopPageDto queryTaskLoopByPage(MtTaskLoopDto mtTaskLoopDto) {
		int pageNo = Constant.INIT_PAGE_NO;
		if (null != mtTaskLoopDto.getPageNo()) {
			pageNo = Integer.valueOf(mtTaskLoopDto.getPageNo());
		}
		int pageLength = Constant.DEFAULT_PAGE_LENGTH;
		if (null != mtTaskLoopDto.getPageLength()) {
			pageLength = Integer.valueOf(mtTaskLoopDto.getPageLength());
		}
		MtTaskLoopCriteria example = new MtTaskLoopCriteria();
		Criteria criteria = example.createCriteria();
		// organId,必传
		criteria.andOrganIdEqualTo(mtTaskLoopDto.getOrganId());
		// transTypeParentCode,非必传
		if (StringUtils.isNotBlank(mtTaskLoopDto.getTransTypeParentCode())) {
			criteria.andTransTypeParentCodeEqualTo(mtTaskLoopDto.getTransTypeParentCode());
		}
		// taskName,非必传,模糊查询
		if (StringUtils.isNotEmpty(mtTaskLoopDto.getTaskName())) {
			criteria.andTaskNameLike(mtTaskLoopDto.getTaskName() + Constant.SPLIT_PER);
		}
		// 状态，非必传
		if (StringUtils.isNotBlank(mtTaskLoopDto.getStatus())) {
			criteria.andStatusEqualTo(mtTaskLoopDto.getStatus());
		}
		// 删除的不展示在分页列表
		criteria.andStatusNotEqualTo(TaskLoopStatusEnum.STATUS_DEL.getStatus());
		example.setOrderByClause(MtConstant.CREATE_DATE_DESC);
		PageList<MtTaskLoop> pageList = mtTaskLoopService.selectByExampleWithRowbounds(example,
				PageUtils.getPageBoundsByPageNo(pageNo, pageLength));
		// 查出来的分页列表装换类型
		List<MtTaskLoopDto> resultList = BeanCopierUtils.copyList2List(pageList, MtTaskLoopDto.class, true);
		// 从分页列表取出所有的循环任务单主键id集合
		List<Integer> loopIdList = AppUtils.list2ParamsList(resultList, (obj) -> obj.getTaskLoopId());
		Map<Integer, List<Integer>> userIdListMap = new HashMap<>();
		Map<Integer, UserInfoV2> userMap = new HashMap<>();
		if (AppUtils.isNotEmpty(loopIdList)) {
			// 查出所有循环任务List中相关的执行人List
			List<MtTaskLoopExecutors> executorsList = this.queryExecutorsListByList(loopIdList);
			List<Integer> userIdList = null;
			// 将执行人List分组到对应的循环任务下
			for (MtTaskLoopExecutors executors : executorsList) {
				Integer loopId = executors.getTaskLoopId();
				userIdList = userIdListMap.get(loopId);
				if (null == userIdList) {
					userIdList = new ArrayList<Integer>();
					userIdListMap.put(loopId, userIdList);
				}
				userIdList.add(executors.getUserId());
			}
			List<Integer> userIds = AppUtils.list2ParamsList(executorsList, (obj) -> obj.getUserId());
			UserInfoV2[] userInfoArr = MtIbatchQueryServiceUtils.queryUserListByUserIds(AppUtils.listToString(userIds, ','));
			// 查出所有人信息
			for (UserInfoV2 userInfoV2 : userInfoArr) {
				if (null != userInfoV2) {
					userMap.put(Integer.valueOf(userInfoV2.getUserId()), userInfoV2);
				}
			}
		}
		// 查出所有部门信息
		Map<Integer, Organ> organInfoMap = CommonServiceUtils.getOrganInfoMap(resultList, (obj) -> obj.getOrganId());
		for (MtTaskLoopDto dto : resultList) {
			Integer taskLoopId = dto.getTaskLoopId();
			// organName
			Organ organ = organInfoMap.get(dto.getOrganId());
			if (null != organ) {
				dto.setOrganName(organ.getOrganName());
			}
			// 根据字典表查出运送大类和运送工具
			dto.setTransTypeParentName(CommonServiceUtils.getDetailName(MtDictType.MT_TRANS_TYPE,
					dto.getTransTypeParentCode()));
			dto.setTransToolsName(CommonServiceUtils.getDetailName(MtDictType.MT_TASK_TOOL, dto.getTransTools()));
			// 状态名称
			dto.setStatusName(TaskLoopStatusEnum.getNamefromStatus(dto.getStatus()));
			// 时间格式
			dto.setTaskBeginTimeStr(AppUtils.toTime(null != dto.getTaskBeginTime()
					? dto.getTaskBeginTime()
					: MtConstant.NEGATIVE_INT));
			dto.setTaskEndTimeStr(AppUtils.toTime(null != dto.getTaskEndTime()
					? dto.getTaskEndTime()
					: MtConstant.NEGATIVE_INT));
			// 根据循环任务id查询执行人List
			getUserInfoList(dto, userIdListMap.get(taskLoopId), userMap);
		}
		MtTaskLoopPageDto mtTaskLoopPageDto = new MtTaskLoopPageDto();
		mtTaskLoopPageDto.setPaginator(pageList.getPaginator());
		mtTaskLoopPageDto.setResultList(resultList);
		return mtTaskLoopPageDto;
	}

	/**
	 * @discription 根据科室List获得house信息List
	 * @author yangyh@segimail.com
	 * @created 2018年5月21日 下午4:44:17
	 * @param mtTaskLoopDto
	 * @param dto
	 * @param routeList
	 */
	private void getHouseInfoList(MtTaskLoopDto mtTaskLoopDto, Integer organId, List<Integer> locationIdList) {
		if (AppUtils.isNotEmpty(locationIdList)) {
			// 通过houseIdList一次性查出所有数据，进行分组
			Map<String, MtLocationInfoIce> houseMap = AppUtils.list2Map(
					MtCommonServiceUtils.getLocationInfoList(organId, locationIdList),
					new InvocationHandler<String, MtLocationInfoIce>() {
						@Override
						public String invoke(MtLocationInfoIce obj) {
							return obj.getLocationId();
						}
					});
			List<String> houseNameList = new ArrayList<>();
			List<HouseDto> houseList = new ArrayList<>();
			for (Integer houseId : locationIdList) {
				HouseDto house = new HouseDto();
				house.setHouseId(String.valueOf(houseId));
				MtLocationInfoIce mtLocationInfoIce = houseMap.get(String.valueOf(houseId));
				if (null != mtLocationInfoIce) {
					house.setHouseName(mtLocationInfoIce.getLocationName());
					house.setStatus(mtLocationInfoIce.getStatus());
					houseNameList.add(mtLocationInfoIce.getLocationName());
				}
				houseList.add(house);
			}
			mtTaskLoopDto.setHouseList(houseList);
			// 拼接houseIds
			mtTaskLoopDto.setHouseIds(AppUtils.listToString(locationIdList, ','));
			// 拼接housrNames
			mtTaskLoopDto.setHouseNames(AppUtils.listToString(houseNameList, ','));
		}
	}

	/**
	 * @discription 根据执行人List获得user信息List
	 * @author yangyh@segimail.com
	 * @created 2018年5月21日 下午4:50:20
	 * @param dto
	 * @param executorList
	 */
	private void getUserInfoList(MtTaskLoopDto mtTaskLoopDto, List<Integer> userIdList, Map<Integer, UserInfoV2> userMap) {
		if (AppUtils.isNotEmpty(userIdList)) {
			List<String> userNameList = new ArrayList<>();
			List<User> userList = new ArrayList<>();
			for (Integer userId : userIdList) {
				User user = new User();
				user.setUserId(String.valueOf(userId));
				UserInfoV2 userInfo = userMap.get(userId);
				if (null != userInfo) {
					user.setUserName(userInfo.getUserName());
					user.setUserNo(userInfo.getJobNumber());
					user.setUserStatus(String.valueOf(userInfo.getStatus()));
					userNameList.add(userInfo.getUserName());
				}
				userList.add(user);
			}
			// 将userId、userName、userNo、userStatus放入userList
			mtTaskLoopDto.setUserList(userList);
			// 拼接userIds
			mtTaskLoopDto.setUserIds(AppUtils.listToString(userIdList, ','));
			// 拼接userNames
			mtTaskLoopDto.setUserNames(AppUtils.listToString(userNameList, ','));
		}
	}

	/**
	 * @discription 新建循环任务
	 * @author yangyh@segimail.com
	 * @created 2018年3月27日 下午3:06:32
	 * @param mtTaskLoopDto
	 * @return
	 */
	@Override
	public Integer saveTaskLoop(MtTaskLoopDto mtTaskLoopDto) {
		// 判断数据库是否已有同名的任务单
		boolean bool = this.distinctTaskName(mtTaskLoopDto, null);
		if (bool != true) {
			throw new RuntimeException("该循环任务名称已存在！");
		}
		MtTaskLoop mtTaskLoop = BeanCopierUtils.copyProperties(mtTaskLoopDto, MtTaskLoop.class, true);
		// 查出groupOrganId
		if (null == mtTaskLoopDto.getOrganId()) {
			throw new RuntimeException("人员部门id为空！");
		}
		TOrganInfo tOrganInfo = CommonServiceUtils.getTopOrganByOrganID(mtTaskLoopDto.getOrganId());
		if (null != tOrganInfo){
			mtTaskLoop.setGroupOrganId(tOrganInfo.getOrganId());
		}
		// 初始状态值为有效
		mtTaskLoop.setStatus(TaskLoopStatusEnum.STATUS_CD_NORMAL.getStatus());
		// 计算限定时间（分钟）
		mtTaskLoop.setLimitMinute(this.getLimitMinute(mtTaskLoop.getTaskEndTime(), mtTaskLoop.getTaskBeginTime()));
		// 当前时间
		mtTaskLoop.setCreateDate(new Date());
		CrontabDto crontabDto = getGenerateTime(mtTaskLoop);
		mtTaskLoop.setTaskCron(CrontabConstructUtil.getTaskCron(crontabDto));
		Integer taskLoopId = insertTaskLoop(mtTaskLoop);
		insertTaskLoopExecutorsList(AppUtils.str2Integer(mtTaskLoopDto.getUserIds()), taskLoopId);
		insertTaskLoopRouteList(AppUtils.str2Integer(mtTaskLoopDto.getHouseIds()), taskLoopId);
		return taskLoopId;
	}
	
	private Integer getLimitMinute(Integer taskEndTime, Integer taskBeginTime){
		int limitMinute = 0;
		SimpleDateFormat formatHm = new SimpleDateFormat("HH:mm");
		Date endTime= null;
		// 计算限定时间（分钟）
		try {
			Date beginTime  = formatHm.parse(AppUtils.toTime(taskBeginTime));
			if (taskEndTime - taskBeginTime > 0) {
				endTime = formatHm.parse(AppUtils.toTime(taskEndTime));
				limitMinute = (int) ((endTime.getTime()-beginTime.getTime())/1000/60);
			} else {
				taskEndTime = taskEndTime + 2400;
				endTime = formatHm.parse(AppUtils.toTime(taskEndTime));
				limitMinute = (int) ((endTime.getTime()-beginTime.getTime())/1000/60);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return limitMinute;
	}

	/**
	 * @discription 循环任务生成cronTab表达式
	 * @author yangyh@segimail.com
	 * @created 2018年5月22日 下午7:25:50
	 * @param mtTaskLoop
	 * @return
	 */
	private CrontabDto getGenerateTime(MtTaskLoop mtTaskLoop) {
		// 获取触发时间
		String beginTime = String.valueOf(mtTaskLoop.getTaskBeginTime());
		int length = beginTime.length();
		if (!"null".equals(beginTime)) {
			int hrs = 0;
			int min = 0;
			if (length > 2) {
				String beginHrs = beginTime.substring(0, length - 2);
				String beginMin = beginTime.substring(length - 2, length);
				hrs = Integer.valueOf(beginHrs).intValue();
				min = Integer.valueOf(beginMin).intValue();
			} else if (!"0".equals(beginTime)) {
				min = Integer.valueOf(beginTime).intValue();
			}
			return new CrontabDto("*", mtTaskLoop.getLoopMonths(), mtTaskLoop.getLoopDays(), mtTaskLoop.getLoopWeeks(),
					String.valueOf(hrs), String.valueOf(min), "0");
		}
		return new CrontabDto();
	}

	/**
	 * @discription 新增循环任务表
	 * @author yangyh@segimail.com
	 * @created 2018年3月27日 下午8:05:17
	 * @param mtTaskLoop
	 */
	private Integer insertTaskLoop(MtTaskLoop mtTaskLoop) {
		int taskLoopId = SeqContants.getSequnces(MtSeqContants.MT_TASK_LOOP_ID_SEQ).intValue();
		mtTaskLoop.setTaskLoopId(taskLoopId);
		mtTaskLoopService.insert(mtTaskLoop);
		return taskLoopId;
	}

	/**
	 * @discription 批量新增循环任务执行人表
	 * @author yangyh@segimail.com
	 * @created 2018年3月28日 上午9:46:44
	 * @param list
	 */
	private void insertTaskLoopExecutorsList(List<Integer> userIdlist, Integer taskLoopId) {
		List<MtTaskLoopExecutors> list = new ArrayList<>();
		for (Integer userId : userIdlist) {
			MtTaskLoopExecutors obj = new MtTaskLoopExecutors();
			int executorId = SeqContants.getSequnces(MtSeqContants.MT_EXECUTOR_ID_SEQ).intValue();
			obj.setExecutorId(executorId);
			obj.setTaskLoopId(taskLoopId);
			obj.setUserId(userId);
			list.add(obj);
		}
		this.mtTaskLoopInfoMapper.saveBatchMtTaskLoopExecutors(list);
	}

	/**
	 * @discription 批量新增循环任务运送线路表
	 * @author yangyh@segimail.com
	 * @created 2018年3月28日 上午9:47:10
	 * @param list
	 */
	private void insertTaskLoopRouteList(List<Integer> houseIdList, Integer taskLoopId) {
		Short sortNo = 1;
		List<MtTaskLoopRoute> list = new ArrayList<>();
		for (Integer houseId : houseIdList) {
			MtTaskLoopRoute obj = new MtTaskLoopRoute();
			int routeId = SeqContants.getSequnces(MtSeqContants.MT_ROUTE_ID_ID_SEQ).intValue();
			obj.setRouteId(routeId);
			obj.setTaskLoopId(taskLoopId);
			obj.setHouseId(houseId);
			obj.setSortNo(sortNo);
			list.add(obj);
			sortNo++;
		}
		this.mtTaskLoopInfoMapper.saveBatchMtTaskLoopRoute(list);
	}

	/**
	 * @discription 编辑循环任务
	 * @author yangyh@segimail.com
	 * @created 2018年3月27日 下午3:06:32
	 * @param mtTaskLoopDto
	 * @return
	 */
	@Override
	public Integer updateTaskLoop(MtTaskLoopDto mtTaskLoopDto) {
		Integer taskLoopId = mtTaskLoopDto.getTaskLoopId();
		MtTaskLoop loop = mtTaskLoopService.selectByPrimaryKey(mtTaskLoopDto.getTaskLoopId());
		// 判断数据库是否已有同名的任务单
		boolean bool = this.distinctTaskName(mtTaskLoopDto, loop);
		if (bool != true) {
			throw new RuntimeException("该循环任务名称已存在！");
		}
		MtTaskLoop mtTaskLoop = BeanCopierUtils.copyProperties(mtTaskLoopDto, MtTaskLoop.class, true);
		// 计算限定时间（分钟）
		mtTaskLoop.setLimitMinute(this.getLimitMinute(mtTaskLoop.getTaskEndTime(), mtTaskLoop.getTaskBeginTime()));
		mtTaskLoop.setUpdateDate(new Date());
		CrontabDto crontabDto = getGenerateTime(mtTaskLoop);
		mtTaskLoop.setTaskCron(CrontabConstructUtil.getTaskCron(crontabDto));
		int updateCount = mtTaskLoopService.updateByPrimaryKeySelective(mtTaskLoop);
		if (updateCount > 0) {
			// 把之前的执行人表和线路表根据引用id清空
			MtTaskLoopExecutorsCriteria executorsExample = new MtTaskLoopExecutorsCriteria();
			MtTaskLoopExecutorsCriteria.Criteria executorsCriteria = executorsExample.createCriteria();
			MtTaskLoopRouteCriteria routeExample = new MtTaskLoopRouteCriteria();
			MtTaskLoopRouteCriteria.Criteria routeCriteria = routeExample.createCriteria();
			// 根据taskLoopId为条件
			executorsCriteria.andTaskLoopIdEqualTo(taskLoopId);
			routeCriteria.andTaskLoopIdEqualTo(taskLoopId);
			// 清空执行人和线路
			mtTaskLoopExecutorsService.deleteByExample(executorsExample);
			mtTaskLoopRouteService.deleteByExample(routeExample);
			// 重新循环新增执行人表和线路表
			insertTaskLoopExecutorsList(AppUtils.str2Integer(mtTaskLoopDto.getUserIds()), taskLoopId);
			insertTaskLoopRouteList(AppUtils.str2Integer(mtTaskLoopDto.getHouseIds()), taskLoopId);
		}
		return taskLoopId;
	}

	/**
	 * 校验同一项目位置名称是否重复(true没有重复，false重复了)，修改位置时需要传老位置
	 * @param mtBuildlocation
	 * @param mtBuildLocationOld
	 * @return
	 */
	private boolean distinctTaskName(MtTaskLoopDto mtTaskLoopDto,MtTaskLoop mtTaskLoop){
		MtTaskLoopCriteria example = new MtTaskLoopCriteria();
		MtTaskLoopCriteria.Criteria criteria = example.createCriteria();
		//修改的时候可以和老位置名字重复
		if(mtTaskLoop != null){
			criteria.andOrganIdEqualTo(mtTaskLoopDto.getOrganId());
			criteria.andStatusNotEqualTo(TaskLoopStatusEnum.STATUS_DEL.getStatus());
			criteria.andTaskNameEqualTo(mtTaskLoopDto.getTaskName());
			criteria.andTaskLoopIdNotEqualTo(mtTaskLoopDto.getTaskLoopId());
		}else{
			criteria.andOrganIdEqualTo(mtTaskLoopDto.getOrganId());
			criteria.andStatusNotEqualTo(TaskLoopStatusEnum.STATUS_DEL.getStatus());
			criteria.andTaskNameEqualTo(mtTaskLoopDto.getTaskName());
		}
		int count = mtTaskLoopService.countByExample(example);
		if (count == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * @discription 循环任务停用启用、删除修改状态
	 * @author yangyh@segimail.com
	 * @created 2018年3月27日 下午3:06:32
	 * @param taskLoopId
	 * @return
	 */
	@Override
	public Integer updateTaskLoopStatus(MtTaskLoopDto mtTaskLoopDto) {
		Integer taskLoopId = mtTaskLoopDto.getTaskLoopId();
		String status = mtTaskLoopDto.getStatus();
		MtTaskLoop mtTaskLoop = BeanCopierUtils.copyProperties(mtTaskLoopDto, MtTaskLoop.class, true);
		//当不为停用操作时，置空停用原因
		if(!TaskLoopStatusEnum.STATUS_CD_STOP.getStatus().equals(mtTaskLoop.getStatus()) ){
			mtTaskLoop.setLoseRemark("");
		}
		mtTaskLoop.setUpdateDate(new Date());
		if (TaskLoopStatusEnum.STATUS_CD_NORMAL.getStatus().equals(status)) {
			List<Integer> locationIdList = mtTaskLoopInfoMapper.selectLocationIdList(taskLoopId);
			List<MtLocationInfoIce> list = MtCommonServiceUtils.getLocationInfoList(mtTaskLoopDto.getOrganId(),
					locationIdList);
			for (MtLocationInfoIce mtLocationInfoIce : list) {
				if (Constant.STATUS_CD_DEL.equals(mtLocationInfoIce.getStatus())) {
					throw new RuntimeException("该任务单存在无效位置，无法启用！");
				}
			}
		}
		MtTaskLoop taskLoop = mtTaskLoopService.selectByPrimaryKey(taskLoopId);
		if (null == taskLoop) {
			throw new RuntimeException("该任务单不存在！");
		}
		// 不符合条件不予以修改
		if (TaskLoopStatusEnum.STATUS_CD_STOP.getStatus().equals(status)) {
			if (TaskLoopStatusEnum.STATUS_CD_NORMAL.getStatus().equals(taskLoop.getStatus())) {
				mtTaskLoopService.updateByPrimaryKeySelective(mtTaskLoop);
			} else {
				throw new RuntimeException("无法停用，任务单状态不为启用！");
			}
		}
		if (TaskLoopStatusEnum.STATUS_CD_NORMAL.getStatus().equals(status)) {
			if (TaskLoopStatusEnum.STATUS_CD_STOP.getStatus().equals(taskLoop.getStatus())) {
				mtTaskLoopService.updateByPrimaryKeySelective(mtTaskLoop);
			} else {
				throw new RuntimeException("无法启用，任务单状态不为停用！");
			}
		}
		if (TaskLoopStatusEnum.STATUS_DEL.getStatus().equals(status)) {
			if (TaskLoopStatusEnum.STATUS_CD_STOP.getStatus().equals(taskLoop.getStatus())) {
				mtTaskLoopService.updateByPrimaryKeySelective(mtTaskLoop);
			} else {
				throw new RuntimeException("无法删除，任务单状态不为停用！");
			}
		}
		return mtTaskLoopDto.getTaskLoopId();
	}

	/**
	 * @discription 循环任务详情
	 * @author yangyh@segimail.com
	 * @created 2018年3月27日 下午3:06:32
	 * @param taskLoopId
	 * @return
	 */
	@Override
	public MtTaskLoopDto queryTaskLoopDetail(Integer taskLoopId) {
		MtTaskLoop mtTaskLoop = mtTaskLoopService.selectByPrimaryKey(taskLoopId);
		if (TaskLoopStatusEnum.STATUS_DEL.equals(mtTaskLoop.getStatus())) {
			throw new RuntimeException("该任务单已删除！");
		}
		MtTaskLoopDto mtTaskLoopDto = BeanCopierUtils.copyProperties(mtTaskLoop, MtTaskLoopDto.class, true);
		// organName
		Organ organ = CommonServiceUtils.getOrganByID(mtTaskLoopDto.getOrganId());
		if (null != organ) {
			mtTaskLoopDto.setOrganName(organ.getOrganName());
		}
		// 根据字典表查出运送大类和运送工具
		mtTaskLoopDto.setTransTypeParentName(CommonServiceUtils.getDetailName(MtDictType.MT_TRANS_TYPE,
				mtTaskLoopDto.getTransTypeParentCode()));
		mtTaskLoopDto.setTransToolsName(CommonServiceUtils.getDetailName(MtDictType.MT_TASK_TOOL,
				mtTaskLoopDto.getTransTools()));
		// 状态名称
		mtTaskLoopDto.setStatusName(TaskLoopStatusEnum.getNamefromStatus(mtTaskLoopDto.getStatus()));
		// 时间格式
		mtTaskLoopDto.setTaskBeginTimeStr(AppUtils.toTime(null != mtTaskLoopDto.getTaskBeginTime() ? mtTaskLoopDto
				.getTaskBeginTime() : MtConstant.NEGATIVE_INT));
		mtTaskLoopDto.setTaskEndTimeStr(AppUtils.toTime(null != mtTaskLoopDto.getTaskEndTime() ? mtTaskLoopDto
				.getTaskEndTime() : MtConstant.NEGATIVE_INT));
		// 根据taskLoopId查出关联路线id
		List<Integer> locationIdList = mtTaskLoopInfoMapper.selectLocationIdList(taskLoopId);
		getHouseInfoList(mtTaskLoopDto, mtTaskLoopDto.getOrganId(), locationIdList);
		// 根据taskLoopId查出关联执行人id
		List<Integer> userIdList = mtTaskLoopInfoMapper.selectUserIdList(taskLoopId);
		UserInfoV2[] userInfoArr = MtIbatchQueryServiceUtils.queryUserListByUserIds(AppUtils.listToString(userIdList, ','));
		// 查出所有人信息
		Map<Integer, UserInfoV2> userMap = new HashMap<Integer, UserInfoV2>();
		for (UserInfoV2 userInfoV2 : userInfoArr) {
			if (null != userInfoV2) {
				userMap.put(Integer.valueOf(userInfoV2.getUserId()), userInfoV2);
			}
		}
		getUserInfoList(mtTaskLoopDto, userIdList, userMap);
		return mtTaskLoopDto;
	}

	/**
	 * @discription 根据循环任务id查询执行人List
	 * @author yangyh@segimail.com
	 * @created 2018年3月28日 下午7:41:52
	 * @param taskLoopId
	 * @return
	 */
	@Override
	public List<MtTaskLoopExecutors> queryExecutorsList(Integer taskLoopId) {
		List<MtTaskLoopExecutors> list = new ArrayList<MtTaskLoopExecutors>();
		MtTaskLoopExecutorsCriteria example = new MtTaskLoopExecutorsCriteria();
		MtTaskLoopExecutorsCriteria.Criteria criteria = example.createCriteria();
		criteria.andTaskLoopIdEqualTo(taskLoopId);
		list = mtTaskLoopExecutorsService.selectByExample(example);
		return list;
	}

	/**
	 * @discription 根据循环任务id查询线路List
	 * @author yangyh@segimail.com
	 * @created 2018年3月28日 下午7:42:37
	 * @param taskLoopId
	 * @return
	 */
	@Override
	public List<MtTaskLoopRoute> queryRouteList(Integer taskLoopId) {
		List<MtTaskLoopRoute> list = new ArrayList<MtTaskLoopRoute>();
		MtTaskLoopRouteCriteria example = new MtTaskLoopRouteCriteria();
		MtTaskLoopRouteCriteria.Criteria criteria = example.createCriteria();
		criteria.andTaskLoopIdEqualTo(taskLoopId);
		example.setOrderByClause(" SORT_NO ASC ");
		list = mtTaskLoopRouteService.selectByExample(example);
		return list;
	}
	
	/**
	 * @discription 根据循环任务id查询执行人List
	 * @author yangyh@segimail.com
	 * @created 2018年3月28日 下午7:41:52
	 * @param taskLoopId
	 * @return
	 */
	@Override
	public List<MtTaskLoopExecutors> queryExecutorsListByList(List<Integer> taskLoopIdList) {
		List<MtTaskLoopExecutors> list = new ArrayList<MtTaskLoopExecutors>();
		MtTaskLoopExecutorsCriteria example = new MtTaskLoopExecutorsCriteria();
		MtTaskLoopExecutorsCriteria.Criteria criteria = example.createCriteria();
		criteria.andTaskLoopIdIn(taskLoopIdList);
		list = mtTaskLoopInfoMapper.selectUserIdListByList(example);
		return list;
	}

	/**
	 * @discription 根据循环任务id查询线路List
	 * @author yangyh@segimail.com
	 * @created 2018年3月28日 下午7:42:37
	 * @param taskLoopId
	 * @return
	 */
	@Override
	public List<MtTaskLoopRoute> queryRouteListByList(List<Integer> taskLoopIdList) {
		List<MtTaskLoopRoute> list = new ArrayList<MtTaskLoopRoute>();
		MtTaskLoopRouteCriteria example = new MtTaskLoopRouteCriteria();
		MtTaskLoopRouteCriteria.Criteria criteria = example.createCriteria();
		criteria.andTaskLoopIdIn(taskLoopIdList);
		list = mtTaskLoopInfoMapper.selectLocationIdListByList(example);
		return list;
	}

	/**
	 * @discription 查询线路（科室List，带状态）
	 * @author yangyh@segimail.com
	 * @created 2018年3月27日 下午3:06:32
	 * @param taskLoopId
	 * @return
	 */
	@Override
	public List<HouseDto> queryHouseList(Integer taskLoopId) {
		// 根据循环任务id查询organId
		MtTaskLoop mtTaskLoop = mtTaskLoopService.selectByPrimaryKey(taskLoopId);
		Integer organId = null != mtTaskLoop ? mtTaskLoop.getOrganId() : null;
		// 根据循环任务id查询线路houseIdList
		List<Integer> houseIdList = mtTaskLoopInfoMapper.selectLocationIdList(taskLoopId);
		houseIdList.removeAll(Collections.singleton(null));
		Map<String, MtLocationInfoIce> houseMap = AppUtils.list2Map(
				MtCommonServiceUtils.getLocationInfoList(organId, houseIdList), (obj) -> obj.getLocationId());
		List<HouseDto> houseList = new ArrayList<>();
		for (Integer houseId : houseIdList) {
			HouseDto house = new HouseDto();
			house.setHouseId(String.valueOf(houseId));
			MtLocationInfoIce mtLocationInfoIce = houseMap.get(String.valueOf(houseId));
			if (null != mtLocationInfoIce) {
				house.setHouseName(mtLocationInfoIce.getLocationName());
				house.setStatus(mtLocationInfoIce.getStatus());
			}
			houseList.add(house);
		}
		return houseList;
	}
	
	/**
	 * @discription 查询执行人（人员List，带状态）
	 * @author yangyh@segimail.com
	 * @created 2018年3月27日 下午3:06:32
	 * @param taskLoopId
	 * @return
	 */
	@Override
	public List<User> queryUserInfoList(Integer taskLoopId) {
		List<Integer> userIdList = mtTaskLoopInfoMapper.selectUserIdList(taskLoopId);
		UserInfoV2[] userInfoArr = MtIbatchQueryServiceUtils.queryUserListByUserIds(AppUtils.listToString(userIdList, ','));
		// 查出所有人信息
		List<User> userList = new ArrayList<User>();
		if(userInfoArr!=null && userInfoArr.length>0){
			for(UserInfoV2 userInfoV2 : userInfoArr){
				if(userInfoV2!=null){
					User user =new User();
					user.setUserId(String.valueOf(userInfoV2.getUserId()));
					user.setUserName(userInfoV2.getUserName());
					user.setUserStatus(String.valueOf(userInfoV2.getStatus()));
					user.setUserNo(userInfoV2.getJobNumber());
					userList.add(user);
				}
			}
		}
		return userList;
	}

	@Override
	public List<MtTaskLoop> queryTaskLoopInstList(Integer organId,
			String status, String loopType) {
		return mtTaskLoopInfoMapper.queryTaskLoopInstList(organId, status,
				loopType);
	}

	@Override
	public List<Integer> selectLocationIdList(Integer taskLoopId) {
		return mtTaskLoopInfoMapper.selectLocationIdList(taskLoopId);
	}

	@Override
	public List<Integer> selectUserIdList(Integer taskLoopId) {
		return mtTaskLoopInfoMapper.selectUserIdList(taskLoopId);
	}
	
	/**
	 * 根据位置id查询循环任务
	 * @param locationId
	 * @return
	 */
	@Override
	public List<MtTaskLoop> findOnWayTaskLoopList(String locationId){
		List<MtTaskLoop> MtTaskLoopList = this.mtTaskLoopInfoMapper.findOnWayTaskLoopList(Integer.valueOf(locationId));
		return MtTaskLoopList;
	}
}
