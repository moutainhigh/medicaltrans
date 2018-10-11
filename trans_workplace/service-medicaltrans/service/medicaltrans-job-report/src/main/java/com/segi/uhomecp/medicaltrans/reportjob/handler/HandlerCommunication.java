package com.segi.uhomecp.medicaltrans.reportjob.handler;

import com.segi.uhomecp.medicaltrans.reportjob.enums.HandlerStateEnums;


public class HandlerCommunication implements Cloneable {
	/**
	 * 
	 */
	private String name;
	/**
     * 运行状态 *
     */
    private HandlerStateEnums state;

    /**
     * 异常记录 *
     */
    private Throwable throwable;

    /**
     * 记录的timestamp *
     */
    private long timestamp;
    
    public HandlerCommunication() {
        this.init();
    }
    
    public HandlerCommunication(String name) {
    	this.name = name;
        this.init();
    }
    /**
     * reset
     */
    public synchronized void reset() {
        this.init();
    }
    /**
     * 初始化
     */
    private void init() {
        this.state = HandlerStateEnums.RUNNING;
        this.throwable = null;
        this.timestamp = System.currentTimeMillis();
    }
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public synchronized HandlerStateEnums getState() {
		return state;
	}

	public void setState(HandlerStateEnums state) {
		this.state = state;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
    
}
