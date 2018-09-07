package com.yy.demo.web.config;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/9/3 at 下午10:01
 */
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidationHandler.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Documented
public @interface Phone {

    String message() default "{com.yy.demo.web.config.Phone.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
