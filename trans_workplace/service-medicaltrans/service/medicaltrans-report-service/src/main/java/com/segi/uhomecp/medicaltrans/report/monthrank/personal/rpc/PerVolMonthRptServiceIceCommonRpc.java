package com.segi.uhomecp.medicaltrans.report.monthrank.personal.rpc;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import resp.RpcRespIce;
import segi.medicaltrans.common.report.monthrank.personal._PerVolMonthRptServiceIceDisp;
import Ice.Current;

import com.segi.uhomecp.medicaltrans.cache.PersonalVolumeRedisCache;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.service.PersonalVolumeMonthInfoService;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;

/**
 * @ClassName:  PerVolMonthRptServiceIceCommonRpc   
 * @Description:个人运送量月报表公共RPC   
 * @author: zhaoqing
 * @date:   2018年8月1日 下午4:11:09
 */
@Component
public class PerVolMonthRptServiceIceCommonRpc extends _PerVolMonthRptServiceIceDisp {
 
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(PerVolMonthRptServiceIceCommonRpc.class);
	
	@Autowired
	private PersonalVolumeMonthInfoService personalVolumeMonthInfoService;
	
	@Autowired
	private PersonalVolumeRedisCache personalVolumeRedisCache;

	/**
	 * <p>Title: savePersonalVolumeIncrease</p>   
	 * <p>Description: 月运送量排名增量保存接口</p> 
	 * <p>zhaoqing</p>
	 * @param organId
	 * @param userIds
	 * @param transCount
	 * @param exeBeginTime 任务实际开始时间，格式：YYYYMMDDHHMMSS
	 * @param __current
	 * @return   
	 */
	@Override
	public RpcRespIce savePersonalVolumeIncrease(int organId, String userIds,
			int transCount, String exeBeginTime, String taskType, Current __current) {
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), null);
		LOGGER.debug("=============>savePersonalVolumeIncrease: "
				+ "organId:{}, userIds:{}, exeBeginTime:{}", organId, userIds, exeBeginTime);
		
		if (StringUtils.isEmpty(userIds)) {
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("用户Id不能为空");
		}
		try {
			// 实际任务开始时间转换成日期格式
			Date exeBeginDate = DateUtil.parseStringToDateYYYYMMDDHHMMSS(exeBeginTime);
			// 获取月份
			String cycleYm = DateUtil.formatDateToStringYYMM(exeBeginDate);
			if (!cycleYm.equals(DateUtil.formatDateToStringYYMM(new Date()))) {
				// 月份不是当月则根据运送员Id和月份更新运送员的历史运送量信息 
				personalVolumeMonthInfoService.updatePersonalVolumeMonthHis(
						userIds, Integer.valueOf(cycleYm), transCount, taskType);
			} else {
				// 月份为空或者为当月，则更新当月排名缓存数据
				updatePersonalVolumeMonth(organId, userIds, transCount);		
			}		
		} catch (Exception e) {
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
			LOGGER.error("savePersonalVolumeIncrease", e);
		}
		return rsp;
	}
	
	/**
	 * @Title: updatePersonalVolumeMonth   
	 *  更新当月排名缓存数据
	 * @author zhaoqing  
	 * @param organId
	 * @param userIds
	 * @param transCount    
	 */
	private void updatePersonalVolumeMonth(int organId, String userIds, int transCount) {
		List<Integer> userIdList = AppUtils.str2Integer(
				StringUtils.replace(userIds, Constant.SPLIT_BLANK, ""));
		if (AppUtils.isNotEmpty(userIdList)) {
			for (Integer userId : userIdList) {
				personalVolumeRedisCache.savePerVolumeIncrease(organId, userId, transCount);
			}
		}
	}

}
