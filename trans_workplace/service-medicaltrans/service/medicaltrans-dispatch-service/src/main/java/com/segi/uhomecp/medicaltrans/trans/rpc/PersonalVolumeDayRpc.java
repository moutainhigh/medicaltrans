package com.segi.uhomecp.medicaltrans.trans.rpc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import segi.medicaltrans.report.pervolume.day.PerDayRankParam;
import segi.medicaltrans.report.pervolume.day.PerVolDayIce;
import segi.medicaltrans.report.pervolume.day.PerVolDayRsp;
import segi.medicaltrans.report.pervolume.day._PerVolDayServiceIceDisp;
import Ice.Current;

import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.enums.TransStatusEnum;
import com.segi.uhomecp.medicaltrans.trans.dto.PersonalVolumeDayDto;
import com.segi.uhomecp.medicaltrans.trans.utils.PerVolDayServiceUtil;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.DataTypeConverUtils;

/**
 * @ClassName:  PersonalVolumeDayRpc   
 * @Description:个人运送量日排名   
 * @author: zhaoqing
 * @date:   2018年9月17日 上午11:28:45
 */
@Component
public class PersonalVolumeDayRpc extends _PerVolDayServiceIceDisp {

	private static final long serialVersionUID = -1283793773769722698L;

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonalVolumeDayRpc.class);

	@Autowired
	private PerVolDayServiceUtil perVolDayServiceUtil;
	
	/**
	 * <p>Title: getDayTransVolRank</p>   
	 * <p>Description: 个人运送量日排名</p> 
	 * <p>zhaoqing</p>
	 * @param params
	 * @param __current
	 * @return   
	 */
	@Override
	public PerVolDayRsp getDayTransVolRank(PerDayRankParam params, Current __current) {
		PerVolDayRsp result = new PerVolDayRsp();
		result.code = RpcError.SUCCESS.getCode();
		result.msg = RpcError.SUCCESS.getMessage();
		try {
			// 参数类型转换
			PersonalVolumeDayDto paramsDto = BeanCopierUtils.copyProperties(params,
					PersonalVolumeDayDto.class, true);
			// 转换实际开始时间
			paramsDto.setExeBeginTime(DataTypeConverUtils.parseLongToDate(paramsDto.getBeginTime()));
			// 转换实际结束时间
			paramsDto.setExeEndTime(DataTypeConverUtils.parseLongToDate(paramsDto.getEndTime()));
			// 设置任务状态为已完成
			paramsDto.setTaskStatus(TransStatusEnum.TRANS_COMPLETED.getCode());
			// 分页查询个人运送量日排名信息
			ResultInfo resultInfo = perVolDayServiceUtil.getDayTransVolRank(paramsDto);
			if (!resultInfo.getIsSucc()) {
				result.setCode(RpcError.FAIL.getCode());
				result.setMsg(resultInfo.getMessage());
				return result;
			}
			@SuppressWarnings("unchecked")
			List<PersonalVolumeDayDto> resultDtoList = resultInfo.getObjList();
			// 设置返回的结果集
			result.setResultList(BeanCopierUtils.copyList2List(
					resultDtoList, PerVolDayIce.class, true));	
		} catch (Exception e) {
			LOGGER.error("getDayTransVolRankPage", e);
			result.code = RpcError.FAIL.getCode();
			result.msg = "查询个人运送量日排名信息失败";
		}
		return result;
	}
}
