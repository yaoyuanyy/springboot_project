package com.yy.demo.web.config;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by skyler on 2017/10/18
 */
@Aspect
@Component
public class WebLogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping() {}

    @Pointcut("execution(public * com.yy.demo.web.controller..*.*(..))")
    public void controllerPointcut() {}

    @Pointcut("execution(public * com.yy.demo..*(..))")
    public void AllMethodPointcut() {}

    //@Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        List list = new ArrayList();
        for (Object o : joinPoint.getArgs()) {
            if (o == null) continue;
            if (o instanceof HttpServletRequest){
                continue;
            }
            if (o instanceof HttpServletResponse) {
                continue;
            }
            list.add(o);
        }
        logger.info("ARGS : request Parameter:{} requestBody:{}" , JSON.toJSONString(request.getParameterMap()) ,JSON.toJSONString(list));
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

    }
}