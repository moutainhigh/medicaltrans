package com.segi.uhomecp.medicaltrans.trans.rpc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import resp.RpcRespIce;
import segi.medicaltrans.mttask.common._MtTaskCommonServiceIceDisp;
import Ice.Current;

import com.segi.uhomecp.medicaltrans.enums.TransStatusEnum;
import com.segi.uhomecp.medicaltrans.trans.service.MtCommonTaskService;
import com.segi.uhomecp.rpc.RpcError;

@Component
public class MtTaskCommonServiceRpc extends _MtTaskCommonServiceIceDisp{

	/**  描述   (@author: zhangyang@segimail.com) */      
	    
	private static final long serialVersionUID = 7065341426928764645L;

	public static final Logger logger = LoggerFactory.getLogger(MtTaskCommonServiceRpc.class);
	
	@Autowired
	private MtCommonTaskService mtCommonTaskService;

	/**
	 * @discription 运送任务预约时间7天没完成异常关闭(除取消/完成状态)
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月8日 下午5:01:49      
	 * @param groupOrganId
	 * @param organIdList
	 * @param statusList
	 * @param limitDate
	 * @param __current
	 * @return     
	 */
	@Override
	public RpcRespIce updateTaskUnusalClose(int groupOrganId,
			List<Integer> organIdList, long limitDate, Current __current) {
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		Date nowDate = new Date();
		// 预约时间7天后,未派单/抢单中/进行中/未开始/退单状态的任务更新为异常关闭
		List<String> statusList = new ArrayList<>();
		statusList.add(TransStatusEnum.TRANS_NON_DISPATCH.getCode());
		statusList.add(TransStatusEnum.TRANS_ROBBING.getCode());
		statusList.add(TransStatusEnum.TRANS_RUNNING.getCode());
		statusList.add(TransStatusEnum.TRANS_NOT_START.getCode());
		statusList.add(TransStatusEnum.TRANS_BACK.getCode());
		try {
			rsp = this.mtCommonTaskService.handlerTaskUnusalClose(groupOrganId, organIdList, limitDate, nowDate, statusList, rsp);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateTaskUnusalClose", e);
			rsp.code = RpcError.FAIL.getCode();
			rsp.message = "系统错误";
		}
		return rsp;
	}

}
