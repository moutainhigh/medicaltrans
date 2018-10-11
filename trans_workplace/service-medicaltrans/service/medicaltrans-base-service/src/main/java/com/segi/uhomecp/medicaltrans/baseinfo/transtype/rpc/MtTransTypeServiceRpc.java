package com.segi.uhomecp.medicaltrans.baseinfo.transtype.rpc;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import resp.RpcRespIce;
import segi.medicaltrans.base.transtype.TransTypeAllListIce;
import segi.medicaltrans.base.transtype.TransTypeDetailIce;
import segi.medicaltrans.base.transtype.TransTypeDetailReturnIce;
import segi.medicaltrans.base.transtype.TransTypeIce;
import segi.medicaltrans.base.transtype.TransTypeOprGuideIce;
import segi.medicaltrans.base.transtype.TransTypeOprGuideRetIce;
import segi.medicaltrans.base.transtype.TransTypeRetIce;
import segi.medicaltrans.base.transtype.TransTypeRetPageIce;
import segi.medicaltrans.base.transtype._TransTypeServiceIceDisp;
import Ice.Current;

import com.segi.uhomecp.medicaltrans.baseinfo.transtype.enums.TransTypeStatusEnum;
import com.segi.uhomecp.medicaltrans.baseinfo.transtype.service.MtTransTypeInfoService;
import com.segi.uhomecp.medicaltrans.cache.TransTypeRedisCache;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.enums.TransTypeEnum;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.CheckRestParams;
import com.segi.uhomecp.wh.common.utils.DateUtil;

/**
 * 
 * 描述: 运输类型
 * 创建人: liuyi@sige.com   
 * 创建时间: 2018年3月26日 下午2:54:44
 */
@Component
public class MtTransTypeServiceRpc extends _TransTypeServiceIceDisp{

	private static final long serialVersionUID = -3611648811977402836L;

	public static final Logger logger = LoggerFactory.getLogger(MtTransTypeServiceRpc.class);

	@Autowired
	public MtTransTypeInfoService mtTransTypeInfoService;
	
	@Autowired
	public TransTypeRedisCache transTypeRedisCache;
	
