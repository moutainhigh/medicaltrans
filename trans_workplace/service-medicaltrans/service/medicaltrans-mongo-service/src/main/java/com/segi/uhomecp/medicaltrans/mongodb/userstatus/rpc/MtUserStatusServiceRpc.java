package com.segi.uhomecp.medicaltrans.mongodb.userstatus.rpc;



import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.mongodb.userstatus.dao.UserStatusOperations;
import com.segi.uhomecp.medicaltrans.mongodb.userstatus.entity.UserStautsCurrent;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;

import segi.medicaltrans.common.userstatus.UserLotStatusDetailRspIce;
import segi.medicaltrans.common.userstatus.UserLotStatusIce;
import segi.medicaltrans.common.userstatus.UserStatusDetailIce;
import segi.medicaltrans.common.userstatus.UserStatusDetailRspIce;
import segi.medicaltrans.common.userstatus.UserStatusIce;
import segi.medicaltrans.common.userstatus._MtUserStatusServiceIceDisp;
import Ice.Current;

@Component
public class MtUserStatusServiceRpc extends _MtUserStatusServiceIceDisp{
	
	private static final long serialVersionUID = 1;

	public static final Logger logger = LoggerFactory.getLogger(MtUserStatusServiceRpc.class);
	
	@Autowired
	private UserStatusOperations userStatusOperations;
	

	/**
	 * 更新人员上下班状态接口
	 */
	@Override
	public UserStatusDetailRspIce updateStausByUser(UserStatusIce userStatusIce, Current __current) {
		UserStatusDetailRspIce rsp = new UserStatusDetailRspIce();
		rsp.setCode(RpcError.SUCCESS.getCode());
		rsp.setMessage(RpcError.SUCCESS.getMessage());
		try {
			//1、更新人员当前状态
			UserStautsCurrent userStautsCurrent = BeanCopierUtils.copyProperties(userStatusIce, UserStautsCurrent.class, true);
			userStautsCurrent.setId(userStautsCurrent.getOrganId()+"_"+userStautsCurrent.getUserId());
			userStatusOperations.save(userStautsCurrent);
			UserStatusDetailIce userStatusDetailIce = new UserStatusDetailIce();
			userStatusDetailIce.setStatus(userStatusIce.getStatus());
			userStatusDetailIce.setUserId(userStatusIce.getUserId());
			rsp.setUserStatusDetailIce(userStatusDetailIce);
		} catch (Exception e) {
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("系统异常，切换员工状态失败！");
			logger.error("MtUserStatusServiceRpc->updateStausByUser:{}", e);
		}
		return rsp;
	}


	/**
	 * 查询人员上下班状态接口
	 */
	@Override
	public UserStatusDetailRspIce getStatusByUser(UserStatusIce userStatusIce, Current __current) {
		UserStatusDetailRspIce rsp = new UserStatusDetailRspIce();
		rsp.setCode(RpcError.SUCCESS.getCode());
		rsp.setMessage(RpcError.SUCCESS.getMessage());
		try{
			String id = userStatusIce.getOrganId()+"_"+userStatusIce.getUserId();
			UserStautsCurrent userStautsCurrent = userStatusOperations.findById(id);
			UserStatusDetailIce userStatusDetailIce = new UserStatusDetailIce();
			if(userStautsCurrent!=null){
				userStatusDetailIce.setStatus(userStautsCurrent.getStatus());
				userStatusDetailIce.setUserId(String.valueOf(userStautsCurrent.getUserId()));
				rsp.setUserStatusDetailIce(userStatusDetailIce);
			}
		}catch (Exception e) {
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("系统异常，查询员工状态失败！");
			logger.error("MtUserStatusServiceRpc->getStatusByUser:{}", e);
		}
		return rsp;
	}


	/**
	 * 批量查询人员状态接口
	 */
	@Override
	public UserLotStatusDetailRspIce getLotStatusByUser(UserLotStatusIce userLotStatusIce, Current __current) {
		UserLotStatusDetailRspIce rsp = new UserLotStatusDetailRspIce();
		rsp.setCode(RpcError.SUCCESS.getCode());
		rsp.setMessage(RpcError.SUCCESS.getMessage());
		try{
			List<String> userList = userLotStatusIce.getUserList();
			if(!AppUtils.isNotEmpty(userList)){
				rsp.setCode(RpcError.FAIL.getCode());
				rsp.setMessage(RpcError.FAIL.getMessage());
				return rsp;
			}
			List<UserStatusDetailIce> userStatusDetailList = new ArrayList<UserStatusDetailIce>();
			for(String userId : userList){
				String id = userLotStatusIce.getOrganId()+"_"+userId;
				UserStautsCurrent userStautsCurrent = userStatusOperations.findById(id);
				UserStatusDetailIce userStatusDetailIce = BeanCopierUtils.copyProperties(userStautsCurrent,
						UserStatusDetailIce.class, true);
				userStatusDetailList.add(userStatusDetailIce);
			}
			rsp.setUserStatusDetailList(userStatusDetailList);
		}catch (Exception e) {
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("系统异常，批量查询员工状态失败！");
			logger.error("MtUserStatusServiceRpc->getLotStatusByUser:{}", e);
		}
		return rsp;
		
	}



}
