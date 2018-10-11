package com.segi.uhomecp.medicaltrans.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import resp.RpcRespIce;
import segi.medicaltrans.base.dbsource.DbSourceRuleIce;
import segi.medicaltrans.base.dbsource.DbSourceRuleRspIce;
import segi.medicaltrans.base.dbsource.MtDbSourceRuleServiceIcePrx;
import segi.medicaltrans.common.report.monthrank.personal.PerVolMonthRptServiceIcePrx;
import segi.medicaltrans.common.report.organ.ReportOrganIce;
import segi.medicaltrans.common.report.organ.TransReportOrganServiceIcePrx;
import segi.medicaltrans.common.report.transschedule.TransScheduleByOrganIdIce;
import segi.medicaltrans.common.report.transschedule.TransScheduleGroupOrganMapIce;
import segi.medicaltrans.common.report.transschedule.TransScheduleIce;
import segi.medicaltrans.common.report.transschedule.TransScheduleServiceIcePrx;
import segi.medicaltrans.common.taskloop.MtTaskLoopCommonServiceIcePrx;
import segi.medicaltrans.common.taskloop.MtTaskLoopIceListReturn;
import segi.medicaltrans.common.taskloop.ReturnInteger;
import segi.medicaltrans.common.taskloop.TaskLoopStatusParam;
import segi.medicaltrans.common.transType.MtCommonTransTypeServiceIcePrx;
import segi.medicaltrans.common.transType.TransTypeInfo;
import segi.medicaltrans.common.transType.TransTypeInfoReturnIce;
import segi.medicaltrans.common.userhosp.UserHospCommonIce;
import segi.medicaltrans.common.userhosp.UserHospCommonServiceIcePrx;
import segi.medicaltrans.common.userhosp.UserHospIce;
import segi.medicaltrans.common.userhosp.UserHospRelIceListRsp;
import segi.medicaltrans.common.userhosp.UserHospRelReturnInfoIce;
import segi.medicaltrans.common.userhosp.UserIdsByHouseIdReturnIce;
import segi.medicaltrans.common.userposit.MtUserPositCommonServiceIcePrx;
import segi.medicaltrans.common.userposit.UserPositParam;
import segi.medicaltrans.common.userstatus.MtUserStatusServiceIcePrx;
import segi.medicaltrans.common.userstatus.UserLotStatusDetailRspIce;
import segi.medicaltrans.common.userstatus.UserLotStatusIce;
import segi.medicaltrans.common.userstatus.UserStatusDetailIce;
import segi.medicaltrans.common.userstatus.UserStatusDetailRspIce;
import segi.medicaltrans.common.userstatus.UserStatusIce;
import segi.medicaltrans.location.common.LocationInfoListReturn;
import segi.medicaltrans.location.common.LocationInfoListReturnIce;
import segi.medicaltrans.location.common.LocationInfoReturn;
import segi.medicaltrans.location.common.LocationInfoReturnIce;
import segi.medicaltrans.location.common.MtLocationCommonServiceIcePrx;
import segi.medicaltrans.location.common.MtLocationInfoIce;
import segi.medicaltrans.mttask.common.MtTaskCommonServiceIcePrx;
import segi.medicaltrans.mttask.track.ItemIce;
import segi.medicaltrans.mttask.track.MtTaskTrackServiceIcePrx;
import IceExt.IceClientUtil;

import com.segi.uhomecp.medicaltrans.callback.TaskTrackCallBackForCreateTask;
import com.segi.uhomecp.medicaltrans.callback.TaskTrackCallBackForEditTask;
import com.segi.uhomecp.medicaltrans.callback.TaskTrackCallBackForSaveMessage;
import com.segi.uhomecp.medicaltrans.callback.UserPositIceCallBackUpdateUserNewLocationList;
import com.segi.uhomecp.medicaltrans.callback.UserPositIceCallBackUpdateUserPosit;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.utils.UhomePropUtils;
import com.segi.uhomecp.wh.common.dto.ResultDto;
import com.segi.uhomecp.wh.common.utils.AppUtils;
/**
 * Title: MtCommonServiceUtils.java    
 * @Description: 医院中央运送通用业务工具类
 * @author zhangyang@segimail.com       
 * @created 2018年4月10日 下午8:32:52
 */
public class MtCommonServiceUtils {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(MtCommonServiceUtils.class);
    
    /**
     * @discription 运送类型公共service
     * @author zhangyang@segimail.com       
     * @created 2018年4月10日 下午8:34:43     
     * @return
     */
    private static MtCommonTransTypeServiceIcePrx getMtCommonTransTypeServiceIcePrx() {
    	return IceClientUtil.getServicePrxByClass(MtCommonTransTypeServiceIcePrx.class);
    }
    
