package com.yy.demo.config.datasource;

import java.lang.annotation.*;

/**
 * Description: 动态数据源切换标志
 * <p></p>
 * <pre>
 *
 *   NB.
 * </pre>
 * <p>
 * Created by skyler on 2019-03-16 at 23:09
 */
// 什么时候起作用
@Retention(RetentionPolicy.RUNTIME)
// 什么地方起作用
@Target(ElementType.METHOD)
@Documented
public @interface DS {

    String value() default "master";
}
