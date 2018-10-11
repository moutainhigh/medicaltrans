package com.segi.uhomecp.medicaltrans.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.rpc.RpcError;

import Ice.LocalException;
import resp.RpcRespIce;
import segi.medicaltrans.common.userposit.Callback_MtUserPositCommonServiceIce_updateUserPositInfo;

public class UserPositIceCallBackUpdateUserPosit 
			extends Callback_MtUserPositCommonServiceIce_updateUserPositInfo {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserPositIceCallBackUpdateUserPosit.class);

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