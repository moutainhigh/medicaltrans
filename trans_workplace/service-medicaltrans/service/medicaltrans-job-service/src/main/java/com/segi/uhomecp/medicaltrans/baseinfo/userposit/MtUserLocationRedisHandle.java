package com.segi.uhomecp.medicaltrans.baseinfo.userposit;

import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

@JobHandler(value = "MtUserLocationRedisHandleJob")
@Component
public class MtUserLocationRedisHandle extends IJobHandler {
	
	/**
	 * 每晚1：00定时删除所有人员位置
	 */
	@Override
	public ReturnT<String> execute(String arg0) throws Exception {
		try {
			MtCommonServiceUtils.refreshRedisUserLocaiton();
		} catch (Exception e) {
			XxlJobLogger.log("refreshRedisUserLocaiton Exception", e);
		}
		return SUCCESS;
	}

}
