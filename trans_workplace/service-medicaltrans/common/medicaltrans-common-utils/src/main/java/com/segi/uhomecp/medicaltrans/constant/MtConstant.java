package com.segi.uhomecp.medicaltrans.constant;
/**
 * 医院中央运送常量
 * @author zhangyang@segimail.com
 *
 */
public class MtConstant {
	/**
     * 位置缓存有效时间(秒)
     */
	public static final Integer LOCATION_REDIS = 86400;
	
	/**
     * 楼栋 层级 1
     */
	public static final Short BUILD_LEVEL = 1;
	
	/**
     * 楼层 层级 2
     */
	public static final Short FLOOR_LEVEL = 2;
	
	/**
     * 科室 层级 3
     */
	public static final Short HOUSE_LEVEL = 3;
	
	/**
     * 楼栋 父位置Id  -1
     */
	public static final Integer BUILD_PAR_POSIT_ID = -1;
	
	/**
     * 楼栋 引用类型 "1"
     */
	public static final String BUILD_REFTYPE = "1";
	
	/**
     * 楼层 引用类型 "2"
     */
	public static final String FLOOR_REFTYPE = "2";
	
	/**
     * 科室 引用类型 "3"
     */
	public static final String HOUSE_REFTYPE = "3";
	
	/**
     * 定位方式 二维码
     */
	public static final String LOCATE_TYPE_QRCODE = "1";
	
	/**
     * 定位方式 NFC
     */
	public static final String LOCATE_TYPE_NFC = "2";
	
	/**
     * 定位方式 二维码
     */
	public static final String LOCATE_TYPE_QRCODE_AND_NFC = "1,2";
	
	/**
     * indexCode 连接用的 "."
     */
	public static final String LINK_POINT = ".";
	
	/**
	 * 根据创建时间倒序字符串
	 */
	public static final String CREATE_DATE_DESC = " CREATE_DATE desc ";
	
	/**
	 * 根据派单时间倒序
	 */
	public static final String SEND_TIME_DESC = " SEND_TIME desc ";
	
	/**
	 * 根据创建时间正序字符串
	 */
	public static final String CREATE_DATE_ASC = " CREATE_DATE asc ";
	
	/**
	 * 根据任务完成时间倒序字符串
	 */
	public static final String EXE_END_TIME_DESC = " EXE_END_TIME desc ";
	
	/**
	 * 根据排序号倒序排列
	 */
	public static final String SORT_NO_ASC = " SORT_NO asc ";
	
    /**
     * 初始化页码
     */
    public static final Integer INIT_PAGE_NO = 0;
    
    /**
     * 默认每页显示记录数
     */
    public static final Integer DEFAULT_PAGE_LENGTH = 10;
    
	/**
	 * 使用时间
	 */
	public static final String FORMAT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	
	/**
	 * 消息提醒使用时间
	 */
	public static final String FORMAT_HH_MM = "HH:mm";
	
	/**
	 * 年月正则表达式
	 */
	public static final String FORMAT_YYYY_MM_REGEX = "^[1-9]\\d{3}-(0[1-9]|1[0-2])$";
	
	/**
	 * 运输类型删除状态
	 */
	public static final String TRANS_TYPE_DEL_STATUS ="0";
	
	/**
	 * 运输类型有效状态
	 */
	public static final String TRANS_TYPE_VALID_STATUS ="1";
	
	/**
	 * 运输类型停用状态
	 */
	public static final String TRANS_TYPE_STOP_STATUS ="2";
	
	/**
	 * 没有使用中的运输类型
	 */
	public static final String NO_USE_TRANS_TYPE ="0";
	
	/**
	 * 有使用中的运输类型
	 */
	public static final String HAVE_USE_TRANS_TYPE ="1";
	
	/**
	 * 没有在途的运输单
	 */
	public static final String NO_ON_WAY_TASK = "0";
	
	/**
	 * 有在途的运送单
	 */
	public static final String HAVE_ON_WAY_TASK = "0";
	
	/**导出默认总共5000条**/
    public static final String EXPORT_PAGE_LENGTH = "5000";
    
    /**导出默认总共10000条**/
    public static final String EXPORT_PAGE_LENGTH_10000 = "10000";
    
