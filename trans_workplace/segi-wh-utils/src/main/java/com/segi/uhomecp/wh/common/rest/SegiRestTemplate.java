package com.segi.uhomecp.wh.common.rest;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.segi.uhomecp.utils.FastjsonUtils;
/**
 * 
 *     
 * 包: com.segi.uhomecp.wh.common.rest    
 * 类名称: SegiRestTemplate.java
 * 类描述: 提供rest调用方法
 * 创建人: kinas    
 * 创建时间: 2018年1月19日 上午11:22:55
 * 修改人:     
 * 修改时间:  
 * 修改备注: [说明本次修改内容]
 * 版本: v1.0
 *
 */
@Component
public class SegiRestTemplate extends RestTemplate {
	
	public HttpHeaders httpHeaders;
	
	public SegiRestTemplate() {
		httpHeaders = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        httpHeaders.setContentType(type);
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE.toString());
	}
	
	public SegiRestTemplate(HttpHeaders httpHeaders) {
		this.httpHeaders = httpHeaders;	
	}
	
	public <T> T postJson(String url, Object request, Class<T> responseType) throws RestClientException {
		HttpEntity entity = new HttpEntity(request, httpHeaders);
		RestResponseEntity response = this.postForObject(url, entity, RestResponseEntity.class);
		if (response != null) {
			if (response.isSucess()) {
				return FastjsonUtils.parseObject(FastjsonUtils.toJsonString(response.getData()), responseType);
			} else {
				throw new RuntimeException(response.getMessage());
			}
		}
		
		return null;
	}
	
	public <T> List<T> postJsonList(String url, Object request, Class<T> responseType) throws RestClientException {
		HttpEntity entity = new HttpEntity(request, httpHeaders);
		RestResponseEntity response = this.postForObject(url, entity, RestResponseEntity.class);
		if (response != null) {
			if (response.isSucess()) {
				return FastjsonUtils.parseArray(FastjsonUtils.toJsonString(response.getData()), responseType);
			} else {
				throw new RuntimeException(response.getMessage());
			}
		}
		
		return null;
	}
}
