package com.segi.uhomecp.medicaltrans.report.deptvolumemonth.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import resp.RpcRespIce;
import segi.medicaltrans.location.common.LocationInfoReturn;

import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocation;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.constant.MtSeqContants;
import com.segi.uhomecp.medicaltrans.enums.UrgencyEnum;
import com.segi.uhomecp.medicaltrans.report.deptvolumemonth.dao.DeptMonthMapper;
import com.segi.uhomecp.medicaltrans.report.deptvolumemonth.dto.DeptMonthDto;
import com.segi.uhomecp.medicaltrans.report.deptvolumemonth.service.MtrDeptVolumeMonthService;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.model.DeptVolumeMonth;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.model.DeptVolumeMonthCriteria;
import com.segi.uhomecp.medicaltrans.report.monthrank.dept.service.DeptVolumeMonthService;
import com.segi.uhomecp.medicaltrans.report.schedule.model.TransSchedule;
import com.segi.uhomecp.medicaltrans.report.schedule.service.TransScheduleInfoService;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.utils.SeqContants;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.DataTypeConverUtils;

@Service
public class MtrDeptVolumeMonthServiceImpl implements MtrDeptVolumeMonthService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MtrDeptVolumeMonthService.class);

	@Autowired
	private DeptVolumeMonthService deptVolumeMonthService;
	
	@Autowired
	private DeptMonthMapper deptMonthMapper;
	
	@Autowired
	private TransScheduleInfoService transScheduleInfoService;
	
	/**
	 * @Title: updateDeptVolume 
	 * @Description: 实时修改科室月统计报表 
	 * @author liuyi@segimail.com 
	 * @date 2018年9月14日上午11:58:26
	 */
	@Override
	public RpcRespIce updateDeptVolume(DeptMonthDto deptMonthDto) {
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), null);
		// 查询排程表信息
		rsp = queryTransSchedule(deptMonthDto);
		if (rsp == null) {
			// 返回空表示没有统计不用更新
			return rsp;
		}
		if (RpcError.FAIL.getCode().equals(rsp.getCode())) {
			return rsp;
		}
		try {
			Long updateDate = DataTypeConverUtils.formatDateToLongYYYYMMDDHHMMSS(new Date());
			// 修改原先科室运送量
			Integer countBefore = updateBeforeDeptMonth(deptMonthDto, updateDate);
			if (MtConstant.SQL_UPDATE_FAILURE != countBefore) {
				// 如果修改成功就判断科室是否运送量为零 并且运送量为零 就把科室删除掉
				LOGGER.error("修原先的科室运送量 科室id----" + deptMonthDto.getBeforeHouseId() + "成功 开始判断科室是否运送量为零 并且运送量为零 就把科室删除掉");
				deleteInvalidDeptMonth(deptMonthDto);
			}
			// 修改新的科室运送量
			Integer countAfter = updateAfterDeptMonth(deptMonthDto, updateDate);
			if (MtConstant.SQL_UPDATE_FAILURE == countAfter) {
				// 如果修改失败就新增
				LOGGER.error("修改新的科室运送量 科室id----" + deptMonthDto.getHouseId() + "失败 开始新增");
				saveDeptMonth(deptMonthDto, updateDate);
			}
		} catch (Exception e) {
			rsp.setCode(RpcError.FAIL.getCode());
			LOGGER.error("updateDeptVolume实时修改科室信息失败", e);
		}
		return rsp;
	}
	
	/**
	 * @Title: deleteInvalidDeptMonth 
	 * @Description: 判断原先科室 是否应该被删除 
	 * @author liuyi@segimail.com 
	 * @date 2018年9月17日上午11:47:26
	 */
	public void deleteInvalidDeptMonth(DeptMonthDto deptMonthDto) {
		DeptVolumeMonth deptMonth = queryDeptVolumeMonth(Integer.valueOf(deptMonthDto.getOrganId()), 
				Integer.valueOf(deptMonthDto.getBeforeHouseId()), Integer.valueOf(deptMonthDto.getBeforeCycleYm()));
		if (deptMonth.getTransAmount() != 0) {
			// 如果科室运送总量不为空  就不用删除
			return ;
		}
		// 查询科室信息
		MtBuildLocation location = getLocationInfo(Integer.valueOf(deptMonthDto.getOrganId()), Integer.valueOf(deptMonthDto.getBeforeHouseId()));
		if (location != null && Constant.STATUS_CD_DEL.equals(location.getStatus())) {
			// 如果科室是无效状态的  就删除
			deleteDeptMonth(Integer.valueOf(deptMonthDto.getOrganId()), 
					Integer.valueOf(deptMonthDto.getBeforeHouseId()), Integer.valueOf(deptMonthDto.getBeforeCycleYm()));
		}
	}

	/**
	 * @Title: deleteDeptMonth 
	 * @Description: 删除科室 
	 * @author liuyi@segimail.com 
	 * @date 2018年9月17日上午11:42:22
	 */
	private void deleteDeptMonth(Integer organId, Integer houseId, Integer cycleYm) {
		DeptVolumeMonthCriteria example = new DeptVolumeMonthCriteria();
		DeptVolumeMonthCriteria.Criteria criteria = example.createCriteria();
		criteria.andOrganIdEqualTo(organId);
		criteria.andHouseIdEqualTo(houseId);
		criteria.andCycleYmEqualTo(cycleYm);
		deptVolumeMonthService.deleteByExample(example);
	}

	/**
	 * @Title: getDeptMonth 
	 * @Description: 获得新的科室报表对象 
	 * @author liuyi@segimail.com 
	 * @date 2018年10月10日上午10:51:20
	 */
	public DeptVolumeMonth getDeptMonth() {
		DeptVolumeMonth deptMonth = new DeptVolumeMonth();
		deptMonth.setHouseId(0);
		deptMonth.setCycleYm(0);
		deptMonth.setCreateDate(0L);
		deptMonth.setUpdateDate(0L);
		deptMonth.setTransAmount(0);
		deptMonth.setDispatchAmount(0);
		deptMonth.setAutonomousAmount(0);
		deptMonth.setSpecialAmount(0);
		deptMonth.setUrgentAmount(0);
		deptMonth.setCommonAmount(0);
		return deptMonth;
	}
	
	/**
	 * @Title: getLocationInfo 
	 * @Description: 查询科室信息 
	 * @author liuyi@segimail.com 
	 * @date 2018年10月10日上午10:51:40
	 */
	private MtBuildLocation getLocationInfo(Integer organId, Integer locationId) {
		MtBuildLocation location = new MtBuildLocation();
		LocationInfoReturn rsp = MtCommonServiceUtils.queryLocationInfo(organId, locationId);
		if (RpcError.SUCCESS.getCode().equals(rsp.getCode()) && rsp.getLocationInfoIce() != null) {
			return BeanCopierUtils.copyProperties(rsp.getLocationInfoIce(), MtBuildLocation.class, true);
		}
		return location;
	}
	
	/**
	 * @Title: saveDeptMonth 
	 * @Description: 保存科室信息 
	 * @author liuyi@segimail.com 
	 * @date 2018年9月14日下午6:57:40
	 */
	private Integer saveDeptMonth(DeptMonthDto deptMonthDto, Long updateDate) {
		DeptVolumeMonth deptMonth = getDeptMonth();
		deptMonth.setHouseId(Integer.valueOf(deptMonthDto.getHouseId()));
		deptMonth.setCycleYm(Integer.valueOf(deptMonthDto.getCycleYm()));
		deptMonth.setCreateDate(updateDate);
		deptMonth.setUpdateDate(updateDate);
		if (UrgencyEnum.URGENCY_COMMONLY.getCode().equals(deptMonthDto.getUrgency())) {
			deptMonth.setCommonAmount(1);
		}
		
		if (UrgencyEnum.URGENCY_EXTRAURGENT.getCode().equals(deptMonthDto.getUrgency())) {
			deptMonth.setSpecialAmount(1);
		}if (UrgencyEnum.URGENCY_URGENT.getCode().equals(deptMonthDto.getUrgency())) {
			deptMonth.setUrgentAmount(1);
		}
		deptMonth.setDispatchAmount(1);
		deptMonth.setTransAmount(1);
		// 查询科室信息
		MtBuildLocation location = getLocationInfo(Integer.valueOf(deptMonthDto.getOrganId()), Integer.valueOf(deptMonthDto.getHouseId()));
		if (location != null) {
			deptMonth.setOrganId(location.getOrganId());
			deptMonth.setGroupOrganId(location.getGroupOrganId());
			deptMonth.setBuildId(location.getLocationId());
			deptMonth.setSid(location.getSid());
			deptMonth.setFloorId(location.getFloorId());
		}
		int intValue = SeqContants.getSequnces(MtSeqContants.MTR_DEPT_VOLUME_MONTH_ID_SEQ).intValue();
		deptMonth.setId(intValue);
		return deptVolumeMonthService.insert(deptMonth);
	}

	/**
	 * @Title: updateAfterDeptMonth 
	 * @Description: 修改之后的科室报表对象
	 * @author liuyi@segimail.com 
	 * @date 2018年10月10日上午10:52:01
	 */
	private Integer updateAfterDeptMonth(DeptMonthDto deptMonthDto,
			Long updateDate) {
		DeptVolumeMonth deptMonth = getDeptMonth();
		deptMonth.setHouseId(Integer.valueOf(deptMonthDto.getHouseId()));
		deptMonth.setCycleYm(Integer.valueOf(deptMonthDto.getCycleYm()));
		deptMonth.setUpdateDate(updateDate);
		if (UrgencyEnum.URGENCY_COMMONLY.getCode().equals(deptMonthDto.getUrgency())) {
			deptMonth.setCommonAmount(1);
		}
		if (UrgencyEnum.URGENCY_URGENT.getCode().equals(deptMonthDto.getUrgency())) {
			deptMonth.setUrgentAmount(1);
		}
		if (UrgencyEnum.URGENCY_EXTRAURGENT.getCode().equals(deptMonthDto.getUrgency())) {
			deptMonth.setSpecialAmount(1);
		}
		deptMonth.setDispatchAmount(1);
		deptMonth.setTransAmount(1);
		return deptMonthMapper.updateDeptMonth(deptMonth);
	}

	/**
	 * @Title: updateBeforeDeptMonth 
	 * @Description: 修改之前的科室报表对象 
	 * @author liuyi@segimail.com 
	 * @date 2018年10月10日上午10:52:36
	 */
	private Integer updateBeforeDeptMonth(DeptMonthDto deptMonthDto, Long updateDate) {
		DeptVolumeMonth deptMonth = getDeptMonth();
		deptMonth.setHouseId(Integer.valueOf(deptMonthDto.getBeforeHouseId()));
		deptMonth.setCycleYm(Integer.valueOf(deptMonthDto.getBeforeCycleYm()));
		deptMonth.setUpdateDate(updateDate);
		if (UrgencyEnum.URGENCY_COMMONLY.getCode().equals(deptMonthDto.getBeforeUrgency())) {
			deptMonth.setCommonAmount(-1);
		}
		if (UrgencyEnum.URGENCY_URGENT.getCode().equals(deptMonthDto.getBeforeUrgency())) {
			deptMonth.setUrgentAmount(-1);
		}
		if (UrgencyEnum.URGENCY_EXTRAURGENT.getCode().equals(deptMonthDto.getBeforeUrgency())) {
			deptMonth.setSpecialAmount(-1);
		}
		deptMonth.setDispatchAmount(-1);
		deptMonth.setTransAmount(-1);
		return deptMonthMapper.updateDeptMonth(deptMonth);
	}

	/**
	 * @Title: queryDeptVolumeMonth 
	 * @Description: 查询科室报表对象信息 
	 * @author liuyi@segimail.com 
	 * @date 2018年10月10日上午10:53:39
	 */
	private DeptVolumeMonth queryDeptVolumeMonth(Integer organId, Integer houseId, Integer cycleYm){
		DeptVolumeMonthCriteria example = new DeptVolumeMonthCriteria();
		DeptVolumeMonthCriteria.Criteria criteria = example.createCriteria();
		criteria.andOrganIdEqualTo(organId);
		criteria.andHouseIdEqualTo(houseId);
		criteria.andCycleYmEqualTo(cycleYm);
		List<DeptVolumeMonth> deptList = deptVolumeMonthService.selectByExample(example);
		if (AppUtils.isNotEmpty(deptList)) {
			return deptList.get(0);
		}
		return new DeptVolumeMonth();
	}

	/**
	 * @Title: queryTransSchedule 
	 * @Description: 查询排程表信息 
	 * @author liuyi@segimail.com 
	 * @date 2018年10月10日上午10:53:59
	 */
	public RpcRespIce queryTransSchedule(DeptMonthDto deptMonthDto) {
		Integer organId = Integer.valueOf(deptMonthDto.getOrganId());
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), null);
		TransSchedule schedule = transScheduleInfoService.queryTransSchedule(organId);
		if (schedule == null) {
			// 排程表没查到 表示没有这个项目不用更新
			return null;
		}
		if (MtConstant.SCHEDULE_STATUS_RUNNING.equals(schedule.getStatus())) {
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("正在更新排程表");
			return rsp;
		}
		Long createDate = Long.valueOf(deptMonthDto.getCreateDate());
		return judgeDate(schedule, createDate);
	}
	
	/**
	 * @Title: judgeDate 
	 * @Description: 根据创建时间和排程表时间 判断任务是否已经被统计 
	 * @author liuyi@segimail.com 
	 * @date 2018年10月10日上午10:54:21
	 */
	private RpcRespIce judgeDate(TransSchedule schedule, Long createDate){
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), null);
		Long paramDate = DataTypeConverUtils.formatDateToLongYYYYMMDDHHMMSS(schedule.getParamDate());
		Long excDate = DataTypeConverUtils.formatDateToLongYYYYMMDDHHMMSS(schedule.getExcDate());
		Long excEndDate = DataTypeConverUtils.formatDateToLongYYYYMMDDHHMMSS(schedule.getExcEndDate());
		if (null != schedule.getExcDate() && null != schedule.getExcEndDate()) {
			if (Long.valueOf(paramDate) > Long.valueOf(excDate)) {
				rsp = judgeDateSize(createDate, paramDate);
			} else {
				rsp = judgeDateSize(createDate, excEndDate);
			}
		} else {
			rsp = judgeDateSize(createDate, paramDate);
		}
		return rsp;
	}
	
	/**
	 * @Title: judgeDateSize 
	 * @Description: 根据统计时间 和创建时间判断 任务是否被统计 
	 * @author liuyi@segimail.com 
	 * @date 2018年10月10日上午10:55:21
	 */
	private RpcRespIce judgeDateSize(Long createDate, Long judgeDate){
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), null);
		if (createDate >= judgeDate) {
			return null;
		}
		return rsp;
	}
}
