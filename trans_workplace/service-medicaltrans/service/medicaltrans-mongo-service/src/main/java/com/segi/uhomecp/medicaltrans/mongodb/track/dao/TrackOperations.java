package com.segi.uhomecp.medicaltrans.mongodb.track.dao;

import java.util.List;

import com.segi.uhomecp.medicaltrans.mongodb.track.entity.Item;
import com.segi.uhomecp.medicaltrans.mongodb.track.entity.TaskTrack;

/**
 * 运输单轨迹处理类
 *     
 * 包: com.segi.uhomecp.medicaltrans.mongodb.trail.dao 
 * 类名称: TrackOperations.java
 * 类描述: 
 * 创建人: kinas    
 * 创建时间: 上午11:50:58
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
public interface TrackOperations {
	/**
	 * 创建运单跟踪记录
	 * @param task
	 */
	public void save(TaskTrack track);
	/**
	 * 编辑运单跟踪记录
	 * @param task
	 */
	public void update(TaskTrack track);
	/**
	 * 追加跟踪记录
	 * @param id 
	 * @param itemList
	 */
	public void push(Integer id, List<Item> itemList);
	/**
	 * 追加跟踪记录
	 * @param id 
	 * @param item
	 */
	public void push(Integer id, Item item);
	/**
	 * 跟踪记录查询
	 * @param id
	 * @return
	 */
	public TaskTrack findById(Integer id);
	
}
