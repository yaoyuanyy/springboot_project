package com.yy.demo.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

public class HttpUtils {

    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient();

    /**
     * HTTP Post请求
     *
     * @param url
     * @param paramMap
     * @return
     * @throws IOException
     */
    public static Response post(String url, Map<String, ? extends Object> paramMap) throws IOException {

        FormBody.Builder formBuilder = new FormBody.Builder();

        for (Entry<String, ? extends Object> entry : paramMap.entrySet()) {
            formBuilder.add(entry.getKey(), String.valueOf(entry.getValue()));
        }

        Request request = new Request.Builder().header("Referer", "http://www.skyler1.com").url(url).post(formBuilder.build()).build();

        okhttp3.Response response = HTTP_CLIENT.newCall(request).execute();

        return new Response(response.code(), response.body().string());
    }

    /**
     * Http Get 请求
     *
     * @param url
     * @param paramMap
     * @return
     * @throws IOException
     */
    public static Response get(String url, Map<String, ?> paramMap) throws IOException {
        HttpUrl httpUrl = HttpUrl.parse(url);
        if (httpUrl == null) {
            throw new RuntimeException("invalid url");
        }

        HttpUrl.Builder httpUrlBuilder = httpUrl.newBuilder();
        for (Entry<String, ?> entry : paramMap.entrySet()) {
            httpUrlBuilder.addQueryParameter(entry.getKey(), entry.getValue().toString());
        }

        Request request = new Request.Builder().url(httpUrlBuilder.build().url()).get().build();

        okhttp3.Response response = HTTP_CLIENT.newCall(request).execute();

        return new Response(response.code(), response.body().string());
    }

    @Data
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Response {
        private int code;
        private String body;
    }

}
