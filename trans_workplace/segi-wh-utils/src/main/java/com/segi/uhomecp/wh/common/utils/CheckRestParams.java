package com.segi.uhomecp.wh.common.utils;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.interfaces.InvocationHandler;

/**
 * @ClassName:  CheckRestParams   
 * @Description:Rest请求入参校验类  
 * @author: zhaoqing
 * @date:   2018年2月1日 上午11:44:03
 */
public class CheckRestParams {
	
	private static final Logger logger = LoggerFactory.getLogger(CheckRestParams.class);
	
	/**
	 * @Title: checkEmpty   
	 *  非空校验
	 * @author zhaoqing  
	 * @param obj
	 * @return 
	 */
	public static <O> String checkEmpty(O obj, String fields, String messages) {
		InvocationHandler<Boolean, Object> invocation = new InvocationHandler<Boolean, Object>() {
			@Override
			public Boolean invoke(Object obj) {
				if (null == obj || StringUtils.isBlank(String.valueOf(obj))) {
					return true;
				}
				return false;
			}
		};
		return check(obj, invocation, fields, messages, "为空");
	}
	
	/**
	 * @Title: checkDigits   
	 *  数字类型校验
	 * @author zhaoqing  
	 * @param obj
	 * @param fields
	 * @param messages
	 * @return 
	 */
	public static <O> String checkDigits(O obj, String fields, String messages) {
		InvocationHandler<Boolean, Object> invocation = new InvocationHandler<Boolean, Object>() {
			@Override
			public Boolean invoke(Object obj) {
				if (StringUtils.isNotBlank(String.valueOf(obj)) && !NumberUtils.isDigits(String.valueOf(obj))) {
					return true;
				}
				return false;
			}
		};
		return check(obj, invocation, fields, messages, "不为数字类型");
	}
	
	/**
	 * @Title: checkDateYYYY_MM_DD   
	 *  校验日期格式是否正确
	 * @author zhaoqing  
	 * @param obj
	 * @param fields
	 * @param messages
	 * @return    
	 */
	public static <O> String checkDateYYYY_MM_DD(O obj, String fields, String messages) {
		return checkDateByRegex(obj, fields, messages, Constant.DATA_FORMATE_YYYY_MM_DD_REG);
	}
	
	/**
	 * @Title: checkDateByRegex   
	 *  校验日期格式是否正确
	 * @author zhaoqing  
	 * @param obj
	 * @param fields
	 * @param messages
	 * @param dateRegex
	 * @return    
	 */
	public static <O> String checkDateByRegex(O obj, String fields, String messages, final String dateRegex) {
		InvocationHandler<Boolean, Object> invocation = new InvocationHandler<Boolean, Object>() {
			@Override
			public Boolean invoke(Object obj) {
				if (StringUtils.isNotBlank(String.valueOf(obj)) && !Pattern.matches(dateRegex, String.valueOf(obj))) {
					return true;
				}
				return false;
			}
		};
		return check(obj, invocation, fields, messages, "日期格式错误");
	}
	
	/**
	 * @Title: checkDecimal   
	 *  验证是否输入2位小数 
	 * @author zhaoqing  
	 * @param obj
	 * @param fields
	 * @param messages
	 * @param digit
	 * @return   
	 */
	public static <O> String checkDecimal(O obj, String fields, String messages) {
		return checkDecimal(obj, fields, messages, 2);
	}
	
	/**
	 * @Title: checkDecimal   
	 *  验证输入digit位小数 
	 * @author zhaoqing  
	 * @param obj
	 * @param fields
	 * @param messages
	 * @param digit
	 * @return    
	 */
	public static <O> String checkDecimal(O obj, String fields, String messages, final int digit) {
		InvocationHandler<Boolean, Object> invocation = new InvocationHandler<Boolean, Object>() {
			@Override
			public Boolean invoke(Object obj) {
				if (StringUtils.isNotBlank(String.valueOf(obj)) && !NumberUtils.IsDecimal(String.valueOf(obj), digit)) {
					return true;
				}
				return false;
			}
		};
		return check(obj, invocation, fields, messages, "不是" + digit + "位小数");
	}
	
