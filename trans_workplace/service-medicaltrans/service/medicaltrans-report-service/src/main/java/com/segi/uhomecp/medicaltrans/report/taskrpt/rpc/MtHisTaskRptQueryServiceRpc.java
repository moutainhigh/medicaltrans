package com.segi.uhomecp.medicaltrans.report.taskrpt.rpc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import page.RpcPageIce;
import segi.datacachesvr.queryInfo.TOrganInfo;
import segi.medicaltrans.mthistask.query.DeptTaskPageIceParam;
import segi.medicaltrans.mthistask.query.FixedTaskExeDetailIce;
import segi.medicaltrans.mthistask.query.FixedTaskExeDetailReturnIce;
import segi.medicaltrans.mthistask.query.FixedTaskHisExeInfoIce;
import segi.medicaltrans.mthistask.query.FixedTaskHisExePageRsp;
import segi.medicaltrans.mthistask.query.MtTaskDetailIceParam;
import segi.medicaltrans.mthistask.query.MtTaskDetailRetIce;
import segi.medicaltrans.mthistask.query.MtTaskDetailRetIceRsp;
import segi.medicaltrans.mthistask.query.MtTaskPageIce;
import segi.medicaltrans.mthistask.query.MtTaskPageIceParam;
import segi.medicaltrans.mthistask.query.MtTaskPaginatorIceRsp;
import segi.medicaltrans.mthistask.query._MtHisTaskRptQueryServiceIceDisp;
import Ice.Current;
import cn.jpush.api.utils.StringUtils;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.enums.TransStatusEnum;
import com.segi.uhomecp.medicaltrans.report.taskrpt.dto.MtHisTaskPageDto;
import com.segi.uhomecp.medicaltrans.report.taskrpt.service.MtHisTaskRptQueryService;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRoute;
import com.segi.uhomecp.medicaltrans.utils.DateCommonUtil;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;
import com.segi.uhomecp.wh.common.utils.DataTypeConverUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;
import com.segi.uhomecp.wh.common.utils.PageUtils;

/**
 * @ClassName:  MtHisTaskRptQueryServiceRpc   
 * @Description:运送记录查询   
 * @author: zhaoqing
 * @date:   2018年8月6日 下午3:48:57
 */
@Component
public class MtHisTaskRptQueryServiceRpc extends _MtHisTaskRptQueryServiceIceDisp {
    	    
	private static final long serialVersionUID = 7065341426928764645L;

	public static final Logger LOGGER = LoggerFactory.getLogger(MtHisTaskRptQueryServiceRpc.class);
	
	@Autowired
	private MtHisTaskRptQueryService mtHisTaskQueryService;
	
