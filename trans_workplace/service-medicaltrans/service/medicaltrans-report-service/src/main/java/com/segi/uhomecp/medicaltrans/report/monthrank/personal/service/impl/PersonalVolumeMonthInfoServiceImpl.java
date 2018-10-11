package com.segi.uhomecp.medicaltrans.report.monthrank.personal.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import segi.datacachesvr.queryInfo.GroupUserBrief;
import segi.datacachesvr.queryInfo.Organ;
import segi.datacachesvr.queryInfo.UserInfoV2;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.cache.PersonalVolumeRedisCache;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.enums.TransTaskTypeEnum;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.dao.PersonalVolumeMonthHisInfoMapper;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.dao.PersonalVolumeMonthInfoMapper;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.dto.CurUserRankDto;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.dto.PersonalVolumeMonthDto;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.dto.PersonalVolumeMonthPageDto;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonth;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonthCriteria;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonthHis;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.model.PersonalVolumeMonthHisCriteria;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.service.PersonalVolumeMonthHisService;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.service.PersonalVolumeMonthInfoService;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.service.PersonalVolumeMonthService;
import com.segi.uhomecp.medicaltrans.utils.MtIbatchQueryServiceUtils;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.interfaces.InvocationHandler;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;
import com.segi.uhomecp.wh.common.utils.DataTypeConverUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;

/**
 * Title: MtPersonalVolumeMonthInfoServiceImpl.java
 * @Description: 描述
 * @author yangyh@segimail.com
 * @created 2018年5月6日 下午3:25:26
 */
