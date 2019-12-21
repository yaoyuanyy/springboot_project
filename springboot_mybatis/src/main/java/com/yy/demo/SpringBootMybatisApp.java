package com.yy.demo;

import com.yy.demo.mapper.UserMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;

@SpringBootApplication
@EnableScheduling
public class SpringBootMybatisApp {

    public static void main(String[] args) throws Exception {

//         将jvm中的代理对象输出到硬盘的.class文件
//         cglib动态代理 --该设置用于输出cglib动态代理产生的类
//         String user_dir = System.getProperty("user.dir");
//         System.out.println("user_dir:" + user_dir);
//         System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, user_dir + "/cglib_proxy/");

//         jdk动态代理 --该设置用于输出jdk动态代理产生的类，输出的文件路径为your project下。如我的项目是java_example,
//         $ProxyX.class在java_example/com/sun/proxy/下
//         System.setProperty("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true"); // 这么设置不起作用，原因不明
//         IDE - VMOption: -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true


        ApplicationContext springApplication = SpringApplication.run(SpringBootMybatisApp.class, args);
//         MyJavaBean myJavaBean = (MyJavaBean)springApplication.getBean("myJavaBean");
//         System.out.println("@& getAge:"+myJavaBean.getAge());
//         System.out.println("@& getDesc:"+myJavaBean.getDesc());

        getProxyFile(springApplication);
    }

    public static void getProxyFile(ApplicationContext springApplication) {
        UserMapper proxy = (UserMapper) springApplication.getBean("userMapper");
        byte[] proxyClass = ProxyGenerator.generateProxyClass(proxy.getClass()
                .getSimpleName(), proxy.getClass().getInterfaces());
        //将字节码文件保存到D盘，文件名为$Proxy0.class
        String user_dir = System.getProperty("user.dir");
        System.out.println("user_dir:" + user_dir);

        try (FileOutputStream outputStream = new FileOutputStream(new File(user_dir + "/UserMapper.class"));) {
            outputStream.write(proxyClass);
            outputStream.flush();
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
    }
}
