package com.yy.demo.post_process;

import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/3/14 at 下午6:24
 */
@Configuration
public class MyConfig implements ApplicationContextAware {

    ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("@@ CommandLineRunner @@");
        this.applicationContext = applicationContext;
    }

    @Bean("myJavaBean")
    public MyJavaBean myJavaBean1(){

        Map map1 = applicationContext.getBeansOfType(MyJavaBean.class);
        System.out.println("@& map1:"+map1.size());

        MyJavaBean myJavaBean = new MyJavaBean();
        System.out.println("@& myJavaBean1 bena @&");
        myJavaBean.setAge(10);
        myJavaBean.setDesc("config bean");

        Map map2 = applicationContext.getBeansOfType(MyJavaBean.class);
        System.out.println("@& map2:"+map2.size());

        return myJavaBean;
    }

    @Bean
    public MyBeanPostProcessor myBeanPostProcessor(){
        return new MyBeanPostProcessor();
    }

    @Bean
    public MyBeanFactoryPostProcessor myBeanFactoryPostProcessor(){
        return new MyBeanFactoryPostProcessor();
    }
}
