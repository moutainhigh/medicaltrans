package com.segi.uhomecp.medicaltrans.report.monthrank.personal.rpc;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import segi.medicaltrans.report.pervolume.month.PerTaskAmoMonthIce;
import segi.medicaltrans.report.pervolume.month.PerTaskAmoMonthPageResp;
import segi.medicaltrans.report.pervolume.month.PerTaskAmoMonthParam;
import segi.medicaltrans.report.pervolume.month._PerTaskAmoMonthServiceIceDisp;
import Ice.Current;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.cache.PersonalVolumeRedisCache;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.dto.PersonalVolumeMonthDto;
import com.segi.uhomecp.medicaltrans.report.monthrank.personal.service.PersonalVolumeMonthInfoService;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.PageUtils;

/**
 * @ClassName:  MtTaskAmountRpc   
 * @Description:个人运送量月报表Rpc  
 * @author: zhaoqing
 * @date:   2018年7月26日 上午9:09:45
 */
@Component
public class MtTaskAmountRpc extends _PerTaskAmoMonthServiceIceDisp {

	private static final long serialVersionUID = -1283793773769722698L;

	@Resource
	private PersonalVolumeMonthInfoService personalVolumeMonthInfoService;
	
	@Resource
	private PersonalVolumeRedisCache personalVolumeRedisCache;

	private static final Logger LOGGER = LoggerFactory.getLogger(MtTaskAmountRpc.class);

	/**
	 * <p>Title: getPerTaskAmoMonthPage</p>   
	 * <p>Description: 运送员运送量月报表分页查询</p> 
	 * <p>zhaoqing</p>
	 * @param params
	 * @param __current
	 * @return   
	 */
	@Override
	public PerTaskAmoMonthPageResp getPerTaskAmoMonthPage(
			PerTaskAmoMonthParam params, Current __current) {
		PerTaskAmoMonthPageResp result = new PerTaskAmoMonthPageResp();
		result.code = RpcError.SUCCESS.getCode();
		result.msg = RpcError.SUCCESS.getMessage();
		result.setResultList(new ArrayList<PerTaskAmoMonthIce>());
		try {
			PersonalVolumeMonthDto perVolMonDto = BeanCopierUtils.copyProperties(
					params, PersonalVolumeMonthDto.class, true);
			ResultInfo resultInfo = personalVolumeMonthInfoService.getMonthTransVolumePage(perVolMonDto);
			if (!resultInfo.getIsSucc() || resultInfo.getObject1() == null) {
				result.setCode(RpcError.FAIL.getCode());
				result.setMsg(resultInfo.getMessage());
				LOGGER.warn(resultInfo.getMessage());
				return result;
			}
			// 获取分页查询结果
			@SuppressWarnings("unchecked")
			PageList<PersonalVolumeMonthDto> pageList = 
					(PageList<PersonalVolumeMonthDto>) resultInfo.getObject1();
			// 结果对象转换成ICE返回对象		
			result.setResultList(BeanCopierUtils.copyList2List(pageList, PerTaskAmoMonthIce.class, true));
			result.setPaginator(PageUtils.paginator2RpcPageIce(pageList.getPaginator()));
		} catch (Exception e) {
			LOGGER.error("运送员运送量月报表分页查询失败：MtTaskAmountRpc getPerTaskAmoMonthPage", e);
			result.code = RpcError.FAIL.getCode();
			result.msg = "运送员运送量月报表分页查询失败";
		}
		return result;
	}
	
}
