package com.segi.uhomecp.medicaltrans.baseinfo.common.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.medicaltrans.base.common.model.MtImpBatchMsg;
import com.segi.uhomecp.medicaltrans.base.common.model.MtImpBatchMsgCriteria;
import com.segi.uhomecp.medicaltrans.base.common.service.MtImpBatchMsgService;
import com.segi.uhomecp.medicaltrans.baseinfo.common.service.MtImpBatchMsgInfoService;
import com.segi.uhomecp.medicaltrans.baseinfo.transtype.service.MtTransTypeImpMidInfoService;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.xxl.job.core.log.XxlJobLogger;

@Service
public class MtImpBatchMsgInfoServiceImpl implements MtImpBatchMsgInfoService{
	
	@Autowired
	public MtImpBatchMsgService mtImpBatchMsgService;
	
	@Autowired
	public MtTransTypeImpMidInfoService mtTransTypeImpMidInfoService;

	/**
	 * @discription 修改导入批次表的状态
	 * @author zhangyang@segimail.com       
	 * @created 2018年10月9日 上午9:49:49      
	 * @param date
	 * @param status     
	 */
	@Override
	public void updateImpBatchMsgInfoStatus(Date date, String status) {
		MtImpBatchMsgCriteria example = new MtImpBatchMsgCriteria();
		MtImpBatchMsgCriteria.Criteria criteria = example.createCriteria();
		criteria.andCreateDateLessThan(date);
		List<String> statusList = new ArrayList<>();
		statusList.add("0");
		statusList.add("1");
		statusList.add("2");
		criteria.andStatusCdIn(statusList);
		
		MtImpBatchMsg mtImpBatchMsg = new MtImpBatchMsg();
		mtImpBatchMsg.setStatusCd(status);
		mtImpBatchMsgService.updateByExample(mtImpBatchMsg, example);
	}

	/**
	 * @discription 通过batchId删除批次表信息
	 * @author zhangyang@segimail.com       
	 * @created 2018年10月9日 上午9:50:27      
	 * @param batchIds     
	 */
	@Override
	public void deleteImpBatchMsgInfo(List<Integer> batchIds) {
		MtImpBatchMsgCriteria example = new MtImpBatchMsgCriteria();
		MtImpBatchMsgCriteria.Criteria criteria = example.createCriteria();
		
		criteria.andBatchIdIn(batchIds);
		mtImpBatchMsgService.deleteByExample(example);
	}

	/**
	 * @discription 查询导入批次表
	 * @author zhangyang@segimail.com       
	 * @created 2018年10月9日 上午9:50:43      
	 * @param queryDate
	 * @return     
	 */
	@Override
	public List<MtImpBatchMsg> queryImpBatchMsgInfo(Date queryDate) {
		MtImpBatchMsgCriteria example = new MtImpBatchMsgCriteria();
		MtImpBatchMsgCriteria.Criteria criteria = example.createCriteria();
		criteria.andCreateDateLessThan(queryDate);
		return mtImpBatchMsgService.selectByExample(example);
	}

	/**
	 * @discription 删除导入批次表和中间表
	 * @author zhangyang@segimail.com       
	 * @created 2018年10月9日 上午9:51:13      
	 * @param date     
	 */
	@Override
	public void deleteImpTableInfo(Date date) {
		List<MtImpBatchMsg> impBatchMsgs = this.queryImpBatchMsgInfo(date);
		if (AppUtils.isNotEmpty(impBatchMsgs)) {
			List<Integer> batchIds = AppUtils.list2ParamsList(impBatchMsgs, (obj) -> obj.getBatchId());
			XxlJobLogger.log("***************有" + batchIds.size() + "个批次需要删除***************");
			this.deleteImpBatchMsgInfo(batchIds);
			mtTransTypeImpMidInfoService.deleteTransTypeImpMidInfo(batchIds);
		}
	}
}
