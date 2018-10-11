package com.segi.uhomecp.medicaltrans.trans.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.constant.MedicalTransRedisConstant;
import com.segi.uhomecp.medicaltrans.enums.TransStatusEnum;
import com.segi.uhomecp.medicaltrans.trans.mttask.model.MtTask;
import com.segi.uhomecp.medicaltrans.trans.service.MtTaskQueryService;
import com.segi.uhomecp.redis.cluster.SegiRedisClusterBuilder;
import com.segi.uhomecp.utils.FastjsonUtils;
import com.segi.uhomecp.wh.common.utils.AppUtils;

/**
 * 抢单redis缓存
 * @author wangxiong@segimail.com
 * 2018-3-21
 */
@Component
public class MtTaskGrabRedisCache {
	
	public static final Logger logger = LoggerFactory.getLogger(MtTaskGrabRedisCache.class);
	
	@Resource(name="segiRedisCluster")
	private SegiRedisClusterBuilder segiRedisClusterBuilder;
	
	@Autowired
	private MtTaskQueryService mtTaskQueryService;
	
	/**
	 * @discription 删除任务单
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月9日 下午7:41:54     
	 * @param organId
	 * @param buildId
	 */
	public void hdelGradTaskDep(Integer groupId, Integer taskId) {
		String key = MedicalTransRedisConstant.MT_DISPATCH_TASK_GRAB + groupId;
		try {
			this.segiRedisClusterBuilder.getSegiRedisCluster().zremrangeByScore(key, taskId, taskId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("hdelGradTaskDep",e);
			throw new RuntimeException("运送单缓存删除失败");
		}
	}

	/**
	 * @discription 批量删除任务单
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月9日 下午7:41:54     
	 * @param organId
	 * @param buildId
	 */
	public void hdelGradTaskDep(List<Integer> groupIds, Integer taskId) {
		for (Integer groupId : groupIds) {
			this.hdelGradTaskDep(groupId, taskId);
		}
	}
	
	/**
	 * @discription 调度工单redis保存
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月9日 下午7:40:56     
	 * @param mtTask
	 * @param groupId
	 */
	public void zaddGradTaskDep(Integer groupId, MtTask mtTask) {
		if (mtTask == null || mtTask.getTaskId() == null || groupId == null) {
			return;
		}
		try {
			//key:常量_项目Id， field楼栋Id，value:楼栋对象
			String key = MedicalTransRedisConstant.MT_DISPATCH_TASK_GRAB + groupId;
			String value = FastjsonUtils.toJsonString(mtTask);
			this.segiRedisClusterBuilder.getSegiRedisCluster().zadd(key, mtTask.getTaskId(), value);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("zaddGradTaskDep",e);
			throw new RuntimeException("抢单任务缓存保存失败");		
		}
	}
	
	/**
	 * @discription 批量增加到缓存
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月16日 下午2:20:25     
	 * @param serviceGroupIds
	 * @param mtTask
	 */
	public void zaddGradTaskDep(List<Integer> serviceGroupIds, MtTask mtTask) {
		for (Integer serviceGroupId : serviceGroupIds) {
			this.zaddGradTaskDep(serviceGroupId, mtTask);
		}
	}
	
	/**
	 * @discription 获取抢单的任务单
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月9日 下午7:40:56     
	 * @param mtTask
	 * @param groupId
	 */
	public List<MtTask> getGradTaskDepRedis(Integer groupId) {
		List<MtTask> list = new ArrayList<>();
		if (groupId == null) {
			return list;
		}
		String key = MedicalTransRedisConstant.MT_DISPATCH_TASK_GRAB + groupId;
		try {
			Set<String> valSet = this.segiRedisClusterBuilder.getSegiRedisCluster().zrange(key, 0, -1);
			if(AppUtils.isNotEmpty(valSet)) {
				for (String json : valSet) {
					list.add(FastjsonUtils.parseObject(json, MtTask.class));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getGradTaskDepRedis",e);
		}
		return list;
	}
	
	/**
	 * @discription 获取抢单的任务单
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月9日 下午7:40:56     
	 * @param mtTask
	 * @param groupId
	 */
	public List<MtTask> getGradTaskDepRedis(Integer groupId,
			int startRow, int endRow) {
		List<MtTask> list = new ArrayList<>();
		if (groupId == null) {
			return list;
		}
		String key = MedicalTransRedisConstant.MT_DISPATCH_TASK_GRAB + groupId;
		try {
			Set<String> valSet = this.segiRedisClusterBuilder
					.getSegiRedisCluster().zrange(key, startRow, endRow);
			if(AppUtils.isNotEmpty(valSet)) {
				for (String json : valSet) {
					list.add(FastjsonUtils.parseObject(json, MtTask.class));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getGradTaskDepRedis",e);	
		}
		return list;
	}
	
	/**
	 * @discription 获取抢单的任务单
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月9日 下午7:40:56     
	 * @param mtTask
	 * @param groupId
	 */
	public MtTask getGradTaskDepRedisByTaskId(int groupOrganId, Integer groupId, Integer taskId) {
		if (groupId == null || taskId == null ) {
			return null;
		}
		String key = MedicalTransRedisConstant.MT_DISPATCH_TASK_GRAB + groupId;
		try {
			Set<String> valSet = this.segiRedisClusterBuilder.getSegiRedisCluster()
					.rangeByScoreWithSortedSet(key, taskId.intValue(), taskId.intValue());
			if(AppUtils.isNotEmpty(valSet)) {
				return FastjsonUtils.parseObject(valSet.iterator().next(), MtTask.class);  
			}
			// 查询数据如果有就补偿一下数据
			MtTask mtTask = this.mtTaskQueryService.queryGradTaskByGroupTaskId(groupOrganId, groupId, taskId);
			if(mtTask != null && TransStatusEnum.TRANS_ROBBING.getCode().equals(mtTask.getStatus())) {
				zaddGradTaskDep(groupId,mtTask);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getGradTaskDepRedisByTaskId",e);	
		}
		return null;
	}
	
	/**
	 * @discription 获取一个服务处下，有多少抢单数据
	 * @author wangxiong@segimail.com       
	 * @created 2018年5月11日 下午5:16:21     
	 * @param groupId
	 * @return
	 */
	public Long countGradTaskDepByGroupId(Integer groupId) {
		String key = MedicalTransRedisConstant.MT_DISPATCH_TASK_GRAB + groupId;
		Long total = this.segiRedisClusterBuilder.getSegiRedisCluster().zcard(key);
		return total;
	}

}
