package com.segi.uhomecp.medicaltrans.reportjob.handler;

import java.util.concurrent.ThreadFactory;

import com.segi.uhomecp.medicaltrans.reportjob.handler.exception.HandlerException;

public class HandlerThreadFactory implements ThreadFactory {
	
	@Override
	public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setUncaughtExceptionHandler(new HandlerException());
        return t;
	}

}
