//package com.segi.uhomecp.medicaltrans.cache;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.TreeMap;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Component;
//
//import com.segi.uhomecp.common.model.ResultInfo;
//import com.segi.uhomecp.medicaltrans.baseinfo.posit.dto.MtBuildDto;
//import com.segi.uhomecp.medicaltrans.baseinfo.posit.dto.MtBuildFloorDto;
//import com.segi.uhomecp.medicaltrans.baseinfo.posit.dto.MtBuildHouseDto;
//import com.segi.uhomecp.medicaltrans.constant.MedicalTransRedisConstant;
//import com.segi.uhomecp.medicaltrans.seriable.KryoSeriableStringUtils;
//import com.segi.uhomecp.redis.cluster.SegiRedisClusterBuilder;
//import com.segi.uhomecp.wh.common.constant.Constant;
//
///**
// * 位置redis缓存
// * @author Jimmy
// * 2018-3-21
// */
//@Component
//public class PositRedisCache {
//	
//	@Resource(name="segiRedisCluster")
//	private SegiRedisClusterBuilder segiRedisClusterBuilder;
//	
//	/**
//	 * 删除项目下所有位置信息
//	 * @param organId
//	 */
//	public void delBuildRedis(Integer organId, Integer buildId) {
//		String key = MedicalTransRedisConstant.POSIT + organId;
//		this.segiRedisClusterBuilder.getSegiRedisCluster().hdel(key, String.valueOf(buildId));
//	}
//	
//	/**
//	 * 通过项目Id获取所有位置信息
//	 * @param organId
//	 * @return
//	 */
//	public Map<Integer, MtBuildDto> getBuildDepRedis(Integer organId) {
//		String key = MedicalTransRedisConstant.POSIT + organId;
//		Map<String, String> valueMap = this.segiRedisClusterBuilder.getSegiRedisCluster().hgetAll(key);
//		if (valueMap == null || valueMap.isEmpty()) {
//			return null;
//		}
//		Map<Integer, MtBuildDto> resultMap = new TreeMap<Integer, MtBuildDto>();
//		MtBuildDto build = null;
//		for (String val : valueMap.values()) {
//			build = KryoSeriableStringUtils.deSerialObject(val, MtBuildDto.class);
//			if (build != null) {
//				resultMap.put(build.getBuildId(), build);
//			}
//		}
//		return resultMap;
//	}
//	
//	/**
//	 * 获取某个项目下所有的科室
//	 * @param organId
//	 * @param isAllStatus true:所有状态； false:只查询有效状态
//	 * @return
//	 */
//	public Map<Integer, MtBuildHouseDto> getHouse(Integer organId, boolean isAllStatus) {
//		if (organId == null) {
//			return null;
//		}
//		Map<Integer, MtBuildDto> buildMap = this.getBuildDepRedis(organId);
//		if (buildMap == null || buildMap.isEmpty()) {
//			return null;
//		}
//		Map<Integer, MtBuildHouseDto> resultMap = new TreeMap<Integer, MtBuildHouseDto>();
//		Map<String, MtBuildFloorDto> floorMap = null;
//		Map<String, MtBuildHouseDto> houseMap = null;
//		for (MtBuildDto build : buildMap.values()) {
//			floorMap = build.getFloorMap();
//			if (floorMap == null || floorMap.isEmpty()) {
//				continue;
//			}
//			for (MtBuildFloorDto floor : floorMap.values()) {
//				if (floor == null) {
//					continue;
//				}
//				floor.setBuild(build);
//				houseMap = floor.getHouseMap();
//				if (houseMap == null || houseMap.isEmpty()) {
//					continue;
//				}
//				for (MtBuildHouseDto house : houseMap.values()) {
//					if (house == null) {
//						continue;
//					}
//					house.setBuildFloor(floor); //所属楼层
//					if (isAllStatus) {
//						resultMap.put(house.getHouseId(), house);
//					} else if (!isAllStatus && Constant.STATUS_CD_NORMAL.equals(house.getStatus())) {
//						resultMap.put(house.getHouseId(), house);
//					}
//				}
//			}
//		}
//		return resultMap;
//	}
//	
//	/**
//	 * 获取楼栋信息
//	 * @param organId 项目Id
//	 * @param buildId 楼栋Id
//	 * @return
//	 */
//	public MtBuildDto getBuildDepRedis(Integer organId, Integer buildId) {
//		if (organId == null || buildId == null) {
//			return null;
//		}
//		String key = MedicalTransRedisConstant.POSIT + organId;
//		String val = null;
//		try {
//			val = this.segiRedisClusterBuilder.getSegiRedisCluster().hget(key, String.valueOf(buildId));
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		if (val == null || "".equals(val)) {
//			return null;
//		}
//		return KryoSeriableStringUtils.deSerialObject(val, MtBuildDto.class);
//	}
//	
//	/**
//	 * 获取楼层信息
//	 * @param organId
//	 * @param buildId
//	 * @param floorId
//	 * @return
//	 */
//	public MtBuildFloorDto getFloorDeptRedis(Integer organId, Integer buildId, Integer floorId) {
//		MtBuildDto findBuild = this.getBuildDepRedis(organId, buildId);
//		if (findBuild == null) {
//			return null;
//		}
//		Map<String, MtBuildFloorDto> floorMap = findBuild.getFloorMap();
//		if (floorMap == null) {
//			return null;
//		}
//		return floorMap.get(String.valueOf(floorId));
//	}
//	
//	/**
//	 * 深度（全量）刷新楼栋Redis缓存
//	 * @param build
//	 */
//	public void hsetBuildDep(MtBuildDto build) {
//		if (build == null || build.getOrganId() == null || build.getBuildId() == null) {
//			return;
//		}
//		//key:常量_项目Id， field楼栋Id，value:楼栋对象
//		String key = MedicalTransRedisConstant.POSIT + build.getOrganId();
//		String value = KryoSeriableStringUtils.seriaObject(build);
//		this.segiRedisClusterBuilder.getSegiRedisCluster().hset(key, String.valueOf(build.getBuildId()), value);
//	}
//	
//	/**
//	 * 通过organId和houseid查询对应的科室全名
//	 * @param organId
//	 * @param houseId
//	 * @return 如"眼科"获取"主体楼-3F-眼科"
//	 */
//	public Map<Integer, String> getWholeHouseNameMap(Integer organId, Integer... houseId) {
//		Map<Integer, MtBuildHouseDto> houseMap = this.getHouse(organId, true);
//		if (houseMap == null || houseMap.isEmpty()) {
//			return null;
//		}
//		Map<Integer, String> resultMap = new HashMap<Integer, String>();
//		MtBuildHouseDto houseTemp = null;
//		for (Integer houseIdTemp : houseId) {
//			houseTemp = houseMap.get(houseIdTemp);
//			if (houseTemp == null) {
//				continue;
//			}
//			if (houseTemp.getBuildFloor() == null) { //所属楼层为空，则只返回科室名称
//				resultMap.put(houseIdTemp, houseTemp.getHouseName());
//				continue;
//			}
//			if (houseTemp.getBuildFloor().getBuild() == null) {//所属楼栋为空， 则只返回 楼层-科室 名称
//				resultMap.put(houseIdTemp, houseTemp.getBuildFloor().getFloorNum() + "F-" + houseTemp.getHouseName());
//				continue;
//			}
//			resultMap.put(houseIdTemp, houseTemp.getBuildFloor().getBuild().getBuildName() + "-" +
//					houseTemp.getBuildFloor().getFloorNum() + "F-" + houseTemp.getHouseName());
//		}
//		return resultMap;
//	}
//	
//	/**
//	 * 只保存或更新楼栋对象
//	 * @param build
//	 */
//	public ResultInfo addOrUpdateBuildSimple(MtBuildDto build) {
//		ResultInfo resultInfo = new ResultInfo();
//		if (build == null || build.getOrganId() == null || build.getBuildId() == null) {
//			resultInfo.setIsSucc(false);
//			resultInfo.setMessage("organId或buildId为空，刷新缓存失败");
//			return resultInfo;
//		}
//		MtBuildDto findBuild = this.getBuildDepRedis(build.getOrganId(), build.getBuildId());
//		if (findBuild == null) { //缓存中没有， 需要全量刷新
//			this.hsetBuildDep(build);
//		} else {//存在楼栋的数据，需要更新楼栋的数据再刷新
//			findBuild.setBuildName(build.getBuildName());
//			findBuild.setBuildFloorCount(build.getBuildFloorCount());
//			findBuild.setBuildFloorMinNum(build.getBuildFloorMinNum());
//			findBuild.setRemark(build.getRemark());
//			findBuild.setStatus(build.getStatus());
//			findBuild.setUpdateBy(build.getUpdateBy());
//			findBuild.setUpdateDate(build.getUpdateDate());
//			
//			this.hsetBuildDep(findBuild);
//		}
//		resultInfo.setIsSucc(true);
//		return resultInfo;
//	}
//	
//	/**
//	 * 新增或修改楼层信息（单纯的楼层信息）
//	 * @param floor
//	 */
//	public ResultInfo addOrUpdateFloorSimple(Integer organId, MtBuildFloorDto floor) {
//		ResultInfo resultInfo = new ResultInfo();
//		if (floor == null || organId == null || floor.getBuildId() == null || floor.getFloorId() == null) {
//			resultInfo.setIsSucc(false);
//			resultInfo.setMessage("organId或buildId为空，刷新缓存失败");
//			return resultInfo;
//		}
//		MtBuildDto findBuild = this.getBuildDepRedis(organId, floor.getBuildId());
//		if (findBuild == null) { //缓存中没有， 需要全量刷新
//			resultInfo.setIsSucc(false);
//			resultInfo.setMessage("缓存中不存在organId=" + organId
//					+ ",buildId=" + floor.getBuildId() + "的数据.");
//			return resultInfo;
//		}
//		Map<String, MtBuildFloorDto> floorMap = findBuild.getFloorMap();
//		if (floorMap == null) { //说明该楼栋没有楼层信息
//			floorMap = new HashMap<String, MtBuildFloorDto>();
//			findBuild.setFloorMap(floorMap);
//		}
//		MtBuildFloorDto newFloor = floorMap.get(String.valueOf(floor.getFloorId()));
//		if (newFloor == null) { //说明该楼栋没有楼层信息
//			floorMap.put(String.valueOf(floor.getFloorId()), floor);
//			this.hsetBuildDep(findBuild);
//			resultInfo.setIsSucc(true);
//			return resultInfo;
//		}
//		
//		//执行到这里说明是修改以前的楼层信息，但该楼层下还有科室信息
//		//修改楼层信息
//		newFloor.setFloorNum(floor.getFloorNum());
//		newFloor.setFloorPicUrl(floor.getFloorPicUrl());
//		newFloor.setStatus(floor.getStatus());
//		newFloor.setBuildId(floor.getBuildId());
//		newFloor.setRemark(floor.getRemark());
//		newFloor.setUpdateBy(floor.getUpdateBy());
//		newFloor.setUpdateDate(floor.getUpdateDate());
//		this.hsetBuildDep(findBuild);
//		
//		resultInfo.setIsSucc(true);
//		return resultInfo;
//	}
//	
//	/**
//	 * 新增或修改科室信息（单纯的科室信息）
//	 * @param house
//	 * @return
//	 */
//	public ResultInfo addOrUpdateHouse(Integer organId, MtBuildHouseDto house) {
//		ResultInfo resultInfo = new ResultInfo();
//		if (house == null 
//				|| organId == null 
//				|| house.getBuildId() == null 
//				|| house.getFloorId() == null 
//				|| house.getHouseId() == null) {
//			resultInfo.setIsSucc(false);
//			resultInfo.setMessage("organId或buildId或houseId为空，刷新缓存失败");
//			return resultInfo;
//		}
//		MtBuildDto findBuild = this.getBuildDepRedis(organId, house.getBuildId());
//		if (findBuild == null) { //缓存中没有， 需要全量刷新
//			resultInfo.setIsSucc(false);
//			resultInfo.setMessage("缓存中不存在organId=" + organId
//					+ ",buildId=" + house.getBuildId() + "的数据,缓存失败.");
//			return resultInfo;
//		}
//		Map<String, MtBuildFloorDto> floorMap = findBuild.getFloorMap();
//		if (floorMap == null || !floorMap.containsKey(String.valueOf(house.getFloorId()))) { //说明该楼栋没有楼层信息
//			resultInfo.setIsSucc(false);
//			resultInfo.setMessage("缓存中不存在organId=" + organId
//					+ ",buildId=" + house.getBuildId() 
//					+ ",floorId=" + house.getFloorId() + "的数据,缓存失败.");
//			return resultInfo;
//		}
//		MtBuildFloorDto newFloor = floorMap.get(String.valueOf(house.getFloorId()));
//		Map<String, MtBuildHouseDto> houseMap = newFloor.getHouseMap();
//		if (houseMap == null) { //说明该楼层下没有科室
//			houseMap = new HashMap<String, MtBuildHouseDto>();
//			newFloor.setHouseMap(houseMap);;
//		}
//		MtBuildHouseDto newHouse = houseMap.get(String.valueOf(house.getHouseId()));
//		if (newHouse == null) {
//			houseMap.put(String.valueOf(house.getHouseId()), house);
//		} else {
//			newHouse.setFloorNum(house.getFloorNum());
//			newHouse.setFloorPositX(house.getFloorPositX());
//			newHouse.setFloorPositY(house.getFloorPositY());
//			newHouse.setHouseName(house.getHouseName());
//			newHouse.setLocateType(house.getLocateType());
//			newHouse.setMac(house.getMac());
//			newHouse.setQrcode(house.getQrcode());
//			newHouse.setRemark(house.getRemark());
//			newHouse.setStatus(house.getStatus());
//			newHouse.setUpdateBy(house.getUpdateBy());
//			newHouse.setUpdateDate(house.getUpdateDate());
//		}
//		this.hsetBuildDep(findBuild);
//		resultInfo.setIsSucc(true);
//		return resultInfo;
//	}
//}
