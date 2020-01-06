package com.yy.demo.config.postprocess;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/1/29 at 上午11:38
 */
@Component
@Slf4j
public class ChineseBeanPostProcessor implements BeanPostProcessor{
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("postProcess Before Initialization-> @@ bean:{} beanName:{}",bean.getClass().getName(), beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("postProcess After Initialization-> @@ bean:{} beanName:{}",bean.getClass().getName(), beanName);
        if (bean instanceof Chinese) {
            Chinese chinese = (Chinese) bean;
            chinese.setName("ChineseBeanPostProcessor chinese setName");
        }
        return bean;
    }
}
