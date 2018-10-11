package com.segi.uhomecp.medicaltrans.reportjob.monthjob.inf;

import java.util.List;

/**
 * 
 * Title: JobFullMonthExecute.java
 * 
 * @Description: 描述 全量更新接口类
 * @author wangxiong@segimail.com
 * @created 2018年8月15日 上午9:46:53
 */
public interface JobFullMonthExecute {
	/**
	 * @discription 处理类
	 * @author wangxiong@segimail.com
	 * @created 2018年8月15日 上午9:47:11
	 * @param arg0
	 */
	public void execute(List<Integer> arg0);
	
	public void execute(Integer organId, List<String> months);
}
