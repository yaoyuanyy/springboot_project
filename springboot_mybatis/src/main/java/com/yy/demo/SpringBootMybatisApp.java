package com.yy.demo;

import com.yy.demo.post_process.MyJavaBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

// 禁掉spring boot自带的DataSourceAutoConfiguration，因为它会读取application.properties文件的spring.datasource.*属性并自动配置单数据源
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableScheduling
public class SpringBootMybatisApp {
    ApplicationContext applicationContext;

	 public static void main(String[] args) throws Exception {
         ApplicationContext springApplication= SpringApplication.run(SpringBootMybatisApp.class, args);
         MyJavaBean myJavaBean = (MyJavaBean)springApplication.getBean("myJavaBean");
         System.out.println("@& getAge:"+myJavaBean.getAge());
         System.out.println("@& getDesc:"+myJavaBean.getDesc());
     }
}