    /**
     * 位置信息公告servcie（新）
     * @return
     */
    private static MtLocationCommonServiceIcePrx getMtLocationCommonServiceIcePrx(){
    	return IceClientUtil.getServicePrxByClass(MtLocationCommonServiceIcePrx.class);
    }
    
    /**
     * 
     * 类描述: 运输管理公共service
     * 创建人: liuyi@sige.com   
     * 创建时间: 2018年4月12日 上午11:09:08
     */
    private static MtTaskCommonServiceIcePrx getMtTaskCommonServiceIcePrx(){
		return IceClientUtil.getServicePrxByClass(MtTaskCommonServiceIcePrx.class);
	}
    
    /**
     * @discription 获取数据源
     * @author wangxiong@segimail.com       
     * @created 2018年8月11日 上午7:24:34     
     * @return
     */
    private static MtDbSourceRuleServiceIcePrx getMtDbSourceRuleServiceIcePrx() {
    	return IceClientUtil.getServicePrxByClass(MtDbSourceRuleServiceIcePrx.class);
    }
    
    /**
     * @discription 循环运输管理公共service
     * @author yangyh@segimail.com       
     * @created 2018年5月14日 上午10:29:46     
     * @return
     */
    private static MtTaskLoopCommonServiceIcePrx getMtTaskLoopCommonServiceIcePrx(){
    	return IceClientUtil.getServicePrxByClass(MtTaskLoopCommonServiceIcePrx.class);
    }
    
    /**
     * 医疗组织项目公共service
     * @author kinas
     * @return
     */
    private static TransReportOrganServiceIcePrx getTransReportOrganServiceIcePrx() {
    	return IceClientUtil.getServicePrxByClass(TransReportOrganServiceIcePrx.class);
    }
	
    /**
     * 医疗组织项目公共service
     * @author wangxiong
     * @return
     */
    private static TransScheduleServiceIcePrx getTransScheduleServiceIcePrx() {
    	return IceClientUtil.getServicePrxByClass(TransScheduleServiceIcePrx.class);
    }
    
    /**
     * @discription 人员位置公共service
     * @author yangyh@segimail.com       
     * @created 2018年6月1日 上午10:29:46     
     * @return
     */
    private static MtUserPositCommonServiceIcePrx getMtUserPositCommonServiceIcePrx(){
    	return IceClientUtil.getServicePrxByClass(MtUserPositCommonServiceIcePrx.class);
    }
    
    /**
     * @Title: getPerVolMonthRptServiceIcePrx   
     *  个人月运送量报表数据统计公共service
     * @author zhaoqing  
     * @return
     */
    private static PerVolMonthRptServiceIcePrx getPerVolMonthRptServiceIcePrx() {
    	return IceClientUtil.getServicePrxByClass(PerVolMonthRptServiceIcePrx.class);
    }
    
    /**
     * @Title: getUserHospCommonServiceIcePrx 
     * @Description: 人员科室信息 
     * @author liuyi@segimail.com 
     * @date 2018年9月11日下午5:56:44
     */
    private static UserHospCommonServiceIcePrx getUserHospCommonServiceIcePrx() {
    	return IceClientUtil.getServicePrxByClass(UserHospCommonServiceIcePrx.class);
    } 
    
    /**
     * @discription 运送单轨迹
     * @author zhangyang@segimail.com       
     * @created 2018年9月25日 下午4:31:54     
     * @return
     */
    private static MtTaskTrackServiceIcePrx getMtTaskTrackServiceIcePrx() {
    	return IceClientUtil.getServicePrxByClass(MtTaskTrackServiceIcePrx.class);
    } 
    
   /**
    * 切换员工上下班状态
    * @return
    */
    private static MtUserStatusServiceIcePrx getMtUserStatusServiceIcePrx() {
    	return IceClientUtil.getServicePrxByClass(MtUserStatusServiceIcePrx.class);
    } 
    
	/**
	 * @discription 根据运送类型小类Id List 查询运送类型信息
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月10日 下午8:44:36     
	 * @param transTypeIdList
	 * @return
	 */
	public static List<TransTypeInfo> getTransTypeInfoByTransTypeIdList(List<Integer> transTypeIdList) {
		if (!AppUtils.isNotEmpty(transTypeIdList)) {
			return new ArrayList<>();
		}
		TransTypeInfoReturnIce rsp = getMtCommonTransTypeServiceIcePrx().getTransTypeInfoByTransTypeIdList(transTypeIdList);
		if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
			return rsp.getResultList();
		}
		LOGGER.error("getTransTypeInfoByTransTypeIdList", rsp.getMessage());
		return new ArrayList<>();
	}
	
	/**
	 * @discription 根据运送类型小类Id List 查询运送类型信息
	 * @author zhangyang@segimail.com       
	 * @created 2018年4月10日 下午8:44:36     
	 * @param transTypeIdList
	 * @return
	 */
	public static TransTypeInfo getTransTypeInfoByTransTypeId(Integer transTypeId) {
		// 查询运输类型名称
        List<Integer> transTypeIdList = new ArrayList<>();
        transTypeIdList.add(transTypeId);
        List<TransTypeInfo> transTypeList = MtCommonServiceUtils.getTransTypeInfoByTransTypeIdList(transTypeIdList);
        if (AppUtils.isNotEmpty(transTypeList)) {
        	return transTypeList.get(0);
		}
		return null;
	}
	
	/**
	 * 
	 * 类描述: 根据organId和transTypeCode判断有没有使用中的类型
	 * 创建人: liuyi@sige.com   
	 * 创建时间: 2018年4月12日 上午11:12:25
	 */
