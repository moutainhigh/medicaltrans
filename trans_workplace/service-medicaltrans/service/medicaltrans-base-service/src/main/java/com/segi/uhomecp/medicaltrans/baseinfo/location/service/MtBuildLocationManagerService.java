package com.segi.uhomecp.medicaltrans.baseinfo.location.service;


import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocation;
import com.segi.uhomecp.medicaltrans.baseinfo.location.dto.MtBuildLocationDto;


public interface MtBuildLocationManagerService {
	
	/**
	 * @discription 保存位置信息
	 * @author dengdong@segimail.com       
	 * @created 2018年5月9日 下午7:56:34     
	 * @param mtBuildLocationDto
	 * @return
	 * @throws Exception 
	 */
	public ResultInfo saveMtBuildLocation(MtBuildLocationDto mtBuildLocationDto) throws Exception;
	
	/**
	 * @discription 编辑位置信息
	 * @author dengdong@segimail.com       
	 * @created 2018年5月9日 下午7:58:00     
	 * @param mtBuildLocationDto
	 * @return
	 */
	public ResultInfo updateMtBuildLocation(MtBuildLocationDto mtBuildLocationDto);
	
	/**
	 * 删除位置信息
	 * @param mtBuildLocationDto
	 * @return
	 */
	public ResultInfo deleteMtBuildLocation(MtBuildLocationDto mtBuildLocationDto);
	
	/**
	 * 根据楼层分页展示位置
	 * @param mtBuildLocationDto
	 * @return
	 */
	public PageList<MtBuildLocation> queryLocationPageByFloorId(MtBuildLocationDto mtBuildLocationDto);
	
	/**
	 * 展示位置列表
	 * @param mtBuildLocationDto
	 * @return
	 */
	public List<MtBuildLocation> queryLocationListByFloorId(MtBuildLocationDto mtBuildLocationDto);
	
	/**
	 * 根据位置idlist和项目查位置信息list（缓存）
	 * @param locationIdList
	 * @return
	 */
	public List<MtBuildLocation> getLocationInfoByRefIdList(Integer organId,List<Integer> locationIdList);
	
	/**
	 * 根据位置idlist查位置信息list（数据库）
	 * @param locationIdList
	 * @return
	 */
	public List<MtBuildLocation> getLocationInfoBylocationIdList(List<Integer> locationIdList);
	
	/**
	 * 根据项目ID查位置信息list(数据库)
	 */
	public List<MtBuildLocation> getLocationInfoByOrganId(Integer organId);
	
	/**
	 * 根据项目id和mac地址查位置信息
	 * @param organId
	 * @param mac
	 * @return
	 */
	public MtBuildLocation getLocationInfoByMac(Integer organId,String mac);
	
	public PageList<MtBuildLocation> queryLocationPageByOrgName(MtBuildLocationDto mtBuildLocationDto);
	
	public PageList<MtBuildLocation> queryLocationPageByOrgNameDefault(MtBuildLocationDto mtBuildLocationDto);
	
	public ResultInfo refreshRedisLocaiton();
	
	public ResultInfo refreshRedisUserLocaiton();
}
