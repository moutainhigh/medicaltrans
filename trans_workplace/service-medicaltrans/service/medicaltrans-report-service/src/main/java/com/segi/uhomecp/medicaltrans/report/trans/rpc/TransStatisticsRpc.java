package com.segi.uhomecp.medicaltrans.report.trans.rpc;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import segi.medicaltrans.report.trans.TransRatioIce;
import segi.medicaltrans.report.trans.TransRatioReturnIce;
import segi.medicaltrans.report.trans.TransTimeIce;
import segi.medicaltrans.report.trans.TransTimeReturnIce;
import segi.medicaltrans.report.trans._TransStatisticsServiceIceDisp;
import Ice.Current;

import com.segi.uhomecp.medicaltrans.report.trans.dto.TransStatisticsDto;
import com.segi.uhomecp.medicaltrans.report.trans.service.TransStatisticsInfoService;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;

/**
 * Title: TransStatisticsRpc.java    
 * @Description: 宽表ice业务
 * @author yangyh@segimail.com       
 * @created 2018年9月28日 上午9:33:25
 */
@Component
public class TransStatisticsRpc extends _TransStatisticsServiceIceDisp {
	
	/**  描述   (@author: yangyh@segimail.com) */      
	private static final long serialVersionUID = -5893423730945895604L;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(TransStatisticsRpc.class);

	@Autowired
	private TransStatisticsInfoService transStatisticsInfoService;
	
	/**
	 * @discription 运送时间统计
	 * @author yangyh@segimail.com       
	 * @created 2018年9月18日 下午5:20:51      
	 * @param organId
	 * @param __current
	 * @return     
	 * @see segi.medicaltrans.report.trans._TransStatisticsServiceIceOperations#queryTransportTime(int, Ice.Current)
	 */
	@Override
	public TransTimeReturnIce queryTransportTime(int organId, Current __current) {
		TransTimeReturnIce result = new TransTimeReturnIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new ArrayList<TransTimeIce>());
		try {
			List<TransStatisticsDto> list = transStatisticsInfoService.queryTransportTime(organId);
			result.setResultList(BeanCopierUtils.copyList2List(list, TransTimeIce.class, true));
		} catch (Exception e) {
			LOGGER.error("queryTransportTime", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMsg("运送时间统计查询失败");
		}
		return result;
	}

	/**
	 * @discription 任务结构占比
	 * @author yangyh@segimail.com       
	 * @created 2018年9月18日 下午5:25:59      
	 * @param organId
	 * @param __current
	 * @return     
	 * @see segi.medicaltrans.report.trans._TransStatisticsServiceIceOperations#queryTaskStructureRatio(int, Ice.Current)
	 */
	@Override
	public TransRatioReturnIce queryTaskStructureRatio(int organId, Current __current) {
		TransRatioReturnIce result = new TransRatioReturnIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new ArrayList<TransRatioIce>());
		try {
			List<TransStatisticsDto> list = transStatisticsInfoService.queryTaskStructureRatio(organId);
			result.setResultList(BeanCopierUtils.copyList2List(list, TransRatioIce.class, true));
		} catch (Exception e) {
			LOGGER.error("queryTaskStructureRatio", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMsg("任务结构占比查询失败");
		}
		return result;
	}

	/**
	 * @discription 运送方式占比
	 * @author yangyh@segimail.com       
	 * @created 2018年9月18日 下午5:26:20      
	 * @param organId
	 * @param __current
	 * @return     
	 * @see segi.medicaltrans.report.trans._TransStatisticsServiceIceOperations#queryTransModeRatio(int, Ice.Current)
	 */
	@Override
	public TransRatioReturnIce queryTransModeRatio(int organId, Current __current) {
		TransRatioReturnIce result = new TransRatioReturnIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new ArrayList<TransRatioIce>());
		try {
			List<TransStatisticsDto> list = transStatisticsInfoService.queryTransModeRatio(organId);
			result.setResultList(BeanCopierUtils.copyList2List(list, TransRatioIce.class, true));
		} catch (Exception e) {
			LOGGER.error("queryTransModeRatio", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMsg("运送方式占比查询失败");
		}
		return result;
	}

	/**
	 * @discription 出发地占比
	 * @author yangyh@segimail.com       
	 * @created 2018年9月18日 下午5:26:40      
	 * @param organId
	 * @param __current
	 * @return     
	 * @see segi.medicaltrans.report.trans._TransStatisticsServiceIceOperations#queryFromHouseRatio(int, Ice.Current)
	 */
	@Override
	public TransRatioReturnIce queryFromHouseRatio(int organId, Current __current) {
		TransRatioReturnIce result = new TransRatioReturnIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new ArrayList<TransRatioIce>());
		try {
//			List<TransStatisticsDto> list = transStatisticsInfoService.queryFromHouseRatio(organId);
//			result.setResultList(BeanCopierUtils.copyList2List(list, TransRatioIce.class, true));
		} catch (Exception e) {
			LOGGER.error("queryFromHouseRatio", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMsg("出发地占比查询失败");
		}
		return result;
	}
}
