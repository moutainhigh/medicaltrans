package com.segi.uhomecp.medicaltrans.reportjob.report.deptvolumemonth.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tddl.sequences.SequenceException;

import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocation;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.constant.MtSeqContants;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.model.DeptVolumeMonth;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.model.DeptVolumeMonthCriteria;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.service.DeptVolumeMonthService;
import com.segi.uhomecp.medicaltrans.reportjob.query.deptvolumemonth.dto.DeptSqlDto;
import com.segi.uhomecp.medicaltrans.reportjob.query.deptvolumemonth.service.CountDeptMonthService;
import com.segi.uhomecp.medicaltrans.reportjob.report.deptvolumemonth.dao.ReportDeptMonthMapper;
import com.segi.uhomecp.medicaltrans.reportjob.report.deptvolumemonth.service.ReportDeptVolumeMonthService;
import com.segi.uhomecp.medicaltrans.reportjob.report.pervolmonth.service.impl.PerVolMonthRptServiceImpl;
import com.segi.uhomecp.sequence.SequenceRpcClient;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.utils.SeqContants;
import com.segi.uhomecp.wh.common.dto.ResultDto;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * 
 * ReportDeptVolumeMonthServiceImpl.java
 * @Description:科室信息
 * @author liuyi@segimail.com 
 * @created 2018年8月11日下午2:57:56
 */
@Service
public class ReportDeptVolumeMonthServiceImpl implements ReportDeptVolumeMonthService {

	@Autowired
	private CountDeptMonthService countDeptMonthService;
	
	@Autowired
	private DeptVolumeMonthService deptVolumeMonthService;
	
