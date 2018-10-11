package com.segi.uhomecp.medicaltrans.reportjob.dbsource;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.segi.uhomecp.common.dao.DynamicDataSourceKeyHolder;
import com.segi.uhomecp.medicaltrans.utils.DynamicTableSourceKeyHolder;
import com.segi.uhomecp.medicaltrans.utils.MtCommonServiceUtils;
import com.segi.uhomecp.utils.FastjsonUtils;

import segi.medicaltrans.base.dbsource.DbSourceRuleIce;

public class DynamicDbSourceInterceptor implements MethodInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDbSourceInterceptor.class);

	/**
	 * @discription 数据源切换
	 * @author wangxiong@segimail.com       
	 * @created 2018年8月15日 上午9:36:38      
	 * @param invocation
	 * @return
	 * @throws Throwable     
	 * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
	 */
	@SuppressWarnings("deprecation")
	public Object invoke(MethodInvocation invocation) throws Throwable {
		final String methodName = invocation.getMethod().getName();
		int groupOrganId;
		try {
			groupOrganId = (int) invocation.getArguments()[0];
		} catch (Exception e1) {
			LOGGER.error("invocation.getArguments(){}" + FastjsonUtils.toJsonString(invocation.getArguments()),e1);
			throw new RuntimeException("被拦截方法不符合规则，请联系管理员！！！");
		}
		
		String tempKey = DynamicDataSourceKeyHolder.getDataSourceKey();
		LOGGER.debug("methodName:" + methodName);
		
		// 调用Ice方法  获取数据
		DbSourceRuleIce dbsourceRule = MtCommonServiceUtils.queryDbSourceRuleByGroupId(groupOrganId);
		if (null == dbsourceRule) {
			throw new RuntimeException("groupOrganId{}==>" + groupOrganId + "获取数据源配置为空");
		}
		LOGGER.debug("groupOrganId=====>{}" + groupOrganId + "之前数据源未：{}" + tempKey + "改变为数据源为：{}" + dbsourceRule.getDataSourceIdx() + "{}methodName:" + methodName);
		DynamicDataSourceKeyHolder.setKey(dbsourceRule.getDataSourceIdx());
		DynamicTableSourceKeyHolder.setKey(dbsourceRule.getTableIdx());
		
		Object obj = null;
		try {
			// 执行方法
			obj = invocation.proceed();
		} catch (Exception e) {
			throw e;
		} finally {
			// 执行完成,还原为原来的数据源
			DynamicDataSourceKeyHolder.setKey(tempKey);
		}

		return obj;
	}

}
