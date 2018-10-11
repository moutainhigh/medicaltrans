package com.segi.uhomecp.medicaltrans.report.organ.rpc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import resp.RpcRespIce;
import segi.medicaltrans.common.report.organ.ReportOrganIce;
import segi.medicaltrans.common.report.organ._TransReportOrganServiceIceDisp;
import Ice.Current;

import com.segi.uhomecp.medicaltrans.constants.ReportConstant;
import com.segi.uhomecp.medicaltrans.report.schedule.model.TransSchedule;
import com.segi.uhomecp.medicaltrans.report.schedule.model.TransScheduleCriteria;
import com.segi.uhomecp.medicaltrans.report.schedule.service.TransScheduleService;
import com.segi.uhomecp.redis.cluster.SegiRedisCluster;
import com.segi.uhomecp.redis.cluster.SegiRedisClusterBuilder;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;

@Component
public class TransReportOganRpc extends _TransReportOrganServiceIceDisp {

	private static final long serialVersionUID = 3467593585318021477L;

	public static final Logger logger = LoggerFactory.getLogger(TransReportOganRpc.class);
	
	@Autowired
	private TransScheduleService transScheduleService;
	
	@Resource(name = "segiRedisCluster")
    private SegiRedisClusterBuilder segiRedisCluster;
	
	@Override
	public RpcRespIce add(int organId, Current __current) {
		RpcRespIce result = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			SegiRedisCluster client = segiRedisCluster.getSegiRedisCluster();
			
			Boolean flag = client.sismember(ReportConstant.TRANS_REPORT_LIST_KEY, String.valueOf(organId));
			if (!flag) {
				TransSchedule schedule = new TransSchedule();
				schedule.setOrganId(organId);
				schedule.setGroupOrganId(CommonServiceUtils.getTopOrganByOrganID(organId).getOrganId());
				schedule.setStartDate(new Date());
				schedule.setParamDate(new Date());
				schedule.setRunningStatus(ReportConstant.SCHEDULE_STATUS_1);
				schedule.setCreateDate(new Date());
				schedule.setUpdateDate(new Date());
				schedule.setStatus(ReportConstant.ORGAN_REPORT_STATUS_SUCESS);
				transScheduleService.insert(schedule);
				//刷新有效时间
				this.putCache(client);
			}
		} catch (Exception e) {
			if (!(e instanceof DuplicateKeyException)) {
				logger.error("TransReportOganRpc add fail", e);
				result.code = RpcError.FAIL.getCode();
				result.message = "系统异常";
			}
		}
		return result;
	}

	@Override
	public ReportOrganIce getOragnList(Current __current) {
		List<Integer> organIds = new ArrayList<Integer>();
		ReportOrganIce result = new ReportOrganIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), organIds);
		
		try {
			SegiRedisCluster client = segiRedisCluster.getSegiRedisCluster();
			
			Boolean flag = client.exists(ReportConstant.TRANS_REPORT_LIST_KEY);
			
			if (!flag) {
				organIds = this.putCache(client);
			} else {
				Set<String> organSet = client.smembers(ReportConstant.TRANS_REPORT_LIST_KEY);
				for (String organId : organSet) {  
					organIds.add(new Integer(organId));
				}
			}
			result.setOrganIds(organIds);
		} catch (Exception e) {
			logger.error("TransReportOganRpc getOragnList fail", e);
			result.code = RpcError.FAIL.getCode();
			result.message = "系统异常";
		}
		
		return result;
	}
	
	/**
	 * 刷新组织机构到缓存
	 * @param client
	 */
	private List<Integer> putCache(SegiRedisCluster client) {
		List<Integer> organIds = new ArrayList<Integer>();
		TransScheduleCriteria criteria = new TransScheduleCriteria();
		List<TransSchedule> list = transScheduleService.selectByExample(criteria);
		if (AppUtils.isNotEmpty(list)) {
			for (TransSchedule obj : list) {
				client.sadd(ReportConstant.TRANS_REPORT_LIST_KEY, String.valueOf(obj.getOrganId()));
				organIds.add(obj.getOrganId());
			}
			
			//刷新有效时间
			client.expire(ReportConstant.TRANS_REPORT_LIST_KEY, ReportConstant.TRANS_REPORT_LIST_KEY_EXPIRE);
		}
		
		return organIds;
	}
}
