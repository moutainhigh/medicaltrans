package com.segi.uhomecp.medicaltrans.report.trans.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import segi.datacachesvr.queryInfo.TOrganInfo;

import com.segi.uhomecp.medicaltrans.constant.MtConstant.TaskStructureRatioName;
import com.segi.uhomecp.medicaltrans.constant.MtConstant.TransModeRatioName;
import com.segi.uhomecp.medicaltrans.constant.MtConstant.TransportTimeName;
import com.segi.uhomecp.medicaltrans.dto.TransStatisticsCommonDto;
import com.segi.uhomecp.medicaltrans.report.common.dto.CommonDto;
import com.segi.uhomecp.medicaltrans.report.trans.dao.TransStatisticsMapper;
import com.segi.uhomecp.medicaltrans.report.trans.dto.TransStatisticsDto;
import com.segi.uhomecp.medicaltrans.report.trans.service.TransStatisticsInfoService;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;

@Service
public class TransStatisticsInfoServiceImpl implements TransStatisticsInfoService {

	@Autowired
	private TransStatisticsMapper transStatisticsMapper;
	
	/**
	 * @discription 运送时间统计
	 * @author yangyh@segimail.com       
	 * @created 2018年9月18日 下午3:21:01     
	 * @param organId
	 * @return
	 */
	@Override
	public List<TransStatisticsDto> queryTransportTime(Integer organId) {
		List<TransStatisticsDto> list = new ArrayList<TransStatisticsDto>();
		CommonDto commonDto = new CommonDto();
		commonDto.setOrganId(organId);
		TOrganInfo tOrganInfo = CommonServiceUtils.getTopOrganByOrganID(organId);
		if (null == tOrganInfo) {
			return list;
		}
		commonDto.setGroupOrganId(tOrganInfo.getOrganId());
		// 查询宽表
		TransStatisticsCommonDto dto = transStatisticsMapper.queryTransportTime(commonDto);
		if (null == dto) {
			return list;
		}
		TransStatisticsDto timeDto1 = new TransStatisticsDto();
		timeDto1.setTimeName(TransportTimeName.INSTANT_TASK_AVGTIME_NAME);
		timeDto1.setTime(dto.getInstantTaskAvgTime());
		TransStatisticsDto timeDto2 = new TransStatisticsDto();
		timeDto2.setTimeName(TransportTimeName.SAMPLE_AVGTIME_NAME);
		timeDto2.setTime(dto.getSampleTransTypeAvgTime());
		TransStatisticsDto timeDto3 = new TransStatisticsDto();
		timeDto3.setTimeName(TransportTimeName.PATIENT_AVGTIME_NAME);
		timeDto3.setTime(dto.getPatientTransTypeAvgTime());
		TransStatisticsDto timeDto4 = new TransStatisticsDto();
		timeDto4.setTimeName(TransportTimeName.DRUG_AVGTIME_NAME);
		timeDto4.setTime(dto.getDrugTransTypeAvgTime());
		TransStatisticsDto timeDto5 = new TransStatisticsDto();
		timeDto5.setTimeName(TransportTimeName.BLOOD_AVGTIME_NAME);
		timeDto5.setTime(dto.getBloodTransTypeAvgTime());
		TransStatisticsDto timeDto6 = new TransStatisticsDto();
		timeDto6.setTimeName(TransportTimeName.GOOD_AVGTIME_NAME);
		timeDto6.setTime(dto.getGoodTransTypeAvgTime());
		TransStatisticsDto timeDto7 = new TransStatisticsDto();
		timeDto7.setTimeName(TransportTimeName.BOOK_AVGTIME_NAME);
		timeDto7.setTime(dto.getBookTransTypeAvgTime());
		list.add(timeDto1);
		list.add(timeDto2);
		list.add(timeDto3);
		list.add(timeDto4);
		list.add(timeDto5);
		list.add(timeDto6);
		list.add(timeDto7);
		return list;
	}

