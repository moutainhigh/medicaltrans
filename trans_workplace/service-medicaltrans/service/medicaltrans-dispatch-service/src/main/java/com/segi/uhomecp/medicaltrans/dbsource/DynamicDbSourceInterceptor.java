package com.segi.uhomecp.medicaltrans.dbsource;

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

	private static final Logger logger = LoggerFactory.getLogger(DynamicDbSourceInterceptor.class);

	@SuppressWarnings("deprecation")
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		final String methodName = invocation.getMethod().getName();
		logger.debug("methodName======执行的方法===>{}"+methodName);
		int groupOrganId;
		try {
			groupOrganId = (int) invocation.getArguments()[0];
			logger.debug("methodName======执行的方法===>{}" + methodName + "{}groupOrganId{}" + groupOrganId);
		} catch (Exception e1) {
			logger.error("invocation.getArguments(){}"+FastjsonUtils.toJsonString(invocation.getArguments()));
			throw new RuntimeException("被拦截方法不符合规则，请联系管理员！！！");
		}
		String tempKey = DynamicDataSourceKeyHolder.getDataSourceKey();
		// 调用Ice方法  获取数据
		DbSourceRuleIce dbsourceRule = MtCommonServiceUtils.queryDbSourceRuleByGroupId(groupOrganId);
		if (null == dbsourceRule) {
			throw new RuntimeException("groupOrganId{}==>" + groupOrganId + "获取数据源配置为空");
		}
		logger.debug("之前数据源未：{}" + tempKey + "改变为数据源为：{}" + dbsourceRule.getDataSourceIdx() + "{}methodName:" + methodName);
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
