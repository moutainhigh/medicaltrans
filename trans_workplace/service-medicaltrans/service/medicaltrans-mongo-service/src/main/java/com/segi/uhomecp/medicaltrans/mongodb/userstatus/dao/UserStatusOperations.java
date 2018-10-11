package com.segi.uhomecp.medicaltrans.mongodb.userstatus.dao;


import com.segi.uhomecp.medicaltrans.mongodb.userstatus.entity.UserStautsCurrent;

/**
 * 人员当前状态处理类
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
public interface UserStatusOperations {
	/**
	 * 更改人员当前状态
	 * @param task
	 */
	public void save(UserStautsCurrent userStautsCurrent);
	
	
	/**
	 * 查询人员当前状态
	 * @param task
	 */
	public UserStautsCurrent findById(String id);
}