	/**
	 * @discription 任务结构占比
	 * @author yangyh@segimail.com       
	 * @created 2018年9月18日 下午3:21:01     
	 * @param organId
	 * @return
	 */
	@Override
	public List<TransStatisticsDto> queryTaskStructureRatio(Integer organId) {
		List<TransStatisticsDto> list = new ArrayList<TransStatisticsDto>();
		CommonDto commonDto = new CommonDto();
		commonDto.setOrganId(organId);
		TOrganInfo tOrganInfo = CommonServiceUtils.getTopOrganByOrganID(organId);
		if (null == tOrganInfo) {
			return list;
		}
		commonDto.setGroupOrganId(tOrganInfo.getOrganId());
		// 查询宽表
		TransStatisticsCommonDto dto = transStatisticsMapper.queryTaskStructureRatio(commonDto);
		if (null == dto) {
			return list;
		}
		TransStatisticsDto timeDto1 = new TransStatisticsDto();
		timeDto1.setAmountName(TaskStructureRatioName.DRUG_AMOUNT_NAME);
		timeDto1.setAmount(dto.getDrugTransTypeAmount());
		TransStatisticsDto timeDto2 = new TransStatisticsDto();
		timeDto2.setAmountName(TaskStructureRatioName.SAMPLE_AMOUNT_NAME);
		timeDto2.setAmount(dto.getSampleTransTypeAmount());
		TransStatisticsDto timeDto3 = new TransStatisticsDto();
		timeDto3.setAmountName(TaskStructureRatioName.PATIENT_AMOUNT_NAME);
		timeDto3.setAmount(dto.getPatientTransTypeAmount());
		TransStatisticsDto timeDto4 = new TransStatisticsDto();
		timeDto4.setAmountName(TaskStructureRatioName.BOOK_AMOUNT_NAME);
		timeDto4.setAmount(dto.getBookTransTypeAmount());
		TransStatisticsDto timeDto5 = new TransStatisticsDto();
		timeDto5.setAmountName(TaskStructureRatioName.BLOOD_AMOUNT_NAME);
		timeDto5.setAmount(dto.getBloodTransTypeAmount());
		TransStatisticsDto timeDto6 = new TransStatisticsDto();
		timeDto6.setAmountName(TaskStructureRatioName.GOOD_AMOUNT_NAME);
		timeDto6.setAmount(dto.getGoodTransTypeAmount());
		list.add(timeDto1);
		list.add(timeDto2);
		list.add(timeDto3);
		list.add(timeDto4);
		list.add(timeDto5);
		list.add(timeDto6);
		return list;
	}

	/**
	 * @discription 运送方式占比
	 * @author yangyh@segimail.com       
	 * @created 2018年9月18日 下午3:21:01     
	 * @param organId
	 * @return
	 */
	@Override
	public List<TransStatisticsDto> queryTransModeRatio(Integer organId) {
		List<TransStatisticsDto> list = new ArrayList<TransStatisticsDto>();
		CommonDto commonDto = new CommonDto();
		commonDto.setOrganId(organId);
		TOrganInfo tOrganInfo = CommonServiceUtils.getTopOrganByOrganID(organId);
		if (null == tOrganInfo) {
			return list;
		}
		commonDto.setGroupOrganId(tOrganInfo.getOrganId());
		// 查询宽表
		TransStatisticsCommonDto dto = transStatisticsMapper.queryTransModeRatio(commonDto);
		if (null == dto) {
			return list;
		}
		TransStatisticsDto timeDto1 = new TransStatisticsDto();
		timeDto1.setAmountName(TransModeRatioName.WALK_AMOUNT_NAME);
		timeDto1.setAmount(dto.getWalkTypeAmount());
		TransStatisticsDto timeDto2 = new TransStatisticsDto();
		timeDto2.setAmountName(TransModeRatioName.PUSHINGBED_AMOUNT_NAME);
		timeDto2.setAmount(dto.getPushingBedTypeAmount());
		TransStatisticsDto timeDto3 = new TransStatisticsDto();
		timeDto3.setAmountName(TransModeRatioName.FLATCAR_AMOUNT_NAME);
		timeDto3.setAmount(dto.getFlatCartypeAmount());
		TransStatisticsDto timeDto4 = new TransStatisticsDto();
		timeDto4.setAmountName(TransModeRatioName.WHEELCHAIR_AMOUNT_NAME);
		timeDto4.setAmount(dto.getWheelchairTypeAmount());
		list.add(timeDto1);
		list.add(timeDto2);
		list.add(timeDto3);
		list.add(timeDto4);
		return list;
	}
}
