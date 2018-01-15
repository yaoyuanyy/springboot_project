package com.yy.demo.web.anno;

import java.lang.annotation.*;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/1/15 at 下午4:58
 */
@Documented
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AttrbuteArg {

    /**
     * 参数值
     * @return
     */
    String value() default "";
}

