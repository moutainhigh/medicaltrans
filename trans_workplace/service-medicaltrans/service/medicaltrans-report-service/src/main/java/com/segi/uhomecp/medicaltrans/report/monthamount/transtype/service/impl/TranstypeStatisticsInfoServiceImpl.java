package com.segi.uhomecp.medicaltrans.report.monthamount.transtype.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.report.common.dto.CommonDto;
import com.segi.uhomecp.medicaltrans.report.monthamount.transtype.dao.ReportCommonMapper;
import com.segi.uhomecp.medicaltrans.report.monthamount.transtype.dto.TransTypeDto;
import com.segi.uhomecp.medicaltrans.report.monthamount.transtype.model.TranstypeStatistics;
import com.segi.uhomecp.medicaltrans.report.monthamount.transtype.service.TranstypeStatisticsInfoService;

@Service
public class TranstypeStatisticsInfoServiceImpl implements TranstypeStatisticsInfoService {

	@Autowired
	private ReportCommonMapper transtypeStatisticsInfoMapper;

	@Override
	public TransTypeDto queryTaskTypeRatio(CommonDto commonDto) {
		TransTypeDto dto = new TransTypeDto();
		// 年份后面加上月份
		Integer cycleY = commonDto.getCycleY();
		commonDto.setCycleYmBeg(cycleY.intValue() * MtConstant.MT_CONSTANT_HUNDRED + MtConstant.MT_MONTH_JANUARY);
		commonDto.setCycleYmEnd(cycleY.intValue() * MtConstant.MT_CONSTANT_HUNDRED + MtConstant.MT_MONTH_DECEMBER);
		List<TranstypeStatistics> list = transtypeStatisticsInfoMapper.selectTransTypeByOrganIdAndCycleYm(commonDto);
		for (TranstypeStatistics transtype : list) {
			// 根据运送类型List生成输出对象
			dto.setValue(transtype.getTransTypeParentCode(), transtype.getTransAmount());
		}
		return dto;
	}
}
