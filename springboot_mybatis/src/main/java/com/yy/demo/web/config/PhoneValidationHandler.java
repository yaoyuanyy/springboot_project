package com.yy.demo.web.config;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Description: 验证手机号
 * <p></p>
 * <pre>
 *     refer to https://mp.weixin.qq.com/s/jAwOz_r5qAKwDqgBWvBoTw
 * </pre>
 * NB.
 * Created by skyler on 2018/9/3 at 下午10:05
 */
public class PhoneValidationHandler implements ConstraintValidator<Phone, String>{


    private static final Pattern pattern = Pattern.compile("^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$");

    @Override
    public void initialize(Phone constraintAnnotation) {
        System.out.println("message:"+constraintAnnotation.message());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("start valid:"+value);
        return pattern.matcher(value).find();
    }

    public static void main(String[] args) {
        System.out.println(pattern.matcher("13011110000").find());
    }
}
