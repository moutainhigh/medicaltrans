//package com.segi.uhomecp.medicaltrans.cache;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import com.segi.uhomecp.common.model.ResultInfo;
//import com.segi.uhomecp.medicaltrans.base.posit.bean.MtCurUserPosit;
//import com.segi.uhomecp.medicaltrans.constant.MedicalTransRedisConstant;
//import com.segi.uhomecp.medicaltrans.seriable.KryoSeriableStringUtils;
//import com.segi.uhomecp.redis.cluster.SegiRedisClusterBuilder;
//import com.segi.uhomecp.utils.DateUtil;
//
///**
// * 人员最新位置
// * @author Jimmy
// * 2018-3-22
// */
//@Component
//public class CurUserPositRedisCache {
//	public static final Logger logger = LoggerFactory.getLogger(CurUserPositRedisCache.class);
//	
//	@Resource(name="segiRedisCluster")
//	private SegiRedisClusterBuilder segiRedisClusterBuilder;
//	
//	/**
//	 * 删除所有人员位置的缓存
//	 */
//	public void deleteAll() {
//		try {
//			Long count = this.segiRedisClusterBuilder.getSegiRedisCluster().hlen(MedicalTransRedisConstant.CUR_USER_POSIT);
//			if (count != null && count.longValue() > 0) {
//				this.segiRedisClusterBuilder.getSegiRedisCluster().hdel(MedicalTransRedisConstant.CUR_USER_POSIT);
//			}
//		} catch (Exception e) {
//			logger.error("删除所有人员位置缓存失败", e);
//		}
//	}
//	
//	/**
//	 * 获取organId下所有的人员位置信息
//	 * @param organId
//	 * @return Map<用户Id， 用户位置对象>
//	 */
//	public Map<String, MtCurUserPosit> getUserPosit(Integer organId) {
//		if (organId == null) {
//			return null;
//		}
//		String findValue = null;
//		try {
//			findValue = this.segiRedisClusterBuilder.getSegiRedisCluster().hget(MedicalTransRedisConstant.CUR_USER_POSIT, String.valueOf(organId));
//		} catch (Exception e) {
//			logger.warn("organId=" + organId + "，获取redis缓存失败", e);
//		}
//		if (findValue == null || "".equals(findValue)) {
//			return null;
//		}
//		try {
//			return KryoSeriableStringUtils.deSerialMap(findValue, MtCurUserPosit.class);
//		} catch (Exception e) {
//			logger.warn("Kryo反序列化用户位置数据失败", e);
//		}
//		return null;
//	}
//	
//	/**
//	 * 通过organId和userId获取某个人员最新的位置信息
//	 * @param organId
//	 * @param userId
//	 * @return
//	 */
//	public MtCurUserPosit getUserPosit(Integer organId, Integer userId) {
//		if (organId == null || userId == null) {
//			return null;
//		}
//		Map<String, MtCurUserPosit> findMap = getUserPosit(organId);
//		if (findMap == null) {
//			return null;
//		}
//		return findMap.get(String.valueOf(userId));
//	}
//	
//	/**
//	 * 更新当前人员位置缓存
//	 * @param curUserPosit
//	 * @return
//	 */
//	public ResultInfo update(MtCurUserPosit curUserPosit) {
//		ResultInfo resultInfo = new ResultInfo();
//		if (curUserPosit.getOrganId() == null 
//				|| curUserPosit.getUserId() == null) {
//			resultInfo.setIsSucc(false);
//			resultInfo.setMessage("刷新当前人员位置缓存失败,organId为空或userId为空.");
//			return resultInfo;
//		}
//		//从缓存中获取当前人员位置缓存
//		Map<String, MtCurUserPosit> findMap = this.getUserPosit(curUserPosit.getOrganId());
//		if (findMap == null) {
//			findMap = new HashMap<String, MtCurUserPosit>();
//		}
//		curUserPosit.setLastUpdateTime(DateUtil.nowDateToStringYYMMDDHHmmss());
//		findMap.put(String.valueOf(curUserPosit.getUserId()), curUserPosit);
//		String ttValue = null;
//		
//		try {
//			ttValue = KryoSeriableStringUtils.serialMap(findMap, MtCurUserPosit.class);
//		} catch (Exception e) {
//			logger.warn("缓存人员位置失败，序列化失败", e);
//			resultInfo.setMessage("缓存人员位置失败，序列化失败");
//			resultInfo.setIsSucc(false);
//			return resultInfo;
//		}
//		
//		try {
//			this.segiRedisClusterBuilder.getSegiRedisCluster().hset(MedicalTransRedisConstant.CUR_USER_POSIT, 
//					String.valueOf(curUserPosit.getOrganId()), ttValue);
//		} catch (Exception e) {
//			logger.warn("Redis缓存人员位置失败", e);
//			resultInfo.setMessage("Redis缓存人员位置失败");
//			resultInfo.setIsSucc(false);
//			return resultInfo;
//		}
//		resultInfo.setIsSucc(true);
//		return resultInfo;
//	}
//}