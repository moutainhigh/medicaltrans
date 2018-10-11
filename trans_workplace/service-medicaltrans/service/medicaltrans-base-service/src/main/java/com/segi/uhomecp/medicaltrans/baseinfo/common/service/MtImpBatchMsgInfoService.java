package com.segi.uhomecp.medicaltrans.baseinfo.common.service;

import java.util.Date;
import java.util.List;

import com.segi.uhomecp.medicaltrans.base.common.model.MtImpBatchMsg;

public interface MtImpBatchMsgInfoService {
	
	/**
	 * @discription 修改导入批次表的状态
	 * @author zhangyang@segimail.com       
	 * @created 2018年10月9日 上午9:28:06     
	 * @param date
	 * @param status
	 */
	void updateImpBatchMsgInfoStatus(Date date, String status);
	
	/**
	 * @discription 通过batchId删除批次表信息
	 * @author zhangyang@segimail.com       
	 * @created 2018年10月9日 上午9:26:48     
	 * @param batchIds
	 */
	void deleteImpBatchMsgInfo(List<Integer> batchIds);
	
	/**
	 * @discription 查询导入批次表
	 * @author zhangyang@segimail.com       
	 * @created 2018年10月9日 上午9:26:54     
	 * @param queryDate
	 * @return
	 */
	List<MtImpBatchMsg> queryImpBatchMsgInfo(Date queryDate);
	
	/**
	 * @discription 删除导入批次表和中间表
	 * @author zhangyang@segimail.com       
	 * @created 2018年10月9日 上午9:27:00     
	 * @param date
	 */
	void deleteImpTableInfo(Date date);
}
