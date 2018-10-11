package com.segi.uhomecp.medicaltrans.baseinfo.transtype.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.medicaltrans.base.transtype.model.MtTransTypeImpMidCriteria;
import com.segi.uhomecp.medicaltrans.base.transtype.service.MtTransTypeImpMidService;
import com.segi.uhomecp.medicaltrans.baseinfo.transtype.service.MtTransTypeImpMidInfoService;

@Service
public class MtTransTypeImpMidInfoServiceImpl implements MtTransTypeImpMidInfoService{
	
	@Autowired
	private MtTransTypeImpMidService mtTransTypeImpMidService;

	/**
	 * @discription 通过batchId删除导入中间表
	 * @author zhangyang@segimail.com       
	 * @created 2018年10月9日 上午9:34:39      
	 * @param batchIds     
	 */
	@Override
	public void deleteTransTypeImpMidInfo(List<Integer> batchIds) {
		MtTransTypeImpMidCriteria example = new MtTransTypeImpMidCriteria();
		MtTransTypeImpMidCriteria.Criteria criteria = example.createCriteria();
		criteria.andBatchIdIn(batchIds);
		mtTransTypeImpMidService.deleteByExample(example);
	}
	
}
