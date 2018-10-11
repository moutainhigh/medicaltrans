package com.mangofactory.swagger.readers.operation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;

import com.google.common.collect.Lists;
import com.mangofactory.swagger.models.dto.Parameter;
import com.mangofactory.swagger.scanners.RequestMappingContext;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;

public class OperationImplicitParametersReader_bak extends SwaggerParameterReader {

	@Override
	protected Collection<Parameter> readParameters(RequestMappingContext context) {
		HandlerMethod handlerMethod = context.getHandlerMethod();
		Method method = handlerMethod.getMethod();
		ApiImplicitParams annotation = AnnotationUtils.findAnnotation(method, ApiImplicitParams.class);

		List<Parameter> headerParameters = Lists.newArrayList();

		List<Parameter> parameters = (List<Parameter>) context.get("parameters");

		// 判断是否有token标识
		boolean flag = false;
		if (null != annotation) {
			for (ApiImplicitParam param : annotation.value()) {
				headerParameters.add(OperationImplicitParameterReader.getImplicitParameter(param));
				if ("token".equals(param.name())) {
					flag = true;
				}
			}
		}
		if (!flag && parameters != null) {
			for (Parameter parameter : parameters) {
				if ("token".equals(parameter.getName())) {
					flag = true;
				}
			}
		}

		// 默认添加一个token
		// @ApiImplicitParam(paramType =
		// "header",value="令牌(token)",name="token",dataType="string",required=true),
		if (!flag) {
			// 增加默认token
			headerParameters.add(0, OperationImplicitParameterReader.getImplicitParameter(new ApiImplicitParam() {
				@Override
				public Class<? extends Annotation> annotationType() {
					return null;
				}

				@Override
				public String value() {
					return "令牌(token)";
				}

				@Override
				public boolean required() {
					return true;
				}

				@Override
				public String paramType() {
					return "header";
				}

				@Override
				public String name() {
					return "token";
				}

				@Override
				public String defaultValue() {
					return null;
				}

				@Override
				public String dataType() {
					return "string";
				}

				@Override
				public String allowableValues() {
					return "";
				}

				@Override
				public boolean allowMultiple() {
					return false;
				}

				@Override
				public String access() {
					return "";
				}
			}));
		}

		// 判断是否有organIs标识
		flag = false;
		/*
		 * if (null != annotation) { for (ApiImplicitParam param :
		 * annotation.value()) { if ("organId".equals(param.name())) { flag =
		 * true; } } } if (!flag&&parameters1 != null) { for (Parameter
		 * parameter : parameters1) { if ("organId".equals(parameter.getName()))
		 * { flag = true; } } }
		 */

		if (!flag) {
			headerParameters.add(0, OperationImplicitParameterReader.getImplicitParameter(new ApiImplicitParam() {
				@Override
				public Class<? extends Annotation> annotationType() {
					return null;
				}

				@Override
				public String value() {
					return "项目ID";
				}

				@Override
				public boolean required() {
					return false;
				}

				@Override
				public String paramType() {
					return "header";
				}

				@Override
				public String name() {
					return "organId";
				}

				@Override
				public String defaultValue() {
					return null;
				}

				@Override
				public String dataType() {
					return "string";
				}

				@Override
				public String allowableValues() {
					return "";
				}

				@Override
				public boolean allowMultiple() {
					return false;
				}

				@Override
				public String access() {
					return "";
				}
			}));
		}

		return headerParameters;
	}
}
