package com.segi.uhomecp.medicaltrans.baseinfo.userposit.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import segi.datacachesvr.queryInfo.BuildDetail;
import segi.datacachesvr.queryInfo.FloorBriefInfo;
import segi.datacachesvr.queryInfo.FloorDetailInfo;
import segi.datacachesvr.queryInfo.TOrganInfo;
import segi.datacachesvr.queryInfo.UnitDetailInfo;
import segi.datacachesvr.queryInfo.UserInfo;
import segi.medicaltrans.common.userposit.UserLocationTaskNumParam;
import segi.medicaltrans.common.userstatus.UserStatusDetailRspIce;
import segi.whcommon.push.MessageIce;
import segi.whcommon.push.PushServiceIcePrx;

import com.alibaba.fastjson.JSONObject;
import com.esotericsoftware.minlog.Log;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocation;
import com.segi.uhomecp.medicaltrans.base.posit.bean.MtCurUserPosit;
import com.segi.uhomecp.medicaltrans.base.userposit.model.MtUserPosit;
import com.segi.uhomecp.medicaltrans.base.userposit.model.MtUserPositCriteria;
import com.segi.uhomecp.medicaltrans.base.userposit.model.MtUserPositCriteria.Criteria;
import com.segi.uhomecp.medicaltrans.base.userposit.service.MtUserPositService;
import com.segi.uhomecp.medicaltrans.baseinfo.userposit.dao.MtUserPositInfoMapper;
import com.segi.uhomecp.medicaltrans.baseinfo.userposit.dto.MtUserLocationDto;
import com.segi.uhomecp.medicaltrans.baseinfo.userposit.dto.MtUserPositDto;
import com.segi.uhomecp.medicaltrans.baseinfo.userposit.enums.UserPositStatusEnum;
import com.segi.uhomecp.medicaltrans.baseinfo.userposit.service.MtUserpositInfoService;
import com.segi.uhomecp.medicaltrans.cache.CurUserLocationRedisCache;
import com.segi.uhomecp.medicaltrans.cache.MtLocationGrabRedisCache;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.constant.MtSeqContants;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.medicaltrans.utils.MtSpaceManageServiceUtils;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.utils.DateUtil;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.utils.SeqContants;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;

import IceExt.IceClientUtil;
import resp.RpcRespIce;

@Service("mtUserpositInfoService")
public class MtUserpositInfoServiceImpl implements MtUserpositInfoService{

	@Autowired
	private MtUserPositService mtUserPositService;
	
	@Autowired
	public CurUserLocationRedisCache curUserLocationRedisCache;
	
	@Autowired
	private MtLocationGrabRedisCache mtLocationGrabRedisCache;

	@Autowired
	private MtUserPositInfoMapper mtUserPositInfoMapper;
	
	private PushServiceIcePrx getPushServiceIcePrx() {
		return IceClientUtil.getServicePrxByClass(PushServiceIcePrx.class);
	}
	
	public static final Logger logger = LoggerFactory.getLogger(MtUserpositInfoServiceImpl.class);
	
	/**
	 * 
	 * 类描述: 根据当前登录用户查询最后一次定位和时间
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月3日 上午11:18:53
	 */
	/*@Override
	public MtUserPosit getLastPositByUser(Integer organId, Integer userId) {
		MtUserPosit mtUserPosit = new MtUserPosit();
		MtUserPositCriteria example = new MtUserPositCriteria();
		MtUserPositCriteria.Criteria criteria = example.createCriteria();
		criteria.andOrganIdEqualTo(organId);
		criteria.andUserIdEqualTo(userId);
		List<MtUserPosit> list = mtUserPositService.selectByExample(example);
		if (AppUtils.isNotEmpty(list)) {
			mtUserPosit = list.get(0);
		}
		return mtUserPosit;
	}*/

	/**
	 * 
	 * 类描述: 上传当前用户位置信息
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年5月4日 下午5:09:53
	 */
	@Override
	public void updateLocatePosit(MtUserPositDto dto) {
		// 更新缓存信息
		MtCurUserPosit properties = BeanCopierUtils.copyProperties(dto, MtCurUserPosit.class, true);
		updateUserNewLocation(properties);
		// 更新数据库信息
		/*MtUserPosit userPosit = getLastPositByUser(dto.getOrganId(), dto.getUserId());
		if (userPosit.getUserPositId() == null) {
			// 没有查出来新增
			userPosit = BeanCopierUtils.copyProperties(dto, MtUserPosit.class, true);
			//创建主键
			Integer userPositId = SeqContants.getSequnces(MtSeqContants.MT_USER_POSIT_ID_SEQ).intValue();
			userPosit.setUserPositId(userPositId);
			userPosit.setHouseId(dto.getLocationId());
			userPosit.setUpdateBy(dto.getUserId());
			userPosit.setUnTaskNum((short)0);
			userPosit.setRunTaskNum((short)0);
			userPosit.setUpdateDate(new Date());
			userPosit.setStatus(UserPositStatusEnum.STATUS_LEISURE.getCode());
			mtUserPositService.insert(userPosit);
		} else {
			// 查出来了修改
			userPosit.setHouseId(dto.getLocationId());
			userPosit.setUpdateBy(dto.getUserId());
			userPosit.setUpdateDate(new Date());
			mtUserPositService.updateByPrimaryKeySelective(userPosit);
		}*/
	}
	
