package com.segi.uhomecp.medicaltrans.mongodb.transsource.rpc;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import resp.RpcRespIce;
import segi.medicaltrans.report.common.transsource.TransSourceInfo;
import segi.medicaltrans.report.common.transsource.TransSourceInfoReturnIce;
import segi.medicaltrans.report.common.transsource._MtCommonTransSourceServiceIceDisp;
import Ice.Current;

import com.segi.uhomecp.medicaltrans.mongodb.transsource.dao.TransSourceOperations;
import com.segi.uhomecp.medicaltrans.mongodb.transsource.entity.TransSource;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;

@Component
public class TransSourceRpc extends _MtCommonTransSourceServiceIceDisp {

	/**  描述   (@author: yangyh@segimail.com) */      
	private static final long serialVersionUID = -8042703703631938613L;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(TransSourceRpc.class);
	
	@Resource
	private TransSourceOperations transSourceOperations;

	@Override
	public RpcRespIce saveTransSource(TransSourceInfo transSourceInfo, Current __current) {
		RpcRespIce result = new RpcRespIce(RpcError.SUCCESS.getCode(),
				RpcError.SUCCESS.getMessage(), "");
		try {
			transSourceOperations.saveTransSource(BeanCopierUtils.copyProperties(transSourceInfo, TransSource.class, true));
		} catch (Exception e) {
			LOGGER.error("saveTransSource", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("新增运送来源失败");
		}
		return result;
	}

	@Override
	public TransSourceInfoReturnIce getTransSourceById(int id, Current __current) {
		TransSourceInfoReturnIce result = new TransSourceInfoReturnIce(RpcError.SUCCESS.getCode(),
				RpcError.SUCCESS.getMessage(), new TransSourceInfo());
		try {
			TransSource transSource = transSourceOperations.findTransSourceById(id);
			result.setData(BeanCopierUtils.copyProperties(transSource, TransSourceInfo.class, true));
		} catch (Exception e) {
			LOGGER.error("getTransSourceById", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMsg("根据id查询运送类型信息失败");
		}
		return result;
	}

	@Override
	public RpcRespIce updateTransSourceById(int id, TransSourceInfo transSourceInfo, Current __current) {
		RpcRespIce result = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			transSourceOperations.updateTransSourceById(id,
					BeanCopierUtils.copyProperties(transSourceInfo, TransSource.class, true));
		} catch (Exception e) {
			LOGGER.error("updateTransSourceById", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("修改运送来源失败");
		}
		return result;
	}

	@Override
	public RpcRespIce deleteTransSourceById(int id, Current __current) {
		RpcRespIce result = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			transSourceOperations.deleteTransSourceById(id);
		} catch (Exception e) {
			LOGGER.error("deleteTransSourceById", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("删除运送来源失败");
		}
		return result;
	}
}
