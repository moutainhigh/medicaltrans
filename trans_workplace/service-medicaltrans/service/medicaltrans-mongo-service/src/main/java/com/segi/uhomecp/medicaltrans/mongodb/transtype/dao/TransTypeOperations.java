package com.segi.uhomecp.medicaltrans.mongodb.transtype.dao;

import com.segi.uhomecp.medicaltrans.mongodb.transtype.entity.TransType;

/**
 * Title: TransTypeOperations.java    
 * @Description: 运输类型表处理类
 * @author yangyh@segimail.com       
 * @created 2018年9月27日 下午6:24:16
 */
public interface TransTypeOperations {

	/**
	 * @discription 新增运送类型
	 * @author yangyh@segimail.com
	 * @created 2018年9月29日 下午3:25:00
	 * @param transType
	 */
	public void saveTransType(TransType transType);

	/**
	 * @discription 修改运送类型
	 * @author yangyh@segimail.com
	 * @created 2018年9月29日 下午3:26:55
	 * @param id
	 * @param transType
	 */
	public void updateTransTypeById(Integer id, TransType transType);

	/**
	 * @discription 根据id查询运送类型
	 * @author yangyh@segimail.com
	 * @created 2018年9月29日 下午3:27:15
	 * @param id
	 * @return
	 */
	public TransType findTransTypeById(Integer id);

	/**
	 * @discription 根据id删除
	 * @author yangyh@segimail.com
	 * @created 2018年9月29日 下午5:50:07
	 * @param id
	 */
	public void deleteTransTypeById(Integer id);
}
