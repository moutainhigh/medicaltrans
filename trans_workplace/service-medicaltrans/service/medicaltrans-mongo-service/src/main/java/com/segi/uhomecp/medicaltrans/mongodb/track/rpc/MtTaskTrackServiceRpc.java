package com.segi.uhomecp.medicaltrans.mongodb.track.rpc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import resp.RpcRespIce;
import segi.medicaltrans.mttask.track.ItemIce;
import segi.medicaltrans.mttask.track.TrackDetailIce;
import segi.medicaltrans.mttask.track.TrackDetailRspIce;
import segi.medicaltrans.mttask.track._MtTaskTrackServiceIceDisp;
import Ice.Current;

import com.segi.uhomecp.medicaltrans.enums.TaskTrackActionCodeEnum;
import com.segi.uhomecp.medicaltrans.enums.TaskTrackActionEnum;
import com.segi.uhomecp.medicaltrans.mongodb.track.dao.TrackOperations;
import com.segi.uhomecp.medicaltrans.mongodb.track.entity.Item;
import com.segi.uhomecp.medicaltrans.mongodb.track.entity.TaskTrack;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.BeanCopierUtils;

@Component
public class MtTaskTrackServiceRpc extends _MtTaskTrackServiceIceDisp{
	
	private static final long serialVersionUID = 1;

	public static final Logger logger = LoggerFactory.getLogger(MtTaskTrackServiceRpc.class);
	
	@Autowired
	private TrackOperations trackOperations;

	/**
	 * @discription 创建任务时保存轨迹
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月26日 下午1:17:16      
	 * @param taskId
	 * @param organId
	 * @param itemIceList
	 * @param __current
	 * @return     
	 */
	@Override
	public RpcRespIce saveTrackForCreateTask(String taskId, int organId,
			String beginTime, List<ItemIce> itemIceList, Current __current) {
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			TaskTrack track = new TaskTrack();
			track.setId(Long.valueOf(taskId));
			track.setTaskId(Integer.valueOf(taskId));
			track.setOrganId(organId);
			track.setBeginTime(Long.valueOf(beginTime));
			track.setFlowList(itemIceList);
			trackOperations.save(track);
		} catch (Exception e) {
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("系统异常，创建任务时保存轨迹失败！");
			logger.error("MtTaskTrackServiceRpc->saveTrackForCreateTask:{}", e);
		}
		return rsp;
	}

	/**
	 * @discription 保存单个轨迹
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月26日 下午1:16:56      
	 * @param taskId
	 * @param itemIce
	 * @param __current
	 * @return     
	 */
	@Override
	public RpcRespIce saveTaskTrackMessage(int taskId, ItemIce itemIce,
			Current __current) {
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			Item item = BeanCopierUtils.copyProperties(itemIce, Item.class, true);
			trackOperations.push(taskId, item);
		} catch (Exception e) {
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("系统异常，保存轨迹失败！");
			logger.error("MtTaskTrackServiceRpc->saveTaskTrackMessage:{}", e);
		}
		return rsp;
	}

	/**
	 * @discription 查询任务轨迹信息
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月26日 下午3:55:49      
	 * @param id
	 * @param __current
	 * @return     
	 */
	@Override
	public TrackDetailRspIce queryTaskTrackById(int id, Current __current) {
		TrackDetailRspIce rsp = new TrackDetailRspIce(RpcError.SUCCESS.getCode(), 
				RpcError.SUCCESS.getMessage(), new ArrayList<TrackDetailIce>());
		try {
			TaskTrack taskTrack = trackOperations.findById(id);
			if (null != taskTrack) {
				LinkedList<TrackDetailIce> trackLinkedList = new LinkedList<>();
				TrackDetailIce track = null;
				for (Object item : taskTrack.getFlowList()) {
					track = BeanCopierUtils.copyProperties(item, TrackDetailIce.class, true);
					track.setActionName(TaskTrackActionEnum.getNameByAction(track.getAction()));
					track.setAction(TaskTrackActionCodeEnum.getCodeByAction(track.getAction()));
					trackLinkedList.addFirst(track);
				}
				rsp.setResultList(BeanCopierUtils.copyList2List(trackLinkedList, TrackDetailIce.class, true));
			}
		} catch (Exception e) {
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("系统异常，查询轨迹失败！");
			logger.error("MtTaskTrackServiceRpc->queryTaskTrackById:{}", e);
		}
		return rsp;
	}

	/**
	 * @discription 编辑任务后修改了预约时间 处理轨迹
	 * @author zhangyang@segimail.com       
	 * @created 2018年9月30日 下午2:41:19      
	 * @param taskId
	 * @param organId
	 * @param beginTime
	 * @param __current
	 * @return     
	 */
	@Override
	public RpcRespIce updateTrackForEditTask(String taskId, int organId,
			String beginTime, Current __current) {
		RpcRespIce rsp = new RpcRespIce(RpcError.SUCCESS.getCode(), RpcError.SUCCESS.getMessage(), "");
		try {
			TaskTrack track = new TaskTrack();
			track.setId(Long.valueOf(taskId));
			track.setTaskId(Integer.valueOf(taskId));
			track.setOrganId(organId);
			track.setBeginTime(Long.valueOf(beginTime));
			trackOperations.update(track);
		} catch (Exception e) {
			rsp.setCode(RpcError.FAIL.getCode());
			rsp.setMessage("系统异常，更新轨迹失败！");
			logger.error("MtTaskTrackServiceRpc->updateTrackForEditTask:{}", e);
		}
		return rsp;
	}

}