	/**
	 * <p>Title: queryMtHisTaskRptPage</p>   
	 * <p>Description: 运送记录分页查询</p> 
	 * <p>zhaoqing</p>
	 * @param mtTaskPageIceParam
	 * @param __current
	 * @return   
	 */
	@Override
	public MtTaskPaginatorIceRsp queryMtHisTaskRptPage(
			MtTaskPageIceParam mtTaskPageIceParam, Current __current) {
		MtTaskPaginatorIceRsp rsp = new MtTaskPaginatorIceRsp(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new RpcPageIce(mtTaskPageIceParam.getPageLength(), mtTaskPageIceParam.getPageNo(), "0"), 
				new ArrayList<MtTaskPageIce>());
		try {
			MtHisTaskPageDto dto = BeanCopierUtils.copyProperties(
					mtTaskPageIceParam, MtHisTaskPageDto.class, true);	
			// 设置查询条件
			setQueryDto(mtTaskPageIceParam, dto);
			// 分页查询数据
			ResultInfo result = mtHisTaskQueryService.queryMtHisTaskPage(dto);
			if (!result.getIsSucc()) {
				rsp.setMessage(result.getMessage());
				rsp.setCode(RpcError.FAIL.getCode());
				return rsp;
			}
			@SuppressWarnings("unchecked")
			PageList<MtHisTaskPageDto> pageList = (PageList<MtHisTaskPageDto>) result.getObject1();
			if (AppUtils.isNotEmpty(pageList)) {
				rsp.setPaginator(PageUtils.paginator2RpcPageIce(pageList.getPaginator()));
			}
			@SuppressWarnings("unchecked")
			List<MtTaskPageIce> resultList = result.getObjList();
			if (AppUtils.isNotEmpty(resultList)) {
				rsp.setResultList(resultList);
			}
		} catch (Exception e) {
			LOGGER.error("queryMtTaskRptPage", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("运送记录分页查询失败");
		}
		return rsp;
	}
	
	/**
	 * @Title: setQueryDto   
	 *  设置查询条件
	 * @author zhaoqing  
	 * @param mtTaskPageIceParam
	 * @param dto  
	 */
	private void setQueryDto(MtTaskPageIceParam mtTaskPageIceParam, MtHisTaskPageDto dto) {
		TOrganInfo tOrganInfo = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
		// 设置一级物业Id
		dto.setGroupOrganId(Integer.valueOf(tOrganInfo.getOrganId()));
		// 转换开始时间
		String beginTimeStr = DateUtil.convertBeginDate(mtTaskPageIceParam.getBeginTime());
		if (StringUtils.isNotEmpty(beginTimeStr) && beginTimeStr.length() >= 12) {
			// 开始时间精确到分
			dto.setBeginTime(Long.valueOf(beginTimeStr.substring(0, 12)));
		}
		// 转换结束时间
		String endTimeStr = DateUtil.convertEndDate(mtTaskPageIceParam.getEndTime());
		if (StringUtils.isNotEmpty(endTimeStr) && endTimeStr.length() >= 12) {
			// 结束时间精确到分
			dto.setEndTime(Long.valueOf(endTimeStr.substring(0, 12)));
		}
		// 设置年份
		dto.setYear(Integer.valueOf(DateUtil.getYearOfDate(mtTaskPageIceParam.getBeginTime())));
		// 设置查询标识
		dto.setQueryFlag(MtConstant.TASK_HIS_QUERY_FLAG_ONE);
	}
	
	/**
	 * <p>Title: queryMtHisFixedTaskExeInfoRptPage</p>   
	 * <p>Description: 固定任务执行信息分页查询</p> 
	 * <p>zhaoqing</p>
	 * @param mtTaskPageIceParam
	 * @param __current
	 * @return   
	 */
	@Override
	public FixedTaskHisExePageRsp queryMtHisFixedTaskExeInfoRptPage(
			MtTaskPageIceParam mtTaskPageIceParam, Current __current) {
		FixedTaskHisExePageRsp rsp = new FixedTaskHisExePageRsp(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new RpcPageIce(), new ArrayList<FixedTaskHisExeInfoIce>());
		try {
			MtHisTaskPageDto dto = BeanCopierUtils.copyProperties(
					mtTaskPageIceParam, MtHisTaskPageDto.class, true);
			// 设置年份
			dto.setYear(Integer.valueOf(DateUtil.getYearOfDate(mtTaskPageIceParam.getCycleYm())));
			// 分页查询数据
			ResultInfo result = mtHisTaskQueryService.queryMtHisFixedTaskExeInfoPage(dto);
			if (!result.getIsSucc()) {
				rsp.setMessage(result.getMessage());
				rsp.setCode(RpcError.FAIL.getCode());
				return rsp;
			}
			@SuppressWarnings("unchecked")
			PageList<MtTaskRoute> pageList = (PageList<MtTaskRoute>) result.getObject1();
			if (AppUtils.isNotEmpty(pageList)) {
				rsp.setPaginator(PageUtils.paginator2RpcPageIce(pageList.getPaginator()));
			}
			@SuppressWarnings("unchecked")
			List<FixedTaskHisExeInfoIce> resultList = result.getObjList();
			if (AppUtils.isNotEmpty(resultList)) {
				rsp.setResultList(resultList);
			}
		} catch (Exception e) {
			LOGGER.error("queryFixedTaskExeInfoRptPage", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("固定任务执行信息分页查询失败");
		}
		return rsp;
	}
	
	/**
	 * <p>Title: queryMtHisTaskRptDetail</p>   
	 * <p>Description: 运送任务详情(自主任务/调度任务)</p> 
	 * <p>zhaoqing</p>
	 * @param mtTaskDetailIceParam
	 * @param __current
	 * @return   
	 */
	@Override
	public MtTaskDetailRetIceRsp queryMtHisTaskRptDetail(
			MtTaskDetailIceParam mtTaskDetailIceParam, Current __current) {
		MtTaskDetailRetIceRsp rsp = new MtTaskDetailRetIceRsp();
		rsp.code = RpcError.SUCCESS.getCode();
		rsp.message = RpcError.SUCCESS.getMessage();
		try {
			MtHisTaskPageDto dto = BeanCopierUtils.copyProperties(
					mtTaskDetailIceParam, MtHisTaskPageDto.class, true);
			// 设置年份
			dto.setYear(Integer.valueOf(DateUtil.getYearOfDate(mtTaskDetailIceParam.getCycleYm())));
			ResultInfo result = mtHisTaskQueryService.queryMtHisTaskDetail(dto);
			if (!result.getIsSucc()) {
				rsp.setMessage(result.getMessage());
				rsp.setCode(RpcError.FAIL.getCode());
				return rsp;
			}
			MtTaskDetailRetIce detailIce = (MtTaskDetailRetIce) result.getObject1();
	        rsp.setData(detailIce);
		} catch (Exception e) {
			LOGGER.error("queryMtHisTaskRptDetail", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("查询任务详情失败");
		 }
		return rsp;
	}

	/**
	 * <p>Title: queryMtHisTaskFixedRptDetail</p>   
	 * <p>Description: 运送任务详情(固定任务)</p> 
	 * <p>zhaoqing</p>
	 * @param mtTaskDetailIceParam
	 * @param __current
	 * @return   
	 */
	@Override
	public MtTaskDetailRetIceRsp queryMtHisTaskFixedRptDetail(
			MtTaskDetailIceParam mtTaskDetailIceParam, Current __current) {
		MtTaskDetailRetIceRsp rsp = new MtTaskDetailRetIceRsp();
		rsp.code = RpcError.SUCCESS.getCode();
		rsp.message = RpcError.SUCCESS.getMessage();
		try {
			MtHisTaskPageDto dto = BeanCopierUtils.copyProperties(
					mtTaskDetailIceParam, MtHisTaskPageDto.class, true);
			// 设置年份
			dto.setYear(Integer.valueOf(DateUtil.getYearOfDate(mtTaskDetailIceParam.getCycleYm())));
			ResultInfo result = mtHisTaskQueryService.queryMtHisTaskFixedDetail(dto);
			if (!result.getIsSucc()) {
				rsp.setMessage(result.getMessage());
				rsp.setCode(RpcError.FAIL.getCode());
				return rsp;
			}
			MtTaskDetailRetIce detailIce = (MtTaskDetailRetIce) result.getObject1();
	        rsp.setData(detailIce);
		} catch (Exception e) {
			LOGGER.error("queryMtHisTaskFixedRptDetail", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("查询任务详情失败");
		 }
		return rsp;
	}
	
	/**
	 * @discription list分页
	 * @author zhangyang@segimail.com       
	 * @created 2018年5月21日 下午6:04:23     
	 * @param list
	 * @return
	 */
	public <V> List<V> listToPaginator(List<V> list, Paginator paginator) {  
		return list.subList(paginator.getStartRow() - 1, paginator.getEndRow());
    }  

	/**
	 * <p>Title: queryMtHisFixedTaskExeRptDetail</p>   
	 * <p>Description: 固定任务某个点执行信息详情</p> 
	 * <p>zhaoqing</p>
	 * @param routeId
	 * @param organId
	 * @param __current
	 * @return   
	 */
	@Override
	public FixedTaskExeDetailReturnIce queryMtHisFixedTaskExeRptDetail(
			MtTaskDetailIceParam mtTaskDetailIceParam, Current __current) {
		FixedTaskExeDetailReturnIce rsp = new FixedTaskExeDetailReturnIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), 
				new FixedTaskExeDetailIce());
		try {
			MtHisTaskPageDto dto = BeanCopierUtils.copyProperties(
					mtTaskDetailIceParam, MtHisTaskPageDto.class, true);
			// 设置年份
			dto.setYear(Integer.valueOf(DateUtil.getYearOfDate(mtTaskDetailIceParam.getCycleYm())));
			// 固定任务某个点执行信息详情查询
			ResultInfo result = mtHisTaskQueryService
					.queryMtHisFixedTaskExeDetail(dto);
			if (!result.getIsSucc()) {
				rsp.setMessage(result.getMessage());
				rsp.setCode(RpcError.FAIL.getCode());
				return rsp;
			}
			FixedTaskExeDetailIce detailIce = (FixedTaskExeDetailIce) result.getObject1();
			rsp.setData(detailIce);
		} catch (Exception e) {
			LOGGER.error("queryMtHisFixedTaskExeRptDetail", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("查询执行详情失败");
		}
		return rsp;
	}

	/**
	 * <p>Title: queryMtHisTaskRptPageByUser</p>   
	 * <p>Description: 运送员运送明细分页查询</p> 
	 * <p>zhaoqing</p>
	 * @param mtTaskPageIceParam
	 * @param __current
	 * @return   
	 */
	@Override
	public MtTaskPaginatorIceRsp queryMtHisTaskRptPageByUser(
			MtTaskPageIceParam mtTaskPageIceParam, Current __current) {
		MtTaskPaginatorIceRsp rsp = new MtTaskPaginatorIceRsp(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new RpcPageIce(mtTaskPageIceParam.getPageLength(), mtTaskPageIceParam.getPageNo(), "0"), 
				new ArrayList<MtTaskPageIce>());
		try {
			MtHisTaskPageDto dto = BeanCopierUtils.copyProperties(
					mtTaskPageIceParam, MtHisTaskPageDto.class, true);
			// 设置查询条件
			setTaskPageQueryDto(dto, mtTaskPageIceParam.getCycleYm());
			// 分页查询数据
			ResultInfo result = mtHisTaskQueryService.queryMtHisTaskPage(dto);
			if (!result.getIsSucc()) {
				rsp.setMessage(result.getMessage());
				rsp.setCode(RpcError.FAIL.getCode());
				return rsp;
			}
			@SuppressWarnings("unchecked")
			PageList<MtHisTaskPageDto> pageList = (PageList<MtHisTaskPageDto>) result.getObject1();
			if (AppUtils.isNotEmpty(pageList)) {
				rsp.setPaginator(PageUtils.paginator2RpcPageIce(pageList.getPaginator()));
			}
			@SuppressWarnings("unchecked")
			List<MtTaskPageIce> resultList = result.getObjList();
			if (AppUtils.isNotEmpty(resultList)) {
				rsp.setResultList(resultList);
			}
		} catch (Exception e) {
			LOGGER.error("queryMtHisTaskRptPageByUser", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("运送员运送明细分页查询失败");
		}
		return rsp;
	}
	
	/**
	 * @Title: setTaskPageQueryDto   
	 *  设置查询条件 
	 * @author zhaoqing  
	 * @param dto 
	 */
	private void setTaskPageQueryDto(MtHisTaskPageDto dto, String cycleYm) {
		TOrganInfo tOrganInfo = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
		// 设置一级物业Id
		dto.setGroupOrganId(Integer.valueOf(tOrganInfo.getOrganId()));
		// 转换实际开始时间			
		dto.setExeBeginTime(DataTypeConverUtils.parseLongToDate(dto.getBeginTime()));	
		// 转换实际结束时间
		dto.setExeEndTime(DataTypeConverUtils.parseLongToDate(dto.getEndTime()));
		// 设置任务状态为已完成
		dto.setTaskStatus(TransStatusEnum.TRANS_COMPLETED.getCode());
		// 设置查询标识
		dto.setQueryFlag(MtConstant.TASK_HIS_QUERY_FLAG_TWO);
		// 设置年份
		dto.setYear(Integer.valueOf(DateUtil.getYearOfDate(String.valueOf(dto.getBeginTime()))));
		// 当前时间的月份
		String curDateMonth = DateUtil.getYearMonthOfDate(DateUtil.formatDateToStringYYMMDD(new Date()));
		if (null != curDateMonth && curDateMonth.equals(DateUtil.converDateToNum(cycleYm))) { // 当月数据
			// 设置执行结束日期的限定日期
			dto.setLimitExeEndDate(DateCommonUtil.getYesterDayLastMoment(new Date()));
			// 设置标识字段
			dto.setLimitExeEndDateFlag(MtConstant.LIMIT_DATE_QUERY_FLAG);
		}
	}
	
	/**
	 * @Title: getDeptTaskRptPage 
	 * @Description: 科室列表信息查询 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月14日上午11:34:15
	 */
	@Override
	public MtTaskPaginatorIceRsp getDeptTaskRptPage(
			DeptTaskPageIceParam deptTaskPageIceParam, Current __current) {
		MtTaskPaginatorIceRsp rsp = new MtTaskPaginatorIceRsp(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new RpcPageIce(deptTaskPageIceParam.getPageLength(), deptTaskPageIceParam.getPageNo(), "0"), 
				new ArrayList<MtTaskPageIce>());
		try {
			MtHisTaskPageDto dto = BeanCopierUtils.copyProperties(
					deptTaskPageIceParam, MtHisTaskPageDto.class, true);
			TOrganInfo tOrganInfo = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
			if (null == dto.getOrganId() || null == tOrganInfo) {
				return rsp;
			}
			// 设置一级物业Id
			dto.setGroupOrganId(Integer.valueOf(tOrganInfo.getOrganId()));
			// 设置查询标识
			dto.setQueryFlag(MtConstant.TASK_HIS_QUERY_FLAG_THREE);
			// 设置科室id
			dto.setSourceHouseId(Integer.valueOf(deptTaskPageIceParam.getHouseId()));
			dto.setYear(Integer.valueOf(DateUtil.getYearOfDate(deptTaskPageIceParam.getBeginTime())));
			// 分页查询数据
			ResultInfo result = mtHisTaskQueryService.queryMtHisTaskPage(dto);
			if (!result.getIsSucc()) {
				rsp.setMessage(result.getMessage());
				rsp.setCode(RpcError.FAIL.getCode());
				return rsp;
			}
			@SuppressWarnings("unchecked")
			PageList<MtHisTaskPageDto> pageList = (PageList<MtHisTaskPageDto>) result.getObject1();
			if (AppUtils.isNotEmpty(pageList)) {
				rsp.setPaginator(PageUtils.paginator2RpcPageIce(pageList.getPaginator()));
			}
			@SuppressWarnings("unchecked")
			List<MtTaskPageIce> resultList = result.getObjList();
			if (AppUtils.isNotEmpty(resultList)) {
				rsp.setResultList(resultList);
			}
		} catch (Exception e) {
			LOGGER.error("getDeptTaskPage", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}
	
}
