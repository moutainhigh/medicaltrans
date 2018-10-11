package com.segi.uhomecp.medicaltrans.reportjob.monthjob.handler;

import com.segi.uhomecp.medicaltrans.reportjob.query.trans.dto.ReportJobTimeDto;
import com.segi.uhomecp.wh.common.dto.ResultDto;

/**
 * 
 * Title: JobFullMonthHandler.java
 * 
 * @Description: 描述 全量更新Job
 * @author wangxiong@segimail.com
 * @created 2018年8月15日 上午9:38:03
 */
public interface JobFullMonthHandler {
	/**
	 * @discription 处理类
	 * @author wangxiong@segimail.com
	 * @created 2018年8月15日 上午9:38:22
	 * @param dto
	 * @return
	 */
	public ResultDto<String, String, Integer> invoke(ReportJobTimeDto dto);
}
