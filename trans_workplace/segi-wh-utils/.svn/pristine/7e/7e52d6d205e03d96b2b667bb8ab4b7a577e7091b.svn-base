package com.segi.uhomecp.wh.common.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.segi.uhomecp.wh.common.constant.AppConCurrentTypeConstant;

/**
 * 公共并发数帮助类
 * @author Jimmy
 * 2018-8-10
 */
public class AppConCurrentUtils {
	/**并发数Map<类型， 并发数>*/
	private ConcurrentMap<String, AtomicInteger> conCurrencyCountMap;
	
	private static AppConCurrentUtils instance;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppConCurrentUtils.class);
	
	private AppConCurrentUtils() {
		
	}
	
	public static synchronized AppConCurrentUtils getInstace() {
		if (instance == null) {
			instance = new AppConCurrentUtils();
			instance.init();
		}
		return instance;
	}
	
	/**
	 * 初始化
	 */
	private void init() {
		if (conCurrencyCountMap == null) {
			conCurrencyCountMap = new ConcurrentHashMap<String, AtomicInteger>();
			Map<String, Integer> typeMap = AppConCurrentTypeConstant.getInstance().getConCurTypeMap();
			if (typeMap != null) {
				for (String type : typeMap.keySet()) {
					conCurrencyCountMap.put(type, new AtomicInteger(0));
				}
			}
		}
	}
	
	/**
	 * 添加并发数
	 * @param type
	 * @return true没有超过并发上线； false超过并发上线
	 */
	public boolean addConCurCount(String type) throws Exception {
		if (type == null || "".equals(type)) {
			return false;
		}
		Integer maxCount = AppConCurrentTypeConstant.getInstance().getConCurTypeMap().get(type);
		if (maxCount == null) {
			throw new Exception("没有找到对应的并发类型：" + type);
		}
		AtomicInteger val = conCurrencyCountMap.get(type);
		int curCont = val.incrementAndGet();
		LOGGER.info("===========>addConCurCount 并发类型：{}, 最大并发数：{}, 当前并发数：{}", type, maxCount, curCont);
		//超过最大并发数
		if (curCont > maxCount) {
			return false;
		}
		return true;
	}
	
	public void subConCurCount(String type) {
		if (type == null || "".equals(type)) {
			return;
		}
		AtomicInteger val = conCurrencyCountMap.get(type);
		if (val == null) {
			return;
		}
		if (val.decrementAndGet() < 0) {
			val.set(0);
		}
	}
}
