package com.segi.uhomecp.medicaltrans.baseinfo.userstatus.service;


import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.baseinfo.userstatus.dto.UserStatusDto;

import segi.medicaltrans.common.userstatus.UserStatusDetailRspIce;

public interface MtUserStatusService {

	/**
	 * 更新人员上下班状态
	 * @param userStatusDto
	 * @return
	 */
	public ResultInfo updateStausByUser(UserStatusDto userStatusDto);

	/**
	 * 查询人员上下班状态
	 * @param userStatusDto
	 * @return
	 */
	public UserStatusDetailRspIce getStatusByUser(UserStatusDto userStatusDto);
}
