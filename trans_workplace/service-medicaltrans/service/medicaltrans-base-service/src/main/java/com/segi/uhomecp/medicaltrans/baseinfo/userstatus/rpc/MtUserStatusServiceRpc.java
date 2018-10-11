package com.segi.uhomecp.medicaltrans.baseinfo.userstatus.rpc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.baseinfo.location.dto.MtBuildLocationDto;
import com.segi.uhomecp.medicaltrans.baseinfo.userstatus.dto.UserStatusDto;
import com.segi.uhomecp.medicaltrans.baseinfo.userstatus.service.MtUserStatusService;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;

import segi.medicaltrans.base.userstatus.UserStatusIce;
import segi.medicaltrans.base.userstatus.UserStatusRetIce;
import segi.medicaltrans.base.userstatus.UserStatusReturnIce;
import segi.medicaltrans.base.userstatus._UserStatusServiceIceDisp;
import segi.medicaltrans.common.userstatus.UserStatusDetailRspIce;
import Ice.Current;


@Component
public class MtUserStatusServiceRpc extends _UserStatusServiceIceDisp{

	/**
	 * 员工状态切换
	 */
	private static final long serialVersionUID = 1L;

	public static final Logger logger = LoggerFactory.getLogger(MtUserStatusServiceRpc.class);
	
	
	@Autowired
	private MtUserStatusService mtUserStatusService;
	

	/**
	 * 切换员工状态
	 */
	@Override
	public UserStatusReturnIce updateStausByUser(UserStatusIce userStatusIce, Current __current) {
		UserStatusReturnIce result = new UserStatusReturnIce();
		result.code = RpcError.SUCCESS.getCode();
		result.message = RpcError.SUCCESS.getMessage();
		try { 
			UserStatusDto userStatusDto = BeanCopierUtils.copyProperties(userStatusIce,
					UserStatusDto.class, true);
			ResultInfo resultInfo = mtUserStatusService.updateStausByUser(userStatusDto);
			if(resultInfo.getIsSucc()){
				UserStatusRetIce userStatusRetIce = new UserStatusRetIce();
				userStatusRetIce.setStatus(userStatusIce.getStatus());
				userStatusRetIce.setUserId(userStatusIce.getUserId());
				result.setUserStatusRetIce(userStatusRetIce);
			}else{
				result.setCode(RpcError.FAIL.getCode());
				result.setMessage(resultInfo.getMessage());
			}
		} catch (Exception e) {
			logger.error("updateStausByUser", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("切换员工上下班状态失败");
		}
		return result;
	}

	/**
	 * 查询员工状态
	 */
	@Override
	public UserStatusReturnIce getStatusByUser(UserStatusIce userStatusIce, Current __current) {
		UserStatusReturnIce result = new UserStatusReturnIce();
		result.code = RpcError.SUCCESS.getCode();
		result.message = RpcError.SUCCESS.getMessage();
		try { 
			UserStatusDto userStatusDto = BeanCopierUtils.copyProperties(userStatusIce,
					UserStatusDto.class, true);
			UserStatusDetailRspIce resultInfo = mtUserStatusService.getStatusByUser(userStatusDto);
			if(RpcError.SUCCESS.getCode().equals(resultInfo.getCode())){
				UserStatusRetIce userStatusRetIce = new UserStatusRetIce();
				userStatusRetIce.setStatus(resultInfo.getUserStatusDetailIce().getStatus());
				userStatusRetIce.setUserId(resultInfo.getUserStatusDetailIce().getUserId());
				result.setUserStatusRetIce(userStatusRetIce);
			}else{
				result.setCode(RpcError.FAIL.getCode());
				result.setMessage("查询员工上下班状态失败");
			}
		} catch (Exception e) {
			logger.error("getStatusByUser", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("查询员工上下班状态失败");
		}
		return result;
		
	}
	
	
}
