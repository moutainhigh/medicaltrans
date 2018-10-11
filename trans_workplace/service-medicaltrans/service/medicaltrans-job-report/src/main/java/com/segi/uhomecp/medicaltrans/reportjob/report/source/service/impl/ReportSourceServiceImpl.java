package com.segi.uhomecp.medicaltrans.reportjob.report.source.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.medicaltrans.constant.MtSeqContants;
import com.segi.uhomecp.medicaltrans.report.monthamount.source.model.SourceStatistics;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.service.TransService;
import com.segi.uhomecp.medicaltrans.reportjob.report.source.dao.ReportSourceMapper;
import com.segi.uhomecp.medicaltrans.reportjob.report.source.service.ReportSourceService;
import com.segi.uhomecp.utils.SeqContants;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.DataTypeConverUtils;

@Service
public class ReportSourceServiceImpl implements ReportSourceService {
	
	@Autowired
	private TransService transService;
	
	@Autowired
	private ReportSourceMapper reportSourceMapper;
	
	/**
	 * @discription 每月或每周的更新job
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:54:43     
	 * @param SourceList
	 */
	@Override
	public void updateSourceMonth(List<SourceStatistics> list, Integer organId, Integer cycleYm) {
		// 根据organId、cycleYm、SourceParentCode查询出运送类型表全部信息
		// SourceTask{groupOrganId,organId,cycleYm,SourceParentCode,transAmount,transMinite}
		// 当前时间
		Long updateDate = DataTypeConverUtils.formatDateToLongYYYYMMDDHHMMSS(new Date());
		List<SourceStatistics> addList = new ArrayList<SourceStatistics>();
		for (SourceStatistics sourceTask : list) {
			// 新增
			int id = SeqContants.getSequnces(MtSeqContants.MT_SOURCE_STATISTICS_ID_SEQ).intValue();
			sourceTask.setId(id);
			if (null == sourceTask.getTransAmount()) {
				sourceTask.setTransAmount(0L);
			}
			sourceTask.setCreateDate(updateDate);
			sourceTask.setUpdateDate(updateDate);
			addList.add(sourceTask);
		}
		if (AppUtils.isNotEmpty(addList)) {
			reportSourceMapper.saveBatchSource(addList);
		}
	}

	/**
	 * @discription 每日的更新job
	 * @author yangyh@segimail.com       
	 * @created 2018年9月12日 下午4:54:43     
	 * @param resultList
	 */
	@Override
	public void updateSourceDay(List<SourceStatistics> resultList) {
		if (!AppUtils.isNotEmpty(resultList)) {
			return;
		}
		// 当前时间
		Long updateDate = DataTypeConverUtils.formatDateToLongYYYYMMDDHHMMSS(new Date());
		List<SourceStatistics> addList = new ArrayList<SourceStatistics>();
		// 修改一条运送类型，未修改就新增（说明没有该条，需要新增）
		// SourceTask{groupOrganId,organId,cycleYm,SourceParentCode,transAmount,transMinite}
		for (SourceStatistics sourceTask : resultList) {
			// 加上更新时间
			sourceTask.setUpdateDate(updateDate);
			Integer updateCount = reportSourceMapper.updateBySource(sourceTask);
			if (updateCount == 0) {
				// 新增，加上主键和创建时间
				sourceTask.setId(SeqContants.getSequnces(MtSeqContants.MT_SOURCE_STATISTICS_ID_SEQ).intValue());
				sourceTask.setCreateDate(updateDate);
				sourceTask.setUpdateDate(updateDate);
				addList.add(sourceTask);
			}
		}
		if (AppUtils.isNotEmpty(addList)) {
			reportSourceMapper.saveBatchSource(addList);
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
		reportSourceMapper.deleteByOrganListAndCycleYm(AppUtils.listToString(organIdList, ','), cycleYm);
	}
}