	/**
	 * 人员位置列表分页查询(3D图)
	 */
	@Override
	public PageList<MtUserLocationDto> queryUserLocationPage(MtUserLocationDto mtUserLocationDto) {
		int pageNo = Integer.parseInt(mtUserLocationDto.getPageNo());//当前页数
		int pageLength = Integer.parseInt(mtUserLocationDto.getPageLength());//每页数
		
		//从缓存中查人员数据(总数据)
		List<MtCurUserPosit> resultRedis = curUserLocationRedisCache.getUserLocationByOrganRedis(mtUserLocationDto.getOrganId());
		logger.info("==================>查出的人员位置：resultRedis: resultRedis:{}", 
				FastjsonUtils.toJsonString(resultRedis));
		//根据条件筛选总数据
		List<MtCurUserPosit> userLocationListResult = this.filterMtCurUserPosit(mtUserLocationDto,resultRedis);
		logger.info("==================>筛选的人员位置：userLocationListResult: userLocationListResult:{}", 
				FastjsonUtils.toJsonString(userLocationListResult));
		//将MtCurUserPosit转换成MtUserLocationDto
		List<MtUserLocationDto> userLocationList = this.translateMtCurUserPositList(userLocationListResult);
		
		int pageTotal = userLocationList.size();//总数据量
		//构造分页
		Paginator paginator = new Paginator(pageNo,pageLength,pageTotal);
		int totalPages = 0;//总页数
		if((pageTotal % pageLength) == 0){
			totalPages = pageTotal / pageLength;
		}else{
			totalPages = pageTotal / pageLength + 1;
		}
		if(pageNo>totalPages){
			return null;
		}
		int pageStartRow = 0;//每页开始数
		int pageEndRow = 0;//每页结束数
		if (pageNo * pageLength < pageTotal) {// 判断是否为最后一页  
	      pageEndRow = pageNo * pageLength;  
	      pageStartRow = pageEndRow - pageLength;  
	    } else {  
	      pageEndRow = pageTotal;  
	      pageStartRow = pageLength * (totalPages - 1);  
	    } 
		List<MtUserLocationDto> result = userLocationList.subList(pageStartRow, pageEndRow);
		PageList<MtUserLocationDto> pagelist = new PageList<MtUserLocationDto>(result,paginator);
		return pagelist;
	}
	
	
	/**
	 * 将MtCurUserPosit转换成MtUserLocationDto
	 * @param userLocationList
	 * @return
	 */
	public List<MtUserLocationDto> translateMtCurUserPositList(List<MtCurUserPosit> userLocationList){
		List<MtUserLocationDto> resutl = new ArrayList<MtUserLocationDto>();
		if(userLocationList!=null && userLocationList.size()>0){
			for(MtCurUserPosit mtCurUserPosit:userLocationList){
				if(mtCurUserPosit==null){
					continue;
				}
				MtUserLocationDto mtUserLocationDto = new MtUserLocationDto();
				mtUserLocationDto = BeanCopierUtils.copyProperties(mtCurUserPosit, MtUserLocationDto.class, true);
				
				//拼接总体名称
				StringBuffer blendName = new StringBuffer();
				//1、查出楼栋名
				if(mtCurUserPosit.getBuildId()==null){
					continue;
				}
				BuildDetail buildDetail = MtSpaceManageServiceUtils.queryBuildByID(mtCurUserPosit.getBuildId());
				if(buildDetail!=null){
					mtUserLocationDto.setBuildName(buildDetail.getName());
					blendName.append(buildDetail.getName());
				}
				//2、查出单元名
				if(mtCurUserPosit.getSid()!=null){
					UnitDetailInfo unitDetailInfo = MtSpaceManageServiceUtils.queryUnitDetailByID(mtCurUserPosit.getSid());
					if(unitDetailInfo!=null){
						mtUserLocationDto.setSidName(unitDetailInfo.getUnitName());
						blendName.append("-").append(unitDetailInfo.getUnitName());
					}
				}
				//3、查出楼层名
				if(mtCurUserPosit.getFloorId()==null){
					continue;
				}
				FloorBriefInfo floorBriefInfo = MtSpaceManageServiceUtils.queryFloorBriefByID(mtCurUserPosit.getFloorId());
				if(floorBriefInfo!=null){
					mtUserLocationDto.setFloorName(floorBriefInfo.getFloorName());
					blendName.append("-").append(floorBriefInfo.getFloorName());
				}
				//4、查出位置（科室）名
				MtBuildLocation redisLocation = mtLocationGrabRedisCache.getLocationByOrganAndLocationIdRedis(mtCurUserPosit.getOrganId(),mtCurUserPosit.getLocationId());
				if(redisLocation!=null){
					mtUserLocationDto.setLoactionName(redisLocation.getLocationName());
					blendName.append("-").append(redisLocation.getLocationName());
					mtUserLocationDto.setBlendName(blendName.toString());
					//6、查出人员的x、y坐标
					mtUserLocationDto.setFloorPositX(String.valueOf(redisLocation.getFloorPositX()));
					mtUserLocationDto.setFloorPositY(String.valueOf(redisLocation.getFloorPositY()));
				}
				//5、查出状态名(注意是人员的状态)
				//2018.10.10,状态在空闲的时候要展示是否是下班状态--DD
				mtUserLocationDto.setStatusName(UserPositStatusEnum.getName(mtUserLocationDto.getStatus()));
				if(UserPositStatusEnum.STATUS_LEISURE.getCode().equals(mtUserLocationDto.getStatus())){
					UserStatusDetailRspIce userStatusDetailRspIce =MtCommonServiceUtils.getStatusByUser(String.valueOf(mtUserLocationDto.getOrganId()),String.valueOf(mtUserLocationDto.getUserId()));
					if(RpcError.SUCCESS.getCode().equals(userStatusDetailRspIce.getCode()) && "0".equals(userStatusDetailRspIce.getUserStatusDetailIce().getStatus())){
						mtUserLocationDto.setStatusName(UserPositStatusEnum.getName(UserPositStatusEnum.STATUS_LEAVE.getCode()));
					}
				}
				resutl.add(mtUserLocationDto);
			}
		}
		return resutl;
	}
	
