package com.segi.uhomecp.medicaltrans.mongodb.transtype.rpc;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import resp.RpcRespIce;
import segi.medicaltrans.report.common.transtype.TransTypeInfo;
import segi.medicaltrans.report.common.transtype.TransTypeInfoReturnIce;
import segi.medicaltrans.report.common.transtype._ReportCommonTransTypeServiceIceDisp;
import Ice.Current;

import com.segi.uhomecp.medicaltrans.mongodb.transtype.entity.TransType;
import com.segi.uhomecp.medicaltrans.mongodb.transtype.dao.TransTypeOperations;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;

@Component
public class TransTypeRpc extends _ReportCommonTransTypeServiceIceDisp {

	/**  描述   (@author: yangyh@segimail.com) */      
	private static final long serialVersionUID = -8042703703631938613L;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(TransTypeRpc.class);
	
	@Resource
	private TransTypeOperations transTypeOperations;

	@Override
	public RpcRespIce saveTransType(TransTypeInfo transTypeInfo, Current __current) {
		RpcRespIce result = new RpcRespIce(RpcError.SUCCESS.getCode(),
				RpcError.SUCCESS.getMessage(), "");
		try {
			transTypeOperations.saveTransType(BeanCopierUtils.copyProperties(transTypeInfo, TransType.class, true));
		} catch (Exception e) {
			LOGGER.error("saveTransType", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("新增运送来源失败");
		}
		return result;
	}

	@Override
	public TransTypeInfoReturnIce getTransTypeById(int id, Current __current) {
		TransTypeInfoReturnIce result = new TransTypeInfoReturnIce(RpcError.SUCCESS.getCode(),
				RpcError.SUCCESS.getMessage(), new TransTypeInfo());
		try {
			TransType transType = transTypeOperations.findTransTypeById(id);
			result.setData(BeanCopierUtils.copyProperties(transType, TransTypeInfo.class, true));
		} catch (Exception e) {
			LOGGER.error("getTransTypeById", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMsg("根据id查询运送类型信息失败");
		}
		return result;
	}
	
	@Override
	public RpcRespIce updateTransTypeById(int id, TransTypeInfo transTypeInfo, Current __current) {
		RpcRespIce result = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			transTypeOperations.updateTransTypeById(id,
					BeanCopierUtils.copyProperties(transTypeInfo, TransType.class, true));
		} catch (Exception e) {
			LOGGER.error("updateTransTypeById", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("修改运送类型失败");
		}
		return result;
	}

	@Override
	public RpcRespIce deleteTransTypeById(int id, Current __current) {
		RpcRespIce result = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			transTypeOperations.deleteTransTypeById(id);
		} catch (Exception e) {
			LOGGER.error("deleteTransTypeById", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("删除运送类型失败");
		}
		return result;
	}
}
