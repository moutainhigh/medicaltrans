package com.segi.uhomecp.medicaltrans.baseinfo.userhosp.rpc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import page.RpcPageIce;
import resp.RpcRespIce;
import segi.common.organ.COrginfoIQueryRpcClient;
import segi.datacachesvr.queryInfo.COrganEmployeeIPrx;
import segi.datacachesvr.queryInfo.UserDepInfo;
import segi.datacachesvr.queryInfo.UserInfo;
import segi.datacachesvr.queryInfo.pageInfo;
import segi.medicaltrans.base.userhosp.UserHospIce;
import segi.medicaltrans.base.userhosp.UserHospInfoIce;
import segi.medicaltrans.base.userhosp.UserHospParamIce;
import segi.medicaltrans.base.userhosp.UserHospRelReturnIce;
import segi.medicaltrans.base.userhosp.UserHospRelReturnPadIce;
import segi.medicaltrans.base.userhosp.UserHospRetPageIce;
import segi.medicaltrans.base.userhosp.UserHospReturnIce;
import segi.medicaltrans.base.userhosp._UserHospServiceIceDisp;
import Ice.Current;
import IceExt.IceClientUtil;

import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocation;
import com.segi.uhomecp.medicaltrans.base.userhosprel.model.MtUserHospRel;
import com.segi.uhomecp.medicaltrans.base.userhosprel.model.MtUserHospRelCriteria;
import com.segi.uhomecp.medicaltrans.base.userhosprel.model.MtUserHospRelCriteria.Criteria;
import com.segi.uhomecp.medicaltrans.base.userhosprel.service.MtUserHospRelService;
import com.segi.uhomecp.medicaltrans.baseinfo.location.service.MtBuildLocationManagerService;
import com.segi.uhomecp.medicaltrans.baseinfo.userhosp.dto.MtUserHospInfoDto;
import com.segi.uhomecp.medicaltrans.baseinfo.userhosp.dto.MtUserHospParamDto;
import com.segi.uhomecp.medicaltrans.baseinfo.userhosp.service.MtUserHospInfoService;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.dto.CommunityInfo;
import com.segi.uhomecp.wh.common.enums.SexEnum;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;

@Component
public class MtUserHospServiceRpc extends _UserHospServiceIceDisp{

	public static final Logger logger = LoggerFactory.getLogger(MtUserHospServiceRpc.class);

	@Autowired
	public MtUserHospInfoService mtUserHospInfoService;
	
	@Autowired
	public MtUserHospRelService mtUserHospRelService;
	
	@Autowired
	public MtBuildLocationManagerService mtBuildLocationManagerService;
	
	/**
	 * 类描述: 
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月10日 下午2:49:09   
	 */
	private static final long serialVersionUID = -6557920440898132013L;

	/**
	 * 员工信息缓存service
	 * 
	 */
	private static COrganEmployeeIPrx getCOrganEmployeeIPrx() {
		return IceClientUtil.getServicePrxByClass(COrganEmployeeIPrx.class);
	}
	
