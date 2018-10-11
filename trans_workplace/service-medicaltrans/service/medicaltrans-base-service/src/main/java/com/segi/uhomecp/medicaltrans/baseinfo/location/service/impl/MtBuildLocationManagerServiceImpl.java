package com.segi.uhomecp.medicaltrans.baseinfo.location.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import segi.datacachesvr.queryInfo.TOrganInfo;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.segi.uhomecp.common.model.ResultInfo;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocation;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocationCriteria;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocationRel;
import com.segi.uhomecp.medicaltrans.base.location.model.MtBuildLocationRelCriteria;
import com.segi.uhomecp.medicaltrans.base.location.service.MtBuildLocationRelService;
import com.segi.uhomecp.medicaltrans.base.location.service.MtBuildLocationService;
import com.segi.uhomecp.medicaltrans.base.taskloop.model.MtTaskLoop;
import com.segi.uhomecp.medicaltrans.baseinfo.location.dao.MtBuildTypeLocationRelMapper;
import com.segi.uhomecp.medicaltrans.baseinfo.location.dto.MtBuildLocationDto;
import com.segi.uhomecp.medicaltrans.baseinfo.location.service.MtBuildLocationManagerService;
import com.segi.uhomecp.medicaltrans.baseinfo.taskloop.dto.MtTaskLoopDto;
import com.segi.uhomecp.medicaltrans.baseinfo.taskloop.service.MtTaskLoopInfoService;
import com.segi.uhomecp.medicaltrans.cache.CurUserLocationRedisCache;
import com.segi.uhomecp.medicaltrans.cache.MtLocationGrabRedisCache;
import com.segi.uhomecp.medicaltrans.constant.MtConstant;
import com.segi.uhomecp.medicaltrans.constant.MtSeqContants;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.rest.utils.StringUtils;
import com.segi.uhomecp.utils.SeqContants;
import com.segi.uhomecp.wh.common.constant.Constant;
import com.segi.uhomecp.wh.common.utils.AppUtils;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;

@Service
public class MtBuildLocationManagerServiceImpl implements MtBuildLocationManagerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MtBuildLocationManagerServiceImpl.class);
	
	@Autowired
	private MtBuildLocationService mtBuildLocationService;

	@Autowired
	private MtBuildLocationRelService mtBuildLocationRelService;
	
	@Autowired
	private MtBuildTypeLocationRelMapper mtBuildTypeLocationRelMapper;
	
	@Autowired
	private MtLocationGrabRedisCache mtLocationGrabRedisCache;
	
	@Autowired
	private CurUserLocationRedisCache curUserLocationRedisCache;

	@Autowired
	private MtTaskLoopInfoService mtTaskLoopInfoService;
	
