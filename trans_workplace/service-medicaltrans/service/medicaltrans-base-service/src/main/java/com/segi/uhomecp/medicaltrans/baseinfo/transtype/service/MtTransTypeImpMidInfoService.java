package com.segi.uhomecp.medicaltrans.baseinfo.transtype.service;

import java.util.List;

public interface MtTransTypeImpMidInfoService {
	
	/**
	 * @discription 删除导入中间表
	 * @author zhangyang@segimail.com       
	 * @created 2018年10月9日 上午9:34:13     
	 * @param batchId
	 */
	void deleteTransTypeImpMidInfo(List<Integer> batchId);
}
