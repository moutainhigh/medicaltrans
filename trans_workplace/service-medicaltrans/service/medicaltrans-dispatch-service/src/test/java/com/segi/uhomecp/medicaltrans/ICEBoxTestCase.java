package com.segi.uhomecp.medicaltrans;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import segi.datacachesvr.queryInfo.COrginfoIPrx;
import segi.datacachesvr.queryInfo.COrginfoIPrxHelper;


/**
 * @ClassName:  ICEBoxCarDriverMonthRptTestCase   
 * @Description: 驾驶员出车月报表统计单元测试类
 * @author: zhaoqing
 * @date:   2018年1月29日 下午6:38:18
 */
public class ICEBoxTestCase {
	
	public static final Logger logger = LoggerFactory.getLogger(ICEBoxTestCase.class);
	
	//修改端口
	private static final int PORT = 4066;
	
	private static final String PROXY_PATH = "segi.datacachesvr.queryInfo.COrginfoI";
	
	private static final String PROXY = PROXY_PATH + ":tcp -h 192.168.1.5 -p " + PORT;

	/**
	 * @Title: countCarDriverMonthRpt   
	 *  车辆出车月统计报表数据统计
	 * @author zhaoqing 
	 */
	@Test
	public void countCarDriverMonthRpt() {
		Ice.Communicator ic = null;
		try {
			ic = Ice.Util.initialize();
			Ice.ObjectPrx base = ic.stringToProxy(PROXY);
			COrginfoIPrx icePrx = COrginfoIPrxHelper.checkedCast(base);	
//			userOrganIdList rsp = icePrx.queryUserIdList("");
//			System.out.println("DATA:"+rsp.getCode()+"-->"+rsp.getMessage());
		} catch (Exception e) {
			logger.error("countCarDriverMonthRpt", e);
		}
	}

}
