package com.segi.uhomecp.report.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocation;
import com.segi.uhomecp.medicaltrans.baseinfo.location.service.impl.PinYinUtil;
import com.segi.uhomecp.medicaltrans.cache.MtLocationGrabRedisCache;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;

import segi.medicaltrans.location.common.MtLocationInfoIce;

@ContextConfiguration(locations = { "classpath:spring/spring-common.xml" })
@RunWith(JUnit4ClassRunner.class)
public class SpringTestBase {
	
	@Autowired
	private MtLocationGrabRedisCache mtLocationGrabRedisCache;
	
	@Test
	public void test1() {
		mtLocationGrabRedisCache.getLocationByOrganAndLocationIdRedis(2,100000029);
	}
	
	/*@Test
	public void test1() {
		Map<String,Map<Integer,List<MtBuildLocation>>> bulidMap = new HashMap<>();
		//key:floorId
		Map<Integer,List<MtBuildLocation>> floorMap = null;
		//位置(科室)list
		List<MtBuildLocation> locationList = null;
		
		//从缓存中查位置数据(总数据)
		List<MtBuildLocation> resultRedis = mtLocationGrabRedisCache.getLocationByOrganRedis(1);
		for(MtBuildLocation location :resultRedis){
			floorMap = bulidMap.get(location.getBuildId()+"_"+location.getSid());
			if(floorMap==null){
				floorMap = new HashMap<>();
				bulidMap.put(location.getBuildId()+"_"+location.getSid(), floorMap);
			}
			locationList = floorMap.get(location.getFloorId());
			if(locationList==null){
				locationList = new ArrayList<>();
				floorMap.put(location.getFloorId(), locationList);
			}
			locationList.add(location);
		}
		System.out.println(bulidMap);
	}*/
	/*@Autowired
	private MtPositManagerService mtPositManagerService;
	
	@Autowired
	private MtBuildService mtBuildService;
	
	@Test
	public void test1() {
		try {
			mtPositManagerService.deleteMtBuild(1);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2() {
		try {
			mtPositManagerService.deleteMtBuild(1);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}*/
}
