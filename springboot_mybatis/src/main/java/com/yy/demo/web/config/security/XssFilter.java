package com.yy.demo.web.config.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * XSS过滤器
 * @see <a href="https://en.wikipedia.org/wiki/Cross-site_scripting">XSS</a>
 */
@Slf4j
public class XssFilter extends OncePerRequestFilter {

    public final static String FILTER_POLICY = "filterPolicy";
    public final static String EXCLUDE_URL_PATTERNS = "excludeUrlPatterns";


    private FilterPolicy filterPolicy = null;
    private String excludeUrlPatterns = null;

    @Override
    protected void initFilterBean() throws ServletException {
        log.info("XssFilter initFilterBean --- ");
        final String policyName = this.getFilterConfig().getInitParameter(FILTER_POLICY);
        this.excludeUrlPatterns = this.getFilterConfig().getInitParameter(EXCLUDE_URL_PATTERNS);
        this.filterPolicy = this.createFilterPolicy(policyName);
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest httpServletRequest,
                                    final HttpServletResponse httpServletResponse,
                                    final FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(
                new XssHttpServletRequestWrapper(httpServletRequest, this.filterPolicy, this.excludeUrlPatterns),
                httpServletResponse
        );
    }

    private FilterPolicy createFilterPolicy(final String policyName) {
        return new OwaspFilterPolicy();
    }
}
