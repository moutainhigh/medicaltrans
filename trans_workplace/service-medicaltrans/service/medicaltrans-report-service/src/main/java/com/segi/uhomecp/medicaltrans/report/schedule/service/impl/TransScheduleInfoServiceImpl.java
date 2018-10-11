package com.segi.uhomecp.medicaltrans.report.schedule.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.medicaltrans.report.schedule.model.TransSchedule;
import com.segi.uhomecp.medicaltrans.report.schedule.model.TransScheduleCriteria;
import com.segi.uhomecp.medicaltrans.report.schedule.model.TransScheduleCriteria.Criteria;
import com.segi.uhomecp.medicaltrans.report.schedule.service.TransScheduleInfoService;
import com.segi.uhomecp.medicaltrans.report.schedule.service.TransScheduleService;
import com.segi.uhomecp.wh.common.interfaces.InvocationHandler;
import com.segi.uhomecp.wh.common.utils.AppUtils;
  
/** 
 * Title: TransScheduleInfoServiceImpl.java    
 * @Description: 描述
 * @author yangyh@segimail.com       
 * @created 2018年5月15日 下午3:51:00    
 */
@Service
public class TransScheduleInfoServiceImpl implements TransScheduleInfoService {

	@Autowired
	private TransScheduleService  transScheduleService;
	
	/** @discription 获取前一天当月更新的项目
	 * @author yangyh@segimail.com       
	 * @created 2018年5月15日 下午3:51:00      
	 * @return        
	 */
	@Override
	public List<Integer> getCurrentMonthOrgan(Date monthFirstDay, Date monthLastDay) {
		TransScheduleCriteria example = new TransScheduleCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andUpdateDateBetween(monthFirstDay, monthLastDay);
		List<TransSchedule> scheduleList = transScheduleService.selectByExample(example);
		if (AppUtils.isNotEmpty(scheduleList)) {
			List<Integer> list = AppUtils.list2ParamsList(scheduleList, new InvocationHandler<Integer, TransSchedule>() {
				@Override
				public Integer invoke(TransSchedule obj) {
					return obj.getOrganId();
				}
			});
			list.removeAll(Collections.singleton(null));
			return list;
		}
		return new ArrayList<Integer>();
	}

	/**
	 * @Title: queryTransSchedule 
	 * @Description: 通过organId查询排程表  
	 * @author liuyi@segimail.com 
	 * @date 2018年10月10日上午11:00:07
	 */
	@Override
	public TransSchedule queryTransSchedule(Integer organId) {
		TransScheduleCriteria example = new TransScheduleCriteria();
		TransScheduleCriteria.Criteria criteria = example.createCriteria();
		criteria.andOrganIdEqualTo(organId);
		List<TransSchedule> transScheduleList = this.transScheduleService.selectByExample(example);
		if (AppUtils.isNotEmpty(transScheduleList)) {
			return transScheduleList.get(0);
		}
		return null;
	}
}