    /**导出每次查询2500条**/
    public static final String EXPORT_EVERY_PAGE_LENGTH = "2500";
    
    /**导出每次查询1000条**/
    public static final String EXPORT_EVERY_PAGE_LENGTH_V1 = "1000";
    
    /**每次查询5000条**/
    public static final Integer MAX_QUERY_LENGTH = 5000;
    
    /**每次新增2000条**/
    public static final Integer MAX_INSERT_LENGTH = 2000;
    
    /**导出默认起始页**/
    public static final String EXPORT_PAGE_NO = "1";
    
    /**sql修改失败返回值**/
    public static final int SQL_UPDATE_FAILURE = 0;
	
	/*未打卡*/
	public static final String CLOCK_STATUS_0 = "0";
	
	/*打卡*/
	public static final String CLOCK_STATUS_1 = "1";
	
	/* 版本默认 */
	public static final int VERSION_DEFAULT = 0;
	
	/* 版本默认 */
	public static final int VERSION_ADD_DEFAULT = 1;
	
	/* 默认人数 1 */
	public static final short TRANS_PERSON_COUNT_DEFAULT = 1;
	
	/* 没预约  */
	public static final String IS_RESERVED_FLAG = "0";
	
	/* 设置了责任人 */
	public static final String IS_PERSON_LIABLE = "1";
	
	/* 默认不是责任人 */
	public static final String DEFAULT_PERSON_LIABLE = "0";
	
	/* 最后一个抢单人待设置责任人 */
	public static final String SETTING_PERSON_LIABLE = "2";
	
	/* 最后一个抢单人调设置责任人接口限制时间*/
	public static final Integer LAST_PERSON_LIMIT_TIME = 1;
	
	/* 用户已经抢单 */
	public static final String IS_GRAB_TASK = "1";
	
	/* 默认未完成任务数 0 */
	public static final String NOT_FINISH_COUNT_DEFAULT = "0";
	
	public static final String SCHEDULE_STATUS_RUNNING = "1";
	
	/**
	 * 任务紧急程度 正则表达式
	 * 1:一般;2:紧急;3:特急
	 */
	public static final String MT_TASK_URGENCY_REGEX = "^[123]$";
	
	/**
	 * 运送工具 正则表达式
	 * 1:步行;2:推床;3:平车;4:轮椅
	 */
	public static final String MT_TASK_TRANSTOOLS_REGEX = "^[1234]$";
	
	/**
	 * 性别 正则表达式
	 * 1:女;2:男
	 */
	public static final String MT_TASK_SEX_REGEX = "^[12]$";
	
	/**
	 * 评价 正则表达式
	 * 1-5颗星
	 */
	public static final String MT_TASK_EVALUATE_REGEX = "^[12345]$";
	
	/**
	 * 任务状态 正则表达式
	 * 1:未派单;2:抢单中;3:进行中;4:未开始;5:已完成;6:已取消;7:退单;9:异常关闭
	 */
	public static final String MT_TASK_STATUS_REGEX = "^[12345679]$";
	
	/**
	 * 任务类型 正则表达式
	 * 1调度任务;2自主任务
	 */
	public static final String MT_TASK_TYPE_DISPATCH_AND_SELF_REGEX = "^[12]$";
	
	/**
	 * 任务类型 正则表达式
	 * 1调度任务;2自主任务
	 */
	public static final String MT_TASK_TYPE_REGEX = "^[123]$";
	
	/**
	 * 任务类型 正则表达式
	 * 3固定任务
	 */
	public static final String MT_TASK_TYPE_LOOP_REGEX = "[3]$";
	
	/**
	 * 响应类型 正则表达式
	 * 1:指定;2:抢单
	 */
	public static final String MT_TASK_RES_TYPE_REGEX = "^[12]$";
	
	/**
	 * 是否担任主责任人
	 * 1:指定;0:不同意
	 */
	public static final String MT_TASK_APPLY_STATUS_REGEX = "^[01]$";
	
	/**
	 * 循环任务排班类型
	 * 1:按日按月;2:按周
	 */
	public static final String MT_TASK_LOOP_LOOP_TYPE_REGEX = "^[12]$";
	