	/**
	 * 
	 * 类描述: 新增运送类型
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月22日 上午10:12:31
	 */
	@Override
	public RpcRespIce saveTransType(TransTypeDetailIce transTypeDetailIce, Current __current) {
		RpcRespIce result = new RpcRespIce();
		result.code = RpcError.SUCCESS.getCode();
		result.message = RpcError.SUCCESS.getMessage();
		try {
			//判断新增判断名称和编码是否同一下项目内重复
			Boolean bool = mtTransTypeInfoService.judgeTransType(transTypeDetailIce);
			if (bool) {
				mtTransTypeInfoService.saveTransType(transTypeDetailIce);
			}else {
				result.setCode(RpcError.FAIL.getCode());
				result.setMessage("名称或编码在同一项目内已重复");
			}
		} catch (Exception e) {
			logger.error("saveTransType", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("新增失败");
		}
		return result;
	}

	/**
	 * 
	 * 类描述: 运送类型修改
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月22日 下午5:34:22
	 */
	@Override
	public RpcRespIce updateTransType(TransTypeDetailIce transTypeDetailIce,
			Current __current) {
		RpcRespIce result = new RpcRespIce();
		result.code = RpcError.SUCCESS.getCode();
		result.message = RpcError.SUCCESS.getMessage();
		try {
			TransTypeIce typeIce = mtTransTypeInfoService.queryTransTypeByTransTypeId(transTypeDetailIce.getTransTypeId());
			if (typeIce == null) {
				//判断运送类型是否已被删除
				result.setCode(RpcError.FAIL.getCode());
				result.setMessage("运送类型已被删除");
				return result;
			}
			//修改判断名称和编码是否同一下项目内重复
			Boolean bool = mtTransTypeInfoService.judgeTransType(transTypeDetailIce, transTypeDetailIce.getTransTypeId());
			if (bool) {
				TransTypeIce transTypeIce = BeanCopierUtils.copyProperties(transTypeDetailIce, TransTypeIce.class, true);
				if (StringUtils.isNotEmpty(transTypeDetailIce.getTransTypeParentCode()) 
						&& StringUtils.isNotEmpty(typeIce.getTransTypeParentCode())
						&& !transTypeDetailIce.getTransTypeParentCode().equals(typeIce.getTransTypeParentCode())){
						// 判断运输大类是否被修改   修改了 就删除原先reids运输大类里的运输类型  在新的运输大类中添加
					// 删除redis
					transTypeRedisCache.delTransType(Integer.valueOf(typeIce.getOrganId()), typeIce.getTransTypeParentCode(), transTypeIce);
				}
				mtTransTypeInfoService.updateTransType(transTypeDetailIce);
			}else {
				result.setCode(RpcError.FAIL.getCode());
				result.setMessage("名称或编码在同一项目内已重复");
			}
		} catch (Exception e) {
			logger.error("updateTransType", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("修改失败");
		}
		return result;
	}

	/**
	 * 
	 * 类描述: 运送类型停用、启用、删除
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月23日 上午9:50:17
	 */
	@Override
	public RpcRespIce updateStatusTransType(TransTypeIce transTypeIce,
			Current __current) {
		RpcRespIce result = new RpcRespIce();
		result.code = RpcError.SUCCESS.getCode();
		String statusName = TransTypeStatusEnum.getName(transTypeIce.getStatus());
		result.setMessage(statusName + "成功");
		try {
			TransTypeIce transType = mtTransTypeInfoService
					.queryTransTypeByTransTypeId(transTypeIce.getTransTypeId());
			if (transType == null) {
				//判断运送类型是否已被删除
				result.setCode(RpcError.FAIL.getCode());
				result.setMessage("运送类型没找到");
				return result;
			}
			if (MtConstant.TRANS_TYPE_DEL_STATUS.equals(transType.getStatus())) {
				//判断运送类型是否已被删除
				result.setCode(RpcError.FAIL.getCode());
				result.setMessage("运送类型已被删除");
				return result;
			}
			if (transTypeIce.getStatus().equals(transType.getStatus())) {
				//判断运送类型是否已被改变
				result.setCode(RpcError.FAIL.getCode());
				result.setMessage("运送类型状态已被改变");
				return result;
			}
			if (MtConstant.TRANS_TYPE_DEL_STATUS.equals(transTypeIce.getStatus())) {
				if (!MtConstant.TRANS_TYPE_STOP_STATUS.equals(transType.getStatus())) {
					result.setCode(RpcError.FAIL.getCode());
					result.setMessage("删除失败！ 只有停用状态才能删除");
					return result;
				}
				// 判断有没有使用中的类型
//				RpcRespIce respIce = MtCommonServiceUtils.judgeTransType(transType.getOrganId(), transType.getTransTypeId());
//				if (RpcError.SUCCESS.getCode().equals(respIce.code) 
//						&& respIce.getData().equals(MtConstant.HAVE_USE_TRANS_TYPE)) {
//					// 判断是否有运送中的运送单
//					result.setCode(RpcError.FAIL.getCode());
//					result.setMessage("删除失败！ 存在运送中的运送单");
//					return result;
//				}
//				if (RpcError.FAIL.getCode().equals(respIce.code)) {
//					result.setCode(RpcError.FAIL.getCode());
//					result.setMessage("删除失败！ 查询运送单失败");
//					return result;
//				}
			}
			transType.setUpdateBy(transTypeIce.getUpdateBy());
			transType.setStatus(transTypeIce.getStatus());
			BeanCopierUtils.copyProperties(transType, transTypeIce, true);
			transTypeIce.setUpdateDate(DateUtil.nowDateToStringYYMMDDHHmmss());
			mtTransTypeInfoService.updateStatusTransType(transTypeIce);
		} catch (Exception e) {
			logger.error("updateStatusTransType", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage(statusName +"失败");
		}
		return result;
	}

	/**
	 * 
	 * 类描述: 运送类型详情
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月23日 上午10:01:35
	 */
	@Override
	public TransTypeDetailReturnIce queryTransType(TransTypeIce transTypeIce,
			Current __current) {
		TransTypeDetailReturnIce result = new TransTypeDetailReturnIce();
		result.code = RpcError.SUCCESS.getCode();
		result.message = RpcError.SUCCESS.getMessage();
		try {
			//查询运送类型详情
			TransTypeDetailIce transType = mtTransTypeInfoService
					.queryTransTypeByTransTypeId(transTypeIce.getTransTypeId());
			if (transType == null || MtConstant
					.TRANS_TYPE_DEL_STATUS.equals(transType.getStatus())) {
				//判断运送类型是否已被删除
				result.setCode(RpcError.FAIL.getCode());
				result.setMessage("运送类型已被删除");
				return result;
			}
			result.setTransTypeDetailIce(transType);	
		} catch (Exception e) {
			logger.error("queryTransType", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("查询运送类型详情失败");
		}
		return result;
	}

	/**
	 * 
	 * 类描述: 运送类型分页
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月23日 上午10:56:23
	 */
	@Override
	public TransTypeRetPageIce transTypePage(TransTypeIce transTypeIce,
			Current __current) {
		TransTypeRetPageIce result = new TransTypeRetPageIce();
		try {
			// 查询运送类型分页
			result = mtTransTypeInfoService.transTypePage(transTypeIce);
			result.code = RpcError.SUCCESS.getCode();
			result.message = RpcError.SUCCESS.getMessage();
		} catch (Exception e) {
			logger.error("transTypePage", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("查询运送类型分页失败");
		}
		return result;
	}
	
	/**
	 * 
	 * 类描述: 运送类型列表展示
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月23日 下午4:48:51
	 */
	@Override
	public TransTypeRetPageIce transTypeList(TransTypeIce transTypeIce,
			Current __current) {
		TransTypeRetPageIce result = new TransTypeRetPageIce();
		try {
			// 查询运送类型列表
			result = mtTransTypeInfoService.transTypeList(transTypeIce);
			result.code = RpcError.SUCCESS.getCode();
			result.message = RpcError.SUCCESS.getMessage();
		} catch (Exception e) {
			logger.error("transTypeList", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("查询运送类型列表失败");
		}
		return result;
	}

	/**
	 * 
	 * 类描述: 运送类型查询全部列表
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月28日 下午8:22:10
	 */
	@Override
	public TransTypeAllListIce transTypeAllList(TransTypeIce transTypeIce,
			Current __current) {
		TransTypeAllListIce result = new TransTypeAllListIce();
		try {
			// 查询运送类型列表
			List<TransTypeRetIce> transTypeAllList = mtTransTypeInfoService.transTypeAllList(transTypeIce);
			result.setTransTypeList(transTypeAllList);
			result.code = RpcError.SUCCESS.getCode();
			result.message = RpcError.SUCCESS.getMessage();
		} catch (Exception e) {
			logger.error("TransTypeAllList", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("运送类型查询全部列表失败");
		}
		return result;
	}

	/**
	 * @Title: queryOprGuide 
	 * @Description: 查询操作指引 
	 * @author liuyi@segimail.com 
	 * @date 2018年9月30日下午3:01:23
	 */
	@Override
	public TransTypeOprGuideRetIce queryOprGuide(TransTypeIce transTypeIce,
			Current __current) {
		TransTypeOprGuideRetIce result = new TransTypeOprGuideRetIce();
		result.code = RpcError.SUCCESS.getCode();
		result.message = RpcError.SUCCESS.getMessage();
		try {
			//查询运送类型详情
			TransTypeDetailIce transType = mtTransTypeInfoService
					.queryTransTypeByTransTypeId(transTypeIce.getTransTypeId());
			if (transType == null || MtConstant
					.TRANS_TYPE_DEL_STATUS.equals(transType.getStatus())) {
				//判断运送类型是否已被删除
				result.setCode(RpcError.FAIL.getCode());
				result.setMessage("运送类型已被删除");
				return result;
			}
			result.setTransTypeOprGuideIce(BeanCopierUtils.copyProperties(transType, TransTypeOprGuideIce.class, true));
		} catch (Exception e) {
			logger.error("queryTransType", e);
			result.setCode(RpcError.FAIL.getCode());
			result.setMessage("查询运送类型详情失败");
		}
		return result;
	}

}
