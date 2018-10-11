package com.segi.uhomecp.medicaltrans.reportjob.handler.exception;

import java.lang.Thread.UncaughtExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于捕获异常
 *     
 * 包: com.segi.uhomecp.medicaltrans.reportjob.handler.exception 
 * 类名称: HandlerException.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 上午11:29:32
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
public class HandlerException implements UncaughtExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(HandlerException.class);
	
	@Override
	public void uncaughtException(Thread t, Throwable e) {
		LOGGER.error("捕获到异常：" + e);
	}

}
