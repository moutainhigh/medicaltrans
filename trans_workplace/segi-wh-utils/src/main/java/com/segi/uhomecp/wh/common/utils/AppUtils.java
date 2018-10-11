package com.segi.uhomecp.wh.common.utils;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.segi.uhomecp.utils.DateUtil;
import com.segi.uhomecp.utils.UhomePropUtils;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.interfaces.InvocationHandler;

/**
 * 应用工具类
 * @author kinas
 *
 */
public class AppUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppUtils.class);

	private static String JOB_RUN_FLAG = "";
	
	/**
	 * 时间转换 如 700转换为7:00, 9030转换为9:30
	 * @param time
	 * @return
	 */
	public static String toTime(int time) {
		String temp = String.valueOf(time);
		if (time < 10 && time >= 0) {
			return "0:0" + temp;
		}
		if (time < 100 && time >= 10) {
			return "0:" + temp;
		}
		if (time < 1000 && time >= 100) {
			return temp.substring(0, 1) + ":" + temp.substring(1, 3);
		}
		if (time >= 1000) {
			return temp.substring(0, 2) + ":" + temp.substring(2, 4);
		}
		return "";
	}
	
	/**
	 * 数字转换为 yyyy-MM-dd
	 * @param yearmmdd
	 * @return
	 */
	public static String toYearmmdd(long yearmmdd) {
		if (yearmmdd < 10000000) {
			return "";
		}
		String temp = String.valueOf(yearmmdd);
		return temp.substring(0, 4) + "-" + temp.substring(4, 6) + "-" + temp.substring(6, 8);
	}
	
	/**
	 * @discription list转换Map
	 * @author wangxiong@segimail.com       
	 * @created 2017年11月7日 下午9:14:59     
	 * @param list
	 * @param keyMethodName
	 * @param c
	 * @return
	 */
    public static <K, V> Map<K, V> list2Map(List<V> list, String filedId, Class<V> c) {  
        Map<K, V> map = new HashMap<K, V>();  
        if (list != null) {  
            try {
				Method methodGetKey = c.getMethod("get" + initcap(filedId));
                for (int i = 0; i < list.size(); i++) {  
                    V value = list.get(i);  
                    @SuppressWarnings("unchecked")  
                    K key = (K) methodGetKey.invoke(list.get(i));  
                    map.put(key, value);  
                }  
                
            } catch (Exception e) {  
            	LOGGER.debug("", e);
            }  
        }  
        return map;  
    }  
    
    /**
     * @discription list转map 并收集参数为下一个查询做准备
     * @author wangxiong@segimail.com       
     * @created 2017年11月14日 上午11:06:22     
     * @param list
     * @param mapKeyField
     * @param c
     * @param params
     * @param paramsField
     * @return
     */
    public static <K, V, T> Map<K, V> list2Map(List<V> list, String mapKeyField, Class<V> c,
    		List<T> params, String paramsField) {
    	Map<K, V> map = new HashMap<K, V>();  
        if (list != null) {  
            try {
				Method methodGetKey = c.getMethod("get" + initcap(mapKeyField));
				Method methodGetParam = c.getMethod("get" + initcap(paramsField)); 
                for (int i = 0; i < list.size(); i++) {  
                    V value = list.get(i);  
                    @SuppressWarnings("unchecked")  
                    K key = (K) methodGetKey.invoke(list.get(i)); 
                    @SuppressWarnings("unchecked")
                    T param = (T) methodGetParam.invoke(list.get(i));
                    params.add(param);
                    map.put(key, value);  
                }  
            } catch (Exception e) {  
            	LOGGER.debug("", e);
            }  
        }  
        return map;  
    }  
    
    /**
     * @discription 从list抽取一个单独属性生成一个List集合
     * @author wangxiong@segimail.com       
     * @created 2017年11月14日 下午7:27:36     
     * @param list
     * @param c
     * @param paramsField
     * @return
     */
    public static <K, V> List<K> list2ParamsList(List<V> list, Class<V> c, String paramsField) {
    	List<K> params = new ArrayList<>();
        if (list != null) {  
            try {
				Method methodGetParam = c.getMethod("get" + initcap(paramsField)); 
                for (int i = 0; i < list.size(); i++) {  
                    @SuppressWarnings("unchecked")
                    K param = (K) methodGetParam.invoke(list.get(i));
                    params.add(param);
                }  
            } catch (Exception e) {  
            	LOGGER.debug("", e);
            }  
        }  
        return params;  
    }  
    
    /**
     * @discription 从list抽取一个单独属性生成一个List集合
     * @author wangxiong@segimail.com       
     * @created 2017年11月14日 下午7:27:36     
     * @param list
     * @param c
     * @param paramsField
     * @return
     */
    public static <K, V> List<K> list2ParamsList(List<V> list, InvocationHandler<K,V> handler) {  
    	List<K> params = new ArrayList<>();
        if (list != null) {  
            try {
                for (int i = 0; i < list.size(); i++) {  
                    K param = handler.invoke(list.get(i));
                    params.add(param);
                }  
            } catch (Exception e) {  
            	LOGGER.debug("", e);
            }  
        }  
        return params;  
    }
    
    /**
     * @Title: setParamsList
     *  从list抽取一个单独属性生成一个Set集合
     * @author zhaoqing  
     * @param list
     * @param handler
     * @return     
     */
    public static <K, V> Set<K> list2ParamsSet(List<V> list, InvocationHandler<K,V> handler) {  
    	Set<K> setParams = new HashSet<>();
        if (list != null) {  
            try {
                for (int i = 0; i < list.size(); i++) {  
                    K param = handler.invoke(list.get(i));
                    if (null != param) {
                    	setParams.add(param);
                    }    
                }  
            } catch (Exception e) {  
            	LOGGER.debug("", e);
            }            
        } 
        return setParams;  
    }
    
    /**
     * @discription 从list抽取一个单独属性生成一个Set集合
     * @author zhangyang@segimail.com       
     * @created 2018年8月6日 下午7:10:04     
     * @param list
     * @param c
     * @param paramsField
     * @return
     */
    public static <K, V> Set<K> list2ParamsSet(List<V> list, Class<V> c, String paramsField) {
    	Set<K> params = new HashSet<>();
        if (list != null) {  
            try {
				Method methodGetParam = c.getMethod("get" + initcap(paramsField)); 
                for (int i = 0; i < list.size(); i++) {  
                    @SuppressWarnings("unchecked")
                    K param = (K) methodGetParam.invoke(list.get(i));
                    if (null != param) {
                    	params.add(param);
                    }
                }  
            } catch (Exception e) {  
            	LOGGER.debug("", e);
            }  
        }  
        return params;  
    }     

    
    /**
	 * @discription list转换Map
	 * @author wangxiong@segimail.com       
	 * @created 2017年11月7日 下午9:14:59     
	 * @param list
	 * @param keyMethodName
	 * @param c
	 * @return
	 */
    public static <K, V> Map<K, V> list2Map(List<V> list, InvocationHandler<K,V> handler) {  
        Map<K, V> map = new HashMap<K, V>();  
        if (list != null) {  
            try {
                for (int i = 0; i < list.size(); i++) {  
                    V value = list.get(i);  
                    K key = handler.invoke(value);
                    map.put(key, value);  
                }  
            } catch (Exception e) {  
            	LOGGER.debug("", e);
            }  
        }  
        return map;  
    }  
    
    /**
     * @discription List分组放入Map
     * @author wangxiong@segimail.com       
     * @created 2017年11月17日 下午7:37:21     
     * @param list
     * @param souceC
     * @param field
     * @param targetC
     * @return
     */
    public static <K, V, I> Map<K, List<I>> listGroup2Map(List<V> list, InvocationHandler<K,V> handler, Class<V> souceC, Class<I> targetC ) {
    	Map<K,List<I>> rstMap = new HashMap<>();
		List<I> rstList = null;
		if( list != null ) {
			try {
				for (int i = 0; i < list.size(); i++) {  
					I target = BeanCopierUtils.copyProperties(list.get(i), targetC, true);
					K param =  handler.invoke(list.get(i));
					rstList = rstMap.get(param);
					if(rstList == null ) {
						rstList = new ArrayList<>();
						rstMap.put(param, rstList);
					}
					rstList.add(target);
				}
			 } catch (Exception e) {  
				 LOGGER.debug("", e);
	         }  
		}
		return rstMap;
    }
    
    
    /**
     * @discription List分组放入Map
     * @author wangxiong@segimail.com       
     * @created 2017年11月17日 下午7:37:21     
     * @param list
     * @param souceC
     * @param field
     * @param targetC
     * @return
     */
    public static <K, V, I> Map<K, List<I>> listGroup2Map(List<V> list, Class<V> souceC, String field, Class<I> targetC) {
    	Map<K, List<I>> rstMap = new HashMap<>();
		List<I> rstList = null;
		if (list != null) {
			try {
				for (int i = 0; i < list.size(); i++) {  
					I target = BeanCopierUtils.copyProperties(list.get(i), targetC, true);
					Method methodGetParam = souceC.getMethod("get" + initcap(field)); 
					@SuppressWarnings("unchecked")
				    K param = (K) methodGetParam.invoke(list.get(i));
					rstList = rstMap.get(param);
					if (rstList == null) {
						rstList = new ArrayList<>();
						rstMap.put(param, rstList);
					}
					rstList.add(target);
				}
			 } catch (Exception e) {  
				 LOGGER.debug("", e);
	         }  
		}
		return rstMap;
    }
    
    /**
     * @discription list 转换成 string
     * @author wangxiong@segimail.com       
     * @created 2017年11月20日 下午3:11:53     
     * @param list
     * @param separator
     * @return
     */
    public static String listToString(List<?> list, char separator) {
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < list.size(); i++) {
			if (i == list.size() - 1) {
    			sb.append(list.get(i).toString());
    		} else { 
    			sb.append(list.get(i).toString());
    			sb.append(separator); 
    		}
    	}
    	return sb.toString();
    }

    /**
     * @discription 字符串以逗号分隔转换成List<Integer>
     * @author wangxiong@segimail.com       
     * @created 2017年11月15日 下午6:02:57     
     * @param str
     * @return
     */
    public static List<Integer> str2Integer(String str) {  
    	List<Integer> list = new ArrayList<>();
        StringTokenizer toKenizer = new StringTokenizer(str, ",");   
        while (toKenizer.hasMoreElements()) {   
        	String tmp = toKenizer.nextToken();
        	if (StringUtils.isNotEmpty(tmp)) {
				list.add(Integer.valueOf(tmp));
			}
        }   
        return list;  
    } 
    
    /**
     * @discription 字符串以逗号分隔转换成List<String>
     * @author wangxiong@segimail.com       
     * @created 2017年11月15日 下午6:02:57     
     * @param str
     * @return
     */
    public static List<String> str2List(String str) { 
    	List<String> list = new ArrayList<>();
    	if(StringUtils.isBlank(str)) {
    		// 如果是空直接返回
    		return list;
    	}
        StringTokenizer toKenizer = new StringTokenizer(str, ",");   
        while (toKenizer.hasMoreElements()) {   
        	String tmp = StringUtils.trim(toKenizer.nextToken());
        	if (StringUtils.isNotBlank(tmp)) {
        		list.add(tmp);
			}
        }   
        return list;  
     }
    
	/**
     * 把输入字符串的首字母改成大写
     * @param str
     * @return
     */
    public static String initcap(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
    
    /**
     * @discription 获取一种权限    0：读  1：写 2：表示删除 
     * @author wangxiong@segimail.com       
     * @created 2017年12月8日 下午5:06:35     
     * @param digit
     * @return
     */
    public static List<Short> getCertainAuth(int digit) {
    	List<Short> rst = new ArrayList<>();
    	for (short j = 0; j < 8; j++) {
			if ((j & (1 << digit)) != 0) {
				rst.add(Short.valueOf(j));
			}
		}
    	return rst;
    }
    
    /**
     * @discription 二进制转十进制
     * @author wangxiong@segimail.com       
     * @created 2017年12月8日 下午5:06:35     
     * @param digit
     * @return
     */
    public static Short binary2Decimal(String read, String write, String delete) {
    	StringBuffer sb = new StringBuffer(read).append(write).append(delete);
    	String binaryReg = "^[01]+$";
		if (Pattern.matches(binaryReg, sb.toString())) {
    		return Short.parseShort(sb.toString(), 2);
    	}
    	return null;
    }
    
    /**
     * @discription 十进制转二进制
     * @author wangxiong@segimail.com       
     * @created 2017年12月8日 下午5:06:35     
     * @param digit
     * @return
     */
    public static Map<String, String> decimal2RwdAuth(Short rwPermiss) {
    	Map<String, String> rst = new HashMap<>();
		rst.put("read", (rwPermiss & (1 << 0)) == 0 ? "0" : "1");
		rst.put("write", (rwPermiss & (1 << 1)) == 0 ? "0" : "1");
		rst.put("delete", (rwPermiss & (1 << 2)) == 0 ? "0" : "1");
    	return rst;
    }
    
    /**
     * 判断集合是否为空
     * @return
     */
    public static boolean isNotEmpty(Collection<?> list) {
    	if (null == list || list.isEmpty()) {
    		return false;
    	}
    	return true;
    }
    
    /**
     * 判断Map集合是否为空
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> boolean isNotEmpty(Map<K, V> map) {
    	if (null == map || map.isEmpty()) {
    		return false;
    	}
    	return true;
    }
    
    /**
     * 查询2个集合不同的元素
     * @param source
     * @param target
     * @return
     */
    public static <T> Collection<T> findExists(List<T> source, List<T> target) {
    	Collection<T> exists = new ArrayList<>(source);
    	exists.removeAll(target);
    	
    	return exists;
    }
    
    /**
     * 文件大小转换
     * @param fileSize
     * @return
     */
    public static String getFileSize(Long fileSize) {
		String size = ""; 
		DecimalFormat df = new DecimalFormat("#.00"); 
		if (fileSize < 1024) {
			size = df.format((double) fileSize) + "BT";
		} else if (fileSize < 1048576) {
			size = df.format( (double) fileSize / 1024) + "KB";
		} else if (fileSize < 1073741824) {
			size = df.format( (double) fileSize / 1048576) + "MB";
		} else {
			size = df.format( (double) fileSize / 1073741824) + "GB";
		}
		return size;
	}
    
	/**
	 * @discription 获取文件名最后一个.后面的结尾
	 * @author wangxiong@segimail.com       
	 * @created 2017年12月14日 上午10:20:27     
	 * @param fileName
	 * @return
	 */
    public static String getExtensionName(String filename) {   
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1).toUpperCase();
			}
		} 
        return "";   
    }  
    
    /**
	 * @discription 判断日期和要比较的日期是否在限定天数内
	 * @author zhangyang@segimail.com       
	 * @created 2018年1月19日 下午1:37:53     
	 * @param date
	 * @param compareDate
	 * @param days
	 * @return
	 */
	public static boolean compareDayInDays(Date date, Date compareDate, int days) {
		if (DateUtil.compareDay(date, compareDate, days)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @discription 判断日期(Number类型)和指定日期(可定义格式)是否在限定天数内
	 * @author zhangyang@segimail.com       
	 * @created 2018年1月19日 下午1:39:56     
	 * @param dateNum  --Number类型的日期
	 * @param dateFormat  --2个要比较日期的转换格式要一致 注意:前面Number类型日期如果为20180206 转换格式必须为yyyyMMdd 
	 * @param days   --2个日期相差的天数   注意:days + 1,可以准确的比较2个日期的相差天数
	 * @return
	 */
	public static boolean compareDayInDays(Number dateNum, String dateFormat, int days) {
		try {
			if (dateNum != null) {
				Date date = DateUtil.parseStringToDate(String.valueOf(dateNum), dateFormat);
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				Date compareDate = sdf.parse(sdf.format(new Date()));
				return compareDayInDays(date, compareDate, days + 1);
			}
		} catch (Exception e) {
			LOGGER.warn("compareDayInDays", e);
		}
		return false;
	}
	
	/**
	 * @discription 获取指定年指定月的最后一天日期
	 * @author zhangyang@segimail.com       
	 * @created 2018年1月19日 上午9:43:10     
	 * @param year
	 * @param month
	 * @return YYYYMMdd
	 */
	public static String getLastDayOfMonth(String year, String month) {  
        Calendar cal = Calendar.getInstance();  
        //设置年份  
        cal.set(Calendar.YEAR, Integer.valueOf(year));  
        //设置月份  
        cal.set(Calendar.MONTH, Integer.valueOf(month) - 1);  
        //获取某月最大天数  
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);  
        //设置日历中月份的最大天数  
        cal.set(Calendar.DAY_OF_MONTH, lastDay);  
        //格式化日期  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
        String lastDayOfMonth = sdf.format(cal.getTime());  
        return lastDayOfMonth;  
    }  
	
	/**
	 * @discription 获取指定年指定月的第一天日期
	 * @author zhangyang@segimail.com       
	 * @created 2018年1月24日 下午8:25:05     
	 * @param year
	 * @param month
	 * @return YYYYMMdd
	 */
	public static String getFirstDayOfMonth(String year, String month) {
		int length = month.length();
		String firstDayOfMonth = null;
		if (length == 1) {
			firstDayOfMonth = year + "0" + month + "01";
			return firstDayOfMonth;
		} else if (length == 2) {
			firstDayOfMonth = year + month + "01";
			return firstDayOfMonth;
		}
		return firstDayOfMonth;
	}
	
	/**
	 * @discription 正则表达式验证字符串
	 * @author wangxiong@segimail.com       
	 * @created 2018年1月25日 下午5:07:24     
	 * @param regex
	 * @param str
	 * @return
	 */
	public static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 
	 * @Description:  修改费用格式单位分改成单位园
	 * liuyi@segimail.com
	 * 2018年2月1日 下午6:22:39
	 * @throws
	 */
	public static String moneryFormat(String fee){
	    if (StringUtils.isNotEmpty(fee)) {
	        Long totalFee100 = Long.valueOf(fee);
	        double num= (double)totalFee100/100;   
	        DecimalFormat df = new DecimalFormat("0.00");
	        return df.format(num); 
        }
	    return "0.00";
	}
	
	/**
     * @discription JOB开关设置   不是关闭的其他都是打开的
     * @author wangxiong@segimail.com       
     * @created 2018年1月21日 上午11:29:18     
     * @return
     */
    public static Boolean verfiyJobRun() {
    	if(StringUtils.isEmpty(JOB_RUN_FLAG)) {
    		JOB_RUN_FLAG = UhomePropUtils.getProperty("JOB_RUN_FLAG");
    	}
    	if(!Constant.STATUS_CD_STOP.equals(JOB_RUN_FLAG)) {
    		return true;
    	}
    	return false;
    }
    
    /**
	 * @Title: spanMonthCount   
	 *  计算两个日期间跨越的月数（不包含结束日期的月份）
	 * @author zhaoqing  
	 * @param beginDate
	 * @param endDate
	 * @return 
	 */
	public static int spanMonthCount(Date beginDate, Date endDate) {
		String beginDateStr = DateUtil.formatDateToStringYYYYMMDD(beginDate);
		String endDateStr = DateUtil.formatDateToStringYYYYMMDD(endDate);
		int beginYear = Integer.parseInt(beginDateStr.substring(0, 4));
		int beginMonth = Integer.parseInt(beginDateStr.substring(4, 6));
		int endYear = Integer.parseInt(endDateStr.substring(0, 4));
		int endMonth = Integer.parseInt(endDateStr.substring(4, 6));
		int differMonth = (endYear - beginYear) * 12 + (endMonth - beginMonth);	
		return differMonth;
	}
	
	/*
	 * @discription 判断日期(Number类型)和当前时间是否在限定月份内
	 * @author zhangyang@segimail.com       
	 * @created 2018年3月13日 上午10:56:44     
	 * @param dateNum  YYYYMMDD格式的
	 * @param months   月份     
	 * 如 ：当前时间是2018316 减除两个月，后和dateNum进行比较 
	 *  如果是20180116就返回true，
	 *  如果是20180115就返回false 
	 *  如果是20180117就返回true
	 * @return
	 */
	public static boolean compareDateYYYYMMDDInMonths(Number dateNum, int months) {
		if (dateNum == null ) {
			return false;
		}
		try {
			//YYYYMMdd转Date
			Long dateTime = DateUtil.parseStringToDateYYYYMMDD(String.valueOf(dateNum)).getTime();
			//处理当前时间
			Calendar c = Calendar.getInstance();
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);  //将分钟置0  
			c.set(Calendar.SECOND,0);  //将秒置0  
			c.set(Calendar.MILLISECOND, 0);//将毫秒置0  
			c.add(Calendar.MONTH,-months);
			Long curMonthDate = c.getTime().getTime();
			if (dateTime != null && dateTime.longValue() != 0
					&& curMonthDate != null && curMonthDate.longValue() != 0
					&& dateTime.longValue() >= curMonthDate.longValue()) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.warn("compareDateYYYYMMDDInMonths", e);
		}
		return false;
	}
	
	/**
	 * 占位符格式化
	 * @param messagePattern
	 * @param argArray
	 * @return
	 */
	final public static String messageFormatter(final String messagePattern,
		      final Object... argArray) {
		return MessageFormat.format(messagePattern, argArray);
	}
	
	/**
	 * 数字转换为 yyyy-MM-dd HH:mm
	 * @param yearmmdd
	 * @return
	 */
	public static String toYearmmddhhmm(long yearmmddhhmm) {
		if (yearmmddhhmm < 100000000000l) {
			return "";
		}
		String temp = String.valueOf(yearmmddhhmm);
		return temp.substring(0, 4) + "-" + temp.substring(4, 6) + "-" + temp.substring(6, 8)
				+ " " + temp.substring(8, 10) + ":" + temp.substring(10, 12);

	}
	
	/**
	 * 是否包含
	 * @param regex
	 * @param str
	 * @return
	 */
	public static boolean isContainsStr(String regex, String str){
        return Pattern.compile(regex).matcher(str).find();
	}
	
}
