package com.yy.demo.config.anno;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/1/15 at 下午7:15
 */
public class T {

    public static void main(String[] args) {
        String s = "https://okcombeta.bafang.com/account/support";
        String p = "(.*\\\\.okcoin\\\\.com)|(^okcoin\\\\.com)|(.*\\\\.bafang\\\\.com)|(^bafang\\\\.com)";
        Pattern pattern = Pattern.compile(p, Pattern.CASE_INSENSITIVE);

        String referer = StringUtils.defaultIfBlank(s, StringUtils.EMPTY);
        referer = referer.replace("&#47;", "/");
        final Matcher matcher = pattern.matcher(referer);
        if (matcher.find()) {
            System.out.println("dddd");
            //return true;
        }
    }
}