	/**
	 * @Title: checkInteger   
	 *  验证是否是整数 
	 * @author zhaoqing  
	 * @param obj
	 * @param fields
	 * @param messages
	 * @return    
	 */
	public static <O> String checkInteger(O obj, String fields, String messages) {
		InvocationHandler<Boolean, Object> invocation = new InvocationHandler<Boolean, Object>() {
			@Override
			public Boolean invoke(Object obj) {
				return !isInteger(obj);
			}
		};
		return check(obj, invocation, fields, messages, "不是数字类型或数字超过允许的范围");
	}
	
	/**
	 * @Title: isInteger   
	 *  判断参数是否是整数 
	 * @author zhaoqing  
	 * @param obj
	 * @return  是整数返回true, 否则返回false    
	 */
	public static boolean isInteger(Object obj) {
		try { 
			if (StringUtils.isNotBlank(String.valueOf(obj))) {
				Integer.parseInt(String.valueOf(obj));  
			}         
        } catch (NumberFormatException e) {  
            return false;  
        }  
        return true;  
	}
	
	/**
	 * @Title: checkInteger   
	 *  验证是否是非负整数 
	 * @author zhaoqing  
	 * @param obj
	 * @param fields
	 * @param messages
	 * @return    
	 */
	public static <O> String checkNonInteger(O obj, String fields, String messages) {
		InvocationHandler<Boolean, Object> invocation = new InvocationHandler<Boolean, Object>() {
			@Override
			public Boolean invoke(Object obj) {
				boolean isInteger = isInteger(obj);
				if (!isInteger || Integer.parseInt(String.valueOf(obj)) < 0) {
					return true;
				}
				return false;
			}
		};
		return check(obj, invocation, fields, messages, "不是非负整数或数字超过允许的范围");
	}
	
	/**
	 * @Title: check   
	 *  参数校验
	 * @author zhaoqing  
	 * @param obj 参数对象
	 * @param invocation 校验参数的回调方法
	 * @param fields 参数属性名称，多个参数之间用英文逗号分隔
	 * @param messages 参数名称信息，和fields中的属性对应
	 * @param errInfo 校验的提示信息
	 * @return  返回null表示检验通过 
	 */
	public static <O> String check(O obj, InvocationHandler<Boolean, Object> invocation, 
			String fields, String messages,  String errInfo) {
		if (obj == null || fields == null ) {
			return "对象或所需校验字段为空";
		}
		String[] fieldArr = fields.split(",");
		if (fieldArr.length <= 0) {
			return "所需校验在字段为空";
		}
		String[] messageArr = null;
		if (messages != null) {
			messageArr = messages.split(",");
			if (fieldArr.length != messageArr.length) {
				return "字段个数和提示个数不一致";
			}
		}
		String methodName = null;
		Method method = null;
		Object objResult = null;
		StringBuilder buf = new StringBuilder();
		try {
			for (int i=0; i<fieldArr.length; i++) {
				String field = fieldArr[i];
				if (StringUtils.isBlank(field)) {
					continue;
				}
				methodName = "get" + AppUtils.initcap(field.trim());
				method = obj.getClass().getMethod(methodName);
				if (method == null) {
					continue;
				}
				objResult = method.invoke(obj);
				if (invocation.invoke(objResult)) {
					buf.append(messageArr[i]).append("、");
				}
			}
		} catch (Exception e) {
			logger.error("check", e);
			return "没有这个方法";
		}
		int len = buf.length();
		if (len > 0) {
			return buf.toString().substring(0, len-1) + errInfo + "，参数校验不通过。";
		}
		return null;
	}
	
	/**
	 * @Title: checkFileIds   
	 *  校验附件Id传参是否正确
	 * @author zhaoqing  
	 * @param fileIds
	 * @return 格式正确返回 true, 否则返回false
	 */
	public static boolean checkFileIds(String fileIds) {
		if (StringUtils.isNotBlank(fileIds)) {
			String[] fileIdsStr = fileIds.split(Constant.SPLIT_COMMA);		
			if (fileIdsStr != null && fileIdsStr.length > 0) {
				for(int i=0; i<fileIdsStr.length; i++) {
					if (StringUtils.isBlank(fileIdsStr[i]) || !NumberUtils.isDigits(fileIdsStr[i])) {
						return false;
					}
				}
			}
		}	
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(checkFileIds("12,,"));
	}
}
