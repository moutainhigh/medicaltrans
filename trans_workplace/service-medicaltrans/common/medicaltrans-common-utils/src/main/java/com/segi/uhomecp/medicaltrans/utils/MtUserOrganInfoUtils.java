package com.segi.uhomecp.medicaltrans.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import segi.datacachesvr.queryInfo.COrganEmployeeIPrx;
import segi.datacachesvr.queryInfo.retUserOrganInfoListV2;
import segi.datacachesvr.queryInfo.stUserOrganInfoV2;
import IceExt.IceClientUtil;

import com.segi.uhomecp.utils.FastjsonUtils;

/**
 * @ClassName:  MtUserOrganInfoUtils   
 * @Description:员工组织信息查询工具类   
 * @author: zhaoqing
 * @date:   2018年5月22日 下午5:57:48
 */
public class MtUserOrganInfoUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(MtUserOrganInfoUtils.class);
	
	/**
	 * 组织机构路由用途的默认值
	 */
	public static final int NORGANID = 0;
	
	/**
	 * 请求渠道（1 物业服务；2 商业服务）物业服务类型
	 */
	public static final int CHANNEL = 1;
	
	/**
	 * @Title: getCOrganEmployeeIPrx   
	 *  员工信息公共ICE服务
	 * @author zhaoqing  
	 * @return 
	 */
	private static COrganEmployeeIPrx getCOrganEmployeeIPrx() {
		return IceClientUtil.getServicePrxByClass(COrganEmployeeIPrx.class);
	}
	
	/**
	 * @Title: queryUserListByOrgIdAndOpts   
	 *  根据组织结构ID（必填）、员工名称、手机号、员工号、员工状态（非必填）查询员工信息
	 * @author zhaoqing  
	 * @param nOrgId 组织结构ID
	 * @param name 员工名称
	 * @param jobNumber 员工工号
	 * @param telNum 手机号
	 * @param status 员工状态
	 * @param nOrganId 为登录用户所属的组织机构，db路由用，不作查询条件，暂时无值可以传0
	 * @param channel 请求渠道（1 物业服务；2 商业服务，目前统一传1）
	 * @return      
	 */
	public static stUserOrganInfoV2[] queryUserListByOrgIdAndOpts(int nOrgId, String name,
			String jobNumber, String telNum, int status, int nOrganId, int channel) {
		logger.info("==================>查询员工信息列表: organId:{}, oraganName:{}, jobNumber:{}, "
				+ "tel:{}, status:{}", nOrgId, name, jobNumber, telNum, status);	
		stUserOrganInfoV2[] stUserOrganInfoV2s = null;
		retUserOrganInfoListV2 retUserOrganInfoListV2 = getCOrganEmployeeIPrx()
				.queryUserListByOrgIdAndOpts(nOrgId, name, jobNumber, telNum, status, nOrganId, channel);
		if (null != retUserOrganInfoListV2) {
			stUserOrganInfoV2s = retUserOrganInfoListV2.getData();
		}
		logger.info("==================>查询员工信息列表: resultList:{}", 
				FastjsonUtils.toJsonString(stUserOrganInfoV2s));
		return stUserOrganInfoV2s;
	}
	
	/**
	 * @Title: queryUserListByOrgIdAndOpts   
	 *  根据组织结构ID（必填）、员工名称、手机号、员工号、员工状态（非必填）查询员工信息
	 * @author zhaoqing  
	 * @param nOrgId
	 * @param name
	 * @param jobNumber
	 * @param telNum
	 * @param status
	 * @return 
	 */
	public static stUserOrganInfoV2[] queryUserListByOrgIdAndOpts(int nOrgId, String name,
			String jobNumber, String telNum, int status) {
		return queryUserListByOrgIdAndOpts(nOrgId, name, jobNumber, telNum, status, NORGANID, CHANNEL);
	}
	
}
