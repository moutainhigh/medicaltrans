package com.segi.uhomecp.wh.common.utils;

import page.RpcPageIce;

import com.github.miemiedev.mybatis.paginator.domain.Paginator;

/**
 * Title: LsPageUtils.java    
 * @Description: 分页工具类
 * @author wangfan@segimail.com       
 * @created 2017年10月12日 下午3:44:04
 */
public class PageUtils {
	/**
	 *
	 * @Description (分页对象拷贝)
	 * @param paginator
	 * @return
	 */
	public static RpcPageIce paginator2RpcPageIce(Paginator paginator) {
	    if (null == paginator) {
           return new RpcPageIce("10", "0", "0");
	    }
		return new RpcPageIce(String.valueOf(paginator.getLimit()),
				String.valueOf(paginator.getPage()), 
				String.valueOf(paginator.getTotalCount()));
	}
	
	/**
	 *
	 * @Description (分页对象拷贝)
	 * @param paginator
	 * @return
	 */
	public static RpcPageIce paginator2RpcPageIce(int curPage, int pageSize, int totalCount) {
	    return new RpcPageIce(String.valueOf(pageSize), String.valueOf(curPage), String.valueOf(totalCount));
	}
}
