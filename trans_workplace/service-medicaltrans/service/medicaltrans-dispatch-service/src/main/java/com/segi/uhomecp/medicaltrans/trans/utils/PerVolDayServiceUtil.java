package com.segi.uhomecp.medicaltrans.trans.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import segi.datacachesvr.queryInfo.GroupUserBrief;
import segi.datacachesvr.queryInfo.Organ;
import segi.datacachesvr.queryInfo.TOrganInfo;
import segi.datacachesvr.queryInfo.UserInfoV2;

import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.trans.dto.PersonalVolumeDayDto;
import com.segi.uhomecp.medicaltrans.trans.service.PerVolDayService;
import com.segi.uhomecp.medicaltrans.utils.MtIbatchQueryServiceUtils;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;

/**
 * @ClassName:  PerVolDayServiceUtil   
 * @Description:个人运送量日排名Service服务业务处理工具类   
 * @author: zhaoqing
 * @date:   2018年9月17日 下午2:10:59
 */
@Component(value = "perVolDayServiceUtil")
public class PerVolDayServiceUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PerVolDayServiceUtil.class);
	
	@Autowired
	private PerVolDayService perVolDayService;
	
	/**
	 * @Title: getDayTransVolRank   
	 *  个人运送量日排名查询 
	 * @author zhaoqing  
	 * @param paramsDto
	 * @return
	 */
	public ResultInfo getDayTransVolRank(PersonalVolumeDayDto paramsDto) {
		ResultInfo result = new ResultInfo();
		result.setIsSucc(true);
		try {
			int groupOrganId = getGroupOrganId(paramsDto.getOrganId());
			paramsDto.setGroupOrganId(groupOrganId);
			if (groupOrganId == 0) {
				return result;
			}
			// 查询个人运送量日排名数据
			List<PersonalVolumeDayDto> perVolDayDtoList = perVolDayService
					.getDayTransVolRank(groupOrganId, paramsDto);
			if (!AppUtils.isNotEmpty(perVolDayDtoList)) {
				return result;
			}
			// 数据处理
			handlePerVolDayDtoList(paramsDto.getOrganId(), groupOrganId, perVolDayDtoList);
			// 根据服务组Id过滤排名信息
			perVolDayDtoList = filterRankListByGroupId(
					paramsDto.getSergroupIds(), perVolDayDtoList);
			// 设置排名信息
			setRankInfo(perVolDayDtoList);
			result.setObjList(perVolDayDtoList);
		} catch (Exception e) {
			result.setIsSucc(false);
			result.setMessage("个人运送量日排名查询失败！");
			LOGGER.error("个人运送量日排名查询失败！", e);
		}
		return result;
	}
	
	/**
	 * @Title: setRankInfo   
	 *  设置排名信息 
	 * @author zhaoqing  
	 * @param perVolDayDtoList  
	 */
	private void setRankInfo(List<PersonalVolumeDayDto> perVolDayDtoList) {
		if (AppUtils.isNotEmpty(perVolDayDtoList)) {
			for (PersonalVolumeDayDto dto : perVolDayDtoList) {
				int index = perVolDayDtoList.indexOf(dto);
				dto.setRank(index + 1);
			}
		}
	}
	
	/**
	 * @Title: filterRankListByGroupId   
	 *  根据服务组Id过滤排名信息
	 * @author zhaoqing  
	 * @param sergroupId
	 * @param perVolDayDtoList 
	 */
	private List<PersonalVolumeDayDto> filterRankListByGroupId(String sergroupIds, 
			List<PersonalVolumeDayDto> perVolDayDtoList) {
		if (StringUtils.isEmpty(sergroupIds)) {
			return perVolDayDtoList;
		}
		List<PersonalVolumeDayDto> resultList = new ArrayList<>();
		// 获取服务组Id集合
		List<Integer> groupIdList = AppUtils.str2Integer(sergroupIds);
		// 排名信息按服务组分组
		Map<Integer, List<PersonalVolumeDayDto>> rankGroupListMap = AppUtils.listGroup2Map(
				perVolDayDtoList, PersonalVolumeDayDto.class, "sergroupId", PersonalVolumeDayDto.class);
		for (Integer sergroupId : groupIdList) {
			List<PersonalVolumeDayDto> list = rankGroupListMap.get(sergroupId);
			if (AppUtils.isNotEmpty(list)) {
				resultList.addAll(list);
			}
		}
		return resultList;
	}
	
	/**
	 * @Title: handlePerVolDayDtoList   
	 *  数据处理
	 * @author zhaoqing  
	 * @param organId
	 * @param perVolDayDtoList 
	 */
	private void handlePerVolDayDtoList(Integer organId, int groupOrganId,  
			List<PersonalVolumeDayDto> perVolDayDtoList) {
		// 取出userId
		List<Integer> userIdList = AppUtils.list2ParamsList(perVolDayDtoList,
				PersonalVolumeDayDto.class, "userId");
		// 查询人员信息
		Map<Integer, UserInfoV2> userMap = MtIbatchQueryServiceUtils
				.queryUserMapByUserIds(userIdList);
		// 查询服务组信息
		Map<Integer, GroupUserBrief> groupMap = MtIbatchQueryServiceUtils
				.getGroupInfoMap(userIdList);
		// 查询组织机构Id
		Organ organ = CommonServiceUtils.getOrganByID(organId);
		for (PersonalVolumeDayDto dto : perVolDayDtoList) {
			Integer userId = dto.getUserId();
			dto.setOrganName(null == organ ? "" : organ.getOrganName());
			UserInfoV2 userInfo = userMap.get(userId);
			if (null != userInfo) {
				dto.setUserName(userInfo.getUserName());
				dto.setUserNo(userInfo.getJobNumber());
			}
			GroupUserBrief groupInfo = groupMap.get(userId);
			if (null != groupInfo) {
				dto.setSergroupId(groupInfo.getGroupId());
				dto.setSergroupName(groupInfo.getGroupName());
			}
			dto.setGroupOrganId(groupOrganId);
		}
	}
	
	/**
	 * @Title: getGroupOrganId   
	 *  获取一级物业Id 
	 * @author zhaoqing  
	 * @param organId
	 * @return  
	 */
	private int getGroupOrganId(Integer organId) {
		TOrganInfo tOrganInfo = CommonServiceUtils.getTopOrganByOrganID(organId);
		int groupOrganId = 0;
		if (null != tOrganInfo) {
			groupOrganId = tOrganInfo.getOrganId();
		}
		return groupOrganId;
	}
	
}
