package com.segi.uhomecp.medicaltrans.baseinfo.transtype.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import page.RpcPageIce;
import segi.datacachesvr.queryInfo.Organ;
import segi.datacachesvr.queryInfo.TOrganInfo;
import segi.medicaltrans.base.transtype.TransTypeBaseIce;
import segi.medicaltrans.base.transtype.TransTypeDetailIce;
import segi.medicaltrans.base.transtype.TransTypeIce;
import segi.medicaltrans.base.transtype.TransTypeRetIce;
import segi.medicaltrans.base.transtype.TransTypeRetPageIce;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.base.transtype.model.MtTransType;
import com.segi.uhomecp.medicaltrans.base.transtype.model.MtTransTypeCriteria;
import com.segi.uhomecp.medicaltrans.base.transtype.service.MtTransTypeService;
import com.segi.uhomecp.medicaltrans.baseinfo.transtype.dao.MtTransTypeInfoMapper;
import com.segi.uhomecp.medicaltrans.baseinfo.transtype.dto.MtTransTypeParentDto;
import com.segi.uhomecp.medicaltrans.baseinfo.transtype.enums.TransTypeStatusEnum;
import com.segi.uhomecp.medicaltrans.baseinfo.transtype.service.MtTransTypeInfoService;
import com.segi.uhomecp.medicaltrans.cache.TransTypeRedisCache;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.constant.MtConstant.MtDictType;
import com.segi.uhomecp.medicaltrans.constant.MtSeqContants;
import com.segi.uhomecp.utils.DateUtil;
import com.segi.uhomecp.utils.SeqContants;
import com.segi.uhomecp.web.PageUtils;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.interfaces.InvocationHandler;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;

@Service
public class MtTransTypeInfoServiceImpl implements MtTransTypeInfoService{
	
	public static final Logger logger = LoggerFactory.getLogger(MtTransTypeInfoServiceImpl.class);

	@Autowired
	public MtTransTypeService mtTransTypeService;
	
	@Autowired
	public MtTransTypeInfoMapper mtTransTypeInfoMapper;
	
	@Autowired
	public TransTypeRedisCache transTypeRedisCache;
	
	/**
	 * 
	 * 类描述: 新增运输类型
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月22日 上午10:31:37
	 */
	@Override
	public Integer saveTransType(TransTypeDetailIce transTypeDetailIce) {
		MtTransType mtTransType = BeanCopierUtils.copyProperties(transTypeDetailIce, MtTransType.class, true);
		//查询一级物业id
		TOrganInfo organInfo = CommonServiceUtils.getTopOrganByOrganID(Integer.valueOf(transTypeDetailIce.getOrganId()));
		if (organInfo == null) {
			throw new RuntimeException("查询组织机构信息失败");
		}
		//创建主键
		Integer transTypeId = SeqContants.getSequnces(MtSeqContants.MT_TRANS_TYPE_ID_SEQ).intValue();
		mtTransType.setGroupOrganId(organInfo.getOrganId());
		//设置主键
		mtTransType.setTransTypeId(transTypeId);
		//设置创建时间 修改时间
		Date date = new Date();
		mtTransType.setCreateDate(date);
		mtTransType.setUpdateDate(date);
		//设置创建人 修改人
		Integer userId = Integer.valueOf(transTypeDetailIce.getCreateBy());
		mtTransType.setCreateBy(userId);
		mtTransType.setUpdateBy(userId);
		// 存数据库
		Integer TypeId = mtTransTypeService.insert(mtTransType);
		// 添加到redis缓存
		TransTypeIce transTypeIce  = new TransTypeIce();
		BeanCopierUtils.copyProperties(mtTransType, transTypeIce, true);
		ResultInfo resultInfo = transTypeRedisCache.addOrUpdateTranstType(mtTransType.getOrganId()
				, transTypeIce.getTransTypeParentCode(), transTypeIce);
		if (resultInfo == null || !resultInfo.getIsSucc()) {
			throw new RuntimeException("添加到redis缓存出错");
		}
		return TypeId;
	}

