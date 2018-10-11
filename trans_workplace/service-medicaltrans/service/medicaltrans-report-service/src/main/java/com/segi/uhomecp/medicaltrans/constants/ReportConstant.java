package com.segi.uhomecp.medicaltrans.constants;

public class ReportConstant {
	/**
	 * 报表组织机构执行状态：成功
	 */
	public static final String ORGAN_REPORT_STATUS_SUCESS = "1";
	/**
	 * 报表组织机构执行状态：失败
	 */
	public static final String ORGAN_REPORT_STATUS_FAIL = "2";
	/**
	 * 医疗组织机构key
	 */
	public static final String TRANS_REPORT_LIST_KEY = "REPORT_ORGAN_LIST";
	/**
	 * 医疗组织机构缓存有效期
	 */
	public static final int TRANS_REPORT_LIST_KEY_EXPIRE = 7200;
	
	/**
	 * 运行状态
	 */

	public static final String SCHEDULE_STATUS_0 = "0";
	
	public static final String SCHEDULE_STATUS_1 = "1";
	
	public static final String SCHEDULE_STATUS_2 = "2";
	
	public static final String SCHEDULE_STATUS_0_NAME = "运行";
	
	public static final String SCHEDULE_STATUS_1_NAME = "完成";
	
	public static final String SCHEDULE_STATUS_2_NAME = "异常";
	
	/** 任务表 */
	public static final String TABLE_EVT_MT_TASK_HIS = "evt_mt_task_his_";
	
	/** 任务扩展表 */
	public static final String TABLE_EVT_MT_TASK_EXT_HIS = "evt_mt_task_ext_his_";
	
	/** 任务执行人表 */
	public static final String TABLE_EVT_MT_TASK_EXECUTORS_HIS = "evt_mt_task_executors_his_";
	
	/** 任务执行路线表 */
	public static final String TABLE_EVT_MT_TASK_ROUTE_HIS = "evt_mt_task_route_his_";
}
