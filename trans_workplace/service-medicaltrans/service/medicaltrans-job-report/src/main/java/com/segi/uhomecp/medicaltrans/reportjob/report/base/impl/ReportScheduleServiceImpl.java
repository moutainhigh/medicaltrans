package com.segi.uhomecp.medicaltrans.reportjob.report.base.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.common.service.impl.GenericServiceImpl;
import com.segi.uhomecp.medicaltrans.report.schedule.dao.ScheduleLogMapper;
import com.segi.uhomecp.medicaltrans.report.schedule.dao.TransScheduleMapper;
import com.segi.uhomecp.medicaltrans.report.schedule.model.ScheduleLog;
import com.segi.uhomecp.medicaltrans.report.schedule.model.TransSchedule;
import com.segi.uhomecp.medicaltrans.report.schedule.model.TransScheduleCriteria;
import com.segi.uhomecp.medicaltrans.report.schedule.model.TransScheduleCriteria.Criteria;
import com.segi.uhomecp.medicaltrans.reportjob.enums.ScheduleStatusEnums;
import com.segi.uhomecp.medicaltrans.reportjob.report.base.ReportScheduleService;
import com.segi.uhomecp.medicaltrans.reportjob.report.base.dao.ScheduleLogInfoMapper;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.AppUtils;

/**
 * 
 *     
 * 包: com.segi.uhomecp.medicaltrans.reportjob.base.service.impl 
 * 类名称: ReportScheduleServiceImpl.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 下午6:07:21
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
@Service
public class ReportScheduleServiceImpl extends GenericServiceImpl<TransSchedule, TransScheduleCriteria, Integer> implements ReportScheduleService {
	
	
	/**  描述   (@author: wangxiong@segimail.com) */      
	private static final long serialVersionUID = 2067882483754734961L;

	@Autowired
	private TransScheduleMapper scheduleMapper;
	
	@Autowired
	private ScheduleLogInfoMapper scheduleLogInfoMapper;
	
	@Autowired
	private ScheduleLogMapper scheduleLogMapper;
	
	@Override
	public List<TransSchedule> getScheduleList() {
		TransScheduleCriteria example = new TransScheduleCriteria();
		Criteria criteria = example.createCriteria();
		List<String> statusList = new ArrayList<String>();
		statusList.add(String.valueOf(ScheduleStatusEnums.COMPLETE.getValue()));
		criteria.andStatusIn(statusList);
		return this.selectByExample(example);
	}

	@Override
	public void updateScheduleByOrganId(Integer organId, ScheduleStatusEnums enums, Date updateDate) {
		TransSchedule record = new TransSchedule();
		record.setOrganId(organId);
		record.setRunningStatus(String.valueOf(enums.getValue()));
		if (updateDate != null) {
			record.setParamDate(updateDate);
		}
		record.setUpdateDate(new Date());
		
		this.updateByPrimaryKeySelective(record);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.setAbstractMapperDao(scheduleMapper);
	}

	@Override
	public List<TransSchedule> getAllTransSchedule(List<Integer> organIds) {
		TransScheduleCriteria example = new TransScheduleCriteria();
		TransScheduleCriteria.Criteria criteria = example.createCriteria();
		if (AppUtils.isNotEmpty(organIds)) {
			criteria.andOrganIdIn(organIds);
		}
		criteria.andStatusEqualTo(Constant.STATUS_CD_NORMAL);
		example.setOrderByClause(" GROUP_ORGAN_ID ");
		List<TransSchedule> list = this.selectByExample(example);
		return list;
	}

	@Override
	public void completed(Integer organId, ScheduleStatusEnums status, Date updateDate, ScheduleLog log) {
		this.updateScheduleByOrganId(organId, status, updateDate);
		scheduleLogMapper.insertSelective(log);
	}

	@Override
	public void updateScheduleByOrganIds(List<Integer> organIds, ScheduleStatusEnums enums,
			Date excDate, Date lastExcDate, Date excEndDate) {
		TransScheduleCriteria example = new TransScheduleCriteria();
		TransScheduleCriteria.Criteria criteria = example.createCriteria();
		criteria.andOrganIdIn(organIds);
		TransSchedule record = new TransSchedule();
		record.setRunningStatus(String.valueOf(enums.getValue()));
		if (excDate != null) {
			record.setExcDate(excDate);
		}
		if (excEndDate != null) {
			record.setExcEndDate(excEndDate);
		}
		if (lastExcDate != null) {
			record.setLastExcDate(lastExcDate);
		}
		record.setUpdateDate(new Date());
		this.updateByExampleSelective(record, example);
	}
	
	@Override
	public void updateScheduleExeDateByOrganIds(List<Integer> organIds, Date excDate) {
		TransScheduleCriteria example = new TransScheduleCriteria();
		TransScheduleCriteria.Criteria criteria = example.createCriteria();
		criteria.andOrganIdIn(organIds);
		TransSchedule record = new TransSchedule();
		record.setExcDate(excDate);
		record.setUpdateDate(new Date());
		this.updateByExampleSelective(record, example);
	}

	@Override
	public void insertBatchLog(List<ScheduleLog> logList) {
		scheduleLogInfoMapper.insertBatchLog(logList);
	}
}