	/**
	 * 
	 * 类描述: 通过主键查找运输类型
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月22日 上午10:31:41
	 */
	@Override
	public TransTypeDetailIce queryTransTypeByTransTypeId(String transTypeId) {
		//创建查询模板
		MtTransTypeCriteria example = new MtTransTypeCriteria();
		MtTransTypeCriteria.Criteria criteria = example.createCriteria();
		criteria.andTransTypeIdEqualTo(Integer.valueOf(transTypeId));
		criteria.andStatusNotEqualTo(MtConstant.TRANS_TYPE_DEL_STATUS);
		List<MtTransType> transTypeList = mtTransTypeService.selectByExample(example);
		MtTransType mtTransType = new MtTransType();
		mtTransType.setTransTypeId(Integer.valueOf(transTypeId));
		mtTransType.setStatus(MtConstant.TRANS_TYPE_DEL_STATUS);
		MtTransType transType = mtTransTypeInfoMapper.queryMtTransType(mtTransType);
		if (transTypeList.isEmpty()) {
			return null;
		}
//		MtTransType transType = transTypeList.get(0);
		//查询字典表
        Map<String, String> transTypeMap = CommonServiceUtils.getDetailNameMap(MtDictType.MT_TRANS_TYPE);
        TransTypeDetailIce typeIce = BeanCopierUtils.copyProperties(transType, TransTypeDetailIce.class, true);
        typeIce.setTransTypeParentCodeName(transTypeMap.get(MtDictType.MT_TRANS_TYPE + Constant.SPLIT_COMMA + typeIce.getTransTypeParentCode()));
        Organ organ = CommonServiceUtils.getOrganByID(transType.getOrganId());
        if (organ == null) {
			throw new RuntimeException("查询组织机构信息失败");
		}
        typeIce.setOrganName(organ.getOrganName());
        // 设置状态名称
        typeIce.setStatusName(StringUtils.isEmpty(transType.getStatus()) ? "" : TransTypeStatusEnum.getName(transType.getStatus()));
        return typeIce;
	}

	/**
	 * 
	 * 类描述: 判断新增名称和编码是否同一下项目内重复
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月22日 上午10:41:28
	 */
	@Override
	public Boolean judgeTransType(TransTypeDetailIce transTypeDetailIce) {
		MtTransType transType = BeanCopierUtils.copyProperties(transTypeDetailIce, MtTransType.class, true);
		Integer typeCount = mtTransTypeInfoMapper.judgeTransType(transType);
		return typeCount == 0 ? true : false;
	}

	/**
	 * 类描述: 修改判断名称和编码是否同一下项目内重复
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月22日 上午10:42:32
	 * "
	 */
	@Override
	public Boolean judgeTransType(TransTypeDetailIce transTypeDetailIce, String transTypeId) {
		MtTransType transType = BeanCopierUtils.copyProperties(transTypeDetailIce, MtTransType.class, true);
		Integer typeCount = mtTransTypeInfoMapper.judgeUpdateTransType(transType);
		return typeCount == 0 ? true : false;
	}

	/**
	 * 
	 * 类描述: 运输类型修改
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月22日 下午5:36:29
	 */
	@Override
	public void updateTransType(TransTypeIce transTypeIce) {
		MtTransType mtTransType = BeanCopierUtils.copyProperties(transTypeIce, MtTransType.class, true);
		// 修改时间
		mtTransType.setUpdateDate(new Date());
		// 修改数据库
		mtTransTypeService.updateByPrimaryKeySelective(mtTransType);
		// 修改运输类型redis数据
		ResultInfo resultInfo = transTypeRedisCache.addOrUpdateTranstType(mtTransType.getOrganId(), mtTransType.getTransTypeParentCode(), transTypeIce);
		if (resultInfo == null || !resultInfo.getIsSucc()) {
			throw new RuntimeException("添加到redis缓存出错");
		}
//		int a = 1/0;
	}

