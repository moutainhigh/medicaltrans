package com.segi.uhomecp.wh.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class DateUtil extends com.segi.uhomecp.utils.DateUtil{
	/**
	 * @discription 获得本月第一天0点时间
	 * @author yangyh@segimail.com       
	 * @created 2018年5月15日 下午4:42:40     
	 * @return
	 */
	public static Date getMonthFirstDay() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	/**
	 * 在date情况下加多少分钟
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date addDateMinute(Date date, int minute) {
		if (date == null) {
			return null;
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MINUTE, minute);
			return calendar.getTime();
		}
	}
	
	/**
	 * 获取当前的几点几分几秒
	 * @param hours
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Date getCurDayHHmmss(int hours, int minute , int second) {
		// 毫秒默认值为0
		return getCurDayHHmmss(hours, minute, second, 0);
	}
	
	/**
	 * 获取当前的几点几分几秒
	 * @param hours
	 * @param minute
	 * @param second
	 * @param 毫秒
	 * @return
	 */
	public static Date getCurDayHHmmss(int hours, int minute ,
			int second, int millisecond) {
		 Calendar calendar = Calendar.getInstance();
		 calendar.set(Calendar.HOUR_OF_DAY, hours);
		 calendar.set(Calendar.MINUTE, minute);
		 calendar.set(Calendar.SECOND, second);
		 calendar.set(Calendar.MILLISECOND, millisecond);
		 return calendar.getTime();
	}
	
	/**
	 * 获取某月第一天的00：00
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getFirstTimeOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //防止月份有31号的错误，先设置1号。
        cal.set(Calendar.DAY_OF_MONTH, 1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
	}
	
	/**
	 * 获取当月的第一天的00：00
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getFirstTimeOfNewMonth(){
		Calendar cal = Calendar.getInstance();
        //防止月份有31号的错误，先设置1号。
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
	}
	
	/**
	* 获得该月最后一天23：59
	* @param year
	* @param month
	* @return
	*/
	public static String getLastTimeOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //防止月份有31号的错误，先设置1号。
        cal.set(Calendar.DAY_OF_MONTH, 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE,59);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
	}
	
	/**
	 * 获取某月第一天的00:00:00
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getFirstTimeOfMonthYYYYMMDDHHMMSS(int year, int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //防止月份有31号的错误，先设置1号。
        cal.set(Calendar.DAY_OF_MONTH, 1);
        //获取某月最小天数
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String firstDayOfMonth = sdf.format(cal.getTime());
        return firstDayOfMonth;
	}
	
	/**
	* 获得该月最后一天23:59:59
	* @param year
	* @param month
	* @return
	*/
	public static String getLastTimeOfMonthYYYYMMDDHHMMSS(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //防止月份有31号的错误，先设置1号。
        cal.set(Calendar.DAY_OF_MONTH, 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,59);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
	}
	
	/**
	 * 获取某月第一天的00:00:00
	 * @param cycleYm
	 * @return
	 */
	public static String getFirstTimeOfMonthYYYYMMDDHHMMSS(String cycleYm){
		String year = getYearOfDate(cycleYm);
		String month = getMonthOfDate(cycleYm);
		if (StringUtils.isNotEmpty(year) && StringUtils.isNotEmpty(month)) {
			return getFirstTimeOfMonthYYYYMMDDHHMMSS(Integer.parseInt(year), Integer.parseInt(month));
		}
        return null;
	}
	
	/**
	* 获得该月最后一天23:59:59
	* @param cycleYm
	* @return
	*/
	public static String getLastTimeOfMonthYYYYMMDDHHMMSS(String cycleYm){
		String year = getYearOfDate(cycleYm);
		String month = getMonthOfDate(cycleYm);
		if (StringUtils.isNotEmpty(year) && StringUtils.isNotEmpty(month)) {
			return getLastTimeOfMonthYYYYMMDDHHMMSS(Integer.parseInt(year), Integer.parseInt(month));
		}
        return null;	
	}
	
	/**
	* 获得当前时间前一天的23：59
	* @param year
	* @param month
	* @return
	*/
	public static String getNewDayBeforeDayTime(int year,int month){
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE,59);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
	}
	
	/**
	 * 获取某月的天数
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
	}
	
	/**
	 * @Title: isTwoYearsBefore   
	 *  判断日期是否是两年前的日期 
	 * @author zhaoqing  
	 * @param date
	 * @return 日期是两年前的日期返回true, 否则返回false
	 */
	public static boolean isTwoYearsBefore(Date date) {
		return isTwoYearsBefore(DateUtil.formatDateToStringYY_MM_DD(date));
	}
	
	/**
	 * @Title: isTwoYearsBefore   
	 *  判断日期是否是两年前的日期 
	 * @author zhaoqing  
	 * @param dateStr
	 * @return 日期是两年前的日期返回true, 否则返回false
	 */
	public static boolean isTwoYearsBefore(String dateStr) {
		if (StringUtils.isEmpty(dateStr) || dateStr.length() < 4) {
			return false;
		}
		String tagetYear = getYearOfDate(dateStr);
		String nowDateYear = getYearOfDate(new Date());
		int tagetYearInt = Integer.parseInt(tagetYear);
		int nowDateYearInt = Integer.parseInt(nowDateYear);
		if ((nowDateYearInt - tagetYearInt) >= 2) {
			return true;
		}
		return false;
	}
	
	/**
	 * @Title: isMoreThanThreeMonths   
	 *  判断开始时间和结束时间的跨度是否超过months个月
	 * @author zhaoqing  
	 * @param beginTime
	 * @param endTime
	 * @return 开始时间和结束时间跨度超过 months个月，返回true, 否则返回false
	 */
	public static boolean isMoreThanMonths(Date beginTime, Date endTime, int months) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginTime);
		cal.add(Calendar.MONTH, 3);
		Date beginDate = cal.getTime();
		if (beginDate.before(endTime)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @Title: isMoreThanMonths   
	 *  判断开始时间和结束时间的跨度是否超过months个月 
	 * @author zhaoqing  
	 * @param beginTimeStr 格式: YYYY-MM-DD
	 * @param endTimeStr 格式: YYYY-MM-DD
	 * @param months
	 * @return 开始时间和结束时间跨度超过 months个月，返回true, 否则返回false
	 */
	public static boolean isMoreThanMonths(String beginTimeStr, String endTimeStr, int months) {
		Date beginTime = DateUtil.parseStringToDateYYYYMMDD(beginTimeStr);
		Date endTime = DateUtil.parseStringToDateYYYYMMDD(endTimeStr);
		return isMoreThanMonths(beginTime, endTime, months);
	}
	
	/**
	 * @Title: getYearOfDate   
	 *  获取日期的年份 
	 * @author zhaoqing  
	 * @param dateStr
	 * @return
	 */
	public static String getYearOfDate(String dateStr) {
		if (StringUtils.isNotEmpty(dateStr) && dateStr.length() >= 4) {
			return dateStr.substring(0, 4);
		}
		return null;
	}
	
	/**
	 * @Title: getYearOfDate   
	 *  获取日期的年份  
	 * @author zhaoqing  
	 * @param date
	 * @return
	 */
	public static String getYearOfDate(Date date) {
		return getYearOfDate(DateUtil.formatDateToStringYYYYMMDD(date));
	}
	
	/**
	 * @Title: getYearMonthOfDate   
	 *  获取日期的年月 
	 * @author zhaoqing  
	 * @param dateStr
	 * @return 返回的年月格式为：YYYYMM
	 */
	public static String getYearMonthOfDate(String dateStr) {
		dateStr = converDateToNum(dateStr);
		if (StringUtils.isNotEmpty(dateStr) && dateStr.length() >= 6) {
			return dateStr.substring(0, 6);
		}
		return null;
	}
	
	/**
	 * @Title: getMonthOfDate   
	 *  获取月份
	 * @author zhaoqing  
	 * @param dateStr
	 * @return      
	 */
	public static String getMonthOfDate(String dateStr) {
		dateStr = converDateToNum(dateStr);
		if (StringUtils.isNotEmpty(dateStr) && dateStr.length() >= 6) {
			return dateStr.substring(4, 6);
		}
		return null;
	}
	
	/**
	 * @Title: converDateToNum   
	 *  日期字符串转成数字
	 * @author zhaoqing  
	 * @param dateStr
	 * @return 
	 */
	public static String converDateToNum(String dateStr) {
		if (StringUtils.isNotEmpty(dateStr)) {
			dateStr = StringUtils.replace(dateStr, "-", "");
			dateStr = StringUtils.replace(dateStr, ":", "");
			return StringUtils.replace(dateStr, " ", "");
		}
		return null;
	}
	
	/**
	 * @Title: isTimeDifferYears   
	 *  开始时间和结束时间是否跨年
	 * @author zhaoqing  
	 * @param beginTimeStr
	 * @param endTimeStr
	 * @return 开始时间和结束时间跨年返回true, 否则返回false
	 */
	public static boolean isTimeDifferYears(String beginTimeStr, String endTimeStr) {
		String beginTimeYear = getYearOfDate(beginTimeStr);
		String endTimeYear = getYearOfDate(endTimeStr);
		if (!beginTimeYear.equals(endTimeYear)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @Title: checkCycleYm   
	 *  判断年月是否有效(格式为"201808"样式的为有效)
	 * @author zhaoqing  
	 * @param cycleYm
	 * @return 有效返回true,无效返回false
	 */
	public static boolean checkCycleYm(Integer cycleYm) {
		if (cycleYm == null || cycleYm.toString().length() < 6) {
			return false;
		}
		return true;
	}
	
	/**
     * @discription 获取String类型日期之间的日期
     * @author zhangyang@segimail.com       
     * @created 2018年8月16日 下午2:21:27     
     * @param beginDate
     * @param endDate
     * @param dateFormat
     * @return
     */
	public static List<String> getStrDateBetween(String beginDate, String endDate, String dateFormat) {
		ArrayList<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	    try {
	    	return getDateBetween(sdf.parse(beginDate), sdf.parse(endDate), dateFormat);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	    
	}
	
	/**
	 * @discription 获取Date类型日期之间的日期
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月16日 下午2:22:04     
	 * @param beginDate
	 * @param endDate
	 * @param dateFormat
	 * @return
	 */
	public static List<String> getDateBetween(Date beginDate, Date endDate, String dateFormat) {
		ArrayList<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	 
	    Calendar begin = Calendar.getInstance();
	    Calendar end = Calendar.getInstance();
	 
    	begin.setTime(beginDate);
    	begin.set(begin.get(Calendar.YEAR), begin.get(Calendar.MONTH), 1);
    	end.setTime(endDate);
    	end.set(end.get(Calendar.YEAR), end.get(Calendar.MONTH), 2);
	    Calendar curr = begin;
	    while (curr.before(end)) {
		    result.add(sdf.format(curr.getTime()));
		    curr.add(Calendar.MONTH, 1);
	    }
	    return result;
	}

	/**
	 * @Title: isFirstMonthOfYear   
	 *  判断时间的月份是否是一月 
	 * @author zhaoqing  
	 * @param dateStr
	 * @return 是一月返回true, 不是则返回false
	 */
	public static boolean isFirstMonthOfYear(String dateStr) {
		String month = getMonthOfDate(dateStr);
		if ("01".equals(month)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @Title: isLastMonthOfYear   
	 *  判断时间的月份是否是十二月  
	 * @author zhaoqing  
	 * @param dateStr
	 * @return 是十二月返回true, 不是则返回false  
	 */
	public static boolean isLastMonthOfYear(String dateStr) {
		String month = getMonthOfDate(dateStr);
		if ("12".equals(month)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 返回当天最小值
	 * @param dt
	 * @return
	 */
	public static Date getMinTime(Date dt) {
		Date dt1 = null;
		dt1 = DateUtil.parseStringToDateYYMMDD(DateUtil.formatDateToString(dt, "yyyy-MM-dd"));
		return dt1;
	}
	
	/**
	 * 上月第一天 00：00：00 0
	 * @return
	 */
	public static Date getPreviousMonthFirstDay() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1号
		return getMinTime(lastDate.getTime());
	}
	
	/**
	 * 当前月第一天 00：00：00 0
	 * @return
	 */
	public static Date getCurrentMonthFirstDay() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		return getMinTime(lastDate.getTime());
	}
}
