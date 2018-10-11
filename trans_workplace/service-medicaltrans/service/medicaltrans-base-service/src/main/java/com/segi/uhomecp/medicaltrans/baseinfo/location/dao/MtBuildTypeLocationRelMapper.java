package com.segi.uhomecp.medicaltrans.baseinfo.location.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocationRel;

/**
 * 
 * Title: MtBuildLocationRelMapper.java    
 * @Description: 位置与空间关系自定义接口
 * @author dengdong@segimail.com       
 * @created 2018年5月9日 下午4:10:26
 */
public interface MtBuildTypeLocationRelMapper {
	
	/**
	 * @discription 批量新增位置与物理空间关系
	 * @author dengdong@segimail.com       
	 * @created 2018年3月23日 下午2:51:00     
	 * @param buildId
	 */
	public void insertBatchMtBuildLocationRel(@Param("list")List<MtBuildLocationRel> tBuildLocationRelList);
}