	/**
	 * 根据条件筛选人员位置MtCurUserPosit
	 */
	public List<MtCurUserPosit> filterMtCurUserPosit(MtUserLocationDto mtUserLocationDto,List<MtCurUserPosit> resultRedis){
		List<MtCurUserPosit> userLocationList = new ArrayList<MtCurUserPosit>();
		if(!AppUtils.isNotEmpty(resultRedis)){
			return null;
		}
		//条件筛选交集
		for(MtCurUserPosit mtCurUserPosit:resultRedis){
			//名字的模糊查询
			if(StringUtils.isNotEmpty(mtUserLocationDto.getUserName()) 
					&& mtCurUserPosit.getUserName().indexOf(mtUserLocationDto.getUserName())==-1){
				continue;
			}
			//员工工号模糊查询
			if(StringUtils.isNotEmpty(mtUserLocationDto.getUserWorkNo()) 
					&& mtCurUserPosit.getUserWorkNo().indexOf(mtUserLocationDto.getUserWorkNo())==-1){
				continue;
			}
			//楼栋
			if(mtUserLocationDto.getBuildId()!=null 
					&& !(mtUserLocationDto.getBuildId().equals(mtCurUserPosit.getBuildId()))){
				continue;
			}
			//单元
			if(mtUserLocationDto.getSid()!=null 
					&& !(mtUserLocationDto.getSid().equals(mtCurUserPosit.getSid()))){
				continue;
			}
			//楼层
			if(mtUserLocationDto.getFloorId()!=null 
					&& !(mtUserLocationDto.getFloorId().equals(mtCurUserPosit.getFloorId()))){
				continue;
			}
			//人员状态
			if(StringUtils.isNotEmpty(mtUserLocationDto.getStatus())
					&& !(mtUserLocationDto.getStatus().equals(mtCurUserPosit.getStatus()))){
				continue;
			}
			userLocationList.add(mtCurUserPosit);
		}
		
		//屏蔽条件筛选并集
		/*//当所有的条件都为空的时候，查的就是总数据
		if(StringUtils.isEmpty(mtUserLocationDto.getUserName()) && StringUtils.isEmpty(mtUserLocationDto.getUserWorkNo()) && mtUserLocationDto.getBuildId()==null && mtUserLocationDto.getFloorId()==null){
			userLocationList.addAll(resultRedis);
		}else{
			Map<Integer,MtCurUserPosit> userLocationMap = new HashMap<Integer,MtCurUserPosit>();
			for(MtCurUserPosit mtCurUserPosit:resultRedis){
				//名字的模糊查询
				if(StringUtils.isNotEmpty(mtUserLocationDto.getUserName()) && mtCurUserPosit.getUserName().indexOf(mtUserLocationDto.getUserName())!=-1){
					userLocationMap.put(mtCurUserPosit.getUserId(), mtCurUserPosit);
				}
				//员工工号模糊查询
				if(StringUtils.isNotEmpty(mtUserLocationDto.getUserWorkNo()) && mtCurUserPosit.getUserWorkNo().indexOf(mtUserLocationDto.getUserWorkNo())!=-1){
					userLocationMap.put(mtCurUserPosit.getUserId(), mtCurUserPosit);
				}
				//楼栋
				if(mtUserLocationDto.getBuildId()!=null && mtUserLocationDto.getBuildId().equals(mtCurUserPosit.getBuildId())){
					//单元
					if(mtUserLocationDto.getSid()!=null && mtUserLocationDto.getSid().equals(mtCurUserPosit.getSid())){
						userLocationMap.put(mtCurUserPosit.getUserId(), mtCurUserPosit);
					}else if(mtUserLocationDto.getSid()==null){
						userLocationMap.put(mtCurUserPosit.getUserId(), mtCurUserPosit);
					}
				}
				//楼层
				if(mtUserLocationDto.getFloorId()!=null && mtUserLocationDto.getFloorId().equals(mtCurUserPosit.getFloorId())){
					userLocationMap.put(mtCurUserPosit.getUserId(), mtCurUserPosit);
				}
			}
			//生成最终的数据list
			if(userLocationMap!=null && userLocationMap.size()>0){
				for(MtCurUserPosit val : userLocationMap.values()){
					userLocationList.add(val);
				}
			}
		}*/
		return userLocationList;
	}
	
