package com.segi.uhomecp.medicaltrans.mongodb.transway.rpc;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import resp.RpcRespIce;
import segi.medicaltrans.report.common.transway.TransWayInfo;
import segi.medicaltrans.report.common.transway.TransWayInfoReturnIce;
import segi.medicaltrans.report.common.transway._MtCommonTransWayServiceIceDisp;
import Ice.Current;

import com.segi.uhomecp.medicaltrans.mongodb.transway.dao.TransWayOperations;
import com.segi.uhomecp.medicaltrans.mongodb.transway.entity.TransWay;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;

@Component
public class TransWayRpc extends _MtCommonTransWayServiceIceDisp {

	/**  描述   (@author: yangyh@segimail.com) */      
	private static final long serialVersionUID = -8042703703631938613L;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(TransWayRpc.class);
	
	@Resource
	private TransWayOperations transWayOperations;

	@Override
	public RpcRespIce saveTransWay(TransWayInfo transWayInfo, Current __current) {
		RpcRespIce result = new RpcRespIce(RpcError.SUCCESS.getCode(),
				RpcError.SUCCESS.getMessage(), "");
		try {
			transWayOperations.saveTransWay(BeanCopierUtils.copyProperties(transWayInfo, TransWay.class, true));
		} catch (Exception e) {
			LOGGER.error("saveTransWay", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("新增运送方式失败");
		}
		return result;
	}

	@Override
	public TransWayInfoReturnIce getTransWayById(int id, Current __current) {
		TransWayInfoReturnIce result = new TransWayInfoReturnIce(RpcError.SUCCESS.getCode(),
				RpcError.SUCCESS.getMessage(), new TransWayInfo());
		try {
			TransWay transWay = transWayOperations.findTransWayById(id);
			result.setData(BeanCopierUtils.copyProperties(transWay, TransWayInfo.class, true));
		} catch (Exception e) {
			LOGGER.error("getTransWayById", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMsg("根据id查询运送方式信息失败");
		}
		return result;
	}
	
	@Override
	public RpcRespIce updateTransWayById(int id, TransWayInfo transWayInfo, Current __current) {
		RpcRespIce result = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			transWayOperations.updateTransWayById(id,
					BeanCopierUtils.copyProperties(transWayInfo, TransWay.class, true));
		} catch (Exception e) {
			LOGGER.error("updateTransWayById", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("修改运送方式失败");
		}
		return result;
	}

	@Override
	public RpcRespIce deleteTransWayById(int id, Current __current) {
		RpcRespIce result = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			transWayOperations.deleteTransWayById(id);
		} catch (Exception e) {
			LOGGER.error("deleteTransWayById", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("删除运送方式失败");
		}
		return result;
	}
}
