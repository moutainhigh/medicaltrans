package com.segi.uhomecp.medicaltrans.baseinfo.userhosp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.segi.uhomecp.medicaltrans.base.userhosprel.model.MtUserHospRel;

public interface MtUserHospInfoMapper {

	/**
	 * 
	 * 类描述: 用户科室新增
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月10日 下午5:00:29
	 */
	int saveUserHospInfo(@Param("list")List<MtUserHospRel> list) ;
}
