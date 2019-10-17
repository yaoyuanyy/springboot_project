package com.yy.demo;

import com.yy.demo.post_process.MyJavaBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootMybatisApp {
    ApplicationContext applicationContext;

	 public static void main(String[] args) throws Exception {

         // 将jvm中的代理对象输出到硬盘的.class文件 方法一
         // --该设置用于输出cglib动态代理产生的类
         String user_dir = System.getProperty("user.dir");
         System.out.println("user_dir:" + user_dir);
         System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, user_dir + "/cglib_proxy/");

         // --该设置用于输出jdk动态代理产生的类，输出的文件路径为your project下。如我的项目是java_example, $ProxyX.class在java_example/com/sun/proxy/下
         System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");


         ApplicationContext springApplication= SpringApplication.run(SpringBootMybatisApp.class, args);
         MyJavaBean myJavaBean = (MyJavaBean)springApplication.getBean("myJavaBean");
         System.out.println("@& getAge:"+myJavaBean.getAge());
         System.out.println("@& getDesc:"+myJavaBean.getDesc());
     }
}
