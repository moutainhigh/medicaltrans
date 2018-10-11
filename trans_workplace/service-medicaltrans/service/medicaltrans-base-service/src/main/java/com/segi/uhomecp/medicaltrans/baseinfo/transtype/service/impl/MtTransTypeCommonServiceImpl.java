package com.segi.uhomecp.medicaltrans.baseinfo.transtype.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.medicaltrans.base.transtype.model.MtTransType;
import com.segi.uhomecp.medicaltrans.base.transtype.model.MtTransTypeCriteria;
import com.segi.uhomecp.medicaltrans.base.transtype.service.MtTransTypeService;
import com.segi.uhomecp.medicaltrans.baseinfo.transtype.service.MtTransTypeCommonService;

@Service
public class MtTransTypeCommonServiceImpl implements MtTransTypeCommonService {

	@Autowired
	public MtTransTypeService mtTransTypeService;
	
	/**
	 * @discription 根据运送类型Id List查询运送类型
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月8日 下午7:55:51      
	 * @param transTypeIdList
	 * @return     
	 */
	@Override
	public List<MtTransType> getTransTypeInfoByTransTypeIdList(
			List<Integer> transTypeIdList) {
		MtTransTypeCriteria example = new MtTransTypeCriteria();
		MtTransTypeCriteria.Criteria criteria = example.createCriteria();
		criteria.andTransTypeIdIn(transTypeIdList);
		return mtTransTypeService.selectByExample(example);
	}
}
