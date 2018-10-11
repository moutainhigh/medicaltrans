package com.segi.uhomecp.medicaltrans.reportjob.report.transtype.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.medicaltrans.constant.MtSeqContants;
import com.segi.uhomecp.medicaltrans.report.monthamount.transtype.model.TranstypeStatistics;
import com.segi.uhomecp.medicaltrans.reportjob.query.trans.service.TransService;
import com.segi.uhomecp.medicaltrans.reportjob.report.transtype.dao.ReportTranstypeMapper;
import com.segi.uhomecp.medicaltrans.reportjob.report.transtype.service.ReportTranstypeService;
import com.segi.uhomecp.utils.SeqContants;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.DataTypeConverUtils;

@Service
public class ReportTranstypeServiceImpl implements ReportTranstypeService {
	
	@Autowired
	private TransService transService;
	
	@Autowired
	private ReportTranstypeMapper reportTranstypeMapper;
	
	/**
	 * @discription 每月或每周的更新job
	 * @author yangyh@segimail.com       
	 * @created 2018年7月27日 下午12:02:47     
	 * @param transtypeList
	 */
	@Override
	public void updateTranstypeMonth(List<TranstypeStatistics> list, Integer organId, Integer cycleYm) {
		// 根据organId、cycleYm、transTypeParentCode查询出运送类型表全部信息
		// transtypeTask{groupOrganId,organId,cycleYm,transTypeParentCode,transAmount,transMinite}
		// 当前时间
		Long updateDate = DataTypeConverUtils.formatDateToLongYYYYMMDDHHMMSS(new Date());
		List<TranstypeStatistics> addList = new ArrayList<TranstypeStatistics>();
		for (TranstypeStatistics transtypeTask : list) {
			// 新增
			int id = SeqContants.getSequnces(MtSeqContants.MT_TRANSTYPE_STATISTICS_ID_SEQ).intValue();
			transtypeTask.setId(id);
			if (null == transtypeTask.getTransAmount()) {
				transtypeTask.setTransAmount(0L);
			}
			if (null == transtypeTask.getTransMinite()) {
				transtypeTask.setTransMinite(0L);
			}
			transtypeTask.setCreateDate(updateDate);
			transtypeTask.setUpdateDate(updateDate);
			addList.add(transtypeTask);
		}
		if (AppUtils.isNotEmpty(addList)) {
			reportTranstypeMapper.saveBatchTranstype(addList);
		}
	}

	/**
	 * @discription 每日的更新job
	 * @author yangyh@segimail.com       
	 * @created 2018年7月27日 下午12:02:47     
	 * @param resultList
	 */
	@Override
	public void updateTranstypeDay(List<TranstypeStatistics> resultList) {
		if (!AppUtils.isNotEmpty(resultList)) {
			return;
		}
		// 当前时间
		Long updateDate = DataTypeConverUtils.formatDateToLongYYYYMMDDHHMMSS(new Date());
		List<TranstypeStatistics> addList = new ArrayList<TranstypeStatistics>();
		// 修改一条运送类型，未修改就新增（说明没有该条，需要新增）
		// transtypeTask{groupOrganId,organId,cycleYm,transTypeParentCode,transAmount,transMinite}
		for (TranstypeStatistics transtypeTask : resultList) {
			// 加上更新时间
			transtypeTask.setUpdateDate(updateDate);
			Integer updateCount = reportTranstypeMapper.updateByTranstype(transtypeTask);
			if (updateCount == 0) {
				// 新增，加上主键和创建时间
				transtypeTask.setId(SeqContants.getSequnces(MtSeqContants.MT_TRANSTYPE_STATISTICS_ID_SEQ).intValue());
				transtypeTask.setCreateDate(updateDate);
				transtypeTask.setUpdateDate(updateDate);
				addList.add(transtypeTask);
			}
		}
		if (AppUtils.isNotEmpty(addList)) {
			reportTranstypeMapper.saveBatchTranstype(addList);
		}
	}

	/**
	 * @discription 根据部门idList和月份删除
	 * @author yangyh@segimail.com       
	 * @created 2018年8月13日 下午12:18:07     
	 * @param organIdList
	 * @param cycleYm
	 */
	@Override
	public void deleteByOrganListAndCycleYm(List<Integer> organIdList, Integer cycleYm) {
		reportTranstypeMapper.deleteByOrganListAndCycleYm(AppUtils.listToString(organIdList, ','), cycleYm);
	}
}
