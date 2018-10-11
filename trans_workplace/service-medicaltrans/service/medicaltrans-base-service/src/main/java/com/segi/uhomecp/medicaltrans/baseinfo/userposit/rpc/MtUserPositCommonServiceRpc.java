package com.segi.uhomecp.medicaltrans.baseinfo.userposit.rpc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import resp.RpcRespIce;
import segi.medicaltrans.common.userposit.UserPositParam;
import segi.medicaltrans.common.userposit._MtUserPositCommonServiceIceDisp;
import Ice.Current;

import com.segi.uhomecp.medicaltrans.base.posit.bean.MtCurUserPosit;
import com.segi.uhomecp.medicaltrans.baseinfo.userposit.service.MtUserpositInfoService;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;

@Component
public class MtUserPositCommonServiceRpc extends _MtUserPositCommonServiceIceDisp{
	
	private static final long serialVersionUID = -7062480453298219560L;

	public static final Logger logger = LoggerFactory.getLogger(MtUserPositCommonServiceRpc.class);
	
	@Autowired
	public MtUserpositInfoService mtUserpositInfoService;

	/** @discription 修改人员状态和未完成任务数
	 * @author yangyh@segimail.com       
	 * @param userPositParamList
	 * @return        
	 */
	@Override
	public RpcRespIce updateUserPositInfo(int organId, List<Integer> userIds, short unTaskNum, short runTaskNum,
			String executeDate,String locationId, Current __current) {
		RpcRespIce result = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			mtUserpositInfoService.updateUserPositInfo(organId, userIds, unTaskNum, runTaskNum,executeDate,locationId);
		} catch (Exception e) {
			logger.error("updateUserPositInfo", e);
			result.code = RpcError.FAIL.getCode();
			result.message = org.apache.commons.lang3.StringUtils.isNotBlank(e.getMessage())
					? e.getMessage()
					: "修改人员状态和未完成任务数!";
		}
		return result;
	}

	/**
	 * 更新人员最新位置
	 */
	@Override
	public RpcRespIce updateUserNewLocationList(List<UserPositParam> userPositParamList, Current __current) {
		RpcRespIce result = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			if(AppUtils.isNotEmpty(userPositParamList)){
				for(UserPositParam userPositParam:userPositParamList){
					if(userPositParam!=null){
						MtCurUserPosit params = BeanCopierUtils.copyProperties(userPositParam,
								MtCurUserPosit.class, true);
						mtUserpositInfoService.updateUserNewLocation(params);
					}
				}
			}
		} catch (Exception e) {
			logger.error("updateUserNewLocationList", e);
			result.code = RpcError.FAIL.getCode();
			result.message = org.apache.commons.lang3.StringUtils.isNotBlank(e.getMessage())
					? e.getMessage()
					: "更新人员位置!";
		}
		return result;
	}

}
