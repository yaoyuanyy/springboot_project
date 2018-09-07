package com.yy.demo.util;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/7/9 at 下午4:48
 */
public class RegexUtils {

    private static final Pattern commaPattern = Pattern.compile("\\;(.*?)\\;");
    private static final Pattern phonePattern = Pattern.compile("^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$");

    /**
     * 提取逗号之间的内容
     * @param str
     */
    public static String extractCommaContent(String str) {
        String filetext = "guangsha;1.1.0;iOS;11.3;iPhone 8";
        //正则表达式，取=和|之间的字符串，不包括=和|
        Matcher m = commaPattern.matcher(null);

        String des = null;
        if(m.find()) {
            des = m.group(1);
            System.out.println(des);

        }
        return des;
    }

    /**
     * 验证手机号格式
     *
     * @param str
     */
    public static boolean valiaPhone(String str) {
        if(Objects.isNull(str)) return false;
        return phonePattern.matcher(str).find();
    }

}
