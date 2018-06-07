package com.yy.demo.web.config;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.yy.demo.web.config.security.SecurityProperties;
import com.yy.demo.web.config.security.XssFilter;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/5/26 at 下午10:07
 */
@Configuration
@EnableConfigurationProperties({SecurityProperties.class})
public class WebMvcConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware{



    private ApplicationContext applicationContext;

    @Resource
    private SecurityProperties securityProperties;

    /**
     * 配置XssFilter
     */
    @Bean
    public FilterRegistrationBean xssFilterRegistrationBean() {
        final String[] urlPatterns = Iterables.toArray(Splitter.on(",")
                .omitEmptyStrings()
                .trimResults()
                .split(this.securityProperties.getXss().getUrlPatterns()), String.class);

        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new XssFilter());
        registrationBean.addInitParameter(XssFilter.FILTER_POLICY,
                this.securityProperties.getXss().getPolicy());
        registrationBean.addInitParameter(XssFilter.EXCLUDE_URL_PATTERNS,
                this.securityProperties.getXss().getExcludeUrlPatterns());
        registrationBean.addUrlPatterns(urlPatterns);
        registrationBean.setName("xssFilter");
        return registrationBean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
