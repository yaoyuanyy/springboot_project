package com.yy.demo.post_process;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/3/14 at 下午6:05
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor{
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("myJavaBean");
        String name = beanDefinition.getBeanClassName();

        System.out.println("@$ MyBeanFactoryPostProcessor postProcessBeanFactory beanName@$" + name);

        MutablePropertyValues pv = beanDefinition.getPropertyValues();
        pv.addPropertyValue("desc", "desc-new");
    }
}
