package com.segi.uhomecp.medicaltrans.report.deptvolumemonth.rest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import segi.medicaltrans.common.report.transschedule.TransScheduleIce;
import segi.medicaltrans.mthistask.query.DeptTaskPageIceParam;
import segi.medicaltrans.mthistask.query.ExeUser;
import segi.medicaltrans.mthistask.query.MtHisTaskQueryServiceIcePrx;
import segi.medicaltrans.mthistask.query.MtHisTaskRptQueryServiceIcePrx;
import segi.medicaltrans.mthistask.query.MtTaskPageIce;
import segi.medicaltrans.mthistask.query.MtTaskPaginatorIceRsp;
import segi.medicaltrans.report.deptvolumemonth.DeptVolumeMonthIce;
import segi.medicaltrans.report.deptvolumemonth.DeptVolumeMonthPaginator;
import segi.medicaltrans.report.deptvolumemonth.DeptVolumeMonthParam;
import segi.medicaltrans.report.deptvolumemonth.MtrDeptVolumeMonthServiceIcePrx;
import IceExt.IceClientUtil;

import com.segi.uhomecp.common.constant.RestApiConstant;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.medicaltrans.utils.ReportApiUtils;
import com.segi.uhomecp.medicaltrans.utils.ResponseDownloadUtils;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.sso.auth.vo.User;
import com.segi.uhomecp.sso.client.annotation.AdminUserParam;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.web.rest.RestResponse;
import com.segi.uhomecp.wh.common.constant.AppConCurrentTypeConstant;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.dto.ResultDto;
import com.segi.uhomecp.wh.common.utils.AppConCurrentUtils;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.CheckRestParams;
import com.segi.uhomecp.wh.common.utils.DateUtil;
import com.segi.uhomecp.wh.common.utils.ExcelWriter;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/rest-api/v1/medicaltrans/mtrDeptVolumeMonthWebRest")
@Api(value = "medicaltrans/mtrDeptVolumeMonthWebRest", description = "科室统计")
public class MtrDeptVolumeMonthWebRest {

	private static final Logger logger = LoggerFactory.getLogger(MtrDeptVolumeMonthWebRest.class);

