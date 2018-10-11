package com.segi.uhomecp.wh.common.redis;

import java.io.Closeable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloseUtil {
	public static final Logger logger = LoggerFactory.getLogger(CloseUtil.class);
	 public static void close(Closeable closeable) {
	        if (closeable != null) {
	            try {
	                closeable.close();
	            } catch (Exception e) {
	                logger.info("Unable to close %s", closeable, e);
	            }
	        }
	    }
}
