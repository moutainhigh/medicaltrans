package com.segi.uhomecp.medicaltrans.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;

/**
 * @ClassName:  MtCommonUtils   
 * @Description:项目中常用的工具方法类   
 * @author: zhaoqing
 * @date:   2018年8月14日 下午2:27:04
 */
public class MtCommonUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MtCommonUtils.class);
	
	/**
	 * @Title: splitList   
	 *  分页拆分list
	 * @author zhaoqing  
	 * @param list
	 * @param pageSize
	 * @return
	 */
	public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
		List<List<T>> listArray = new ArrayList<List<T>>();
		List<T> subList = null;
		for (int i = 0; i < list.size(); i++) {
			if (i % pageSize == 0) {// 每次到达页大小的边界就重新申请一个subList
				subList = new ArrayList<T>();
				listArray.add(subList);
			}
			subList.add(list.get(i));
		}
		return listArray;
	}
	
	/**
	 * @Title: initNumberNullProperties   
	 *  把对象中Integer、Long类型为Null的属性值设置为0
	 * @author zhaoqing  
	 * @param list 
	 */
	public static <T> void initNumberNullProperties(List<T> list) {
		if (AppUtils.isNotEmpty(list)) {
			for (T t : list) {
				initNumberNullProperties(t);
			}
		}
	}
	
	/**
	 * @Title: initNumberNullProperties   
	 *  把对象中Integer、Long类型为Null的属性值设置为0
	 * @author zhaoqing  
	 * @param <T>
	 */
	public static <T> void initNumberNullProperties(T t) {
		if (null != t) {
			// 获取对象的所有属性、属性类型、属性值信息
			List<Map<String, String>> filedInfoList = getFiledsInfo(t);
			if (!AppUtils.isNotEmpty(filedInfoList)) {
				return;
			}
			// 循环属性信息
			for (Map<String, String> map : filedInfoList) {
				try {
					if (null == map || map.isEmpty() 
							|| "serialVersionUID".equalsIgnoreCase(map.get("name"))) {
						continue;
					}
					if ((Integer.class.toString()).equals(map.get("type")) 
							&& "null".equalsIgnoreCase(map.get("value"))) {
						// 设置为空的Integer属性值为0
						BeanCopierUtils.setValue(t, map.get("name"), 0, Integer.class);
					}
					if ((Long.class.toString()).equals(map.get("type")) 
							&& "null".equalsIgnoreCase(map.get("value"))) {
						// 设置为空的Long属性值为0
						BeanCopierUtils.setValue(t, map.get("name"), 0L, Long.class);
					}
				} catch (Exception e) {
					LOGGER.error("initNumberNullProperties", e);
				}
			}
		}	
	}
	
	/**
	 * @Title: getFiledName   
	 *  获取属性名数组  
	 * @author zhaoqing  
	 * @param o
	 * @return  
	 */
	public static String[] getFiledName(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			fieldNames[i] = fields[i].getName();
		}
		LOGGER.debug("=================getFiledName: {}", FastjsonUtils.toJsonString(fieldNames));
		return fieldNames;
	} 
	 
	/**
	 * @Title: getFiledsInfo   
	 *  获取属性类型(type)，属性名(name)，属性值(value)的map组成的list  
	 * @author zhaoqing  
	 * @param o
	 * @return 
	 */
	public static List<Map<String, String>> getFiledsInfo(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String> infoMap = null;
		try {
			for (int i = 0; i < fields.length; i++) {
				if ("serialVersionUID".equalsIgnoreCase(fields[i].getName())) {
					continue;
				}
				infoMap = new HashMap<>();
				infoMap.put("type", fields[i].getType().toString());
				infoMap.put("name", fields[i].getName());
				infoMap.put("value", String.valueOf(BeanCopierUtils.getValue(o, fields[i].getName())));
				list.add(infoMap);
			}
		} catch (Exception e) {
			LOGGER.error("getFiledsInfoException", e);
		}
//		LOGGER.debug("=================getFiledsInfo: {}", FastjsonUtils.toJsonString(list));
		return list;
	}
	
	/**
	 * 将integer和long为空的转换成常量
	 * @param t
	 * @return
	 */
	public static Number initNullToZero(Number t, Number deafautNum){
		return t == null ? deafautNum : t;
	}
	
}
