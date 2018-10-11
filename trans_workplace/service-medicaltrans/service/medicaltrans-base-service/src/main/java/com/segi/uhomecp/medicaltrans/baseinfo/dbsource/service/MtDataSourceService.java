package com.segi.uhomecp.medicaltrans.baseinfo.dbsource.service;

import com.segi.uhomecp.medicaltrans.base.dbsourcerule.model.DbSourceRule;

public interface MtDataSourceService {
	
	/**
	 * @discription 通过groupOrganId查询数据源规则(数据库)
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月14日 下午2:30:17     
	 * @param groupOrganId
	 * @return
	 */
	public DbSourceRule queryDbSourceByGroupOrganId(int groupOrganId);
	
	/**
	 * @discription 同时保存数据源规则到数据库和缓存
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月14日 下午2:31:06     
	 * @param groupOrganId
	 * @param dbIdx
	 * @param tableIdx
	 */
	public DbSourceRule saveDbSource(int groupOrganId, String dbIdx, String tableIdx);
	
	/**
	 * @discription 通过groupOrganId查询数据源规则(数据库)
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月14日 下午2:31:36     
	 * @param groupOrganId
	 * @return
	 */
	public DbSourceRule getDbSourceByGroupOrganIdRedis(int groupOrganId);
	
	/**
	 * @discription 保存数据源规则到缓存
	 * @author zhangyang@segimail.com       
	 * @created 2018年8月14日 下午2:31:55     
	 * @param dbSourceRule
	 */
	public void addDbSourceRedis(DbSourceRule dbSourceRule);

	/**
	 * @discription 获取新的数据源规则数据并保存
	 * @author wangxiong@segimail.com       
	 * @created 2018年8月15日 上午10:09:39     
	 * @param groupOrganId
	 * @return 
	 */
	public DbSourceRule saveOrGetNewDbSourceRuleAndSave(int groupOrganId);
}
