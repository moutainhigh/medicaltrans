package com.segi.uhomecp.medicaltrans.report.organmonth.rpc;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import segi.medicaltrans.report.organmonth.AvgRespTimeIce;
import segi.medicaltrans.report.organmonth.AvgRespTimeReturnIce;
import segi.medicaltrans.report.organmonth.OrganMonthAmontIce;
import segi.medicaltrans.report.organmonth.OrganMonthAmontReturnIce;
import segi.medicaltrans.report.organmonth.TimelyAmountMonthIce;
import segi.medicaltrans.report.organmonth.TimelyAmountMonthReturnIce;
import segi.medicaltrans.report.organmonth.TransProfileIce;
import segi.medicaltrans.report.organmonth.TransProfileReturnIce;
import segi.medicaltrans.report.organmonth.UserAmountMonthIce;
import segi.medicaltrans.report.organmonth.UserAmountMonthReturnIce;
import segi.medicaltrans.report.organmonth._OrganMonthAmountServiceIceDisp;
import Ice.Current;

import com.segi.uhomecp.medicaltrans.report.organmonth.dto.AmountMonthDto;
import com.segi.uhomecp.medicaltrans.report.organmonth.dto.TransProfileDto;
import com.segi.uhomecp.medicaltrans.report.organmonth.service.OrganMonthAmountService;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
/**
 * 项目月运送量
 * @author Administrator
 *
 */
@Component
public class OrganMonthAmountServiceRpc extends _OrganMonthAmountServiceIceDisp {
	
	private static final long serialVersionUID = 1L;
	
	public static final Logger logger = LoggerFactory.getLogger(OrganMonthAmountServiceRpc.class);

	@Autowired
	private OrganMonthAmountService organMonthAmountService;
	
	/**
	 * 获取报表运送概况接口
	 */
	@Override
	public TransProfileReturnIce getTransProfile(String organId, String cycleY, Current __current) {
		TransProfileReturnIce rsp = new TransProfileReturnIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new TransProfileIce());
		try {
			TransProfileDto transProfileDto = organMonthAmountService.getTransProfile(organId, cycleY);
			if (transProfileDto != null) {
				TransProfileIce transProfileIce = BeanCopierUtils.copyProperties(transProfileDto, TransProfileIce.class,
						true);
				rsp.setTransProfile(transProfileIce);
			}
		} catch (NumberFormatException e) {
			logger.error("getTransProfile", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMsg(RpcError.FAIL.getMessage());
		}
		return rsp;
	}

	/**
	 * 每月运送量趋势分析
	 */
	@Override
	public OrganMonthAmontReturnIce queryAmountMonth(String organId, String cycleY, Current __current) {
		OrganMonthAmontReturnIce rsp = new OrganMonthAmontReturnIce(RpcError.SUCCESS.getCode(),
				RpcError.SUCCESS.getMessage(), new ArrayList<OrganMonthAmontIce>());
		try {
			List<AmountMonthDto> amountMonthDtoList = organMonthAmountService.queryAmountMonth(organId, cycleY);
			if (AppUtils.isNotEmpty(amountMonthDtoList)) {
				List<OrganMonthAmontIce> organMonthAmontIceList = BeanCopierUtils.copyList2List(amountMonthDtoList,
						OrganMonthAmontIce.class, true);
				rsp.setOrganMonthAmontIceList(organMonthAmontIceList);
			}
		} catch (NumberFormatException e) {
			logger.error("queryAmountMonth", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMsg(RpcError.FAIL.getMessage());
		}
		return rsp;
	}

	/**
	 * 及时率满意率分析
	 */
	@Override
	public TimelyAmountMonthReturnIce queryTimelyAmountMonth(String organId, String cycleY, Current __current) {
		TimelyAmountMonthReturnIce rsp = new TimelyAmountMonthReturnIce(RpcError.SUCCESS.getCode(),
				RpcError.SUCCESS.getMessage(), new ArrayList<TimelyAmountMonthIce>());
		try {
			List<AmountMonthDto> amountMonthDtoList = organMonthAmountService.queryTimelyAmountMonth(organId, cycleY);
			if (AppUtils.isNotEmpty(amountMonthDtoList)) {
				List<TimelyAmountMonthIce> organMonthAmontIceList = BeanCopierUtils.copyList2List(amountMonthDtoList,
						TimelyAmountMonthIce.class, true);
				rsp.setTimelyAmountMonthIceList(organMonthAmontIceList);
			}
		} catch (NumberFormatException e) {
			logger.error("queryTimelyAmountMonth", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMsg(RpcError.FAIL.getMessage());
		}
		return rsp;
	}

	/**
	 * 运送员每月平均运送量趋势分析
	 */
	@Override
	public UserAmountMonthReturnIce queryUserAmountMonth(String organId, String cycleY, Current __current) {
		UserAmountMonthReturnIce rsp = new UserAmountMonthReturnIce(RpcError.SUCCESS.getCode(),
				RpcError.SUCCESS.getMessage(), new ArrayList<UserAmountMonthIce>());
		try {
			List<AmountMonthDto> amountMonthDtoList = organMonthAmountService.queryUserAmountMonth(organId, cycleY);
			if (AppUtils.isNotEmpty(amountMonthDtoList)) {
				List<UserAmountMonthIce> userAmountMonthIceList = BeanCopierUtils.copyList2List(amountMonthDtoList,
						UserAmountMonthIce.class, true);
				rsp.setUserAmountMonthIceList(userAmountMonthIceList);
			}
		} catch (NumberFormatException e) {
			logger.error("queryUserAmountMonth", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMsg(RpcError.FAIL.getMessage());
		}
		return rsp;
	}

	/**
	 * 即时任务响应时间趋势分析
	 */
	@Override
	public AvgRespTimeReturnIce queryHisRespTime(String organId, String cycleY, Current __current) {
		AvgRespTimeReturnIce rsp = new AvgRespTimeReturnIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new ArrayList<AvgRespTimeIce>());
		try {
			List<AmountMonthDto> amountMonthDtoList = organMonthAmountService.queryHisRespTime(organId, cycleY);
			if (AppUtils.isNotEmpty(amountMonthDtoList)) {
				List<AvgRespTimeIce> avgRespTimeIceList = BeanCopierUtils.copyList2List(amountMonthDtoList,
						AvgRespTimeIce.class, true);
				rsp.setAvgRespTimeIceList(avgRespTimeIceList);
			}
		} catch (NumberFormatException e) {
			logger.error("queryUserAmountMonth", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMsg(RpcError.FAIL.getMessage());
		}
		return rsp;
	}
	
}
