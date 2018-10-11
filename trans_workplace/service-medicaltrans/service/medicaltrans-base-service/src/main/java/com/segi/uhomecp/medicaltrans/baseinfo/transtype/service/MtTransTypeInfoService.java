package com.segi.uhomecp.medicaltrans.baseinfo.transtype.service;

import java.util.List;
import java.util.Map;

import segi.medicaltrans.base.transtype.TransTypeDetailIce;
import segi.medicaltrans.base.transtype.TransTypeIce;
import segi.medicaltrans.base.transtype.TransTypeRetIce;
import segi.medicaltrans.base.transtype.TransTypeRetPageIce;

public interface MtTransTypeInfoService {

	/**
	 * 
	 * 类描述: 新增运输类型
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月22日 上午10:21:08
	 */
	Integer saveTransType(TransTypeDetailIce transTypeDetailIce);
	
	/**
	 * 
	 * 类描述: 通过主键查找运输类型
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月22日 上午10:28:04
	 */
	TransTypeDetailIce queryTransTypeByTransTypeId(String transTypeId);
	
	/**
	 * 
	 * 类描述: 新增判断名称和编码是否同一下项目内重复
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月22日 上午10:41:28
	 */
	Boolean judgeTransType(TransTypeDetailIce transTypeDetailIce);
	
	/**
	 * 
	 * 类描述: 修改判断名称和编码是否同一下项目内重复
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月22日 上午10:42:32
	 */
	Boolean judgeTransType(TransTypeDetailIce transTypeDetailIce, String transTypeId);

	/**
	 * 
	 * 类描述: 运输类型修改
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月22日 下午5:36:29
	 */
	void updateTransType(TransTypeIce transTypeIce);

	/**
	 * 
	 * 类描述: 运输类型分页
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月23日 上午11:06:00
	 */
	TransTypeRetPageIce transTypePage(TransTypeIce transTypeIce);

	/**
	 * 
	 * 类描述: 查询运送类型列表
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月23日 下午4:54:17
	 */
	TransTypeRetPageIce transTypeList(TransTypeIce transTypeIce);

	/**
	 * 
	 * 类描述: 运输类型查询全部列表
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月28日 下午8:27:54
	 */
	List<TransTypeRetIce> transTypeAllList(TransTypeIce transTypeIce);

	/**
	 * 
	 * 类描述: 运输类型改变状态
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年3月30日 下午3:42:45
	 */
	void updateStatusTransType(TransTypeIce transTypeIce);
	
	/**
	 * 
	 * 类描述: 按运输类型大类对运输类型进行分组
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年4月1日 下午12:56:17
	 */
	public Map<String ,List<TransTypeIce>> getFleetRangeGroupMaps(List<TransTypeIce> transTypeList);

	/**
	 * 
	 * @param 根据项目id查询运输类型
	 */
	public List<TransTypeIce> getTransTypeListByOrganId(Integer organId);
	
	/**
	 * 
	 * @param 根据项目id 运送大类 查询运输类型
	 */
	public List<TransTypeIce> getTransTypeList(Integer organId, String transTypeParentCode);
}