	/**
	 * 循环任务月份格式正则
	 */
	public static final String MT_TASK_LOOP_LOOP_MONTH_REGEX = "^(([1-9]|1[0-2]),)+$";
	
	/**
	 * 循环任务日期格式正则
	 */
	public static final String MT_TASK_LOOP_LOOP_DAY_REGEX = "^(([1-9]|1[0-9]|2[0-9]|3[0-1]),)+$";
	
	/**
	 * 循环任务星期格式正则
	 */
	public static final String MT_TASK_LOOP_LOOP_WEEK_REGEX = "^([1-7],)+$";
	
	/**
	 * 循环任务单状态
	 * 1有效;2停用;0已删除
	 */
	public static final String MT_TASK_LOOP_STATUS_REGEX = "^[012]$";
	
	/**
	 * 排班类型1:按日按月
	 */
	public static final String MT_TASK_LOOP_LOOP_TYPE_MONTH = "1";
	
	/**
	 * 排班类型2:按周
	 */
	public static final String MT_TASK_LOOP_LOOP_TYPE_WEEK = "2";
	
	/**
	 * cron表达式1:按日按月
	 */
	public static final String CRON_TYPE_MONTH = "1";
	
	/**
	 * cron表达式2:按周
	 */
	public static final String CRON_TYPE_WEEK = "2";
	
	/**
	 * 科室用户审核通过
	 */
	public static final String HOSP_USER_AUDIT_PASS ="1";
	
	/**
	 * 用户有效
	 */
	public static final int USER_EFF =1;
	
	/**
	 * 运送单正常
	 */
	public static final String MT_TASK_STATUS_NORMAL ="1";

	/** 是运送责任人 */
	public static final String IS_EXE_END_USER = "1";
	
	/**
	 *0:删除（管理员删除）
	 */
	public static final String EXE_USER_STATUS_DEL ="0";
	
	/**  
	 *  1:正常  
	 */
	public static final String EXE_USER_STATUS_NORMAL ="1";
	
	/**
	 * 2:退单
	 */
	public static final String EXE_USER_STATUS_BACK ="2";

	/* 默认是否签名  0:未签名 */
	public static final String IS_AUTOGRAPH_DEFAULT = "0";

	/* 默认评价值  0  */
	public static final Short EVALUATE_DEFAULT_VALUE = 0;
	
	/* 未评价  */
	public static final String NOT_EVALUATE = "0";
	
	/** 评价结论文本 */
	public static final String EVALUATE_NOTE = "颗星";
	
	/**
	 * 负数
	 */
	public static final Integer NEGATIVE_INT = -1;
	
	/*拒绝当主责任人*/
	public static final String APPLY_STATUS_0 = "0";
	
	/*统一当主责任人*/
	public static final String APPLY_STATUS_1 = "1";
	
	/**
	 * 根据项目id查询个人月运送量默认按20个项目分次查
	 */
	public static final Integer ORAGAN_ID_PAGESIZE = 20;

	public static final String TAKS_LOOP_MSG_TYPE_CODE = "MEDICAL_TAKS_LOOP";
	
	public static final String MEDICAL_TAKS_NUM_CODE ="MEDICAL_TAKS_NUM";
	
	/*已签名*/
	public static final String IS_AUTOGRAPH = "1";
	
	/**
	 * 位置类型(1：建筑类，2：公共区域，3：停车场)
	 */
	public static final int POSITION_TYPE_ONE = 1;
	
	/**
	 * 子位置类型（当位置类型为1时，0：表示楼栋，1：表示单元，2：表示楼层,3表示小区）
	 */
	public static final int SUB_POSITION_TYPE_ZERO = 0;
	
	/**
	 * 子位置类型（当位置类型为1时，0：表示楼栋，1：表示单元，2：表示楼层,3表示小区）
	 */
	public static final int SUB_POSITION_TYPE_ONE = 1;
	
	/**
	 * 子位置类型（当位置类型为1时，0：表示楼栋，1：表示单元，2：表示楼层,3表示小区）
	 */
	public static final int SUB_POSITION_TYPE_TWO = 2;
	
	/**
	 * 循环任务失效造成原因
	 */
	public static final String LOCATION_LOST = "位置失效停用";
	
	/**
	 * 循环任务失效造成原因
	 */
	public static final String USER_LOST = "执行人失效停用";
	
