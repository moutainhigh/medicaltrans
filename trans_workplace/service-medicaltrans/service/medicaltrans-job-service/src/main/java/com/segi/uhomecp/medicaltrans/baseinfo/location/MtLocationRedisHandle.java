package com.segi.uhomecp.medicaltrans.baseinfo.location;

import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

@JobHandler(value = "MtLocationRedisHandleJob")
@Component
public class MtLocationRedisHandle extends IJobHandler {

	/**
	 * 每晚12：20定时刷新位置的缓存（从数据库取最新数据刷新到缓存）
	 */
	@Override
	public ReturnT<String> execute(String arg0) throws Exception {
		try {
			MtCommonServiceUtils.refreshRedisLocaiton();
		} catch (Exception e) {
			XxlJobLogger.log("refreshRedisLocaiton Exception", e);
		}
		return SUCCESS;
	}

}
