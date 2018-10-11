package com.segi.uhomecp.medicaltrans.baseinfo.taskloop.rpc;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import resp.RpcRespIce;
import segi.medicaltrans.base.taskloop.House;
import segi.medicaltrans.base.taskloop.HouseInfo;
import segi.medicaltrans.base.taskloop.TaskLoopIce;
import segi.medicaltrans.base.taskloop.TaskLoopInfo;
import segi.medicaltrans.base.taskloop.TaskLoopPageParam;
import segi.medicaltrans.base.taskloop.TaskLoopPaginator;
import segi.medicaltrans.base.taskloop.TaskLoopParam;
import segi.medicaltrans.base.taskloop.TaskLoopStatusParam;
import segi.medicaltrans.base.taskloop.UserInfo;
import segiwh.common.User;
import segi.medicaltrans.base.taskloop._TaskLoopServiceIceDisp;
import Ice.Current;

import com.segi.uhomecp.medicaltrans.baseinfo.taskloop.dto.HouseDto;
import com.segi.uhomecp.medicaltrans.baseinfo.taskloop.dto.MtTaskLoopDto;
import com.segi.uhomecp.medicaltrans.baseinfo.taskloop.dto.MtTaskLoopPageDto;
import com.segi.uhomecp.medicaltrans.baseinfo.taskloop.service.MtTaskLoopInfoService;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.PageUtils;

@Component
public class MtTaskLoopServiceRpc extends _TaskLoopServiceIceDisp {

	@Autowired
	private MtTaskLoopInfoService mtTaskLoopInfoService;

	/** 描述 (@author: yangyh@segimail.com) */
	private static final long serialVersionUID = 1443805440450555307L;

	private final static Logger logger = LoggerFactory
			.getLogger(MtTaskLoopServiceRpc.class);

	/**
	 * @discription 循环任务分页列表
	 * @author yangyh@segimail.com
	 * @created 2018年3月27日 上午11:54:09
	 * @param loopTaskPageParam
	 * @param __current
	 * @return
	 */
	@Override
	public TaskLoopPaginator queryTaskLoopByPage(TaskLoopPageParam taskLoopPageParam, Current __current) {
		TaskLoopPaginator result = new TaskLoopPaginator();
		result.code = RpcError.SUCCESS.getCode();
		result.msg = RpcError.SUCCESS.getMessage();
		try {
			MtTaskLoopDto mtTaskLoopDto = BeanCopierUtils.copyProperties(taskLoopPageParam, MtTaskLoopDto.class, true);
			MtTaskLoopPageDto mtTaskLoopPageDto = mtTaskLoopInfoService.queryTaskLoopByPage(mtTaskLoopDto);
			result.setPaginator(PageUtils.paginator2RpcPageIce(mtTaskLoopPageDto.getPaginator()));
			result.setResultList(BeanCopierUtils.copyList2List(mtTaskLoopPageDto.getResultList(), TaskLoopIce.class,
					true));
		} catch (Exception e) {
			logger.error("queryTaskLoopByPage", e);
			result.code = RpcError.FAIL.getCode();
			result.msg = RpcError.FAIL.getMessage();
		}
		return result;
	}