	/**
	 * 根据条件筛选位置MtCurUserPosit
	 */
	public List<MtBuildLocation> filterMtBuildLocation(MtUserLocationDto mtUserLocationDto,List<MtBuildLocation> resultRedis){
		if(!AppUtils.isNotEmpty(resultRedis)){
			return null;
		}
		List<MtBuildLocation> mtBuildLocationList = new ArrayList<MtBuildLocation>();
		//条件筛选交集
		for(MtBuildLocation mtBuildLocation:resultRedis){
			//失效过滤
			if(!Constant.STATUS_CD_NORMAL.equals(mtBuildLocation.getStatus())){
				continue;
			}
			//楼栋
			if(mtUserLocationDto.getBuildId()!=null 
					&& !(mtUserLocationDto.getBuildId().equals(mtBuildLocation.getBuildId()))){
				continue;
			}
			//单元
			if(mtUserLocationDto.getSid()!=null 
					&& !(mtUserLocationDto.getSid().equals(mtBuildLocation.getSid()))){
				continue;
			}
			//楼层
			if(mtUserLocationDto.getFloorId()!=null 
					&& !(mtUserLocationDto.getFloorId().equals(mtBuildLocation.getFloorId()))){
				continue;
			}
			mtBuildLocationList.add(mtBuildLocation);
		}
		return mtBuildLocationList;
	}
	
	
	/**
	 * 3D立体图查询人员总数量
	 */
	@Override
	public List<MtBuildLocation> querySumUserLocation(MtUserLocationDto mtUserLocationDto) {
		//从缓存中查位置数据(组织机构下总数据)
		List<MtBuildLocation> resultRedis1 = mtLocationGrabRedisCache.getLocationByOrganRedis(mtUserLocationDto.getOrganId());
		//根据条件筛选位置总数据
		List<MtBuildLocation> resultRedisLocaiton = this.filterMtBuildLocation(mtUserLocationDto,resultRedis1);		
		//从缓存中查人员位置数据(组织机构下总数据)
		List<MtCurUserPosit> resultRedis2 = curUserLocationRedisCache.getUserLocationByOrganRedis(mtUserLocationDto.getOrganId());
		//根据条件筛选人员总数据
		List<MtCurUserPosit> resultRedisUser = this.filterMtCurUserPosit(mtUserLocationDto,resultRedis2);
		
		if(AppUtils.isNotEmpty(resultRedisLocaiton)){
			for(MtBuildLocation location :resultRedisLocaiton){
				//构造科室-人员的数据结构
				List<MtCurUserPosit> mtCurUserPositList = new ArrayList<>();
				if(AppUtils.isNotEmpty(resultRedisUser)){
					for(MtCurUserPosit mtCurUserPosit:resultRedisUser){
						if(location!=null && location.getLocationId()!=null&& mtCurUserPosit!=null&& mtCurUserPosit.getLocationId()!=null && location.getLocationId().equals(mtCurUserPosit.getLocationId())){
							mtCurUserPositList.add(mtCurUserPosit);
						}
					}
				}
				location.setMtCurUserPositList(mtCurUserPositList);
			}
		}
		
		return resultRedisLocaiton;
	}
	