	/**
	 * 循环任务失效造成原因
	 */
	public static final String HANDLE_LOST = "手动停用";

	/**
	 * 默认预警时间
	 */
	public static final Integer WARN_MINUTE = 5;
	
	/**
	 * 主表状态升序排序
	 */
	public static final String STATUS_ASC = "STATUS asc";
	
	/**
	 * 执行人表状态升序排序
	 */
	public static final String TASK_STATUS_ASC = "TASK_STATUS asc";
	
	/**
	 * 楼层类型地上
	 */
	public static final String FLOOR_TYPE_UP_NAME = "地上";
	
	/**
	 * 楼层类型地下
	 */
	public static final String FLOOR_TYPE_UNDER_NAME = "地下";
	
	/**
	 * 根据taskId倒序
	 */
	public static final String TASK_ID_DESC = "TASK_ID desc";
	
	/**
	 * 根据taskId倒序
	 */
	public static final String TRANS_TYPE_ID_DESC = "TRANS_TYPE_ID desc";
	
	/** 月更新项目限制条数 */
	public final static int MONTH_UPDATE_LIMIT = 10;
	
	/** 任务已超时  */
	public static final String TIME_OUT = "1";
	
	/** 任务未超时  */
	public static final String NO_TIME_OUT = "0";
	
	/** 默认时间 0秒  */
	public static final Integer DEFAULT_TIME = 0;
	
	public static final String ORGAN_MONTH_HANDLER ="OrganMonthHandler";
	
	public static final String INCR_DEPT_MONTH_HANDLER ="IncrDeptMonthHandler";
	
	/** 运送类型增量名称  */
	public static final String TRANS_TYPE_HANDLER = "transtypeHandler";
	
	/** 运送方式增量名称  */
	public static final String TRANS_WAY_HANDLER = "transwayHandler";
	
	/** 运送来源增量名称  */
	public static final String TRANS_SOURCE_HANDLER = "sourceHandler";
	
	/** 项目每周每天任务量增量名称  */
	public static final String ORGAN_WEEK_VOL_HANDLER ="organWeekVolHandler";
	
	/** 运送记录分页查询标识 */
	public static final String TASK_HIS_QUERY_FLAG_ONE = "1";
	
	/** 运送员运送任务列表分页查询标识 */
	public static final String TASK_HIS_QUERY_FLAG_TWO = "2";
	
	/** 科室运送任务列表分页查询标识 */
	public static final String TASK_HIS_QUERY_FLAG_THREE = "3";
	
	/** 查询开始时间和结束时间跨越的最大月数 */
	public static final int QUERY_TIME_SPAN_MONTH = 3;
	
	/** 评价星数*/
	public static final Short EVALUATE_ZERO =0;
	
	/**评价星数*/
	public static final Short EVALUATE_Three =3;

	/**年月字段长度*/
	public static final Short YEAR_MONTH_SIZE =6;
	
	/** 个人运送量报表查询排序 */
	public static final String PER_VOL_MONTH_QUERY_ORDER_BY = "TRANS_AMOUNT desc, USER_ID asc";
	
	/** 需要限定时间查询标识 */
	public static final String LIMIT_DATE_QUERY_FLAG = "1";
	
	/** 任务评价不满意星级 */
	public static final String MT_UNSATIS_EVALUATES = "1,2";
	
	/** 第一个月 */
	public static final String MT_FIRST_MONTH = "01";
	
	/** 最后一个月 */
	public static final String MT_LAST_MONTH = "12";
	
	/** 以逗号分隔的数字正则匹配 */
	public static final String MT_NUM_COMMA_NUM_REGEX = "^\\d+(,\\d+)*$";
	
	/** app端查询调度任务Id列表排序 */
	public static final String DISPATCH_TASK_ID_SORT = "URGENCY desc, TASK_STATUS asc, END_TIME asc";
	
	/** app端调度任务列表排序 */
	public static final String DISPATCH_TASK_SORT = "URGENCY desc, STATUS asc, END_TIME asc";
	
	/** 预计完成时间升序(剩余时间升序) */
	public static final String END_TIME_ASC = "END_TIME asc";
	
