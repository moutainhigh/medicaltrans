package com.segi.uhomecp.wh.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 并发类型常量类
 * @author Jimmy
 * 2018-8-10
 */
public class AppConCurrentTypeConstant {
	/**Excel导入类型*/
	public static final String MAX_CURRENCY_EXCEL_IMPORT = "CURRENCY_EXCEL_IMPORT";
	/**Excel导入类型最大并发数*/
	public static final Integer MAX_CURRENCY_EXCEL_IMPORT_COUNT = 10;
	
	/**Excel导出类型*/
	public static final String MAX_CURRENCY_EXCEL_EXP = "CURRENCY_EXCEL_EXP";
	/**Excel导出类型最大并发数*/
	public static final Integer MAX_CURRENCY_EXCEL_EXP_COUNT = 10;
	
	/**附件下载类型*/
	public static final String MAX_CURRENCY_FILE_DOWN = "CURRENCY_FILE_DOWN";
	/**附件下载类型最大并发数*/
	public static final Integer MAX_CURRENCY_FILE_DOWN_COUNT = 100;
	
	private static AppConCurrentTypeConstant instance;
	
	private Map<String, Integer> dataMap;
	
	private AppConCurrentTypeConstant() {
		
	}
	
	public static synchronized AppConCurrentTypeConstant getInstance() {
		if (instance == null) {
			instance = new AppConCurrentTypeConstant();
			instance.init();
		}
		return instance;
	}
	
	private void init() {
		if (dataMap == null) {
			dataMap = new HashMap<String, Integer>();
			dataMap.put(MAX_CURRENCY_EXCEL_IMPORT, MAX_CURRENCY_EXCEL_IMPORT_COUNT);
			dataMap.put(MAX_CURRENCY_EXCEL_EXP, MAX_CURRENCY_EXCEL_EXP_COUNT);
			dataMap.put(MAX_CURRENCY_FILE_DOWN, MAX_CURRENCY_FILE_DOWN_COUNT);
		}
	}
		
	public Map<String, Integer> getConCurTypeMap() {
		return dataMap;
	}
	
}