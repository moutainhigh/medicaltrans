package com.segi.uhomecp.medicaltrans.baseinfo.userhosp.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import segi.datacachesvr.queryInfo.Organ;
import segi.datacachesvr.queryInfo.UserInfo;
import segi.datacachesvr.queryInfo.UserInfoV2;

import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocation;
import com.segi.uhomecp.medicaltrans.base.userhosprel.model.MtUserHospRel;
import com.segi.uhomecp.medicaltrans.base.userhosprel.model.MtUserHospRelCriteria;
import com.segi.uhomecp.medicaltrans.base.userhosprel.model.MtUserHospRelCriteria.Criteria;
import com.segi.uhomecp.medicaltrans.base.userhosprel.service.MtUserHospRelService;
import com.segi.uhomecp.medicaltrans.baseinfo.location.service.MtBuildLocationManagerService;
import com.segi.uhomecp.medicaltrans.baseinfo.userhosp.dao.MtUserHospInfoMapper;
import com.segi.uhomecp.medicaltrans.baseinfo.userhosp.dto.MtUserHospInfoDto;
import com.segi.uhomecp.medicaltrans.baseinfo.userhosp.dto.MtUserHospParamDto;
import com.segi.uhomecp.medicaltrans.baseinfo.userhosp.service.MtUserHospInfoService;
import com.segi.uhomecp.medicaltrans.constant.MtSeqContants;
import com.segi.uhomecp.medicaltrans.utils.MtIbatchQueryServiceUtils;
import com.segi.uhomecp.utils.SeqContants;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;


@Service
public class MtUserHospInfoServiceImpl implements MtUserHospInfoService{
	 
	@Autowired
	private MtUserHospRelService mtUserHospRelService;
	
	@Autowired
	public MtUserHospInfoMapper mtUserHospInfoMapper;
	
	@Autowired
	MtBuildLocationManagerService mtBuildLocationManagerService;
	
	/** @discription 查询科室用户关系表科室信息
	 * @author yangyh@segimail.com       
	 * @created 2018年5月10日 下午4:55:56      
	 * @param mtUserHospRel
	 * @return        
	 */  
	@Override
	public MtUserHospInfoDto queryHouseInfo(MtUserHospRel mtUserHospRel) {
		MtUserHospInfoDto mtUserHospInfoDto = new MtUserHospInfoDto();
		MtUserHospRelCriteria example = new MtUserHospRelCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(mtUserHospRel.getUserId());
		criteria.andOrganIdEqualTo(mtUserHospRel.getOrganId());
		List<MtUserHospRel> list = mtUserHospRelService.selectByExample(example);
		if (AppUtils.isNotEmpty(list)) {
			// 查出houseName
			List<Integer> houseIdList = AppUtils.list2ParamsList(list, MtUserHospRel.class, "houseId");
			List<MtBuildLocation> infoList = mtBuildLocationManagerService.getLocationInfoByRefIdList(mtUserHospRel.getOrganId(),houseIdList);
			//List<MtLocationInfoIce> infoList = MtCommonServiceUtils.getLocationInfoList(mtUserHospRel.getOrganId(), houseIdList);
			if (AppUtils.isNotEmpty(infoList) && infoList.get(0) != null) {
				MtBuildLocation locationInfoIce = infoList.get(0);
				mtUserHospInfoDto.setHouseId(locationInfoIce.getLocationId() != null ? Integer.valueOf(locationInfoIce.getLocationId()) : null);
				mtUserHospInfoDto.setHouseName(StringUtils.isNotBlank(locationInfoIce.getLocationName()) ? locationInfoIce.getLocationName() : "");
			}
		}
		//根据organID查询组织信息
		Organ organ = CommonServiceUtils.getOrganByID(Integer.valueOf(mtUserHospRel.getOrganId()));
		mtUserHospInfoDto.setOrganId(mtUserHospRel.getOrganId());
		if (organ != null && StringUtils.isNotBlank(organ.getOrganName())){
			mtUserHospInfoDto.setOrganName(organ.getOrganName());
		}
		// 查询userName
		UserInfo userInfo = CommonServiceUtils.getCurrentUserInfoByID(Integer.valueOf(mtUserHospRel.getUserId()));
		mtUserHospInfoDto.setUserId(mtUserHospRel.getUserId());
		if (userInfo != null && StringUtils.isNotBlank(userInfo.getUserName())) {
			mtUserHospInfoDto.setUserName(userInfo.getUserName());
		}
		return mtUserHospInfoDto;
	}
	  
	/** @discription 科室用户科室项目切换
	 * @author yangyh@segimail.com       
	 * @created 2018年5月10日 下午4:55:56      
	 * @param mtUserHospRel
	 * @return        
	 */  
	@Override
	public String updateUserHospRel(MtUserHospRel mtUserHospRel) {
		MtUserHospRelCriteria example = new MtUserHospRelCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(mtUserHospRel.getUserId());
		criteria.andOrganIdEqualTo(mtUserHospRel.getOrganId());
		// 修改科室id
		MtUserHospRel updateUserHospRel = new MtUserHospRel();
		updateUserHospRel.setHouseId(mtUserHospRel.getHouseId());
		int updateCount = mtUserHospRelService.updateByExampleSelective(updateUserHospRel, example);
		if (updateCount < 1) {
			updateUserHospRel.setOrganId(mtUserHospRel.getOrganId());
			this.saveBatchMtUserHospRel(mtUserHospRel, mtUserHospRel.getUserId());
		}
		return "";
	}
	