	/**
	 * 
	 * 描述: 医疗运输字典表
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月23日 下午3:11:22
	 */
	public static class MtDictType {
		
		/**
		 * 运送大类状态                             
		 */
		public final static String MT_TRANS_TYPE = "MT_TRANS_TYPE";
		
		/**
		 * 紧急程度                           
		 */
		public final static String MT_URGENCY = "MT_URGENCY_TYPE";
		
		/**
		 * 运送单状态                            
		 */
		public final static String MT_TASK_STATUS = "MT_TASK_STATUS_TYPE";
		
		/**
		 * 运送工具                           
		 */
		public final static String MT_TASK_TOOL = "MT_TASK_TOOL_TYPE";
	}
	
	/**
	 * 
	 * Title: MtConstant.java    
	 * @Description: 医疗运输附件引用类型
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月9日 下午8:07:38
	 */
	public static class FileRefType {
		/**
		 * 运送任务                            
		 */
		public final static String MT_TASK_FILE = "MT_TASK_FILE";
		
		/**
		 * 签名                           
		 */
		public final static String MT_TASK_AUTOGRAPH_FILE = "MT_TASK_AUTOGRAPH_FILE";
		
		/**
		 * 任务完成                            
		 */
		public final static String MT_TASK_FINISH_FILE = "MT_TASK_FINISH_FILE";
		
		/**
		 * 语音                          
		 */
		public final static String MT_TASK_VOICE_FILE = "MT_TASK_VOICE_FILE";
	}
	
	/**
	 * app端                            
	 */
	public static final String MT_DATASOURCE_APP = "app端";
	
	/**
	 * pad端                          
	 */
	public static final String MT_DATASOURCE_PAD = "pad端";
	
	/**
	 * web端                            
	 */
	public static final String MT_DATASOURCE_WEB = "web端";
	
	/**
	 * 微信端                          
	 */
	public static final String MT_DATASOURCE_WECHAT = "微信端";
	
	/**
	 * 月份数字-一月
	 */
	public static final int MT_MONTH_JANUARY = 1;

	/**
	 * 月份数字-十二月
	 */
	public static final int MT_MONTH_DECEMBER = 12;

	/**
	 * 微信端
	 */
	public static final int MT_CONSTANT_HUNDRED = 100;

	/**
	 * 修复数据 管理员密码(SEGI-WH-trans!@#)
	 */
	public static final Object REPAIR_DATA_PWD = "ec7fb179c49ffd00bcbabbcbc36c69f4";
	
	/**
	 * 个人月排名表缓存信息更新方式（1：全量）
	 */
	public static final String MT_PERVOL_REDIS_UPDATE_TYPE_All = "1";
	
	/** 为空字段默认转换0 */
	public static final Integer INT_ZERO = 0;
	
	public static final Long LONG_ZERO = 0L;
	
	/**
	 * 个人运送量月报表数据统计类型（1：当月数据统计， 2：历史数据统计）
	 */
	public static final String MT_PERVOL_STAT_TYPE_ONE = "1";

	/** 任务超时轨迹填充 */
	public static final String TRACK_TIME_OUT = "，超时原因：";

	/** 任务超时轨迹   特殊类型    1 */
	public static final String TIME_OUT_SPECIAL_TYPE = "1";
	
	/** 任务退单轨迹   特殊类型    2 */
	public static final String BACK_TASK_SPECIAL_TYPE = "2";

	/** 轨迹系统名称填充 */
	public static final String SYSTEM_NAME = "系统";
	
	/** pad端旧版本号 */
	public static final String PAD_OLD_VERSION = "1";
	
	/** 运送类型排名前5的科室 */
	public static final String TRANS_SOURCE_TOP = "5";

	/**
	 * Title: MtConstant.java    
	 * @Description: 运送类型code常量类
	 * @author yangyh@segimail.com       
	 * @created 2018年8月14日 下午4:07:27
	 */
	public static class TransTypeCode {
		/**
		 * 药品运送                           
		 */
		public static final String MT_TRANS_TYPE_CODE_01 = "01";
		
		/**
		 * 标本运送                          
		 */
		public static final String MT_TRANS_TYPE_CODE_02 = "02";
		
		/**
		 * 血制品运送                           
		 */
		public static final String MT_TRANS_TYPE_CODE_03 = "03";
		
