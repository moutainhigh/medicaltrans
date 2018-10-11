package com.segi.uhomecp.medicaltrans.constant;

/**
 * 
 * Title: MtNoticeConstant.java    
 * @Description: 提醒字典信息
 * @author wangxiong@segimail.com       
 * @created 2018年6月7日 下午2:51:15
 */
public class MtMqConstant {
	
	/**
	 * 编辑科室来源mq_tag
	 */
	public static final String MT_TASK_EDIT_SOURCE_HOUSE_MQ_TAG = "EDIT_SOURCE_HOUSE_MQ_TAG";
	
	/**
	 * Report 数据统计mq_tag
	 */
	public static final String MT_TASK_TRANSPORT_STATISTICS_MQ_TAG = "MT_TASK_TRANSPORT_STATISTICS_MQ_TAG";

	public static class TaskMqOeration {
		/**
		 * 创建任务mq
		 */
		public static final String CREATE_TASK_MQ = "CREATE_TASK_MQ";
		
		/**
		 * 处理任务mq
		 */
		public static final String UPDATE_TASK_MQ = "UPDATE_TASK_MQ";
		
		/**
		 * 取消任务mq
		 */
		public static final String CANCEL_TASK_MQ = "CANCEL_TASK_MQ";
		
		/**
		 * 抢单完成、设置责任人mq
		 */
		public static final String GRAB_TASK_MQ = "GRAB_TASK_MQ";
		
		/**
		 * 编辑任务来源地mq
		 */
		public static final String EDIT_SOURCE_HOUSE_MQ = "EDIT_SOURCE_HOUSE_MQ";
	}
	
	/**
	 * Title: MtConstant.java    
	 * @Description: mq推送topic
	 * @author zhangyang@segimail.com       
	 * @created 2018年10月9日 下午5:58:16
	 */
	public static class TaskMqTopic {
		
		/**
		 * 运送任务数据统计
		 */
		public static final String TRANSPORT_REPORT_TOPIC = "TRANSPORT_REPORT_TOPIC";
		
	}
}


