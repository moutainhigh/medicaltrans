package com.segi.uhomecp.wh.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.segi.uhomecp.utils.DateUtil;
/**
 * 时间格式转换工具
 * @author Administrator
 *
 */
public class DataTypeConverUtils {

	/**
	 * 获取日期为YYYYMMDDHHMMSS格式 Long
	 * 
	 * @return
	 */
	public static Long formatDateToLongYYYYMMDDHHMMSS(Date date) {
		String dateStr = DateUtil.formatDateToString(date,
				DateUtil.FORMAT_YYYYMMDDHHMMSS);
		if (StringUtils.isEmpty(dateStr)) {
			return new Long(0L);
		}
		return Long.parseLong(dateStr);
	}

	/**
	 * 获取日期为YYYYMMDDHHMM格式 Long
	 * 
	 * @return
	 */
	public static Long formatDateToLongYYYYMMDDHHMM(Date date) {
		String dateStr = DateUtil.formatDateToString(date,
				DateUtil.FORMAT_YYYY_MM_DD_HH_MM);
		if (StringUtils.isEmpty(dateStr)) {
			return new Long(0L);
		}
		return Long.parseLong(dateStr);
	}
	/**
	 * 根据Long为YYYYMMDDHHMMSS格式 转换成 String
	 * 
	 * @return
	 */
	public static Date parseLongToDate(Long dateLong) {
		if (dateLong != null && dateLong.intValue() != 0) {
			return DateUtil
					.parseStringToDateYYYYMMDDHHMMSS(dateLong.toString());
		}
		return null;
	}

	/**
	 * @discription Long时间转换为String
	 * @author wangfan@segimail.com
	 * @created 2017年10月17日 下午4:55:09
	 * @param dateLong
	 * @param pattern
	 * @return
	 */
	public static String paresLongToString(Long dateLong, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = parseLongToDate(dateLong);
		if (date == null) {
			return null;
		}
		String dateString = sdf.format(date);
		return dateString;
	}

	/**
	 * 
	 * @param num          
	 * @param parseFormat  解析格式 
	 * @param outFormat    输出格式
	 * @return
	 */
	public static String paresNumberToString(Number num, String parseFormat, String outFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(outFormat);
		Date date = DateUtil.parseStringToDate(String.valueOf(num), parseFormat);
		if (date == null) {
			return null;
		}
		String dateString = sdf.format(date);
		return dateString;
	}
	
	/**
	 * 根据Long为YYYYMMDD格式 转换成 String
	 * 
	 * @return
	 */
	public static Date parseLongToDateYYYYMMDD(Long dateLong) {
		if (dateLong != null && dateLong.intValue() != 0) {
			return DateUtil.parseStringToDateYYYYMMDD(dateLong.toString());
		}
		return null;
	}

	/**
	 * @discription Long日期转换为String
	 * @author wangfan@segimail.com
	 * @created 2017年11月14日 上午11:48:47
	 * @param dateLong
	 * @param pattern
	 * @return
	 */
	public static String paresLongToStringYYYYMMDD(Long dateLong, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = parseLongToDateYYYYMMDD(dateLong);
		if (date == null) {
			return null;
		}
		String dateString = sdf.format(date);
		return dateString;
	}

	/**
	 * @discription 解析Number 返回格式化 YYYY-MM-DD 时间
	 * @author wangxiong@segimail.com
	 * @created 2017年11月22日 上午11:58:01
	 * @param dateNumber
	 * @return
	 */
	public static Date parseNumberToDateYYYYMMDD(Number dateNumber) {
		if (dateNumber != null && dateNumber.intValue() != 0) {
			return DateUtil.parseStringToDateYYYYMMDD(dateNumber.toString());
		}
		return null;
	}

	/**
	 * @discription Long日期转换为String
	 * @author wangfan@segimail.com
	 * @created 2017年11月14日 上午11:48:47
	 * @param dateLong
	 * @param pattern
	 * @return
	 */
	public static String paresNumberToStringYYYYMMDD(Number dateNumber, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = parseNumberToDateYYYYMMDD(dateNumber);
		if (date == null) {
			return null;
		}
		String dateString = sdf.format(date);
		return dateString;
	}
	
	/**
	 * @discription Long日期转换为String
	 * @author wangfan@segimail.com
	 * @created 2017年11月14日 上午11:48:47
	 * @param dateLong
	 * @param pattern
	 * @return
	 */
	public static String paresNumberToStringYYYYMMDDHHMISS(Number dateNumber, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = parseNumberToDateYYYYMMDDHHMISS(dateNumber);
		if (date == null) {
			return null;
		}
		String dateString = sdf.format(date);
		return dateString;
	}
	/**
	 * @discription 解析Number 返回格式化 YYYY-MM-DD 时间
	 * @author wangxiong@segimail.com
	 * @created 2017年11月22日 上午11:58:01
	 * @param dateNumber
	 * @return
	 */
	public static Date parseNumberToDateYYYYMMDDHHMISS(Number dateNumber) {
		if (dateNumber != null && dateNumber.intValue() != 0) {
			return DateUtil.parseStringToDateYYYYMMDDHHMMSS(dateNumber.toString());
		}
		return null;
	}
	
	
}
