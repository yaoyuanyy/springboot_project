package com.yy.demo.web.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 公钥私钥工具类
 **/
@Getter
@Setter
@ConfigurationProperties(prefix = "skyler.url.key")
public class UrlKeyConfig {

    /**
     * 转账私钥
     */
    private String privateKey;

    /**
     * 转账公钥
     */
    private String publicKey;

    /**
     * 转入的url
     */
    private String inUrl;

    /**
     * 转出的url
     */
    private String outUrl;

    private List<String> excludeUrls;

}