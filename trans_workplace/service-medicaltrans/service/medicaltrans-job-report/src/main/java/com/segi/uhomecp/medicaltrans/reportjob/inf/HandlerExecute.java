package com.segi.uhomecp.medicaltrans.reportjob.inf;



/**
 * handler执行器
 *     
 * 包: com.segi.uhomecp.medicaltrans.reportjob.inf 
 * 类名称: HandlerExecute.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 下午7:02:25
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 * 
 * @param <K>
 *
 */
public interface HandlerExecute<K> {
	/**
	 * handler初始化
	 */
	void init();
	/**
	 * handler执行入口
	 * @param k k
	 */
	void execute(final K k);
	
}
