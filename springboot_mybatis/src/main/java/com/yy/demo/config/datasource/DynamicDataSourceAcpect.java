package com.yy.demo.config.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Description:
 * <p></p>
 * <pre>
 *
 *   NB.
 * </pre>
 * <p>
 * Created by skyler on 2019-03-16 at 23:15
 */
@Component
@Aspect
@Slf4j
public class DynamicDataSourceAcpect {

    public DynamicDataSourceAcpect() {
        log.info("DynamicDataSourceAcpect constractor");
    }

    @Pointcut(value = "@annotation(DS)")
    public void serverPointCut(){}

    @Before(value = "serverPointCut()")
    public void handle(JoinPoint joinPoint){
        // 目标类
        Class sourceClass = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        // 方法参数的类型
        Class[] argsClass = ((MethodSignature)joinPoint.getSignature()).getParameterTypes();

        try {
            Method method = sourceClass.getMethod(methodName, argsClass);
            if(method.isAnnotationPresent(DS.class)) {
                DS ds = method.getAnnotation(DS.class);
                String dataSourceType = ds.value();
                DataSourceContextHolder.set(dataSourceType);
            }else {
                // 如果没有注解，使用默认的数据源
                DataSourceContextHolder.set(DataSourceContextHolder.DEFAULT_DS);
            }
        } catch (NoSuchMethodException e) {
            log.error("aop 放入数据源出现异常. methodName:{} ERROR:", joinPoint.getSignature().getName(), e);
            e.printStackTrace();
        }
    }


    @After(value = "serverPointCut()")
    public void afterHandle(){
        DataSourceContextHolder.clear();
    }
}