	/**
	 * 
	 * 类描述: 查询运送类型分页
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月23日 上午11:19:21
	 */
	@Override
	public TransTypeRetPageIce transTypePage(TransTypeIce transTypeIce) {
		List<TransTypeIce> transTypeIceList = new ArrayList<TransTypeIce>();
		TransTypeRetPageIce result = new TransTypeRetPageIce();
		//创建查询模板
		MtTransTypeCriteria example = new MtTransTypeCriteria();
		MtTransTypeCriteria.Criteria criteria = example.createCriteria();
		int pageNo = MtConstant.INIT_PAGE_NO;
		if (StringUtils.isNotEmpty(transTypeIce.getPageNo())) {
			pageNo = Integer.parseInt(transTypeIce.getPageNo());
		}
		int pageLength = MtConstant.DEFAULT_PAGE_LENGTH;
		if (StringUtils.isNotEmpty(transTypeIce.getPageLength())) {
			pageLength = Integer.parseInt(transTypeIce.getPageLength());
		}
		//添加organId查询条件
		criteria.andOrganIdEqualTo(Integer.valueOf(transTypeIce.getOrganId()));
		if (StringUtils.isNotEmpty(transTypeIce.getTransTypeParentCode())) {
			//判断运送大类是否为空
			criteria.andTransTypeParentCodeEqualTo(transTypeIce.getTransTypeParentCode());
		}
		if (StringUtils.isNotEmpty(transTypeIce.getTransTypeName())) {
			//判断运送类型名称是否为空
			criteria.andTransTypeNameLike(transTypeIce.getTransTypeName()
					+ Constant.SPLIT_PER);
		}
		//只查询没有被删除的
		criteria.andStatusNotEqualTo(MtConstant.TRANS_TYPE_DEL_STATUS);
		// 排序运送类型主键
		example.setOrderByClause(MtConstant.TRANS_TYPE_ID_DESC);
		PageList<MtTransType> transTypeList = mtTransTypeService.selectByExampleWithRowbounds(
				example,PageUtils.getPageBoundsByPageNo(pageNo, pageLength));
		if (AppUtils.isNotEmpty(transTypeList)) {
			// 查询组织名称
			Organ organ = CommonServiceUtils.getOrganByID(Integer.valueOf(transTypeIce.getOrganId()));
            //查询字典表
            Map<String, String> transTypeMap = CommonServiceUtils.getDetailNameMap(MtDictType.MT_TRANS_TYPE);
            for (MtTransType mtTransType : transTypeList) {
				TransTypeIce TransTypeReturnIce = BeanCopierUtils
						.copyProperties(mtTransType, TransTypeIce.class, true);
				//设置运送大类名称
				TransTypeReturnIce.setTransTypeParentCodeName(
						transTypeMap.get(MtDictType.MT_TRANS_TYPE + Constant.SPLIT_COMMA + TransTypeReturnIce.getTransTypeParentCode()));
				// 设置状态
				TransTypeReturnIce.setStatusName(StringUtils.isEmpty(TransTypeReturnIce.getStatus()) ?
						"" : TransTypeStatusEnum.getName(TransTypeReturnIce.getStatus()));
				// 设置组织结构名称
				TransTypeReturnIce.setOrganName(organ == null ? null : organ.getOrganName());
				transTypeIceList.add(TransTypeReturnIce);
			}
			 result.setTransTypeListIce(transTypeIceList);
	         result.setPaginator(com.segi.uhomecp.wh.common.utils
	        		 .PageUtils.paginator2RpcPageIce(transTypeList.getPaginator()));
		} 
		else {
			result.setTransTypeListIce(transTypeIceList);
			result.setPaginator(new RpcPageIce());
		}
		return result;
	}

