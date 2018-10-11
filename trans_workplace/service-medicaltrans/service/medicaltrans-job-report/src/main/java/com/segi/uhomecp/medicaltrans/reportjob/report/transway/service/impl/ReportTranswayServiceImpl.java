package com.segi.uhomecp.medicaltrans.reportjob.report.transway.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.medicaltrans.constant.MtSeqContants;
import com.segi.uhomecp.medicaltrans.report.monthamount.transway.model.TranswayStatistics;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.service.TransService;
import com.segi.uhomecp.medicaltrans.reportjob.report.transway.dao.ReportTranswayMapper;
import com.segi.uhomecp.medicaltrans.reportjob.report.transway.service.ReportTranswayService;
import com.segi.uhomecp.utils.SeqContants;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.DataTypeConverUtils;

@Service
public class ReportTranswayServiceImpl implements ReportTranswayService {
	
	@Autowired
	private TransService transService;
	
	@Autowired
	private ReportTranswayMapper reportTranswayMapper;
	
	/**
	 * @discription 每月或每周的更新job
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:54:43      
	 * @param list
	 * @param organId
	 * @param cycleYm     
	 */
	@Override
	public void updateTranswayMonth(List<TranswayStatistics> list, Integer organId, Integer cycleYm) {
		// 根据organId、cycleYm、TranswayParentCode查询出运送类型表全部信息
		// TranswayTask{groupOrganId,organId,cycleYm,TranswayParentCode,transAmount,transMinite}
		// 当前时间
		Long updateDate = DataTypeConverUtils.formatDateToLongYYYYMMDDHHMMSS(new Date());
		List<TranswayStatistics> addList = new ArrayList<TranswayStatistics>();
		for (TranswayStatistics transwayTask : list) {
			// 新增
			int id = SeqContants.getSequnces(MtSeqContants.MT_TRANSWAY_STATISTICS_ID_SEQ).intValue();
			transwayTask.setId(id);
			if (null == transwayTask.getTransAmount()) {
				transwayTask.setTransAmount(0L);
			}
			if (null == transwayTask.getTransMinite()) {
				transwayTask.setTransMinite(0L);
			}
			transwayTask.setCreateDate(updateDate);
			transwayTask.setUpdateDate(updateDate);
			addList.add(transwayTask);
		}
		if (AppUtils.isNotEmpty(addList)) {
			reportTranswayMapper.saveBatchTransway(addList);
		}
	}

	/**
	 * @discription 每日的更新job
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:54:43     
	 * @param resultList
	 */
	@Override
	public void updateTranswayDay(List<TranswayStatistics> resultList) {
		if (!AppUtils.isNotEmpty(resultList)) {
			return;
		}
		// 当前时间
		Long updateDate = DataTypeConverUtils.formatDateToLongYYYYMMDDHHMMSS(new Date());
		List<TranswayStatistics> addList = new ArrayList<TranswayStatistics>();
		// 修改一条运送类型，未修改就新增（说明没有该条，需要新增）
		// TranswayTask{groupOrganId,organId,cycleYm,TranswayParentCode,transAmount,transMinite}
		for (TranswayStatistics transwayTask : resultList) {
			// 加上更新时间
			transwayTask.setUpdateDate(updateDate);
			Integer updateCount = reportTranswayMapper.updateByTransway(transwayTask);
			if (updateCount == 0) {
				// 新增，加上主键和创建时间
				transwayTask.setId(SeqContants.getSequnces(MtSeqContants.MT_TRANSWAY_STATISTICS_ID_SEQ).intValue());
				transwayTask.setCreateDate(updateDate);
				transwayTask.setUpdateDate(updateDate);
				addList.add(transwayTask);
			}
		}
		if (AppUtils.isNotEmpty(addList)) {
			reportTranswayMapper.saveBatchTransway(addList);
		}
	}

	/**
	 * @discription 根据部门idList和月份删除
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:54:43     
	 * @param organIdList
	 * @param cycleYm
	 */
	@Override
	public void deleteByOrganListAndCycleYm(List<Integer> organIdList, Integer cycleYm) {
		reportTranswayMapper.deleteByOrganListAndCycleYm(AppUtils.listToString(organIdList, ','), cycleYm);
	}
}
