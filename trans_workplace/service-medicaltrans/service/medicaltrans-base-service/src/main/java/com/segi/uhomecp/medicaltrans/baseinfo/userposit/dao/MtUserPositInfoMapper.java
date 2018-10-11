package com.segi.uhomecp.medicaltrans.baseinfo.userposit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.segi.uhomecp.medicaltrans.base.userposit.model.MtUserPosit;

public interface MtUserPositInfoMapper {

	/**
	 * @discription 批量新增人员位置
	 * @author yangyh@segimail.com       
	 * @created 2018年6月19日 下午5:43:43     
	 * @param mtUserPositList
	 */
	public void saveBatchMtUserPosit(@Param("list")List<MtUserPosit> mtUserPositList);
	
	/**
	 * @discription 批量修改人员位置
	 * @author yangyh@segimail.com       
	 * @created 2018年6月19日 下午5:43:52     
	 * @param mtUserPositList
	 */
	public void updateBatchMtUserPosit(@Param("list")List<MtUserPosit> mtUserPositList);
}
