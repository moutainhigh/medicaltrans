package com.segi.uhomecp.medicaltrans.trans.inf;

import java.util.Date;
import java.util.List;

import com.segi.uhomecp.exception.BusinessException;

/**
 * 运输单相关接口
 *     
 * 包: com.segi.uhomecp.medicaltrans.inf    
 * 类名称: TransportBillI.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 2018年3月20日 下午7:33:18
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
public interface TransportBillI<V> {
	
	/**
	 * 创建
	 * @param T
	 * @throws BusinessException
	 */
	void create(List<V> t, String exeUserIds, Date nowDate) throws BusinessException;  
	
	/**
	 * 接单
	 * @param T
	 * @throws BusinessException
	 */
	void take(V t, V beforeTask, String exeUserIds, Date nowDate) throws BusinessException;
	
	/**
	 * 取消
	 * @param transportId
	 * @throws BusinessException
	 */
	void cancel(V t, Date nowDate) throws BusinessException;
	
	/**
	 * @discription 抢单
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月11日 上午10:09:48     
	 * @param t
	 * @throws BusinessException
	 */
	void grab(V t, String exeEndUserId, Date nowDate) throws BusinessException;
	
	/**
	 * @discription 修改任务单时 修改了任务来源或者紧急程度推送mq
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月14日 下午3:21:49     
	 * @param t
	 * @param beforeTask
	 * @param nowDate
	 * @throws BusinessException
	 */
	void editSourceHouce(V t, V beforeTask, Date nowDate) throws BusinessException;
	
}
