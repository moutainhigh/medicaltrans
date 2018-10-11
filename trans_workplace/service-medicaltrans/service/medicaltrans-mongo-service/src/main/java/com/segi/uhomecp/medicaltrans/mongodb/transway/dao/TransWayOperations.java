package com.segi.uhomecp.medicaltrans.mongodb.transway.dao;

import com.segi.uhomecp.medicaltrans.mongodb.transway.entity.TransWay;

/**
 * Title: TransWayOperations.java
 * 
 * @Description: 运输来源表处理类
 * @author yangyh@segimail.com
 * @created 2018年9月27日 下午6:24:16
 */
public interface TransWayOperations {

	/**
	 * @discription 新增运送方式
	 * @author yangyh@segimail.com
	 * @created 2018年9月29日 下午3:25:00
	 * @param transWay
	 */
	public void saveTransWay(TransWay transWay);

	/**
	 * @discription 修改运送方式
	 * @author yangyh@segimail.com
	 * @created 2018年9月29日 下午3:26:55
	 * @param id
	 * @param transWay
	 */
	public void updateTransWayById(Integer id, TransWay transWay);

	/**
	 * @discription 根据id查询运送方式
	 * @author yangyh@segimail.com
	 * @created 2018年9月29日 下午3:27:15
	 * @param id
	 * @return
	 */
	public TransWay findTransWayById(Integer id);

	/**
	 * @discription 根据id删除
	 * @author yangyh@segimail.com
	 * @created 2018年9月29日 下午5:50:07
	 * @param id
	 */
	public void deleteTransWayById(Integer id);
}