//	public static RpcRespIce judgeTransType(String organId, String transTypeId) {
//		RpcRespIce rsp = getMtTaskCommonServiceIcePrx().judgeTransType(organId, transTypeId);
//		if(RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
//			return rsp;
//		}
//		LOGGER.error("judgeTransType",rsp.getMessage());
//		return null;
//	}
	
	/**
	 * 根据位置id查询是否有在途运送单
	 * @param locationId
	 * @return
	 */
//	public static RpcRespIce judgeOnWayTask(String locationId){
//		RpcRespIce rsp = getMtTaskCommonServiceIcePrx().judgeOnWayTask(locationId);
//		if(RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
//			return rsp;
//		}
//		LOGGER.error("judgeOnWayTask",rsp.getMessage());
//		return null;
//	}
	
	/**
	 * 根据项目id和位置id的list查位置信息list(缓存)
	 * @param locationIdList
	 * @return
	 */
	public static List<MtLocationInfoIce> getLocationInfoList(Integer organId,List<Integer> locationIdList){
		
		LocationInfoListReturnIce rsp = getMtLocationCommonServiceIcePrx().getLocationInfoByRefIdList(organId,locationIdList);
		if(RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
			return rsp.getLocationInfoList();
		}
		LOGGER.error("getLocationInfoList",rsp.getMessage());
		return new ArrayList<>();
	}
	
	/**
	 * 根据位置id的list查位置信息list(查数据库)
	 * @param locationIdList
	 * @return
	 */
	public static List<MtLocationInfoIce> getLocationInfoBylocationIdList(List<Integer> locationIdList){
		
		LocationInfoListReturnIce rsp = getMtLocationCommonServiceIcePrx().getLocationInfoBylocationIdList(locationIdList);
		if(RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
			return rsp.getLocationInfoList();
		}
		LOGGER.error("getLocationInfoList",rsp.getMessage());
		return new ArrayList<>();
	}
	
	/**
	 * 根据组织机构id和mac查位置信息
	 * @param organId
	 * @param mac
	 * @return
	 */
	public static MtLocationInfoIce getLocationInfoByMac(int organId, String mac){
		LocationInfoReturnIce rsp = getMtLocationCommonServiceIcePrx().getLocationInfoByMac(organId, mac);
		if(RpcError.SUCCESS.getCode().equals(rsp.getCode())){
			return rsp.getMtLocationInfoIce();
		}
		LOGGER.error("getLocationInfoByMac",rsp.getMessage());
		return new MtLocationInfoIce();
	}
	
	/**
	 * 每晚刷新位置缓存
	 * @return
	 */
	public static RpcRespIce refreshRedisLocaiton(){
		RpcRespIce rsp = getMtLocationCommonServiceIcePrx().refreshRedisLocaiton();
		if(RpcError.SUCCESS.getCode().equals(rsp.getCode())){
			return rsp;
		}
		LOGGER.error("refreshRedisLocaiton",rsp.getMessage());
		return new RpcRespIce();
	}
	
	/**
	 * 每晚删除人员位置信息
	 * @return
	 */
	public static RpcRespIce refreshRedisUserLocaiton(){
		RpcRespIce rsp = getMtLocationCommonServiceIcePrx().refreshRedisUserLocaiton();
		if(RpcError.SUCCESS.getCode().equals(rsp.getCode())){
			return rsp;
		}
		LOGGER.error("refreshRedisUserLocaiton",rsp.getMessage());
		return new RpcRespIce();
	}
	
	/**
	 * @discription 修改循环任务单状态
	 * @author yangyh@segimail.com       
	 * @created 2018年5月14日 上午10:35:26     
	 * @param taskLoopStatusParam
	 * @return
	 */
	public static RpcRespIce updateTaskLoopStatus(TaskLoopStatusParam taskLoopStatusParam) {
		RpcRespIce rsp = getMtTaskLoopCommonServiceIcePrx().updateTaskLoopStatus(taskLoopStatusParam);
		if(RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
			return rsp;
		}
		LOGGER.error("updateTaskLoopStatus",rsp.getMessage());
		return null;
	}
	
	/**
	 * @discription 根据项目id查询个人月运送量，并根据userId和organId分组
	 * @author yangyh@segimail.com       
	 * @created 2018年5月19日 上午10:01:18     
	 * @param organIdList
	 * @return
	 */
