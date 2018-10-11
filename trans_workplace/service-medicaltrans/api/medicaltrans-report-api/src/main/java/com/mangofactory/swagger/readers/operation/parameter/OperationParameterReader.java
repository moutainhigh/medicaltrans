package com.mangofactory.swagger.readers.operation.parameter;

import static com.google.common.collect.Lists.newArrayList;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.method.HandlerMethod;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mangofactory.swagger.configuration.SwaggerGlobalSettings;
import com.mangofactory.swagger.core.CommandExecutor;
import com.mangofactory.swagger.models.alternates.AlternateTypeProvider;
import com.mangofactory.swagger.models.dto.AllowableValues;
import com.mangofactory.swagger.models.dto.Parameter;
import com.mangofactory.swagger.readers.Command;
import com.mangofactory.swagger.readers.operation.HandlerMethodResolver;
import com.mangofactory.swagger.readers.operation.ResolvedMethodParameter;
import com.mangofactory.swagger.readers.operation.SwaggerParameterReader;
import com.mangofactory.swagger.scanners.RequestMappingContext;
import com.segi.uhomecp.annotation.RequestJsonParam;
import com.segi.uhomecp.utils.JacksonUtil;

public class OperationParameterReader extends SwaggerParameterReader {

  @Override
  protected Collection<? extends Parameter> readParameters(final RequestMappingContext context) {
    HandlerMethod handlerMethod = context.getHandlerMethod();
    SwaggerGlobalSettings swaggerGlobalSettings = (SwaggerGlobalSettings) context.get("swaggerGlobalSettings");
    HandlerMethodResolver handlerMethodResolver
            = new HandlerMethodResolver(swaggerGlobalSettings.getTypeResolver());
    AlternateTypeProvider alternateTypeProvider = swaggerGlobalSettings.getAlternateTypeProvider();

    List<ResolvedMethodParameter> methodParameters = handlerMethodResolver.methodParameters(handlerMethod);
    List<Parameter> parameters = newArrayList();

    List<Command<RequestMappingContext>> commandList = newArrayList();
    commandList.add(new ParameterAllowableReader());
    commandList.add(new ParameterDataTypeReader());
    commandList.add(new ParameterTypeReader());
    commandList.add(new ParameterDefaultReader());
    commandList.add(new ParameterDescriptionReader());
    commandList.add(new ParameterMultiplesReader());
    commandList.add(new ParameterNameReader());
    commandList.add(new ParameterRequiredReader());

    ModelAttributeParameterExpander expander = new ModelAttributeParameterExpander(alternateTypeProvider);
    for (ResolvedMethodParameter methodParameter : methodParameters) {

      if (!shouldIgnore(methodParameter, swaggerGlobalSettings.getIgnorableParameterTypes())) {

        RequestMappingContext parameterContext
                = new RequestMappingContext(context.getRequestMappingInfo(), handlerMethod);

        parameterContext.put("methodParameter", methodParameter.getMethodParameter());
        parameterContext.put("resolvedMethodParameter", methodParameter);
        parameterContext.put("swaggerGlobalSettings", swaggerGlobalSettings);

        CommandExecutor<Map<String, Object>, RequestMappingContext> commandExecutor = new CommandExecutor();

        commandExecutor.execute(commandList, parameterContext);

        Map<String, Object> result = parameterContext.getResult();

        if (!shouldExpand(methodParameter)) {
          String defaultValue = (String) result.get("defaultValue");
          for (Annotation annotation : methodParameter.getMethodParameter().getParameterAnnotations()) {
        	  /*System.out.println("===================>>"+annotation.toString());
        	  System.out.println(annotation.annotationType());
        	  System.out.println((String) result.get("dataType"));*/
        	  if (RequestJsonParam.class == annotation.annotationType()&&!methodParameter.getMethodParameter().getParameterType().isArray()) {
        		  	try {
//						defaultValue=JacksonUtil.objectToJackson(methodParameter.getMethodParameter().getParameterType().newInstance());
        		  		// json 格式化（美化）
						Gson gson = new GsonBuilder().setPrettyPrinting().create();
						defaultValue = gson.toJson(methodParameter.getMethodParameter().getParameterType().newInstance());
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
        		  	break;
        	      }
          }
          Parameter parameter = new com.mangofactory.swagger.models.dto.builder.ParameterBuilder().name((String)
        		  result.get("name")).description((String) result.get("description")).defaultValue(defaultValue).required((Boolean) result.get("required")).allowMultiple((Boolean) result.get
        						  ("allowMultiple")).dataType((String) result.get("dataType")).allowableValues((AllowableValues)
        								  result.get("allowableValues")).parameterType((String) result.get("paramType")).parameterAccess(
        										  (String) result.get("paramAccess")).build();
          
          parameters.add(parameter);
        } else {
          expander.expand("", methodParameter.getResolvedParameterType().getErasedType(), parameters);
        }
      }
    }
    return parameters;
  }

  private boolean shouldIgnore(final ResolvedMethodParameter parameter, final Set<Class> ignorableParamTypes) {
    if (null != ignorableParamTypes && !ignorableParamTypes.isEmpty()) {

      if (ignorableParamTypes.contains(parameter.getMethodParameter().getParameterType())) {
        return true;
      }
      for (Annotation annotation : parameter.getMethodParameter().getParameterAnnotations()) {
        if (ignorableParamTypes.contains(annotation.annotationType())) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean shouldExpand(final ResolvedMethodParameter parameter) {
    for (Annotation annotation : parameter.getMethodParameter().getParameterAnnotations()) {
      if (ModelAttribute.class == annotation.annotationType()) {
        return true;
      }
    }
    return false;
  }
}