	/**
	 * 拼接2D图的数据
	 * @param mtUserLocationDto
	 */
	@Override
	public Map<String, Map<Integer,List<MtBuildLocation>>> queryUserPlaLocation(MtUserLocationDto mtUserLocationDto){
		//key：楼栋id_单元id
		Map<String,Map<Integer,List<MtBuildLocation>>> bulidMap = new HashMap<>();
		//key:floorId
		Map<Integer,List<MtBuildLocation>> floorMap = null;
		//位置(科室)list
		List<MtBuildLocation> locationList = null;
		
		//从缓存中查位置数据(组织机构下总数据)
		List<MtBuildLocation> resultRedis1 = mtLocationGrabRedisCache.getLocationByOrganRedis(mtUserLocationDto.getOrganId());
		logger.info("==================>缓存位置数据: resultRedis1:{}", 
				FastjsonUtils.toJsonString(resultRedis1));
		//根据条件筛选位置总数据
		List<MtBuildLocation> resultRedisLocaiton = this.filterMtBuildLocation(mtUserLocationDto,resultRedis1);	
		logger.info("==================>过滤位置数据: resultRedisLocaiton:{}", 
				FastjsonUtils.toJsonString(resultRedisLocaiton));
		//从缓存中查人员位置数据(组织机构下总数据)
		List<MtCurUserPosit> resultRedis2 = curUserLocationRedisCache.getUserLocationByOrganRedis(mtUserLocationDto.getOrganId());
		logger.info("==================>缓存人员数据: resultRedis2:{}", 
				FastjsonUtils.toJsonString(resultRedis2));
		//根据条件筛选人员总数据
		List<MtCurUserPosit> resultRedisUser = this.filterMtCurUserPosit(mtUserLocationDto,resultRedis2);
		logger.info("==================>缓存人员数据: resultRedisUser:{}", 
				FastjsonUtils.toJsonString(resultRedisUser));
				
		if(AppUtils.isNotEmpty(resultRedisLocaiton)){
			for(MtBuildLocation location :resultRedisLocaiton){
				if(location!=null){
					//构造科室-人员的数据结构
					List<MtCurUserPosit> mtCurUserPositList = new ArrayList<>();
					if(AppUtils.isNotEmpty(resultRedisUser)){
						for(MtCurUserPosit mtCurUserPosit:resultRedisUser){
							if(location !=null && location.getLocationId()!=null &&mtCurUserPosit!=null && mtCurUserPosit.getLocationId()!=null && location.getLocationId().equals(mtCurUserPosit.getLocationId())){
								mtCurUserPositList.add(mtCurUserPosit);
							}
						}
					}
					location.setMtCurUserPositList(mtCurUserPositList);
					
					//构造楼栋单元-楼层-科室三层数据结构
					if(location.getSid()!=null){
						floorMap = bulidMap.get(location.getBuildId()+"_"+location.getSid());
						if(floorMap==null){
							floorMap = new HashMap<>();
							bulidMap.put(location.getBuildId()+"_"+location.getSid(), floorMap);
						}
					}else{
						floorMap = bulidMap.get(location.getBuildId().toString());
						if(floorMap==null){
							floorMap = new HashMap<>();
							bulidMap.put(location.getBuildId().toString(), floorMap);
						}
					}
					
					locationList = floorMap.get(location.getFloorId());
					if(locationList==null){
						locationList = new ArrayList<>();
						floorMap.put(location.getFloorId(), locationList);
					}
					locationList.add(location);
				}
			}
		}
		
		return bulidMap;
	}

	/**
	 * 根据userIdList查询人员信息
	 * @author yangyh@segimail.com       
	 * @param organIdList
	 * @return        
	 */
	@Override
	public List<MtUserPositDto> queryUserPositInfo(List<Integer> userIdList) {
		MtUserPositCriteria example = new MtUserPositCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdIn(userIdList);
		return BeanCopierUtils.copyList2List(
				mtUserPositService.selectByExample(example),
				MtUserPositDto.class, true);
	}

