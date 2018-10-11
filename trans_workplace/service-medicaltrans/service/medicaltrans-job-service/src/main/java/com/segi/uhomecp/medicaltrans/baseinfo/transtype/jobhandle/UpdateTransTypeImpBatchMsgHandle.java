package com.segi.uhomecp.medicaltrans.baseinfo.transtype.jobhandle;

import org.springframework.stereotype.Component;

import resp.RpcRespIce;

import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.rpc.RpcError;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

@JobHandler(value = "updateTransTypeImpBatchMsg")
@Component
public class UpdateTransTypeImpBatchMsgHandle extends IJobHandler {
	
	@Override
	public ReturnT<String> execute(String arg0) throws Exception {
		ReturnT<String> resp = new ReturnT<String>();
		resp.setCode(ReturnT.SUCCESS_CODE);
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		XxlJobLogger.log("***************更新批次表记录状态为失效job开始***************");
		rsp = MtCommonServiceUtils.updateTransTypeImpBatchMsg();
		if (!RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
			resp.setMsg(rsp.getMessage());
			XxlJobLogger.log("***************job执行失败***************");
			return resp;
		}
		XxlJobLogger.log("***************更新批次表记录状态为失效job执行完成***************");
		return resp;
	}	
}

