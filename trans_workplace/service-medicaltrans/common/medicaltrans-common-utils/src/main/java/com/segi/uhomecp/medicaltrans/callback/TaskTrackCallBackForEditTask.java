package com.segi.uhomecp.medicaltrans.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import resp.RpcRespIce;
import segi.medicaltrans.mttask.track.Callback_MtTaskTrackServiceIce_updateTrackForEditTask;
import Ice.LocalException;

import com.segi.uhomecp.rpc.RpcError;

public class TaskTrackCallBackForEditTask
			extends Callback_MtTaskTrackServiceIce_updateTrackForEditTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskTrackCallBackForEditTask.class);

	@Override
	public void response(RpcRespIce arg) {
		if(RpcError.FAIL.getCode().equals(arg.getCode())) {
			LOGGER.error("调用ICE服务异常",arg.getMessage());
		}
	}

	@Override
	public void exception(LocalException __ex) {
		LOGGER.error("调用ICE服务异常",__ex.getMessage());
	}
		
}