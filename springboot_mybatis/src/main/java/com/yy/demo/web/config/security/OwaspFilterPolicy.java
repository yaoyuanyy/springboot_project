package com.okcoin.commons.security.xss;

import org.owasp.encoder.Encode;

/**
 * 基于@see org.owasp.encoder.Encode 实现的XssFilter
 *
 * @author okcoin-team
 * @date 2017/11/9
 * @version $Id: $Id
 */
public class OwaspFilterPolicy implements FilterPolicy {
    /** {@inheritDoc} */
    @Override
    public String filter(final String input) {
        return Encode.forHtmlContent(input);
    }
}
