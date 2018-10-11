package com.segi.uhomecp.medicaltrans.report.schedule.rpc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import page.RpcPageIce;
import segi.datacachesvr.queryInfo.Organ;
import segi.medicaltrans.common.report.transschedule.TransScheduleByOrganIdIce;
import segi.medicaltrans.common.report.transschedule.TransScheduleGroupOrganMapIce;
import segi.medicaltrans.common.report.transschedule.TransScheduleIce;
import segi.medicaltrans.common.report.transschedule.TransScheduleIceParams;
import segi.medicaltrans.common.report.transschedule.TransSchedulePaginatorIce;
import segi.medicaltrans.common.report.transschedule._TransScheduleServiceIceDisp;
import Ice.Current;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.medicaltrans.constants.ReportConstant;
import com.segi.uhomecp.medicaltrans.report.schedule.dto.TransScheduleInfoDto;
import com.segi.uhomecp.medicaltrans.report.schedule.model.TransSchedule;
import com.segi.uhomecp.medicaltrans.report.schedule.model.TransScheduleCriteria;
import com.segi.uhomecp.medicaltrans.report.schedule.service.TransScheduleService;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.web.PageUtils;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;

@Component
public class TransScheduleRpc extends _TransScheduleServiceIceDisp {

	private static final long serialVersionUID = 3467593585318021477L;

	public static final Logger logger = LoggerFactory.getLogger(TransScheduleRpc.class);
	
	@Autowired
	private TransScheduleService transScheduleService;

	/**
	 * @discription 查询效正列表数据
	 * @author wangxiong@segimail.com       
	 * @created 2018年8月9日 下午3:28:28     
	 * @param list
	 * @param organMap
	 * @return
	 */
	@Override
	public TransSchedulePaginatorIce queryTransSchedulePaginator(TransScheduleIceParams params, Current __current) {
		TransSchedulePaginatorIce rsp = new TransSchedulePaginatorIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new RpcPageIce(params.getPageLength(), params.getPageNo(), "0"), 
				new ArrayList<TransScheduleIce>());
		try {
			TransScheduleInfoDto dto = BeanCopierUtils.copyProperties(params, TransScheduleInfoDto.class, true);
			
			TransScheduleCriteria example = new TransScheduleCriteria();
			TransScheduleCriteria.Criteria criteria = example.createCriteria();
			int pageNo = Constant.INIT_PAGE_NO;
			if (null != dto.getPageNo() 
					&& dto.getPageNo().intValue() != 0) {
				//先对页码初始化,然后进行判断并赋值
				pageNo = dto.getPageNo();
			}
			int pageLength = Constant.DEFAULT_PAGE_LENGTH;
			if (null != dto.getPageLength() 
					&& dto.getPageLength().intValue() != 0) { 
				//先对每页记录数初始化,然后进行判断并赋值
				pageLength = dto.getPageLength();
			}
			Integer organId = dto.getOrganId();
			if (organId != null && organId.intValue() != 0 ) {
				criteria.andOrganIdEqualTo(organId);
			}
			Integer groupOrganId = dto.getGroupOrganId();
			if (groupOrganId != null && groupOrganId.intValue() != 0 ) {
				criteria.andGroupOrganIdEqualTo(groupOrganId);
			}
			String runningStatus = dto.getRunningStatus();
			if (StringUtils.isNotBlank(runningStatus) ) {
				criteria.andRunningStatusEqualTo(runningStatus);
			}
			PageList<TransSchedule> list = this.transScheduleService.selectByExampleWithRowbounds(example,
					PageUtils.getPageBoundsByPageNo(pageNo, pageLength));
			if (AppUtils.isNotEmpty(list)) { 
				Set<Integer> allOrganIds = new HashSet<>();
				Set<Integer> organIds = AppUtils.list2ParamsSet(list, TransSchedule.class, "organId");
				Set<Integer> groupOrganIds = AppUtils.list2ParamsSet(list, TransSchedule.class, "groupOrganId");
				allOrganIds.addAll(organIds);
				allOrganIds.addAll(groupOrganIds);
				Map<Integer, Organ> organMap = AppUtils.list2Map(CommonServiceUtils.getOrganInfoList(allOrganIds), "organId", Organ.class);
				List<TransScheduleIce> iceList = initTransScheduleData(list, organMap);
				rsp.setTransScheduleIceList(iceList);
				rsp.setPaginator(com.segi.uhomecp.wh.common.utils.PageUtils.paginator2RpcPageIce(list.getPaginator()));
			}
		} catch (Exception e) {
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}

