package com.segi.uhomecp.medicaltrans.baseinfo.transtype.dao;

import java.util.List;

import com.segi.uhomecp.medicaltrans.base.transtype.model.MtTransType;

/**
 * 
 * 描述: 运输类型个性化接口
 * 创建人: liuyi@sige.com   
 * 创建时间: 2018年3月22日 上午11:27:24
 */
public interface MtTransTypeInfoMapper {

	/**
	 * 
	 * 类描述: 新增判断名称和编码是否同一下项目内重复
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月22日 上午10:41:28
	 */
	public Integer judgeTransType(MtTransType mtTransType);

	/**
	 * 
	 * 类描述: 修改判断名称和编码是否同一下项目内重复
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月22日 下午5:37:57
	 */
	public Integer judgeUpdateTransType(MtTransType mtTransType);
	
	/**
	 * 
	 * @return 获得全部organId
	 */
	public List<Integer> getTransTypeOrganIdAllList();
	
	/**
	 * @Title: queryMtTransType 
	 * @Description: 查询运送类型 
	 * @author liuyi@segimail.com 
	 * @date 2018年9月29日下午8:23:31
	 */
	public MtTransType queryMtTransType(MtTransType mtTransType);
}
