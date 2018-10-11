package com.segi.uhomecp.medicaltrans.taskhis.rpc;

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
import segi.medicaltrans.mthistask.query._MtHisTaskQueryServiceIceDisp;
import Ice.Current;
import cn.jpush.api.utils.StringUtils;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.enums.TransStatusEnum;
import com.segi.uhomecp.medicaltrans.enums.TransTaskTypeEnum;
import com.segi.uhomecp.medicaltrans.taskhis.dto.MtHisTaskPageDto;
import com.segi.uhomecp.medicaltrans.taskhis.util.MtHisTaskQueryServiceUtil;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTaskRoute;
import com.segi.uhomecp.medicaltrans.utils.DateCommonUtil;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;
import com.segi.uhomecp.wh.common.utils.DataTypeConverUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;
import com.segi.uhomecp.wh.common.utils.PageUtils;

/**
 * @ClassName:  MtHisTaskQueryServiceRpc   
 * @Description:运送记录查询   
 * @author: zhaoqing
 * @date:   2018年8月6日 下午3:48:57
 */
@Component
public class MtHisTaskQueryServiceRpc extends _MtHisTaskQueryServiceIceDisp {
    	    
	private static final long serialVersionUID = 7065341426928764645L;

	public static final Logger LOGGER = LoggerFactory.getLogger(MtHisTaskQueryServiceRpc.class);
	
	@Autowired
	private MtHisTaskQueryServiceUtil mtHisTaskQueryServiceUtil;
	
