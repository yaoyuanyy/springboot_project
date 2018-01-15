package com.yy.demo.web.anno;

import com.sun.org.apache.regexp.internal.RE;

import java.lang.annotation.*;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/1/15 at 下午5:54
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Login {
}

