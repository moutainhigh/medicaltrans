package com.segi.uhomecp.medicaltrans.baseinfo.userhosp.rpc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import segi.medicaltrans.common.userhosp.UserHospCommonIce;
import segi.medicaltrans.common.userhosp.UserHospIce;
import segi.medicaltrans.common.userhosp.UserHospRelIceListRsp;
import segi.medicaltrans.common.userhosp.UserHospRelReturnInfoIce;
import segi.medicaltrans.common.userhosp.UserIdsByHouseIdReturnIce;
import segi.medicaltrans.common.userhosp._UserHospCommonServiceIceDisp;
import Ice.Current;

import com.segi.uhomecp.medicaltrans.base.userhosprel.model.MtUserHospRel;
import com.segi.uhomecp.medicaltrans.base.userhosprel.service.MtUserHospRelService;
import com.segi.uhomecp.medicaltrans.baseinfo.location.service.MtBuildLocationManagerService;
import com.segi.uhomecp.medicaltrans.baseinfo.userhosp.dto.MtUserHospInfoDto;
import com.segi.uhomecp.medicaltrans.baseinfo.userhosp.service.MtUserHospInfoService;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;

@Component
public class MtUserHospCommonServiceRpc extends _UserHospCommonServiceIceDisp{

	
	/**
	 * 2018年9月11日下午5:47:00
	 */
	private static final long serialVersionUID = 1L;
	
	public static final Logger logger = LoggerFactory.getLogger(MtUserHospCommonServiceRpc.class);

	@Autowired
	public MtUserHospInfoService mtUserHospInfoService;
	
	@Autowired
	public MtUserHospRelService mtUserHospRelService;
	
	@Autowired
	public MtBuildLocationManagerService mtBuildLocationManagerService;

	/**
	 * @Title: queryHospUserInfo 
	 * @Description: 查询人员当前科室信息 
	 * @author liuyi@segimail.com 
	 * @date 2018年9月11日下午5:53:03
	 */
	@Override
	public UserHospRelReturnInfoIce queryHospUserInfo(UserHospIce userHospIce,
			Current __current) {
		UserHospRelReturnInfoIce result = new UserHospRelReturnInfoIce();
		result.code = RpcError.SUCCESS.getCode();
		result.message = RpcError.SUCCESS.getMessage();
		try {
			MtUserHospInfoDto houseInfoDto = mtUserHospInfoService.queryHouseInfo(BeanCopierUtils.copyProperties(userHospIce, MtUserHospRel.class, true));
			result.setUserHospCommonIce(BeanCopierUtils.copyProperties(houseInfoDto, UserHospCommonIce.class, true));
		} catch (Exception e) {
			logger.error("queryHospUserInfo", e);
			result.code = RpcError.FAIL.getCode();
			result.message = e.getMessage();
		}
		return result;
	}

	/**
	 * @discription 通过科室ID查询科室人员
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月17日 下午4:53:11      
	 * @param houseId
	 * @param __current
	 * @return     
	 */
	@Override
	public UserIdsByHouseIdReturnIce queryUserIdsByHouseId(String houseId,
			Current __current) {
		UserIdsByHouseIdReturnIce rsp = new UserIdsByHouseIdReturnIce(
				RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), new ArrayList<>());
		try {
			if (StringUtils.isEmpty(houseId)) {
				return rsp;
			}
			List<MtUserHospRel> userHospList = mtUserHospInfoService.
					queryUserHospInfoByHouseId(Integer.valueOf(houseId));
			if (AppUtils.isNotEmpty(userHospList)) {
				rsp.setUserIdList(AppUtils.list2ParamsList(userHospList, (obj) -> String.valueOf(obj.getUserId())));
			}
		} catch (Exception e) {
			logger.error("queryUserIdsByHouseId", e);
			rsp.code = RpcError.FAIL.getCode();
			rsp.message = e.getMessage();
		}
		return rsp;
	}

	/**
	 * <p>Title: queryHospUserInfoList</p>   
	 * <p>Description: 用户科室详情列表查询</p> 
	 * <p>zhaoqing</p>
	 * @param userHospIce
	 * @param __current
	 * @return   
	 */
	@Override
	public UserHospRelIceListRsp queryHospUserInfoList(UserHospIce userHospIce,
			Current __current) {
		UserHospRelIceListRsp result = new UserHospRelIceListRsp();
		result.code = RpcError.SUCCESS.getCode();
		result.message = RpcError.SUCCESS.getMessage();
		try {
			// 参数对象转换
			MtUserHospInfoDto dto = BeanCopierUtils.copyProperties(
					userHospIce, MtUserHospInfoDto.class, true);
			dto.setUserIdList(userHospIce.getUserIdList());
			// 列表信息查询
			List<MtUserHospInfoDto> houseInfoDtoList = mtUserHospInfoService.queryHouseInfoList(dto);
			// 结果集对象转换
			result.setRerultList(BeanCopierUtils.copyList2List(
					houseInfoDtoList, UserHospCommonIce.class, true));
		} catch (Exception e) {
			logger.error("queryHospUserInfoList", e);
			result.code = RpcError.FAIL.getCode();
			result.message = e.getMessage();
		}
		return result;
	}

}
