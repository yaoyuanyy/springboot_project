package com.yy.demo.web.config.security;

import org.owasp.encoder.Encode;

/**
 * XssFilter
 *
 * 基于@see org.owasp.encoder.Encode实现
 *
 */
public class OwaspFilterPolicy implements FilterPolicy {
    /** {@inheritDoc} */
    @Override
    public String filter(final String input) {
        return Encode.forHtmlContent(input);
    }
}
