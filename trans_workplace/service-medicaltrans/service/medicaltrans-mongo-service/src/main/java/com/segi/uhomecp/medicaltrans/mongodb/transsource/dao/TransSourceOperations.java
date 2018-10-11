package com.segi.uhomecp.medicaltrans.mongodb.transsource.dao;

import com.segi.uhomecp.medicaltrans.mongodb.transsource.entity.TransSource;

/**
 * Title: TransSourceOperations.java
 * @Description: 运输来源表处理类
 * @author yangyh@segimail.com
 * @created 2018年9月27日 下午6:24:16
 */
public interface TransSourceOperations {

	/**
	 * @discription 新增运输来源
	 * @author yangyh@segimail.com
	 * @created 2018年9月29日 下午3:25:00
	 * @param transWay
	 */
	public void saveTransSource(TransSource transSource);

	/**
	 * @discription 修改运输来源
	 * @author yangyh@segimail.com
	 * @created 2018年9月29日 下午3:26:55
	 * @param id
	 * @param param
	 * @param value
	 */
	public void updateTransSourceById(Integer id, TransSource transSource);

	/**
	 * @discription 根据id查询运输来源
	 * @author yangyh@segimail.com
	 * @created 2018年9月29日 下午3:27:15
	 * @param id
	 * @return
	 */
	public TransSource findTransSourceById(Integer id);

	/**
	 * @discription 根据id删除
	 * @author yangyh@segimail.com
	 * @created 2018年9月29日 下午5:50:07
	 * @param id
	 */
	public void deleteTransSourceById(Integer id);
}
