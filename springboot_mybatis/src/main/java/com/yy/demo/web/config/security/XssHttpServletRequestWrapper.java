package com.yy.demo.web.config.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * XSS过滤器http请求主要处理类包装类
 *
 */
@Slf4j
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    private final FilterPolicy filterPolicy;
    private final String excludeUrlPatterns;

    public XssHttpServletRequestWrapper(final HttpServletRequest servletRequest,
                                        final FilterPolicy filterPolicy,
                                        final String excludeUrlPatterns) {
        super(servletRequest);
        this.filterPolicy = filterPolicy;
        this.excludeUrlPatterns = excludeUrlPatterns;
    }

    @Override
    public String[] getParameterValues(final String parameter) {
        final String[] values = super.getParameterValues(parameter);
        if (values == null || this.isExcludedUrl(this.getRequestURL().toString())) {
            return values;
        }

        final int count = values.length;
        final String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = this.cleanXSS(values[i]);
        }
        return encodedValues;

    }


    @Override
    public String getParameter(final String parameter) {
        final String value = super.getParameter(parameter);
        if (value == null || this.isExcludedUrl(this.getRequestURL().toString())) {
            return value;
        }
        return this.cleanXSS(value);
    }


    @Override
    public String getHeader(final String name) {
        final String value = super.getHeader(name);
        if (value == null || this.isExcludedUrl(this.getRequestURL().toString())) {
            return value;
        }
        return this.cleanXSS(value);
    }

    private String cleanXSS(final String value) {
        final String filteredValue = this.filterPolicy.filter(value);
        log.debug("Xss Filter raw:{},filtered:{}", value, filteredValue);
        return filteredValue;
    }

    private boolean isExcludedUrl(final String url) {
        final String[] patterns = StringUtils.split(this.excludeUrlPatterns, ',');
        if (patterns == null || patterns.length == 0) {
            return false;
        }

        for (final String pattern : patterns) {
            if (this.pathMatcher.match(StringUtils.trim(pattern), url)) {
                log.debug("Xss Filter Excluded Url:{},pattern:{}", url, pattern);
                return true;
            }
        }
        return false;
    }
}
