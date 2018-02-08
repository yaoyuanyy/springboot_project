package com.yy.demo.config.postprocess;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/1/29 at 上午11:42
 */
@Component
public class Chinese implements InitializingBean {

    private String name;

    public Chinese() {
        System.out.println("Spring实例化主调Bean：Chinese实例。。。");
    }

    public String getName() {
        System.out.println("Spring为Chinese实例 getName():"+name);
        return name;
    }

    public void setName(String name) {
        System.out.println("Spring为Chinese实例 setName()方法注入依赖关系。。。");
        this.name = name;
    }


    //生命周期方法
    public void init()
    {
        System.out.println("Chinese实例 正在执行初始化方法init...");
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Chinese实例 正在执行初始化方法afterPropertiesSet");

    }


}
