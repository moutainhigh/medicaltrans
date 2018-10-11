package com.segi.uhomecp.medicaltrans.baseinfo.userposit.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocation;
import com.segi.uhomecp.medicaltrans.base.posit.bean.MtCurUserPosit;
import com.segi.uhomecp.medicaltrans.base.userposit.model.MtUserPosit;
import com.segi.uhomecp.medicaltrans.baseinfo.userposit.dto.MtUserLocationDto;
import com.segi.uhomecp.medicaltrans.baseinfo.userposit.dto.MtUserPositDto;

public interface MtUserpositInfoService {

	/**
	 * 
	 * 类描述: 根据当前登录用户查询最后一次定位和时间
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月3日 上午11:18:53
	 */
	/*public MtUserPosit getLastPositByUser(Integer organId, Integer userId);*/

	/**
	 * 
	 * 类描述: 上传当前用户位置信息
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月4日 下午5:09:53
	 */
	public void updateLocatePosit(MtUserPositDto dto);
	
	/**
	 * 人员位置列表分页查询(3D图)
	 * @param mtUserLocationDto
	 * @return
	 */
	public PageList<MtUserLocationDto> queryUserLocationPage(MtUserLocationDto mtUserLocationDto);
	
	/**
	 * 人员位置列表总数查询(3D图)
	 * @param mtUserLocationDto
	 * @return
	 */
	public List<MtBuildLocation> querySumUserLocation(MtUserLocationDto mtUserLocationDto);
	
	/**
	 * 人员位置查询（2D图）
	 * @param mtUserLocationDto
	 * @return
	 */
	public Map<String, Map<Integer,List<MtBuildLocation>>> queryUserPlaLocation(MtUserLocationDto mtUserLocationDto);
	
	/**
	 * 根据userIdList查询人员信息
	 * @param organIdList
	 * @return
	 */
	public List<MtUserPositDto> queryUserPositInfo(List<Integer> userIdList);
	
	/**
	 * 修改人员状态和未完成任务数
	 * @param mtCurUserPositList
	 * @throws Exception 
	 */
	public void updateUserPositInfo(Integer organId, List<Integer> userIds, Short unTaskNum, Short runTaskNum,String executeDate,String locationId) throws Exception;
	
	/**
	 * 人员最新位置
	 * @param mtCurUserPosit
	 */
	public void updateUserNewLocation(MtCurUserPosit mtCurUserPosit);
	
	/**
	 * 将楼层按楼层详情返回的顺序号重新排序
	 * @return
	 */
	public List<Map<Integer,List<MtBuildLocation>>> sortFloorMap(Map<Integer,List<MtBuildLocation>> map); 
}