//	private TransReportOrganServiceIcePrx getTransReportOrganServiceIcePrx() {
//    	return IceClientUtil.getServicePrxByClass(TransReportOrganServiceIcePrx.class);
//    }
	
	/**
	 * @discription 新增位置信息
	 * @author dengdong@segimail.com       
	 * @created 2018年5月9日 下午7:58:38      
	 * @param mtBuildLocationDto
	 * @return     
	 * @throws Exception 
	 * @see com.segi.uhomecp.medicaltrans.baseinfo.location.service.MtBuildLocationManagerService#saveMtBuildLocation(com.segi.uhomecp.medicaltrans.baseinfo.location.dto.MtBuildLocationDto)
	 */
	@Override
	public ResultInfo saveMtBuildLocation(MtBuildLocationDto mtBuildLocationDto) throws Exception {
			ResultInfo resultInfo = new ResultInfo();
			MtBuildLocation mtBuildlocation = BeanCopierUtils.copyProperties(mtBuildLocationDto, MtBuildLocation.class, true);
			//创建人和创建时间
			mtBuildlocation.setCreateuserId(Integer.valueOf(mtBuildLocationDto.getCurUserId()));
			mtBuildlocation.setCreateDate(new Date());
			//新增默认状态为有效
			mtBuildlocation.setStatus(Constant.STATUS_CD_NORMAL);
			//设置一级物业id
			TOrganInfo organInfo = CommonServiceUtils.getTopOrganByOrganID(mtBuildlocation.getOrganId());
			mtBuildlocation.setGroupOrganId(organInfo.getOrganId());
			//校验同一项目位置名称是否重复
			boolean distinctLocationName = distinctLocationName(mtBuildlocation,null);
			//当为NFC，需要校验mac地址是否唯一
			boolean distinctMac = true;
			if(MtConstant.LOCATE_TYPE_NFC .equals(mtBuildlocation.getLocateType()) || MtConstant.LOCATE_TYPE_QRCODE_AND_NFC .equals(mtBuildlocation.getLocateType()) ){
				distinctMac = distinctMac(mtBuildlocation,null);
			}
			
			if(distinctLocationName && distinctMac){
				int locationId = SeqContants.getSequnces(MtSeqContants.MT_BUILD_LOCATION_ID_SEQ).intValue();
				String houseSpaces =  mtBuildLocationDto.getHouseSpaces();
				//存储位置与物理空间关系
				this.saveLoactionHouse(Integer.valueOf(locationId),houseSpaces,mtBuildlocation.getOrganId(),Integer.valueOf(mtBuildLocationDto.getCurUserId()));
				//存储位置主表
				mtBuildlocation.setLocationId(Integer.valueOf(locationId));
				mtBuildLocationService.insert(mtBuildlocation);
				
				//存缓存
				//将位置名称的首字母存入缓存，便于查询
				String reusltPingyin = PinYinUtil.intercept(mtBuildlocation.getLocationName());
				String pingyin = PinYinUtil.getFirstSpell(reusltPingyin).toUpperCase();
				String pingyinName = mtBuildlocation.getLocationName()+"（"+pingyin+"）";
				mtBuildlocation.setPingyinName(pingyinName);
				ResultInfo resultRedis = mtLocationGrabRedisCache.addOrUpdateLocationRedis(mtBuildlocation);
				
				if(resultRedis.getIsSucc()){
					resultInfo.setIsSucc(true); 
					resultInfo.setObject1(locationId);
				}else{
					resultInfo.setIsSucc(false);
					resultInfo.setMessage("缓存失败");
					throw new RuntimeException("缓存失败");
				}
			}else if(!distinctLocationName && distinctMac){
				resultInfo.setIsSucc(false);
				resultInfo.setMessage("位置名称重复");
			}else if(distinctLocationName && !distinctMac){
				resultInfo.setIsSucc(false);
				resultInfo.setMessage("mac地址重复");
			}else{
				resultInfo.setIsSucc(false);
				resultInfo.setMessage("位置名称重复，mac地址重复");
			}
		
		//遗留同步位置同步到空间管理系统接口
		
		return resultInfo;
	}
	
	/**
	 * @discription 编辑位置信息
	 * @author dengdong@segimail.com       
	 * @created 2018年5月9日 下午7:59:35     
	 * @param mtBuildHouseIceParam
	 * @return
	 */
	@Override
	public ResultInfo updateMtBuildLocation(MtBuildLocationDto mtBuildLocationDto) {
		ResultInfo resultInfo = new ResultInfo();
		
		MtBuildLocation mtBuildlocation = BeanCopierUtils.copyProperties(mtBuildLocationDto, MtBuildLocation.class, true);
		//查询需要更新的位置对象
		MtBuildLocation mtBuildLocationOld = mtBuildLocationService.selectByPrimaryKey(mtBuildLocationDto.getLocationId());
		if(mtBuildLocationOld==null){
			resultInfo.setIsSucc(false);
			resultInfo.setMessage("编辑位置不存在，请确认");
			return resultInfo;
		}else if(Constant.STATUS_CD_DEL.equals(mtBuildLocationOld.getStatus())){
			resultInfo.setIsSucc(false);
			resultInfo.setMessage("编辑位置已删除，请确认");
			return resultInfo;
		}
		
		//更新人更新时间
		mtBuildlocation.setUpdateuserId(Integer.valueOf(mtBuildLocationDto.getCurUserId()));
		mtBuildlocation.setUpdateDate(new Date());
		mtBuildlocation.setOrganId(mtBuildLocationOld.getOrganId());
		
		//校验同一项目位置名称是否重复
		boolean distinctLocationName = distinctLocationName(mtBuildlocation,mtBuildLocationOld);
		
		//当为NFC，需要校验mac地址是否唯一
		boolean distinctMac = true;
		if(MtConstant.LOCATE_TYPE_NFC .equals(mtBuildlocation.getLocateType()) ||MtConstant.LOCATE_TYPE_QRCODE_AND_NFC .equals(mtBuildlocation.getLocateType())){
			distinctMac = distinctMac(mtBuildlocation,mtBuildLocationOld);
		}
		
		if(distinctLocationName && distinctMac){
			int locationId = mtBuildLocationOld.getLocationId().intValue();
			String houseSpaces =  mtBuildLocationDto.getHouseSpaces();
			//1、先删除老位置与物理空间关系
			this.deleteLoactionHouse(Integer.valueOf(locationId));
			//2、存储位置与物理空间关系
			this.saveLoactionHouse(Integer.valueOf(locationId),houseSpaces,mtBuildLocationOld.getOrganId(),Integer.valueOf(mtBuildLocationDto.getCurUserId()));
			//3、更新位置主表
			mtBuildLocationService.updateByPrimaryKeySelective(mtBuildlocation);//为空的字段不更改
			
			//4、更新缓存
			//将老信息存入缓存
			mtBuildlocation.setGroupOrganId(mtBuildLocationOld.getGroupOrganId());
			mtBuildlocation.setBuildId(mtBuildLocationOld.getBuildId());
			mtBuildlocation.setSid(mtBuildLocationOld.getSid());
			mtBuildlocation.setFloorId(mtBuildLocationOld.getFloorId());
			mtBuildlocation.setFloorNum(mtBuildLocationOld.getFloorNum());
			mtBuildlocation.setQrcode(mtBuildLocationOld.getQrcode());
			mtBuildlocation.setStatus(mtBuildLocationOld.getStatus());
			mtBuildlocation.setCreateDate(mtBuildLocationOld.getCreateDate());
			mtBuildlocation.setCreateuserId(mtBuildLocationOld.getCreateuserId());
			//将位置名称的首字母存入缓存，便于查询
			String reusltPingyin="";
			try {
				reusltPingyin = PinYinUtil.intercept(mtBuildlocation.getLocationName());
			} catch (Exception e) {
				LOGGER.warn("位置名取首字母异常:"+e);
			}
			String pingyin = PinYinUtil.getFirstSpell(reusltPingyin).toUpperCase();
			String pingyinName = mtBuildlocation.getLocationName()+"（"+pingyin+"）";
			mtBuildlocation.setPingyinName(pingyinName);
			ResultInfo resultRedis = mtLocationGrabRedisCache.addOrUpdateLocationRedis(mtBuildlocation);
			
			if(resultRedis.getIsSucc()){
				resultInfo.setIsSucc(true); 
				resultInfo.setObject1(locationId);
			}else{
				resultInfo.setIsSucc(false);
				resultInfo.setMessage("缓存失败");
				throw new RuntimeException("缓存失败");
			}
			
		}else if(!distinctLocationName && distinctMac){
			resultInfo.setIsSucc(false);
			resultInfo.setMessage("位置名称重复");
		}else if(distinctLocationName && !distinctMac){
			resultInfo.setIsSucc(false);
			resultInfo.setMessage("mac地址重复");
		}else{
			resultInfo.setIsSucc(false);
			resultInfo.setMessage("位置名称重复，mac地址重复");
		}
		
		return resultInfo;
	}
	
	/**
	 * @discription 删除位置信息
	 * @author dengdong@segimail.com       
	 * @created 2018年5月9日 下午7:59:35     
	 * @param mtBuildHouseIceParam
	 * @return
	 */
	@Override
	public ResultInfo deleteMtBuildLocation(MtBuildLocationDto mtBuildLocationDto){
		ResultInfo resultInfo = new ResultInfo();
		//判断该位置是否有在途的运送单(true是没有)
		//2018.6.6先不判断在途任务
		boolean isOnWayTaskLocation  = true;
		//boolean isOnWayTaskLocation = this.isOnWayTaskLocation(mtBuildLocationDto.getLocationId());
		//判断该位置是否在循环任务中,如果有,将任务置为失效，并返回循环任务名称
		String taskLoopNames = this.isOnWayTaskLoopLocation(mtBuildLocationDto);
		//判断该位置是否已经失效
		MtBuildLocation mtBuildLocationOld = mtBuildLocationService.selectByPrimaryKey(mtBuildLocationDto.getLocationId());
		if(mtBuildLocationOld!=null && Constant.STATUS_CD_DEL.equals(mtBuildLocationOld.getStatus())){
			resultInfo.setIsSucc(false);
			resultInfo.setMessage("该科室已删除，请不要重复操作");
			return resultInfo;
		}
		if(isOnWayTaskLocation ){
			mtBuildLocationOld.setStatus(Constant.STATUS_CD_DEL);
			int result = mtBuildLocationService.updateByPrimaryKeySelective(mtBuildLocationOld);
			//4、更新缓存的删除状态
			ResultInfo resultRedis = mtLocationGrabRedisCache.addOrUpdateLocationRedis(mtBuildLocationOld);
			if(!resultRedis.getIsSucc()){
				resultInfo.setIsSucc(false);
				resultInfo.setMessage(resultRedis.getMessage());
				throw new RuntimeException("缓存失败");
				//return resultInfo;
			}
			
			if(result==1){
				resultInfo.setIsSucc(true);
				//返回在途的循环任务名称
				resultInfo.setObject1(taskLoopNames);
			}else{
				resultInfo.setIsSucc(false);
				resultInfo.setMessage("不存在需要删除的科室");
			}
		}else{
			resultInfo.setIsSucc(false);
			resultInfo.setMessage("不可删除科室,该科室还有未完成的运送单");
		}
		
		return resultInfo;
	}
	
	/**
	 * 根据楼层展示位置信息
	 * @param mtBuildHouseIceParam
	 * @return
	 */
	/*@Override
	public PageList<MtBuildLocation> queryLocationPageByFloorId(MtBuildLocationDto mtBuildLocationDto) {
		MtBuildLocationCriteria example = new MtBuildLocationCriteria();
		MtBuildLocationCriteria.Criteria criteria = example.createCriteria();
		
		int pageNo = Constant.INIT_PAGE_NO;
		if (null != mtBuildLocationDto.getPageNo() 
				&& Integer.valueOf(mtBuildLocationDto.getPageNo()).intValue() != 0) {
			//先对页码初始化,然后进行判断并赋值
			pageNo = Integer.valueOf(mtBuildLocationDto.getPageNo());
		}
		int pageLength = Constant.DEFAULT_PAGE_LENGTH;
		if (null != mtBuildLocationDto.getPageLength() 
				&& Integer.valueOf(mtBuildLocationDto.getPageLength()).intValue() != 0) { 
			//先对每页记录数初始化,然后进行判断并赋值
			pageLength = Integer.valueOf(mtBuildLocationDto.getPageLength());
		}
		if(mtBuildLocationDto.getFloorId()!=null){
			criteria.andFloorIdEqualTo(mtBuildLocationDto.getFloorId());
		}
		if(mtBuildLocationDto.getOrganId()!=null){
			criteria.andOrganIdEqualTo(mtBuildLocationDto.getOrganId());
		}
		if(!StringUtils.isEmpty(mtBuildLocationDto.getLocationName()) ){
			criteria.andLocationNameLike( Constant.SPLIT_PER+mtBuildLocationDto.getLocationName()+Constant.SPLIT_PER);
		}
		criteria.andStatusEqualTo(Constant.STATUS_CD_NORMAL);
		
		return mtBuildLocationService.selectByExampleWithRowbounds(example, PageUtils.getPageBoundsByPageNo(pageNo, pageLength));
	}*/
	
	/**
	 * 根据楼层展示位置信息
	 * @param mtBuildHouseIceParam
	 * @return
	 */
	@Override
	public PageList<MtBuildLocation> queryLocationPageByFloorId(MtBuildLocationDto mtBuildLocationDto) {
		int pageNo = Integer.parseInt(mtBuildLocationDto.getPageNo());//当前页数
		int pageLength = Integer.parseInt(mtBuildLocationDto.getPageLength());//每页数
		//从缓存中查数据(总数据)
		List<MtBuildLocation> resultRedis = mtLocationGrabRedisCache.getLocationByOrganRedis(mtBuildLocationDto.getOrganId());
		
		List<MtBuildLocation> locationList = new ArrayList<MtBuildLocation>();
		if(AppUtils.isNotEmpty(resultRedis)){
			Collections.sort(resultRedis, new Comparator<MtBuildLocation>() { 
				@Override  
	            public int compare(MtBuildLocation MtBuildLocation1, MtBuildLocation MtBuildLocation2) {  
					int i = MtBuildLocation2.getCreateDate().compareTo(MtBuildLocation1.getCreateDate());
					return i;
				}
			} );
			
			if(mtBuildLocationDto.getFloorId()!=null){ //根据楼层查的时候
				for(MtBuildLocation mtBuildLocation:resultRedis){
					if(mtBuildLocation!=null && mtBuildLocationDto.getFloorId().equals(mtBuildLocation.getFloorId()) && Constant.STATUS_CD_NORMAL.equals(mtBuildLocation.getStatus())){
						locationList.add(mtBuildLocation);
					}
				}
			}else if(StringUtils.isNotEmpty(mtBuildLocationDto.getLocationName())){//根据名字模糊查询的时候
				for(MtBuildLocation mtBuildLocation:resultRedis){
					if(((mtBuildLocation.getPingyinName()!=null && mtBuildLocation.getPingyinName().indexOf(mtBuildLocationDto.getLocationName().toUpperCase())!=-1) || (mtBuildLocation.getLocationName()!=null && mtBuildLocation.getLocationName().indexOf(mtBuildLocationDto.getLocationName())!=-1))&& Constant.STATUS_CD_NORMAL.equals(mtBuildLocation.getStatus())){
						locationList.add(mtBuildLocation);
					}
				}
			}else{
				for(MtBuildLocation mtBuildLocation:resultRedis){
					if(mtBuildLocation!=null && Constant.STATUS_CD_NORMAL.equals(mtBuildLocation.getStatus())){
						//locationList.addAll(resultRedis);	
						locationList.add(mtBuildLocation);
					}
				}
				
			}
		}
		
		int pageTotal = locationList.size();//总数据量
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
		List<MtBuildLocation> result = locationList.subList(pageStartRow, pageEndRow);
		PageList<MtBuildLocation> pagelist = new PageList<MtBuildLocation>(result,paginator);
		return pagelist;
	}
	
	/**
	 * 根据楼层展示列表展示位置信息
	 */
	@Override
	public List<MtBuildLocation> queryLocationListByFloorId(MtBuildLocationDto mtBuildLocationDto) {
		//从缓存中查数据
		List<MtBuildLocation> resultRedis = mtLocationGrabRedisCache.getLocationByOrganRedis(mtBuildLocationDto.getOrganId());
		List<MtBuildLocation> result = new ArrayList<MtBuildLocation>();
		for(MtBuildLocation mtBuildLocation:resultRedis){
			if(mtBuildLocation!=null && mtBuildLocationDto.getFloorId().equals(mtBuildLocation.getFloorId())
					&& Constant.STATUS_CD_NORMAL.equals(mtBuildLocation.getStatus())){
				result.add(mtBuildLocation);
			}
		}
		return result;
	}
	
	/**
	 * 校验同一项目位置名称是否重复(true没有重复，false重复了)，修改位置时需要传老位置
	 * @param mtBuildlocation
	 * @param mtBuildLocationOld
	 * @return
	 */
	public boolean distinctLocationName(MtBuildLocation mtBuildlocation,MtBuildLocation mtBuildLocationOld){
		MtBuildLocationCriteria example = new MtBuildLocationCriteria();
		MtBuildLocationCriteria.Criteria criteria = example.createCriteria();
		
		//修改的时候可以和老位置名字重复
		if(mtBuildLocationOld!=null){
			criteria.andOrganIdEqualTo(mtBuildLocationOld.getOrganId());
			criteria.andStatusEqualTo(Constant.STATUS_CD_NORMAL);
			criteria.andLocationNameEqualTo(mtBuildlocation.getLocationName());
			criteria.andLocationIdNotEqualTo(mtBuildLocationOld.getLocationId());
		}else{
			criteria.andOrganIdEqualTo(mtBuildlocation.getOrganId());
			criteria.andStatusEqualTo(Constant.STATUS_CD_NORMAL);
			criteria.andLocationNameEqualTo(mtBuildlocation.getLocationName());
		}
		List<MtBuildLocation> locationList = mtBuildLocationService.selectByExample(example);
		if (!AppUtils.isNotEmpty(locationList)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 校验同一个项目mac地址是否重复（true没有重复，false重复了），修改位置时需要传老位置
	 * @param mtBuildlocation
	 * @return
	 */
	public boolean distinctMac(MtBuildLocation mtBuildlocation,MtBuildLocation mtBuildLocationOld){
		MtBuildLocationCriteria example = new MtBuildLocationCriteria();
		MtBuildLocationCriteria.Criteria criteria = example.createCriteria();
		
		//修改的时候可以和老位置名字重复
		if(mtBuildLocationOld!=null){
			criteria.andOrganIdEqualTo(mtBuildLocationOld.getOrganId());
			criteria.andStatusEqualTo(Constant.STATUS_CD_NORMAL);
			criteria.andMacEqualTo(mtBuildlocation.getMac());
			criteria.andLocationIdNotEqualTo(mtBuildLocationOld.getLocationId());
		}else{
			criteria.andOrganIdEqualTo(mtBuildlocation.getOrganId());
			criteria.andStatusEqualTo(Constant.STATUS_CD_NORMAL);
			criteria.andMacEqualTo(mtBuildlocation.getMac());
		}
		List<MtBuildLocation> locationList = mtBuildLocationService.selectByExample(example);
		if (!AppUtils.isNotEmpty(locationList)) {
			return true;
		}
		return false;
	}
	
	/**
	 * @discription 保存位置与物理空间关系
	 * @author dengdong@segimail.com       
	 * @created 2018年5月9日 下午4:19:51     
	 * @param locationId
	 * @param houseSpaces
	 * @param organId
	 * @param updateuserId
	 */
	public void saveLoactionHouse(Integer locationId,String houseSpaces,Integer organId,Integer updateuserId){
		if(StringUtils.isNotEmpty(houseSpaces)){
			String[] houseSpace =  houseSpaces.split(",");
			List<MtBuildLocationRel> list = new ArrayList<>();
			for(int x=0;x<houseSpace.length;x++){
				MtBuildLocationRel mtBuildLocationRel = new MtBuildLocationRel();
				int locationRelId = SeqContants.getSequnces(MtSeqContants.MT_BUILD_LOCATION_REL_ID_SEQ).intValue();
				mtBuildLocationRel.setLocationRelId(Integer.valueOf(locationRelId));
				mtBuildLocationRel.setLocationId(locationId);
				mtBuildLocationRel.setHouseId(Integer.valueOf(houseSpace[x]));
				mtBuildLocationRel.setOrganId(organId);
				mtBuildLocationRel.setUpdateuserId(updateuserId);
				mtBuildLocationRel.setUpdateDate(new Date());
				list.add(mtBuildLocationRel);
			}
			this.mtBuildTypeLocationRelMapper.insertBatchMtBuildLocationRel(list);
		}
	}
	
	/**
	 * 删除位置与物理空间的关系
	 * @param locationId
	 */
	public void deleteLoactionHouse(Integer locationId){
		MtBuildLocationRelCriteria exmple = new MtBuildLocationRelCriteria();
		MtBuildLocationRelCriteria.Criteria criteria = exmple.createCriteria();
		criteria.andLocationIdEqualTo(locationId);
		mtBuildLocationRelService.deleteByExample(exmple);
	}
	
	/**
	 * 查询位置是否有在途的运输单(true为没有)
	 * @param locationId
	 * @return
	 */
/*	public boolean isOnWayTaskLocation (Integer locationId){
		RpcRespIce rsp = MtCommonServiceUtils.judgeOnWayTask(locationId.toString());
		if (RpcError.SUCCESS.getCode().equals(rsp.code)) {
			if(rsp.getData().equals(MtConstant.NO_ON_WAY_TASK)){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}*/
	
	/**
	 * 查询循环任务是否有在途的循环任务(true为没有)
	 * @param locationId
	 * @return
	 */
	public String isOnWayTaskLoopLocation(MtBuildLocationDto mtBuildLocationDto){
		Integer locationId = mtBuildLocationDto.getLocationId();
		String curUserId= mtBuildLocationDto.getCurUserId();
		
		StringBuffer taskLoopNames = new StringBuffer();
		String result ="";
		List<MtTaskLoop> mtTaskLoopInfoIceList  =  mtTaskLoopInfoService.findOnWayTaskLoopList(locationId.toString());
		if(mtTaskLoopInfoIceList!=null && mtTaskLoopInfoIceList.size()>0){
			//当存在循环任务需要删除该循环任务
			for(MtTaskLoop mtTaskLoopInfoIce:mtTaskLoopInfoIceList){
				if(mtTaskLoopInfoIce!=null){
					//拼接需要删除的循环任务名称，用于前台返回
					taskLoopNames.append(mtTaskLoopInfoIce.getTaskName()).append(",");
					//调取删除循环任务接口
					MtTaskLoopDto askLoopStatusParam = new MtTaskLoopDto();
					askLoopStatusParam.setUpdateBy(Integer.valueOf(curUserId));
					askLoopStatusParam.setTaskLoopId(mtTaskLoopInfoIce.getTaskLoopId());
					askLoopStatusParam.setStatus(Constant.STATUS_CD_STOP);
					askLoopStatusParam.setLoseRemark(MtConstant.LOCATION_LOST);
					mtTaskLoopInfoService.updateTaskLoopStatus(askLoopStatusParam);
				}
			}
			result = "该科室关联的循环任务已删除,请知悉！";
		//	result = taskLoopNames.toString().substring(0,taskLoopNames.toString().length()-1);
		}
		return result;
	}
	
	/**
	 * 根据位置id，列表展示位置信息
	 */
	@Override
	public List<MtBuildLocation> getLocationInfoByRefIdList(Integer organId,List<Integer> locationIdList) {
		//从缓存中查数据
		List<MtBuildLocation> result = new ArrayList<MtBuildLocation>();
		for(Integer locationId:locationIdList){
			MtBuildLocation mtBuildLocation = mtLocationGrabRedisCache.getLocationByOrganAndLocationIdRedis(organId,locationId);
			if(mtBuildLocation!=null){
				result.add(mtBuildLocation);
			}
		}
		return result;
	}
	
	/**
	 * 根据位置id，列表展示位置信息(数据库)
	 */
	@Override
	public List<MtBuildLocation> getLocationInfoBylocationIdList(List<Integer> locationIdList) {
		MtBuildLocationCriteria example = new MtBuildLocationCriteria();
		MtBuildLocationCriteria.Criteria criteria = example.createCriteria();
		criteria.andLocationIdIn(locationIdList);
		return mtBuildLocationService.selectByExample(example);
	}
	
	/**
	 * 根据项目ID查位置信息list(数据库)
	 */
	@Override
	public List<MtBuildLocation> getLocationInfoByOrganId(Integer organId){
		MtBuildLocationCriteria example = new MtBuildLocationCriteria();
		MtBuildLocationCriteria.Criteria criteria = example.createCriteria();
		criteria.andOrganIdEqualTo(organId);
		//criteria.andStatusEqualTo((Constant.STATUS_CD_NORMAL));
		return mtBuildLocationService.selectByExample(example);
	}
	
	/**
	 * 根据组织机构和mac地址查询位置详情
	 */
	@Override
	public MtBuildLocation getLocationInfoByMac(Integer organId,String mac){
		MtBuildLocation result=null;
		List<MtBuildLocation> locationList = mtLocationGrabRedisCache.getLocationByOrganRedis(organId);
		if(AppUtils.isNotEmpty(locationList)){
			for(MtBuildLocation mtBuildLocation:locationList){
				if(mac.equals(mtBuildLocation.getMac())){
					result = mtBuildLocation;
					break;
				}
			}
		}
		return result;
	}

	@Override
	public PageList<MtBuildLocation> queryLocationPageByOrgNameDefault(MtBuildLocationDto mtBuildLocationDto) {
		String defaultLocationId = mtBuildLocationDto.getDefaultLocationId();
		int pageNo = Integer.parseInt(mtBuildLocationDto.getPageNo());//当前页数
		int pageLength = Integer.parseInt(mtBuildLocationDto.getPageLength());//每页数
		//从缓存中查数据(总数据)
		List<MtBuildLocation> resultRedis = mtLocationGrabRedisCache.getLocationByOrganRedis(mtBuildLocationDto.getOrganId());
		//从缓存中查是否有默认
		MtBuildLocation defaultLocation = mtLocationGrabRedisCache.getLocationByOrganAndLocationIdRedis(mtBuildLocationDto.getOrganId(), Integer.valueOf(mtBuildLocationDto.getDefaultLocationId()));
		List<MtBuildLocation> locationList = new ArrayList<MtBuildLocation>();
		if(defaultLocation!=null && StringUtils.isEmpty(mtBuildLocationDto.getLocationName()) && Constant.STATUS_CD_NORMAL.equals(defaultLocation.getStatus())){
			locationList.add(defaultLocation);
		}
		if(AppUtils.isNotEmpty(resultRedis)){
			for(MtBuildLocation mtBuildLocation:resultRedis){
				//无效状态直接略过
				if(!Constant.STATUS_CD_NORMAL.equals(mtBuildLocation.getStatus())){
					continue;
				}
				if(defaultLocation!=null && StringUtils.isEmpty(mtBuildLocationDto.getLocationName()) && mtBuildLocation!=null && Integer.valueOf(defaultLocationId).equals(mtBuildLocation.getLocationId())){
					continue;
				}
				if(StringUtils.isNotEmpty(mtBuildLocationDto.getLocationName())){//根据名字模糊查询的时候
					if((mtBuildLocation.getPingyinName()!=null && mtBuildLocation.getPingyinName().indexOf(mtBuildLocationDto.getLocationName().toUpperCase())!=-1 )||(mtBuildLocation.getLocationName()!=null && mtBuildLocation.getLocationName().indexOf(mtBuildLocationDto.getLocationName())!=-1)){
						locationList.add(mtBuildLocation);
					}
				}else{
					locationList.add(mtBuildLocation);
				}
			}
		}
		
		int pageTotal = locationList.size();//总数据量
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
		List<MtBuildLocation> result = locationList.subList(pageStartRow, pageEndRow);
		PageList<MtBuildLocation> pagelist = new PageList<MtBuildLocation>(result,paginator);
		return pagelist;
	}

	/**
	 * 每晚刷新位置缓存
	 */
	@Override
	public ResultInfo refreshRedisLocaiton() {
		ResultInfo resultInfo = new ResultInfo();
		/** modify by kinas 改为使用公共类 start*/
//		ReportOrganIce response = this.getTransReportOrganServiceIcePrx().getOragnList();
//		if (!response.getCode().equals(RpcError.SUCCESS.getCode())) {
//			resultInfo.setIsSucc(false);
//			resultInfo.setMessage("获取组织机构失败!");
//		}
		//获取使用医疗运输的项目
//		List<Integer> organList = response.getOrganIds();
		
		List<Integer> organList = MtCommonServiceUtils.queryOrgan();
		if (!AppUtils.isNotEmpty(organList)) {
			resultInfo.setIsSucc(false);
			resultInfo.setMessage("获取组织机构失败!");
			return resultInfo;
		}
		/** modify by kinas 改为使用公共类 end*/
		for(Integer organId:organList){
			//查询数据库该项目下的位置信息
			List<MtBuildLocation> resultDB = getLocationInfoByOrganId(organId);
			//删除缓存该项目下已有的位置信息
			mtLocationGrabRedisCache.delLocationRedisByOrganId(organId);
			if(AppUtils.isNotEmpty(resultDB)){
				//补充科室的拼音name
				for(MtBuildLocation location:resultDB){
					if(null == location || null == location.getLocationName()){
						continue;
					}
					//将位置名称的首字母存入缓存，便于查询
					String reusltPingyin="";
					try {
						reusltPingyin = PinYinUtil.intercept(location.getLocationName());
					} catch (Exception e) {
						LOGGER.warn("位置名取首字母异常:"+e);
					}
					String pingyin = PinYinUtil.getFirstSpell(reusltPingyin).toUpperCase();
					String pingyinName = location.getLocationName()+"（"+pingyin+"）";
					location.setPingyinName(pingyinName);
				}
				//批量插入改项目下从数据库查到的项目
				mtLocationGrabRedisCache.addLocationListRedis(resultDB);
			}
			
		}
		resultInfo.setIsSucc(true); 
		return resultInfo;
	}

	
	
	/**
	 * 每晚刷新人员位置缓存
	 */
	@Override
	public ResultInfo refreshRedisUserLocaiton() {
		ResultInfo resultInfo = new ResultInfo();
		/** modify by kinas 改为使用公共类 start*/
//		ReportOrganIce response = this.getTransReportOrganServiceIcePrx().getOragnList();
//		if (!response.getCode().equals(RpcError.SUCCESS.getCode())) {
//			resultInfo.setIsSucc(false);
//			resultInfo.setMessage("获取组织机构失败!");
//		}
//		//获取使用医疗运输的项目
//		List<Integer> organList = response.getOrganIds();
		
		List<Integer> organList = MtCommonServiceUtils.queryOrgan();
		if (!AppUtils.isNotEmpty(organList)) {
			resultInfo.setIsSucc(false);
			resultInfo.setMessage("获取组织机构失败!");
			return resultInfo;
		}
		/** modify by kinas 改为使用公共类 end*/
		for(Integer organId:organList){
			curUserLocationRedisCache.delUserLocationRedisByOrganId(organId);
		}
		resultInfo.setIsSucc(true); 
		return resultInfo;
	}

	/**
	 * 根据组织机构、位置名称、状态加载
	 */
	@Override
	public PageList<MtBuildLocation> queryLocationPageByOrgName(MtBuildLocationDto mtBuildLocationDto) {
		int pageNo = Integer.parseInt(mtBuildLocationDto.getPageNo());//当前页数
		int pageLength = Integer.parseInt(mtBuildLocationDto.getPageLength());//每页数
		//从缓存中查数据(总数据)
		List<MtBuildLocation> resultRedis = mtLocationGrabRedisCache.getLocationByOrganRedis(mtBuildLocationDto.getOrganId());
		
		List<MtBuildLocation> locationList = new ArrayList<MtBuildLocation>();
		if(AppUtils.isNotEmpty(resultRedis)){
			Collections.sort(resultRedis, new Comparator<MtBuildLocation>() { 
				@Override  
	            public int compare(MtBuildLocation MtBuildLocation1, MtBuildLocation MtBuildLocation2) {  
					int i = MtBuildLocation2.getCreateDate().compareTo(MtBuildLocation1.getCreateDate());
					return i;
				}
			} );
			//根据名字模糊查询有效的
			if(StringUtils.isNotEmpty(mtBuildLocationDto.getLocationName()) && !StringUtils.isNotEmpty(mtBuildLocationDto.getStatus())){
				for(MtBuildLocation mtBuildLocation:resultRedis){
					if(((mtBuildLocation.getPingyinName()!=null && mtBuildLocation.getPingyinName().indexOf(mtBuildLocationDto.getLocationName().toUpperCase())!=-1) || (mtBuildLocation.getLocationName()!=null && mtBuildLocation.getLocationName().indexOf(mtBuildLocationDto.getLocationName())!=-1))&& Constant.STATUS_CD_NORMAL.equals(mtBuildLocation.getStatus())){
						locationList.add(mtBuildLocation);
					}
				}
			//根据名字模糊查询的全部的
			}else if(StringUtils.isNotEmpty(mtBuildLocationDto.getLocationName()) && "ALL".equals(mtBuildLocationDto.getStatus())){
				for(MtBuildLocation mtBuildLocation:resultRedis){
					if(((mtBuildLocation.getPingyinName()!=null && mtBuildLocation.getPingyinName().indexOf(mtBuildLocationDto.getLocationName().toUpperCase())!=-1) || (mtBuildLocation.getLocationName()!=null && mtBuildLocation.getLocationName().indexOf(mtBuildLocationDto.getLocationName())!=-1))){
						locationList.add(mtBuildLocation);
					}
				}
			//查询有效的
			}else if(!StringUtils.isNotEmpty(mtBuildLocationDto.getLocationName()) &&  !StringUtils.isNotEmpty(mtBuildLocationDto.getStatus())){ 
				for(MtBuildLocation mtBuildLocation:resultRedis){
					if(mtBuildLocation!=null && Constant.STATUS_CD_NORMAL.equals(mtBuildLocation.getStatus())){
						locationList.add(mtBuildLocation);
					}
				}
			//查询全部
			}else if(!StringUtils.isNotEmpty(mtBuildLocationDto.getLocationName()) && "ALL".equals(mtBuildLocationDto.getStatus())){
				locationList.addAll(resultRedis);
			}
		}
		
		int pageTotal = locationList.size();//总数据量
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
		List<MtBuildLocation> result = locationList.subList(pageStartRow, pageEndRow);
		PageList<MtBuildLocation> pagelist = new PageList<MtBuildLocation>(result,paginator);
		return pagelist;
	}
	
}