	/**
	 * 刷新人员位置
	 */
	@Override
	public void updateUserNewLocation(MtCurUserPosit mtCurUserPosit) {
		logger.info("==================>刷新人员那位置信息前：mtCurUserPosit: mtCurUserPosit:{}", 
				FastjsonUtils.toJsonString(mtCurUserPosit));
		Integer organId = mtCurUserPosit.getOrganId();
		Integer userId = mtCurUserPosit.getUserId();
		Integer locationId = mtCurUserPosit.getLocationId();
		if (null == mtCurUserPosit || null == organId || null == userId || null == locationId) {
			throw new RuntimeException("刷新人员最新位置参数缺失！");
		}
		// 查询位置信息
		MtBuildLocation location = mtLocationGrabRedisCache.getLocationByOrganAndLocationIdRedis(organId, locationId);
		
		if (location != null) {
			mtCurUserPosit.setBuildId(location.getBuildId());
			mtCurUserPosit.setFloorId(location.getFloorId());
			if (location.getSid() != null) {
				mtCurUserPosit.setSid(location.getSid());
			}
		}
		TOrganInfo topOrgan = CommonServiceUtils.getTopOrganByOrganID(organId);
		mtCurUserPosit.setGroupOrganId(null != topOrgan ? topOrgan.getOrganId() : 0);
		UserInfo userInfo = CommonServiceUtils.getCurrentUserInfoByID(userId);
		if (null != userInfo) {
			mtCurUserPosit.setUserName(userInfo.getUserName());
			mtCurUserPosit.setUserWorkNo(userInfo.getJobNumber());
		}
		mtCurUserPosit.setLastUpdateTime(DateUtil.nowDateToStringYYMMDDHHmmss());

		MtCurUserPosit userPosit = curUserLocationRedisCache.getUserLocationByOrganAndUserIdRedis(organId, userId);
		if (null == userPosit) {
			// 新增默认空闲属性
			mtCurUserPosit.setStatus(UserPositStatusEnum.STATUS_LEISURE.getCode());
		} else {
			mtCurUserPosit.setUnTaskNum(userPosit.getUnTaskNum());
			mtCurUserPosit.setRunTaskNum(userPosit.getRunTaskNum());
			mtCurUserPosit.setStatus(userPosit.getStatus());
		}
		// 数据库修改或新增
		/*MtUserPosit mtUserPosit = this.getLastPositByUser(organId, userId);
		List<MtUserPosit> paramList = new ArrayList<MtUserPosit>();
		if (null != mtUserPosit) {
			mtUserPosit.setHouseId(locationId);
			mtUserPosit.setStatus(mtUserPosit.getStatus());
			mtUserPosit.setUpdateDate(new Date());
			mtUserPosit.setUpdateBy(userId);
			paramList.add(mtUserPosit);
			mtUserPositInfoMapper.updateBatchMtUserPosit(paramList);
		} else {
			mtUserPosit = new MtUserPosit();
			mtUserPosit.setOrganId(organId);
			mtUserPosit.setUserId(userId);
			mtUserPosit.setHouseId(locationId);
			mtUserPosit.setStatus(UserPositStatusEnum.STATUS_LEISURE.getCode());
			mtUserPosit.setUpdateDate(new Date());
			mtUserPosit.setUpdateBy(userId);
			paramList.add(mtUserPosit);
			insertMtUserPositList(paramList);
		}*/
		// 缓存修改或新增
		logger.info("==================>刷新人员那位置信息后：mtCurUserPosit: mtCurUserPosit:{}", 
				FastjsonUtils.toJsonString(mtCurUserPosit));
		curUserLocationRedisCache.addOrUpdateUserLocationRedis(mtCurUserPosit);
	}
	
