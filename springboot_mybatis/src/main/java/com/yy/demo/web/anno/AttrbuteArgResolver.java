package com.yy.demo.web.anno;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/1/15 at 下午5:07
 */
@Slf4j
public class AttrbuteArgResolver implements HandlerMethodArgumentResolver{
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("supportsParameter in");
        return parameter.hasParameterAnnotation(AttrbuteArg.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        log.info("resolveArgument in");
        AttrbuteArg attrbuteArg = parameter.getParameterAnnotation(AttrbuteArg.class);
        String arg = attrbuteArg.value();
        if(StringUtils.isNotBlank(arg)){
            log.info("resolveArgument arg:{}", arg);

            mavContainer.addAttribute("ll","ll");//for test

            return webRequest.getAttribute(arg, RequestAttributes.SCOPE_REQUEST);
        }
        return webRequest.getAttribute(parameter.getParameterName(), RequestAttributes.SCOPE_REQUEST);
    }
}
