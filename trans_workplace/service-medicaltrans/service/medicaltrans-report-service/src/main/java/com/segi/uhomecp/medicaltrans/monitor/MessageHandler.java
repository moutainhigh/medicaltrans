package com.segi.uhomecp.medicaltrans.monitor;

import java.util.Map;
/**
 * 消息处理接口
 *     
 * 包: com.segi.uhomecp.medicaltrans.monitor    
 * 类名称: MessageHandler.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 2018年3月22日 上午10:00:34
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
public interface MessageHandler {
	/**
	 * 执行方法
	 * @param params
	 */
	abstract void excute(Map<String, String> params);
	
}
