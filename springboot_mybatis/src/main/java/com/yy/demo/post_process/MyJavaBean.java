package com.yy.demo.post_process;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/3/14 at 下午5:58
 */
@Component
public class MyJavaBean implements InitializingBean{

    private String desc;
    private int age;

    public MyJavaBean() {
        System.out.println("@$ MyJavaBean gouzaofangfa@$");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("@$ MyJavaBean afterPropertiesSet@$");
    }

    public String getDesc() {
        System.out.println("@$ MyJavaBean getDesc@$"+ desc);
        return desc;
    }

    public void setDesc(String desc) {
        System.out.println("@$ MyJavaBean setDesc@$"+ desc);
        this.desc = desc;
    }

    public int getAge() {
        System.out.println("@$ MyJavaBean getAge@$" + age);
        return age;
    }

    public void setAge(int age) {
        System.out.println("@$ MyJavaBean setAge@$" + age);
        this.age = age;
    }

    @PostConstruct
    public void initMethod(){
        System.out.println("@$ MyJavaBean initMethod@$");
    }
}