	/**
	 * 
	 * 类描述: 用户科室信息web
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月10日 下午8:24:29
	 */
	@Override
	public UserHospRelReturnIce queryUserHospInfo(UserHospIce userHospIce,
			Current __current) {
		UserHospRelReturnIce result = new UserHospRelReturnIce();
		result.code = RpcError.SUCCESS.getCode();
		result.message = RpcError.SUCCESS.getMessage();
		UserHospReturnIce returnIce = new UserHospReturnIce();
		try {
			MtUserHospRelCriteria example = new MtUserHospRelCriteria();
			Criteria criteria = example.createCriteria();
			criteria.andUserIdEqualTo(Integer.valueOf(userHospIce.getUserId()));
			List<MtUserHospRel> listUserHosp = mtUserHospRelService.selectByExample(example);
			// 查询用户信息
			UserInfo userInfo = CommonServiceUtils.getCurrentUserInfoByID(Integer.valueOf(userHospIce.getUserId()));
			if (userInfo != null) {
				returnIce.setTel(userInfo.getTel() == null ? "" : userInfo.getTel());
				returnIce.setUserNo(userInfo.getJobNumber() == null ? "" : userInfo.getJobNumber());
				returnIce.setUserName(userInfo.getUserName() == null ? "" : userInfo.getUserName());
			}
			//  查询当前用户的有权限的组织信息
			List<CommunityInfo> userOrganInfoList = CommonServiceUtils.getCommunitysByUser(Integer.valueOf(userHospIce.getUserId()));
			if (userOrganInfoList != null && userOrganInfoList.size() > 0) {
				Map<Integer, MtUserHospRel> organHospMap = new HashMap<Integer, MtUserHospRel>();
				Map<Integer, MtBuildLocation> locationIdMap = new HashMap<Integer, MtBuildLocation>();
				List<UserHospInfoIce> userHospInfoList = new ArrayList<UserHospInfoIce>();
				for (CommunityInfo communityInfo : userOrganInfoList) {
					if (communityInfo != null) {
						UserHospInfoIce userHospInfoIce = new UserHospInfoIce();
						userHospInfoIce.setOrganId(communityInfo.getCommunityId() == null ? "" : String.valueOf(communityInfo.getCommunityId()));
						userHospInfoIce.setOrganName(StringUtils.isNotEmpty(communityInfo.getName()) ? communityInfo.getName() : "");
						userHospInfoList.add(userHospInfoIce);
					}
				}
				if (AppUtils.isNotEmpty(listUserHosp)) {
					organHospMap = AppUtils.list2Map(listUserHosp, "organId", MtUserHospRel.class);
					// 一次性取出科室信息
					List<Integer> houseIdList = AppUtils.list2ParamsList(listUserHosp, MtUserHospRel.class, "houseId");
					List<MtBuildLocation> locationList = mtBuildLocationManagerService.getLocationInfoBylocationIdList(houseIdList);
					locationIdMap = AppUtils.list2Map(locationList, "locationId", MtBuildLocation.class);
				}
				MtUserHospRel hospRel = null;
				MtBuildLocation house = null;
				for (UserHospInfoIce userHospInfoIce : userHospInfoList) {
					hospRel = organHospMap.get(StringUtils.isEmpty(userHospInfoIce.getOrganId()) ? "" : Integer.valueOf(userHospInfoIce.getOrganId()));
					if (hospRel != null && hospRel.getHouseId() != null) {
						house = locationIdMap.get(hospRel.getHouseId());
						userHospInfoIce.setHouseId(String.valueOf(hospRel.getHouseId()));
						userHospInfoIce.setHouseName(house == null || StringUtils.isEmpty(house.getLocationName()) ? "" : house.getLocationName());
						userHospInfoIce.setStatus(house == null || StringUtils.isEmpty(house.getStatus()) ? "" : house.getStatus());
					}
				}
				returnIce.setOrganList(userHospInfoList);
			}
			result.setUserHospReturnIce(returnIce);
		} catch (Exception e) {
			logger.error("queryUserHospInfo", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("用户科室信息查询失败");
		}
		return result;
	}

	/**
	 * 
	 * 类描述: 保存用户科室信息
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月10日 下午3:08:44
	 */
	@Override
	public RpcRespIce saveUserHospInfo(UserHospParamIce userHospParamIce,
			Current __current) {
		RpcRespIce result = new RpcRespIce();
		result.code = RpcError.SUCCESS.getCode();
		result.message = RpcError.SUCCESS.getMessage();
		try {
			MtUserHospParamDto userHospParamDto = BeanCopierUtils.copyProperties(userHospParamIce, MtUserHospParamDto.class, true);
			if (AppUtils.isNotEmpty(userHospParamIce.getOrganList())) {
				userHospParamDto.setOrganList(BeanCopierUtils.copyList2List(userHospParamIce.getOrganList(), MtUserHospInfoDto.class, true));
			}
			mtUserHospInfoService.saveUserHospInfo(userHospParamDto);
		} catch (Exception e) {
			logger.error("saveUserHospInfo", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("保存用户科室信息失败");
		}
		return result;
	}

	/**
	 * @discription 用户项目查询科室信息
	 * @author yangyh@segimail.com       
	 * @created 2018年5月10日 下午5:11:21      
	 * @param userHospIce
	 * @param __current
	 */
	@Override
	public UserHospRelReturnPadIce queryHospUserPad(UserHospIce userHospIce,
			Current __current) {
		UserHospRelReturnPadIce result = new UserHospRelReturnPadIce();
		result.code = RpcError.SUCCESS.getCode();
		result.message = RpcError.SUCCESS.getMessage();
		try {
			MtUserHospInfoDto houseInfoDto = mtUserHospInfoService.queryHouseInfo(BeanCopierUtils.copyProperties(userHospIce, MtUserHospRel.class, true));
			result.setUserHospInfoIce(BeanCopierUtils.copyProperties(houseInfoDto, UserHospInfoIce.class, true));
		} catch (Exception e) {
			logger.error("queryHospUserPad", e);
			result.code = RpcError.FAIL.getCode();
			result.message = e.getMessage();
		}
		return result;
	}

	/**
	 * @discription 科室用户科室项目切换
	 * @author yangyh@segimail.com       
	 * @created 2018年5月10日 下午4:42:02      
	 * @param userHospIce
	 * @param __current
	 */
	@Override
	public RpcRespIce switchoverUserHosp(UserHospIce userHospIce,
			Current __current) {
		RpcRespIce result = new RpcRespIce();
		result.code = RpcError.SUCCESS.getCode();
		result.message = RpcError.SUCCESS.getMessage();
		try {
			MtUserHospRel mtUserHospRel = BeanCopierUtils.copyProperties(userHospIce, MtUserHospRel.class, true);
			String userHospRelId = mtUserHospInfoService.updateUserHospRel(mtUserHospRel);
			result.setData(userHospRelId);
		} catch (Exception e) {
			logger.error("switchoverUserHosp", e);
			result.code = RpcError.FAIL.getCode();
			result.message = e.getMessage();
		}
		return result;
	}

	/**
	 * 
	 * 类描述: 用户科室分页
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月11日 上午9:51:54
	 */
	@Override
	public UserHospRetPageIce queryUserHospPage(UserHospIce userHospIce,
			Current __current) {
		UserHospRetPageIce result = new UserHospRetPageIce();
		try {
			// 查询用户科室分页
			//result = mtUserHospInfoService.queryUserHospPage(transTypeIce);
			int nOrganId = Integer.valueOf(userHospIce.getOrganId());
			int pageNo = Integer.valueOf(userHospIce.getPageNo());
			int pageLength = Integer.valueOf(userHospIce.getPageLength());
			// TODO 查询当前组织下的人员信息
			pageInfo pageInfo = MtUserHospServiceRpc.getCOrganEmployeeIPrx()
					.getUserPageByOrgId(nOrganId, nOrganId, COrginfoIQueryRpcClient.channel, pageNo, pageLength);
			UserDepInfo[] data = pageInfo.getData();
			UserHospIce userHosp = new UserHospIce();
			List<UserHospIce> listUserHosp = new ArrayList<UserHospIce>();
			if (data != null && data.length > 0) {
				for (UserDepInfo userDepInfo : data) {
					userHosp = BeanCopierUtils.copyProperties(userDepInfo, UserHospIce.class, true);
					userHosp.setUserNo(userDepInfo.getJobNumber());
					userHosp.setGender(String.valueOf(userDepInfo.getSex()));
					userHosp.setGenderName(SexEnum.getNameByCode(String.valueOf(userDepInfo.getSex())));
					listUserHosp.add(userHosp);
				}
			}
			RpcPageIce rpcPageIce = new RpcPageIce(String.valueOf(pageInfo.getPageLength()), String.valueOf(pageInfo.getPageNo()), String.valueOf(pageInfo.getTotalCount()));
			result.setResultList(listUserHosp);
			result.setPaginator(rpcPageIce);
			result.code = RpcError.SUCCESS.getCode();
			result.message = RpcError.SUCCESS.getMessage();
		} catch (Exception e) {
			logger.error("queryUserHospPage", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("查询用户科室分页失败");
		}
		return result;
	}
}
