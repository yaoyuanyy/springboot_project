package com.yy.demo.web.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/5/26 at 下午10:11
 */
@ConfigurationProperties(prefix = "skyler.security")
public class SecurityProperties {

    private Xss xss;
    private Csrf csrf;
    private Jwt jwt;

    public Xss getXss() {
        return this.xss;
    }

    public void setXss(final Xss xss) {
        this.xss = xss;
    }

    public Csrf getCsrf() {
        return this.csrf;
    }

    public void setCsrf(final Csrf csrf) {
        this.csrf = csrf;
    }

    public Jwt getJwt() {
        return this.jwt;
    }

    public void setJwt(final Jwt jwt) {
        this.jwt = jwt;
    }

    public static class Xss {

        private String policy = "owasp";
        private String urlPatterns = "/v1/*";
        private String excludeUrlPatterns = "";

        public String getPolicy() {
            return this.policy;
        }

        public void setPolicy(final String policy) {
            this.policy = policy;
        }

        public String getUrlPatterns() {
            return this.urlPatterns;
        }

        /**
         * 设置需要XssFilter的url pattern(ant pattern 表达式)
         * 如果多个pattern用英文逗号,分隔
         *
         * @param urlPatterns url pattern(ant pattern 表达式)
         * @see org.springframework.util.AntPathMatcher
         */
        public void setUrlPatterns(final String urlPatterns) {
            this.urlPatterns = urlPatterns;
        }

        public String getExcludeUrlPatterns() {
            return this.excludeUrlPatterns;
        }

        /**
         * 用法同UrlPatterns
         */
        public void setExcludeUrlPatterns(final String excludeUrlPatterns) {
            this.excludeUrlPatterns = excludeUrlPatterns;
        }
    }

    public static class Csrf {
        private String refererPattern = "all";
        private String excludePathPatterns = "";

        /**
         * 获取可以通过csrf的http header referer url pattern(正则表达式）
         * 默认值为"all"表示全部通过
         *
         */
        public String getRefererPattern() {
            return this.refererPattern;
        }

        public void setRefererPattern(final String refererPattern) {
            this.refererPattern = refererPattern;
        }

        public String getExcludePathPatterns() {
            return this.excludePathPatterns;
        }

        public void setExcludePathPatterns(final String excludePathPatterns) {
            this.excludePathPatterns = excludePathPatterns;
        }
    }

    /**
     * Json Web Token(JWT)配置项
     */
    public static class Jwt {
        private String requestHeaderName = "Auth";
        // 颁发机构
        private String issuer = "skyler";
        private String secret;
        private String cryptoKey;
        private String excludePathPatterns = "";
        private long expiration = 24*60*60*3;
        private String cryptoAlgorithm = "AES";

        /**
         * 获取httpRequest Header中存放jwt token的名称 (默认为"Auth")
         */
        public String getRequestHeaderName() {
            return this.requestHeaderName;
        }

        /**
         * 设置httpRequest Header中存放jwt token的名称
         */
        public void setRequestHeaderName(final String requestHeaderName) {
            this.requestHeaderName = requestHeaderName;
        }

        public String getIssuer() {
            return this.issuer;
        }

        public void setIssuer(final String issuer) {
            this.issuer = issuer;
        }

        /**
         * 获取TOKEN过期时间
         * (默认为"24*60*60*3"即3天有效期)
         */
        public long getExpiration() {
            return this.expiration;
        }

        /**
         * 设置TOKEN过期时间
         */
        public void setExpiration(final long expiration) {
            this.expiration = expiration;
        }

        /**
         * 获取jwt签名密钥
         */
        public String getSecret() {
            return this.secret;
        }

        /**
         * 设置jwt签名密钥
         */
        public void setSecret(final String secret) {
            this.secret = secret;
        }

        /**
         * 获取JWT加密密钥(默认为AES算法）
         */
        public String getCryptoKey() {
            return this.cryptoKey;
        }

        /**
         * 设置JWT加密密钥(默认为AES算法）
         */
        public void setCryptoKey(final String cryptoKey) {
            this.cryptoKey = cryptoKey;
        }

        /**
         * 获取排除jwt拦截的url pattern(ant pattern 表达式)，如果多个pattern用英文逗号,分隔
         *
         * @see org.springframework.util.AntPathMatcher
         */
        public String getExcludePathPatterns() {
            return this.excludePathPatterns;
        }

        /**
         * 设置排除jwt拦截的url pattern(ant pattern 表达式) 逗号,分隔
         */
        public void setExcludePathPatterns(final String excludePathPatterns) {
            this.excludePathPatterns = excludePathPatterns;
        }

        /**
         * 获取JWT加密算法名称(默认为AES）
         */
        public String getCryptoAlgorithm() {
            return this.cryptoAlgorithm;
        }

        /**
         * 设置JWT加密算法名称(默认为AES）
         **/
        public void setCryptoAlgorithm(final String cryptoAlgorithm) {
            this.cryptoAlgorithm = cryptoAlgorithm;
        }
    }
}
