package com.okcoin.exchange.c2c.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangchuan.deng on 2017/10/20.
 */
public class SignUtil {

    public static String sign(Map<String, ? extends Object> params, String privateKey) {
        return RSAUtil.sign(getParamString(params), privateKey, "UTF-8");
    }
    
    public static boolean verify(Map<String, ? extends Object> params, String publicKey) {
        String sign = (String) params.remove("sign");
        return RSAUtil.verify(getParamString(params), sign, publicKey, "UTF-8");
    }
    
    public static String getParamString(Map<String, ? extends Object> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        StringBuilder kvs = new StringBuilder();
        if (!keys.isEmpty()) {
            for (String key : keys) {
                kvs.append(key).append("=").append(params.get(key)).append("&");
            }
            kvs.setLength(kvs.length() - 1);
        }
        return kvs.toString();
    }
    
}
