package com.segi.uhomecp.medicaltrans.baseinfo.userstatus.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.baseinfo.userstatus.dto.UserStatusDto;
import com.segi.uhomecp.medicaltrans.baseinfo.userstatus.service.MtUserStatusService;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.DataTypeConverUtils;

import segi.medicaltrans.common.userstatus.UserStatusDetailRspIce;
import segi.medicaltrans.common.userstatus.UserStatusIce;


@Service("mtUserStatusServiceImpl")
public class MtUserStatusServiceImpl implements MtUserStatusService{

	/**
	 * 更新人员上下班状态
	 */
	@Override
	public ResultInfo updateStausByUser(UserStatusDto userStatusDto) {
		ResultInfo result =new ResultInfo();
		if (null == userStatusDto || null == userStatusDto.getOrganId() || null == userStatusDto.getStatus()
				|| null == userStatusDto.getUserId()) {
			throw new RuntimeException(" 更新人员上下班状态参数缺失！");
		}
		UserStatusIce userStatusIce = BeanCopierUtils.copyProperties(userStatusDto,
				UserStatusIce.class, true);
		UserStatusDetailRspIce rspOld = MtCommonServiceUtils.getStatusByUser(userStatusDto.getOrganId(),userStatusDto.getUserId());
		if(userStatusDto.getStatus().equals(rspOld.getUserStatusDetailIce().getStatus())){
			result.setIsSucc(false);
			if(userStatusDto.getStatus().equals("1")){
				result.setMessage("该员工已为上班状态,切换失败");
			}else{
				result.setMessage("该员工已为下班状态,切换失败");
			}
			return result;
		}
		//打卡时间
		Long paramTime = DataTypeConverUtils.formatDateToLongYYYYMMDDHHMMSS(new Date());
		userStatusIce.setParamTime(String.valueOf(paramTime));
		userStatusIce.setUpdateTime(String.valueOf(paramTime));
		UserStatusDetailRspIce rsp = MtCommonServiceUtils.updateStausByUser(userStatusIce);
		if(RpcError.SUCCESS.getCode().equals(rsp.getCode())){
			result.setIsSucc(true);
			result.setMessage(rsp.message);
		}else{
			result.setIsSucc(false);
			result.setMessage(rsp.message);
		}
		return result;
	}

	/**
	 * 查询人员上下班状态
	 */
	@Override
	public UserStatusDetailRspIce getStatusByUser(UserStatusDto userStatusDto) {
		if (null == userStatusDto || null == userStatusDto.getOrganId() || null == userStatusDto.getUserId()) {
			throw new RuntimeException(" 查询人员上下班状态参数缺失！");
		}
		UserStatusDetailRspIce rsp = MtCommonServiceUtils.getStatusByUser(userStatusDto.getOrganId(),userStatusDto.getUserId());
		return rsp;
	}
	
	
	
	

	
}
