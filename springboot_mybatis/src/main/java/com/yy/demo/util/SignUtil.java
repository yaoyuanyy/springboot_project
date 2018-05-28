package com.yy.demo.util;

import com.alibaba.fastjson.JSON;
import com.oracle.tools.packager.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
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

    public static void main(String[] args) throws Exception {
        Map<String, Object> pubPriKeyMap = RSAUtil.genKeyPair();

//        String privateKey = RSAUtil.getPrivateKey(pubPriKeyMap);
////        String publicKey = RSAUtil.getPublicKey(pubPriKeyMap);
        String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJNqCvIE0E7PvM223FPAnyz616mn2XmD8h/O3RfJtbNec5dZm4J4tJ1z5X0SVazutq2oLBLYPM6dMt+zxJoTEyxWkjv195YCQYmWNr2BPLibJTUiOT7dY0b6QfccFVZekGgRjhP3GX9RYpQAVrVG0eJEB0h/3QUWOfk6u7+ZAg+tAgMBAAECgYAmwqs+F6XuExFBxeMI4oz39rzeDxRPrzHqyym3J4JvN1aNZTJOjA5xmBevWzLL1biS0LVF/zV93jeSOmJnYLyZdFg65E/ZFHXN3hhvOeRUkhPM6fH3EaR4qfka81az7Sqelp87CrThiPpH0X8oS+rWQgrr9U4gogSiU9jO0BkIwQJBAOVVZSWwixK58OYYeVVvRe0DwYg0IidC/E7hHg9gVOSZCMbBrtRFh8a9vQbgNTlNn89wQTOAk2Pi1I8P/QNh818CQQCkjiSXi8anDG9FTGtbSgYx7aYG5ujW/p+M7UD554cBPNfJSSlTjp18cbkWW7uM4DdUe7kXrh+wXE7azql+c8RzAkA1MHklJK0cysN0mniJuCUfs59PV8gUExUxtkSijFUkL9o5PvKtiPLUxWzyC05M3BQUqJR2vXtxz0o9ZBfqkO1BAkB84svun8xSN2OzSj4n+FQfTGc20cRgdroMMHuhrg+N5uk2AGp22cO4EG8SAuiiEACuA4AD8shmBohqE3IbYCz1AkALbomYuzhecIyNBjuJ2PyRLFS5iYGb65NQvF8EErd5jEz6JHOUmMJsCXUGYfA0lq0kXgL+niI9jMEoVA/+OLEh";
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCTagryBNBOz7zNttxTwJ8s+tepp9l5g/Ifzt0XybWzXnOXWZuCeLSdc+V9ElWs7ratqCwS2DzOnTLfs8SaExMsVpI79feWAkGJlja9gTy4myU1Ijk+3WNG+kH3HBVWXpBoEY4T9xl/UWKUAFa1RtHiRAdIf90FFjn5Oru/mQIPrQIDAQAB";

        System.out.println("privateKey:" + privateKey);
        System.out.println("publicKey:" + publicKey);

        //String transferUUid = UUID.randomUUID().toString();
        String transferUUid = "3d9f78ac-5f8d-4af0-9bc3-842f8316a17c";
        System.out.println("transferUUid:" + transferUUid);
        //
        Map<String, Object> map = new HashMap<>();
        map.put("userId", 1111);
        map.put("transferUUid", transferUUid);
        map.put("amount", 12.3322222);
        // UeR1Wd9JnLt/pqlz6PWFDawLNOY3uwAGGhOXXUM7TjFW09cMgzNvRjry85MDOk1Tfg2DIZMfwaSs3y4LscoasOYH5cRFWeARFTHfNb/E4NK1IIX9v5ceUk76nTiozMOp3ZCmMA280/B+KQgd78s52XQBP0p/fzvL8PaCCuhh7l8=
        // UeR1Wd9JnLt/pqlz6PWFDawLNOY3uwAGGhOXXUM7TjFW09cMgzNvRjry85MDOk1Tfg2DIZMfwaSs3y4LscoasOYH5cRFWeARFTHfNb/E4NK1IIX9v5ceUk76nTiozMOp3ZCmMA280/B KQgd78s52XQBP0p/fzvL8PaCCuhh7l8=
        String sign = SignUtil.sign(map, privateKey);
        System.out.println("sign:" + sign);
        map.put("sign", sign);

        System.out.println("result:" + SignUtil.verify(map, publicKey));

        HttpUtils.Response response = HttpUtils.post("http://localhost:8000/v1/accountTransfer/in", map);
        SignUtil.log.info("response code : {}, body : {}", response.getCode(), response.getBody());
    }
}