	/**
	 * 
	 * 类描述: 查询运送类型列表
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月23日 下午4:54:50
	 */
	@Override
	public TransTypeRetPageIce transTypeList(TransTypeIce transTypeIce) {
		TransTypeRetPageIce transTypeRetPageIce = new TransTypeRetPageIce();
		// 查询redis缓存
		MtTransTypeParentDto typeParent = transTypeRedisCache.getTransTypeParent(
				 Integer.valueOf(transTypeIce.getOrganId()), transTypeIce.getTransTypeParentCode());
		if (StringUtils.isNotBlank(transTypeIce.getStatus()) && transTypeIce.getStatus().endsWith("1")) {
			// 如果入参传了状态1 返回返回启用停用状态的运送类型
			if (typeParent != null && typeParent.getTransTypetMap() != null) {
				// 判断redis缓存有没有取得数据
				List<TransTypeIce> mtTransTypeList = new ArrayList<TransTypeIce>();
				for (Entry<String, TransTypeIce> entry : typeParent.getTransTypetMap().entrySet()) {
					if (null != entry.getValue() && StringUtils.isNotEmpty(entry.getValue().getStatus()) &&
							 ((TransTypeStatusEnum.STATUS_NORMAL.getCode().equals(entry.getValue().getStatus())) || 
								(TransTypeStatusEnum.STATUS_STOP.getCode().equals(entry.getValue().getStatus())))) {
						// 只添加启用状态的运输类型
						mtTransTypeList.add(entry.getValue());
					}
				}
				transTypeRetPageIce.setTransTypeListIce(mtTransTypeList);
			} 
			return transTypeRetPageIce;
		}
		
		if (typeParent != null && typeParent.getTransTypetMap() != null) {
			// 判断redis缓存有没有取得数据
			List<TransTypeIce> mtTransTypeList = new ArrayList<TransTypeIce>();
			for (Entry<String, TransTypeIce> entry : typeParent.getTransTypetMap().entrySet()) {
				if (null != entry.getValue() && StringUtils.isNotEmpty(entry.getValue().getStatus())
						&& TransTypeStatusEnum.STATUS_NORMAL.getCode().equals(entry.getValue().getStatus())) {
					// 只添加启用状态的运输类型
					mtTransTypeList.add(entry.getValue());
				}
			}
			transTypeRetPageIce.setTransTypeListIce(mtTransTypeList);
		} 
		return transTypeRetPageIce;
	}

	/**
	 * 
	 * 类描述: 运输类型查询全部列表
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月28日 下午8:31:53
	 */
	@Override
	public List<TransTypeRetIce> transTypeAllList(TransTypeIce transTypeIce) {
		// 查询redis缓存
		Map<String, MtTransTypeParentDto> typeParentMap = transTypeRedisCache.getTransTypeDepRedis(Integer.valueOf(transTypeIce.getOrganId()));
		List<TransTypeRetIce> transTypeParentList = new ArrayList<TransTypeRetIce>();
		if (typeParentMap != null) {
			// 判断redis缓存有没有取得数据
			Map<String, String> transTypeCodeMap = CommonServiceUtils
					.getDetailNameMap(MtDictType.MT_TRANS_TYPE);
			for (Entry<String, String> entry : transTypeCodeMap.entrySet()) {
				TransTypeRetIce typeRetIce = new TransTypeRetIce();
				String code = entry.getKey().substring(14);
				typeRetIce.setTransTypeParentCode(code);
				typeRetIce.setTransTypeParentCodeName(entry.getValue());
				if (typeParentMap.get(code) != null && typeParentMap.get(code).getTransTypetMap() != null) {
					List<TransTypeBaseIce> transTypeList = new ArrayList<TransTypeBaseIce>();
					Map<String, TransTypeIce> typetMap = typeParentMap
							.get(code).getTransTypetMap();
					for (Entry<String, TransTypeIce> transType : typetMap
							.entrySet()) {
						if (null != transType.getValue() && StringUtils.isNotEmpty(transType.getValue().getStatus())
								&& TransTypeStatusEnum.STATUS_NORMAL.getCode().equals(transType.getValue().getStatus())) {
							// 只添加启用状态的运输类型
							transTypeList.add(BeanCopierUtils.copyProperties(transType.getValue(), TransTypeBaseIce.class, true));
						}
						//transTypeList.add(transType.getValue());
					}
					typeRetIce.setTransTypeList(transTypeList);
				}
				transTypeParentList.add(typeRetIce);
			}
			return transTypeParentList;
		}
		return transTypeParentList;
	}

