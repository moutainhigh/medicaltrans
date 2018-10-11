package com.segi.uhomecp.wh.common.interfaces.impl;

import segi.datacachesvr.queryInfo.Organ;

import com.segi.uhomecp.wh.common.interfaces.InvocationHandler;

/**
 * @ClassName:  OrganInvocationImpl   
 * @Description:组织信息 InvocationHandler实现类
 * @author: zhaoqing
 * @date:   2018年1月22日 下午5:56:56
 */
public class OrganInvocationImpl  implements InvocationHandler<Integer, Organ>{

	@Override
	public Integer invoke(Organ obj) {
		return obj.getOrganId();
	}	

}
