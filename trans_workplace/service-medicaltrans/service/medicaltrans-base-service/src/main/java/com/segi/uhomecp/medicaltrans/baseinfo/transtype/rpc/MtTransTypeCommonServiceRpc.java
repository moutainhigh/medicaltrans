package com.segi.uhomecp.medicaltrans.baseinfo.transtype.rpc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import resp.RpcRespIce;
import segi.medicaltrans.base.transtype.TransTypeIce;
import segi.medicaltrans.common.transType.TransTypeInfo;
import segi.medicaltrans.common.transType.TransTypeInfoReturnIce;
import segi.medicaltrans.common.transType._MtCommonTransTypeServiceIceDisp;
import Ice.Current;

import com.segi.uhomecp.medicaltrans.base.transtype.model.MtTransType;
import com.segi.uhomecp.medicaltrans.baseinfo.common.service.MtImpBatchMsgInfoService;
import com.segi.uhomecp.medicaltrans.baseinfo.transtype.dao.MtTransTypeInfoMapper;
import com.segi.uhomecp.medicaltrans.baseinfo.transtype.service.MtTransTypeCommonService;
import com.segi.uhomecp.medicaltrans.baseinfo.transtype.service.MtTransTypeInfoService;
import com.segi.uhomecp.medicaltrans.cache.TransTypeRedisCache;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.DateUtil;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * Title: MtTransTypeCommonServiceRpc.java    
 * @Description: 运送类型公共rpc
 * @author zhangyang@segimail.com       
 * @created 2018年4月10日 下午8:51:13
 */
@Component
public class MtTransTypeCommonServiceRpc extends _MtCommonTransTypeServiceIceDisp {

	/**  描述   (@author: zhangyang@segimail.com) */      
	private static final long serialVersionUID = 1L;
	
	public static final Logger logger = LoggerFactory.getLogger(MtTransTypeCommonServiceRpc.class);

	@Autowired
	public MtTransTypeCommonService mtTransTypeCommonService;
	@Autowired
	public MtTransTypeInfoMapper mtTransTypeInfoMapper;
	
	@Autowired
	public MtTransTypeInfoService mtTransTypeInfoService;
	
	@Autowired
	public TransTypeRedisCache transTypeRedisCache;
	
	@Autowired
	public MtImpBatchMsgInfoService mtImpBatchMsgInfoService;

	/**
	 * @discription 根据运送类型Id List查询运送类型
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月10日 下午8:56:36      
	 * @param transTypeIdList
	 * @param __current
	 * @return     
	 */
	@Override
	public TransTypeInfoReturnIce getTransTypeInfoByTransTypeIdList(
			List<Integer> transTypeIdList, Current __current) {
		TransTypeInfoReturnIce rsp = new TransTypeInfoReturnIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(),
				new ArrayList<TransTypeInfo>());
		try {
			List<MtTransType> transTypeList = mtTransTypeCommonService.getTransTypeInfoByTransTypeIdList(transTypeIdList);
			if (AppUtils.isNotEmpty(transTypeList)) {
				List<TransTypeInfo> typeIceList = new ArrayList<TransTypeInfo>();
				for (MtTransType mtTransType : transTypeList) {
					TransTypeInfo transTypeInfo = BeanCopierUtils.copyProperties(mtTransType, TransTypeInfo.class, true);
					typeIceList.add(transTypeInfo);
				}
				rsp.setResultList(typeIceList);
			} 
		} catch (Exception e) {
			logger.error("getTransTypeInfoByTransTypeIdList", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}

	/**
	 * @Title: refreshRedisTransType 
	 * @Description: 刷新运送类型缓存 
	 * @author liuyi@segimail.com 
	 * @date 2018年8月9日上午11:27:35
	 */
	@Override
	public RpcRespIce refreshRedisTransType(String organIds, Current __current) {
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			if (StringUtils.isBlank(organIds)) {
				// 没有传organIds就全量更新
				// 得到运输类型全部organId
				List<Integer> organIdList = mtTransTypeInfoMapper.getTransTypeOrganIdAllList();
				if (!AppUtils.isNotEmpty(organIdList)) {
					XxlJobLogger.log("没有查到运送费类型信息");
					return rsp;
				}
				int num = 0;
				for (Integer organId : organIdList) {
					try {
						//查询数据库该项目下的运送类型信息
						List<TransTypeIce> transTypeIceList = mtTransTypeInfoService.getTransTypeListByOrganId(organId);
						//删除缓存该项目下已有的位置信息
						transTypeRedisCache.delTransTypeRedis(organId);
						//批量插入改项目下从数据库查到的项目
						Map<String, List<TransTypeIce>> map = mtTransTypeInfoService.getFleetRangeGroupMaps(transTypeIceList);
						transTypeRedisCache.addTransTypeRedis(organId, map);
						num++;
					} catch (Exception e) {
						XxlJobLogger.log("transTypeJobHandler Exception", e);
					}
				}
				XxlJobLogger.log("更新运送类型--------" + num + "条信息");
			}else {
				List<Integer> organIdList = AppUtils.str2Integer(organIds);
				for (Integer organId : organIdList) {
					// 查询数据库该organId下的运送类型信息
					List<TransTypeIce> transTypeIceList = mtTransTypeInfoService
							.getTransTypeListByOrganId(organId);
					// 删除缓存该项目下已有的位置信息
					transTypeRedisCache.delTransTypeRedis(organId);
					// 批量插入改项目下从数据库查到的项目
					Map<String, List<TransTypeIce>> map = mtTransTypeInfoService
							.getFleetRangeGroupMaps(transTypeIceList);
					transTypeRedisCache.addTransTypeRedis(organId, map);
				}
			}
		} catch (Exception e) {
			logger.error("refreshRedisTransType", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
		}
		return rsp;
	}

	/**
	 * @discription 删除导入批次表和中间表创建时间超过90天的数据
	 * @author zhangyang@segimail.com       
	 * @created 2018年10月9日 上午9:24:37      
	 * @param __current
	 * @return     
	 */
	@Override
	public RpcRespIce delTransTypeImpTable(Current __current) {
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			Date date = DateUtil.addDateDay(new Date(), -90);
			mtImpBatchMsgInfoService.deleteImpTableInfo(date);
		} catch (Exception e) {
			logger.error("delTransTypeImpTable", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(e.toString());
		}
		return rsp;
	}

	/**
	 * @discription 将创建时间超过7天 状态还不是成功的导入批次状态改为失效
	 * @author zhangyang@segimail.com       
	 * @created 2018年10月9日 上午9:25:08      
	 * @param __current
	 * @return     
	 */
	@Override
	public RpcRespIce updateTransTypeImpBatchMsg(Current __current) {
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			Date date = DateUtil.addDateDay(new Date(), -7);
			mtImpBatchMsgInfoService.updateImpBatchMsgInfoStatus(date, "4");
		} catch (Exception e) {
			logger.error("updateTransTypeImpBatchMsg", e);
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(e.toString());
		}
		return rsp;
	}

}
