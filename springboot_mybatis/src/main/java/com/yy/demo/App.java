package com.yy.demo;

import com.yy.demo.post_process.MyJavaBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableKafka
public class App  {
    ApplicationContext applicationContext;

	 public static void main(String[] args) throws Exception {
         ApplicationContext springApplication= SpringApplication.run(App.class, args);
         MyJavaBean myJavaBean = (MyJavaBean)springApplication.getBean("myJavaBean");
         System.out.println("@& getAge:"+myJavaBean.getAge());
         System.out.println("@& getDesc:"+myJavaBean.getDesc());
     }
}
