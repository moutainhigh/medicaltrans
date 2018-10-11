package com.segi.uhomecp.common.rest;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.segi.uhomecp.rest.document.AbsAction;

public class AbsActionRest extends AbsAction {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	

	/**
	 * 获取参数
	 * @param request
	 * @return
	 */
	protected Map<String, Object> getParams(HttpServletRequest request) {
		Enumeration<String> paramsNames = request.getParameterNames();
		Map<String, Object> params = Maps.newHashMap();
		while (paramsNames.hasMoreElements()) {
			String paramsName = paramsNames.nextElement();
			params.put(paramsName, request.getParameter(paramsName));
		}
		return params;
	}

	/**
	 * 打印参数
	 * @param request
	 */
	protected void printParams(HttpServletRequest request) {
		Enumeration<String> paramsNames = request.getParameterNames();
		Map<String, Object> params = Maps.newHashMap();
		while (paramsNames.hasMoreElements()) {
			String paramsName = paramsNames.nextElement();
			params.put(paramsName, request.getParameter(paramsName));
			logger.info("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝ {} : {};",paramsName,request.getParameter(paramsName));
		}
	}
}