	/**
	 * 类描述: 保存用户科室信息
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月10日 下午3:08:44
	 */
	@Override
	public void saveUserHospInfo(MtUserHospParamDto mtUserHospParamDto) {
		MtUserHospRelCriteria example = new MtUserHospRelCriteria();
		MtUserHospRelCriteria.Criteria criteria = example.createCriteria();
		if (mtUserHospParamDto.getUserId() != null) {
			criteria.andUserIdEqualTo(Integer.valueOf(mtUserHospParamDto.getUserId()));
		}
		// 先删除原先的用户科室信息
		mtUserHospRelService.deleteByExample(example);
		// 新增用户科室信息
		if (AppUtils.isNotEmpty(mtUserHospParamDto.getOrganList())) {
			List<MtUserHospRel> list = BeanCopierUtils.copyList2List(mtUserHospParamDto.getOrganList(), MtUserHospRel.class, true);
			this.saveBatchMtUserHospRel(list,mtUserHospParamDto.getUpdateBy(), mtUserHospParamDto.getUserId());
		}
	}
	
	@Override
	public void saveBatchMtUserHospRel(MtUserHospRel rel, Integer updateBy) {
		List<MtUserHospRel> list = new ArrayList<>();
		list.add(rel);
		this.saveBatchMtUserHospRel(list, updateBy, updateBy);
	}
	
	@Override
	public void saveBatchMtUserHospRel(List<MtUserHospRel> list, Integer updateBy, Integer userId) {
		Date nowDate = new Date();
		for (MtUserHospRel mtUserHospRel : list) {
			int intValue = SeqContants.getSequnces(MtSeqContants.MT_USER_HOSP_REL_ID_SEQ).intValue();
			mtUserHospRel.setUserHospRelId(intValue);
			mtUserHospRel.setUpdateBy(updateBy);
			mtUserHospRel.setUpdateDate(nowDate);
			mtUserHospRel.setUserId(userId);
		}
		mtUserHospInfoMapper.saveUserHospInfo(list);
	}

	/**
	 * @discription 通过科室ID查询科室人员
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月17日 下午4:58:55      
	 * @param houseId
	 * @return     
	 */
	@Override
	public List<MtUserHospRel> queryUserHospInfoByHouseId(Integer houseId) {
		MtUserHospRelCriteria example = new MtUserHospRelCriteria();
		MtUserHospRelCriteria.Criteria criteria = example.createCriteria();
		criteria.andHouseIdEqualTo(houseId);
		return mtUserHospRelService.selectByExample(example);
	}

	/**
	 * <p>Title: queryHouseInfoList</p>   
	 * <p>Description: 查询科室用户关系表科室信息</p> 
	 * <p>zhaoqing</p>
	 * @param dto
	 * @return   
	 */
	@Override
	public List<MtUserHospInfoDto> queryHouseInfoList(MtUserHospInfoDto dto) {
		MtUserHospRelCriteria example = new MtUserHospRelCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andOrganIdEqualTo(dto.getOrganId());
		if (AppUtils.isNotEmpty(dto.getUserIdList())) {
			criteria.andUserIdIn(dto.getUserIdList());
		}	
		List<MtUserHospRel> list = mtUserHospRelService.selectByExample(example);
		List<MtUserHospInfoDto> resultList = new ArrayList<>();
		if (AppUtils.isNotEmpty(list)) {
			// 数据处理
			handleHouseInfoList(list, resultList, dto);
		}
		return resultList;
	}
	
	/**
	 * @Title: handleHouseInfoList   
	 *  科室用户关系表科室信息数据处理 
	 * @author zhaoqing  
	 * @param list
	 * @param resultList
	 * @param dto 
	 */
	private void handleHouseInfoList(List<MtUserHospRel> list, 
			List<MtUserHospInfoDto> resultList, MtUserHospInfoDto dto) {
		// 获取科室Id信息
		List<Integer> houseIdList = AppUtils.list2ParamsList(list, MtUserHospRel.class, "houseId");
		// 根据科科室Id查询位置信息
		List<MtBuildLocation> localInfoList = mtBuildLocationManagerService
				.getLocationInfoByRefIdList(dto.getOrganId(), houseIdList);
		// 位置信息转换成Map集合
		Map<Integer, MtBuildLocation> localInfoMap = AppUtils.list2Map(
				localInfoList, "locationId", MtBuildLocation.class);
		// 查询用户信息
		Map<Integer, UserInfoV2> userInfoMap = MtIbatchQueryServiceUtils
				.queryUserMapByUserIds(dto.getUserIdList());
		// 根据organID查询组织信息
		Organ organ = CommonServiceUtils.getOrganByID(dto.getOrganId());
		String organName = "";
		if (null != organ) {
			organName = organ.getOrganName();
		}
		MtUserHospInfoDto mtUserHospInfoDto = null;
		UserInfoV2 userInfo = null;
		for (MtUserHospRel mtUserHospRel : list) {
			mtUserHospInfoDto = BeanCopierUtils.copyProperties(
					mtUserHospRel, MtUserHospInfoDto.class, true);
			mtUserHospInfoDto.setOrganName(organName);
			MtBuildLocation locationInfoIce = localInfoMap.get(mtUserHospRel.getHouseId());
			if (null != locationInfoIce) {
				mtUserHospInfoDto.setHouseName(locationInfoIce.getLocationName());
			}
			userInfo = userInfoMap.get(mtUserHospRel.getUserId());
			if (null != userInfo) {
				mtUserHospInfoDto.setUserName(userInfo.getUserName());
			}
			resultList.add(mtUserHospInfoDto);
		}
	}
}
