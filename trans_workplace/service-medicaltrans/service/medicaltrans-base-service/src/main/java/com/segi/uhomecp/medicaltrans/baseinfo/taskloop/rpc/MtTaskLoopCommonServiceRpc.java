package com.segi.uhomecp.medicaltrans.baseinfo.taskloop.rpc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import resp.RpcRespIce;
import segi.medicaltrans.common.taskloop.MtTaskLoopIce;
import segi.medicaltrans.common.taskloop.MtTaskLoopIceListReturn;
import segi.medicaltrans.common.taskloop.ReturnInteger;
import segi.medicaltrans.common.taskloop.TaskLoopStatusParam;
import segi.medicaltrans.common.taskloop._MtTaskLoopCommonServiceIceDisp;
import Ice.Current;

import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoop;
import com.segi.uhomecp.medicaltrans.baseinfo.taskloop.dto.MtTaskLoopDto;
import com.segi.uhomecp.medicaltrans.baseinfo.taskloop.enums.TaskLoopStatusEnum;
import com.segi.uhomecp.medicaltrans.baseinfo.taskloop.service.MtTaskLoopInfoService;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
  
/** 
 * Title: MtTaskLoopCommonServiceRpc.java    
 * @Description: 描述
 * @author yangyh@segimail.com       
 * @created 2018年5月14日 上午10:24:28    
 */
@Component
public class MtTaskLoopCommonServiceRpc extends _MtTaskLoopCommonServiceIceDisp {
	
	/**  描述   (@author: yangyh@segimail.com) */      
	private static final long serialVersionUID = 6864486438857118008L;

	@Autowired
	private MtTaskLoopInfoService mtTaskLoopInfoService;
	
	private final static Logger logger = LoggerFactory.getLogger(MtTaskLoopServiceRpc.class);
	
	/** @discription 循环任务停用启用、删除
	 * @author yangyh@segimail.com       
	 * @created 2018年3月27日 上午11:54:09      
	 * @param taskLoopId
	 * @param __current
	 * @return        
	 */  
	@Override
	public RpcRespIce updateTaskLoopStatus(TaskLoopStatusParam taskLoopStatusParam, Current __current) {
		RpcRespIce result = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			MtTaskLoopDto mtTaskLoopDto = BeanCopierUtils
					.copyProperties(taskLoopStatusParam, MtTaskLoopDto.class, true);
			Integer taskLoopId = mtTaskLoopInfoService.updateTaskLoopStatus(mtTaskLoopDto);
			result.setData(String.valueOf(taskLoopId));
		} catch (Exception e) {
			logger.error("updateTaskLoopStatus", e);
			result.code = RpcError.FAIL.getCode();
			result.message = StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : RpcError.FAIL.getMessage();
		}
		return result;
	}
	
	/** @discription 根据organId查出按月的任务
	 * @author yangyh@segimail.com       
	 * @created 2018年3月27日 上午11:54:09      
	 * @param taskLoopId
	 * @param __current
	 * @return        
	 */  
	@Override
	public MtTaskLoopIceListReturn getMtTaskLoopListByMonth(int organId, Current __current) {
		MtTaskLoopIceListReturn result = new MtTaskLoopIceListReturn(RpcError.SUCCESS.getCode(),
				RpcError.SUCCESS.getMessage(), new ArrayList<MtTaskLoopIce>());
		try {
			List<MtTaskLoop> list = mtTaskLoopInfoService.queryTaskLoopInstList(Integer.valueOf(organId),
					TaskLoopStatusEnum.STATUS_CD_NORMAL.getStatus(), MtConstant.CRON_TYPE_MONTH);
			result.setResultList(BeanCopierUtils.copyList2List(list, MtTaskLoopIce.class, true));
		} catch (Exception e) {
			logger.error("getMtTaskLoopListByMonth", e);
			result.code = RpcError.FAIL.getCode();
			result.msg = StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : RpcError.FAIL.getMessage();
		}
		return result;
	}

	/**
	 * @discription 根据organId查出按周的任务
	 * @author yangyh@segimail.com
	 * @created 2018年3月27日 上午11:54:09
	 * @param taskLoopId
	 * @param __current
	 * @return
	 */
	@Override
	public MtTaskLoopIceListReturn getMtTaskLoopListByWeek(int organId, Current __current) {
		MtTaskLoopIceListReturn result = new MtTaskLoopIceListReturn(RpcError.SUCCESS.getCode(),
				RpcError.SUCCESS.getMessage(), new ArrayList<MtTaskLoopIce>());
		try {
			List<MtTaskLoop> list = mtTaskLoopInfoService.queryTaskLoopInstList(Integer.valueOf(organId),
					TaskLoopStatusEnum.STATUS_CD_NORMAL.getStatus(), MtConstant.CRON_TYPE_WEEK);
			result.setResultList(BeanCopierUtils.copyList2List(list, MtTaskLoopIce.class, true));
		} catch (Exception e) {
			logger.error("getMtTaskLoopListByWeek", e);
			result.code = RpcError.FAIL.getCode();
			result.msg = StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : RpcError.FAIL.getMessage();
		}
		return result;
	}

	/**
	 * @discription 根据循环任务id查询线路IdList
	 * @author yangyh@segimail.com       
	 * @created 2018年7月31日 下午5:50:27      
	 * @param taskLoopId
	 * @param __current
	 * @return     
	 * @see segi.medicaltrans.common.taskloop._MtTaskLoopCommonServiceIceOperations#selectLocationIdList(int, Ice.Current)
	 */
	@Override
	public ReturnInteger selectLocationIdList(String taskLoopId, Current __current) {
		ReturnInteger result = new ReturnInteger(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new ArrayList<Integer>());
		try {
			result.setResultList(mtTaskLoopInfoService.selectLocationIdList(Integer.valueOf(taskLoopId)));
		} catch (Exception e) {
			logger.error("selectLocationIdList", e);
			result.code = RpcError.FAIL.getCode();
			result.msg = StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : RpcError.FAIL.getMessage();
		}
		return result;
	}

	/**
	 * @discription 根据循环任务id查询执行人IdList
	 * @author yangyh@segimail.com       
	 * @created 2018年7月31日 下午5:50:31      
	 * @param taskLoopId
	 * @param __current
	 * @return     
	 * @see segi.medicaltrans.common.taskloop._MtTaskLoopCommonServiceIceOperations#selectUserIdList(int, Ice.Current)
	 */
	@Override
	public ReturnInteger selectUserIdList(String taskLoopId, Current __current) {
		ReturnInteger result = new ReturnInteger(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new ArrayList<Integer>());
		try {
			result.setResultList(mtTaskLoopInfoService.selectUserIdList(Integer.valueOf(taskLoopId)));
		} catch (Exception e) {
			logger.error("selectUserIdList", e);
			result.code = RpcError.FAIL.getCode();
			result.msg = StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : RpcError.FAIL.getMessage();
		}
		return result;
	}
}