	private MtrDeptVolumeMonthServiceIcePrx getMtrDeptVolumeMonthServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(MtrDeptVolumeMonthServiceIcePrx.class);
	}
	
	private MtHisTaskQueryServiceIcePrx getMtHisTaskQueryServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(MtHisTaskQueryServiceIcePrx.class);
	}
	
	/**
	 * @Title: getMtHisTaskRptQueryServiceIcePrx   
	 *  获取ICE服务（查询报表库） 
	 * @author zhaoqing  
	 * @return 
	 */
	private MtHisTaskRptQueryServiceIcePrx getMtHisTaskRptQueryServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(MtHisTaskRptQueryServiceIcePrx.class);
	}
	
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "科室统计分页展示", response = String.class, notes = "{<br/>"
			+ "\"organId\":\"项目ID\",<br/>" 
			+ "\"houseId\":\"科室id 全部就不传\",<br/>" 
			+ "\"cycleYm\":\"年月\",<br/>" 
			+ "\"pageNo\":\"分页页码  第几页\",<br/>" 
			+ "\"pageLength\":\"每页数目	每页记录数\",<br/>" 
            + "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/getDeptVolumeMonthPage.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse getDeptVolumeMonthPage(@AdminUserParam User user,
			@RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"用户未登录,请重新登录!").buidler();
		}
		try {
			// 前端传进来的json转对象
			DeptVolumeMonthParam params = FastjsonUtils.parseObject(paramJson,
					DeptVolumeMonthParam.class);
			// 必填参数验证
			String fields = "pageNo, pageLength, organId, cycleYm";
			String messages = "分页页码, 每页数目, 项目ID, 年月";
			String errInfo = CheckRestParams.checkEmpty(params, fields,
					messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(
						errInfo).buidler();
			}
			if (StringUtils.isNotEmpty(params.getCycleYm())) {
				if (!Pattern.matches(MtConstant.FORMAT_YYYY_MM_REGEX, params.getCycleYm())) {
					// 检验月份格式
					return RestResponse.RestResponseBuilder.
							createFailBuilder("月份格式错误，请传YYYY-MM格式的月份").buidler();
				} else {
					// 去掉月份YYYY-MM中的横杠
					params.setCycleYm(StringUtils.replace(params.getCycleYm(), 
							Constant.SPLIT_BAR, Constant.SPLIT_EMPTY));
				}
			}
			// 获取ICE服务
			DeptVolumeMonthPaginator rpcResp = this.getMtrDeptVolumeMonthServiceIcePrx().getDeptVolumeMonthPage(params);
			if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) {
				return RestResponse.RestResponseBuilder.createBuilder(rpcResp.code, rpcResp.msg)
						.setResult(rpcResp.paginator, rpcResp.resultList.toArray()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(
					rpcResp.msg).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,查询各科室运送量月报表失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"系统异常,查询各科室运送量月报表失败").buidler();
		}
	}
	
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "各科室运送量月报表导出", response = String.class, 
			notes = "各科室运送量月报表导出")
	@ApiImplicitParams({})
	@RequestMapping(value = "/exportDeptVolumeMonth.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse exportDeptVolumeMonth(@AdminUserParam User user, HttpServletResponse response, 
            @RequestParam(value = "organId", required = true) String organId,
            @RequestParam(value = "houseId", required = false) String houseId,            
            @RequestParam(value = "cycleYm", required = true) String cycleYm) {
		try {
			if (null == user) {
				return RestResponse.RestResponseBuilder.createFailBuilder(
						"用户未登录,请重新登录!").buidler();
			}
			// 下载并发次数新增
			boolean exeFlag = AppConCurrentUtils.getInstace().addConCurCount(
					AppConCurrentTypeConstant.MAX_CURRENCY_EXCEL_EXP);
			if (!exeFlag) {
				return RestResponse.RestResponseBuilder.createFailBuilder("当前数据导出操作的人数过多，请稍后再试！").buidler();
			}
			// 入参对象
			DeptVolumeMonthParam params = new DeptVolumeMonthParam();
			params.setOrganId(organId);
			params.setHouseId(houseId);
			params.setCycleYm(cycleYm);
			String fields = "organId, cycleYm";
			String messages = "项目(医院)Id, 月份";
			//非空验证
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			//Number类型格式验证
			errInfo = CheckRestParams.checkInteger(params, "organId", "项目(医院)Id");
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (!Pattern.matches(MtConstant.FORMAT_YYYY_MM_REGEX, params.getCycleYm())) {
				// 检验月份格式
				return RestResponse.RestResponseBuilder.createFailBuilder("月份格式错误，请传YYYY-MM格式的月份").buidler();
			} else {
				// 去掉月份YYYY-MM中的横杠
				params.setCycleYm(StringUtils.replace(params.getCycleYm(), 
						Constant.SPLIT_BAR, Constant.SPLIT_EMPTY));
			} 		
			 // 设置长度为5000
            params.setPageLength(MtConstant.EXPORT_PAGE_LENGTH);
            params.setPageNo(MtConstant.EXPORT_PAGE_NO);
			// 获取ICE服务
			DeptVolumeMonthPaginator rpcResp = this.getMtrDeptVolumeMonthServiceIcePrx().getDeptVolumeMonthPage(params);
			// 判断总条数是否大于5000条
			String totalCount = rpcResp.getPaginator().getTotalCount();
			int totalCountInt = Integer.parseInt(totalCount);
			if (Integer.parseInt(MtConstant.EXPORT_PAGE_LENGTH) < totalCountInt) {
				return RestResponse.RestResponseBuilder.createFailBuilder("当前导出数据条数大于限制的最大条数["
						+ MtConstant.EXPORT_PAGE_LENGTH + "]条，请缩小查询范围再导出！").buidler();
			}
			// 各科室运送量月报表导出
			return exportDeptMonthExcel(response, rpcResp);
		} catch (Exception e) {
			logger.warn("系统异常, 各科室运送量月报表导出", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常, 各科室运送量月报表导出").buidler();
		} finally {
			// 下载并发次数释放
			AppConCurrentUtils.getInstace().subConCurCount(
					AppConCurrentTypeConstant.MAX_CURRENCY_EXCEL_EXP);
		}
	}
	
	/**
	 * @Title: exportMtHisTaskExcel 
	 * @Description: 各科室运送量月报表导出 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月14日上午10:57:57
	 */
	private RestResponse exportDeptMonthExcel(HttpServletResponse response, DeptVolumeMonthPaginator rpcResp) {
		try {
			File file = null;
			String title = "各科室运送量月报表导出.xls";
			String[] columnTitles = { "所属项目", "科室", "科室位置", "运送总量", "调度任务数", "自主任务数"};
			String[] data = null;
            List<String[]> dataList = new ArrayList<String[]>();
            if (rpcResp.getResultList() != null && rpcResp.getResultList().size() > 0) {
            	List<DeptVolumeMonthIce> resultList = rpcResp.getResultList();
            	for (int i = 0; i < resultList.size(); i++) {
            		DeptVolumeMonthIce deptIce = resultList.get(i);
            		data = new String[6];
            		data[0] = deptIce.getOrganName(); // 所属组织
                    data[1] = deptIce.getHouseName(); // 科室
                    if (StringUtils.isEmpty(deptIce.getSidName())) {
                    	data[2] = deptIce.getBuildName() + Constant.SPLIT_BAR + deptIce.getFloorName();
					}else {
						data[2] = deptIce.getBuildName()+ "(" + deptIce.getSidName() + ")" 
								+ Constant.SPLIT_BAR + deptIce.getFloorName();
					}
                    data[3] = deptIce.getTransAmount(); // 运送总量
                    data[4] = deptIce.getDispatchAmount(); // 调度任务数
                    data[5] = deptIce.getAutonomousAmount(); // 自主任务数
                    dataList.add(data);
            	}
            }
			file = ExcelWriter.simpleCreate(RestApiConstant.MEDICALTRANS, columnTitles, dataList);
	        	ResponseDownloadUtils.downloadAndDelete(response, file, title);
	        return null;
		} catch (Exception e) {
			logger.error("各科室运送量月报表导出异常", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("各科室运送量月报表导出异常").buidler();
		}
	}
	
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "科室运送任务列表分页", response = String.class, notes = "{<br/>"
			+ "\"organId\":\"项目ID\",<br/>" 
			+ "\"houseId\":\"科室id 全部就不传\",<br/>" 
			+ "\"cycleYm\":\"年月\",<br/>" 
			+ "\"pageNo\":\"分页页码  第几页\",<br/>" 
			+ "\"pageLength\":\"每页数目	每页记录数\",<br/>" 
            + "}<br/>")
	@ApiImplicitParams({})
	@RequestMapping(value = "/getDeptTaskPage.json", method = { RequestMethod.POST })
	@ResponseBody
	public RestResponse getDeptTaskPage(@AdminUserParam User user,
			@RequestBody(required = true) String paramJson) {
		if (null == user) {
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"用户未登录,请重新登录!").buidler();
		}
		try {
			// 前端传进来的json转对象
			DeptTaskPageIceParam params = FastjsonUtils.parseObject(paramJson,
					DeptTaskPageIceParam.class);
			// 必填参数验证
			String fields = "pageNo, pageLength, organId, cycleYm, houseId";
			String messages = "分页页码, 每页数目, 项目ID, 年月, 科室id";
			String errInfo = CheckRestParams.checkEmpty(params, fields,
					messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(
						errInfo).buidler();
			}
			if (StringUtils.isNotEmpty(params.getCycleYm())) {
				if (!Pattern.matches(MtConstant.FORMAT_YYYY_MM_REGEX, params.getCycleYm())) {
					// 检验月份格式
					return RestResponse.RestResponseBuilder.
							createFailBuilder("月份格式错误，请传YYYY-MM格式的月份").buidler();
				} else {
					// 拆分月份
					String[] cycleYms = params.getCycleYm().split(Constant.SPLIT_BAR);
					int year = Integer.parseInt(cycleYms[0]);
					int month = Integer.parseInt(cycleYms[1]);
					// 设置开始结束时间
					params.setBeginTime(DateUtil.getFirstTimeOfMonth(year, month));
					params.setEndTime(DateUtil.getLastTimeOfMonth(year, month));
				}
			}
			// 验证科室Id
			if (StringUtils.isNotBlank(params.getHouseId())) {
				errInfo = CheckRestParams.checkInteger(params, "houseId", "科室Id");
				if (null != errInfo) {
					return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
				}
			}
			// 查询排程表获得参数时间
			ResultDto<String,String,TransScheduleIce> dto = MtCommonServiceUtils
					.queryTransSchedule(Integer.valueOf(params.getOrganId()));
			if (dto != null && dto.getIsSucc() && null != dto.getObj() && StringUtils.isNotBlank(dto.getObj().getParamDate())) {
				TransScheduleIce transScheduleIce = dto.getObj();
				// 获得使用的参数时间
				params.setParamDate(getDeadline(transScheduleIce));
			}else {
				logger.warn("系统异常, 查询排程表获得参数时间失败");
				return RestResponse.RestResponseBuilder.createFailBuilder(
						"系统异常,查询排程表获得参数时间").buidler();
			}
			MtTaskPaginatorIceRsp rpcResp = new MtTaskPaginatorIceRsp();
			// 获取ICE服务
			if (ReportApiUtils.queryRptDataBaseFlag(params.getCycleYm())) {
				// 两年前数据查询报表库
				rpcResp = this.getMtHisTaskRptQueryServiceIcePrx().getDeptTaskRptPage(params);
			} else {
				// 两年内的数据查询业务库
				rpcResp = this.getMtHisTaskQueryServiceIcePrx().getDeptTaskPage(params);
			}
			if (RpcError.SUCCESS.getCode().equals(rpcResp.code)) { 
				return RestResponse.RestResponseBuilder.createBuilder(rpcResp.code, rpcResp.message)
						.setResult(rpcResp.paginator, rpcResp.resultList.toArray()).buidler();
			}
			return RestResponse.RestResponseBuilder.createFailBuilder(
					rpcResp.message).buidler();
		} catch (Exception e) {
			logger.warn("系统异常,查询运送类型分页失败", e);
			return RestResponse.RestResponseBuilder.createFailBuilder(
					"系统异常,查询运送类型分页失败").buidler();
		}
	}
	
	@ApiOperation(value = RestApiConstant.PREFIXPROJECTNAME + "科室运送任务列表导出", response = String.class, 
			notes = "科室运送任务列表导出")
	@ApiImplicitParams({})
	@RequestMapping(value = "/exportDeptTaskPage.json", method = { RequestMethod.GET })
	@ResponseBody
	public RestResponse exportDeptTaskPage(@AdminUserParam User user, HttpServletResponse response, 
            @RequestParam(value = "organId", required = true) String organId,
            @RequestParam(value = "houseId", required = true) String houseId,            
            @RequestParam(value = "cycleYm", required = true) String cycleYm) {
		try {
			if (null == user) {
				return RestResponse.RestResponseBuilder.createFailBuilder(
						"用户未登录,请重新登录!").buidler();
			}
			// 下载并发次数新增
			boolean exeFlag = AppConCurrentUtils.getInstace().addConCurCount(
					AppConCurrentTypeConstant.MAX_CURRENCY_EXCEL_EXP);
			if (!exeFlag) {
				response.getWriter().write("当前数据导出操作的人数过多，请稍后再试！");
				return RestResponse.RestResponseBuilder.createFailBuilder("当前数据导出操作的人数过多，请稍后再试！").buidler();
			}
			// 入参对象
			DeptTaskPageIceParam params = new DeptTaskPageIceParam();
			params.setOrganId(organId);
			params.setHouseId(houseId);
			params.setCycleYm(cycleYm);
			String fields = "organId, cycleYm";
			String messages = "项目(医院)Id, 月份";
			//非空验证
			String errInfo = CheckRestParams.checkEmpty(params, fields, messages);
			if (null != errInfo) {
				return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
			}
			if (StringUtils.isNotEmpty(params.getCycleYm())) {
				if (!Pattern.matches(MtConstant.FORMAT_YYYY_MM_REGEX, params.getCycleYm())) {
					// 检验月份格式
					return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
				} else {
					// 拆分月份
					String[] cycleYms = params.getCycleYm().split(Constant.SPLIT_BAR);
					int year = Integer.parseInt(cycleYms[0]);
					int month = Integer.parseInt(cycleYms[1]);
					// 设置开始结束时间
					params.setBeginTime(DateUtil.getFirstTimeOfMonth(year, month));
					params.setEndTime(DateUtil.getLastTimeOfMonth(year, month));
				}
			}
			// 验证科室Id
			if (StringUtils.isNotBlank(params.getHouseId())) {
				errInfo = CheckRestParams.checkInteger(params, "houseId", "科室Id");
				if (null != errInfo) {
					return RestResponse.RestResponseBuilder.createFailBuilder(errInfo).buidler();
				}
			}
			// 查询排程表获得参数时间
			ResultDto<String,String,TransScheduleIce> dto = MtCommonServiceUtils
					.queryTransSchedule(Integer.valueOf(params.getOrganId()));
			if (dto != null && dto.getIsSucc() && null != dto.getObj() && StringUtils.isNotBlank(dto.getObj().getParamDate())) {
				TransScheduleIce transScheduleIce = dto.getObj();
				// 获得使用的参数时间
				params.setParamDate(getDeadline(transScheduleIce));
			} else {
				logger.warn("系统异常, 查询排程表获得参数时间失败");
				response.getWriter().write("系统异常, 查询排程表获得参数时间失败");
				return RestResponse.RestResponseBuilder.createFailBuilder("系统异常, 查询排程表获得参数时间失败").buidler();
			}
			// 设置每次导出1000条
			String pageLength = MtConstant.EXPORT_EVERY_PAGE_LENGTH_V1;
			params.setPageLength(pageLength);
			params.setPageNo(MtConstant.EXPORT_PAGE_NO);
			// 导出标识
            params.setExportFlag(true);
			// 获取ICE服务
            MtTaskPaginatorIceRsp rpcResp = new MtTaskPaginatorIceRsp();
			if (ReportApiUtils.queryRptDataBaseFlag(params.getCycleYm())) {
				// 两年前数据查询报表库
				rpcResp = this.getMtHisTaskRptQueryServiceIcePrx().getDeptTaskRptPage(params);
			} else {
				// 两年内的数据查询业务库
				rpcResp = this.getMtHisTaskQueryServiceIcePrx().getDeptTaskPage(params);
			}
			// 判断总条数是否大于10000条
			String totalCount = rpcResp.getPaginator().getTotalCount();
			int totalCountInt = Integer.parseInt(totalCount);
			if (Integer.parseInt(MtConstant.EXPORT_PAGE_LENGTH_10000) < totalCountInt) {
				return RestResponse.RestResponseBuilder.createFailBuilder("当前导出数据条数大于限制的最大条数["
						+ MtConstant.EXPORT_PAGE_LENGTH_10000 + "]条，请缩小查询范围再导出！").buidler();
			}
			List<MtTaskPageIce> resultList = new ArrayList<MtTaskPageIce>();
			resultList.addAll(rpcResp.getResultList());
			int pageLengthInt = Integer.parseInt(pageLength);
			String curPageNo = rpcResp.getPaginator().getPageNo();
			int curPageNoInt = Integer.parseInt(curPageNo);
			// 当前数据条数
			int curCount = curPageNoInt * pageLengthInt;
			while (curCount < totalCountInt) {
				params.setPageNo(String.valueOf(curPageNoInt + 1));
				if (ReportApiUtils.queryRptDataBaseFlag(params.getCycleYm())) {
					// 两年前数据查询报表库
					rpcResp = this.getMtHisTaskRptQueryServiceIcePrx()
							.getDeptTaskRptPage(params);
				} else {
					// 两年内的数据查询业务库
					rpcResp = this.getMtHisTaskQueryServiceIcePrx()
							.getDeptTaskPage(params);
				}
				if (AppUtils.isNotEmpty(rpcResp.getResultList())) {
					resultList.addAll(rpcResp.getResultList());
				}
				curPageNo = rpcResp.getPaginator().getPageNo();
				curPageNoInt = Integer.parseInt(curPageNo);
				curCount = curPageNoInt * pageLengthInt;
			}
			// 科室运送任务列表导出
			return exportDeptTaskExcel(response, resultList);
		} catch (Exception e) {
			logger.warn("系统异常, 科室运送任务列表导出", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常, 科室运送任务列表导出").buidler();
		} finally {
			// 下载并发次数释放
			AppConCurrentUtils.getInstace().subConCurCount(
					AppConCurrentTypeConstant.MAX_CURRENCY_EXCEL_EXP);
		}
	}
	
	/**
	 * @Title: exportDeptTaskExcel 
	 * @Description: 科室运送任务列表导出 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月14日上午11:16:19
	 */
	private RestResponse exportDeptTaskExcel(HttpServletResponse response, List<MtTaskPageIce> resultList) {
		try {
			File file = null;
			String title = "科室运送量列表导出.xls";
			String[] columnTitles = { "所属项目", "任务ID", "运送来源", "下单时间", "运送类型", "出发地", "目的地"
					, "任务类型", "运送员", "任务状态", "运送开始时间", "运送结束时间", "运送内容描述", "派单人", 
					"派单时间", "任务实际开始时间", "任务实际结束时间", "签收时间", "签收人", "评价结论", "评价信息"};
			String[] data = null;
            List<String[]> dataList = new ArrayList<String[]>();
            if (AppUtils.isNotEmpty(resultList)) {
            	for (int i = 0; i < resultList.size(); i++) {
            		MtTaskPageIce deptIce = resultList.get(i);
            		data = new String[21];
            		data[0] = deptIce.getOrganName(); // 所属组织
            		data[1] = deptIce.getTaskId(); // 任务id
                    data[2] = deptIce.getSourceHouseName(); // 运送来源
                    data[3] = deptIce.getCreateDate();// 下单时间
                    data[4] = deptIce.getTransTypeParentCodeName(); // 运送类型
                    data[5] = deptIce.getFromHouseName(); // 出发地
                    data[6] = deptIce.getToHouseName(); // 目的地
                    data[7] = deptIce.getTaskTypeName(); // 任务类型
                    if (deptIce.getUserList() != null && AppUtils.isNotEmpty(deptIce.getUserList())) {
                    	List<ExeUser> userList = deptIce.getUserList();
						List<String> userNameNoList = AppUtils.list2ParamsList(
								userList, ExeUser.class, "userNameNo");
                    	data[8] = AppUtils.listToString(userNameNoList, ','); // 运送员
					}
                    data[9] = deptIce.getStatusName(); // 状态
                    data[10] = deptIce.getBeginTime(); // 运送开始时间
                    data[11] = deptIce.getEndTime(); // 运送结束时间
                    data[12] = deptIce.getTaskContent(); // 运送内容描述	
					data[13] = deptIce.getDispatchUserName(); // 派单人
					data[14] = deptIce.getSendTime(); // 派单时间
					data[15] = deptIce.getExeBeginTime(); // 任务实际开始时间
					data[16] = deptIce.getExeEndTime(); // 任务实际结束时间
					data[17] = deptIce.getReceiveTime(); // 签收时间
					data[18] = deptIce.getReceiverUserName(); // 签收人
					data[19] = deptIce.getEvaluate(); // 评价结论
					data[20] = deptIce.getEvaluateContent(); // 评价信息
                    dataList.add(data);
            	}
            }
			file = ExcelWriter.simpleCreate(RestApiConstant.MEDICALTRANS, columnTitles, dataList);
	        	ResponseDownloadUtils.downloadAndDelete(response, file, title);
	        return null;
		} catch (Exception e) {
			logger.warn("系统异常, 科室运送任务列表导出", e);
			return RestResponse.RestResponseBuilder.createFailBuilder("系统异常, 科室运送任务列表导出").buidler();
		}
	}
	
	/**
	 * @Title: getDeadline 
	 * @Description: 获得排程表中取数截止时间 
	 * @author liuyi@segimail.com 
	 * @date 2018年10月10日下午4:30:53
	 */
	public String getDeadline(TransScheduleIce transScheduleIce){
		String paramDate = transScheduleIce.getParamDate().replace(Constant.SPLIT_BAR, "")
				.replace(Constant.SPLIT_COLON, "").replace(Constant.SPLIT_BLANK, "");
		if (StringUtils.isNotEmpty(transScheduleIce.getExcDate()) 
				&& StringUtils.isNotEmpty(transScheduleIce.getExcEndDate())) {
			String excDate = transScheduleIce.getExcDate().replace(Constant.SPLIT_BAR, "")
			.replace(Constant.SPLIT_COLON, "").replace(Constant.SPLIT_BLANK, "");
			String excEndDate = transScheduleIce.getExcEndDate().replace(Constant.SPLIT_BAR, "")
					.replace(Constant.SPLIT_COLON, "").replace(Constant.SPLIT_BLANK, "");
			if (Long.valueOf(paramDate) > Long.valueOf(excDate)) {
				return paramDate;
			} else {
				return excEndDate;
			}
		} else {
			// 执行时间 和 本月截止时间为空时 用参数时间
			return paramDate;
		}
	}
}
