package com.yy.demo.web.anno;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.ValueConstants;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 */
public class RequestParamJsonMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String CACHE_KEY = "Json_param_Cache";
    
    @Autowired
    private ConversionService conversionService;
    
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestParamJson.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        
        JSONObject jsonObject = (JSONObject) webRequest.getAttribute(CACHE_KEY, RequestAttributes.SCOPE_REQUEST);
        
        if (jsonObject == null) {
            
            String json = readRequestBody(webRequest);
            
            jsonObject = JSON.parseObject(json);
            
            webRequest.setAttribute(CACHE_KEY, jsonObject, 0);
            
        }
        
        RequestParamJson requestParamJson = parameter.getParameterAnnotation(RequestParamJson.class);
        
        String parameterName = StringUtils.defaultIfBlank(requestParamJson.name(), parameter.getParameterName());
        
        Class<?> parameterType = parameter.getParameterType();
        
        Object argumentValue = jsonObject.getObject(parameterName, parameterType);
        
        if (argumentValue == null && !ValueConstants.DEFAULT_NONE.equals(requestParamJson.defaultValue())) {
            argumentValue = conversionService.convert(requestParamJson.defaultValue(), parameterType);
        }
        
        if (requestParamJson.required() && argumentValue == null) {
            throw new RuntimeException("argumentValue is null");
        }
        
        return argumentValue;
    }

    private String readRequestBody(NativeWebRequest webRequest) throws Exception {
        
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        
        BufferedReader reader = null;
        
        StringBuilder content = new StringBuilder();
        
        try {
            
            reader = new BufferedReader(new InputStreamReader(request.getInputStream(), request.getCharacterEncoding()));
            
            for (String line; (line = reader.readLine()) != null;) {
                content.append(line).append("\n");
            }
            
            return content.toString();
        } finally {
            IOUtils.closeQuietly(reader);
        }
        
    }
    
}
