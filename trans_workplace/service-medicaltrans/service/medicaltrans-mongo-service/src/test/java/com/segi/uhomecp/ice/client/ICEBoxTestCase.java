package com.segi.uhomecp.ice.client;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Title: ICEBoxCarYinsTestCase.java    
 * @Description: 车辆年检单元测试
 * @author zhangyang@segimail.com       
 * @created 2018年4月16日 上午11:59:57
 */
public class ICEBoxTestCase {
	
	public static final Logger logger = LoggerFactory.getLogger(ICEBoxTestCase.class);
	
	//修改端口
	private static final int PORT = 6301;
	
	private static final String PROXY_PATH = "segi.medicaltrans.report.organ.TransReportOrganServiceIce";
	
	private static final String PROXY = PROXY_PATH + ":tcp -h localhost -p " + PORT;

	@Test
	public void saveYins() {
		Ice.Communicator ic = null;
		try {
			ic = Ice.Util.initialize();
			Ice.ObjectPrx base = ic.stringToProxy(PROXY);
			
		} catch (Exception e) {
			logger.error("save", e);
		}
	}
}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		