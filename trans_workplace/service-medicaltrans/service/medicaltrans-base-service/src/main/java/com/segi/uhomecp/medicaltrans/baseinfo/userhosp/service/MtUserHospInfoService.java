package com.segi.uhomecp.medicaltrans.baseinfo.userhosp.service;


import java.util.List;

import com.segi.uhomecp.medicaltrans.base.userhosprel.model.MtUserHospRel;
import com.segi.uhomecp.medicaltrans.baseinfo.userhosp.dto.MtUserHospInfoDto;
import com.segi.uhomecp.medicaltrans.baseinfo.userhosp.dto.MtUserHospParamDto;

public interface MtUserHospInfoService {

	/**
	 * 类描述: 保存用户科室信息
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月10日 下午3:08:44
	 */
	public void saveUserHospInfo(MtUserHospParamDto mtUserHospParamDto);

	/**
	 * @discription 查询科室用户关系表科室信息
	 * @author yangyh@segimail.com       
	 * @created 2018年5月10日 下午4:56:28     
	 * @param mtUserHospRel
	 */
	public MtUserHospInfoDto queryHouseInfo(MtUserHospRel mtUserHospRel);
	
	/**
	 * @Title: queryHouseInfoList   
	 *  查询科室用户关系表科室信息列表
	 * @author zhaoqing  
	 * @param dto
	 * @return 
	 */
	public List<MtUserHospInfoDto> queryHouseInfoList(MtUserHospInfoDto dto);
	
	/**
	 * @discription 科室用户科室项目切换
	 * @author yangyh@segimail.com       
	 * @created 2018年5月10日 下午4:56:59     
	 * @param mtUserHospRel
	 */
	public String updateUserHospRel(MtUserHospRel mtUserHospRel);
	
	/**
	 * @discription 保存单个关系信息
	 * @author wangxiong@segimail.com       
	 * @created 2018年6月4日 下午7:26:38     
	 * @param rel
	 */
	public void saveBatchMtUserHospRel(MtUserHospRel rel, Integer updateBy);
	
	/**
	 * @discription 批量保存关系信息
	 * @author wangxiong@segimail.com       
	 * @created 2018年6月4日 下午7:26:42     
	 * @param list
	 */
	public void saveBatchMtUserHospRel(List<MtUserHospRel> list, Integer updateBy, Integer userId);
	
	/**
	 * @discription 通过科室ID查询科室人员
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月17日 下午4:59:14     
	 * @param houseId
	 * @return
	 */
	public List<MtUserHospRel> queryUserHospInfoByHouseId(Integer houseId);

}