//	public static PersonalVolumeMonthReturnIce getPersonalVolumeMonth(List<Integer> organIdList, long monthFirstDay, long monthLastDay) {
//		PersonalVolumeMonthReturnIce rsp = getMtTaskCommonServiceIcePrx().getPersonalVolumeMonth(organIdList, monthFirstDay, monthLastDay);
//		if(RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
//			return rsp;
//		}
//		LOGGER.error("getPersonalVolumeMonth",rsp.getMessage());
//		return null;
//	}
	
	/**
	 * 新增医疗组织机构
	 * @param organId
	 */
	public static void addOrgan(String organId) {
		try {
			RpcRespIce response = getTransReportOrganServiceIcePrx().add(Integer.parseInt(organId));
			if(RpcError.FAIL.getCode().equals(response.getCode())) {
				LOGGER.error("调用ICE服务异常",response.getMessage());
			}
		} catch (Exception e) {
			LOGGER.error("addOrgan",e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取所有医疗组织机构
	 * @return
	 */
	public static List<Integer> queryOrgan() {
		ReportOrganIce response = getTransReportOrganServiceIcePrx().getOragnList();
		if(RpcError.SUCCESS.getCode().equals(response.getCode())) {
			return response.getOrganIds();
		}
		
		LOGGER.error("调用ICE服务异常",response.getMessage());
		
		return null;
	}
	
	/**
	 * 修改人员状态和未完成任务数
	 * @param userPositParamList
	 */
	public static void updateUserPositInfo(int organId, List<Integer> userIds, short unTaskNum, short runTaskNum,String executeDate,String locaitonId) {
		UserPositIceCallBackUpdateUserPosit callBack 
			= new UserPositIceCallBackUpdateUserPosit();
		getMtUserPositCommonServiceIcePrx().begin_updateUserPositInfo(organId,
				userIds, unTaskNum, runTaskNum, executeDate,locaitonId, callBack);
	}
	
	/**
	 * 更新人员最新位置
	 * @param userPositParamList（组织机构id、人员id，位置id必传）
	 * @author dengdong@segimail.com  
	 */
	public static void updateUserNewLocation(List<UserPositParam> userPositParamList){
		try {
			UserPositIceCallBackUpdateUserNewLocationList callBack
				= new UserPositIceCallBackUpdateUserNewLocationList();
			getMtUserPositCommonServiceIcePrx().begin_updateUserNewLocationList(userPositParamList, callBack);
		} catch (Exception e) {
			LOGGER.error("updateUserNewLocation",e);
			e.printStackTrace();
			throw new RuntimeException("更新人员位置异常!");
		}
	}
	
	/**
	 * 更新人员最新位置
	 * @param userPositParamList（组织机构id、人员id，位置id必传）
	 * @author dengdong@segimail.com  
	 */
	public static void updateUserNewLocation(UserPositParam userPositParam){
		List<UserPositParam> userPositParamList = new ArrayList<>();
		userPositParamList.add(userPositParam);
		updateUserNewLocation(userPositParamList);
	}
	
	/**
	 * 更新人员最新位置
	 * @param userPositParamList（组织机构id、人员id，位置id必传）
	 * @author dengdong@segimail.com  
	 */
	public static void updateUserNewLocation(Integer organId, Integer userId, Integer locationId){
		UserPositParam userPositParam = new UserPositParam();
		userPositParam.setOrganId(String.valueOf(organId));
		userPositParam.setUserId(String.valueOf(userId));
		userPositParam.setLocationId(String.valueOf(locationId));
		updateUserNewLocation(userPositParam);
	}
	
	/**
	 * 更新人员最新位置
	 * @param userPositParamList（组织机构id、人员id，位置id必传）
	 * @author dengdong@segimail.com  
	 */
	public static void updateUserNewLocation(Integer organId, List<Integer> userIds, Integer locationId){
		if(AppUtils.isNotEmpty(userIds)) {
			List<UserPositParam> list = new ArrayList<>();
			UserPositParam userPositParam = null;
			for (Integer userId : userIds) {
				userPositParam = new UserPositParam();
				userPositParam.setOrganId(String.valueOf(organId));
				userPositParam.setUserId(String.valueOf(userId));
				userPositParam.setLocationId(String.valueOf(locationId));
				list.add(userPositParam);
			}
			updateUserNewLocation(list);
		}
	}
	
	/*超时未派单预警时间*/
	public static int getTaskNoSendWarnMinute() {
		return UhomePropUtils.getInt("NO_SEND_WARN_MINUTE",5);
	}
	
	/*超时未开始预警时间*/
	public static int getTaskNoStartWarnMinute() {
		return UhomePropUtils.getInt("NO_START_WARN_MINUTE",5);
	}
	
	/*超时未抢单预警时间*/
	public static int getTaskNoRobWarnMinute() {
		return UhomePropUtils.getInt("NO_ROB_WARN_MINUTE",5);
	}
	
	/**
	 * @Title: savePersonalVolumeIncrease   
	 *  个人当月运送量排名增量保存缓存信息接口
	 * @author zhaoqing  
	 * @param organId
	 * @param userIds
	 * @param transCount 
	 * @param exeBeginTime 任务实际开始时间，格式：YYYYMMDDHHMMSS
	 * @param taskType 
	 */
	public static void savePersonalVolumeIncrease(int organId, 
			String userIds, int transCount, String exeBeginTime, String taskType) {
		try {
			RpcRespIce response = getPerVolMonthRptServiceIcePrx()
					.savePersonalVolumeIncrease(organId, userIds, transCount, exeBeginTime, taskType);
			if(RpcError.FAIL.getCode().equals(response.getCode())) {
				LOGGER.error("调用ICE服务异常", response.getMessage());
			}
		} catch (Exception e) {
			LOGGER.error("savePersonalVolumeIncrease",e);
		}
	}
	
	/**
	 * 
	 * @Title: queryLocationInfoPage 
	 * @Description: 科室信息列表 
	 * @author liuyi@segimail.com 
	 * @date 2018年7月24日下午2:23:32
	 */
	public static LocationInfoListReturn queryLocationInfoList(List<Integer> param){
		LocationInfoListReturn rsp = getMtLocationCommonServiceIcePrx().queryLocationList(param);
		if(RpcError.SUCCESS.getCode().equals(rsp.getCode())){
			return rsp;
		}
		LOGGER.error("queryLocationInfoList",rsp.getMsg());
		return new LocationInfoListReturn();
	}

	
	/**
	 * @discription 根据organId查询循环任务List(月)
	 * @author yangyh@segimail.com       
	 * @created 2018年8月1日 上午11:17:18     
	 * @param organId
	 * @return
	 */
	public static MtTaskLoopIceListReturn getMtTaskLoopListByMonth(Integer organId) {
		MtTaskLoopIceListReturn rsp = getMtTaskLoopCommonServiceIcePrx().getMtTaskLoopListByMonth(organId);
		if(RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
			return rsp;
		}
		LOGGER.error("getMtTaskLoopListByMonth",rsp.getMsg());
		return null;
	}
	
	/**
	 * @discription 根据organId查询循环任务List(周)
	 * @author yangyh@segimail.com       
	 * @created 2018年8月1日 上午11:17:18     
	 * @param organId
	 * @return
	 */
	public static MtTaskLoopIceListReturn getMtTaskLoopListByWeek(Integer organId) {
		MtTaskLoopIceListReturn rsp = getMtTaskLoopCommonServiceIcePrx().getMtTaskLoopListByWeek(organId);
		if(RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
			return rsp;
		}
		LOGGER.error("getMtTaskLoopListByWeek",rsp.getMsg());
		return null;
	}
	
	/**
	 * @discription 根据循环任务主键id查询科室idList
	 * @author yangyh@segimail.com       
	 * @created 2018年8月1日 上午11:23:22     
	 * @param taskLoopId
	 * @return
	 */
	public static ReturnInteger selectLocationIdList(String taskLoopId) {
		ReturnInteger rsp = getMtTaskLoopCommonServiceIcePrx().selectLocationIdList(taskLoopId);
		if(RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
			return rsp;
		}
		LOGGER.error("selectLocationIdList",rsp.getMsg());
		return null;
	}
	
	/**
	 * @discription 根据循环任务主键id查询用户idList
	 * @author yangyh@segimail.com       
	 * @created 2018年8月1日 上午11:23:53     
	 * @param taskLoopId
	 * @return
	 */
	public static ReturnInteger selectUserIdList(String taskLoopId) {
		ReturnInteger rsp = getMtTaskLoopCommonServiceIcePrx().selectUserIdList(taskLoopId);
		if(RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
			return rsp;
		}
		LOGGER.error("selectUserIdList",rsp.getMsg());
		return null;
	}
	
	/**
	 * @discription 运送任务预约时间7天没完成异常关闭(除取消/完成状态)
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月8日 下午5:42:00     
	 * @param groupOrganId
	 * @param organIdList
	 * @param limitDate
	 * @return
	 */
	public static RpcRespIce updateTaskUnusualClose(int groupOrganId,
			List<Integer> organIdList, long limitDate) {
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			rsp = getMtTaskCommonServiceIcePrx().updateTaskUnusalClose(groupOrganId, organIdList, limitDate);
			if (!RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				LOGGER.error("updateTaskUnusualClose", rsp.getMessage());
			}
		} catch (Exception e) {
			LOGGER.error("updateTaskUnusualClose", e);
			e.printStackTrace();
		}
		return rsp;
	}
	
	/**
	 * 运送类型刷新
	 * @return
	 */
	public static RpcRespIce refreshRedisTransType(String organIds){
		RpcRespIce rsp = getMtCommonTransTypeServiceIcePrx().refreshRedisTransType(organIds);
		if(RpcError.SUCCESS.getCode().equals(rsp.getCode())){
			return rsp;
		}
		LOGGER.error("refreshRedisTransType",rsp.getMessage());
		return new RpcRespIce();
	}
	
	/**
	 * @discription 查询排程表,返回以groupOrganId为key,organId的集合为value的map
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月9日 下午4:19:02     
	 * @return
	 */
	public static Map<Integer, List<Integer>> getTransScheduleMap() {
		Map<Integer, List<Integer>> organMap = new HashMap<>();
		try {
			TransScheduleGroupOrganMapIce rsp = getTransScheduleServiceIcePrx().getTransScheduleMap();
			if(!RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				LOGGER.error("调用ICE服务异常", rsp.getMessage());
			}
			organMap = rsp.getGroupOrganMap();
		} catch (Exception e) {
			LOGGER.error("getTransScheduleMap", e);
			e.printStackTrace();
		}
		return organMap;
	}
	
	/**
	 * @discription 通过organId获取排程表信息
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月9日 下午5:36:32     
	 * @param organId
	 * @return
	 */
	public static ResultDto<String, String, TransScheduleIce> queryTransSchedule(int organId) {
		ResultDto<String, String, TransScheduleIce> rst = new ResultDto<>(true, "");
		if (StringUtils.isBlank(String.valueOf(organId))) {
			rst.setIsSucc(false);
			rst.setMessage("organId不可为空");
			return rst;
		}
		try {
			TransScheduleByOrganIdIce rsp = getTransScheduleServiceIcePrx().queryTransSchedule(organId);
			if(!RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				LOGGER.error("调用ICE服务异常", rsp.getMessage());
			}
			rst.setObj(rsp.getTransScheduleIce());
		} catch (Exception e) {
			LOGGER.error("queryTransSchedule", e);
			e.printStackTrace();
		}
		return rst;
	}
	
	/**
	 * @discription 根据groupId 获取数据源路径
	 * @author wangxiong@segimail.com       
	 * @created 2018年8月11日 上午7:25:39     
	 * @param groupId
	 * @return
	 */
	public static DbSourceRuleIce queryDbSourceRuleByGroupId(int groupOrganId) {
		if (groupOrganId == 0) {
			throw new RuntimeException("groupOrganId 不可为空");
		}
		try {
			DbSourceRuleRspIce rsp = getMtDbSourceRuleServiceIcePrx().getDbSourceRuleByGroupId(groupOrganId);
			if(!RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				LOGGER.error("调用ICE服务异常", rsp.getMessage());
				throw new RuntimeException("groupId{}"+groupOrganId+rsp.getMessage()+"获取数据源错误，请联系管理员");
			}
			return rsp.getDbSourceRuleIce();
		} catch (Exception e) {
			LOGGER.error("queryDataSourceByGroupId", e);
			e.printStackTrace();
			throw new RuntimeException("groupId{}"+groupOrganId+e.getMessage()+"获取数据源错误，请联系管理员");
		}
	}
	
	/**
	 * @Title: queryLocationInfoByOrganIdRedis 
	 * @Description:  缓存查询科室信息  
	 * @author liuyi@segimail.com 
	 * @date 2018年8月11日下午3:46:06
	 */
	public static LocationInfoListReturn queryLocationInfoByOrganIdRedis(int organId) {
		if (organId == 0) {
			throw new RuntimeException("organIdList 不可为空");
		}
		try {
			LocationInfoListReturn rsp = getMtLocationCommonServiceIcePrx().queryLocationInfoByOrganIdList(organId);
			if(!RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				LOGGER.error("调用ICE服务异常", rsp.getMsg());
				throw new RuntimeException("organId{}"+organId+rsp.getMsg()+"缓存查询科室信息  ，请联系管理员");
			}
			return rsp;
		} catch (Exception e) {
			LOGGER.error("queryLocationInfoByOrganIdRedis", e);
			e.printStackTrace();
			throw new RuntimeException("organId{}"+organId+e.getMessage()+"缓存查询科室信息  ，请联系管理员");
		}
	}
	
	/**
	 * @Title: queryHospUserInfo 
	 * @Description:   用户项目查询科室信息
	 * @author liuyi@segimail.com 
	 * @date 2018年9月11日下午6:03:09
	 */
	public static UserHospCommonIce queryHospUserInfo(String organId, String userId){
		UserHospCommonIce userHospIce = new UserHospCommonIce();
		UserHospIce ice = new UserHospIce();
		ice.setOrganId(organId);
		ice.setUserId(userId);
		UserHospRelReturnInfoIce rsp = getUserHospCommonServiceIcePrx().queryHospUserInfo(ice);
		if(RpcError.SUCCESS.getCode().equals(rsp.getCode())){
			return rsp.getUserHospCommonIce();
		}
		LOGGER.error("queryHospUserInfo",rsp.getMessage());
		return userHospIce;
	}
	
	/**
	 * @Title: queryLocationInfo 
	 * @Description: 通过organId  locationId 查询缓存科室信息
	 * @author liuyi@segimail.com 
	 * @date 2018年9月17日上午10:57:39
	 */
	public static LocationInfoReturn queryLocationInfo(Integer organId, Integer locationId){
		LocationInfoReturn rsp = getMtLocationCommonServiceIcePrx().queryLocationInfo(organId, locationId);
		if(RpcError.SUCCESS.getCode().equals(rsp.getCode())){
			return rsp;
		}
		LOGGER.error("queryLocationInfo", rsp.getMsg());
		return rsp;
	}
	
	/**
	 * @Title: queryHospUserInfoList   
	 *  用户项目查询科室信息列表 
	 * @author zhaoqing  
	 * @param organId
	 * @param userIdList
	 * @return 
	 */
	public static List<UserHospCommonIce> queryHospUserInfoList(
			String organId, List<Integer> userIdList){
		UserHospRelIceListRsp rsp = new UserHospRelIceListRsp();
		LOGGER.debug("MtCommonServiceUtils queryHospUserInfoList ==> organId:{}, "
				+ "userIdList:{}", organId, FastjsonUtils.toJsonString(userIdList));
		if (StringUtils.isEmpty(organId) || !AppUtils.isNotEmpty(userIdList)) {
			return rsp.getRerultList();
		}
		UserHospIce ice = new UserHospIce();
		ice.setOrganId(organId);
		ice.setUserIdList(userIdList);
		rsp = getUserHospCommonServiceIcePrx().queryHospUserInfoList(ice);
		if(!RpcError.SUCCESS.getCode().equals(rsp.getCode())){
			LOGGER.error("调用ICE服务异常", rsp.getMessage());
			throw new RuntimeException("queryHospUserInfo: 用户项目查询科室信息列表失败 ,请联系管理员");
		}
		return rsp.getRerultList();
	}
	
	/**
	 * @discription 通过科室ID查询科室人员
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月17日 下午4:51:46     
	 * @param houseId
	 * @return
	 */
	public static List<String> queryUserIdsByHouseId(Integer houseId) {
		List<String> userIds = new ArrayList<String>();
		try {
			UserIdsByHouseIdReturnIce rsp = 
					getUserHospCommonServiceIcePrx().queryUserIdsByHouseId(String.valueOf(houseId));
			if (RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				userIds = rsp.getUserIdList();
			}
		} catch (Exception e) {
			LOGGER.error("queryUserIdsByHouseId", e);
			e.printStackTrace();
		}
		return userIds;
	}
	
	/**
	 * @discription 创建任务时初次保存轨迹信息
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月26日 上午11:17:42     
	 * @param taskId
	 * @param organId
	 */
	public static void saveTrackForCreateTask(String taskId, Integer organId, Long beginTime, List<ItemIce> itemIceList) {
		try {
			TaskTrackCallBackForCreateTask callBack = new TaskTrackCallBackForCreateTask();
			getMtTaskTrackServiceIcePrx().begin_saveTrackForCreateTask(taskId, organId, String.valueOf(beginTime), itemIceList, callBack);
		} catch (Exception e) {
			LOGGER.error("saveTrackForCreateTask", e);
			e.printStackTrace();
			throw new RuntimeException("创建任务时保存轨迹信息异常!");
		}
	}
	
	/**
	 * @discription 保存单挑轨迹信息
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月26日 上午11:19:15     
	 * @param taskId
	 * @param itemIce
	 */
	public static void saveTaskTrackMessage(Integer taskId, ItemIce itemIce) {
		try {
			TaskTrackCallBackForSaveMessage callBack = new TaskTrackCallBackForSaveMessage();
			getMtTaskTrackServiceIcePrx().begin_saveTaskTrackMessage(taskId, itemIce, callBack);
		} catch (Exception e) {
			LOGGER.error("saveTaskTrackMessage", e);
			e.printStackTrace();
			throw new RuntimeException("保存单条轨迹信息异常!");
		}
	}
	
	public static void editTaskTrackMessage(String taskId, Integer organId, Long beginTime) {
		try {
			TaskTrackCallBackForEditTask callBack = new TaskTrackCallBackForEditTask();
			getMtTaskTrackServiceIcePrx().begin_updateTrackForEditTask(taskId, organId, String.valueOf(beginTime), callBack);
		} catch (Exception e) {
			LOGGER.error("editTaskTrackMessage", e);
			e.printStackTrace();
			throw new RuntimeException("编辑轨迹信息异常!");
		}
	}
	
	/**
	 * @Title: queryLocationInfoByOrganIdList 
	 * @Description: 更具项目查科室信息 优化后
	 * @author liuyi@segimail.com 
	 * @date 2018年9月26日下午6:14:06
	 */
	public static List<MtLocationInfoIce> queryLocationInfoListByOrganId(
			Integer organId, List<Integer> houseIdList) {
		if (organId == 0) {
			throw new RuntimeException("organIdList 不可为空");
		}
		try {
			LocationInfoListReturnIce rsp = getMtLocationCommonServiceIcePrx().queryLocationInfoListByOrganId(organId, houseIdList);
			if(!RpcError.SUCCESS.getCode().equals(rsp.getCode())) {
				LOGGER.error("调用ICE服务异常", rsp.getMessage());
				throw new RuntimeException("organId{}"+organId+rsp.getMessage()+"缓存查询科室信息  ，请联系管理员");
			}
			return rsp.getLocationInfoList();
		} catch (Exception e) {
			LOGGER.error("queryLocationInfoByOrganIdRedis", e);
			e.printStackTrace();
			throw new RuntimeException("organId{}"+organId+e.getMessage()+"缓存查询科室信息  ，请联系管理员");
		}
	}
	
	/**
	 * @discription 删除创建时间超过90天的导入批次和中间表
	 * @author zhangyang@segimail.com       
	 * @created 2018年10月9日 上午11:52:59     
	 * @return
	 */
	public static RpcRespIce delTransTypeImpTable() {
		return getMtCommonTransTypeServiceIcePrx().delTransTypeImpTable();
	}

	/**
	 * @discription 将创建时间超过7天 状态不是完成的批次表记录 状态改为失效
	 * @author zhangyang@segimail.com       
	 * @created 2018年10月9日 上午11:53:44     
	 * @return
	 */
	public static RpcRespIce updateTransTypeImpBatchMsg() {
		return getMtCommonTransTypeServiceIcePrx().updateTransTypeImpBatchMsg();
	}
	
	/**
	 * 更新人员上下班状态
	 * @return
	 */
	public static UserStatusDetailRspIce updateStausByUser(UserStatusIce userStatusIce){
		UserStatusDetailRspIce rsp = getMtUserStatusServiceIcePrx().updateStausByUser(userStatusIce);
		if(RpcError.SUCCESS.getCode().equals(rsp.getCode())){
			return rsp;
		}
		LOGGER.error("updateStausByUser",rsp.getMessage());
		return new UserStatusDetailRspIce();
	}
	
	/**
	 * 查询人员上下班状态
	 * @return
	 */
	public static UserStatusDetailRspIce getStatusByUser(String organId,String userId){
		UserStatusDetailRspIce rsp = new UserStatusDetailRspIce();
		if(organId == null){
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
			LOGGER.error("getStatusByUser","organId不能为空");
			return rsp;
		}
		if(userId == null){
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
			LOGGER.error("getStatusByUser","userId不能为空");
			return rsp;
		}
		UserStatusIce userStatusIce = new UserStatusIce();
		userStatusIce.setOrganId(organId);
		userStatusIce.setUserId(userId);
		UserStatusDetailRspIce userStatusDetailRspIce = getMtUserStatusServiceIcePrx().getStatusByUser(userStatusIce);
		return userStatusDetailRspIce;
	}
	
	/**
	 * 批量查询人员上下班状态接口
	 * @param organId
	 * @param userIdList
	 * @return
	 */
	public static UserLotStatusDetailRspIce getLotStatusByUser(String organId,List<String> userIdList){
		UserLotStatusDetailRspIce rsp = new UserLotStatusDetailRspIce();
		if(organId == null){
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
			LOGGER.error("getLotStatusByUser","organId不能为空");
			return rsp;
		}
		if(!AppUtils.isNotEmpty(userIdList)){
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage(RpcError.FAIL.getMessage());
			LOGGER.error("getLotStatusByUser","userIdList不能为空");
			return rsp;
		}
		UserLotStatusIce userLotStatusIce = new UserLotStatusIce();
		userLotStatusIce.setOrganId(organId);
		userLotStatusIce.setUserList(userIdList);
		UserLotStatusDetailRspIce userLotStatusDetailRspIce = getMtUserStatusServiceIcePrx().getLotStatusByUser(userLotStatusIce);
		return userLotStatusDetailRspIce;
	}
	
}
















