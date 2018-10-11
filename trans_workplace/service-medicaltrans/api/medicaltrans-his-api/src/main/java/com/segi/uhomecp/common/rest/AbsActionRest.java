package com.segi.uhomecp.common.rest;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.collect.Maps;
import com.segi.uhomecp.rest.document.AbsAction;
import com.segi.uhomecp.utils.UhomePropUtils;

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
			logger.debug("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝ {} : {};",paramsName,request.getParameter(paramsName));
		}
	}
	
	/**
	 * 获取App版本号
	 * @return
	 */
	protected String getVersion() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); 
		String version = request.getHeader("version");
		if (StringUtils.isBlank(version)) {
			version = request.getParameter("version");
		}
		return version;
	}
	
	/**
	 * 获取科室Id
	 * @return
	 */
	protected String getHouseId() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); 
		String houseId = request.getHeader("houseId");
		if (StringUtils.isBlank(houseId)) {
			houseId = request.getParameter("houseId");
		}
		return houseId;
	}
	
	/**
	 * 获取数据来源
	 * 设备类型：2（苹果手机），3（苹果平板），4（安卓手机），5（安卓平板）
	 * @return
	 */
	protected String getSource() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); 
		String source = request.getHeader("source");
		logger.debug("source Header------------------------------------------------------>"+source);
		if (StringUtils.isBlank(source)) {
			source = request.getParameter("source");
		}
		return source;
	}
	
	/**
	 * 获取组织id
	 * @return
	 */
	protected String getOrganId() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); 
		String organId = request.getHeader("organId");
		logger.debug("organId Header------------------------------------------------------>"+organId);
		if (StringUtils.isBlank(organId)) {
			organId = request.getParameter("organId");
		}
		return organId;
	}
	
	/**
	 * 获取登录用户id
	 * @return
	 */
	protected String getUserId() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); 
		String userId = request.getHeader("userId");
		logger.debug("UserId Header------------------------------------------------------>"+userId);
		if (StringUtils.isBlank(userId)) {
			userId = request.getParameter("userId");
		}
		return userId;
	}
	
	
	/**
	 * 检查当前版本号
	 * @return
	 */
	protected boolean checkVersion() {
		String versionApp = getVersion();
		String version = UhomePropUtils.getProperty("segi.app.version.limit");
		
		boolean flag = false;
		if (StringUtils.isBlank(versionApp) || StringUtils.isBlank(version)) {
			flag = true;
		} else {
			logger.info(versionApp + ">" + version);
			if (Integer.valueOf(versionApp) >= Integer.valueOf(version)) {
				flag = true;
			}
		}
		return flag;
	}
}
