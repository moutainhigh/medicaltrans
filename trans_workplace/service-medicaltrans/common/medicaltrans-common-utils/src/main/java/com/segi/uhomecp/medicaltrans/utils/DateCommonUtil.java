package com.segi.uhomecp.medicaltrans.utils;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.segi.uhomecp.wh.common.utils.DateUtil;

public class DateCommonUtil extends com.segi.uhomecp.utils.DateUtil{
	/**
	 * @discription 获得传入日期的本月第一天0点时间
	 * @author yangyh@segimail.com       
	 * @created 2018年5月15日 下午4:42:40     
	 * @return
	 */
	public static Date getMonthFirstDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
	
	/**
	 * @discription 获得传入日期的本月最后一天23：59：59时间
	 * @author yangyh@segimail.com       
	 * @created 2018年6月27日 下午17:33:40     
	 * @return
	 */
	public static Date getMonthLastDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
	
	/**
	 * @Title: getYesterDayLastMoment   
	 *  获取传入日期前一天的23:59:59的日期 
	 * @author zhaoqing  
	 * @return 
	 */
	public static Date getYesterDayLastMoment(Date date) {
		Date yesterDay = DateUtil.addDateDay(date, -1);
		String yesterDayEndDate = DateUtil.convertEndDate(
				DateUtil.formatDateToStringYYYYMMDD(yesterDay));
		return DateUtil.parseStringToDateYYYYMMDDHHMMSS(yesterDayEndDate);
	}
	
	/**
	 * @Title: getFirstTimeOfMonthMM   
	 *  获取月份第一天的00:00 
	 * @author zhaoqing  
	 * @param cycleYm
	 * @return 
	 */
	public static Long getFirstTimeOfMonthMM(String cycleYm) {
		if (StringUtils.isEmpty(cycleYm) || cycleYm.length() < 6) {
			return null;
		}
		Integer year = Integer.valueOf(DateUtil.getYearOfDate(cycleYm));
		Integer month = Integer.valueOf(DateUtil.getYearMonthOfDate(cycleYm));
		String beginDate = DateUtil.getFirstTimeOfMonth(year, month);
		return Long.valueOf(beginDate);
	}
	
	/**
	 * @Title: getLastTimeOfMonthMM   
	 *  获取月份最后一天的23:59  
	 * @author zhaoqing  
	 * @param cycleYm
	 * @return 
	 */
	public static Long getLastTimeOfMonthMM(String cycleYm) {
		if (StringUtils.isEmpty(cycleYm) || cycleYm.length() < 6) {
			return null;
		}
		Integer year = Integer.valueOf(DateUtil.getYearOfDate(cycleYm));
		Integer month = Integer.valueOf(DateUtil.getYearMonthOfDate(cycleYm));
		String endDate = DateUtil.getLastTimeOfMonth(year, month);
		return Long.valueOf(endDate);
	}
	
	/**
	 * @Title: getWeekOfDate   
	 *  获取日期的星期 
	 * @author zhaoqing  
	 * @param date
	 * @return 1：星期一， 2：星期二 ... 7：星期天
	 */
	public static String getWeekOfDate(Date date) {
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return String.valueOf(w);
	}
	
	/**
	 * @Title: getWeekOfMonth   
	 *  获取日期是所属月份的第几周（日历式的第几周） 
	 * @author zhaoqing  
	 * @param date
	 * @return  
	 */
	public static Integer getWeekOfMonth(Date date) {
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_MONTH);
	}
	
	/**
	 * @Title: getMonthDay   
	 *  获取日期的月日 
	 * @author zhaoqing  
	 * @param dateStr
	 * @return      
	 */
	public static String getMonthDay(String dateStr) {
		if (StringUtils.isNotEmpty(dateStr) && dateStr.length() >= 8) {
			// 返回截取的月日
			return StringUtils.substring(dateStr, 4, 8);
		}
		return null;
	}
	
	/**
	 * @Title: getMonthDay   
	 *  获取日期的月日 
	 * @author zhaoqing  
	 * @param date
	 * @return      
	 */
	public static String getMonthDay(Date date) {
		return getMonthDay(DateUtil.formatDateToStringYYYYMMDD(new Date()));
	}
	
}
