package com.segi.uhomecp.medicaltrans.constant;

/**
 * 医疗运输Redis常量类
 * @author Jimmy
 * 2018-3-21
 */
public class MedicalTransRedisConstant {
	
	/**位置*/
	public static final String POSIT = "MT_POSIT_";
	
	/**位置(新)*/
	public static final String LOCATION = "MT_LOCATION_";
	
	/**最新人员位置*/
	public static final String CUR_USER_POSIT = "MT_CURRENT_USER_POSIT_";
	
	/**运输类型*/
	public static final String TRANS_TYPE = "MT_TRANS_TYPE_";
	
	/**抢单*/
	public static final String DISPATCH_TASK_GRAB_LOCK = "MT_DISPATCH_TASK_GRAB_LOCK_";
	
	/**抢单运送单*/
	public static final String MT_DISPATCH_TASK_GRAB = "MT_DISPATCH_TASK_GRAB_";
	
	/** 个人运送量排名 */
	public static final String MT_PERSONAL_VOLUME_CUR_MONTH = "PERSONAL_VOLUME_CUR_MONTH_";
	
	/** 数据源规则 */
	public static final String DB_SOURCE_RULE = "MT_DB_SOURCE_RULE";
	
	/** 数据源规则统计信息 */
	public static final String DB_SOURCE_RULE_DB_COUNT = "DB_SOURCE_RULE_DB_COUNT";
	
	/** 数据源表 **/
	public static final String DB_SOURCE_RULE_TABLE_COUNT = "DB_SOURCE_RULE_TABLE_COUNT_";

}
