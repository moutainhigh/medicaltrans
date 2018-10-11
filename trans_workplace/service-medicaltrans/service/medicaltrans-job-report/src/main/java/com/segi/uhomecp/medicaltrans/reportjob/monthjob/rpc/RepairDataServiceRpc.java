package com.segi.uhomecp.medicaltrans.reportjob.monthjob.rpc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import resp.RpcRespIce;
import segi.medicaltrans.report.repairdata.RepairReportDataIceParams;
import segi.medicaltrans.report.repairdata._RepairDataServiceIceDisp;
import Ice.Current;

import com.segi.uhomecp.medicaltrans.reportjob.monthjob.dto.RepairDataDto;
import com.segi.uhomecp.medicaltrans.reportjob.monthjob.inf.support.RepairDataUpdateReportExecute;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;

@Component
public class RepairDataServiceRpc extends _RepairDataServiceIceDisp {
	
	/**  描述   (@author: zhangyang@segimail.com) */      
	    
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RepairDataUpdateReportExecute repairDataUpdateReportExecute;

	/**
	 * @discription 报表数据修复校正
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月16日 上午9:59:20      
	 * @param params
	 * @param __current
	 * @return     
	 */
	@Override
	public RpcRespIce repairTaskReportData(RepairReportDataIceParams params,
			Current __current) {
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			RepairDataDto dto = BeanCopierUtils.copyProperties(params, RepairDataDto.class, true);
			// 获取开始时间和结束时间之间的日期
			List<String> months = DateUtil.getStrDateBetween(dto.getBeginTime(), dto.getEndTime(), DateUtil.FORMAT_YYYYMM);
			// 获取时间1个月1个月去修复数据
			if (AppUtils.isNotEmpty(months)) {
				repairDataUpdateReportExecute.execute(dto.getOrganId(), months);
			}
		} catch (Exception e) {
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}
}
