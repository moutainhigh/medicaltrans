package com.segi.uhomecp.wh.common.utils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jersey.repackaged.com.google.common.collect.Lists;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对象转换工具
 * @author kinas
 *
 */
public class BeanCopierUtils {
	private static final Logger logger = LoggerFactory.getLogger(BeanCopier.class);
	private static final Map<String, BeanCopier> beanCopierMap = new HashMap<String, BeanCopier>();

	/**
	 * 格式日期
	 */

	public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	private static String generateKey(Class<?> class1, Class<?> class2) {
		return class1.toString() + class2.toString();
	}

	/**
	 * 
	 * @Title: copyProperties
	 * @param source
	 *            资源类
	 * @param target
	 *            目标类
	 */
	public static void copyProperties(Object source, Object target) {
		copyProperties(source, target, false);
	}

	/**
	 * useConverter = true时使用默认转换器，实现名称相同，类型不同的数据转换
	 * 
	 * @Title: copyProperties
	 * @param source
	 *            资源类
	 * @param targetClass
	 *            目标类
	 * @param useConverter
	 *            是否使用转换器，true时使用默认转换器
	 */
	public static <T> T copyProperties(Object source, Class<T> targetClass) {
		return copyProperties(source, targetClass, false);
	}

	/**
	 * useConverter = true时使用默认转换器，实现名称相同，类型不同的数据转换
	 * 
	 * @Title: copyProperties
	 * @param source
	 *            资源类
	 * @param target
	 *            目标类
	 * @param useConverter
	 *            是否使用转换器，true时使用默认转换器
	 */
	public static void copyProperties(Object source, Object target, boolean useConverter) {
		String beanKey = generateKey(source.getClass(), target.getClass());
		BeanCopier copier = null;
		if (!beanCopierMap.containsKey(beanKey)) {
			copier = BeanCopier.create(source.getClass(), target.getClass(), useConverter);
			beanCopierMap.put(beanKey, copier);
		} else {
			copier = beanCopierMap.get(beanKey);
		}
		if (useConverter) {
			SimpleConverter converter = new SimpleConverter();
			copier.copy(source, target, converter);
		} else {
			copier.copy(source, target, null);
		}
	}

	/**
	 * useConverter = true时使用默认转换器，实现名称相同，类型不同的数据转换
	 * 
	 * @Title: copyProperties
	 * @param source
	 *            资源类
	 * @param targetClass
	 *            目标类
	 * @param useConverter
	 *            是否使用转换器，true时使用默认转换器
	 */
	public static <T> T copyProperties(Object source, Class<T> targetClass, boolean useConverter) {
		return copyProperties(source, targetClass, useConverter, null);
	}

	/**
	 * useConverter = true时使用默认转换器，实现名称相同，类型不同的数据转换
	 * 
	 * @Title: copyProperties
	 * @param source
	 *            资源类
	 * @param targetClass
	 *            目标类
	 * @param useConverter
	 *            是否使用转换器，true时使用默认转换器
	 * @param dateFormate
	 *            如果存在日期型属性，设置日期型属性的转换格式
	 */
	public static <T> T copyProperties(Object source, Class<T> targetClass, boolean useConverter, String dateFormate) {
		T target = null;
		try {
			target = targetClass.newInstance();

			String beanKey = generateKey(source.getClass(), targetClass);
			BeanCopier copier = null;
			if (!beanCopierMap.containsKey(beanKey)) {
				copier = BeanCopier.create(source.getClass(), targetClass, useConverter);
				beanCopierMap.put(beanKey, copier);
			} else {
				copier = beanCopierMap.get(beanKey);
			}
			if (useConverter) {
				SimpleConverter converter = new SimpleConverter(dateFormate);
				copier.copy(source, target, converter);
			} else {
				copier.copy(source, target, null);
			}
		} catch (InstantiationException e) {
			logger.error("", e);
		} catch (IllegalAccessException e) {
			logger.error("", e);
		}

		return target;
	}

