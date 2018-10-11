package com.segi.uhomecp.medicaltrans.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DynamicTableSourceKeyHolder {

    private static final Logger logger = LoggerFactory.getLogger(DynamicTableSourceKeyHolder.class);

    private static final ThreadLocal<String> tableSourceHolder = new ThreadLocal<String>();

    public static void setKey(String key) {
    	logger.debug("=====>> tableSourceHolder set {}",key);
    	tableSourceHolder.set(key);
    }

    public static String getDataSourceKey() {
    	logger.debug("=====>> tableSourceHolder get {}",tableSourceHolder.get());
        return tableSourceHolder.get();
    }
    
}