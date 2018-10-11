package com.segi.uhomecp.wh.common.utils;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;
  
/**
 * Title: NumberUtils.java    
 * @Description: 数字相关的工具类
 * @author wangxiong@segimail.com       
 * @created 2017年12月13日 上午10:03:04
 */
public class NumberUtils {  
  
	private final static String DOUBLE_00_FORMART = "######0.00";
	
    /** 
     * 判断当前值是否为整数 
     * @param value 
     * @return 
     */  
    public static boolean isInteger(Object value) {  
        if (StringUtils.isEmpty(value)) {  
            return false;  
        }  
        String mstr = value.toString();  
        Pattern pattern = Pattern.compile("^-?\\d+{1}");  
        return pattern.matcher(mstr).matches();  
    }  
  
    public static boolean isDigits(String str) {
    	return org.apache.commons.lang3.math.NumberUtils.isDigits(str);
    }
    
	 /** 
	  * 将值转成Integer型，如果不是整数，则返回0 
	  *  
	  * @param value 
	  * @param replace 
	  *            如果为0或者null，替换值 
	  * @return 
	  */  
	 public static Integer parseInteger(Object value, Integer defaultValue) {  
	     if (!NumberUtils.isInteger(value)) {  
	         return defaultValue;  
	     }  
	     return new Integer(value.toString());  
	 }  

	 /**
	  * @discription 验证非零的正整数
	  * @author wangxiong@segimail.com       
	  * @created 2018年1月25日 下午5:16:09     
	  * @param str
	  * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	  */
	 public static boolean IsIntNumber(String str) {
		 String regex = "^\\+?[1-9][0-9]*$";
		 return AppUtils.match(regex, str);
	 }
	 
	 /**
	 * 验证输入两位小数
	 * 
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	 public static boolean IsDecimal(String str) {
		 return IsDecimal(str, 2);
	 }
	 
	 /**
	 * 验证输入digit位小数
	 * @param 待验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	 public static boolean IsDecimal(String str, int digit) {
		 StringBuffer regex = new StringBuffer("^[0-9]+(.[0-9]{").append(digit).append("})?$");
		 return AppUtils.match(regex.toString(), str);
	 }
	 
	 /**
	  * 
	  * 方法描述: Double类型转换成String类型
	  * 创建人: liuyi@segimail.com    
	  * 创建时间: 2018年3月1日下午7:57:08
	  * 修改人:     
	  * 修改时间:  
	  * 修改备注: [说明本次修改内容]
	  * 版本: v1.0
	  */
    public static String double2Str(Double num, String format) {
        DecimalFormat df = new DecimalFormat(format);
        return df.format(num);
    }
    
    /**
     * @discription 默认展示两个小数点的数据
     * @author wangxiong@segimail.com       
     * @created 2018年3月1日 下午8:21:25     
     * @param num
     * @return
     */
    public static String double2Str(Double num) {
        return double2Str(num, DOUBLE_00_FORMART);
    }
}