	/**
	 * useConverter = true时使用默认转换器，实现名称相同，类型不同的数据转换
	 * 
	 * @Title: copyProperties
	 * @param source
	 *            资源类
	 * @param targetClass
	 *            目标类
	 * @param useConverter
	 *            是否使用转换器，true时使用默认转换器
	 * @param dateFormate
	 *            如果存在日期型属性，设置日期型属性的转换格式
	 */
	public static <T> T copyProperties(Object source, Class<T> targetClass, Converter useConverter) {
		T target = null;
		try {
			target = targetClass.newInstance();

			String beanKey = generateKey(source.getClass(), targetClass);
			BeanCopier copier = null;
			if (!beanCopierMap.containsKey(beanKey)) {
				copier = BeanCopier.create(source.getClass(), targetClass, true);
				beanCopierMap.put(beanKey, copier);
			} else {
				copier = beanCopierMap.get(beanKey);
			}
			copier.copy(source, target, useConverter);
		} catch (InstantiationException e) {
			logger.error("", e);
		} catch (IllegalAccessException e) {
			logger.error("", e);
		}

		return target;
	}
	
	/**
	 * 
	 * @Description (TODO对List集合类进行拷贝)
	 * @param froms
	 * @param to
	 * @return
	 */
	public static <T,E> List<T> copyList2List(List<E> froms, Class<T> to,boolean useConverter) {
	    if(null == froms || froms.size() == 0){
            return Lists.newArrayListWithCapacity(0);
        }
	    List<T> targetList = Lists.newArrayListWithCapacity(froms.size());
        for(Object item : froms){
            T targetObj = BeanCopierUtils.copyProperties(item, to, useConverter);
            targetList.add(targetObj);
        }
	    
	    return targetList;
	}

	
	static class SimpleConverter implements Converter {

		private SimpleDateFormat sdf = null;

		public SimpleConverter() {
			sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
		}

		public SimpleConverter(String format) {
			if (StringUtils.isNotEmpty(format)) {
				sdf = new SimpleDateFormat(format);
			} else {
				sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
			}
		}