	/**
	 * 
	 * 类描述: 修改运输类型状态
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月28日 下午8:27:54
	 */
	@Override
	public void updateStatusTransType(TransTypeIce typeIce) {
		MtTransType transType = new MtTransType();
		transType.setTransTypeId(Integer.valueOf(typeIce.getTransTypeId()));
		transType.setStatus(typeIce.getStatus());
		transType.setUpdateBy(Integer.valueOf(typeIce.getUpdateBy()));
		transType.setUpdateDate(DateUtil.parseStringToDateYYMMDDHHmmss(typeIce.getUpdateDate()));
		// 逻辑删除数据库
		mtTransTypeService.updateByPrimaryKeySelective(transType);
		if (MtConstant.TRANS_TYPE_DEL_STATUS.endsWith(typeIce.getStatus())) {
			// 删除redis
			transTypeRedisCache.delTransType(Integer.valueOf(typeIce.getOrganId()), typeIce.getTransTypeParentCode(), typeIce);
		}else {
			typeIce.setStatus(typeIce.getStatus());
			// 修改redis
			ResultInfo resultInfo = transTypeRedisCache.addOrUpdateTranstType(Integer.valueOf(typeIce.getOrganId())
					, typeIce.getTransTypeParentCode(), typeIce);
			if (resultInfo == null || !resultInfo.getIsSucc()) {
				throw new RuntimeException("添加到redis缓存出错");
			}
		}
	}

	/**
	 * 
	 * 类描述: 按运输类型大类对运输类型进行分组
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年4月1日 下午12:57:41
	 */
	@Override
	public Map<String, List<TransTypeIce>> getFleetRangeGroupMaps(
			List<TransTypeIce> transTypeList) {
		InvocationHandler<String, TransTypeIce> handler = 
				new InvocationHandler<String, TransTypeIce>() {
					@Override
					public String invoke(TransTypeIce obj) {
						return obj.getTransTypeParentCode();
					}		
		};
		return AppUtils.listGroup2Map(transTypeList, handler, 
				TransTypeIce.class, TransTypeIce.class);
	}

	/**
	 * 
	 * @param 根据项目id查询运输类型
	 */
	@Override
	public List<TransTypeIce> getTransTypeListByOrganId(Integer organId) {
		//创建查询模板
		MtTransTypeCriteria example = new MtTransTypeCriteria();
		MtTransTypeCriteria.Criteria criteria = example.createCriteria();
		//添加organId查询条件
		criteria.andOrganIdEqualTo(Integer.valueOf(organId));
		//只查询没有被删除的
		criteria.andStatusNotEqualTo(MtConstant.TRANS_TYPE_DEL_STATUS);
		// 排序运送类型主键
		example.setOrderByClause(MtConstant.TRANS_TYPE_ID_DESC);
		List<MtTransType> list = mtTransTypeService.selectByExample(example);
		if (AppUtils.isNotEmpty(list)) {
			return BeanCopierUtils.copyList2List(list, TransTypeIce.class, true);
		}
		return null;
	}

	/**
	 * 
	 * @param 根据项目id 运送大类 查询运输类型
	 */
	@Override
	public List<TransTypeIce> getTransTypeList(Integer organId,
			String transTypeParentCode) {
		//创建查询模板
		MtTransTypeCriteria example = new MtTransTypeCriteria();
		MtTransTypeCriteria.Criteria criteria = example.createCriteria();
		//添加organId查询条件
		criteria.andOrganIdEqualTo(organId);
		//添加运送大类查询条件
		criteria.andTransTypeParentCodeEqualTo(transTypeParentCode);
		List<MtTransType> mtTransTypeList = mtTransTypeService.selectByExample(example);
		if (AppUtils.isNotEmpty(mtTransTypeList)) {
			return BeanCopierUtils.copyList2List(mtTransTypeList, TransTypeIce.class, true);
		}
		return null;
	}
}
