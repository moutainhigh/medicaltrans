package com.segi.uhomecp.medicaltrans.report.monthrank.personal.rpc;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import segi.medicaltrans.report.pervolume.month.CurUserRankIce;
import segi.medicaltrans.report.pervolume.month.PerTaskAmoMonthParam;
import segi.medicaltrans.report.pervolume.month.PersonalVolumeMonthIce;
import segi.medicaltrans.report.pervolume.month.PersonalVolumeMonthPaginator;
import segi.medicaltrans.report.pervolume.month._PersonalVolumeMonthServiceIceDisp;
import Ice.Current;

import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.cache.PersonalVolumeRedisCache;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.dto.PersonalVolumeMonthDto;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.dto.PersonalVolumeMonthPageDto;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.service.PersonalVolumeMonthInfoService;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.PageUtils;

/**
 * Title: MtPersonalVolumeMonthRpc.java
 * 
 * @Description: 描述
 * @author yangyh@segimail.com
 * @created 2018年5月5日 下午6:07:47
 */
@Component
public class PersonalVolumeMonthRpc extends _PersonalVolumeMonthServiceIceDisp {

	/** 描述 (@author: yangyh@segimail.com) */
	private static final long serialVersionUID = -1283793773769722698L;

	@Resource
	private PersonalVolumeMonthInfoService personalVolumeMonthInfoService;
	
	@Resource
	private PersonalVolumeRedisCache personalVolumeRedisCache;

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonalVolumeMonthRpc.class);

	/**
	 * @discription 本月运送量排名
	 * @author yangyh@segimail.com
	 * @created 2018年5月7日 下午2:46:26
	 * @param personalVolumeMonthParam
	 * @param __current
	 * @return
	 */
	@Override
	public PersonalVolumeMonthPaginator getMonthTransVolumeRank(PerTaskAmoMonthParam perTaskAmoMonthParam,
			Current __current) {
		PersonalVolumeMonthPaginator result = new PersonalVolumeMonthPaginator();
		result.code = RpcError.SUCCESS.getCode();
		result.msg = RpcError.SUCCESS.getMessage();
		try {
			PersonalVolumeMonthDto params = BeanCopierUtils.copyProperties(perTaskAmoMonthParam,
					PersonalVolumeMonthDto.class, true);
			ResultInfo resultInfo = personalVolumeMonthInfoService.getMonthTransVolumeRank(params);	
			LOGGER.debug("getMonthTransVolumeRank: msg:{}", resultInfo.getMessage());	
			PersonalVolumeMonthPageDto resultDto = new PersonalVolumeMonthPageDto();
			if (null != resultInfo.getObject1()) {
				resultDto = (PersonalVolumeMonthPageDto) resultInfo.getObject1();
			}
			int totalCount = 0;
			if (null != resultInfo.getObject2()) {
				totalCount = (int) resultInfo.getObject2();
			}
			Integer pageNo = Constant.INIT_PAGE_NO;
			if (null != params.getPageNo()) {
				pageNo = params.getPageNo();
			}
			Integer pageLength = Constant.DEFAULT_PAGE_LENGTH;
			if (null != params.getPageLength()) {
				pageLength = params.getPageLength();
			}
			result.setPaginator(PageUtils.paginator2RpcPageIce(pageNo, pageLength, totalCount));
			result.setResultList(BeanCopierUtils.copyList2List(
					resultDto.getResultList(), PersonalVolumeMonthIce.class,
					true));
			result.setMsg(resultInfo.getMessage());
			if (null != resultDto.getCurUserRankDto()) {
				result.setCurUserRankIce(BeanCopierUtils.copyProperties(
						resultDto.getCurUserRankDto(), CurUserRankIce.class, true));
			}	
		} catch (Exception e) {
			LOGGER.error("getMonthTransVolumeRank", e);
			result.code = RpcError.FAIL.getCode();
			result.msg = RpcError.FAIL.getMessage();
		}
		return result;
	}
}
