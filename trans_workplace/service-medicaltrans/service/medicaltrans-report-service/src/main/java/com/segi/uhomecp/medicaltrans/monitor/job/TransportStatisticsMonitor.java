package com.segi.uhomecp.medicaltrans.monitor.job;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.segi.uhomecp.medicaltrans.monitor.ConsumerMonitor;
import com.segi.uhomecp.medicaltrans.monitor.MessageHandler;

/**
 * 运单统计主程序
 *     
 * 包: com.segi.uhomecp.medicaltrans.monitor.job    
 * 类名称: TransportStatisticsMonitor.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 2018年3月22日 下午5:11:18
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
public class TransportStatisticsMonitor {
	
	public static final Logger LOG = LoggerFactory.getLogger(TransportStatisticsMonitor.class);
	
	//线程池
	private static final ExecutorService threadPool = Executors.newFixedThreadPool(16);
		
	private List<MessageHandler> handlers;

	public void setHandlers(List<MessageHandler> handlers) {
		this.handlers = handlers;
	}
	
	/**
	 * 执行入口
	 */
	@PostConstruct
	public void excute() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(new Runnable() {
			
			public void run() {
				while (true) {
					try {
//						log.info("TransportStatisticsMonitor pull message.");
						ConsumerMonitor.init();
						
						Map<String, BlockingQueue<Map<String, String>>> maps = ConsumerMonitor.getMessageMap();
						
						for (Map.Entry<String, BlockingQueue<Map<String, String>>> entry : maps.entrySet()) {
							BlockingQueue<Map<String, String>> queue = entry.getValue();
							if (!queue.isEmpty() && !checkCurrentExecutor(entry.getKey())) {
								ConsumerMonitor.hisCountMap.get(entry.getKey()).compareAndSet(false, true);
								LOG.debug("organ[{}] queue handler is running.{}", entry.getKey(), ConsumerMonitor.hisCountMap.get(entry.getKey()));
								threadPool.execute(new TransportExecutor(entry.getKey(), handlers, queue.take()));
							}
						}
						
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	/**
	 * 检查当前组织是否在执行任务
	 * @param key
	 * @return
	 */
	private boolean checkCurrentExecutor(String key) {
		if (null == ConsumerMonitor.hisCountMap.get(key)
				|| !ConsumerMonitor.hisCountMap.get(key).get()) {
			return false;
		}
		return true;
	}
	
	
	public class TransportExecutor implements Runnable {
		
		private String organId;
		
		private List<MessageHandler> handlers;
		
		private Map<String, String> params;
		
		public TransportExecutor(String organId, List<MessageHandler> handlers, Map<String, String> params) {
			super();
			this.organId = organId;
			this.handlers = handlers;
			this.params = params;
			params.put("organId", organId);
		}

		@Override
		public void run() {
			try {
				ExecutorService executor1 = Executors.newFixedThreadPool(handlers.size());
				for (final MessageHandler handler : handlers) {
					executor1.execute(new Runnable() {
						
						public void run() {
							handler.excute(params);
						}
						
					});
					
				}
				
				executor1.shutdown();

				while (true) {
					if (executor1.isTerminated()) {
						LOG.info("executor pool completed.");
						ConsumerMonitor.hisCountMap.get(organId).compareAndSet(true, false);
						break;
					}
					Thread.sleep(500);
				}
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