	/**
	 * <p>Title: queryMtHisTaskPage</p>   
	 * <p>Description: 运送记录分页查询</p> 
	 * <p>zhaoqing</p>
	 * @param mtTaskPageIceParam
	 * @param __current
	 * @return   
	 */
	@Override
	public MtTaskPaginatorIceRsp queryMtHisTaskPage(
			MtTaskPageIceParam mtTaskPageIceParam, Current __current) {
		MtTaskPaginatorIceRsp rsp = new MtTaskPaginatorIceRsp(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new RpcPageIce(mtTaskPageIceParam.getPageLength(), mtTaskPageIceParam.getPageNo(), "0"), 
				new ArrayList<MtTaskPageIce>());
		try {
			MtHisTaskPageDto dto = BeanCopierUtils.copyProperties(
					mtTaskPageIceParam, MtHisTaskPageDto.class, true);
			TOrganInfo tOrganInfo = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
			if (null == dto.getOrganId() || null == tOrganInfo) {
				return rsp;
			}
			// 设置分页查询条件
			initQueryMttaskPageDto(dto, tOrganInfo);
			// 分页查询数据
			ResultInfo result = mtHisTaskQueryServiceUtil.queryMtHisTaskPage(dto);
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
			LOGGER.error("queryMtTaskPage", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("运送记录分页查询失败");
		}
		return rsp;
	}
	
	/**
	 * @Title: initQueryMttaskPageDto   
	 *  设置分页查询条件 
	 * @author zhaoqing  
	 * @param dto
	 * @param tOrganInfo   
	 */
	private void initQueryMttaskPageDto(MtHisTaskPageDto dto, TOrganInfo tOrganInfo) {
		// 设置一级物业Id
		dto.setGroupOrganId(Integer.valueOf(tOrganInfo.getOrganId()));
		// 转换开始时间
		String beginTimeStr = DateUtil.convertBeginDate(String.valueOf(dto.getBeginTime()));
		if (StringUtils.isNotEmpty(beginTimeStr) && beginTimeStr.length() >= 12) {
			// 开始时间精确到分
			dto.setBeginTime(Long.valueOf(beginTimeStr.substring(0, 12)));
		}
		// 转换结束时间
		String endTimeStr = DateUtil.convertEndDate(String.valueOf(dto.getEndTime()));
		if (StringUtils.isNotEmpty(endTimeStr) && endTimeStr.length() >= 12) {
			// 结束时间精确到分
			dto.setEndTime(Long.valueOf(endTimeStr.substring(0, 12)));
		}
		// 设置查询标识
		dto.setQueryFlag(MtConstant.TASK_HIS_QUERY_FLAG_ONE);
	}
	
	/**
	 * <p>Title: queryMtHisFixedTaskExeInfoPage</p>   
	 * <p>Description: 固定任务执行信息分页查询</p> 
	 * <p>zhaoqing</p>
	 * @param mtTaskPageIceParam
	 * @param __current
	 * @return   
	 */
	@Override
	public FixedTaskHisExePageRsp queryMtHisFixedTaskExeInfoPage(
			MtTaskPageIceParam mtTaskPageIceParam, Current __current) {
		FixedTaskHisExePageRsp rsp = new FixedTaskHisExePageRsp(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new RpcPageIce(), new ArrayList<FixedTaskHisExeInfoIce>());
		try {
			MtHisTaskPageDto dto = BeanCopierUtils.copyProperties(
					mtTaskPageIceParam, MtHisTaskPageDto.class, true);
			// 分页查询数据
			ResultInfo result = mtHisTaskQueryServiceUtil.queryMtHisFixedTaskExeInfoPage(dto);
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
			LOGGER.error("queryFixedTaskExeInfoPage", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("固定任务执行信息分页查询失败");
		}
		return rsp;
	}
	
	/**
	 * <p>Title: queryMtHisTaskDetail</p>   
	 * <p>Description: 运送任务详情(自主任务/调度任务)</p> 
	 * <p>zhaoqing</p>
	 * @param mtTaskDetailIceParam
	 * @param __current
	 * @return   
	 */
	@Override
	public MtTaskDetailRetIceRsp queryMtHisTaskDetail(
			MtTaskDetailIceParam mtTaskDetailIceParam, Current __current) {
		MtTaskDetailRetIceRsp rsp = new MtTaskDetailRetIceRsp();
		rsp.code = RpcError.SUCCESS.getCode();
		rsp.message = RpcError.SUCCESS.getMessage();
		try {
			MtHisTaskPageDto dto = BeanCopierUtils.copyProperties(
					mtTaskDetailIceParam, MtHisTaskPageDto.class, true);
			ResultInfo result = mtHisTaskQueryServiceUtil.queryMtHisTaskDetail(dto);
			if (!result.getIsSucc()) {
				rsp.setMessage(result.getMessage());
				rsp.setCode(RpcError.FAIL.getCode());
				return rsp;
			}
			MtTaskDetailRetIce detailIce = (MtTaskDetailRetIce) result.getObject1();
	        rsp.setData(detailIce);
		} catch (Exception e) {
			LOGGER.error("queryMtHisTaskDetail", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("查询任务详情失败");
		 }
		return rsp;
	}

	/**
	 * <p>Title: queryMtHisTaskFixedDetail</p>   
	 * <p>Description: 运送任务详情(固定任务)</p> 
	 * <p>zhaoqing</p>
	 * @param mtTaskDetailIceParam
	 * @param __current
	 * @return   
	 */
	@Override
	public MtTaskDetailRetIceRsp queryMtHisTaskFixedDetail(
			MtTaskDetailIceParam mtTaskDetailIceParam, Current __current) {
		MtTaskDetailRetIceRsp rsp = new MtTaskDetailRetIceRsp();
		rsp.code = RpcError.SUCCESS.getCode();
		rsp.message = RpcError.SUCCESS.getMessage();
		try {
			MtHisTaskPageDto dto = BeanCopierUtils.copyProperties(
					mtTaskDetailIceParam, MtHisTaskPageDto.class, true);
			ResultInfo result = mtHisTaskQueryServiceUtil.queryMtHisTaskFixedDetail(dto);
			if (!result.getIsSucc()) {
				rsp.setMessage(result.getMessage());
				rsp.setCode(RpcError.FAIL.getCode());
				return rsp;
			}
			MtTaskDetailRetIce detailIce = (MtTaskDetailRetIce) result.getObject1();
	        rsp.setData(detailIce);
		} catch (Exception e) {
			LOGGER.error("queryMtHisTaskFixedDetail", e);
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
	 * <p>Title: queryMtHisFixedTaskExeDetail</p>   
	 * <p>Description: 固定任务某个点执行信息详情</p> 
	 * <p>zhaoqing</p>
	 * @param routeId
	 * @param organId
	 * @param __current
	 * @return   
	 */
	@Override
	public FixedTaskExeDetailReturnIce queryMtHisFixedTaskExeDetail(
			MtTaskDetailIceParam mtTaskDetailIceParam, Current __current) {
		FixedTaskExeDetailReturnIce rsp = new FixedTaskExeDetailReturnIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), 
				new FixedTaskExeDetailIce());
		try {
			MtHisTaskPageDto dto = BeanCopierUtils.copyProperties(
					mtTaskDetailIceParam, MtHisTaskPageDto.class, true);
			// 固定任务某个点执行信息详情查询
			ResultInfo result = mtHisTaskQueryServiceUtil
					.queryMtHisFixedTaskExeDetail(dto);
			if (!result.getIsSucc()) {
				rsp.setMessage(result.getMessage());
				rsp.setCode(RpcError.FAIL.getCode());
				return rsp;
			}
			FixedTaskExeDetailIce detailIce = (FixedTaskExeDetailIce) result.getObject1();
			rsp.setData(detailIce);
		} catch (Exception e) {
			LOGGER.error("queryMtHisFixedTaskExeDetail", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("执行详情查询失败");
		}
		return rsp;
	}
	
	/**
	 * @Title: getDeptTaskPage 
	 * @Description: 科室运送任务列表分页 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月9日下午8:05:24
	 */
	@Override
	public MtTaskPaginatorIceRsp getDeptTaskPage(
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
			// 查询调度和自主类型的
			dto.setTaskTypes(TransTaskTypeEnum.TASK_TYPE_DISPATCH.getCode() 
					+ Constant.SPLIT_COMMA + TransTaskTypeEnum.TASK_TYPE_SELF.getCode());
			// 设置一级物业Id
			dto.setGroupOrganId(Integer.valueOf(tOrganInfo.getOrganId()));
			// 设置查询标识
			dto.setQueryFlag(MtConstant.TASK_HIS_QUERY_FLAG_THREE);
			// 设置科室id
			dto.setSourceHouseId(Integer.valueOf(deptTaskPageIceParam.getHouseId()));
			// 分页查询数据
			ResultInfo result = mtHisTaskQueryServiceUtil.queryMtHisTaskPage(dto);
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

	/**
	 * <p>Title: queryMtHisTaskPageByUser</p>   
	 * <p>Description: 运送员运送明细分页查询</p> 
	 * <p>zhaoqing</p>
	 * @param mtTaskPageIceParam
	 * @param __current
	 * @return   
	 */
	@Override
	public MtTaskPaginatorIceRsp queryMtHisTaskPageByUser(
			MtTaskPageIceParam mtTaskPageIceParam, Current __current) {
		MtTaskPaginatorIceRsp rsp = new MtTaskPaginatorIceRsp(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new RpcPageIce(mtTaskPageIceParam.getPageLength(), mtTaskPageIceParam.getPageNo(), "0"), 
				new ArrayList<MtTaskPageIce>());
		try {
			MtHisTaskPageDto dto = BeanCopierUtils.copyProperties(
					mtTaskPageIceParam, MtHisTaskPageDto.class, true);
			TOrganInfo tOrganInfo = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
			if (null == tOrganInfo) {
				return rsp;
			}
			// 设置分页查询条件
			initQueryDto(tOrganInfo, dto, mtTaskPageIceParam.getCycleYm());
			// 分页查询数据
			ResultInfo result = mtHisTaskQueryServiceUtil.queryMtHisTaskPage(dto);
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
			LOGGER.error("queryMtHisTaskPageByUser", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("运送员运送明细分页查询失败");
		}
		return rsp;
	}
	
	/**
	 * @Title: initQueryDto   
	 *  设置查询条件
	 * @author zhaoqing  
	 * @param tOrganInfo
	 * @param dto
	 */
	private void initQueryDto(TOrganInfo tOrganInfo, MtHisTaskPageDto dto, String cycleYm) {
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
	 * <p>Title: queryUserHisTaskDaylyPage</p>   
	 * <p>Description: 运送员日排名运送明细分页查询</p> 
	 * <p>zhaoqing</p>
	 * @param mtTaskPageIceParam
	 * @param __current
	 * @return   
	 */
	@Override
	public MtTaskPaginatorIceRsp queryUserHisTaskDaylyPage(
			MtTaskPageIceParam mtTaskPageIceParam, Current __current) {
		MtTaskPaginatorIceRsp rsp = new MtTaskPaginatorIceRsp(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new RpcPageIce(mtTaskPageIceParam.getPageLength(), mtTaskPageIceParam.getPageNo(), "0"), 
				new ArrayList<MtTaskPageIce>());
		try {
			MtHisTaskPageDto dto = BeanCopierUtils.copyProperties(
					mtTaskPageIceParam, MtHisTaskPageDto.class, true);
			TOrganInfo tOrganInfo = CommonServiceUtils.getTopOrganByOrganID(dto.getOrganId());
			if (null == tOrganInfo) {
				return rsp;
			}
			// 转换实际开始时间
			dto.setExeBeginTime(DataTypeConverUtils.parseLongToDate(dto.getBeginTime()));
			// 转换实际结束时间
			dto.setExeEndTime(DataTypeConverUtils.parseLongToDate(dto.getEndTime()));
			// 设置任务状态为已完成
			dto.setTaskStatus(TransStatusEnum.TRANS_COMPLETED.getCode());
			// 设置查询标识
			dto.setQueryFlag(MtConstant.TASK_HIS_QUERY_FLAG_TWO);
			// 分页查询数据
			ResultInfo result = mtHisTaskQueryServiceUtil.queryMtHisTaskPage(dto);
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
			LOGGER.error("queryMtHisTaskPageByUser", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("运送员运送明细分页查询失败");
		}
		return rsp;
	}
	
}