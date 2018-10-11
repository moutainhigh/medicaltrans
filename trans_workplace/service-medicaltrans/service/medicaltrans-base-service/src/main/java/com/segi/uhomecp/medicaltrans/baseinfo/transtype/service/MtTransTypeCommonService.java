package com.segi.uhomecp.medicaltrans.baseinfo.transtype.service;

import java.util.List;

import com.segi.uhomecp.medicaltrans.base.transtype.model.MtTransType;

public interface MtTransTypeCommonService {
	/**
	 * @discription 根据运送类型Id List查询运送类型
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月8日 下午7:55:40     
	 * @param transTypeIdList
	 * @return
	 */
	public List<MtTransType> getTransTypeInfoByTransTypeIdList(List<Integer> transTypeIdList);
}