	/** @discription 修改人员状态和未完成任务数
	 * @author yangyh@segimail.com       
	 * @param mtCurUserPositList
	 * @return        
	 * @throws Exception 
	 */
	@Override
	public void updateUserPositInfo(Integer organId, List<Integer> userIds, Short unTaskNum, Short runTaskNum,String executeDate,String locationId) throws Exception {
		logger.info("==================>修改人员项目：organId: organId:{}", 
				organId);
		logger.info("==================>修改人员：userIds: userIds:{}", 
				FastjsonUtils.toJsonString(userIds));
		logger.info("==================>修改人员未完成数的：unTaskNum: unTaskNum:{}", 
				unTaskNum);
		logger.info("==================>修改人员正在完成数的的：runTaskNum: runTaskNum:{}", 
				runTaskNum);
		logger.info("==================>修改人员日期的：executeDate: executeDate:{}", 
				executeDate);
		logger.info("==================>修改人员位置的：executeDate: locationId:{}", 
				locationId);
		if (null == organId || !AppUtils.isNotEmpty(userIds) || null == unTaskNum || null == runTaskNum || StringUtils.isEmpty(executeDate)) {
			throw new RuntimeException("修改人员状态和未完成任务数参数缺失！");
		}
		// 定义日期格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentString = format.format(date);
		//转换成当天的0：0分
		Date currentDate =  format.parse(currentString);
		//触发时间
		Date exeDate = format.parse(executeDate);
		//判断触发时间是否是当天，当天就直接触发,早于今天就不管，晚于今天就推送MQ
		int resultDate = currentDate.compareTo(exeDate);
		//exeDate在currentDate之前
		//2018.9.26，DD更改逻辑,早于或者万元当天的，由直接return改为只更新位置，不更新完成数
		boolean updateFlag = true;
		if(resultDate>0){
			//return;
			updateFlag = false;
		}else if(resultDate<0){
			this.pushUserTaskNumMQ(organId,userIds, unTaskNum, runTaskNum,executeDate);
			//return;
			updateFlag = false;
		}
		
		TOrganInfo topOrgan = CommonServiceUtils.getTopOrganByOrganID(organId);
		Integer groupOrganId = null != topOrgan ? topOrgan.getOrganId() : 0;
		userIds.removeAll(Collections.singleton(null));
		List<MtCurUserPosit> mtCurUserPositList = new ArrayList<MtCurUserPosit>();
		Map<Integer, MtCurUserPosit> curUserPositMap = AppUtils.list2Map(
				curUserLocationRedisCache.getUserLocationByOrganRedis(organId), (obj) -> obj.getUserId());
		Map<Integer, UserInfo> userMap = AppUtils.list2Map(
				CommonServiceUtils.getUserInfoList(new HashSet<Integer>(userIds)), (obj) -> obj.getUserId());
		
		MtBuildLocation location =null;
		// 查询位置信息
		if(StringUtils.isNotEmpty(locationId)){
			location = mtLocationGrabRedisCache.getLocationByOrganAndLocationIdRedis(organId, Integer.valueOf(locationId));
		}
		logger.info("==================>修改人员位置的1：location: location:{}", 
				FastjsonUtils.toJsonString(location));
		// 循环对缓存进行新增或修改
		for (Integer userId : userIds) {
			MtCurUserPosit userPosit = curUserPositMap.get(userId);
			MtCurUserPosit mtCurUserPosit = new MtCurUserPosit();
			mtCurUserPosit.setOrganId(organId);
			mtCurUserPosit.setGroupOrganId(groupOrganId);
			// 缓存需要存的信息
			mtCurUserPosit.setUserId(userId);
			UserInfo userInfo = userMap.get(userId);
			if (null != userInfo) {
				mtCurUserPosit.setUserName(userInfo.getUserName());
				mtCurUserPosit.setUserWorkNo(userInfo.getJobNumber());
			}
			mtCurUserPosit.setLastUpdateTime(DateUtil.nowDateToStringYYMMDDHHmmss());
			// 查询缓存人员信息
			// 查到之后进行计算后修改
			short newRunTaskNum = 0;
			short newUnTaskNum = 0;
			if (null != userPosit) {
				mtCurUserPosit.setLocationId(userPosit.getLocationId());
				mtCurUserPosit.setBuildId(userPosit.getBuildId());
				mtCurUserPosit.setFloorId(userPosit.getFloorId());
				mtCurUserPosit.setSid(userPosit.getSid());
				mtCurUserPosit.setUserName(userPosit.getUserName());
				mtCurUserPosit.setUserWorkNo(userPosit.getUserWorkNo());
				//需要修改未完成数
				if(updateFlag){
					newRunTaskNum = (short) (userPosit.getRunTaskNum() == null ? runTaskNum : userPosit.getRunTaskNum()
							+ runTaskNum);
					newUnTaskNum = (short) (userPosit.getUnTaskNum() == null ? unTaskNum : userPosit.getUnTaskNum()
							+ unTaskNum);
					mtCurUserPosit.setRunTaskNum(newRunTaskNum < 0 ? 0 : newRunTaskNum);
					mtCurUserPosit.setUnTaskNum(newUnTaskNum < 0 ? 0 : newUnTaskNum);
					mtCurUserPosit.setStatus(0 >= newRunTaskNum
							? UserPositStatusEnum.STATUS_LEISURE.getCode()
									: UserPositStatusEnum.STATUS_USE.getCode());
				}else{
					mtCurUserPosit.setRunTaskNum(userPosit.getRunTaskNum());
					mtCurUserPosit.setUnTaskNum(userPosit.getUnTaskNum());
					mtCurUserPosit.setStatus(userPosit.getStatus());
				}
				
			} else {
				if(updateFlag){
					// 没查到新增
					mtCurUserPosit.setRunTaskNum(runTaskNum < 0 ? 0 : runTaskNum);
					mtCurUserPosit.setUnTaskNum(unTaskNum < 0 ? 0 : unTaskNum);
					mtCurUserPosit.setStatus(0 >= runTaskNum
							? UserPositStatusEnum.STATUS_LEISURE.getCode()
									: UserPositStatusEnum.STATUS_USE.getCode());
				}else{
					mtCurUserPosit.setRunTaskNum((short) 0);
					mtCurUserPosit.setUnTaskNum((short) 0);
					mtCurUserPosit.setStatus( UserPositStatusEnum.STATUS_LEISURE.getCode());
				}
			}
			//增加位置信息(没传locationId的时候不更新位置)
			if(location!=null){
				mtCurUserPosit.setLocationId(Integer.valueOf(locationId));
				mtCurUserPosit.setBuildId(location.getBuildId());
				mtCurUserPosit.setFloorId(location.getFloorId());
				if(null != location.getSid()){
					mtCurUserPosit.setSid(location.getSid());
				}else{
					mtCurUserPosit.setSid(null);
				}
				
			}
			// 缓存修改或新增
			mtCurUserPositList.add(mtCurUserPosit);
		}
		this.addOrUpdateUserLocationRedis(mtCurUserPositList);
	}
	
