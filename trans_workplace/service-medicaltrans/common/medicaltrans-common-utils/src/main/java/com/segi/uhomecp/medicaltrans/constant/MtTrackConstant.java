package com.segi.uhomecp.medicaltrans.constant;

/**
 * Title: MtTrackConstant.java    
 * @Description: 运送轨迹常量
 * @author zhangyang@segimail.com       
 * @created 2018年10月10日 上午9:53:32
 */
public class MtTrackConstant {
	
	/**
	 * Title: MtConstant.java    
	 * @Description: 任务轨迹操作标志
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月30日 上午9:40:00
	 */
	public static class TaskTrackOprFlag {
		
		/**
		 * web运送管理创建任务
		 */
		public static final String WEB_CERATE_FLAG = "1";
		
		/**
		 * pad/web运送发起创建任务
		 */
		public static final String PAD_OR_WEB_CREATE_FLAG = "2";
		
		/**
		 * app端创建自主任务
		 */
		public static final String CREATE_AUTO_FLAG = "3";
		
		/**
		 * app调度/自主完成任务
		 */
		public static final String APP_FINISH_FLAG = "4";
	}
	
}