		@SuppressWarnings("rawtypes")
		@Override
		public Object convert(Object value, Class target, Object context) {
			try {
				// 类型相同
				if (value == null || target.equals(value.getClass()) || target.getName().equalsIgnoreCase(value.getClass().getSimpleName())) {
					return value;
				}
				// 源为Integer型
				else if (value instanceof Integer) {
					if (target.equals(String.class)) {
						return String.valueOf(value);
					}
					if (target.equals(Integer.TYPE)) {
						return ((Integer) value).intValue();
					}

					return value;
				}
				// 源为int型
				else if (Integer.TYPE.equals(value.getClass())) {
					if (target.equals(String.class)) {
						return String.valueOf(value);
					}
					/*
					 * if (target.equals(Integer.class)) { return value; }
					 */
					return value;
				}
				// 源为Long型
				else if (value instanceof Long) {
					if (target.equals(String.class)) {
						return String.valueOf(value);
					}
					if (target.equals(Long.TYPE)) {
						return ((Long) value).longValue();
					}

					return value;
				}
				// 源为long型
				else if (Long.TYPE.equals(value.getClass())) {
					if (target.equals(String.class)) {
						return String.valueOf(value);
					}
					return value;
				}
				// 源为Short型
				else if (value instanceof Short) {
					if (target.equals(String.class)) {
						return String.valueOf(value);
					}
					if (target.equals(Long.TYPE)) {
						return ((Short) value).shortValue();
					}

					return value;
				}
				// 源为short型
				else if (Short.TYPE.equals(value.getClass())) {
					if (target.equals(String.class)) {
						return String.valueOf(value);
					}
					return value;
				}
				// 源为String型
				else if (value instanceof String) {
					// 目标为Integer
					if (target.equals(Integer.class) || target.equals(Integer.TYPE)) {
						if (StringUtils.isNotEmpty(value.toString())) {
							return Integer.parseInt((String) value);
						}
						return null;
					}
					// 目标为Long
					if (target.equals(Long.class) || target.equals(Long.TYPE)) {
						if (StringUtils.isNotEmpty(value.toString())) {
							return Long.valueOf((String) value);
						}
						return null;
					}
					// 目标为Short
					if (target.equals(Short.class) || target.equals(Short.TYPE)) {
						if (StringUtils.isNotEmpty(value.toString())) {
							return Short.valueOf((String) value);
						}
						return null;
					}
					// 目标为Date 日期型
					if (target.equals(Date.class)) {
						if (StringUtils.isNotEmpty(value.toString())) {
							return sdf.parseObject(value.toString());
						}
						return null;
					}
					// 目标为Float
					if (target.equals(Float.class) || target.equals(Float.TYPE)) {
						if (StringUtils.isNotEmpty(value.toString())) {
							return Float.valueOf((String) value);
						}
						return null;
					}
					// 目标为Double 双精度类型
					if (target.equals(Double.class)) {
						if (StringUtils.isNotEmpty(value.toString())) {
							return Double.parseDouble(value.toString());
						}
						return null;
					}
					
					// 目标为BigDecimal 大数据类型
					if (target.equals(BigDecimal.class)) {
						if (StringUtils.isNotEmpty(value.toString())) {
							return new BigDecimal(value.toString());
						}
						return null;
					}

					return value;
				}
				// 源为Date型
				else if (value instanceof Date) {
					// 模板为String
					if (target.equals(String.class)) {
						return sdf.format((Date) value);
					}
					// 目标为Long
					if (target.equals(Long.class) || target.equals(Long.TYPE)) {
						return ((Date) value).getTime();
					}
					// 模板为Integer
					if (target.equals(Integer.class) || target.equals(Integer.TYPE)) {
						return new Long(((Date) value).getTime()).intValue();
					}

					return value;

				}// 源为Double型
				else if (value instanceof Double) {
					if (target.equals(String.class)) {
						return String.valueOf(value);
					}
					if (target.equals(Double.TYPE)) {
						return ((Double) value).doubleValue();
					}

					return value;

				}
				// 源为Float型
				else if (value instanceof Float) {
					if (target.equals(String.class)) {
						return String.valueOf(value);
					}
					if (target.equals(Float.TYPE)) {
						return ((Float) value).floatValue();
					}

					return value;

				}
				/* else if (value instanceof BigDecimal) {
					BigDecimal bd = (BigDecimal) value;
					return bd.toPlainString();
				}*/ 
				// 原属性为数组，目标为List
				else if (value.getClass().isArray()) {
					if (target.equals(List.class)) {
						List list = new ArrayList();
						for (Object obj : (Object[]) value) {
							list.add(BeanCopierUtils.copyProperties(obj, obj.getClass(), true));
						}
						return list;
					}
				} 
				//  原属性为List，目标为数组
				else if (value instanceof List) {
					if (target.isArray()) {
						return ((List) value).toArray();
					}
				}
			} catch (Exception e) {
				logger.error("属性复制异常", e);
			}
			return null;
		}
	}
	
	/**
	 * 通过反射get对象属性值
	 * @param dto
	 * @param name  支持object.attribute方式
	 * @return
	 * @throws Exception
	 */
	public static Object getValue(Object dto, String name) throws Exception {
		if (StringUtils.isNotBlank(name) && null != dto) {
			if (name.indexOf(".") > -1) {
				String[] splits = name.split("\\.");
				Object obj = getValue(dto, splits[0]);
				if (obj != null) {
					return getValue(obj, splits[1]);
				}
			} else {
				Method methodGetParam = dto.getClass().getMethod("get" + AppUtils.initcap(name));
				return methodGetParam.invoke(dto);
			}
		}
		return null;
	}
	
	/**
	 * 通过反射set对象属性值
	 * @param dto
	 * @param name
	 * @param value
	 * @param valueType
	 * @throws Exception
	 */
	public static void setValue(Object dto, String name, Object value, Class valueType)  throws Exception {
		if (StringUtils.isNotBlank(name)  && null != dto) {
			Method methodGetParam = dto.getClass().getMethod("set" + AppUtils.initcap(name), valueType);
			methodGetParam.invoke(dto, value);
		}
	}

}
