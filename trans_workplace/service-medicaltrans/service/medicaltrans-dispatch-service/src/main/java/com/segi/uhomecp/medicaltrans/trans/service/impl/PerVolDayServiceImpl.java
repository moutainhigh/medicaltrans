package com.segi.uhomecp.medicaltrans.trans.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.segi.uhomecp.medicaltrans.trans.dao.PerVolDayMapper;
import com.segi.uhomecp.medicaltrans.trans.dto.PersonalVolumeDayDto;
import com.segi.uhomecp.medicaltrans.trans.service.PerVolDayService;

/**
 * @ClassName:  PerVolDayServiceImpl   
 * @Description:个人运送量日排名实现类   
 * @author: zhaoqing
 * @date:   2018年9月17日 上午11:48:12
 */
@Service
public class PerVolDayServiceImpl implements PerVolDayService {

	@Autowired
	private PerVolDayMapper perVolDayMapper;
	
	/**
	 * <p>Title: getDayTransVolRank</p>   
	 * <p>Description: 查询个人运送量日排名信息</p> 
	 * <p>zhaoqing</p>
	 * @param groupOrganId
	 * @param paramsDto
	 * @return   
	 */
	@Override
	public List<PersonalVolumeDayDto> getDayTransVolRank(
			int groupOrganId, PersonalVolumeDayDto paramsDto) {
		return perVolDayMapper.getDayTransVolRank(paramsDto);
	}

}
