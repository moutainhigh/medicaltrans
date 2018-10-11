package com.segi.uhomecp.medicaltrans.reportjob.util;

import java.util.Calendar;
import java.util.Date;

public class ReportUtils {
	
	public static final String TASK_LIST = "taskList";
	
	public static final String TASK_CREATE_LIST = "taskCreateList";
	
	/**
	 * 获取取数截止时间
	 * @param min 分钟
	 * @return
	 */
	public static Date getNextMinDate(int min) {
		Date nextDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(nextDate);
		c.add(Calendar.MINUTE, min);
		
		return c.getTime();
	}
}