	@Autowired
	private ReportDeptMonthMapper reportDeptMonthMapper;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PerVolMonthRptServiceImpl.class);
	
	/**
	 * @Title: delDeptVolumeMonthInfo 
	 * @Description: 删除科室运送量 
	 * @author liuyi@segimail.com 
	 * @date 2018年7月30日下午3:42:28
	 */
	@Override
	public void deleteDeptVolumeMonthInfo(List<Integer> organIdList, String cycleYm) {
		DeptVolumeMonthCriteria example = new DeptVolumeMonthCriteria();
		DeptVolumeMonthCriteria.Criteria criteria = example.createCriteria();
		criteria.andOrganIdIn(organIdList);
		criteria.andCycleYmEqualTo(Integer.valueOf(cycleYm));
		deptVolumeMonthService.deleteByExample(example);
	}

	/**
	 * @Title: saveDeptVolumeMonthInfo 
	 * @Description: 保存科室月运送量   
	 * @author liuyi@segimail.com 
	 * @date 2018年7月30日下午3:34:42
	 */
	@Override
	public void saveDeptVolumeMonthInfo(List<DeptVolumeMonth> deptList) {
		if (AppUtils.isNotEmpty(deptList)) {
			List<Long> deptSeqList = null;
			// 获取主键
			try {
				deptSeqList = SequenceRpcClient.getCurrentSequenceListValueByIce(
						MtSeqContants.MTR_DEPT_VOLUME_MONTH_ID_SEQ, deptList.size());
				
				XxlJobLogger.log("准备获取主键" + deptList.size() + "个");
				XxlJobLogger.log("主键" + FastjsonUtils.toJsonString(deptList.size()));
				String logInfo = "=================>MtrDeptVolumeMonthServiceImpl saveDeptVolumeMonthInfo get sequences [" 
						+ (deptSeqList == null ? 0 : deptSeqList.size()) + "] rows";
				XxlJobLogger.log(logInfo);
				LOGGER.debug(logInfo);
			} catch (SequenceException e) {
				LOGGER.error(MtSeqContants.MTR_DEPT_VOLUME_MONTH_ID_SEQ, e);
			}
			DeptVolumeMonth deptMonth = new DeptVolumeMonth();
			for (int i = 0; i < deptList.size(); i++) {
				deptMonth = deptList.get(i);
				if (AppUtils.isNotEmpty(deptSeqList)) {
					// 设置主键Id
					deptMonth.setId(deptSeqList.get(i).intValue());
				} else {
					// 设置主键Id
					int intValue = SeqContants.getSequnces(MtSeqContants.MTR_DEPT_VOLUME_MONTH_ID_SEQ).intValue();
					deptMonth.setId(intValue);
				}		
			}
			// mybaits 方法保存每次保存2000条
			List<DeptVolumeMonth> newList = null;
			Paginator page = null;
			if (MtConstant.MAX_INSERT_LENGTH < deptList.size()) {
				page = new Paginator(1, MtConstant.MAX_INSERT_LENGTH, deptList.size());
				for (int i = 0; i < page.getTotalPages(); i++) {
					page = new Paginator(i + 1, MtConstant.MAX_INSERT_LENGTH, deptList.size());
					newList = deptList.subList(page.getStartRow() - 1, page.getEndRow());
					reportDeptMonthMapper.saveDeptMonthIbatch(newList);
				}
			} else {
				reportDeptMonthMapper.saveDeptMonthIbatch(deptList);
			}
		}
	}

	/**
	 * @Title: queryDeptVolumeMonthList 
	 * @Description: 查询科室统计信息 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月6日下午4:49:26
	 */
	@Override
	public List<DeptVolumeMonth> queryDeptVolumeMonthList(List<Integer> houseIdList,
			String cycleYm) {
		DeptVolumeMonthCriteria example = new DeptVolumeMonthCriteria();
		DeptVolumeMonthCriteria.Criteria criteria = example.createCriteria();
		criteria.andHouseIdIn(houseIdList);
		criteria.andCycleYmEqualTo(Integer.valueOf(cycleYm));
		return deptVolumeMonthService.selectByExample(example);
	}

	/**
	 * @Title: deleteDeptInfoByHouseIdList 
	 * @Description: 根据科室id删除科室统计信息 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月7日下午12:02:30
	 */
	@Override
	public void updateDeptCount(DeptVolumeMonth deptMonth) {
		int updateCount = MtConstant.SQL_UPDATE_FAILURE;
		if (deptMonth.getHouseId() != null && deptMonth.getCycleYm() != null) {
			updateCount = reportDeptMonthMapper.updateDeptCount(deptMonth);
		}
		if (MtConstant.SQL_UPDATE_FAILURE == updateCount) {
			XxlJobLogger.log("updateDeptMonth 新增科室运送量报表库、" + deptMonth.getHouseId() + "-------" + deptMonth.getCycleYm() + "-------" + deptMonth.getTransAmount());
			saveDeptVolumeMonthInfo(deptMonth);
		}
	}

	/**
	 * @Title: saveDeptVolumeMonthInfo 
	 * @Description: 保存科室运送量    
	 * @author liuyi@segimail.com 
	 * @date 2018年8月13日上午11:36:29
	 */
	@Override
	public void saveDeptVolumeMonthInfo(DeptVolumeMonth dept) {
		List<DeptVolumeMonth> deptList = new ArrayList<DeptVolumeMonth>();
		deptList.add(dept);
		this.saveDeptVolumeMonthInfo(deptList);
	}
	
	/**
	 * @Title: countDeptInitInfo 
	 * @Description: 计算科室报表信息剔除掉报表里纯在的对象 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月16日上午10:36:28
	 */
	public List<MtBuildLocation> countDeptInitInfo(List<MtBuildLocation> buildList, String exeYearMonth) {
		List<MtBuildLocation> locationList = new ArrayList<MtBuildLocation>();
		//查询报表库里存在的科室对象id
		List<Integer> houseIdList = queryHouseId(AppUtils.list2ParamsList(
				buildList, (obj) -> obj.getLocationId()), exeYearMonth);
		Map<Integer, MtBuildLocation> houseIdMap = AppUtils.list2Map(buildList, (obj) -> obj.getLocationId());
		if (AppUtils.isNotEmpty(houseIdList)) {
			for (Integer houseId : houseIdList) {
				houseIdMap.remove(houseId);
			}
		}
		// 获得删除以后的科室
		for (Map.Entry<Integer, MtBuildLocation> entry : houseIdMap.entrySet()) {
			locationList.add(entry.getValue());
		}
		return locationList;
	}
	
	/**
	 * @Title: queryHouseId 
	 * @Description: 判断科室id是否存在  每次查询5000条
	 * @author liuyi@segimail.com 
	 * @date 2018年8月16日下午8:10:16
	 */
	private List<Integer> queryHouseId(List<Integer> houseIdList, String exeYearMonth) {
		List<Integer> houseList = new ArrayList<Integer>();
		List<Integer> newList = null;
		Paginator page = null;
		DeptSqlDto deptSqlDto = new DeptSqlDto();
		deptSqlDto.setCycleYm(Integer.valueOf(exeYearMonth));
		if (MtConstant.MAX_QUERY_LENGTH < houseIdList.size()) {
			page = new Paginator(1, MtConstant.MAX_QUERY_LENGTH, houseIdList.size());
			for (int i = 0; i < page.getTotalPages(); i++) {
				page = new Paginator(i + 1, MtConstant.MAX_QUERY_LENGTH, houseIdList.size());
				newList = houseIdList.subList(page.getStartRow() - 1, page.getEndRow());
				deptSqlDto.setHouseIdList(newList);
				houseList.addAll(reportDeptMonthMapper.queryHouseId(deptSqlDto));
			}
		} else {
			deptSqlDto.setHouseIdList(houseIdList);
			deptSqlDto.setCycleYm(Integer.valueOf(exeYearMonth));
			houseList = reportDeptMonthMapper.queryHouseId(deptSqlDto);
		}
		return houseList;
	}
	
	/**
	 * @Title: sqveDeptInfo 
	 * @Description: 保存科室统计信息  
	 * @author liuyi@segimail.com 
	 * @date 2018年8月30日下午4:40:38
	 */
	public void saveDeptInfo(Integer organId, String cycleYm, ResultDto<String, String, Integer> resultDto,
			Map<Integer, List<DeptVolumeMonth>> deptMap) {
		long beginTime = System.currentTimeMillis();
		List<Integer> organIdsList = new ArrayList<Integer>();
		organIdsList.add(organId);
		// 保存统计数据前先删除旧数据
		List<DeptVolumeMonth> dvmList = deptMap.get(organId);
		deleteDeptVolumeMonthInfo(organIdsList, cycleYm);
		saveDeptVolumeMonthInfo(dvmList);
		String logInfo = "==============>成功更新组织[" + organId + "]的科室运输统计数据信息["
				+ (AppUtils.isNotEmpty(dvmList) ? dvmList.size() : 0)
				+ "]条, 耗时[" + (System.currentTimeMillis() - beginTime) + "]毫秒";
		XxlJobLogger.log(logInfo);
	}
}
