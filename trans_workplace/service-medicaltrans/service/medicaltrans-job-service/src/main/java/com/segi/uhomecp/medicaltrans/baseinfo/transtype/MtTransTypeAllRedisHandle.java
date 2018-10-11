package com.segi.uhomecp.medicaltrans.baseinfo.transtype;

import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

@JobHandler(value = "transTypeRedisAllJobHandler")
@Component
public class MtTransTypeAllRedisHandle extends IJobHandler {

	@Override
	public ReturnT<String> execute(String arg0) throws Exception {
		try {
			MtCommonServiceUtils.refreshRedisTransType(arg0);
		} catch (Exception e) {
			XxlJobLogger.log("refreshRedisLocaiton Exception", e);
		}
		return SUCCESS;
	}

}