		/**
		 * 病人护送
		 */
		public static final String MT_TRANS_TYPE_CODE_04 = "04";
		
		/**
		 * 物品运送
		 */
		public static final String MT_TRANS_TYPE_CODE_05 = "05";
		
		/**
		 * 文书运送
		 */
		public static final String MT_TRANS_TYPE_CODE_06 = "06";
	}
	
	/**
	 * Title: MtConstant.java    
	 * @Description: 运送方式code常量类
	 * @author yangyh@segimail.com       
	 * @created 2018年9月20日 下午4:43:50
	 */
	public static class TransWayCode {
		/**
		 * 步行
		 */
		public static final String TRANS_TOOLS_CODE_WALK = "1";
		
		/**
		 * 推床
		 */
		public static final String TRANS_TOOLS_CODE_PUSHING_BED = "2";
		
		/**
		 * 平车
		 */
		public static final String TRANS_TOOLS_CODE_FLAT_CAR = "3";
		
		/**
		 * 轮椅
		 */
		public static final String TRANS_TOOLS_CODE_WHEELCHAIR = "4";
	}
	
	/**
	 * Title: MtConstant.java    
	 * @Description: 运送时间统计name常量类
	 * @author yangyh@segimail.com       
	 * @created 2018年9月26日 下午5:53:26
	 */
	public static class TransportTimeName {
		/**
		 * 即时任务平均响应时间                           
		 */
		public static final String INSTANT_TASK_AVGTIME_NAME = "即时任务平均响应时间";
		
		/**
		 * 标本运送平均时间                          
		 */
		public static final String SAMPLE_AVGTIME_NAME = "标本运送平均时间";
		
		/**
		 * 病人护送平均时间                          
		 */
		public static final String PATIENT_AVGTIME_NAME = "病人护送平均时间";
		
		/**
		 * 药品运送平均时间
		 */
		public static final String DRUG_AVGTIME_NAME = "药品运送平均时间";
		
		/**
		 * 血制品运送平均时间
		 */
		public static final String BLOOD_AVGTIME_NAME = "血制品运送平均时间";
		
		/**
		 * 物品运送平均时间
		 */
		public static final String GOOD_AVGTIME_NAME = "物品运送平均时间";
		
		/**
		 * 文书运送平均时间
		 */
		public static final String BOOK_AVGTIME_NAME = "文书运送平均时间";
	}
	
	/**
	 * Title: MtConstant.java    
	 * @Description: 任务结构占比name常量类
	 * @author yangyh@segimail.com       
	 * @created 2018年9月26日 下午6:23:16
	 */
	public static class TaskStructureRatioName {
		
		/**
		 * 标本运输量                      
		 */
		public static final String SAMPLE_AMOUNT_NAME = "标本运输量";
		
		/**
		 * 病人运输量                        
		 */
		public static final String PATIENT_AMOUNT_NAME = "病人运输量";
		
		/**
		 * 药品运输量
		 */
		public static final String DRUG_AMOUNT_NAME = "药品运输量";
		
		/**
		 * 血制品运输量
		 */
		public static final String BLOOD_AMOUNT_NAME = "血制品运输量";
		
		/**
		 * 物品运输量
		 */
		public static final String GOOD_AMOUNT_NAME = "物品运输量";
		
		/**
		 * 文书运输量
		 */
		public static final String BOOK_AMOUNT_NAME = "文书运输量";
	}
	
	/**
	 * Title: MtConstant.java    
	 * @Description: 出发地占比name常量类
	 * @author yangyh@segimail.com       
	 * @created 2018年9月26日 下午6:29:39
	 */
	public static class TransModeRatioName {
		
		/**
		 * 步行运输量
		 */
		public static final String WALK_AMOUNT_NAME = "步行运输量";
		
		/**
		 * 推床运输量
		 */
		public static final String PUSHINGBED_AMOUNT_NAME = "推床运输量";
		
		/**
		 * 平车运输量
		 */
		public static final String FLATCAR_AMOUNT_NAME = "平车运输量";
		
		/**
		 * 轮椅运输量
		 */
		public static final String WHEELCHAIR_AMOUNT_NAME = "轮椅运输量";
	}
	
}
