package com.segi.uhomecp.medicaltrans.report.monthamount.transtype.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import segi.medicaltrans.report.ratio.ReportCommonParam;
import segi.medicaltrans.report.ratio.TaskDateSourceIce;
import segi.medicaltrans.report.ratio.TaskDateSourceReturnIce;
import segi.medicaltrans.report.ratio.TaskTypeIce;
import segi.medicaltrans.report.ratio.TaskTypeReturnIce;
import segi.medicaltrans.report.ratio.TranstypeIce;
import segi.medicaltrans.report.ratio.TranstypeReturnIce;
import segi.medicaltrans.report.ratio._RatioReportServiceIceDisp;
import Ice.Current;

import com.segi.uhomecp.medicaltrans.report.common.dto.CommonDto;
import com.segi.uhomecp.medicaltrans.report.monthamount.transtype.dto.TransTypeDto;
import com.segi.uhomecp.medicaltrans.report.monthamount.transtype.service.TranstypeStatisticsInfoService;
import com.segi.uhomecp.medicaltrans.report.organmonth.dto.TaskDateSourceDto;
import com.segi.uhomecp.medicaltrans.report.organmonth.dto.TaskTypeDto;
import com.segi.uhomecp.medicaltrans.report.organmonth.service.OrganMonthAmountService;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;

/**
 * Title: RatioReportServiceRpc.java    
 * @Description: 运送数据分析饼状图
 * @author yangyh@segimail.com       
 * @created 2018年8月6日 上午11:45:21
 */
@Component
public class RatioReportServiceRpc extends _RatioReportServiceIceDisp {
	
	/**  描述   (@author: yangyh@segimail.com) */      
	private static final long serialVersionUID = 6705640953636216162L;

	private static final Logger LOGGER = LoggerFactory.getLogger(RatioReportServiceRpc.class);
	
	@Autowired
	private TranstypeStatisticsInfoService transtypeStatisticsInfoService;

	@Autowired
	private OrganMonthAmountService organMonthAmountService;
	
	/**
	 * @discription 任务类型
	 * @author yangyh@segimail.com       
	 * @created 2018年8月6日 上午11:47:08      
	 * @param reportCommonParam
	 * @param __current
	 * @return     
	 * @see segi.medicaltrans.report.ratio._RatioReportServiceIceOperations#queryTaskTypeRatio(segi.medicaltrans.report.ratio.ReportCommonParam, Ice.Current)
	 */
	@Override
	public TaskTypeReturnIce queryTaskTypeRatio(ReportCommonParam reportCommonParam, Current __current) {
		TaskTypeReturnIce result = new TaskTypeReturnIce(RpcError.SUCCESS.getCode(),
				RpcError.SUCCESS.getMessage(), new TaskTypeIce());
		try {
			TaskTypeDto dto = organMonthAmountService.queryTaskTypeRatio(BeanCopierUtils.copyProperties(
					reportCommonParam, CommonDto.class, true));
			result.setData(BeanCopierUtils.copyProperties(dto, TaskTypeIce.class, true));
		} catch (Exception e) {
			LOGGER.debug("queryTaskTypeRatio", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMsg(RpcError.FAIL.getMessage());
		}
		return result;
	}

	/**
	 * @discription 运输类型
	 * @author yangyh@segimail.com       
	 * @created 2018年8月6日 上午11:47:24      
	 * @param reportCommonParam
	 * @param __current
	 * @return     
	 * @see segi.medicaltrans.report.ratio._RatioReportServiceIceOperations#queryTransTypeRatio(segi.medicaltrans.report.ratio.ReportCommonParam, Ice.Current)
	 */
	@Override
	public TranstypeReturnIce queryTransTypeRatio(ReportCommonParam reportCommonParam, Current __current) {
		TranstypeReturnIce result = new TranstypeReturnIce(RpcError.SUCCESS.getCode(),
				RpcError.SUCCESS.getMessage(), new TranstypeIce());
		try {
			TransTypeDto dto = transtypeStatisticsInfoService.queryTaskTypeRatio(BeanCopierUtils.copyProperties(
					reportCommonParam, CommonDto.class, true));
			result.setData(BeanCopierUtils.copyProperties(dto, TranstypeIce.class, true));
		} catch (Exception e) {
			LOGGER.debug("queryTransTypeRatio", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMsg(RpcError.FAIL.getMessage());
		}
		return result;
	}

	/**
	 * @discription 来源类型
	 * @author yangyh@segimail.com       
	 * @created 2018年8月6日 上午11:47:45      
	 * @param reportCommonParam
	 * @param __current
	 * @return     
	 * @see segi.medicaltrans.report.ratio._RatioReportServiceIceOperations#queryTaskDateSourceRatio(segi.medicaltrans.report.ratio.ReportCommonParam, Ice.Current)
	 */
	@Override
	public TaskDateSourceReturnIce queryTaskDateSourceRatio(ReportCommonParam reportCommonParam, Current __current) {
		TaskDateSourceReturnIce result = new TaskDateSourceReturnIce(RpcError.SUCCESS.getCode(),
				RpcError.SUCCESS.getMessage(), new TaskDateSourceIce());
		try {
			TaskDateSourceDto dto = organMonthAmountService.queryTaskDateSourceRatio(BeanCopierUtils.copyProperties(
					reportCommonParam, CommonDto.class, true));
			result.setData(BeanCopierUtils.copyProperties(dto, TaskDateSourceIce.class, true));
		} catch (Exception e) {
			LOGGER.debug("queryTaskDateSourceRatio", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMsg(RpcError.FAIL.getMessage());
		}
		return result;
	}
}
