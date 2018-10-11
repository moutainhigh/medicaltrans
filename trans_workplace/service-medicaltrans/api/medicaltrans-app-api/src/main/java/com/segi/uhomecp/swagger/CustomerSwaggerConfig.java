package com.segi.uhomecp.swagger;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.models.dto.builder.ApiInfoBuilder;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.segi.uhomecp.swagger.SwaggerPluginConfig;

@Configuration
@EnableWebMvc
@EnableSwagger
public class CustomerSwaggerConfig extends SwaggerPluginConfig {

	@Override
	public ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfoBuilder().title("web-api order-web接口服务  RESTful API")
				.description("前后端分离，web端工单处理相关接口").termsOfServiceUrl("http://www.uhomecp.com")
				.contact("liukai@segimail.com").build();
		
		/*new ApiInfo("模版配置接口  RESTful API",
				"前后端分离，模版配置相关接口",
				"www.uhomecp.com", 
				"liukai@segimail.com",
				"",
				"");*/
		return apiInfo;
	}

}