	/**
	 * 当不是今天的运送单，推送到MQ，到点触发未完成运单数
	 * @param organId
	 * @param userIds
	 * @param unTaskNum
	 * @param runTaskNum
	 * @param executeDate
	 * @throws ParseException 
	 */
	public void pushUserTaskNumMQ(Integer organId, List<Integer> userIds, Short unTaskNum, Short runTaskNum,String executeDate) throws Exception {
		UserLocationTaskNumParam userLocationTaskNumParam = new UserLocationTaskNumParam();
		userLocationTaskNumParam.setOrganId(String.valueOf(organId));
		String users = StringUtils.join(userIds.toArray(), ","); 
		userLocationTaskNumParam.setUserIds(users);
		userLocationTaskNumParam.setUnTaskNum(String.valueOf(unTaskNum));
		userLocationTaskNumParam.setRunTaskNum(String.valueOf(runTaskNum));
		userLocationTaskNumParam.setExecuteDate(executeDate);
		// 定义日期格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//设置当天时间的1点钟为触发MQ推送时间
		Date execute = format.parse(executeDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(execute);
		calendar.add(Calendar.HOUR, 1);
		Date executeDateNew = calendar.getTime();
		
		//生成触发任务
		MessageIce msg = new MessageIce();
		msg.setMsgTypeCode(MtConstant.MEDICAL_TAKS_NUM_CODE); // 推送CODE
		msg.setMsgTime(executeDateNew.getTime()); // 执行时间
		msg.setMsgParams(JSONObject.toJSONString(userLocationTaskNumParam)); // 参数
		RpcRespIce rpc = this.getPushServiceIcePrx().push(msg);
		if (RpcError.FAIL.getCode().equals(rpc.code)) {
			throw new RuntimeException();
		}
	}
	
	/**
	 * @discription 批量新增人员位置表
	 * @author yangyh@segimail.com
	 * @created 2018年3月28日 上午9:46:44
	 * @param mtUserPositList
	 */
	public void insertMtUserPositList(List<MtUserPosit> mtUserPositList) {
		for (MtUserPosit mtUserPosit : mtUserPositList) {
			int userPositId = SeqContants.getSequnces(MtSeqContants.MT_USER_POSIT_ID_SEQ).intValue();
			mtUserPosit.setUserPositId(userPositId);
		}
		this.mtUserPositInfoMapper.saveBatchMtUserPosit(mtUserPositList);
	}
	
	/**
	 * @discription 批量新增或修改人员位置缓存
	 * @author yangyh@segimail.com
	 * @created 2018年3月28日 上午9:46:44
	 * @param mtUserPositList
	 */
	private void addOrUpdateUserLocationRedis(List<MtCurUserPosit> mtCurUserPositList) {
		for (MtCurUserPosit mtCurUserPosit : mtCurUserPositList) {
			curUserLocationRedisCache.addOrUpdateUserLocationRedis(mtCurUserPosit);
		}
	}
	
	/**
	 * 将楼层按楼层详情返回的顺序号重新排序
	 * @return
	 */
	public List<Map<Integer,List<MtBuildLocation>>> sortFloorMap(Map<Integer,List<MtBuildLocation>> map){
		List<Map<Integer,List<MtBuildLocation>>> result = new ArrayList<Map<Integer,List<MtBuildLocation>>>();
		if(map==null){
			Log.error("楼层排序传入参数有问题");
			return null;
		}
		List<FloorDetailInfo> floorDetailInfoList =new ArrayList<FloorDetailInfo>();
		for(Integer floorId:map.keySet()){
			if(floorId!=null){
				FloorDetailInfo floorDetailInfo = MtSpaceManageServiceUtils.queryFloorDetailByID(floorId);
				if(floorDetailInfo!=null){
					floorDetailInfoList.add(floorDetailInfo);	
				}
			}
		}
		if(AppUtils.isNotEmpty(floorDetailInfoList)){
			Collections.sort(floorDetailInfoList, new Comparator<FloorDetailInfo>() { 
				@Override  
	            public int compare(FloorDetailInfo floorDetailInfo1, FloorDetailInfo floorDetailInfo2) {  
					int i = floorDetailInfo2.getSortNo()-floorDetailInfo1.getSortNo();
					return i;
				}
			} );
			for(FloorDetailInfo floorDetailInfo:floorDetailInfoList){
				Map<Integer,List<MtBuildLocation>> newMap= new HashMap<Integer,List<MtBuildLocation>>();
				List<MtBuildLocation> MtBuildLocationList=map.get(floorDetailInfo.getFloorId());
				if(MtBuildLocationList!=null){
					newMap.put(floorDetailInfo.getFloorId(), MtBuildLocationList);
					result.add(newMap);
				}
			}
			logger.info("==================>楼层排序后数据result: result:{}", 
					FastjsonUtils.toJsonString(result));
		}
		return result;
	}
}