	/**
	 * @discription 初始化Ice对象
	 * @author wangxiong@segimail.com       
	 * @created 2018年8月9日 下午3:28:28     
	 * @param list
	 * @param organMap
	 * @return
	 */
	private List<TransScheduleIce> initTransScheduleData(PageList<TransSchedule> list,
			Map<Integer, Organ> organMap) {
		List<TransScheduleIce> iceList = new ArrayList<>();
		TransScheduleIce transScheduleIce = null;
		Organ organ = null;
		Organ groupOrgan = null;
		for (TransSchedule transSchedule : list) {
			transScheduleIce = BeanCopierUtils.copyProperties(transSchedule, TransScheduleIce.class, true);
			groupOrgan = organMap.get(transSchedule.getGroupOrganId());
			organ = organMap.get(transSchedule.getOrganId());
			transScheduleIce.setGroupOrganName(groupOrgan != null ? groupOrgan.getOrganName() : "");
			transScheduleIce.setOrganName(organ != null ? organ.getOrganName() : "");
			if (ReportConstant.SCHEDULE_STATUS_0.equals(transSchedule.getRunningStatus())) {
				transScheduleIce.setRunningStatusName(ReportConstant.SCHEDULE_STATUS_0_NAME);
			} else if (ReportConstant.SCHEDULE_STATUS_1.equals(transSchedule.getRunningStatus())) {
				transScheduleIce.setRunningStatusName(ReportConstant.SCHEDULE_STATUS_1_NAME);
			} else if (ReportConstant.SCHEDULE_STATUS_2.equals(transSchedule.getRunningStatus())) {
				transScheduleIce.setRunningStatusName(ReportConstant.SCHEDULE_STATUS_2_NAME);
			}
			iceList.add(transScheduleIce);
		}
		return iceList;
	}
	
	
	@Override
	public TransScheduleGroupOrganMapIce getTransScheduleMap(Current __current) {
		TransScheduleGroupOrganMapIce rsp = new TransScheduleGroupOrganMapIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new HashMap<Integer, List<Integer>>());
		try {
			TransScheduleCriteria example = new TransScheduleCriteria();
//			TransScheduleCriteria.Criteria criteria = example.createCriteria();
			List<TransSchedule> list = this.transScheduleService.selectByExample(example);
			if (AppUtils.isNotEmpty(list)) {
				rsp.setGroupOrganMap(getGroupOrganMapBytransList(list));
			}
		} catch (Exception e) {
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(e.getMessage());
		}
		return rsp;
	}
	/**
	 * 按照group进行分组
	 * @param transList
	 * @return
	 */
	private Map<Integer, List<Integer>> getGroupOrganMapBytransList(List<TransSchedule> transList) {
		Map<Integer, List<Integer>> rstMap = new HashMap<>();
		if (AppUtils.isNotEmpty(transList)) {
			Integer groupOrganId = null;
			List<Integer> organIds = null;
			for (TransSchedule transSchedule : transList) {
				groupOrganId = transSchedule.getGroupOrganId();
				organIds = rstMap.get(groupOrganId);
				if (organIds == null) {
					// 数据返回为 null 说明里面没有数据
					organIds = new ArrayList<>();
					organIds.add(transSchedule.getOrganId());
					rstMap.put(groupOrganId, organIds);
				} else {
					organIds.add(transSchedule.getOrganId());
				}
			}
		}
		return rstMap;
	}

	/**
	 * @discription 通过organId查询排程表
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月9日 下午5:19:39      
	 * @param organId
	 * @param __current
	 * @return     
	 */
	@Override
	public TransScheduleByOrganIdIce queryTransSchedule(int organId,
			Current __current) {
		TransScheduleByOrganIdIce rsp = new TransScheduleByOrganIdIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), new TransScheduleIce());
		try {
			TransScheduleCriteria example = new TransScheduleCriteria();
			TransScheduleCriteria.Criteria criteria = example.createCriteria();
			criteria.andOrganIdEqualTo(organId);
			List<TransSchedule> transSchedule = this.transScheduleService.selectByExample(example);
			if (AppUtils.isNotEmpty(transSchedule)) {
				rsp.setTransScheduleIce(BeanCopierUtils.copyProperties(transSchedule.get(0), TransScheduleIce.class, true));
			}
		} catch (Exception e) {
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
			logger.error("queryTransSchedule=====>", e);
		}
		return rsp;
	}

}
