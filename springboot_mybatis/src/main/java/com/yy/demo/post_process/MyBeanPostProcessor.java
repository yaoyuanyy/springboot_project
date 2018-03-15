package com.yy.demo.post_process;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/3/14 at 下午6:05
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("myJavaBean".equals(beanName)) {
            System.out.println("@$ MyBeanPostProcessor postProcessBeforeInitialization beanName --> "+beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if("myJavaBean".equals(beanName)) {
            System.out.println("@$ MyBeanPostProcessor postProcessAfterInitialization beanName --> " + beanName);
        }
        return bean;
    }
}
