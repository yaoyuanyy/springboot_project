package com.yy.demo.web.config.security;

/**
 * Description: Xss过滤策略接口
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/5/26 at 下午10:18
 */
public interface FilterPolicy {

    /**
     * 执行Xss过滤方法
     *
     * @param input 原字符串
     * @return Xss过滤后的字符串
     */
    String filter(String input);
}

