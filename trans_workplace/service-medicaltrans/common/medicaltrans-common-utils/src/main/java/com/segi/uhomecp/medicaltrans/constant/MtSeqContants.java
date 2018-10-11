package com.segi.uhomecp.medicaltrans.constant;

/**
 * Title: MtSeqContants.java    
 * @Description: 医院中央运送序列
 * @author zhangyang@segimail.com       
 * @created 2018年3月22日 下午2:30:09
 */
public class MtSeqContants {
	/**********************************医院基础信息******************************************/
	/**位置Id*/
	public static final String MT_BUILD_LOCATION_ID_SEQ ="mt_build_location_id_seq";
	
	/**位置空间关系Id*/
	public static final String MT_BUILD_LOCATION_REL_ID_SEQ = "mt_build_location_rel_id_seq";
	
	/** 位置信息Id */
	public static final String 	MT_POSIT_ID_SEQ = "mt_posit_id_seq";
	
	/** 楼栋信息Id */
	public static final String MT_BUILD_ID_SEQ = "mt_build_id_seq";
	
	/** 楼层信息Id */
	public static final String MT_BUILD_FLOOR_ID_SEQ = "mt_build_floor_id_seq";
	
	/** 科室信息Id */
	public static final String MT_BUILD_HOUSE_ID_SEQ = "mt_build_house_id_seq";
	
	/** 运送类型Id */
	public static final String MT_TRANS_TYPE_ID_SEQ = "mt_trans_type_id_seq";
	
	/** 科室用户Id */
	public static final String MT_HOSP_USER_ID_SEQ = "mt_hosp_user_id_seq";
	
	/** 医院科室用户信息关系表Id */
	public static final String MT_HOSP_USER_REL_ID_SEQ = "mt_hosp_user_rel_id_seq";
	
	/** 用户科室Id */
	public static final String MT_USER_HOSP_REL_ID_SEQ = "mt_user_hosp_rel_id_seq";
	
	/**********************************医院中央运送******************************************/
	/** 循环任务Id */
	public static final String 	MT_TASK_LOOP_ID_SEQ = "mt_task_loop_id_seq";
	
	/** 循环任务执行人Id */
	public static final String 	MT_EXECUTOR_ID_SEQ = "mt_executor_id_seq";
	
	/** 循环任务运送线路Id */
	public static final String 	MT_ROUTE_ID_ID_SEQ = "mt_route_id_seq";
	
	/**********************************医院运送管理*****************************/
	/** 运送单Id */
	public static final String MT_TASK_ID_SEQ = "mt_task_id_seq";
	
	/** 运送单扩展Id */
	public static final String MT_TASK_EXT_ID_SEQ = "mt_task_ext_id_seq";
	
	/** 运行执行人主键Id */
	public static final String MT_TASK_EXE_ID_SEQ = "mt_task_exe_id_seq";
	
	/** 运行执行人log主键Id */
	public static final String MT_TASK_EXE_LOG_ID_SEQ = "mt_task_exe_log_id_seq";
	
	/** 运送单路由Id */
	public static final String MT_TASK_ROUTE_ID_SEQ = "mt_task_route_id_seq";
	
	/** 医院人员最新位置信息表Id */
	public static final String MT_USER_POSIT_ID_SEQ = "mt_hosp_user_rel_id_seq";
	
	/** 运送任务单服务处关系表Id */
	public static final String MT_TASK_GROUP_REL_ID_SEQ = "mt_task_group_rel_id_seq";

	/**********************************医院报表*****************************/
	/** 排程表Id */
	public static final String MT_TRANS_SCHEDULE_ID_SEQ = "mt_trans_schedule_id_seq";
	
	/** 个人运输量按月排名表Id */
	public static final String MT_PERSONAL_VOLUME_MONTH_ID_SEQ = "mt_personal_volume_month_id_seq";
	
	/** 个人运输量按月排名历史表Id */
	public static final String MT_PERSONAL_VOLUME_MONTH_HIS_ID_SEQ = "mt_personal_volume_month_his_id_seq";

	/** 科室月运送量Id */
	public static final String MTR_DEPT_VOLUME_MONTH_ID_SEQ = "mtr_dept_volume_month_id_seq";
	
	/**项目月运送量表*/
	public static final String MT_ORGAN_MONTH_STATISTICS_SEQ ="mt_organ_month_statistics_seq";
	
	/**运送类型月运送量表*/
	public static final String MT_TRANSTYPE_STATISTICS_ID_SEQ ="mt_transtype_statistics_id_seq";
	
	/**各时段运送量表*/
	public static final String MT_ORGAN_TIME_STATISTICS_SEQ = "mt_organ_time_statistics_seq";
	
	/**分表分库规则记录表*/
	public static final String MT_DB_SOURCE_RULE_ID_SEQ = "mt_db_source_rule_id_seq";
	
	/**运送方式月运送量表*/
	public static final String MT_TRANSWAY_STATISTICS_ID_SEQ ="mt_transway_statistics_id_seq";
	
	/**运送来源月运送量表*/
	public static final String MT_SOURCE_STATISTICS_ID_SEQ ="mt_source_statistics_id_seq";
	
	/** 按周运送运输总量表 */
	public static final String MT_ORGAN_WEEK_VOLUME_ID_SEQ = "mt_organ_week_volume_id_seq";
	
	/** 统计报表宽表 */
	public static final String MT_TRANS_STATISTICS_ID_SEQ = "mt_trans_statistics_id_seq";
	
}