@Service
public class PersonalVolumeMonthInfoServiceImpl implements PersonalVolumeMonthInfoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonalVolumeMonthInfoServiceImpl.class);

	@Autowired
	private PersonalVolumeMonthInfoMapper personalVolumeMonthInfoMapper;
	
	@Autowired
	private PersonalVolumeMonthHisInfoMapper personalVolumeMonthHisInfoMapper;
	
	@Autowired
	private PersonalVolumeMonthService personalVolumeMonthService;
	
	@Autowired
	private PersonalVolumeMonthHisService personalVolumeMonthHisService;
	
	@Autowired
	private PersonalVolumeRedisCache personalVolumeRedisCache;
	
	/**
	 * @discription 根据organId查询本月运送量排名（organId必传）
	 * @author yangyh@segimail.com
	 * @created 2018年5月7日 上午10:54:53
	 * @param PersonalVolumeMonthDto
	 * @return
	 */
	@Override
	public ResultInfo getMonthTransVolumeRank(PersonalVolumeMonthDto personalVolumeMonthDto) {
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setIsSucc(true);
		LOGGER.debug("==================>查询月排名入参: personalVolumeMonthDto:{}", 
				FastjsonUtils.toJsonString(personalVolumeMonthDto));
		int pageNo = Constant.INIT_PAGE_NO;
		if (null != personalVolumeMonthDto.getPageNo()) {
			pageNo = Integer.valueOf(personalVolumeMonthDto.getPageNo());
		}
		int pageLength = Constant.DEFAULT_PAGE_LENGTH;
		if (null != personalVolumeMonthDto.getPageLength()) {
			pageLength = Integer.valueOf(personalVolumeMonthDto.getPageLength());
		}
		// 查询人员运送量排名缓存信息
		List<PersonalVolumeMonthDto> personVolumeList = personalVolumeRedisCache
				.getPersonalVolumeAll(personalVolumeMonthDto.getOrganId());
		LOGGER.debug("================>getMonthTransVolumeRank: personVolumeList：resultList:{}", 
				FastjsonUtils.toJsonString(personVolumeList));
		if (!AppUtils.isNotEmpty(personVolumeList)) {
			resultInfo.setIsSucc(false);
			resultInfo.setMessage("该部门缓存信息为空!");
			return resultInfo;
		}
		// 当前用户排名
		CurUserRankDto curUserRankDto = new CurUserRankDto();
		// 初始化当前用户排名，返回用户Id
		Integer userId = initCurUserRankDto(curUserRankDto, personalVolumeMonthDto, personVolumeList);
		
		PersonalVolumeMonthPageDto personalVolumeMonthPageDto = new PersonalVolumeMonthPageDto();
		personalVolumeMonthPageDto.setCurUserRankDto(curUserRankDto);
		resultInfo.setObject1(personalVolumeMonthPageDto);
		resultInfo.setObject2(personVolumeList.size());
		// 分页信息
		PageList<PersonalVolumeMonthDto> pageList = new PageList<>();
		LOGGER.debug("================>getMonthTransVolumeRank: pageNo{},pageLength{}", 
				pageNo, pageLength);
		int beginIndex = (pageNo - 1) * pageLength;
		beginIndex = beginIndex < 0 ? 0 : beginIndex;
		int endIndex = beginIndex + pageLength;
		endIndex = endIndex > personVolumeList.size() ? personVolumeList.size() : endIndex;
		if (beginIndex > personVolumeList.size()) {
			resultInfo.setIsSucc(false);
			resultInfo.setMessage("返回信息为空!");
			return resultInfo;
		}
		List<PersonalVolumeMonthDto> personVolumeListPage = personVolumeList.subList(beginIndex, endIndex);
		LOGGER.debug("================>getMonthTransVolumeRank: personVolumeListPage：resultList{}", 
				FastjsonUtils.toJsonString(personVolumeListPage));
		if (!AppUtils.isNotEmpty(personVolumeListPage)) {
			resultInfo.setIsSucc(false);
			resultInfo.setMessage("返回信息为空!");
			return resultInfo;
		}
		// 取出userId
		List<Integer> userIdList = AppUtils.list2ParamsList(
				personVolumeListPage, PersonalVolumeMonthDto.class, "userId");
		userIdList.removeAll(Collections.singleton(null));
		userIdList.add(userId);
		LOGGER.debug("================>getMonthTransVolumeRank: userIdList：resultList{}", 
				FastjsonUtils.toJsonString(userIdList));
		if (!AppUtils.isNotEmpty(userIdList)) {
			resultInfo.setIsSucc(false);
			resultInfo.setMessage("用户信息为空!");
			return resultInfo;
		}
		// 人员信息
		Map<Integer, UserInfoV2> userMap = MtIbatchQueryServiceUtils.queryUserMapByUserIds(userIdList);
		// 服务组信息
		Map<Integer, GroupUserBrief> groupMap = MtIbatchQueryServiceUtils.getGroupInfoMap(userIdList);
		for (PersonalVolumeMonthDto dto : personVolumeListPage) {
			// 设置用户信息
			setUserInfo(dto, userMap, groupMap);
			pageList.add(dto);
		}
		personalVolumeMonthPageDto.setResultList(pageList);
		return resultInfo;
	}
	
	/**
	 * @Title: setUserInfo   
	 *  设置用户信息
	 * @author zhaoqing  
	 * @param dto
	 * @param userMap
	 * @param groupMap      
	 */
	private void setUserInfo(PersonalVolumeMonthDto dto,
			Map<Integer, UserInfoV2> userMap, Map<Integer, GroupUserBrief> groupMap) {
		Integer userId = dto.getUserId();
		// 姓名、工号
		UserInfoV2 userInfoV2 = userMap.get(userId);
		if (null != userInfoV2) {
			dto.setUserName(userInfoV2.getUserName());
			dto.setUserNo(userInfoV2.getJobNumber());
		}
		// 组别
		GroupUserBrief groupUserBrief = groupMap.get(userId);
		if (null != groupUserBrief) {
			dto.setTeam(groupUserBrief.getGroupName());
		}
	}
	
	/**
	 * @Title: initCurUserRankDto   
	 *  初始化当前用户排名信息 
	 * @author zhaoqing  
	 * @param curUserRankDto
	 * @param personalVolumeMonthDto
	 * @param personVolumeList
	 * @return  
	 */
	private Integer initCurUserRankDto(CurUserRankDto curUserRankDto, 
			PersonalVolumeMonthDto personalVolumeMonthDto, 
			List<PersonalVolumeMonthDto> personVolumeList) {
		Integer userId = null;
		Integer currentUserId = personalVolumeMonthDto.getUserId();
		if (AppUtils.isNotEmpty(personVolumeList)) {
			for (PersonalVolumeMonthDto dto : personVolumeList) {
				userId = dto.getUserId();
				// 当前用户排名
				if (null != currentUserId && currentUserId.equals(userId)) {
					curUserRankDto.setRank(dto.getRank());
					curUserRankDto.setUserId(dto.getUserId());
					curUserRankDto.setTransVolume(dto.getTransAmount());
				}
			}
		}	
		return userId;
	}
	
	/**
	 * <p>Title: getMonthTransVolumePage</p>   
	 * <p>Description: 个人运送量月报表分页查询</p> 
	 * <p>zhaoqing</p>
	 * @param dto
	 * @return   
	 */
	@Override
	public ResultInfo getMonthTransVolumePage(PersonalVolumeMonthDto dto) {
		ResultInfo result = new ResultInfo();
		result.setIsSucc(true);
		PersonalVolumeMonthCriteria example = new PersonalVolumeMonthCriteria();
		PersonalVolumeMonthCriteria.Criteria criteria = example.createCriteria();
		PersonalVolumeMonthHisCriteria hisExample = new PersonalVolumeMonthHisCriteria();
		PersonalVolumeMonthHisCriteria.Criteria hisCriteria = hisExample.createCriteria();
		/* 处理分页信息 */
		initPageInfo(dto);
		/* 处理其他查询条件 */
		initQueryCase(dto, criteria, hisCriteria);
		try {
			List<PersonalVolumeMonth> perVolMonList = new ArrayList<>();
			int totalCount = 0;
			// 当前月份
			String nowdateMonth = DateUtil.formatDateToStringYYMM(new Date());
			if (StringUtils.isNotEmpty(nowdateMonth) 
					&& nowdateMonth.equals(String.valueOf(dto.getCycleYm()))) {
				/* 查询当月表数据 */
				perVolMonList = getPersonalVolumeMonth(dto);
				// 查询分页数据的总条数
				totalCount = personalVolumeMonthService.countByExample(example);
			} else {
				/* 查询历史表数据 */
				perVolMonList = getPersonalVolumeMonthHis(dto);
				// 查询分页数据的总条数
				totalCount = personalVolumeMonthHisService.countByExample(hisExample);
			}		
			// 实例化分页信息
			Paginator paginator = new Paginator(dto.getPageNo(), dto.getPageLength(), totalCount);
	
			// 数据处理
			List<PersonalVolumeMonthDto> perVolMonDtoList = handlePerVolMonData(perVolMonList, result);
			if (!result.getIsSucc()) {
				return result;
			}
			// 数据分页对象
			PageList<PersonalVolumeMonthDto> pageList = new PageList<PersonalVolumeMonthDto>(
					perVolMonDtoList, paginator);
			// 结果设置到返回对象中
			result.setObject1(pageList);	
		} catch (Exception e) {
			result.setIsSucc(false);
			result.setMessage("个人运送量月报表分页查询数据失败");
			LOGGER.error("PersonalVolumeMonthInfoServiceImpl getMonthTransVolumePage", e);
		}
		return result;
	}
	
	/**
	 * @Title: initQueryCase   
	 *  初始化查询条件 
	 * @author zhaoqing  
	 * @param dto
	 * @param criteria
	 * @param hisCriteria 
	 */
	private void initQueryCase(PersonalVolumeMonthDto dto, 
			PersonalVolumeMonthCriteria.Criteria criteria,
			PersonalVolumeMonthHisCriteria.Criteria hisCriteria) {
		if (dto.getOrganId() != null) {
			// 所属组织
			criteria.andOrganIdEqualTo(dto.getOrganId());
			hisCriteria.andOrganIdEqualTo(dto.getOrganId());
		}
		// 用户Id
		setCriteriaUserId(dto, criteria, hisCriteria);
		// 服务组Id
		setSergrupId(dto, criteria, hisCriteria);
		if (dto.getCycleYm() != null) {
			// 月份
			criteria.andCycleYmEqualTo(dto.getCycleYm());
			hisCriteria.andCycleYmEqualTo(dto.getCycleYm());
		}
	}
	
	/**
	 * @Title: setSergrupId   
	 *  设置服务组Id
	 * @author zhaoqing  
	 * @param dto
	 * @param criteria
	 * @param hisCriteria
	 */
	private void setSergrupId(PersonalVolumeMonthDto dto, 
			PersonalVolumeMonthCriteria.Criteria criteria,
			PersonalVolumeMonthHisCriteria.Criteria hisCriteria) {
		List<Integer> sergroupIdList = new ArrayList<>();
		if (StringUtils.isNotEmpty(dto.getSergroupIds())) {
			sergroupIdList = AppUtils.str2Integer(dto.getSergroupIds());
		}
		if (AppUtils.isNotEmpty(sergroupIdList)) {
			// 服务组
			criteria.andSergroupIdIn(sergroupIdList);
			hisCriteria.andSergroupIdIn(sergroupIdList);
		}
	}
	
	/**
	 * @Title: setCriteriaUserId   
	 *  设置运送员Id 
	 * @author zhaoqing  
	 * @param dto
	 * @param criteria
	 * @param hisCriteria 
	 */
	private void setCriteriaUserId(PersonalVolumeMonthDto dto, 
			PersonalVolumeMonthCriteria.Criteria criteria,
			PersonalVolumeMonthHisCriteria.Criteria hisCriteria) {
		List<Integer> userIdList = new ArrayList<>();
		if (StringUtils.isNotEmpty(dto.getUserIds())) {
			userIdList = AppUtils.str2Integer(dto.getUserIds());
		}
		if (AppUtils.isNotEmpty(userIdList)) {
			// 运送员ID
			criteria.andUserIdIn(userIdList);
			hisCriteria.andUserIdIn(userIdList);
		}
	}
	
	/**
	 * @Title: initPageInfo   
	 *  处理分页信息
	 * @author zhaoqing  
	 * @param dto 
	 */
	private void initPageInfo(PersonalVolumeMonthDto dto) {
		int pageNo = Constant.INIT_PAGE_NO + 1;
		if (dto.getPageNo() != null) {
			pageNo = dto.getPageNo();
		}
		int pageLength = Constant.DEFAULT_PAGE_LENGTH;
		if (dto.getPageLength() != null) {
			pageLength = dto.getPageLength();
		}
		dto.setOffset((pageNo - 1) * pageLength);
		dto.setLimit(pageLength);
		dto.setPageLength(pageLength);
		dto.setPageNo(pageNo);
	}
	
	/**
	 * @Title: getPersonalVolumeMonthHis   
	 *  查询历史表数据  
	 * @author zhaoqing  
	 * @param dto
	 * @return 
	 */
	private List<PersonalVolumeMonth> getPersonalVolumeMonthHis(PersonalVolumeMonthDto dto) {
		List<PersonalVolumeMonth> perVolMonList = new ArrayList<>();
		List<PersonalVolumeMonthHis> perVolMonHisList = new ArrayList<>();
		// 分页查询个人运送量月报表数据的主键ID
		List<Integer> idList = personalVolumeMonthHisInfoMapper.selectIdByExamplePage(dto);
		if (AppUtils.isNotEmpty(idList)) {
			// 根据主键ID查询个人运送量月报表数据
			PersonalVolumeMonthHisCriteria perHisExample = new PersonalVolumeMonthHisCriteria();
			PersonalVolumeMonthHisCriteria.Criteria perHisCriteria = perHisExample.createCriteria();
			perHisCriteria.andIdIn(idList);
			perHisExample.setOrderByClause(MtConstant.PER_VOL_MONTH_QUERY_ORDER_BY);
			perVolMonHisList = personalVolumeMonthHisService.selectByExample(perHisExample);
			
		}
		if (AppUtils.isNotEmpty(perVolMonHisList)) {
			perVolMonList = BeanCopierUtils.copyList2List(
					perVolMonHisList, PersonalVolumeMonth.class, true);
		}
		return perVolMonList;
	}
	
	/**
	 * @Title: getPersonalVolumeMonth   
	 *  查询当月表数据
	 * @author zhaoqing  
	 * @return
	 */
	private List<PersonalVolumeMonth> getPersonalVolumeMonth(PersonalVolumeMonthDto dto) {
		List<PersonalVolumeMonth> perVolMonList = new ArrayList<>();
		// 分页查询个人运送量月报表数据的主键ID
		List<Integer> idList = personalVolumeMonthInfoMapper.selectIdByExamplePage(dto);
		if (AppUtils.isNotEmpty(idList)) {
			// 根据主键ID查询个人运送量月报表数据
			PersonalVolumeMonthCriteria perExample = new PersonalVolumeMonthCriteria();
			PersonalVolumeMonthCriteria.Criteria perCriteria = perExample.createCriteria();
			perCriteria.andIdIn(idList);
			perExample.setOrderByClause(MtConstant.PER_VOL_MONTH_QUERY_ORDER_BY);
			perVolMonList = personalVolumeMonthService.selectByExample(perExample);
		}
		return perVolMonList;
	}
	
	/**
	 * @Title: handlePerVolMonData   
	 *  处理查询的个人运送量月报表数据（设置基础信息，计算满意率和及时率等）
	 * @author zhaoqing  
	 * @param perVolMonthPage
	 * @return 
	 */
	private List<PersonalVolumeMonthDto> handlePerVolMonData(
			List<PersonalVolumeMonth> perVolMonthList, ResultInfo result) {
		List<PersonalVolumeMonthDto> perVolMonDtoList = new ArrayList<>();
		if (!AppUtils.isNotEmpty(perVolMonthList)) {
			return perVolMonDtoList;
		}
		try {
			// 获取组织机构信息
			Map<Integer, Organ> organMap = getOrganMap(perVolMonthList);
			// 获取运送员ID集合
			List<Integer> userIdList = AppUtils.list2ParamsList(
					perVolMonthList, PersonalVolumeMonth.class, "userId");
			// 获取服务服信息
			Map<Integer, GroupUserBrief> groupInfoMap = 
					MtIbatchQueryServiceUtils.getGroupInfoMap(userIdList);
			// 获取运送员用户信息
			Map<Integer, UserInfoV2> userInfoMap = MtIbatchQueryServiceUtils
					.queryUserMapByUserIds(userIdList);
			for (PersonalVolumeMonth perVolMonth : perVolMonthList) {
				// 获取转换后的对象
				PersonalVolumeMonthDto perVolMonthDto = initPerVolMonthDto(
						perVolMonth, organMap, userInfoMap, groupInfoMap);
				try {
					// 计算个人运送量月报表数据的满意率和及时率
					countPerVolMonRatioData(perVolMonth, perVolMonthDto);
				} catch (Exception e) {
					result.setIsSucc(false);
					result.setMessage("个人运送量月报表分页查询计算满意率和及时率异常");
					LOGGER.error("PersonalVolumeMonthInfoServiceImpl countPerVolMonRatioData", e);
				}
				perVolMonDtoList.add(perVolMonthDto);
			}
		} catch (Exception e) {
			result.setIsSucc(false);
			result.setMessage("个人运送量月报表分页查询处理查询数据失败");
			LOGGER.error("PersonalVolumeMonthInfoServiceImpl handlePerVolMonData", e);
		}
		return perVolMonDtoList;
	}
	
	/**
	 * @Title: initPerVolMonthDto   
	 *  初始化返回对象 
	 * @author zhaoqing  
	 * @param perVolMonth
	 * @param organMap
	 * @param userInfoMap
	 * @param groupInfoMap
	 * @return 
	 */
	private PersonalVolumeMonthDto initPerVolMonthDto(PersonalVolumeMonth perVolMonth, 
			Map<Integer, Organ> organMap, Map<Integer, UserInfoV2> userInfoMap, 
			Map<Integer, GroupUserBrief> groupInfoMap) {
		PersonalVolumeMonthDto perVolMonthDto = BeanCopierUtils.copyProperties(
				perVolMonth, PersonalVolumeMonthDto.class, true);
		// 组织信息
		Organ organ = organMap.get(perVolMonth.getOrganId());
		// 设置组织名称
		perVolMonthDto.setOrganName(organ == null ? "" : organ.getOrganName());
		// 运送员的员工信息
		UserInfoV2 userInfo = userInfoMap.get(perVolMonth.getUserId());
		if (userInfo != null) {
			// 设置运送员员工信息
			perVolMonthDto.setUserName(userInfo.getUserName());
			perVolMonthDto.setUserNo(userInfo.getJobNumber());
		}
		// 服务组信息
		GroupUserBrief groupInfo = groupInfoMap.get(perVolMonth.getUserId());
		// 设置服务组名称
		perVolMonthDto.setSergroupName(groupInfo == null ? "" : groupInfo.getGroupName());
		
		return perVolMonthDto;
	}
	
	/**
	 * @Title: getOrganMap   
	 *  获取组织信息 
	 * @author zhaoqing  
	 * @param perVolMonthList
	 * @return 
	 */
	private Map<Integer, Organ> getOrganMap(List<PersonalVolumeMonth> perVolMonthList) {
		return CommonServiceUtils.getOrganInfoMap(perVolMonthList,
				new InvocationHandler<Integer, PersonalVolumeMonth>() {
					@Override
					public Integer invoke(PersonalVolumeMonth obj) {
						return obj.getOrganId();
					}
				});
	}
	
	/**
	 * @Title: countPerVolMonRatioData   
	 *  计算个人运送量月报表数据的满意率和及时率 
	 * @author zhaoqing  
	 * @param perVolMonth  
	 */
	private void countPerVolMonRatioData(PersonalVolumeMonth perVolMonth, 
			PersonalVolumeMonthDto perVolMonthDto) {
		/* 计算调度任务的满意率和及时率  */
		Integer dispatchAmount = perVolMonth.getDispatchAmount();
		Integer dispatchSatisfactionAmount = perVolMonth.getDispatchSatisfactionAmount();
		Integer dispatchTimelyAmount = perVolMonth.getDispatchTimelyAmount();
		// 满意率
		perVolMonthDto.setDispatchSatisfactionRatio(
				countDataRatio(dispatchAmount, dispatchSatisfactionAmount));
		// 及时率
		perVolMonthDto.setDispatchTimelyRatio(
				countDataRatio(dispatchAmount, dispatchTimelyAmount));
		// 运送量为空时设置为0
		perVolMonthDto.setDispatchAmount(dispatchAmount == null ? 0 : dispatchAmount);

		/* 计算自主任务的满意率和及时率  */
		Integer autonomousAmount = perVolMonth.getAutonomousAmount();
		Integer autonomousSatisfactionAmount = perVolMonth.getAutonomousSatisfactionAmount();
		Integer autonomousTimelyAmount = perVolMonth.getAutonomousTimelyAmount();
		// 满意率
		perVolMonthDto.setAutonomousSatisfactionRatio(
				countDataRatio(autonomousAmount, autonomousSatisfactionAmount));
		// 及时率
		perVolMonthDto.setAutonomousTimelyRatio(
				countDataRatio(autonomousAmount, autonomousTimelyAmount));
		// 运送量为空时设置为0
		perVolMonthDto.setAutonomousAmount(autonomousAmount == null ? 0 : autonomousAmount);

		/* 计算固定任务的满意率和及时率  */
		Integer fixedAmount = perVolMonth.getFixedAmount();
		Integer fixedSatisfactionAmount = perVolMonth.getFixedSatisfactionAmount();
		Integer fixedTimelyAmount = perVolMonth.getFixedTimelyAmount(); 
		// 满意率
		perVolMonthDto.setFixedSatisfactionRatio(
				countDataRatio(fixedAmount, fixedSatisfactionAmount));
		// 及时率
		perVolMonthDto.setFixedTimelyRatio(
				countDataRatio(fixedAmount, fixedTimelyAmount));
		// 运送量为空时设置为0
		perVolMonthDto.setFixedAmount(fixedAmount == null ? 0 : fixedAmount);
	}
	
	/**
	 * @Title: countDataRatio   
	 *  数据占比计算，保留百分号后两位 
	 * @author zhaoqing  
	 * @param tatalCount 总数
	 * @param count 算占比的数量
	 * @return
	 */
	private String countDataRatio(Integer tatalCount, Integer count) {
		String dataRatio = "0.00%";
		if (tatalCount != null && count != null && tatalCount > 0) {
			double doubleRatio = (count * 100) / Double.valueOf(tatalCount);
			BigDecimal bigDecimal = new BigDecimal(doubleRatio);  
			doubleRatio = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			DecimalFormat df = new DecimalFormat("0.00");
			dataRatio = df.format(doubleRatio) + Constant.SPLIT_PER;
		} 
		return dataRatio;
	}
	
	/**
	 * <p>Title: updatePersonalVolumeMonthHis</p>   
	 * <p>Description: 根据运送员Id和月份更新运送员的历史运送量信息</p> 
	 * <p>zhaoqing</p>
	 * @param userIds
	 * @param cycleYm
	 * @param transCount
	 * @param taskType   
	 */
	@Override
	public void updatePersonalVolumeMonthHis(String userIds, 
			Integer cycleYm, Integer transCount, String taskType) {
		LOGGER.debug("=============>updatePersonalVolumeMonthHis: "
				+ "userIds:{}, cycleYm:{}, transCount:{}, taskType:{}", 
				userIds, cycleYm, transCount, taskType);
		PersonalVolumeMonthDto dto = new PersonalVolumeMonthDto();
		dto.setUserIds(userIds);
		dto.setCycleYm(cycleYm);
		// 按任务类型设置运送量数据 
		initUpdatePerVolMonthInfo(dto, transCount, taskType);
		dto.setUpdateDate(DataTypeConverUtils.formatDateToLongYYYYMMDDHHMMSS(new Date()));
		// 更新数据
		personalVolumeMonthHisInfoMapper.updatePerVolMonthHisInfo(dto);
		LOGGER.debug("=============>updatePersonalVolumeMonthHis End");
	}
	
	/**
	 * @Title: initUpdatePerVolMonthInfo   
	 *  按任务类型设置运送量数据 
	 * @author zhaoqing  
	 * @param dto
	 * @param transCount
	 * @param taskType      
	 * void      
	 */
	private void initUpdatePerVolMonthInfo(PersonalVolumeMonthDto dto, 
			Integer transCount, String taskType) {
		dto.setTaskType(taskType);
		dto.setTransAmount(transCount);
		if (TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode().equals(taskType)) {
			// 设置调度任务运送量统计数据
			dto.setDispatchAmount(transCount);
			dto.setDispatchSatisfactionAmount(transCount);
			dto.setDispatchTimelyAmount(transCount);
		}
		if (TransTaskTypeEnum.TASK_TYPE_SELF.getCode().equals(taskType)) {
			// 设置自主任务运送量统计数据
			dto.setAutonomousAmount(transCount);
			dto.setAutonomousSatisfactionAmount(transCount);
			dto.setAutonomousTimelyAmount(transCount);
		}
		if (TransTaskTypeEnum.TASK_TYPE_LOOP.getCode().equals(taskType)) {
			// 设置固定任务运送量统计数据
			dto.setFixedAmount(transCount);
			dto.setFixedSatisfactionAmount(transCount);
			dto.setFixedTimelyAmount(transCount);
		}
	}
	
}