	/**
	 * @discription 循环任务新建
	 * @author yangyh@segimail.com
	 * @created 2018年3月27日 上午11:54:09
	 * @param loopTaskParam
	 * @param __current
	 * @return
	 */
	@Override
	public RpcRespIce saveTaskLoop(TaskLoopParam taskLoopParam, Current __current) {
		RpcRespIce result = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			MtTaskLoopDto mtTaskLoopDto = BeanCopierUtils.copyProperties(taskLoopParam, MtTaskLoopDto.class, true);
			Integer taskLoopId = mtTaskLoopInfoService.saveTaskLoop(mtTaskLoopDto);
			result.setData(String.valueOf(taskLoopId));
			// 成功添加organId
			MtCommonServiceUtils.addOrgan(taskLoopParam.getOrganId());
		} catch (Exception e) {
			logger.error("saveTaskLoop", e);
			result.code = RpcError.FAIL.getCode();
			result.message = StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : RpcError.FAIL.getMessage();
		}
		return result;
	}

	/**
	 * @discription 循环任务编辑
	 * @author yangyh@segimail.com
	 * @created 2018年3月27日 上午11:54:09
	 * @param loopTaskParam
	 * @param __current
	 * @return
	 */
	@Override
	public RpcRespIce updateTaskLoop(TaskLoopParam taskLoopParam, Current __current) {
		RpcRespIce result = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			MtTaskLoopDto mtTaskLoopDto = BeanCopierUtils.copyProperties(taskLoopParam, MtTaskLoopDto.class, true);
			Integer taskLoopId = mtTaskLoopInfoService.updateTaskLoop(mtTaskLoopDto);
			result.setData(String.valueOf(taskLoopId));
		} catch (Exception e) {
			logger.error("updateTaskLoop", e);
			result.code = RpcError.FAIL.getCode();
			result.message = StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : RpcError.FAIL.getMessage();
		}
		return result;
	}

	/**
	 * @discription 循环任务停用启用、删除
	 * @author yangyh@segimail.com
	 * @created 2018年3月27日 上午11:54:09
	 * @param taskLoopId
	 * @param __current
	 * @return
	 */
	@Override
	public RpcRespIce updateTaskLoopStatus(TaskLoopStatusParam taskLoopStatusParam, Current __current) {
		RpcRespIce result = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			MtTaskLoopDto mtTaskLoopDto = BeanCopierUtils
					.copyProperties(taskLoopStatusParam, MtTaskLoopDto.class, true);
			Integer taskLoopId = mtTaskLoopInfoService.updateTaskLoopStatus(mtTaskLoopDto);
			result.setData(String.valueOf(taskLoopId));
		} catch (Exception e) {
			logger.error("updateTaskLoopStatus", e);
			result.code = RpcError.FAIL.getCode();
			result.message = StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : RpcError.FAIL.getMessage();
		}
		return result;
	}

	/**
	 * @discription 循环任务详情展示
	 * @author yangyh@segimail.com
	 * @created 2018年3月27日 上午11:54:09
	 * @param taskLoopId
	 * @param __current
	 * @return
	 */
	@Override
	public TaskLoopInfo queryTaskLoopDetail(String taskLoopId, Current __current) {
		TaskLoopInfo result = new TaskLoopInfo();
		result.code = RpcError.SUCCESS.getCode();
		result.msg = RpcError.SUCCESS.getMessage();
		try {
			MtTaskLoopDto dto = mtTaskLoopInfoService.queryTaskLoopDetail(Integer.valueOf(taskLoopId));
			TaskLoopIce taskLoopIce = BeanCopierUtils.copyProperties(dto, TaskLoopIce.class, true);
			taskLoopIce.setHouseList(BeanCopierUtils.copyList2List(dto.getHouseList(), House.class, true));
			taskLoopIce.setUserList(BeanCopierUtils.copyList2List(dto.getUserList(), User.class, true));
			result.setTaskLoopIce(taskLoopIce);
		} catch (Exception e) {
			logger.error("queryTaskLoopDetail", e);
			result.code = RpcError.FAIL.getCode();
			result.msg = StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : RpcError.FAIL.getMessage();
		}
		return result;
	}

	/**
	 * @discription 根据循环任务id查询科室信息列表
	 * @author yangyh@segimail.com
	 * @created 2018年3月27日 上午11:54:09
	 * @param taskLoopId
	 * @param __current
	 * @return
	 */
	@Override
	public HouseInfo queryHouseList(String taskLoopId, Current __current) {
		HouseInfo result = new HouseInfo();
		result.code = RpcError.SUCCESS.getCode();
		result.msg = RpcError.SUCCESS.getMessage();
		try {
			List<HouseDto> houseList = mtTaskLoopInfoService.queryHouseList(Integer.valueOf(taskLoopId));
			List<House> houseIceList = BeanCopierUtils.copyList2List(houseList, House.class, true);
			result.setHouseList(houseIceList);
		} catch (Exception e) {
			logger.error("queryHouseList", e);
			result.code = RpcError.FAIL.getCode();
			result.msg = RpcError.FAIL.getMessage();
		}
		return result;
	}
	
	/**
	 * @discription 根据循环任务id查询人员信息列表
	 * @author yangyh@segimail.com
	 * @created 2018年3月27日 上午11:54:09
	 * @param taskLoopId
	 * @param __current
	 * @return
	 */
	@Override
	public UserInfo queryUserInfoList(String taskLoopId, Current __current) {
		UserInfo result = new UserInfo();
		result.code = RpcError.SUCCESS.getCode();
		result.msg = RpcError.SUCCESS.getMessage();
		try {
			List<User> userList = mtTaskLoopInfoService.queryUserInfoList(Integer.valueOf(taskLoopId));
			result.setUserList(userList);
		} catch (Exception e) {
			logger.error("queryUserInfoList", e);
			result.code = RpcError.FAIL.getCode();
			result.msg = RpcError.FAIL.getMessage();
		}
		return result;
	}
}
