package com.segi.uhomecp.medicaltrans.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import segi.medicaltrans.mthistask.query.ExeUser;
import segi.medicaltrans.mthistask.query.MtTaskPageIce;

import com.segi.uhomecp.common.constant.RestApiConstant;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.utils.UhomePropUtils;
import com.segi.uhomecp.web.rest.RestResponse;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;
import com.segi.uhomecp.wh.common.utils.ExcelWriter;

/**
 * @ClassName:  ReportApiUtils   
 * @Description:reportApi中使用的工具类   
 * @author: zhaoqing
 * @date:   2018年8月17日 下午5:21:14
 */
public class ReportApiUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportApiUtils.class);
	
	/**
	 * @Title: queryRptDataBaseFlag   
	 *  判断是否需要查询报表库 
	 * @author zhaoqing  
	 * @param dateStr
	 * @param queryFlag 查询标识（1:运送记录分页查询, 2:运送员运送任务列表分页查询, 3:科室运送任务列表分页查询）
	 * @return 需要查询报表库返回true, 否则返回false;
	 */
	public static boolean queryRptDataBaseFlag(String dateStr, String queryFlag) {
		// 参数日期的年份
		String tagetYear = DateUtil.getYearOfDate(dateStr);
		Date curDate = new Date();
		// 获取当前年份
		String curYear = DateUtil.getYearOfDate(curDate);
		int tagetYearInt = Integer.parseInt(tagetYear);
		int curYeaInt = Integer.parseInt(curYear);
		if (tagetYearInt >= curYeaInt && !MtConstant.TASK_HIS_QUERY_FLAG_TWO.equals(queryFlag)) {
			// 参数年份不小于当年则查询业务库
			return false;
		}
		if (curYeaInt - tagetYearInt > 1) {
			// 两年前的数据查询报表库
			return true;
		}
		// 获取数据切割的日期（MM-DD）
		String transDateStr = UhomePropUtils.getProperty("data_transfer_date");
		LOGGER.debug("================>transDateStr:{}", transDateStr);
		if (StringUtils.isEmpty(transDateStr)) {
			transDateStr = "04-01";
		}
		// 当年数据切割日期
		String curTranDateStr = curYear + DateUtil.converDateToNum(transDateStr);
		String curDateStr = DateUtil.formatDateToStringYYYYMMDD(curDate);
		boolean curDataFlag = Integer.parseInt(curDateStr) > Integer.parseInt(curTranDateStr);
		// 参数日期的月份
		String tagetMonth = DateUtil.getMonthOfDate(dateStr);
		if (MtConstant.TASK_HIS_QUERY_FLAG_TWO.equals(queryFlag) 
				&& "01".equals(tagetMonth) && curDataFlag) {
			// 数据切割后当年1月份数据查询报表库（可能有跨年数据）
			return true;
		}
		if (tagetYearInt >= curYeaInt && MtConstant.TASK_HIS_QUERY_FLAG_TWO.equals(queryFlag)) {
			// 参数年份不小于当年则查询业务库
			return false;
		}
		if (curDataFlag) {
			// 一年前的数据数据切割日期过后查询报表库
			return true;
		}
		return false;
	}
	
	/**
	 * @Title: queryRptDataBaseFlag   
	 *  判断是否需要查询报表库 
	 * @author zhaoqing  
	 * @param dateStr
	 * @return 需要查询报表库返回true, 否则返回false;
	 */
	public static boolean queryRptDataBaseFlag(String dateStr) {
		return queryRptDataBaseFlag(dateStr, null);
	}
	
	/**
	 * @Title: checkSergroupIds   
	 *  校验服务组Ids
	 * @author zhaoqing  
	 * @param sergroupIds
	 * @return
	 */
	public static RestResponse checkSergroupIds(String sergroupIds) {
		if (StringUtils.isEmpty(sergroupIds)) {
			return null;
		}
		if (StringUtils.isNotEmpty(sergroupIds)) {
			// 服务组不为空时，去掉空格
			sergroupIds = StringUtils.replace(sergroupIds, " ", "");
			if (!Pattern.matches(MtConstant.MT_NUM_COMMA_NUM_REGEX, sergroupIds)) {
				return RestResponse.RestResponseBuilder
						.createFailBuilder("服务组Id格式错误！").buidler();
			}
		}
		return null;
	}
	
	/**
	 * @Title: exportUserHisTaskExcel   
	 *  运送员运送任务列表数据导出 
	 * @author zhaoqing  
	 * @param response
	 * @param resource 
	 */
	public static RestResponse exportUserHisTaskExcel(
			HttpServletResponse response, List<MtTaskPageIce> resultList) {
		try {
			File file = null;
			String title = "运送员运送任务列表导出.xls";
			String[] columnTitles = {"所属项目", "任务Id", "运送开始时间", "运送结束时间", "运送来源", "运送类型", 
					"出发地", "目的地", "运送员", "派单类型", "任务类型", "是否超时", "运送内容描述", "派单人", 
					"派单时间", "任务实际开始时间", "任务实际结束时间", "签收时间", "签收人", "评价结论", "评价信息"};		
			String[] data = null;
			List<String[]> dataList = new ArrayList<String[]>();
			if (AppUtils.isNotEmpty(resultList)) {
				for (int i = 0; i < resultList.size(); i++) {
					MtTaskPageIce taskIce = resultList.get(i);
					data = new String[21];
					data[0] = taskIce.getOrganName(); // 所属项目
					data[1] = taskIce.getTaskId(); // 任务Id
					data[2] = taskIce.getExeBeginTime(); // 运送开始时间
					data[3] = taskIce.getExeEndTime(); // 运送结束时间
					data[4] = taskIce.getSourceHouseName(); // 运送来源
					data[5] = taskIce.getTransTypeParentCodeName(); // 运送类型
					data[6] = taskIce.getFromHouseName(); // 出发地
					data[7] = taskIce.getToHouseName(); // 目的地
					if (taskIce.getUserList() != null 
							&& AppUtils.isNotEmpty(taskIce.getUserList())) {
						List<ExeUser> userList = taskIce.getUserList();
						List<String> userNameNoList = AppUtils.list2ParamsList(
								userList, ExeUser.class, "userNameNo");
						data[8] = AppUtils.listToString(userNameNoList, ','); // 运送员
					}
					data[9] = taskIce.getResTypeName(); // 派单类型
					data[10] = taskIce.getTaskTypeName(); // 任务类型
					data[11] = taskIce.getIsTimeOutName(); // 是否超时
					data[12] = taskIce.getTaskContent(); // 运送内容描述	
					data[13] = taskIce.getDispatchUserName(); // 派单人
					data[14] = taskIce.getSendTime(); // 派单时间
					data[15] = taskIce.getExeBeginTime(); // 任务实际开始时间
					data[16] = taskIce.getExeEndTime(); // 任务实际结束时间
					data[17] = taskIce.getReceiveTime(); // 签收时间
					data[18] = taskIce.getReceiverUserName(); // 签收人
					data[19] = taskIce.getEvaluate(); // 评价结论
					data[20] = taskIce.getEvaluateContent(); // 评价信息
					dataList.add(data);
				}
			}
			file = ExcelWriter.simpleCreate(RestApiConstant.MEDICALTRANS, columnTitles, dataList);
			ResponseDownloadUtils.downloadAndDelete(response, file, title);
			return null;
		} catch (Exception e) {
			LOGGER.error("运送员运送任务列表数据导出异常", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("运送员运送任务列表数据导出异常").buidler();
		}
	}
}
